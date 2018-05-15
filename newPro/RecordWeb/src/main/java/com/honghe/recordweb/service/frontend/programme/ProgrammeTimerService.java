package com.honghe.recordweb.service.frontend.programme;

import antlr.PythonCodeGenerator;
import com.honghe.recordhibernate.dao.ProgrammeDao;
import com.honghe.recordhibernate.entity.Host;
import com.honghe.recordhibernate.entity.Programme;
import com.honghe.recordweb.service.frontend.device.HongheDeviceService;
import com.honghe.recordweb.service.frontend.devicemanager.ComputerCommand;
import com.honghe.recordweb.service.frontend.devicemanager.HostDevice;
import com.honghe.recordweb.service.frontend.hostgroup.HostgroupService;
import com.honghe.recordweb.service.frontend.syslog.SyslogService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lyx on 2015-08-22.
 */
@Service
public class ProgrammeTimerService {

    private final static Logger logger = Logger.getLogger(ProgrammeTimerService.class);

    private final String CHANNEL_TYPE_DTV = "数字电视";
    private final String CHANNEL_TYPE_ATV = "模拟电视";

    public HashMap<String, Map<String, Timer>> getProgrammeTimerList() {
        return programmeTimerList;
    }

    public void setProgrammeTimerList(HashMap<String, Map<String, Timer>> programmeTimerList) {
        this.programmeTimerList = programmeTimerList;
    }

    private HashMap<String, Map<String, Timer>> programmeTimerList = new HashMap<String, Map<String, Timer>>();

    @Resource
    private ProgrammeDao programmeDao;

    @Resource
    private ComputerCommand computerCommand;
    @Resource
    private SyslogService syslogService;
    @Resource
    private HongheDeviceService hongheDeviceService;
    @Resource
    private HostDevice hostDevice;

    private SimpleDateFormat dtTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    private SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式

    //@PostConstruct

