<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.honghe.recordweb.util.MD5Util" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.honghe.recordhibernate.dao.Pager" %>
<%--
  Created by IntelliJ IDEA.
  User: chby
  Date: 2014/10/11
  Time: 9:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
  <title>未分组的班级 | 集控平台</title>
  <script src="${pageContext.request.contextPath}/js/common/jquery-1.8.0.min.js"></script>
  <link href="${pageContext.request.contextPath}/css/frontend/record/index.css" rel="stylesheet" type="text/css"/>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/frontend/device/group.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/jquery.cookie.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/js/common/screendetail.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/checkbox.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/scrolldemo.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
</head>
<body>
<input type="hidden" id="whichscroll">
<div class="public">
  <jsp:include page="/pages/frontend/equipment_header.jsp"></jsp:include>
  <%
    List<Map> hostNoRelationList = new ArrayList();
    String pageCount= "0";
    String itemsCount = "0";
    Map<String,Object> hostNoRelationLists = (Map<String,Object>) request.getAttribute("hostNoRelationLists");
    if(hostNoRelationLists != null && hostNoRelationLists.size()>0){
      hostNoRelationList = (List<Map>)hostNoRelationLists.get("host_list");
      pageCount = hostNoRelationLists.get("pageCount").toString();
      itemsCount = hostNoRelationLists.get("itemsCount").toString();
    }
  %>
  <div class="mm floatleft">
    <div class="mm_head floatleft">
      <a href="${pageContext.request.contextPath}/host/Host_hostManagement.do"><span class="mm_nogroup_option" style="margin-left: 20px;"><span class="mm_nogroup_icon"></span>返回</span></a>
      <%
        if (!hostNoRelationList.isEmpty()) {
      %>
      <a href="javascript:void(0)" class="moveHosts"><span class="mm_nogroup_option"><span class="mm_nogroup_moveto"></span>移动到</span></a>
      <%
        }
      %>
      <%--<a href="javascript:location.reload()" class="refreshpage"><span--%>
      <%--class="mm_nogroup_option"><span class="mm_nogroup_refrash"></span>刷新</span></a>--%>
    </div>
    <div class="scrollfather" id="mm_nas_videonogroup" style="overflow: hidden;float: left;width: 100%;">
      <div class="scrollson">
        <%
          if (!hostNoRelationList.isEmpty()) {
        %>
        <div class="user_checkall floatleft">
          <div class="checkbox" style="margin-top:10px;">
            <input type="checkbox" id="checkall"/>
          </div>
          <div class="all">
            <div class="head" style="margin-left: 30px;">
              <div class="bg" id="ischeckall" style="margin-top: 0;"></div>
              全选
            </div>
          </div>
        </div>
        <%
          }
        %>

        <%
          for (Map<String, Object> obj : hostNoRelationList) {
            String status = "mm_offline";
            String hostDesc = obj.get("host_desc").toString();
            String dtypeName = obj.get("dtype_name").toString();
            String font = "离线";
            String style = "mm_list_logo_gray";
            if (obj.containsKey("status") && obj.get("status").toString().equals("Online")) {
              status = "mm_online";
              font = "在线";
            }
            if (obj.get("host_username") != null && obj.get("host_username").equals("@error@")) {style = "mm_list_logoerror";
            }
        %>
        <% if(dtypeName.equals("NVR")){%>
        <%
          if(hostDesc.equals("0")){ //简易录播
            style = "mm_list_jy_logo_gray";
            if (obj.containsKey("status") && obj.get("status").toString().equals("Online")) {
              style = "mm_list_jy_logo";
            }
        %>
        <div class="mm_listout" style="margin-top: 15px;">
          <div class="mm_nogroup_list" style="margin: 0;cursor: pointer" bingo="none"
               onclick="device('<%=obj.get("id")%>','<%=obj.get("host_ip")%>','<%=obj.get("name")%>')">
            <div class="mm_list_group">
              <div class="<%=style%>"></div>
              <div class="mm_list_textdevice"><%=obj.get("name")%></div>
              <%
                String ip = obj.get("host_ip").toString().trim();
              %>
              <div class="mm_list_text" style="font-size: 14px;" title="<%=ip%>">
                <%
                  out.println(ip);
                %>
              </div>
              <span class="<%=status%>"><%=font%></span>
            </div>
          </div>
          <div class="mm_list_option">
            <style>
              .bg,.bged{
                margin-left: 5px;
              }
              .mm_list_option{
                width: 175px;
              }
            </style>
                        <span class="bg" hostId="<%=obj.get("id")%>"
                              hostname="<%=obj.get("name")%>"></span>
                        <span class="mm_nogroup_del" style="float: right;" title="删除班级"
                              onclick="delhost('<%=obj.get("id")%>','<%=obj.get("host_ip")%>')"></span>
            <%
              if (obj.get("name").toString().equals("") || MD5Util.ipValid(obj.get("name").toString())) {
            %>
            <span hostId="<%=obj.get("id")%>" hostIp="<%=obj.get("host_ip")%>" class="mm_nogroup_list_machineset" style="background-position: -1px 0;" title="编辑班级信息"></span>
            <%
              }
            %>
            <%
              if(font.equals("在线") && hostDesc.equals("0")){
            %>
            <a href="javascript:nvrView('<%=obj.get("host_ip").toString().trim()%>','<%=obj.get("host_username").toString()%>','<%=obj.get("host_password").toString()%>')" style="cursor: default;">
                            <span class="mm_list_options mm_list_setnvr" style="float: right;"
                                  title="查看NVR"></span>
            </a>
            <%
              }
            %>
          </div>
        </div>
        <%
        }else{//精品录播
          style = "mm_list_jp_logo_gray";
          if (obj.containsKey("status") && obj.get("status").toString().equals("Online")) {
            style = "mm_list_jp_logo";
          }
        %>
        <div class="mm_listout" style="margin-top: 15px;">
          <div class="mm_nogroup_list" style="margin: 0;cursor: pointer" bingo="none"
               onclick="device('<%=obj.get("id")%>','<%=obj.get("host_ip")%>','<%=obj.get("name")%>')">
            <div class="mm_list_group">
              <div class="<%=style%>"></div>
              <div class="mm_list_textdevice"><%=obj.get("name")%>
              </div>
              <%
                String ip = obj.get("host_ip").toString().trim();
              %>
              <div class="mm_list_text" style="font-size: 14px;" title="<%=ip%>">
                <%

                  out.println(ip);
                %>
              </div>
              <span class="<%=status%>"><%=font%></span>
            </div>
          </div>
          <div class="mm_list_option">
            <style>
              .bg,.bged{
                margin-left: 5px;
              }
              .mm_list_option{
                width: 175px;
              }
            </style>
            <span class="bg" hostId="<%=obj.get("id")%>" hostname="<%=obj.get("name")%>"></span>
                        <span class="mm_nogroup_del" style="float: right;" title="删除班级"
                              onclick="delhost('<%=obj.get("id")%>','<%=obj.get("host_ip")%>')"></span>
            <%
              if (obj.get("name").toString().equals("") || MD5Util.ipValid(obj.get("name").toString())) {
            %>
            <span hostId="<%=obj.get("id")%>" hostIpaddr="<%=obj.get("host_ip")%>" class="mm_nogroup_list_machineset" title="编辑班级信息"></span>
            <%
              }
            %>
            <%
              if(font.equals("在线")){
            %>
            <a href="javascript:nvrView('<%=obj.get("host_ip").toString().trim()%>','<%=obj.get("host_username").toString()%>','<%=obj.get("host_password").toString()%>')" style="cursor: default;">
              <%--<span class="mm_list_options mm_list_setnvr" style="float: right;"--%>
              <%--title="查看NVR"></span>--%>
            </a>
            <%}%>
          </div>
        </div>
        <%
          }
        %>
        <%
        }else if(dtypeName.equals("HHTC")){//大屏设备
          style = "mm_list_dp_logo_gray";
          if (obj.containsKey("status") && obj.get("status").toString().equals("Online")) {style = "mm_list_dp_logo";
          }
        %>
        <div class="mm_listout" style="margin-top: 15px;">
          <div class="mm_nogroup_list" style="margin: 0;cursor: default" bingo="none">
            <div class="mm_list_group">
              <div class="<%=style%>"></div>
              <div class="mm_list_textdevice"><%=obj.get("name")%>
              </div>
              <%
                String ip = obj.get("host_ip").toString().trim();
              %>
              <div class="mm_list_text" style="font-size: 14px;" title="<%=ip%>>">
                <%

                  out.println(ip);
                %>
              </div>
              <span class="<%=status%>"><%=font%></span>
            </div>
          </div>
          <div class="mm_list_option">
            <style>
              .bg,.bged{
                margin-left: 5px;
              }
              .mm_list_option{
                width: 175px;
              }
            </style>
            <span class="bg" hostId="<%=obj.get("id")%>" hostname="<%=obj.get("name")%>"></span>
            <span class="mm_nogroup_del" style="float: right;" title="删除班级" onclick="delhost('<%=obj.get("id")%>','<%=obj.get("host_ip")%>')"></span>

            <span hostId="<%=obj.get("id")%>" hostIpaddr="<%=obj.get("host_ip")%>" class="mm_nogroup_list_machineset" title="编辑班级信息"></span>
            <%
              if(font.equals("在线")){
            %>
            <a href="javascript:nvrView('<%=obj.get("host_ip").toString().trim()%>','<%=obj.get("host_username").toString()%>','<%=obj.get("host_password").toString()%>')" style="cursor: default;">
              <%--<span class="mm_list_options mm_list_setnvr" style="float: right;"--%>
              <%--title="查看NVR"></span>--%>
            </a>
            <%}%>
          </div>
        </div>
        <%
        }else if (dtypeName.equals("HHTWB")) {//白板一体机设备
          style = "mm_list_hhtwb_logo_gray";
          if (obj.containsKey("status") && obj.get("status").toString().equals("Online")) {
            style = "mm_list_hhtwb_logo";
          }
        %>
        <div class="mm_listout" style="margin-top: 15px;">
          <div class="mm_nogroup_list" style="margin: 0;cursor: default" bingo="none">
            <div class="mm_list_group">
              <div class="<%=style%>"></div>
              <div class="mm_list_textdevice"><%=obj.get("name")%>
              </div>
              <%
                String ip = obj.get("host_ip").toString().trim();
              %>
              <div class="mm_list_text" style="font-size: 14px;" title="<%=ip%>>">
                <%

                  out.println(ip);
                %>
              </div>
              <span class="<%=status%>"><%=font%></span>
            </div>
          </div>
          <div class="mm_list_option">
            <style>
              .bg, .bged {
                margin-left: 5px;
              }

              .mm_list_option {
                width: 175px;
              }
            </style>
            <span class="bg" hostId="<%=obj.get("id")%>" hostname="<%=obj.get("name")%>"></span>
                        <span class="mm_nogroup_del" style="float: right;" title="删除班级"
                              onclick="delhost('<%=obj.get("id")%>','<%=obj.get("host_ip")%>')"></span>
            <%
              if (obj.get("name").toString().equals("") || MD5Util.ipValid(obj.get("name").toString())) {
            %>
                        <span hostId="<%=obj.get("id")%>" hostIpaddr="<%=obj.get("host_ip")%>"
                              class="mm_nogroup_list_machineset" title="编辑班级信息"></span>
            <%
              }
            %>
            <%
              if (font.equals("在线")) {
            %>
            <a href="javascript:nvrView('<%=obj.get("host_ip").toString().trim()%>','<%=obj.get("host_username").toString()%>','<%=obj.get("host_password").toString()%>')"
               style="cursor: default;">
              <%--<span class="mm_list_options mm_list_setnvr" style="float: right;"--%>
              <%--title="查看NVR"></span>--%>
            </a>
            <%}%>
          </div>
        </div>
        <%
        } else{
          style = "mm_list_dp_unkonwnlogo_gray";
          if (obj.containsKey("status") && obj.get("status").toString().equals("Online")) {
            style = "mm_list_dp_unkonwnlogo";
          }
        %>
        <div class="mm_listout" style="margin-top: 15px;">
          <div class="mm_nogroup_list" style="margin: 0;cursor: default" bingo="none">
            <div class="mm_list_group">
              <div class="<%=style%>"></div>
              <div class="mm_list_textdevice"><%=obj.get("name")%></div>
              <%
                String ip = obj.get("host_ip").toString().trim();
              %>>
              <div class="mm_list_text" style="font-size: 14px;" title="<%=ip%>">
                <%

                  out.println(ip);
                %>
              </div>
              <span class="<%=status%>"><%=font%></span>
            </div>
          </div>
          <div class="mm_list_option">
            <style>
              .bg,.bged{
                margin-left: 5px;
              }
              .mm_list_option{
                width: 175px;
              }
            </style>
            <span class="bg" hostId="<%=obj.get("id")%>" hostname="<%=obj.get("name")%>"></span>
            <span class="mm_nogroup_del" style="float: right;" title="删除班级" onclick="delhost('<%=obj.get("id")%>','<%=obj.get("host_ip")%>')"></span>
          </div>
        </div>

        <%     }

          }//for end
        %>
        <%--<%--%>
        <%--for(int k=0;k<=81;k++){--%>
        <%--%>--%>
        <%--<div class="mm_listout" style="margin-top: 15px;">--%>
        <%--<div class="mm_nogroup_list" style="margin: 0;cursor: default" bingo="none">--%>
        <%--<div class="mm_list_group">--%>
        <%--<div class="mm_list_dp_unkonwnlogo"></div>--%>
        <%--<div class="mm_list_textdevice">aaaaaaaa</div>--%>
        <%--<div class="mm_list_text" style="font-size: 14px;">--%>
        <%--192.168.17.170--%>
        <%--</div>--%>
        <%--<span class="mm_online">在线</span>--%>
        <%--</div>--%>
        <%--</div>--%>
        <%--<div class="mm_list_option">--%>
        <%--<style>--%>
        <%--.bg,.bged{--%>
        <%--margin-left: 5px;--%>
        <%--}--%>
        <%--.mm_list_option{--%>
        <%--width: 175px;--%>
        <%--}--%>
        <%--</style>--%>
        <%--<span class="bg" hostId="1" hostname="aaa"></span>--%>
        <%--<span class="mm_nogroup_del" style="float: right;" title="删除班级" onclick="delhost('1','192.168.17.170')"></span>--%>
        <%--</div>--%>
        <%--</div>--%>
        <%--<%--%>
        <%--}--%>
        <%--%>--%>
        <%
          if(Integer.parseInt(pageCount)>1){
        %>
        <div class="pages floatleft">
          <link href="${pageContext.request.contextPath}/css/frontend/hpager.css" rel="stylesheet"
                type="text/css"/>
          <%
            Integer currentpage = Integer.parseInt(request.getParameter("currentPage"));
            Pager pager = new Pager(Integer.parseInt(itemsCount), currentpage, "<span class='pages_prevpage'></span>", "<span class='pages_nextpage'></span>", "", "", false);
            String pagers = pager.run();
          %>
          <div id="linkpage">
            <%=pagers%>
          </div>
        </div>
        <%
          }
        %>
      </div>
      <div class="scroll_ymove">
        <div class="scroll_y" unorbind="unbind"></div>
      </div>
      <div class="scroll_xmove">
        <div class="scroll_x" unorbind="unbind"></div>
      </div>
    </div>
  </div>
  <div class="foot">
    <jsp:include page="../footer.jsp"></jsp:include>
  </div>
  <input type="text" style="display: none" urlhead="${pageContext.request.contextPath}" id="paramhidden">

  <%--<form id="deviceForm" action="${pageContext.request.contextPath}/device/Device_deviceList.do" method="POST">--%>
    <%--<input type="hidden" id="hostId" name="hostId"/>--%>
    <%--<input type="hidden" id="hostIp" name="hostIp"/>--%>
    <%--<input type="hidden" id="hostName" name="hostName">--%>
    <%--<input type="hidden" id="goBackUrl" name="goBackUrl"--%>
           <%--value="${pageContext.request.contextPath}/host/Host_hostManagement.do"/>--%>
  <%--</form>--%>
