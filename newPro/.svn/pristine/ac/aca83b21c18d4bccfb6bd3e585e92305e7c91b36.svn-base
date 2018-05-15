package com.honghe.recordweb.service.frontend.devicemanager;

import com.honghe.device.sdk.DeviceServiceClient;
import com.honghe.recordhibernate.entity.Device;
import com.honghe.recordhibernate.entity.Setting;
import com.honghe.recordweb.service.frontend.device.DeviceService;
import com.honghe.recordweb.service.frontend.device.HongheDeviceService;
import com.honghe.recordweb.service.frontend.settings.SettingsService;
import com.honghe.recordweb.service.frontend.user.UserService;
import com.honghe.recordweb.util.CommonName;
import com.honghe.recordweb.util.DeviceNameUtil;
import com.honghe.recordweb.util.HongheDeviceServiceFactory;
import com.honghe.recordweb.util.UuidUtil;
import com.honghe.service.client.Result;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by zhanghongbin on 2014/11/17.
 * 设备
 */
@Component
public class NVR extends HostDevice {
    private final static Logger logger = Logger.getLogger(NVR.class);

    //发现设备，调用JAR包，获取设备列表
    @Deprecated
    public Map<String, Device> discovered() {

        return null;
    }

    @Resource
    SettingsService settingsService;
    @Resource
    UserService userService;
    @Resource
    NVRCommand nvrCommand;
    @Resource
    HongheDeviceService hongheDeviceService;
    @Resource
    DeviceService deviceService;


