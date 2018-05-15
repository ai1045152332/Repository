package com.honghe.recordweb.service.frontend.websocket;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WsOutbound;
import org.apache.log4j.Logger;

import java.nio.CharBuffer;
import java.util.Set;

/**
 * websocket 发送消息到js
 * User: zhanghongbin
 * Date: 14-9-17
 * Time: 上午11:30
 * To change this template use File | Settings | File Templates.
 */
public final class MessageSender {

    final static Logger logger = Logger.getLogger(MessageSender.class);
    private MessageSender() {

    }

    /**
     * 发送消息到web js负责接收
     *
     * @param id  用户标识
     * @param message 消息内容
     * @return
     */
    public static final boolean send(final String id, final String message) {
        boolean flag = false;
        if (SessionPool.contains(id)) {
            MessageInbound messageInbound = SessionPool.get(id);
            try {
                //if (messageInbound.onData() != AbstractEndpoint.Handler.SocketState.CLOSED) {
                    WsOutbound wsOutbound = messageInbound.getWsOutbound();
                    wsOutbound.writeTextMessage(CharBuffer.wrap(message));
                    flag = true;
                //}
                    logger.debug("事件发送到浏览器端:id为"+id+",内容为:"+message);

            } catch (Exception e) {
                logger.error("事件发送到浏览器端异常:id为"+id+",内容为:"+message,e);
//                logger.info(e.getMessage());
            }
        }
        return flag;
    }

    /**
     * 给所有人发送消息
     * @param message
     */
    public static final void send(String message){
        Set<String> idSet = SessionPool.Keys();
        for (String id : idSet) {
            MessageSender.send(id, message);
        }
    }
}
