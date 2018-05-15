<%@ page import="com.honghe.recordhibernate.entity.Device" %>
<%@ page import="com.honghe.recordweb.service.frontend.devicemanager.NVR" %>
<%--
  Created by IntelliJ IDEA.
  User: zhanghongbin
  Date: 14-10-21
  Time: 下午5:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <title>增加设备</title>
  <link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css"/>
  <link href="${pageContext.request.contextPath}/css/frontend/index.css" rel="stylesheet" type="text/css"/>
  <script src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/checkbox.js"></script>
  <style>
    .all {
      max-width: 200px;
      width: auto;
      float: left;
    }

    .head1, .head {
      margin-top: 5px;
    }

    body {
      overflow: hidden;
      top: 0;
      position: relative;
    }

    .sall {
      margin-left: 20px;
      width: 185px;
    }

    .selectdiv {
      background: none;
    }

    #selectdivall0, #selectdivall1, #selectdivall2, #selectdivall3, #selectdivall4, #selectdivall5, #selectdivall6 {
      background: none;
      width: 184px;
    }

    .selectdiv {
      background: url(${pageContext.request.contextPath}/image/frontend/n_icon_141016.png) 0 0 no-repeat;
      width: 184px;
      height: 27px;
      line-height: 27px;
    }

    .selectdivul {
      width: 182px;
    }

    .selectdivul a {
      /*text-align: center;*/
      text-indent: 10px;
      width: 182px;
    }

    #selectdivul0, #selectdivul1, #selectdivul2, #selectdivul3, #selectdivul4, #selectdivul5, #selectdivul6 {
      border: 1px solid #dbdbdb;
      margin-top: -2px;
      width: 182px;
    }

    #selectdivul1 {
      width: 182px;
    }

    #selectdivul1 a {
      width: 182px;
    }

    .jk_condition_check {
      cursor: pointer;
      float: left;
      line-height: 30px;
      margin-left: 10px;
      text-overflow: ellipsis;
      white-space: nowrap;
      overflow: hidden;
      width: 190px;
    }
  </style>
</head>
<body>
<%
  //String mediaUrl = request.getAttribute("mediaUrl").toString();
  Device device = (Device)request.getAttribute("device");
%>
<div class="win520_content" style="height:800px;box-shadow: 0 0 0 1px #212121 inset;">
  <div style="margin: 5% auto;width: 650px;">
    <div class="xk_video" style="background:white;position: relative;margin-left: 0;margin-top: 38px;" bingo="none">
      <div id="onecamera" name="<%=device.getDeviceName()%>">
        <object id="ScriptableObject3" CLASSID="CLSID:3a22176d-118f-4185-9653-cdea558a6df8" name="ScriptableObject"
                url="<%=device.getDeviceMainStream()%>" WIDTH=650 HEIGHT=450></object>
      </div>
    </div>
  </div>
</div>
<script>
  $(function () {

    var names = document.getElementsByName("ScriptableObject");
    for (var i = 0; i < names.length; i++) {
      var url = names[i].getAttribute("url");
      var id = names[i].getAttribute("id");
      var obj = document.getElementById(id);
      try {
        names[i].play(url);
      } catch (e) {
      }
    }
    var videp_big = $("#onecamera").attr("name");
    if(videp_big=="电影模式"){
      $(".win520_content div:first").width(1100).css("margin-top","0");
      $("#ScriptableObject3").height(650).width(1100);
    }else {
      $(".win520_content div:first").width(650).css("margin-top","60px");
      $("#ScriptableObject3").height(450).width(650);
    };

  });
</script>
</body>
</html>
