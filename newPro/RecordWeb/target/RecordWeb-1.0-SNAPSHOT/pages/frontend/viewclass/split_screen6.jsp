<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
    <meta HTTP-EQUIV="pragma" CONTENT="no-cache">
    <meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
    <meta HTTP-EQUIV="expires" CONTENT="0">
    <title>监看画面</title>
    <link href="${pageContext.request.contextPath}/css/frontend/index.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/split_screen_p.css">
    <script src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
    <script src="${pageContext.request.contextPath}/js/lb_common/split_screen.js"></script>
</head>
<%
    List<Object[]> deviceList = (List<Object[]>) request.getAttribute("deviceList");
    String hostName = (String)request.getAttribute("hostName");
    String hostIp = (String)request.getAttribute("hostIp");
    String hostId = request.getAttribute("hostId").toString();
    Map mediaUrlList = (Map)request.getAttribute("mediaUrlList");
    String dspecid = request.getAttribute("dspecid").toString();
    Object[] device = deviceList.get(0);
    Object[] device1 = deviceList.get(1);
    Object[] device2 = deviceList.get(2);
    Object[] device3 = deviceList.get(3);
    Object[] device4 = deviceList.get(4);
    Object[] device5 = deviceList.get(5);
    String currentPage = (String)request.getAttribute("currentPage");
