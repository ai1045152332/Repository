package com.honghe.recordweb.service.frontend.devicemanager;

import com.honghe.device.sdk.DeviceServiceClient;
import com.honghe.recordweb.service.frontend.news.ConfigUtil;
import com.honghe.recordweb.util.CacheUtil;
import com.honghe.recordweb.util.HongheDeviceServiceFactory;
import com.honghe.service.client.Result;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * Created by lyx on 2016-05-31.
 * 虚拟设备类（受控端相当于虚拟的一台设备），处理对应的安装受控端的设备的相关操作及状态获取
 */
public abstract class VirtualCommand {

    protected abstract String getCommandName();

    protected abstract Logger getLogger();

    /**
     * 开机
     *
     * @param ip 主机地址
     * @return 返回操作是否成功  true：成功；false:失败
     */
    public boolean start(String ip) {
        boolean re_vlaue = false;
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        Result result = remoteInvocation(getCommandName(), DeviceServiceClient.Method.Device_Command_Boot, params);
        if (result.getCode() == 0 && result.getValue() != null) {
            re_vlaue = (Boolean) result.getValue();
        }
        getLogger().debug("开机命令发出,ip为:" + ip + ",结果为result.getValue():" + result.getValue() + ",getCode=" + result.getCode());
        return re_vlaue;
    }

    /**
     * 关机
     *
     * @param ip      主机地址
     * @param cmdCode 关机命令  “ShutDown”
     * @return 返回操作是否成功  true：成功；false:失败
     */

    public boolean shutdown(String ip, String cmdCode) {
        boolean re_vlaue = false;
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        params.put("cmdCode", cmdCode);
        Result result = remoteInvocation(getCommandName(), DeviceServiceClient.Method.Device_Command_Shutdown, params);

        if (result.getCode() == 0 && result.getValue() != null) {
            re_vlaue = (Boolean) result.getValue();
        }
        getLogger().debug("关机命令已发出，ip为" + ip + ",关机命令为" + cmdCode + "结果为result.getValue():" + result.getValue() + ",getCode为" + result.getCode());
        return re_vlaue;
    }


    /**
     * 信号切换
     *
     * @param ip     主机地址
     * @param signal 信号源
     * @return 返回操作是否成功  true：成功；false:失败
     */
    public boolean changSignal(String ip, String signal) {
        boolean re_value = false;
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        params.put("cmdCode", signal);
        Result result = remoteInvocation(getCommandName(), DeviceServiceClient.Method.Device_Command_ChangeSignal, params);
        if (result.getCode() == 0 && result.getValue() != null) {
            re_value = (Boolean) result.getValue();
        }
        getLogger().debug("发送切换信号源命令,ip为" + ip + ",信号源命令为" + signal + ",结果为result.getValue():" + result.getValue() + ",getCode为" + result.getCode());
        return re_value;
    }

    /**
     * 获取设备信息
     *
     * @param ip
     * @return
     */
    public Map<String, String> getInfo(String ip) {
        Map<String, String> re_value = Collections.EMPTY_MAP;
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        Result result = remoteInvocation(getCommandName(), DeviceServiceClient.Method.Device_Command_GetInfo, params);
        if (result.getCode() == 0 && result.getValue() != null) {
            re_value = (Map<String, String>) result.getValue();
        }
        getLogger().debug("获取设备信息，ip:" + ip + ",结果为result.getValue():" + re_value + " getCode:" + result.getCode());
        return re_value;
    }

    /**
     * 发送消息
     *
     * @param ip
     * @param params
     * @return
     */
    public boolean sendMessage(String ip, Map<String, String> params) {
        boolean re_value = false;
        params.put("ip", ip);
        Result result = remoteInvocation(getCommandName(), DeviceServiceClient.Method.Device_Command_SendMessage, params);
        if (result.getCode() == 0 && result.getValue() != null) {
            re_value = (Boolean) result.getValue();
        }
        getLogger().debug("发送消息,ip为" + ip + ",结果为result.getValue():" + result.getValue() + ",getCode为" + result.getCode());
        return re_value;
    }

    /**
     * 获取桌面截图
     *
     * @param ip
     * @return
     */
    public Object getPictrue(String ip) {
        Object re_value = null;
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        Result result = remoteInvocation(getCommandName(), DeviceServiceClient.Method.Device_Command_GetPictrue, params);
        if (result.getCode() == 0) {
            re_value = (Object) result.getValue();

            if ("".equals(re_value)) {
                re_value = null;
            }
        }
        getLogger().debug("获取截图,ip为" + ip + ",结果为result.getValue():" + result.getValue() + ",getCode为" + result.getCode());
        return re_value;
    }


