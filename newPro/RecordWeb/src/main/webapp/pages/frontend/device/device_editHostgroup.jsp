<%--
  Created by IntelliJ IDEA.
  User: chby
  Date: 2014/10/9
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
    <title></title>
</head>
<body>
<%

    response.setHeader("Cache-Control","no-store");

    response.setHeader("Pragrma","no-cache");

    response.setDateHeader("Expires",0);

%>
    <div>
        <span>请输入年级组名称</span>
        <input type="text" maxlength="20" id="hostgroupName" />
    </div>
    <div>
        <input type="button" id="cancel" width="220px" height="35px" name="cancel" value="取消" onclick="cancelClick()" />
        <input type="button" id="confirm" width="220px" height="35px" name="confirm" value="确认" onclick="confirmClick()" />
    </div>
</body>
</html>
<script>


    function confirmClick()
    {
        alert("修改成功");
        window.opener.location.href.reload();
    }
    function cancelClick()
    {
        alert("取消");
    }
</script>