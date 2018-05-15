package com.honghe.recordweb.listener;



import com.honghe.recordweb.util.ConfigUtil;
import com.honghe.recordweb.util.SipUtil;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


/**
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: 北京鸿合盛视数字媒体技术有限公司</p>
 *
 * @author hthwx
 * @date 2016/8/17
 */
public class SipClientListener extends ContextLoader implements ServletContextListener
{
    /**
     * todo 加注释
     * @param servletContextEvent
     */
    public void contextInitialized(ServletContextEvent servletContextEvent)
    {
        try
        {
            String sipFlag = ConfigUtil.get("SIPFlag");
            if("true".equals(sipFlag))
            {
                SipUtil sipUtil = new SipUtil();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * todo 加注释
     * @param servletContextEvent
     */
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
