<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
  <meta HTTP-EQUIV="pragma" CONTENT="no-cache">
  <meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
  <meta HTTP-EQUIV="expires" CONTENT="0">
  <title>监看画面</title>
  <link href="${pageContext.request.contextPath}/css/frontend/index.css" rel="stylesheet" type="text/css" />
  <link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/split_screen_p.css">
  <script src="${pageContext.request.contextPath}/js/common/jquery-1.9.1.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
  <link href="${pageContext.request.contextPath}/css/schedule.css" rel="stylesheet" type="text/css">
  <script src="${pageContext.request.contextPath}/js/lb_common/split_screen.js"></script>
</head>
<%
  String hostName = (String)request.getAttribute("hostName");
  String currentPage = (String)request.getAttribute("currentPage");
%>
<body>
<div class="public">
  <jsp:include page="/pages/frontend/lb_header.jsp"></jsp:include>
    <div class="mm" style='height:100%;background:#707681;'>
      <div class="mm_head" style="background:#dee0e4">
        <a href="javascript:gotoViewClass(<%=currentPage%>);"><span class="user_head_option"><span class="user_head_return_icon"></span ><span style="float: left;margin-left:10px;">返回</span></span></a>
        <span class="user_head_option"><%=hostName%></span>
      </div>
    <div class="split_screen_p">
      <div class="schedule_no_data" style="margin-top:10%">
        <img src="${pageContext.request.contextPath}/image/schedule/no_data.png" title="" /><br/>
        <span style="color:#fff;">哎呀~暂时还没有可以显示的通道哦！</span>
      </div>
    </div>
  </div>
</div>
</body>
</html>

