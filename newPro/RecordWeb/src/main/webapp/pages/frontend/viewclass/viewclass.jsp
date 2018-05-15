<%@ page import="com.honghe.recordweb.service.frontend.user.UserService" %>
<%@ page import="com.honghe.recordweb.util.ConfigureUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="${pageContext.request.contextPath}">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
    <title>巡课 | 集控平台</title>
    <%@include file="../dmanager/websocket.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cxmenu.css"/>
    <script src="${pageContext.request.contextPath}/js/lb_common/jquery.cookie.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/lb_common/screendetailforrecord.js"></script>
    <script src="${pageContext.request.contextPath}/js/lb_common/selectmore_xk.js"></script>
    <%--<script src="${pageContext.request.contextPath}/js/lb_common/tree.js"></script>--%>
    <script src="${pageContext.request.contextPath}/js/lb_common/perfect-scrollbar.with-mousewheel.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/lb_common/prettify.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/lb_common/tree.cxmenu.js"></script>


    <!--
        作者：474569696@qq.com
        时间：2014-10-07
        描述：js引用顺序不可以变,会导致select模拟失效
    -->
    <style>
        .hostoverflow {
            float: left;
            text-overflow: ellipsis;
            position: relative;
           /* overflow: hidden;*/
            /*width: 200px*/
        }

        .tree_titleb {
            display: none;
        }
        @media only screen and (max-width: 1280px) and (max-height: 800px) {
            .public{
                overflow: auto !important;
            }
        }
    </style>

    <script>
        //alert("hei")
//        $.cookie('screen', $.cookie("screen"), {path: '/'});
//        $.cookie('curPage', 1, {path: '/'});
    </script>
</head>


<body>
<div class="public">

    <jsp:include page="/pages/frontend/lb_header.jsp"></jsp:include>
    <%
        // Map userroleMap = SessionManager.get(request.getSession(), SessionManager.Key.Authority);
        // boolean isSeting = userroleMap.containsKey(Authority.Anthority_Set_SysSetting.name());
//            boolean isSeting = Boolean.parseBoolean(((User)SessionManager.get(request.getSession(), SessionManager.Key.USER)).getUser_salt());
        User curruser = (User) SessionManager.get(request.getSession(), SessionManager.Key.USER);
        Integer uid = curruser.getUserId();
        String system = "dmanager";
        Boolean isSeting = UserService.authCheck(uid.toString(), "syssetting:retrieve", system);
        String currentPage = request.getAttribute("currentPage").toString();
    %>
    <div class="public_left22_xk" id="showhidden">
        <input type="hidden" value="" id="selected_host" ip="">

        <div class="equipment" opening="no">
            教室设备
            <%--<span class="refrash"></span>--%>
            <%
                if (!ConfigureUtil.isOnlyHhrec()) {
            %>
            <div class="public_head_changesystem"></div>
            <%
                }
            %>

        </div>
        <%
            if (!ConfigureUtil.isOnlyHhrec()) {
        %>
        <jsp:include page="/pages/frontend/equipmentlist_fragment.jsp"></jsp:include>
        <%
            }
        %>
        <div id="otp_vedeoabout_rvideo" style="overflow-y: hidden;width: 100% !important;">
            <div class='contentr'>
                <div class="tree">
                    <%--<a href="javascript:void(0)" class="tree_titleb tree_titleb_open">所有设备</a>--%>
                    <div class="public_left">
                        ${groupTree}
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        var curClass = "";
        var curTeacher = "";
        var curSubject = "";
        var curUnit = "";
        var hostIdStr = "";
        //离线设备变灰
        $(".offlineshow").prev("span").css("color", "#808080")
        function change_size() {
            var width = parseInt($("#Width").val());
            var height = parseInt($("#Height").val());
            $("#pingbi").height($(".xk_option").height())
            if (!width || isNaN(width)) {
                width = 260;
            }
            if (!height || isNaN(height)) {
                height = 654;
            }
            $("#otp_vedeoabout_rvideo").width(width).height(height);

            // update perfect scrollbar
            $('#otp_vedeoabout_rvideo').perfectScrollbar('update');
        }
        var hostIds = []
        $(function () {

            $('#left_nav_ul').cxMenu();
            $('#left_nav_ul a').click(function(){
                $.cookie('curPage', 1, {path: '/'});
                hostIds.splice(0,hostIds.length)
                $(this).parent().find('#spanhost').each(function(){
                    hostIds.push($(this).attr('hostid'))
                })
                hostIdStr = hostIds.join(",");

                var viewClassCameraName = $.cookie("camera");
                console.log(hostIdStr)

                videoShow(viewClassCameraName,hostIds)
            })

            $("#pingbi").height($(".xk_option").height())
            $('#otp_vedeoabout_rvideo').perfectScrollbar();
        });
        $(function () {
            prettyPrint();
        });
        $(document).on("mouseover",".tree_contenta",function(ev){
            // 如果设备离线, 则不显示
            if($(this).find('.hostoverflow').find(".offlineshow").text()=="(离线)") {
                $(this).find("span.video_screen").hide();
                $(this).find("span.split_screen").hide();
            } else {
                $(this).find("span.video_screen").css('display','inline-block');
                $(this).find("span.split_screen").css('display','inline-block');
            }
            var oEvent=ev||event;
            if(oEvent.clientY > 380){
                $(this).find(".hostoverflow").find(".add_class_main_background").css('margin-top','-105px');
            }else{
                $(this).find(".hostoverflow").find(".add_class_main_background").css('margin-top','33px');
            }
            $(this).find(".hostoverflow").find(".add_class_main_background").show();
        })
        $(document).on("mouseleave",".tree_contenta",function(){
            $(this).find("span.video_screen").hide();
            $(this).find("span.split_screen").hide();
//            $(this).find(".hostoverflow").find(".add_class_main_background").remove();
            $(this).find(".hostoverflow").find(".add_class_main_background").hide();
        })
    </script>

    <div class="public_right">
        <div class="public_right_center">
            <div id="pingbi"
                 style="position: absolute;display:none;right:54px;height:40px;width: 80px;background:#e1e3e6;z-index: 1000"></div>
            <div class="xk_option">
                <div class="xk_options">
                    <%--<%--%>
                    <%--if (userroleMap.containsKey(Authority.Anthority_Use_Power.name())) {--%>
                    <%--%>--%>
                            <span class="xk_options_open" id="turnon" onclick="wakeup()"><span
                                    class="xk_options_openicon" id="turnon_img"></span><span
                                    class="xk_options_text">开机</span></span>
                            <span class="xk_options_close" id="turnoff" onclick="shutdown()"><span
                                    class="xk_options_closeicon" id="turnoff_img"></span><span
                                    class="xk_options_text">关机</span></span>
                    <%--<%--%>
                    <%--}--%>
                    <%--if (userroleMap.containsKey(Authority.Anthority_Use_Video.name())) {--%>
                    <%--%>--%>
                            <span class="xk_options_recording" onclick="recording()" id="record_vedio"><span
                                    class="xk_options_recordingicon" id="record_vedio_img"></span><span
                                    class="xk_options_text">录制</span></span>
                            <span class="xk_options_stoprecording" onclick="stoprecord()" id="stop_record_vedio"><span
                                    class="xk_options_stoprecordingicon" id="stop_record_vedio_img"></span><span
                                    class="xk_options_text">停止录制</span></span>
                    <span class="warn" style="display: block;float: left;position: absolute;right:-260px;margin-top: 10px;color:#f00"></span>

                    <%--<%--%>
                    <%--}--%>
                    <%--%>--%>


                </div>
                <div id="classmessage" class="xk_video_group_warn" style="float:left;width: 190px;"></div>
                <%--<%--%>
                <%--if (groupTreeList.size() > 0) {--%>
                <%--%>--%>
                <div class="xk_stage_change" title="切换" changing="no">
                    <div class="xk_stage">
                        <div id="camera_stu_tea" class="xk_stage_st" title="教师学生" onclick="change_camera('教师学生',hostIds)"></div>
                        <div id="camera_stu" class="xk_stage_student" title="学生全景"
                             onclick="change_camera('学生全景',hostIds)"></div>
                        <div id="camera_tea" class="xk_stage_teacher" title="教师全景"
                             onclick="change_camera('教师全景',hostIds)"></div>
                        <div id="camera_stu_all" class="xk_stage_studentunique" title="学生特写"
                             onclick="change_camera('学生特写',hostIds)"></div>
                        <div id="camera_tea_all" class="xk_stage_teacherunique" title="教师特写"
                             onclick="change_camera('教师特写',hostIds)"></div>
                    </div>
                </div>
                <div class="xk_fournine">
                    <div class="xk_four" title="四屏显示" onclick="change_screen(2,hostIds)"></div>
                    <div class="xk_nine" title="九屏显示" onclick="change_screen(3,hostIds)"></div>
                </div>
                <%--<%--%>
                <%--}--%>
                <%--%>--%>

            </div>

            <%--/viewclass/Viewclass_viewClass4.do?groupid=<%=groupTreeList.get(0).get("group_id").toString()%>&pagesize=2&currentPage=1--%>
            <iframe style="z-index: 1" id="go" src="" frameborder="0" class="iframe" scrolling="no"></iframe>

            <input type="checkbox" id="select_auto" start="display:none"/>
            <%--<%--%>
            <%--if (groupTreeList.size() > 0) {--%>
            <%--%>--%>
            <div class="check_head"
                 style="color:white;margin-top: -25px;text-indent: 0px;position: absolute;bottom: 20px;right:180px">
                <style>
                    .bg {
                        background: url(${pageContext.request.contextPath}/image/frontend/checkbox.png) 0 -1px no-repeat;
                        height: 17px;
                        margin-right: 8px;
                        width: 35px;
                    }
                    .bged {
                        background: url(${pageContext.request.contextPath}/image/frontend/checkbox.png) 0 -35px no-repeat;
                        height: 17px;
                        margin-right: 8px;
                        width: 35px;
                    }
                </style>
                <label class="check_head_label" for="select_auto" style="display: none">
                    <div class="bg" style="margin-top: 2px"></div>
                    自动轮巡</label>
            </div>
            <div class="fullscreen" full="false"
                 style="position: absolute;bottom: 20px;right:10px"></div>
            <%--<%}%>--%>
        </div>

    </div>
    <style>
        .foot {
            border-top: 1px solid #e6e5e5;
            background: #fff;
            /*float: left;*/
            height: 3%;
            min-width: 240px;
            position: absolute;
            bottom: 0;
            left: 0px;
            width: 22%;
        }

        .foot_center {
            color: #706c6c;
            font-size: 12px;
            line-height: 100%;
            height: 100%;
            left: 0;
            margin-left: -0;
            position: relative;
            text-align: center;
            width: 100%;
        }
    </style>
    <div class="foot">
        <%@ page contentType="text/html;charset=UTF-8" language="java" %>

        <div class="foot_center floatleft">&copy;2013-2018HongHe.All Rights Reserved</div>
    </div>
    <script>
        $(function () {
            $.ajaxSetup({
                contentType: "application/x-www-form-urlencoded;charset=utf-8",
                complete: function (XMLHttpRequest, textStatus) {
                    var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus"); //通过XMLHttpRequest取得响应头，sessionstatus，
                    if (sessionstatus == "timeout") {
                        //如果超时就处理 ，指定要跳转的页面
                        window.location.href = "${pageContext.request.contextPath}";
                    }
                }
            });
        });
        //点击空白区域，隐藏select模拟
        $(document).bind("click", function (e) {
            var target = $(e.target);//获取点击时间
            if (target.closest(".public_setting").length == 0) {
                $(".public_setting_menu").hide();
                $(".public_setting_modifypassword").hide();
            }
        })
    </script>
