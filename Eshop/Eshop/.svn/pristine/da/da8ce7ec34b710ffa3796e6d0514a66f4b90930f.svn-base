<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title></title>
  </head>
  <script type="text/javascript">
	function checkDel() {
		if(confirm("确定要删除吗，你忍心吗？")) {
			return true;
		} else {
			return false;
		}
	}
</script>
  <body>
    	<a href="listUserAction">查询</a>
    	<a href="userUpdate.jsp">添加</a>
   			<table border="2">
   				<tr><th>姓名</th><th>密码</th><th>联系方式</th><th>创建时间</th><th>操作</th></tr>
   				<c:forEach items="${list}" var="bean">
   				<tr>
   					<td>${bean.username}</td>
   					<td>${bean.password }</td>
   					<td>${bean.cellphone}</td>
   					<td>${bean.create_date}</td>
   					<td>
   						<a href="getByIdUserAction?id=${bean.id}">修改</a>
   						|<a onclick="return checkDel()" href="deleteUserAction?id=${bean.id }">删除</a>
   					</td>
   				</tr>
   			</c:forEach>
   		</table>
  </body>
</html>
