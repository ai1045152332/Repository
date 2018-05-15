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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/jquery-1.8.0.min.js"></script>
<script src="${pageContext.request.contextPath}/js/common/jquery.cookie.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/layer-v2.4/layer/layer.js"></script>
<body class="bg707681">
<style>

  #linkpage {
    bottom:0px;
    float: none;
    position: absolute;
    left: 55%;
  }

  .pages {
    float: none;
    height: 25px;
    margin-left: 0px;
    min-width: 0px;
  }
  .bgr,.bgred{
    margin-top: 4px;
    margin-left: 15px;
    margin-right: 5px;

  }
  .bgr {
    background: url(${pageContext.request.contextPath}/image/frontend/radio_xk.png) 0px -1px no-repeat;
    height:17px;
    width:17px;
  }
  .bgred{
    background: url(${pageContext.request.contextPath}/image/frontend/radio_xk.png) 0px -36px no-repeat;
    height:17px;
    width:17px;
  }
  .xk_video_group{
    text-indent: 0;
  }
    .xk_video{
	background: #535E71;
    height: 100%;
    float: left;
    position: relative;
    width: 100%;
    
}

.xk_video_selected{
    background: #535E71;
     height: 100%;
    float: left;
    position: relative;
    width: 100%;
}
  .jkn_mc_hover_name{
    color: #fff;
    height: 35px;
    line-height: 35px;
    overflow: hidden;
    text-overflow: ellipsis;
    text-indent: 15px;
    width: 50%;
    white-space: nowrap;
  }
