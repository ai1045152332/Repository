<%@ page import="com.honghe.recordweb.service.frontend.user.UserService" %>
<%@ page import="com.honghe.recordhibernate.dao.Pager" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.honghe.recordweb.util.DateUtil" %>
<%@ page import="com.honghe.recordhibernate.entity.User" %>
<%@ page import="com.honghe.recordweb.action.SessionManager" %>
<%--
  Created by IntelliJ IDEA.
  User: sky
  Date: 2016/5/18
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <base href="${pageContext.request.contextPath}">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
  <title>触控切换 | 集控平台</title>
  <link href="${pageContext.request.contextPath}/css/frontend/hpager.css" rel="stylesheet"  type="text/css"/>
  <link href="${pageContext.request.contextPath}/js/common/layer/skin/layer.css" rel="stylesheet" type="text/css"/>
  <link href="${pageContext.request.contextPath}/js/common/layer/skin/layer.ext.css" rel="stylesheet" type="text/css"/>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/js/common/colorpicker/css/colorpicker.css" type="text/css" />
  <link href="${pageContext.request.contextPath}/css/frontend/fd-slider.css" rel="stylesheet" type="text/css"/>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
  <!--layerdate-->
  <script src="${pageContext.request.contextPath}/js/common/laydate/laydate.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/jquery.cookie.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/js/common/screendetail.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/tree_jkn_checkbox.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/selectmore.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/perfect-scrollbar.with-mousewheel.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/prettify.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/colorpicker/js/colorpicker.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/colorpicker/js/eye.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/colorpicker/js/layout.js?ver=1.0.2"></script>

</head>
<%
  List<Map> groupTreeList = (List<Map>) request.getAttribute("groupTree"); //获取分组数据和设备
  Map<String,Object> listmap = (Map<String,Object>) request.getAttribute("touchscreenList");
  String pageCount = listmap.get("pageCount").toString();
  int currentPage = Integer.parseInt(request.getAttribute("currentPage").toString());
  List<Object[]> policylist = (List<Object[]>) listmap.get("touchscreenlist") ;
  Pager pager = new Pager(Integer.parseInt(pageCount),currentPage,"<span class='pages_prevpage'></span>","<span class='pages_nextpage'></span>","","",false);
  String pagers = pager.run();
  String userId = request.getAttribute("userId").toString();
  UserService userService = new UserService();
  int isAdmin = userService.getAuthorityValueByUserid(Integer.parseInt(userId));//返回是否是管理员，0为是，1为否
  String device_type = SessionManager.get(request.getSession(),SessionManager.Key.DeviceType);
%>
<body>
<style>
  .tree_titleaiconmain_jkn,.tree_titleaiconmained_jkn{
    position: absolute;
    left: 5px;
    top: 0px;
  }
  .headr{
    margin-left: 20px;
  }

  .jkn_message_radio, .jkn_message_radio3 {
    margin-top: 10px;
    width: 500px;
  }

  #selectdivall0, #selectdivall1, #selectdivall2 {
    background: url("${pageContext.request.contextPath}/image/frontend/n_icon_141006.png") no-repeat scroll 0px -456px transparent;
    float: left;
    height: 36px;
    line-height: 36px;
    width: 131px;
  }

  .selectdivul {
    width: 115px;
  }

  .selectdivul a {
    text-indent: 10px;
    width: 115px;
  }

  .sall {
    margin-top: 10px;
  }

  .laydate-icon {
    float: left;
    text-indent: 5px;
    margin-top: 2px;
    width: 100px;
  }

  .jkn_message_90 {
    width: 95%;
    margin: 0 auto;
  }

  .jkn_message_radio {
    margin-top: 10px;
    width: 500px;
  }

  .jkn_message_radio1 {
    margin-top: 10px;
  }

  .headr {
    margin-left: 5px;
  }
  .jkn_treecheckbox,.jkn_treecheckboxed{
    left: 27px;
  }

  #linkpage {
    position: absolute;
    bottom: 0px;
    left: 55%;
    text-align: center
  }
  .tree_titleb {
    display: none;
  }
  .hostoverflow {
    float: left;
    margin-left: 23px;
    text-overflow: ellipsis;
    overflow: hidden;
    width: 80%;

  }

