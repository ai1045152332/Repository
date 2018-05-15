/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-1-24
 * Time: 下午2:15
 * To change this template use File | Settings | File Templates.
 */


var CMDBlockPrptChange = function (id,prptName,oldVal,newVal){
	if(prptName == 'tymdhms'){  // 倒计时  天
		this.util = DSS.util;
		this.elId = id;
		var wander = this.util.byId(this.elId);
		var w = 326;
		var h = 64;
		wander.innerHTML = '<div class="editItem26" ><div class="COUNTDOWN_COMP_src"><span class="countdowntime" id="cddd">00</span><span class="countdowndate">天</span><span class="countdowntime" id="cddh">00</span><span class="countdowndate">时</span><span class="countdowntime" id="cdddm">00</span><span class="countdowndate">分</span><span class="countdowntime" id="cdds">00</span><span class="countdowndate">秒</span></div></div>';
		var newwidth = wander.offsetWidth;
		var newheight = wander.offsetHeight;
		var o = JSON.parse(wander.params);
		 /*if(oldwidth/oldheight>w/h){
			 wander.style.height = oldheight + 'px' ;
		        wander.style.width = w*oldheight/h + 'px' ;
		        wander.style.height = h + 'px' ;
		    wander.style.width = w*newheight/h + 'px' ;
		}else{
		    	wander.style.width = oldwidth + 'px' ;
		    	wander.style.height = h*oldwidth/w + 'px' ;
		}*/
		if(newwidth/newheight>w/h){
			wander.style.height = newheight + 'px' ;
	        wander.style.width = w*newheight/h + 'px' ;
		}else{
			wander.style.width = newwidth + 'px' ;
	    	wander.style.height = h*newwidth/w + 'px' ;
		}
		//o.w = wander.offsetWidth;
		//o.h = wander.offsetHeight;
		wander.children[0].children[0].style.width = wander.style.width;
		wander.children[0].children[0].style.height = wander.style.height;
		wander.children[0].children[0].style.lineHeight = wander.style.height;
//		wander.style.width = oldwidth + 'px' ;
//		wander.style.height = oldheight + 'px';
		var chl = wander.children[0].children[0].children;
		if(chl.length > 0){
			for(var i =0;i<chl.length;i++){
				if(chl[i].className == 'countdowndate'){
					chl[i].style.color = o.fontcolor;
				}else if(chl[i].className == 'countdowntime'){
//					chl[i].style.color = o.numbercolor;
					chl[i].style.color = o.fontcolor;
				}
			}
		}
		
		 DSS.BlockOpr.setCurObj(this.util.byId(this.elId));
	     DSS.BlockOpr.setSize(wander.offsetWidth,wander.offsetHeight);
	     if(DSS.PropertyBar)DSS.PropertyBar.setSize(wander.offsetWidth,wander.offsetHeight);
		
		DSS.Request.updateBlockReq(wander);
	}else if(prptName == 'tymd'){ // 倒计时  天时分秒
		this.util = DSS.util;
		this.elId = id;
		var wander = this.util.byId(this.elId);
		var w = 109;
		var h = 64;
		wander.innerHTML = '<div class="editItem26" ><div class="COUNTDOWN_COMP_src"><span class="countdowntime" id="cdy">000</span><span class="countdowndate">天</span></div></div>';
		
		var newwidth = wander.offsetWidth;
		var newheight = wander.offsetHeight;
		
		var o = JSON.parse(wander.params);
		
		if(newwidth/newheight>w/h){
			wander.style.height = newheight + 'px' ;
	        wander.style.width = w*newheight/h + 'px' ;
		}else{
			wander.style.width = newwidth + 'px' ;
	    	wander.style.height = h*newwidth/w + 'px' ;
		}
		
		wander.children[0].children[0].style.width = wander.style.width;
		wander.children[0].children[0].style.height = wander.style.height;
		wander.children[0].children[0].style.lineHeight = wander.style.height;
		var chl = wander.children[0].children[0].children;
		if(chl.length > 0){
			for(var i =0;i<chl.length;i++){
				if(chl[i].className == 'countdowndate'){
					chl[i].style.color = o.fontcolor;
				}else if(chl[i].className == 'countdowntime'){
//					chl[i].style.color = o.numbercolor;
					chl[i].style.color = o.fontcolor;
				}
			}
		}
		
		
		 DSS.BlockOpr.setCurObj(this.util.byId(this.elId));
	     DSS.BlockOpr.setSize(wander.offsetWidth,wander.offsetHeight);
	     if(DSS.PropertyBar)DSS.PropertyBar.setSize(wander.offsetWidth,wander.offsetHeight);
		
		
		DSS.Request.updateBlockReq(wander);
	}else{
		this.elId = id;
	    this.util = DSS.util;
	    console.log(oldVal+'==='+newVal);
	    this.prptName = prptName;
	    this.oldVal = oldVal;
	    this.newVal = newVal;
	    // 修改轮播的所见所得
	    var wander = this.util.byId(this.elId);
	    if(prptName=='ppt_path'){ // 如果是PPT
	    	wander.children[0].children[0].src = newVal.mapped;
	    }else{
	    	var jsonobj = eval('('+wander.params+')');
	        if(jsonobj.imgLoop!=null){
	        	if(jsonobj.imgLoop.length > 0){
	        		wander.children[0].children[0].src = jsonobj.imgLoop[0].mapped;
	        	}
	        }
	    }
	    //  右侧 三个轮播图 选择图片   添加的时候  如果文件过大   会存在暂不显示的问题
	    if(wander.type_id == '4' || wander.type_id == '9' || wander.type_id == '8'|| wander.type_id == '18' || wander.type_id == '15' ||  wander.type_id == '11'){
	        // 胖子加一个div遮罩
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
	    }
	    if(prptName=='ppt_path'){ // 如果是PPT
	    	DSS.BlockOpr.setCurObj(DSS.BlockOpr.getCurObj());
	    	DSS.Request.selectPptReq(DSS.BlockOpr.getCurObj().id,DSS.BlockOpr.getCurObj(),newVal.mapped,newVal.path);
	    }else{
	    	DSS.Request.updateBlockReq(wander);
	    }
	}
};
CMDBlockPrptChange.prototype = {
    constructor : CMDBlockPrptChange,
    undo : function (){
//        DSS.BlockOpr.setCurObj(this.util.byId(this.elId));
//        DSS.BlockOpr.setProperty(this.prptName,this.oldVal);
//        if(DSS.PropertyBar)DSS.PropertyBar.setVal(this.prptName,this.oldVal);
//        DSS.Request.updateBlockReq(this.util.byId(this.elId));
    },
    redo : function (){
//        DSS.BlockOpr.setCurObj(this.util.byId(this.elId));
//        DSS.BlockOpr.setProperty(this.prptName,this.newVal);
//        if(DSS.PropertyBar)DSS.PropertyBar.setVal(this.prptName,this.newVal);
//        DSS.Request.updateBlockReq(this.util.byId(this.elId));
    },
    destroy : function (){
        this.util = null;
        this.elId = null;
        this.prptName = null;
        this.oldVal = null;
        this.newVal = null;
    }
};

