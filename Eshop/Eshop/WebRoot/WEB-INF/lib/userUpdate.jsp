<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title></title>
  </head>
  
  <body>
   	 <form action="addUserAction" method="post">
   			<table>
   			<c:forEach items="${userBean1}" var="bean">
   				<tr>
   					<td>姓名：</td>
   					<td><input name="userBean.username" value="${bean.username}"></td>
   				</tr>
   				<tr>
   					<td>密码：</td>
   					<td><input name="userBean.password" value="${bean.password}"></td>
   				</tr>
   				<tr>
   					<td>联系方式：</td>
   					<td><input name="userBean.cellphone" value="${bean.cellphone}"></td>
   				</tr>
   				<tr>
   					<td>创建时间</td>
   					<td><input name="userBean.create_date" value="${bean.create_date}"></td>
   				</tr>
   				<tr>
   					<td><input type="submit" value="提交"></td>
   				</tr>
   				<c:if test="${bean!=null}">
   					<input type="hidden" name="userBean.id" value="${bean.id}">
   				</c:if>
   			</c:forEach>
   			</table>
   		</form>	
  </body>
</html>
