/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-2-11
 * Time: 下午4:53
 * To change this template use File | Settings | File Templates.
 */

(function (){
    function adjustViewZone() {
        var container = DSS.util.byId('player_container');
        container.style.height =  PB.Options.cur.h + 'px';
        container.style.width =  PB.Options.cur.w + 'px';
        var curRatio = PB.Options.cur.w/PB.Options.cur.h;
        var docW = document.documentElement.clientWidth,docH = document.documentElement.clientHeight;
        var scale = docW/PB.Options.cur.w;
        if(docW/docH>=curRatio){
            var scale = docH/PB.Options.cur.h;
        }
        var l = (docW - PB.Options.cur.w * scale) / 2;
        var t = (docH - PB.Options.cur.h * scale) / 2;
        var transStr = "matrix("+scale+",0,0,"+scale+","+l+","+t+")";
        with (container.style){
            webkitTransformOrigin = "0 0";
            mozTransformOrigin = "0 0";
            transformOrigin = "0 0";
            webkitTransform = transStr;
            mozTransform = transStr;
            transform = transStr;
        }
    }

    function showPage (pageObj){
    	androidStopVideos();
        var ifm = document.createElement('iframe');
        var ifmid = document.createAttribute('id');
        ifmid.value = pageObj.pid;
        ifm.setAttributeNode(ifmid);

        var container = DSS.util.byId('player_container');
        container.appendChild(ifm);
        ifm.src = pageObj.path;
        ifm.className = 'alf';
        ifm.style.cssText = 'left:0px;z-index:100;';
        return ifm;
    }
    function isEmptyObject(obj){ // 判断对象是否为空
        for(var n in obj){return false;} 
        return true; 
    } 
    var ts = 0;
    function setPageTimer(){
    	console.info(DSS.util.byId('stopsign').value+" --------- 开始切换页面");
		var iframes = document.getElementsByTagName("iframe");
		if (iframes != null && iframes.length > 0) {
			for (var i = 0, ilen = iframes.length; i < ilen; i++) {
				if (iframes[i]) {
					var aSign = DSS.util.byId('stopsign');
					if (parseInt(aSign.value) == -1) {
						//二级页面打开，页切换延后
			    		setTimeout(function(){
			                setPageTimer();
			            }, 5000);
			    		return;
					}
				}
			}
		}

        var pageObj = PB.Options.cur.pageList[PB.Options.cur.curPage];
        var ifm = showPage(pageObj);
        ifm.style.cssText = 'left:0px;z-index:2;';

//        DSS.util.startPageMove(ifm,{left:0},function(){
        	// 节目的iframe
            setTimeout(function(){
                var container = document.getElementById("player_container");
                var ch = container.getElementsByTagName("iframe")[0];
                if(ch.className == 'alf'){
                	ch.contentWindow.destroyPage();
                	container.removeChild(ch);
                	ifm.style.cssText = 'left:0px;z-index:100;';
                    ifm = null;
                }
            },1000);
//        });

			/*	// 表示许可证过期的DIV
        	var divexcept = document.getElementById("noticeWrap");
        	if(divexcept!=null && !isEmptyObject(divexcept)){
        		container.removeChild(divexcept); // 移除许可证过期的DIV
        	}*/

            console.info(" ---------------------  : "+ts);
            if(ts == 0){
            	ts = 1;
            	 PB.Options.pageTimer = setTimeout(function(){
                     PB.Options.cur.curPage = (PB.Options.cur.curPage+1)%PB.Options.cur.pageList.length;
                     setPageTimer();
                 }, pageObj.during * 1000);
            }
            else if(ts == 1){
            	 PB.Options.pageTimer = setTimeout(function(){
                     PB.Options.cur.curPage = (PB.Options.cur.curPage+1)%PB.Options.cur.pageList.length;
                     setPageTimer();
                 }, pageObj.during * 1000+3000);
            }
       
    }

    function showCurPage (){
        var pageObj = PB.Options.cur.pageList[PB.Options.cur.curPage];
        showPage(pageObj);

        PB.Options.pageTimer = setTimeout(function(){
            PB.Options.cur.curPage = (PB.Options.cur.curPage+1)%PB.Options.cur.pageList.length;
            setPageTimer();
        },pageObj.during*1000);
    }

    adjustViewZone();
    /**
     * 播放节目
     */
    if(PB.Options.cur.path){
        showPage({path:PB.Options.cur.path});
    }else if(PB.Options.cur.pageList){
        var lis = PB.Options.cur.pageList = JSON.parse(PB.Options.cur.pageList);
        if(lis.length==0){
            return;
        }else if(lis.length==1){
            showPage(lis[0]);
        }else {
            showCurPage();
        }
    }

    var serverip = DSS.util.getQueryString("serverip");
    var serverport = DSS.util.getQueryString("serverport");
    var serverClassid = DSS.util.getQueryString("classid"); 
    if (serverip) {
        if (serverport) {
            PB.Options.serverDomain = serverip+":"+serverport;
        } else {
            PB.Options.serverDomain = serverip;
        }
    }
    if(serverClassid){
    	 PB.Options.serverClassid = serverClassid;
    }

    //禁止浏览器默认的滑动事件
    DSS.util.events(window,'touchmove',function(e){
        if(e.stopPropagation)e.stopPropagation();
        if(e.preventDefault)e.preventDefault();
    })
})();