</style>
<jsp:include page="/pages/frontend/equipment_header.jsp"></jsp:include>
<div class="public_left22 floatleft">

  <input type="hidden" value="" id="selected_host" ip="">

  <div class="equipment">教室设备</div>
  <div id="otp_vedeoabout_rvideo">
    <div class='contentr'>
      <div class="tree">
        <a href="javascript:void(0)" class="tree_titleb tree_titleb_open">所有设备</a>

        <div class="public_left">
          <%
            if (groupTreeList != null && !groupTreeList.isEmpty()) {
              for (Map groupTreeMap : groupTreeList) {
                String group_id = groupTreeMap.get("group_id").toString();
                String group_name = groupTreeMap.get("group_name").toString();
                List<Map> hostList = (List<Map>) groupTreeMap.get("host_list");
                if (hostList == null || hostList.isEmpty()) {
                  continue;
                }
          %>
          <div class="tree_title tree_title_close ">
            <span class="tree_titlea" groupId="<%=group_id%>" style="text-indent: 48px;"><%=group_name%></span>
            <span class="tree_titleaiconmain_jkn" style="position: absolute;"></span>
            <div class="tree_content">
              <%
                if (hostList != null && !hostList.isEmpty()) {
                  for (Map host : hostList) {
                    String host_id = host.get("id").toString();
                    String host_name = host.get("name").toString();
                    String host_ip = host.get("host_ip").toString();
                    String statusStyle = "tree_content_onlinebg";
                    if (host.get("status").toString().equals("Offline")) {
                      statusStyle = "tree_content_nousebg";
                    }
              %>
              <div class="tree_contenta" hostId="<%=host_id%>" hostIp="<%=host_ip%>" hostName="<%=host_name%>">
                <span class="<%=statusStyle%>" style="background: none;"></span>
                <span class="hostoverflow"> <%=host_name%></span>
                <div class="jkn_treecheckbox" style="left:27px;"></div>
              </div>
              <%
                  }
                }
              %>
            </div>
          </div>
          <%
              }
            }
          %>
        </div>
      </div>
    </div>
  </div>
