<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang3.StringUtils"%>
<%@ page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page import="com.honghe.recordweb.util.base.util.IncludePageHelper" %>
<%@ page import="com.honghe.recordweb.util.base.entity.RefreshRandom" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!DOCTYPE HTML>
<!-- 共用的js,css页面将会配置在此,导入jquery,在ie8及以下导入jquery1.8.0,其则用2.0.3 -->
<%
	String jqueryVersion=null;
	String easyuiLocale=null;
	String easyuiTheme=null;
	String easyuiVersion=null;
	IncludePageHelper includeHelper=new IncludePageHelper();
	jqueryVersion=includeHelper.getJqueryVersion(request, session);
	easyuiLocale=includeHelper.getEasyuiLocale(request, session);
	//easyuiTheme=includeHelper.getEasyuiTheme(request);
	easyuiTheme="green";
	easyuiVersion=includeHelper.getEasyuiVersion();
	
	RefreshRandom refreshRandom = new RefreshRandom();
	int randomjs = refreshRandom.getRandomjs();
%>

<!-- 根据其所选择的语言做框架的选择调整 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/stringUtil.js?randomjs=<%=randomjs %>" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/regUtil.js?randomjs=<%=randomjs %>" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/jquery-<%=jqueryVersion%>.min.js?randomjs=<%=randomjs %>" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/jquery-easyui-<%=easyuiVersion%>/jquery.easyui.min.js?randomjs=<%=randomjs %>" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pages/frontend/news/editor/js/jquery-easyui-<%=easyuiVersion%>/locale/easyui-lang-zh_CN.js?randomjs=<%=randomjs %>" charset="utf-8"></script>
<link id="easyuiTheme" rel="stylesheet" href="${pageContext.request.contextPath}/pages/frontend/news/editor/js/jquery-easyui-<%=easyuiVersion%>/themes/<%=easyuiTheme%>/easyui.css?randomjs=<%=randomjs %>" type="text/css"/>
<link id="easyuiThemeIcon" rel="stylesheet" href="${pageContext.request.contextPath}/pages/frontend/news/editor/js/jquery-easyui-<%=easyuiVersion%>/themes/icon.css?randomjs=<%=randomjs %>" type="text/css"/>
<!-- <link id="master" rel="stylesheet" href="${pageContext.request.contextPath}/css/master.css" type="text/css"/> -->
<link id="master" rel="stylesheet" href="${pageContext.request.contextPath}/pages/frontend/news/editor/css/editor/master.css?randomjs=<%=randomjs %>" type="text/css"/>
<link id="easyuimaster" rel="stylesheet" href="${pageContext.request.contextPath}/pages/frontend/news/editor/css/editor/easyui-controller.css?randomjs=<%=randomjs %>" type="text/css"/>
<script>  
<%--JS gloable varilible--%>
	var requestContextPath = "${pageContext.request.contextPath}";
</script>  
