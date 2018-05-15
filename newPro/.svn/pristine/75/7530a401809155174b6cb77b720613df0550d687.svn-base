<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.honghe.recordhibernate.dao.Pager" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: lch
  Date: 2015/1/14
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="${pageContext.request.contextPath}/css/frontend/index.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/frontend/hpager.css" rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
<%
    int pageCount = Integer.parseInt(request.getAttribute("pageCount").toString());
    Integer currentpage = Integer.parseInt(request.getAttribute("currentPage").toString());
    Pager pager = new Pager(pageCount, currentpage, 3, "<span class='pages_prevpage'></span>", "<span class='pages_nextpage'></span>", "", "", false);
    // Pager pager = new Pager(hostcount,currentpage);
    String pagers = pager.run();
%>
<style>

    #linkpage {
        position: absolute;
        bottom: 45px;
        left: 55%;
        text-align: center
    }

    .tr {
        background: none repeat scroll 0% 0% #FFF;
        display: table;
        margin-bottom: 3px;
    }
</style>
<div class="wid_100 floatleft hei_99" style="margin-top: 1%;">
    <s:iterator value="#monitorList">
        <div class="wid_100 floatleft hei_7 lh300" style="margin-top: 0.25%; background: white;text-align: center">
            <div class="wid_15 floatleft hei_100">${deviceName}<!--设备名称--></div>
            <div class="wid_15 floatleft hei_100">${mac}<!--物理地址--></div>
            <div class="wid_20 floatleft hei_100">${softName}<!--软件名--></div>
            <div class="wid_10 floatleft hei_100">${usecount}<!--使用次数--></div>
            <div class="wid_24 floatleft hei_100">保留<!--时间段--></div>
            <div class="wid_16 floatleft hei_100">${createTime}<!--操作时间-->    </div>

        </div>

    </s:iterator>
</div>
<div id="linkpage">
    <%=pagers%>
</div>


<script>
    var totalpagesize = "<%=pageCount%>";
    //分页调整
    var page = totalpagesize;
    var html = "<span style='float: left;margin-left: 2px;'>&nbsp;&nbsp;共<span id='pageCount'>" + page + "</span>页 &nbsp;&nbsp;跳转到 " + "<input id=\"jump\" type=text style=\"width:20px;background:#d7dade;border:1px solid #5e6470\" title=\"点击回车即可跳转分页\" onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'')\" onafterpaste=\"this.value=this.value.replace(/[^0-9]/g,'')\"/> 页<span>";
    $(".yiiPager").append(html)
    left = -(18) * 28 / 2 + "px";
    $("#linkpage").css({"margin-left": left, "width": (17) * 28 + "px"});
</script>
