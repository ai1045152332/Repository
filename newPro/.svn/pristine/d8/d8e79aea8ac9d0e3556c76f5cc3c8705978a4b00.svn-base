
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.honghe.recordweb.util.base.entity.RefreshRandom" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%String signre=request.getParameter("signre"); %>
<script type="text/javascript">
	
</script>
<%
RefreshRandom refreshRandom = new RefreshRandom();
int randomjs = refreshRandom.getRandomjs();
 %>
<!-- 引入模块自有的js -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/common/extends/easyui-extends.js?randomjs=<%=randomjs %>">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/common/jquery-autocomplete/jquery.autocomplete.js?randomjs=<%=randomjs %>">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/common/jquery-autocomplete/jquery.browser.js?randomjs=<%=randomjs %>">
</script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/common/jquery-autocomplete/jquery.autocomplete.css?randomjs=<%=randomjs %>">
<!--  
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/modules/device/deviceList.js" charset="utf-8">
</script>
-->

<!-- 右边部分 -->
<!--<div class="master-content">-->
	<!--<div class="master-contentheader">
		
	</div>-->
<div>
	<jsp:include page="/pages/frontend/tresource/quickReplaceList.jsp"></jsp:include>
</div>