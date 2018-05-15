
$(function(){
//判断在线和离线，处理文字变色
var onofflen=$(".hostoverflow").length;
for(i=0;i<onofflen;i++){
	var onlinetext=$(".hostoverflow").eq(i).prev().attr("class");
	if(onlinetext=="tree_content_onlinebg")
    {
		$(".hostoverflow").eq(i).attr("style","color:#5cb75a")
	}
    else
    {
        $(".hostoverflow").eq(i).attr("style","color:#333")
        $(".hostoverflow").eq(i).next().css({"background-position":"-1px -72px"})
    }
}
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
        var indexson=$(this).parent(".tree_title").index();
        var groupid = $(this).attr("name");
        $("#group_id").val(groupid);
        $(".tree_selected").attr("style","");
        $(".tree_selected").find(".tree_content_onlinebg").css({"background-position":"-144px -24px","margin-left":"0px"})
        $(".zhang").css("color","#333");
        var block=getindex.find(".tree_title").eq(indexson).find(".tree_content").css("display");
        var groupid = $("#group_id").val();
        var src = $("#root_path").val();
        src += "/dmanager/DManager_deviceList.do?groupid="+groupid+"&currentPage=1";
        if(block=="none"){
        	
            $("#go").attr("src",src);
            getindex.find(".tree_title").find(".tree_content").css("display","none");
            getindex.find(".tree_title").removeClass("tree_title_open").addClass("tree_title_close");
            getindex.find(".tree_title").eq(indexson).find(".tree_content").css("display","block");
            getindex.find(".tree_title").eq(indexson).removeClass("tree_title_close").addClass("tree_title_open");
            getindex.find(".tree_title").eq(indexson).find(".tree_titlea").addClass("zhang").css({"color":"#28b779"});
//         	if(getindex.find(".tree_title").eq(indexson).find(".tree_content").css("display")=="block"){
//         		getindex.find(".tree_title").find(".tree_content").find(".jkn_treecheckbox").css("left","0px")
//         		getindex.find(".tree_title").find(".tree_content").find(".jkn_treecheckboxed").css("left","0px")
//         	}else{
//         		getindex.find(".tree_title").find(".tree_content").find(".jkn_treecheckbox").css("left","54px")
//         		getindex.find(".tree_title").find(".tree_content").find(".jkn_treecheckboxed").css("left","54px")
//         	}
           change_size();
        }else{
            getindex.find(".tree_title").eq(indexson).find(".tree_content").css("display","none");
            getindex.find(".tree_title").eq(indexson).removeClass("tree_title_open").addClass("tree_title_close");
            getindex.find(".tree_title").eq(indexson).find(".tree_titlea").addClass("zhang").css({"color":"#28b779"});
            change_size();
        }
    })
//点击分组，选中子选项
$(".tree_titleaiconmain_jkn").live("click",function(){

	$(this).next().find(".jkn_treecheckbox").addClass("jkn_treecheckboxed").removeClass("jkn_treecheckbox");
	$(this).addClass("tree_titleaiconmained_jkn").removeClass("tree_titleaiconmain_jkn");
})
$(".tree_titleaiconmained_jkn").live("click",function(){
	$(this).next().find(".jkn_treecheckboxed").addClass("jkn_treecheckbox").removeClass("jkn_treecheckboxed");
	$(this).addClass("tree_titleaiconmain_jkn").removeClass("tree_titleaiconmained_jkn");
})
//点击子选项，勾选分组
$(".tree_contenta div").click(function(){
	var classes=$(this).attr("class");
	if(classes=="jkn_treecheckboxed"){
		$(this).addClass("jkn_treecheckbox").removeClass("jkn_treecheckboxed");
	}
    else if(classes=="jkn_treecheckbox")
    {
		$(this).addClass("jkn_treecheckboxed").removeClass("jkn_treecheckbox");
	}
    else
    {
        //不做处理
    }
	
	var selectlen=$(this).parents(".tree_content").find(".jkn_treecheckboxed").length;
	var len=$(this).parents(".tree_content").find(".tree_contenta").length;
	if(len==selectlen){
		$(this).parents(".tree_title").find(".tree_titleaiconmain_jkn").addClass("tree_titleaiconmained_jkn").removeClass("tree_titleaiconmain_jkn");
	}else{
		$(this).parents(".tree_title").find(".tree_titleaiconmained_jkn").addClass("tree_titleaiconmain_jkn").removeClass("tree_titleaiconmained_jkn");
	}
})
////二级菜单之间切换处理
//  $(".tree_contenta").click(function(){
//      $(".tree_selected").find(".tree_content_onlinebg").css({"background-position":"-144px -24px","margin-left":"0px"})
//      $(".tree_selected").attr("style","").removeClass("tree_selected").addClass("tree_contenta");
//      $(this).find(".jkn_treecheckbox").css("left","54px")
//      $(this).find(".jkn_treecheckboxed").css("left","54px")
//      $(".zhang").css("color","#333").removeClass("zhang");
//  })
    var group_selected = readCookie("group_selected");
    if(group_selected == "")
    {
        group_selected = 0;
    }
    $(".tree_content").eq(group_selected).parent().find(".tree_titlea").click();

//鼠标经过状态
$(".tree_contenta").mouseover(function(){
    if($(this).attr("class")=="tree_contenta"){
        $(this).css({"color":"#5cb75a","width":"100%"});
//      $(this).find(".tree_content_nousebg").css("margin-left","57px");
//      $(this).find(".tree_content_onlinebg").css("margin-left","57px")
//      $(this).find(".jkn_treecheckbox").css("left","54px")
//      $(this).find(".jkn_treecheckboxed").css("left","54px")
    }
}).mouseout(function(){
    if($(this).attr("class")=="tree_contenta"){
        $(this).css({"color":"#333","width":"100%","margin-left":"0px","border":"none"});
//      $(this).find(".tree_content_nousebg").css("margin-left","57px");
//      $(this).find(".tree_content_onlinebg").css("margin-left","57px")
//      $(this).find(".jkn_treecheckbox").css("left","0px")
//      $(this).find(".jkn_treecheckboxed").css("left","0px")
    }
})

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
$(".tree,.contentr").height($(".public_left").height())