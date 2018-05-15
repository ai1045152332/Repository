<%--
  Created by IntelliJ IDEA.
  User: Wen
  Date: 2014/11/4
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
    <title>集控后台首页</title>
    <link href="../../css/frontend/index.css" rel="stylesheet" type="text/css" />
    <link href="../../css/frontend/main.css" rel="stylesheet" type="text/css" />
    <script src="../../js/common/jquery-1.9.1.min.js"></script>
    <script src="../../js/common/checkbox.js"></script>
    <script src="../../js/common/change49.js"></script>
</head>

<body>
<div class="public">
    <table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
        <jsp:include page="../header.jsp"></jsp:include>
        <tr>
            <td background="#f2f2f2" width="100%" height="100%" >
                <div class="user">
                    <div class="user_center">
                        <div class="mm_head">
                            <a href="${pageContext.request.contextPath}/hostgroup/Hostgroup_management.do"><span class="mm_nogroup_option"><span class="mm_nogroup_icon"></span>返回</span></a>
                            <a href="javascript:void(0)"><span class="mm_nogroup_option"><span class="mm_nogroup_moveto"></span>移动到</span></a>
                            <a href="javascript:void(0)"><span class="mm_nogroup_option"><span class="mm_nogroup_del"></span>删除设备</span></a>
                            <a href="javascript:void(0)"><span class="mm_nogroup_option"><span class="mm_nogroup_refrash"></span>刷新</span></a>
					<span class="user_search">
							<input type="text" class="user_search_input" placeholder="请输入用户名"/>
							<input type="button" class="user_search_btn" />
						</span>
                        </div>
                        <div class="user_checkall">
                            <div class="checkbox">
                                <input type="checkbox" />
                            </div>
                            <div class="all">
                                <div class="head" style="margin-left: 30px;">
                                    <div class="bg" style="margin-top: 0;"></div>全选
                                </div>
                            </div>
                        </div>
                        <%
                            List<Map> hostNoRelationList = (List<Map>) request.getAttribute("hostNoRelationList");
                            for (Map<String, Object> obj : hostNoRelationList) {
                                String status="mm_offline";
                                String font = "离线";
                        %>
                        <div class="mm_nogroup_list" bingo="none" divHostId="<%=obj.get("host_id")%>">
                            <a href="javascript:void(0)">
						<span class="mm_nogroup_list_text">
							<span class="mm_nogroup_list_machinename">设备名称:&nbsp;</span>
							<span class="mm_nogroup_list_machinetext">高一（1）班</span>
						</span>
						<span class="mm_nogroup_list_text">
							<span class="mm_nogroup_list_machinename">设备IP:&nbsp;</span>
							<span class="mm_nogroup_list_machinetext">192.168.200.200</span>
						</span>
						<span class="mm_nogroup_list_text">
							<span class="mm_nogroup_list_machineset"></span>
							<span class="mm_nogroup_list_machineadd"></span>
						</span>
                                <span class="xk_video_selected_bz"></span>
                            </a>
                        </div>
                        <%--<div class="mm_nogroup_list" bingo="none">
                            <a href="#">
						<span class="mm_nogroup_list_text">
							<span class="mm_nogroup_list_machinename">设备名称:&nbsp;</span>
							<span class="mm_nogroup_list_machinetext">高一（1）班</span>
						</span>
						<span class="mm_nogroup_list_text">
							<span class="mm_nogroup_list_machinename">设备IP:&nbsp;</span>
							<span class="mm_nogroup_list_machinetext">192.168.200.200</span>
						</span>
						<span class="mm_nogroup_list_text">
							<span class="mm_nogroup_list_machineset"></span>
						</span>
                                <span class="xk_video_selected_bz"></span>
                            </a>
                        </div>
                        --%>


                    </div>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2" width="100%">
                <div class="foot">
                    <div class="foot_center">Copyright&copy;2003-2014HongHe.ALL Rights Reserved</div>
                </div>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