</div>
<div class="public_right floatleft">


  <div class="jkn_message_rightcenter">
    <input type="hidden" id="touchscreenid" value="0"/>

    <div class="jkn_message_90 floatleft">
      <div class="jkn_message_text">定时类型：</div>
      <div class="jkn_message_radio3" style="margin-top: 19px;">

        <div class="headr" style="position: relative;z-index: 10;">
          <span class="bgr"></span>开关机
        </div>
        <% if(device_type == "hhtc"){%>
        <div class="headr" style="position: relative;z-index: 10;">
          <span class="bgr"></span>节目频道
        </div>
        <%}else{%>
        <div class="headr" style="position: relative;z-index: 10;display: none">
          <span class="bgr"></span>节目频道
        </div>
        <%}%>
        <div class="headr" style="position: relative;z-index: 10;">
          <span class="bgr"></span>打铃
        </div>
        <% if(device_type == "hhtc"){%>
        <div class="headr" style="position: relative;z-index: 10;">
          <span class="bgr"></span>信号源
        </div>
        <%}else{%>
        <div class="headr" style="position: relative;z-index: 10;display: none">
          <span class="bgr"></span>信号源
        </div>
        <%}%>
        <div class="headr" style="position: relative;z-index: 10;">
          <span class="bgred"></span>触控
        </div>


      </div>
      <div class="jkn_message_radio2" style="margin-top: 0; margin-left: 30px;width:auto">
        <div class="headr">
          <span id="lock" class="bgr"></span>锁定
        </div>
        <div class="headr">
          <span id="unlock" class="bgr"></span>解锁
        </div>

      </div>
    </div>
    <div class="jkn_message_text" style="margin-top: 10px;">策略选择：</div>
    <div class="jkn_message_90">
      <div class="jkn_message_radio1">
        <div class="headr">
          <span id="single" class="bgr"></span>单次
        </div>
        <div class="floatleft" style="margin-top: -3px;margin-left: 10px;">
          <input id="onece" class="laydate-icon"/>
          <input type="hidden" value=""  id="oneceTime"/>
        </div>
      </div>

    </div>
    <div class="jkn_message_90" style="margin-left: 70px;">
      <div class="jkn_message_radio1">
        <div class="headr">
          <span id="day" class="bgr"></span>每日固定时间
        </div>
      </div>
    </div>
    <div class="jkn_message_90" style="margin-left: 70px;">
      <div class="jkn_message_radio1">
        <div class="headr">
          <span id="week" class="bgr"></span>每周固定时间
        </div>
        <div class="weekradio">
          <div class="headr">
            <span id="Monday" class="bgr"></span>周一
          </div>
          <div class="headr">
            <span id="Tuesday" class="bgr"></span>周二
          </div>
          <div class="headr">
            <span id="Wednesday" class="bgr"></span>周三
          </div>
          <div class="headr">
            <span id="Thursday" class="bgr"></span>周四
          </div>
          <div class="headr">
            <span id="Friday" class="bgr"></span>周五
          </div>
          <div class="headr">
            <span id="Saturday" class="bgr"></span>周六
          </div>
          <div class="headr">
            <span id="Sunday" class="bgr"></span>周日
          </div>
        </div>
      </div>
    </div>
    <div class="jkn_message_90" style="margin-left: 70px;">
      <div class="jkn_message_radio1">
        <div class="headr">
          <span id="month" class="bgr"></span>每月固定时间
        </div>
        <div class="sall" style="margin-top: -8px;margin-left: 10px;">
          <select class="select" id="select0">
            <script>
              for (i = 1; i <= 31; i++) {
                var html = "<option value=" + i + " >" + i + "</option>";
                $("#select0").append(html)
              }
            </script>
          </select>

          <div class="selectdivall" id="selectdivall0">
            <div class="selectdiv" id="selectdiv0"></div>
            <div class="selectdivul" id="selectdivul0" style="z-index: 11;max-height: 180px"></div>
          </div>
        </div>
      </div>
    </div>
    <div class="jkn_message_90 floatleft">
      <div class="jkn_message_text">时间选择：</div>
      <div class="jkn_message_timechoose">
        <div class="jkn_message_hour">小时</div>
        <div class="jkn_message_center">:</div>
        <div class="jkn_message_minutes">分钟</div>
        <div class="jkn_message_hourshow"></div>
        <div class="jkn_message_minutesshow"></div>
      </div>
      <div class="jkn_message_sendbtn">发布</div>
    </div>
  </div>
  <div class="jkn_message_rightcenter hei_50 floatleft" style="margin-left: 5%;">
    <div class="jkn_message_listhead floatleft" style="margin-top: 15px;">
      <div class="wid_5 hei_100 floatleft jkn_message_listheadtxt">序号</div>
      <div class="wid_10 hei_100 floatleft jkn_message_listheadtxt">策略名称</div>
      <div class="wid_15 hei_100 floatleft jkn_message_listheadtxt">创建者</div>
      <div class="wid_30 hei_100 floatleft jkn_message_listheadtxt">应用班级</div>
      <div class="wid_20 hei_100 floatleft jkn_message_listheadtxt">发布时间</div>
      <div class="wid_15 hei_100 floatleft jkn_message_listheadtxt">操作</div>
    </div>
    <%
      if (policylist != null && !policylist.isEmpty()) {
        for (int i = 0; i < policylist.size(); i++) {
          Object[] policy = policylist.get(i);
    %>
    <div id="<%=policy[0]%>" type="<%=policy[1]%>" isloop="<%=policy[2]%>"
         looptype="<%=policy[3]==null?"":policy[3]%>"
         date="<%=policy[4]==null?"":policy[4]%>" week="<%=policy[5]==null?"":policy[5]%>"
         time="<%=policy[6]==null?"":policy[6]%>"
         alert(<%=DateUtil.get(String.valueOf(policy[7]))%>;

         singletime="<%=policy[7]==null?"":policy[7]%>"
         uid="<%=policy[8]==null?"":policy[8]%>"
         class="<%=i%2==0?"jkn_message_listwhite floatleft":"jkn_message_listgray floatleft"%>">
      <div class="wid_5 hei_100 floatleft jkn_message_listtxt"><%=5 * (currentPage - 1) + i + 1%>
      </div>
      <div class="wid_10 hei_100 floatleft jkn_message_listtxt"><%=Integer.parseInt(policy[1].toString()) == 0 ? "锁定" : "解锁"%>
      </div>
      <%
        String userName = "";
        int newuser = -1;
        if(policy[8]!= null && policy[8].toString() != "") {
          User user = userService.getUserinfoByIdService(Integer.parseInt(policy[8].toString()));
          if(user != null) {
            if(user.getUserId() != null) {
              newuser = user.getUserId();
            }
          }
        }
      %>
      <div class="wid_15 hei_100 floatleft jkn_message_listtxt"><%=policy[12] == null ? "" : policy[12]%>
      </div>
      <div class="wid_30 hei_100 floatleft jkn_message_listtxt"><%=policy[11] == null ? "" : policy[11]%>
      </div>
      <div class="wid_20 hei_100 floatleft jkn_message_listtxt"><%=DateUtil.StrToDate(policy[9] == null ? "" : String.valueOf(policy[9]))%>
      </div>
      <div class="wid_15 hei_100 floatleft jkn_message_listtxt">
        <%
          if(Integer.valueOf(userId) != newuser){
            if(userId.equals("1") || isAdmin == 0){//如果是管理员或admin可以删除
        %>
        <div class="jkn_message_listdel"   onclick="javascript:delTouchScreen(<%=policy[0]%>,'<%=policy[10]%>')"></div>
        <%
          }
        }else
        {
        %>
        <div class="jkn_message_listedit"  onclick="javascript:alterTouchScreen(<%=policy[0]%>,'<%=policy[10]%>')"></div>
        <div class="jkn_message_listdel"   onclick="javascript:delTouchScreen(<%=policy[0]%>,'<%=policy[10]%>')"></div>
        <%
          }
        %>
      </div>
    </div>
    <%
        }
      }
    %>
  </div>
  <div class="floatleft" id="linkpage"><%=pagers%>
  </div>

  <input type="text" style="display: none" id="urlhead"
         urlhead="${pageContext.request.contextPath}"/>
</div>
<div class="foot">
  <jsp:include page="/pages/frontend/footer.jsp"></jsp:include>
</div>
</div>
<script type="text/javascript">
  //重置public_right高度
  var docwidth = document.documentElement.clientWidth;
  var docheight = parseInt(document.documentElement.clientHeight);
  function change_size() {
    width = $("#otp_vedeoabout_rvideo").width();
    height = parseInt($(".public_right").height() - $(".equipment").height());
    $("#otp_vedeoabout_rvideo").width(width).height(height);
    $('#otp_vedeoabout_rvideo').perfectScrollbar('update');
  }
  $(function () {
    $('#otp_vedeoabout_rvideo').perfectScrollbar();
    prettyPrint();


  });
  /*
   * 获取url地址中的参数
   */
  function urloption(url) {
    //alert(url);
    var totalpagesize = "<%=pageCount%>";
    if (url.indexOf("?") != -1) {
      var p = url.indexOf("?"); //返回所在位置
      var host = "http://" + window.location.host + url.substr(0, p + 1);
      var str = url.substr(p + 1) //从这个位置开始截取
      strs = str.split("&"); //拆分
      var jumpval = $("#jump").val();
      var patrn = /^[0-9]*$/;
      if(!patrn.exec(jumpval)|| jumpval<=0){
        jumpval = 1;
      } else if (parseInt(jumpval) >= parseInt(totalpagesize)) {

        jumpval = totalpagesize
      }
      var newurl = host;
      for (var i = 0; i < strs.length; i++) {
        if (i == 0) {
          newurl += strs[i].split("=")[0] + "=" + jumpval + "&";
        } else {
          newurl += strs[i].split("=")[0] + "=" + unescape(strs[i].split("=")[1]) + "&";

        }
      }
      var urllen = newurl.length - 1;
      var newurl = newurl.substr(0, urllen) //从这个位置开始截取
      location.href = newurl
    }
  }
  //修改信息
  function alterTouchScreen(tid,phosts){
    $("#touchscreenid").val(tid);
    var touchtype=$("#" + tid).attr("type");
    var touchLoop=$("#" + tid).attr("isloop");
    var tLooptype=$("#" + tid).attr("looptype");
    var touchDate=$("#" + tid).attr("date");
    var touchWeek=$("#" + tid).attr("week");
    var touchTime=$("#" + tid).attr("time");
    var tSingletime=$("#" + tid).attr("singletime");
    var touchUid=$("#" + tid).attr("uid");
    //锁定/解锁
    if(touchtype==0){
      $("#unlock").removeClass("bgred").addClass("bgr");
      $("#lock").removeClass("bgr").addClass("bgred");
    }else{
      $("#unlock").removeClass("bgr").addClass("bgred");
      $("#lock").removeClass("bgred").addClass("bgr");
    }

    // 单次/循环
    if (touchLoop == 0) {
      var date = tSingletime.substring(0, 11);
      var time = tSingletime.substring(11);
      var hour = time.split(":")[0];
      var minu = time.split(":")[1];
      $("#single").removeClass("bgr").addClass("bgred");
      $("#onece").val(date);
      $("#day").removeClass("bgred").addClass("bgr");
      $("#week").removeClass("bgred").addClass("bgr");
      $(".weekradio").find(".bgred").removeClass("bgred").addClass("bgr");
      $("#month").removeClass("bgred").addClass("bgr");
      $(".jkn_message_hour").text(hour);
      $(".jkn_message_minutes").text(minu);
    }else {
      var hour = touchTime.split(":")[0];
      var minu = touchTime.split(":")[1];
      $("#single").removeClass("bgred").addClass("bgr");
      $("#onece").val("");
      if (tLooptype == "day") {
        $("#day").removeClass("bgr").addClass("bgred");
        $("#week").removeClass("bgred").addClass("bgr");
        $(".weekradio").find(".bgred").removeClass("bgred").addClass("bgr");
        $("#month").removeClass("bgred").addClass("bgr");
        $(".jkn_message_hour").text(hour);
        $(".jkn_message_minutes").text(minu);
      } else if (tLooptype == "week") {

        $("#day").removeClass("bgred").addClass("bgr");
        $("#week").removeClass("bgr").addClass("bgred");
        $(".weekradio").find(".bgred").removeClass("bgred").addClass("bgr");
        switch (touchWeek) {
          case "1":
            $("#Monday").removeClass("bgr").addClass("bgred");
            break;
          case "2":
            $("#Tuesday").removeClass("bgr").addClass("bgred");
            break;
          case "3":
            $("#Wednesday").removeClass("bgr").addClass("bgred");
            break;
          case "4":
            $("#Thursday").removeClass("bgr").addClass("bgred");
            break;
          case "5":
            $("#Friday").removeClass("bgr").addClass("bgred");
            break;
          case "6":
            $("#Saturday").removeClass("bgr").addClass("bgred");
            break;
          case "7":
            $("#Sunday").removeClass("bgr").addClass("bgred");
            break;
          default :
            $("#Monday").removeClass("bgr").addClass("bgred");
            break;
        }
        $("#month").removeClass("bgred").addClass("bgr");
        $(".jkn_message_hour").text(hour);
        $(".jkn_message_minutes").text(minu);
      }
      else {
        $("#day").removeClass("bgred").addClass("bgr");
        $("#week").removeClass("bgred").addClass("bgr");
        $(".weekradio").find(".bgred").removeClass("bgred").addClass("bgr");
        $("#month").removeClass("bgr").addClass("bgred");
        $("#selectdiv0").text(touchDate);
        $("#select0 option").eq(touchDate - 1).prop("selected", true);
        $("#selectdivul0 a").removeAttr("style").attr("stype", "color: rgb(51, 51, 51); background: none repeat scroll 0% 0% rgb(255, 255, 255);");
        $("#selectdivul0 a").eq(pdate - 1).removeAttr("style").attr("style", "color: rgb(255, 255, 255); background: none repeat scroll 0% 0% rgb(40, 183, 121);")
        $(".jkn_message_hour").text(hour);
        $(".jkn_message_minutes").text(minu);
        $("#select0").parent().show();
      }
    }
    //左侧树班级选择状态
    $(".jkn_treecheckboxed").addClass("jkn_treecheckbox").removeClass("jkn_treecheckboxed");
    $(".tree_titleaiconmained_jkn").addClass("tree_titleaiconmain_jkn").removeClass("tree_titleaiconmained_jkn");
    if (phosts != "") {
      var hosts = new Array();
      hosts = phosts.split(",");
      for (var i = 0; i < hosts.length; i++) {
        for (var j = 0; j < $(".tree_contenta").length; j++) {
          var hostid = $(".tree_contenta").eq(j).attr("hostid");
          if (hostid.trim() == hosts[i].trim()) {
            $(".tree_contenta").children("div").eq(j).click();
          }
        }
      }
    }

  }
  //点击发布按钮
  $(".jkn_message_sendbtn").click(function(){

    var touchid=$("#touchscreenid").val();

    var checkedHostsArr = $(".jkn_treecheckboxed");
    var hostStr = "";
    var hostIpStr="";
    var hostNameStr = "";
    if(checkedHostsArr.length<1){
      alert("未选择设备！");
      return;
    }
    for(var i=0;i<checkedHostsArr.length;i++){
      if(i<checkedHostsArr.length-1) {
        hostStr += checkedHostsArr.eq(i).parent().attr("hostId") + ",";//获取选中的host
        hostIpStr += checkedHostsArr.eq(i).parent().attr("hostIp") + ",";//获取选中的host的ip
        hostNameStr += checkedHostsArr.eq(i).parent().attr("hostName") + ",";//获取选中的host
      }
      else{
        hostStr += checkedHostsArr.eq(i).parent().attr("hostId") ;//获取选中的host
        hostIpStr += checkedHostsArr.eq(i).parent().attr("hostIp") ;//获取选中的host的ip
        hostNameStr += checkedHostsArr.eq(i).parent().attr("hostName");//获取选中的host
      }
    }

    //童锁 锁定/解锁
    var touchtype="0";
    if($("#unlock").attr("class")=="bgred"){
      touchtype="1";
    }else if($("#lock").attr("class")=="bgred"){
      touchtype="0"; //0 表示锁定面板
    }else{
      layer.msg("请选择策略名称");
      return false;
    }
    // 小时、分钟
    var hour = $(".jkn_message_hour").text();
    if (hour == "小时") {
      layer.msg("请选择小时");
      return false;
    }
    var minu = $(".jkn_message_minutes").text();
    if (minu == "分钟") {
      layer.msg("请选择分钟");
      return false;
    }
    var touchTime = hour + ":" + minu;
    //是否循环，循环类型
    var tLooptype="";
    var touchLoop="0";
    var tSingletime="";
    var touchWeek="7";
    var touchDate="1";


    if($("#single").attr("class")=="bgred"){
      touchLoop="0";
      if($("#onece").val()!=""){
        tSingletime=$("#onece").val()+" "+touchTime+":00";
      }else{
        layer.msg("请选择日期");
        return false;
      }
    }else if($("#day").attr("class")=="bgred"){
      touchLoop="1";
      tLooptype="day";
    }else if($("#week").attr("class")=="bgred"){
      touchLoop="1";
      tLooptype="week";
      if($("#Monday").attr("class")=="bgred"){
        touchWeek="1";
      }else if($("#Tuesday").attr("class")=="bgred"){
        touchWeek="2";
      }else if($("#Wednesday").attr("class") == "bgred"){
        touchWeek="3";
      }else if($("#Thursday").attr("class") == "bgred") {
        touchWeek = "4";
      }else if($("#Friday").attr("class") == "bgred")
      {
        touchWeek = "5";
      }else if($("#Saturday").attr("class") == "bgred") {
        touchWeek = "6";
      }else if($("#Sunday").attr("class") == "bgred") {
        touchWeek = "7";
      }else{
        layer.msg("请选择星期");
        return false;
      }
    }else if($("#month").attr("class") == "bgred"){
      touchLoop="1";
      tLooptype="month";
      touchDate=$("#selectdiv0").text();
    }else{
      layer.msg("请选择策略类型");
      return false;
    }
    if(touchid=="0"){
      $.post("${pageContext.request.contextPath}/policy/Touchscreen_addTouchScreen.do",
              {'touchstype':touchtype,
                'touchloop':touchLoop,
                'tlooptype':tLooptype,
                'touchdate':touchDate,
                'touchweek':touchWeek,
                'touchtime':touchTime,
                'tsingletime':tSingletime,
                'tdevicetype':"<%=device_type%>",
                'hostStr':hostStr,
                 'hostIpStr': hostIpStr,
                'hostNameStr':hostNameStr
              },function(data){
                layer.msg(data.msg);
                setTimeout(function(){
                  if (data.success == true) {
                    window.location.reload();
                  }
                },2000);
              },"json");
    }else{
      $.post("${pageContext.request.contextPath}/policy/Touchscreen_alterTouchScreen.do",
              {touchid:touchid,
                touchstype:touchtype,
                touchloop:touchLoop,
                tlooptype:tLooptype,
                touchdate:touchDate,
                touchweek:touchWeek,
                touchtime:touchTime,
                tsingletime:tSingletime,
                tdevicetype:"<%=device_type%>",
                hostStr:hostStr,
                hostIpStr: hostIpStr,
                hostNameStr:hostNameStr
              },function(data){
                layer.msg(data.msg);
                setTimeout(function(){
                  if(data.success== true){
                    window.location.reload();

                  }
                },2000);
              },"json");
    }

  })

  //删除定时计划
  function delTouchScreen(tid,hosts)
  {
    $.layer({
      shade: [0.5],
      maxmin: false,
      area: ['310px','129px'],
      dialog: {
        msg: '确定删除该策略吗？',
        btns: 2,
        type: 4,
        btn: ['确定','取消'],
        yes: function(){
          $.post("${pageContext.request.contextPath}/policy/Touchscreen_delTouchScreen.do",{touchid:tid,hostStr:hosts},function(data){
            layer.msg(data.msg);
            setTimeout(function(){
              if (data.success == true) {
                window.location.reload();
              }
            },2000);
          });
        }, no: function(){
        }
      }
    });

  }

  var onece = {
    elem: '#onece ',
    format: 'YYYY-MM-DD',
    min: laydate.now(), //设定最小日期为当前日期
    max: '2099-06-16 23:59:59', //最大日期
    festival: true, //是否显示节日
    choose: function(datas){
      end.min = datas; //开始日选好后，重置结束日的最小日期
      end.start = datas //将结束日的初始值设定为开始日
      $("#oneceTime").val(datas);
    }
  };
  laydate(onece);

  $(function(){
    //单次日期初始化
    var myDate=new Date();
    var myDateStr=myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate();
    $("#oneceTime").val(myDateStr);//日期初始化
    $("#onece").val(myDateStr);

    //发布方式
    $(".jkn_message_radio div").click(function(){
      $(this).children("span").addClass("bgred").removeClass("bgr")
      $(this).siblings().children("span").addClass("bgr").removeClass("bgred")
    })
    //向小时div添加子元素
    for(var i=0;i<24;i++){
    if(i<10){
      html="<span>0"+i+"</span>";
    }else{
      html="<span>"+i+"</span>";
    }
      $(".jkn_message_hourshow").append(html);
    }
    //向分钟div添加子元素
    for(var i=0;i<60;i++){
      var html="";
      if(i<10){
        html="<span>0" + i + "</span>";
      }else{
      html="<span>" + i + "</span>";
      }
      $(".jkn_message_minutesshow").append(html);
    }
    //分钟子菜单隐藏，小时子菜单显示
    $(".jkn_message_hour").click(function(){
      $(".jkn_message_hourshow").show();
      $(".jkn_message_minutesshow").hide();
    })
    //分钟子菜单显示，小时子菜单隐藏
    $(".jkn_message_minutes").click(function(){
      $(".jkn_message_hourshow").hide();
      $(".jkn_message_minutesshow").show();
    })
    //子菜单鼠标经过效果
    $(".jkn_message_timechoose span").mouseover(function(){
      $(this).css("background","#ccc");
    }).mouseout(function(){
      $(this).css("background","#fff");
    })
    //时间选择赋值  小时赋值
    $(".jkn_message_hourshow span").click(function(){
      $(".jkn_message_hour").text($(this).text());
      $(".jkn_message_hourshow").hide();

    })
    //时间选择赋值  分钟赋值
    $(".jkn_message_minutesshow span").click(function(){
      $(".jkn_message_minutes").text($(this).text());
      $(".jkn_message_minutesshow").hide();
    })
    //点击空白处隐藏操作项子菜单
    $(document).bind("click",function(e){
      if($(e.target).closest(".jkn_message_hour").length == 0){
        $(".jkn_message_hourshow").hide()
        }
     if( $(e.target).closest(".jkn_message_minutes").length == 0){
       $(".jkn_message_minutesshow").hide()
        }
    })
    //策略选择单选按钮操作
    $(".jkn_message_radio1").children(".headr").click(function(){
      $(this).children("span").addClass("bgred").removeClass("bgr")
      $(this).parents(".jkn_message_90").siblings().find(".jkn_message_radio1").children(".headr").children("span").addClass("bgr").removeClass("bgred")
      if($(this).parents(".jkn_message_90").find(".weekradio").length!=1){
        $(this).parents(".jkn_message_90").siblings().find(".weekradio").children(".headr").children("span").addClass("bgr").removeClass("bgred")
      }8
      if($.trim($(this).text())!="每月固定时间")
      {
        //隐藏每月固定时间下拉框
        $("#select0").parent().hide()
      }
      else
      {
        $("#select0").parent().show()
      }
    })


      //童锁单选操作
      $(".jkn_message_radio2").children(".headr").click(function(){
        $(this).children("span").addClass("bgred").removeClass("bgr")
        $(this).siblings().children("span").addClass("bgr").removeClass("bgred")//将所有同胞元素设置为未选中
      })
    //策略选择周选择策略
    $(".weekradio").children(".headr").click(function () {
      $(".jkn_message_radio1").children(".headr").eq(2).click()
      $(this).children("span").addClass("bgred").removeClass("bgr")
      $(this).siblings().children("span").addClass("bgr").removeClass("bgred")
    })
    //默认选择第一个
    $(".jkn_message_radio2").children(".headr").eq(0).click();
    $(".jkn_message_radio1").children(".headr").eq(0).click()
    $(".jkn_message_hourshow span").eq(8).click();
    $(".jkn_message_minutesshow span").eq(0).click();
  })
    var totalpagesize = "<%=pageCount%>";
    //分页调整
    var page = totalpagesize;
    var html = "<span style='float: left;margin-left: 2px;'>&nbsp;&nbsp;共" + page + "页 &nbsp;&nbsp;跳转到 " + "<input id=\"jump\" type=text style=\"width:20px;background:#d7dade;border:1px solid #5e6470\" title=\"点击回车即可跳转分页\" onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'')\" onafterpaste=\"this.value=this.value.replace(/[^0-9]/g,'')\"/> 页<span>";
    $(".yiiPager").append(html)
    left = -(18) * 28 / 2 + "px";
    $("#linkpage").css({"margin-left": left, "width": (17) * 28 + "px"});
    $(document).keydown(function (event) {
      //判断当event.keyCode 为37时（即左方面键），执行函数to_left();
      //判断当event.keyCode 为39时（即右方面键），执行函数to_right();
      if (event.ctrlKey || event.which == 13) {

        var jumpval = $("#jump").val();
        var patrn = /^[0-9]*$/;
        if(!patrn.exec(jumpval)){
          return false;
        } else {
          var lilen = $("#linkpage ul li").length - 1;
          var prevhref = $("#linkpage ul li").eq(0).find("a").attr("href");
          var nexthref = $("#linkpage ul li").eq(lilen).find("a").attr("href");
          var thisurl = "";
          if (prevhref == "" && nexthref == "") {
            return false;
          } else if (prevhref == "" || prevhref == undefined) {
            thisurl = nexthref;
            urloption(thisurl)
          } else if (nexthref == "" || nexthref == undefined) {
            thisurl = prevhref;
            urloption(thisurl)
          } else {
            thisurl = nexthref;
            urloption(thisurl)
          }
        }
      }
    })


  //定时类型切换
  $(".jkn_message_radio3 div").click(function () {
    var urlHead = $("#urlhead").attr("urlhead");
    var ind = $(this).index();

    if (ind == 0) {
      location.href = urlHead + "/policy/Policy_policyList.do"
    } else if (ind == 1) {
      location.href = urlHead + "/policy/Programme_programmeList.do"
    } else if (ind == 2) {
      location.href = urlHead + "/policy/Ringbell_ringbellList.do"
    } else if (ind == 3) {
      location.href = urlHead + "/policy/Signalplan_signalplanList.do"
    }

    $(this).children("span").addClass("bgred").removeClass("bgr")
    $(this).siblings().children("span").addClass("bgr").removeClass("bgred")
  })

</script>
</body>
</html>
