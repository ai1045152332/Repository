<%@ page import="com.honghe.recordhibernate.entity.Host" %>
<%@ page import="com.honghe.recordhibernate.entity.Dspecification" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: chby
  Date: 2014/10/11
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="${pageContext.request.contextPath}/css/frontend/index.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/js/common/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frontend/device/group.js"></script>
<script src="${pageContext.request.contextPath}/js/common/jquery.cookie.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/common/autoheight.js"></script>
<script src="${pageContext.request.contextPath}/js/common/selectmore.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
<style>

    .sall{margin-left: 10px;width: 186px;}
    .selectdiv{background: none;}
    #selectdivall0,#selectdivall1,#selectdivall2,#selectdivall3,#selectdivall4,#selectdivall5,#selectdivall6{
        background: none;
        width: 184px;
    }
    #selectdivul1 a{
        width: 182px;
    }
    .selectdiv{
        background: url(${pageContext.request.contextPath}/image/frontend/n_icon_141016.png) 0 0 no-repeat;
        width: 184px;
        height: 27px;
        line-height: 27px;
        font-size: 12px;
    }
    .selectdivul{
        width: 182px;
    }
    .selectdivul a{
        text-indent: 10px;
        width: 182px;
        font-size: 12px;
    }

    #selectdivul0,#selectdivul1,#selectdivul2,#selectdivul3,#selectdivul4,#selectdivul5,#selectdivul6{
        border:1px solid #dbdbdb;
        margin-top: -2px;
        width: 182px;
    }
    input{
        font-size: 12px;
        font-family: "微软雅黑";
    }
</style>
<%

    response.setHeader("Cache-Control","no-store");

    response.setHeader("Pragrma","no-cache");

    response.setDateHeader("Expires",0);

%>
<div class="win410" >

    <div class="win_content" style="width: 410px;height:300px;margin: 20px 0px 0px 0px;">
        <%
            Host host = (Host)request.getAttribute("hostInfo");
            Integer dspecId = host.getDspecification()!=null ? host.getDspecification().getDspecId() : 1;
        %>
        <div style="float:left;width: 100%">
            <input type="text" id="hostId" name="hostId" style="display: none" value="<%=host.getHostId()%>" >
        </div>


        <div class="tab_content_list">
            <span class="tab_content_listtext" style="width: 100px;">名称：</span>
            <input type="text" class="tab_content_listinput"  value="<%=host.getHostName()%>" id="hostName" name="hostName" />
        </div>
        <div class="tab_content_list">
            <span class="tab_content_listtext" style="width: 100px;">IP：</span>
            <input type="text" class="tab_content_listinput" readonly="true" value="<%=host.getHostIpaddr()%>" id="hostIpaddr" name="hostIpaddr" />
        </div>
        <div class="tab_content_list">
            <span class="tab_content_listtext" style="width: 100px;">描述：</span>
            <input type="text" class="tab_content_listinput" value="<%=host.getHostDesc()%>" id="hostDesc" name="hostDesc" />
        </div>
        <div class="tab_content_list">
            <span class="tab_content_listtext" style="width: 100px;">用户名：</span>
            <input type="text" class="tab_content_listinput" value="<%=host.getHostUserName()%>" id="hostUserName" name="hostUserName" />
        </div>
        <div class="tab_content_list">
            <span class="tab_content_listtext" style="width: 100px;">密码：</span>
            <input type="text" class="tab_content_listinput"  value="<%=host.getHostPassWord()%>" id="hostPassWord" name="hostPassWord" />
        </div>
    </div>
    <div class="win_btn" style="margin: 20px 20px 20px 0;">
        <a href="javascript:void(0)"><div class="win_btn_sure" style="margin-right: 20px;" id="confirm">确定</div></a>
        <a href="javascript:void(0)"><div class="win_btn_done" id="cancel">取消</div></a>
    </div>
        <input type="text" style="display: none" urlhead="${pageContext.request.contextPath}" id="paramhidden">

</div>

<script>

    $("#confirm").click(function(){
        var hostId=$("#hostId").val();
        var hostName = $("#hostName").val();
        var hostIpaddr = $("#hostIpaddr").val();
        var hostDesc = $("#hostDesc").val();
        var hostUserName = $("#hostUserName").val();
        var hostPassWord = $("#hostPassWord").val();
       // var hostDspecId=$("#select0").find("option:selected").val();//$("dspecId").options($("dspecId").selectedIndex).value;
        //alert(hostDspecId);
        var hostDspecId=<%=dspecId%>;
        var url =$("#paramhidden").attr("urlhead")+"/host/Host_updateHost.do";
        $.post(url,{'hostId':hostId,'hostName':hostName,'hostIpaddr':hostIpaddr,'hostDesc':hostDesc,'hostUserName':hostUserName,'hostPassWord':hostPassWord,'dspecId':hostDspecId},function(data){
            layer.msg(data.msg);
            if(data.success==true)
            {
                window.parent.location.reload();
            }
        },'json');
    });
    $("#cancel").click(function(){
        window.parent.location.reload();
    });
</script>