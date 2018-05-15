package com.honghe.recordweb.service.frontend.timeplan;
import com.honghe.recordweb.service.frontend.devicemanager.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * Created by wzz on 2016/8/2.
 */
@Service
public class TimeplanTimerService {
    private final static Logger logger = Logger.getLogger(TimeplanTimerService.class);
    @Resource
    private TimeplanService timeplanService;
    @Resource
    private NVRCommand nvrCommand;
    @Resource
    private HostDevice hostDevice;
    @Resource
    private NVR nvr;

    private SimpleDateFormat dtTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    private SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
    /**
     * 定时器：每周一8:00向所有在线录播主机下发录像计划
     *
     */
    public void init() {
        Calendar cal = Calendar.getInstance();
        //设置定时时间
        cal.add(Calendar.DATE, 0);//表示从今天的12点开始跑起来，如果当前时间超过12点会马上跑一次，否则等12点再跑第一次
        cal.set(Calendar.DAY_OF_WEEK,2);//SUNDAY：1，MONDAY：2，……SATURDAY：7，其中2表示星期一
        cal.set(Calendar.HOUR_OF_DAY, 8);//24小时制的，12就是中午12点
        cal.set(Calendar.MINUTE, 0);//分
        cal.set(Calendar.SECOND, 0);//秒
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                setTimeplan();
            }
        }, cal.getTime(),86400000*7);
    }
    /**
     * 向所有录播主机下发录像计划
     * update by zlj on 2018/04/12
     *
     */
    private void setTimeplan()
    {
        Calendar calendar = Calendar.getInstance();
        List<Object[]> timeplanHostList = timeplanService.getTimeplanHostList();
        if(!timeplanHostList.isEmpty())
        {
            //ip串拼接，为批量获取设备在离线状态做准备
            StringBuilder hostIpStr = new StringBuilder();
            for(int i = 0; i < timeplanHostList.size(); i++){
                String hostIp = timeplanHostList.get(i)[0].toString();
                hostIpStr.append(hostIp);
                hostIpStr.append(",");
            }
            if (hostIpStr.length()>0&&hostIpStr.charAt(hostIpStr.length()-1)==','){
                hostIpStr = hostIpStr.deleteCharAt(hostIpStr.length()-1);
            }
            //批量查询设备在离线状态
            Map<String,String> statusMap = new HashMap<>();
            List<Map<String, String>> deviceStatus = hostDevice.getDevicesStatus(hostIpStr.toString());
            //将设备的状态信息存入map中，key为ip，value为状态
            for (Map<String,String> status :deviceStatus){
                statusMap.put(status.get("ip"),status.get("deviceStatus"));
            }

            for (int i = 0; i < timeplanHostList.size(); i++) {
                int hostId = Integer.parseInt(timeplanHostList.get(i)[1].toString());
                String hostIp = timeplanHostList.get(i)[0].toString();
                if(statusMap.containsKey(hostIp)) {
                    boolean isOnline = statusMap.get(hostIp).equals("Online") ? true : false;
                    if (isOnline) {
                        List<Object[]> timePlanList = timeplanService.getTimeplanByHost(hostId, calendar.getTime());
                        if (!timePlanList.isEmpty()) {
                            int flag = timeplanService.setTimePlan(hostIp, timePlanList, hostId, nvrCommand);
                            if (flag == 1) {
                                logger.error(hostIp + "已于" + dtTime.format(calendar.getTime()) + "定时下发录像计划成功");
                            } else {
                                logger.error(hostIp + "于" + dtTime.format(calendar.getTime()) + "定时下发录像计划失败");
                            }
                        }
                        //add by xinye
                        //如果数据库中数据为空，则清空设备上的计划
                        else {
                            nvrCommand.delAllPlanCommand(hostId, hostIp);
                        }
                        //add by xinye end
                    } else {
                        logger.error(hostIp + "于" + dtTime.format(calendar.getTime()) + "离线，未定时下发录像计划");
                    }
                }else {
                    logger.error(hostIp + "于" + dtTime.format(calendar.getTime()) + "状态未知，未定时下发录像计划");
                }
            }
        }

    }
}
