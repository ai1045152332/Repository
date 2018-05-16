<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="static/css/ch-ui.admin.css">
    <link rel="stylesheet" href="static/font/css/font-awesome.min.css">
</head>
<body style="background:#F3F3F4;" onload="start();">
<div class="login_box">
    <h1>CloudNote</h1>
    <h2>欢迎使用云笔记平台</h2>
    <div class="form">
        <p>
            &nbsp;<span id="nameMessage" style="color:red"></span>
        </p>
        <form action="http://localhost/cloudnote/login" method="post">
            <ul>
                <li>
                    <input type="text" name="username" class="text" onblur="check(this.value);" onfocus="clearError()"/>
                    <span><i class="fa fa-user"></i></span>
                </li>
                <li>
                    <input type="password" name="password" class="text"/>
                    <span><i class="fa fa-lock"></i></span>
                </li>
                <li>
                    <input type="text" class="code" name="code"/>
                    <span><i class="fa fa-check-square-o"></i></span>
                    <img src="{{url('admin/code')}}" alt="" onclick="this.src='{{url('admin/code')}}?'+Math.random()">
                </li>
                <li>
                    <input type="submit" value="立即登陆"/>
                </li>
            </ul>
        </form>
        <p><a href="#">返回首页</a> &copy; 2018 Powered by <a href="#" target="_blank">现科计科1501赵健宇</a></p>
        <div id = "time"></div>

    </div>
    <div>
        <%=%>request.getSession().getAttribute("loginName")%>

    </div>
</div>
</body>
<script type="text/javascript">
    var usernameFlag = false;
    function check(username){
        if(username == ""){
            nameMessage.innerHTML="用户名不能为空";
            usernameFlag = false;
        }else if(username.length>14||username.length<5){
            usernameFlag = false;
            nameMessage.innerHTML="用户名要在5-14之间";
        }else{
            usernameFlag = true;
        }
    }
    function  clearError(){
        nameMessage.innerHTML="";
    }
    function checkAll(){
        return usernameFlag;
    }


    function start(){
        window.setInterval("showDate()",1000);
        //每隔1s更新
    }
    function showDate(){
        var divTime = document.getElementById("time");
        var d = new Date();
        divTime.innerHTML = d.toLocaleString();//转换当地时间
    }
</script>
</html>