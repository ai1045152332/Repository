//弹窗控制
$(document).on("click",".chice_div_li span",function(){
	var flag = $(this).hasClass("global_checkboxicon");
	if(flag){
		$(this).removeClass("global_checkboxicon");
	}else{
		$(this).addClass("global_checkboxicon");
	}
	var isUpdata = $(".chice_div_li span[updata]");
	if(isUpdata.length>0){
		var doms = $(".chice_div_li .global_checkboxicon");
		if(doms.length==0){
			$(".lis_bottom_a > span").css("background","#D3D3D3");
		}else{
			$(".lis_bottom_a > span").css("background","#28b779");
		}
	}
})

// 修改通道
$(document).on("click",".lis_bottom_a",function(){
	var channels = new Array();
	$(".chice_div_li span").each(function(i, v) {
		if ($(v).hasClass("global_checkboxicon")) {
			channels.push($(v).attr("value"));
		}
	})

	var host_id = $(this).parents(".vipt_timegreen").attr("id");
	if (host_id == undefined) {
		host_id = $(this).parents(".vipt_time").attr("id");
	}
	var id = host_id;
    var section_id = host_id.split("_")[1];

	host_id = host_id.substring(0, host_id.indexOf("_"));

	var week_id = $("#week_id").val();
	if(week_id == undefined)
	{
		week_id = 1;
	}
	var date = $("#datashow").val();
	if(date == undefined)
	{
		date = Date();
	}

	/*$.post(
		"${pageContext.request.contextPath}/liveplan/Liveplan_updateChannel.do",
		{host_id: host_id, section_id:section_id, week_id:week_id, date:date, channels:channels.toString()},
		function(data){
			if (data.flag == true) {
				layer.msg(data.msg);
			} else {
				layer.msg(data.msg);
			}
		},
		"json"
	);*/
	$.ajax({
		url: "${pageContext.request.contextPath}/liveplan/Liveplan_updateChannel.do",
		type: "POST",
		dataType: "json",
		data: {host_id: host_id, section_id:section_id, week_id:week_id, date:date, channels:channels.toString()},
		beforeSend:function() {
			if (channels.length == 0) {
				var isUpdata = $(".chice_div_li span[updata]");
				if(isUpdata.length>0){return false;}
				layer.msg("请选择资源！");
				//$(this).parents(".cl_name_aimg").css("display:none");
				//console.log($(that).parents(".main_tab_thcon_li_win").prev().prev(".cl_name_aimg").attr("style"));
				//that.parents(".main_tab_thcon_li_win").prev().prev(".cl_name_aimg").hide();
				return false;
			}
		},

		success: function(data){
			if (data.flag == true) {
				$("#"+id).find(".cl_name_aimg").show();
				layer.msg(data.msg);
			} else {
				layer.msg(data.msg);
			}
		}
	});
});

