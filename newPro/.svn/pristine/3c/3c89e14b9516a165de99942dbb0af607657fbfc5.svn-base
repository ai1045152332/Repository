<%--
  Created by IntelliJ IDEA.
  User: lch
  Date: 2014/11/17
  Time: 13:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
<script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
<link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css" />
<style>
    body{background: white;}
</style>
<%
    int uid = Integer.parseInt(request.getParameter("uid").toString());
    response.setHeader("Cache-Control","no-store");
    response.setHeader("Pragrma","no-cache");

    response.setDateHeader("Expires",0);

%>
<div style="box-shadow: 0 0 0 1px #212121 inset;float: left;height: 165px;">
<div class="tab_content_list" style="margin-top: 20px" >
    <span class="tab_content_listtext" style="width: 130px;">输入密码：</span>
    <input type="password" maxlength="20" name="pwd" class="tab_content_listinput" />&nbsp;
</div>
<div class="tab_content_list" >
    <span class="tab_content_listtext" style="width: 130px;">确认密码：</span>
    <input type="password" maxlength="20" name="confirm_pwd" class="tab_content_listinput" />&nbsp;
</div>
<div class="win_btn" style="margin: 20px 20px 20px 0;" >
    
    <div class="win_btn_sure" style="margin-right: 20px;" onclick="javascript:editUserSave()">确定</div>
    <div class="win_btn_done" style="margin-right: 10px;" onclick="javascript:cancelUserSave()">取消</div>
</div>
</div>
<input type="hidden" id="root_path" value="${pageContext.request.contextPath}">
<script>
    function editUserSave() {
        var rootpath = $("#root_path").val();
        var pwd = $("input[name='pwd']").val();
//        var uid = parent.$(".user_list_selected").attr("id");
        var uid = <%=uid%>;
        if(pwd=="")
        {
            layer.msg("密码不能为空", 2, 8);
            return false;
        }
        var confirm_pwd = $("input[name='confirm_pwd']").val();
        if(confirm_pwd == "")
        {
            layer.msg("确认密码不能为空", 2, 8);
            return false;
        }
        if(pwd != confirm_pwd)
        {
            $("input[name='confirm_pwd']").val("");
            layer.msg("两次密码输入不一致",2,8);
            return false;
        }
       // alert(uid);
        if(uid)
        {
            $.post(rootpath+"/user/User_alterPwd.do", {userId: uid,userpwd:pwd},function(data){
                if(data.success==true)
                {
                    var searchname =$("#user_search_input",window.parent.document).val();
                    if(searchname=="")
                    {
                        parent.window.location.href=rootpath+"/user/User_userList.do";
                    }
                    else
                    {
                        $.get(rootpath+"/user/User_userSearch.do", {username: searchname}, function (data) {
                            parent.$("#user_search_input").blur();
                            parent.$("#userdatalist").empty();
                            parent.$("#userdatalist").html(data);
                            var pagelen=parent.$(".page a").length;
                            left=-(pagelen+3)*28/2+"px";
                            parent.$("#linkpage_son").css({"margin-left":"left","width":(pagelen+4)*28+"px","position":"absolute","bottom":"40px","left":"45%"});
                            parent.layer.closeAll();
                        }, 'html');
                    }
                }
                else
                {
                    layer.msg("密码重置失败",2,8);
                }
            },'json');
        }
        else
        {
            layer.msg("未选择用户，不能进行操作哦",2,8);
        }
    }
    function cancelUserSave(){
        parent.layer.closeAll();
    }
</script>