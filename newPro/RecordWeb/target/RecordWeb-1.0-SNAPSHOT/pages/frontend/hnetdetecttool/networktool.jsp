<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.honghe.version.Version" %>
<%--
  Created by IntelliJ IDEA.
  User: lxy
  Date: 2015/12/28
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <base href="">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
  <title>网络侦测 | 集控平台</title>
  <link href="${pageContext.request.contextPath}/css/frontend/hpager.css" rel="stylesheet"  type="text/css"/>
  <link href="${pageContext.request.contextPath}/js/common/layer/skin/layer.css" rel="stylesheet" type="text/css"/>
  <link href="${pageContext.request.contextPath}/js/common/layer/skin/layer.ext.css" rel="stylesheet"
        type="text/css"/>
  <link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css" />
  <link href="${pageContext.request.contextPath}/css/frontend/index.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/js/common/colorpicker/css/colorpicker.css" type="text/css" />
  <link href="${pageContext.request.contextPath}/css/frontend/fd-slider.css" rel="stylesheet" type="text/css"/>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
  <!--layerdate-->
  <script src="${pageContext.request.contextPath}/js/common/laydate/laydate.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/jquery.cookie.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/js/common/screendetail.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/tree_jkn_checkbox_forinstall.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/selectmore.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/perfect-scrollbar.with-mousewheel.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/prettify.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/colorpicker/js/colorpicker.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/colorpicker/js/eye.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/colorpicker/js/layout.js?ver=1.0.2"></script>
  <script src="${pageContext.request.contextPath}/js/common/scrolldemo.js"></script>

  <style>
    .hostoverflow {
      float: left;
      margin-left: 23px;
      text-overflow: ellipsis;
      overflow: hidden;
      width: 80%;

    }

    .tree_titleb {
      display: none;
    }
    .fd-slider-handle{
      border:1px solid #a3a3a3;
      background: #f0f0f0;
      border-radius: 5px;
      height: 9px;
      top: 5px;
      width: 9px;
    }
    .treecheckboxfalse{
      background: rgba(0, 0, 0, 0) url("/image/frontend/checkbox_jkn.png") no-repeat scroll -1px -72px;
      height: 14px;
      left: 27px;
      position: absolute;
      top: 10px;
      width: 14px;
    }
  </style>
</head>

