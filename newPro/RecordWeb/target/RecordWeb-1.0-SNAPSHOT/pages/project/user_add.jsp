<%--
  Created by IntelliJ IDEA.
  User: chby
  Date: 2014/10/29
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.honghe.recordhibernate.entity.Role" %>
<%@ page import="com.honghe.recordhibernate.entity.Hostgroup" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.honghe.recordweb.action.SessionManager" %>
<%@ page import="com.honghe.recordhibernate.entity.User" %>

<head>
    <link href="${pageContext.request.contextPath}/css/project/main.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/js/common/layer/skin/layer.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/js/common/layer/skin/layer.ext.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/project/public.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
    <script src="${pageContext.request.contextPath}/js/project/userList.js"></script>
    <script src="${pageContext.request.contextPath}/js/project/checkbox-user.js"></script>
</head>
<style>
    .all,.all1{max-width: 200px;width: auto;float: left;}
    .head1,.head{margin-top: 5px;float:left}
    body{
        overflow: hidden;
        top: 0;
        position: relative;
    }
    .foot{margin-top:0}
</style>
<script>
    $(function(){
        var win520height=$(".win520_content").height();
        var win520width=$(".win520_content").width();
        //alert($(".win520_content").height())
        parent.$(".xubox_main").find(".xubox_iframe").css({"width":win520width+"px","height":win520height+"px"});
        parent.$(".xubox_main").css({"width":win520width+"px","margin-left":"-120px","margin-top":-win520height/2+"px"});
    })
