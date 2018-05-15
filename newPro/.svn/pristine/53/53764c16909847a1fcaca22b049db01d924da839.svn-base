/**
 * Created by lch on 2014/10/8.
 */
var root_path;
$(function(){
    root_path = $("#root_path").val();
})

function getData(weekday)
{
	$.post(
		"${pageContext.request.contextPath}/settings/Settings_getCurWeek.do",
		{week_id:weekday},
		function(data) {
			$("#timeplan_list").html(data);
		}
	);
}

/*$(".add_timeplan").live('click',function(){
    var hostid = $(this).attr('id');
    var week_id = $("#week_id").val();
    var arr = hostid.split("_");
    var host_id = arr[0];
    var classtime_id = arr[1];
    var classtimes = arr[2];
    $.post(root_path+'/timeplan/Timeplan_addplan.do',{week_id:week_id,classtime_id:classtime_id,host_id:host_id,classtimes:classtimes},function(data){

        if(data.msg=="添加成功")
        {
            $("#"+hostid).removeClass("add_timeplan");
            $("#"+hostid).removeClass("vipt_time");
            $("#"+hostid).addClass("del_timeplan");
            $("#"+hostid).addClass("vipt_timeselected");
            $("#"+hostid).attr("name",data.id);
            var hiddenString = "";
            hiddenString += "<input type='hidden' name='hostIp' value='"+data.ip+"' />";
            hiddenString += "<input type='hidden' name='timeplanId' value='"+data.id+"'/>";
            //hiddenString += "<input type='hidden' name='mediaIp' value='"+data.mediaIp+"'/>";
            $("#"+hostid).html(hiddenString);
        }
		else if(data.msg=="nas未设置")
		{
			var isSetting = $("#isSetting").val();
			if(isSetting == "true"){
				layer.confirm("请设置Nas服务器",function(){
					window.location.href=root_path+"/settings/Settings_findNas.do";
				})
			}else{
				layer.msg("Nas服务器未设置",1);
			}
		}
        else
        {
            layer.msg(data.msg,1);
        }
        //
    });
});*/
/*$(".del_timeplan").live('click',function(){
    var hostid = $(this).attr('id');
    var arr = hostid.split("_");
    var timeplan_id = $(this).attr('name');
    $.post(root_path+'/timeplan/Timeplan_delplan.do',{timeplan_id:timeplan_id,host_id:arr[0]},function(data){
        //$.messager.alert("计划设置成功",data.msg);
        if(data.msg=="删除成功")
        {
            $("#"+hostid).removeClass("del_timeplan");
            $("#"+hostid).removeClass("vipt_timeselected");
            $("#"+hostid).addClass("add_timeplan");
            $("#"+hostid).addClass("vipt_time");
            $("#"+hostid).html("")
        }
        else
        {
            layer.msg("删除失败",1);
        }

    });
});
$(".add_timegreenplan").live('click',function(){
    var hostid = $(this).attr('id');
    var week_id = $("#week_id").val();
    var arr = hostid.split("_");
    var host_id = arr[0];
    var classtime_id = arr[1];
    var classtimes = arr[2];
    $.post(root_path+'/timeplan/Timeplan_addplan.do',{week_id:week_id,classtime_id:classtime_id,host_id:host_id,classtimes:classtimes},function(data){

        if(data.msg=="添加成功")
        {
            $("#"+hostid).removeClass("add_timegreenplan");
            $("#"+hostid).removeClass("vipt_timegreen");
            $("#"+hostid).addClass("del_timegreenplan");
            $("#"+hostid).addClass("vipt_timegreenselected");
            $("#"+hostid).attr("name",data.id);
            var hiddenString = "";
            hiddenString += "<input type='hidden' name='hostIp' value='"+data.ip+"' />";
            hiddenString += "<input type='hidden' name='timeplanId' value='"+data.id+"'/>";
            hiddenString += "<input type='hidden' name='mediaIp' value='"+data.mediaIp+"'/>";
            $("#"+hostid).html(hiddenString);
        }
		else if(data.msg=="nas未设置")
		{
			var isSetting = $("#isSetting").val();
			if(isSetting == "true"){
				layer.confirm("请设置Nas服务器",function(){
					window.location.href=root_path+"/settings/Settings_findNas.do";
				})
			}else{
				layer.msg("Nas服务未设置",1);
			}
		}
        else
        {
            layer.msg(data.msg,1);
        }
        //
    });
});
$(".del_timegreenplan").live('click',function(){
    var hostid = $(this).attr('id');
    var arr = hostid.split("_");
    var timeplan_id = $(this).attr('name');
    $.post(root_path+'/timeplan/Timeplan_delplan.do',{timeplan_id:timeplan_id,host_id:arr[0]},function(data){
        //$.messager.alert("计划设置成功",data.msg);
        if(data.msg=="删除成功")
        {
            $("#"+hostid).removeClass("del_timegreenplan");
            $("#"+hostid).removeClass("vipt_timegreenselected");
            $("#"+hostid).addClass("add_timegreenplan");
            $("#"+hostid).addClass("vipt_timegreen");
            $("#"+hostid).html("");
        }
        else
        {
            layer.msg(data.msg,1);
        }
        //
    });
});*/
var classmanagerlayer;
function updateClasstime()//修改上课时间
{
    classmanagerlayer=$.layer({
        type : 2,
        title: '修改上课时间',
        shadeClose: true,
        maxmin: false,
        fix : false,
        area: ['641px', '672px'],
        iframe: {
            src : root_path+'/timeplan/Classtime_classtime.do'
        },
        end:function(){
            window.location.reload();
        }
    });
    /*$.get('/timeplan/Classtime_classtime.do',function(data){
        $("#controltool").html("");
        $("#controltool").html(data);
    },'html');*/
}
function closelayer()
{
    layer.close(classmanagerlayer);
}
function clearPlan()//清空计划 清空本天的还是所有的
{
    layer.confirm("您确定清空所有的录像计划吗?",function() {
        var week_id = $("#week_id").val();
        var hostIp = $("input[name='hostIp']");
        var timeplanId = $("input[name='timeplanId']");
        var mediaIp = $("input[name='mediaIp']");
        var hostIpstring = "";
        var timeplanIdstring ="";
        var mediaIpString = "";

        for (var i = 0; i < hostIp.length; i++) {

            if (i >= hostIp.length - 1) {
                hostIpstring += hostIp.eq(i).val();
                timeplanIdstring +=  timeplanId.eq(i).val();
                mediaIpString += mediaIp.eq(i).val();
            }
            else {
                hostIpstring += hostIp.eq(i).val() + "_";
                timeplanIdstring +=  timeplanId.eq(i).val() + "_";
                mediaIpString += mediaIp.eq(i).val()+"_";
            }
        }
        console.log(hostIpstring);
        console.log(timeplanIdstring);
        //return;
        //datas += '}';
        $.post(root_path + '/timeplan/Timeplan_clearplan.do',{week_id:week_id,hostIp:hostIpstring,timeplanId:timeplanIdstring,mediaIp:mediaIpString},function(data){
            $.get(root_path + '/timeplan/Timeplan_ajaxplan.do', {week_id: week_id}, function (data) {
                $("#timeplan_list").html("");
                $("#timeplan_list").html(data);
            }, 'html');
            layer.msg(data.msg, 1);
        });
    },'清空计划')
}
		$(function(){ 
			var chapterlen=$(".chapter").length;
			var class_sperate = 5;
			var five_minites = 5;
			if(chapterlen>=6){
				for(i=11;i<chapterlen*2;i++){
					$(".tm_class").eq(i).find(".chooseh").css("top","-102px")
					$(".tm_class").eq(i).find(".choosem").css("top","-227px");
				}
			}
			//首先获取课时长度，当课时为0时，显示提示信息
			var classlen=$(".tm_class").length;
			if (classlen==0) {
				$(".tm_warn_add").show();
			}else{
				$(".tm_warn_add").hide();
			}
		$(".tm_hour").live("click",function(){
				//获取点击事件是当前第几个tm_class的点击事件
				var ind=$(this).parents(".tm_class").index();
				
				var thisobj=$(".tm_class").eq(ind);
				var nextobj=$(".tm_class").eq(ind+1);
				if(ind>11){
					$(".tm_class").eq(ind).find(".choosem").css("top","-227px");
					$(".tm_class").eq(ind).find(".chooseh").css("top","-102px")
				}
				//显示小时数
				for(i=0;i<24;i++){
					if(i<10){
						i="0"+i
					}else{
						i=i
					}
					var html="<span>"+i+"</span>";
					thisobj.children(".chooseh").append(html);
				}
				//多次点击会循环添加多次，需要去除重复的
				var spanlen=thisobj.children(".chooseh").children("span").length;
				if(spanlen>24){
					for(j=spanlen;j>=24;j--){
						thisobj.children(".chooseh").children("span").eq(j).remove();
					}
				}
				var spanlen=thisobj.children(".chooseh").children("span").length;
				thisobj.children(".chooseh").show();
				thisobj.children(".choosem").hide();
				thisobj.siblings().children(".chooseh").hide();
				thisobj.siblings().children(".choosem").hide();
				//判断当前tm_class的长度
				var classlen=$(".tm_class").length;
				var starthour=$(".tm_class").eq(classlen-2).children(".tm_hour").text();
				var endhour=$(".tm_class").eq(classlen-1).children(".tm_hour").text();
				var prevhour=$(".tm_class").eq(classlen-3).children(".tm_hour").text();
				var startminites=$(".tm_class").eq(classlen-2).children(".tm_minites").text();
				var endminites=$(".tm_class").eq(classlen-1).children(".tm_minites").text();
				var prevminites=$(".tm_class").eq(classlen-3).children(".tm_minites").text();
				//当点击第一开始时间时，只需要判定第一节课的结束时间
				if(ind==0){
					if(endhour!="小时"){
						endhour=parseInt(endhour);
						for(m=0;m<24;m++){
							thisobj.children(".chooseh").children("span").eq(m).css("display","block");
						}
						for(m=endhour+1;m<24;m++){
							thisobj.children(".chooseh").children("span").eq(m).css("display","none");
						}
					}
					var endindhour=$(".tm_class").eq(ind+1).children(".tm_hour").text();
					if(endindhour!="小时"){
						var endindminites=$(".tm_class").eq(ind+1).children(".tm_minites").text();
						var startindminites=$(".tm_class").eq(0).children(".tm_minites").text();
						var endindhour=$(".tm_class").eq(ind+1).children(".tm_hour").text();
						var startindhour=$(".tm_class").eq(0).children(".tm_hour").text();
						if(startindhour==endindhour){
							if(startindminites>endindminites){
								//alert("开始分钟不能小于结束分钟");
								$(".tm_warn_setstart").css("display","block");
								setTimeout(hidestart,2000);
								$(".tm_class").eq(0).children(".tm_hour").text(endindminites)
							}
						}
						endindhour=parseInt(endindhour);
						for(m=0;m<24;m++){
							thisobj.children(".chooseh").children("span").eq(m).css("display","block");
						}
						for(m=endindhour+1;m<24;m++){
							thisobj.children(".chooseh").children("span").eq(m).css("display","none");
						}
					}
				}else if(ind==(classlen-1)){
					
					if(starthour=="小时"){
						$(".tm_class").eq(ind).children(".chooseh").hide();
						$(".tm_class").eq(ind).children(".choosem").hide();
						$(".tm_warn_starttime").css("display","block");
						setTimeout(hide,2000);
						setTimeout(setstarttime,500)
						//alert("设置小时或者分钟")
					}else if(startminites=="分钟"){
						$(".tm_class").eq(ind).children(".chooseh").hide();
						$(".tm_class").eq(ind).children(".choosem").hide();
						$(".tm_warn_starttime").css("display","block");
						setTimeout(hide,2000);
						setTimeout(setendtime,500)
					}
					//当点击最后一节课的结束时间时，只需要判断该节课的开始时间
					if(starthour=="小时"){
						$(".tm_class").eq(ind).children(".chooseh").hide();
						$(".tm_warn_starttime").css("display","block");
						setTimeout(hide,2000);
						setTimeout(setstarttime,500)
					}else{
						if(starthour!="小时"){
							
							starthour=parseInt(starthour);
							for(m=0;m<24;m++){
								thisobj.children(".chooseh").children("span").eq(m).css("display","block");
							}
							if(parseInt(startminites)>=55){
								for(m=0;m<=starthour;m++){
									thisobj.children(".chooseh").children("span").eq(m).css("display","none");
								}
							}else{
								
								for(m=0;m<starthour;m++){
									thisobj.children(".chooseh").children("span").eq(m).css("display","none");
								}
							}
						}
					}
					
				}else{
					//alert("b")
					//当点击时间不是第一节跟最后一节时，需要判断前后的时间
					var starthour=$(".tm_class").eq(ind).children(".tm_hour").text();
					if(starthour=="小时"){
						var starthour=$(".tm_class").eq(ind).children(".tm_hour").text();
						var endhour=$(".tm_class").eq(ind+1).children(".tm_hour").text();
						var prevhour=$(".tm_class").eq(ind-1).children(".tm_hour").text();
						var prevval=parseInt(prevhour);
						var thisval=parseInt(starthour);
						var nextval=parseInt(endhour);
						var startm=parseInt($(".tm_class").eq(ind).children(".tm_minites").text());
						var endm=parseInt($(".tm_class").eq(ind+1).children(".tm_minites").text());
						var prevm=parseInt($(".tm_class").eq(ind-1).children(".tm_minites").text());
						if(endhour!="小时"&&prevhour!="小时"){
							for(m=0;m<24;m++){
								thisobj.children(".chooseh").children("span").eq(m).css("display","block");
							}
							for(i=0;i<prevval;i++){
								thisobj.children(".chooseh").children("span").eq(i).css("display","none");
							}
							for(i=nextval;i<24;i++){
								thisobj.children(".chooseh").children("span").eq(i).css("display","none");
							}
						}else{
							for(m=0;m<24;m++){
								thisobj.children(".chooseh").children("span").eq(m).css("display","block");
							}
							var prevval=parseInt(prevhour);
							if(prevm>=55){
								for(i=0;i<=prevval;i++){
									thisobj.children(".chooseh").children("span").eq(i).css("display","none");
								}
							}else{
								for(i=0;i<prevval;i++){
									thisobj.children(".chooseh").children("span").eq(i).css("display","none");
								}
							}

						}
					}else{
						//alert("c")
						var starthour=$(".tm_class").eq(ind).children(".tm_hour").text();
						var endhour=$(".tm_class").eq(ind+1).children(".tm_hour").text();
						var prevhour=$(".tm_class").eq(ind-1).children(".tm_hour").text();
						var startminites=parseInt($(".tm_class").eq(ind).children(".tm_minites").text());
						var endminites=parseInt($(".tm_class").eq(ind+1).children(".tm_minites").text());
						var prevminites=parseInt($(".tm_class").eq(ind-1).children(".tm_minites").text());
						var preval=parseInt(prevhour);
						var thisval=parseInt(starthour);
						var endval=parseInt(endhour);
						
					//当preval《thisval=endval
					if(preval<thisval&&thisval==endval){
						//alert("ddd")
						for(i=0;i<24;i++){
							thisobj.children(".chooseh").children("span").eq(i).css("display","block");
						}
						if(endminites>=5 && prevminites<55){
							for(i=0;i<preval;i++){

								thisobj.children(".chooseh").children("span").eq(i).css("display","none");
							}
						}else if(endminites<5 && prevminites<55){
							for(i=preval;i<endval;i++){
								thisobj.children(".chooseh").children("span").eq(i).css("display","none");
							}
							/*for(i=0;i<thisval;i++){
								thisobj.children(".chooseh").children("span").eq(i).css("display","none");
							}*/
						}else if(prevminites>=55 && endminites>=5){
							for(i=0;i<=preval;i++){
								thisobj.children(".chooseh").children("span").eq(i).css("display","none");
							}
						}
						/*for(i=preval;i<24;i++){
							thisobj.children(".chooseh").children("span").eq(i).css("display","block");
						}*/
						//alert(startminites+"=="+endminites)
						//alert(endval)
						for(i=endval+1;i<24;i++){
							//alert()
							thisobj.children(".chooseh").children("span").eq(i).css("display","none");
						}
					}else if(preval==thisval&&thisval==endval){
						//alert("ddd")
						for(i=0;i<24;i++){
							thisobj.children(".chooseh").children("span").eq(i).css("display","block");
						}
						for(i=0;i<preval;i++){
							thisobj.children(".chooseh").children("span").eq(i).css("display","none");
						}
						for(i=preval+1;i<24;i++){
							thisobj.children(".chooseh").children("span").eq(i).css("display","none");
						}
					}else if(preval<thisval&&thisval<endval){//当preval<thisval<endval
						//alert("eee")
						for(i=0;i<24;i++){
							thisobj.children(".chooseh").children("span").eq(i).css("display","block");
						}
						if(prevminites >= 55 && endminites>5){
							for(i=0;i<=preval;i++){
								thisobj.children(".chooseh").children("span").eq(i).css("display","none");
							}
							
							
						}else if(endminites<5 && prevminites<55){
							//alert("eee2")
							for(i=0;i<preval;i++){
								thisobj.children(".chooseh").children("span").eq(i).css("display","none");
							}
							
							if(startminites+class_sperate-60 >0){
								i=endval-1;
								//alert("5-1")
							}
							else{
								i=endval;
								//alert("5-2")
							}
							for(i;i<24;i++){
								thisobj.children(".chooseh").children("span").eq(i).css("display","none");
							}

						}else{
							//alert("eee4")
							for(i=0;i<preval;i++){
								thisobj.children(".chooseh").children("span").eq(i).css("display","none");
							}
							
						}
						if(startminites < endminites-class_sperate){
							i = endval+1;
						}else{
							i=endval;
						}
						for(i;i<24;i++){
							thisobj.children(".chooseh").children("span").eq(i).css("display","none");
						}
					}else if(preval==thisval&&thisval<endval){//当preval=thisval<endval
						//alert("f")
						for(i=0;i<24;i++){
							thisobj.children(".chooseh").children("span").eq(i).css("display","block");
						}
						for(i=0;i<preval;i++){
							thisobj.children(".chooseh").children("span").eq(i).css("display","none");
						}
						if(endminites<5) {
							for (i = endval ; i < 24; i++) {
								thisobj.children(".chooseh").children("span").eq(i).css("display", "none");
							}
						}else{
							for (i = endval +1; i < 24; i++) {
								thisobj.children(".chooseh").children("span").eq(i).css("display", "none");
							}
						}
					}else if(preval==thisval&&endhour=="小时"){
						//alert("d")
						for(i=0;i<24;i++){
							thisobj.children(".chooseh").children("span").eq(i).css("display","block");
						}
						for(i=0;i<preval;i++){
							thisobj.children(".chooseh").children("span").eq(i).css("display","none");
						}
						//$(".tm_warn_setover").css("display","block");
						//setTimeout(hideover,2000);
						//thisobj.find(".tm_minites").text($(".tm_class").eq(ind-1).find(".tm_minites").text())
					}else if(preval<thisval&&endhour=="小时"){
						for(i=0;i<24;i++){
							thisobj.children(".chooseh").children("span").eq(i).css("display","block");
						}
						if(prevminites>=55){
							for(i=0;i<=preval;i++){
								thisobj.children(".chooseh").children("span").eq(i).css("display","none");
							}
						}else{
							for(i=0;i<preval;i++){
								thisobj.children(".chooseh").children("span").eq(i).css("display","none");
							}
						}
					}
					}
				}

			})
			$(".chooseh span").live("click",function(){
				//获取点击事件是当前第几个tm_class的点击事件
				var ind=$(this).parents(".tm_class").index();
				
				//alert(ind)
				var thisobj=$(".tm_class").eq(ind);
				var nextobj=$(".tm_class").eq(ind+1);
				var prevobj=$(".tm_class").eq(ind-1);
				var thisval=$(this).text();
				
				$(this).addClass("selected").siblings().removeClass("selected");
				$(this).parents(".tm_class").children(".tm_hour").text(thisval);
				$(this).parents(".tm_class").children(".hourtime").val(thisval);
				$(this).parents(".chooseh").attr("class","chooseh").hide();
				//当点击小时选项后，需要判断分钟值
				var last=$(".tm_class").length-1;
				var thishval=thisobj.find(".tm_hour").text();
				var nexthval=nextobj.find(".tm_hour").text();
				var prevhval=prevobj.find(".tm_hour").text();
				var thismval=thisobj.find(".tm_minites").text();
				var nextmval=nextobj.find(".tm_minites").text();
				var prevmval=prevobj.find(".tm_minites").text();
				if(ind==0){
					if(nexthval!="小时"){
						if(thishval==nexthval){
							if(nextmval!="分钟"){
								if(parseInt(thismval)>parseInt(nextmval)){
									//alert("开始分钟不能大于结束分钟")
									$(".tm_warn_setstart").css("display","block");
									setTimeout(hidestart,2000);
									thisobj.find(".tm_minites").text(nextmval)
									thisobj.children(".minutetime").val(nextmval);
								}
							}
						}
					}
				}else if(ind==last){
					//alert("aa")
					if(parseInt(thishval)==parseInt(prevhval)){
						if(prevmval!="分钟"){
							if(parseInt(thismval)<parseInt(prevmval)){
								//alert("结束分钟不能小于开始分钟")
								$(".tm_warn_setover").css("display","block");
								setTimeout(hideover,2000);
								if(parseInt(prevmval)+class_sperate < 10)
								{
									prevmval = "0"+(parseInt(prevmval)+class_sperate);
								}
								thisobj.find(".tm_minites").text(parseInt(prevmval)+five_minites)
								thisobj.children(".minutetime").val(parseInt(prevmval)+five_minites);
							}
						}
					}					
				}
				else{
					//alert("a")
					if(parseInt(prevhval)<parseInt(thishval)&&parseInt(thishval)==parseInt(nexthval)){
						//alert("a1")
						
						if((parseInt(nextmval)-5)<parseInt(thismval)){
							//alert(nextmval)
							//alert("开始分钟不能大于结束分钟")
							$(".tm_warn_setstart").css("display","block");
							//setTimeout(hidestart,2000);
							nextmval = parseInt(nextmval)-five_minites;
							if(nextmval <10 && nextmval>0){
								nextmval = "0"+parseInt(nextmval);
								//thisobj.find(".tm_hour").text(parseInt(thishval)-1);
								thisobj.find(".tm_minites").text(nextmval)
								thisobj.children(".minutetime").val(nextmval);
							}else if(nextmval<0){
								thisobj.find(".tm_hour").text(parseInt(thishval)-1);
								thisobj.find(".tm_minites").text(60-nextmval);
								thisobj.children(".minutetime").val(60-nextmval);
							}

						}
						//if(parseInt(nextmval)<parseInt(thismval)){
							
						//}
					}else if(parseInt(prevhval)==parseInt(thishval)&&parseInt(thishval)==parseInt(nexthval)){
						//alert("a2")
						if(parseInt(thismval)<parseInt(prevmval)){
							//alert("结束分钟不能小于开始分钟")
							$(".tm_warn_setover").css("display","block");
							//setTimeout(hideover,2000);
							thisobj.find(".tm_minites").text(prevmval);
							thisobj.children(".minutetime").val(prevmval);
						}
						if(parseInt(thismval)>parseInt(nextmval)){
							//alert("结束分钟不能小于开始分钟")
							$(".tm_warn_setover").css("display","block");
							//setTimeout(hideover,2000);
							thisobj.find(".tm_minites").text(prevmval)
							thisobj.children(".minutetime").val(prevmval);
						}
					}else if(parseInt(prevhval)==parseInt(thishval)&&parseInt(thishval)<parseInt(nexthval)){
						//alert("a3")
						if(parseInt(thismval)<parseInt(prevmval)){
							//alert("结束分钟不能小于开始分钟")
							$(".tm_warn_setover").css("display","block");
							//setTimeout(hideover,2000);
							prevmval = parseInt(prevmval) + five_minites;
							if(prevmval<10){
								prevmval = "0"+prevmval;
							}
							thisobj.find(".tm_minites").text(prevmval);
							thisobj.children(".minutetime").val(prevmval);
						}
					}else if(parseInt(prevhval)==parseInt(thishval)&&nexthval=="小时"){
						//alert("a4")
						prevmval = parseInt(prevmval)+five_minites;
						if(prevmval < 10){
							prevmval = "0"+prevmval;
						}
						thisobj.find(".tm_minites").text(prevmval)
						thisobj.children(".minutetime").val(prevmval);
					}else if(parseInt(prevhval)<parseInt(thishval)&&parseInt(thishval)<parseInt(nexthval)){
						//alert("a5")
						if(parseInt(prevmval)>55&&(parseInt(thishval)-1)==parseInt(prevhval)&&parseInt(thishval)<parseInt(nexthval)){
							var thisminites=parseInt(prevmval)+5-60;
							if(thisminites<10){
								thisminites="0"+(parseInt(prevmval)+5-60)
							}else{
								thisminites=thisminites
							}
							thisobj.children(".tm_minites").text(thisminites);
							thisobj.children(".minutetime").val(thisminites);
						}

					}else{
						
						if(parseInt(prevmval)>55&&(parseInt(thishval)-1)==parseInt(prevhval)){
							var thisminites=parseInt(prevmval)+5-60;
							if(thisminites<10){
								thisminites="0"+(parseInt(prevmval)+5-60)
							}else{
								thisminites=thisminites
							}
							thisobj.children(".tm_minites").text(thisminites);
							thisobj.children(".minutetime").val(thisminites);
						}
						
					}
				}
				var selectval=parseInt(thisobj.find(".tm_hour").text());
				$(this).parents(".chooseh").html("")
				//显示小时数
				for(i=0;i<24;i++){
					if(i<10){
						i="0"+i
					}else{
						i=i
					}
					var html="<span>"+i+"</span>";
					$(this).parents(".tm_class").children(".chooseh").append(html);
				}
				
				
				//确定添加按钮是否可用
				var classlen=$(".tm_class").length;
				var starthour=$(".tm_class").eq(classlen-2).children(".tm_hour").text();
				var endhour=$(".tm_class").eq(classlen-1).children(".tm_hour").text();
				var startminites=$(".tm_class").eq(classlen-2).children(".tm_minites").text();
				var endminites=$(".tm_class").eq(classlen-1).children(".tm_minites").text();
				//alert(starthour+"-"+startminites+"-"+endhour+"-"+endminites)
				if(starthour!="小时"&&endhour!="小时"&&startminites!="分钟"&&endminites!="分钟"){
					$(".tm_addunbind").attr("class","tm_add");
				}	
			})
			$(".tm_minites").live("click",function(){
				//获取点击事件是当前第几个tm_class的点击事件
				var ind=$(this).parents(".tm_class").index();
				//alert(ind)
				var thisobj=$(".tm_class").eq(ind);
				var nextobj=$(".tm_class").eq(ind+1);
				if(ind>11){
					$(".tm_class").eq(ind).find(".choosem").css("top","-227px");
					$(".tm_class").eq(ind).find(".chooseh").css("top","-102px")
				}
				//显示小时数
				for(i=0;i<60;i++){
					if(i<10){
						i="0"+i
					}else{
						i=i
					}
					var html="<span>"+i+"</span>";
					thisobj.children(".choosem").append(html);
				}
				//多次点击会循环添加多次，需要去除重复的
				var spanlen=thisobj.children(".choosem").children("span").length;
				if(spanlen>60){
					for(j=spanlen;j>=60;j--){
						thisobj.children(".choosem").children("span").eq(j).remove();
					}
				}
				thisobj.children(".choosem").show();
				thisobj.children(".chooseh").hide();
				thisobj.siblings().children(".chooseh").hide();
				thisobj.siblings().children(".choosem").hide();
				//判断当前tm_class的长度
				var classlen=$(".tm_class").length;
				var starthour=$(".tm_class").eq(classlen-2).children(".tm_hour").text();
				var endhour=$(".tm_class").eq(classlen-1).children(".tm_hour").text();
				var endhourb=$(".tm_class").eq(classlen-1).children(".tm_hour").text();
				var prevhour=$(".tm_class").eq(classlen-3).children(".tm_hour").text();
				var startminites=$(".tm_class").eq(classlen-2).children(".tm_minites").text();
				var endminites=$(".tm_class").eq(classlen-1).children(".tm_minites").text();
				var prevminites=$(".tm_class").eq(classlen-3).children(".tm_minites").text();

				//当点击分钟为第一个时，需要判断小时是否相等，然后只需判断结束时间的值
				if(ind==0){
					var endindhour=$(".tm_class").eq(1).children(".tm_hour").text();
					var startindhour=$(".tm_class").eq(0).children(".tm_hour").text();
					var startindminites=$(".tm_class").eq(0).children(".tm_minites").text();
					var endindminites=$(".tm_class").eq(1).children(".tm_minites").text();
					if(startindminites=="分钟"&&startindhour=="小时"){
						$(".tm_class").eq(ind).children(".choosem").hide();
							$(".tm_warn_starttime").css("display","block");
							setTimeout(hide,2000);
							//setTimeout(setendtime,500)
					}
					if(endindminites!="分钟"){
						if(endindhour==startindhour){
							for(m=0;m<60;m++){
								thisobj.children(".choosem").children("span").eq(m).css("display","block");
								}
							for(m=parseInt(endindminites)-five_minites+1;m<60;m++){
								thisobj.children(".choosem").children("span").eq(m).css("display","none");
								}
						}else{
							for(m=0;m<60;m++){
								thisobj.children(".choosem").children("span").eq(m).css("display","block");
								}
						}
					}
//					else{
//						alert("b")
//					}
//	
				}else if(ind==(classlen-1)){//添加时最后一个小时分钟
					if(endminites=="分钟"&&endhour=="小时"){
						$(".tm_class").eq(ind).children(".choosem").hide();
							$(".tm_warn_starttime").css("display","block");
							setTimeout(hide,2000);
							//setTimeout(setendtime,500)
					}
					//当点击分钟为最后一个时，需要判断小时是否相等，然后只需判断开始时间的值
					if(starthour==endhour){
						if(startminites=="分钟"){
							$(".tm_class").eq(ind).children(".choosem").hide();
							$(".tm_warn_starttime").css("display","block");
							setTimeout(hide,2000);
							setTimeout(setendtime,500)
						}else{
							if(startminites!="分钟"){
								for(m=0;m<60;m++){
								thisobj.children(".choosem").children("span").eq(m).css("display","block");
								}
								starthour=parseInt(starthour);
								for(m=0;m<parseInt(startminites)+five_minites;m++){
									thisobj.children(".choosem").children("span").eq(m).css("display","none");
								}
							}
						}
					}else{
						if(startminites=="分钟"){
							$(".tm_class").eq(ind).children(".choosem").hide();
							$(".tm_warn_starttime").css("display","block");
							setTimeout(hide,2000);
							setTimeout(setendtime,500)
						}else{
							if(startminites!="分钟"){	
								starthour=parseInt(starthour);
								//alert()
								//alert(starthour+"----"+endhour)
								for(m=0;m<60;m++){
									thisobj.children(".choosem").children("span").eq(m).css("display","block");
								}
								if(endhour-starthour == 1){
									//alert(startminites)
									for(m=0;m<parseInt(startminites)+five_minites-60;m++){
										thisobj.children(".choosem").children("span").eq(m).css("display","none");
									}
								}else{
									for(m=0;m<60;m++){
										thisobj.children(".choosem").children("span").eq(m).css("display","block");
									}
								}

							}
						}
					}
				}else{//修改时，有多个值
					//当点击分钟不是第一个，也不是最后一个时，需要判断前后课时小时是否相等，如果小时未选择，不允许选择分钟
					var starthour=$(".tm_class").eq(ind).children(".tm_hour").text();
					var endhour=$(".tm_class").eq(ind+1).children(".tm_hour").text();
					var prevhour=$(".tm_class").eq(ind-1).children(".tm_hour").text();
					var startminites=$(".tm_class").eq(ind).children(".tm_minites").text();
					var endminites=$(".tm_class").eq(ind+1).children(".tm_minites").text();
					var prevminites=$(".tm_class").eq(ind-1).children(".tm_minites").text();
					if(startminites=="分钟"&&starthour=="小时"){
						$(".tm_class").eq(ind).children(".choosem").hide();
							$(".tm_warn_starttime").css("display","block");
							setTimeout(hide,2000);
							//setTimeout(setendtime,500)
					}
					//当preval《thisval=endval
					var preval=parseInt(prevhour);
					var thisval=parseInt(starthour);
					var endval=parseInt(endhour);
					var premval=parseInt(prevminites);
					var thismval=parseInt(startminites);
					var endmval=parseInt(endminites);
					//当preval《thisval=endval
					if(preval<thisval && thisval==endval){
						//alert("a")
						for(m=0;m<60;m++){
							thisobj.children(".choosem").children("span").eq(m).css("display","block");
						}
						if(premval > 55){
							for(i=0;i<premval+five_minites-60;i++){
								thisobj.children(".choosem").children("span").eq(i).css("display","none");
							}
							for(i=endmval-class_sperate+1;i<60;i++){
								thisobj.children(".choosem").children("span").eq(i).css("display","none");
							}
						}else{
							//alert(endmval+"aaa")
							//alert("aa")
							for(i=endmval-class_sperate+1;i<60;i++){
								thisobj.children(".choosem").children("span").eq(i).css("display","none");
							}
							
						}
					}else if(preval==thisval&&thisval==endval){
						//alert("b")
						for(m=0;m<60;m++){
							thisobj.children(".choosem").children("span").eq(m).css("display","block");
						}
						for(i=0;i<premval+five_minites;i++){
							thisobj.children(".choosem").children("span").eq(i).css("display","none");
						}
						/*for(i=premval+five_minites;i<60;i++){
							thisobj.children(".choosem").children("span").eq(i).css("display","block");
						}*/
						for(i=endmval-class_sperate+1;i<60;i++){
							thisobj.children(".choosem").children("span").eq(i).css("display","none");
						}
					}else if(preval<thisval&&thisval<endval){//当preval<thisval<endval
						//alert("c")
						for(i=0;i<60;i++){
							//alert(i)
							thisobj.children(".choosem").children("span").eq(i).css("display","block");
						}
						if(thisval-preval == 1){
							//alert("cc")
							for(i=0;i<premval+class_sperate-60;i++){
								thisobj.children(".choosem").children("span").eq(i).css("display","none");
							}
						}

						if(endhour-thisval == 1){
							//alert("aaasa")
							for(i=60+endmval-five_minites;i<60;i++){
							thisobj.children(".choosem").children("span").eq(i).css("display","none");
							}
						}
					}else if(preval==thisval&&thisval<endval){//当preval=thisval<endval
						//alert("d")
						for(i=0;i<60;i++){
							thisobj.children(".choosem").children("span").eq(i).css("display","block");
						}
						if(premval>55){
							for(i=0;i<premval+five_minites-60;i++){
								thisobj.children(".choosem").children("span").eq(i).css("display","none");
							}	
						}else{
							if(endval-thisval == 1){
								for(i=0;i<premval+five_minites;i++){
									thisobj.children(".choosem").children("span").eq(i).css("display","none");
								}
								//alert(endminites)
								for(i=60-class_sperate+parseInt(endminites)+1;i<60;i++){

									thisobj.children(".choosem").children("span").eq(i).css("display","none");
								}
							}else{
								for(i=0;i<premval+five_minites;i++){
									thisobj.children(".choosem").children("span").eq(i).css("display","none");
								}
							}

						}
						
					}else if(preval==thisval&&endhourb=="小时"){
						//alert("e")
						for(m=0;m<60;m++){
							thisobj.children(".choosem").children("span").eq(m).css("display","block");
						}

						for(i=0;i<premval+class_sperate;i++){
							thisobj.children(".choosem").children("span").eq(i).css("display","none");
						}
						//alert("a4")
					}else if(preval<thisval&&endhourb=="小时"){
						//alert("f")
						for(i=0;i<60;i++){
							thisobj.children(".choosem").children("span").eq(i).css("display","block");
						}
						if(thisval-preval == 1){
							for(i=0;i<premval+class_sperate-60;i++){
								thisobj.children(".choosem").children("span").eq(i).css("display","none");
							}
						}
					}
				}
			})
			$(".choosem span").live("click",function(){
				//获取点击事件是当前第几个tm_class的点击事件
				var ind=$(this).parents(".tm_class").index();
				//alert(ind)
				var thisobj=$(".tm_class").eq(ind);
				var nextobj=$(".tm_class").eq(ind+1);
				var thisval=$(this).text();
				$(this).addClass("selected").siblings().removeClass("selected");
				
				$(this).parents(".tm_class").children(".tm_minites").text(thisval);
				$(this).parents(".tm_class").children(".minutetime").val(thisval);
				$(this).parents(".choosem").attr("class","choosem").hide();
				
				var selectval=parseInt(thisobj.find(".tm_minites").text());
				$(this).parents(".choosem").html("");
				
				//显示小时数
				for(i=0;i<60;i++){
					if(i<10){
						i="0"+i
					}else{
						i=i
					}
					var html="<span>"+i+"</span>";
					thisobj.children(".choosem").append(html);
//					if(i==selectval){
//						thisobj.find(".choosem").find("span").eq(i).addClass("selected");
//						//alert(i)
//					}
				}
				//确定添加按钮是否可用
				var classlen=$(".tm_class").length;
				var starthour=$(".tm_class").eq(classlen-2).children(".tm_hour").text();
				var endhour=$(".tm_class").eq(classlen-1).children(".tm_hour").text();
				var startminites=$(".tm_class").eq(classlen-2).children(".tm_minites").text();
				var endminites=$(".tm_class").eq(classlen-1).children(".tm_minites").text();
				//alert(starthour+"-"+startminites+"-"+endhour+"-"+endminites)
				if(starthour!="小时"&&endhour!="小时"&&startminites!="分钟"&&endminites!="分钟"){
					$(".tm_addunbind").attr("class","tm_add");
				}
			})

		$(".tm_add").live("click",function(){
				var classlen=$(".tm_class").length;
				if(classlen<=0){
					$(".tm_warn_add").show();
				//点击添加按钮添加课时
				//自定义添加课时代码，每次添加一个开始时间和结束时间
				var html="<div class=\"tm_class\" ><input type=\"hidden\" name=\"classtimeId\">"+
									"<div class=\"tm_hour\">小时</div><input type='hidden' name='starttime' class='hourtime' />"+
									"<div class=\"tm_mh\">:</div>"+
									"<div class=\"tm_minites\">分钟</div><input type='hidden' name='starttime_1' class='minutetime' />"+
									"<div class=\"chooseh\"></div>"+
									"<div class=\"choosem\"></div>"+
									"<div class=\"chapter\"></div>"+
								"</div>"+
						"<div class=\"tm_class\" style=margin-left:28px>"+
							"<div class=\"tm_line\">~</div>"+
							"<div class=\"tm_hour\">小时</div><input type='hidden' name='endtime' class='hourtime' />"+
							"<div class=\"tm_mh\">:</div>"+
							"<div class=\"tm_minites\">分钟</div><input type='hidden' name='endtime_1' class='minutetime' />"+
							"<div class=\"chooseh\"></div>"+
							"<div class=\"choosem\"></div>"+
							"<div class=\"chapterdel\"></div>"+
						"</div>";
				//自定义添加自定义课时到课时列表
				$(".timeplanlist").append(html);
				}else{
					$(".tm_warn_add").hide();
					//点击添加按钮添加课时
				//自定义添加课时代码，每次添加一个开始时间和结束时间
				var html="<div class=\"tm_class\" ><input type=\"hidden\" name=\"classtimeId\">"+
									"<div class=\"tm_hour\">小时</div><input type='hidden' name='starttime' class='hourtime' />"+
									"<div class=\"tm_mh\">:</div>"+
									"<div class=\"tm_minites\">分钟</div><input type='hidden' name='starttime_1' class='minutetime' />"+
									"<div class=\"chooseh\"></div>"+
									"<div class=\"choosem\"></div>"+
									"<div class=\"chapter\"></div>"+
								"</div>"+
						"<div class=\"tm_class\" style=margin-left:28px>"+
							"<div class=\"tm_line\">~</div>"+
							"<div class=\"tm_hour\">小时</div><input type='hidden' name='endtime' class='hourtime' />"+
							"<div class=\"tm_mh\">:</div>"+
							"<div class=\"tm_minites\">分钟</div><input type='hidden' name='endtime_1' class='minutetime' />"+
							"<div class=\"chooseh\"></div>"+
							"<div class=\"choosem\"></div>"+
							"<div class=\"chapterdel\"></div>"+
						"</div>";
				//自定义添加自定义课时到课时列表
				$(".timeplanlist").append(html);
				}
				//添加完课时后隐藏添加按钮，或者添加一个替换的div,禁用点击事件
				$(".tm_add").attr("class","tm_addunbind");
				var classlen=$(".tm_class").length;
				//给课时添加课时数
				if(classlen%2==0){
					var zhangjie=classlen/2;
//						if($(".vip").height()<675){
//							if(zhangjie>=6){
//								//alert("bb")
//								$(".choosem").eq().css("top","-227px");
//								$(".chooseh").css("top","-102px")
//							}
//						}
						
					switch(zhangjie){
						case 1:
							zhangjie="一";
							break;
						case 2:
							zhangjie="二";
							break;
						case 3:
							zhangjie="三";
							break;
						case 4:
							zhangjie="四";
							break;
						case 5:
							zhangjie="五";
							break;
						case 6:
							zhangjie="六";
							break;
						case 7:
							zhangjie="七";
							break;
						case 8:
							zhangjie="八";
							break;
						case 9:
							zhangjie="九";
							break;
						case 10:
							zhangjie="十";
							break;
						case 11:
							zhangjie="十一";
							break;
						case 12:
							zhangjie="十二";
							break;
						case 13:
							zhangjie="十三";
							break;
						case 14:
							zhangjie="十四";
							break;
						case 15:
							zhangjie="十五";
							break;
						case 16:
							zhangjie="十六";
							break;
						case 17:
							zhangjie="十七";
							break;
						case 18:
							zhangjie="十八";
							break;
						case 19:
							zhangjie="十九";
							break;
						case 20:
							zhangjie="二十";
							break;
						case 21:
							zhangjie="二十一";
							break;	
					}
					$(".tm_class").eq(classlen-2).children(".chapter").text("第"+zhangjie+"节");
				}
				for(i=0;i<classlen;i++){
					if(i==(classlen-1)){
						$(".tm_class").eq(i).children(".chapterdel").css("display","block");
					}else{
						$(".tm_class").eq(i).children(".chapterdel").css("display","none");
					}
				}
				var starthour=$(".tm_class").eq(classlen-2).children(".tm_hour").text();
				var endhour=$(".tm_class").eq(classlen-1).children(".tm_hour").text();
				var startminites=$(".tm_class").eq(classlen-2).children(".tm_minites").text();
				var endminites=$(".tm_class").eq(classlen-1).children(".tm_minites").text();
				if(starthour!="小时"&&endhour!="小时"&&startminites!="分钟"&&endminites!="分钟"){
					$(".tm_add").attr("class","tm_add");
				}
				if(classlen==0){
					$(".tm_warn_add").show();
				}else{
					$(".tm_warn_add").hide();
				}
			})
			$(".tm_addunbind").live("click",function(){
				$(".tm_warn_set").show();
				setTimeout(hiden,1500);
			})
$(".chapterdel").live("click",function(){
        var classtime_id = $(this).attr("id");
    var ind=$(this).parents(".tm_class").index();
    var prevind=$(this).parents(".tm_class").index()-1;
        if(classtime_id != undefined)
        {
            var indexlayer = $.layer({
                shade: [0.5],
                maxmin: false,
                area: ['400px','129px'],
                dialog: {
                    msg: '删除后将无法恢复，确定删除此节课吗？',
                    btns: 2,
                    type: 4,
                    btn: ['确定','取消'],
                    yes: function(){
                        layer.close(indexlayer);
                        if(classtime_id)
                        {
                            var arr = classtime_id.split("_");
                            var root_path = $("#root_pth").val();
                            var classtimeid = arr[1];
                            $.post(root_path+'/timeplan/Classtime_delete.do',{classtimeId:classtimeid},function(data){
                                if(data.msg && data.msg =="删除成功")
                                {
                                    //$("#"+classtime_id).parent().parent().prev().children().append("<div class='vip_timedel del_classtime' id='"+del_id+"'></div>");
                                    //$("#"+classtime_id).parent().parent().remove();
                                    $(".tm_class").eq(prevind).remove();
                                    $(".tm_class").eq(prevind).remove();
                                    var classlen=$(".tm_class").length;
                                    $(".tm_addunbind").attr("class","tm_add");
                                    $(".tm_class").eq(prevind-1).children(".chapterdel").show();
                                }
                                else if(data.msg && data.msg == "删除失败")
                                {
                                    layer.alert(data.msg,"","提示信息");
                                }
                                else
                                {
                                    layer.alert("此节课有录像计划不能删除","","提示信息");
                                }

                            });
                        }
                        else
                        {
                            $(".tm_class").eq(prevind).remove();
                            $(".tm_class").eq(prevind).remove();
                            var classlen=$(".tm_class").length;
                            $(".tm_addunbind").attr("class","tm_add");
                            $(".tm_class").eq(prevind-1).children(".chapterdel").show();
                        }
                    },
                    no: function(){
                    }
                }
            });
        }
    else
        {
            if(classtime_id)
            {
                var arr = classtime_id.split("_");
                var root_path = $("#root_pth").val();
                var classtimeid = arr[1];
                $.post(root_path+'/timeplan/Classtime_delete.do',{classtimeId:classtimeid},function(data){
                    if(data.msg && data.msg =="删除成功")
                    {
                        //$("#"+classtime_id).parent().parent().prev().children().append("<div class='vip_timedel del_classtime' id='"+del_id+"'></div>");
                        //$("#"+classtime_id).parent().parent().remove();
                        $(".tm_class").eq(prevind).remove();
                        $(".tm_class").eq(prevind).remove();
                        var classlen=$(".tm_class").length;
                        $(".tm_addunbind").attr("class","tm_add");
                        $(".tm_class").eq(prevind-1).children(".chapterdel").show();

                    }
                    else if(data.msg && data.msg == "删除失败")
                    {
                        layer.alert(data.msg,"","提示信息");
                    }
                    else
                    {
                        layer.alert("此节课有录像计划不能删除","","提示信息");
                    }

                });
            }
            else
            {
                $(".tm_class").eq(prevind).remove();
                $(".tm_class").eq(prevind).remove();
                var classlen=$(".tm_class").length;
                $(".tm_addunbind").attr("class","tm_add");
                $(".tm_class").eq(prevind-1).children(".chapterdel").show();
            }
        }
    })

})
		//点击空白区域，隐藏select模拟
		$(document).bind("click",function(e){
			var target = $(e.target);
			//alert(target.closest(".selectdivall").length)
			if(target.closest(".tm_class").length == 0){
				$(".chooseh").hide();
				$(".choosem").hide();
			}
		})
		function hide(){
			$(".tm_warn_starttime").hide()
		}
		function hiden(){
			$(".tm_warn_set").hide()
		}
		function hidestart(){
			$(".tm_warn_setstart").hide()
		}
		function hideover(){
			$(".tm_warn_setover").hide()
		}
		function setstarttime(){
			var classlen=$(".tm_class").length;
			$(".tm_class").eq(classlen-2).children(".tm_hour").click();
		}
		function setendtime(){
			var classlen=$(".tm_class").length;
			$(".tm_class").eq(classlen-2).children(".tm_minites").click();
		}

