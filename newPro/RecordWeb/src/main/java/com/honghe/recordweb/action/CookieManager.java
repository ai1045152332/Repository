package com.honghe.recordweb.action;

import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wj on 2014-10-15.
 */
public class CookieManager {
    private final static Logger logger = Logger.getLogger(CookieManager.class);
    public enum Key{
        REMEMBERME("cookie_keeplogin123"),GROUPSELECTED("group_selected"),MYINDEX("myindex"),
        SELECTAUTO("selectauto"),SCREEN49("screen49"),VIEWCAMERA("viewCamera");
        private String key;
        Key(String key){
            this.key = key;
        }

        @Override
        public String  toString(){
            return this.key;
        }
    }

    private CookieManager(){

    }

    /**
     * 清空cookie
     * @param request
     * @param response
     * @param path
     * @param key
     */
    public static void clearCookie(HttpServletRequest request,HttpServletResponse response, String path,Key key)
    {
        try {
            Cookie cookie = new Cookie(key.toString(), null);
            cookie.setMaxAge(0);
            cookie.setPath(path);//根据你创建cookie的路径进行填写
            String host = request.getHeader("host");
            if (host.indexOf(":") > -1) {
                host = host.split(":")[0];
            }
            cookie.setDomain(host);
            response.addCookie(cookie);
        }
        catch(Exception e)
        {
            logger.error("清空Cookies发生异常！",e);
            logger.debug("清空Cookies发生异常！");
        }

    }
    /**
     * 设置cookie
     * @param response
     * @param key
     * @param value
     * @param path
     * @param life
     */
    public static void setCookie(HttpServletRequest request,HttpServletResponse response, Key key,
                                 String value, String path,int life) {
        Cookie cookie = new Cookie(key.toString(), value);
        cookie.setMaxAge(life);
        cookie.setPath(path); // 这个不能少
        String host=request.getHeader("host");
        if(host.indexOf(":")>-1){
            host=host.split(":")[0];
        }
        cookie.setDomain(host);
        response.addCookie(cookie);
    }
    /**
     * 获取某Cookie值
     * @param request
     * @param key
     * return
     */
    public static String getCookieValue(HttpServletRequest request,Key key)
    {
        Cookie[] cookies = request.getCookies();
        String cookieValue = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (key.toString().equals(cookie.getName())) {
                    cookieValue = cookie.getValue();
                    break;
                }
            }
        }
        return cookieValue;
    }
}
