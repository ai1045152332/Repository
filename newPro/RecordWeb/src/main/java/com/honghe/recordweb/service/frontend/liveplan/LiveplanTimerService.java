package com.honghe.recordweb.service.frontend.liveplan;

import com.honghe.recordweb.service.frontend.devicemanager.HostDevice;
import com.honghe.recordweb.service.frontend.devicemanager.NVR;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wzz on 2016/8/2.
 */
@Service
public class LiveplanTimerService {
    private final static Logger logger = Logger.getLogger(LiveplanTimerService.class);
    @Resource
    private LiveplanService liveplanService;
    @Resource
    private HostDevice hostDevice;
    @Resource
    private NVR nvr;

    private SimpleDateFormat dtTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    private SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
    /**
     * 定时器：每隔5分钟维护一次直播计划
     *
     */
    public void init() {
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                stopLiveplan();
                setLiveplan();
            }
        }, 1000,1000*60*2);
    }
    /**
     * 获取当前时间5分钟内的直播计划，并调用媒体转发服务，向资源平台推送rtmp流
     *
     */
    private void setLiveplan()
    {
        Calendar calendar = Calendar.getInstance();
        List<Object[]> liveplanHostList = liveplanService.getLiveplanHostList();
        if(!liveplanHostList.isEmpty())
        {
            //ip串拼接，为批量获取设备在离线状态做准备
            StringBuilder hostIpStr = new StringBuilder();
            for(int i = 0; i < liveplanHostList.size(); i++){
                String hostIp = liveplanHostList.get(i)[0].toString();
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
            logger.debug("获取的设备在离线状态信息个数为："+deviceStatus.size());
            for (int i = 0; i < liveplanHostList.size(); i++) {
                int hostId = Integer.parseInt(liveplanHostList.get(i)[1].toString());
                String hostIp = liveplanHostList.get(i)[0].toString();
                if(statusMap.containsKey(hostIp)) {
                    boolean isOnline = statusMap.get(hostIp).equals("Online") ? true : false;
                    if (isOnline) {
                        List<Object[]> livePlanList = liveplanService.getLiveplanByHost(hostId, calendar.getTime());
                        if (!livePlanList.isEmpty()) {
                            for (int j = 0; j < livePlanList.size(); j++) {
                                String channel = livePlanList.get(j)[4] == null ? "" : livePlanList.get(j)[4].toString();
                                String[] _channel = channel.split(",");
                                if (_channel.length != 0) {
                                    liveplanService.handleStream(hostIp, hostId, "start", _channel);
                                }
                            }
                            boolean flag = liveplanService.startLiveplan(hostIp, livePlanList, hostId);
                            if (flag) {
                                logger.error(hostIp + "已于" + dtTime.format(calendar.getTime()) + "定时下发直播计划成功");
                            } else {
                                logger.error(hostIp + "于" + dtTime.format(calendar.getTime()) + "定时下发直播计划失败");
                            }
                        }
                    } else {
                        logger.error(hostIp + "于" + dtTime.format(calendar.getTime()) + "离线，未定时下发录像计划");
                    }
                }else {
                    logger.error(hostIp + "于" + dtTime.format(calendar.getTime()) + "状态未知，未定时下发录像计划");
                }
            }
        }
    }
    /**
     * 获取当前时间5分钟后即将结束的直播计划，并调用媒体转发服务，终止rtsp转rtmp，关闭资源平台直播间
     *
     */
    private void stopLiveplan()
    {
        Calendar calendar = Calendar.getInstance();
        List<Object[]> liveplanHostList = liveplanService.getLiveplanHostList();
        if(!liveplanHostList.isEmpty())
        {
            for (int i = 0; i < liveplanHostList.size(); i++) {
                int hostId = Integer.parseInt(liveplanHostList.get(i)[1].toString());
                String hostIp = liveplanHostList.get(i)[0].toString();
//                boolean isOnlie = nvr.isOnline(hostIp);
//                if (isOnlie) {
                    List<Object[]> livePlanList = liveplanService.getLiveplanEndByHost(hostId, calendar.getTime(),1);
                    if (!livePlanList.isEmpty()) {
                        for(int j=0;j<livePlanList.size();j++) {
                            String channel = livePlanList.get(j)[4] == null ? "" : livePlanList.get(j)[4].toString();
                            String[] _channel = channel.split(",");
                            if(_channel.length!=0){
                                liveplanService.handleStream(hostIp, hostId, "stop", _channel);
                            }
                        }
                        boolean flag = liveplanService.stopLiveplan(hostIp, livePlanList, hostId);
                        if(flag)
                        {
                            logger.error(hostIp+"已于"+dtTime.format(calendar.getTime())+"定時結束直播计划成功");
                            System.out.println(hostIp+"已于"+dtTime.format(calendar.getTime())+"定時結束直播计划成功");
                        }
                        else
                        {
                            logger.error(hostIp+"于"+dtTime.format(calendar.getTime())+"定时結束直播计划失败");
                            System.out.println(hostIp+"于"+dtTime.format(calendar.getTime())+"定时結束直播计划失败");
                        }
                    }
//                }
//                else
//                {
//                    logger.error(hostIp+"于"+dtTime.format(calendar.getTime())+"离线，未定时下发直播计划");
//                    System.out.println(hostIp+"于"+dtTime.format(calendar.getTime())+"离线，未定时下发直播计划");
//                }
            }
        }

    }
}
