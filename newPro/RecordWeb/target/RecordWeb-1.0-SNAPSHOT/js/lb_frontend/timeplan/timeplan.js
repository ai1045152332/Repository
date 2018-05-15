var root_path;
//var hostids = new Array();

$(function(){

    root_path = $("#root_path").val();
})

function getData(weekday)
{
    $.get(root_path+'/timeplan/Timeplan_ajaxplan.do?'+Math.random(),{week_id:weekday},function(data){
        $("#timeplan_list").html(data);
    },'html');
}
$('#settimeplan').click(function() {
    var hostids = new Array();
    $('#timeplan_list').find('.global_cheted').each(function() {
        console.log($(this));
        console.log("id-->" + $(this).attr('hostid'));
        console.log("ip-->" + $(this).attr('hostip'));
        hostids.push($(this).attr('hostid'));
    })

    if (hostids.length == 0) {
        layer.msg('请先选中设备');
    } else {
        $.post(root_path + '/timeplan/Timeplan_setModel.do?', {hostids: JSON.stringify(hostids)}, function (data) {
            //$("#timeplan_list").html(data);
            layer.msg("正在设置录像计划模式,请稍后!")
        }, 'json');
    }
})

$('#cancelplan').click(function() {
    var hostids = new Array();
    $('#timeplan_list').find('.global_cheted').each(function() {
        console.log($(this));
        console.log("id-->" + $(this).attr('hostid'));
        console.log("ip-->" + $(this).attr('hostip'));
        hostids.push($(this).attr('hostid'));
    })
    if (hostids.length == 0) {
        layer.msg('请先选中设备');
    } else {
        $.post(root_path + '/timeplan/Timeplan_cancelModel.do?', {hostids: JSON.stringify(hostids)}, function (data) {
            //$("#timeplan_list").html(data);
            layer.msg("正在取消录像计划模式,请稍后!")
        }, 'json');
    }
})

// 设置录像计划模式
$(".setPlan").find(".cheice_icon_t").click(function(){
    //$(this).addClass('global_cheted');
    //alert('111');
    var hostids = new Array();
    $('#timeplan_list').find('.chicoe_icn_t').each(function() {
        console.log($(this));
        console.log("id-->" + $(this).attr('hostid'));
        console.log("ip-->" + $(this).attr('hostip'));
        hostids.push($(this).attr('hostid'));
    })
    // 取消录像计划模式
    if ($(this).hasClass("global_cheted")) {
        $(this).removeClass('global_cheted');
        $.post(root_path+'/timeplan/Timeplan_cancelModel.do?',{hostids: JSON.stringify(hostids)},function(data){
            //$("#timeplan_list").html(data);
        },'json');
    } else {
        //alert('33333333333333')
        $(this).addClass('global_cheted');
        $.post(root_path+'/timeplan/Timeplan_setModel.do?',{hostids: JSON.stringify(hostids)},function(data){
            //$("#timeplan_list").html(data);
        },'json');
    }
})


//$(".setPlan").find(".global_cheted").click(function(){
//    alert('33333333333');
//})


