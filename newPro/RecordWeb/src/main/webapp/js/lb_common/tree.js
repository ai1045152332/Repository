$(function(){

//树形菜单
    $(".tree_content_nousebg").parent().css({"color":"#333","cursor":"default"});
    var titleblen=$(".tree_titleb").length;
    for(i=0;i<titleblen;i++){
        $(".public_left").eq(i).css("display","block");
    }
    $(".tree_titleb").click(function(){
        var show=$(".tree_titleb").next().css("display");
        if(show=="none"){
            $(".tree_titleb").next().css("display","block");
            $(".tree_titleb").addClass("tree_titleb_open").removeClass("tree_titleb_close");

        }else{
            $(".tree_titleb").next().css("display","none");
            $(".tree_titleb").addClass("tree_titleb_close").addClass("tree_titleb_close").removeClass("tree_titleb_open");
        }
    })
    $(".tree_titleb").next().find(".tree_titlea").click(function(){
        var getindex=$(".tree_titleb").next();
        var indexson=$(this).parent(".tree_title").index()-1;
        var groupid = $(this).attr("name");
        $("#group_id").val(groupid);
        $(".tree_selected").attr("style","");
        $(".tree_selected").find(".tree_content_onlinebg").css({"background-position":"-144px -24px","margin-left":"0px"})
        $(".zhang").css("color","#333");
        var block=getindex.find(".tree_title").eq(indexson).find(".tree_content").css("display");
        //coookie：xk_flag为0，表示从导播或监看页面返回
        if(readCookie("xk_flag") == "1")
        {
            nowselectpage = 1;
        }
        writeCookie("xk_flag","1");
        <!--巡课 Tree-->
        var pagesize = $.cookie("screenrecord");
        //alert(pagesize);
        if(!pagesize)
        {
            $.cookie('screenrecord', 2, {path: '/'});
            pagesize = 2;
        }
        var groupid = $("#group_id").val();
        var src = $("#root_path").val();
        var viewClassCameraName = $.cookie("camera");
        if(!viewClassCameraName)
        {
            viewClassCameraName = "教师学生";
            $.cookie('camera', "教师学生", {path: '/'});

    }
        viewClassCameraName = encodeURI(encodeURI(viewClassCameraName));
        //alert(viewClassCameraName)
        if(pagesize == 2)
        {
            src += "/viewclass/Viewclass_viewClass4.do?groupid="+groupid+"&pagesize="+pagesize+"&currentPage="+nowselectpage+"&viewClassCameraName="+viewClassCameraName;
        }
        else
        {
            src += "/viewclass/Viewclass_viewClass9.do?groupid="+groupid+"&pagesize="+pagesize+"&currentPage="+nowselectpage+"&viewClassCameraName="+viewClassCameraName;
        }

        //$("#record_vedio").attr("onclick","recording()");
        //$("#record_vedio_img").removeClass("xk_options_recordingicon_disable").addClass("xk_options_recordingicon");
        //$("#stop_record_vedio").attr("onclick","stoprecord()");
        //$("#stop_record_vedio_img").removeClass("xk_options_stoprecordingicon_disable").addClass("xk_options_stoprecordingicon");
        if(block=="none"){
            $("#go").attr("src",src);
            var flag = $("#select_auto").prop("checked");
            if (flag == true) {
                $(".bged").addClass("bg").removeClass("bged");
                $.cookie('selectauto', 0, {path: '/'});
                clear();
            }
            writeCookie("group_selected",indexson);
            getindex.find(".tree_title").find(".tree_content").css("display","none");
            getindex.find(".tree_title").removeClass("tree_title_open").addClass("tree_title_close");
            getindex.find(".tree_title").eq(indexson).find(".tree_content").css("display","block");
            getindex.find(".tree_title").eq(indexson).removeClass("tree_title_close").addClass("tree_title_open");
            getindex.find(".tree_title").eq(indexson).find(".tree_titlea").addClass("zhang").css({"color":"#28b779"});
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
           change_size();
           /* getindex.find(".tree_title").eq(indexson).find(".tree_content .tree_contenta").eq(0).click(function()
            {
                if($(this).find("span").attr("class")=="tree_content_nousebg"){
                    alert("2")
                    getindex.find(".tree_title").eq(indexson).find(".tree_titlea").addClass("zhang").css({"color":"#333"});
                    $(this).css({"color":"#333","cursor":"default"})
                    $(this).attr("class","tree_contenta");
                }else if($(this).find("span").attr("class")=="tree_content_abg"||"tree_content_onlinebg"){
                    alert("3")
                    getindex.find(".tree_title").eq(indexson).find(".tree_titlea").addClass("zhang").css({"color":"#333"});
                    $(this).attr("class","tree_selected");
        //			$(this).css({"color":"#333"}).siblings().css({"color":"#333"}).removeClass("tree_selected");
                    $(this).css({"color":"#fff","background":"#28b779","width":"258px","margin-left":"-63px"}).siblings().css({"color":"#333"}).removeClass("tree_selected");
                    $(this).find(".tree_content_onlinebg").css({"background-position":"-120px 0","margin-left":"60px"})
                }
            })*/
        }else{

            getindex.find(".tree_title").eq(indexson).find(".tree_content").css("display","none");
            getindex.find(".tree_title").eq(indexson).removeClass("tree_title_open").addClass("tree_title_close");
            getindex.find(".tree_title").eq(indexson).find(".tree_titlea").addClass("zhang").css({"color":"#28b779"});
            change_size();
        }
    })
//二级菜单之间切换处理

    //$(".tree_contenta").click(function(){
    $(".tree_contenta").click(function(){
        $(".tree_selected").find(".tree_content_onlinebg").css({"background-position":"-144px -24px","margin-left":"0px"})
        $(".tree_selected").attr("style","").removeClass("tree_selected").addClass("tree_contenta");
        $(".zhang").css("color","#333").removeClass("zhang");
    })
    var group_selected = readCookie("group_selected");
    if(group_selected == "")
    {
        group_selected = 0;
    }
    $(".tree_content").eq(group_selected).parent().find(".tree_titlea").click();
//树形菜单滚动条模拟
    $('#otp_vedeoabout_rvideo').perfectScrollbar();
    prettyPrint();
//鼠标经过状态
$(".tree_contenta").mouseover(function(){
//	if($(this).attr("class")=="tree_contenta"){
//		$(this).css({"color":"#5cb75a","width":"255px","border-left":"3px solid #5cb75a","margin-left":"-63px"});
//		$(this).find(".tree_content_nousebg").css("margin-left","60px");
//		$(this).find(".tree_content_onlinebg").css("margin-left","60px")
//	}else if($(this).attr("class")=="tree_selected"){
//		$(this).css({"color":"#5cb75a","width":"255px","border-left":"3px solid #5cb75a","margin-left":"-63px","color":"#fff"});
//	}
}).mouseout(function(){
//	if($(this).attr("class")=="tree_contenta"){
//		$(this).css({"color":"#333","width":"255px","border-left":"3px solid #fff","margin-left":"-63px"});
//	}else if($(this).attr("class")=="tree_selected"){
//		$(this).css({"color":"#5cb75a","width":"255px","border-left":"3px solid #5cb75a","margin-left":"-63px","color":"#fff"});
//	}
})
//树形菜单滚动条模拟
function change_size() {
	var exphei=document.documentElement.clientHeight;
	var expwidth=document.documentElement.clientWidth;
	var treewid=expwidth*0.22
	if(expwidth*0.22<260){
		treewid=260
	}
    //$("#otp_vedeoabout_rvideo").width(treewid).height(exphei*0.90*0.90);
    $("#otp_vedeoabout_rvideo").height(exphei*0.90*0.90);
    // update perfect scrollbar
    $('#otp_vedeoabout_rvideo').perfectScrollbar('update');
}  
var treetitlelen=$(".tree_title").length;
for(i=0;i<treetitlelen;i++){
	var treecontenta=$(".tree_title").eq(i).children(".tree_content").children(".tree_contenta")	
	var groupnum="("+treecontenta.length+")";
		$(".tree_title").eq(i).children("a").children(".groupnum").text(groupnum)	
}

})


function writeCookie(name,value)
{
    document.cookie=name+"="+escape(value)+";path=/";
}

/*读取cookie*/
function readCookie(name)
{
    cookieValue="";
    search1=name+"=";
    if(document.cookie.length>0)
    {
        offset=document.cookie.indexOf(search1) ;
        if(offset!=-1)
        {
            offset+=search1.length;
            end=document.cookie.indexOf(";",offset);
            if(end==-1)
                end= document.cookie.length;
            cookieValue=unescape(document.cookie.substring(offset,end));
        }
    }
    return cookieValue;
}
