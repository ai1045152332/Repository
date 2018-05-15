<%@ page import="com.honghe.recordhibernate.entity.CommandCode" %>
<%@ page import ="com.honghe.recordhibernate.entity.Dspecification"%>
<%@ page import ="java.util.List"%>
<%@ page import="java.util.Map" %>
<%--
  Created by IntelliJ IDEA.
  User: sky
  Date: 2015/8/24
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://"
          + request.getServerName() + ":" + request.getServerPort()
          + path + "/";
%>
<link href="${pageContext.request.contextPath}/css/project/main.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/js/common/layer/skin/layer.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/js/common/layer/skin/layer.ext.css" rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/project/public.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
<script src="${pageContext.request.contextPath}/js/project/checkbox-user.js"></script>
<html>
<head>
    <title></title>
</head>
<body>
<div>
  <input type="hidden" value="${pageContext.request.contextPath}" id="root_path">
  <%
    int codeId=Integer.parseInt(request.getAttribute("codeId").toString());
    CommandCode commandCode=(CommandCode)request.getAttribute("codeinfo");
    if (commandCode==null)
    {
  %>
     <div>
       <div class="tab_content_list" style="margin-top: 25px;">
         <div class="tab_content_listtext">设备名称: </div>
         <select name="dspecName" id="dspec" class="tab_content_listinput">
           <%
             Map<String,Object> dpecnamemaps=(Map<String,Object>)request.getAttribute("dpecNameMap");
             List<Object[]> dpecnamemap=(List<Object[]>)dpecnamemaps.get("codeadd");
             for (int i=0;i<dpecnamemap.size();i++){
           %>
           <option value="<%=dpecnamemap.get(i)[1]%>"><%=dpecnamemap.get(i)[0]%></option>
           <%}%>
         </select>
       </div>
       <div class="tab_content_list" >
         <div class="tab_content_listtext">命令所属组: </div>
         <select name = "codeGroup" id="group" class="tab_content_listinput">
           <option value="Channel">信号源切换</option>
           <option value="Audio">音响模式调节</option>
           <option value="Energy">远程节能</option>
           <option value="Touch">触摸功能</option>
           <option value="power">电源管理</option>
           <option value="PTZ">PTZ控制</option>
           <option value="LAN">镜头控制</option>
           <option value="live">直播管理</option>
           <option value="volume">音量调节</option>
           <option  value="turn">投影仪开关</option>
         </select>
       </div>
       <div class="tab_content_list" >
         <div class="tab_content_listtext">命令名称: </div>
         <select name="cmdName" id="name" class="tab_content_listinput">
           <%
             Map<String,Object> cmdnamemaps=(Map<String,Object>)request.getAttribute("cmdNameMap");
             List<Object[]> cmdnamemap=(List<Object[]>)cmdnamemaps.get("codeadd");
             for(int i=0;i<cmdnamemap.size();i++){
           %>
           <option  value="<%=cmdnamemap.get(i)[1]%>"><%=cmdnamemap.get(i)[0]%></option>
           <%}%>
         </select>
         </div>
       <div class="tab_content_list" >
         <div class="tab_content_listtext">命令编码: </div>
         <input type="text" maxlength="22" name="codeName" class="tab_content_listinput"
                onkeyup="this.value=this.value.replace(/[^a-zA-Z0-9]*/g,'')"/>
       </div>
       <div class="tab_content_list">
         <div class="tab_content_listtext">通信接口解释标准码:</div>
         <input class="text" maxlength="20" name="codeCode" class="tab_content_listinput"/>&nbsp;
       </div>
       <div class="tab_content_list">
         <div class="tab_content_listtext">命令返回数值:</div>
         <input class="text" maxlength="2" name="codeFlag" class="tab_content_listinput" onkeyup="this.value=this.value.replace(/\D/g,'')" />&nbsp;
       </div>
       <div class="tab_content_list">
         <div class="tab_content_listtext">命令编码备注: </div>
         <input class="text" maxlength="20" name="codeRemark" class="tab_content_listinput"/>&nbsp;
       </div>
       <div class="tab_content_list">
         <div class="tab_content_listtext">命令编码说明: </div>
         <input class="text" maxlength="20" name="codeInstruction" class="tab_content_listinput"/>&nbsp;
       </div>
       <div class="win_btn" style="margin: 20px 20px 20px 0;" onclick="javascript:addCodeSave()">
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
      <div class="tab_content_listtext">设备名称: </div>
      <select name="dspecName" id="dspec1" class="tab_content_listinput">
        <%
          Map<String,Object> dpecnamemaps=(Map<String,Object>)request.getAttribute("dpecNameMap");
          List<Object[]> dpecnamemap=(List<Object[]>)dpecnamemaps.get("codeadd");
          for (int i=0;i<dpecnamemap.size();i++){
               if (dpecnamemap.get(i)[1].equals(commandCode.getDspecification().getDspecId())){
        %>
        <option id="<%=dpecnamemap.get(i)[1]%>" selected="selected"><%=dpecnamemap.get(i)[0]%></option>
        <%}%>
        <option id="<%=dpecnamemap.get(i)[1]%>"><%=dpecnamemap.get(i)[0]%></option>
        <%}%>
      </select>
    </div>
    <div class="tab_content_list" >
      <div class="tab_content_listtext" id="cmdg" value="<%=commandCode.getCodeGroup()%>">命令所属组: </div>
      <input type=hidden id='codegroup' value="${codeinfo.getCodeGroup()}"/>
      <select name = "codeGroup" id="group1"  class="tab_content_listinput">
        <option id="Channel" value="Channel" >信号源切换</option>
        <option id="Audio" value="Audio">音响模式调节</option>
        <option id="Energy" value="Energy">远程节能</option>
        <option id="Touch" value="Touch">触摸功能</option>
        <option id="power" value="power">电源管理</option>
        <option id="PTZ" value="PTZ">PTZ控制</option>
        <option id="LAN" value="LAN">镜头控制</option>
        <option id="live" value="live">直播管理</option>
        <option id="volume" value="volume">音量调节</option>
        <option id="Turn" value="Turn">投影仪开关</option>
      </select>
    </div>
    <div class="tab_content_list" >
      <div class="tab_content_listtext">命令名称: </div>
      <select name="cmdName" id="name1" class="tab_content_listinput">
        <%
          Map<String,Object> cmdnamemaps=(Map<String,Object>)request.getAttribute("cmdNameMap");
          List<Object[]> cmdnamemap=(List<Object[]>)cmdnamemaps.get("codeadd");
          for(int i=0;i<cmdnamemap.size();i++){
            if (cmdnamemap.get(i)[1].equals(commandCode.getCodeType())){
        %>
        <option id="<%=cmdnamemap.get(i)[1]%>" selected="selected"><%=cmdnamemap.get(i)[0]%></option>
        <%}%>
        <option  value="<%=cmdnamemap.get(i)[1]%>"><%=cmdnamemap.get(i)[0]%></option>
        <%}%>
      </select>
    </div>
    <div class="tab_content_list" >
      <div class="tab_content_listtext">命令编码: </div>
      <input input type="text" maxlength="22" name="codeName" class="tab_content_listinput"  value="<%=commandCode.getCodeName()%>"/>
      <input type="hidden"  name="codeId" value="<%=commandCode.getCodeId()%>"
             onkeyup="this.value=this.value.replace(/[^a-zA-Z0-9]*/g,'')"/>
    </div>
    <div class="tab_content_list">
      <div class="tab_content_listtext">通信接口解释标准码: </div>
      <input class="text" maxlength="20" name="codeCode" class="tab_content_listinput" value="<%=commandCode.getCodeCode()%>"/>
    </div>
    <div class="tab_content_list">
      <div class="tab_content_listtext">命令返回数值: </div>
      <input class="text" maxlength="2" name="codeFlag" class="tab_content_listinput" value="<%=commandCode.getCodeFlag()%>"
             onkeyup="this.value=this.value.replace(/\D/g,'')"/>
    </div>
    <div class="tab_content_list">
      <div class="tab_content_listtext">命令编码备注: </div>
      <input class="text" maxlength="20" name="codeRemark" class="tab_content_listinput" value="<%=commandCode.getCodeRemark()%>"/>
    </div>
    <div class="tab_content_list">
      <div class="tab_content_listtext">命令编码说明: </div>
      <input class="text" maxlength="20" name="codeInstruction" class="tab_content_listinput" value="<%=commandCode.getCodeInstruction()%>"/>
    </div>
    <div class="win_btn" style="margin: 20px 20px 20px 0;" onclick="javascript:altercodeSave()">
      <div class="win_btn_sure" style="margin-right: 180px;">确定</div>
    </div>
  </div>
   <%
     }
   %>
