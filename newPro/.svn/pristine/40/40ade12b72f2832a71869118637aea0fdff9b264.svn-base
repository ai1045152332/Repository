<%@ page import="com.honghe.version.Version" %>
<%@ page import="com.honghe.recordweb.util.LicenseUtils" %>
<%@ page import="java.util.Map" %>
<%--
  Created by IntelliJ IDEA.
  User: zhanghongbin
  Date: 14-10-8
  Time: 下午4:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String versionInfo = Version.getVersionInfo();
  Map<String,String> data = LicenseUtils.findLicense();
  String licenseInfo = "未授权";
  if(!data.isEmpty()){
    licenseInfo = data.get("device_desc");
  }
  String vp = Version.getVersionInfo("vp");
%>
<div class="foot_center">&copy;2003-2016HiteVision.All Rights Reserved <%=versionInfo%>  <%=licenseInfo%></div>
            <%--<div class="foot_center floatleft">&copy;2003-2015HongHe.All Rights Reserved &nbsp;&nbsp;Version:V2.0.1.20150918(9219)</div>--%>
<script>
  $(function() {
    $.ajaxSetup({
      contentType : "application/x-www-form-urlencoded;charset=utf-8",
      complete : function(XMLHttpRequest, textStatus) {
        var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus"); //通过XMLHttpRequest取得响应头，sessionstatus，
        if (sessionstatus == "timeout") {
          //如果超时就处理 ，指定要跳转的页面
          window.location.href="${pageContext.request.contextPath}";
        }
      }
    });
  });
</script>