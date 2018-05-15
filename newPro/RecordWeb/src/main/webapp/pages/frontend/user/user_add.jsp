<%@ page import="com.honghe.recordhibernate.entity.Role" %>
<%@ page import="com.honghe.recordhibernate.entity.Hostgroup" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.honghe.recordhibernate.entity.User" %>
<%@ page import="com.honghe.recordweb.action.SessionManager" %>
<%--
  Created by IntelliJ IDEA.
  User: wj
  Date: 2014-10-11
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link href="${pageContext.request.contextPath}/css/frontend/index.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
    <link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/js/common/perfect-scrollbar.with-mousewheel.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/prettify.js"></script>
</head>
<style>
    .all{max-width: 300px;width: auto;float: left;}
    .head1,.head{margin-top: 5px;}
    body{
        overflow: hidden;
        top: 0;
        position: relative;
    }
    .foot{margin-top:0}
    .head,.head1{
        width: 90px;
        text-overflow: ellipsis;
        overflow: hidden;
        white-space:nowrap;
    }
    .headr{
        margin-top: 5px;
    }

</style>

<body>
<%

    response.setHeader("Cache-Control","no-store");

    response.setHeader("Pragrma","no-cache");

    response.setDateHeader("Expires",0);

%>
<div id="user_winadd" style="height: 345px;width:520px;background: white;">
    <div class="contentr" style="min-height: 0;width: 100%;">
        <input type="hidden" value="${pageContext.request.contextPath}" id="root_path">

        <%

            User currentUser = SessionManager.get(request.getSession(), SessionManager.Key.USER);
            int userId = Integer.parseInt(request.getAttribute("userId").toString()) ;
            User user = SessionManager.get(request.getSession(), SessionManager.Key.USER);
            int currentId = user.getUserId();
            List<Map> userinfo = (List<Map>)request.getAttribute("userinfo");
            if(userinfo==null || userinfo.size()==0)
            {
        %>
        <div class="win520_content" style="min-height: 289px;border:none;background: none;">
            <div class="tab_content_list" style="margin-top: 25px;">
                <span class="tab_content_listtext">用户名：</span>
                <input type="text" maxlength="20" name="uname" class="tab_content_listinput" />
            </div>
            <div class="tab_content_list" >
                <span class="tab_content_listtext">密码：</span>
                <input type="password" maxlength="20" name="pwd" class="tab_content_listinput" />&nbsp;
            </div>
            <div class="tab_content_list" >
                <span class="tab_content_listtext">确认密码：</span>
                <input type="password" maxlength="20" name="confirm_pwd" class="tab_content_listinput" />&nbsp;
            </div>
            <%
                List<Role> rolelists = (List<Role>)request.getAttribute("rolelist");
                if(rolelists.size()>0)
                {
            %>
            <div class="tab_content_list"   style="max-width:480px">
                <span class="tab_content_listtext">角色：</span>
                <div class="radio">
                    <%
                        for(int i=0;i<rolelists.size();i++)
                        {
                            if(currentUser.getUserName().equals("admin"))
                            {
                    %>
                    <input type="radio" name="role"  value="<%=rolelists.get(i).getRoleId()%>"/>
                    <%
                    }
                    else {
                        if(!rolelists.get(i).getRoleName().equals(com.honghe.recordweb.service.frontend.user.Role.Role_SchoolManager.toString())|| !rolelists.get(i).getRoleName().equals(com.honghe.recordweb.service.frontend.user.Role.Role_SystemManager.toString()))
                        {
                    %>
                    <input type="radio" name="role"  value="<%=rolelists.get(i).getRoleId()%>"/>
                    <%
                                }
                            }
                        }
                    %>
                </div>
                <div class="allr" style="float: left;width:330px;">
                    <%
                        for(int i=0;i<rolelists.size();i++)
                        {
                            if(currentUser.getUserName().equals("admin"))
                            {
                    %>
                    <div class="headr" style="margin-left: 10px;"  title=<%=rolelists.get(i).getRoleName()%>>
                        <span class="bgr"></span><%=rolelists.get(i).getRoleName()%>
                    </div>
                    <%
                    }
                    else {
                        if(!rolelists.get(i).getRoleName().equals(com.honghe.recordweb.service.frontend.user.Role.Role_SchoolManager.toString())|| !rolelists.get(i).getRoleName().equals(com.honghe.recordweb.service.frontend.user.Role.Role_SystemManager.toString()))
                        {
                    %>
                    <div class="headr" style="margin-left: 10px;"  title=<%=rolelists.get(i).getRoleName()%>>
                        <span class="bgr"></span><%=rolelists.get(i).getRoleName()%>
                    </div>
                    <%
                                }
                            }
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
            <div class="tab_content_list hostgroup">
                <div class="tab_content_listtext">分组：</div>
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
                <div class="all">
                    <%
                        for(int i=0;i<hostgrouplists.size();i++)
                        {
                    %>
                    <div class="head1" style="margin-left: 10px;" title=<%=hostgrouplists.get(i).getGroupName()%>>
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

            <div class="win_btn" style="margin: 20px 20px 40px 0;" >
                <div class="win_btn_sure" style="margin-right: 20px;" onclick="javascript:addUserSave()">确定</div>
                <div class="win_btn_done" style="margin-right: 10px;" onclick="javascript:cancle()" >取消</div>
            </div>
        </div>
    </div>
        <%
        }
        else
        {
    %>
    <style>
    	.tab_content_listtext{
    		width: 100px;
    	}
    </style>
    <div  style="height: 313px;width:520px;background: white;">
        <div class="contentr" style="min-height: 0;width: 100%;">
            <div class="win520_content" style="min-height: 210px;border:none;background: none;">
                <div class="tab_content_list" style="margin-top: 25px;">
                    <span class="tab_content_listtext">用户名：</span>
                    <input type="text" name="uname" class="tab_content_listinput" disabled="disabled" value="<%=userinfo.get(0).get("user_name")%>"/>
                    <input type="hidden"  name="uid" value="<%=userinfo.get(0).get("user_id")%>"/>
                </div>
                <%
                    if(userId != currentId)
                    {
                        List<Role> rolelists = (List<Role>)request.getAttribute("rolelist");
                        if(rolelists.size()>0)
                        {
                %>
                <div class="tab_content_list"  style="max-width:480px" >
                    <span class="tab_content_listtext" style="margin-left: 30px">角色：</span>
                    <div class="radio">
                        <%
                            List<Map> userrolelist = (List<Map>)userinfo.get(0).get("role_list");
                            for(int i=0;i<rolelists.size();i++)
                            {
                                Map<String, String> role = new HashMap<String, String>();
                                role.put("id", rolelists.get(i).getRoleId().toString());
                                if(rolelists.get(i).getRoleName().equals("系统管理员"))
                                {
                                    role.put("name",com.honghe.recordweb.service.frontend.user.Role.Role_SystemManager.toString());
                                }
                                else {
                                    role.put("name", rolelists.get(i).getRoleName());
                                }
                                if(currentUser.getUserName().equals("admin"))
                                {
                        %>
                        <input type="radio" name="role"  value="<%=rolelists.get(i).getRoleId()%>"
                                <%=userrolelist.contains(role)?"checked":"unchecked" %>/>
                        <%
                        }
                        else {
                            if(!rolelists.get(i).getRoleName().equals(com.honghe.recordweb.service.frontend.user.Role.Role_SchoolManager.toString())|| !rolelists.get(i).getRoleName().equals(com.honghe.recordweb.service.frontend.user.Role.Role_SystemManager.toString()))
                            {
                        %>
                        <input type="radio" name="role"  value="<%=rolelists.get(i).getRoleId()%>"
                                <%=userrolelist.contains(role)?"checked":"unchecked" %>/>
                        <%
                                    }
                                }
                            }
                        %>
                    </div>
                    <div class="allr"  style="float: left;width:330px;">
                        <%
                            for(int i=0;i<rolelists.size();i++)
                            {
                                Map<String, String> role = new HashMap<String, String>();
                                role.put("id", rolelists.get(i).getRoleId().toString());
                                if(rolelists.get(i).getRoleName().equals("系统管理员"))
                                {
                                    role.put("name",com.honghe.recordweb.service.frontend.user.Role.Role_SystemManager.toString());
                                }
                                else {
                                    role.put("name", rolelists.get(i).getRoleName());
                                }
                                if(currentUser.getUserName().equals("admin"))
                                {
                        %>
                        <div class="headr" style="margin-left: 10px;" title=<%=rolelists.get(i).getRoleName()%>>
                            <span class="<%=userrolelist.contains(role)?"bgred":"bgr" %>"></span>
                            <%=rolelists.get(i).getRoleName()%>
                        </div>
                        <%
                        }
                        else {
                            if(!rolelists.get(i).getRoleName().equals(com.honghe.recordweb.service.frontend.user.Role.Role_SchoolManager.toString())|| !rolelists.get(i).getRoleName().equals(com.honghe.recordweb.service.frontend.user.Role.Role_SystemManager.toString()))
                            {
                        %>
                        <div class="headr" style="margin-left: 10px;" title=<%=rolelists.get(i).getRoleName()%>>
                            <span class="<%=userrolelist.contains(role)?"bgred":"bgr" %>"></span>
                            <%=rolelists.get(i).getRoleName()%>
                        </div>
                        <%
                                    }
                                }
                            }
                        %>
                    </div>
                </div>
                <%
                        }
                    }
                %>
                <%
                    List<Hostgroup> hostgrouplists = (List<Hostgroup>)request.getAttribute("hostgrouplist");
                    if(hostgrouplists.size()>0)
                    {
                %>
                <div class="tab_content_list hostgroup">
                    <div class="tab_content_listtext">分组：</div>
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
                                <%=usergrouplist.contains(group)?"checked":"unchecked" %>/>
                        <%
                            }
                        %>
                    </div>
                    <div class="all">
                        <%
                            for(int i=0;i<hostgrouplists.size();i++)
                            {
                                Map<String, String> group = new HashMap<String, String>();
                                group.put("id", hostgrouplists.get(i).getGroupId().toString());
                                group.put("name", hostgrouplists.get(i).getGroupName());
                        %>
                        <div class="head1" style="margin-left: 10px;" title="<%=hostgrouplists.get(i).getGroupName()%>">
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
                <div class="win_btn" style="margin: 20px 20px 40px 0;" >
                    <div class="win_btn_sure" style="margin-right: 20px;" onclick="javascript:alterUserSave()">确定</div>
                    <div class="win_btn_done" style="margin-right: 10px;" onclick="javascript:cancle()">取消</div>
                </div>
            </div>
        </div>
    </div>
        <%
    }
%>


</body>
<script type="text/javascript">

    //树形菜单滚动条模拟
    function change_size() {
        $("#user_winadd").width(520).height(247);
        // update perfect scrollbar
        $('#user_winadd').perfectScrollbar('update');
    }
    $(function(){

        $('#user_winadd').perfectScrollbar();
        prettyPrint();
    })
    var rootpath = $("#root_path").val();
    //添加用户，保存进数据库(点击‘添加’按钮)
    function addUserSave()
    {
        var uname = $.trim($("input[name='uname']").val());
        if(uname=="")
        {
            layer.msg("用户名不能为空", 2, 8);
            return false;
        }
        if(uname.length < 6 || uname.length > 21){
            layer.msg("用户名长度在6-20个字符之间", 2, 8);
            return false;
        }
        var reg = /^[0-9a-zA-Z]*$/g;
        if(!reg.test(uname)){
            layer.msg("用户名必须为数字或字母", 2, 8);
            return false;
        }

        var pwd = $.trim($("input[name='pwd']").val());
        if(pwd=="")
        {
            layer.msg("密码不能为空", 2, 8);
            return false;
        }
        reg.lastIndex=0;
        if(!reg.test(pwd)){
            layer.msg("密码必须由数字或者字母组成", 2, 8);
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
        var desc = $("input[name='desc']").val();
        var roles ="";
        $('input:radio[name="role"]:checked').each(function()
        {
            roles+=$(this).val()+",";
        });
        var rolenames = "";
        $('.bgred').each(function()
        {
            rolenames+=$(this).parent().text().trim()+"";
        });
        if(roles=="")
        {
            layer.msg("角色不能为空", 2, 8);
            return false;
        }
        var groups ="";
        $('input:checkbox[name="group"]:checked').each(function()
        {
            groups+=$(this).val()+",";
        });

        $.get(rootpath+"/user/User_addUser.do", {username: uname,userpwd:pwd,userdesc:desc,roleIdStr:roles,roleNameStr:rolenames,hostgroupIdStr:groups}, function (data) {
            if(data.success==true)
            {
                parent.window.location.href=rootpath+"/user/User_userList.do";
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
        var uname = $.trim($("input[name='uname']").val());
        var desc = $("input[name='desc']").val();
        var roles ="";
        var rolenames = "";
        var cuid = "<%=currentId%>";
        if( uid != cuid )
        {
            var obj=  $(".radio").find("input[type=radio]:checked");
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
            $('.bgred').each(function()
            {
                rolenames+=$(this).parent().text().trim()+"";
            });
        }
        var groups ="";
        $('input:checkbox[name="group"]:checked').each(function()
        {
            groups+=$(this).val()+",";
        });
        $.post(rootpath+"/user/User_alterUser.do", {userId: uid,username:uname,userdesc:desc,roleIdStr:roles,roleNameStr:rolenames,hostgroupIdStr:groups}, function (data) {
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
                layer.msg(data.msg);
            }
        },'json');
    }
    $(function(){
        $(document.body).find("input[type=checkbox]").css("display","none");
        $("input[type=radio]").css("display","none");
        var flag=$(".radio").find("input[type=radio]").eq(0).prop("checked");
        var rolename = $(".headr").eq(0).text().trim();
        if((rolename == "系统管理员"||rolename=="校园管理员") && flag == true)
        {
            $(".hostgroup div").css('display','none');
            $('input:checkbox[name="group"]:checked').removeAttr("checked");
            $(".head1").find(".bged").removeClass("bged").addClass("bg");
        }
        else
        {
            $(".hostgroup div").css('display','block');
        }
        $(".headr").click(function(){
            var index=$(this).index();
            var flag=$(".radio").find("input[type=radio]").eq(index).prop("checked");
            if(flag==true){
//                $(".radio").find("input[type=radio]").eq(index).prop("checked",false);
//                $(".headr").eq(index).find(".bgred").removeClass("bgred").addClass("bgr");
            }
            else
            {
                $(".radio").find("input[type=radio]").eq(index).prop("checked",true);
                $(".headr").find(".bgred").removeClass("bgred").addClass("bgr");
                $(".headr").eq(index).find(".bgr").removeClass("bgr").addClass("bgred");
            }
            //选择系统管理员，隐藏所有分组。
            var rolename = $(this).text().trim();
            if((rolename == "系统管理员"||rolename=="校园管理员") )
            {
                $(".hostgroup").css('display','none');
                $(".hostgroup div").css('display','none');
                $('input:checkbox[name="group"]:checked').removeAttr("checked");
                $(".head1").find(".bged").removeClass("bged").addClass("bg");
            }
            else
            {
                $(".hostgroup").css('display','block');
                $(".hostgroup div").css('display','block');
            }
        });
        $(".head1").click(function(){
            var index=$(this).index();
            var flag=$(".checkbox1").find("input[type=checkbox]").eq(index).prop("checked");
            if(flag==true){
                $(".checkbox1").find("input[type=checkbox]").eq(index).prop("checked",false);
                $(".head1").eq(index).find(".bged").removeClass("bged").addClass("bg");
            }else{
                $(".checkbox1").find("input[type=checkbox]").eq(index).prop("checked",true);
                $(".head1").eq(index).find(".bg").removeClass("bg").addClass("bged");
            }
        });
    });
    function cancle()
    {
        parent.layer.closeAll();
    }
</script>
