<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.honghe.recordhibernate.dao.Pager" %>
<%@ page import="com.honghe.recordhibernate.dao.Page" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: lch
  Date: 2015/1/14
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>设备命令码查看 | 集控平台</title>
    <link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css"/>

    <script src="${pageContext.request.contextPath}/js/common/jquery-1.8.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/perfect-scrollbar.with-mousewheel.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/prettify.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/checkbox_res.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/selectmore.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/laydate/laydate.js"></script>

    <link href="${pageContext.request.contextPath}/css/frontend/index.css" rel="stylesheet" type="text/css" />

    <%--<link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css"/>--%>
    <link href="${pageContext.request.contextPath}/js/common/layer/skin/layer.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/js/common/layer/skin/layer.ext.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/common/screendetail.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/project/public.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
    <script src="${pageContext.request.contextPath}/js/project/checkbox-user.js"></script>
</head>
<style>
    .user div {
        display: block;
        float: left;
        line-height: 34px;
        overflow: hidden;
        text-overflow: ellipsis;
        -o-text-overflow: ellipsis;
        white-space: nowrap;
        border: 1px solid : red;

    }

    .code_search_btn,.amd_avarage{
        border-radius: 8px;
        background: #28b779;
        color: white;
        cursor: pointer;
        float: left;
        font-size: 14px;
        height: 30px;
        line-height: 30px;
        margin-top: 2px;
        text-align: center;
        width: 82px;
    }

    #linkpage {
        position: absolute;
        bottom: 10%;
        left: 55%;
        margin-left: -252px;
        width: 700px;
        /*width: 800px;*/
    }

    .tab_content_listinput {
        font-size: 12px;
        line-height: 15px;
        text-indent: 2px;
        display: block;
        float: left;
        width: 150px;
        margin-top: 2px;
        height: 30px;
    }



    .csearch {
        float: left;
        margin: 5px 5px;
        width: auto
        /*global_checkbox*/
    }

    #pages1 {
        display: inline;
        bottom: 4.5%;
        height: 25px;
        width: 40px;
        float: none;
    }

    .selec {
        float: right;
    }

    .sall {
        height: 36px;
        width: 500px;
    }

    #selectdivall0 {
        width: 115px;
        background-position: 0 0;

    }

    .selectdiv {
        background: none;
        width: 139px;
        height: 27px;
        line-height: 27px;
        margin-top: 3px;
    }

    .selectdivul {
        width: 113px;
    }

    .selectdivul a {
        text-indent: 10px;
        text-align: left;
        width: 113px;
    }

    #selectdivul0 {
        border: 1px solid #dbdbdb;
        /*	margin-top: -2px;*/
        height: 108px;
        width: 113px;
        overflow: hidden;
    }

    .tr {
        background: #fff;
        display: block;
        margin-bottom: 3px;
    }

    .laydate-icon {
        float: left;
        text-indent: 5px;
        margin-top: 2px;
        width: 100px;
    }

    .laydate-icon {
        height: 25px;
        line-height: 25px;
    }

    .deviceloginput {
        border: 1px solid #C6C6C6;
        float: left;
        height: 25px;
        line-height: 25px;
        margin-right: 30px;
        margin-top: 2px;
        outline: none;
        text-indent: 5px;
        width: 120px;
    }

    .wid_20, .wid_16, .wid_10, .wid_6, .wid_24, .wid_15 {
        overflow: hidden;
        text-overflow: ellipsis;
        text-indent: 10px;
        white-space: nowrap;
    }

    #selectdivall0 {
        background: url("${pageContext.request.contextPath}/image/frontend/n_icon_141006.png") no-repeat scroll 0px -458px transparent;
    }

    input[type="checkbox"] {
        display: block;
        float: left
    }

    .checkline {
        float: left;
        margin: 13px 13px;
        width: auto
        /*global_checkbox*/
    }
    .page div{
        line-height: 23px;
        text-align: center;
        float: none;
    }
    .page,.pages_nextpage,.pages_prevpage {
        cursor: pointer;
    }
