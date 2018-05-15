<%@ page import="com.honghe.recordweb.util.LicenseUtils" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.honghe.version.Version" %>
<%@ page import="com.honghe.recordweb.util.ConfigureUtil" %>
<%--
  Created by IntelliJ IDEA.
  User: Moon
  Date: 2014/8/5
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <%--<%--%>
    <%--String webSitePath = "http://"+request.getLocalAddr() + ":" + request.getLocalPort()+request.getContextPath();--%>
    <%--%>--%>
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10"/>
        <title>集控平台</title>
    <link href="${pageContext.request.contextPath}/css/frontend/index.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/common/jquery-1.9.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/checkbox_login.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/jquery.cookie.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/screendetail.js"></script>
    <script language="javascript" type="text/javascript">
        if (top.location != self.location)top.location = self.location;
    </script>
    <style>
        .n_loginheadtxt {
            color: #28b779;
            float: right;
            height: 25px;
            line-height: 25px;
            margin-right: 150px;
            width: auto;
            margin-top: 42px;
        }
        </style>
</head>
<body class="bgf2f2f2">
<%

    response.setHeader("Cache-Control", "no-store");

    response.setHeader("Pragrma", "no-cache");

    response.setDateHeader("Expires", 0);

    boolean isHhrec = ConfigureUtil.isOnlyHhrec();//判断是否只是录播设备

%>
<input type="hidden" value="${pageContext.request.contextPath}" id="root_path">

<div class="public" style="background: white;">
    <%
        if(ConfigureUtil.isHhrec()){//包含录播
            if(isHhrec){
    %>
    <div class="n_loginhead2">
         <a href="/VideoPlugin.exe"><div class="n_loginheadtxt">下载插件</div></a>
    </div>
    <%
             }else{
    %>
    <div class="n_loginhead">
        <a href="/VideoPlugin.exe"><div class="n_loginheadtxt">下载插件</div></a>
    </div>
    <%
             }
        }else{
    %>
        <div class="n_loginhead"></div>
    <%
        }
    %>


    <div class="n_login">
    	<div class="login_screen">适用版本为IE10及以上，最小分辨率1280×800px</div>
    <div class="login_center">
        
        <div class="n_login_leftpic"></div>
        <div class="n_login_rightvideopic"></div>
        <div class="n_login_rightlogin">
            <span class="login_text" style="margin-top: 29px;font-size: 18px;">用户登录</span>
            <input id="login_name" name="login_name" type="text" class="login_input username" placeholder="请输入用户名"
                   style="margin-top: 29px;" tabIndex="1"/>
            <input id="login_pass" name="login_pass" type="password" class="login_input pass" placeholder="请输入密码"   tabIndex="2"/>
				<span class="login_text" style="margin-top: 12px;">
					<div class="checkbox">
                        <input id="login_rememberme" type="checkbox" name="login_rememberme"/>
                    </div>
					<div class="all">
                        <div class="head">
                            <div class="bg" id="remember" tabIndex="3"></div>
                            记住我
                        </div>
                    </div>
				</span>
            <span tabIndex="3" style="width: 280px;display: block;height:50px;clear: both;margin-top: 25px;margin-left: 30px;float: left">
            <span class="login_submit" tabIndex="3" style="margin: 0" >登录</span></span>

        </div>
        </div>
    </div>
    <%
        String versionInfo = Version.getVersionInfo();
        Map<String,String> data = LicenseUtils.findLicense();
        String licenseInfo = "未授权";
        if(!data.isEmpty()){
            licenseInfo = data.get("device_desc");
        }
        String vp = Version.getVersionInfo("vp");
    %>
     <div class="foot">
    <%--<jsp:include page="/pages/frontend/footer.jsp"></jsp:include>--%>
         <div class="foot_center">&copy;2003-2016HiteVision.All Rights Reserved <%=versionInfo%> <%=licenseInfo%></div>
    </div>
</div>
</body>

<%
    if(ConfigureUtil.isHhrec()){//包含录播
%>
<OBJECT ID="ScriptableObject" codeBase="vp.CAB#version=<%=vp%>"
        CLASSID="CLSID:5a22176d-118f-4185-9653-9f98958a6df8"
        WIDTH=150 HEIGHT=150 style="display: none"/>
<%
    }
%>

