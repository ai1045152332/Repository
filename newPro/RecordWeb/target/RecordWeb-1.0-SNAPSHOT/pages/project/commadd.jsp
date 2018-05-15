<%@ page import="com.honghe.recordhibernate.entity.Command" %>
<%--
  Created by IntelliJ IDEA.
  User: lch
  Date: 2014/11/4
  Time: 8:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="${pageContext.request.contextPath}/css/project/main.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/js/common/layer/skin/layer.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/js/common/layer/skin/layer.ext.css" rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/project/public.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
<script src="${pageContext.request.contextPath}/js/project/checkbox-user.js"></script>
<style>
    .all,.all1{max-width: 200px;width: auto;float: left;}
    .head1,.head{margin-top: 5px;float:left}
    body{
        overflow: hidden;
        top: 0;
        position: relative;
    }
    .foot{margin-top:0}
</style>
<script>
    /*$(function(){
        var win520height=$(".win520_content").height();
        var win520width=$(".win520_content").width();
        //alert($(".win520_content").height())
        parent.$(".xubox_main").find(".xubox_iframe").css({"width":win520width+"px","height":win520height+"px"});
        parent.$(".xubox_main").css({"width":win520width+"px","margin-left":"-120px","margin-top":-win520height/2+"px"});
    })*/
</script>
<body>
<div>
<input type="hidden" value="${pageContext.request.contextPath}" id="root_path">
<%
    int commId = Integer.parseInt(request.getAttribute("cmdId").toString()) ;
    Command command = (Command)request.getAttribute("comminfo");
    //out.println(userinfo);
    if(command==null)
    {
%>
<div>
    <div class="tab_content_list" style="margin-top: 25px;">
        <span class="tab_content_listtext">命令名称：</span>
        <input type="text" maxlength="20" name="cmdName" class="tab_content_listinput" />
    </div>
    <div class="tab_content_list" >
        <span class="tab_content_listtext">命令分组：</span>
        <input type="text" maxlength="20" name="cmdGroup" class="tab_content_listinput" />&nbsp;
    </div>
    <div class="tab_content_list" >
        <span class="tab_content_listtext">命令缺省：</span>
        <select name = "cmdDefault" class="tab_content_listinput">
            <option value="0">否</option>
            <option value="1">是</option>
        </select>
        <%--<input type="text" name="cmdDefault" class="tab_content_listinput" />--%>
    </div>
    <div class="tab_content_list" >
        <span class="tab_content_listtext">命令标识：</span>
        <select name = "cmdFlag" class="tab_content_listinput">
            <option value="01">01</option>
            <option value="10">10</option>
            <option value="11">11</option>
        </select>
        <%--<input type="text" name="cmdFlag" class="tab_content_listinput" />--%>
    </div>
    <div class="tab_content_list" >
        <span class="tab_content_listtext">命令图标：</span>
        <input type="text" maxlength="50" name="cmdImage" class="tab_content_listinput" />
    </div>
    <div class="tab_content_list" >
        <span class="tab_content_listtext">命令代号：</span>
        <input type="text" maxlength="20" name="cmdHex" class="tab_content_listinput" />
    </div>

    <div class="win_btn" style="margin: 20px 20px 20px 0;" onclick="javascript:addCmdSave()">
        <div class="win_btn_sure" style="margin-right: 180px;">确定</div>
    </div>
</div>
<%
}
else
{
%>
<div>
    <div class="tab_content_list" style="margin-top: 25px;">
        <span class="tab_content_listtext">命令名称：</span>
        <input type="text" maxlength="20" name="cmdName" class="tab_content_listinput" disabled="disabled" value="<%=command.getCmdName()%>"/>
        <input type="hidden"  name="cmdId" value="<%=command.getCmdId()%>"/>
    </div>
    <div class="tab_content_list" >
        <span class="tab_content_listtext">命令分组：</span>
        <input type="text" maxlength="20" name="cmdGroup" class="tab_content_listinput" value="<%=command.getCmdGroup()%>"/>
    </div>
    <div class="tab_content_list" >
        <span class="tab_content_listtext">命令缺省：</span>
        <select name = "cmdDefault" class="tab_content_listinput">
            <option value="0" <%if(command.getCmdDefault().equals("0")) out.print(" selected=\"selected\"");%>>否</option>
            <option value="1" <%if(command.getCmdDefault().equals("1")) out.print(" selected=\"selected\"");%>>是</option>
        </select>
        <%--<input type="text" name="cmdDefault" class="tab_content_listinput" value="<%=command.getCmdDefault()%>" />--%>
    </div>
    <div class="tab_content_list" >
        <span class="tab_content_listtext">命令标识：</span>
        <select name = "cmdFlag" class="tab_content_listinput">
            <option value="01" <%if(command.getCmdFlag().equals("01")) out.print(" selected=\"selected\"");%>>01</option>
            <option value="10" <%if(command.getCmdFlag().equals("10")) out.print(" selected=\"selected\"");%>>10</option>
            <option value="11" <%if(command.getCmdFlag().equals("11")) out.print(" selected=\"selected\"");%>>11</option>
        </select>
        <%--<input type="text" name="cmdFlag" class="tab_content_listinput" value="<%=command.getCmdFlag()%>" />--%>
    </div>
    <div class="tab_content_list" >
        <span class="tab_content_listtext">命令图标：</span>
        <input type="text" maxlength="50" name="cmdImage" class="tab_content_listinput" value="<%=command.getCmdImage()%>" />
    </div>
    <div class="tab_content_list" >
        <span class="tab_content_listtext">命令代号：</span>
        <input type="text" maxlength="20" disabled="disabled" name="cmdHex" class="tab_content_listinput" value="<%=command.getCmdHex()%>" />
    </div>
    <div class="win_btn" style="margin: 20px 20px 20px 0;" onclick="javascript:alterCmdSave()">
        <div class="win_btn_sure" style="margin-right: 180px;">确定</div>
    </div>
</div>
<%
    }
