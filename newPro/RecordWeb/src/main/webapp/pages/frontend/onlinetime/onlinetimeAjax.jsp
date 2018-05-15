<%@ page import="com.honghe.recordhibernate.dao.Pager" %>
<%
  String path = request.getContextPath();
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="wid_100 floatleft hei_93" style="${onlineTimeData.size()>11?'overflow-y:scroll;height:90%':''}">
  <c:forEach var="online" items="${onlineTimeData}" varStatus="st" >
    <div  class="table_recycle" >
      <div class="wid_15 floatleft hei_100">${online.name}</div>
      <div class="wid_15 floatleft hei_100">${online.ip}</div>
      <div class="wid_15 floatleft hei_100">${online.openRate}</div>
      <div class="wid_15 floatleft hei_100">${online.openDuration}</div>
      <div class="wid_15 floatleft hei_100">${online.acitivity}</div>
      <div class="wid_25 floatleft hei_100">
        <a class="reportlistbtn" style="margin-top: 2%" href="<%=path%>/onlinetime/Onlinetime_softInUse.do?softMac=${online.mac}">软件使用情况</a>
      </div>
    </div>
  </c:forEach>
</div>
<link href="${pageContext.request.contextPath}/css/frontend/hpager.css" rel="stylesheet"
      type="text/css"/>
<%
  Integer currentpage = Integer.parseInt(request.getAttribute("currentPage").toString());
  int pageCount = Integer.parseInt(request.getAttribute("pageCount").toString());
  Pager pager = new Pager(pageCount, currentpage, 3,"<span class='pages_prevpage'></span>", "<span class='pages_nextpage'></span>", "", "", false);
  String pagers = pager.run();
%>
<style>
  #linkpage{
    position: absolute;
    bottom: 4.5%;
    left: 55%;
    text-align:center;
    margin-left: -252px;
    width: 476px;
  }
</style>
<div id="linkpage">
  <%=pagers%>
</div>
<script>
  var totalpagesize = "<%=pageCount%>";
  //分页调整
  var page=totalpagesize;
  var html="<span style='float: left;margin-left: 2px;'>&nbsp;&nbsp;共<span id='pageCount'>"+page+"</span>页 &nbsp;&nbsp;跳转到 "+"<input id=\"jump\" type=text style=\"width:20px;background:#d7dade;border:1px solid #5e6470\" title=\"点击回车即可跳转分页\" onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'')\" onkeydown=\"jump(event)\" onafterpaste=\"this.value=this.value.replace(/[^0-9]/g,'')\"/> 页<span>";
  $(".yiiPager").append(html)
  left=-(18)*28/2+"px";
  $("#linkpage").css({"margin-left":left,"width":(17)*28+"px"});
  $(function(){
    $(".wid_6,.wid_16,wid_10,.wid_36").css({"overflow":"hidden","text-overflow":"ellipsis","white-space":"nowrap"})
  })
</script>