<%--<object id="ScriptableObject" TYPE="trivial/very" style="display:none"></object>--%>
</html>
<script>
    var user = document.getElementById("login_name"),
            pass = document.getElementById("login_pass"),
            check =  document.getElementById("login_rememberme"),
            loUser = localStorage.getItem("user") || "";
    loPass = localStorage.getItem("pass") || "";
    user.value = loUser;
    pass.value = loPass;
    if(loUser != "" && loPass != ""){
        check.checked=true;
        document.getElementsByClassName("bg")[0].className="bged";
    }
    var rootpath = $("#root_path").val();
    $(function () {

        try {
            var scriptableObject = document.getElementById('ScriptableObject');
            ScriptableObject.sltSetFunctionShownState("Captions", "hide");
        } catch (e) {
            //window.open(rootpath + '/videoPlay.exe');
        }

        //谷歌浏览器
        var isChrome = navigator.userAgent.toLowerCase().match(/chrome/) != null
        if (isChrome) {
            $(".login_input").css("line-height", "18px")
        }

        $("input[name='login_name']").focus();
        //设置弹出框layer的top值，以及layer关闭后的焦点
        function setlayertop(a) {
            var screenheight = window.screen.height * (0.35) + "px";
            $(".xubox_layer").css("top", screenheight);
            if (a == true) {
                $(".xubox_yes").click(function () {
                    $("input[name='login_name']").focus();
                })
                $(".xubox_close").click(function () {
                    $("input[name='login_name']").focus();
                })
            } else if (a == false) {
                $(".xubox_yes").click(function () {
                    $("input[name='login_pass']").focus();
                })
                $(".xubox_close").click(function () {
                    $("input[name='login_pass']").focus();
                })
            }
            else {
                $("#xubox_layer1").css("top", "40%");
            }
        }

        //登录按钮click事件
        $(".login_submit").click(function () {

            if(check.checked){
                localStorage.setItem("user",user.value);
                localStorage.setItem("pass",pass.value);
            }else{
                localStorage.setItem("user","");
                localStorage.setItem("pass","");
            }


            var name = $("input[name='login_name']").val();
            if (name == "") {
                layer.msg("用户名不能为空");
//              $.layer({
//				    shade: [0.5],
//				    title:'提示信息',
//				    area: ['auto','100'],
//				    time: 2,
//				    dialog: {
//				        msg: '用户名不能为空!',
//				        btns: 0,                    
//				        type: 0
//				    }
//				});
                setlayertop(true);
                return false;
            }
            var pwd = $("input[name='login_pass']").val();
            if (pwd == "") {

                layer.msg("密码不能为空");
//              $.layer({
//				    shade: [0.5],
//				    title:'提示信息',
//				    area: ['auto','100'],
//				    time: 2,
//				    dialog: {
//				        msg: '密码不能为空!',
//				        btns: 0,                    
//				        type: 0
//				    }
//				});
                setlayertop(false);
                return false;
            }

            //执行记住用户名密码
            var remember = $("input:checkbox[name='login_rememberme']").prop("checked");
            $.post(rootpath + "/user/User_login.do", {
                userName: name,
                login_pass: pwd,
                login_rememberme: remember
            }, function (data) {
                if (data.success == true) {
                    var path = rootpath + data.msg;
                    location.href = path;
                }
                else {
                    layer.msg(data.msg);
                    setlayertop();
                }
            }, 'json');
        });

        //回车按键进入登录按钮click事件
        $(".pass").keypress(function (e) {
            var keyCode = e.keyCode ? e.keyCode : e.which ? e.which : e.charCode;
            if (keyCode == 13) {
                if ($("input[name='login_name']").val() == "") {
                    $("input[name='login_name']").focus();
                    return false;
                }
                if ($("input[name='login_pass']").val() == "") {
                    $("input[name='login_pass']").focus();
                    return false;
                }

                $(".login_submit").click();
                return false;
            }
            else
                return true;
        });

    });
    //记住我响应空格键方法
    document.onkeydown=function(event){
        var e = event || window.event || arguments.callee.caller.arguments[0];
        var isTab = document.activeElement.id=="remember";
        if(e && e.keyCode==32&& isTab){ // 按 space
            var className = $(".head > div").attr("class");
            if(className=="bged"){
                $(".head").empty();
                $(".head").append('<div class="bg" id="remember" tabIndex="3"></div>记住我');
                $("#remember").focus();
                $("input[type=checkbox]").prop("checked",false);
            }else{
                $(".head").empty();
                $(".head").append('<div class="bged" id="remember" tabIndex="3"></div>记住我');
                $("#remember").focus();
                $("input[type=checkbox]").prop("checked",true);
            }
        }

    };
</script>