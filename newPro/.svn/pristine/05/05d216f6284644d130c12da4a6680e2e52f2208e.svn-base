/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 13-12-12
 * Time: 下午5:27
 * To change this template use File | Settings | File Templates.
 */
var uuid= '';
DSS = DSS || {};
var sig = false;//解决 点击多次发布按钮  会多次弹框的现象
DSS.Controller = {
    execute : function (excID){
        var cmdMap = {
            'CMD_NEW_BLOCK' : CMDBlockAdd,
            'CMD_BLOCK_MOVE': CMDBlockMove,
            'CMD_BLOCK_RESIZE':CMDBlockResize,
            'CMD_BLOCK_MOVE_RESIZE':CMDBlockMvAndRs,
            'CMD_BLOCK_PRPT_CHANGE':CMDBlockPrptChange,
            'CMD_BLOCK_RESOURSE_CHANGE':CMDBlockSrcChange,
            'CMD_BLOCK_RESOURSE_CHANGE_QUI':CMDBlockSrcChangeQui,
            'CMD_BLOCK_LOCK':CMDBlockLock, //普通节目编辑页面 右键 锁定功能
            'CMD_BLOCK_UNLOCK': CMDBlockUnlock, //普通节目编辑页面 右键 解锁功能
            'CMD_BLOCK_DEL':CMDBlockDel,
            'CMD_BLOCK_CUT': CMDBlockCut,
            'CMD_BLOCK_PASTE':CMDBlockPaste,
            'CMD_BLOCK_COPY':CMDBlockCopy,
            'CMD_BLOCK_ALIGN_TOP':CMDBlockAlignTop,
            'CMD_BLOCK_ALIGN_BOTTOM':CMDBlockAlignBottom,
            'CMD_BLOCK_ALIGN_LEFT':CMDBlockAlignLeft,
            'CMD_BLOCK_ALIGN_RIGHT':CMDBlockAlignRight,
            'CMD_BLOCK_ALIGN_HORIZONTAL':CMDBlockAlignHorizontal,
            'CMD_BLOCK_ALIGN_VERTICAL':CMDBlockAlignVertical,
            'CMD_BLOCK_LEVEL_TOP':CMDBlockLevelTop,
            'CMD_BLOCK_LEVEL_BOTTOM':CMDBlockLevelBottom,
            'CMD_BLOCK_LEVEL_UP':CMDBlockLevelMoveUp,
            'CMD_BLOCK_LEVEL_DOWN':CMDBlockLevelMoveDown,
            'CMD_CREATE_PAGE':CMDPageCreate,
            'CMD_DELETE_PAGE':CMDPageDelete,
            'CMD_SELECT_PAGE':CMDPageSelect,
            'CMD_UPDATE_PAGE_TIME':CMDPageUpdateTime,
            'CMD_COPY_PAGE':CMDPageCopy,
            'CMD_MOVE_PAGE':CMDPageMove,
            'CMD_PROGRAM_SIZE':CMDProgramSize,
            'CMD_TXT_PRPT_CHANGE':CMDBlockPrptChange,
            'CMD_RESOURSE':CMDAddRsrc,
          
            /*以下的18的键值对,是新版（点击工具栏的元素小图标，不再是拖拽效果，换成点击效果，如果想还原拖拽效果，请屏蔽此处）的节目制作      begin  */
            'CMD_NEW_BLOCK_TEXT' : CMDBlockAddText,
            'CMD_RESOURSE_IMAGE':CMDAddRsrc,
            'CMD_RESOURSE_PPT':CMDBlockAddPpt,
            'CMD_RESOURSE_FLASH':CMDAddRsrc,
            'CMD_RESOURSE_VIDEO':CMDAddRsrc,
            'CMD_NEW_BLOCK_FLOWVIDEO' : CMDBlockAddFlowvideo,
            'CMD_NEW_BLOCK_WEB' : CMDBlockAddWeb,
            'CMD_NEW_BLOCK_RECTANGLE' : CMDBlockAddRectangle,
            'CMD_NEW_BLOCK_HORIZONTALINE' : CMDBlockAddHorizontaline,
            'CMD_NEW_BLOCK_VERTICALLINE' : CMDBlockAddVerticalline,
            'CMD_NEW_BLOCK_PIANO' : CMDBlockAddPinao,
            'CMD_NEW_BLOCK_WANDER' : CMDBlockAddWander,
            'CMD_NEW_BLOCK_SWEPE' : CMDBlockAddSwepe,
            'CMD_NEW_BLOCK_3D' : CMDBlockAdd3D,
            'CMD_NEW_BLOCK_TIME' : CMDBlockAddTime,
            'CMD_NEW_BLOCK_WEATHER' : CMDBlockAddWeather,
            'CMD_NEW_BLOCK_GOODPLAY' : CMDBlockAddGoodplay,
            'CMD_NEW_BLOCK_CURRICULA' : CMDBlockAddCurricula,
            'CMD_NEW_BLOCK_COUNTDOWN' : CMDBlockAddCountdown
            /*以下的18的键值对,是新版（点击工具栏的元素小图标，不再是拖拽效果，换成点击效果，如果想还原拖拽效果，请屏蔽此处）的节目制作      end  */
        };
        var that = this;
        switch (excID){
            case 'CMD_UNDO':
                DSS.Commond.undo();
                break;
            case 'CMD_REDO':
                DSS.Commond.redo();
                break;
            case 'CMD_BASELINE_NO':
                DSS.BaseLine.setNeedLine(false);
                break;
            case 'CMD_BASELINE_YES':
                DSS.BaseLine.setNeedLine(true);
                break;
            case 'CMD_BLOCK_CUT':
                if(!DSS.BlockOpr.countSelectIds())return;
                DSS.Commond.executeUnRe(cmdMap[excID],arguments[1],arguments[2],arguments[3]);
                break;
            case 'CMD_BLOCK_PASTE':
                if(!DSS.BlockOpr.getCutIds())return;
                DSS.Commond.executeUnRe(cmdMap[excID],arguments[1],arguments[2],arguments[3]);
                break;
            case 'CMD_BLOCK_COPY':
                if(!DSS.BlockOpr.countSelectIds())return;
                DSS.Commond.executeUnRe(cmdMap[excID],arguments[1],arguments[2],arguments[3]);
                break;
            case 'CMD_PAGE_PREVIEW':
                //window.open(DSS.serverIpPort+'/ManagementCenter/data/product/project/project.html?prid='+DSS.Options.cur.program+'&pid='+DSS.Options.cur.page);
                window.open(DSS.serverIp + '/project/ProjectPreview_prepage.do?prid='+DSS.Options.cur.program+'&pid='+DSS.Options.cur.page);
                break;
            case 'CMD_SINGLE_PAGE_PREVIEW':
                window.open(DSS.serverIp + '/news/Page_preSingle.do?prid='+DSS.Options.cur.program+'&pid='+DSS.Options.cur.page);
                break;
            case 'CMD_SINGLE_PAGE_PREVIEW_Q':
                window.open(DSS.serverIp + '/project/ProjectPage_preSingleQ.do?prid='+DSS.Options.cur.program+'&pid='+DSS.Options.cur.page);
                break;
            case 'CMD_PROJECT_PREVIEW':
//            	var pn = document.getElementsByClassName('editItem22')[0].parentNode;
//            	var a =  DSS.EditComponent.getProperty(pn,'cListID');
//            	if(a == ''){
//            		alert('请选择课程表');
//            	}else{
//            		window.open(DSS.serverIp + '/ManagementCenter/project/ProjectPreview_preprogram.do?prid='+DSS.Options.cur.program+'&pid='+DSS.PageOpr.getCurIdx());
//            	}
            	window.open(DSS.serverIp + '/news/Page_preprogram.do?prid='+DSS.Options.cur.program+'&pid='+DSS.PageOpr.getCurIdx());
               	break;
            case 'CMD_PROJECT_PUBLISH':
            		DSS.Request.checkBeforePublish(function(rsu){
                		//console.info(rsu);
                		if(rsu == '0'){
                			DSS.Request.beginPublish(function(rs){
    	                		//console.info(rs);
    		                	if(rs == 'ok'){
    		                		 DSS.Request.programPublish(function(rs){
    		     	                	//console.info(rs);
    		     	                	if(sig == true){
    		     	                		
    		     	                	}
    		     	                	else{
    		     	                		
    		     	                	}
    		                		 });
    		                	}
    		                	else if(rs == 'busy'){
    		                		 alert("节目正在发布中，请稍候.....");
    		                	}
                			});
                		}
                		else if(rsu == '-1'){
	                		 alert("该节目暂无内容，请添加内容!");
	                	}
            		});
                break;
            case 'CMD_PROJECT_TEMPLATE':
            		// 模板弹出框
	            DSS.MdTempDialog.open('../../../project/ProjectProgram_createTemplate.do?prid='+DSS.Options.cur.program,
	            	function(win){
	            		win.addProjectTemplate();
	            		return;
//	            		var bl = win.createTemplate(DSS.Options.cur.program);
//	            		return bl;
	                },
	                function(){
		                //close
		            },
	                function(win){
		               	//完成操作
		               	 return;
			        }
            	);
                break;
            case 'CMD_PROJECT_PAGERATECUSTOM':
        		// 自定义分辨率
            	DSS.MdPagerateDialog.open('../../../project/ProjectProgram_pagerateCustom.do?prid='+DSS.Options.cur.program,
    	            	function(win){
    	            		var l = win.pageRateSet();
    	            		if(l){
    	            			var s = document.getElementById("page_rate_custom");
        	            		document.getElementById("page_rate_custom").className = 'check ratio_select';
        	            		s.setAttribute("_val","");
        	            		s.setAttribute("_val",l);
        	            		var strs = l.split('*');
                                DSS.util.byId('defaultrate').innerHTML=strs[0]+":"+strs[1];
        	            		DSS.Controller.execute('CMD_PROGRAM_SIZE',{w:strs[0],h:strs[1]});
    	            		}
    	            		return l;
    	                },
    	                function(){
    		                //close
    	                	return;
    		            }
                	);
            	break;
            case 'CMD_RESOURSE':
                DSS.MdDialog.open('/news/ResourceFile_homeIndex.do?type=1,4,6,7&prid='+DSS.Options.cur.program,function(win){
                    var list = win.query();
                    if(!list)return;
                    DSS.Commond.executeUnRe(cmdMap[excID],list);
                    that.snapPage();
                    return;
                });
                break;
            case 'CMD_RESOURSE_IMAGE':
                DSS.MdDialog.open('/news/ResourceFile_homeIndex.do?type=4&prid='+DSS.Options.cur.program,function(win){
					console.log(win);
                    var list = win.query();
					//console.log(list);
                    if(!list)return;
                    DSS.Commond.executeUnRe(cmdMap[excID],list);
                    that.snapPage();
                    return;
                });
                break;
			case 'CMD_RESOURSE_PPT'://ppt 6,pdf 5,word 2,excel3
            	 DSS.MdDialog.open('/news/ResourceFile_homeIndex.do?type=6&prid='+DSS.Options.cur.program,function(win){
                     var list = win.query();
                     if(!list)return;
                     DSS.Commond.executeUnRe(cmdMap[excID],list);
                     that.snapPage();
                     return;
                 });
                 break;
            case 'CMD_RESOURSE_FLASH':
            	 DSS.MdDialog.open('/news/ResourceFile_homeIndex.do?type=7&prid='+DSS.Options.cur.program,function(win){
                     var list = win.query();
                     if(!list)return;
                     DSS.Commond.executeUnRe(cmdMap[excID],list);
                     that.snapPage();
                     return;
                 });
                 break;
            case 'CMD_RESOURSE_VIDEO':
            	 DSS.MdDialog.open('/news/ResourceFile_homeIndex.do?type=1&prid='+DSS.Options.cur.program,function(win){
            		 var t = win.getVideoType();
            		 if(t == 'videoonline'){ // 在线视频
            			 var onlinesource = win.getVideoOnlineSource();
            			 //console.info(onlinesource+"______________________________");
            			 if(onlinesource == ''){
            				 return "";
            			 }
            			 if(onlinesource.indexOf("/") > -1){
            				 var ors = onlinesource.split("/");
            				 /*if(ors.length > 1){
            					 var orsmd5 = ors[ors.length - 2];
            					 var resources = new Array();
 	            				 var resource = new Object();
            					 if(orsmd5 != ""){
	            					 $.ajax({
	            	            			type : "GET",
	            	            			cache : false,
	            	            			url : DSS.serverIp + "/ManagementCenter/resource/ResourceFile_checkOnlineResource.do?prid="+DSS.Options.cur.program+'&pid='+DSS.Options.cur.page+"&md="+orsmd5,
	            	            			async:false,
	            	            			success : function(data) {
	            	            				if(data != "null"){
	            	            					 var jsonobj=eval('('+data+')'); 
	                	            				 resource.path = jsonobj[0]+jsonobj[1]+"."+jsonobj[2];
	    	            	           		         resource.mapped = "/resources/1/"+orsmd5+"/thumb.png";
	    	            	           		         resource.snap = "/resources/1/"+orsmd5+"/thumb.png";
	    	            	           		         resource.w = jsonobj[3];
	    	            	           		         resource.h = jsonobj[4];
	    	            	           		         resource.type = "1";
	    	            	           		         resource.respath = "";
	            	            				}else if(data == "null"){
	            	            					 resource.path = onlinesource;
	            	            			         resource.mapped = "/ManagementCenter/data/defaultvideothumb/thumb.png";
	            	            			         resource.snap = "/ManagementCenter/data/defaultvideothumb/thumb.png";
	            	            			         resource.w = "480";
	            	            			         resource.h = "360";
	            	            			         resource.type = "1";
	            	            			         resource.respath = "";
	            	            				}
	            	            			},
	            	            			error : function() {
	            	            				$.messager.alert('错误', '程序进行出现错误', 'error');
	            	            			}
	            	            		});
            					 }else{
            						 resource.path = onlinesource;
	            			         resource.mapped = "/ManagementCenter/data/defaultvideothumb/thumb.png";
	            			         resource.snap = "/ManagementCenter/data/defaultvideothumb/thumb.png";
	            			         resource.w = "480";
	            			         resource.h = "360";
	            			         resource.type = "1";
	            			         resource.respath = "";
            					 }
            					 resources.push(resource);   
    	           		         DSS.Commond.executeUnRe(cmdMap[excID],resources);
    	                         that.snapPage();
    	                         return;
            				 }*/
            				 var resources = new Array();
	            			 var resource = new Object();
            				 resource.path = onlinesource;
        			         resource.mapped = "/data/defaultvideothumb/thumb.png";
        			         resource.snap = "/data/defaultvideothumb/thumb.png";
        			         resource.w = "480";
        			         resource.h = "360";
        			         resource.type = "1";
        			         resource.respath = "";
        			         resources.push(resource);   
	           		         DSS.Commond.executeUnRe(cmdMap[excID],resources);
	                         that.snapPage();
	                         return;
            			 }
            		 }else{
            			 var list = win.query();
                         if(!list)return;
                         DSS.Commond.executeUnRe(cmdMap[excID],list);
                         that.snapPage();
                         return;
            		 }
                 });
                 break;
            case 'CMD_PROJECT_OPEN':
                window.location.href = DSS.serverIp + '/news/News_richNewsList.do?module=Project&signre=0';
                // signre=0  表示只有在这种情况下  页面才自动刷新一次
                break;
            case 'CMD_PROJECT_OPEN_Q': 
            	window.location.href = DSS.serverIp + '/project/Project_quickReplaceHome.do?module=QuickReplace&signre=0';
                // signre=0  表示只有在这种情况下  页面才自动刷新一次
                break;
            case 'CMD_PROJECT_NEW':
                break;
            default :
            	// 需要进行 undo redo的操作进行区分
            	// 右键删除    素材拖动    右键上移一层    下移一层    右键置顶    右键置底      18个素材  （允许undo redo）
            	if(excID == 'CMD_BLOCK_DEL' || excID == 'CMD_BLOCK_MOVE' || excID == 'CMD_BLOCK_LEVEL_UP' || excID == 'CMD_BLOCK_LEVEL_DOWN'
            		|| excID == 'CMD_BLOCK_LEVEL_TOP' || excID == 'CMD_BLOCK_LEVEL_BOTTOM'
            			|| excID == 'CMD_NEW_BLOCK_TEXT' || excID == 'CMD_RESOURSE_IMAGE' || excID == 'CMD_RESOURSE_PPT'|| excID == 'CMD_RESOURSE_FLASH'
            				|| excID == 'CMD_RESOURSE_VIDEO' || excID == 'CMD_NEW_BLOCK_FLOWVIDEO' || excID == 'CMD_NEW_BLOCK_WEB' || excID == 'CMD_NEW_BLOCK_RECTANGLE'
            					|| excID == 'CMD_NEW_BLOCK_HORIZONTALINE' || excID == 'CMD_NEW_BLOCK_VERTICALLINE' || excID == 'CMD_NEW_BLOCK_PIANO' || excID == 'CMD_NEW_BLOCK_WANDER' 
            						|| excID == 'CMD_NEW_BLOCK_SWEPE' || excID == 'CMD_NEW_BLOCK_3D' || excID == 'CMD_NEW_BLOCK_TIME' || excID == 'CMD_NEW_BLOCK_WEATHER' 
            							|| excID == 'CMD_NEW_BLOCK_GOODPLAY'){
            		DSS.Commond.executeUnRe(cmdMap[excID],arguments[1],arguments[2],arguments[3],arguments[4],arguments[5],arguments[6],arguments[7]);
            	}else if(excID == 'CMD_NEW_BLOCK_CURRICULA'){
            		//每个页面只允许添加一张课程表
            		$.ajax({
            			type : "GET",
            			cache : false,
            			url : DSS.serverIp + "/hhtc/news/News_getCurricula.do?prid="+DSS.Options.cur.program+'&pid='+DSS.Options.cur.page,
            			async:false,
            			success : function(data) {
            				if(data=='0'){
            					DSS.Commond.executeUnRe(cmdMap[excID],arguments[1],arguments[2],arguments[3],arguments[4],arguments[5],arguments[6],arguments[7]);
            				}else{
            					alert('每个页面只允许添加一张课程表');
            				}
            			},
            			error : function() {
            				$.messager.alert('错误', '程序进行出现错误', 'error');
            			}
            		});
            	}
            	else{
            		if(DSS.util.byId('quick').value=='1'){
            			if(excID == 'CMD_BLOCK_RESOURSE_CHANGE_QUI'){
                     		DSS.Commond.executeImVe(cmdMap[excID],arguments[1],arguments[2],arguments[3],arguments[4],arguments[5],arguments[6],arguments[7],arguments[8],arguments[9]);
                     	}else{
                     		DSS.Commond.execute(cmdMap[excID],arguments[1],arguments[2],arguments[3],arguments[4],arguments[5],arguments[6],arguments[7]);
                     	}
            		}else{
            			DSS.Commond.execute(cmdMap[excID],arguments[1],arguments[2],arguments[3],arguments[4],arguments[5],arguments[6],arguments[7]);
            		}
            		if(excID != 'CMD_SELECT_PAGE' && excID != 'CMD_UPDATE_PAGE_TIME'){
            			this.snapPage();
            		}
            	}
        }
        if(excID.indexOf('_BLOCK')>-1){
            this.snapPage();
        }
    },
    snapPage : function (){
        var distw = DSS.Options.cur.w;
        var disth = DSS.Options.cur.h;
        if (distw >= disth) {
            disth = disth * 240 / distw;
            distw = 240;
        }
        else {
            distw = distw * 240 / disth;
            disth = 240;
        }
        html2canvas(DSS.Options.el.mainBoxIn, {
            width: DSS.Options.cur.w,
            height: DSS.Options.cur.h,
            dist_width: distw,
            dist_height: disth,
            onrendered: function(canvas) {
                var imgData = canvas.toDataURL("image/png");
                DSS.Request.snapPageReq(DSS.Options.cur.page,imgData);
                /*var win=window.open("about:blank","image from canvas");
                win.document.write('<img src="'+imgData+'" alt="from canvas"/>');*/
                var t = DSS.PageOpr.getCurItem();
                if(t!=null){
                	var pageItem = t.getElementsByClassName(DSS.Options.eClass.pageImg)[0];
                    pageItem.innerHTML = '';
                    pageItem.style.background = 'none';
                    pageItem.appendChild(canvas);
                    canvas.style.width = '100%';
                    canvas.style.height = '100%';
                }
            }
        });
    },
    sunsmgQ : function(rs){
    	if(rs!=''){
    		var el0 = document.getElementsByClassName("loading0")[0];
        	var el1 = document.getElementsByClassName("loading1")[0];
        	el0.parentNode.removeChild(el0);
        	el1.parentNode.removeChild(el1);

        	$.ajax({
    			type : "GET",
    			cache : false,
    			url : DSS.serverIp + "/news/News_updatePlayPlanQ.do?prid="+DSS.Options.cur.program,
    			async:false,
    			success : function(data) {
    				if(0==data){
    					alert("发布成功");
//    					window.location.href = DSS.serverIp + '/ManagementCenter/project/Project_quickReplaceHome.do?module=QuickReplace&signre=0';
    					var hre = '';  // 标志：快速更新从哪个地方进来的  快速更新列表是0   播放管理列表是1 
    					if(DSS.util.byId('ent').value=='1'){
    						hre = DSS.serverIp + '/news/News_richNewsList.do?module=PlayPlan';
    					}else{
    						hre = DSS.serverIp + '/news/News_quickReplaceHome.do?module=QuickReplace';
    					}
    					location.replace(hre);
    				}
    				else{
    					
    				}
    			},
    			error : function() {
    				$.messager.alert('错误', '程序进行出现错误', 'error');
    			}
    		});
    	}
    },
    sunsmg : function (rs){
    	if(rs!=''){
    		var el0 = document.getElementsByClassName("loading0")[0];
        	var el1 = document.getElementsByClassName("loading1")[0];
        	el0.parentNode.removeChild(el0);
        	el1.parentNode.removeChild(el1);
    		
        	var audit=false;
        	$.ajax({
    			type : "GET",
    			cache : false,
    			url : DSS.serverIp + "/news/News_isProAuditOpen.do",
    			async:false,
    		    complete: function(){
    		    },
    			success : function(data) {
    				if("yes"==data){
    					audit=true;
    				}
    				else{
    				}
    			},
    			error : function() {
    				$.messager.alert('错误', '程序进行出现错误', 'error');
    			}
    		});
    		
        	//if(rs.split("@")[0] == '2'){ // 2 表示是开启审核的状态
        	if(audit){ 	
        	
        	//if(rs.split("@")[0] == '2'){ // 2 表示是开启审核的状态
    			 /*if(confirm("是否结束编辑？提交审核。")){
      			 // 返回首页
      			 window.location.href = DSS.serverIp + '/ManagementCenter/project/Project_home.do?module=Project&signre=0';
      			 return;
	      		 }else{
	          		 return;
	          	 }*/
	      		 alert("节目已经提交审核！");
    		} else{
    			var grade = 0;
    			var campus = 0;
    			sig = true;//解决 点击多次发布按钮  会多次弹框的现象
           	 	//弹出confirm类型的框  “发布成功！是否立即播放？”  “确定”-->继续选择设备   “取消”-->就此结束
            	//if(confirm("发布成功！是否立即播放？")){
            	//	// 权限检查
            	//	$.ajax({
            	//		type : "GET",
            	//		cache : false,
            	//		url : DSS.serverIp + "/news/News_sendtohost.do",
            	//		async:false,
            	//		success : function(data) {
            	//			// 说明有插播节目的权限
            	//			grade = data;
            	//		},
            	//		error : function() {
            	//			$.messager.alert('错误', '程序进行出现错误', 'error');
            	//		}
            	//	});
            	//	$.ajax({
            	//		type : "GET",
            	//		cache : false,
            	//		url : DSS.serverIp + "/playplan/PlayPlan_checkPriinseModule.do",
            	//		async:false,
            	//		success : function(data) {
            	//			// 说明有优先插播节目的权限
            	//			campus = data;
            	//		},
            	//		error : function() {
            	//			$.messager.alert('错误', '程序进行出现错误', 'error');
            	//		}
            	//	});
            		
            		//DSS.MdDeDialog.open('/tresource/TresourceFile_devHomeIndex.do?type=-1&grade='+grade+'&campus='+campus,
            		DSS.MdDeDialog.open('/news/News_selectPolicy.do?prid='+DSS.Options.cur.program,
                            function(win){
                				//var bl = win.nextStep();
                				//DSS.Controller.snapPage();
    		                    //return bl;
								return ;
    		                },
    		                //function(){
    		                //    //close
    		                //	sig = false;//解决 点击多次发布按钮  会多次弹框的现象
    		                //},
                            //function(win){
    		                //   //win.prevStep();
    		                //   return;
    		                //},
                            //function(win){
    		                //	//var cl = win.saveOrUpdate(rs.split("@")[1]);
    		                //	//DSS.Controller.snapPage();
    		                //    //return cl;
								//return;
    		                //},
    		                function(win){
    		                	//完成操作
								win.updateProjectVal(rs.split("@")[1])
    		                	 //return;
	     		            }
	     		            //普通节目提交操作
    		                //function(win){
	     		            //	//win.updateProjectVal(rs.split("@")[1]);
								//win.updateProjectVal(rs.split("@")[1])
	     		            //	//var cl = win.directplay();
    		                //	//return  cl;
	     		            //}
                	);
           	 	//}else{
           	 	//	sig = false;//解决 点击多次发布按钮  会多次弹框的现象
           	 	//}
       	}
      }
    },
    beginquery:	function (){
		//console.info("in query ...");
		var url=DSS.serverIp + "/news/News_queryState.do";
		$.ajax({
			type : "GET",
			cache : false,
			url : url,
			async:false,
			data:{
				uuid:uuid
			},
		    complete: function(){
		    },
			success : function(data) {
				var d = data;
				if(data.indexOf("finish")>-1){
					//console.info("finish。。。。");
					var ps = '';
					if(DSS.util.byId('quick').value=='1'){
						var index = DSS.util.byId('indexportIndexQ').value;
						window.clearInterval(index); //移除定时器
						ps = document.getElementById("stateQ").value;
						if(ps == ''){
							ps = '1';
						}
						DSS.Controller.sunsmgQ(ps+"@"+d.split("finish_")[1]);
						DSS.Request.controlFlag(uuid,"remove");
					}else{
						var index = DSS.util.byId('indexportIndex').value;
						window.clearInterval(index); //移除定时器
						ps = document.getElementById("state").value;
						if(ps == ''){
							ps = '1';
						}
						DSS.Controller.sunsmg(ps+"@"+d.split("finish_")[1]);
						DSS.Request.controlFlag(uuid,"remove");
					}
				}
				else{
				}
			},
			error : function() {
				$.messager.alert('错误', '程序进行出现错误', 'error');
			}
		});
	}
};