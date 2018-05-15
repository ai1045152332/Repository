<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<style>
	.searchbox {
		width: 212px;
	}
.searchbox .searchbox-menu{
	padding: 0;
	margin: 0;
	float: left;
}
.searchbox a.l-btn-plain{
	border-radius: 0;
	height: 23px;
}
.searchbox .searchbox-prompt{
	width: 97px;
}
.searchbox .searchbox-button{
	position: absolute;
	right: 15px;
}
</style>
<div id="move" class='classtable_cancel' title='移动' onclick='moveToFolder()'>
	<img src="${pageContext.request.contextPath}/image/frontend/icon/resource-move.png" style="width:20px"/><span class="content">移动</span>
</div>
<div id="rename" class='classtable_cancel' title='重命名' onclick='rename()'>
	<img src="${pageContext.request.contextPath}/image/frontend/icon/write.png"/><span class="content">重命名</span>
</div>
<div id="delete" class='classtable_cancel' title='删除' onclick='pubDels()'>
	<img src="${pageContext.request.contextPath}/image/frontend/icon/delete.png"/><span class="content">删除</span>
</div>
<div id="download" class='classtable_cancel' title='下载' onclick='downloadItems()'>
	<img src="${pageContext.request.contextPath}/image/frontend/icon/download.png" style="width: 20px;"/><span class="content">下载</span>
</div>
<div id="newfloder" class='classtable_cancel' title='新建文件夹' onclick='create()'>
	<img src="${pageContext.request.contextPath}/image/frontend/icon/rersource-newfolder.png" style="width:20px"/><span class="content">新建文件夹</span>
</div>
<div id="upload" class='classtable_cancel' title='上传' onclick='upload()'>
	<img src="${pageContext.request.contextPath}/image/frontend/icon/resource-upload.png" style="width:20px"/><span class="content">上传</span>
</div>
<div id="pass" class='classtable_cancel' title='通过审核' style="display: none" onclick='pass()'>
	<img src="${pageContext.request.contextPath}/image/frontend/icon/resource-pass.png" style="width:20px"/><span class="content">通过审核</span>
</div>
<div id="noPass" class='classtable_cancel' title='未通过审核' style="display: none" onclick='noPass()'>
	<img src="${pageContext.request.contextPath}/image/frontend/icon/resource-noPass.png" style="width:20px"/><span class="content">未通过审核</span>
</div>
<%
	String prid = request.getParameter("prid");
	if(prid!=null && !prid.equals("")){
%>
<div id="back" class="classtable_cancel" title="返回编辑消息" style="margin-left:120px;color: #28b779;">
	<a href="${pageContext.request.contextPath}/pages/frontend/news/editor/index.jsp?prid=<%=prid%>&w=1920&h=1080">
		<img src="${pageContext.request.contextPath}/image/frontend/icon/back.png"/><span class="content">返回编辑消息</span>
	</a>
</div>
<%}%>
<input id="ss" class="easyui-searchbox" style="display: none" data-options="prompt:&#39;请输入资源名称&#39;,menu:&#39;#mm&#39;,searcher:doSearch"
>
<DIV id="mm" style="width: 60px;background:white;z-index:100;display: none" >
          <DIV data-options="name:'all' ,iconCls:'icon-ok'">全部</DIV>
          <DIV data-options="name:'video'">视频</DIV>
          <DIV data-options="name:'word'">文档</DIV>
          <DIV data-options="name:'excel'">Excel</DIV>
          <DIV data-options="name:'img'">图片</DIV>
          <DIV data-options="name:'pdf'">PDF</DIV>
          <DIV data-options="name:'ppt'">PPT</DIV>
          <DIV data-options="name:'flash'">动画</DIV>
    </DIV>
    <script>
    	var timer=setInterval(function(){
		if($(".searchbox").height()!=null){
			clearInterval(timer)
			var tollheight=$(".master-content-tool").height()
			var searchheihgt=$(".searchbox").height()
			$(".searchbox").css("margin-top",(tollheight-searchheihgt)/2+"px")
		}
	},1000)
    	window.onresize=function(){

    		   	var timer=setInterval(function(){
		if($(".searchbox").height()!=null){
			clearInterval(timer)
			var tollheight=$(".master-content-tool").height()
			var searchheihgt=$(".searchbox").height()
			$(".searchbox").css("margin-top",(tollheight-searchheihgt)/2+"px")
		}
	},1000)
    	}


    </script>