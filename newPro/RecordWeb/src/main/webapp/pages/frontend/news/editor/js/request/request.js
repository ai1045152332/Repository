/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 13-12-12
 * Time: 下午4:26
 * To change this template use File | Settings | File Templates.
 */
var uuid= '';
DSS = DSS || {};
DSS.Request = {
    urlHead : DSS.serverIp+'/news/',
    srcHead : DSS.serverIp+'/resource/Project',
    ajax : DSS.util.ajax,
    getLMenuList : function (fn){
        var data = [
      {id:'ID_LEFT_MENU_1',name:'开始',data:[
		{id:'25',name:'文本',innerHTML:'<div class="editItem25" ><div class="text_bg_over"></div><div class="TEXT_COMP_text2"></div><div class="TEXT_COMP_MASK">&nbsp;</div></div>'},
		{id:'1',name:'图片',innerHTML:'<div class="editItem1" ><img src="images/image.png" class="IMG_COMP_img"></div>'},
		/*{id:'12',name:'PPT',innerHTML:'<div class="editItem12" ><img src="images/ppt.png" class="PPT_COMP_src"></div>'},*/
		{id:'18',name:'PPT',innerHTML:'<div class="editItem8" ><img src="images/ppt.png" class="IMAGESWIPE_COMP_img" title="PPT"></div>'},
		{id:'11',name:'Flash',innerHTML:'<div class="editItem11" ><img src="images/flash.png" class="FLASH_COMP_src"></div>'},
		{id:'2',name:'视频',innerHTML:'<div class="editItem2" ><img src="images/video-new.png" class="VIDEO_COMP_video" /></div>'},
		{id:'14',name:'流媒体',innerHTML:'<div class="editItem14" ><img src="images/streaming.png" class="STREAM_COMP_src"></div>'},         
		{id:'13',name:'网页',innerHTML:'<div class="editItem13" ><img src="images/webpage.png" class="WEBPAGE_COMP_src"></div>'},
		{id:'3',name:'矩形',innerHTML:'<div class="editItem3" ><div class="RECT_COMP_rect"></div> </div>'},
		{id:'6',name:'横线',innerHTML:'<div class="editItem6" ></div>'},
		{id:'7',name:'竖线',innerHTML:'<div class="editItem7" ></div>'}]},
	{id:'ID_LEFT_MENU_2',name:'图片秀',data:[
	    {id:'4',name:'手风琴',innerHTML:'<div class="editItem4" ><img src="images/scroll1.png" class="ACCORDION_COMP_img"  title="轮播图-手风琴"></div>'},
	    {id:'9',name:'Wander',innerHTML:'<div class="editItem9" ><img src="images/scroll3.png" class="WANDER_COMP_img" title="轮播图-漫游"></div>'},
	    {id:'8',name:'Swipe',innerHTML:'<div class="editItem8" ><img src="images/swipe1.png" class="IMAGESWIPE_COMP_img" title="轮播图-滑动"></div>'},
	    {id:'10',name:'3D轮播',innerHTML:'<div class="editItem10" ><img src="images/scroll4.png" class="IMGS3D_COMP_img" title="轮播图-3D"></div>'}]},
	{id:'ID_LEFT_MENU_4',name:'插件',data:[
	   {id:'16',name:'时间_1',innerHTML:'<div class="editItem16"><div class="TIME_COMP_time_1"><span class="time">17:15</span><span class="date">05月07日星期三</span></div></div>'},
	   {id:'19',name:'天气_1',innerHTML:'<div class="editItem19" ><div class="WRATHER_COMP_weather_1"><img src="images/weather_icon.png" class="weather_icon" width="40" height="40" /><span class="data">城市<br />XX°/XX°</span></div></div>'},
	   {id:'15',name:'优秀作品',innerHTML:'<div class="editItem15" ><img src="images/eWorksComp.png" class="EWorks_COMP_src"></div>'},
	   {id:'22',name:'课程表',innerHTML:'<div class="editItem22" ><img src="images/courseList.png" class="COURSELIST_COMP_src"></div>'},
	   {id:'26',name:'倒计时',innerHTML:'<div class="editItem26" ><div class="COUNTDOWN_COMP_src"><span class="countdowntime" id="cdd">000</span><span class="countdowndate">天</span></div></div>'}]}        
   	];
        fn(data);
    },
    getProgramReq : function (id,fn){
        this.req(this.urlHead+'News_select.do','prid='+encodeURIComponent(id, "UTF-8"),function(rs){
            DSS.Options.cur.page = rs['page'][parseInt(rs.curIdx,10)-1];
            DSS.Options.cur.w = parseInt(rs['w'],10);
            DSS.Options.cur.h = parseInt(rs['h'],10);

            DSS.PageOpr.init(rs['page']);
            if(rs['page'].length>0)DSS.Request.selectPageReq(DSS.PageOpr.findPgIdByIdx(rs['curIdx']));
            if(fn)fn(rs);
        })
    },
    getProgramReqQui : function (id,fn){
        this.req(this.urlHead+'Program_selectQ.do','prid='+encodeURIComponent(id, "UTF-8"),function(rs){
            DSS.Options.cur.page = rs['page'][parseInt(rs.curIdx,10)-1];
            DSS.Options.cur.w = parseInt(rs['w'],10);
            DSS.Options.cur.h = parseInt(rs['h'],10);

            DSS.PageOpr.init(rs['page']);
            if(rs['page'].length>0)DSS.Request.selectPageReqQui(DSS.PageOpr.findPgIdByIdx(rs['curIdx']));
            if(fn)fn(rs);
        })
    },
	checkOnline  : function (orsmd5,onlinesource,fn){
    	var resources = new Array();
    	$.ajax({
   			type : "GET",
   			cache : false,
   			url : DSS.serverIp + "/news/ResourceFile_checkOnlineResource.do?prid="+DSS.Options.cur.program+'&pid='+DSS.Options.cur.page+"&md="+orsmd5,
   			async:false,
   			success : function(data) {
   				var resource = new Object();
   				if(data != "null"){
   					 var jsonobj=eval('('+data+')'); 
       				 resource.path = jsonobj[0]+jsonobj[1]+"."+jsonobj[2];
       				 resource.mapped = "/msgResource/data/resources/0/"+orsmd5+"/thumb.png";
      		         resource.snap = "/msgResource/data/resources/0/"+orsmd5+"/thumb.png";
      		         resource.w = jsonobj[3];
     		         resource.h = jsonobj[4];
      		         resource.type = "1";
      		         resource.respath = "";   
   				}else if(data == "null"){
   					 resource.path = onlinesource;
   			         resource.mapped = "/data/defaultvideothumb/thumb.png";
   			         resource.snap = "/data/defaultvideothumb/thumb.png";
   			         resource.w = "480";
   			         resource.h = "360";
   			         resource.type = "1";
   			         resource.respath = "";
   				}
   				resources.push(resource); 
   			},
   			error : function() {
   				$.messager.alert('错误', '程序进行出现错误', 'error');
   			}
   		});
    	return resources;
    },
    req : function (url,dt,fn){
        console.log(url+'?'+dt);

        /*if(url.indexOf('Program_select')>-1){
            var rs = {"w":"720","id":"Program1","curIdx":"1","page":[{"path":"http://172.21.110.63:7001/ManagementCenter/data/projects/edit/admin/Program1/1/1.png","pid":"1"},{"path":"","pid":"2"},{"path":"","pid":"3"}],"h":"540"};
            if(fn)fn(rs);
            return;
        }
        if(url.indexOf('Page_select')>-1){
            var rs = [{"imgLoop":[{"title":"","path":"/root/pic1.jpg","mapped":"/resources/1/A22A794C036C06431E632F9D5E2E298F/thumb.png"},{"title":"yyyyyyyy","path":"/root/pic2.jpg","mapped":"/resources/1/3304ABDD147D8E140B2CEF3201BD8372/thumb.png"}],"idx":"2","t":"9","during":"2000","iH":"<div style=\"width: 497px; height: 301px;\" class=\"editItem9\"><img src=\"images/scroll3.png\" class=\"WANDER_COMP_img\"><\/div>","scX":"1","scY":"1","isFtp":"1","bid":"blk20140315142207789","y":"188","x":"70"}];
            if(fn)fn(rs);
            return;
        };
        if(url.indexOf('_loadBtn')>-1){
            var rs = 'program_new,program_open,button_undo,button_redo,button_resource,program_preview,program_publish';
            if(fn)fn(rs);
            return;
        }*/
        DSS.util.ajax(url,dt,'post',true,function(rs){
        	//console.log('rs='+rs);
            if(typeof (rs)=="string"&&(rs.indexOf('[')>-1||rs.indexOf('{')>-1))rs = eval('('+rs+')'); 
           // console.log('rs eval='+rs);
            if(fn)fn(rs);
        });
    },
    reqSyncFal : function (url,dt,fn){
        console.log(url+'?'+dt);
        DSS.util.ajax(url,dt,'post',false,function(rs){
            if(typeof (rs)=="string"&&(rs.indexOf('[')>-1||rs.indexOf('{')>-1))rs = eval('('+rs+')'); 
            if(fn)fn(rs);
        });
    },
    reqGet : function (url,dt,fn){
        console.log(url+'?'+dt);
        DSS.util.ajax(url,dt,'get',true,function(rs){
            if(typeof (rs)=="string"&&(rs.indexOf('[')>-1||rs.indexOf('{')>-1))rs = eval('('+rs+')');   
            if(fn)fn(rs);
        });
    },
    addBlock : function (dt,fn){
       $.ajax({type:'get',
                url: this.urlHead+'Item_add.do?',
                data: dt,
                timeout:180000,
                success:function (rs){
                	if(rs.indexOf('-')>-1){
                		var el0 = document.getElementsByClassName("loading0")[0];
                    	var el1 = document.getElementsByClassName("loading1")[0];
                		 if(typeof(el0) != "undefined"){
	                		// 去掉遮罩
	                    	el0.parentNode.removeChild(el0);
	                    	el1.parentNode.removeChild(el1);
                		 }
                	}
                }
        });
//      this.req(this.urlHead+'Item_add.do',dt,fn);
    },
    updateBlock : function (dt,fn){
    	if(DSS.util.byId('quick').value=='1'){
    		dt = dt + '&quipub=1';
    	}
    	$.ajax({type:'post',
             url: this.urlHead+'Item_edit.do?',
             data: dt,
             timeout:180000,
             success:function (rs){
            	 if(rs.indexOf('-')>-1){
            		var el0 = document.getElementsByClassName("loading0")[0];
                   	var el1 = document.getElementsByClassName("loading1")[0];
            		// 去掉遮罩
            		 if(typeof(el0) != "undefined"){
                      	el0.parentNode.removeChild(el0);
                      	el1.parentNode.removeChild(el1);
             		}
            	 }
             }
     });
//     this.req(this.urlHead+'Item_edit.do',dt,fn);
    },
    delBlock : function (dt,fn){
        this.req(this.urlHead+'Item_del.do',dt,fn);
    },
    levelBlock : function (dt,fn){
    	// 置顶 置底 上移一层  下移一层
    	if(DSS.util.byId('quick').value=='1'){
    		dt = dt + '&quipub=1';
    	}
        this.req(this.urlHead+'Item_sort.do',dt,fn);
    },
    alignBlock : function(dt,fn){
        this.req(this.urlHead + 'Item_alin.do',dt,fn)
    },
    addBlockReq :function (ids,fn){
        var dt = DSS.EditComponent.getMtPsStr(ids);
        this.addBlock(dt,fn);
    },
    delBlockReq : function (ids,fn){
        var dt = DSS.EditComponent.getDelPsStr(ids);
        this.delBlock(dt,fn);
    },
    updateBlockReq : function (item,fn){
        var dt = DSS.EditComponent.getParseStr(item);
        this.updateBlock(dt,fn);
    },
    levelBlockReq : function (item,fn){
        var dt = DSS.EditComponent.getLevelPsStr(item);
        this.levelBlock(dt,fn);
    },
    alignBlockReq : function (list,fn){
        var dt = DSS.EditComponent.getAlignPsStr(list);
        this.alignBlock(dt,fn);
    },
    addPageReq : function (idx,fn){
        var dt = 'prid='+DSS.Options.cur.program+'&idx='+idx;
        this.reqSyncFal(this.urlHead+'Page_add.do?',dt,fn);
    },
    delPageReq : function (id,fn){
        var dt = 'prid='+DSS.Options.cur.program + '&pid='+id;
        this.req(this.urlHead+'Page_del.do',dt,fn);
    },
    copyPageReq :function (idx,ids,fn){
        var dt = 'prid='+DSS.Options.cur.program+'&idx='+idx+'&copyPid='+DSS.Options.cur.page+'&params='+encodeURIComponent(JSON.stringify(DSS.EditComponent.getMtPsList(ids)));
        this.req(this.urlHead+'Page_copy.do',dt,fn);
    },
    selectPageReq : function (id,fn){
        var dt = 'prid='+DSS.Options.cur.program + '&pid='+id;
        this.req(this.urlHead+'Page_select.do',dt,function (list){
            DSS.PageOpr.setCurId(id);
            DSS.BlockOpr.initAllBlock(list);
            if(fn)fn(list);
        });
    },
    selectPptReq: function (id,item,repa,soupa,fn){  // PPT
    	var dt = DSS.EditComponent.getParseStr(item);
    	dt = dt+"&repa="+repa+"&soupa="+soupa;
        $.ajax({type:'post',
            url: this.urlHead+'Item_selectPpt.do?',
            data: dt,
            async:false,
            timeout:180000,
            success:function (rs){
            	// 用户选择PPT文件的时候  同时获取到该PPT对应的所有image
                var wander = DSS.util.byId(id);
                var jsonobj = eval('('+wander.params+')'); 
                jsonobj.imgLoop =eval('('+rs+')');
                wander.params = JSON.stringify(jsonobj);
                DSS.Request.updateBlockReq(wander);
            }
    });
    },
    selectPageReqQui : function (id,fn){
        var dt = 'prid='+DSS.Options.cur.program + '&pid='+id;
        this.req(this.urlHead+'Page_selectQ.do',dt,function (list){
            DSS.PageOpr.setCurId(id);
            DSS.BlockOpr.initAllBlock(list);
            if(fn)fn(list);
        });
    },
    updatePageTime : function (id,time,fn){
        var dt = 'prid='+DSS.Options.cur.program + '&pid='+id + '&time='+time;
        this.req(this.urlHead+'Page_updateDuration.do',dt,fn);
    },
    sortPageReq : function (id,idx,fn){
        var dt = 'prid='+DSS.Options.cur.program + '&pid='+id + '&idx='+idx;
        this.req(this.urlHead + 'Page_sort.do',dt,fn);
    },
    snapPageReq : function (id,data,fn){
    	/*var pid = '';
    	if(typeof id =='object'){
    		pid = id.pid;
    	}else {
    		pid = id;
    	}*/
    	var dt = 'prid='+DSS.Options.cur.program + '&pid='+id + '&data='+data;
    	if(DSS.util.byId('quick').value=='1'){
    		dt = dt + '&quipub=1';
    	}
        this.req(this.urlHead + 'Page_snap.do',dt,fn);//生成缩略图
    },
    updateProgramRatio : function (fn){
        var dt = 'prid='+DSS.Options.cur.program + '&w='+DSS.Options.cur.w + '&h='+DSS.Options.cur.h;
        this.req(this.urlHead + 'News_updateRadio.do',dt,fn);
    },
    updateProgramRatioCustom : function (w,h,fn){
        var dt = 'prid='+DSS.Options.cur.program + '&w='+w + '&h='+h;
        this.req(this.urlHead + 'News_updateRadio.do',dt,fn);
    },
    checkBeforePublish:function(fn){
    	var dt = "";
    	if(DSS.util.byId('quick').value=='1'){
    		dt = 'quipub=1&';  
    	}
    	dt = dt + 'publishflag='+DSS.Options.cur.program+"@";
        this.req(this.urlHead + 'News_checkBeforePublish.do',dt,fn);
   },
    beginPublish:function(fn){
    	var dt = "";
    	if(DSS.util.byId('quick').value=='1'){
    		dt = 'quipub=1&';  
    	}
    	 dt = dt + 'publishflag='+DSS.Options.cur.program+"@";
         this.req(this.urlHead + 'News_publishbebusy.do',dt,fn);
    },
    programPublish : function (fn){
    	var elb = document.getElementsByTagName('body')[0];

	    var el0 = document.createElement("div");
	    el0.className="loading0";
	    el0.style.cssText = "height:100%;width:100%;position:absolute;left:0;top:0;display:block;z-index:998;background-color:#333;opacity:0.5;";
    	
	    var el = document.createElement("div");
	    el.className="loading1";
	    el.style.cssText = "height:100px;width:100px;position:absolute;left:50%;top:40%;display:block;z-index:999;margin-left:-50px;margin-top:-50px;";
		el.style.backgroundImage="url(images/load.gif)"; 
		
		elb.appendChild(el);
		elb.appendChild(el0);
		
		uuid=this.getuuid();//*************
		this.controlFlag(uuid,"add");
		var index=window.setInterval('DSS.Controller.beginquery()', 3000);//****test是ajax方法执行成功厚的操作，比如关闭窗口********
		
		var dt = "";
    	if(DSS.util.byId('quick').value=='1'){
    		DSS.util.byId('indexportIndexQ').value = index;
    		dt = 'quipub=1&prid='+DSS.Options.cur.program+"&uuid="+uuid;
    		this.req(this.urlHead + '_publishQ.do',dt,fn);
    	}else{
    		DSS.util.byId('indexportIndex').value = index;
    		dt = 'prid='+DSS.Options.cur.program+"&uuid="+uuid;
    		this.req(this.urlHead + 'News_publish.do',dt,fn);
    	}
    },
    saveBtn : function (idList,fn){
        var dt = 'btns='+idList;
        this.req(this.urlHead + 'News_saveBtn.do',dt,fn);
    },
    loadBtn : function (fn){
        var dt = '';
        this.req(this.urlHead + 'News_loadBtn.do',dt,fn);
    },
    getFptPath : function (fn){
        var dt = 'prid='+DSS.Options.cur.program + '&pid='+id + '&bid='+DSS.BlockOpr.getCurObj().id;
        this.req(this.urlHead + 'Item_getFtpPath.do',dt,fn);
    },
	getCName: function(callback){
		var url = DSS.serverIp + '/hhtc/curriculum/Curriculum_returnCourseListJson.do';
		this.req(url,'',callback);
	},
	getCList: function(callback){
		var dt = 'idstr='+DSS.BlockOpr.getProperty('cListID') +'&t='+ new Date().getTime();
		var url = '/hhtc/curriculum/Curriculum_returnCourseInfoJson.do';
		this.req(url,dt,callback);
	},
	getCityData: function(callback){
		var url = '/hhtc/project/ProjectPage_getCitys.do?&t='+new Date().getTime();
		this.req(url,'',callback);
	},
	UUIDprototypevalueOf : function(){ return this.id; },
	UUIDprototypetoString : function(){ return this.id; },
	UUIDprototypecreateUUID : function(){ 
	 var dg = new Date(1582, 10, 15, 0, 0, 0, 0);
	 var dc = new Date();
	 var t = dc.getTime() - dg.getTime();
	 var tl = this.UUIDgetIntegerBits(t,0,31);
	 var tm = this.UUIDgetIntegerBits(t,32,47);
	 var thv = this.UUIDgetIntegerBits(t,48,59) + '1'; // version 1, security version is 2
	 var csar = this.UUIDgetIntegerBits(this.UUIDrand(4095),0,7);
	 var csl = this.UUIDgetIntegerBits(this.UUIDrand(4095),0,7);
	 var n = this.UUIDgetIntegerBits(this.UUIDrand(8191),0,7) +
	 		this.UUIDgetIntegerBits(this.UUIDrand(8191),8,15) +
	 		this.UUIDgetIntegerBits(this.UUIDrand(8191),0,7) +
	 		this.UUIDgetIntegerBits(this.UUIDrand(8191),8,15) +
	 		this.UUIDgetIntegerBits(this.UUIDrand(8191),0,15); // this last number is two octets long
	 return tl + tm  + thv  + csar + csl + n;
	},
	UUIDgetIntegerBits : function(val,start,end){
		var base16 = this.UUIDreturnBase(val,16);
		var quadArray = new Array();
		var quadString = '';
		var i = 0;
		for(i=0;i<base16.length;i++){
		  quadArray.push(base16.substring(i,i+1));   
		}
		for(i=Math.floor(start/4);i<=Math.floor(end/4);i++){
		  if(!quadArray[i] || quadArray[i] == '') quadString += '0';
		  else quadString += quadArray[i];
		}
		return quadString;
   },
		UUIDreturnBase : function(number, base){
			return (number).toString(base).toUpperCase();
		},
	UUIDrand : function(max){
		return Math.floor(Math.random() * (max + 1));
		},
	getuuid:function getuuid(){
			return this.UUIDprototypecreateUUID();
		},
		uuid :	function UUID(){
		 this.id = this.createUUID();
		},
		controlFlag:	function (str,str2){
			var url=this.urlHead +"/News_controlState.do";
			$.ajax({
				type : "GET",
				cache : false,
				url : url,
				async:true,
				data:{
					uuid:str,
					flag:str2
				},
				success : function(data) {
					
				}
			});
		}	
};
