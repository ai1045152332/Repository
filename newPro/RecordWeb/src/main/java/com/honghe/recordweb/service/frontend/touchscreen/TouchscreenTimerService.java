package com.honghe.recordweb.service.frontend.touchscreen;

import com.honghe.recordhibernate.dao.TouchscreenDao;
import com.honghe.recordhibernate.entity.Touchscreen;
import com.honghe.recordweb.service.frontend.device.HongheDeviceService;
import com.honghe.recordweb.service.frontend.devicemanager.ComputerCommand;
import com.honghe.recordweb.service.frontend.devicemanager.HostDevice;
import com.honghe.recordweb.service.frontend.devicemanager.WhiteboardCommand;
import com.honghe.recordweb.service.frontend.syslog.SyslogService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by sky on 2016/5/19.
 */
@Service
public class TouchscreenTimerService {
    private final static Logger logger = Logger.getLogger(TouchscreenTimerService.class);
    @Resource
    TouchscreenDao touchscreenDao;
    @Resource
    private HostDevice hostDevice;
    @Resource
    private HongheDeviceService hongheDeviceService;
    @Resource
    private ComputerCommand computerCommand;
    @Resource
    private SyslogService syslogService;
    @Resource
    private WhiteboardCommand whiteboardCommand;
    private SimpleDateFormat dtTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    private SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式

    private HashMap<String, Map<String, Timer>> touchscreenTimerList = new HashMap<String, Map<String, Timer>>();

    private final String  Screenlock="Screenlock";
    private final String  Screenunlock="Screenunlock";

    private final String  Lock="lock";
    private final String  Unlock="unlock";

    public HashMap<String, Map<String, Timer>> getTouchscreenTimerList() {
        return touchscreenTimerList;
    }

    public void setTouchscreenTimerList(HashMap<String, Map<String, Timer>> touchscreenTimerList) {
        this.touchscreenTimerList = touchscreenTimerList;
    }

