package com.honghe.recordweb.util;

/**
 * Created by jjdqh on 2017/4/1.
 */
public class SourceFactory {
    public static IpAndPortSouce getSouceInstance(Class clz) {
        try {
            return (IpAndPortSouce) clz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