</style>
<%
  Map hostListMap = (Map) request.getAttribute("hostingroup");
  List<Map> hostList = new ArrayList();
  int i = 0;
  String host_count;
  if (hostListMap != null && !hostListMap.isEmpty()) {
    hostList = (List<Map>)hostListMap.get("hostInfo");
    host_count = hostListMap.get("pageCount").toString();
  } else {
    host_count = "0";
  }
  int hostcount = Integer.parseInt(host_count);
  int hostListSize = 0;
  Map<String,String> map = new HashMap();
  map.put("电源管理","power");
  map.put("信号源切换","signal");
  map.put("音量调节","audio_control");
  map.put("音响模式调节","audio_mode");
  map.put("触摸功能","touch_screen");
  map.put("远程节能","remote_energy");
  map.put("垃圾清理状态","clean_status");

  if (!hostList.isEmpty()) {
    hostListSize = hostList.size();
    for (Map host : hostList) {
      String host_id = host.get("id").toString();
      String host_name = host.get("name").toString();
      // String host_type = host.get("type").toString();
      String hostip = host.get("host_ip").toString();
      String host_token = host.get("token").toString();
      String status = host.get("status").toString();
      //Map<String,String> map_cmds = (Map<String,String>)host.get("allinfo");
      //Map<String,String> images = (Map<String,String>)host.get("picture");
      String image_str = "";
      //if(images != null){
      //image_str = images.get("Value");
      //out.print(image_str);
      //}
      String signal = "";
      String remote_energy = "";
      String audio_control = "";
      String audio_mode = "";
      String touch_screen = "";
      String top_softWare="";
      String clean_status="";
      //if(map_cmds != null){
      //signal = map_cmds.get("ChannelName");
      //remote_energy = map_cmds.get("EnergyMode");
      //audio_control = map_cmds.get("Volume");
      //audio_mode = map_cmds.get("AudioMode");
      //touch_screen = map_cmds.get("TouchMode");
      //}
      List<Map<String,String>> cmdlist = (List<Map<String,String>>)host.get("host_cmd");
      Map<String,String> map_cmd = new HashMap();
      //String currentSignal = "";
      if(cmdlist.size()>0){
        for(int j=0;j<cmdlist.size();j++){//codetype,b.cmd_group, code_cmd
          if(map.get(cmdlist.get(j).get("cmd_group")) != null){
            map_cmd.put(map.get(cmdlist.get(j).get("cmd_group").toString()), cmdlist.get(j).get("codetype").toString());
            map_cmd.put(map.get(cmdlist.get(j).get("cmd_group").toString())+"_cmd",cmdlist.get(j).get("code_cmd").toString());
          }
        }
        //String[] signal_arr = map_cmd.get("signal").split(",");
        //String[] signal_cmd_arr = map_cmd.get("signal_cmd").split(",");
                    /*for(int k =0;k<signal_arr.length;k++){
                        String aa = signal_cmd_arr[k];
                        //out.print(aa);
                        //out.print(signal_arr[k]);
                        if(aa.equals(signal)){
                            currentSignal = signal_arr[k];
                            //out.print(signal_arr[k]);
                        }
                    }*/
      }
%>
<div class="hei_30 wid_33 floatleft showhidden" style="position: relative;margin-top: 1%;">

  <div class="allarea wid_33 floatleft">
    <div class="xk_video"  bingo="none" id="<%=host_id%>" ip="<%=hostip%>">
      <input id="host_cmd_<%=host_id%>" type="hidden" remote_energy="<%=map_cmd.get("remote_energy")%>" touch_screen="<%=map_cmd.get("touch_screen")%>" audio_mode="<%=map_cmd.get("audio_mode")%>" audio_control="<%=map_cmd.get("audio_control")%>" signal="<%=map_cmd.get("signal")%>" power="<%=map_cmd.get("power")%>" clean_status="<%=map_cmd.get("clean_status")%>">
      <input id="host_cmd_code_<%=host_id%>" type="hidden" remote_energy_cmd="<%=map_cmd.get("remote_energy_cmd")%>" touch_screen_cmd="<%=map_cmd.get("touch_screen_cmd")%>" audio_mode_cmd="<%=map_cmd.get("audio_mode_cmd")%>" audio_control_cmd="<%=map_cmd.get("audio_control_cmd")%>" signal_cmd="<%=map_cmd.get("signal_cmd")%>" power_cmd="<%=map_cmd.get("power_cmd")%>" clean_status_cmd="<%=map_cmd.get("clean_status_cmd")%>">
      <input id="host_status_<%=host_id%>" type="hidden" value="<%=status%>" />
      <input class="hoststatus" type="hidden" value="status_<%=host_id%>_<%=hostip%>" />
      <input id="status_<%=host_id%>" type="hidden" remote_energy="<%=remote_energy%>" touch_screen="<%=touch_screen%>" audio_mode="<%=audio_mode%>" audio_control="<%=audio_control%>" signal="<%=signal%>" top_softWare="<%=top_softWare%>" clean_status="<%=clean_status%>"/>
      <div id="image_<%=host_id%>">
        <%
          //if(!image_str.equals("")){
        %>
        <%--<img id="img_<%=host_id%>" src="<%="data:image/png;base64,"+image_str%>"  class="xk_video_img" style="position:absolute;left:0;top:0;z-index:1">--%>
        <%
          //}
        %>
      </div>
      <div class="jkn_mc_nosiginal"></div>
      <div class="jkn_mc_bz"></div>
      <% if(status.equals("Online")){%>
      <div class="jkn_mc_hover">
        <div class="jkn_mc_hover_name floatleft">教室：<span id = "current_class_<%=host_id%>"><%=host_name%></span></div>
        <div class="jkn_mc_hover_name floatleft">信号源：<span id="current_signal_<%=host_id%>"></span></div>
        <div class="jkn_mc_hover_name floatleft">内存使用率：<span id="current_memmary_<%=host_id%>"></span></div>
        <div class="jkn_mc_hover_name floatleft">CPU使用率：<span id="current_cpuUsage_<%=host_id%>"></span></div>
        <div class="jkn_mc_hover_name floatleft">C盘使用率：<span id="current_disk_C_<%=host_id%>"></span></div>
        <div class="jkn_mc_hover_name floatleft">音量：<span id="current_audio_control_<%=host_id%>"></span></div>
        <div class="jkn_mc_hover_name floatleft">音响模式：<span id="current_audio_mode_<%=host_id%>"></span></div>
        <div class="jkn_mc_hover_name floatleft">节能模式：<span id="current_remote_energy_<%=host_id%>"></span></div>
        <div class="jkn_mc_hover_name floatleft">置顶软件：<span id="current_top_softWare_<%=host_id%>"></span></div>
        <% if ("8".equals(host.get("dspec"))||"13".equals(host.get("dspec"))||"14".equals(host.get("dspec"))){//当设备为901.6/828/638设备时 支持触控切换 所以显示此项 %>
        <div class="jkn_mc_hover_name floatleft">触控状态：<span id="current_touch_screen_<%=host_id%>"></span></div>
        <% }%>
        <div class="jkn_mc_hover_name floatleft">垃圾清理状态：<span id="current_clean_status_<%=host_id%>"></span></div>
      </div>
      <%}%>
    </div>
  </div>
</div>
<%
    i++;
  }
%>
</div>
<div class="pages floatleft">
  <link href="${pageContext.request.contextPath}/css/frontend/hpager.css" rel="stylesheet"
        type="text/css"/>
  <%
    Integer currentpage = Integer.parseInt(request.getParameter("currentPage"));
    Pager pager = new Pager(hostcount, currentpage, "<span class='pages_prevpage'></span>", "<span class='pages_nextpage'></span>", "", "", false);
    String pagers = pager.run();
  %>
  <input id="current" value="${currentPage}" style="display: none"/>
  <div id="linkpage">
    <%=pagers%>
  </div>
</div>
<%
  }