$(".add_timeplan").live('click',function(){
    var hostid = $(this).attr('id');
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
    var arr = hostid.split("_");
    var host_id = arr[0];
    var section_id = arr[1];

    $.post(root_path+'/timeplan/Timeplan_addplan.do',{week_id:week_id,date:date,section_id:section_id,host_id:host_id},function(data){
        if(data.msg=="添加成功")
        {
            $("#"+hostid).removeClass("add_timeplan");
            $("#"+hostid).removeClass("vipt_time");
            $("#"+hostid).addClass("del_timeplan");
            $("#"+hostid).addClass("vipt_timeselected");
            $("#"+hostid).attr("name",data.id);
            //var hiddenString = "";
            //hiddenString += "<input type='hidden' name='hostIp' value='"+data.ip+"' />";
            //hiddenString += "<input type='hidden' name='timeplanId' value='"+data.id+"'/>";
            //$("#"+hostid).html(hiddenString);
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
});

$(".del_timeplan").live('click',function(){
    var hostid = $(this).attr('id');
    //var arr = hostid.split("_");
    var timeplan_id = $(this).attr('name');
    $.post(root_path+'/timeplan/Timeplan_delplan.do',{timeplan_id:timeplan_id},function(data){
        if(data.msg=="删除成功")
        {
            $("#"+hostid).removeClass("del_timeplan");
            $("#"+hostid).removeClass("vipt_timeselected");
            $("#"+hostid).addClass("add_timeplan");
            $("#"+hostid).addClass("vipt_time");
            //$("#"+hostid).html("")
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
    // console.log(week_id);
    if(week_id == undefined)
    {
        week_id = 1;
    }
    var date = $("#datashow").val();
    if(date == undefined)
    {
        date = Date();
    }
    var arr = hostid.split("_");
    var host_id = arr[0];
    var section_id = arr[1];
    $.post(root_path+'/timeplan/Timeplan_addplan.do',{week_id:week_id,date:date,section_id:section_id,host_id:host_id},function(data){

        if(data.msg=="添加成功")
        {
            $("#"+hostid).removeClass("add_timegreenplan");
            $("#"+hostid).removeClass("vipt_timegreen");
            $("#"+hostid).addClass("del_timegreenplan");
            $("#"+hostid).addClass("vipt_timegreenselected");
            $("#"+hostid).attr("name",data.id);
            //var hiddenString = "";
            //hiddenString += "<input type='hidden' name='hostIp' value='"+data.ip+"' />";
            //hiddenString += "<input type='hidden' name='timeplanId' value='"+data.id+"'/>";
            //hiddenString += "<input type='hidden' name='mediaIp' value='"+data.mediaIp+"'/>";
            //$("#"+hostid).html(hiddenString);
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
    //var arr = hostid.split("_");
    var timeplan_id = $(this).attr('name');
    $.post(root_path+'/timeplan/Timeplan_delplan.do',{timeplan_id:timeplan_id},function(data){
        if(data.msg=="删除成功")
        {
            $("#"+hostid).removeClass("del_timegreenplan");
            $("#"+hostid).removeClass("vipt_timegreenselected");
            $("#"+hostid).addClass("add_timegreenplan");
            $("#"+hostid).addClass("vipt_timegreen");
            //$("#"+hostid).html("");
        }
        else
        {
            layer.msg(data.msg,1);
        }
        //
    });
});
$("span.settimeplan").on("click",function(){
    var hostids = "";
    var hostips = "";
    $(".vipt_tablerecycle > .global_cheted").each(function(){
        hostids += $(this).attr("hostid")+","
        hostips += $(this).attr("hostip")+","
    });
    if(hostids == "")
    {
        layer.msg("请选择班级");
        return false;
    }
    $.post(root_path+'/timeplan/Timeplan_setTimeplan.do',{hostIds:hostids,hostIps:hostips},function(data){
        if(data.msg == "") {
            layer.msg("所选录像计划下发成功");
        }
        else
        {
            layer.alert(data.msg);
        }
    });
});
//清空计划
function clearPlan()
{
    layer.confirm("您确定清空所有的录像计划吗?",function() {
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
        $.post(root_path + '/timeplan/Timeplan_clearplan.do',{week_id:week_id},function(data){
            $.get(root_path + '/timeplan/Timeplan_ajaxplan.do', {week_id:week_id,date:date}, function (data) {
                $("#timeplan_list").html("");
                $("#timeplan_list").html(data);
            }, 'html');
            $(".global_cheted").removeClass("global_cheted");
            //layer.msg(data.msg, 1);
            //add by xinye
            if(data.msgList!=null&&data.msgList!=''){
                layer.msg(data.msgList,2);
            }else{
                layer.msg(data.msg, 1);
            }

            //add by xinye end
        });
    },'清空计划')
}

$(".vipt_time").live("mouseover",function(){
    $(this).css({"height":"60px","width":"115px","box-shadow":"0 0 0 2px #28b779 inset"})
}).live("mouseout",function(){
    $(this).css({"height":"60px","width":"115px","box-shadow":"0 0 0 2px #fff inset"})
})
$(".vipt_timeselected").live("mouseover",function(){
    $(this).css({"height":"60px","width":"115px","box-shadow":"0 0 0 2px #28b779 inset"})
}).live("mouseout",function(){
    $(this).css({"height":"60px","width":"115px","box-shadow":"0 0 0 2px #fff inset"})
})
$(".vipt_timegreen").live("mouseover",function(){
    $(this).css({"height":"60px","width":"115px","box-shadow":"0 0 0 2px #28b779 inset"})
}).live("mouseout",function(){
    $(this).css({"height":"60px","width":"115px","box-shadow":"0 0 0 2px #F1F8F2 inset"})
})
$(".vipt_timegreenselected").live("mouseover",function(){
    $(this).css({"height":"60px","width":"115px","box-shadow":"0 0 0 2px #28b779 inset"})
}).live("mouseout",function(){
    $(this).css({"height":"60px","width":"115px","box-shadow":"0 0 0 2px #F1F8F2 inset"})
})
var copyElement = null;
$(document).on('click','.copy_timeplaan',function(){
    if(copyElement==null){
        var checked = $("span[id=global_cheted_i]").length;
        if(checked!=1){
            layer.msg("请选择一个班级");
            return;
        }
        copyElement = $(".vipt_tablerecycle:has(span[id=global_cheted_i])");
        layer.msg("已复制到剪切板");
        $(this).text("粘贴选中计划");
    }else{
        var hostids = "";
        $(".vipt_tablerecycle > .global_cheted").each(function(){
            hostids += $(this).attr("hostid")+",";
        });
        $.post(root_path + '/timeplan/Timeplan_findNas.do', {hostids: hostids.toString()}, function (data) {
            if (data.msg == "nas未设置") {
                var isSetting = $("#isSetting").val();
                if (isSetting == "true") {
                    layer.confirm("请设置Nas服务器", function () {
                        window.location.href = root_path + "/settings/Settings_findNas.do";
                        return;
                    })
                } else {
                    layer.msg("Nas服务器未设置", 1);
                    return;
                }
            }
        })
        var target = $(".vipt_tablerecycle:has(span[id=global_cheted_i])").not($(copyElement));
        var copyChilds = $(copyElement).children("div");
        var str="";
        //var date = $("#selectdiv").html();
        for(var i= 0,j=target.length;i<j;i++){
            var childs = target[i].getElementsByTagName("div");
            str+=$(target[i]).children("span[hostid]").attr("hostid")+",";
            for(var index=1;index<childs.length;index++){
                var oldClass =  $(childs[index]).attr("class");
                var copyClass = $(copyChilds[index]).attr("class");
                if(copyClass==null){
                    if(oldClass.indexOf("vipt_timeselected")!=-1||oldClass.indexOf("vipt_timegreenselected")!=-1){
                        str+=index+",";
                    }
                    continue;
                }
                if(copyClass.indexOf("vipt_timeselected")!=-1||copyClass.indexOf("vipt_timegreenselected")!=-1){
                    str+=index+",";
                }
            }
            str=str.substring(0,str.length-1)+"/";
        }
        $(this).text("复制选中计划");
        copyElement = null;
        console.log(str);
        var week_id = $("#week_id").val();
        if (week_id == undefined) {
            week_id = 1;
        }
        var date = $("#datashow").val();
        if (date == undefined) {
            date = Date();
        }
        $.post(root_path + '/timeplan/Timeplan_addcopyplan.do', {
            week_id: week_id,
            date: date,
            hostids: str
        },function(data){
            if (data.msg == "添加成功"){
                var json = data.timeplans;
                var num = 0;
                for(var i= 0,j=target.length;i<j;i++){
                    var childs = target[i].getElementsByTagName("div");
                    str+=$(target[i]).children("span[hostid]").attr("hostid")+",";
                    console.log(childs);
                    var childClass = $(childs[0]).attr("class");
                    for(var index=1;index<childs.length;index++){
                        var copyClass = $(copyChilds[index]).attr("class");
                        if(copyClass==null){
                            var oldClass = $(childs[index]).attr("class");
                            if(oldClass.indexOf("vipt_timeselected")!=-1||oldClass.indexOf("vipt_timegreenselected")!=-1){
                                $(childs[index]).attr("name",json[num]);
                                num++;
                            }
                            continue;
                        }
                        if(childClass.indexOf("vipt_groupgreen")!=-1){
                            if(copyClass.indexOf("green")==-1){
                                copyClass = copyClass.replace(/vipt_time/,"vipt_timegreen");
                                copyClass = copyClass.replace(/timeplan/,"timegreenplan");
                            }
                        }else{
                            if(copyClass.indexOf("green")!=-1){
                                copyClass = copyClass.replace(/timegreen/,"time");
                                copyClass = copyClass.replace(/timegreen/,"time");
                            }
                        }
                        $(childs[index]).attr("class",copyClass);
                        if(copyClass.indexOf("vipt_timeselected")!=-1||copyClass.indexOf("vipt_timegreenselected")!=-1){
                            $(childs[index]).attr("name",json[num]);
                            num++;
                        }
                    }
                }
            }
            layer.msg(data.msg);
        })
    }
})