//params:projectId,pageId,blkId,content
function javablock(prjId,pageId,blkId,content) {
	var ifm=document.getElementById(pageId);
	if(ifm)
		return ifm.contentWindow.updateProject(prjId,pageId,blkId,content);
	else
		return "false";
}


// 通知滚动效果
var Rolling = function(container,options){
    this.container = typeof container === 'string' ? document.getElementById(container) : container;
    this.options = options || {};
    this.delay = this.options.delay || 30;
    this.init();
};
Rolling.prototype = {
    init: function(){
        var me = this;
        this.cell_1 = this.container.getElementsByClassName('rollingCell_1')[0];
        this.cell_2 = this.container.getElementsByClassName('rollingCell_2')[0];
        // 当滚动区的宽度大于或等于父容器的宽度时，则将当前列的内容复制给下一列
        if(this.cell_1.offsetWidth >= this.container.offsetWidth){ 
            this.cell_2.innerHTML = this.cell_1.innerHTML;
        }
        this.start();
        // 添加事件处理程序
        //this.addEvtsHandler();
    },
    addEvtsHandler: function(){
        var me = this;
        this.utils.addEvent(this.container,this.events.mouseover,function(){me.stop();},!1);
        this.utils.addEvent(this.container,this.events.mouseout,function(){me.start();},!1);
    },
    stop: function(){
        var me = this;
        if(me.rollingTimer) clearInterval(me.rollingTimer);
    },
    start: function(){
        var me = this;
        me.rollingTimer = setInterval(function(){
            me.rolling();
        }, me.delay);
    },
    rolling: function(){
        var me = this;
        me.container.scrollLeft >= me.cell_2.offsetWidth ? me.container.scrollLeft-=me.cell_1.offsetWidth + 50 : me.container.scrollLeft++;
    },
    utils: {
        addEvent: function (el, type, fn, capture) {
            el.addEventListener ? el.addEventListener(type, fn, capture) : el.attachEvent && el.attachEvent("on" + type, fn);
        },
        removeEvent: function (el, type, fn, capture) {
            el.removeEventListener ? el.removeEventListener(type, fn, capture) : el.detachEvent && el.detachEvent("on" + type, fn);
        }
    },
    events: {
        mouseover: 'mouseover',
        mouseout: 'mouseout'
    },
    destroy: function(){
        var me = this;
        this.stop();
        this.utils.removeEvent(this.container,this.events.mouseover,function(){me.stop();},!1);
        this.utils.removeEvent(this.container,this.events.mouseout,function(){me.start();},!1);
        me = null;
    }
};

