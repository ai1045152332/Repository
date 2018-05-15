<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="resourceList" style="height:100%;">
	<table id="dg"></table>
</div>
<div id="emptyr" style="display:none">
		<center>
		<br/><br/><br/><br/><br/><br/><br/><br/><br/>
		<img src="${pageContext.request.contextPath }/image/frontend/user/empty.png"/>
		<br/>
		<h1 style="color: rgb(141,141,141);font-size: 20px;font-weight: normal;">抱歉,没有找到符合条件的结果!</h1>
		</center>
</div>
<!-- 	<div id="resourcesList" style="display:none;height:100%;"><table id="dg"></table></div> -->
<div id="dd2" class="easyui-dialog" data-options="closed:true,modal:true,title:'上传'" style="width:500px;height:200px; " align="center">
	<s:fielderror />
	<s:form action="/tresource/ResourceUpload_doUpload.do" id="ff" method="POST" enctype="multipart/form-data" onsubmit="return false">
		<s:file name="upload" id="upload" label="上传的文件" />
		<s:textfield name="fileCaption" label="上传的路径"
			class="easyui-validatebox" data-options="required:true" />
		名字:<input class="easyui-validatebox" type="text" name="name"
			data-options="required:true" />
		<s:submit value="开始上传"/>
		onsubmit="checkType();
	</s:form >
	<form action="/resource/ResourceUpload_doUpload.do" id="ff" method="POST" enctype="multipart/form-data">
		选择文件 <input type="file" name="upload" id="upload" /> <input type="text" name="fileCaption" />路径 <input type="text" name="name" />文件名

		<input type="button" value="上传" onclick="checkType();">
	</form>
</div>

<div id="dd4" class="easyui-dialog"
	data-options="closed:true,modal:true,title:'重命名',	buttons:[{
				text:'取消',
				handler:cancelUodateName
			},{
				text:'提交',
				handler:submitUpdate

			}]"
	style="width:500px;height:200px;" align="center">

	<!-- <input id="ids" name="ids"	 type="hidden" /> -->
	<div class="cmm" style="">名字:<input class="easyui-validatebox" type="text" name="UpdateName"	id="UpdateName" data-options="required:true" /></div>

</div>
<div id="hiddenAjaxBoolean" data-options="closed:true">
	<input type="hidden" id="booleanForDelete" /> <input type="hidden"
		id="uploadFlag"/>
</div>
<div id="dd3" class="easyui-dialog"
	data-options="closed:true,modal:true,title:'新建文件夹',	buttons:[{
				text:'取消',
				handler:cancelNewFloder
			},{
				text:'确定',
				handler:check3
			}]"
	style="width:500px;height:200px;" align="center">
	<input type="hidden" name="path"
		value=" <%=request.getAttribute("virtualPreView")%>" /> <input
		type="hidden" id="createPathId" name="createPathId"
		value="${currentPathId}" /><span style="margin-left: 100px;">文件夹名字: </span><input class="easyui-validatebox"
		type="text" name="floderName" id="floderName" style="width:200px;"/>
</div>


<div id="dMoveToFolder" class="easyui-dialog" style="width:500px;height:400px;" align="center"
	data-options="closed:true,modal:true,title:'选择目录',	buttons:[{
				text:'取消',
				handler:cancelPathSelect2
			},{
				text:'确定',
				handler:check2

			}]"
	>
	<div style="height:20px" data-options="closed:true">
		<input type="hidden" id="ajaxBoolean"> <input type="hidden"
			id="tempMovePathId" value="" /> <input type="hidden" id="divFlag"
			value="all" /> <input type="hidden" id="downloadName" value="" /> <input
			type="hidden" id="previewEditId" value="" /> <input type="hidden"
			id="previewEditType" value="" /> <input type="hidden"
			id="previewEditName" value="" /> <input type="hidden"
			id="previewEditNum" value="" /> <input type="hidden"
			id="delsIfVerified" value="0" />
			 <input type="hidden"
			id="previewPath" value="" />
	</div>
	<div>
		<ul id="treeDemo2" class="ztree"></ul>
	</div>
