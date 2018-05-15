<%--
  Created by IntelliJ IDEA.
  User: chby
  Date: 2014/10/23
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="/css/frontend/index.css" rel="stylesheet" type="text/css"/>
<link href="/css/frontend/main.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frontend/device/group.js"></script>
<script src="${pageContext.request.contextPath}/js/common/jquery-1.8.0.min.js"></script>
<script src="${pageContext.request.contextPath}/js/common/jquery.cookie.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/common/autoheight.js"></script>
<div class="win360">
    <div class="win_head">
        <div class="win_head_text">删除设备</div>
        <div class="win_close"></div>
    </div>
    <div  class="win360_content" style="height: 115px;">
        <div class="win360_content_text"  style="margin-top: 25px;">确定删除此设备吗？</div>
        <div class="win360_content_text" style="margin-top: 20px;">
            <div class="win_btn_sure">确定</div>
            <div class="win_btn_done">取消</div>
        </div>
    </div>
</div>