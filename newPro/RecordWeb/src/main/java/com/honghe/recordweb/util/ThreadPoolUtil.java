package com.honghe.recordweb.util;


import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: 北京鸿合智能系统股份有限公司</p>
 *
 * @author hthwx
 * @date 2017/10/7
 */
public final class ThreadPoolUtil {


    private static ExecutorService executorService;

    static {
        executorService = Executors. newFixedThreadPool(20);

//        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
//            @Override
//            public void run() {
//                executorService.shutdown();
//            }
//        }, "thread-dss-shutdown"));
    }

    private ThreadPoolUtil() {

    }

    public static final void execute(Runnable runnable) {
        executorService.execute(runnable);
    }
}
