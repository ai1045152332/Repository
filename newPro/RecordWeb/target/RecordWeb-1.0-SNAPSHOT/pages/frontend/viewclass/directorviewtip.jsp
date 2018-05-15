<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html
PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<!-- scripting.qdoc -->
<head>
    <title>导播</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/jquery-1.9.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/jquery.cookie.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/lb_common/autoheight.js"></script>
</head>
<body>
<%
    String hostname = request.getAttribute("hostname").toString();
    String token = request.getAttribute("token").toString();
    String hostid = request.getAttribute("hostid").toString();
    String recordType = request.getAttribute("recordType").toString();
    String currentPage = request.getParameter("currentPage");
%>
<div class="public">
    <%--<table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">--%>
        <jsp:include page="/pages/frontend/lb_header.jsp"></jsp:include>
        <style>
            .mm_head_option{
                text-indent: 10px;
            }
        </style>
        <div class="mm">
            <div class="mm_head">
                <%--<a href="javascript:history.back()">--%>
                <a href="javascript:back()">
                    <span class="mm_head_option" ><span class="mm_nogroup_icon"></span>返回</span>
                </a>
                <span class="groupname"><%=hostname%></span>
            </div>
            <div class="obj" style="width: 100%;background: #fff;display: block;">
                <div class="directview">
                    <div class="error">
                        <%--<div class="error_text"></div>--%>
                        <div class="error_pic">
                            <br/>
                            <span class="error_pictext">导播台已有人操作 <a href="javascript:direct()" style="color: red;text-decoration: underline">强制踢出</a> </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    <div class="foot">
        <jsp:include page="/pages/frontend/footer.jsp"></jsp:include>
    </div>
</div>
<script>
    var token = "<%=token%>";
    var hostname = "<%=hostname%>";
    var hostid = "<%=hostid%>";
    var recordType = "<%=recordType%>";

    function direct(){
        hostname = encodeURI(hostname);
        var url = "${pageContext.request.contextPath}/viewclass/Viewclass_removeDirectPoolToken.do";
        try{
            $.post(url, {token: token},function (data){
                location.href = "${pageContext.request.contextPath}/viewclass/Viewclass_directorView.do?currentPage="+<%=currentPage%>+"&hostid=" + hostid + "&token=" + token + "&hostname=" + hostname + "&recordType=" + recordType;
            });
        }catch(e){

        }


    }
    function back(){
//        history.back();
        location.href = "${pageContext.request.contextPath}/viewclass/Viewclass_getHostGroup.do?currentPage="+<%=currentPage%>;
    }
</script>
</body>
</html>
