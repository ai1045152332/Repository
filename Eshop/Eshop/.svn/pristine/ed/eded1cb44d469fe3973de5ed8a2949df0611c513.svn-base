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
	function checkDel(obj, id) { 
		if (confirm("真的删除？")) {
			$.post("${pageContext.request.contextPath}/admin/del2ProductPropertyAction", { "id": id },
			   function(data){
			     if(data=="1") {
			    	 //删除成功
			    	 alert("删除成功");
			    	 var tr = obj.parentNode.parentNode;
			    	 tr.parentNode.removeChild(tr);
			     } else if(data=="2") {
			    	 alert("该属性有选项，不可删除");
			     } else {
			    	 alert("删除失败");
			     }
			   });
		}
	}
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<h1>商品属性信息列表<small><a href="${pageContext.request.contextPath}/admin/toAddProductPropertyAction?typeId=${typeId}">添加属性</a></small></h1>
				<table class="table table-bordered">
					<tr>
						<th>id</th>
						<th>名称</th>
						<th>序号</th>
						<th>创建时间</th>
						<th>分类</th>
						<th>创建者</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${list }" var="bean">
						<tr>
							<td>${bean.id }</td>
							<td>${bean.name }</td>
							<td>${bean.sort }</td>
							<td>${bean.createDate }</td>
							<td>${bean.productTypeBean.name }</td>
							<td>${bean.adminBean.username }</td>
							<td><a class="btn btn-default"
								href="${pageContext.request.contextPath}/admin/toUpdateProductPropertyAction?id=${bean.id}">修改</a>
								<a class="btn btn-default" onclick="return checkDel(this, ${bean.id})"
								href="#">删除</a>
								<a class="btn btn-default"
								href="${pageContext.request.contextPath}/admin/listProductPropertyOptionAction?propertyId=${bean.id}&typeId=${typeId}">查看选项</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>