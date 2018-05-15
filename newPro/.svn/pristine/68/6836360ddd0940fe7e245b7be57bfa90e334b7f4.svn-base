package com.honghe.recordweb.util;

import com.honghe.device.sdk.DeviceServiceClient;
import com.honghe.service.client.Result;

import java.util.Map;

/**
 * Created by lch on 2015/6/27.
 */
public class HongheDeviceServiceFactory {
    private HongheDeviceServiceFactory() {

    }

    public static final DeviceServiceClient getDeviceServiceClient() {
        String ip = ConfigUtil.get("deviceServerIp");
        String port = ConfigUtil.get("deviceServerPort");
        if(!ip.equals("") && !port.equals("")) {
            return new DeviceServiceClient(ip,Integer.parseInt(port));
        }
        else if(!ip.equals(""))
        {
            return new DeviceServiceClient(ip);
        }
        else
        {
            return new DeviceServiceClient();
        }
    }

    /**
     * 远程调用设备服务对应的方法
     *
     * @param methodType 设备类型
     * @param method     具体的方法名
     * @param params     参数
     * @return
     */
    public static Result remoteInvocation(String methodType, Enum method, Map params) {
        Result result = null;
        result = getDeviceServiceClient().execute(methodType, method, params);
        return result;
    }
}
