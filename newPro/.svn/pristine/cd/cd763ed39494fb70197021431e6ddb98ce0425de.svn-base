package com.honghe.recordweb.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhanghongbin on 2015/5/12.
 * zhanghongbi
 */
public final class CacheUtil {

    private static Map<String, String[]> cacheMap = new HashMap<>();

    private CacheUtil() {

    }

    public final static void put(String key, String[] value) {
        cacheMap.put(key, value);
    }

    public final static String[] get(String key) {
        return cacheMap.get(key);
    }

    public final static boolean contain(String key) {
        return cacheMap.containsKey(key);
    }

    public final static void remove(String key){
        cacheMap.remove(key);
    }
}
