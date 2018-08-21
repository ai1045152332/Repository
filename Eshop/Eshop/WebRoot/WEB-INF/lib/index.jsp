<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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

<form action="addTestAction" method="post">
	<table>
		<tr><td>name：</td><td><input name="testBean.name" value="${bean.name }"/></td></tr>
		<tr><td>address：</td><td><input name="testBean.address" value="${bean.address }"/></td></tr>
		<tr><td>major：</td><td><input name="testBean.major" value="${bean.major }"/></td></tr>
		<tr><td></td><td><input type="submit" value="submit"/></td></tr>
		<c:if test="${bean!=null }">
			<input type="hidden" name="testBean.id" value="${bean.id }"/>
		</c:if>
	</table>
</form>
<a href="listTestAction">查看</a>

<table border="1px">
	<tr><td>name</td><td>address</td><td>major</td><td>操作</td></tr>
	<c:forEach items="${list }" var="bean">
		<tr>
			<td>${bean.name }</td>
			<td>${bean.address }</td>
			<td>${bean.major }</td>
			<td>
				<a href="toUpdateTestAction?id=${bean.id }">修改</a>
				<a onclick="return checkDel()" href="delTestAction?id=${bean.id }">删除</a>
			</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>