</script>
<body>
<div class="win520">
<input type="hidden" value="${pageContext.request.contextPath}" id="root_path">
<%
    int userId = Integer.parseInt(request.getAttribute("userId").toString()) ;
    User user = SessionManager.get(request.getSession(), SessionManager.Key.USERBACK);
    int currentId = user.getUserId();
    List<Map> userinfo = (List<Map>)request.getAttribute("userinfo");
    //out.println(userinfo);
    if(userinfo==null || userinfo.size()==0)
    {
%>
<div class="win520_content">
    <div class="tab_content_list" style="margin-top: 25px;">
        <span class="tab_content_listtext">用户名：</span>
        <input type="text" maxlength="20" name="uname" class="tab_content_listinput" />
    </div>
    <div class="tab_content_list" >
        <span class="tab_content_listtext">密码：</span>
        <input type="text" maxlength="20" name="pwd" class="tab_content_listinput" />&nbsp;
    </div>
    <%--<div class="tab_content_list" >--%>
        <%--<span class="tab_content_listtext">所属学校：</span>--%>
        <%--<input type="text" maxlength="20" name="school" class="tab_content_listinput" />--%>
    <%--</div>--%>
    <div class="tab_content_list" >
        <span class="tab_content_listtext">用户描述：</span>
        <input type="text" maxlength="50" name="desc" class="tab_content_listinput" />
    </div>
    <%
        List<Role> rolelists = (List<Role>)request.getAttribute("rolelist");
        if(rolelists.size()>0)
        {
    %>
    <div class="tab_content_list" >
        <span class="tab_content_listtext">角色：</span>
        <div class="checkbox">
            <%
                for(int i=0;i<rolelists.size();i++)
                {
            %>
            <input type="checkbox" name="role" value="<%=rolelists.get(i).getRoleId()%>" />
            <%
                }
            %>
        </div>
        <div class="all">
            <%
                for(int i=0;i<rolelists.size();i++)
                {
            %>
            <div class="head" style="margin-left: 10px;">
                <div class="bg" style="margin-top: 0px;"></div><%=rolelists.get(i).getRoleName()%>
            </div>
            <%
                }
            %>
        </div>
    </div>
    <%
        }
    %>
    <%
        List<Hostgroup> hostgrouplists = (List<Hostgroup>)request.getAttribute("hostgrouplist");
        if(hostgrouplists.size()>0)
        {
    %>
    <div class="tab_content_list" >
        <span class="tab_content_listtext">分组：</span>
        <div class="checkbox1">
            <%
                for(int i=0;i<hostgrouplists.size();i++)
                {

            %>
            <input type="checkbox" name="group" value="<%=hostgrouplists.get(i).getGroupId()%>" />
            <%
                }
            %>
        </div>
        <div class="all1">
            <%
                for(int i=0;i<hostgrouplists.size();i++)
                {
            %>
            <div class="head1" style="margin-left: 10px;">
                <div class="bg" style="margin-top: 0px;"></div><%=hostgrouplists.get(i).getGroupName()%>
            </div>
            <%
                }
            %>
        </div>
    </div>
    <%
        }
    %>
    <div class="win_btn" style="margin: 20px 20px 20px 0;" onclick="javascript:addUserSave()">
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
        <span class="tab_content_listtext">用户名：</span>
        <input type="text" name="uname" class="tab_content_listinput" disabled="disabled" value="<%=userinfo.get(0).get("user_name")%>"/>
        <input type="hidden" maxlength="20"  name="uid" value="<%=userinfo.get(0).get("user_id")%>"/>
    </div>
    <%--<div class="tab_content_list" >--%>
        <%--<span class="tab_content_listtext">所属学校：</span>--%>
        <%--<input type="text" maxlength="20" name="school" class="tab_content_listinput" value="<%=userinfo.get(0).get("school_name")==null?"":userinfo.get(0).get("school_name")%>"/>--%>
    <%--</div>--%>
    <div class="tab_content_list" >
        <span class="tab_content_listtext">用户描述：</span>
        <input type="text" maxlength="50" name="desc" class="tab_content_listinput" value="<%=userinfo.get(0).get("user_desc")==null?"":userinfo.get(0).get("user_desc")%>"/>
    </div>
    <%
        int uid = Integer.parseInt(userinfo.get(0).get("user_id").toString());

        //if(uid != 1 && uid != 2 && uid != userId)
        if(uid != 1 && uid != 2 && currentId != userId)
        {
    %>
    <%
        List<Role> rolelists = (List<Role>)request.getAttribute("rolelist");
        if(rolelists.size()>0)
        {
    %>
    <div class="tab_content_list" >

        <span class="tab_content_listtext">角色：</span>

        <div class="checkbox">
            <%
                List<Map> userrolelist = (List<Map>)userinfo.get(0).get("role_list");
                for(int i=0;i<rolelists.size();i++)
                {
                    Map<String, String> role = new HashMap<String, String>();
                    role.put("id", rolelists.get(i).getRoleId().toString());
                    role.put("name", rolelists.get(i).getRoleName());
            %>

            <input type="checkbox" name="role" value="<%=rolelists.get(i).getRoleId()%>"
                    <%=userrolelist.contains(role)?"checked":"unchecked" %>/>
            <%

                }
            %>
        </div>

        <div class="all">
            <%
                for(int i=0;i<rolelists.size();i++)
                {
                    Map<String, String> role = new HashMap<String, String>();
                    role.put("id", rolelists.get(i).getRoleId().toString());
                    role.put("name", rolelists.get(i).getRoleName());
                    /*if(rolelists.get(i).getRoleId()==1||rolelists.get(i).getRoleId()==2)
                    {*/
            %>

            <div class="head" style="margin-left: 10px;" >
                <div class="<%=userrolelist.contains(role)?"bged":"bg" %>" style="margin-top: 0px;"></div><%=rolelists.get(i).getRoleName()%>
            </div>
            <%

                }
            %>
        </div>
    </div>
    <%
        }
    %>
    <%
        }
    %>
    <%
        List<Hostgroup> hostgrouplists = (List<Hostgroup>)request.getAttribute("hostgrouplist");
        if(hostgrouplists.size()>0)
        {
    %>
    <div class="tab_content_list" >
        <span class="tab_content_listtext">分组：</span>
        <div class="checkbox1">
            <%
                List<Map> usergrouplist = (List<Map>)userinfo.get(0).get("group_list");
                for(int i=0;i<hostgrouplists.size();i++)
                {
                    Map<String, String> group = new HashMap<String, String>();
                    group.put("id", hostgrouplists.get(i).getGroupId().toString());
                    group.put("name", hostgrouplists.get(i).getGroupName());
            %>
            <input type="checkbox" name="group" value="<%=hostgrouplists.get(i).getGroupId()%>"
                  <%=usergrouplist.contains(group)?"checked":"unchecked" %> />
            <%
                }
            %>
        </div>
        <div class="all1">
            <%
                for(int i=0;i<hostgrouplists.size();i++)
                {
                    Map<String, String> group = new HashMap<String, String>();
                    group.put("id", hostgrouplists.get(i).getGroupId().toString());
                    group.put("name", hostgrouplists.get(i).getGroupName());
            %>
            <div class="head1" style="margin-left: 10px;">
                <div class="<%=usergrouplist.contains(group)?"bged":"bg" %>" style="margin-top: 0px;"></div><%=hostgrouplists.get(i).getGroupName()%>
            </div>
            <%
                }
            %>
        </div>
    </div>
    <%
        }
    %>
    <div class="win_btn" style="margin: 20px 20px 20px 0;" onclick="javascript:alterUserSave()">
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
    //添加用户，保存进数据库(点击‘添加’按钮)
    function addUserSave()
    {
        var uname = $("input[name='uname']").val().trim();
        if(uname=="")
        {
            layer.alert("用户名不能为空");
            return false;
        }
        var pwd = $("input[name='pwd']").val();
        if(pwd=="")
        {
            layer.alert("密码不能为空");
            return false;
        }
//        var school = $("input[name='school']").val();
        var desc = $("input[name='desc']").val();
        var roles ="";
        $('input:checkbox[name="role"]:checked').each(function()
        {
            roles+=$(this).val()+",";
        });
        if(roles=="")
        {
            layer.alert("角色不能为空");
            return false;
        }
        var groups ="";
        $('input:checkbox[name="group"]:checked').each(function()
        {
            groups+=$(this).val()+",";
        });
//        if(groups=="")
//        {
//            layer.alert("分组不能为空");
//            return false;
//        }
        $.get(rootpath+"/userback/Userback_addUser.do", {username: uname,userpwd:pwd,userdesc:desc,roleIdStr:roles,hostgroupIdStr:groups}, function (data) {
            if(data.success==true)
            {
//                $("input[name='uname']").val("");
//                $("input[name='pwd']").val("");
//                $("input[name='school']").val("");
//                $("input[name='desc']").val("");
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
    //修改用户，保存进数据库(点击‘修改’按钮)
    function alterUserSave()
    {
        var uid = $("input[name='uid']").val();
        var uname = $("input[name='uname']").val().trim();
//        var school = $("input[name='school']").val();
        var desc = $("input[name='desc']").val();
        var roles ="";
//        $('input:checkbox[name="role"]:checked').each(function()
//        {
//            roles+=$(this).val()+",";
//        });
        var cuid = "<%=currentId%>";
        if(uid != 1 && uid != 2 && uid != cuid )
        {
            var obj=  $(".checkbox").find("input[type=checkbox]:checked");
            //alert(obj);
            if(obj.length<1)
            {
                layer.alert("角色不能为空");
                return false;
            }
            else
            {
                for(var i=0;i<obj.length;i++)
                {
                    roles+=$(obj[i]).val()+",";
                }
            }
        }

        var groups ="";
        var obj1= $(".checkbox1").find("input[type=checkbox]:checked");
        if(obj1.length>0)
        {
            for(var p=0;p<obj1.length;p++)
            {
                groups+=$(obj1[p]).val()+",";
            }
        }
//        else
//        {
//            layer.alert("分组不能为空");
//            return false;
//        }
       /* $('input:checkbox[name="group"]:checked').each(function()
        {
            groups+=$(this).val()+",";
        });
        if(groups=="")
        {
            layer.alert("分组不能为空");
            return false;
        }*/
        $.get(rootpath+"/userback/Userback_alterUser.do", {userId: uid,username:uname,userdesc:desc,roleIdStr:roles,hostgroupIdStr:groups}, function (data) {
            if(data.success==true)
            {
                //window.parent.location.reload();
                layer.msg(data.msg,1,function(){
                    //alert("123");
                    $.get(rootpath+"/userback/Userback_userList.do",function(data){
                        parent.$("#public_right_center").html(data);
                        parent.layer.closeAll();
                    },'html')
                    //dataReload(rootpath+"/userback/Userback_userList.do");
                    //parent.layer.closeAll();

                    //parent.layer.close(layer.index);
                });
            }
            else
            {
                layer.msg(data.msg);
            }
        },'json');
    }


</script>
