package com.honghe.recordweb.service.frontend.policy;

import com.honghe.recordhibernate.dao.PolicyDao;
import com.honghe.recordhibernate.entity.Policy;
import com.honghe.recordweb.service.frontend.device.HongheDeviceService;
import com.honghe.recordweb.service.frontend.devicemanager.ComputerCommand;
import com.honghe.recordweb.service.frontend.devicemanager.HostDevice;
import com.honghe.recordweb.service.frontend.devicemanager.WhiteboardCommand;
import com.honghe.recordweb.service.frontend.hostgroup.HostgroupService;
import com.honghe.recordweb.service.frontend.syslog.SyslogService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by wzz on 2015/3/6.
 */
@Service
public class PolicyTimerService {

    private final static Logger logger = Logger.getLogger(PolicyTimerService.class);

    public HashMap<String, Map<String, Timer>> getPolicyTimerList() {
        return policyTimerList;
    }

    public void setPolicyTimerList(HashMap<String, Map<String, Timer>> policyTimerList) {
        this.policyTimerList = policyTimerList;
    }

    private HashMap<String, Map<String, Timer>> policyTimerList = new HashMap<String, Map<String, Timer>>();

    @Resource
    private PolicyDao policyDao;
    @Resource
    private HostgroupService hostgroupService;
    @Resource
    private HongheDeviceService hongheDeviceService;
    @Resource
    private HostDevice hostDevice;
    @Resource
    private ComputerCommand computerCommand;
    @Resource
    private SyslogService syslogService;
    @Resource
    private WhiteboardCommand whiteboardCommand;

    private final String Shutdown = "ShutDown";
    private SimpleDateFormat dtTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    private SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式

    //@PostConstruct