// 通知组件
var NoticeComp = function(jsonData){
    this.container = document.getElementById('noticeWrap');
    this.init(jsonData);
};
NoticeComp.prototype = {
    template: {
        bannerMode: '<table>'
            + ' <tr>'
            + '     <td class="rollingCell_1">'
            + '         <table>'
            + '             <tr>'
            + '                 <td>{content}</td>'
            + '             </tr>'
            + '         </table>'
            + '     </td>'
            + '     <td class="rollingCell_2"></td>'
            + ' </tr>'
            + '</table>',
        fullMode: '<div class="notice_content">{content}</div>'
    },
    init: function(json) {   
        if( typeof json == "string" && (json.indexOf('[')>-1 || json.indexOf('{')>-1)){
            var rsData = JSON.parse(json);
            if(!this.container){
                this.container = document.createElement('div');
                this.container.id = 'noticeWrap';
            }  else {
                this.destroy();
                this.container = null;
                noticeInstances.destroy();
                this.container = document.createElement('div');
                this.container.id = 'noticeWrap';
            }
            
            this.destroy();

            var tempStr = rsData.style == 3 ? this.template.fullMode : this.template.bannerMode;
            //this.container.innerHTML += tempStr.replace(/\{content\}/g,rsData.content);
            this.container.innerHTML += rsData.style == 3 ? tempStr.replace(/\{content\}/g, rsData.content) : tempStr.replace(/\{content\}/g, "&nbsp;" + rsData.content);
            var player_container = document.getElementById('player_container');
            var player_body = document.getElementById('player_body');
            if(player_container){
            	player_body.appendChild(this.container);
            }

            // 根据不同的播放模式，设置相应的样式及效果
            //this.container.removeAttribute('style');
            this.container.style.cssText = '';
            switch(rsData.style){
                case 1:
                    this.container.style.top = 0;
                    this.rolling = new Rolling(this.container); 
                    break;
                case 2:
                    this.container.style.bottom = 0;
                    this.rolling = new Rolling(this.container); 
                    break;
                default:
                    this.container.style.cssText = 'height:100%;';
                    var targetEl = this.container.children[0];
                    targetEl.style.cssText = 'position:absolute;top:50%;left:50%;';
                    targetEl.style.marginTop = -targetEl.clientHeight/2 + 'px';
                    targetEl.style.marginLeft = -targetEl.clientWidth/2 + 'px';
            }
        }
    },
    destroy: function(){
        if(this.rolling) this.rolling.destroy();
        if(this.container) {
	        this.container.innerHTML = '';
	        this.container.style.cssText = 'height:0px;width:0px;';
	    }
    }
};


//刷卡统计
function checkmessage(jsonMessage,jsonNotice){
	
	var mycheckmessage, mycheckpop;
	
	
	
	function isNullObj(obj){ //判断是否是空对象
	    for(var i in obj){
	        if(obj.hasOwnProperty(i)){
	            return false;
	        }
	    }
	    return true;
	}
	
	this.container = document.createElement('div');
	this.container.id = 'noticediv';
	this.container.innerHTML = '<div class="checkmessage">'
	+ '<div class="bg"></div>'
	+ '<div class="content" id="checkmessage"></div>'
	+ '</div>'	
	+ '<div class="checkpop">'	
	+ '<div class="content" id="checkpop"></div>'
	+ '</div>';
	
	var player_container = document.getElementById('player_container');
    //var player_body = document.getElementById('player_body');
    if(player_container){
    	player_container.appendChild(this.container);
    }

	var messageObj = eval("("+jsonMessage+")");
	var objResultMessage = isNullObj(messageObj);
	var noticeObj = eval("("+jsonNotice+")");
	var objResultNotice = isNullObj(noticeObj);
	
	mycheckmessage = document.getElementById("checkmessage");
	mycheckpop = document.getElementById("checkpop");
	
	if(objResultMessage==true&&objResultNotice==true){ //同时为空 终止播放
		return false;
		mycheckmessage.style.display = 'none';
		mycheckpop.style.display = 'none';
	}else if(objResultNotice==true){ //切换节目
		mycheckmessage.innerHTML = "今日打卡前三名："+ messageObj.firstName + "，" + messageObj.secondName + "，" + messageObj.thirdName + "<br>应到人数：" + messageObj.totalCount + " 实到人数：" + messageObj.alreadyCount + " 未到人数：<span class='color'>" + messageObj.notCount + "</span>" ;
	}else{
		mycheckmessage.innerHTML = "今日打卡前三名："+ messageObj.firstName + "，" + messageObj.secondName + "，" + messageObj.thirdName + "<br>应到人数：" + messageObj.totalCount + " 实到人数：" + messageObj.alreadyCount + " 未到人数：<span class='color'>" + messageObj.notCount + "</span>" ;
		if(typeof(noticeObj.sort)=='undefined'||typeof(noticeObj.time)=='undefined'){
			mycheckpop.innerHTML = noticeObj.name + "同学，你已打卡！";
		}else if(noticeObj.islate==1){
			mycheckpop.innerHTML = "<span class='color'>" + noticeObj.name + "同学，你迟到了</span><br/>打卡时间：" + noticeObj.time ;
		}else{
			mycheckpop.innerHTML = noticeObj.name + "同学，你今天是第" + noticeObj.sort + "到达学校的，下次继续加油哦！";
		}
		popanimate(mycheckpop.parentNode);
	}
	
	function myFadeOut(obj,speed){ // 移动淡出动画
		var opa=10;
		obj.fadeOut = setInterval(function(){
			opa--;
			obj.style.opacity = opa/10;
			if(opa==0){
				clearInterval(obj.fadeOut);
				obj.style.display = "none";
				obj.parentNode.removeChild(obj);
			}
		},speed)
	}
	
	function popanimate(obj){ //弹出层动画效果
		obj.style.opacity = 1;
		obj.style.display = "block";
		obj.previousSibling.style.display = 'block';
		obj.style.marginLeft = -mycheckpop.offsetWidth/2 + "px";
		obj.style.marginRight = -mycheckpop.offsetHeight/2 +"px";
		setTimeout(function(){
			myFadeOut(obj,40);
		},2000)
	}
	
}

