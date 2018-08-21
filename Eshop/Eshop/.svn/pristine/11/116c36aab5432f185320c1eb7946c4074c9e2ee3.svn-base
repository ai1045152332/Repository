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
<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<h1>商品类型信息列表</h1>
				<table class="table table-bordered">
					<tr>
						<th>id</th>
						<th>名称</th>
						<th>序号</th>
						<th>创建时间</th>
						<th>创建者</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${list }" var="bean">
						<tr>
							<td>${bean.id }</td>
							<td>${bean.name }</td>
							<td>${bean.sort }</td>
							<td>${bean.createDate }</td>
							<td>${bean.adminBean.username }</td>
							<td><a class="btn btn-default"
								href="${pageContext.request.contextPath}/admin/toUpdateProductTypeAction?id=${bean.id}">修改</a>
								<a class="btn btn-default" onclick="checkDel(this, ${bean.id})"
								href="#">删除</a>
								<a class="btn btn-default"
								href="${pageContext.request.contextPath}/admin/listProductPropertyAction?typeId=${bean.id}">查看属性</a>
							</td>
						</tr>
					</c:forEach>
				</table>
				<shop:page pagingBean="${pagingBean }"/>
				<%-- <ul class="pagination">
					<c:choose>
						<c:when test="${currentPage>0 }">
							<li><a
								href="${pageContext.request.contextPath}/admin/listProductTypeAction?currentPage=0"
								aria-label="Previous"> <span aria-hidden="true">首页</span>
							</a></li>
							<li><ad
								href="${pageContext.request.contextPath}/admin/listProductTypeAction?currentPage=${currentPage-1}"
								aria-label="Previous"> <span aria-hidden="true">上一页</span>
							</a></li>
						</c:when>
						<c:otherwise>
							<li class="disabled"><a href="#" aria-label="Previous">
									<span aria-hidden="true">首页<span>
							</a></li>
							<li class="disabled"><a href="#" aria-label="Previous">
									<span aria-hidden="true">上一页</span>
							</a></li>
						</c:otherwise>
					</c:choose>


					<c:choose>
						<c:when test="${currentPage<(totalPage-1) }">
							<li><a
								href="${pageContext.request.contextPath}/admin/listProductTypeAction?currentPage=${currentPage+1}"
								aria-label="Previous"> <span aria-hidden="true">下一页</span>
							</a></li>
							<li><a
								href="${pageContext.request.contextPath}/admin/listProductTypeAction?currentPage=${totalPage-1}"
								aria-label="Previous"> <span aria-hidden="true">尾页</span>
							</a></li>
						</c:when>
						<c:otherwise>
							<li class="disabled"><a href="#" aria-label="Next"> <span
									aria-hidden="true">下一页</span>
							</a></li>
							<li class="disabled"><a href="#" aria-label="Next"> <span
									aria-hidden="true">尾页</span>
							</a></li>
						</c:otherwise>
					</c:choose>
					<li class="disabled"><a href="#" aria-label="Next"> <span
							aria-hidden="true">${currentPage+1 }/${totalPage }</span>
					</a></li>
				</ul> --%>
				<!-- 分页2 
				<ul class="pagination">
					<c:choose>
						<c:when test="${pagingBean.currentPage>0 }">
							<li><a
								href="${pagingBean.prefixUrl }?currentPage=0"
								aria-label="Previous"> <span aria-hidden="true">首页</span>
							</a></li>
							<li><ad
								href="${pagingBean.prefixUrl }?currentPage=${pagingBean.currentPage-1}"
								aria-label="Previous"> <span aria-hidden="true">上一页</span>
							</a></li>
						</c:when>
						<c:otherwise>
							<li class="disabled"><a href="#" aria-label="Previous">
									<span aria-hidden="true">首页<span>
							</a></li>
							<li class="disabled"><a href="#" aria-label="Previous">
									<span aria-hidden="true">上一页</span>
							</a></li>
						</c:otherwise>
					</c:choose>


					<c:choose>
						<c:when test="${pagingBean.currentPage<(pagingBean.pageCount-1) }">
							<li><a
								href="${pagingBean.prefixUrl }?currentPage=${pagingBean.currentPage+1}"
								aria-label="Previous"> <span aria-hidden="true">下一页</span>
							</a></li>
							<li><a
								href="${pagingBean.prefixUrl }?currentPage=${pagingBean.pageCount-1}"
								aria-label="Previous"> <span aria-hidden="true">尾页</span>
							</a></li>
						</c:when>
						<c:otherwise>
							<li class="disabled"><a href="#" aria-label="Next"> <span
									aria-hidden="true">下一页</span>
							</a></li>
							<li class="disabled"><a href="#" aria-label="Next"> <span
									aria-hidden="true">尾页</span>
							</a></li>
						</c:otherwise>
					</c:choose>
					<li class="disabled"><a href="#" aria-label="Next"> <span
							aria-hidden="true">${pagingBean.currentPage+1 }/${pagingBean.pageCount }</span>
					</a></li>
				</ul>
				-->
			</div>
		</div>
	</div>
</body>
</html>