%>
<input type="text" style="display: none" id="deviceParams" value="" deviceId=""
       urlhead="${pageContext.request.contextPath}"/>

<script>
  $.cookie("curPageHHTC",'${currentPage}',{path: '/'});
  function getImage() {
    var hostSize = "<%=hostListSize%>";
    hostSize = parseInt(hostSize);
    //alert(decodeURI(viewClassCameraName));
    var hostid = "";
    var hostip = "";
    if(hostSize != 0){
      for(var i =0;i<hostSize;i++){
        var hostStatus = $(".allarea").eq(i).find(".hoststatus").val();
        var hostarr = hostStatus.split("_");
        hostid = hostarr[1];
        hostip = hostarr[2];
        var status = $("#host_status_"+hostid).val();
        if(status == "Online" && hostid != "" && hostip !=""){
          $.post("${pageContext.request.contextPath}/dmanager/DManager_getImage.do",
                  {hostid:hostid,hostIp:hostip},
                  function(data){
                    for(var k=0;k<data.length;k++){
                      var objectid = data[k].id;
                      var imageUrl = data[k].Value;
                      if(imageUrl != ""){
                        imageUrl = "data:image/png;base64,"+imageUrl;
                        $("#image_"+objectid).html("<img class='xk_video_img' style='position:absolute;left:0;top:0;z-index:1;width:100%;height:100%' src='"+imageUrl+"'>");
                      }
                    }
                  },'json');
        }
      }
    }
  }
  /**
   * 获取url地址中的参数
   */
  function urloption(url){
    var totalpagesize = "<%=hostcount%>";
    if(url.indexOf("?")!=-1){
      var p=url.indexOf("?"); //返回所在位置
      var host="http://"+window.location.host+url.substr(0,p+1);
      var str = url.substr(p+1) //从这个位置开始截取
      strs = str.split("&"); //拆分
      var jumpval=$("#jump").val();
      var patrn = /^[0-9]*$/;
      if(!patrn.exec(jumpval)|| jumpval<=0){
        jumpval=1;
      }else if(parseInt(jumpval)>=parseInt(totalpagesize)){
        jumpval=totalpagesize
      }
      var newurl=host;
      for(var i=0;i<strs.length;i++){
        if(i==0){
          newurl+=strs[i].split("=")[0]+"="+jumpval+"&";
        }else{
          newurl+=strs[i].split("=")[0]+"="+unescape(strs[i].split("=")[1])+"&";
        }
      }
      var urllen=newurl.length-1;
      var newurl = newurl.substr(0,urllen) //从这个位置开始截取
      location.href=newurl;
    }
  }
  $(function(){
    var hostSize = "<%=hostListSize%>";
    hostSize = parseInt(hostSize);
    
    //alert(decodeURI(viewClassCameraName));
    var hostid = "";
    var hostip = "";
    if(hostSize != 0){
      for(var i =0;i<hostSize;i++){
        var hostStatus = $(".allarea").eq(i).find(".hoststatus").val();
        var hostarr = hostStatus.split("_");
        //hostid += hostarr[1]+",";
        //hostip += hostarr[2]+",";
        hostid = hostarr[1];
        hostip = hostarr[2];
        var status = $("#host_status_"+hostid).val();
        if(status == "Online" && hostid != "" && hostip !=""){
          $.post("${pageContext.request.contextPath}/dmanager/DManager_getInfo.do",
                  {hostid:hostid,hostIp:hostip},
                  function(data){
                    //console.log(data);
                    for(var j=0;j<data.length;j++) {
                      var hostidstr = data[j].id;
                      var signal = data[j].ChannelName;
                      //alert(signal);
                      var energyMode = data[j].EnergyMode;
                      var volume = data[j].Volume;
                      var audioMode = data[j].AudioMode;
                      var touchMode = data[j].TouchMode;
                      var cpuUsage = data[j].CpuUsage;
                      var disk_C = data[j].Disk_C;
                      var memmary = data[j].Memmary;
                      var topSoftWare=data[j].TopSoftWare;
                      var oneKeyClean = "未清理垃圾";

                      $("#status_" + hostidstr).attr("remote_energy", energyMode);
                      $("#status_" + hostidstr).attr("touch_screen", touchMode);
                      $("#status_" + hostidstr).attr("audio_mode", audioMode);
                      $("#status_" + hostidstr).attr("audio_control", volume);
                      $("#status_" + hostidstr).attr("signal", signal);
                      $("#status_" + hostidstr).attr("topSoftWare", topSoftWare);
                      $("#status_" + hostidstr).attr("clean_status", oneKeyClean);
                      var currentSignal = "";
                      var power = $("#host_cmd_" + hostidstr).attr("signal");

                      var power_cmd = $("#host_cmd_code_" + hostidstr).attr("signal_cmd");
                      if (power != "null") {
                        var arr = power.split(",");
                        var arr_cmd = power_cmd.split(",");
                        for (var i = 0; i < arr.length; i++) {
                          if (arr_cmd[i] == signal) {
                            currentSignal = arr[i];
                          }
                        }
                        $("#current_signal_" + hostidstr).html(currentSignal);
                      }
                      //触控
                      var currentTouchMode = "";
                      var touch = $("#host_cmd_" + hostidstr).attr("touch_screen");
                      var touch_cmd = $("#host_cmd_code_" + hostidstr).attr("touch_screen_cmd");
                      if (touch != "null") {
                        var arr = touch.split(",");
                        var arr_cmd = touch_cmd.split(",");
                        for (var i = 0; i < arr.length; i++) {
                          if (arr_cmd[i] == touchMode) {
                            currentTouchMode = arr[i];
                          }
                        }
                        //触控
                        $("#current_touch_screen_" + hostidstr).html(currentTouchMode);
                      }

                      var currentAudioMode = "";
                      var audio = $("#host_cmd_" + hostidstr).attr("audio_mode");
                      var audio_cmd = $("#host_cmd_code_" + hostidstr).attr("audio_mode_cmd");
                      if (audio != "null") {
                        var arr = audio.split(",");
                        var arr_cmd = audio_cmd.split(",");
                        for (var i = 0; i < arr.length; i++) {
                          if (arr_cmd[i] == audioMode) {
                            currentAudioMode = arr[i];
                          }
                        }
                        //音响模式
                        $("#current_audio_mode_" + hostidstr).html(currentAudioMode);
                      }
                      var currentEnergyMode = "";
                      var energy = $("#host_cmd_" + hostidstr).attr("remote_energy");
                      var energy_cmd = $("#host_cmd_code_" + hostidstr).attr("remote_energy_cmd");
                      if (energy != "null") {
                        var arr = energy.split(",");
                        var arr_cmd = energy_cmd.split(",");
                        for (var i = 0; i < arr.length; i++) {
                          if (arr_cmd[i] == energyMode) {
                            currentEnergyMode = arr[i];
                          }
                        }
                        //节能模式
                        $("#current_remote_energy_" + hostidstr).html(currentEnergyMode);
                      }

                        //垃圾清理状态
                      $("#current_clean_status_" + hostidstr).html(oneKeyClean);
                      //音量
                      $("#current_audio_control_" + hostidstr).html(volume);
                      $("#current_cpuUsage_" + hostidstr).html(cpuUsage);
                      $("#current_disk_C_" + hostidstr).html(disk_C);
                      $("#current_memmary_" + hostidstr).html(memmary);
                      $("#current_top_softWare_" + hostidstr).html(topSoftWare);

                    }
                  },'json');
          $.post("${pageContext.request.contextPath}/dmanager/DManager_getImage.do",
                  {hostid:hostid,hostIp:hostip},
                  function(data){
                    for(var k=0;k<data.length;k++){
                      var objectid = data[k].id;
                      var imageUrl = data[k].Value;
                      if(imageUrl != ""){
                        imageUrl = "data:image/png;base64,"+imageUrl;
                        $("#image_"+objectid).html("<img class='xk_video_img' style='position:absolute;left:0;top:0;z-index:1;width:100%;height:100%' src='"+imageUrl+"'>");
                      }
                    }
                  },'json');
        }
      }
    }
    setInterval("getImage()",30000);//30秒钟获取一次桌面截图
    var totalpagesize = "<%=hostcount%>";
    //分页调整
    var page=totalpagesize;

    if(page<=1){
      parent.$(".check_head_label").hide()
      $("#linkpage").hide()
    }else{
      parent.$(".check_head_label").show()
      $("#linkpage").show()
    }
    var html="<span  style='float: left;margin-left: 2px;color: #fff;'>&nbsp;&nbsp;共"+page+"页 &nbsp;&nbsp;跳转到 "+"<input id=\"jump\" type=text style=\"width:24px;background:#d7dade;border:1px solid #5e6470\" title=\"点击回车即可跳转分页\" onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'')\" onafterpaste=\"this.value=this.value.replace(/[^0-9]/g,'')\"/> 页<span>";
    $(".yiiPager").append(html)
    left=-(18)*28/2+"px";
    $("#linkpage").css({"margin-left":left,"width":(17)*28+"px"});
    $("#jump").focus().keydown(function(event){
      //判断当event.keyCode 为37时（即左方面键），执行函数to_left();
      //判断当event.keyCode 为39时（即右方面键），执行函数to_right();
      if(event.which == 13){
        //alert("aaa")
        var jumpval=$("#jump").val();
        var patrn = /^[0-9]*$/;
        if(!patrn.exec(jumpval)){
          return false;
        }else{
          var lilen=$("#linkpage ul li").length-1;
          var prevhref=$("#linkpage ul li").eq(0).find("a").attr("href");
          var nexthref=$("#linkpage ul li").eq(lilen).find("a").attr("href");
          var thisurl="";
          if(prevhref==""&&nexthref==""){
            return false;
          }else if(prevhref==""||prevhref==undefined){
            thisurl=nexthref;
            urloption(thisurl)
          }else if(nexthref==""||nexthref==undefined){
            thisurl=prevhref;
            urloption(thisurl)
          }else{
            thisurl=nexthref;
            urloption(thisurl)
          }
        }
      }
    })
  })
  function addidcookie(){
    var treeid="";
    var checkboxlened=$(".jkn_treecheckboxed").length;
    for(i=0;i<checkboxlened;i++){
      var thisid=$(".jkn_treecheckboxed").eq(i).attr("hostid")
      treeid=treeid+thisid+",";//获取id数组
      $.cookie("settreeid1",treeid,{expires: 1,path: '/' })
    }
  }
  $(function () {
    var urlHead = $("#newsParams").attr("deviceParams");
    //非全屏状态下效果
    var iframewidth=$(".hei_30").width();
    var iframeheight=$(".hei_30").height();
    var sixtweenwidth=iframeheight/(9/16);
    //alert(sixtweenwidth+"--"+iframewidth)
    if(sixtweenwidth>=iframewidth){//高度计算宽度
      var sixtweenheight=(iframewidth*(9/16));//计算高度
      if(sixtweenheight>=iframeheight){//如果高度大于45%
        var sixtweenheight;
        for(i=iframewidth;i>0;i--){
          sixtweenheight=(i*(9/16))
          if(sixtweenheight<=iframeheight){
            var iframewidth=iframewidth-30
            var sixtweenheight=(iframewidth*(9/16));//计算高度
            $(".allarea").height(sixtweenheight)
            $(".allarea").width(iframewidth)
            $(".twofour,.xk_video_img,.xk_video_twofour").height(sixtweenheight)
            $(".xk_video_img,.xk_video_twofour,.xk_video_twofour").width(iframewidth)
            var halfwidth=$(".xk_video_twofour").width()/2-1;
            var halfheight=$(".xk_video_twofour").height()/2;
            $(".xk_video_twofourobj").width(halfwidth);
            $(".xk_video_twofourobj").height(halfheight)
            $(".fourfour").attr("height",halfheight)
            $(".fourfour").css("margin-top",halfheight/2+"px")
            break;
          }
        }
      }else{
        var iframewidth=iframewidth-30
        var sixtweenheight=(iframewidth*(9/16));//计算高度
        $(".allarea").height(sixtweenheight)
        $(".allarea").width(iframewidth)
        $(".twofour,.xk_video_twofour,.xk_video_img,").height(sixtweenheight)
        $(".xk_video_img,.xk_video_twofour,.xk_video_twofour").width(iframewidth)
        var halfwidth=$(".xk_video_twofour").width()/2-1;
        var halfheight=$(".xk_video_twofour").height()/2;
        $(".xk_video_twofourobj").width(halfwidth);
        $(".xk_video_twofourobj").height(halfheight)
        $(".fourfour").attr("height",halfheight)
        $(".fourfour").css("margin-top",halfheight/2+"px")
      }
    }else{//宽度计算高度
      $(".allarea").height(iframeheight)
      $(".allarea").width(sixtweenwidth)
      $(".twofour,.xk_video_twofour,.xk_video_img").height(iframeheight-25)
      $(".xk_video_img,.xk_video_twofour,.xk_video_twofour").width(sixtweenwidth)
      var halfwidth=$(".xk_video_twofour").width()/2-1;
      var halfheight=$(".xk_video_twofour").height()/2;
      $(".xk_video_twofourobj").width(halfwidth);
      $(".xk_video_twofourobj").height(halfheight)
      $(".fourfour").attr("height",halfheight)
      $(".fourfour").css("margin-top",halfheight/2+"px")
    }
    $(".selectiframe").contents().find("body").css("background", "transparent")
    $(document.body).find("input[type=checkbox]").prop("checked", false).css("display", "none");
    $(".check_head label").click(function () {
      var flag = $("#select_auto").prop("checked");
      if (flag == true) {
        $(".bged").addClass("bg").removeClass("bged");
        //alert("false");

        clear();
      } else {
        $(".bg").addClass("bged").removeClass("bg");
        //alert("true");
        //选中后执行
        start_timer();
      }
    });
    //鼠标经过效果
    $(".xk_video").mouseover(function(){
      $(this).find(".jkn_mc_hover").show();
    }).mouseout(function(){
      $(this).find(".jkn_mc_hover").hide();
    })
    $(".xk_video_selected").mouseover(function(){
      $(this).find(".jkn_mc_hover").show();
    }).mouseout(function(){
      $(this).find(".jkn_mc_hover").hide();
    })
    //判断有无信号源，没有显示logo
    var xkvideolen=$(".showhidden").length;
    for(i=0;i<xkvideolen;i++){
      var online=$(".showhidden").eq(i).find("input[type=hidden]").eq(2).val();
      if(online!="Online"){
        $(".showhidden").eq(i).find(".jkn_mc_nosiginal").show()
        $(".showhidden").eq(i).find(".allarea").children(".xk_video_selected").css("background","#535E71")
        $(".showhidden").eq(i).find(".allarea").children(".xk_video").css("background","#535E71")
      }
    }

    //选择教室(主机)
    $(".xk_video").click(function () {
      if ($(this).attr("bingo") == "none") {
        parent.$(".jkn_mc_powerson_cover").hide();
        $(this).attr("class", "xk_video_selected");
        $(this).find(".jkn_mc_bz").show()
        $(this).css("box-shadow","0 0 0 3px #5cb75a")
        $(this).find(".bgr").addClass("bgred").removeClass("bgr");
        //标识选中
        $(this).attr("bingo", "done");
        parent.$("#classmessage").html("")
        //未选择班级是需要去除左侧树的选中样式;
        var disableid=$(this).attr("id");
        parent.$("#jkn_checkbox_"+disableid).click();
        var treedisableid=parent.$(".tree_title_open .tree_content").find(".tree_contenta");
        //未选择班级还需要判断当前打开分组是都全部选中，如果选中，需要勾选分组的checkbox
        if( treedisableid.find(".jkn_treecheckboxed").length==treedisableid.length){
          parent.$(".tree_title_open").children("span").addClass("tree_titleaiconmained_jkn").removeClass("tree_titleaiconmain_jkn")
        }
        var hostip = $(this).attr("ip");
        var hostid = $(this).attr("id");
        parent.$("#selected_host").val(hostid);
      } else if ($(this).attr("bingo") == "done") { //未选班级时
        $(this).attr("bingo", "none");
        $(this).attr("class", "xk_video");
        $(this).css("box-shadow","none")
        $(this).find(".jkn_mc_bz").hide()
        $(this).find(".bgred").addClass("bgr").removeClass("bgred");
        parent.$(".jkn_mc_powerson_cover").hide();
        parent.$("#selected_host").val("");
        parent.$("#selected_host").attr("ip","");
        parent.$("#power").show();
        parent.$("#signal").show();
        parent.$("#audio_control").show();
        parent.$("#audio_mode").show();
        parent.$("#touch_screen").show();
        parent.$("#remote_energy").show();
        parent.$("#top_softWare").show();
        parent.$("#clean_status").show();
        //未选择班级是需要去除左侧树的选中样式
        var disableid=$(this).attr("id");
        parent.$("#jkn_checkbox_"+disableid).click();
        var treedisableid=parent.$(".tree_title_open .tree_content").find(".tree_contenta");
        //未选择班级还需要判断当前打开分组是都全部选中，如果选中，需要勾选分组的checkbox
        if( treedisableid.find(".jkn_treecheckboxed").length<treedisableid.length){
          parent.$(".tree_title_open").children("span").addClass("tree_titleaiconmain_jkn").removeClass("tree_titleaiconmained_jkn")
        }
      }

    });
    var names = document.getElementsByName("ScriptableObject");
    //alert(names.length);
    for (var i = 0; i < names.length; i++) {
      var url = names[i].getAttribute("url");
      var id = names[i].getAttribute("id");
      var obj = document.getElementById(id);
      // alert(url);
      //obj.play(url);
      try {
        names[i].play(url);
      } catch (e) {

      }
    }

  })
  //点击空白区域，隐藏操作项子菜单
  $(document).bind("click",function(e){
    var target = $(e.target);
    //alert(target.closest(".xk_options").length)
    if(target.closest(".xk_options").length == 0){
      parent.$(".jkn_mc_powerson").css("z-index","-1");
    }
  })
  /*if (self != top) {
   top.location = self.location;
   }*/
  //消息发送调用
  function setval(parames,dapingip,types){
    var pinglen=$(".showhidden").length;//获取页面大屏个数
    for(i=0;i<pinglen;i++){
      if($(".showhidden").eq(i).find(".allarea").children("div").eq(0).attr("ip")==dapingip){//比对ip
        var cid = $(".showhidden").eq(i).find(".allarea").children("div").eq(0).attr("id");
        if(types =="Channel")
        {
          $("#status_"+cid).attr("signal",parames);
          var currentSignal = "";
          var power = $("#host_cmd_" + cid).attr("signal");
          var power_cmd = $("#host_cmd_code_" + cid).attr("signal_cmd");
          if (power != "null") {
            var arr = power.split(",");
            var arr_cmd = power_cmd.split(",");
            for (var i = 0; i < arr.length; i++) {
              if (arr_cmd[i] == parames) {
                currentSignal = arr[i];
              }
            }
            $("#current_signal_" + cid).html(currentSignal);
          }
          //$(".showhidden").eq(i).find(".allarea").find("jkn_mc_hover").children("div").eq(1).children("span").text(parames)
        }
        else if(types =="AudioMode")
        {
          $("#status_"+cid).attr("audio_mode",parames);
          var currentSignal = "";
          var power = $("#host_cmd_" + cid).attr("audio_mode");
          var power_cmd = $("#host_cmd_code_" + cid).attr("audio_mode_cmd");
          if (power != "null") {
            var arr = power.split(",");
            var arr_cmd = power_cmd.split(",");
            for (var i = 0; i < arr.length; i++) {
              if (arr_cmd[i] == parames) {
                currentSignal = arr[i];
              }
            }
            $("#current_audio_mode_" + cid).html(currentSignal);
          }
        }
        else if(types =="ScreenLockMode")
        {
          $("#status_"+cid).attr("touch_screen",parames);
          var currentTouchMode = "";
          var touch = $("#host_cmd_" + cid).attr("touch_screen");
          var touch_cmd = $("#host_cmd_code_" + cid).attr("touch_screen_cmd");
          if (touch != "null") {
            var arr = touch.split(",");
            var arr_cmd = touch_cmd.split(",");
            for (var i = 0; i < arr.length; i++) {
              if (arr_cmd[i] == parames) {
                currentTouchMode = arr[i];
              }
            }
            //触控
            $("#current_touch_screen_" + cid).html(currentTouchMode);
          }
        }
        else if(types =="EnergyMode") {
          $("#status_"+cid).attr("remote_energy", parames);
          var currentSignal = "";
          var power = $("#host_cmd_" + cid).attr("remote_energy");
          var power_cmd = $("#host_cmd_code_" + cid).attr("remote_energy_cmd");
          if (power != "null") {
            var arr = power.split(",");
            var arr_cmd = power_cmd.split(",");
            for (var i = 0; i < arr.length; i++) {
              if (arr_cmd[i] == parames) {
                currentSignal = arr[i];
              }
            }
            $("#current_remote_energy_" + cid).html(currentSignal);
          }
        }
        else if(types =="Volume") {
          $("#status_"+cid).attr("audio_control", parames);
          $("#current_audio_control_"+cid).html(parames);
        }
        else if(types =="ClassName") {
          if($(".showhidden").eq(i).find(".allarea").find(".jkn_mc_hover").children("div").eq(0).children("span").text()!=parames){
            $(".showhidden").eq(i).find(".allarea").find(".jkn_mc_hover").children("div").eq(0).children("span").text(parames);
            var treelen= parent.$(".tree_title_open").find(".tree_content").children(".tree_contenta");
            for(i=0;i<treelen.length;i++){
              var treeip=treelen.eq(i).attr("token");
              if(treeip==dapingip){
                treelen.eq(i).find(".hostoverflow").text(parames);
                $("#current_class_"+cid).html(parames);
              }
            }
          }
        }
        else if(types == "topSoftWare") {
          $("#status_" + cid).attr("top_softWare", parames);
          $("#current_top_softWare_" + cid).html(parames);
        }
        else if(types == "OneKeyClean") {
          if(parames=="true"){
            parames="清理完毕";
          }
          $("#status_" + cid).attr("clean_status", parames);
          $("#current_clean_status_" + cid).html(parames);
          clean(parames,dapingip);
        }
      }
    }
  }
  //垃圾清理提示
  function clean( para, ip){
    layer.open({
      type: 2,
      title: false,
      closeBtn: 0, //不显示关闭按钮
      shade: [0],
      area: ['240px', '155px'],
      offset: 'rb', //右下角弹出
      time: 4000, //2秒后自动关闭
      shift: 2,
      content: ['/dmanager/DManager_turnInfo.do?parames='+para+'&dapingip='+ip, 'no'] //iframe的url，no代表不显示滚动条

    });
  }
  $(function(){
    var idstr = $.cookie("settreeid");
    if(idstr){
      var idarr = idstr.split(",");
      for(var i =0 ;i<idarr.length; i++){
        if($("#"+idarr[i]).attr("class")=="xk_video"){
          $("#"+idarr[i]).addClass("xk_video_selected").removeClass("xk_video")
          $("#"+idarr[i]).attr("bingo","done")
          $("#"+idarr[i]).css("box-shadow","0px 0px 0px 3px rgb(92, 183, 90)")
          $("#"+idarr[i]).find(".jkn_mc_bz").show()
        }
        //选中左侧树
        var treelen=parent.$(".tree_title_open .tree_content").find(".tree_contenta");
        for(j=0;j<treelen.length;j++){
          var hostid=treelen.eq(j).children("div").attr("hostid");
          if(hostid==idarr[i]){
            if(treelen.eq(j).children("div").attr("class")=="jkn_treecheckbox"){
              treelen.eq(j).children("div").addClass("jkn_treecheckboxed").removeClass("jkn_treecheckbox")
            }
          }
        }
        if(treelen.length==parent.$(".tree_title_open .tree_content").find(".jkn_treecheckboxed").length){
          parent.$(".tree_title_open").children("span").addClass("tree_titleaiconmained_jkn").removeClass("tree_titleaiconmain_jkn")
        }
      }
    }
  })
</script>
</body>
