package com.honghe.recordweb.util;

import com.hht.smartcampus.sip.SIPManager;
import com.hht.smartcampus.sip.SIPManagerCallback;
import com.honghe.recordweb.service.frontend.event.DeviceEvent;
import org.apache.log4j.Logger;

import java.util.Timer;
import java.util.TimerTask;

/**
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: 北京鸿合盛视数字媒体技术有限公司</p>
 *
 * @author hthwx
 * @date 2016/8/16
 */
public class SipUtil
{
    private static final Logger logger=Logger.getLogger(SipUtil.class);
    private static String server_ip = ConfigUtil.get("SIPServerIp"); //服务端ip
    private static String server_port = ConfigUtil.get("SIPServerPort"); //服务端端口
    private static String client_ip = ConfigUtil.get("SIPClientIp");//客户端ip
    private static String client_port = ConfigUtil.get("SIPClientPort");//客户端端口
    private static int pool_size = 100;
    private String sip_id = "";

    public  SipUtil()
    {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                try {
                    sip_id = initSIP();
                    if (sip_id!=null && !"".equals(sip_id.trim())) {
                        timer.cancel();
                    }
                } catch (Exception e) {
                    logger.error("SIP客户端初始化异常", e);
                }
            }
        }, 10000,30000);
    }
    /**
     *
     * 初始化sip客户端
     * @return
     * @throws Exception
     */
    private String initSIP() throws Exception
    {
        SIPManagerCallback callback=new DmanagerSIPManagerCallback();
        if(server_ip == null)
        {
            server_ip ="";
        }
        if(server_port == null || "".equals(server_port))
        {
            server_port = "9010";
        }
        if(client_ip == null)
        {
            client_ip = "";
        }
        if(client_port == null || "".equals(client_port))
        {
            client_port = "9085";
        }
        SIPManager.setPrintMessage(true);
        SIPManager.init(pool_size, callback);
        //录播专用
        String SIPID1=SIPManager.AddAgent("lubo", client_ip, Integer.parseInt(client_port), server_ip, Integer.parseInt(server_port));
        logger.debug("SIP客户端启动成功，SIPID为" + SIPID1);
        return SIPID1;
    }

    /**
     * 移除sip客户端
     * @throws Exception
     */
    public  void removeSIP() throws Exception
    {
        SIPManager.RemoveAgent(sip_id);
    }

    public static String getServer_ip() {
        return server_ip;
    }

    public static void setServer_ip(String server_ip) {
        SipUtil.server_ip = server_ip;
    }

    public static String getServer_port() {
        return server_port;
    }

    public static void setServer_port(String server_port) {
        SipUtil.server_port = server_port;
    }

    public static String getClient_ip() {
        return client_ip;
    }

    public static void setClient_ip(String client_ip) {
        SipUtil.client_ip = client_ip;
    }

    public static String getClient_port() {
        return client_port;
    }

    public static void setClient_port(String client_port) {
        SipUtil.client_port = client_port;
    }

    public static int getPool_size() {
        return pool_size;
    }

    public static void setPool_size(int pool_size) {
        SipUtil.pool_size = pool_size;
    }

    public String getSip_id() {
        return sip_id;
    }

    public void setSip_id(String sip_id) {
        this.sip_id = sip_id;
    }
}