    /**
     * 获取在线设备
     * update by zlj on 2018/04/12
     * @return
     */
    public List<Object[]> getOnlineClasses() {

        Setting setting = settingsService.getSetting();
        String userName = null;
        String password = null;
        if (setting != null) {
            userName = setting.getNvrUsername();
            password = setting.getNvrPassword();
        }
        if (userName != null && userName.equals("")) {
            userName = null;
        }

        if (password != null && password.equals("")) {
            password = null;
        }
        List<Object[]> ipList = new ArrayList<Object[]>();
        Map<String, String> params = new HashMap<>();
        params.put("deviceType", CommonName.DEVICE_TYPE_RECOURD);
        params.put("username", userName == null ? "" : userName);
        params.put("password", password == null ? "" : password);
        Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_DEVICE, DeviceServiceClient.Method.Device_Hhrec_GetOnlineDevice, params);
        if (result != null && result.getCode() == 0 && result.getValue() != null) {
            try {
                List<List<Object>> res = (List<List<Object>>) result.getValue();
                if (res != null && res.size() > 0) {
                    for (List<Object> objects : res) {
                        //todo 暂不处理
                        Map<String, Object> hostInfo = hongheDeviceService.getHostInfoByIp(objects.get(0).toString());
                            if (hostInfo.isEmpty()) {
                                continue;
                            }
                            Object[] newObjs = new Object[]{objects.get(0).toString(), userName, password, hostInfo.get("host_id").toString(), hostInfo.get("host_name").toString()};
                            ipList.add(newObjs);
                    }
                }
            } catch (Exception e) {
                logger.error("获取在线设备异常", e);
            }
        }
        return ipList;
    }


    /**
     * 添加虚拟设备
     *
     * @param hostName
     * @param typeInt
     * @param netUrl
     * @return
     */
    public Map<String, Object> addVirtualHost(String hostName, String typeInt, String netUrl, String cameraName) {

        Map<String, Object> re_value = Collections.emptyMap();
        Map<String, String> params = new HashMap<>();
        String factory = "Virtual";

        params.put("ip", UuidUtil.getUUID());
        params.put("className", hostName);
        params.put("deviceType", "hhrec");
        params.put("typeInt", typeInt);
        params.put("system", "record");
        params.put("factory", factory);
        params.put("metString", netUrl);
        params.put("cameraString", cameraName);
        try {
            Result res = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_DEVICE, DeviceServiceClient.Method.Device_ManualVirtualDiscover, params);
            if (res.getCode() == 0 && res.getValue() != null) {
                re_value = (Map<String, Object>) res.getValue();
            }
        } catch (Exception e) {
            logger.error("添加虚拟设备异常", e);
        }
        return re_value;
    }


    /**
     * 添加外网设备
     *
     * @param ip
     * @param className
     * @param typeInt
     * @param netUrl
     * @return
     */
    public Map<String, Object> addNetHost(String ip, String className, String typeInt, String netUrl) {

        Map<String, Object> re_value = Collections.emptyMap();
        Map<String, String> params = new HashMap<>();
        String factory = "HiteTech";
        //---typeInt==2,Arec设备,4.TBox设备
        if (typeInt.equals(String.valueOf(DeviceNameUtil.DEVICE_EMBEDDED_RECOURD))) {
            typeInt = "1";
            factory = "Arec";
        } else if (typeInt.equals(String.valueOf(DeviceNameUtil.DEVICE_TBOX_RECOURD))) { //TBOX设备
            factory = "Touch";
            typeInt = "1";
        }
        //---typeInt==2,Arec设备，4，TBox设备
        params.put("ip", ip);
        params.put("className", className);
        params.put("deviceType", "hhrec");
        params.put("typeInt", typeInt);
        params.put("system", "record");
        params.put("factory", factory);
        params.put("metString", netUrl);
        try {
            Result res =HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_DEVICE, DeviceServiceClient.Method.Device_ManualNetDiscovered, params);
            if (res.getCode() == 0 && res.getValue() != null) {
                re_value = (Map<String, Object>) res.getValue();
            }
        } catch (Exception e) {
            logger.error("添加外网设备异常", e);
        }
        return re_value;
    }


    /**
     * 获取设备镜头流地址
     *
     * @param ip
     * @param cameraToken
     * @return
     */
    public String getCameraUrl(String ip, String cameraToken) {
        String re_value = "";
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        params.put("cameraToken", cameraToken);
        Result res = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_DEVICE, DeviceServiceClient.Method.Device_Hhrec_GetCameraUrl, params);
        if (res.getCode() == 0 && res.getValue() != null) {
            re_value = res.getValue().toString();
        }
        logger.debug("获取cameraUrl,ip=" + ip + ",cameraToken=" + cameraToken + "结果为:" + res.getValue() + ",getCode=" + res.getCode());
        return re_value;
    }


    /**
     * 添加镜头信息
     *
     * @param hostId
     * @param hostIp
     * @return
     */
    public Boolean addDevice(String hostId, String hostIp) {
        boolean re_value = true;
        try {
            List<Object[]> deviceConfigList = settingsService.getDeviceConfigList();
            boolean isConfig = false;
            List<String> mediaToken = null;
            mediaToken = nvrCommand.getAllMediaToken(hostIp);

            if (mediaToken == null) {
                mediaToken = Collections.EMPTY_LIST;
            }

            for (Object[] obj : deviceConfigList) {
                Map<String, String> params = new HashMap<>();
                params.put("ip", hostIp);
                Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_DEVICE, DeviceServiceClient.Method.Device_GetLocalModel, params);
                String localModle = "";
                if (result.getCode() == 0 && result.getValue() != null && !"".equals(result.getValue())) {
                    localModle = (String) result.getValue();
                }

                String modle = obj[0].toString().trim();

                if (modle.equals(localModle)) {
                    if (!mediaToken.contains(obj[3].toString())) continue;
                    isConfig = true;
                    String mainStream = getCameraUrl(hostIp, obj[2].toString());
                    String subStream = getCameraUrl(hostIp, obj[3].toString());
                    if (subStream.equals("")) {
                        subStream = mainStream;
                    }
                    deviceService.addDeviceService(hostId, obj[1].toString(), obj[2].toString(), obj[3].toString(), mainStream, subStream);
                }
            }
        } catch (Exception e) {
            logger.error("", e);
            re_value = false;
        }
        return re_value;
    }


}
