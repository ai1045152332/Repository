<%@page import="com.shop.util.Constants"%>
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
	
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<h1>商品信息列表</h1>
				<table class="table table-bordered">
					<tr>
						<th>id</th>
						<th>商品名称</th>
						<th>价格</th>
						<th>原价</th>
						<th>数量</th>
						<th>销售量</th>
						<th>类型</th>
						<th>属性</th>
						<th>介绍</th>
						<th>图片</th>
						<th>创建时间</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${list }" var="bean">
						<tr>
							<td>${bean.id }</td>
							<td>${bean.name }</td>
							<td>${bean.price }</td>
							<td>${bean.originalPrice }</td>
							<td>${bean.num }</td>
							<td>${bean.selledNum }</td>
							<td>${bean.productTypeBean.name }</td>
							<td>${bean.propertys }</td>
							<td>${bean.intro }</td>
							<td><img width="50px" height="50px" alt="" src="<%=Constants.SHOW_PRODUCT_LIST %>${bean.pic }"> </td>
							<td>${bean.createDate }</td>
							<td>
								<a href="${pageContext.request.contextPath }/admin/toUpdateProductAction?id=${bean.id}">修改</a>
							</td>
						</tr>
					</c:forEach>
				</table>
				<shop:page pagingBean="${pagingBean }"/> 
			</div>
		</div>
	</div>
</body>
</html>