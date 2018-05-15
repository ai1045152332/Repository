package com.honghe.recordweb.util;

import org.apache.log4j.Logger;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Created by wj on 2015-01-22.
 */
public final class ThreadUtil {

    private final static Logger logger = Logger.getLogger(ThreadUtil.class);

    private static ExecutorService executorService = java.util.concurrent.Executors.newCachedThreadPool();

    private ThreadUtil() {

    }

    static {
        //当出现异常时，实现线程退出
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                executorService.shutdown();
            }
        }));
    }

    /**
     * todo 加注释
     * @param threadCount
     * @param callables
     * @return
     */
    public final static Map<String, Object> invokeAll(int threadCount, Collection<Callable<Map<String, Object>>> callables) {

        Map<String, Object> result = new HashMap<String, Object>();

        try {
            List<Future<Map<String, Object>>> futureList = executorService.invokeAll(callables);
            for (Future<Map<String, Object>> future : futureList) {
                result.putAll(future.get());
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return result;

    }

}