<body>
<input type="hidden" id="whichscroll">
<div class="public">
    <%
      List<Map> groupTreeList = (List<Map>) request.getAttribute("groupTree");
      String transmissionSpeed = (String)request.getAttribute("transmissionSpeed");
      String timeInterval = (String)request.getAttribute("timeInterval");
      String packetLoss = (String)request.getAttribute("packetLoss");
      String networkDelay = (String)request.getAttribute("networkDelay");
      String ip = request.getHeader("x-forwarded-for");
      String dt = Version.getVersionInfo("dt");
      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("Proxy-Client-IP");
      }
      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("WL-Proxy-Client-IP");
      }
      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getRemoteAddr();
      }
    %>
  <jsp:include page="/pages/frontend/lb_header.jsp"></jsp:include>

  <link href="/css/frontend/index.css" rel="stylesheet" type="text/css"/>
  <link href="/css/frontend/main.css" rel="stylesheet" type="text/css"/>


  <style>
    .tab_content_listinput{
      border:1px solid #bbb
    }
    .tab_content_listtext{
      color: #80858F;
    }

  </style>
  <input type="hidden" value="" id="root_path">
  <style>
    .tree_titleaiconmain_jkn,.tree_titleaiconmained_jkn{
      position: absolute;
      left: 5px;
      top: 0px;
    }

    .jkn_treecheckbox,.jkn_treecheckboxed{
      left: 27px;
    }

  </style>
      <div class="mm">
  <div class="res_tree" flag="yes">
      <%
        if (groupTreeList.size() > 0) {
          for (Map groupTreeMap : groupTreeList) {
            String group_id = groupTreeMap.get("group_id").toString();
            String group_name = groupTreeMap.get("group_name").toString();
            List<Map> hostList = (List<Map>) groupTreeMap.get("host_list");
            if(hostList==null||hostList.isEmpty()){
              continue;
            }
      %>
      <div class="res_treegroup" id="group_<%=group_id%>"><div class="res_treegroupcover"></div><%=group_name.trim()%></div>
      <div class="res_treeclass">
        <%
          if (!hostList.isEmpty()) {
            for (Map host : hostList) {
              String host_id = host.get("id").toString();
              String host_name = host.get("name").toString();
              String host_dspec = host.get("dspec").toString();
              String host_type = host.get("type").toString();
              String host_token = host.get("token").toString();
              String host_status = host.get("status").toString();
              String recordType = host.get("host_desc").toString();
              String statusStyle = "tree_content_onlinebg";
              String host_ip =host.get("host_ip").toString();
              String offlineshow = "offlinehide";
              String host_div_id = host_ip.replace(".","-");
              String onclick = "onclick=\"directorView()\"";
        %>
        <div class="res_treeclasslist">
          <div class="res_treeclassicon"></div>
          <div class="res_treeclasstxt" id = "<%=host_div_id%>" ip="<%=host_ip%>" recordType="<%=recordType%>" dspec="<%=host_dspec%>" host_status="<%=host_status%>">
            <%if(host_status.equals("Offline"))
            {
            %>
              <span style="float: left;white-space: nowrap;overflow:hidden;max-width:110px;text-overflow: ellipsis;color:#808080"><%=host_name.trim()%>(离线)</span>
              <%
              }
              else if("5".equals(host_dspec))
              {
              %>
                <%=host_name.trim()%>
              <%
                }else{
              %>
              <span style="float: left;white-space: nowrap;overflow:hidden;max-width:110px;text-overflow: ellipsis;color:#808080"><%=host_name.trim()%></span>
              <%
                }
              %>
             </div>
          <div class="res_treeclasscheckbox"></div>
        </div>
        <%
            }
          }
        %>
      </div>
      <%
          }
        }
      %>
  </div>
  <div class="res_card">
    <style type="text/css">
      .mm_head{
        background: #e2e4e7;
      }
    </style>
    <div class="mm_head floatleft">
      <div id="transmission" class="network_head_btn" style="color:#28b779" onclick="transmission()">网络传输测试</div>
      <div id="delaytest" class="network_head_btn" onclick="delaytest()">网络延迟测试</div>

      <%--网络侦测设备插件--%>
      <%--<a href="/VideoPlugin.exe">--%>
        <%--<div class="n_loginheadtxt">下载插件</div>--%>
      <%--</a>--%>

      <div class="network_head_input_txt" style="margin-left:2%">当前测试IP：</div>
      <input type="text" id="ipaddr" class="network_head_input"/>
      <a class="network_head_btn" href="/Networkplugin.exe" style="float: right;width: 75px;margin-left: 0;margin-right: 10px;background: #28B779;color: #fff;">下载插件</a>
      <div id="start" class="network_head_btn"  onclick="start()" style="float: right;width: 75px;margin-left: 0;margin-right: 60px;">开始测速</div>
      <div class="network_head_btn" id="setting" onclick="setting()" style="float: right;width: 75px;margin-left: 0;margin-right: 10px;">测速设置</div>
      <input type="hidden" id="div" value="0"/>
      <input type="hidden" id="udpIp" value=<%=ip%>>
    </div>
    <div class="scrollfather" style="background: none;" id="mm_right_video">

      <OBJECT ID="ScriptableObject" codeBase="dt.CAB#version=<%=dt%>"
       CLASSID="CLSID:679abc32-556c-4185-9653-cdea558a8791"
       style="width:100%;height:100%" />

        <param name="wmode" value="transparent" />
      </OBJECT>
        <iframe id="iframe" src="${pageContext.request.contextPath}/hnetdetecttool/HNetDetectTool_setting.do" frameborder="0" style="position: absolute;left:0;right:0;bottom:0;top:0;margin:auto;width:468px;height:532px;display:none"></iframe>

      <div class="scrollson">
        <div class="scroll_ymove">
          <div class="scroll_y" unorbind="unbind"></div>
        </div>
        <div class="scroll_xmove">
          <div class="scroll_x" unorbind="unbind"></div>
        </div>
      </div>
    </div>
  </div>
      </div>
  <div class="foot">
    <jsp:include page="/pages/frontend/footer.jsp"></jsp:include>
  </div>

    <script>
      $(function() {
        $(".res_tree,.res_card").height($(".mm").height())
        $.ajaxSetup({
          contentType : "application/x-www-form-urlencoded;charset=utf-8",
          complete : function(XMLHttpRequest, textStatus) {
            var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus"); //通过XMLHttpRequest取得响应头，sessionstatus，
            if (sessionstatus == "timeout") {
              //如果超时就处理 ，指定要跳转的页面
              window.location.href="";
            }
          }
        });
      });
    </script>
  </div>
  <script>
    //重置public_right高度
    var docwidth = document.documentElement.clientWidth;
    var docheight = parseInt(document.documentElement.clientHeight);
    var ScriptableObject;
    ScriptableObject = document.getElementById("ScriptableObject");
    //网络传输测试
    function transmission(){
      $("#delaytest").css("color","#0d0e0f");
      $("#transmission").css("color","#28b779");
      $("#setting").show();
      if($("#start").text()=="结束测速"){
        start();
      }
      $("#div").val("0");
      ScriptableObject.setTestType(0);
    }
    //网络延迟测试
    function delaytest(){
      $("#transmission").css("color","#0d0e0f");
      $("#delaytest").css("color","#28b779");
      $("#setting").hide();
      if($("#start").text()=="结束测速"){
        start();
      }
      $("#div").val("1");
      ScriptableObject.setTestType(1);
    }
    function change_size() {
      width = $("#otp_vedeoabout_rvideo").width();
      height = parseInt($(".public_right").height() - $(".equipment").height());
      $("#otp_vedeoabout_rvideo").width(width).height(height);
      $('#otp_vedeoabout_rvideo').perfectScrollbar('update');
    }
    //设置链接
    function setting()
    {
      $("#iframe").show();
   //   $("#ScriptableObject").hide()
//      $.layer({
//        type : 2,
//        title: '测速设置',
//        shadeClose: true,
//        maxmin: false,
//        fix : false,
//        area: ['470px', '490px'],
//        iframe: {
//          src : rootpath+"/hnetdetecttool/HNetDetectTool_setting.do"
//        }
////        end:function(){
//////            window.location.reload();
////            window.location.href=rootpath+"/user/User_userList.do";
////        }
//      });
    }
    function start(){
      var start=$("#start").text();
      var type=$("#div").val().trim();
      var serverIP=$("#ipaddr").val().trim();
      if (serverIP.length == 0) {
        alert("当前测试IP不能为空！");
        return;
      }else{
        var patrn=/^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/;
        if(!patrn.test(serverIP)){
          alert("请输入正确的IP地址");
          return;
        }
      }
      var serverPort="9554";
      var udpIP=$("#udpIp").val().trim();
      if(start=="开始测速"){
        $("#start").text("结束测速");
        $("#start").css("color","#28b779");
        $("#setting").css("color","#dbdbdb");
        $("#ipaddr").css("color","#dbdbdb ");
        $("#setting").removeAttr("onclick")
        if($("#div").val()==0){
          $("#transmission").removeAttr("onclick")
        }else if($("#div").val()==1){
          $("#delaytest").removeAttr("onclick")
        }
        $(".res_tree").attr("flag","no")
        $("#ipaddr").prop("disabled",true)
        $.post(root_path + '/hnetdetecttool/HNetDetectTool_start.do', {type: type,udpIP:udpIP,serverIP:serverIP,serverPort:serverPort}, function (data) {
        //  alert(data)
         // var str = data.toJSONString();
          var str = JSON.stringify(data)
          //alert(str.toString())
          ScriptableObject.startTest(str);
        })
      }else{
        $("#start").text("开始测速");
        $("#start").css("color","#707681");
        $("#setting").css("color","#707681");
        $("#ipaddr").css("color","#707681 ");
        $("#transmission").attr("onclick","transmission()")
        $("#delaytest").attr("onclick","delaytest()")
        $("#setting").attr("onclick","setting()")
        $(".res_tree").attr("flag","yes")
        $("#ipaddr").prop("disabled",false)
        ScriptableObject.stopTest();
      }
    }
    function tcp(){
      $("#udptext").prop("disabled",true)
    }
    function udp(){
      $("#udptext").prop("disabled",false)
    }
    $(function () {
//      $("#ScriptableObject").height(parseInt($(".res_card").height())-parseInt(($(".mm_head").height()))-parseInt(($(".foot").height())))
      $("#mm_right_video").height(parseInt($(".res_card").height())-parseInt(($(".mm_head").height())))
      $(".scrollson").mouseover(function(){
        $("#whichscroll").val($.trim($(this).parent().attr("id")))
        if ((navigator.userAgent.match(/(iPhone|Android|iPad)/i))){
          var scrollfathter1=document.getElementById($.trim($(this).parent().attr("id")));
          scrollfathter1.addEventListener("touchstart", touchStart, false);
          scrollfathter1.addEventListener("touchmove", touchMove, false);
          scrollfathter1.addEventListener("touchend", touchEnd, false);
        }
      })
      scroll_y("mm_right_video","scrollson","scroll_y","scroll_ymove","scroll_x","scroll_xmove","","wheely","")
      $(".scrollson").css("margin-top","0")
      $(".scroll_y").css("top","0")

      width = $("#otp_vedeoabout_rvideo").width();
      height = parseInt($(".public_right").height())-parseInt($(".equipment").height());

      $("#otp_vedeoabout_rvideo").width(width).height(height);
      $('#otp_vedeoabout_rvideo').perfectScrollbar();
      prettyPrint();
      var exphei=parent.document.documentElement.clientHeight;
      var param =  $(".resiframe").width() + "-" + $(".resiframe").height()+ "-" +exphei;
      $(".resiframe").attr("name",param);
      $(".res_tree .res_treegroup").click(function(){
        $(this).parents(".res_tree").children(".res_treeclass").hide();
        $(this).next().show()
        $(this).parents(".res_tree").children(".res_treegroup").css("background-position","-148px -250px")
        $(this).css("background-position","-149px -276px")
        $.cookie("tree_group_id",$(this).attr("id"),{path:'/resource'});
      })
      if($.cookie("tree_group_id") != undefined)
      {
        console.log("tree_group_id");
        var groupId = $.cookie("tree_group_id");
        $("#" + groupId).click();
      }
      else{
        $(".res_tree div").eq(0).click()
      }
      $(".res_treeclasstxt").click(function(){
        var flag=$(".res_tree").attr("flag")
        var tmp_ip = $(this).attr("ip");
        var host_div_id = tmp_ip.replace(".","-");
        host_div_id = host_div_id.replace(".","-");
        host_div_id = host_div_id.replace(".","-");
        var dspec=$("#"+host_div_id.toString()).attr("dspec");
        var host_status=$("#"+host_div_id.toString()).attr("host_status");
        if(flag=="yes" && "5"==dspec)
        {
          if(host_status=="Online"){
            $(".res_treeclasstxt").css("color","#000");
            $(this).css("color","#28b779");
            $("#ipaddr").val(tmp_ip);
          }
        }
      });
      ScriptableObject.sltSetMarkValue("当前传输速率",<%=Integer.parseInt(transmissionSpeed)%>);
      ScriptableObject.sltSetMarkValue("时间间隔",<%=Integer.parseInt(timeInterval)%>);
      ScriptableObject.sltSetMarkValue("丢包率",<%=Integer.parseInt(packetLoss)%>);
      ScriptableObject.sltSetMarkValue("网络延迟",<%=Integer.parseInt(networkDelay)%>);
    });
  </script>
</body>
</html>
