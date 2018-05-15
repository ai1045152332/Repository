<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
  <title>FTP设置</title>


    <link href="${pageContext.request.contextPath}/css/frontend/record/hpager.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/index.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/main.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/fd-slider.css" rel="stylesheet" type="text/css"/>
  <script src="${pageContext.request.contextPath}/js/common/jquery-1.8.0.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/checkbox.js"></script>

</head>
<%
  String ftpName = (String) request.getAttribute("ftpName");
  String ftpPort = (String) request.getAttribute("ftpPort");
  String uName = (String) request.getAttribute("uName");
  String uPwd = (String) request.getAttribute("uPwd");
%>
<body>
<div class="win300">
  <div  class="win300_content" style="height: 362px;">
    <div class="tab_content_list" >
        <div class="ftperror"><!--连接错误，请重新输入--></div>
      <span class="tab_content_listtext" style="color: #333;">FTP地址：</span>
      <input maxlength="20" id="ftpname" value="<%=ftpName != null ? ftpName : "" %>" class="tab_content_listinput" type="text">
    </div>

    <div class="tab_content_list">
      <span class="tab_content_listtext" style="color: #333;">端口号：</span>
      <input maxlength="20" id="ftpport" value="<%=ftpPort != null ? ftpPort : ""%>" class="tab_content_listinput" type="text">&nbsp;
    </div>
    <div class="tab_content_list">
      <span class="tab_content_listtext" style="color: #333;">用户名：</span>
      <input maxlength="20" id="uname" value="<%=uName != null ? uName : ""%>" class="tab_content_listinput" type="text">&nbsp;
    </div>
    <div class="tab_content_list">
      <span class="tab_content_listtext" style="color: #333;">密码：</span>
      <input maxlength="20" id="pwd" value="<%=uPwd != null ? uPwd : ""%>" class="tab_content_listinput" type="text">&nbsp;
    </div>
      <div class="tab_content_list">
          <span class="tab_content_listtext" style="color: #333;">&nbsp;</span>
          <div  class="tab_content_listmove" id="testFtpBtn" style="border-radius: 3px;position: relative;">
              <div class="tab_content_listmovedom"  style="display:none"></div>
              <span class="testFtpProgress">开始测试</span>
              <!--测试中.../重新测试-->
          </div>
      </div>
    <div class="win_btn" style="margin: 35px 20px 20px 0;">
      <div class="win_btn_sure" style="margin-right: 20px;" onclick="save()">确定</div>
      <div class="win_btn_done" onclick="cancel()">取消</div>
    </div>
  </div>
  </div>
</body>
</html>
<Script>
  function save() {
    var ftpname = $("#ftpname").val().trim();
    var ftpport = $("#ftpport").val().trim();
    var uname = $("#uname").val().trim();
    var upwd = $("#pwd").val().trim();
    $.post('${pageContext.request.contextPath}/settings/Settings_saveSettingFtp.do', {
      // settingType: 3,
      ftpName: ftpname,
      ftpPort: ftpport,
      uName:uname,
      uPwd:upwd
    }, function (data) {
      if (data.success == true) {
        layer.msg(data.msg);
      }
      else {
        layer.msg(data.msg);
      }
    }, "json");
  }
  var clickNum = 0;
  $("#testFtpBtn").click(function(){
      var ftpname = $("#ftpname").val().trim();
      var ftpport = $("#ftpport").val().trim();
      var uname = $("#uname").val().trim();
      var upwd = $("#pwd").val().trim();
      clickNum ++;
      $(".tab_content_listmovedom").css("display","block");
      var parentO = $(".testFtpProgress");
      var ftpError = $(".ftperror");
      parentO.text("测试中...");
      if(clickNum == 1)
      {
          $.get('${pageContext.request.contextPath}/settings/Settings_testFtpConnect.do', {
              ftpName: ftpname,
              ftpPort: ftpport,
              uName:uname,
              uPwd:upwd
          }, function (data) {
              clickNum = 0;
              parentO.text("重新测试");
              $(".tab_content_listmovedom").css("display","none");
              if (data.success == true) {
                  ftpError.text("连接成功");
              }
              else {
                  ftpError.text("连接错误，请重新输入");
              }
          }, "json");
      }

  });

  function cancel()
  {
    parent.layer.closeAll();
  }
</Script>