%>
</div>
</body>
<script type="text/javascript">
    var rootpath = $("#root_path").val();
    //添加用户，保存进数据库(点击‘添加’按钮)
    function addCmdSave()
    {
        var cmdName = $.trim($("input[name='cmdName']").val());
        var flag = $("select[name='cmdFlag']").val();
        var cmd_default = $("select[name='cmdDefault']").val();
        //alert(flag + "----------" +cmd_default );
        //var aa = cmdName.replace(/(^\s*)|(\s*$)/g,'');
        //alert(aa);
        if(cmdName.replace(/(^\s*)|(\s*$)/g, "") =="")
        {
            layer.alert("命令名称不能为空");
            $("input[name='cmdName']").val("");
            return false;
        }
        var group = $("input[name='cmdGroup']").val();
        if(group.replace(/(^\s*)|(\s*$)/g, "")=="")
        {
            layer.alert("分组不能为空");
            $("input[name='cmdGroup']").val("");
            return false;
        }
        var hex = $("input[name='cmdHex']").val();
        if(hex.replace(/(^\s*)|(\s*$)/g, "") == "")
        {
            layer.alert("命令代号不能为空");
            $("input[name='cmdHex']").val("");
        }

        var cmd_image = $("input[name='cmdImage']").val();

        $.post(rootpath+"/project/Devicecomm_add.do", {cmdName: cmdName,cmdGroup:group,cmdHex:hex,cmdFlag:flag,cmdDefault:cmd_default,cmdImage:cmd_image}, function (data) {
            if(data.success==true)
            {
                $("input[name='cmdName']").val("");
                $("input[name='cmdGroup']").val("");
                $("input[name='cmdHex']").val("");
                /*$("input[name='cmdFlag']").val("");*/
                $("input[name='cmdImage']").val("");
                layer.msg(data.msg,1,function(){
                    $.get(rootpath+"/project/Devicecomm_commList.do",function(data){
                        parent.$("#public_right_center").html(data);
                        parent.layer.closeAll();
                    },'html')
                });
            }
            else
            {
                layer.msg(data.msg);
            }
        },'json');
    }
    //修改用户，保存进数据库(点击‘修改’按钮)
    function alterCmdSave()
    {
        var cmdId = $("input[name='cmdId']").val();
        var cmdName = $.trim($("input[name='cmdName']").val());
        var group = $("input[name='cmdGroup']").val();
        if(group.replace(/(^\s*)|(\s*$)/g, "")=="")
        {
            layer.alert("分组不能为空");
            $("input[name='cmdGroup']").val("");
            return false;
        }
        var hex = $("input[name='cmdHex']").val();
        var flag = $("select[name='cmdFlag']").val();
        var cmd_default = $("select[name='cmdDefault']").val();
        //alert(flag+"=====");return;
        var cmd_image = $("input[name='cmdImage']").val();
        $.post(rootpath+"/project/Devicecomm_edit.do", { cmdName: cmdName,cmdGroup:group,cmdHex:hex,cmdFlag:flag,cmdDefault:cmd_default,cmdImage:cmd_image,cmdId:cmdId}, function (data) {
            if(data.success==true)
            {
                layer.msg(data.msg,1,function(){
                    $.get(rootpath+"/project/Devicecomm_commList.do",function(data){
                        parent.$("#public_right_center").html(data);
                        parent.layer.closeAll();
                    },'html')
                });
            }
            else
            {
                layer.msg(data.msg);
            }
        },'json');
    }
</script>
