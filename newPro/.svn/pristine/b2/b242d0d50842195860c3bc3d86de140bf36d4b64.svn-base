//确认是都全部选中
function selectall(){
	var alllen=$(".mm_listout").length;
	var flog=$(".mm_listout").find(".bged").length;
	if(alllen==flog){
		 $("#ischeckall").attr("class","bged").css({"background-position":"0px -35px"});//.css修正鼠标经过checkbox的背景位置;;
        $("#ischeckall").prop("checked",true);
	}
}
$(function(){
//鼠标点击选中
$(".mm_listout").find(".bg").live('click', function () {
    var hostname = $(this).attr('hostname');
    if(hostname == "")
    {
        layer.msg("请设置班级名称");
    }
    else
    {
        $(this).attr("class","bged");
        $(".mm_nogroup_delcover").css("display","block")
        if($(".mm_listout").find(".bg").length==0)
        {
            $("#ischeckall").attr("class","bged").css({"background-position":"0px -35px"});//.css修正鼠标经过checkbox的背景位置;;
            $("#ischeckall").prop("checked",true);
        }
//        selectall();
    }
});
//鼠标点击取消选中
$(".mm_listout").find(".bged").live('click', function () {
	$(this).attr("class","bg");
    var hostlist = $(".mm_list_option .bged");
    if(hostlist.length <= 0)
    {
        $(".mm_nogroup_delcover").css("display","none");
    }
    $("#ischeckall").attr("class","bg").css({"background-position":"0px -0px"});//.css修正鼠标经过checkbox的背景位置;
    $("#ischeckall").prop("checked",false);
    $("#checkall").prop("checked",false);
//    selectall();
});
// 全选、取消全选
$(".head").live('click', function () {
    //alert($("#ischeckall").prev("input[type=checkbox]").prop("checked"));
    var checkstate = $("#checkall").prop("checked");
    if (checkstate == true) {
        var flag = true;
        $(".mm_listout .bg").each(function () {
            if ($(this).attr("hostname") == "") {
                flag = false;
            }
            else {
                $(this).attr("class", "bged");
            }
        });
        if (flag == true) {
            $("#checkall").prop("checked", true);
            $("#ischeckall").removeClass("bg").addClass("bged").css({"background-position": "0px -35px"});//.css修正鼠标经过checkbox的背景位置;;
        }
        else {
            $("#checkall").prop("checked", false);
            $("#ischeckall").removeClass("bged").addClass("bg").css({"background-position": "0px -0px"});//.css修正鼠标经过checkbox的背景位置;
            layer.msg("请设置班级名称");
        }
    }
    else {
        $(".mm_listout .bged").each(function () {
            $(this).attr("class", "bg");
        });
        $("#checkall").prop("checked", false);
        $("#ischeckall").removeClass("bged").addClass("bg").css({"background-position": "0px -0px"});//.css修正鼠标经过checkbox的背景位置;
    }

});
});