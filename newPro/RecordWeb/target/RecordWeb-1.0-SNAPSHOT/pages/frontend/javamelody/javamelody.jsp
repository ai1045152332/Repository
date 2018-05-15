<%@ page import="com.honghe.recordhibernate.entity.DeviceLog" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.honghe.recordhibernate.dao.Pager" %>
<%--
  Created by IntelliJ IDEA.
  User: lch
  Date: 2015/1/14
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
  <title>系统监控 | 集控平台</title>
  <link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css" />
  <link href="${pageContext.request.contextPath}/css/frontend/index.css" rel="stylesheet" type="text/css" />

  <script src="${pageContext.request.contextPath}/js/common/jquery-1.8.0.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/screendetail.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/perfect-scrollbar.with-mousewheel.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/prettify.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/checkbox_res.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/selectmore.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
  <!--layerdate-->
  <script src="${pageContext.request.contextPath}/js/common/laydate/laydate.js"></script>
  <style>
    </style>
</head>

<body style="overflow-y:hidden">
<div class="public">
  <jsp:include page="/pages/frontend/equipment_header.jsp"></jsp:include>
    <iframe src="${pageContext.request.contextPath}/monitoring"  class="mm floatleft" style="background: #fff" ></iframe>
    <div class="foot">
      <jsp:include page="/pages/frontend/footer.jsp"></jsp:include>
    </div>
</div>
</body>
</html>