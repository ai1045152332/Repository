<%@ page import="java.util.List" %>
<%@ page import="com.honghe.recordhibernate.entity.Role" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.honghe.recordhibernate.entity.Authority" %>
<%@ page import="com.honghe.recordhibernate.entity.User" %>
<%@ page import="com.honghe.recordweb.action.SessionManager" %>
<%@ page import="com.honghe.recordhibernate.dao.Pager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="${pageContext.request.contextPath}/js/common/layer/skin/layer.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/js/common/layer/skin/layer.ext.css" rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/js/common/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
<script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
<link href="${pageContext.request.contextPath}/css/frontend/hpager.css" rel="stylesheet" type="text/css"/>
<%
    List<Role> roleList = (List<Role>) request.getAttribute("rolelist");
    if(roleList!=null||roleList.size()>=0)
    {
%>
<%
    User currentuser = SessionManager.get(request.getSession(), SessionManager.Key.USERBACK);
%>
<input type="hidden" id="sessionuser" name="<%=currentuser.getUserId()%>"/>
<div class="amd">
    <a href="javascript:addRole()" class="amd_avarage">添加</a>
</div>
<div class="check_all">
    <table class="table" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td>
                <table border="0" cellpadding="0" cellspacing="0" class="table_head">
                    <tr>
                        <td width="15%" align="center"></td>
                        <td width="40%" align="center">角色名称</td>
                        <%--<td width="60%" align="center">权限</td>--%>
                        <td width="40%" align="center">操作</td>
                    </tr>
                </table>
            </td>
        </tr>
        <%
            for (int i = 0; i < roleList.size(); i++) {
                Role role = roleList.get(i);
        %>
        <tr class="tr">
            <td>
                <%
                    if(i%2 == 0)
                    {
                %>
                <table border="0" cellpadding="0" cellspacing="0" class="table_recycle table_recycle_ebebeb">
                <%
                    }
                    else
                    {
                %>
                    <table border="0" cellpadding="0" cellspacing="0" class="table_recycle table_recycle_fff">
                <%
                    }
                %>
                    <tr>
                        <td width="15%" align="center"><%=i + 1%>
                        </td>
                        <td width="40%" align="center"><%=role.getRoleName()%>
                        </td>
                        <%--<td width="60%" align="center">--%>
                            <%--<%--%>
                                <%--Iterator iterator = role.getAuthoritys().iterator();--%>
                                <%--String authNames = "";--%>
                                <%--while (iterator.hasNext())--%>
                                <%--{--%>
                                    <%--Authority auth = (Authority)iterator.next();--%>
                                    <%--authNames += auth.getAuthName()+",";--%>
                                <%--}--%>
                                <%--authNames = authNames.substring(0,authNames.length()-1);--%>
                                <%--out.println(authNames);--%>
                            <%--%>--%>
                        <%--</td>--%>
                        <td width="40%" align="center">
                            <%
                                if(currentuser.getUserId() == 1)
                                {
                                    if(role.getRoleId()<=4)
                                    {
                            %>
                            <a href="javascript:alterRole('<%=role.getRoleId()%>')">修改</a>
                            <%
                                    }
                                    else
                                    {
                            %>
                            <a href="javascript:delRole('<%=role.getRoleId()%>')">删除</a>/<a href="javascript:alterRole('<%=role.getRoleId()%>')">修改</a>
                            <%
                                    }
                                }
                                else if(currentuser.getUserId() == 2 && role.getRoleId() > 1)
                                {
                                    if(role.getRoleId()<=4)
                                    {
                            %>
                            <a href="javascript:alterRole('<%=role.getRoleId()%>')">修改</a>
                            <%
                                    }
                                    else
                                    {
                            %>
                            <a href="javascript:delRole('<%=role.getRoleId()%>')">删除</a>/<a href="javascript:alterRole('<%=role.getRoleId()%>')">修改</a>
                            <%
                                    }
                                }
                                else if(currentuser.getUserId() != 2 && role.getRoleId() > 2)
                                {
                                    if(role.getRoleId()<=4)
                                    {
                            %>
                            <a href="javascript:alterRole('<%=role.getRoleId()%>')">修改</a>
                            <%
                                    }
                                    else
                                    {
                            %>
                            <a href="javascript:delRole('<%=role.getRoleId()%>')">删除</a>/<a href="javascript:alterRole('<%=role.getRoleId()%>')">修改</a>
                            <%
                                    }
                                }
                            %>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <%
            }
            int pageCount = Integer.parseInt(request.getAttribute("pageCount").toString());
            int currentPage = Integer.parseInt(request.getAttribute("currentPage").toString());
            Pager pager = new Pager(pageCount,currentPage,"<span class='pages_prevpage'></span>","<span class='pages_nextpage'></span>","","",false);
            String pagers = pager.run();
        %>
    </table>
    <div id="linkpage">
        <%=pagers%>
    </div>
</div>
<%
    }
    else
    {
%>
暂无角色信息
<%
    }
%>
<script>
    //添加角色(点击‘添加角色’链接)
    function addRole()
    {
        var rid = -1;
        var suid = $("#sessionuser").attr("name");
        $.layer({
            type : 2,
            title: '添加角色',
            shadeClose: true,
            maxmin: false,
            fix : false,
            iframe: {
                src : "${pageContext.request.contextPath}/roleback/Roleback_roleDetaileBack.do?roleId="+rid+"&userId="+suid
            },
            end:function(){
                if (!this.isClose) {
                    $.get("${pageContext.request.contextPath}/roleback/Roleback_roleListBack.do",function (data) {
                        $("#public_right_center").empty();
                        $("#public_right_center").append(data);
                    }, "html");
                }
            }
        });
    }
    function alterRole(id){
        var suid = $("#sessionuser").attr("name");
        $.layer({
            type : 2,
            title: '编辑角色',
            shadeClose: true,
            maxmin: false,
            fix : false,
            iframe: {
                src : "${pageContext.request.contextPath}/roleback/Roleback_roleDetaileBack.do?roleId="+id+"&userId="+suid
            },
            end:function(){
                if (!this.isClose) {
                    $.get("${pageContext.request.contextPath}/roleback/Roleback_roleListBack.do",function (data) {
                        $("#public_right_center").empty();
                        $("#public_right_center").append(data);
                    }, "html");
                }
            }
        });
    }
    function delRole(id){
        $.layer({
            shade: [0.5, '#000'],
            maxmin: false,
            area: ['auto','auto'],
            dialog: {
                msg: '确定删除该角色？',
                btns: 2,
                type: 4,
                btn: ['确定','取消'],
                yes: function(){
                    var delUrl ="${pageContext.request.contextPath}/roleback/Roleback_delRole.do";
                    $.post(delUrl,{'roleId':id},function(data){
                        if(data.success==true)
                        {
                            $.get("${pageContext.request.contextPath}/roleback/Roleback_roleListBack.do",function (data) {
                                $("#public_right_center").empty();
                                $("#public_right_center").append(data);
                            }, "html");
                            layer.msg(data.msg,1,1);
                        }
                        else
                        {
                            layer.alert(data.msg);
                        }
                    },'json')
                },
                no: function(){
                }
            }
        });
    }
</script>