</style>
<body>
<%
    Map<String,Object> codelists=(Map<String,Object>)request.getAttribute("codeList");
    List<Object[]> codelist=(List<Object[]>)codelists.get("codelist");
    int currentPage=Integer.parseInt(request.getAttribute("currentPage").toString());
    Page pages=(Page)codelists.get("page");
    int pageCount=pages.getTotalPageSize();
    if(codelist!=null && !codelist.isEmpty()){
        for (int i=0;i<codelist.size();i++)
        {
            Object[] commandCode = (Object[])codelist.get(i);
%>
<div class="table_recycle">
    <div class="wid_10  hei_100"><span><%=i+1%></span></div>
    <div class="wid_10 floatleft hei_100"><span title="<%=commandCode[1]==null?"":commandCode[1].toString()%>">
                    <%=commandCode[1]==null?"&nbsp;":commandCode[1].toString()%>&nbsp;<!--命令编码--></span></div>
    <div class="wid_10 floatleft hei_100"><span title="<%=commandCode[2]==null?"":commandCode[2].toString()%>">
                    <%=commandCode[2]==null?"&nbsp;":commandCode[2].toString()%>&nbsp;<!--对应的通用命令id--></span></div>
    <div class="wid_10 floatleft hei_100"><span title="<%=commandCode[3]==null?"":commandCode[3].toString()%>">
                    <%=commandCode[3]==null?"&nbsp;":commandCode[3].toString()%>&nbsp;<!--命令编码备注--></span></div>
    <div class="wid_10 floatleft hei_100"><span title="<%=commandCode[4]==null?"":commandCode[4].toString()%>">
                    <%=commandCode[4]==null?"&nbsp;":commandCode[4].toString()%>&nbsp;<!--命令编码说明--></span></div>
    <div class="wid_10 floatleft hei_100"><span title="<%=commandCode[5]==null?"":commandCode[5].toString()%>">
                    <%=commandCode[5]==null?"&nbsp;":commandCode[5].toString()%>&nbsp;<!--通信接口解释标准码--></span></div>
    <div class="wid_10 floatleft hei_100"><span title="<%=commandCode[6]==null?"":commandCode[6].toString()%>">
                    <%=commandCode[6]==null?"&nbsp;":commandCode[6].toString()%>&nbsp;<!--命令返回数值--></span></div>
    <div class="wid_10 floatleft hei_100"><span title="<%=commandCode[7]==null?"":commandCode[7].toString()%>">
                    <%=commandCode[7]==null?"&nbsp;":commandCode[7].toString()%>&nbsp;<!--命令所属组--></span></div>
    <div class="wid_10 floatleft hei_100"><span title="<%=commandCode[8]==null?"":commandCode[8].toString()%>">
                    <%=commandCode[8]==null?"&nbsp;":commandCode[8].toString()%>&nbsp;<!--设备名称--></span></div>
    <div class="wid_10 floatleft hei_100"><span >
                        <a href="javascript:void(0)" onclick="delcode(<%=commandCode[0]==null?"":commandCode[0].toString()%>)">删除</a>
                        <a href="javascript:void(0)" onclick="updatecode(<%=commandCode[0]==null?"":commandCode[0].toString()%>)">修改</a>
                    </span></div>
</div>
<%
    }

    Pager pager=new Pager(pageCount,currentPage,3,"<span class='pages_prevpage'></span>","<span class='pages_nextpage'></span>","","",false);
    String pagers=pager.run();
    String pagerschange=pagers.replace("<a","<div");
    String pagerschange1=pagerschange.replace("a>","div>");
%>
<link href="${pageContext.request.contextPath}/css/frontend/hpager.css" rel="stylesheet" type="text/css"/>
<style>
    #linkpage{
        position: absolute;
        bottom: 4.5%;
        left: 55%;
        text-align:center;
        margin-left: -252px;
        width: 476px;
    }
    .tpage{
        margin-left: 152px;
        font-size: large;
    }
</style>
<div id="linkpage">
    <%=pagerschange1%>
    <%
        if (pageCount==0){pageCount=1;}
        if (pageCount==1){
    %>
    <%--<div class="tpage">共<%=pageCount%>页</div>--%>
    <% }else {
    %>
    共<%=pageCount%>页
    跳转到<select id="pages">
    <option  value="0" selected="selected">
            <%
                   for(int i=1;i<=pageCount;i++){%>
    <option  value="<%=i%>"><%=i%>
            <% }
                 %>
</select>页
    <input type="button" name="跳转" value="跳转" onclick="go()" />
    <% }%>
</div>
<%
} else {
%>
<style>

    #selectdivall0 {
        background-position: 0 -460px;
        height: 28px;
        line-height: 28px;
        width: 115px;

    }

    .selectdiv {
        background: none;
        width: 139px;
        height: 28px;
        line-height: 28px;
        margin-top: 0;
    }

    .selectdivul {
        height: 140px;
        max-height: 0;
        width: 113px;
    }

    .selectdivul a {
        text-indent: 10px;
        text-align: left;
        line-height: 28px;
        width: 113px;
    }

    #selectdivul0 {
        border: 1px solid #dbdbdb;
        /*	margin-top: -2px;*/
        height: 108px;
        width: 113px;
        overflow: hidden;
    }

    .laydate-icon {
        float: left;
        text-indent: 5px;
        width: 100px;
    }

    .logsearch , .zdeplay{
        border-radius: 8px;
        background: #28b779;
        color: #fff;
        cursor: pointer;
        float: left;
        font-size: 14px;
        height: 27px;
        line-height: 27px;
        text-align: center;
        width: 82px;
    }

    .deviceloginput {
        border: 1px solid #C6C6C6;
        float: left;
        height: 24px;
        line-height: 24px;
        margin-right: 30px;
        outline: none;
        text-indent: 5px;
        width: 120px;
    }


</style>
<div class="error">
    <%--<div class="error_text"></div>--%>
    <div class="error_pic">
        <span class="error_pictext">暂无您要找的数据</span>
    </div>
</div>
<%
    }
%>
<script>
    function go() {
        var pages = document.getElementById("pages").value;
        var dspecName = encodeURI(document.getElementById('dspec').value);
        var cmdName = encodeURI(document.getElementById('cmdname').value);
        $.post("./frontend/devicecode/Devicecode_ccodelist.do", {
            currentPage: pages,
            dspecName: dspecName,
            cmdName: cmdName
        }, function (data) {
            $("#loglist").html(data);
        }, 'html');
    }
</script>
</body>
</html>