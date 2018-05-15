package com.honghe.recordweb.listener;

import com.honghe.recordweb.service.frontend.resource.NasAutoDelThread;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextLoader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: 北京鸿合盛视数字媒体技术有限公司</p>
 *
 * @author hthwx
 * @date 2016/8/3
 */
@Component
public class NasAutoDelListener implements ServletContextListener, ContextLoader {
    /**
     * todo 加注释
     * @param servletContextEvent
     */
    public void contextInitialized(ServletContextEvent servletContextEvent) {
//        System.out.println("11111111111111111111 +NasAutoDelListener");
        NasAutoDelThread task=new NasAutoDelThread();
        ScheduledExecutorService ses= Executors.newScheduledThreadPool(1);
        ses.scheduleAtFixedRate(task,1000*60*1,1000*60*60*24, TimeUnit.MILLISECONDS);

    }

    /**
     * todo 加注释
     * @param servletContextEvent
     */
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    /**
     * todo 加注释
     * @param aClass
     * @param strings
     * @return
     */
    @Override
    public String[] processLocations(Class<?> aClass, String... strings) {
        return new String[0];
    }

    /**
     * todo 加注释
     * @param strings
     * @return
     * @throws Exception
     */
    @Override
    public ApplicationContext loadContext(String... strings) throws Exception {
        return null;
    }
}
