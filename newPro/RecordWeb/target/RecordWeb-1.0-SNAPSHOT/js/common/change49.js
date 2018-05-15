$(function(){
//遍历当前页面视频个数--四屏
$(".xk_video").click(function(){
	
	if($(this).attr("bingo")=="none"){
		$(this).attr("bingo","done");
		$(this).attr("class","xk_video_selected");
		$(this).find(".xk_video_selected_bz").css("display","block");
	}else if($(this).attr("bingo")=="done"){
		$(this).attr("bingo","none")
		$(this).attr("class","xk_video");
		$(this).find(".xk_video_selected_bz").css("display","none");
	}
})
//遍历当前页面视频个数--九屏
$(".xk_video_nine").click(function(){
	if($(this).attr("bingo")=="none"){
		$(this).attr("bingo","done");
		$(this).attr("class","xk_video_selected_nine");
		$(this).find(".xk_video_selected_bz").css("display","block");
	}else if($(this).attr("bingo")=="done"){
		$(this).attr("bingo","none")
		$(this).attr("class","xk_video_nine");
		$(this).find(".xk_video_selected_bz").css("display","none");
	}
})
//实现设备切换效果
$(".mm_nogroup_list").click(function(){
	if($(this).attr("bingo")=="none"){
		$(this).attr("bingo","done");
		$(this).attr("class","mm_nogroup_list_selected");
		$(this).find(".xk_video_selected_bz").css("display","block");
	}else if($(this).attr("bingo")=="done"){
		$(this).attr("bingo","none")
		$(this).attr("class","mm_nogroup_list");
		$(this).find(".xk_video_selected_bz").css("display","none");
	}
})
})

