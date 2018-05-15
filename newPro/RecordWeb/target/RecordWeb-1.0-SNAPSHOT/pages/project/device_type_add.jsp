<%--
  Created by IntelliJ IDEA.
  User: zhanghongbin
  Date: 14-10-29
  Time: 上午10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/index.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
</head>
<body>
<%
    String typeId = request.getParameter("id");
    String name = request.getParameter("name");
    String desc = request.getParameter("desc");
    if (typeId == null) {
        typeId = "";
    }
    if (name == null) {
        name = "";
    }
    if (desc == null) {
        desc = "";
    }
%>
<div class="win520_content">
    <div style="float: left">
        <div class="tab_content_list" style="margin-top: 25px;">
            <span class="tab_content_listtext">设备类型名称：</span>
            <input type="text" name="typeName" id="typeName" value="<%=name%>" class="tab_content_listinput"
                   style="width: 180px"/>

        </div>
        <div class="tab_content_list" style="margin-top: 25px;">
            <span class="tab_content_listtext">设备类型描述：</span>
            <input type="text" name="typeDesc" id="typeDesc" value="<%=desc%>" class="tab_content_listinput"
                   style="width: 180px"/>

        </div>
        <div class="win_btn" onclick="saveDeviceType()">
            <div class="win_btn_sure" style="margin-left: 267px;float:left;">确定</div>
        </div>
    </div>
</div>
</body>
<script>
    var typeId = "<%=typeId%>";
    function saveDeviceType() {

        var typeName = $("#typeName").val().trim();
        if (typeName.length == 0 || typeName.length < 3 || typeName.length > 15) {
            layer.msg("设备类型名称3-10个字符之间");
            return;
        }
        var typeDesc = $("#typeDesc").val().trim();
        if (typeDesc.length == 0 || typeDesc.length < 3 || typeDesc.length > 15) {

            layer.msg("设备类型描述3-15个字符之间");
            return;
        }
        var params;
        var path = "${pageContext.request.contextPath}/dtype/Dtype_saveDType.do";
        if(typeId != ""){
            path = "${pageContext.request.contextPath}/dtype/Dtype_updateDtype.do";
            params = {typeId: typeId, typeName: typeName, typeDesc: typeDesc};
        }else{
            params = {typeName: typeName, typeDesc: typeDesc};
        }

        <%--$.get("${pageContext.request.contextPath}/dtype/Dtype_findDtype.do", {typeName: typeName}, function (data) {--%>
            <%--if (data.success == true) {--%>
                <%--layer.msg(data.msg);--%>
            <%--} else {--%>
            <%----%>
            <%--}--%>
        <%--}, "json");--%>
        $.get(path,params , function (data) {
            if (data.success == true) {
                layer.msg(data.msg, 1, function () {
                    parent.layer.closeAll();
                });
            } else {
                layer.msg(data.msg);
            }
        }, "json");

    }
</script>
</html>