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
			//ajax 删除
			$.post("${pageContext.request.contextPath}/admin/delProductTypeAction", { "id": id },
			   function(data){
			     if(data=="1") {
			    	 //删除成功
			    	 alert("删除成功");
			    	 var tr = obj.parentNode.parentNode;
			    	 tr.parentNode.removeChild(tr);
			     } else if(data=="2") {
			    	 //该类型有属性，不可删除
			    	 alert("该类型有属性，不可删除");
			     } else {
			    	 alert("删除失败");
			     }
			   });
		}
	}
</script>
</head>
<body bgcolor="eff2fb">
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<h1>商品分类</h1>
				<table class="table table-bordered">
					<tr>					
						<th>名称</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${list }" var="bean">
						<tr>
							<td>${bean.name }</td>
							<td>
								<a class="btn btn-default"
								href="${pageContext.request.contextPath}/admin/chaxunProductAction?typeId=${bean.id}">详情</a>
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