    /**
     * 更新设备的命令表的地址
     *
     * @param ip
     * @param sqlStr
     * @return
     */
    public Boolean updateCmdCodeSql(String ip, String sqlStr) {
        Boolean re_value = false;

        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        params.put("sqlStr", sqlStr);
        Result result = remoteInvocation(getCommandName(), DeviceServiceClient.Method.Device_Command_UpdateCmdCodeSql, params);
        if (result.getCode() == 0 && result.getValue() != null) {
            re_value = (Boolean) result.getValue();
        }
        getLogger().debug("获取截图,ip为" + ip + " sqlStr:" + sqlStr + ",结果为result.getValue():" + result.getValue() + ",getCode为" + result.getCode());
        return re_value;
    }

    /**
     * 获取设备的命令表是否过期标识符
     *
     * @param ip
     */
    public boolean isDeviceCmdCodeTimeOut(String ip) {
        boolean re_value = true;
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        Result result = remoteInvocation(getCommandName(), DeviceServiceClient.Method.Device_Command_IsDeviceCmdCodeTimeOut, params);
        if (result.getCode() == 0 && result.getValue() != null) {
            re_value = (boolean) result.getValue();
        }
        getLogger().debug("获取截图,ip为" + ip + ",结果为result.getValue():" + result.getValue() + ",getCode为" + result.getCode());
        return re_value;
    }

    /**
     * 推送订阅地址
     *
     * @param ip
     * @param addrStr
     * @return
     */
    public Boolean sendAddress(String ip, String addrStr) {
        Boolean re_value = false;
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        params.put("addrStr", addrStr);
        Result result = remoteInvocation(getCommandName(), DeviceServiceClient.Method.Device_Command_SendAddress, params);
        if (result.getCode() == 0 && result.getValue() != null) {
            re_value = (Boolean) result.getValue();
        }
        getLogger().debug("获取截图,ip为" + ip + "addrStr:" + addrStr + ",结果为result.getValue():" + result.getValue() + ",getCode为" + result.getCode());
        return re_value;
    }

    /**
     * 清理垃圾
     *
     * @param hostIp ip
     * @return
     */
    public Boolean cleanRubbish(String hostIp) {
        Boolean re_value = false;
        Map<String, String> params = new HashMap<>();
        params.put("ip", hostIp);
        Result result = remoteInvocation(getCommandName(), DeviceServiceClient.Method.Device_Command_ClearRubish, params);
        if (result.getCode() == 0 && result.getValue() != null) {
            re_value = (Boolean) result.getValue();
        }
        getLogger().debug("清理垃圾,ip为" + hostIp + ",结果为result.getValue():" + result.getValue() + ",getCode为" + result.getCode());
        return re_value;
    }

    /**
     * 打铃
     *
     * @param hostIp
     * @return
     */
    public Boolean ring(String hostIp) {
        Boolean re_value = false;
        Map<String, String> params = new HashMap<>();
        params.put("ip", hostIp);
        Result result = remoteInvocation(getCommandName(), DeviceServiceClient.Method.Device_Command_Ring, params);
        if (result.getCode() == 0 && result.getValue() != null) {
            re_value = (Boolean) result.getValue();
        }
        getLogger().debug("打铃,ip为" + hostIp + ",结果为result.getValue():" + result.getValue() + ",getCode为" + result.getCode());
        return re_value;
    }

    /**
     * 系统备份
     *
     * @param hostIp
     * @return
     */
    public Boolean backup(String hostIp) {
        Boolean re_value = false;
        Map<String, String> params = new HashMap<>();
        params.put("ip", hostIp);
        Result result = remoteInvocation(getCommandName(), DeviceServiceClient.Method.Device_Command_Backup, params);
        if (result.getCode() == 0 && result.getValue() != null) {
            re_value = (Boolean) result.getValue();
        }
        getLogger().debug("发送系统备份命令,ip为" + hostIp + ",结果为result.getValue():" + result.getValue() + ",getCode为" + result.getCode());
        return re_value;
    }

    /**
     * 系统还原
     *
     * @param hostIp
     * @return
     */
    public Boolean recovery(String hostIp) {
        Boolean re_value = false;
        Map<String, String> params = new HashMap<>();
        params.put("ip", hostIp);
        Result result = remoteInvocation(getCommandName(), DeviceServiceClient.Method.Device_Command_Recovery, params);
        if (result.getCode() == 0 && result.getValue() != null) {
            re_value = (Boolean) result.getValue();
        }
        getLogger().debug("发送系统还原命令,ip为" + hostIp + ",结果为result.getValue():" + result.getValue() + ",getCode为" + result.getCode());
        return re_value;
    }

