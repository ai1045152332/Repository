var curMenu = null, zTree_Menu = null;
var urlStrOption = '';
// setting  上传文件时弹框的文件夹结构
var setting = {
		/*
		check: {
			enable: true,
			chkStyle: "radio",
			radioType: "all"
		},*/
		view: {
			showLine: true,
			showIcon: false,
			selectedMulti: false,
			dblClickExpand: false,
			addDiyDom: addDiyDom
		},
		async: {
		    contentType : "application/json",
		    enable : true,
		    dataType : "json",
		    type : "post",
		    url : "/tresource/Tresource_getResourceTree.do",
		    dataFilter: ajaxDataFilter
		    },
		data: {
			key:{
				name:"name"
			},
			simpleData: {
				enable: true,
				idKey:'id',
				pIdKey:'pid',
				rootPid:1
			}
		},
		callback: {
			beforeClick: beforeClick,
			onClick: zTreeOnClick,
			onAsyncSuccess: onAsyncSuccess,
			beforeCollapse: beforeCollapse,
			beforeExpand: beforeExpand,
			onCollapse: onCollapse,
			onExpand: onExpand
		}
	};
var ss ;
function beforeClick(treeId, treeNode, clickFlag) {
	$("#pathId").val(treeNode.id);
	if(treeNode.id!='1'){ 
		$('#'+treeId+'_'+treeNode.id+'_a').on('click',function(){
			$('#'+treeId+'_'+ss+'_a').removeClass('curSelectedNode');
			$('#'+treeId+'_'+treeNode.id+'_a').addClass('curSelectedNode');
			ss = treeNode.id;
		});
		
		$('#'+treeId+'_'+treeNode.id+'_ul').on('click',function(){
			$('#'+treeId+'_'+ss+'_a').removeClass('curSelectedNode');
			$('#'+treeId+'_'+treeNode.id+'_a').addClass('curSelectedNode');
			ss = treeNode.id;
		});
		
		$('#'+treeId+'_'+ss+'_a').removeClass('curSelectedNode');
		$('#'+treeId+'_'+treeNode.id+'_a').addClass('curSelectedNode');
		ss = treeNode.id;
	}else if(treeNode.id=='1'){
		$('#'+treeId+'_'+1+'_a').on('click',function(){
			$('#'+treeId+'_'+ss+'_a').removeClass('curSelectedNode');
			$('#'+treeId+'_1_a').addClass('curSelectedNode');
			ss = 1;
		});
		$('#'+treeId+'_'+ss+'_a').removeClass('curSelectedNode');
		$('#'+treeId+'_1_a').addClass('curSelectedNode');
		ss = 1;
	}
	if (treeNode.level == 0 ) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		return false;
	}
}
function beforeExpand(treeId, treeNode) {
	$('#'+treeId+'_'+ss+'_a').removeClass('curSelectedNode');
}
function onExpand(event, treeId, treeNode) {
	$('#'+treeId+'_'+ss+'_a').removeClass('curSelectedNode');
}
function beforeCollapse(treeId, treeNode) {
	$('#'+treeId+'_'+ss+'_a').removeClass('curSelectedNode');
}
function onCollapse(event, treeId, treeNode) {
	$('#'+treeId+'_'+ss+'_a').removeClass('curSelectedNode');
}
function onAsyncSuccess(event, treeId, treeNode, msg) {
	if (msg.length == 0) {
		return;
	}
	ss = msg[0].id;
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.selectNode(msg[0].id);
	$('#'+treeId+'_'+msg[0].id+'_a').addClass('curSelectedNode');
	$("#pathId").val(msg[0].id);
}
function zTreeOnClick(event, treeId, treeNode) {
	/*if(treeNode.id!=null&&!treeNode.id==""){			
		listDevice("id",treeNode.id);
	}else{
		listDevice("deviceId",treeNode.deviceId);
	}*/
	$("#pathId").val(treeNode.id);
	
}
function addDiyDom(treeId, treeNode) {
	
	var spaceWidth = 5;
	var switchObj = $("#" + treeNode.tId + "_switch"),
	icoObj = $("#" + treeNode.tId + "_ico");
	switchObj.remove();
	icoObj.before(switchObj);

	if (treeNode.level > 0) {
		var spaceStr = "<span style='display: inline-block;width:" + (spaceWidth * treeNode.level)+ "px'></span>";
		switchObj.before(spaceStr);
	}
	var spantxt = $("#" + treeNode.tId + "_span").html();
	if(spantxt.length>25){  
		spantxt = trunc(spantxt,13,326);
	    $("#" + treeNode.tId + "_span").html(spantxt);  
	}

	//msg[i] = trunc(spantxt.name,26,326);alert(msg[i]);
	
}