</div>
</div>
</body>
<script>
    // 当页面加载时启动定时器
    $(function() {
        chat();
        setInterval(chat,1000*60);
    });

    function chat(){
        $(".public_left ul li a").each(function(index,value) {
            var host = $(this).find("span").attr("hostid");
            if(host!=undefined){
                $.post(
                        "${pageContext.request.contextPath}/viewclass/Viewclass_findCulum.do",
                        {"hostid": host},
                        function(jsonObj) {
                            curClass = (jsonObj.class==''?'班级:'+'无':'班级:'+jsonObj.class);
                            curSubject = (jsonObj.subject==''?'科目:'+'无':'科目:'+jsonObj.subject);
                            curTeacher = (jsonObj.teacher==''?'老师:'+'无':'老师:'+jsonObj.teacher);
                            curUnit = (jsonObj.unit==''?'教学单位:'+'无':'教学单位:'+jsonObj.unit);
                            if(jsonObj.subject==''|| jsonObj.subject == null){
                                $("#classState"+jsonObj.hostid).css("background","#bdbdbd").html("无课");
                            }else{
                                $("#classState"+jsonObj.hostid).css("background","#28b779").html("有课");
                            }
                            $("#hostoverflow"+jsonObj.hostid).find(".add_class_main_background").remove();
                            var add_background_c =  "<div class='add_class_main_background'> " +
                                    "<span id='curClass' title='" + curClass + "'>" + curClass + " </span> " +
                                    "<span id='curSubject' title='" + curSubject + "'> " + curSubject + " </span> " +
                                    "<span id='curTeacher' title='" + curTeacher + "'> " + curTeacher + " </span> " +
                                    "<span id='curUnit' title='" + curUnit + "'> " + curUnit + " </span> " +
                                    "</div>";
                            // console.log("#hostoverflow"+jsonObj.hostid+"======================"+add_background_c)
                            $("#hostoverflow"+jsonObj.hostid).append(add_background_c)
                        },
                        'json'
                );
            }

        })

    }
    function seticon4() {
        //JS判断访问设备(userAgent)加载不同页面。代码如下：
        var sUserAgent = navigator.userAgent;
        var isWin = (navigator.platform === "Win32") || (navigator.platform === "Windows");
        //非全屏状态下效果
        var iframewidth = $(".iframe").width();
        var halfwidth = iframewidth / 2;
        var iframeheight = $(".iframe").height();
        //iframe去除分页和文本框后的高度
        var halfheight = iframeheight * 0.93 / 2 - 25 - 10;
        //计算16/9的比例
        var heicompare = halfwidth * 9 / 16;
        if (heicompare > halfheight) {
            //如果宽度的比例值高度大于iframe可用区域高度，则把高度默认为halfheight
            var widcompare = halfheight * 16 / 9;
            //alert(widcompare+"--"+halfwidth)
            if (widcompare < halfwidth) {
                var marginleft = (halfwidth - widcompare) / 3;
                //alert((iframeheight*0.90/2-25-30)+"---"+(halfheight+25))

                $("#go").contents().find(".allarea,.xk_video").height(halfheight + 25);
                $("#go").contents().find(".allarea,.xk_video,.xk_video_text,.xk_video_twofour").width(widcompare);
                $("#go").contents().find(".allarea").css({"margin-left": marginleft * 2 + "px", "margin-top": "5px"})
                $("#go").contents().find(".twofour").attr("height", halfheight);
                $("#go").contents().find(".twoimg").css("height", halfheight + "px");
                $("#go").contents().find(".xk_video_twofour,.xk_video_twofourobj").height(halfheight);
                //教师学生全景模式
                $("#go").contents().find(".xk_video_twofourobj").width(widcompare / 2 - 0.01);
                var twofourobjheight = $("#go").contents().find(".xk_video_twofourobj").height();
                var twofourobjwidth = $("#go").contents().find(".xk_video_twofourobj").width();
                var twofoursixtweenwidth = twofourobjheight * 16 / 9;
                if (twofoursixtweenwidth > twofourobjwidth) {
                    var twofourxkvideoheight = twofourobjwidth * 9 / 16;
                    //alert(twofourxkvideoheight+"---"+twofoursixtweenwidth)
                    var twofourmargintop = (twofourobjheight - twofourxkvideoheight) / 2;
                    $("#go").contents().find(".fourfourimg").css("height", twofourxkvideoheight + "px");
                    $("#go").contents().find(".fourfourimg").css("margin-top", twofourmargintop + "px");
                    $("#go").contents().find(".fourfour").css("height", twofourxkvideoheight + "px");
                    $("#go").contents().find(".fourfour").css("width", "100%")
                    $("#go").contents().find(".fourfour").css("margin-top", twofourmargintop + "px")

                }
            } else {
                //alert("从新计算")
            }
        } else {
            //如果宽度的比例值高度小于iframe可用区域高度，则把宽度默认为计算值heicompare
            $("#go").contents().find(".allarea,.xk_video").height(heicompare + 25);
            $("#go").contents().find(".allarea,.xk_video,.xk_video_text,.xk_video_twofour").width(halfwidth - 10);
            $("#go").contents().find(".allarea").css({"margin-top": "5px", "margin-left": "5px"})
            $("#go").contents().find(".twofour").attr("height", heicompare);
            $("#go").contents().find(".twoimg").css("height", heicompare + "px");
            $("#go").contents().find(".xk_video_twofour,.xk_video_twofourobj").height(heicompare);
            //教师学生全景模式
            $("#go").contents().find(".xk_video_twofourobj").width((halfwidth - 10) / 2);
            var twofourobjheight = $("#go").contents().find(".xk_video_twofourobj").height();
            var twofourobjwidth = $("#go").contents().find(".xk_video_twofourobj").width();
            var twofoursixtweenwidth = twofourobjheight * 16 / 9;
            if (twofoursixtweenwidth > twofourobjwidth) {
                var twofourxkvideoheight = twofourobjwidth * 9 / 16;
                //alert(twofourxkvideoheight+"---"+twofoursixtweenwidth)
                var twofourmargintop = (twofourobjheight - twofourxkvideoheight) / 2;
                $("#go").contents().find(".fourfourimg").css("height", twofourxkvideoheight + "px");
                $("#go").contents().find(".fourfourimg").css("margin-top", twofourmargintop + "px");
                $("#go").contents().find(".fourfour").css("height", twofourxkvideoheight + "px");
                $("#go").contents().find(".fourfour").css("width", "100%")
                $("#go").contents().find(".fourfour").css("margin-top", twofourmargintop + "px")
            }
        }
    }
    function seticonval(){
        //JS判断访问设备(userAgent)加载不同页面。代码如下：
        var sUserAgent = navigator.userAgent;
        var isWin = (navigator.platform === "Win32") || (navigator.platform === "Windows");
        //非全屏状态下效果
        var iframewidth = $(".iframe").width();
        var halfwidth = iframewidth / 3

        var iframeheight = $(".iframe").height();
        //iframe去除分页和文本框后的高度
        var halfheight = iframeheight * 0.93 / 3 - 19 - 10;
        //计算16/9的比例
        var heicompare = halfwidth * 9 / 16;
        if (heicompare > halfheight) {
            //如果宽度的比例值高度大于iframe可用区域高度，则把高度默认为halfheight
            var widcompare = halfheight * 16 / 9;
            //alert(widcompare+"--"+halfwidth)
            if (widcompare < halfwidth) {
                var marginleft = (halfwidth - widcompare) / 4;
                $("#go").contents().find(".allarea,.xk_video_nine").height(halfheight + 19);
                $("#go").contents().find(".allarea,.xk_video_nine,.xk_video_text_nine,.xk_video_twonine").width(widcompare);
                $("#go").contents().find(".allarea").css({"margin-left": marginleft * 3 + "px", "margin-top": "5px"})
                $("#go").contents().find(".threenine").attr("height", halfheight);
                $("#go").contents().find(".twoimg").css("height", halfheight + "px");
                $("#go").contents().find(".xk_video_twonine,.xk_video_twonineobj").height(halfheight);
                //教师学生全景模式
                $("#go").contents().find(".xk_video_twonineobj").width(widcompare / 2 - 0.01);
                var twofourobjheight = $("#go").contents().find(".xk_video_twonineobj").height();
                var twofourobjwidth = $("#go").contents().find(".xk_video_twonineobj").width();
                var twofoursixtweenwidth = twofourobjheight * 16 / 9;
                if (twofoursixtweenwidth > twofourobjwidth) {
                    var twofourxkvideoheight = twofourobjwidth * 9 / 16;
                    var twofourmargintop = (twofourobjheight - twofourxkvideoheight) / 2;
                    $("#go").contents().find(".threetwonine").css("height", twofourxkvideoheight + "px");
                    $("#go").contents().find(".threetwonine").css("width", "100%")
                    $("#go").contents().find(".threetwonine").css("margin-top", twofourmargintop + "px")
                    $("#go").contents().find(".fourfourimg").css("height", twofourxkvideoheight + "px")
                    //$(".fourfourimg").css("margin-top",twofourmargintop+"px")
                }
            } else {
                //alert("从新计算")
            }
        } else {
            //如果宽度的比例值高度小于iframe可用区域高度，则把宽度默认为计算值halfwidth
            $("#go").contents().find(".allarea,.xk_video_nine").height(heicompare + 19);
            $("#go").contents().find(".allarea,.xk_video_nine,.xk_video_text_nine,.xk_video_twonine").width(halfwidth - 10);
            $("#go").contents().find(".allarea").css({"margin-top": "5px", "margin-left": "3px"})
            $("#go").contents().find(".threenine").attr("height", heicompare);
            $("#go").contents().find(".twoimg").css("height", heicompare + "px");
            $("#go").contents().find(".xk_video_twonine,.xk_video_twonineobj").height(heicompare);
            //教师学生全景模式
            $("#go").contents().find(".xk_video_twonineobj").width((halfwidth - 10) / 2);
            var twofourobjheight = $("#go").contents().find(".xk_video_twonineobj").height();
            var twofourobjwidth = $("#go").contents().find(".xk_video_twonineobj").width();
            var twofoursixtweenwidth = twofourobjheight * 16 / 9;
            if (twofoursixtweenwidth > twofourobjwidth) {
                var twofourxkvideoheight = twofourobjwidth * 9 / 16;
                var twofourmargintop = (twofourobjheight - twofourxkvideoheight) / 2;
                $("#go").contents().find(".threetwonine").css("height", twofourxkvideoheight + "px");
                $("#go").contents().find(".threetwonine").css("width", "100%")
                $("#go").contents().find(".threetwonine").css("margin-top", twofourmargintop + "px")
                $("#go").contents().find(".fourfourimg").css("height", twofourxkvideoheight + "px")
                //$(".fourfourimg").css("margin-top",twofourmargintop+"px")
            }
        }
    }
    if (screen.height == 900) {
        $(".xk_options").css("margin", "5px 0px 0px 25px")
        $(".xk_stage_change,.xk_fournine").css("margin-top", "10px")
    }
    window.onscroll = function () {
//    var top = document.documentElement.scrollTop || document.body.scrollTop;
//    if(top<50){
        var names = $("iframe").contents().find("object");
        for (var i = 0; i < names.length; i++) {
            var url = names[i].getAttribute("url");
            var id = names[i].getAttribute("id");
            var obj = document.getElementById(id);
            try {
                names[i].sltFlush();
            } catch (e) {

            }
        }
//    }
    };

    //自动巡课定时器
    var timer = "";
    function start_timer() {
        timer = setInterval(handler, 40000);

    }
    function clear() {
        if (timer) {
            clearInterval(timer);
        }
    }
    var nowselectpage =<%=currentPage%>;
    //update by zlj on 2018/04/26
    var handler = function () {
        var src = $("#go").contents().find(".next").children().eq(0).attr("href");
        if ($("#go").contents().find(".yiiPager") != undefined) {

            var iframepage = parseInt($("#go").contents().find(".yiiPager").children("li:last").prev("li").text());
            console.log("iframepage-------------" + iframepage)
            var viewClassCameraName = encodeURI(encodeURI($.cookie("camera")));
            var groupid = $("#group_id").val();
            var nextpage;
//            console.log($("#go").contents().find("div[ip='" + token + "']")+"~~~hostids");
            var hostids = deviceIDByPage();//获取地点树上选中节点下的所有设备，不选则默认根节点下的设备
            var split = $.cookie("screen");
            if (nowselectpage >= iframepage) {//轮巡到最后一页，重新轮巡
                nowselectpage = 1;
                nextpage = 1;
            } else {//轮巡继续
                nowselectpage = nowselectpage + 1;
                nextpage = nowselectpage;
            }

            console.log("================================")
            console.log($.cookie("currentPage") + "=============$.cookie(\"currentPage\")")
            console.log(nextpage + "=============nextpage")
//            console.log(split+"=============split")

            var nextPageHostIds = getNextPageHostIds(split, nextpage, hostids);

//            console.log(hostids+"=============hostids")
//            console.log(nowselectpage+"=============nowselectpage")
//            console.log(nextPageHostIds+"==========nextPageHostIds");
            pollShow(viewClassCameraName, nextPageHostIds, nextpage, hostids);
        }
    }
    //轮询-----获取下页的设备ids
    function getNextPageHostIds(split,nextPage,hostids){
        var hostIdStr =  hostids.split(",");
        var beginIndex = split*split*(nextPage-1);
//        console.log(beginIndex+"===================beginIndex")
        var endIndex = 0;
        if(beginIndex+3>hostids.length){
            endIndex = hostids.length-beginIndex+1;
        }else{
            endIndex = beginIndex+4;
        }
//        console.log(endIndex+"===================endIndex")
        var nextPageHostIds = "";
        for(var i=beginIndex;i<endIndex;i++){
            if(i==(endIndex-1)){
                nextPageHostIds += hostIdStr[i];
            }else{
                nextPageHostIds += hostIdStr[i]+",";
            }
        }
        return nextPageHostIds;
    }

    //轮询---获取总页数
    function getTotalPage(allHostIds){
        var split = $.cookie("screen");//储存的值：2代表2*2，3代表3*3
        var sum = allHostIds.split(",").length;
        var size = split * split;
        if (sum < size) {
            return 1;
        }
        if (sum % size == 0) {
            return sum / size;
        } else {
            return Math.floor(sum / size) + 1;
        }
    }

    //轮询界面显示
    function pollShow(camera,hostIds,nextPage,allHostIds) {
        var split = $.cookie("screen");//储存的值：2代表2*2，3代表3*3
        var hostIdStr;
        if (!split) {
            $.cookie('screen', 2, {path: '/'});
            split = 2;
        }
        var curPage = nextPage;
        var totalPage = getTotalPage(allHostIds);
//        console.log(allHostIds+"-----allHostIds")
//        console.log(totalPage+"-----totalPage")
        if (!curPage) {
            $.cookie('curPage', 1, {path: '/'});
            curPage = 1;
        }
        if(!hostIds || hostIds.length == 0){
            hostIdStr = deviceIDByPage();
        }else{
            hostIdStr = hostIds.split(",");
        }

        // else{
        //     if(split == 2){
        //         hostIdStr = hostIds.slice(0,4).join(',');
        //     }else{
        //         hostIdStr = hostIds.slice(0,9).join(',');
        //     }
        // }


        var src = $("#root_path").val();
        var viewClassCameraName;
        if (camera == null) {
            viewClassCameraName = $.cookie("camera");
            if (!viewClassCameraName) {
                viewClassCameraName = "教师学生";
                $.cookie('camera', "教师学生", {path: '/'});

            }
        } else {
            viewClassCameraName = camera;
        }
        viewClassCameraName = encodeURI(encodeURI(viewClassCameraName));
        src += "/viewclass/Viewclass_viewClass.do?viewClassCameraName=" + viewClassCameraName + "&hostid=" + allHostIds +
            "&pageCount=" + totalPage + "&currentPage=" + curPage + "&split=" + split;
        $("#go").attr("src", src);
        document.getElementById("go").onload = function () {
            refreshSelected();
        }
    }
    //初始化开机按钮、关机按钮、录制按钮、停止录制按钮为初始状态;提示文本置空;班级选中隐藏域置空
    function buttonInit() {
        parent.$("#turnon_img").removeClass("xk_options_openicon_disable").addClass("xk_options_openicon");
        parent.$("#turnon").attr("onclick", "wakeup()");
        parent.$("#turnoff_img").removeClass("xk_options_closeicon_disable").addClass("xk_options_closeicon");
        parent.$("#turnoff").attr("onclick", "shutdown()");
        parent.$("#record_vedio").attr("onclick", "recording()");
        parent.$("#record_vedio_img").removeClass("xk_options_recordingicon_disable").addClass("xk_options_recordingicon");
        parent.$("#stop_record_vedio").attr("onclick", "stoprecord()");
        parent.$("#stop_record_vedio_img").removeClass("xk_options_stoprecordingicon_disable").addClass("xk_options_stoprecordingicon");
        $("#classmessage").show();
        $("#classmessage").html("");
        $("#selected_host").val("");
    }
    var flag_page = 0;
    //ajax获取九屏数据，填充当前页面
    function viewClass9Ajax(viewClassCameraName, hostIdStr, nowselectpage, pageCount,callback) {
        if (flag_page == 1)return;
        flag_page = 1;
        $.post("${pageContext.request.contextPath}/viewclass/Viewclass_viewClassAjax.do",
                {
                    viewClassCameraName: viewClassCameraName,
                    hostid: hostIdStr,
                    pageCount: pageCount,
                    currentPage: nowselectpage
                },
                function (data) {
                    var iframedom = $("#go").contents().find(".allarea");
                    var datalen = data.length;
                    $("#go").contents().find(".xk_video_selected_nine").attr("class", "xk_video_nine");
                    $("#go").contents().find(".xk_video_nine").attr("bingo", "none");
                    $("#go").contents().find(".xk_video_nine,.xk_video_selected_nine").css("box-shadow", "none")
                    $("#go").contents().find(".xk_video_selected_nine").removeClass("xk_video_selected_nine");
                    $("#go").contents().find(".bgr,.bgred").addClass("bgr").removeClass("bgred");
                    buttonInit();
                    if (datalen > 0) {
                        $("#go").contents().find("#linkpage ul").remove();
                        $("#go").contents().find("#linkpage #pagerSpan").before(data[0].pagers);
                    }
                    if (datalen <= iframedom.length) {//dom节点多于data数据，删除dom多余控件
                        for (var m = 0; m < (iframedom.length - datalen); m++) {//删除多余的dom控件
                            iframedom.eq(datalen + m).remove()
                        }
                        //重新赋值，play调用
                        for (var i = 0; i < datalen; i++)//调用初始化控件流地址
                        {
                            iframedom.eq(i).find(".xk_video_text_nine").children("span").children("div:nth-child(2)").text(data[i].name);
                            iframedom.eq(i).find(".xk_video_text_nine").children("span").children("div:nth-child(2)").attr("title", data[i].name);
                            if (data[i].dspecid == "17") {
                                iframedom.eq(i).find("a").find('span.xk_video_camera').hide();
                                iframedom.eq(i).find("a").find('span.xk_video_camera').attr("onclick", '');
                            }
                            else if (data[i].status == "Online" && data[i].cameralurl[j] != "") {
                                iframedom.eq(i).find("a").find('span.xk_video_camera').show();
                                iframedom.eq(i).find("a").find('span.xk_video_camera').attr("onclick", 'directorView(\'' + data[i].id + '\',\'' + data[i].host_ip + '\',\'' + data[i].name + '\',\'' + data[i].host_desc + '\')');
                            } else {
                                iframedom.eq(i).find("a").find('span.xk_video_camera').eq(0).show();
                                iframedom.eq(i).find("a").find('span.xk_video_camera').attr("onclick", '');
                            }
                            if (data[i].status == "Online") {
                                iframedom.eq(i).find("a").find('span.xk_split_camera').attr("onclick", 'directorCamera(\'' + data[i].id + '\')');
                            } else {
                                iframedom.eq(i).find("a").find('span.xk_split_camera').attr("onclick", '');
                            }
                            iframedom.eq(i).find(".xk_video_nine").attr("id", data[i].id);
                            iframedom.eq(i).find(".xk_video_nine").attr("ip", data[i].host_ip);
                            iframedom.eq(i).find(".xk_video_nine").children("input[type=hidden]").eq(0).attr("id", "host_status_" + data[i].id);
                            iframedom.eq(i).find(".xk_video_nine").children("input[type=hidden]").eq(0).attr("recordtype", data[i].host_desc);
                            iframedom.eq(i).find(".xk_video_nine").children("input[type=hidden]").eq(0).attr("dspecid", data[i].dspecid);
                            iframedom.eq(i).find(".xk_video_nine .xk_video_group_nine").children("div").eq(2).attr("id", "record_" + data[i].id);
                            iframedom.eq(i).find(".xk_video_nine .xk_video_group_nine").children("div").eq(3).attr("id", "recordstop_" + data[i].id)
                            iframedom.eq(i).find(".xk_video_nine").children("input[type=hidden]").eq(0).attr("hosttype", data[i].type);
                            var hoststatus = data[i].status + "-false-false";
                            if (data[i].workingMode != 'false') {
                                if (data[i].workingMode == 'workingModeLiveCasting') {
                                    iframedom.eq(i).find(".xk_video_nine").find("#record_" + data[i].id).html("直播中");
                                    hoststatus = "Online-false-true";
                                } else {
                                    iframedom.eq(i).find(".xk_video_nine").find("#record_" + data[i].id).html("录制中");
                                    hoststatus = "Online-true-false";
                                }
                                iframedom.eq(i).find(".xk_video_nine").find("#record_" + data[i].id).show();
                                iframedom.eq(i).find(".xk_video_nine").find("#recordstop_" + data[i].id).hide();
                            }
                            else {
                                iframedom.eq(i).find(".xk_video_nine").find("#record_" + data[i].id).hide();
                                iframedom.eq(i).find(".xk_video_nine").find("#recordstop_" + data[i].id).show();
                            }
                            iframedom.eq(i).find(".xk_video_nine").children("input[type=hidden]").eq(0).val(hoststatus);
                            iframedom.eq(i).find(".xk_video_nine").children("input[type=hidden]").eq(1).attr("id", "status_" + data[i].id);
                            iframedom.eq(i).find(".xk_video_nine").children("input[type=hidden]").eq(1).val(data[i].status + "_" + data[i].id + "_" + data[i].host_ip);
                            iframedom.eq(i).find(".xk_video_twonine").attr("id", "object_" + data[i].id);
                            iframedom.eq(i).find(".xk_video_twonine").attr("id", "object_" + data[i].id);
                            iframedom.eq(i).find(".xk_video_group_nineerror").attr("id", "nas_no_" + data[i].id);
                            iframedom.eq(i).find(".xk_video_group_nine").children("div").eq(0).attr("id", "bz_" + data[i].id);
                            iframedom.eq(i).find(".xk_video_group_warn").attr("id", "message_" + data[i].id);
                            if ($.cookie("camera") == "教师学生") {
                                //获取双屏dom
                                var changeid = iframedom.eq(i).find(".xk_video_twonineobj");
                            }
                            else {
                                var changeid = iframedom.eq(i).find(".xk_video_twonine");
                            }

                            for (var j = 0; j < changeid.length; j++) {
                                var nowobj = changeid.eq(j).find(".fourfourimg");
                                var obj = changeid.eq(j).find("object");
                                if (nowobj.html() == null) {//有视频流
                                    if (data[i].status == "Online" && data[i].cameralurl[j] != "" && data[i].cameralurl[j] != "@_@") {
                                        obj.attr("id", "object_" + j + "_" + data[i].id)
                                        try {
                                            document.getElementById("go").contentWindow.document.getElementById("object_" + j + "_" + data[i].id).play(data[i].cameralurl[j]);
                                        } catch (e) {
                                        }
                                    }
                                    else {
                                        if ($.cookie("camera") == "教师学生") {
                                            changeid.eq(j).find(".threetwonine").html("")
                                            changeid.eq(j).find(".threetwonine").append('<div class=\"fourfourimg\"><div class=\"fourfourimgicon\"></div></div>');
                                        }
                                        else {
                                            changeid.html("")
                                            changeid.append('<div class=\"fourfourimg\"><div class=\"fourfourimgicon\"></div></div>');
                                        }

                                    }
                                }
                                else {
                                    if (data[i].status == "Online" && data[i].cameralurl[j] != "" && data[i].cameralurl[j] != "@_@") {
                                        var objhtml = "<object name=\"ScriptableObject\" width=\"100%\" height=\"100%\" id=\"object_" + j + "_" + data[i].id + "\"  CLASSID=\"CLSID:3a22176d-118f-4185-9653-cdea558a6df8\"></object>";
                                        if ($.cookie("camera") == "教师学生") {
                                            changeid.eq(j).find(".threetwonine").html(objhtml);
                                        }
                                        else {
                                            changeid.eq(j).html(objhtml);
                                        }
                                        try {
                                            document.getElementById("go").contentWindow.document.getElementById("object_" + j + "_" + data[i].id).play(data[i].cameralurl[j]);
                                        } catch (e) {
                                        }
                                    }
                                }
                            }
                        }
                        flag_page = 0;
                    }
                    else {//dom节点少于data数据,向window渲染新的控件,初始控件流地址未调用
                        for (var i = iframedom.length; i < datalen; i++) {
                            var hoststatus = data[i].status + '-false-false';
                            var recordhtml = "未录制";
                            var recorddisplay = "none";
                            var recordstopdisplay = "block";
                            if (data[i].workingMode != 'false') {
                                if (data[i].workingMode == 'workingModeLiveCasting') {
                                    recordhtml = "直播中";
                                    hoststatus = "Online-false-true";
                                } else {
                                    recordhtml = "录制中";
                                    hoststatus = "Online-true-false";
                                }
                                recorddisplay = "block";
                                recordstopdisplay = "none";
                            }

                            var html = "";
                            html += '<div class=allarea style=\"width: 22%; float: left; position: relative; margin: 3px 4% 10px 20px;\">';
                            if (data[i].dspecid == "17") {
                                html += '';
                            }
                            else if (data[i].status == "Online" && data[i].cameralurl[j] != "") {
                                html += '<a title=\"进入导播\" onclick=directorView(\'' + data[i].id + '\',\'' + data[i].host_ip + '\',\'' + data[i].name + '\',\'' + data[i].host_desc + '\') href=javascript:void(0)><span class=xk_video_camera></span></a>';
                            } else {
                                html += '<span class=xk_video_camera></span>';
                            }
                            if (data[i].status == "Online") {
                                html += '<a title=\"进入监看画面\" onclick=directorCamera(\'' + data[i].id + '\') href=javascript:void(0)><span class=\'xk_split_camera\' style=\'background-position: 0px 0px;\'></span></a>';
                            } else {
                                html += '<span class=\'xk_split_camera\' style=\'background-position: 0px 0px;\'></span>';
                            }
                            html += '<div class=xk_video_nine id=' + data[i].id + ' style=\"margin: auto; left: 0px; top: 0px; right: 0px; bottom: 0px; position: absolute;\" ip=\"' + data[i].host_ip + '\" bingo=none>';
                            html += '<input id=host_status_' + data[i].id + ' type=hidden value=' + hoststatus + ' recordtype=' + data[i].host_desc + ' hosttype=' + data[i].type + ' dspecid=' + data[i].dspecid + '>';
                            html += ' <input class=hoststatus id=status_' + data[i].id + ' type=hidden value=' + data[i].status + '_' + data[i].id + '_' + data[i].host_ip + '>';
                            html += '<a style=cursor: default; href=javascript:void(0)>';
                            html += ' <div class=xk_video_twonine id=object_' + data[i].id + ' >';

                            var cameraname = $.cookie("camera");
                            if (cameraname == "教师学生") {
                                html += ' <div class=xk_video_twonineobj style=\"float: left\">';
                                html += ' <div class=threetwonine >';
                                if (data[i].status == "Online" && data[i].cameralurl[0] != "" && data[i].cameralurl[0] != "@_@") {
                                    html += '  <object name=ScriptableObject width=100% height=100% id=object_0_' + data[i].id + ' CLASSID="CLSID:3a22176d-118f-4185-9653-cdea558a6df8">';
                                    html += '   </object>';
                                }
                                else {
                                    html += '<div class=\"fourfourimg\"><div class=\"fourfourimgicon\"></div></div>';
                                }
                                html += '   </div>';
                                html += '   </div>';
                                html += ' <div class=xk_video_twonineobj style=\"float: left\">';
                                html += ' <div class=threetwonine >';
                                if (data[i].status == "Online" && data[i].cameralurl[1] != "" && data[i].cameralurl[1] != "@_@") {
                                    html += '  <object name=ScriptableObject width=100% height=100% id=object_1_' + data[i].id + ' CLASSID="CLSID:3a22176d-118f-4185-9653-cdea558a6df8">';
                                    html += '   </object>';
                                }
                                else {
                                    html += '<div class=\"fourfourimg\"><div class=\"fourfourimgicon\"></div></div>';
                                }
                                html += '   </div>';
                                html += '   </div>';
                            }
                            else {
                                html += ' <div class=threenine>';
                                if (data[i].status == "Online" && data[i].cameralurl[0] != "" && data[i].cameralurl[0] != "@_@") {
                                    html += '  <object name=ScriptableObject width=100% height=100% id=object_0_' + data[i].id + ' CLASSID="CLSID:3a22176d-118f-4185-9653-cdea558a6df8">';
                                    html += '   </object>';
                                }
                                else {
                                    html += '<div class=\"fourfourimg\"><div class=\"fourfourimgicon\"></div></div>';
                                }
                                html += ' </div>';
                            }
                            html += '   </div>';
                            html += '   <div class=xk_video_text_nine >';
                            html += ' <div class=xk_video_group_nineerror id=nas_no_' + data[i].id + '></div>';
                            html += '   <span class=xk_video_group_nine>';
                            html += '   <div class=bgr id=bz_' + data[i].id + '></div>';
                            html += '   <div title=\"' + data[i].name + '\" class=\"bgr_styl\">' + data[i].name + '</div>';
                            html += '   <div class=xk_circlerecord id=record_' + data[i].id + ' style=\"display: ' + recorddisplay + '\">' + recordhtml + '</div>';
                            html += '    <div class=xk_circlestop id=recordstop_' + data[i].id + ' style=\"display: ' + recordstopdisplay + '\">未录制</div>';
                            html += '    <span style=\"color: red; float: right\"> </span>';
                            html += '     <span class=xk_video_group_warn id=message_' + data[i].id + '></span>';
                            html += ' </span>';
                            html += ' </div>';
                            html += '  </a>';
                            html += '   </div>';
                            html += '   </div>';
                            $("#go").contents().find("#view").append(html);
                            if (cameraname == "教师学生") {
                                if (data[i].status == "Online" && data[i].cameralurl[0] != "" && data[i].cameralurl[0] != "@_@") {
                                    try {
                                        document.getElementById("go").contentWindow.document.getElementById("object_0_" + data[i].id).play(data[i].cameralurl[0]);
                                    } catch (e) {
                                    }
                                }
                                if (data[i].status == "Online" && data[i].cameralurl[1] != "" && data[i].cameralurl[1] != "@_@") {
                                    try {
                                        document.getElementById("go").contentWindow.document.getElementById("object_1_" + data[i].id).play(data[i].cameralurl[1]);
                                    } catch (e) {
                                    }
                                }
                            }
                            else {
                                if (data[i].status == "Online" && data[i].cameralurl[0] != "" && data[i].cameralurl[0] != "@_@") {
                                    try {
                                        document.getElementById("go").contentWindow.document.getElementById("object_0_" + data[i].id).play(data[i].cameralurl[0]);
                                    } catch (e) {
                                    }
                                }
                            }
                        }
                        for (var i = 0; i < iframedom.length; i++)//调用初始化控件流地址
                        {
                            iframedom.eq(i).find(".xk_video_text_nine").children("span").children("div:nth-child(2)").text(data[i].name);
                            iframedom.eq(i).find(".xk_video_text_nine").children("span").children("div:nth-child(2)").attr("title", data[i].name);
                            if (data[i].workingMode != 'false') {
                                if (data[i].workingMode == 'workingModeLiveCasting') {
                                    iframedom.eq(i).find(".xk_video_nine").find("#record_" + data[i].id).html("直播中");
                                    hoststatus = "Online-false-true";
                                } else {
                                    iframedom.eq(i).find(".xk_video_nine").find("#record_" + data[i].id).html("录制中");
                                    hoststatus = "Online-true-false";
                                }
                                iframedom.eq(i).find(".xk_video_nine").find("#record_" + data[i].id).show();
                                iframedom.eq(i).find(".xk_video_nine").find("#recordstop_" + data[i].id).hide();
                            }
                            else {
                                iframedom.eq(i).find(".xk_video_nine").find("#record_" + data[i].id).hide();
                                iframedom.eq(i).find(".xk_video_nine").find("#recordstop_" + data[i].id).show();
                            }
                            if (data[i].dspecid == "17") {
                                iframedom.eq(i).find("a").find('span.xk_video_camera').hide();
                                iframedom.eq(i).find("a").find('span.xk_video_camera').attr("onclick", '');
                            }
                            else if (data[i].status == "Online" && data[i].cameralurl[j] != "") {
                                iframedom.eq(i).find("a").find('span.xk_video_camera').show();
                                iframedom.eq(i).find("a").find('span.xk_video_camera').attr("onclick", 'directorView(\'' + data[i].id + '\',\'' + data[i].host_ip + '\',\'' + data[i].name + '\',\'' + data[i].host_desc + '\')');
                            } else {
                                iframedom.eq(i).find("a").find('span.xk_video_camera').eq(0).show();
                                iframedom.eq(i).find("a").find('span.xk_video_camera').attr("onclick", '');
                            }
                            if (data[i].status == "Online") {
                                iframedom.eq(i).find("a").find('span.xk_split_camera').attr("onclick", 'directorCamera(\'' + data[i].id + '\')');
                            } else {
                                iframedom.eq(i).find("a").find('span.xk_split_camera').attr("onclick", '');
                            }
                            iframedom.eq(i).find(".xk_video_nine").attr("id", data[i].id);
                            iframedom.eq(i).find(".xk_video_nine").attr("ip", data[i].host_ip);
                            iframedom.eq(i).find(".xk_video_nine").children("input[type=hidden]").eq(0).attr("id", "host_status_" + data[i].id);
                            iframedom.eq(i).find(".xk_video_nine").children("input[type=hidden]").eq(0).attr("recordtype", data[i].host_desc);
                            iframedom.eq(i).find(".xk_video_nine").children("input[type=hidden]").eq(0).attr("dspecid", data[i].dspecid);
                            iframedom.eq(i).find(".xk_video_nine .xk_video_group_nine").children("div").eq(2).attr("id", "record_" + data[i].id);
                            iframedom.eq(i).find(".xk_video_nine .xk_video_group_nine").children("div").eq(3).attr("id", "recordstop_" + data[i].id);
                            iframedom.eq(i).find(".xk_video_nine").children("input[type=hidden]").eq(0).attr("hosttype", data[i].type);
                            var hoststatus = data[i].status + "-false-false";
                            if (data[i].workingMode != 'false') {
                                if (data[i].workingMode == 'workingModeLiveCasting') {
                                    iframedom.eq(i).find(".xk_video_nine").find("#record_" + data[i].id).html("直播中");
                                    hoststatus = "Online-false-true";
                                } else {
                                    iframedom.eq(i).find(".xk_video_nine").find("#record_" + data[i].id).html("录制中");
                                    hoststatus = "Online-true-false";
                                }
                                iframedom.eq(i).find(".xk_video_nine").find("#record_" + data[i].id).show();
                                iframedom.eq(i).find(".xk_video_nine").find("#recordstop_" + data[i].id).hide();
                            }
                            else {
                                iframedom.eq(i).find(".xk_video_nine").find("#record_" + data[i].id).hide();
                                iframedom.eq(i).find(".xk_video_nine").find("#recordstop_" + data[i].id).show();
                            }
                            iframedom.eq(i).find(".xk_video_nine").children("input[type=hidden]").eq(0).val(hoststatus);
                            iframedom.eq(i).find(".xk_video_nine").children("input[type=hidden]").eq(1).attr("id", "status_" + data[i].id);
                            iframedom.eq(i).find(".xk_video_nine").children("input[type=hidden]").eq(1).val(data[i].status + "_" + data[i].id + "_" + data[i].host_ip);
                            iframedom.eq(i).find(".xk_video_twonine").attr("id", "object_" + data[i].id);
                            iframedom.eq(i).find(".xk_video_group_nineerror").attr("id", "nas_no_" + data[i].id);
                            iframedom.eq(i).find(".xk_video_group_nine").children("div").eq(0).attr("id", "bz_" + data[i].id);
                            iframedom.eq(i).find(".xk_video_group_warn").attr("id", "message_" + data[i].id);

                            if ($.cookie("camera") == "教师学生") {
                                //获取双屏dom
                                var changeid = iframedom.eq(i).find(".xk_video_twonineobj");
                            }
                            else {
                                var changeid = iframedom.eq(i).find(".xk_video_twonine");
                            }
                            for (var j = 0; j < changeid.length; j++) {
                                var nowobj = changeid.eq(j).find(".fourfourimg");
                                var obj = changeid.eq(j).find("object");
                                if (nowobj.html() == null) {
                                    if (data[i].status == "Online" && data[i].cameralurl[j] != "" && data[i].cameralurl[j] != "@_@") {
                                        obj.attr("id", "object_" + j + "_" + data[i].id)
                                        try {
                                            document.getElementById("go").contentWindow.document.getElementById("object_" + j + "_" + data[i].id).play(data[i].cameralurl[j]);
                                        } catch (e) {
                                        }
                                    }
                                    else {
                                        if ($.cookie("camera") == "教师学生") {
                                            //获取双屏dom
                                            changeid.eq(j).find(".threetwonine").html("")
                                            changeid.eq(j).find(".threetwonine").append('<div class=\"fourfourimg\"><div class=\"fourfourimgicon\"></div></div>');
                                        }
                                        else {
                                            changeid.html("")
                                            changeid.append('<div class=\"fourfourimg\"><div class=\"fourfourimgicon\"></div></div>');
                                        }

                                    }
                                }
                                else {
                                    if (data[i].status == "Online" && data[i].cameralurl[j] != "" && data[i].cameralurl[j] != "@_@") {
                                        var objhtml = "<object name=\"ScriptableObject\" width=\"100%\" height=\"100%\" id=\"object_" + j + "_" + data[i].id + "\"  CLASSID=\"CLSID:3a22176d-118f-4185-9653-cdea558a6df8\"></object>";
                                        if ($.cookie("camera") == "教师学生") {
                                            changeid.eq(j).find(".threetwonine").html(objhtml);
                                        }
                                        else {
                                            changeid.eq(j).html(objhtml);
                                        }
                                        try {
                                            document.getElementById("go").contentWindow.document.getElementById("object_" + j + "_" + data[i].id).play(data[i].cameralurl[j]);
                                        } catch (e) {
                                        }
                                    }
                                }
                            }
                        }
                        flag_page = 0;
                    }
                    refreshSelected();
                    document.getElementById("go").contentWindow.pageChange();
                }, 'json');
        videoShow(viewClassCameraName,hostIds)
    }


    //ajax获取四屏数据，填充当前页面
    function viewClass4Ajax(viewClassCameraName, hostIdStr, curPage, pageCount) {
        if (flag_page == 1)return;
        flag_page = 1;
        $.post("${pageContext.request.contextPath}/viewclass/Viewclass_viewClassAjax.do",
                {
                    viewClassCameraName: viewClassCameraName,
                    hostid: hostIdStr,
                    pageCount: pageCount,
                    currentPage: curPage
                },
                function (data) {
                    var iframedom = $("#go").contents().find(".allarea");
                    var datalen = data.length;
                    //初始化所有班级为未选中状态
                    $("#go").contents().find(".xk_video_selected").attr("class", "xk_video");
                    $("#go").contents().find(".xk_video").attr("bingo", "none");
                    $("#go").contents().find(".xk_video,.xk_video_selected").css("box-shadow", "none")
                    $("#go").contents().find(".xk_video_selected").removeClass("xk_video_selected");
                    $("#go").contents().find(".bgr,.bgred").addClass("bgr").removeClass("bgred");
                    buttonInit();
                    if (datalen > 0) {
                        $("#go").contents().find("#linkpage ul").remove();
                        $("#go").contents().find("#linkpage #pagerSpan").before(data[0].pagers);
                    }
                    if (datalen <= iframedom.length) {//dom节点多于data数据，删除dom多余控件
                        for (var m = 0; m < (iframedom.length - datalen); m++) {//删除多余的dom控件
                            iframedom.eq(datalen + m).remove()
                        }
                        //重新赋值，play调用
                        for (var i = 0; i < datalen; i++)//调用初始化控件流地址
                        {
                            iframedom.eq(i).find(".xk_video_text").children("span").children("div:nth-child(2)").text(data[i].name);
                            iframedom.eq(i).find(".xk_video_text").children("span").children("div:nth-child(2)").attr("title", data[i].name);
                            if (data[i].dspecid == "17") {
                                iframedom.eq(i).find("a").find('span.xk_video_camera').hide();
                                iframedom.eq(i).find("a").find('span.xk_video_camera').attr("onclick", '');
                            }
                            else if (data[i].status == "Online" && data[i].cameralurl[j] != "") {
                                iframedom.eq(i).find("a").find('span.xk_video_camera').show();
                                iframedom.eq(i).find("a").find('span.xk_video_camera').attr("onclick", 'directorView(\'' + data[i].id + '\',\'' + data[i].host_ip + '\',\'' + data[i].name + '\',\'' + data[i].host_desc + '\')');
                            } else {
                                iframedom.eq(i).find("a").find('span.xk_video_camera').show();
                                iframedom.eq(i).find("a").find('span.xk_video_camera').attr("onclick", '');
                            }
                            if (data[i].status == "Online") {
                                iframedom.eq(i).find("a").find('span.xk_split_camera').attr("onclick", 'directorCamera(\'' + data[i].id + '\')');
                            } else {
                                iframedom.eq(i).find("a").find('span.xk_split_camera').attr("onclick", '');
                            }
                            iframedom.eq(i).find(".xk_video").attr("id", data[i].id);
                            iframedom.eq(i).find(".xk_video").attr("ip", data[i].host_ip);
                            iframedom.eq(i).find(".xk_video").children("input[type=hidden]").eq(0).attr("id", "host_status_" + data[i].id);
                            iframedom.eq(i).find(".xk_video").children("input[type=hidden]").eq(0).attr("recordtype", data[i].host_desc);
                            iframedom.eq(i).find(".xk_video").children("input[type=hidden]").eq(0).attr("dspecid", data[i].dspecid);
                            iframedom.eq(i).find(".xk_video .xk_video_group").children("div").eq(2).attr("id", "record_" + data[i].id);
                            iframedom.eq(i).find(".xk_video .xk_video_group").children("div").eq(3).attr("id", "recordstop_" + data[i].id)
                            iframedom.eq(i).find(".xk_video").children("input[type=hidden]").eq(0).attr("hosttype", data[i].type);
                            var hoststatus = data[i].status + "-false-false";
                            if (data[i].workingMode != 'false') {
                                if (data[i].workingMode == 'workingModeLiveCasting') {
                                    iframedom.eq(i).find(".xk_video").find("#record_" + data[i].id).html("直播中");
                                    hoststatus = "Online-false-true";
                                } else {
                                    iframedom.eq(i).find(".xk_video").find("#record_" + data[i].id).html("录制中");
                                    hoststatus = "Online-true-false";
                                }
                                iframedom.eq(i).find(".xk_video").find("#record_" + data[i].id).show();
                                iframedom.eq(i).find(".xk_video").find("#recordstop_" + data[i].id).hide();
                            }
                            else {
                                iframedom.eq(i).find(".xk_video").find("#record_" + data[i].id).hide();
                                iframedom.eq(i).find(".xk_video").find("#recordstop_" + data[i].id).show();
                            }
                            iframedom.eq(i).find(".xk_video").children("input[type=hidden]").eq(0).val(hoststatus);
                            iframedom.eq(i).find(".xk_video").children("input[type=hidden]").eq(1).attr("id", "status_" + data[i].id);
                            iframedom.eq(i).find(".xk_video").children("input[type=hidden]").eq(1).val(data[i].status + "_" + data[i].id + "_" + data[i].host_ip);
                            iframedom.eq(i).find(".xk_video_twofour").attr("id", "object_" + data[i].id);
                            iframedom.eq(i).find(".xk_video_twofour").attr("id", "object_" + data[i].id);
                            iframedom.eq(i).find(".xk_video_group_error").attr("id", "nas_no_" + data[i].id);
                            iframedom.eq(i).find(".xk_video_group").children("div").eq(0).attr("id", "bz_" + data[i].id);
                            iframedom.eq(i).find(".xk_video_group_warn").attr("id", "message_" + data[i].id);
                            //获取双屏dom
                            if ($.cookie("camera") == "教师学生") {
                                var changeid = iframedom.eq(i).find(".xk_video_twofourobj");
                            }
                            else {
                                var changeid = iframedom.eq(i).find(".xk_video_twofour");
                            }

                            for (var j = 0; j < changeid.length; j++) {
                                var nowobj = changeid.eq(j).find(".fourfourimg");
                                var obj = changeid.eq(j).find("object");
                                if (nowobj.html() == null) {
                                    if (data[i].status == "Online" && data[i].cameralurl[j] != "" && data[i].cameralurl[j] != "@_@") {
//                                alert(obj.attr("id"));
                                        obj.attr("id", "object_" + j + "_" + data[i].id);
//                                alert(obj.attr("id"));
//                                alert(data[i].cameralurl[j]);
                                        try {
                                            document.getElementById("go").contentWindow.document.getElementById("object_" + j + "_" + data[i].id).play(data[i].cameralurl[j]);
                                        } catch (e) {
                                        }
//                                alert("111111111111111");
                                    }
                                    else {
                                        if ($.cookie("camera") == "教师学生") {
                                            changeid.eq(j).find(".fourfour").html("")
                                            changeid.eq(j).find(".fourfour").append('<div class=\"fourfourimg\"><div class=\"fourfourimgicon\"></div></div>');
                                        }
                                        else {
                                            changeid.html("")
                                            changeid.append('<div class=\"fourfourimg\"><div class=\"fourfourimgicon\"></div></div>');
                                        }
//                                alert("222222222222222222222222");
                                    }
                                }
                                else {
                                    if (data[i].status == "Online" && data[i].cameralurl[j] != "" && data[i].cameralurl[j] != "@_@") {
                                        var objhtml = "<object name=\"ScriptableObject\" width=\"100%\" height=\"100%\" id=\"object_" + j + "_" + data[i].id + "\"  CLASSID=\"CLSID:3a22176d-118f-4185-9653-cdea558a6df8\"></object>";
                                        if ($.cookie("camera") == "教师学生") {
                                            changeid.eq(j).find(".fourfour").html(objhtml);
                                        }
                                        else {
                                            changeid.eq(j).html(objhtml);
                                        }
                                        try {
                                            document.getElementById("go").contentWindow.document.getElementById("object_" + j + "_" + data[i].id).play(data[i].cameralurl[j]);
                                        } catch (e) {

                                        }
                                    }
                                }
                            }
                        }
//                        seticon4();
                        flag_page = 0;
                    }
                    else {//dom节点少于data数据,向window渲染新的控件,初始控件流地址未调用
                        for (var i = iframedom.length; i < datalen; i++) {
                            var hoststatus = data[i].status + '-false-false';
                            var recordhtml = "未录制";
                            var recorddisplay = "none";
                            var recordstopdisplay = "block";
                            if (data[i].workingMode != 'false') {
                                if (data[i].workingMode == 'workingModeLiveCasting') {
                                    recordhtml = "直播中";
                                    hoststatus = "Online-false-true";
                                } else {
                                    recordhtml = "录制中";
                                    hoststatus = "Online-true-false";
                                }
                                recorddisplay = "block";
                                recordstopdisplay = "none";
                            }
                            var html = "";
                            html += '<div class=allarea style=\"width: 35%; float: left; position: relative; margin: 3px 4% 10px 20px;\">';
                            if (data[i].dspecid == "17") {
                                html += '';
                            }
                            else if (data[i].status == "Online" && data[i].cameralurl[j] != "") {
                                html += '<a title=\"进入导播\" onclick=directorView(\'' + data[i].id + '\',\'' + data[i].host_ip + '\',\'' + data[i].name + '\',\'' + data[i].host_desc + '\') href=javascript:void(0)><span class=xk_video_camera></span></a>';
                            } else {
                                html += '<span class=xk_video_camera></span>';
                            }
                            if (data[i].status == "Online") {
                                html += '<a title=\"进入监看画面\" onclick=directorCamera(\'' + data[i].id + '\') href=javascript:void(0)><span class=\'xk_split_camera\' style=\'background-position: 0px 0px;\' id=\'xk_video_camera_4\'></span></a>';
                            } else {
                                html += '<span class=\'xk_split_camera\' style=\'background-position: 0px 0px;\'></span>';
                            }
                            html += '<div class=xk_video id=' + data[i].id + ' style=\"margin: auto; left: 0px; top: 0px; right: 0px; bottom: 0px; position: absolute;\" ip=\"' + data[i].host_ip + '\" bingo=none>';
                            html += '<input id=host_status_' + data[i].id + ' type=hidden value=' + hoststatus + ' recordtype=' + data[i].host_desc + ' hosttype=' + data[i].type + ' dspecid=' + data[i].dspecid + '>';
                            html += ' <input class=hoststatus id=status_' + data[i].id + ' type=hidden value=' + data[i].status + '_' + data[i].id + '_' + data[i].host_ip + '>';
                            html += '<a style=cursor: default; href=javascript:void(0)>';
                            html += ' <div class=xk_video_twofour id=object_' + data[i].id + ' >';
                            var cameraname = $.cookie("camera");
                            if (cameraname == "教师学生") {
                                html += ' <div class=xk_video_twofourobj style=\"float: left\">';
                                html += ' <div class=fourfour >';
                                if (data[i].status == "Online" && data[i].cameralurl[0] != "" && data[i].cameralurl[0] != "@_@") {
                                    html += '  <object name=ScriptableObject width=100% height=100% id=object_0_' + data[i].id + ' CLASSID="CLSID:3a22176d-118f-4185-9653-cdea558a6df8">';
                                    html += '   </object>';
                                }
                                else {
                                    html += '<div class=\"fourfourimg\"><div class=\"fourfourimgicon\"></div></div>';
                                }
                                html += '   </div>';
                                html += '   </div>';
                                html += ' <div class=xk_video_twofourobj style=\"float: left\">';
                                html += ' <div class=fourfour >';
                                if (data[i].status == "Online" && data[i].cameralurl[1] != "" && data[i].cameralurl[1] != "@_@") {
                                    html += '  <object name=ScriptableObject width=100% height=100% id=object_1_' + data[i].id + ' CLASSID="CLSID:3a22176d-118f-4185-9653-cdea558a6df8">';
                                    html += '   </object>';
                                }
                                else {
                                    html += '<div class=\"fourfourimg\"><div class=\"fourfourimgicon\"></div></div>';
                                }
                                html += '   </div>';
                                html += '   </div>';
                            }
                            else {
                                if (data[i].status == "Online" && data[i].cameralurl[0] != "" && data[i].cameralurl[0] != "@_@") {
                                    html += '  <object name=ScriptableObject width=100% height=100% id=object_0_' + data[i].id + ' CLASSID="CLSID:3a22176d-118f-4185-9653-cdea558a6df8">';
                                    html += '   </object>';
                                }
                                else {
                                    html += '<div class=\"fourfourimg\"><div class=\"fourfourimgicon\"></div></div>';
                                }
                            }
                            html += '</div>';
                            html += '   <div class=xk_video_text >';
                            html += ' <div class=xk_video_group_error id=nas_no_' + data[i].id + '></div>';
                            html += '   <span class=xk_video_group>';
                            html += '   <div class=bgr id=bz_' + data[i].id + '></div>';
                            html += '   <div title=\"' + data[i].name + '\" class=\"bgr_styl\">' + data[i].name + '</div>';
                            html += '   <div class=xk_circlerecord id=record_' + data[i].id + ' style=\"display: ' + recorddisplay + '\">' + recordhtml + '</div>';
                            html += '    <div class=xk_circlestop id=recordstop_' + data[i].id + ' style=\"display: ' + recordstopdisplay + '\">未录制</div>';
                            html += '    <span style=\"color: red; float: right\"> </span>';
                            html += '     <span class=xk_video_group_warn id=message_' + data[i].id + '></span>';
                            html += ' </span>';
                            html += ' </div>';
                            html += '  </a>';
                            html += '   </div>';
                            html += '   </div>';
                            $("#go").contents().find("#view").append(html);
//                            seticon4();
                            if (cameraname == "教师学生") {
                                if (data[i].status == "Online" && data[i].cameralurl[0] != "" && data[i].cameralurl[0] != "@_@") {
                                    try {
                                        document.getElementById("go").contentWindow.document.getElementById("object_0_" + data[i].id).play(data[i].cameralurl[0]);

                                    } catch (e) {

                                    }
                                }
                                if (data[i].status == "Online" && data[i].cameralurl[1] != "" && data[i].cameralurl[1] != "@_@") {
                                    try {
                                        document.getElementById("go").contentWindow.document.getElementById("object_1_" + data[i].id).play(data[i].cameralurl[1]);

                                    } catch (e) {

                                    }
                                }
                            }
                            else {
                                if (data[i].status == "Online" && data[i].cameralurl[0] != "" && data[i].cameralurl[0] != "@_@") {
                                    try {
                                        document.getElementById("go").contentWindow.document.getElementById("object_0_" + data[i].id).play(data[i].cameralurl[0]);

                                    } catch (e) {

                                    }
                                }
                            }
                        }
                        for (var i = 0; i < iframedom.length; i++)//调用初始化控件流地址
                        {
                            iframedom.eq(i).find(".xk_video_text").children("span").children("div:nth-child(2)").text(data[i].name);
                            iframedom.eq(i).find(".xk_video_text").children("span").children("div:nth-child(2)").attr("title", data[i].name);
                            if (data[i].dspecid == "17") {
                                iframedom.eq(i).find("a").find('span.xk_video_camera').eq(0).hide();
                            }
                            else if (data[i].status == "Online" && data[i].cameralurl[j] != "" && data[i].cameralurl[j] != "@_@") {
                                iframedom.eq(i).find("a").find('span.xk_video_camera').show();
                                iframedom.eq(i).find("a").find('span.xk_video_camera').attr("onclick", 'directorView(\'' + data[i].id + '\',\'' + data[i].host_ip + '\',\'' + data[i].name + '\',\'' + data[i].host_desc + '\')');
                            } else {
                                iframedom.eq(i).find("a").find('span.xk_video_camera').show();
                                iframedom.eq(i).find("a").find('span.xk_video_camera').attr("onclick", '');
                            }
                            if (data[i].status == "Online") {
                                iframedom.eq(i).find("a").find('span.xk_split_camera').attr("onclick", 'directorCamera(\'' + data[i].id + '\')');
                            } else {
                                iframedom.eq(i).find("a").find('span.xk_split_camera').attr("onclick", '');
                            }
                            iframedom.eq(i).find(".xk_video").attr("id", data[i].id);
                            iframedom.eq(i).find(".xk_video").attr("ip", data[i].host_ip);
                            iframedom.eq(i).find(".xk_video").children("input[type=hidden]").eq(0).attr("id", "host_status_" + data[i].id);
                            iframedom.eq(i).find(".xk_video").children("input[type=hidden]").eq(0).attr("recordtype", data[i].host_desc);
                            iframedom.eq(i).find(".xk_video").children("input[type=hidden]").eq(0).attr("dspecid", data[i].dspecid);
                            iframedom.eq(i).find(".xk_video .xk_video_group").children("div").eq(2).attr("id", "record_" + data[i].id);
                            iframedom.eq(i).find(".xk_video .xk_video_group").children("div").eq(3).attr("id", "recordstop_" + data[i].id)
                            iframedom.eq(i).find(".xk_video").children("input[type=hidden]").eq(0).attr("hosttype", data[i].type);
                            var hoststatus = data[i].status + "-false-false";
                            if (data[i].workingMode != 'false') {
                                if (data[i].workingMode == 'workingModeLiveCasting') {
                                    iframedom.eq(i).find(".xk_video").find("#record_" + data[i].id).html("直播中");
                                    hoststatus = "Online-false-true";
                                } else {
                                    iframedom.eq(i).find(".xk_video").find("#record_" + data[i].id).html("录制中");
                                    hoststatus = "Online-true-false";
                                }
                                iframedom.eq(i).find(".xk_video").find("#record_" + data[i].id).show();
                                iframedom.eq(i).find(".xk_video").find("#recordstop_" + data[i].id).hide();
                            }
                            else {
                                iframedom.eq(i).find(".xk_video").find("#record_" + data[i].id).hide();
                                iframedom.eq(i).find(".xk_video").find("#recordstop_" + data[i].id).show();
                            }
                            iframedom.eq(i).find(".xk_video").children("input[type=hidden]").eq(0).val(hoststatus);
                            iframedom.eq(i).find(".xk_video").children("input[type=hidden]").eq(1).attr("id", "status_" + data[i].id);
                            iframedom.eq(i).find(".xk_video").children("input[type=hidden]").eq(1).val(data[i].status + "_" + data[i].id + "_" + data[i].host_ip);
                            iframedom.eq(i).find(".xk_video_twofour").attr("id", "object_" + data[i].id);
                            iframedom.eq(i).find(".xk_video_twofour").attr("id", "object_" + data[i].id);
                            iframedom.eq(i).find(".xk_video_group_error").attr("id", "nas_no_" + data[i].id);
                            iframedom.eq(i).find(".xk_video_group").children("div").eq(0).attr("id", "bz_" + data[i].id);
                            iframedom.eq(i).find(".xk_video_group_warn").attr("id", "message_" + data[i].id);
                            if ($.cookie("camera") == "教师学生") {
                                var changeid = iframedom.eq(i).find(".xk_video_twofourobj");
                            }
                            else {
                                var changeid = iframedom.eq(i).find(".xk_video_twofour");
                            }

                            for (var j = 0; j < changeid.length; j++) {
                                var nowobj = changeid.eq(j).find(".fourfourimg");
                                var obj = changeid.eq(j).find("object");
                                if (nowobj.html() == null) {
                                    if (data[i].status == "Online" && data[i].cameralurl[j] != "" && data[i].cameralurl[j] != "@_@") {
                                        obj.attr("id", "object_" + j + "_" + data[i].id)
                                        try {
                                            document.getElementById("go").contentWindow.document.getElementById("object_" + j + "_" + data[i].id).play(data[i].cameralurl[j]);

                                        } catch (e) {

                                        }
                                    }
                                    else {
                                        if ($.cookie("camera") == "教师学生") {
                                            changeid.eq(j).find(".fourfour").html("");
                                            changeid.eq(j).find(".fourfour").append('<div class=\"fourfourimg\"><div class=\"fourfourimgicon\"></div></div>');
                                        }
                                        else {
                                            changeid.html("");
                                            changeid.append('<div class=\"fourfourimg\"><div class=\"fourfourimgicon\"></div></div>');
                                        }

                                    }
                                }
                                else {
                                    if (data[i].status == "Online" && data[i].cameralurl[j] != "" && data[i].cameralurl[j] != "@_@") {
                                        var objhtml = "<object name=\"ScriptableObject\" width=\"100%\" height=\"100%\" id=\"object_" + j + "_" + data[i].id + "\"  CLASSID=\"CLSID:3a22176d-118f-4185-9653-cdea558a6df8\"></object>";
                                        if ($.cookie("camera") == "教师学生") {
                                            changeid.eq(j).find(".fourfour").html(objhtml);
                                        }
                                        else {
                                            changeid.eq(j).html(objhtml);
                                        }
                                        try {
                                            document.getElementById("go").contentWindow.document.getElementById("object_" + j + "_" + data[i].id).play(data[i].cameralurl[j]);
                                        } catch (e) {

                                        }
                                    }
                                }
                            }
                        }
                    }
//                    seticon4();
                    flag_page = 0;
                    refreshSelected();
                    document.getElementById("go").contentWindow.pageChange();
//                    callback&&callback();
                }, 'json');
        videoShow(viewClassCameraName,hostIds)
    }

    var root_path;

    $(function () {
        $(".xk_stage_change").click(function () {
            var changeflag = $(this).attr("changing")
            if (changeflag == "no") {
                $(".xk_stage").show()
                $(this).attr("changing", "yes")
            } else {
                $(".xk_stage").hide()
                $(this).attr("changing", "no")
            }
        })
        //切换系统
        $(".equipment").click(function () {
            $(".public_head_system").css("top", $(".equipment").height() + "px")
            $(".public_head_system").height($("#otp_vedeoabout_rvideo").height())
            var flag = $(this).attr("opening");
            if (flag == "no") {
                $(".public_head_system").show()
                //$(".public_head_changesystem").css("transform","rotate(180deg)")
                $(this).attr("opening", "true");
            } else {
                $(".public_head_system").hide()
                //$(".public_head_changesystem").css("transform","rotate(0deg)")
                $(this).attr("opening", "no");
            }
        })
        $(".public_head_system li").click(function () {
            var ind = $(this).index();
            var thisurl = location.href;
            thisurl = thisurl.split("/");
            thisurl = "http://" + thisurl[1] + thisurl[2];
            if ($.trim($(this).text()) == "录播设备") {
                location.href = thisurl + "/viewclass/Viewclass_getHostGroup.do";
            } else if ($.trim($(this).text()) == "大屏设备") {
                location.href = thisurl + "/dmanager/DManager_getHostGroup.do"
            } else if ($.trim($(this).text()) == "投影仪设备") {
                location.href = thisurl + "/htprojector/HTProjector_getHostGroup.do"
            }else if ($.trim($(this).text()) == "白板一体机设备") {
                location.href = "${pageContext.request.contextPath}/hhtwhiteboard/HHTWhiteboard_getHostGroup.do"
            }
        })

        root_path = $("#root_path").val();
        message.processResponse = function (tokenParams, eventType, desc) {
            //开始
            if (eventType == '0x005004') {
                var json = eval("(" + desc + ")");
                var hostid_string = json.hostId;
                $("#go").contents().find("#host_status_" + hostid_string).val("Online-true");
                $("#go").contents().find("#record_" + hostid_string).show();
                $("#go").contents().find("#recordstop_" + hostid_string).hide();
            } else if (eventType == '0x005007') {// 停止
                var json = eval("(" + desc + ")");
                var hostid_string = json.hostId;
                $("#go").contents().find("#host_status_" + hostid_string).val("Online-false");
                $("#go").contents().find("#record_" + hostid_string).hide();
                $("#go").contents().find("#recordstop_" + hostid_string).show();
            }
        }


        message.processOnlineOfflineEvent = function (token, eventType) {
            if ($(".fullscreen").attr("full") == "true") return;
            $(".xk_video_group_warn").hide();
            // var hostArray = $("div[token='" + token + "']");
            //alert(token + "===="+hostArray.length);
            var hostArray = $("span[hostip='" + token + "']");
            var hostIds = new Array();
            $("#go").contents().find('.allarea').each(function () {
                hostIds.push($(this).find('div').attr('id'));
            })
            var isRightRefresh = false;
            for (var i = 0; i < hostArray.length; i++) {
                var host = hostArray[i];
//                var clickValue = $(host).attr("onclick");
                if (eventType == "102") {   //离线
                    //判断是否已经是离线状态
//                    if (typeof(clickValue) == "undefined") {
//                        continue;
//                    }
                    $(host).removeAttr("onclick");
//                    $(host).attr("onclick_1", clickValue);
                    var span = $(host).children(":first");
                    span.attr("class", "tree_content_nousebg");
                    var spanP = $($(host).children()[2]).children();
                    $(spanP[0]).attr("style", "float: left; color: rgb(128, 128, 128);");
                    $(spanP[1]).attr("class", "offlineshow");
                    isRightRefresh = true;
                } else { //101
                    //离线执行以下条件
                    //&& $(host).attr("type") != "2" 存在ipc 也能够点击进入导播的问题
                    if (typeof(clickValue) == "undefined") {
//                        var onclick_1_value = $(host).attr("onclick_1");
//                        $(host).removeAttr("onclick_1");
//                        $(host).attr("onclick", onclick_1_value);
                        var span = $(host).children(":first");
                        span.attr("class", "tree_content_onlinebg");
                        var spanP = $($(host).children()[2]).children();
                        $(spanP[0]).attr("style", "float: left;");
                        $(spanP[1]).attr("class", "offlinehide");
                        isRightRefresh = true;
                    }
                }
            }
            //有事件通知时刷新右侧列表
            // if (hostArray.length > 0 && isRightRefresh) {
            //     var pagesize = $.cookie("screenrecord");
            //     var groupid = $("#group_id").val();
            //     var src = $("#root_path").val();
            //     var viewClassCameraName = encodeURI($.cookie("camera"));
            //     buttonInit();
            //     if (pagesize == 2) {
            //         src += "/viewclass/Viewclass_viewClass4.do?groupid=" + groupid + "&pagesize=" + pagesize + "&currentPage=" + nowselectpage + "&viewClassCameraName=" + viewClassCameraName;
            //     }
            //     else {
            //         src += "/viewclass/Viewclass_viewClass9.do?groupid=" + groupid + "&pagesize=" + pagesize + "&currentPage=" + nowselectpage + "&viewClassCameraName=" + viewClassCameraName;
            //     }
            //     $("#go").attr("src", src);
            // }
            if (hostArray.length > 0 && isRightRefresh) {
                var isIn = $("#go").contents().find("div[ip='" + token + "']");
                // console.log("-----------" + isIn);
                console.log($("#go").contents().find("div[ip='" + token + "']")+"jfjjjjjslfjkdfjkdjfk");
                if (isIn.length != 0) {//只刷新当前页的设备
                    // var hostIds = $.cookie('hostIdStr');
                    var viewClassCameraName = encodeURI(encodeURI($.cookie("camera")));
                    // console.log('-----cookie--------' + hostIds);
                    videoShow(viewClassCameraName,hostIds);
                }

            }
        }

        message.processOtherEvent = function (token, eventType, desc) {
            var ip = token.split("-")[0];
            // alert(token + "---" + desc + "---" + eventType);
            var div = $("#go").contents().find("div[ip='" + ip + "']");
            var hostid_string = div.attr("id");
            var isViewClassPage = div.length == 1;
            if (isViewClassPage) {
                if (desc.indexOf("event_direct_start") != -1) {  //简易录播开始
                    //location.reload();
                    $("#stop_record_vedio_img").attr("class","xk_options_stoprecordingicon");
                    $("#record_vedio_img").attr("class","xk_options_recordingicon_disable");

                    $("#go").contents().find("#host_status_" + hostid_string).val("Online-true");
                    $("#go").contents().find("#record_" + hostid_string).show();
                    $("#go").contents().find("#recordstop_" + hostid_string).hide();
                } else if (desc.indexOf("event_direct_stop") != -1) {  //简易录播停止
                    //location.reload();
                    $("#stop_record_vedio_img").attr("class","xk_options_stoprecordingicon_disable");
                    $("#record_vedio_img").attr("class","xk_options_recordingicon");
                    $("#go").contents().find("#host_status_" + hostid_string).val("Online-false");
                    $("#go").contents().find("#record_" + hostid_string).hide();
                    $("#go").contents().find("#recordstop_" + hostid_string).show();
                }
            }
        }

        $(document.body).find("input[type=checkbox]").prop("checked", false).css("display", "none");

        $(".check_head label").click(function () {
            var flag = $("#select_auto").prop("checked");
            //  alert(flag);
            if (flag == true) {
                $("#pingbi").hide()
                $(".bged").addClass("bg").removeClass("bged");
//                alert("false");
                $.cookie('selectauto', 0, {path: '/'});
                clear();
            } else {
                if ($(".public_head").css("display") == "none") {
                    $("#pingbi").hide()
                }
                else {
                    $("#pingbi").show()
                }
                parent.$("#jump").prop("disabled",true)
                $(".bg").addClass("bged").removeClass("bg");
                $.cookie('selectauto', 1, {path: '/'});

                //alert("true");
                //选中后执行
                start_timer();
            }
        });
        //谷歌浏览器
        var isChrome = navigator.userAgent.toLowerCase().match(/chrome/) != null
        if (isChrome) {
            $(".fullscreen").css("margin-top", "0");
            $(".public_right_center").css("margin-top", "0")
        }
        //alert(navigator.userAgent.toLowerCase())
        var isie = navigator.userAgent.toLowerCase().match(/(ie)|(clr)|(msie)/) != null
        if (isie) {
            $(".fullscreen").css("margin-top", "0");
        }

        $(".fullscreen").click(function () {
            var show = $(this).attr("full");
            if (show == "false") {
                $("#pingbi").hide()
                $("#showhidden,.foot,.public_head,.xk_option").hide();
                $(this).attr("full", "true");
                console.log("screensplit===="+$.cookie("screen"))
                $(this).css({"background-position": "0 -109px"});
                if ($.cookie("screen") == 2) {
                    //四屏处理
                    var docheight = $("body").height();
                    var docwidth = $("body").width();
                    if (docwidth < 1200) {
                        docwidth = 1200
                    }
                    $(".public_right").css({"width": "100%", "height": "100%"})
                    $(".iframe").width(docwidth)
                    $(".iframe").height(docheight)
                    var iframewidth = docwidth;
                    var halfwidth = iframewidth / 2;
                    var iframeheight = docheight;
                    //iframe去除分页和文本框后的高度
                    var halfheight = iframeheight * 0.93 / 2 - 25 - 10;
                    //计算16/9的比例
                    var heicompare = halfwidth * 9 / 16;
                    if (heicompare > halfheight) {
                        //如果宽度的比例值高度大于iframe可用区域高度，则把高度默认为halfheight
                        var widcompare = halfheight * 16 / 9;
                        //alert(widcompare+"--"+halfwidth)
                        if (widcompare < halfwidth) {
                            var marginleft = (halfwidth - widcompare) / 3;
                            //alert((iframeheight*0.90/2-25-30)+"---"+(halfheight+25))

                            $(".iframe").contents().find(".allarea,.xk_video").height(halfheight + 25);
                            $(".iframe").contents().find(".allarea,.xk_video,.xk_video_text,.xk_video_twofour").width(widcompare);
                            $(".iframe").contents().find(".allarea").css({
                                "margin-left": marginleft * 2 + "px",
                                "margin-top": "5px"
                            })
                            $(".iframe").contents().find(".twofour").attr("height", halfheight);
                            $(".iframe").contents().find(".twoimg").css("height", halfheight + "px");
                            $(".iframe").contents().find(".xk_video_twofour,.xk_video_twofourobj").height(halfheight);
                            //教师学生全景模式
                            $(".iframe").contents().find(".xk_video_twofourobj").width(parseInt(widcompare / 2));
                            var twofourobjheight = $(".iframe").contents().find(".xk_video_twofourobj").height();
                            var twofourobjwidth = $(".iframe").contents().find(".xk_video_twofourobj").width();
                            var twofoursixtweenwidth = twofourobjheight * 16 / 9;
                            if (twofoursixtweenwidth > twofourobjwidth) {
                                var twofourxkvideoheight = twofourobjwidth * 9 / 16;
                                //alert(twofourxkvideoheight+"---"+twofoursixtweenwidth)
                                var twofourmargintop = (twofourobjheight - twofourxkvideoheight) / 2;
                                $(".iframe").contents().find(".fourfourimg").css("height", twofourxkvideoheight + "px");
                                $(".iframe").contents().find(".fourfourimg").css("margin-top", twofourmargintop + "px");
                                $(".iframe").contents().find(".fourfour").css("height", twofourxkvideoheight + "px");
                                $(".iframe").contents().find(".fourfour").css("width", "100%")
                                $(".iframe").contents().find(".fourfour").css("margin-top", twofourmargintop + "px")

                            }
                        } else {
                            //alert("从新计算")
                        }
                    } else {
                        //如果宽度的比例值高度小于iframe可用区域高度，则把宽度默认为计算值heicompare
                        $(".iframe").contents().find(".allarea,.xk_video").height(heicompare + 25);
                        $(".iframe").contents().find(".allarea,.xk_video,.xk_video_text,.xk_video_twofour").width(halfwidth - 10);
                        $(".iframe").contents().find(".allarea").css({"margin-top": "5px", "margin-left": "5px"})
                        $(".iframe").contents().find(".twofour").attr("height", heicompare);
                        $(".iframe").contents().find(".twoimg").css("height", heicompare + "px");
                        $(".iframe").contents().find(".xk_video_twofour,.xk_video_twofourobj").height(heicompare);
                        //教师学生全景模式
                        $(".iframe").contents().find(".xk_video_twofourobj").width((halfwidth - 10) / 2);
                        var twofourobjheight = $(".iframe").contents().find(".xk_video_twofourobj").height();
                        var twofourobjwidth = $(".iframe").contents().find(".xk_video_twofourobj").width();
                        var twofoursixtweenwidth = twofourobjheight * 16 / 9;
                        if (twofoursixtweenwidth > twofourobjwidth) {
                            var twofourxkvideoheight = twofourobjwidth * 9 / 16;
                            //alert(twofourxkvideoheight+"---"+twofoursixtweenwidth)
                            var twofourmargintop = (twofourobjheight - twofourxkvideoheight) / 2;
                            $(".iframe").contents().find(".fourfourimg").css("height", twofourxkvideoheight + "px");
                            $(".iframe").contents().find(".fourfourimg").css("margin-top", twofourmargintop + "px");
                            $(".iframe").contents().find(".fourfour").css("height", twofourxkvideoheight + "px");
                            $(".iframe").contents().find(".fourfour").css("width", "100%")
                            $(".iframe").contents().find(".fourfour").css("margin-top", twofourmargintop + "px")
                        }
                    }

                } else {
                    //九屏处理
                    //非全屏状态下效果
                    var docheight = $("body").height();
                    var docwidth = $("body").width();
                    if (docwidth < 1200) {
                        docwidth = 1200
                    }
                    $(".public_right").css({"width": "100%", "height": "100%"})
                    $(".iframe").width(docwidth)
                    $(".iframe").height(docheight)
                    var iframewidth = docwidth;
                    var halfwidth = iframewidth / 3;
                    var iframeheight = docheight;
                    //iframe去除分页和文本框后的高度
                    var halfheight = iframeheight * 0.93 / 3 - 19 - 10;
                    //计算16/9的比例
                    var heicompare = halfwidth * 9 / 16;
                    if (heicompare > halfheight) {
                        //如果宽度的比例值高度大于iframe可用区域高度，则把高度默认为halfheight
                        var widcompare = halfheight * 16 / 9;
                        //alert(widcompare+"--"+halfwidth)
                        if (widcompare < halfwidth) {
                            var marginleft = (halfwidth - widcompare) / 4;
                            $(".iframe").contents().find(".allarea,.xk_video_nine").height(halfheight + 19);
                            $(".iframe").contents().find(".allarea,.xk_video_nine,.xk_video_text_nine,.xk_video_twonine").width(widcompare);
                            $(".iframe").contents().find(".allarea").css({
                                "margin-left": marginleft * 3 + "px",
                                "margin-top": "5px"
                            })
                            $(".iframe").contents().find(".threenine").attr("height", halfheight);
                            $(".iframe").contents().find(".twoimg").css("height", halfheight + "px");
                            $(".iframe").contents().find(".xk_video_twonine,.xk_video_twonineobj").height(halfheight);
                            //教师学生全景模式
                            $(".iframe").contents().find(".xk_video_twonineobj").width(parseInt(widcompare / 2));
                            var twofourobjheight = $(".iframe").contents().find(".xk_video_twonineobj").height();
                            var twofourobjwidth = $(".iframe").contents().find(".xk_video_twonineobj").width();
                            var twofoursixtweenwidth = twofourobjheight * 16 / 9;
                            if (twofoursixtweenwidth > twofourobjwidth) {
                                var twofourxkvideoheight = twofourobjwidth * 9 / 16;
                                var twofourmargintop = (twofourobjheight - twofourxkvideoheight) / 2;
                                $(".iframe").contents().find(".threetwonine").css("height", twofourxkvideoheight + "px");
                                $(".iframe").contents().find(".threetwonine").css("width", "100%")
                                $(".iframe").contents().find(".threetwonine").css("margin-top", twofourmargintop + "px")
                                $(".iframe").contents().find(".fourfourimg").css("height", twofourxkvideoheight + "px")
                                //$(".fourfourimg").css("margin-top",twofourmargintop+"px")
                            }
                        } else {
                            //alert("从新计算")
                        }
                    } else {
                        //如果宽度的比例值高度小于iframe可用区域高度，则把宽度默认为计算值heicompare
                        $(".iframe").contents().find(".allarea,.xk_video_nine").height(heicompare + 19);
                        $(".iframe").contents().find(".allarea,.xk_video_nine,.xk_video_text_nine,.xk_video_twonine").width(halfwidth - 10);
                        $(".iframe").contents().find(".allarea").css({"margin-top": "5px", "margin-left": "5px"})
                        $(".iframe").contents().find(".threenine").attr("height", heicompare);
                        $(".iframe").contents().find(".twoimg").css("height", heicompare + "px");
                        $(".iframe").contents().find(".xk_video_twonine,.xk_video_twonineobj").height(heicompare);
                        //教师学生全景模式
                        $(".iframe").contents().find(".xk_video_twonineobj").width((halfwidth - 10) / 2);
                        var twofourobjheight = $(".iframe").contents().find(".xk_video_twonineobj").height();
                        var twofourobjwidth = $(".iframe").contents().find(".xk_video_twonineobj").width();
                        var twofoursixtweenwidth = twofourobjheight * 16 / 9;
                        if (twofoursixtweenwidth > twofourobjwidth) {
                            var twofourxkvideoheight = twofourobjwidth * 9 / 16;
                            var twofourmargintop = (twofourobjheight - twofourxkvideoheight) / 2;
                            $(".iframe").contents().find(".threetwonine").css("height", twofourxkvideoheight + "px");
                            $(".iframe").contents().find(".threetwonine").css("width", "100%")
                            $(".iframe").contents().find(".threetwonine").css("margin-top", twofourmargintop + "px")
                            $(".iframe").contents().find(".fourfourimg").css("height", twofourxkvideoheight + "px")
                            //$(".fourfourimg").css("margin-top",twofourmargintop+"px")
                        }
                    }
                }
                $(window).resize(function () {
                    $("#showhidden,.foot,.public_head,.xk_option").hide();
                    $(this).attr("full", "true");
                    $(this).css({"background-position": "0 -109px"});
                    if ($.cookie("screen") == "2") {
                        //四屏处理
                        var docheight = $("body").height();
                        var docwidth = $("body").width();
                        if (docwidth < 1200) {
                            docwidth = 1200
                        }
                        $(".public_right").css({"width": "100%", "height": "100%"})
                        $(".iframe").width(docwidth)
                        $(".iframe").height(docheight)
                        var iframewidth = docwidth;
                        var halfwidth = iframewidth / 2;
                        var iframeheight = docheight;
                        //iframe去除分页和文本框后的高度
                        var halfheight = iframeheight * 0.93 / 2 - 25 - 10;
                        //计算16/9的比例
                        var heicompare = halfwidth * 9 / 16;
                        if (heicompare > halfheight) {
                            //如果宽度的比例值高度大于iframe可用区域高度，则把高度默认为halfheight
                            var widcompare = halfheight * 16 / 9;
                            //alert(widcompare+"--"+halfwidth)
                            if (widcompare < halfwidth) {
                                var marginleft = (halfwidth - widcompare) / 3;
                                //alert((iframeheight*0.90/2-25-30)+"---"+(halfheight+25))

                                $(".iframe").contents().find(".allarea,.xk_video").height(halfheight + 25);
                                $(".iframe").contents().find(".allarea,.xk_video,.xk_video_text,.xk_video_twofour").width(widcompare);
                                $(".iframe").contents().find(".allarea").css({
                                    "margin-left": marginleft * 2 + "px",
                                    "margin-top": "5px"
                                })
                                $(".iframe").contents().find(".twofour").attr("height", halfheight);
                                $(".iframe").contents().find(".twoimg").css("height", halfheight + "px");
                                $(".iframe").contents().find(".xk_video_twofour,.xk_video_twofourobj").height(halfheight);
                                //教师学生全景模式
                                $(".iframe").contents().find(".xk_video_twofourobj").width(parseInt(widcompare / 2));
                                var twofourobjheight = $(".iframe").contents().find(".xk_video_twofourobj").height();
                                var twofourobjwidth = $(".iframe").contents().find(".xk_video_twofourobj").width();
                                var twofoursixtweenwidth = twofourobjheight * 16 / 9;
                                if (twofoursixtweenwidth > twofourobjwidth) {
                                    var twofourxkvideoheight = twofourobjwidth * 9 / 16;
                                    //alert(twofourxkvideoheight+"---"+twofoursixtweenwidth)
                                    var twofourmargintop = (twofourobjheight - twofourxkvideoheight) / 2;
                                    $(".iframe").contents().find(".fourfourimg").css("height", twofourxkvideoheight + "px");
                                    $(".iframe").contents().find(".fourfourimg").css("margin-top", twofourmargintop + "px");
                                    $(".iframe").contents().find(".fourfour").css("height", twofourxkvideoheight + "px");
                                    $(".iframe").contents().find(".fourfour").css("width", "100%")
                                    $(".iframe").contents().find(".fourfour").css("margin-top", twofourmargintop + "px")

                                }
                            } else {
                                //alert("从新计算")
                            }
                        } else {
                            //如果宽度的比例值高度小于iframe可用区域高度，则把宽度默认为计算值heicompare
                            $(".iframe").contents().find(".allarea,.xk_video").height(heicompare + 25);
                            $(".iframe").contents().find(".allarea,.xk_video,.xk_video_text,.xk_video_twofour").width(halfwidth - 10);
                            $(".iframe").contents().find(".allarea").css({"margin-top": "5px", "margin-left": "5px"})
                            $(".iframe").contents().find(".twofour").attr("height", heicompare);
                            $(".iframe").contents().find(".twoimg").css("height", heicompare + "px");
                            $(".iframe").contents().find(".xk_video_twofour,.xk_video_twofourobj").height(heicompare);
                            //教师学生全景模式
                            $(".iframe").contents().find(".xk_video_twofourobj").width((halfwidth - 10) / 2);
                            var twofourobjheight = $(".iframe").contents().find(".xk_video_twofourobj").height();
                            var twofourobjwidth = $(".iframe").contents().find(".xk_video_twofourobj").width();
                            var twofoursixtweenwidth = twofourobjheight * 16 / 9;
                            if (twofoursixtweenwidth > twofourobjwidth) {
                                var twofourxkvideoheight = twofourobjwidth * 9 / 16;
                                //alert(twofourxkvideoheight+"---"+twofoursixtweenwidth)
                                var twofourmargintop = (twofourobjheight - twofourxkvideoheight) / 2;
                                $(".iframe").contents().find(".fourfourimg").css("height", twofourxkvideoheight + "px");
                                $(".iframe").contents().find(".fourfourimg").css("margin-top", twofourmargintop + "px");
                                $(".iframe").contents().find(".fourfour").css("height", twofourxkvideoheight + "px");
                                $(".iframe").contents().find(".fourfour").css("width", "100%")
                                $(".iframe").contents().find(".fourfour").css("margin-top", twofourmargintop + "px")
                            }
                        }

                    } else {
                        //九屏处理
                        //非全屏状态下效果
                        var docheight = $("body").height();
                        var docwidth = $("body").width();
                        if (docwidth < 1200) {
                            docwidth = 1200
                        }
                        $(".public_right").css({"width": "100%", "height": "100%"})
                        $(".iframe").width(docwidth)
                        $(".iframe").height(docheight)
                        var iframewidth = docwidth;
                        var halfwidth = iframewidth / 3;
                        var iframeheight = docheight;
                        //iframe去除分页和文本框后的高度
                        var halfheight = iframeheight * 0.93 / 3 - 19 - 10;
                        //计算16/9的比例
                        var heicompare = halfwidth * 9 / 16;
                        if (heicompare > halfheight) {
                            //如果宽度的比例值高度大于iframe可用区域高度，则把高度默认为halfheight
                            var widcompare = halfheight * 16 / 9;
                            //alert(widcompare+"--"+halfwidth)
                            if (widcompare < halfwidth) {
                                var marginleft = (halfwidth - widcompare) / 4;
                                $(".iframe").contents().find(".allarea,.xk_video_nine").height(halfheight + 19);
                                $(".iframe").contents().find(".allarea,.xk_video_nine,.xk_video_text_nine,.xk_video_twonine").width(widcompare);
                                $(".iframe").contents().find(".allarea").css({
                                    "margin-left": marginleft * 3 + "px",
                                    "margin-top": "5px"
                                })
                                $(".iframe").contents().find(".threenine").attr("height", halfheight);
                                $(".iframe").contents().find(".twoimg").css("height", halfheight + "px");
                                $(".iframe").contents().find(".xk_video_twonine,.xk_video_twonineobj").height(halfheight);
                                //教师学生全景模式
                                $(".iframe").contents().find(".xk_video_twonineobj").width(parseInt(widcompare / 2));
                                var twofourobjheight = $(".iframe").contents().find(".xk_video_twonineobj").height();
                                var twofourobjwidth = $(".iframe").contents().find(".xk_video_twonineobj").width();
                                var twofoursixtweenwidth = twofourobjheight * 16 / 9;
                                if (twofoursixtweenwidth > twofourobjwidth) {
                                    var twofourxkvideoheight = twofourobjwidth * 9 / 16;
                                    var twofourmargintop = (twofourobjheight - twofourxkvideoheight) / 2;
                                    $(".iframe").contents().find(".threetwonine").css("height", twofourxkvideoheight + "px");
                                    $(".iframe").contents().find(".threetwonine").css("width", "100%")
                                    $(".iframe").contents().find(".threetwonine").css("margin-top", twofourmargintop + "px")
                                    $(".iframe").contents().find(".fourfourimg").css("height", twofourxkvideoheight + "px")
                                    //$(".fourfourimg").css("margin-top",twofourmargintop+"px")
                                }
                            } else {
                                //alert("从新计算")
                            }
                        } else {
                            //如果宽度的比例值高度小于iframe可用区域高度，则把宽度默认为计算值heicompare
                            $(".iframe").contents().find(".allarea,.xk_video_nine").height(heicompare + 19);
                            $(".iframe").contents().find(".allarea,.xk_video_nine,.xk_video_text_nine,.xk_video_twonine").width(halfwidth - 10);
                            $(".iframe").contents().find(".allarea").css({"margin-top": "5px", "margin-left": "5px"})
                            $(".iframe").contents().find(".threenine").attr("height", heicompare);
                            $(".iframe").contents().find(".twoimg").css("height", heicompare + "px");
                            $(".iframe").contents().find(".xk_video_twonine,.xk_video_twonineobj").height(heicompare);
                            //教师学生全景模式
                            $(".iframe").contents().find(".xk_video_twonineobj").width((halfwidth - 10) / 2);
                            var twofourobjheight = $(".iframe").contents().find(".xk_video_twonineobj").height();
                            var twofourobjwidth = $(".iframe").contents().find(".xk_video_twonineobj").width();
                            var twofoursixtweenwidth = twofourobjheight * 16 / 9;
                            if (twofoursixtweenwidth > twofourobjwidth) {
                                var twofourxkvideoheight = twofourobjwidth * 9 / 16;
                                var twofourmargintop = (twofourobjheight - twofourxkvideoheight) / 2;
                                $(".iframe").contents().find(".threetwonine").css("height", twofourxkvideoheight + "px");
                                $(".iframe").contents().find(".threetwonine").css("width", "100%")
                                $(".iframe").contents().find(".threetwonine").css("margin-top", twofourmargintop + "px")
                                $(".iframe").contents().find(".fourfourimg").css("height", twofourxkvideoheight + "px")
                                //$(".fourfourimg").css("margin-top",twofourmargintop+"px")
                            }
                        }
                    }
                });
            } else {
                location.reload()
            }
        })

        //根据cookie判断自动轮巡
        var selectauto = $.cookie('selectauto');
        if (selectauto == 1) {
            $(".check_head label").click();


        }
        else {
            $("#pingbi").hide()
        }

    })
    function directorView(hostid, token, hostname, recordType) {
        closeVideo();
        hostname = encodeURI(hostname);
        if (recordType == "1") {//精品录播
            $.post("${pageContext.request.contextPath}/viewclass/Viewclass_gotoDirectView.do", {token: token}, function (data) {
            }, 'json');
        }
        location.href = "${pageContext.request.contextPath}/viewclass/Viewclass_directorView.do?hostid=" + hostid
                + "&token=" + token + "&hostname=" + hostname + "&recordType=" + recordType+ "&currentPage=" + nowselectpage;
    }

    function directorCamera(hostid){
        closeVideo();
        location.href = "${pageContext.request.contextPath}/viewclass/Viewclass_splitscreen.do?hostid=" + hostid
                + "&currentPage=" + nowselectpage;
    }

    /* function split(hostid, token, hostname, recordType) {
     closeVideo();
     hostname = encodeURI(hostname);
     if (recordType == "1") {//精品录播
     $.post("${pageContext.request.contextPath}/splitscreen/splitscreen.do", {token: token}, function (data) {
     }, 'json');
     }
     location.href = "${pageContext.request.contextPath}/splitscreen/splitscreen.do?hostid=" + hostid + "&token=" + token + "&hostname=" + hostname + "&recordType=" + recordType;
     }*/
    function closeVideo() {
        var soArray = $(window.frames["go"].document).find("object[name='ScriptableObject']");
        $.each(soArray, function (i, value) {
            try {
                value.stop();
            } catch (e) {
            }
        });
    }

    function recording() {
        /**
         *
         */
        var hostid = $("#selected_host").val();
        var hostip = $("#selected_host").attr("ip");
        var classmessage = $("#classmessage").html();
        if (hostid != "") {
            $("#classmessage").html("");
            var recordType = $("#go").contents().find("#host_status_" + hostid).attr("recordType");
            $.post(root_path + '/viewclass/Viewclass_startRecord.do', {
                hostid: hostid,
                recordType: recordType
            }, function (data) {
                if (data.success == "true") {
                    $("#go").contents().find("#record_" + hostid).removeClass().addClass("xk_circlerecord");
                    $("#record_vedio").attr("onclick", "");
                    $("#record_vedio_img").attr("class", "xk_options_recordingicon_disable");
                    //.removeClass("xk_options_recordingicon").addClass("xk_options_recordingicon_disable");
                    $("#stop_record_vedio").attr("onclick", "stoprecord()");
                    $("#stop_record_vedio_img").removeClass("xk_options_stoprecordingicon_disable").addClass("xk_options_stoprecordingicon");
                    $("#go").contents().find("#host_status_" + hostid).val("Online-true");
                    $("#go").contents().find("#record_" + hostid).show();
                    $("#go").contents().find("#recordstop_" + hostid).hide();
                    $("#go").contents().find("#message_" + hostid).html("");
                    if (data.desc) {
                        $("#go").contents().find("#nas_no_" + hostid).show().html(data.desc);
                        setTimeout(function () {
                            $("#go").contents().find("#nas_no_" + hostid).hide()
                        }, 3000)
                    }
                }
                else {
                    //$("#go").contents().find("#record_"+hostid).html("未录制");
//                    if (data.msg == "已经开始录制") {
//                        $("#go").contents().find("#record_" + hostid).removeClass().addClass("xk_circlerecord");
//                        $("#record_vedio").attr("onclick", "");
//                        $("#record_vedio_img").removeClass("xk_options_recordingicon").addClass("xk_options_recordingicon_disable");
//                        $("#stop_record_vedio").attr("onclick", "stoprecord()");
//                        $("#stop_record_vedio_img").removeClass("xk_options_stoprecordingicon_disable").addClass("xk_options_stoprecordingicon");
//                        $("#go").contents().find("#host_status_" + hostid).val("Online-RecordingState_Recording");
//                    }
                    if (data.msg == "nas未设置") {
                        $("#go").contents().find("#nas_no_" + hostid).show();
                        var str = "<%=isSeting%>";//是否有系统设置权限
                        if (str == "true") {
                            $("#go").contents().find("#nas_no_" + hostid).html("<a href='javascript:void(0)' onclick='jumpSetting()' style=\"color:#28b779;text-decoration:underline;\">请设置Nas服务器 点击设置</a>");
                        } else {
                            $("#go").contents().find("#nas_no_" + hostid).html("Nas服务器未设置");
                        }
                        setTimeout("showNasError(" + hostid + ")", 5000);
                        //$("#go").contents().find("#nas_no_"+hostid).show();

                        //alert("nas未设置");
                    } else {
                        $("#go").contents().find("#message_" + hostid).html(data.msg + "!");
                    }
                }
            })
        }
        else {
            $("#classmessage").html("请选择一个班级!");
        }
    }
    function showNasError(hostid) {
        //alert(123)
        $("#go").contents().find("#nas_no_" + hostid).hide();
        $("#go").contents().find("#nas_no_" + hostid).html("");
    }
    function stoprecord() {
        var hostid = $("#selected_host").val();
        var hostip = $("#selected_host").attr("ip");
        var classmessage = $("#classmessage").html();
        if (hostid != "") {
            $("#classmessage").html("");
            $.post(root_path + '/viewclass/Viewclass_stopRecord.do', {hostid: hostid, hostIp: hostip}, function (data) {
                if (data.success == "true") {
//                    $("#go").contents().find("#record_" + hostid).html("未录制");
                    $("#go").contents().find("#record_" + hostid).removeClass().addClass("xk_circlestop");
                    $("#record_vedio").attr("onclick", "recording()");
                    $("#record_vedio_img").removeClass("xk_options_recordingicon_disable").addClass("xk_options_recordingicon");
                    $("#stop_record_vedio").attr("onclick", "");
                    $("#stop_record_vedio_img").removeClass("xk_options_stoprecordingicon").addClass("xk_options_stoprecordingicon_disable");
                    $("#go").contents().find("#host_status_" + hostid).val("Online-false");
                    $("#go").contents().find("#record_" + hostid).hide();
                    $("#go").contents().find("#recordstop_" + hostid).show();
                    //layer.alert(data.msg,"","提示信息");
                    $("#go").contents().find("#message_" + hostid).html("");
                }
                else {
                    //layer.alert(data.msg, "","提示信息")
//                    $("#go").contents().find("#message_" + hostid).html(data.msg + "!");
                    $(".warn").html(data.msg + "!");
                    setTimeout(function(){
                        $(".warn").html('');
                    },1500)
                }
            })
        }
        else {
            // layer.alert("请选择一个班级", "","提示信息");
            $("#classmessage").html("请选择一个班级!");
        }
    }
    /**
     *开机
     */
    function wakeup() {
        var hostid = $("#selected_host").val();        //
        var classmessage = $("#classmessage").html();
        if (hostid != "") {
            $("#classmessage").html("");
            $.post(root_path + '/viewclass/Viewclass_start.do', {hostid: hostid});
            if ($(".xk_fournine div").eq(0).css("display") == "block") {
                var xkselectlen = $(".iframe").contents().find(".xk_video_selected").length;
                if (xkselectlen == 1) {
                    var groupname = $(".iframe").contents().find(".xk_video_selected").find(".xk_video_group").children("div").eq(1).text();
                    $(".xk_video_group_warn").show()
                    $(".xk_video_group_warn").text(groupname + "开机中，请耐心等待...")
                }
            } else {
                var xkselectlen = $(".iframe").contents().find(".xk_video_selected_nine").length;
                if (xkselectlen == 1) {
                    var groupname = $(".iframe").contents().find(".xk_video_selected_nine").find(".xk_video_group_nine").children("div").eq(1).text();
                    $(".xk_video_group_warn").show()
                    $(".xk_video_group_warn").text(groupname + "开机中，请耐心等待...")
                }
            }

        }
        else {
            $("#classmessage").html("请选择一个班级!");
        }
    }
    /**
     *关机
     */
    function shutdown() {
        var hostid = $("#selected_host").val();        //
        var classmessage = $("#classmessage").html();
        if (hostid != "") {
            $("#classmessage").html("");
            $.post(root_path + '/viewclass/Viewclass_shutdown.do', {hostid: hostid}, function (data) {
                if (data.success == true) {

                } else {

                }
            });
            if ($(".xk_fournine div").eq(0).css("display") == "block") {
                var xkselectlen = $(".iframe").contents().find(".xk_video_selected").length;
                if (xkselectlen == 1) {
                    var groupname = $(".iframe").contents().find(".xk_video_selected").find(".xk_video_group").children("div").eq(1).text();
                    $(".xk_video_group_warn").show()
                    $(".xk_video_group_warn").text(groupname + "关机中，请耐心等待...")
                }
            } else {
                var xkselectlen = $(".iframe").contents().find(".xk_video_selected_nine").length;
                if (xkselectlen == 1) {
                    var groupname = $(".iframe").contents().find(".xk_video_selected_nine").find(".xk_video_group_nine").children("div").eq(1).text();
                    $(".xk_video_group_warn").show()
                    $(".xk_video_group_warn").text(groupname + "关机中，请耐心等待...")
                }
            }
        }
        else {
            $("#classmessage").html("请选择一个班级!");
        }
    }
    //测试
    //    setTimeout(function(){
    //        location.href = "http://localhost:8080/record/viewclass/Viewclass_directorView.do?hostid=19&token=192.168.69.21-ONVIF&hostname=%E9%AB%98%E4%B8%89%E4%BA%8C%E7%8F%AD&recordType=0";
    //    },5000);

    function jumpPage(page) {
        nowselectpage = parseInt(page);
        $.cookie('curPage', nowselectpage, {path: '/'});
        $("#go").contents().find("#jump").val(nowselectpage);
        var viewClassCameraName = $.cookie("camera");
        var split = $.cookie("screen");
        // var hostIdStr = deviceIDByPage(split * split, nowselectpage);
        var pageCount = countPage(hostIds);
        if (split == 2) {
            videoShow(viewClassCameraName,hostIds)
            // viewClass4Ajax(viewClassCameraName, hostIdStr, nowselectpage, pageCount);
        }
        else {
            videoShow(viewClassCameraName,hostIds)
            // viewClass9Ajax(viewClassCameraName, hostIdStr, nowselectpage, pageCount);
        }
    }
    $(function(){
        $(".xk_split_camera").mouseover(function(){
            $(this).css("background-position","0px -24px")
        }).mouseleave(function(){
            $(this).css("background-position","0px 0px")
        })
        $(".xk_video_camera").mouseover(function(){
            $(this).css("background-position","-117px -71px")
        }).mouseleave(function(){
            $(this).css("background-position","-117px -92px")
        })
    })
</script>
<script src="${pageContext.request.contextPath}/js/lb_frontend/device/tree.js"></script>
</html>

