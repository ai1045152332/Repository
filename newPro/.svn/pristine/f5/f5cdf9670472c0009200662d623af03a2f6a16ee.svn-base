package com.honghe.recordweb.service.frontend.websocket;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhanghongbin on 2015/3/23.
 */
public final class DirectPool {

    private DirectPool() {

    }

    private static java.util.concurrent.ConcurrentHashMap pool = new ConcurrentHashMap();

    public final static void add(String id, String token) {
        if (!containKey(id)) {
            pool.put(id, token);
        }
    }

    public static boolean containKey(String id) {
        return pool.containsKey(id);
    }

    public static boolean containValue(String token) {
        return pool.containsValue(token);
    }

    public static ConcurrentHashMap all() {
        return pool;
    }

    public static final void remove(String id) {
        if (containKey(id)) {
            pool.remove(id);
        }
    }


}
