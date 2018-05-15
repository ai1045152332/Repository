<%@ page import="com.honghe.recordhibernate.entity.tools.GlobalParameter" %>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html>
<head>
  <title>资源管理 | 集控平台</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <%--<link rel="shortcut icon" href="${pageContext.request.contextPath}/image/home/title.ico" />--%>
  <%--<link rel="Bookmark" href="${pageContext.request.contextPath}/image/home/title.ico"/>--%>

  <s:set name="moduleStr" value="#parameters.module"></s:set>
  <s:set name="moduleLowerCase" value="@org.apache.commons.lang3.StringUtils@lowerCase(#moduleStr)"></s:set>
  <%--<s:if test='#moduleLowerCase!="project"'>--%>
    <jsp:include page="/pages/frontend/include/include.jsp"></jsp:include>
  <%--</s:if>--%>
  <%--<s:else>--%>
    <%--<jsp:include page="/pages/frontend/include/include-project.jsp"></jsp:include>--%>
  <%--</s:else>--%>
  <script type="text/javascript">
    if (top.location != self.location)top.location=self.location;
  </script>
</head>
<body>
<div class="public" style="float: left;">

  	<style>
  		.master-full{
  			width: 100%;
  		}
  		.master-header{
  			float: left;
  			background: none;
  			padding-top: 0
  		}
  	</style>
    <jsp:include page="/pages/frontend/equipment_header.jsp">
      <jsp:param name="module" value="${moduleStr}"/>
    </jsp:include>

  <script src="${pageContext.request.contextPath}/js/common/screendetail.js"></script>
  <script>
  	$(function(){
  		 $(".public_head_logo").height($(".public_head").height())
  	})
  </script>
  <div class="master-mainbody">
    <%--<s:if test='#moduleLowerCase=="quickreplace"'>--%>
      <%--<jsp:include page='/pages/frontend/tresource/quickReplaceHome.jsp'></jsp:include>--%>
    <%--</s:if>--%>
    <%--<s:else>--%>
      <jsp:include page='/pages/frontend/tresource/index.jsp'></jsp:include>
    <%--</s:else>--%>
  </div>

  <div class="foot">
    <jsp:include page="/pages/frontend/footer.jsp"></jsp:include>
  </div>
</div>
</body>
</html>
