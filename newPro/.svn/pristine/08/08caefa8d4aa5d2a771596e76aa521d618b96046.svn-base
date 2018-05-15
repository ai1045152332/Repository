<%@ page import="com.honghe.recordhibernate.entity.User" %>
<%@ page import="com.honghe.recordweb.action.SessionManager" %>
<%@ page import="com.honghe.recordweb.service.frontend.user.Role" %>
<%@ page import="com.honghe.recordweb.service.frontend.user.UserService" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.honghe.recordweb.util.ConfigureUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="public_head_system">
    <%
        User curruser = SessionManager.get(request.getSession(), SessionManager.Key.USER);
        Integer uid = curruser.getUserId();
        UserService userService = new UserService();
        Map<String, String> userMap = userService.getRoleMapByUserid(uid);
        if ((userMap.containsValue(Role.Role_HhrecManager.toString()) || userMap.containsValue(Role.Role_Viewclass.toString())) && ConfigureUtil.isHhrec()) {
    %>
    <li url="">录播设备</li>
    <%
    } else if ((userMap.containsValue(Role.Role_HhtcManger.toString()) || userMap.containsValue(Role.Role_Dmanager.toString())) && ConfigureUtil.isHhtc()) {
    %>
    <li url="">大屏设备</li>
    <%
    } else if (userMap.containsValue(Role.Role_ProjectorManger.toString()) && ConfigureUtil.isHtpr()) {
    %>
    <li>投影仪设备</li>
    <%
    }else {
        if (ConfigureUtil.isHhtc()) {
    %>

    <li url="">大屏设备</li>
    <%
        }
        if (ConfigureUtil.isHhrec()){
    %>
    <li>录播设备</li>
    <%
        }
        if (ConfigureUtil.isHtpr()) {
    %>
        <li>投影仪设备</li>
    <%
        }
        if (ConfigureUtil.isHhtwb()) {
    %>
        <li>白板一体机设备</li>
    <%
        }
    }
    %>
</div>