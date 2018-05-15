<%@ page import="com.honghe.authority.AuthorityCheck" %>
<%@ page import="com.honghe.recordweb.action.SessionManager" %>
<%@ page import="com.honghe.recordweb.util.LicenseUtils" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.honghe.recordweb.util.CommonName" %>
<%@ page import="java.util.List" %>
<%@ page import="com.honghe.recordhibernate.entity.Initialization" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.honghe.recordweb.exception.FrontException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
  <title>系统设置 | 集控平台</title>
  <link href="${pageContext.request.contextPath}/css/frontend/record/hpager.css" rel="stylesheet" type="text/css"/>
  <link href="${pageContext.request.contextPath}/css/frontend/record/fd-slider.css" rel="stylesheet" type="text/css"/>
  <script src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/selectmore.js"></script>

</head>
<body>

<div class="public" style="min-width: 1680px;">
  <jsp:include page="/pages/frontend/equipment_header.jsp"></jsp:include>
  <%
    String device_type = SessionManager.get(request.getSession(), SessionManager.Key.DeviceType);
  %>
  <%
    if (device_type.equals(CommonName.DEVICE_TYPE_SCREEN) || device_type.equals(CommonName.DEVICE_TYPE_WHITEBOARD)) {
  %>
  <script src="${pageContext.request.contextPath}/js/common/screendetail.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/frontend/setting/setting.js"></script>
  <link href="${pageContext.request.contextPath}/css/frontend/index.css" rel="stylesheet" type="text/css"/>
  <link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css"/>
  <%
  } else if (device_type.equals(CommonName.DEVICE_TYPE_RECOURD)) {
  %>
  <script src="${pageContext.request.contextPath}/js/lb_common/screendetailforrecord.js"></script>
  <link href="${pageContext.request.contextPath}/css/frontend/record/index.css" rel="stylesheet" type="text/css"/>
  <link href="${pageContext.request.contextPath}/css/frontend/record/main.css" rel="stylesheet" type="text/css"/>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/lb_frontend/setting/setting.js"></script>
  <%
    }
  %>
  <style>
    .sall{height:36px;width: 146px;}

    #selectdivall0,#selectdivall1{
      width: 115px;
      background-position: 0 0;

    }
    .selectdiv{
      background: none;
      width: 139px;
      height: 36px;
      line-height: 36px;
    }
    .selectdivul{
      border:1px solid #bdbdbd;
      border-top:0;
      width: 113px;
      overflow: hidden;
      height:auto !important;
    }
    .selectdivul a{
      text-indent: 10px;
      text-align: left;
      width: 113px;
    }

    #selectdivul0,#selectdivall1{
      /*border:1px solid #dbdbdb;*/
      /*	margin-top: -2px;*/
      /*height: 36px;*/
      width: 114px;
      /*overflow: hidden;*/
    }
    .win520_content{
      border:1px solid #dbdbdb;
      position:absolute;
      height: 520px;
      width: 700px;
      margin-left:500px;
      margin-top: 100px;

    }
    .set_vv_save {
      cursor: pointer;
    }

    .headr {
      margin-left: 0px;
      margin-top: 0px;
      text-indent: 10px;
      margin-left: 30px;
    }

    .tab_content_listinput {
      border: 1px solid #dbdbdb;
      float: left;
      font-size: 12px;
      height: 26px;
      line-height: 26px;
      text-indent: 2px;
      margin-left: 10px;
      outline: none;
      width: 180px;
    }

    .set_vv_linetext {
      width: 160px;
      font-size: 14px;
    }
    .initiala_i{
      border: 1px solid #ccc;
      height: 300px;
      background: #fff;
      width:500px;
      margin: 0 auto;
      margin-top: 35px;
    }
    .Initialize{
      background: #fff url("../../../image/frontend/n_icon_141016.png") no-repeat scroll -175px -489px;
      float: left;
      height: 25px;
      margin-left: 38px;
      margin-top: 10%;
      padding-left: 10px;
      width: 25px;
    }
  </style>
  <input type="hidden" id="detype" value="<%=device_type%>"/>
  <div class="set">
    <div class="set_head">
      <div class="settinghead">
        <ul>
          <%
            if (device_type.equals(CommonName.DEVICE_TYPE_SCREEN) || device_type.equals(CommonName.DEVICE_TYPE_WHITEBOARD)) {
          %>
          <!--
              描述：大屏
          -->
          <li onclick="javascript:turn(0)">
            <div class="license" ></div>
            License设置
          </li>
          <li onclick="javascript:turn(1)" style="color:#28b779;" >
            <div class="Initialize"></div>
            大屏初始化设置
          </li>
          <%
          } else if (device_type.equals(CommonName.DEVICE_TYPE_RECOURD)) {
          %>
          <!--
              描述：集控
          -->
          <li onclick="javascript:jump(0)">
            <div class="tb"></div>
            台标
          </li>
          <li onclick="javascript:jump(1)">
            <div class="videoinfo"></div>
            录像信息
          </li>
          <li onclick="javascript:jump(2)">
            <div class="living"></div>
            直播
          </li>
          <li onclick="javascript:jump(3)">
            <div class="nas"></div>
            NAS存储设置
          </li>
          <li onclick="javascript:jump(4)">
            <div class="passuser"></div>
            录播主机用户名密码设置
          </li>
          <li onclick="javascript:jump(5)">
            <div class="ftp"></div>
            FTP设置
          </li>
          <li onclick="javascript:jump(6)" style="color:#28b779;">
            <div class="license"></div>
            License设置
          </li>
          <%
            }
          %>


        </ul>
      </div>
    </div>

    <div class="mm_right" style="" id="displaynone1">
      <%
        List channelist = (List)request.getAttribute("channelName");
        Initialization initinfo = (Initialization)request.getAttribute("initInfo");


        %>

      <div class="initiala_i">
        <div style="width: 130px;line-height: 33px;float:left;text-align: right;margin-left: 30px;margin-top: 50px">默认通道选择:</div>
          <div class="sall" style="width:200px;float:left;margin-top: 50px;margin-left: 10px;">

            <select class="select" id="select0">
              <%
                if (channelist.size()>0){
                  Map channel=new HashMap();

                  for (int i=0;i<channelist.size();i++){
                       channel= (Map) channelist.get(i);
                    if (channel.get("cmd_name").toString().equals(initinfo.getChannelName().toString())){
                      %>
              <option value="<%=channel.get("cmd_name").toString()%>" selected="selected"><%=channel.get("cmd_name").toString()%></option>
              <%
                    }else {
              %>

              <option value="<%=channel.get("cmd_name").toString()%>"><%=channel.get("cmd_name").toString()%></option>
              <%
                  }
                            }
                }else {
                              %>
              <option value="<%=initinfo.getChannelName()%>"><%=initinfo.getChannelName()%></option>
              <%
                            }
              %>



            </select>
            <div class="selectdivall" id="selectdivall0">
              <div class="selectdiv" id="selectdiv0"></div>
              <div class="selectdivul" id="selectdivul0"></div>
            </div>

          </div>
          <div style="width: 130px;line-height: 33px;text-align: right;float:left;margin-left: 30px;margin-top: 30px">默认童锁状态选择:</div>
          <div class="sall" style="width: 200px;float:left;margin-top: 30px;margin-left: 10px;">
            <select class="select" id="select1">
              <%
                if (device_type.equals("hhtwb")){
                  if ("Unlock".equals(initinfo.getLockStatus())){
                %>
                <option value="Lock">锁定</option>
                <option value="Unlock" selected="selected">解锁</option>
             <%
                 }else{
             %>
              <option value="Lock" selected="selected">锁定</option>
              <option value="Unlock">解锁</option>
              <%
                 }
               }else{
                   if ("ScreenUnlock".equals(initinfo.getLockStatus())){
              %>
              <option value="ScreenLock">锁定</option>
              <option value="ScreenUnlock" selected="selected">解锁</option>
              <%
              }else{
              %>
              <option value="ScreenLock" selected="selected">锁定</option>
              <option value="ScreenUnlock">解锁</option>
              <%
                  }
                 }
             %>

            </select>
            <div class="selectdivall" id="selectdivall1">
              <div class="selectdiv" id="selectdiv1"></div>
              <div class="selectdivul" id="selectdivul1"></div>
            </div>
          </div>
      </div>

      <div class="set_vv_foot">
        <div onclick="submit()" class="set_vv_save">提交</div>
      </div>
    </div>
  </div>
  <div class="foot">
    <jsp:include page="/pages/frontend/footer.jsp"></jsp:include>
  </div>
