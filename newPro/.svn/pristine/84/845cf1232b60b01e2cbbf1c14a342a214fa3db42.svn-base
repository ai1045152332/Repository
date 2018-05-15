package com.honghe.recordweb.service.frontend.devicemanager;

import com.honghe.device.sdk.DeviceServiceClient;
import com.honghe.recordweb.service.frontend.socket.SocketServer;
import com.honghe.recordweb.util.*;
import com.honghe.service.client.Result;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;


/**
 * Created by zhanghongbin on 2014/11/17.
 */
public class HostDevice {
    private final static Logger logger = Logger.getLogger(HostDevice.class);

    public static final String OnlineStatus_Offline = "Offline";    //设备状态，离线
    public static final String OnlineStatus_Online = "Online";        //设备状态，在线


    static {
        SocketServer.run();
        RWProperties.init();
    }

    /**
     * 初始化
     */
    @PostConstruct
    public void init() {
        final Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            public void run() {
                Map<String, String> params = new HashMap<>();
                String ip = "127.0.0.1";
                try {
                    ip = java.net.InetAddress.getLocalHost().getHostAddress();
                } catch (Exception e) {
                    ip = "";
                }
                String url = "serviceEvent";
                String port = "8010";
                params.put("port", port);
                params.put("ip", ip);
                params.put("url", url);
                params.put("sys", "dmanager");
                Result result = HttpServiceUtil.service(CommonName.METHOD_TYPE_DEVICE, "registEventAdrr", params);
                logger.debug("平台向设备服务订阅url为" + url + ",port:" + port + ",结果为:" + result.getValue());
                System.out.println("平台向设备服务订阅url为 " +ip+"/"+ url + ",port:" + port + ",结果为:" + result.getValue());
                if (result.getCode() == 0 && result.getValue() != null) {
                    Boolean flag = (boolean) result.getValue();
                    if (flag) {
                        timer.cancel();
                    }
                }
            }
        }, 10000,30000);
    }

    /**
     * 更新设备
     *
     * @param ip
     * @param userName
     * @param password
     * @return
     */
    public final boolean update(String ip, String userName, String password) {
        boolean flag = false;
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        params.put("userName", userName);
        params.put("password", password);
        Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_DEVICE, DeviceServiceClient.Method.Device_Update, params);

        if (result != null && result.getCode() == 0) {
            flag = (boolean) result.getValue();
        }
        return flag;
    }

    /**
     * 依据IP获取设备状态
     *
     * @param ip
     * @return String "Offline" ；"Online"
     */
    public final String getStatus(String ip) {
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_DEVICE, DeviceServiceClient.Method.Device_Get_Status, params);
        String values = OnlineStatus_Offline;
        if (result != null && result.getCode() == 0 && result.getValue() != null) {
            values = (String) result.getValue();
        }
        return values;
    }

    /**
     * 依据IP获取设备状态（批量查询，多个ip用逗号隔开）
     *
     * @param ip
     * @return
     */
    public final List<Map<String, String>> getDevicesStatus(String ip) {
        Map<String, String> params = new HashMap<>();
        List<Map<String, String>> values = new ArrayList<>();
        params.put("ip", ip);
        Result result = HttpServiceUtil.service(CommonName.METHOD_TYPE_DEVICE, "getDeviceStatus", params);
        if (result != null && result.getCode() == 0 && result.getValue() != null) {
            values = (List<Map<String, String>>) result.getValue();
        }
        return values;
    }


    /**
     * 依据IP判断是否在线
     *
     * @param ip
     * @return boolean true；false
     */
    public final boolean isOnline(final String ip) {
        boolean flag = false;
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        try {
            Result res = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_DEVICE, DeviceServiceClient.Method.Device_Is_Online, params);
            if (res.getCode() == 0 && res.getValue() != null) {
                flag = (boolean) res.getValue();
            }
            logger.debug("依据IP判断是否在线，ip：" + ip);
        } catch (Exception e) {
            logger.error("依据IP判断是否在线异常，ip：" + ip, e);
        }
        return flag;
    }

    /**
     * 根据ip获取设备信息
     *
     * @param ip
     * @return
     */
    public final Map<String, Object> getHostInfoByIp(final String ip) {
        Map<String, Object> hostInfo = Collections.EMPTY_MAP;
        Map<String, String> params = new HashMap<>();
        params.put("hostIp", ip);
        Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_DEVICE, DeviceServiceClient.Method.Device_GetHostInfoByIp, params);

        if (result.getCode() == 0 && result.getValue() != null) {
            hostInfo = (Map<String, Object>) result.getValue();
        }
        return hostInfo;
    }

    /**
     * 获取设备信息
     *
     * @param ip
     * @return
     */
    public final Map<String, Object> getExtensionInfo(String ip) {

        Map<String, Object> hostmap = Collections.EMPTY_MAP;
        hostmap = (Map<String, Object>) getHostInfoByIp(ip);
        return hostmap;
    }


    /**
     * 未分组设备信息
     *
     * @param hostIds 设备id
     * @param type    设备类型
     * @return
     */
    public List<Map> getNotHostInfo(String type, String hostIds) {
        List<Map> mapList = Collections.emptyList();
        Map<String, String> params = new HashMap<>();
        params.put("deviceType", type);
        params.put("hostIdStr", hostIds);
        Result res = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_DEVICE, DeviceServiceClient.Method.Device_NotHostInfo, params);
        if (res.getCode() == 0 && res.getValue() != null) {
            List<Map> values = (List<Map>) res.getValue();
            if (!values.isEmpty()) {
                mapList = values;
            }
        }
        return mapList;
    }

    /**
     * 获取设备信息
     *
     * @param type      //类型
     * @param hostIdStr //主机id 列表
     * @return
     */
    public Result getHostInfo(String type, String hostIdStr) {
        Map<String, String> params = new HashMap<>();
        params.put("deviceType", type);
        params.put("hostIdStr", hostIdStr);
        return HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_DEVICE, DeviceServiceClient.Method.Device_HostInfo, params);
    }


    /**
     * 获取设备数量
     *
     * @param type 设备类型
     * @return
     */
    public int getHostCount(String type) {
        int re_value = 0;
        try {
            Map<String, String> params = new HashMap<>();
            params.put("deviceType", type);
            Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_DEVICE, DeviceServiceClient.Method.Device_HostCount, params);
            if (result.getCode() == 0 && result.getValue() != null) {
                int res = Integer.parseInt(result.getValue().toString());
                re_value = res;
            }
        } catch (Exception e) {
            logger.error("获取设备数量异常", e);
        }
        return re_value;
    }

    /**
     * 修改班级名称(指修改数据库)
     *
     * @param hostIp
     * @param hostName
     * @return
     */
    @Transactional
    public boolean updateHostName(String hostIp, String hostName,String devicetype) {
        boolean re_value = false;
        try {
            Map<String, String> params = new HashMap<>();
            params.put("ip", hostIp);
            params.put("hostName", hostName);
            Result result=null;
            if (devicetype.equals("HHTC")){
                result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_SCREENCOMMAND, DeviceServiceClient.Method.Device_Command_UpdateHhtcHostName, params);
            }else if (devicetype.equals("NVR")) {
                result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_Command_UpdateHhrecHostName, params);
            }
     if (result.getCode() == 0 && result.getValue() != null) {
     re_value = (Boolean) result.getValue();
     }
     logger.debug("发送修改设备名称命令,ip为" + hostIp + ",hostName:" + hostName + ",结果为" + result.getValue() + ",getCode为" + result.getCode());
     } catch (Exception e) {
     logger.error("发送修改设备名称命令异常,ip为" + hostIp + ",hostName:" + hostName, e);
     }
     return re_value;
     }

     /**
     * 删除设备
     *
     * @param id     设备id
     * @param hostIp 设备ip
     * @return
     */
    public boolean delHostd(String id, String hostIp) {
        boolean re_value = false;
        Map<String, String> params = new HashMap<>();
        params.put("ip", hostIp);
        params.put("hostId", id);
        Result res = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_DEVICE, DeviceServiceClient.Method.Device_DelHost, params);
        if (res != null && res.getCode() == 0 && res.getValue() != null) {
            re_value = (Boolean) res.getValue();
        }
        return re_value;
    }


    /**
     * 发现设备数量
     *
     * @param deviceType 多个设备类型，用逗号分隔 例如：hhtc，htrec
     * @return
     */
    public String discoverCount(String deviceType) {
        String re_value = "";
        deviceType = getDeviceTypes(deviceType);  //选取符合条件的设备类型

        try {
            Map<String, String> params = new HashMap<>();
            params.put("deviceType", deviceType);
            Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_DEVICE, DeviceServiceClient.Method.Device_Discover, params);

            if (result.getCode() == 0 && result.getValue() != null) {
                Map values = new HashMap();
                values = result.getValue().equals("") ? new HashMap() : ((Map) result.getValue());
                re_value = values.isEmpty() ? "" : values.get("discoverCount").toString();
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return re_value;
    }


    /**
     * 获取某一类型的所有设备信息
     *
     * @param _type 设备类型  参见util文件夹下的CommonName 类
     * @return 查询的设备列表
     */
    public Map getAllHostInfo(String _type) {
        Map re_values = new HashMap();
        Map<String, String> params = new HashMap<>();
        params.put("deviceType", _type);
        Result res = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_DEVICE, DeviceServiceClient.Method.Device_GetAllHostList, params);
        if (res.getCode() == 0 && res.getValue() != null) {
            Map hostlists = (Map) res.getValue();
            re_values = hostlists.isEmpty() ? new HashMap() : hostlists;
        }
        return re_values;
    }


    /**
     * 根据物理地址返回设备名
     *
     * @param _mac 物理地址
     * @return 返回对应的设备名和设备Ip，已Map结构呈现
     */
    public Map getNameByMac(String _mac) {
        Map re_values = new HashMap();
        Map<String, String> params = new HashMap<>();
        params.put("mac", _mac);
        Result res = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_DEVICE, DeviceServiceClient.Method.Device_GetNameByMac, params);
        if (res.getCode() == 0 && res.getValue() != null) {
            Map hostlists = (Map) res.getValue();
            re_values = hostlists.isEmpty() ? new HashMap() : hostlists;
        }
        return re_values;
    }


    /**
     * 获取设备信息并分页
     *
     * @param _type
     * @param _hostIds
     * @param _pageSize
     * @param _currentPage
     * @return
     */
    public Map getHostInfoByPage(String _type, String _hostIds, int _pageSize, int _currentPage) {
        Map re_values = new HashMap();
        Map<String, String> params = new HashMap<>();
        params.put("deviceType", _type);
        params.put("hostIdStr", _hostIds);
        params.put("currentPage", String.valueOf(_currentPage));
        params.put("pageSize", String.valueOf(_pageSize));
        Result res = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_DEVICE, DeviceServiceClient.Method.Device_HostInfoByPage, params);
        if (res.getCode() == 0 && res.getValue() != null) {
            Map hostlists = (Map) res.getValue();
            re_values = hostlists.isEmpty() ? new HashMap() : hostlists;
        }
        return re_values;
    }

    /**
     * 获取未分组设备信息并分页
     *
     * @param _type
     * @param _hostIds
     * @param _pageSize
     * @param _currentPage
     * @return
     */
    public Map getNotHostInfoByPage(String _type, String _hostIds, int _pageSize, int _currentPage) {
        Map re_values = Collections.emptyMap();
        Map<String, String> params = new HashMap<>();
        params.put("deviceType", _type);
        params.put("hostIdStr", _hostIds);
        params.put("currentPage", String.valueOf(_currentPage));
        params.put("pageSize", String.valueOf(_pageSize));
        Result res = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_DEVICE, DeviceServiceClient.Method.Device_NotHostInfoByPage, params);
        if (res.getCode() == 0 && res.getValue() != null) {
            Map<String, Object> map = (Map<String, Object>) res.getValue();
            re_values = map.isEmpty() ? new HashMap() : map;
        }
        return re_values;
    }

    /**
     * 按名称精确查找host主机是否存在
     *
     * @param name String 用户名称
     * @return Host
     */
    @Transactional
    public Boolean getHostService(String name) {
        Boolean re_value = false;
        try {
            Map<String, String> params = new HashMap<>();
            params.put("hostName", name);
            Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_DEVICE, DeviceServiceClient.Method.Device_IsHostNameExist, params);
            if (result.getCode() == 0 && result.getValue() != null) {
                re_value = (Boolean) result.getValue();
            }
        } catch (Exception e) {
            logger.error("按名称精确查找host主机是否存在异常", e);
        }
        return re_value;
    }

    /**
     * 通过id获取设备信息
     *
     * @param hostId 主机Id
     * @return
     */
    public Map getHostInfoById(String hostId) {
        Map re_value = Collections.emptyMap();
        Map<String, String> params = new HashMap<>();
        params.put("hostId", hostId);
        Result res = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_DEVICE, DeviceServiceClient.Method.Device_GetHostInfoById, params);
        if (res.getCode() == 0 && res.getValue() != null) {
            Map values = (Map) res.getValue();
            re_value = !values.isEmpty() ? values : Collections.emptyMap();
        }
        return re_value;
    }

    /**
     * 获取某一类型所有设备列表
     *
     * @param type 设备类型
     * @return
     */
    public List<Map<String, Object>> getHostInfoByType(String type) {
        List<Map<String, Object>> list = Collections.emptyList();
        Map<String, String> params = new HashMap<>();
        params.put("type", type);
        Result res = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_DEVICE, DeviceServiceClient.Method.Device_HostInfoBytype, params);
        if (res.getCode() == 0 && res.getValue() != null) {
            list = (List<Map<String, Object>>) res.getValue();
        }
        return list;
    }

    /**
     * 添加设备入库
     *
     * @param type     设备类型
     * @param userName 用户名
     * @param password 密码
     * @param system   请求对象
     * @return
     */
    public Map addHost(String type, String userName, String password, String system) {
        Map re_value = Collections.emptyMap();
        Map<String, String> params = new HashMap<>();
        params.put("deviceType", type);
        params.put("system", system);
        params.put("userName", userName);
        params.put("password", password);
        Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_DEVICE, DeviceServiceClient.Method.Device_Addhost, params);
        if (result.getCode() == 0 && result.getValue() != null) {
            re_value = ((Map) result.getValue());
        }
        return re_value;
    }

    /**
     * 发现设备
     *
     * @param ip        设备地址
     * @param className 主机名
     * @param typeInt   类型  typeInt：1.录播设备；2,Arec设备；3，大屏设备；4，TBOX录播设备;5,投影仪设备；6，白板一体机设备
     * @return
     */
    public Map<String, Object> discovered(String ip, String className, String typeInt) {

        Map<String, Object> re_value = Collections.emptyMap();
        Map<String, String> params = new HashMap<>();
        String factory = DeviceNameUtil.getFactoryByTypeInt(typeInt);
        String deviceType = DeviceNameUtil.getDeviceType(typeInt);

        //对于录播设备，TBox
        if (CommonName.DEVICE_TYPE_RECOURD.equals(deviceType) && !typeInt.equals(String.valueOf(DeviceNameUtil.DEVICE_NORMALIZATION_RECOURD)) && !typeInt.equals(String.valueOf(DeviceNameUtil.DEVICE_WBOX_RECOURD))) {
            typeInt = String.valueOf(DeviceNameUtil.DEVICE_FINE_RECOURD);
        }
        params.put("ip", ip);
        params.put("className", className);
        params.put("deviceType", deviceType);
        params.put("typeInt", typeInt);
        params.put("factory", factory);
        try {
            Result res = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_DEVICE, DeviceServiceClient.Method.Device_Manual_Discover, params);
            if (res.getCode() == 0 && res.getValue() != null) {
                re_value = (Map<String, Object>) res.getValue();
            }
        } catch (Exception e) {
            logger.error("发现设备异常", e);
        }
        return re_value;
    }

    /**
     * 获取设备信息
     *
     * @param type  //设备类型类型
     * @param names //主机名，可以为多个，用逗号分隔
     * @return
     */
    public List<Map> getHostListByNames(String type, String names) {
        List<Map> re_value = Collections.EMPTY_LIST;
        Map<String, String> params = new HashMap<>();
        params.put("deviceType", type);
        params.put("names", names);
        Result res = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_DEVICE, DeviceServiceClient.Method.Device_GetHostListByNames, params);

        if (res != null && res.getCode() == 0 && res.getValue() != null) {
            List<Map> value = ((List<Map>) res.getValue());
            re_value = value.isEmpty() ? new ArrayList<Map>() : value;
        }

        return re_value;
    }

    /**
     * 获取符合条件的设备类型
     *
     * @param type 设备类型
     * @return
     */
    private String getDeviceTypes(String type) {
        StringBuffer re_value = new StringBuffer();
        if (ConfigureUtil.isAll()) {  //软件支持所有设备时
            re_value.append(type);
        } else {//软件依据配置信息，支持“hhtc,hhrec,htpj”中的某几类设备时
            if (type.equals(CommonName.DEVICE_TYPE_ALL)) {
                if (ConfigureUtil.isHhtc()) {
                    re_value.append(CommonName.DEVICE_TYPE_SCREEN + ",");
                }
                if (ConfigureUtil.isHhrec()) {
                    re_value.append(CommonName.DEVICE_TYPE_RECOURD + ",");
                }
                if (ConfigureUtil.isHtpr()) {
                    re_value.append(CommonName.DEVICE_TYPE_PROJECTOR + ",");
                }
                if (ConfigureUtil.isHhtwb()) {
                    re_value.append(CommonName.DEVICE_TYPE_WHITEBOARD + ",");
                }
            } else {

                if (ConfigureUtil.isHhtc() && type.contains(CommonName.DEVICE_TYPE_SCREEN)) {
                    re_value.append(CommonName.DEVICE_TYPE_SCREEN + ",");
                }
                if (ConfigureUtil.isHhrec() && type.contains(CommonName.DEVICE_TYPE_RECOURD)) {
                    re_value.append(CommonName.DEVICE_TYPE_RECOURD + ",");
                }
                if (ConfigureUtil.isHtpr() && type.contains(CommonName.DEVICE_TYPE_PROJECTOR)) {
                    re_value.append(CommonName.DEVICE_TYPE_PROJECTOR + ",");
                }
                if (ConfigureUtil.isHhtwb() && type.contains(CommonName.DEVICE_TYPE_WHITEBOARD)) {
                    re_value.append(CommonName.DEVICE_TYPE_WHITEBOARD + ",");
                }
            }

        }
        return re_value.toString();
    }
}
