<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shop" uri="http://localhost:8080/Eshop/util" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/plugins/bootstrap/css/bootstrap.css" />
<script
	src="${pageContext.request.contextPath}/static/js/jquery-1.11.1.js"
	type="text/javascript" charset="utf-8"></script>
<script
	src="${pageContext.request.contextPath}/static/plugins/bootstrap/js/bootstrap.js"
	type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	function checkDel() {
		if(confirm("确定要删除吗，你忍心吗？")) {
			return true;
		} else {
			return false;
		}
	}
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<h3><font face="楷体">管理员信息列表</font></h3>
				<table class="table table-bordered">
					<tr>
						<th>ID号</th>
						<th>用户名</th>
						<th>密码</th>
						<th>联系方式</th>
						<th>创建日期</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${list }" var="bean">
						<tr>
							<td>${bean.id }</td>
							<td>${bean.username }</td>
							<td>${bean.password }</td>
							<td>${bean.cellphone }</td>
							<td>${bean.createDate }</td>
							<td class="btn btn-default">
								<a href="${pageContext.request.contextPath}/admin/getByIdAdminAction?id=${bean.id}">修改</a>
								&nbsp;&nbsp;&nbsp;
								<a onclick="return checkDel()" href="${pageContext.request.contextPath}/admin/deleteAdminAction?id=${bean.id }">删除</a>			
							</td>
						</tr>
					</c:forEach>
				</table>
				<table>
					<tr>
						<td>
							<shop:page pagingBean="${pagingBean}"/>
						</td>	
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>