//setting2  移动到文件夹时弹框的文件夹结构
var setting2 = {
		view: {
			showLine: true,
			showIcon: false,
			selectedMulti: false,
			dblClickExpand: false,
			addDiyDom: addDiyDom
		},
		async: {
		    contentType : "application/json",
		    enable : true,
		    dataType : "json",
		    type : "post",
		    url : "/tresource/Tresource_getResourceTree.do",
		    dataFilter: ajaxDataFilter
		    },
		data: {
			key:{
				name:"name"
			},
			simpleData: {
				enable: true,
				idKey:'id',
				pIdKey:'pid',
				rootPid:1
			}
		},
		callback: {
			beforeClick: beforeClick2,
			onClick: zTreeOnClick2,
			onAsyncSuccess: onAsyncSuccess2,
			beforeCollapse: beforeCollapse2,
			beforeExpand: beforeExpand2,
			onCollapse: onCollapse2,
			onExpand: onExpand2
		}
	};
	var ss2 ;
	function beforeClick2(treeId, treeNode, clickFlag) {
		$("#pathId").val(treeNode.id);
		if(treeNode.id!='1'){ 
			$('#'+treeId+'_'+treeNode.id+'_a').on('click',function(){
				$('#'+treeId+'_'+ss2+'_a').removeClass('curSelectedNode');
				$('#'+treeId+'_'+treeNode.id+'_a').addClass('curSelectedNode');
				ss2 = treeNode.id;
			});
			
			$('#'+treeId+'_'+treeNode.id+'_ul').on('click',function(){
				$('#'+treeId+'_'+ss2+'_a').removeClass('curSelectedNode');
				$('#'+treeId+'_'+treeNode.id+'_a').addClass('curSelectedNode');
				ss2 = treeNode.id;
			});
			
			$('#'+treeId+'_'+ss2+'_a').removeClass('curSelectedNode');
			$('#'+treeId+'_'+treeNode.id+'_a').addClass('curSelectedNode');
			ss2 = treeNode.id;
		}else if(treeNode.id=='1'){
			$('#'+treeId+'_'+1+'_a').on('click',function(){
				$('#'+treeId+'_'+ss2+'_a').removeClass('curSelectedNode');
				$('#'+treeId+'_1_a').addClass('curSelectedNode');
				ss2 = 1;
			});
			$('#'+treeId+'_'+ss2+'_a').removeClass('curSelectedNode');
			$('#'+treeId+'_1_a').addClass('curSelectedNode');
			ss2 = 1;
		}
		if (treeNode.level == 0 ) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			return false;
		}
	}
	function beforeExpand2(treeId, treeNode) {
		$('#'+treeId+'_'+ss2+'_a').removeClass('curSelectedNode');
	}
	function onExpand2(event, treeId, treeNode) {
		$('#'+treeId+'_'+ss2+'_a').removeClass('curSelectedNode');
	}
	function beforeCollapse2(treeId, treeNode) {
		$('#'+treeId+'_'+ss2+'_a').removeClass('curSelectedNode');
	}
	function onCollapse2(event, treeId, treeNode) {
		$('#'+treeId+'_'+ss2+'_a').removeClass('curSelectedNode');
	}
	function zTreeOnClick2(event, treeId, treeNode){
		$("#tempMovePathId").val(treeNode.id);
	}
	function onAsyncSuccess2(event, treeId, treeNode, msg) {
		if (msg.length == 0) {
			return;
		}
		ss2 = msg[0].id;
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.selectNode(msg[0].id);
		$('#'+treeId+'_'+msg[0].id+'_a').addClass('curSelectedNode');
		$("#tempMovePathId").val(msg[0].id);
	}

	var log, className = "dark";
	function dblClickExpand(treeId, treeNode) {
			return treeNode.level > 1;
	}
	function ajaxDataFilter(treeId, parentNode, responseData) {
		 if (responseData) {
		      for(var i =0; i < responseData.length; i++) {
			  	if(responseData[i].id==responseData[i].pid)
		        responseData[i].open=true;
		      }
	   	}
	    return responseData;
	};
	function selectAll() {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.setting.edit.editNameSelectAll =  $("#selectAll").prop("checked");
	}

	
	
	
	
	
	$(document).ready(function(){
		var treeObj1 = $("#treeDemo");
		$.fn.zTree.init(treeObj1, setting);
		$("#selectAll").bind("click", selectAll);
		
		if (!treeObj1.hasClass("showIcon")) {
			treeObj1.addClass("showIcon");
		}
		
		var treeObj = $("#treeDemo2");
		$.fn.zTree.init(treeObj, setting2);
		$("#selectAll").bind("click", selectAll);
		
		if (!treeObj.hasClass("showIcon")) {
			treeObj.addClass("showIcon");
		}
	});