</div>
   </body>
    </html>
<script type="text/javascript">
  var root_path = $("#root_path").val();
  //点击添加按钮将数据加入数据库
  function addCodeSave()
  {
    var codeName= $.trim($("input[name='codeName']").val());//获取输入的codeName的值并去掉前后空格
//    if(codeName.replace(/(^\s*)|(\s*$)/g,"")=="")
//    {
//      layer.alert("命令编码不能为空");
//      $("input[name='codeName']").val("");
//      return false;
//    }
//    if(codeName.length!=22){
//      layer.alert("命令编码必须是22位数字和字母的组合");
//      $("input[name='codeName']").val("");
//      return false;
//    }
    var dspecId=document.getElementById('dspec').value;
    var dspecName=  $('#dspec option:selected').text();
//    var dspecId = $("#dspec option:selected").value;
    var codeRemark= $.trim($("input[name='codeRemark']").val());
//    if(codeRemark.replace(/(^\s*)|(\s*$)/g,"")=="")
//    {
//      layer.alert("命令编码备注不能为空");
//      $("input[name='codeRemark']").val("");
//      return false;
//    }
    var codeType = document.getElementById('name').value;
    var codeInstruction = $("input[name='codeInstruction']").val();
    var codeFlag = $("input[name='codeFlag']").val();
    if(codeFlag)
    var codeCode = $("input[name='codeCode']").val();
    var codeGroup = document.getElementById('group').value;

//    alert(dspecId);
    $.post('./frontend/devicecode/Devicecode_add.do',{codeName:codeName,codeType:codeType,codeRemark:codeRemark,codeInstruction:codeInstruction,codeCode:codeCode, codeFlag:codeFlag,codeGroup:codeGroup,dspecName:dspecName,dspecId:dspecId},function(data){

      if(data.success==true)
      {

        $("input[name='codeName']").val();
        dspecName = document.getElementById('dspec').value;
         codeType = document.getElementById('name').value;
        $("input[name='codeRemark']").val();
        $("input[name='codeInstruction']").val();
        $("input[name='codeFlag']").val();
        $("input[name='codeCode']").val();

        var codeGroup = document.getElementById('group').value;

        layer.msg(data.msg,1,function(){
          $.get('./frontend/devicecode/Devicecode_codeList.do',function(data){
            parent.$("#public_right_center").html(data);
            parent.layer.closeAll();
          },'html')
        });
      }

      else
      {
        layer.msg(data.msg);
      }
    },'json')

  }
  $(function(){
    var cgroupname= document.getElementById('codegroup').value;
    //cgroupname = "#"+cgroupname;
    $("#"+cgroupname+"").attr("selected", "selected");
  });
     //修改用户，保存进数据库(点击‘修改’按钮)
  function altercodeSave()
  {
   var cgroupname= document.getElementById('codegroup').value;
//    alert(cgroupname);
    $("#codegroup").select();
    var codeId=$("input[name='codeId']").val();
    var codeName= $.trim($("input[name='codeName']").val());
//    if(codeName.replace(/(^\s*)|(\s*$)/g,"")=="")
//    {
//      layer.alert("命令编码不能为空");
//      $("input[name='codeName']").val("");
//      return false;
//    }
//    if(codeName.length!=22){
//      layer.alert("命令编码必须是22位数字和字母的组合");
//      $("input[name='codeName']").val("");
//      return false;
//    }
      var dspecName =  $('#dspec1 option:selected').text();
      var dspecId = $("#dspec1 option:selected").attr("id");
    var codeRemark= $.trim($("input[name='codeRemark']").val());
    var codeType = document.getElementById('name1').value;
    var codeInstruction = $("input[name='codeInstruction']").val();
    var codeCode = $("input[name='codeCode']").val();
    var codeFlag = $("input[name='codeFlag']").val();
    var codeGroup = document.getElementById('group1').value;
    $.post('./Devicecode_edit.do',{codeId:codeId,codeName:codeName,codeType:codeType,codeRemark:codeRemark,codeInstruction:codeInstruction,
     codeCode:codeCode, codeFlag:codeFlag,codeGroup:codeGroup,dspecName:dspecName,dspecId:dspecId},function (data) {
      if(data.success==true)
      {
        layer.msg(data.msg,1,function(){
          $.get('./Devicecode_codeList.do',function(data){
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

