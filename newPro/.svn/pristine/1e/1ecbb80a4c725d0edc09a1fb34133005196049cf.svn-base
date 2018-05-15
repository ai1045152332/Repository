<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="com.honghe.recordweb.action.SessionManager" %>
<%@ page import="com.honghe.recordhibernate.dao.Pager" %>
<%@ page import="com.honghe.recordhibernate.dao.Page" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="org.apache.axiom.om.util.Base64" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="sun.misc.BASE64Decoder" %>
<%@ page import="sun.misc.BASE64Encoder" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: lch
  Date: 2014/10/11
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<base href="${pageContext.request.contextPath}">
<link href="${pageContext.request.contextPath}/css/frontend/index.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/frontend/hpager.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/common/jquery.cookie.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/common/deployJava.js" type="text/javascript"></script>
<body class="showmessage" >
<style>
  .showmessage{
    border-radius: 3px;
    background: #F4F4F4;
    border-color:#6e7685;
    border:1px solid;
  }
  #msg_title{
    background:#6e7685 ;
    height:20px;
  }
</style>
<%
  Map cleaninfo = (Map)request.getAttribute("cleaninfo");
  String ip = cleaninfo.get("dapingip").toString();
  String params = cleaninfo.get("parames").toString();
%>
<div id="msg_title"></div>
<div class=" showhidden" style="position: relative;margin-top: 5%;width: 100%;text-align: center;font-size: 16px;">IP:<%=ip%></div>

<div class="pages" style="font-size: 18px;width: 100%;text-align: center;margin-right: 120px;margin-left: 0;margin-top: 15%; min-width:155px;">垃圾<%=params%></div>

</body>
