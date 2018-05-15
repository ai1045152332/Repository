<%@ page import="com.honghe.recordhibernate.dao.Pager" %>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="com.honghe.recordhibernate.entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.honghe.recordhibernate.entity.Role" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.honghe.recordweb.action.SessionManager" %>
<%--
  Created by IntelliJ IDEA.
  User: chby
  Date: 2014/10/29
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <link href="${pageContext.request.contextPath}/js/common/layer/skin/layer.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/js/common/layer/skin/layer.ext.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/hpager.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/project/checkbox.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
    <script src="${pageContext.request.contextPath}/js/project/userList.js"></script>
<%
    User currentuser = SessionManager.get(request.getSession(), SessionManager.Key.USERBACK);
    List<User> userlists = (List<User>)request.getAttribute("userlist");
%>
<div class="all" style="display: none;">
    <input type="checkbox" />
    <input type="checkbox" />
    <input type="checkbox" />
    <input type="checkbox" />
    <input type="checkbox" />
    <input type="checkbox" />
    <input type="checkbox" />
    <input type="checkbox" />
    <input type="checkbox" />
</div>
<input type="hidden" name="<%=currentuser.getUserId()%>"/>
<div class="amd">
    <a href="javascript:addUser()"  class="amd_avarage">添加</a>
   <%-- <a href="#" class="amd_avarage">修改</a>
    <a href="#" class="amd_avarage">删除</a>--%>
</div>
<div class="check_all">
    <table class="table" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td>
                <table border="0" cellpadding="0" cellspacing="0" class="table_head">
                    <tr>
                        <td width="5%" align="center"></td>
                        <%--<td width="5%" align="center">
                            <div class="checkall_head">
                                <input id="check_head" type="checkbox" />
                                <div  class="bg" id="checkallhead" onclick="checkall()"></div>
                            </div>
                        </td>--%>
                        <td width="10%" align="center">用户名</td>
                        <td width="25%" align="center">角色</td>
                        <td width="15%" align="center">所属学校</td>
                        <td width="25%" align="center">权限描述</td>
                        <td width="25%" align="center">操作</td>
                    </tr>
                </table>
            </td>
        </tr>
        <%
            if(userlists.size() == 0||userlists == null)
            {
        %>
        暂无人员
        <%
        }
        else
        {
            for(int i=0;i<userlists.size();i++)
            {
        %>
        <tr class="tr">
            <td>
            <%
                Iterator iterator = userlists.get(i).getRoles().iterator();
                String roleNames = "";
                HashMap<Integer,String> rolemap = new HashMap<Integer, String>();
                while (iterator.hasNext())
                {
                    Role role = (Role)iterator.next();
                    roleNames += role.getRoleName()+",";
                    rolemap.put(role.getRoleId(),""+role.getRoleName());
                }
                if(!roleNames.equals(""))
                {
                    roleNames = roleNames.substring(0,roleNames.length()-1);
                }
            %>
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
                        <td width="5%" align="center"><%=(i+1)%></td>
                        <td width="10%" align="center"><%=userlists.get(i).getUserName()%></td>
                        <td width="25%" align="center"><%=roleNames%></td>
                        <td width="15%" align="center"><%=userlists.get(i).getSchoolName()%></td>
                        <td width="25%" align="center"><%=userlists.get(i).getUserDesc()%></td>
                        <td width="25%" align="center">
                        <%
                            if(currentuser.getUserId()==1)
                            {
                                if(userlists.get(i).getUserId()==1 || userlists.get(i).getUserId()==2)
                                {
                        %>
                            <a href="javascript:void(0)" onclick="updateuser(<%=userlists.get(i).getUserId()%>)">修改</a>/<a href="javascript:void(0)" onclick="resetPwd(<%=userlists.get(i).getUserId()%>)">密码重置</a>
                        <%
                                }
                                else
                                {
                        %>
                                <a href="javascript:void(0)" onclick="deluser(<%=userlists.get(i).getUserId()%>)">删除</a>/<a href="javascript:void(0)" onclick="updateuser(<%=userlists.get(i).getUserId()%>)">修改</a>/<a href="javascript:void(0)" onclick="resetPwd(<%=userlists.get(i).getUserId()%>)">密码重置</a>
                        <%
                                }
                            }
                            else if(currentuser.getUserId()==2)
                            {
                                if(userlists.get(i).getUserId()==2)
                                {
                        %>
                            <a href="javascript:void(0)" onclick="updateuser(<%=userlists.get(i).getUserId()%>)">修改</a>/<a href="javascript:void(0)" onclick="resetPwd(<%=userlists.get(i).getUserId()%>)">密码重置</a>
                        <%
                                }
                                else if(userlists.get(i).getUserId()>2)
                                {
                        %>
                            <a href="javascript:void(0)" onclick="deluser(<%=userlists.get(i).getUserId()%>)">删除</a>/<a href="javascript:void(0)" onclick="updateuser(<%=userlists.get(i).getUserId()%>)">修改</a>/<a href="javascript:void(0)" onclick="resetPwd(<%=userlists.get(i).getUserId()%>)">密码重置</a>
                        <%
                                }
                            }
                            else
                            {
                                if(!rolemap.containsKey(1) && !rolemap.containsKey(2))
                                {
                        %>
                            <a href="javascript:void(0)" onclick="deluser(<%=userlists.get(i).getUserId()%>)">删除</a>/<a href="javascript:void(0)" onclick="updateuser(<%=userlists.get(i).getUserId()%>)">修改</a>/<a href="javascript:void(0)" onclick="resetPwd(<%=userlists.get(i).getUserId()%>)">密码重置</a>
                        <%
                                }
                                else if(userlists.get(i).getUserId()==currentuser.getUserId())
                                {
                        %>
                                    <a href="javascript:void(0)" onclick="updateuser(<%=userlists.get(i).getUserId()%>)">修改</a>/<a href="javascript:void(0)" onclick="resetPwd(<%=userlists.get(i).getUserId()%>)">密码重置</a>
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
            }
        %>
    </table>
    <%
        int pageCount = Integer.parseInt(request.getAttribute("pageCount").toString());
        int currentPage = Integer.parseInt(request.getAttribute("currentPage").toString());
        Pager pager = new Pager(pageCount,currentPage,"<span class='pages_prevpage'></span>","<span class='pages_nextpage'></span>","","",false);
        String pagers = pager.run();
    %>
    <div id="linkpage">
        <%=pagers%>
    </div>


