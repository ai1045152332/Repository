<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
  <title>系统设置 | 集控平台</title>
  <link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css"/>
  <link href="${pageContext.request.contextPath}/css/frontend/time_celve.css" rel="stylesheet" type="text/css">
  <link href="${pageContext.request.contextPath}/css/frontend/record/hpager.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/index.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/main.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/fd-slider.css" rel="stylesheet" type="text/css"/>
  <script src="${pageContext.request.contextPath}/js/common/jquery-1.9.1.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/lb_common/screendetailforrecord.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/lb_frontend/setting/setting.js"></script>
</head>
<body>

<div class="public" style="min-width: 1680px;">
    <jsp:include page="/pages/frontend/lb_header.jsp"></jsp:include>
    <style>
      .mm_list_text {
        text-indent: 0;

      }
    </style>
        <div class="set">
          <div class="set_head">
            <div class="settinghead">
              <ul>
                <li onclick="javascript:jump(0)" ><div class="tb"></div>台标</li>
                <li onclick="javascript:jump(1)"><div class="videoinfo"></div>录像信息</li>
                <li onclick="javascript:jump(2)"><div class="living"></div>直播</li>
                <li onclick="javascript:jump(3)"><div class="nas"></div>NAS存储设置</li>
                <li onclick="javascript:jump(4)"><div class="passuser"></div>录播主机用户名密码设置</li>
                <li onclick="javascript:jump(5)" style="color:#28b779;"><div class="ftp"></div>FTP设置</li>
                <li onclick="javascript:jump(6)" ><div class="license"></div>License设置</li>
                <li onclick="javascript:jump(7)" ><div class="second_nav_selected"></div>课表类型选择</li>
                <li onclick="javascript:jump(8)" ><div class="second_nav_schedule"></div>课表管理</li>
                <li onclick="javascript:jump(9)" ><div class="second_nav_celve"></div>作息时间策略</li>
                <li onclick="javascript:jump(10)"><div class="record_name_setting"></div>录制文件名设置</li>
              </ul>
            </div>
          </div>
          <%
            String ftpName = (String) request.getAttribute("ftpName");
            String ftpPort = (String) request.getAttribute("ftpPort");
            String uName = (String) request.getAttribute("uName");
            String uPwd = (String) request.getAttribute("uPwd");
          %>
          <div class="win520_content" style="background: none;border:none">
            <div class="set_bg" style="height: 190px;">
              <div class="tab_content_list" style="margin-top: 15px;">
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
            </div>
            <div class="set_vv_foot">
              <div class="set_vv_save" onclick="save()">保存</div>
            </div>
          </div>
        </div>
      <div class="foot" >
	<jsp:include page="/pages/frontend/footer.jsp"></jsp:include>
</div>
</div>

</body>
</html>
<script>

  $(function () {
    $(".set_vv_save").mouseover(function () {
      $(this).css("background", "#5ec699");
    }).mouseout(function () {
      $(this).css("background", "#28b779");
    })
    //开关
    $("#close").click(function () {
      $(this).attr("class", "setopen set_vv_rightborder");
      $("#open").attr("class", "setclose set_vv_leftborder");
    })
    $("#open").click(function () {
      $(this).attr("class", "setopen set_vv_leftborder");
      $("#close").attr("class", "setclose set_vv_rightborder");
    })
  })

  function save() {
    var ftpname = $("#ftpname").val().trim();
    var ftpport = $("#ftpport").val().trim();
    var uname = $("#uname").val().trim();
    var upwd = $("#pwd").val().trim();
    if (ftpname.length == 0) {
      alert("FTP地址不能为空！");
      return;
    }else{
      var patrn=/^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/;
      if(!patrn.exec(ftpname)){
        alert("请输入正确的FTP地址");
        return;
      }
    }
    if (ftpport.length == 0) {
      alert("端口号不能为空！");
      return;
    }
    if (uname.length == 0) {
      alert("用户名不能为空！");
      return;
    }
    if (upwd.length == 0) {
      alert("密码不能为空！");
      return;
    }
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

</script>

