$(function(){
//	var scrollFunc=function(e){
//		var direct=0;
//		e=e || window.event;
//	}
//	if(document.attachEvent){
//		document.attachEvent('onmousewheel',scrollFunc);
//
//	}
//	if(document.addEventListener){
//		document.addEventListener('DOMMouseScroll',scrollFunc,false);
//	}
//	window.onmousewheel=document.onmousewheel=scrollFunc
var docwidth=document.documentElement.clientWidth;
var docheight=parseInt(document.documentElement.clientHeight);
var docheightbody=document.body.offsetHeight;
//自适应屏幕高度 70是头部导航高度26是底部高度47是操作按钮部分高度（返回，编辑，删除等）117是系统设置界面保存按钮部分高度647为界面最小高度
if(docwidth>1200){
	if(docheight>680){
	    if(screen.height>768){
	    	//巡课界面iframe
			$(".iframe").css({"height":(docheight-70-47-15)+"px"});
			//巡课左侧树
			var xkright=$(".public_right").height()-47;
			$("#otp_vedeoabout_rvideo").css({"height":xkright+"px"})
			$(".obj").height(docheight-70-57);
	    $(".directview").attr("height",(docheight-70-57));
	    }else{
	    	//巡课界面iframe
			$(".iframe").css({"height":"700px"});
			//巡课左侧树
			$("#otp_vedeoabout_rvideo").css({"height":"715px"})	
			$(".obj").height(768);
	    	$(".directview").attr("height","768");
	    }
	}else{
		if(screen.height>768){
	    	if(docheight>789){
	    		//巡课界面iframe
				$(".iframe").css({"height":(docheight-70-47-15)+"px"});
				//巡课左侧树
				var xkright=$(".public_right").height()-47;
				$("#otp_vedeoabout_rvideo").css({"height":xkright+"px"})
	    	}else{
	    		//巡课界面iframe
				$(".iframe").css({"height":"700px"});
				//巡课左侧树
				$("#otp_vedeoabout_rvideo").css({"height":"715px"})	
	    	}
	    }else{
	    	//巡课界面iframe
			$(".iframe").css({"height":"700px"});
			//巡课左侧树
			$("#otp_vedeoabout_rvideo").css({"height":"715px"})	
			$(".obj").height(768);
		    $(".directview").attr("height","768");
	    }
	}
	if(docheight>799){
		$(".obj").height(docheight-70-57-4);
   		$(".directview").attr("height",(docheight-70-57-4));
		//录像计划
		$("#vipt_video,.vipt_video_content").height(docheight-70-57-1);
		//设备管理
		$(".mm_right,.mm_left").height(docheight-70-57-1);
		$("#mm_right_video,#mm_nogroup_video,#mm_nas_video").height(docheight-70-57-1);
		//mm_right_video
		//资源管理
		var height=$(".table_recycle tr").length*45;
		if(height>(docheight-70)){
				$(".res_center").height(height+49+15);
				$(".res").height(height+49+15+57-1);
			}else{
				$(".res").css("height",(docheight-70-1)+"px")
			}
		//用户管理
		$(".user_center").height(docheight-70-1);
		//系统设置
		$(".set").height(docheight-70-1);
		$(".tabs,.tab_content").height(docheight-70-117-57-2);
	}else{
		//录像计划
		$("#vipt_video,.vipt_video_content").height(647);
			//设备管理
			$(".mm_right,.mm_left").height(647);
			$("#mm_right_video,#mm_nogroup_video,#mm_nas_video").height(647);
			//资源管理
			var height=$(".table_recycle tr").length*45;
			$(".res_center").height(height+49+15);
			$(".res").height(height+49+15+57);
			$(".res").css("min-height","704px")
			//用户管理
			$(".user_center").height(docheight-70);
		}
}else{
	if(docheight>680){
		$(".obj").height(docheight-70-57);
	    $(".directview").attr("height",(docheight-70-57));
	    if(screen.height>768){
	    	//巡课界面iframe
			$(".iframe").css({"height":(docheight-70-47-15)+"px"});
			//巡课左侧树
			var xkright=$(".public_right").height()-47;
			$("#otp_vedeoabout_rvideo").css({"height":xkright+"px"})
	    }else{
	    	//巡课界面iframe
			$(".iframe").css({"height":"700px"});
			//巡课左侧树
			$("#otp_vedeoabout_rvideo").css({"height":"715px"})	
	    }
	}else{
		$(".obj").css("height","526px")
		if(screen.height>768){
    	if(docheight>789){
    		//巡课界面iframe
			$(".iframe").css({"height":(docheight-70-47-15)+"px"});
			//巡课左侧树
			var xkright=$(".public_right").height()-47;
			$("#otp_vedeoabout_rvideo").css({"height":xkright+"px"})
    	}else{
    		//巡课界面iframe
			$(".iframe").css({"height":"700px"});
			//巡课左侧树
			$("#otp_vedeoabout_rvideo").css({"height":"715px"})	
    	}
    }else{
    	//巡课界面iframe
		$(".iframe").css({"height":"700px"});
		//巡课左侧树
		$("#otp_vedeoabout_rvideo").css({"height":"715px"})	
    	}
	}
	if(docheight>799){
		//录像计划
		$("#vipt_video,.vipt_video_content").height(docheight-70-57-1);
		//设备管理
		$(".mm_right,.mm_left").height(docheight-70-57-1);
		$("#mm_right_video,#mm_nogroup_video,#mm_nas_video").height(docheight-70-57-1);
		//mm_right_video
		//资源管理
		var height=$(".table_recycle tr").length*45;
		if(height>(docheight-70)){
				$(".res_center").height(height+49+15);
				$(".res").height(height+49+15+57-1);
				
			}else{
				$(".res").css("height",(docheight-70-1)+"px")
			}
		//用户管理
		$(".user_center").height(docheight-70-1);
		//系统设置
		$(".set").height(docheight-70-1);
		$(".tabs,.tab_content").height(docheight-70-117-57-2);
	}else{
		//录像计划
		$("#vipt_video,.vipt_video_content").height(647);
		//设备管理
		$(".mm_right,.mm_left").height(647);
		$("#mm_right_video,#mm_nogroup_video,#mm_nas_video").height(647);
		//资源管理
		var height=$(".table_recycle tr").length*45;
		$(".res_center").height(height+49+15);
		$(".res").height(height+49+15+57);
		$(".res").css("min-height","704px")
		//用户管理
		$(".user_center").height(docheight-70);
		}
}

$(window).resize(function(){
//自适应屏幕高度 70是头部导航高度26是底部高度47是操作按钮部分高度（返回，编辑，删除等）117是系统设置界面保存按钮部分高度647为界面最小高度
var docwidth=document.documentElement.clientWidth;
var docheight=parseInt(document.documentElement.clientHeight);
var docheightbody=document.body.offsetHeight;
//alert(docheight)
if(docwidth>1200){
	if(docheight>680){
	//导播界面
	$(".obj").height(docheight-70-57);
    $(".directview").attr("height",(docheight-70-57));
    if(screen.height>768){//1366*768分辨率下调试
    	if(docheight>789){
    		//巡课界面iframe
			$(".iframe").css({"height":(docheight-70-47-15)+"px"});
			//巡课左侧树
			var xkright=$(".public_right").height()-47;
			$("#otp_vedeoabout_rvideo").css({"height":xkright+"px"})
    	}else{
    		//巡课界面iframe
			$(".iframe").css({"height":"700px"});
			//巡课左侧树
			$("#otp_vedeoabout_rvideo").css({"height":"715px"})	
			if(docwidth>(screen.width-25)&&screen.height>768&&screen.width<1600){
				location.href=location.href;
			}
    	}
    }else{
    	//巡课界面iframe
		$(".iframe").css({"height":"700px"});
		//巡课左侧树
		$("#otp_vedeoabout_rvideo").css({"height":"715px"})	
   		}
	}else{
		$(".obj").css("height","526px")
		if(screen.height>768){
    	if(docheight>789){
    		//巡课界面iframe
			$(".iframe").css({"height":(docheight-70-47-15)+"px"});
			//巡课左侧树
			var xkright=$(".public_right").height()-47;
			$("#otp_vedeoabout_rvideo").css({"height":xkright+"px"})
    	}else{
    		//巡课界面iframe
			$(".iframe").css({"height":"700px"});
			//巡课左侧树
			$("#otp_vedeoabout_rvideo").css({"height":"715px"})	
			if(docwidth>(screen.width-25)&&screen.height>768&&screen.width<1600){
				location.href=location.href;
			}else if(screen.width>1600 && (docheight>677 && docheight<746)){
				//巡课界面iframe
				$(".iframe").css({"height":"700px"});
				//巡课左侧树
				$("#otp_vedeoabout_rvideo").css({"height":"715px"})
			}
    	}
    }else{
    	//巡课界面iframe
		$(".iframe").css({"height":"700px"});
		//巡课左侧树
		$("#otp_vedeoabout_rvideo").css({"height":"715px"})
   	 	}
	}
	if(docheight>799){
		//录像计划
		$("#vipt_video,.vipt_video_content").height(docheight-70-57-1);
		//设备管理
		$(".mm_right,.mm_left").height(docheight-70-57-1);
		$("#mm_right_video,#mm_nogroup_video,#mm_nas_video").height(docheight-70-57-1);
		//mm_right_video
		//资源管理
		var height=$(".table_recycle tr").length*45;
		if(height>(docheight-70)){
				$(".res_center").height(height+49+15);
				$(".res").height(height+49+15+57-1);
				
			}else{
				$(".res").css("height",(docheight-70-1)+"px")
			}
		//用户管理
		$(".user_center").height(docheight-70-1);
		//系统设置
		$(".set").height(docheight-70-1);
		$(".tabs,.tab_content").height(docheight-70-117-57-2);
	}else{
		//录像计划
		$("#vipt_video,.vipt_video_content").height(647);
		//设备管理
		$(".mm_right,.mm_left").height(647);
		$("#mm_right_video,#mm_nogroup_video,#mm_nas_video").height(647);
		//资源管理
		var height=$(".table_recycle tr").length*45;
		$(".res_center").height(height+49+15);
		$(".res").height(height+49+15+57);
		$(".res").css("min-height","704px")
		//用户管理
		$(".user_center").height(docheight-70);
		}
}else{
	if(docheight>680){
	//导播界面
	$(".obj").height(docheight-70-57);
    $(".directview").attr("height",(docheight-70-57));
    if(screen.height>768){
    	if(docheight>789){
    		//巡课界面iframe
			$(".iframe").css({"height":(docheight-70-47-15)+"px"});
			//巡课左侧树
			var xkright=$(".public_right").height()-47;
			$("#otp_vedeoabout_rvideo").css({"height":xkright+"px"})
    	}else{
    		//巡课界面iframe
			$(".iframe").css({"height":"700px"});
			//巡课左侧树
			$("#otp_vedeoabout_rvideo").css({"height":"715px"})
			if(docwidth>(screen.width-25)&&screen.height>768&&screen.width<1600){
				location.href=location.href;
			}else if(screen.width>1600 && (docheight>677 && docheight<746)){
				//alert($(".iframe").height()+"----"+$("#otp_vedeoabout_rvideo"))
				//巡课界面iframe
				$(".iframe").css({"height":"700px"});
				//巡课左侧树
				$("#otp_vedeoabout_rvideo").css({"height":"715px"})
			}
    	}
    }else{
    	//巡课界面iframe
		$(".iframe").css({"height":"700px"});
		//巡课左侧树
		$("#otp_vedeoabout_rvideo").css({"height":"715px"})	
    	}
	}else{
		$(".obj").css("height","526px")
		if(screen.height>768){
    	if(docheight>789){
    		//巡课界面iframe
			$(".iframe").css({"height":(docheight-70-47-15)+"px"});
			//巡课左侧树
			var xkright=$(".public_right").height()-47;
			$("#otp_vedeoabout_rvideo").css({"height":xkright+"px"})
    	}else{
    		//巡课界面iframe
			$(".iframe").css({"height":"700px"});
			//巡课左侧树
			$("#otp_vedeoabout_rvideo").css({"height":"715px"})	
			if(docwidth>(screen.width-25)&&screen.height>768&&screen.width<1600){
				location.href=location.href;
			}else if(screen.width>1600 && (docheight>677 && docheight<746)){
				//巡课界面iframe
				$(".iframe").css({"height":"700px"});
				//巡课左侧树
				$("#otp_vedeoabout_rvideo").css({"height":"715px"})
			}
    	}
    }else{
    	//巡课界面iframe
		$(".iframe").css({"height":"700px"});
	//巡课左侧树
		$("#otp_vedeoabout_rvideo").css({"height":"715px"})	
    }
	}
	if(docheight>799){
	//录像计划
		$("#vipt_video,.vipt_video_content").height(docheight-70-57-1);
	//设备管理
		$(".mm_right,.mm_left").height(docheight-70-57-1);
		$("#mm_right_video,#mm_nogroup_video,#mm_nas_video").height(docheight-70-57-1);
		//mm_right_video
	//资源管理
		var height=$(".table_recycle tr").length*45;
		if(height>(docheight-70)){
				$(".res_center").height(height+49+15);
				$(".res").height(height+49+15+57-1);
				
			}else{
				$(".res").css("height",(docheight-70-1)+"px")
			}
	//用户管理
			$(".user_center").height(docheight-70-1);
		//系统设置
		$(".set").height(docheight-70-1);
		$(".tabs,.tab_content").height(docheight-70-117-57-2);
	}else{
		//导播界面
		//$(".obj").css("height","768px")
		$(".obj").height(docheight-70-57-4);
    	$(".directview").attr("height",(docheight-70-57-4));
		//录像计划
		$("#vipt_video,.vipt_video_content").height(647);
		//设备管理
		$(".mm_right,.mm_left").height(647);
		$("#mm_right_video,#mm_nogroup_video,#mm_nas_video").height(647);
		//资源管理
		var height=$(".table_recycle tr").length*45;
		$(".res_center").height(height+49+15);
		$(".res").height(height+49+15+57);
		$(".res").css("min-height","704px")
		//用户管理
		$(".user_center").height(docheight-70);
	}
}
})


//搜索框高亮
$(".user_search").mouseover(function(){
	$(this).css("border","1px solid #28B779");
	$(this).find(".user_search_input").focus();
}).mouseout(function(){
	$(this).css("border","1px solid #dbdbdb");
	$(this).find(".user_search_input").blur();
})
//登录框高亮
$(".login_input").mouseover(function(){
	$(this).css("border","1px solid #28B779");
	$(this).focus();
}).mouseout(function(){
	$(this).css("border","1px solid #dbdbdb");
})

//录像计划，如果课时数超过八节课，则滚动
var viptlen=$(".vipt_node").length;
var vipthlen=$(".vipt_tablerecycle").length;
var viptwidth=viptlen*125+180+17+"px";
if(viptlen>8){
	$(".vipt_tablerecycle").css("width",viptwidth);
	$(".vipt_video_content").width(viptwidth);
	if(vipthlen>=9){
		$(".vipt_video_content").height(vipthlen*61);
	}
}else{
	$(".vipt_tablerecycle").css("width","1200px");
	$(".vipt_video_content").width(viptwidth);
	if(vipthlen>=9){
		$(".vipt_video_content").height(vipthlen*61);
	}
}
})
