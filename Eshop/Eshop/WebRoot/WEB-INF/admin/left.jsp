<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="${pageContext.request.contextPath}/static/js/jquery-1.11.1.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/css/leftMenu/menu_min.js"></script>
<script type="text/javascript">
$(document).ready(function (){
  $(".menu ul li").menu();
});
</script> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/leftMenu/css/menu-css.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/leftMenu/css/style.css"> 
<script type="text/javascript">
</script>
</head>
<body>
	<div id="content">
<div class="menu">
<ul>
	<li><a class="active" href="#">管理员</a>
		<ul style="display: block;">
			 <li><a target="mainFrame" href="${pageContext.request.contextPath}/admin/toAddAdminAction">添加管理员</a></li>
			 <li><a target="mainFrame" href="${pageContext.request.contextPath}/admin/listAdminAction">查看管理员</a></li>
		</ul>
	</li>
	<li><a href="#">商品分类管理</a>
		<ul>
			<li><a href="${pageContext.request.contextPath}/product_type/add.jsp" target="mainFrame">添加分类</a></li>
			<li><a href="${pageContext.request.contextPath}/admin/listProductTypeAction" target="mainFrame">查看分类</a></li>
		</ul>
	</li>
	<li><a href="#">商品管理</a>
		<ul>
			<li><a href="${pageContext.request.contextPath}/admin/toAddProductAction" target="mainFrame">添加商品</a></li>
			<li><a href="${pageContext.request.contextPath}/admin/listProductAction" target="mainFrame">查看商品</a></li>
		</ul>				
	</li>
	<li><a href="#">订单管理</a>
		<ul>
			<li><a href="${pageContext.request.contextPath}/listOrderAction?type=5" target="mainFrame">查看订单</a></li>
			<li><a href="${pageContext.request.contextPath}/listStoreOrderProfitAction" target="mainFrame">查看收益</a></li>
		</ul>	
	</li>
	<li><a href="#">修改资料</a>
		<ul>
			<li><a href="${pageContext.request.contextPath}/admin/toUpdateStoreAction"  target="mainFrame">修改商户资料</a></li>
		</ul>
	</li>
</ul>
</div>
</div>
</body>
</html>