package com.honghe.recordweb.service.frontend.socket;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.mina.core.session.IoSession;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Tpaduser on 2015/7/29.
 */
public class AppSessionPool {

    private AppSessionPool(){

    }
    public static Map<String, IoSession> sessionPool;

    static {
        sessionPool = new ConcurrentHashMap<String, IoSession>();
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
     * @param session
     */
    public final static void add(String key, IoSession session) {
        sessionPool.put(key, session);
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
    public final static IoSession get(String key) {
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
