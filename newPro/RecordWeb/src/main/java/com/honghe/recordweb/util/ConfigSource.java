package com.honghe.recordweb.util;

import java.util.ResourceBundle;

/**
 * Created by jjdqh on 2017/4/1.
 *
 * 根据配置文件读取ip 和 port
 */
public class ConfigSource implements IpAndPortSouce {
    @Override
    public String getIp(String serviceType) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
        if(HttpServiceUtil.SERVICE_SCREENCOMMAND.equals(serviceType)|| HttpServiceUtil.SERVICE_RECORDCOMMAND.equals(serviceType)
                || HttpServiceUtil.SERVICE_PROJECTORCOMMAND.equals(serviceType)|| HttpServiceUtil.SERVICE_WHITEBOARD.equals(serviceType) || HttpServiceUtil.SERVICE_DSSCOMMAND.equals(serviceType)){
            serviceType = HttpServiceUtil.SERVICE_DEVICE;
        }
        if (resourceBundle.containsKey(serviceType + "ServerIp")) {
            return resourceBundle.getString(serviceType + "ServerIp");
        }
        return "";
    }

    @Override
    public int getPort(String serviceType) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
        if(HttpServiceUtil.SERVICE_SCREENCOMMAND.equals(serviceType)|| HttpServiceUtil.SERVICE_RECORDCOMMAND.equals(serviceType)
                || HttpServiceUtil.SERVICE_PROJECTORCOMMAND.equals(serviceType) || HttpServiceUtil.SERVICE_WHITEBOARD.equals(serviceType) || HttpServiceUtil.SERVICE_DSSCOMMAND.equals(serviceType)){
            serviceType = HttpServiceUtil.SERVICE_DEVICE;
        }
        if (resourceBundle.containsKey(serviceType + "ServerPort")) {
            if ("".equals(resourceBundle.getString(serviceType + "ServerPort"))) {
                return 7070;
            }
            return Integer.parseInt(resourceBundle.getString(serviceType + "ServerPort"));
        }
        return 0;
    }
}
