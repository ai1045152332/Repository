/**
 * Created by honghe on 2016/9/6.
 */
var obj;
function addTemp(){
    $.post(url+"/timeplan/Tempplan_host_list.do",function(data){
                $(".chico_class_title .mCSB_container").html('');
                $(".chico_class_titletwo .mCSB_container").html('');
                $(".win_planclass_divli_name").val('');
                $("#start").val('');
                $("#end").val('');
                $(".win_planclass_div").removeAttr("tempId");
                if(data.status==0){
                    var hosts = data.data.hostList;
                    for(var j=hosts.length,i=j-1;i>=0;i--){
                        var host = hosts[i];
                        if(host[2]){
                            $(".chico_class_title .mCSB_container").prepend('<a href="javascript:void(0)" hostId="'+host[0]+'">'+host[1]+'</a>');
                        }else{
                            $(".chico_class_title .mCSB_container").prepend('<a href="javascript:void(0)" hostId="'+host[0]+'" hasSetNas="false" class="hassetnas" style="color: red">'+host[1]+'</a>');
                        }
            }
        }
    })
    var screenWidth = document.body.offsetWidth;
    var width = (screenWidth-450)/2;
    $(".win_planclass_div").css({"left":width+"px"});
    $(".win_planclass_div").fadeIn();
}
//确定、取消
$(".win_planclass_bottom span").live("click",function(){
    var text = $(this).text();
    if(text=="取消"){
        $(".win_planclass_div").fadeOut();
    }else{
        var plan_name = $(".win_planclass_divli_name").val();
        var start_time = $("#start").val();
        var end_time = $("#end").val();
        var array = $(".chico_class_titletwo a");
        if(plan_name==null||plan_name.trim()==''||start_time==null||start_time.trim()==''||end_time==null||end_time.trim()==''||array==null||array.length==0){
            layer.msg("请将计划填写完整",1);
            return;
        }
        var hostIds = '';
        for(var i = 0,j=array.length;i<j;i++){
            hostIds+=$(array[i]).attr("hostId")+";";
        }
        var tempId = $(".win_planclass_div").attr("tempId");
        if(tempId!=null){
            var parent = obj.parentNode.parentNode;
            $.post(url+"/timeplan/Tempplan_modifyTemp.do",{tempId:tempId,plan_name:plan_name,start_time:start_time,end_time:end_time,hostIds:hostIds},function(data){
                //layer.msg(data.msg);
                var temp_plan = data.temp_plan;
                if(temp_plan!=null){
                    var html = '<span>'+temp_plan.name+'</span>';
                    html+= '<span>'+temp_plan.timeStart+' — '+temp_plan.timeEnd+'</span>';
                    html+= '<span style="color:#28b779;cursor: pointer;" tempId="'+temp_plan.temporaryplanId+'">查看</span>';
                    html+= ' <span><span class="modifyspan_imgicon" tempId="'+temp_plan.temporaryplanId+'" tempName="'+temp_plan.name+'" time_start="'+temp_plan.timeStart+'" time_end="'+temp_plan.timeEnd+'"></span>';
                    html+=' <span class="delete_icontd" tempId="'+temp_plan.temporaryplanId+'"></span></span>';
                    $(parent).html(html);
                }
            })
        }else{
            $.post(url+"/timeplan/Tempplan_addTemp.do",{plan_name:plan_name,start_time:start_time,end_time:end_time,hostIds:hostIds},function(data){
                //layer.msg(data.msg);
                var temp_plan = data.temp_plan;
                if(temp_plan!=null){
                    var html = '<div class="main_content_contlst_li_tit"><div class="main_content_contlst_lid"><span>'+temp_plan.name+'</span>';
                    html+= '<span>'+temp_plan.timeStart+' — '+temp_plan.timeEnd+'</span>';
                    html+= '<span style="color:#28b779;cursor: pointer;" tempId="'+temp_plan.temporaryplanId+'">查看</span>';
                    html+= ' <span><span class="modifyspan_imgicon" tempId="'+temp_plan.temporaryplanId+'" tempName="'+temp_plan.name+'" time_start="'+temp_plan.timeStart+'" time_end="'+temp_plan.timeEnd+'"></span>';
                    html+=' <span class="delete_icontd" tempId="'+temp_plan.temporaryplanId+'"></span></span></div><div class="main_content_contlst_li_tit">';
                    $(".add_iconimgbig").before(html);
                }
            })
            //alert(parent.parentNode.className);
        }
        $(".win_planclass_div").fadeOut();
    }
})
//查看临时计划中的班级列表
$(".main_content_contlst_lid span:nth-child(3)").live('click',function(){
    var tempId = $(this).attr("tempId");
    $.post(url+"/timeplan/Tempplan_showTempInfo.do",{tempId:tempId},function(data){
        if(data.status==0){
            var array = data.data.hosts;
            var str = '<div class="innerContent" style="position: absolute;left: 1px;width: 398px;height:464px;overflow: auto;">';
            str+='</div>';
           var page_layer = $.layer({
                type: 1,
                title: '查看班级列表',
                area: ['400', '500'],
                border: [1], //去掉默认边框
                shade: [0], //去掉遮罩
                closeBtn: [0, true], //去掉默认关闭按钮
                //shift: 'left', //从左动画弹出
                page: {
                    html: str
                }
            });
            for(var i= 0,j=array.length;i<j;i++){
               var ele='<p style="float: left;display: block;height: 30px;line-height: 30px;width: 100%;">';
                ele+='<span style="float: left;width: 50%;text-align: center">'+array[i].group_name+'</span>';
                ele+='<span style="float: left;width: 50%;text-align: center">'+array[i].host_name+'</span>';
                ele+='</p>';
                $(".innerContent").prepend(ele);
            }
            $(".innerContent").mCustomScrollbar();
            $(".innerContent").find("p:odd").css("background","#dadada")
        }else{
            layer.msg(data.msg);
        }
    })
})
//修改临时计划
$(".modifyspan_imgicon").live('click',function(){
    obj = this;
    var tempId = $(this).attr("tempId");
    $(".win_planclass_div").attr("tempId",tempId);
    var tempName = $(this).attr("tempName");
    var time_start = $(this).attr("time_start");
    var time_end = $(this).attr("time_end");
    $(".win_planclass_divli_name").val(tempName);
    $("#start").val(time_start);
    $("#end").val(time_end);
    $.post(url+"/timeplan/Tempplan_host_list.do",function(data){

        $(".chico_class_title .mCSB_container").html('');
        $(".chico_class_titletwo .mCSB_container").html('');
        if(data.status==0){
            var hosts = data.data.hostList;
            $.post(url+"/timeplan/Tempplan_showTempInfo.do",{tempId:tempId},function(data2){
                if(data2.status==0){
                    var array = data2.data.hosts;
                    var str = ',';
                    for(var i= 0,j=array.length;i<j;i++){
                        str+=array[i].host_id+',';
                    }
                    for(var j=hosts.length,i=j-1;i>=0;i--){
                        var host = hosts[i];
                        var bool = str.indexOf(','+host[0]+",")>=0;
                        if(bool){
                            $(".chico_class_titletwo .mCSB_container").prepend('<a href="javascript:void(0)" hostId="'+host[0]+'">'+host[1]+'</a>');
                        }else{
                            if(host[2]){
                                $(".chico_class_title .mCSB_container").prepend('<a href="javascript:void(0)" hostId="'+host[0]+'">'+host[1]+'</a>');
                            }else{
                                $(".chico_class_title .mCSB_container").prepend('<a href="javascript:void(0)" hostId="'+host[0]+'" hasSetNas="false" class="hassetnas" style="color: red">'+host[1]+'</a>');
                            }
                            //$(".chico_class_title .mCSB_container").prepend('<a href="javascript:void(0)" hostId="'+host[0]+'">'+host[1]+'</a>');
                        }
                    }
                }else{
                    layer.msg(data2.msg);
                }
            })

        }
    })
    //$(".add_iconimgbig").hide();
    var screenWidth = document.body.offsetWidth;
    var width = (screenWidth-450)/2;
    $(".win_planclass_div").css({"left":width+"px"});
    $(".win_planclass_div").fadeIn();
})

//删除临时计划
$(".delete_icontd").live('click',function(){
    var tempId = $(this).attr("tempId");
    var element = this.parentNode.parentNode.parentNode;
    layer.confirm('确定要删除这个临时计划吗?', function(index){

        $.post(url+"/timeplan/Tempplan_delTemp_plan.do",{tempId:tempId},function(data){
            //layer.msg(data.msg,1);
            if(data.status==0){
                $(element).remove();
            }
        })
        layer.close(index);
    });
})
//未设置Nas的班级点击事件
$(".chico_class_title a[hasSetNas=false]").live('click',function(){
    layer.confirm("请设置Nas服务器",function(){
        window.location.href=url+"/settings/Settings_findNas.do";
    })
})