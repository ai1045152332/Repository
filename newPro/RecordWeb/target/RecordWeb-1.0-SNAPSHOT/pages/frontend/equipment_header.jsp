<%@ page import="com.honghe.recordweb.action.SessionManager" %>
<%@ page import="com.honghe.recordweb.util.CommonName" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String deviceType = SessionManager.get(request.getSession(), SessionManager.Key.DeviceType);

    if (deviceType == null) deviceType = "";
%>

<%
    if (deviceType.equals(CommonName.DEVICE_TYPE_SCREEN)) {
%>
<jsp:include page="header.jsp"></jsp:include>
<%
} else if (deviceType.equals(CommonName.DEVICE_TYPE_RECOURD)) {
%>
<jsp:include page="lb_header.jsp"></jsp:include>
<%
} else if (deviceType.equals(CommonName.DEVICE_TYPE_PROJECTOR)) {
%>
<jsp:include page="pr_header.jsp"></jsp:include>
<%
} else if (deviceType.equals(CommonName.DEVICE_TYPE_WHITEBOARD)) {
%>
<jsp:include page="wb_header.jsp"></jsp:include>
<%
    }
%>