    /**
     * todo 加注释
     */
    public void init() {
        try {
            HashMap<String, Map<String, Timer>> tmp = new HashMap<String, Map<String, Timer>>();
            List<Policy> policyList = policyDao.getPolicyList();
            if (policyList != null && !policyList.isEmpty()) {
                for (final Policy policy : policyList) {
                    Map<String, Timer> timerMap = new HashMap<String, Timer>();
                    final HashMap<String, Integer> hosts = policyDao.getPolicyHosts(policy);
                    if (hosts != null && !hosts.isEmpty()) {
                            final Map<String, Object> taskMap = getTask(policy);
                            Timer timer = new Timer();
                            if (taskMap.get("type").toString().equals("single")) {
                                //如果设置的时间早于当前时间，不执行计划
                                if (!taskMap.get("intervalTime").toString().equals("-1")) {
                                    timer.schedule(new TimerTask() {
                                        @Override
                                        public void run() {
                                            execCommand(hosts,policy,"single");
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
                                        if (currentDayofMonth == policy.getpDate()) {
                                            execCommand(hosts,policy,"month");
                                        }
                                    }
                                }, dtTime.parse(dtTime.format(taskMap.get("firstTime"))), Long.parseLong(taskMap.get("intervalTime").toString()));
                            } else {
                                timer.scheduleAtFixedRate(new TimerTask() {
                                    @Override
                                    public void run() {
                                        execCommand(hosts,policy,taskMap.get("type").toString());
                                    }
                                }, dtTime.parse(dtTime.format(taskMap.get("firstTime"))), Long.parseLong(taskMap.get("intervalTime").toString()));
                            }
                        timerMap.put("" + policy.getpId(), timer);
                        tmp.put("" + policy.getpId(), timerMap);
                        this.policyTimerList.putAll(tmp);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("", e);
        }
    }
    /*
    * update by zlj on 2018/04/12
    * 批量循环向设备下发命令
    */
    private void execCommand(HashMap<String, Integer> hosts,Policy policy,String type)
    {
        if (hosts != null && !hosts.isEmpty()) {
            String logContent = "";
            if(type.equals("single"))
            {
                logContent = "执行了单次定时";
            }
            else if(type.equals("month"))
            {
                logContent = "执行了每月定时";
            }
            else if(type.equals("month"))
            {
                logContent = "执行了每周定时";
            }
            else
            {
                logContent = "执行了每天定时";
            }
            Iterator iter = hosts.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String hostid = entry.getKey().toString();
                //  final Host host = timeplanService.getHost(Integer.parseInt(hostid));
                Map<String, Object> hostMap = hongheDeviceService.getHostInfoById(hostid);
                if (policy.getpType() == 0) {
                    if (hostMap != null && !hostMap.isEmpty()) {
                        if ("hhtc".equals(policy.getpDevicetype())) {
                            computerCommand.shutdown(hostMap.get("host_ipaddr").toString(), Shutdown);
                        } else if ("hhtwb".equals(policy.getpDevicetype())) {
                            whiteboardCommand.shutdown(hostMap.get("host_ipaddr").toString(), Shutdown);
                        }
                        syslogService.addDeviceLog(hostMap.get("host_ipaddr").toString(), dtTime.format(new Date()) + logContent + "关机计划", "PLAN");
                    }
                } else {
                    if (hostMap != null && !hostMap.isEmpty()) {
                        if ("hhtc".equals(policy.getpDevicetype())) {
                            computerCommand.start(hostMap.get("host_ipaddr").toString());
                        } else if ("hhtwb".equals(policy.getpDevicetype())) {
                            whiteboardCommand.start(hostMap.get("host_ipaddr").toString());
                        }
                        syslogService.addDeviceLog(hostMap.get("host_ipaddr").toString(), dtTime.format(new Date()) + logContent + "开机计划", "PLAN");
                    }
                }
            }
        }
    }
    /*
    * 获取循环定时计划的任务开始时间、间隔时间
    */
    private Map<String, Object> getTask(Policy policy) {
        Map<String, Object> taskMap = new HashMap<String, Object>();
        try {
            Date now = new Date();
            Date firstTime = now;
            Date date = dt.parse(dt.format(now));
            Calendar cld = Calendar.getInstance();
            //单次
            if (policy.getpLoop() == 0) {
                if (policy.getpSingletime().getTime() >= now.getTime()) {
                    taskMap.put("firstTime", policy.getpSingletime());
                    taskMap.put("intervalTime", 0);
                    taskMap.put("type", "single");
                } else {
                    taskMap.put("firstTime", policy.getpSingletime());
                    taskMap.put("intervalTime", -1);
                    taskMap.put("type", "single");
                }
            }
            //每天
            else if (policy.getpLooptype().equals("day")) {
                firstTime = dtTime.parse(dt.format(now) + " " + policy.getpTime() + ":00");
                //如果执行时间早于当前时间，从第二天开始执行
                if (firstTime.getTime() < now.getTime()) {
                    cld.setTime(date);
                    cld.add(Calendar.DATE, 1);
                    firstTime = dtTime.parse(dt.format(cld.getTime()) + " " + policy.getpTime() + ":00");
                }
                taskMap.put("firstTime", firstTime);
                taskMap.put("intervalTime", 24 * 3600 * 1000);
                taskMap.put("type", "day");
            }
            //每周
            else if (policy.getpLooptype().equals("week")) {
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
                if (policy.getpWeek() < currentDayofWeek) {
                    cld.add(Calendar.DATE, (7 - currentDayofWeek + policy.getpWeek()));
                    firstTime = dtTime.parse(dt.format(cld.getTime()) + " " + policy.getpTime() + ":00");
                }
                //如果计划周几等于当前周几，判断当天具体时间
                else if (policy.getpWeek() == currentDayofWeek) {
                    firstTime = dtTime.parse(dt.format(now) + " " + policy.getpTime() + ":00");
                    if (firstTime.getTime() < now.getTime()) {
                        cld.setTime(date);
                        cld.add(Calendar.DATE, 7 - currentDayofWeek + policy.getpWeek());
                        firstTime = dtTime.parse(dt.format(cld.getTime()) + " " + policy.getpTime() + ":00");
                    }
                } else {
                    cld.add(Calendar.DATE, (policy.getpWeek() - currentDayofWeek));
                    firstTime = dtTime.parse(dt.format(cld.getTime()) + " " + policy.getpTime() + ":00");
                }
                taskMap.put("firstTime", firstTime);
                taskMap.put("intervalTime", 7 * 24 * 3600 * 1000);
                taskMap.put("type", "week");
            }
            //每月
            else if (policy.getpLooptype().equals("month")) {
                cld.setTime(date);
                int currentDayofMonth = cld.get(Calendar.DAY_OF_MONTH);
                //如果计划日期早于当前日期，从下月开始执行
                if (policy.getpDate() < currentDayofMonth) {
                    cld.add(Calendar.MONTH, 1);
                    firstTime = dtTime.parse(dt.format(cld.getTime()) + " " + policy.getpTime() + ":00");
                }
                //如果计划周几等于当前周几，判断当天具体时间
                else if (policy.getpDate() == currentDayofMonth) {
                    firstTime = dtTime.parse(dt.format(now) + " " + policy.getpTime() + ":00");
                    if (firstTime.getTime() < now.getTime()) {
                        cld.setTime(date);
                        cld.add(Calendar.MONTH, 1);
                        firstTime = dtTime.parse(dt.format(cld.getTime()) + " " + policy.getpTime() + ":00");
                    }
                } else {
                    cld.add(Calendar.DATE, (policy.getpDate() - currentDayofMonth));
                    firstTime = dtTime.parse(dt.format(cld.getTime()) + " " + policy.getpTime() + ":00");
                }
                taskMap.put("firstTime", firstTime);
                taskMap.put("intervalTime", 24 * 3600 * 1000);
                taskMap.put("type", "month");
            }
            return taskMap;
        } catch (Exception e) {
            logger.error("", e);
            return null;
        }
    }

    /*
    * 删除定时计划时，删除有关该计划的所有定时器
    * */
    public boolean delTimerbyPolicy(int pid) {
        try {
            if (this.policyTimerList != null && !this.policyTimerList.isEmpty()) {
                HashMap<String, Map<String, Timer>> policyTmp = new HashMap<String, Map<String, Timer>>();
                policyTmp.putAll(this.policyTimerList);
                Iterator iter = this.policyTimerList.entrySet().iterator();
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
                this.policyTimerList = policyTmp;
            }
            return true;
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
    }

    /*
    * 添加定时计划时，添加将该定时计划有关的定时器
    * */
    public boolean addTimerbyPolicy(final Policy policy, String hostStr) {
        try {
            HashMap<String, Map<String, Timer>> tmp = new HashMap<String, Map<String, Timer>>();
            Map<String, Timer> timerMap = new HashMap<String, Timer>();
            if (!hostStr.equals("")) {
                String[] hostsArr = hostStr.split(",");
                final HashMap<String,Integer> _hosts = new HashMap<>();
                for (int i = 0; i < hostsArr.length; i++) {
                    String hostid = hostsArr[i];
                    _hosts.put(hostid,Integer.parseInt(hostid));
                }
                final HashMap<String ,Integer> hosts = _hosts;
                final Map<String, Object> taskMap = getTask(policy);
                Timer timer = new Timer();
                if (taskMap.get("type").toString().equals("single")) {
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            execCommand(hosts,policy,"single");
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
                            if (currentDayofMonth == policy.getpDate()) {
                                execCommand(hosts,policy,"month");
                            }
                        }
                    }, dtTime.parse(dtTime.format(taskMap.get("firstTime"))), Long.parseLong(taskMap.get("intervalTime").toString()));
                } else {
                    timer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            execCommand(hosts,policy,taskMap.get("type").toString());
                        }
                    }, dtTime.parse(dtTime.format(taskMap.get("firstTime"))), Long.parseLong(taskMap.get("intervalTime").toString()));
                }
                timerMap.put("" + policy.getpId(), timer);
                tmp.put("" + policy.getpId(), timerMap);
                this.policyTimerList.putAll(tmp);
            }
            return true;
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
    }

    /*
    * 删除班级时，删除有关该班级的所有定时器
    * */
    public boolean delTimerbyHost(int hid) {
        try {
            if (this.policyTimerList != null && !this.policyTimerList.isEmpty()) {
                HashMap<String, Map<String, Timer>> policyTmp = new HashMap<String, Map<String, Timer>>();
                policyTmp.putAll(this.policyTimerList);
                Iterator iter = this.policyTimerList.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    String policyid = entry.getKey().toString();
                    Policy policy = new Policy();
                    policy.setpId(Integer.parseInt(policyid));
                    HashMap<String,Integer> hosts = policyDao.getPolicyHosts(policy);
                    if(hosts.size() == 0 || (hosts.size() == 1 && hosts.containsKey(hid+""))){
                        policyTmp.remove(policyid);
                        delTimerbyPolicy(Integer.parseInt(policyid));
                    }
                }
                this.policyTimerList = policyTmp;
            }
            return true;
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
    }
}