</div>
<input type="hidden" style="display: none" id="licenseParams" value="" newsId=""
       urlhead="${pageContext.request.contextPath}"/>
</body>
<script>
function submit(){

    var channel = $("#select0").val();
    var lockStatus = $("#select1").val();
    var devicetype = $("#detype").val();
    $.post('${pageContext.request.contextPath}/settings/Settings_InitSetting.do',{channel:channel,lockStatus:lockStatus,devicetype:devicetype},function(data){
      layer.msg(data.msg);
      if(data.success()==true){
        layer.alert("初始化设置成功！");
      }else{
        layer.alert("初始化设置失败！");
      }
    })

}


  //消息类型切换
  $(".jkn_message_radio3 div").click(function () {
    var ind = $(this).index();
    if (ind == 0) {
      location.href = urlHead + "/settings/Settings_findLicense.do"
    } else {
      location.href = urlHead + "/settings/Settings_findLicenseKey.do"
    }
    $(this).children("span").addClass("bgred").removeClass("bgr")
    $(this).siblings().children("span").addClass("bgr").removeClass("bgred")
  })

  function upperCase(x) {
    var y = document.getElementById(x).value
    document.getElementById(x).value = y.toUpperCase()
  }
  function symbols(x) {
    var y = document.getElementById(x).value
    document.getElementById(x).value = y.replace(/[/@#.,;:'!$%^&*()<>?{}\\|\[\]`~+=；：‘？/》《。，、·~！￥……（）——’\&quot;“”【】]/g, '')
  }
</script>
</html>

