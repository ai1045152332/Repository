/**
 * Created with JetBrains WebStorm.
 * User: hht
 * Date: 14-2-11
 * Time: 下午4:55
 * To change this template use File | Settings | File Templates.
 */

(function() {
    window.DSS = window.DSS || parent.DSS;
    window.serverDomain = parent.PB.Options.serverDomain;
    window.serverClassid = parent.PB.Options.serverClassid;

    var showBox = document.getElementsByClassName('container')[0];
    window.type_class_map = {'scroll_acd_ul':scrollImgs_accordion,'scrollImgs_swipe':scrollImgs_swipe,
        'scroll_wander_box':scrollImgs_wander,'scroll_3D_box':scrollImgs_3D,'editItem_eWork': ExcellentWorks,'editItem5':scrollText,'editItem_time1':GetCurrentTime,'editItem_weather1':GetWeather,'editItem_countdown':GetTimer,'courseList_wrap':Curriculum,'editItem25':scrollText2,'editItem_rect':rectComp,'editItem_time27':classComp,'editItem_time28':noticeComp};
    window.id_obj_map = {};

    //根据 type_class_map，搜索相应的需要加载js的模块，将加载的模块js存放在id_obj_map里
    for(var i=0;i<showBox.children.length;i++){
        var ch = showBox.children[i].children[0];
        var comp = window.type_class_map[ch.className];
        var params = showBox.children[i].getAttribute('params');
        params = params?JSON.parse(params):null;
        if(comp){
            var obj = new comp(ch,params);
            obj.blk_id = showBox.children[i].id;
            obj.init();
            window.id_obj_map[obj.blk_id] = obj;
        }
    }
    
    setTimeout(function(){
        var container = document.getElementsByClassName('container')[0];
    	var videos = container.getElementsByTagName("video");
    	for(var i=0;i<videos.length;i++){
    		 videos[i].play();
        }
    }, 5000);
    
    DSS.util.events(window,'touchmove',function(e){
        if(e.stopPropagation)e.stopPropagation();
        if(e.preventDefault)e.preventDefault();
    });

    //iframe禁止浏览器默认的滑动事件
    var childFrames = document.getElementsByTagName("iframe");
    if (childFrames && childFrames.length > 0) {
        for (var i = 0; i < childFrames.length; i++) {
            DSS.util.events(childFrames[i].contentWindow,'touchmove',function(e){
                if(e.stopPropagation)e.stopPropagation();
                if(e.preventDefault)e.preventDefault();
            });
        }
    }
})();

function destroyPage()
{
    // 清除二级页面相关资源
    document.onclick = null;
    document.ontouchstart = null;

    if (AUTO_RETURN_TIMER != null) {
        clearTimeout(AUTO_RETURN_TIMER);
        AUTO_RETURN_TIMER = null;
    }

    subPageReturn();

    // 清除事件绑定
    var childFrames = document.getElementsByTagName("iframe");
    if (childFrames && childFrames.length > 0) {
        for (var i = 0; i < childFrames.length; i++) {
        	if(childFrames[i].className != 'WEBPAGE_COMP_src'){
        		 DSS.util.delEvent(childFrames[i].contentWindow,'touchmove');
        	}
        }
    }

    DSS.util.delEvent(window,'touchmove');

    // 清除页面对象
    for (var obj_id in window.id_obj_map) {
        if (window.id_obj_map[obj_id].destroy) {
            window.id_obj_map[obj_id].destroy();
        }
        window.id_obj_map[obj_id] = null;
    }
}

function updateProject(prjId,pageId,blkId,content)
{
	  var mySwipe = null;
	//  var json = eval("(" + params + ")");
	  window.id_obj_map[blkId].destroy();
	  var blkElem = document.getElementById(blkId);
	  blkElem.innerHTML=content;
	  mySwipe =new  window.type_class_map[blkElem.children[0].className](blkElem.children[0]);
	  mySwipe.init();
	  window.id_obj_map[blkId]=mySwipe;
	  return "true";
}

//二级页面自动返回
var RETURN_INTERVAL = 30;//s
var AUTO_RETURN_TIMER;
var COURSE_POP;
var WORK_POP;
var IMGSLIDE_POP;
document.onclick = docClick;
document.ontouchstart = docClick;
function docClick(e){
	if (RETURN_INTERVAL > 0) {
		if (AUTO_RETURN_TIMER != null) {
			clearTimeout(AUTO_RETURN_TIMER);
		}
		AUTO_RETURN_TIMER = setTimeout("subPageReturn()", RETURN_INTERVAL * 1000);
	}
}
function subPageReturn() {
	var _sign = getStopSignValue();
	if (_sign != undefined) {
        setStopSignValue('0');
	}

	if (COURSE_POP != null) {
		COURSE_POP.hideDetailPage();
	}
	if (WORK_POP != null) {
        WORK_POP.hideDetail();
	}
	if (IMGSLIDE_POP != null) {
        IMGSLIDE_POP.destroy();
	}
}
function getStopSignValue() {
    return parent.DSS.util.byId('stopsign').value;
}
function setStopSignValue(value) {
    parent.DSS.util.byId('stopsign').value = value;
}

function isAndroid() {
    return parent.isAndroid();
}

function nativeWebAccess(url, postData, callback, caller) {
    parent.nativeWebAccess(url, postData, callback, caller);
}

function webAccessCallback(caller, result, data) {
    var obj = window.id_obj_map[caller];
    if (obj && obj.onWebAccessResult) {
        obj.onWebAccessResult(result, data);
    }
}
