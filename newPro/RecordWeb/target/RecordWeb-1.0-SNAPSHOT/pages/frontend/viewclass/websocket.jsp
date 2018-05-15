<%@ page import="com.honghe.recordweb.action.SessionManager" %>
<%@ page import="com.honghe.recordhibernate.entity.User" %>
<%@ page import="com.honghe.recordweb.service.frontend.hostgroup.HostgroupService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: zhanghongbin
  Date: 14-10-15
  Time: 上午11:17
  To change this template use File | Settings | File Templates.
--%>
<%
    HostgroupService hostgroupService = new HostgroupService();
    String ip = hostgroupService.getIp();
    String serviceAddr = ip + ":" + request.getLocalPort();
%>
<script src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frontend/websocket/swfobject.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frontend/websocket/web_socket.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/frontend/websocket/reconnecting-websocket.js"></script>
<%--<script type="text/javascript"--%>
        <%--src="${pageContext.request.contextPath}/js/common/noty-2.2.9/jquery.noty.packaged.min.js"></script>--%>


<script>

    //var WEB_SOCKET_SWF_LOCATION = "http://<%=serviceAddr%>${pageContext.request.contextPath}/js/frontend/websocket/WebSocketMainInsecure.swf";
    //var WEB_SOCKET_DEBUG = true;
    var wsUrl = "ws://<%=serviceAddr%>${pageContext.request.contextPath}/webSocket";
    var message;
    var tipMessage = [];
    /**
     *  消息类
     * @param messageEventCallback 事件回调函数
     * @constructor
     */
    function Message(messageEventCallback) {
        var ws = new ReconnectingWebSocket(wsUrl);
        ws.onmessage = function (e) {
            messageEventCallback(e.data);
        };
        ws.onclose = function () {

        };
        ws.onerror = function () {

        };
        this.processOnlineOfflineEvent = function (token, eventType) {


        };
        this.processOtherEvent = function (token, eventType, desc) {
            // notify(desc);
        }
        this.processResponse = function(token, eventType, desc){

        }
        this.send = function (data) {
            ws.send(data);
        };

    }


    $(function () {
        message = new Message(function (data) {
            var jsonData = jQuery.parseJSON(data);
            if (jsonData.type == "RESPONSE") {
                var result = jsonData.desc.trim();
                if (result != '') {
                    //if(jsonData.eventType != ''){
                        message.processResponse(jsonData.token, jsonData.eventType, jsonData.desc);
                       // return;
                   // }
//                    var isNotify = true;
//                    for(var i = 0; i < tipMessage.length;i++){
//                        if(tipMessage[i] == result){
//                            isNotify = false;
//                        }
//                    }
//                    if(isNotify){
//                        tipMessage.push(result);
//                        notify(result);
//                    }

                }
            } else {

                if (jsonData.eventType == "101" || jsonData.eventType == "102") {
                    message.processOnlineOfflineEvent(jsonData.token, jsonData.eventType);
                } else {
                    if (jsonData.desc.trim() != '') {
                        message.processOtherEvent(jsonData.token, jsonData.eventType, jsonData.desc.trim());
                    }
                }
            }

        });
    })

    /**
     * 消息通知
     * @param content 通知内容
     */
    function notify(content) {
        noty({
            text: content,
            type: 'success',
            dismissQueue: true,
            layout: 'top',
            theme: 'defaultTheme',
            maxVisible: 3,
            closeWith: ['button']
        });
    }


</script>