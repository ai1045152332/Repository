package com.honghe.recordweb.service.frontend.websocket;

import org.apache.catalina.websocket.MessageInbound;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 会话池 用来维护用户和连接
 * User: zhanghongbin
 * Date: 14-9-17
 * Time: 上午10:28
 */



 public final class SessionPool {

    private SessionPool() {

    }

    private static Map<String, MessageInbound> sessionPool;

    static {
        sessionPool = new ConcurrentHashMap<String, MessageInbound>();
    }

    /**
     * 根据用户id 删除连接
     * @param key
     */
    public final static void remove(String key) {
        sessionPool.remove(key);
    }

    /**
     * 增加用户和连接
     * @param key
     * @param messageInbound
     */
    public final static void add(String key, MessageInbound messageInbound) {
        sessionPool.put(key, messageInbound);
    }

    /**
     * 是否存在此用户连接
     * @param key
     * @return
     */
    public final static boolean contains(String key) {
        return sessionPool.containsKey(key);
    }

    /**
     * 获取此用户连接
     * @param key
     * @return
     */
    public final static MessageInbound get(String key) {
        return sessionPool.get(key);
    }


    /**
     * 获取所有用户
     * @return
     */
    public final static Set<String> Keys(){
        return sessionPool.keySet();
    }


}