    /**
     * 一键安装软件
     *
     * @param hostIp   大屏ip地址
     * @param softName 安装软件名称
     * @param softPath 安装软件地址
     * @return
     */

    public Boolean oneKeyInstall(String hostIp, String softName, String softPath, String serverIp, String port) {
        Boolean re_value = false;
        Map<String, String> params = new HashMap<>();
        params.put("ip", hostIp);
        params.put("softName", softName);
        params.put("softPath", softPath);
        //統一配置文件，將原system.properties的配置信息迁移到config.properties wzz 2017-01-5
//        String ftpUsername = System.getProperty("FtpUserName");
//        String ftpPassword = System.getProperty("FtpPassword");
        String ftpUsername = ConfigUtil.get("FtpUserName");
        String ftpPassword = ConfigUtil.get("FtpPassword");
        params.put("ftpUsername", ftpUsername);
        params.put("ftpPassword", ftpPassword);
        params.put("port", port);
        params.put("serverIp", serverIp);
        Result result = remoteInvocation(getCommandName(), DeviceServiceClient.Method.Device_Command_OneKeyInstall, params);
        if (result.getCode() == 0 && result.getValue() != null) {
            re_value = (Boolean) result.getValue();
        }
        getLogger().debug("发送一键安装软件命令,ip为" + hostIp + ",softName:" + softName + ",softPath:" + softPath + ",serverIp:" + serverIp + ",port:" + port + ",结果为result.getValue():" + result.getValue() + ",getCode为" + result.getCode());
        return re_value;
    }

    /**
     * 一键卸载软件
     *
     * @param hostIp   大屏ip地址
     * @param softName 安装软件名称
     * @return
     */
    public Boolean oneKeyuninstall(String hostIp, String softName) {
        Boolean re_value = false;
        Map<String, String> params = new HashMap<>();
        params.put("ip", hostIp);
        params.put("softName", softName);
        Result result = remoteInvocation(getCommandName(), DeviceServiceClient.Method.Device_Command_OneKeyUninstall, params);
        if (result.getCode() == 0 && result.getValue() != null) {
            re_value = (Boolean) result.getValue();
        }
        getLogger().debug("发送一键卸载软件命令,ip为" + hostIp + ",softName:" + softName + ",结果为result.getValue():" + result.getValue() + ",getCode为" + result.getCode());
        return re_value;
    }

    /**
     * 获取设备命令
     *
     * @return
     */
    public Map<String, List<Map<String, String>>> getDspecCommand(String commonName) {
        Map<String, List<Map<String, String>>> re_value = Collections.EMPTY_MAP;
        Map<String, String> params = new HashMap<>();
        params.put("deviceType", commonName);
        Result result = remoteInvocation(getCommandName(), DeviceServiceClient.Method.Device_Command_GetCmd, params);

        if (result.getCode() == 0 && result.getValue() != null&&!result.equals("")) {

            Map<String, List<Map<String, String>>> res = (Map<String, List<Map<String, String>>>) result.getValue();
            re_value = res.isEmpty() ? Collections.EMPTY_MAP : res;
        }
        getLogger().debug("获取设备命令," + ",结果为result.getValue():" + result.getValue() + ",getCode为" + result.getCode());
        return re_value;
    }
    /**
     * 获取默认通道和童锁信息
     */
    public List<Map<String,Object>> getInitInfo(String deviceType){
        List<Map<String,Object>> re_value = Collections.EMPTY_LIST;
        Map<String, String> params = new HashMap<>();
        params.put("deviceType",deviceType);
        Result result = remoteInvocation(getCommandName(),DeviceServiceClient.Method.Device_Command_GetAllChannelIntersectionByType,params);
       if (result.getCode()==0&&result.getValue()!=null&&!result.getValue().equals("")){
           re_value = (List<Map<String, Object>>) result.getValue();
       }
        return re_value;
    }

    /**
     * 远程调用设备服务对应的方法
     *
     * @param methodType 设备类型
     * @param method     具体的方法名
     * @param params     参数
     * @return
     */
    protected Result remoteInvocation(String methodType, Enum method, Map params) {
        return HongheDeviceServiceFactory.remoteInvocation(methodType, method, params);
    }
}
