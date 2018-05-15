<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>集控后台首页</title>
    <link href="${pageContext.request.contextPath}/css/project/index.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/project/main.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/common/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/project/placeholder.js"></script>
    <script src="${pageContext.request.contextPath}/js/project/public.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
    <script>
        $(function () {
////checkbox美化
//                $(document.body).find("input[type=checkbox]").prop("checked", false).css("display", "none");
//                $(".check_head").click(function () {
//                    var flag = $(".check_head").find("input[type=checkbox]").prop("checked");
//                    if (flag == true) {
//                        $(".check_head").find("input[type=checkbox]").prop("checked", false);
//                        $(".check_head").find("input[type=checkbox]").next("div").removeClass("bged").addClass("bg");
//                        //alert(flag+"a")
//                    } else {
//                        $(".check_head").find("input[type=checkbox]").prop("checked", true);
//                        $(".check_head").find("input[type=checkbox]").next("div").removeClass("bg").addClass("bged");
//                    }
//                })
//自适应屏幕高度--登录
            var heightlogin=document.documentElement.clientHeight-208-30;
            if(heightlogin<502){
                $(".public_login_form").css("height","502px");
            }else{
                $(".public_login_form").css("height",heightlogin+"px");
            }
            $(window).resize(function() {
                if(heightlogin<502){
                    $(".public_login_form").css("height","502px");
                }
                if(heightlogin>=502){
                    $(".public_login_form").css("height",heightlogin+"px");
                }
            })
        })
    </script>
</head>
<body>
<%

    response.setHeader("Cache-Control","no-store");

    response.setHeader("Pragma","no-cache");

    response.setDateHeader("Expires",0);

%>
<div class="public">
    <input type="hidden" value="${pageContext.request.contextPath}" id="root_path">
    <table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
        <tr>
            <td width="100%" height="100%" colspan="2">
                <div class="public_head_login">
                    <div class="public_head_logo_login"></div>
                </div>
            </td>
        </tr>
        <tr>
            <td width="260px">
                <div class="public_login_form">
                    <div class="public_login_form_center">
                        <input name="login_name" class="public_login_input" type="text" placeholder="用户名"/>
                        <input name="login_pass" class="public_login_input pass" type="password" placeholder="密码"/>

                        <div class="public_login_input" style="border: none;">

                            <div class="public_login_btn">登录</div>
                        </div>
                    </div>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2" width="100%">
                <div class="foot">
                    <div class="foot_center">鸿合科技版权所有</div>
                </div>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
<script>
    var rootpath = $("#root_path").val();
    $(function() {
        $("input[name='login_name']").focus();
        //设置弹出框layer的top值，以及layer关闭后的焦点
        function setlayertop(a){
            var screenheight=window.screen.height*(0.35)+"px";
            $(".xubox_layer").css("top",screenheight);
            if(a==true){
                $(".xubox_yes").click(function(){
                    $("input[name='login_name']").focus();
                })
                $(".xubox_close").click(function(){
                    $("input[name='login_name']").focus();
                })
            }else if(a==false){
                $(".xubox_yes").click(function(){
                    $("input[name='login_pass']").focus();
                })
                $(".xubox_close").click(function(){
                    $("input[name='login_pass']").focus();
                })
            }
            else
            {
                $("#xubox_layer1").css("top","40%");
            }
        }
        //登录按钮click事件
        $(".public_login_btn").click(function () {
            var name = $("input[name='login_name']").val();
            if(name=="")
            {
                layer.alert("用户名不能为空");
                setlayertop(true);
                return false;
            }
            var pwd = $("input[name='login_pass']").val();
            if(pwd=="")
            {

                layer.alert("密码不能为空");
                setlayertop(false);
                return false;
            }
            $.post(rootpath+"/userback/Userback_login.do", {login_name: name,login_pass:pwd}, function (data) {
                if(data.success==true)
                {
                    var path = rootpath+"/pages/project/index.jsp";
                    location.href=path;
                }
                else
                {
                    layer.alert(data.msg);
                    setlayertop();
                }
            },'json');
        });

        //回车按键进入登录按钮click事件
        $(".pass").keypress(function (e) {
            var keyCode = e.keyCode ? e.keyCode : e.which ? e.which : e.charCode;
            if (keyCode == 13){
                if($("input[name='login_name']").val()=="")
                {
                    $("input[name='login_name']").focus();
                    return false;
                }
                if($("input[name='login_pass']").val()=="")
                {
                    $("input[name='login_pass']").focus();
                    return false;
                }

                $(".public_login_btn").click();
                return false;
            }
            else
                return true;
        });

    });

</script>
