var curMenu = null, zTree_Menu = null;
var urlStrOption = '';
var setting = {
		view: {
			showLine: false,
			showIcon: false,
			selectedMulti: false,
			dblClickExpand: false,
			addDiyDom: addDiyDom,
		},
		async: {
		    contentType : "application/json",
		    enable : true,
		    dataType : "json",
		    type : "post",
		    url : "/ManagementCenter/device/DeviceGroup_getGroupsTreeHasDevice.do",
		    dataFilter: ajaxDataFilter
		    },
		data: {
			key:{
				name:"text"
			},
			simpleData: {
				enable: true,
				idKey:'id',
				pIdKey:'pid',
				rootPId:1
			}
			
		},
		callback: {
			beforeClick: beforeClick,
			beforeDrag: beforeDrag,
			onClick: zTreeOnClick,
			onAsyncSuccess: zTreeOnAsyncSuccess
		}
	};

	var log, className = "dark";
	function beforeDrag(treeId, treeNodes) {
		return false;
	}
	function zTreeOnClick(event, treeId, treeNode) {
		if(treeNode.id!=null&&(!treeNode.id=="")){			
			listDevice("groupId",treeNode.id,treeNode.text);
			selectNode("groupId",treeNode.id,treeNode);
		}else{
			listDevice("deviceId",treeNode.deviceId,treeNode.text);
			selectNode("deviceId",treeNode.deviceId,treeNode.text);
		}
	}
	
	//树形结构加载完成以后做相应的修正工作
	function zTreeOnAsyncSuccess(event, treeId, msg) {   
		var firstgroup=getfirstgroup();
		// 根目录下的节目列表
		if(firstgroup!=1){
			document.getElementById("treeDemo_1_a").style.display ="none";
		}
		
		listDevice("groupId",firstgroup,'所有设备');
		selectNode("groupId",firstgroup,'所有设备');
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
	function zTreeOption(doUrl, backParams){
		var tempdata ;
		$.ajax({
			async: false,
			url: doUrl,
			type: 'post',
			dataType: 'json',
			data: backParams,
			success: function(data){
				tempdata = data;
			}
		});
		return tempdata;
		}

	function selectAll() {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.setting.edit.editNameSelectAll =  $("#selectAll").prop("checked");
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
		
		var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
		
		urlStrOption='/ManagementCenter/device/DeviceGroup_getDeviceCount.do';
	  	var params="text="+treeNode.text+"&id="+treeNode.id+"&pid="+treeNode.pid+"&type="+treeNode.type;
	 	var tempdata=zTreeOption(urlStrOption,params);
	 	var spantxt = sObj.html();
		if(spantxt.length>10){  
			spantxt = trunc(spantxt,30,200);
			sObj.html(spantxt);  
		}
		if(treeNode.type=='groupType'){
			var addStr;
			if(treeNode.id==1){
				addStr = "<span id='diyBtn_" +treeNode.id+ "'> / "+tempdata+"</span>";
			}else{
				addStr = "<span id='diyBtn_" +treeNode.id+ "'>("+tempdata+")</span>";
			}
			sObj.after(addStr);			
		}
		// 播放管理  树结构刚加载时 默认选中 所有设备的节点   当用户再点击其它节点时   所有设备的节点的选中的样式也要做相应的变更 
		if(treeNode.type=='groupType'){
			if(treeNode.id==1){
				var a =switchObj;
				var b=a[0].parentNode; 
				b.className = b.className +' curSelectedNode';
			}
		}
	}

	function beforeClick(treeId, treeNode) {
//		if (treeNode.level == 0 ) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
//			return false;
//		}
		return true;
	}
	
	function getfirstgroup(){
		var firstgroup="";
		$.ajax({
			async: false,
			url:   "/ManagementCenter/device/DeviceGroup_getFirstGroup.do",
			type: 'post',
			dataType: 'json',
			success: function(data){
				firstgroup=data;
			},
			error:function(data){
			}
		});
		return parseInt(firstgroup);
	}
	
	$(document).ready(function(){
		var treeObj = $("#treeDemo");
		$.fn.zTree.init(treeObj, setting);
		$("#selectAll").bind("click", selectAll);
		
		if (!treeObj.hasClass("showIcon")) {
			treeObj.addClass("showIcon");
		}
//		var treeObj2 = $.fn.zTree.getZTreeObj("treeDemo");
//		var nodes = treeObj2.getNodes();
//		treeObj2.hideNode(nodes[0]);
	});
