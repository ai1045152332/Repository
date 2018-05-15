$(function(){
	
	var root_path = $("#root_path").val();
	if($.cookie("screen")==undefined || $.cookie("camera")==undefined){
		//默认选择四屏，教师学生
    	$(".xk_fournine div").eq(0).attr("class","xk_four_selected");
    	$(".xk_fournine div").eq(0).css({"border":"1px solid #bbb","border-radius":" 3px 0 0 3px"});
		$(".xk_stage div").eq(0).attr("class","xk_stage_st_selected");

		
//		$(".xk_stage div").eq(0).css({"border":"1px solid #bbb","border-left":"none","border-radius":" 0px 3px 3px 0px"});
	}else if($.cookie("screen")=="2"){
		$(".xk_fournine div").eq(0).attr("class","xk_four_selected");
		$(".xk_fournine div").eq(1).hide()
	}else if($.cookie("screen")=="3"){
		$(".xk_fournine div").eq(1).attr("class","xk_nine_selected");
		$(".xk_fournine div").eq(0).hide()
	}
	
	//alert($.cookie("camera")+"----"+$.cookie("screen"))
	if($.cookie("camera")=="教师学生"){
		$(".xk_stage div").eq(0).attr("class","xk_stage_st_selected");
//		$(".xk_stage div").eq(0).css({"border":"1px solid #bbb","border-left":"none","border-radius":" 0px 3px 3px 0px"});
//		$(".xk_stage div").eq(1).hide()
//		$(".xk_stage div").eq(2).hide()
//		$(".xk_stage div").eq(3).hide()
//		$(".xk_stage div").eq(4).hide()
	}else if($.cookie("camera")=="学生全景"){
		$(".xk_stage div").eq(1).attr("class","xk_stage_student_selected");
//		$(".xk_stage div").eq(1).css({"border":"1px solid #bbb","border-left":"none","border-radius":" 0px 3px 3px 0px"});
//		$(".xk_stage div").eq(0).hide()
//		$(".xk_stage div").eq(2).hide()
//		$(".xk_stage div").eq(3).hide()
//		$(".xk_stage div").eq(4).hide()
	}else if($.cookie("camera")=="教师全景"){
		$(".xk_stage div").eq(2).attr("class","xk_stage_teacher_selected");
//		$(".xk_stage div").eq(2).css({"border":"1px solid #bbb","border-left":"none","border-radius":" 0px 3px 3px 0px"});
//		$(".xk_stage div").eq(0).hide()
//		$(".xk_stage div").eq(1).hide()
//		$(".xk_stage div").eq(3).hide()
//		$(".xk_stage div").eq(4).hide()
	}else if($.cookie("camera")=="学生特写"){
		$(".xk_stage div").eq(3).attr("class","xk_stage_studentunique_selected");
//		$(".xk_stage div").eq(3).css({"border":"1px solid #bbb","border-left":"none","border-radius":" 0px 3px 3px 0px"});
//		$(".xk_stage div").eq(0).hide()
//		$(".xk_stage div").eq(1).hide()
//		$(".xk_stage div").eq(2).hide()
//		$(".xk_stage div").eq(4).hide()
	}else if($.cookie("camera")=="教师特写"){
		$(".xk_stage div").eq(4).attr("class","xk_stage_teacherunique_selected");
//		$(".xk_stage div").eq(4).css({"border":"1px solid #bbb","border-left":"none","border-radius":" 0px 3px 3px 0px"});
//		$(".xk_stage div").eq(0).hide()
//		$(".xk_stage div").eq(1).hide()
//		$(".xk_stage div").eq(2).hide()
//		$(".xk_stage div").eq(3).hide()
	}
	
});
function change_camera(camera,hostIds)
{
	//alert(camera+":camera")

	var viewClassCameraName = $.cookie("camera");
	//alert(viewClassCameraName)
	var groupid=$("#group_id").val();
	// var pagesize = $.cookie("screenrecord");
	var pagesize = $.cookie("screen");
    var camera_new = camera;
	if(viewClassCameraName != camera)
	{
		$(".xk_stage div").eq(0).attr("class","xk_stage_st");
		$(".xk_stage div").eq(1).attr("class","xk_stage_student");
		$(".xk_stage div").eq(2).attr("class","xk_stage_teacher");
		$(".xk_stage div").eq(3).attr("class","xk_stage_studentunique");
		$(".xk_stage div").eq(4).attr("class","xk_stage_teacherunique");

		if(pagesize == 2)
		{
            videoShow(camera_new,hostIds);
			// $("#go").attr("src",root_path+'/viewclass/Viewclass_viewClass.do?groupid='+groupid+'&pagesize='+pagesize+'&currentPage=1&viewClassCameraName='+camera_new);
		}
		else
		{
            videoShow(camera_new,hostIds);
			// $("#go").attr("src",root_path+'/viewclass/Viewclass_viewClass.do?groupid='+groupid+'&pagesize='+pagesize+'&currentPage=1&viewClassCameraName='+camera_new);
		}
		$("#selected_host").val("");
		$("#selected_host").attr("ip","");
		$("#turnon").attr("onclick","wakeup()");
		$("#turnoff").attr("onclick","shutdown()");
		$("#turnon_img").removeClass("xk_options_openicon_disable").addClass("xk_options_openicon");
		$("#turnoff_img").removeClass("xk_options_closeicon_disable").addClass("xk_options_closeicon");
		$("#record_vedio").attr("onclick", "recording()");
		$("#record_vedio_img").removeClass("xk_options_recordingicon_disable").addClass("xk_options_recordingicon");
		$("#stop_record_vedio").attr("onclick", "stoprecord()");
		$("#stop_record_vedio_img").removeClass("xk_options_stoprecordingicon_disable").addClass("xk_options_stoprecordingicon");
		$.cookie('camera', camera, {path: '/'});
		switch($.cookie("camera")){
            case "教师学生":
                $(".xk_stage div").eq(0).attr("class","xk_stage_st_selected");
//              $(".xk_stage div").eq(0).css({"margin-left":"0","border":"1px solid #bbb","border-left":"none","border-radius":"0 3px 3px 0"})
//              $(".xk_stage div").eq(1).css({"display":"none","margin-left":"-72px","border":"1px solid #bbb","border-left":"none","border-radius":"0"});
//              $(".xk_stage div").eq(2).css({"display":"none","margin-left":"-108px","border":"1px solid #bbb","border-left":"none","border-radius":"0"});
//              $(".xk_stage div").eq(3).css({"display":"none","margin-left":"-144px","border":"1px solid #bbb","border-left":"none","border-radius":"0"});
//              $(".xk_stage div").eq(4).css({"display":"none","margin-left":"-180px","border":"1px solid #bbb","border-radius":"3px 0 0 3px"});
                break;
            case "学生全景":
                $(".xk_stage div").eq(1).attr("class","xk_stage_student_selected");
//              $(".xk_stage div").eq(1).css({"margin-left":"0","border":"1px solid #bbb","border-left":"none","border-radius":"0 3px 3px 0"})
//              $(".xk_stage div").eq(0).css({"display":"none","margin-left":"-36px","border":"1px solid #bbb","border-left":"none","border-radius":"0"});
//              $(".xk_stage div").eq(2).css({"display":"none","margin-left":"-108px","border":"1px solid #bbb","border-left":"none","border-radius":"0"});
//              $(".xk_stage div").eq(3).css({"display":"none","margin-left":"-144px","border":"1px solid #bbb","border-left":"none","border-radius":"0"});
//              $(".xk_stage div").eq(4).css({"display":"none","margin-left":"-180px","border":"1px solid #bbb","border-radius":"3px 0 0 3px"});

                break;
            case "教师全景":
                $(".xk_stage div").eq(2).attr("class","xk_stage_teacher_selected");
//              $(".xk_stage div").eq(2).css({"margin-left":"0","border":"1px solid #bbb","border-left":"none","border-radius":"0 3px 3px 0"})
//              $(".xk_stage div").eq(0).css({"display":"none","margin-left":"-36px","border":"1px solid #bbb","border-left":"none","border-radius":"0"});
//              $(".xk_stage div").eq(1).css({"display":"none","margin-left":"-72px","border":"1px solid #bbb","border-left":"none","border-radius":"0"});
//              $(".xk_stage div").eq(3).css({"display":"none","margin-left":"-144px","border":"1px solid #bbb","border-left":"none","border-radius":"0"});
//              $(".xk_stage div").eq(4).css({"display":"none","margin-left":"-180px","border":"1px solid #bbb","border-radius":"3px 0 0 3px"});

                break;
            case "学生特写":
                $(".xk_stage div").eq(3).attr("class","xk_stage_studentunique_selected");
//              $(".xk_stage div").eq(3).css({"margin-left":"0","border":"1px solid #bbb","border-left":"none","border-radius":"0 3px 3px 0"})
//              $(".xk_stage div").eq(0).css({"display":"none","margin-left":"-36px","border":"1px solid #bbb","border-left":"none","border-radius":"0"});
//              $(".xk_stage div").eq(1).css({"display":"none","margin-left":"-72px","border":"1px solid #bbb","border-left":"none","border-radius":"0"});
//              $(".xk_stage div").eq(2).css({"display":"none","margin-left":"-108px","border":"1px solid #bbb","border-left":"none","border-radius":"0"});
//              $(".xk_stage div").eq(4).css({"display":"none","margin-left":"-180px","border":"1px solid #bbb","border-radius":"3px 0 0 3px"});
                break;
            case "教师特写":
                $(".xk_stage div").eq(4).attr("class","xk_stage_teacherunique_selected");
//              $(".xk_stage div").eq(4).css({"margin-left":"0","border":"1px solid #bbb","border-left":"none","border-radius":"0 3px 3px 0"})
//              $(".xk_stage div").eq(0).css({"display":"none","margin-left":"-36px","border":"1px solid #bbb","border-left":"none","border-radius":"0"});
//              $(".xk_stage div").eq(1).css({"display":"none","margin-left":"-72px","border":"1px solid #bbb","border-left":"none","border-radius":"0"});
//              $(".xk_stage div").eq(2).css({"display":"none","margin-left":"-108px","border":"1px solid #bbb","border-left":"none","border-radius":"0"});
//              $(".xk_stage div").eq(3).css({"display":"none","margin-left":"-144px","border":"1px solid #bbb","border-radius":"3px 0 0 3px"});
                break;
        }
	}else{
		switch($.cookie("camera")){
			case "教师学生":
				$(".xk_stage div").eq(0).attr("class","xk_stage_st_selected");
//				$(".xk_stage div").eq(0).css("margin-left","0")
//				$(".xk_stage div").eq(1).css({"display":"block","margin-left":"-72px","border":"1px solid #bbb","border-left":"none","border-radius":"0"});
//				$(".xk_stage div").eq(2).css({"display":"block","margin-left":"-108px","border":"1px solid #bbb","border-left":"none","border-radius":"0"});
//				$(".xk_stage div").eq(3).css({"display":"block","margin-left":"-144px","border":"1px solid #bbb","border-left":"none","border-radius":"0"});
//				$(".xk_stage div").eq(4).css({"display":"block","margin-left":"-180px","border":"1px solid #bbb","border-radius":"3px 0 0 3px"});
				//$(".xk_stage div").eq(1).css({"border":"1px solid #bbb","border-radius":" 3px 0 0 3px"});
				break;
			case "学生全景":
				$(".xk_stage div").eq(1).attr("class","xk_stage_student_selected");
//				$(".xk_stage div").eq(1).css("margin-left","0")
//				$(".xk_stage div").eq(0).css({"display":"block","margin-left":"-36px","border":"1px solid #bbb","border-left":"none","border-radius":"0"});
//				$(".xk_stage div").eq(2).css({"display":"block","margin-left":"-108px","border":"1px solid #bbb","border-left":"none","border-radius":"0"});
//				$(".xk_stage div").eq(3).css({"display":"block","margin-left":"-144px","border":"1px solid #bbb","border-left":"none","border-radius":"0"});
//				$(".xk_stage div").eq(4).css({"display":"block","margin-left":"-180px","border":"1px solid #bbb","border-radius":"3px 0 0 3px"});
				break;
			case "教师全景":
				$(".xk_stage div").eq(2).attr("class","xk_stage_teacher_selected");
//				$(".xk_stage div").eq(2).css("margin-left","0")
//				$(".xk_stage div").eq(0).css({"display":"block","margin-left":"-36px","border":"1px solid #bbb","border-left":"none","border-radius":"0"});
//				$(".xk_stage div").eq(1).css({"display":"block","margin-left":"-72px","border":"1px solid #bbb","border-left":"none","border-radius":"0"});
//				$(".xk_stage div").eq(3).css({"display":"block","margin-left":"-144px","border":"1px solid #bbb","border-left":"none","border-radius":"0"});
//				$(".xk_stage div").eq(4).css({"display":"block","margin-left":"-180px","border":"1px solid #bbb","border-radius":"3px 0 0 3px"});
				break;
			case "学生特写":	
//				$(".xk_stage div").eq(0).css({"display":"block","margin-left":"-36px","border":"1px solid #bbb","border-left":"none","border-radius":"0"});
//				$(".xk_stage div").eq(1).css({"display":"block","margin-left":"-72px","border":"1px solid #bbb","border-left":"none","border-radius":"0"});
//				$(".xk_stage div").eq(2).css({"display":"block","margin-left":"-108px","border":"1px solid #bbb","border-left":"none","border-radius":"0"});
				$(".xk_stage div").eq(3).attr("class","xk_stage_studentunique_selected");
//				$(".xk_stage div").eq(3).css("margin-left","0")
//				$(".xk_stage div").eq(4).css({"display":"block","margin-left":"-180px","border":"1px solid #bbb","border-radius":"3px 0 0 3px"});
				break;
			case "教师特写":
//				$(".xk_stage div").eq(0).css({"display":"block","margin-left":"-36px","border":"1px solid #bbb","border-left":"none","border-radius":"0"});
//				$(".xk_stage div").eq(1).css({"display":"block","margin-left":"-72px","border":"1px solid #bbb","border-left":"none","border-radius":"0"});
//				$(".xk_stage div").eq(2).css({"display":"block","margin-left":"-108px","border":"1px solid #bbb","border-left":"none","border-radius":"0"});
//				$(".xk_stage div").eq(3).css({"display":"block","margin-left":"-144px","border":"1px solid #bbb","border-radius":"3px 0 0 3px"});
				$(".xk_stage div").eq(4).attr("class","xk_stage_teacherunique_selected");
//				$(".xk_stage div").eq(4).css("margin-left","0")
				break;
		}
	}
}
// function change_screen(screen)
// {
// 	nowselectpage = 1;
// 	var viewClassCameraName = $.cookie("camera");
// 	var groupid=$("#group_id").val();
// 	var pagesize = $.cookie("screenrecord");
//     viewClassCameraName = encodeURI(encodeURI(viewClassCameraName));
// 	if(pagesize != screen)
// 	{
// 		$(".xk_fournine div").eq(0).attr("class","xk_four");
// 		$(".xk_fournine div").eq(1).attr("class","xk_nine");
// 		if(screen == 2)
// 		{
// 			$("#go").attr("src","")
// 			$("#go").attr("src",root_path+'/viewclass/Viewclass_viewClass4.do?groupid='+groupid+'&pagesize='+screen+'&currentPage=1&viewClassCameraName='+viewClassCameraName);
// 		}
// 		else
// 		{
// 			$("#go").attr("src","")
// 			$("#go").attr("src",root_path+'/viewclass/Viewclass_viewClass9.do?groupid='+groupid+'&pagesize='+screen+'&currentPage=1&viewClassCameraName='+viewClassCameraName);
// 		}
//
// 		$.cookie('screenrecord', screen, {path: '/'});
// 		switch($.cookie("screenrecord")){
// 			case "2":
// 				$(".xk_fournine div").eq(0).attr("class","xk_four_selected");
// 				$(".xk_fournine div").eq(1).hide()
// 				$(".xk_fournine div").eq(0).css({"border":"1px solid #bbb","border-radius":" 3px 0 0 3px"});
// 				break;
// 			case "3":
// 				$(".xk_fournine div").eq(1).attr("class","xk_nine_selected");
// 				$(".xk_fournine div").eq(1).css({"border":"1px solid #bbb","border-radius":" 3px 0 0 3px"});
// 				$(".xk_fournine div").eq(0).hide()
// 				break;
// 		}
// 		$("#selected_host").val("");
// 		$("#selected_host").attr("ip","");
// 		$("#turnon").attr("onclick","wakeup()");
// 		$("#turnoff").attr("onclick","shutdown()");
// 		$("#turnon_img").removeClass("xk_options_openicon_disable").addClass("xk_options_openicon");
// 		$("#turnoff_img").removeClass("xk_options_closeicon_disable").addClass("xk_options_closeicon");
// 		$("#record_vedio").attr("onclick", "recording()");
// 		$("#record_vedio_img").removeClass("xk_options_recordingicon_disable").addClass("xk_options_recordingicon");
// 		$("#stop_record_vedio").attr("onclick", "stoprecord()");
// 		$("#stop_record_vedio_img").removeClass("xk_options_stoprecordingicon_disable").addClass("xk_options_stoprecordingicon");
// 	}else{
// 		if(screen == 2)
// 		{
// 			$(".xk_fournine div").eq(1).css({"display":"block","float":"left"});
// 			$(".xk_fournine div").eq(1).css({"border":"1px solid #bbb","border-radius":" 3px 0 0 3px"});
// 			$(".xk_fournine div").eq(0).css({"border":"1px solid #bbb","border-left":"none","border-radius":" 0","float":"right"})
// 		}
// 		else
// 		{
// 			$(".xk_fournine div").eq(0).css({"display":"block","float":"left"});
// 			$(".xk_fournine div").eq(0).css({"border":"1px solid #bbb","border-radius":" 3px 0 0 3px"});
// 			$(".xk_fournine div").eq(1).css({"border":"1px solid #bbb","border-left":"none","border-radius":" 0","float":"right"})
// 		}
// 	}
// }
function change_screen(screen,hostIds)
{
    nowselectpage = 1;
    var pagesize = $.cookie("screen");
    $.cookie('curPage', 1, {path: '/'});
    if(pagesize != screen)
    {
        $(".xk_fournine div").eq(0).attr("class","xk_four");
        $(".xk_fournine div").eq(1).attr("class","xk_nine");

        $.cookie('screen', screen, {path: '/'});
        var viewClassCameraName = encodeURI(encodeURI($.cookie("camera")));
        videoShow(viewClassCameraName,hostIds);
        switch($.cookie("screen")){
            case "2":
                $(".xk_fournine div").eq(0).attr("class","xk_four_selected");
                $(".xk_fournine div").eq(1).hide()
                $(".xk_fournine div").eq(0).css({"border":"1px solid #bbb","border-radius":" 3px 0 0 3px"});
                break;
            case "3":
                $(".xk_fournine div").eq(1).attr("class","xk_nine_selected");
                $(".xk_fournine div").eq(1).css({"border":"1px solid #bbb","border-radius":" 3px 0 0 3px"});
                $(".xk_fournine div").eq(0).hide()
                break;
        }
        $("#selected_host").val("");
        $("#selected_host").attr("ip","");
        $("#turnon").attr("onclick","wakeup()");
        $("#turnoff").attr("onclick","shutdown()");
        $("#turnon_img").removeClass("xk_options_openicon_disable").addClass("xk_options_openicon");
        $("#turnoff_img").removeClass("xk_options_closeicon_disable").addClass("xk_options_closeicon");
        $("#record_vedio").attr("onclick", "recording()");
        $("#record_vedio_img").removeClass("xk_options_recordingicon_disable").addClass("xk_options_recordingicon");
        $("#stop_record_vedio").attr("onclick", "stoprecord()");
        $("#stop_record_vedio_img").removeClass("xk_options_stoprecordingicon_disable").addClass("xk_options_stoprecordingicon");
    }else{
        if(screen == 2)
        {
            $(".xk_fournine div").eq(1).css({"display":"block","float":"left"});
            $(".xk_fournine div").eq(1).css({"border":"1px solid #bbb","border-radius":" 3px 0 0 3px"});
            $(".xk_fournine div").eq(0).css({"border":"1px solid #bbb","border-left":"none","border-radius":" 0","float":"right"})
        }
        else
        {
            $(".xk_fournine div").eq(0).css({"display":"block","float":"left"});
            $(".xk_fournine div").eq(0).css({"border":"1px solid #bbb","border-radius":" 3px 0 0 3px"});
            $(".xk_fournine div").eq(1).css({"border":"1px solid #bbb","border-left":"none","border-radius":" 0","float":"right"})
        }
    }
}