    /**
     * 初始化
     */
    public void init() {
        try {
            HashMap<String, Map<String, Timer>> tmp = new HashMap<String, Map<String, Timer>>();
            List<Touchscreen> touchScreenList = touchscreenDao.getTouchScreenlist();
            if (touchScreenList != null && !touchScreenList.isEmpty()) {
                for (final Touchscreen touchscreen: touchScreenList) {
                    Map<String, Timer> timerMap = new HashMap<String, Timer>();
                    final HashMap<String, Integer> hosts = touchscreenDao.getTouchScreenHosts(touchscreen);
                    if (hosts != null && !hosts.isEmpty()) {
                        final Map<String, Object> taskMap = getTask(touchscreen);;
                        Timer timer = new Timer();
                        if (taskMap.get("type").toString().equals("single")) {
                            //如果设置的时间早于当前时间，不执行计划
                            if (!taskMap.get("intervalTime").toString().equals("-1")) {
                                timer.schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        execCommand(hosts,touchscreen,"single");
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
                                    if (currentDayofMonth == touchscreen.getTouchDate()) {
                                        execCommand(hosts,touchscreen,"month");
                                    }
                                }
                            }, dtTime.parse(dtTime.format(taskMap.get("firstTime"))), Long.parseLong(taskMap.get("intervalTime").toString()));
                        } else {
                            timer.scheduleAtFixedRate(new TimerTask() {
                                @Override
                                public void run() {
                                    execCommand(hosts,touchscreen,taskMap.get("type").toString());
                                }
                            }, dtTime.parse(dtTime.format(taskMap.get("firstTime"))), Long.parseLong(taskMap.get("intervalTime").toString()));
                        }
                        timerMap.put("" + touchscreen.getTid(), timer);
                        tmp.put("" + touchscreen.getTid(), timerMap);
                        this.touchscreenTimerList.putAll(tmp);
                    }
                }
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    /*
     * update by zlj on 2018/04/12
     * 批量循环向设备下发命令
     */
    private void execCommand(HashMap<String, Integer> hosts,Touchscreen touchscreen,String type)
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
                if (touchscreen.getTouchtype() == 0) {
                    if (hostMap != null && !hostMap.isEmpty()) {
                        if("hhtc".equals(touchscreen.gettDevicetype())) {
                            computerCommand.changeTouchScreen(hostMap.get("host_ipaddr").toString(), Screenlock);
                        }else if ("hhtwb".equals(touchscreen.gettDevicetype())){
                            whiteboardCommand.setBoardOneKeyLock(hostMap.get("host_ipaddr").toString(), Lock);
                        }
                        syslogService.addDeviceLog(hostMap.get("host_ipaddr").toString(), dtTime.format(new Date()) + logContent +"锁定计划", "PLAN");
                    }
                } else {
                    if (hostMap != null && !hostMap.isEmpty()) {
                        if("hhtc".equals(touchscreen.gettDevicetype())) {
                            computerCommand.changeTouchScreen(hostMap.get("host_ipaddr").toString(), Screenunlock);
                        }else if ("hhtwb".equals(touchscreen.gettDevicetype())){
                            whiteboardCommand.setBoardOneKeyLock(hostMap.get("host_ipaddr").toString(), Unlock);
                        }
                        syslogService.addDeviceLog(hostMap.get("host_ipaddr").toString(), dtTime.format(new Date()) + logContent +"解锁计划", "PLAN");
                    }
                }
            }
        }
    }
    /*
       * 获取循环定时计划的任务开始时间、间隔时间
       */
    private Map<String, Object> getTask(Touchscreen touchscreen) {
        Map<String, Object> taskMap = new HashMap<String, Object>();
        try {
            Date now = new Date();
            Date firstTime = now;
            Date date = dt.parse(dt.format(now));
            Calendar cld = Calendar.getInstance();
            //单次

            if (touchscreen.getTouchLoop() == 0) {
                if (touchscreen.gettSingletime().getTime() >= now.getTime()) {
                    taskMap.put("firstTime", touchscreen.gettSingletime());
                    taskMap.put("intervalTime", 0);
                    taskMap.put("type", "single");
                } else {
                    taskMap.put("firstTime", touchscreen.gettSingletime());
                    taskMap.put("intervalTime", -1);
                    taskMap.put("type", "single");
                }
            }
            //每天

            else if (touchscreen.gettLooptype().equals("day")) {
                firstTime = dtTime.parse(dt.format(now) + " " + touchscreen.getTouchTime() + ":00");
                //如果执行时间早于当前时间，从第二天开始执行
                if (firstTime.getTime() < now.getTime()) {
                    cld.setTime(date);
                    cld.add(Calendar.DATE, 1);
                    firstTime = dtTime.parse(dt.format(cld.getTime()) + " " + touchscreen.getTouchTime() + ":00");
                }
                taskMap.put("firstTime", firstTime);
                taskMap.put("intervalTime", 24 * 3600 * 1000);
                taskMap.put("type", "day");
            }
            //每周
            else if (touchscreen.gettLooptype().equals("week")) {
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
                if (touchscreen.getTouchWeek() < currentDayofWeek) {
                    cld.add(Calendar.DATE, (7 - currentDayofWeek + touchscreen.getTouchWeek()));
                    firstTime = dtTime.parse(dt.format(cld.getTime()) + " " + touchscreen.getTouchTime() + ":00");
                }
                //如果计划周几等于当前周几，判断当天具体时间
                else if (touchscreen.getTouchWeek() == currentDayofWeek) {
                    firstTime = dtTime.parse(dt.format(now) + " " + touchscreen.getTouchTime() + ":00");
                    if (firstTime.getTime() < now.getTime()) {
                        cld.setTime(date);
                        cld.add(Calendar.DATE, 7 - currentDayofWeek + touchscreen.getTouchWeek());
                        firstTime = dtTime.parse(dt.format(cld.getTime()) + " " + touchscreen.getTouchTime() + ":00");
                    }
                } else {
                    cld.add(Calendar.DATE, (touchscreen.getTouchWeek() - currentDayofWeek));
                    firstTime = dtTime.parse(dt.format(cld.getTime()) + " " + touchscreen.getTouchTime() + ":00");
                }
                taskMap.put("firstTime", firstTime);
                taskMap.put("intervalTime", 7 * 24 * 3600 * 1000);
                taskMap.put("type", "week");
            }
            //每月
            else if (touchscreen.gettLooptype().equals("month")) {
                cld.setTime(date);
                int currentDayofMonth = cld.get(Calendar.DAY_OF_MONTH);
                //如果计划日期早于当前日期，从下月开始执行
                if (touchscreen.getTouchDate() < currentDayofMonth) {
                    cld.add(Calendar.MONTH, 1);
                    firstTime = dtTime.parse(dt.format(cld.getTime()) + " " + touchscreen.getTouchTime() + ":00");
                }
                //如果计划周几等于当前周几，判断当天具体时间
                else if (touchscreen.getTouchDate() == currentDayofMonth) {
                    firstTime = dtTime.parse(dt.format(now) + " " + touchscreen.getTouchTime() + ":00");
                    if (firstTime.getTime() < now.getTime()) {
                        cld.setTime(date);
                        cld.add(Calendar.MONTH, 1);
                        firstTime = dtTime.parse(dt.format(cld.getTime()) + " " + touchscreen.getTouchTime() + ":00");
                    }
                } else {
                    cld.add(Calendar.DATE, (touchscreen.getTouchDate() - currentDayofMonth));
                    firstTime = dtTime.parse(dt.format(cld.getTime()) + " " + touchscreen.getTouchTime() + ":00");
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
    * 添加定时计划时，添加该定时计划有关的定时器
    * */
    public boolean addTimerbyTouch(final Touchscreen touchscreen, String hostStr) {
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
                final Map<String, Object> taskMap = getTask(touchscreen);
                    Timer timer = new Timer();
                    if (taskMap.get("type").toString().equals("single")) {
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                execCommand(hosts,touchscreen,"single");
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
                                if (currentDayofMonth == touchscreen.getTouchDate()) {
                                    execCommand(hosts,touchscreen,"month");
                                }
                            }
                        }, dtTime.parse(dtTime.format(taskMap.get("firstTime"))), Long.parseLong(taskMap.get("intervalTime").toString()));
                    } else {
                        timer.scheduleAtFixedRate(new TimerTask() {
                            @Override
                            public void run() {
                                execCommand(hosts,touchscreen,taskMap.get("type").toString());
                            }
                        }, dtTime.parse(dtTime.format(taskMap.get("firstTime"))), Long.parseLong(taskMap.get("intervalTime").toString()));
                    }
                    timerMap.put("" + touchscreen.getTid(), timer);
                tmp.put("" + touchscreen.getTid(), timerMap);
                this.touchscreenTimerList.putAll(tmp);
            }
            return true;
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
    }
    /*
     * 删除定时计划时，删除有关该计划的所有定时器
     * */
    public boolean delTimerbyTouch(int id){
        try {
            if (this.touchscreenTimerList != null && !this.touchscreenTimerList.isEmpty()) {
                HashMap<String, Map<String, Timer>> touchTmp = new HashMap<String, Map<String, Timer>>();
                touchTmp.putAll(this.touchscreenTimerList);
                Iterator iter = this.touchscreenTimerList.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    String touchid = entry.getKey().toString();
                    //如果存在该定时计划，逐个取消定时器，并在链表中删除该定时计划对应的所有定时器
                    Touchscreen touchscreen = new Touchscreen();
                    touchscreen.setTid(Integer.parseInt(touchid));
                    HashMap<String,Integer> hosts = touchscreenDao.getTouchScreenHosts(touchscreen);
                    if(hosts.size() == 0 || (hosts.size() == 1 && hosts.containsKey(id+""))){
                        touchTmp.remove(touchid);
                        delTimerbyTouch(Integer.parseInt(touchid));
                    }
                }
                this.touchscreenTimerList = touchTmp;
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
            if (this.touchscreenTimerList != null && !this.touchscreenTimerList.isEmpty()) {
                HashMap<String, Map<String, Timer>> touchTmp = new HashMap<String, Map<String, Timer>>();
                touchTmp.putAll(this.touchscreenTimerList);
                Iterator iter = this.touchscreenTimerList.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    String touchid = entry.getKey().toString();
                    HashMap<String, Timer> timerHashMap = new HashMap<String, Timer>();
                    timerHashMap = (HashMap<String, Timer>) entry.getValue();
                    if (timerHashMap != null && !timerHashMap.isEmpty()) {
                        HashMap<String, Timer> tmp = new HashMap<String, Timer>();
                        tmp.putAll(timerHashMap);
                        Iterator timerIter = timerHashMap.entrySet().iterator();
                        while (timerIter.hasNext()) {
                            Map.Entry timerEntry = (Map.Entry) timerIter.next();
                            if (timerEntry.getKey().toString().equals("" + hid)) {
                                Timer timer = (Timer) timerEntry.getValue();
                                timer.cancel();
                                tmp.remove(timerEntry.getKey());
                            }
                        }
                        if (tmp.isEmpty()) {
                            touchTmp.remove(touchid);
                        } else {
                            touchTmp.put(touchid, tmp);
                        }
                    }
                }
                this.touchscreenTimerList = touchTmp;
            }
            return true;
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
    }
}
