<%@ page import="com.honghe.recordhibernate.entity.Role" %>
<%@ page import="com.honghe.recordhibernate.entity.Authority" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%--
  Created by IntelliJ IDEA.
  User: wj
  Date: 2014-10-11
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <script src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
    <link href="${pageContext.request.contextPath}/css/project/main.css" rel="stylesheet" type="text/css" />
</head>
<style>
    .all{width: 260px;float: left;}
    .head{margin-top: 5px;float:left;}
    body{
        overflow: hidden;
        top: 0;
        position: relative;
    }
    .foot{margin-top:0}
</style>
<script>
    $(function(){
        var win520height=$(".win520_content").height()-57;
        var win520width=$(".win520_content").width();
        //alert($(".win520_content").height())
        parent.$(".xubox_main").find(".xubox_iframe").css({"width":win520width+"px","height":win520height+"px"});
        parent.$(".xubox_main").css({"width":win520width+"px","margin-left":"-120px","margin-top":-win520height/2+"px"});
    })
</script>
<body>
<div class="win520">
<input type="hidden" value="${pageContext.request.contextPath}" id="root_path"/>
<%
Role roleinfo = (Role)request.getAttribute("roleinfo");
if(roleinfo==null )
{
%>
<div class="win520_content">
    <div class="tab_content_list" style="margin-top: 25px;">
        <span class="tab_content_listtext">角色名：</span>
        <input type="text" maxlength="20" name="rname" class="tab_content_listinput" />
    </div>
    <%
        List<Authority> authlists = (List<Authority>)request.getAttribute("authlist");
        if(authlists!=null && authlists.size()>0)
        {
    %>
    <div class="tab_content_list" >
        <span class="tab_content_listtext">权限：</span>
        <div class="checkbox">
            <%
                for(int i=0;i<authlists.size();i++)
                {
            %>
            <input type="checkbox" id="c_<%=authlists.get(i).getAuthId()%>" name="auth" value="<%=authlists.get(i).getAuthId()%>" />
            <%
                }
            %>
        </div>
        <div class="all">
            <%
                for(int i=0;i<authlists.size();i++)
                {
            %>
            <div class="head" id="<%=authlists.get(i).getAuthId()%>" style="margin-left: 10px;">
                <div class="bg" id="h_<%=authlists.get(i).getAuthId()%>" style="margin-top: 0px;"></div><%=authlists.get(i).getAuthName()%>
            </div>
            <%
                }
            %>
        </div>
    </div>
    <%
        }
    %>
    <div class="win_btn" style="margin: 20px 20px 20px 0;" onclick="javascript:addRoleSave()">
        <div class="win_btn_sure" style="margin-right: 180px;">确定</div>
    </div>
</div>
<%
}
else
{
%>
<div class="win520_content">
    <div class="tab_content_list" style="margin-top: 25px;">
        <span class="tab_content_listtext">角色名：</span>
        <input type="text" maxlength="20" name="rname" class="tab_content_listinput" value="<%=roleinfo.getRoleName()%>"/>
        <input type="hidden"  name="rid" value="<%=roleinfo.getRoleId()%>"/>
    </div>
    <%
        List<Authority> authlists = (List<Authority>)request.getAttribute("authlist");
        if(authlists.size()>0)
        {
    %>
    <div class="tab_content_list" >
        <span class="tab_content_listtext">权限：</span>
        <div class="checkbox">
            <%
                List<Map> roleauthlist = (List<Map>)request.getAttribute("roleauthlist");
                for(int i=0;i<authlists.size();i++)
                {
                    HashMap<Integer,String> authmap = new HashMap<Integer, String>();
                    authmap.put(authlists.get(i).getAuthId(), authlists.get(i).getAuthName());
            %>
            <input type="checkbox" id="c_<%=authlists.get(i).getAuthId()%>" name="auth" value="<%=authlists.get(i).getAuthId()%>"
                    <%=roleauthlist.contains(authmap)?"checked":"unchecked" %>/>
            <%
                }
            %>
        </div>
        <div class="all">
            <%
                for(int i=0;i<authlists.size();i++)
                {
                    Map<Integer,String> authmap = new HashMap<Integer, String>();
                    authmap.put(authlists.get(i).getAuthId(), authlists.get(i).getAuthName());
            %>
            <div class="head" id="<%=authlists.get(i).getAuthId()%>" style="margin-left: 10px;" >
                <div id="h_<%=authlists.get(i).getAuthId()%>" class="<%=roleauthlist.contains(authmap)?"bged":"bg" %>" style="margin-top: 0px;"></div><%=authlists.get(i).getAuthName()%>
            </div>
            <%
                }
            %>
        </div>
    </div>
    <%
        }
    %>
    <div class="win_btn" style="margin: 20px 20px 20px 0;" onclick="javascript:alterRoleSave()">
        <div class="win_btn_sure" style="margin-right: 180px;">确定</div>
    </div>
</div>
<%
}
%>
</div>
</body>
<script type="text/javascript">
    var rootpath = $("#root_path").val();
    //添加角色，保存进数据库(点击‘添加’按钮)
    function addRoleSave()
    {
        var rname = $("input[name='rname']").val().trim();
        if(rname=="")
        {
            layer.alert("角色名不能为空");
            return false;
        }
        var auths ="";
        $('input:checkbox[name="auth"]:checked').each(function()
        {
            auths+=$(this).val()+",";
        });
        if(auths=="")
        {
            layer.alert("权限不能为空");
            return false;
        }
        $.get(rootpath+"/roleback/Roleback_addRole.do", {rolename: rname,authStr:auths}, function (data) {
            if(data.success==true)
            {
//                $("input[name='rname']").val("");
//                $('input:checkbox:checked').removeAttr("checked");
//                $(".bged").removeClass("bged").addClass("bg");
//                layer.msg(data.msg,1,1);
                layer.msg(data.msg,1,function(){
                    parent.layer.closeAll();
                    //parent.layer.close(layer.index);
                });
            }
            else
            {
                layer.msg(data.msg);
            }
        },'json');
    }
    //修改角色，保存进数据库(点击‘修改’按钮)
    function alterRoleSave()
    {
        var rid = $("input[name='rid']").val();
        var rname = $("input[name='rname']").val().trim();
        if(rname=="")
        {
            layer.alert("角色名不能为空");
            return false;
        }
        var auths ="";
        $('input:checkbox[name="auth"]:checked').each(function()
        {
            auths+=$(this).val()+",";
        });
        if(auths=="")
        {
            layer.alert("权限不能为空");
            return false;
        }
        $.get(rootpath+"/roleback/Roleback_editRole.do", {roleId: rid,rolename:rname,authStr:auths}, function (data) {
            if(data.success==true)
            {
                //window.parent.location.reload();
                layer.msg(data.msg,1,function(){
                    parent.layer.closeAll();
                    //parent.layer.close(layer.index);
                });
            }
            else
            {
                layer.msg(data.msg);
            }
        },'json');
    }
    $(function(){
        $(document.body).find("input[type=checkbox]").css("display","none");
//        $(".head").click(function(){
//            var index=$(this).index();
//            //alert(index)
//            var flag=$(".checkbox").find("input[type=checkbox]").eq(index).prop("checked");
//            if(flag==true){
//                //alert(flag)
//                $(".checkbox").find("input[type=checkbox]").eq(index).prop("checked",false);
//                $(".head").eq(index).find(".bged").removeClass("bged").addClass("bg");
//                //alert(flag+"a")
//            }else{
//                //alert(flag)
//                $(".checkbox").find("input[type=checkbox]").eq(index).prop("checked",true);
//                $(".head").eq(index).find(".bg").removeClass("bg").addClass("bged");
//            }
//        });
        $(".head").click(function(){
            var id = $(this).attr("id");
            var flag=$("#c_"+id).prop("checked");
            if(flag==true){
                $("#c_"+id).prop("checked",false);
                $("#h_"+id).removeClass("bged").addClass("bg");
                //分组定义、班级定义、设备定义
                if(id==13 || id==14 || id==15)
                {
                    $("#c_"+13).prop("checked",false);
                    $("#h_"+13).removeClass("bged").addClass("bg");
                    $("#c_"+14).prop("checked",false);
                    $("#h_"+14).removeClass("bged").addClass("bg");
                    $("#c_"+15).prop("checked",false);
                    $("#h_"+15).removeClass("bged").addClass("bg");
                }
                //如果取消巡课权限，电源管理和录制停止录制也取消
                if(id==16)
                {
                    $("#c_"+20).prop("checked",false);
                    $("#h_"+20).removeClass("bged").addClass("bg");
                    $("#c_"+21).prop("checked",false);
                    $("#h_"+21).removeClass("bged").addClass("bg");
                }
            }
            else
            {
                $("#c_"+id).prop("checked",true);
                $("#h_"+id).removeClass("bg").addClass("bged");
                //分组定义、班级定义、设备定义，三个权限同时存在
                if(id==13 || id==14 || id==15)
                {
                    $("#c_"+13).prop("checked",true);
                    $("#h_"+13).removeClass("bg").addClass("bged");
                    $("#c_"+14).prop("checked",true);
                    $("#h_"+14).removeClass("bg").addClass("bged");
                    $("#c_"+15).prop("checked",true);
                    $("#h_"+15).removeClass("bg").addClass("bged");
                }
                //如果勾选了电源管理或者录制停止录制，必须带上巡课权限。
                if(id==20 || id==21)
                {
                    $("#c_"+16).prop("checked",true);
                    $("#h_"+16).removeClass("bg").addClass("bged");
                }
            }
        })
    });

</script>