</div>
<form target="_blank" method="GET" id="nvrForm">
  <input type="hidden" value="honghe2015" name="username" id="userName"/>
  <input type="hidden" value="2015honghe" name="password" id="password"/>
</form>

</body>
</html>
<script>

  var urlhead;
  $(function () {
    var vipt_videowid=$(".mm").width()
    $("#mm_nas_videonogroup .scrollson").width(vipt_videowid)
    $("#mm_nas_videonogroup").height($(".mm").height()*0.93)

    $("#mm_nas_videonogroup .scrollson").mouseover(function(){
      $("#whichscroll").val($.trim($(this).parent().attr("id")))
      if ((navigator.userAgent.match(/(iPhone|Android|iPad)/i))){
        var scrollfathter1=document.getElementById($.trim($(this).parent().attr("id")));
        scrollfathter1.addEventListener("touchstart", touchStart, false);
        scrollfathter1.addEventListener("touchmove", touchMove, false);
        scrollfathter1.addEventListener("touchend", touchEnd, false);
      }
    })
    scroll_y("mm_nas_videonogroup","scrollson","scroll_y","scroll_ymove","scroll_x","scroll_xmove","","wheely","")
    $("#mm_nas_videonogroup .scrollson").css("margin-top","0")
    $("#mm_nas_videonogroup .scroll_y").css("top","0")

    $(".mm_nogroup_list_machineset").click(function(){
      setTimeout(function(){
        $(".xubox_dialog").parents(".xubox_main").css({"width":"310px"})
        $(".xubox_form").width(257)
      },0)

    });
    urlhead = $("#paramhidden").attr("urlhead");
  });

  $(".mm_nogroup_list_machineset").click(function () {
    var selectId = $(this).attr("hostId");
    var hostIpaddr = $(this).attr("hostIpaddr");
    var url = urlhead + "/host/Host_updateHost.do";
    layer.prompt({title: '输入班级名称', val: "", length: 20}, function (name) {
      name = $.trim(name);
      if (name != "") {
        $.post(url, {'hostId': selectId,hostIpaddr:hostIpaddr, 'hostName': name}, function (data) {
          layer.msg(data.msg);
          if (data.success == true) {
            window.parent.location.reload();
          }
        }, 'json');
      }
      else {
        var val = $(".xubox_title em").text("班级名称不能为空!");
        $("#xubox_prompt").val("").focus();
      }
    });
  });
  function delhost(hostid, hostIpaddr) {
    var delUrl = urlhead + "/host/Host_delHost.do";
    $.layer({
      shade: [0.5, '#000'],
      area:['310px','129px'],
      dialog: {
        msg: '确认删除该未分组班级吗？',
        btns: 2,
        type: 4,
        btn: ['确认', '取消'],

        yes: function () {
          $.post(delUrl, {'hostId': hostid, 'hostIpaddr': hostIpaddr}, function (data) {

            if (data.success == true) {
              window.location.reload();
            }
            else {
              layer.alert(data.msg);
            }
          }, 'json')
        }, no: function () {

        }
      }
    });
  }
  $(".moveHosts").click(function () {
    $("#perfectscrollval").attr("which","move")
    var selectId = "";
    var hostlist = $(".mm_list_option .bged");
    if (hostlist.length > 0) {

      $.layer({
        type: 2,
        title: '移动班级',
        shadeClose: true,
        maxmin: false,
        fix: false,
        area: ['300', '395'],
        iframe: {
          src: urlhead + '/host/Host_hostTree.do?hostTreeFlag=',
          scrolling: 'no'
        }
      });
    }
    else {
      layer.msg("未选择班级！")
    }

  });
  function moveHosts(groupId, groupIdOld) {

    var selectId = "";
    var hostlist = $(".mm_list_option .bged");
    var moveurl = urlhead + "/host/Host_moveHostRelation.do";
    //alert(hostlist.length);
    if (hostlist.length > 0) {
      for (var hf = 0; hf < hostlist.length; hf++) {
        selectId = selectId + hostlist.eq(hf).attr("hostId") + ",";
        //alert("kankan"+selectId);
      }
      $.post(moveurl, {"hostIds": selectId, "groupId": groupId, "hostgroupIdOld": groupIdOld}, function (data) {
        layer.msg(data.msg);
        if (data.success == true) {
          // setTimeout(function(){
          location.reload();
          // });
        }

      }, "json")
    }
    else {
      layer.msg("移动失败！")
    }
  }
  //    $(".refreshpage").click(function () {
  //        window.location.reload();
  //    });
  function layerclose() {
    layer.closeAll();
  }
  function device(hostId, hostIp, hostName) {
//    $("#hostId").attr("value", hostId);
//    $("#hostIp").attr("value", hostIp);
//    $("#hostName").attr("value", hostName);
//    $("#deviceForm").submit();
    var hostNameEncode = encodeURI(hostName);
    var goBackUrl = $("#goBackUrl").attr("value");
    location.href = "${pageContext.request.contextPath}/device/Device_deviceList.do?hostId="+hostId+"&hostName="+hostNameEncode+"&goBackUrl="+goBackUrl+"&hostIp="+hostIp+"&type=2";
  }
  function deviceOffLine() {
    layer.msg("主机离线，无法查看附属设备！");
  }
  function config(hostId, hostIp) {
    $.layer({
      type: 2,
      title: 'NVR设置',
      shadeClose: true,
      maxmin: false,
      fix: false,
      iframe: {
        src: urlhead + '/host/Host_hostConfig.do?hostId=' + hostId,
        scrolling: 'no'
      }
    });
  }
  //    function nasSetting() {
  //        location.href = urlhead + "/host/Host_loadHostAndNasByHostgroup.do";
  //    }
  function nvrView(ip, userName, password) {
    var nvrForm = document.getElementById("nvrForm");
    nvrForm.action = "http://" + ip + "/Custom/userCheck/";
//        $("#userName").attr("value", userName);
//        $("#password").attr("value", password);
    nvrForm.submit();
  }
  var totalpagesize = "<%=pageCount%>";
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
  $(document).keydown(function(event){
    //判断当event.keyCode 为37时（即左方面键），执行函数to_left();
    //判断当event.keyCode 为39时（即右方面键），执行函数to_right();
    if(event.ctrlKey || event.which == 13){
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

</script>