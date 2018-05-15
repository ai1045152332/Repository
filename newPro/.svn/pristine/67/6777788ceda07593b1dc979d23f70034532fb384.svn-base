<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title></title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/frontend/main.css"/>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/frontend/index.css"/>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/screendetail.js"></script>
</head>
<body style="background: #000">
<%
  String bigPacketLenMax = (String) request.getAttribute("bigPacketLenMax");
  String bigPacketLenMin = (String) request.getAttribute("bigPacketLenMin");
  String smallPacketLenMax = (String) request.getAttribute("smallPacketLenMax");
  String smallPacketLenMin = (String) request.getAttribute("smallPacketLenMin");
  String frameInterval = (String) request.getAttribute("frameInterval");
  String smallCount = (String) request.getAttribute("smallCount");
  String commMode = (String) request.getAttribute("commMode");
  String udpPort = (String) request.getAttribute("udpPort");
  String format = (String) request.getAttribute("format");
%>
<div class="network" style="margin: 1px">
  <div class="win_head">
    <div class="win_head_text">测速设置</div>
    <div class="win_close" onclick="settingNo()"></div>
  </div>
  <div class="network_line">
    <style>
      .head2{
        width: auto;
      }
      .headr2{
        float: left;
        margin-top: 11px;
        width: auto;
      }
      .bgr,.bgred{
        margin-left: 10px;
      }
    </style>
    <span class="network_line_txt">视频格式：</span>
    <%
      if("0".equals(format)){
    %>
    <div class="headr2" id="1080P" onclick="click1080()">
      <span class="bgred" ></span>1080P
    </div>
    <div class="headr2" id="720P" onclick="click720()">
      <span class="bgr" ></span>720P
    </div>
    <div class="headr2" id="CIF" onclick="clickcif()">
      <span class="bgr"></span>CIF
    </div>
    <%
      }else if("1".equals(format)){
    %>
    <div class="headr2" id="1080P" onclick="click1080()">
      <span class="bgr" ></span>1080P
    </div>
    <div class="headr2" id="720P" onclick="click720()">
      <span class="bgred" ></span>720P
    </div>
    <div class="headr2" id="CIF" onclick="clickcif()">
      <span class="bgr"></span>CIF
    </div>
    <%
      }else if("2".equals(format)){
    %>
    <div class="headr2" id="1080P" onclick="click1080()">
      <span class="bgr" ></span>1080P
    </div>
    <div class="headr2" id="720P" onclick="click720()">
      <span class="bgr" ></span>720P
    </div>
    <div class="headr2" id="CIF" onclick="clickcif()">
      <span class="bgred"></span>CIF
    </div>
    <%
      }
    %>
  </div>
  <div class="network_line">
    <span class="network_line_txt">大帧最大长度：</span>
    <input  id="bigPacketLenMax" type="text" class="network_line_inputs" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" value=<%=bigPacketLenMax%> >
    <span class="network_line_bit">字节</span>
    <span class="network_line_txt">大帧最小长度：</span>
    <input id="bigPacketLenMin" type="text" class="network_line_inputs" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" value=<%=bigPacketLenMin%> >
    <span class="network_line_bit">字节</span>
  </div>
  <div class="network_line">
    <span class="network_line_txt">小帧最大长度：</span>
    <input id="smallPacketLenMax" type="text" class="network_line_inputs" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" value=<%=smallPacketLenMax%> >
    <span class="network_line_bit">字节</span>
    <span class="network_line_txt">小帧最小长度：</span>
    <input id="smallPacketLenMin" type="text" class="network_line_inputs" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" value=<%=smallPacketLenMin%> >
    <span class="network_line_bit">字节</span>
  </div>
  <div class="network_line">
    <span class="network_line_txt">每帧之间的时间间隔单位：</span>
    <input id="frameInterval" type="text" class="network_line_inputs"  style="width: 220px;" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" value=<%=frameInterval%> >
    <span class="network_line_bit" style="width: 45px;">ms</span>
  </div>
  <div class="network_line">
    <span class="network_line_txt">两个大帧之间的小包数目：</span>
    <input id="smallCount" type="text" class="network_line_inputs"  style="width: 220px;" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" value=<%=smallCount%> >
    <!--<span class="network_line_bit" style="width: 45px;">ms</span>-->
  </div>
  <div class="network_line" style="border:1px solid #dbdbdb;width: 92%;">
    <style type="text/css">
      .headr{
        float: left;
        margin-top: 11px;
        width: auto;
      }
    </style>
    <%
      if("0".equals(commMode)){
    %>
      <div class="headr" style="margin-right:230px;">
        <span id="tcp" class="bgred" ></span>TCP协议测试
      </div>
      <div class="headr" style="margin-right:230px;">
         <span  class="bgr" id="udp"></span>UDP协议测试
      </div>
    <span class="network_line_bit" style="margin-left: 50px;width: 65px; color: #020202;" >UDP端口</span>
    <input id="udpPort" type="text" class="network_line_inputs"  style="width: 220px;margin-bottom: 17px;border:1px solid #dbdbdb;" disabled=""  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" value=<%=udpPort%>>
    <%
      }else{
    %>
        <div class="headr" style="margin-right:230px;">
          <span id="tcp" class="bgr"></span>TCP协议测试
        </div>
        <div class="headr" style="margin-right:230px;">
          <span  class="bgred" id="udp"></span>UDP协议测试
        </div>
    <span class="network_line_bit" style="margin-left: 50px;width: 65px; color: #020202;">UDP端口</span>
    <input id="udpPort" type="text" class="network_line_inputs"  style="width: 220px;margin-bottom: 17px;border:1px solid #dbdbdb" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" value=<%=udpPort%>>
    <%
      }
    %>
  </div>
  <div class="network_line_btn" onclick="reset() " style="margin-left: 15px">重置</div>
  <div class="network_line_btn" onclick="settingNo()" style="background: #fff;margin-left: 140px;color:#020202;border:1px solid #dbdbdb">取消</div>
  <div class="network_line_btn" onclick="settingOk()" style="margin-left: 30px">确定</div>