</div>

<input type="hidden" id = "aryo" value=''>
<div id="ddSelect" class="easyui-dialog" style="width: 550px; height: 420px;" align="center" closed="true" modal="true"
	data-options="title:'选择上传文件',modal:true,&#10;&#9;&#9;&#9;closable: false,
            buttons:[{&#10;&#9;&#9;&#9;&#9;text:'取消',&#10;&#9;&#9;&#9;&#9;handler:noticeclose2&#10;&#9;&#9;&#9;},
            {&#10;&#9;&#9;&#9;&#9;text:'下一步',&#10;&#9;&#9;&#9;&#9;handler:nextStep2&#10;&#9;&#9;&#9;}]">
		<input type="hidden" id="pathIdForSubmit" value="" />
		<input type="file" name="file_upload" id="file_upload" />
		<div  id = "p3" style='display: none'></div>
</div><!-- {&#10;&#9;&#9;&#9;&#9;text:'上一步',&#10;&#9;&#9;&#9;&#9;handler:prevStep1&#10;&#9;&#9;&#9;}, -->
<DIV class="easyui-dialog" id="dd5" style="width: 550px; height: 420px;clear:both;"  modal="true" closed="true"
            data-options="title:'选择上传目录',modal:true,&#10;&#9;&#9;&#9;closable: false,
            buttons:[{&#10;&#9;&#9;&#9;&#9;text:'取消',&#10;&#9;&#9;&#9;&#9;handler:noticeclose1&#10;&#9;&#9;&#9;},
            {&#10;&#9;&#9;&#9;&#9;text:'下一步',&#10;&#9;&#9;&#9;&#9;handler:nextStep1&#10;&#9;&#9;&#9;}
            ]">
	<input type="hidden" id="pathId" value="" />
	<div class="select-content-device">
		<div style="clear:both"></div>
        <div class="select-note">上传到目录</div>
		<div>
			<ul id="treeDemo" class="ztree"></ul>
		</div>
	</div>
	<div  id = "p2" style='display:none;'></div>
</div>
<!-- {&#10;&#9;&#9;&#9;&#9;text:'全部取消',&#10;&#9;&#9;&#9;&#9;handler:saveOrUpdate&#10;&#9;&#9;&#9;}, -->
<div id="playproject3" class="easyui-dialog" style="width:712px;height:449px;" align="center" closed="true" modal="true"
	data-options="modal:true,title:'正在上传',closable: false,
	buttons:[{&#10;&#9;&#9;&#9;&#9;text:'关闭',&#10;&#9;&#9;&#9;&#9;handler:noticeclose3&#10;&#9;&#9;&#9;}]">
	<div style='position:relative; border:1px #CCC solid;  height:30px; width:690px; margin:0 auto; padding:1px'>
		<div id="fortest" style=' background:#3F9; height:30px; width:0px'></div>
		<strong style='position:absolute; width:690px; top:7px; overflow:hidden'>
			<div style='height:48px;width:340px;float:left;font-weight:normal'>文件名称</div>
			<div style='height:48px;width:105px;float:left;font-weight:normal'>大小</div>
			<div style='height:48px;width:115px;float:left;font-weight:normal;'></div>
			<div style='height:48px;width:50px;float:left;font-weight:normal'>进度</div>
			<div style='height:48px;width:80px;float:left;font-weight:normal'>上传状态</div>
		</strong>
		<div id = 'p4'></div>
	</div>
</div>

<!-- 资源预览框  -->
<div id="previewId" class="overlay" style="display:none">
	<div class="edit-resources-div">
		<div class="edit-resources-title">
			<img onclick="previewClose();"
				src="${pageContext.request.contextPath}/image/frontend/resources/resource-close.png"/>
		</div>
		<div id="previewContent" class="edit-resources-content">
		</div>
		<div class="edit-resources-foot">
			<!--下载删除按钮，因为ie不支持，所以屏蔽-->
			<%--<div onclick="previewDownload();" style="background:url('${pageContext.request.contextPath}/image/frontend/resources/resource-download.png')"></div>--%>
			<%--<div onclick="previewDelete();" style="background:url('${pageContext.request.contextPath}/image/frontend/resources/resource-delete.png')"></div>--%>
		</div>
	</div>
</div>
<script>
	function opendd4(){
	    clearProgressBar();
		opendd5();
	}
	var uploadNum = {success:0,error:0};
	function closedd4(sucess,error){
		var su,er;
		if (!!window.ActiveXObject || "ActiveXObject" in window){
			su = sucess - uploadNum.success;
			er = error - uploadNum.error;
			uploadNum.success = sucess;
			uploadNum.error = error;
		}else{
			su = sucess;
			er = error;
		}
		 $("#uploadFlag").val("0");
		 $("#ddSelect").dialog('close');
		 if(error>0){
		 	 $.messager.alert('提示',su+'个文件上传成功!&nbsp;&nbsp;&nbsp;&nbsp;'+er+'个文件上传失败!', 'info', function () {
				 var reg=$("#newfloder").attr("style").trim().indexOf("display: block;");
				 if(reg>0){
					bb2();
				 }
         	 });
		 }else if(error == 0){
			 $.messager.alert('提示',su+'个文件上传成功!&nbsp;&nbsp;&nbsp;&nbsp;'+er+'个文件上传失败!', 'info', function () {
	            $("#p4").html('');
	           	$("#playproject3").dialog('close');
			    var reg=$("#newfloder").attr("style").trim().indexOf("display: block;");
				 if(reg>0){
					 bb2();
				 }
	         });
		 }
	}
	function opendd5(){

		var treeObj = $("#treeDemo");
		$.fn.zTree.init(treeObj, setting);

		$("#file_upload-queue").html("");

		$("#dd5").dialog('open');
	}
	function closedd5(){
		$("#dd5").dialog('close');
	}
	function openprocessBar(){
	 var uploadFlag=$("#uploadFlag").val();
     if(uploadFlag==0){
   		$.messager.alert("错误","上传文件列表不能为空","error");
        return false;
     }
		$("#playproject3").dialog('open');
	}
	function clearProgressBar(){
		 $("#uploadFlag").val("0");
		 $("#p4").children(":first").children(":first").children("div.2").remove();
	}
	function checkType(){

		var name=$("#upload").val();
		$.messager.alert("",name,"info");
		if(name!=""&&name!=null){
			var types=name.split(".");
			var len=types.length-1;
			var type=types[len];
			var flag=0;
			var limitType=new Array("gif","bmp","jpg","jpeg","png","avi","wmv","mov","rm","rmvb","mpg","mpeg","mp4","vob","flv","f4v","mkv","3gp","m4v","ts","ppt","pps","swf","pdf","doc","docx","xls","xlsx");
			for(var i=0;i<limitType.length;i++){
				if(type==limitType[i]){
					flag=1;
					break;
				}else{
					flag=0;
				}
			}
			if(flag==0){
				$.messager.alert("失败","请选择格式为*.gif, *.bmp, *.jpg, *.jpeg, *.png, *.avi, *.wmv, *.mov, *.rm, *.rmvb, *.mpg, *.mpeg, *.mp4, *.vob, *.flv, *.f4v, *.mkv, *.3gp, *.m4v, *.ts, *.ppt, *.pps, *.swf, *.pdf, *.doc, *.docx, *.xls, *.xlsx的文件","info");
				return false;
			}else{
				$("#ff").submit();
			}
		}else{
			$.messager.alert("失败","请先选择文件","info");
			return false;
		}

	}
	function check(){
		var path=$("#pathId").val();
		if(path==""||path==null){

		}else{
			$("#pathIdForSubmit").val(path);
			$("#pathId").val("");
			closedd5();

		}
		 $("#ddSelect").dialog('open');
	}
	function cancelPathSelect(){
		$("#pathId").val("");
		closedd5();
	}
	function check2(){
		var path=$("#tempMovePathId").val();
		if(path==""||path==null){
			$.messager.alert("错误","未选择移动文件的目标目录","info");
			return;
		}else{
			if(path=="${currentPathId}"){
				$.messager.alert("错误","无效操作，请勿移动到文件本身所在目录","info");
				return;
			}
			var ids = [];
			var names=[];
			var rows = $('#dg').datagrid('getSelections');
			for(var i=0;i<rows.length;i++){
				ids.push(rows[i].id);
				names.push(rows[i].name);
			}
			var str=ids.join(",");
			var str2=names.join(",");
			for(var i=0;i<ids.length;i++){
				if(ids[i]==path){
					$.messager.alert("错误","包含非法操作！","info");
					return;
				}
			}
			var oneOnly=checkOneNameOnly(str2,path);
			if(false==oneOnly){
				return;
			}
			$.ajax({
				url:'${pageContext.request.contextPath }/tresource/Tresource_moveFile.do',
    			type:'post',
    			async:false,
    			data:{
    				ids:str,
    				movePath:path
    			},
    			dataType:'json',
    			success:function(result){
    			var flag=eval('('+result+')');
				if(flag){
					$("#dMoveToFolder").dialog('close');
					$.messager.alert('成功','文件移动成功！','info');
					bbupload();
				}else{
					$("#dMoveToFolder").dialog('close');
					$.messager.alert('失败','文件移动失败！','info');
					bbupload();
				}
    		}




			});
		}
	}
	function cancelPathSelect2(){
		$("#tempMovePathId").val("");
		$("#dMoveToFolder").dialog('close');
		}



	function checkOneNameOnly(str,pahtId){
	$.ajax({
				url:'${pageContext.request.contextPath }/tresource/Tresource_checkOneNameOnly.do',
    			type:'post',
    			async:false,
    			data:{
    				names:str,
    				movePath:pahtId
    			},
    			dataType:'json',
    			success:function(result){
    			var flag=eval('('+result+')');
				if(flag==true){
					$("#ajaxBoolean").val("1");
				}else{
					$("#ajaxBoolean").val("0");
					$.messager.alert('错误', '该路径下有同名文件,请更改文件名称', 'error');

				}
    		}
			});
	var flag=$("#ajaxBoolean").val();
	if(flag=="0"){
		return false;
	}else{
		return true;
	}
	}

function check3(){
	var name=$("#floderName").val();
	if(!checkCharater(name)){
		return false;
	}
	if(name.indexOf(" ")>=0){
			$.messager.alert('错误', '文件名称不可包含空格', 'error');
			return;
	}if(name.trim().length==0){
			$.messager.alert('错误', '文件名称不可为空', 'error');
			return;
	}
	if(name.trim().length>50){
		$.messager.alert('失败', '名称不能超过五十个字符!', 'error');
		return;
	}
	var path="${currentPathId}";
	var virtual="${virtualPreView}";
	if(!checkOneNameOnly(name,path)){
		return;
	}
	$.ajax({
				url:'${pageContext.request.contextPath }/tresource/Tresource_addFloder.do',
    			type:'post',
    			async:false,
    			data:{
    				floderName:name,
    				createPathId:path,
    				path:virtual
    			},
    			dataType:'json',
    			success:function(result){
    			var flag=eval('('+result+')');
				if(flag==true){
					$.messager.alert('成功', '新增文件夹成功!', 'info');
					bbupload();
					cancelNewFloder();
					disTitle();
				}else{
					$.messager.alert('错误', '本名称已经存在！', 'error');

				}
    		}
	});
}
function cancelNewFloder(){
	$("#floderName").val("");
	$("#dd3").dialog('close');
}

function checkDeletePermission (str){
	var flag="4096";
	if(str.lastIndexOf(flag)==-1){
		$.messager.alert("失败","您没有相应权限！","error");
		return true;
	}
}
function checkUploadPermission (str){
	var flag="1024";
	if(str.lastIndexOf(flag)==-1){
		$.messager.alert("失败","您没有相应权限！","error");
		return true;
	}
}
function checkEdeitPermission(str){
	var flag="8192";
	if(str.lastIndexOf(flag)==-1){
		$.messager.alert("失败","您没有相应权限！","error");
		return true;
	}
}
function checkVerifyPermission(str){
	var flag="2048";
	if(str.lastIndexOf(flag)==-1){
		$.messager.alert("失败","您没有相应权限！","error");
		return true;
	}
}
function submitUpdate(){
	var rows = $('#dg').datagrid('getSelections');
	var id=rows[0].id;

	var name=$("#UpdateName").val();
		if(!checkCharater(name)){
		return false;
	}
	if(name.indexOf(" ")>=0){
			$.messager.alert('错误', '文件名称不可包含空格', 'error');
			return;
	}
	if(name.trim()==""){
		$.messager.alert('失败', '名称不能为空!', 'error');
		return;
	}
	if(name.trim().length>50){
		$.messager.alert('失败', '名称不能超过五十个字符!', 'error');
		return;
	}
	$.ajax({
				url:'${pageContext.request.contextPath }/tresource/Tresource_rename.do',
    			type:'post',
    			async:false,
    			data:{
    				ids:id,
    				name:name
    			},
    			dataType:'json',
    			success:function(result){
    			var flag=eval('('+result+')');
				if(flag==true){
					$.messager.alert('成功', '修改名称成功!', 'info');
					bbupload();
					$("#UpdateName").val("");

					$("#dd4").dialog('close');

					$('#dg').datagrid('clearSelections');
				}else{
					$.messager.alert('错误', '同一目录下已经存在同名文件！', 'error');
					/* $("#UpdateName").val("");
					$('#dg').datagrid('clearSelections'); */
				}
    		}
	});
}
function cancelUodateName(){
	$("#dd4").dialog('close');
	$("#UpdateName").val("");
}
function nextStep1(){
	//var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
    //var nodes = treeObj.getCheckedNodes(true);
    var pathID = $("#pathId").val();
    if(pathID==''){
        	$.messager.alert('提示', '请选择一个文件夹!', 'info');
            return false;
    }
   $('#p3').html($('#p2').html());$('#p2').html("");
	$("#dd5").dialog('close');
	$("#ddSelect").dialog('open');
}
function nextStep2(){
	if($('#p3').html()==''){
		$("#uploadFlag").val("0");
		$.messager.alert("错误","上传文件列表不能为空","error");
        return false;
	}
	$('#p4').html($('#p3').html());
	$('#p3').html('');
	$("#file_upload").uploadify("upload","*");
	openprocessBar();
}
function noticeclose1(){
	//$('#file_upload').uploadify('cancel', '*');
    $("#dd5").dialog('close');
}
function noticeclose2(){
	$("#file_upload").uploadify("cancel","*",'0');
	$('#p4').html('');
	$('#p3').html('');
	$('#aryo').val('');
	$("#ddSelect").dialog('close');
}
function noticeclose3(){
    var uf = $("#uploadFlag").val();
    if(uf=="0"){
    	$("#p4").html('');
		$('#aryo').val('');
		$("#playproject3").dialog('close');
    }
	else if(uf=="1"){
		$.messager.alert("提示","文件正在上传,请稍候...","info");
	}
}
function saveOrUpdate(){
	var a=document.getElementById("p4").getElementsByTagName("a");
	for(var i=0;i<a.length;i++){
	    if(a[i].className == 'resourceUpload-cancel'){
	    	$('#file_upload').uploadify('cancel', a[i].name,'1');
	    }
	}
}
function checkCharater(name){

	var reg = /^(\w|[\u4E00-\u9FA5])*$/;
if(name.match(reg))
{
return true;
}
else
{
//$.messager.alert("提示","文件夹名只允许为英文，数字和汉字的混合,\n请检查是否前后有空格或者其他符号","info");
$.messager.alert("提示","文件名不能有空格或者特殊字符！","info");
return false;
}
}
</script>