function addtime() {
    var classlength = $(".tm_class").length;
    var thisobj = $(".tm_class").eq(classlength-1);
    var pre_thisobj = $(".tm_class").eq(classlength-2);
    var fir = thisobj.children(".tm_hour").text();
    var sec = thisobj.children(".tm_minites").text();
    var thi = pre_thisobj.children(".tm_hour").text();
    var forth = pre_thisobj.children(".tm_minites").text();
   // alert(fir + "--"+sec+"---"+thi+"---"+forth)
    if(fir =="小时" || sec == "分钟" || thi =="小时" || forth == "分钟" )
    {
        $(".tm_warn_set").show();
        setTimeout(hiden,1500);
    }
    else {
        $.ajax({
            url: root_path + '/timeplan/Classtime_add.do',
            type: "POST",
            dataType: "json",
            data: $("#addtime").serialize(),
            success: function (data) {
                layer.msg(data.msg, 1, function () {
					window.location.reload();
                    //parent.closelayer();
                });
            }
        });
    }
}
function getUpperInt(int)
{
    var arr = ['一','二','三','四','五','六','七','八','九','十','十一','十二','十三','十四','十五','十六','十七','十八','十九','二十','二十一','二十二','二十三','二十四','二十五','二十六','二十七','二十八'];
    return arr[parseInt(int)-1];
}
$(".checktime").live("blur",function(){
    //var reg = /^([0-9]|([0-1][0-9])|2[0-3]):(([0-5][0-9]))$/gi;
    //var reg = /(^(0{0,1})([0-9]{1})$)|^1[0-9]{1}$|^2[0-3]{1}$/;
    var reg = /^0[0-9]{1}$|^1[0-9]{1}$|^2[0-3]{1}$/;
    var val = $(this).val();
    var obj=$(this);
    //alert($(this).val());
    if(!reg.test(val))
    {
        $(this).val("");
        layer.msg("请输入正确的24小时制时间例:00",1,function(){
            obj.focus();
        });
    }
    else
    {
        var id = $(this).attr("id");
        var arr = id.split("_");
        var classtime = arr[1];
        if(classtime > 1)
        {
            classtime = parseInt(classtime)-1;
            var prevhourtime = $("#endhourtime_"+classtime).val();
            var prevminutetime = $("#endminutetime_"+classtime).val();
            if(val < prevhourtime)
            {
                $(this).val("");

                layer.msg("时间不能小于上节课的结束时间",1,function(){

                    obj.focus();
                });
            }
        }
    }
});
$(".checkminute").live("blur",function(){
    var obj = $(this);
    var reg = /(^[0-5]{1}[0-9]{1}$)/gi;
    var val = $(this).val();

    if(!reg.test(val))
    {
        $(this).val("");

        layer.msg("请输入正确的24小时制时间例:00",1,function(){

            obj.focus();
        });

    }
});
//鼠标经过时显示输入框
//$(".vip_cframe_inputnoborder").live("mouseover",function(){
//   // alert("123")
//    $(this).addClass("vip_cframe_input").removeClass("vip_cframe_inputnoborder");
//});
/*//鼠标经过后恢复
$(".vip_cframe_input").live("mouseout",function(){
    // alert("123")
    $(this).addClass("vip_cframe_inputnoborder").removeClass("vip_cframe_input");
})*/
//鼠标获取焦点时显示输入框
$(".vip_cframe_inputnoborder").live("focus",function(){
    // alert("123")
    $(this).addClass("vip_cframe_input").removeClass("vip_cframe_inputnoborder");
});
//鼠标失去焦点后恢复
$(".vip_cframe_input").live("blur",function(){
    // alert("123")
    $(this).addClass("vip_cframe_inputnoborder").removeClass("vip_cframe_input");
})

    $(".vipt_time").live("mouseover",function(){
        $(this).css({"height":"115px","width":"115px","box-shadow":"0 0 0 2px #28b779 inset"})
    }).live("mouseout",function(){
        $(this).css({"height":"115px","width":"115px","box-shadow":"0 0 0 2px #fff inset"})
    })
    $(".vipt_timeselected").live("mouseover",function(){
         $(this).css({"height":"115px","width":"115px","box-shadow":"0 0 0 2px #28b779 inset"})
    }).live("mouseout",function(){
        $(this).css({"height":"115px","width":"115px","box-shadow":"0 0 0 2px #fff inset"})
    })
    $(".vipt_timegreen").live("mouseover",function(){
         $(this).css({"height":"115px","width":"115px","box-shadow":"0 0 0 2px #28b779 inset"})
    }).live("mouseout",function(){
       $(this).css({"height":"115px","width":"115px","box-shadow":"0 0 0 2px #F1F8F2 inset"})
    })
    $(".vipt_timegreenselected").live("mouseover",function(){
        $(this).css({"height":"115px","width":"115px","box-shadow":"0 0 0 2px #28b779 inset"})
    }).live("mouseout",function(){
         $(this).css({"height":"115px","width":"115px","box-shadow":"0 0 0 2px #fff inset"})
    })
    $("#modify").live("mouseover",function(){
        $(this).css({"background":"#fff","border":"1px solid #dbdbdb"});
        $(this).find(".vipt_head_modify").addClass("vipt_head_modifyed").removeClass("vipt_head_modify");
    }).live("mouseout",function(){
        $(this).css({"background":"#fff","border":"1px solid #f2f2f2"});
        $(this).find(".vipt_head_modifyed").addClass("vipt_head_modify").removeClass("vipt_head_modifyed");
    })
    $("#clean").live("mouseover",function(){
        $(this).css({"background":"#fff","border":"1px solid #dbdbdb"});
        $(this).find(".vipt_head_clean").addClass("vipt_head_cleaned").removeClass("vipt_head_clean");
    }).live("mouseout",function(){
        $(this).css({"background":"#fff","border":"1px solid #f2f2f2"});
        $(this).find(".vipt_head_cleaned").addClass("vipt_head_clean").removeClass("vipt_head_cleaned");
    })
function updatetime()
{
    var root_path = $("#root_path").val();
    var classlength = $(".tm_class").length;
    var thisobj = $(".tm_class").eq(classlength-1);
    var pre_thisobj = $(".tm_class").eq(classlength-2);
    var fir = thisobj.children(".tm_hour").text();
    var sec = thisobj.children(".tm_minites").text();
    var thi = pre_thisobj.children(".tm_hour").text();
    var forth = pre_thisobj.children(".tm_minites").text();
    if(fir =="小时" || sec == "分钟" || thi =="小时" || forth == "分钟" )
    {

        $(".tm_warn_set").show();
        setTimeout(hiden,1500);
    }
    else
    {
        $.ajax({
            url: root_path+'/timeplan/Classtime_update.do',
            type: "POST",
            dataType:"json",
            data: $("#updatetime").serialize(),
            success: function (data)
            {
                if(data.msg == "修改成功")
                {
                    layer.msg(data.msg,1,function(r){
                        parent.closelayer();
                    });
                }
                else
                {
                    layer.alert(data.msg,"","提示信息",function(){
                        parent.closelayer();
                    });
                }
            }
        });
    }
}

