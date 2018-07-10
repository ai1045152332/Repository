package com.honghe.dmanager.common.util;

import java.util.ResourceBundle;

/**
 * Created by lyx on 2016-09-26.
 * <p>
 * 请求服务类的工厂，用于创建对应的服务的连接
 */
public class HttpServiceUtil {

    private final static String CMD = "cmd";
    private final static String CMD_OP = "cmd_op";

    //授权码驻守服务标识
    public final static String SERVICE_LICENSESERVER = "licenseserver";
    public final static String SERVICE_DEVICE = "device";

    public static String URL = "http://localhost:8888/service/cloud/httpCommandService";
    public static String licenseserverUrl = "http://localhost:58890";
    static ResourceBundle resourceBundle = null;

    static {
        try {
            //读取设备对应远程连接服务配置文件
            resourceBundle = ResourceBundle.getBundle("httpserverurl");
            if (resourceBundle.containsKey("url")) {
                URL = resourceBundle.getString("url");
            }
            if (resourceBundle.containsKey("dmlicenseserverUrl") ) {
                licenseserverUrl = resourceBundle.getString("dmlicenseserverUrl");
            }
            if (resourceBundle.containsKey("dmdeviceUrl")) {
                URL = resourceBundle.getString("dmdeviceUrl");
            }
        } catch (Exception e) {
//            logger.debug("读取远程连接文件错误", e);
        }
    }

    public HttpServiceUtil() {
    }

    public static String getRequestUrl(String serviceToken){
        String url = "";
        if (SERVICE_LICENSESERVER.equals(serviceToken)){
            url = licenseserverUrl;
        }
        return url;
    }
    /**
     * 设备服务连接调用
     *
     * @param token 方法名
     * @return
     */
    public static String deviceService(String token) {
        String url = "";
        if (token.equals(SERVICE_DEVICE)){
            url = URL;
        }
        return url;
    }
    /**
     * 抛出连接远程调用异常
     *
     * @param code 连接调用状态返回值
     * @throws Exception
     */
    private static void throwExceptionMessage(int code) throws Exception {
        if (code != 0) {
            throw new Exception("response fault:" + parseErrorCode(code));
        }
    }

    /**
     * 返回值错误解析
     *
     * @param code
     * @return
     */
    private static String parseErrorCode(int code) {
        String msg;
        if (code == -1) {
            msg = "参数错误";
        } else if (code == -2) {
            msg = "没有此方法";
        } else {
            msg = "未知错误";
        }
        return msg;
    }
}