%>
<body>
<div class="public">
            <jsp:include page="/pages/frontend/lb_header.jsp"></jsp:include>
	   <div class="mm" style='height:100%;background:#707681;'>
	    <div class="mm_head" style="background:#dee0e4">
	        <a href="javascript:gotoViewClass(<%=currentPage%>);"><span class="user_head_option"><span class="user_head_return_icon"></span ><span style="float: left;margin-left:10px;">返回</span></span></a>
            <span class="user_head_option"><%=hostName%></span>
        </div>
	    <div class="split_screen_p">
            <div class="split_screen_tetop">
                <div class="reen_vide_p">
                    <div class="screen_video_stdt"  style="border:1px solid #000;height:100%;">
                        <object  CLASSID="CLSID:3a22176d-118f-4185-9653-cdea558a6df8" name="ScriptableObject" id="ScriptableObject1" title="<%=device[1]%>---<%=device[5]%>---<%=device[6]%>---<%=mediaUrlList.get(device[1]).toString()%>"
                                 url="<%=device[6]%>" WIDTH=100% HEIGHT=100%></object>
                    </div>
                </div>
                <div class="reen_vide_b">
                    <div class="screen_video_stdtay">
                        <object  CLASSID="CLSID:3a22176d-118f-4185-9653-cdea558a6df8" name="ScriptableObject" id="ScriptableObject2" title="<%=device1[1]%>---<%=device1[5]%>---<%=device1[6]%>---<%=mediaUrlList.get(device1[1]).toString()%>"
                                 url="<%=device1[6]%>" WIDTH=100% HEIGHT=100%>
                        </object>
                    </div>
                    <div class="screen_video_stdtay">
                        <object  CLASSID="CLSID:3a22176d-118f-4185-9653-cdea558a6df8" name="ScriptableObject" id="ScriptableObject3" title="<%=device2[1]%>---<%=device2[5]%>---<%=device2[6]%>---<%=mediaUrlList.get(device2[1]).toString()%>"
                                 url="<%=device2[6]%>" WIDTH=100% HEIGHT=100%>
                        </object>
                    </div>  
                </div>
            </div>
            <div class="split_screen_tetop">
                <div class="reen_vide_p">
                    <div class="screen_video_stdt">
                        <div class="screen_video_stdtam1">
                            <object  CLASSID="CLSID:3a22176d-118f-4185-9653-cdea558a6df8" name="ScriptableObject" id="ScriptableObject4" title="<%=device3[1]%>---<%=device3[5]%>---<%=device3[6]%>---<%=mediaUrlList.get(device3[1]).toString()%>"
                                     url="<%=device3[6]%>" WIDTH=100% HEIGHT=100%>
                            </object>
                        </div>
                        <div class="screen_video_stdtam2">
                            <object  CLASSID="CLSID:3a22176d-118f-4185-9653-cdea558a6df8" name="ScriptableObject" id="ScriptableObject5" title="<%=device4[1]%>---<%=device4[5]%>---<%=device4[6]%>---<%=mediaUrlList.get(device4[1]).toString()%>"
                                     url="<%=device4[6]%>" WIDTH=100% HEIGHT=100%>
                            </object>
                        </div>
                    </div>
                </div>
                <div class="reen_vide_b">
                    <div class="screen_video_stdtay">
                        <object  CLASSID="CLSID:3a22176d-118f-4185-9653-cdea558a6df8" name="ScriptableObject" id="ScriptableObject6" title="<%=device5[1]%>---<%=device5[5]%>---<%=device5[6]%>---<%=mediaUrlList.get(device5[1]).toString()%>"
                                 url="<%=device5[6]%>" WIDTH=100% HEIGHT=100%>
                        </object>
                    </div>
                </div>
            </div>
        </div>
           <%@include file="../viewclass/websocket.jsp" %>
	    <script type="text/javascript">
                $(".screen_video_stdtay:last").css("margin","0");
                $(".screen_video_stdtay").width(355 * $(".screen_video_stdtay").height() / 199);
                $(".screen_video_stdt").width($(".screen_video_stdtay").width() * 2 +$(".reen_vide_p").width() * 3 / 100);
            $(window).resize(function (){ 
                $(".screen_video_stdtay").width(355 * $(".screen_video_stdtay").height() / 199);
                $(".screen_video_stdt").width($(".screen_video_stdtay").width() * 2 +$(".reen_vide_p").width() * 3 / 100);
                console.log($(document).height())
            })
            
                $(".class_fd_ban").parent().css("position","relative")
                function cameraInfo(deviceId)
                {
                    var path = "${pageContext.request.contextPath}/viewclass/Viewclass_cameraInfo.do?deviceId=" + deviceId;
                    $.layer({
                        isClose: false,
                        type: 2,
                        title: '预览镜头',
                        shadeClose: true,
                        maxmin: false,
                        fix: false,
                        area:['1000px','700px'],
                        iframe: {
                            src: path
                        },
                        close: function (index) {
                            this.isClose = true;
                        },
                        end: function (index) {
                            this.isClose = true;
                        }
                    });
                }

                function cameraInfobig(deviceId)
                {
                    var path = "${pageContext.request.contextPath}/viewclass/Viewclass_cameraInfo.do?deviceId=" + deviceId;
                    $.layer({
                        isClose: false,
                        type: 2,
                        title: '预览镜头',
                        shadeClose: true,
                        maxmin: false,
                        fix: false,
                        area:['1300px','750px'],
                        iframe: {
                            src: path
                        },
                        close: function (index) {
                            this.isClose = true;
                        },
                        end: function (index) {
                            this.isClose = true;
                        }
                    });
                }

                var command = new Command();
                var hostip = "<%=hostIp%>";
                var hostid = "<%=hostId%>";
                var dspecid = "<%=dspecid%>"
                function ScriptableObject1::OnPtzControl(v)
                {
                    PTZcontrol(v,"<%=device[4]%>")
                };
                function ScriptableObject2::OnPtzControl(v)
                {
                    PTZcontrol(v,"<%=device1[4]%>")
                };
                function ScriptableObject3::OnPtzControl(v)
                {
                    PTZcontrol(v,"<%=device2[4]%>")
                };
                function ScriptableObject4::OnPtzControl(v)
                {
                    PTZcontrol(v,"<%=device3[4]%>")
                };
                function ScriptableObject5::OnPtzControl(v)
                {
                    PTZcontrol(v,"<%=device4[4]%>")
                };
                function ScriptableObject6::OnPtzControl(v)
                {
                    PTZcontrol(v,"<%=device4[4]%>")
                };
        </script>
	    </div>
        <!-- <div class="foot">
            <div class="foot_center">&copy;2003-2015HiteVision.All Rights Reserved V2.0.3.20160301 (11055)</div>
        </div> -->

</div>
</body>
</html>