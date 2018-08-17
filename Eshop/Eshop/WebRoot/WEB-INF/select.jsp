<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
	<table class="table table-borderd" border="2" bgcolor="gray">
	<c:forEach items="${productbean }" var="type">
				<tr>	
					<td><font color="#FF3333" size="4">商品编号:</font></td>
					<td><font color="#FF3333" size="4">${type.id }</font></td>
				</tr>
				<tr>
					<td>价格</td>
					<td>${type.price }</td>
				</tr>
				<tr>
					<td>原价</td>
					<td>${type.originalPrice }</td>
				</tr>
				<tr>
					<td>商品数量</td>
					<td>${type.num }</td>	
				</tr>
				<tr>					
					<td>商品属性</td>
					<td>${type.propertys }</td>
				</tr>
				<tr>
					<td>商品介绍</td>
					<td>${type.intro }</td>
				</tr>
		</c:forEach></div></div></div>
	</body>
</html>