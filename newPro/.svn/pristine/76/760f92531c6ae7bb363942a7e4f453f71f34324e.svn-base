package com.honghe.recordweb.service.frontend.device;

import com.honghe.recordhibernate.dao.DeviceDao;
import com.honghe.recordweb.service.frontend.devicemanager.HostDevice;
import com.honghe.recordweb.service.frontend.hostgroup.HostgroupService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by lch on 2015/6/27.（跨库查询）
 *
 * update by zlj on 2018/04/12
 */

@Service("hongheDeviceService")
public class HongheDeviceService {
    @Resource
    private DeviceDao deviceDao;
    @Resource
    private HostDevice hostDevice;

    private final static Logger logger = Logger.getLogger(HostgroupService.class);

    /**
     * 通过id获取设备主机信息(由之前向设备服务请求接口获取改为直接请求设备服务数据库表请求)
     * @param hostId
     * @return
     */
    public Map getHostInfoById(String hostId) {
        Map value = new HashMap();
        try {
            value = deviceDao.getHostInfoById(hostId);
            logger.debug("获取设备信息为："+value);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("通过设备id获取设备信息异常",e);
        }
        return !value.isEmpty() ? value : null;
    }

    /**
     * 根据ip获取设备信息
     *
     * @param ip
     * @return
     */
    public  Map<String, Object> getHostInfoByIp( String ip) {
        Map<String, Object> hostInfo = Collections.EMPTY_MAP;
        try {
            hostInfo = deviceDao.getHostInfoByIp(ip);
            logger.debug("获取设备信息为："+hostInfo);
        } catch (Exception e) {
            logger.error("通过设备ip获取设备信息异常",e);
        }
        return hostInfo;
    }

    /**
     * 获取某设备类型的设备数量
     * @param deviceType 设备类型
     * @return
     */
    public int getHostCount(String deviceType){
        int re_value = 0;
        try {
            re_value = deviceDao.getHostCount(deviceType);
            logger.debug("获取"+deviceType+"设备数量为："+re_value);
        } catch (Exception e) {
            logger.error("获取设备数量异常",e);
        }
        return re_value;
    }

    /**
     * update by zlj on 2018/04/12
     * 通过设备类型获取设备信息
     */
    public List<Map<String, Object>> getHostInfoByType(String deviceType){
        List<Map<String, String>> hostList = deviceDao.getHostByType(deviceType);
        List<Map<String, Object>> re_value = new ArrayList<Map<String, Object>>();
        StringBuilder hostIpStr = new StringBuilder();
        for (Map<String, String> obj :hostList){
            hostIpStr.append(obj.get("host_ipaddr"));
            hostIpStr.append(",");
        }
        if (hostIpStr.length()>0&&hostIpStr.charAt(hostIpStr.length()-1)==','){
            hostIpStr = hostIpStr.deleteCharAt(hostIpStr.length()-1);
        }
        Map<String,String> statusMap = new HashMap<>();
        //批量查询设备在离线状态
        List<Map<String, String>> devicesStatus = hostDevice.getDevicesStatus(hostIpStr.toString());

        //将设备的状态信息存入map中，key为ip，value为状态
        for (Map<String,String> status :devicesStatus){
            statusMap.put(status.get("ip"),status.get("deviceStatus"));
        }

        for (Map<String, String> obj : hostList) {
            Map<String, Object> host = new LinkedHashMap<String, Object>();
            host.put("id", obj.get("host_id"));
            host.put("name", obj.get("host_name"));
            host.put("token", obj.get("host_ipaddr").toString());
            host.put("host_ip", obj.get("host_ipaddr"));
            host.put("host_desc", obj.get("host_desc"));

            host.put("host_username", obj.get("host_username"));
            host.put("host_password", obj.get("host_password"));
            host.put("host_serialno", obj.get("serialno"));
            host.put("type", obj.get("dtype_id"));
            host.put("dspec", obj.get("dspec_id"));

            host.put("status", statusMap.get(obj.get("host_ipaddr")));
            host.put("dspecid", obj.get("dspec_id"));
            host.put("dtype_name", obj.get("dtype_name"));
            host.put("host_port", obj.get("host_port"));
            host.put("host_channel", obj.get("host_channel"));
            re_value.add(host);
        }
        return re_value;
    }
//    private final static Logger logger = Logger.getLogger(HongheDeviceService.class);
//
//    //设备发现
//    public String discover() {
//        String re_value = "";
//        try {
//            DeviceServiceClient deviceServiceClient = HongheDeviceServiceFactory.getDeviceServiceClient();
//            Map<String, String> params = new HashMap<>();
//            params.put("deviceType", "");
//            Result result = deviceServiceClient.execute(CommonName.METHOD_TYPE_DEVICE, DeviceServiceClient.Method.Device_Discover, params);
//            if (result.getCode() == 0) {
//                if (!CommonName.DEVICE_TYPE_SCREEN.equals(result.getValue())) {
//                    Map values = ((Map) result.getValue());
//                    re_value = values.isEmpty() ? "" : values.get("discoverCount").toString();
//                }
//            }
//        } catch (Exception e) {
//            logger.error("", e);
//        }
//        return re_value;
//    }
}
