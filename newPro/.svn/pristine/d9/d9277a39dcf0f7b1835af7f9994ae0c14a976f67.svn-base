package com.honghe.recordweb.util;

import org.apache.log4j.Logger;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by zhanghongbin on 2015/1/28.
 */
public class TomcatUtil {
    private final static Logger logger = Logger.getLogger(TomcatUtil.class);
    private String httpPort = "";
    private static TomcatUtil tomcatUtil;

    private TomcatUtil() {

    }

    public synchronized static TomcatUtil newInstance() {
        if (tomcatUtil == null) {
            tomcatUtil = new TomcatUtil();
            tomcatUtil.httpPort = getTomcatHttpPort();
        }
        return tomcatUtil;
    }

    public String getHttpPort() {
        return this.httpPort;
    }

    /**
     * todo 加注释
     * @return
     */
    private static String getTomcatHttpPort() {
        MBeanServer mBeanServer = null;
        if (MBeanServerFactory.findMBeanServer(null).size() > 0) {
            mBeanServer = (MBeanServer) MBeanServerFactory.findMBeanServer(null).get(0);
        }
        Set names = null;
        try {
            names = mBeanServer.queryNames(new ObjectName("Catalina:type=Connector,*"), null);
            Iterator it = names.iterator();
            ObjectName oname;
            while (it.hasNext()) {
                oname = (ObjectName) it.next();
                String pvalue = mBeanServer.getAttribute(oname, "protocol").toString();
                String svalue = mBeanServer.getAttribute(oname, "scheme").toString();
                return ((Integer) mBeanServer.getAttribute(oname, "port")).toString();
            }
        } catch (Exception e) {
            logger.error("", e);
            return "";
        }

        return "";
    }

}
