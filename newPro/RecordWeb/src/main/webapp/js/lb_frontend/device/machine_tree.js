$(function(){
//树形菜单
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
	$(".tree_selected").attr("style","");
	$(".zhang").css("color","#333");
	var block=getindex.find(".tree_title").eq(indexson).find(".tree_content").css("display");
	if(block=="none"){
		getindex.find(".tree_title").eq(indexson).find(".tree_content").css("display","block");
		getindex.find(".tree_title").eq(indexson).removeClass("tree_title_close").addClass("tree_title_open");
		getindex.find(".tree_title").eq(indexson).find(".tree_titlea").addClass("zhang").css({"color":"#333"});
		getindex.find(".tree_title").eq(indexson).find(".tree_content .tree_contenta").click(function(){
		 if($(this).find("span").attr("class")=="tree_content_abg"||"tree_content_onlinebg"){
			getindex.find(".tree_title").eq(indexson).find(".tree_titlea").addClass("zhang").css({"color":"#333"});
			$(this).attr("class","tree_selected");
			$(this).css({"color":"#333"}).siblings().css({"color":"#333"}).removeClass("tree_selected");
			}
		})
		change_size();
	}else{
		getindex.find(".tree_title").eq(indexson).find(".tree_content").css("display","none");
		getindex.find(".tree_title").eq(indexson).removeClass("tree_title_open").addClass("tree_title_close");
		change_size();
	}
})
//二级菜单之间切换处理
$(".tree_contenta").click(function(){
	$(".tree_selected").attr("style","").removeClass("tree_selected").addClass("tree_contenta");
	$(".zhang").css("color","#333").removeClass("zhang");
})
//树形菜单滚动条模拟
 $('#win_moveto').perfectScrollbar();
	 prettyPrint();
})
//树形菜单滚动条模拟
function change_size() {
    var width = parseInt($("#Width").val());
    var height = parseInt($("#Height").val());

    if(!width || isNaN(width)) {
        width = 258;
    }
    if(!height || isNaN(height)) {
        height = 255;
    }
	$("#win_moveto").width(width).height(height);
    $('#win_moveto').perfectScrollbar('update');
}