    /**
     * 初始化
     */
    public void init() {
        try {
            HashMap<String, Map<String, Timer>> tmp = new HashMap<String, Map<String, Timer>>();
            List<Programme> programmeList = programmeDao.getProgrammeList();
            if (programmeList != null && !programmeList.isEmpty()) {
                for (final Programme programme : programmeList) {
                    Map<String, Timer> timerMap = new HashMap<String, Timer>();
                    final HashMap<String, Integer> hosts = programmeDao.getProgrammeHosts(programme);
                    if (hosts != null && !hosts.isEmpty()) {
                        final Map<String, Object> taskMap = getTask(programme);
                        Timer timer = new Timer();
                        if (taskMap.get("type").toString().equals("single")) {
                            //如果设置的时间早于当前时间，不执行计划
                            if (!taskMap.get("intervalTime").toString().equals("-1")) {
                                timer.schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        execCommand(hosts,programme,"single");
                                    }
                                }, dtTime.parse(dtTime.format(taskMap.get("firstTime"))));
                            }
                        }
                        //如果是每月执行，间隔为每天判断当前日期是否为计划日期
                        else if (taskMap.get("type").toString().equals("month")) {
                            timer.scheduleAtFixedRate(new TimerTask() {
                                @Override
                                public void run() {
                                    Date nowOnTask = new Date();
                                    Calendar cld = Calendar.getInstance();
                                    cld.setTime(nowOnTask);
                                    int currentDayofMonth = cld.get(Calendar.DAY_OF_MONTH);
                                    if (currentDayofMonth == programme.getProDate()) {
                                        execCommand(hosts,programme,"month");
                                    }
                                }
                            }, dtTime.parse(dtTime.format(taskMap.get("firstTime"))), Long.parseLong(taskMap.get("intervalTime").toString()));
                        } else {
                            timer.scheduleAtFixedRate(new TimerTask() {
                                @Override
                                public void run() {
                                    execCommand(hosts,programme,taskMap.get("type").toString());
                                }
                            }, dtTime.parse(dtTime.format(taskMap.get("firstTime"))), Long.parseLong(taskMap.get("intervalTime").toString()));
                        }
                        timerMap.put("" + programme.getProId(), timer);
                        tmp.put("" + programme.getProId(), timerMap);
                        this.programmeTimerList.putAll(tmp);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("执行计划启动异常", e);
        }
    }
    /*
     * update by zlj on 2018/04/12
     * 批量循环向设备下发命令
     */
    private void execCommand(HashMap<String, Integer> hosts,Programme programme,String type)
    {
        if (hosts != null && !hosts.isEmpty()) {
            String logContent = "";
            if(type.equals("single"))
            {
                logContent = "执行了单次定时切换节目计划";
            }
            else if(type.equals("month"))
            {
                logContent = "执行了每月定时切换节目计划";
            }
            else if(type.equals("month"))
            {
                logContent = "执行了每周定时切换节目计划";
            }
            else
            {
                logContent = "执行了每天定时切换节目计划";
            }
            Iterator iter = hosts.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String hostid = entry.getKey().toString();
                Map<String, Object> hostMap = hongheDeviceService.getHostInfoById(hostid);
                if (programme.getProType().equals(CHANNEL_TYPE_ATV) || programme.getProType().equals("ATV")) {
                    programme.setProType("ATV");
                    //添加切换频道的操作
                    computerCommand.atvOrDtv(hostMap.get("host_ipaddr").toString(), programme.getProSingnal(), programme.getProType());
                    syslogService.addDeviceLog(hostMap.get("host_ipaddr").toString(), dtTime.format(new Date()) + logContent, "PLAN");
                } else {
                    programme.setProType("DTV");
                    //添加切换频道的操作
                    computerCommand.atvOrDtv(hostMap.get("host_ipaddr").toString(), programme.getProSingnal(), programme.getProType());
                    syslogService.addDeviceLog(hostMap.get("host_ipaddr").toString(), dtTime.format(new Date()) + logContent, "PLAN");
                }
            }
        }
    }
    /*
    * 获取循环定时计划的任务开始时间、间隔时间
    */
    private Map<String, Object> getTask(Programme programme) {
        Map<String, Object> taskMap = new HashMap<String, Object>();
        try {
            Date now = new Date();
            Date firstTime = now;
            Date date = dt.parse(dt.format(now));
            Calendar cld = Calendar.getInstance();
            //单次
            if (programme.getProLoop() == 0) {
                if (programme.getProSingletime().getTime() >= now.getTime()) {
                    taskMap.put("firstTime", programme.getProSingletime());
                    taskMap.put("intervalTime", 0);
                    taskMap.put("type", "single");
                } else {
                    taskMap.put("firstTime", programme.getProSingletime());
                    taskMap.put("intervalTime", -1);
                    taskMap.put("type", "single");
                }
            }
            //每天
            else if (programme.getProLooptype().equals("day")) {
                firstTime = dtTime.parse(dt.format(now) + " " + programme.getProTime() + ":00");
                //如果执行时间早于当前时间，从第二天开始执行
                if (firstTime.getTime() < now.getTime()) {
                    cld.setTime(date);
                    cld.add(Calendar.DATE, 1);
                    firstTime = dtTime.parse(dt.format(cld.getTime()) + " " + programme.getProTime() + ":00");
                }
                taskMap.put("firstTime", firstTime);
                taskMap.put("intervalTime", 24 * 3600 * 1000);
                taskMap.put("type", "day");
            }
            //每周
            else if (programme.getProLooptype().equals("week")) {
                cld.setTime(date);
                int currentDayofWeek = cld.get(Calendar.DAY_OF_WEEK);
                switch (currentDayofWeek) {
                    case 1:
                        currentDayofWeek = 7;
                        break;
                    case 2:
                        currentDayofWeek = 1;
                        break;
                    case 3:
                        currentDayofWeek = 2;
                        break;
                    case 4:
                        currentDayofWeek = 3;
                        break;
                    case 5:
                        currentDayofWeek = 4;
                        break;
                    case 6:
                        currentDayofWeek = 5;
                        break;
                    case 7:
                        currentDayofWeek = 6;
                        break;
                }
                //如果计划周几早于当前周几，从下周开始执行
                if (programme.getProWeek() < currentDayofWeek) {
                    cld.add(Calendar.DATE, (7 - currentDayofWeek + programme.getProWeek()));
                    firstTime = dtTime.parse(dt.format(cld.getTime()) + " " + programme.getProTime() + ":00");
                }
                //如果计划周几等于当前周几，判断当天具体时间
                else if (programme.getProWeek() == currentDayofWeek) {
                    firstTime = dtTime.parse(dt.format(now) + " " + programme.getProTime() + ":00");
                    if (firstTime.getTime() < now.getTime()) {
                        cld.setTime(date);
                        cld.add(Calendar.DATE, 7 - currentDayofWeek + programme.getProWeek());
                        firstTime = dtTime.parse(dt.format(cld.getTime()) + " " + programme.getProTime() + ":00");
                    }
                } else {
                    cld.add(Calendar.DATE, (programme.getProWeek() - currentDayofWeek));
                    firstTime = dtTime.parse(dt.format(cld.getTime()) + " " + programme.getProTime() + ":00");
                }
                taskMap.put("firstTime", firstTime);
                taskMap.put("intervalTime", 7 * 24 * 3600 * 1000);
                taskMap.put("type", "week");
            }
            //每周
            else if (programme.getProLooptype().equals("month")) {
                cld.setTime(date);
                int currentDayofMonth = cld.get(Calendar.DAY_OF_MONTH);
                //如果计划日期早于当前日期，从下月开始执行
                if (programme.getProDate() < currentDayofMonth) {
                    cld.add(Calendar.MONTH, 1);
                    firstTime = dtTime.parse(dt.format(cld.getTime()) + " " + programme.getProTime() + ":00");
                }
                //如果计划周几等于当前周几，判断当天具体时间
                else if (programme.getProDate() == currentDayofMonth) {
                    firstTime = dtTime.parse(dt.format(now) + " " + programme.getProTime() + ":00");
                    if (firstTime.getTime() < now.getTime()) {
                        cld.setTime(date);
                        cld.add(Calendar.MONTH, 1);
                        firstTime = dtTime.parse(dt.format(cld.getTime()) + " " + programme.getProTime() + ":00");
                    }
                } else {
                    cld.add(Calendar.DATE, (programme.getProDate() - currentDayofMonth));
                    firstTime = dtTime.parse(dt.format(cld.getTime()) + " " + programme.getProTime() + ":00");
                }
                taskMap.put("firstTime", firstTime);
                taskMap.put("intervalTime", 24 * 3600 * 1000);
                taskMap.put("type", "month");
            }
            return taskMap;
        } catch (Exception e) {
            logger.error("获取循环定时计划的任务开始时间、间隔时间异常", e);
            return null;
        }
    }

    /*
    * 删除定时计划时，删除有关该计划的所有定时器
    * */
    public boolean delTimerbyProgramme(int pid) {
        try {
            if (this.programmeTimerList != null && !this.programmeTimerList.isEmpty()) {
                HashMap<String, Map<String, Timer>> policyTmp = new HashMap<String, Map<String, Timer>>();
                policyTmp.putAll(this.programmeTimerList);
                Iterator iter = this.programmeTimerList.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    String policyid = entry.getKey().toString();
                    //如果存在该定时计划，逐个取消定时器，并在链表中删除该定时计划对应的所有定时器
                    if (policyid.equals("" + pid)) {
                        HashMap<String, Timer> timerHashMap = new HashMap<String, Timer>();
                        timerHashMap = (HashMap<String, Timer>) entry.getValue();
                        if (timerHashMap != null && !timerHashMap.isEmpty()) {
                            Iterator timerIter = timerHashMap.entrySet().iterator();
                            while (timerIter.hasNext()) {
                                Map.Entry timerEntry = (Map.Entry) timerIter.next();
                                Timer timer = (Timer) timerEntry.getValue();
                                timer.cancel();
                            }
                        }
                        policyTmp.remove(policyid);
                    }
                }
                this.programmeTimerList = policyTmp;
            }
            return true;
        } catch (Exception e) {
            logger.error("删除定时计划时，删除有关该计划的所有定时器", e);
            return false;
        }
    }

    /*
    * 添加定时计划时，添加将该定时计划有关的定时器
    * */
    public boolean addTimerbyProgramme(final Programme programme, String hostStr) {
        try {
            HashMap<String, Map<String, Timer>> tmp = new HashMap<String, Map<String, Timer>>();
            if (!hostStr.equals("")) {
                Map<String, Timer> timerMap = new HashMap<String, Timer>();
                String[] hostsArr = hostStr.split(",");
                final HashMap<String,Integer> _hosts = new HashMap<>();
                for (int i = 0; i < hostsArr.length; i++) {
                    String hostid = hostsArr[i];
                    _hosts.put(hostid,Integer.parseInt(hostid));
                }
                final HashMap<String ,Integer> hosts = _hosts;
                    final Map<String, Object> taskMap = getTask(programme);
                    Timer timer = new Timer();
                    if (taskMap.get("type").toString().equals("single")) {
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                execCommand(hosts,programme,"single");
                            }
                        }, dtTime.parse(dtTime.format(taskMap.get("firstTime"))));
                    }
                    //如果是每月执行，间隔为每天判断当前日期是否为计划日期
                    else if (taskMap.get("type").toString().equals("month")) {
                        timer.scheduleAtFixedRate(new TimerTask() {
                            @Override
                            public void run() {
                                Date nowOnTask = new Date();
                                Calendar cld = Calendar.getInstance();
                                cld.setTime(nowOnTask);
                                int currentDayofMonth = cld.get(Calendar.DAY_OF_MONTH);
                                if (currentDayofMonth == programme.getProDate()) {
                                    execCommand(hosts,programme,"month");
                                }
                            }
                        }, dtTime.parse(dtTime.format(taskMap.get("firstTime"))), Long.parseLong(taskMap.get("intervalTime").toString()));
                    } else {
                        timer.scheduleAtFixedRate(new TimerTask() {
                            @Override
                            public void run() {
                                execCommand(hosts,programme,taskMap.get("type").toString());
                            }
                        }, dtTime.parse(dtTime.format(taskMap.get("firstTime"))), Long.parseLong(taskMap.get("intervalTime").toString()));
                    }
                    timerMap.put("" + programme.getProId(), timer);
                tmp.put("" + programme.getProId(), timerMap);
                this.programmeTimerList.putAll(tmp);
            }
            return true;
        } catch (Exception e) {
            logger.error("添加定时计划时，添加将该定时计划有关的定时器异常", e);
            return false;
        }
    }

    /*
    * 删除班级时，删除有关该班级的所有定时器
    * */
    public boolean delTimerbyHost(int hid) {
        try {
            if (this.programmeTimerList != null && !this.programmeTimerList.isEmpty()) {
                HashMap<String, Map<String, Timer>> programmeTmp = new HashMap<String, Map<String, Timer>>();
                programmeTmp.putAll(this.programmeTimerList);
                Iterator iter = this.programmeTimerList.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    String programmeid = entry.getKey().toString();
                    Programme programme = new Programme();
                    programme.setProId(Integer.parseInt(programmeid));
                    HashMap<String,Integer> hosts = programmeDao.getProgrammeHosts(programme);
                    if(hosts.size() == 0 || (hosts.size() == 1 && hosts.containsKey(hid+""))){
                        programmeTmp.remove(programmeid);
                        delTimerbyProgramme(Integer.parseInt(programmeid));
                    }
                }
                this.programmeTimerList = programmeTmp;
            }
            return true;
        } catch (Exception e) {
            logger.error("删除班级时，删除有关该班级的所有定时器", e);
            return false;
        }
    }

}