/**
 * 后台调用该函数，操控通知在前台页面的播放与停止
 * @parame String jsonData
 */
var noticeInstances;
function servermessage(jsonData){
    if(jsonData!='STOP'){
        noticeInstances =  new NoticeComp(jsonData);
        if(!noticeInstances){
            if (isAndroid()) {
                window.AndroidMethods.jsMessageSendFailed();
            } else {
                return '0';
            }
        }
        if (isAndroid()) {
        	window.AndroidMethods.jsMessageSendOK();
            //top.location.href = "message:SendOK";
        } else {
            return '1';
        }
    }else{
        if(noticeInstances) 
            noticeInstances.destroy();
        if (isAndroid()) {
        	window.AndroidMethods.jsMessageStopOK();
            //top.location.href = "message:StopOK";
        } else {
            return '1';
        }
    }
}

var platformChecked = false;
var isAndroidPlatform = false;
function isAndroid(){
    if (!platformChecked)
    {
        isAndroidPlatform = (navigator.userAgent.indexOf("Android") > -1);
        platformChecked = true;
    }

    return isAndroidPlatform;
}

/**
 * 启动Android视频
 */
function androidPlayVideo() {
	if (isAndroid()) {
		var iframes = document.getElementsByTagName("iframe");
		if (iframes != null && iframes.length > 0) {
			for (var i = 0, ilen = iframes.length; i < ilen; i++) {
				var videos = iframes[i].contentDocument.getElementsByTagName("video");
				if (videos != null && videos.length > 0 && videos.length < 2) {
					for (var j = 0, jlen = videos.length; j < jlen; j++) {
						if (videos[j] && videos[j].playState != 3) {
							videos[j].play();
						}
					}
				}
			}
		}
	}
}
function androidStopVideos() {
	if (isAndroid()) {
		var iframes = document.getElementsByTagName("iframe");
		if (iframes != null && iframes.length > 0) {
			for (var i = 0, ilen = iframes.length; i < ilen; i++) {
				var videos = iframes[i].contentDocument.getElementsByTagName("video");
				if (videos != null && videos.length > 0) {
					for (var j = videos.length - 1; j >= 0; j--) {
						if (videos[j]) {
							videos[j].currentTime = 0;
							videos[j].pause();
						}
					}
				}
			}
		}
	}
}

function nativeWebAccess(url, postData, callback, caller) {
    if (isAndroid()) {
        window.AndroidMethods.proxyWebAccess(url, postData, callback, caller);
    }
}

function webAccessCallback(caller, result, data) {
    if (isAndroid()) {
        var iframes = document.getElementsByTagName("iframe");
        if (iframes != null && iframes.length > 0) {
            for (var i = 0, ilen = iframes.length; i < ilen; i++) {
                iframes[i].contentWindow.webAccessCallback(caller, result, data);
            }
        }
    }
}


