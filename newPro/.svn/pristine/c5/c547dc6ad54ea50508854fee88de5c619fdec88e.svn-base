package com.honghe.recordweb.service.frontend.socket;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Tpaduser on 2015/7/29.
 */
public class SendMessage {
    final static Logger logger = Logger.getLogger(SendMessage.class);
    public SendMessage(){

    }

    /**
     * todo 加注释
     * @param mes
     */
    public static void sendSocketMessage(String mes){
        // 拿到所有的客户端Session
        Map<String,IoSession> sessions = AppSessionPool.sessionPool;
        // 向所有客户端发送数据
        if(sessions != null && sessions.size()>0){
            Iterator it = sessions.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                IoSession sess = (IoSession)entry.getValue();
                sess.write(mes);
            }
        }
        logger.debug("发消息到android端,客户端为:"+sessions+",内容为:"+mes);
    }
}
