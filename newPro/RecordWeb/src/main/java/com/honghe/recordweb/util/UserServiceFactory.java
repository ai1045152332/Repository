package com.honghe.recordweb.util;

import com.honghe.service.client.Result;
import com.honghe.user.sdk.UserServiceClient;

import java.util.Map;

/**
 * Created by zhanghongbin on 2015/6/9.
 */
public final class UserServiceFactory {

    private UserServiceFactory() {
    }

    public static final UserServiceClient getUserServiceClient() {
        String ip = ConfigUtil.get("userServerIp");
        String port = ConfigUtil.get("userServerPort");
        if(!ip.equals("") && !port.equals("")) {
            return new UserServiceClient(ip,Integer.parseInt(port));
        }
        else if(!ip.equals(""))
        {
            return new UserServiceClient(ip);
        }
        else
        {
            return new UserServiceClient();
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
        result = getUserServiceClient().execute(methodType, method, params);
        return result;
    }
}
