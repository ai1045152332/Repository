package com.honghe.recordweb.util;
import com.honghe.service.client.http.HttpServiceClient;


/**
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: 北京鸿合盛视数字媒体技术有限公司</p>
 *
 * @author wzz
 * @date 2016/04/27
 */
public final class HttpServiceClientFactory {

    static {

    }

    /**
     * ss
     */
    private HttpServiceClientFactory() {

    }

    /**
     * 用户服务地址
     * @return 用户服务
     */
    public static  HttpServiceClient getHttpServiceClient() {
        String ip = ConfigUtil.get("httpServerIp");
        String port = ConfigUtil.get("httpServerPort");
        if(!ip.equals("") && !port.equals("")) {
            return new HttpServiceClient(ip,Integer.parseInt(port));
        }
        else if(!ip.equals(""))
        {
            return new HttpServiceClient(ip);
        }
        else
        {
            return new HttpServiceClient();
        }
    }
}
