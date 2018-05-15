package com.honghe.recordweb.service.frontend.websocket;

import com.honghe.recordweb.service.frontend.devicemanager.NVRCommand;
import com.honghe.recordweb.service.frontend.devicemanager.message.DeviceRequestMessage;
import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.catalina.websocket.WsOutbound;
import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * webSocket 服务
 * User: zhanghongbin
 * Date: 14-9-17
 * Time: 上午9:56
 * To change this template use File | Settings | File Templates.
 */
@WebServlet("/webSocket")
public class WebSocketServer extends WebSocketServlet {

    private final static Logger logger = Logger.getLogger(WebSocketServer.class);
    @Override
    protected StreamInbound createWebSocketInbound(String s, HttpServletRequest httpServletRequest) {
      //  User user = SessionManager.get(httpServletRequest.getSession(), SessionManager.Key.USER);
        String userId;
        //if (user == null) {
        userId = httpServletRequest.getSession().getId();
        String token = httpServletRequest.getParameter("token");
        String uid = httpServletRequest.getParameter("uid");
        //} else {
           // userId = user.getUserId().toString();
       // }
        return new WebSocketMessageInbound(userId,token,uid);

    }

    /**
     * todo 加注释
     */
    private class WebSocketMessageInbound extends MessageInbound {

        private String id;
        private String token;
        private String uid;
        public WebSocketMessageInbound(String id,String token,String uid) {
            this.id = id;
            this.token = token;
            this.uid = uid;
        }

        /**
         * todo 加注释
         * @param byteBuffer
         * @throws IOException
         */
        @Override
        protected void onBinaryMessage(ByteBuffer byteBuffer) throws IOException {

        }

        /**
         * todo 加注释
         * @param charBuffer
         * @throws IOException
         */
        @Override
        protected void onTextMessage(CharBuffer charBuffer) throws IOException {
            String data = charBuffer.toString();
            logger.debug("command request data is " + data);
            DeviceRequestMessage deviceRequestMessage = DeviceRequestMessage.parse(data);
            //DeviceRequestMessage deviceRequestMessage = DeviceRequestMessage.parse(charBuffer.toString());
            deviceRequestMessage.setUserId(this.id);
            NVRCommand nvrCommand = ContextLoaderListener.getCurrentWebApplicationContext().getBean(NVRCommand.class);
            nvrCommand.executeOperatorMessage(deviceRequestMessage);
        }

        /**
         * todo 加注释
         * @param outbound
         */
        @Override
        protected void onOpen(WsOutbound outbound) {
            SessionPool.add(this.id, this);
            if (token != null) {
                DirectPool.add(token,this.id);
            }
        }

        /**
         * todo 加注释
         * @param status
         */
        @Override
        protected void onClose(int status) {
            SessionPool.remove(this.id);
            if (token != null) {
                DirectPool.remove(token);
            }
        }
    }
}


