package com.honghe.managerTool.util;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ParamUtil {

    static Logger logger = LoggerFactory.getLogger(ParamUtil.class);
    private ParamUtil(){}

    /**
     * 某一个参数为空或不合法，返回true
     * @param objects
     * @return
     */
    public static boolean isOneEmpty(Object... objects){
        for(Object o : objects){
            if(o == null){
                return true;
            }
            if(o instanceof String){
                if("".equals(o)){
                    return true;
                }
            }else if(o instanceof Integer && Integer.parseInt(String.valueOf(o))<0){
                return true;
            }
        }
        return false;
    }

    /**
     * 所有参数都为空或不合法，返回true
     * @param objects
     * @return
     */
    public static boolean isAllEmpty(Object... objects){
        Boolean flag;
        int count = objects.length;
        for(Object o : objects){
            if(o == null){
                count = count -1;
            }
            if(o instanceof String){
                if("".equals(o)){
                    count = count -1;
                }
            }else if(o instanceof Integer && Integer.parseInt(String.valueOf(o))<=0){
                count = count -1;
            }
        }
        if(count == 0){
            flag = true;
        }else {
            flag = false;
        }
        return flag;

    }


    /**
     * 获取request参数
     *
     * @param request http请求信息
     * @return 参数map
     */
    public static  Map<String, Object> getParams(HttpServletRequest request) {
        Map<String, String[]> parameters = request.getParameterMap();
        Map<String, Object> params = new HashMap<>();
        String type = request.getHeader("Content-Type");
        if ("GET".equalsIgnoreCase(request.getMethod()) || !type.contains("application/json")) {
            parameters = request.getParameterMap();
            int len;
            for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
                len = entry.getValue().length;
                if (len == 1) {
                    params.put(entry.getKey(), entry.getValue()[0]);
                } else if (len > 1) {
                    params.put(entry.getKey(), entry.getValue());
                }
            }
        } else {
            try {
                ServletInputStream inputStream = request.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line + "\n");
                }
                String strContent = content.toString();
                params = (Map<String, Object>) JSON.parse(strContent);
            } catch (IOException e) {
                Logger logger = LoggerFactory.getLogger(ParamUtil.class);
                logger.error("getParamsERROR", e);
            }
        }
        return params;
    }

    /**
     * 获取请求的ip地址
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     *
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     *
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        final String unknownKey = "unknown";
        if (ip == null || ip.length() == 0 || unknownKey.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknownKey.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknownKey.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || unknownKey.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || unknownKey.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if(ip.contains("0:0:0:0:0:0:0:1")){
            ip = "127.0.0.1";
        }
        return ip;
    }

}
