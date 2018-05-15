<%@ page import="com.honghe.recordhibernate.entity.User" %>
<%@ page import="com.honghe.recordweb.action.SessionManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>集控后台首页</title>
    <link href="${pageContext.request.contextPath}/css/project/index.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/project/main.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/project/public.js"></script>
    <script src="${pageContext.request.contextPath}/js/project/checkbox.js"></script>
</head>
<body>
<input type="hidden" value="${pageContext.request.contextPath}" id="root_path">
<div class="all" style="display: none;">
    <input type="checkbox" />
    <input type="checkbox" />
</div>
<div class="public">
    <table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
        <tr>
            <td width="100%" height="100%" colspan="2">
                <div class="public_head">
                    <div class="public_head_logo"></div>
                    <div class=" public_adminout">
                        <%User user = (User) SessionManager.get(request.getSession(), SessionManager.Key.USERBACK);%>
                        <a href="javascript:void(0)" class="public_admin" style="color: #fff;">您好，<%=user.getUserName()%></a>
                        <%--<a href="javascript:logout()" style="color: #fff;">退出</a>--%>
                        <a href="${pageContext.request.contextPath}/userback/Userback_logout.do" style="color: #fff;">退出</a>
                    </div>
                </div>
            </td>
        </tr>
        <tr>
            <td width="260px" style="float:left;">
                <div class="public_left">

                    <div class="tree_title tree_title_close ">
                        <a href="javascript:void(0)" linktype="roleManager" linkpage="${pageContext.request.contextPath}/auth/Authority_authorityList.do" class="tree_titlea zz">权限管理</a>
                    </div>
                    <div class="tree_title tree_title_close ">
                        <a href="javascript:void(0)" linktype="userManager" linkpage="${pageContext.request.contextPath}/userback/Userback_userList.do" class="tree_titlea">用户管理</a>
                    </div>
                    <div class="tree_title tree_title_close">
                        <a href="javascript:void(0)" linktype="roleManager" linkpage="${pageContext.request.contextPath}/roleback/Roleback_roleListBack.do"  class="tree_titlea">角色管理</a>
                    </div>
                    <div class="tree_title tree_title_close">
                        <a href="#"  class="tree_titlea" >设备管理</a>
                        <div class="tree_content">
                            <a href="javascript:dtype()">设备类型管理</a>
                            <a href="javascript:void(0)" name = "spec">设备型号管理</a>
                            <a href="javascript:void(0)" name="command">设备命令管理</a>
                        </div>
                    </div>
                    <div class="tree_title tree_title_close">
                        <a href="#"  class="tree_titlea" >主机管理</a>
                        <div class="tree_content">
                            <a href="javascript:void(0)">主机命令管理</a>
                        </div>
                    </div>

                </div>
            </td>
            <td align="center"  background="#f2f2f2" width="100%" height="100%">
                <div class="public_right">
                    <div class="public_right_center" id="public_right_center">

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
<script>
    function dtype(){

        $.get("${pageContext.request.contextPath}/dtype/Dtype_findDTypeList.do",{currentPageSize:"1"},function(data){
            $("#public_right_center").empty();
           $("#public_right_center").append(data);
        },"html");
    }
    $("#linkpage li a").live("click",function(){
        //alert("aa");
        var href = $(this).attr("href");
        $.get(href,{},function(data){
            $("#public_right_center").html(data);
        },"html");
        return false;
    });
</script>
</body>
</html>