</div>
<script type="text/javascript">
  $(function(){
    $(".headr").click(function(){
      $(".headr").children("span").attr("class","bgr");
      var flag=$(this).children("span").attr("class");
      if(flag=="bgr")
      {
        $(this).children("span").attr("class","bgred");
        if($("#tcp").attr("class")=="bgred"){
          $("#udpPort").prop("disabled",true)
        }else if($("#tcp").attr("class")=="bgr"){
          $("#udpPort").prop("disabled",false)
        }
      }
    })
  });
  function settingOk(){
    var commMode = "0";
    var format = "0";
    var udpPort = $("#udpPort").val().trim();
    var bigPacketLenMax = $("#bigPacketLenMax").val().trim();
    var bigPacketLenMin = $("#bigPacketLenMin").val().trim();
    var smallPacketLenMax = $("#smallPacketLenMax").val().trim();
    var smallPacketLenMin = $("#smallPacketLenMin").val().trim();
    var frameInterval = $("#frameInterval").val().trim();
    var smallCount = $("#smallCount").val().trim();
    if(udpPort.length == 0){
      alert("UDP端口不能为空！");
      return;
    }else if(bigPacketLenMax.length == 0){
      alert("大帧最大长度不能为空！");
      return;
    }else if(bigPacketLenMin.length == 0){
      alert("大帧最小长度不能为空！");
      return;
    }else if(smallPacketLenMax.length == 0){
      alert("小帧最大长度不能为空！");
      return;
    }else if(smallPacketLenMin.length == 0){
      alert("小帧最小长度不能为空！");
      return;
    }else if(frameInterval.length == 0){
      alert("每帧之间的时间间隔单位不能为空！");
      return;
    }else if(smallCount.length == 0){
      alert("两个大帧之间的小包数目不能为空！");
      return;
    }else if(parseInt(frameInterval)<25 || parseInt(frameInterval)>40){
      alert("设置每帧之间的时间间隔单位范围应在25-40之间！");
      return;
    }else if(parseInt(smallCount)<20 || parseInt(smallCount)>30){
      alert("设置两个大帧之间的小包数目范围应在20-30之间！");
      return;
    }else if(parseInt(bigPacketLenMin) > parseInt(bigPacketLenMax)){
      alert("大帧最小长度不能超过最大长度！");
      return;
    }else if(parseInt(smallPacketLenMin) > parseInt(smallPacketLenMax)) {
      alert("小帧最小长度不能超过最大长度！");
      return;
    }
    if($("#1080P").children("span").attr("class") == "bgred"){
      if(parseInt(bigPacketLenMax)>200000 || parseInt(bigPacketLenMin)<100000){
        alert("1080P下大帧测试范围应在200000-100000之间");
        return;
      }else if(parseInt(smallPacketLenMin)<5000 || parseInt(smallPacketLenMax)>10000){
        alert("1080P下小帧范围应在10000-5000之间");
        return;
      }
    }else if($("#720P").children("span").attr("class") == "bgred"){
      if(parseInt(bigPacketLenMax)>100000 || parseInt(bigPacketLenMin)<50000){
        alert("720P下大帧测试范围应在100000-50000之间");
        return;
      }else if(parseInt(smallPacketLenMin)<2500 || parseInt(smallPacketLenMax)>5000){
        alert("720P下小帧范围应在5000-2500之间");
        return;
      }
    }else{
      if(parseInt(bigPacketLenMax)>25000 || parseInt(bigPacketLenMin)<12500){
        alert("CIF下大帧测试范围应在25000-12500之间");
        return;
      }else if(parseInt(smallPacketLenMin)<625 || parseInt(smallPacketLenMax)>1250){
        alert("CIF下小帧范围应在1250-625之间");
        return;
      }
    }
    if ($("#tcp").attr("class") == "bgred") {
      commMode = "0";
    }else{
      commMode = "1";
    }
    if( $("#1080P").children("span").attr("class")=="bgred"){
      format = "0"
    }else if($("#720P").children("span").attr("class")=="bgred"){
      format = "1"
    }else if($("#CIF").children("span").attr("class")=="bgred"){
      format = "2"
    }

    $.post('${pageContext.request.contextPath}/hnetdetecttool/HNetDetectTool_change.do', {
              commMode: commMode,
              format:format,
              udpPort: udpPort,
              bigPacketLenMax: bigPacketLenMax,
              bigPacketLenMin: bigPacketLenMin,
              smallPacketLenMax: smallPacketLenMax,
              smallPacketLenMin: smallPacketLenMin,
              frameInterval: frameInterval,
              smallCount: smallCount
            }, function (data) {
              setTimeout(settingNo(),2000);
            }
    );
  }
  function settingNo(){
    parent.$("#iframe").hide();
  }
  function reset(){
    $("#udpPort").val(13000);
    $("#bigPacketLenMax").val(200000);
    $("#bigPacketLenMin").val(100000);
    $("#smallPacketLenMax").val(10000);
    $("#smallPacketLenMin").val(5000);
    $("#frameInterval").val(35);
    $("#smallCount").val(25);
    $("#1080P").children("span").attr("class","bgred");
    $("#720P").children("span").attr("class","bgr");
    $("#CIF").children("span").attr("class","bgr");
  }
  function click1080(){
    $("#bigPacketLenMax").val(200000);
    $("#bigPacketLenMin").val(100000);
    $("#smallPacketLenMax").val(10000);
    $("#smallPacketLenMin").val(5000);
    $("#1080P").children("span").attr("class","bgred");
    $("#720P").children("span").attr("class","bgr");
    $("#CIF").children("span").attr("class","bgr");
  }
  function click720(){
    $("#bigPacketLenMax").val(100000);
    $("#bigPacketLenMin").val(50000);
    $("#smallPacketLenMax").val(5000);
    $("#smallPacketLenMin").val(2500);
    $("#1080P").children("span").attr("class","bgr");
    $("#720P").children("span").attr("class","bgred");
    $("#CIF").children("span").attr("class","bgr");
  }
  function clickcif(){
    $("#bigPacketLenMax").val(25000);
    $("#bigPacketLenMin").val(12500);
    $("#smallPacketLenMax").val(1250);
    $("#smallPacketLenMin").val(625);
    $("#1080P").children("span").attr("class","bgr");
    $("#720P").children("span").attr("class","bgr");
    $("#CIF").children("span").attr("class","bgred");
  }
</script>
</body>
</html>
