$(function(){
//树形菜单
$(".tree_titlea").click(function(){
	var index=$(this).parent(".tree_title").index();
	$(".selected").attr("style","");
	var block=$(".tree_title").eq(index).find(".tree_content").css("display");
	if(block=="none"){
		$(".tree_title").eq(index).find(".tree_content").css("display","block");
		$(".tree_title").eq(index).removeClass("tree_title_close").addClass("tree_title_open");
		$(".tree_title").eq(index).find(".tree_content a").click(function(){
			//$(this).attr("click","clicked");//标识选中
			$(this).attr("class","selected");
			$(this).css({"background":"#333","color":"#fff"}).siblings().css({"background":"#fff","color":"#333"}).removeClass("selected");
		})	
	}else{
		$(".tree_title").eq(index).find(".tree_content").css("display","none");
		$(".tree_title").eq(index).removeClass("tree_title_open").addClass("tree_title_close");
		
	}
})
//二级菜单之间切换处理
$(".tree_content a").click(function(){
	$(".selected").attr("style","").removeClass("selected");
})
//checkbox美化
$(document.body).find("input[type=checkbox]").prop("checked",false).css("display","none");
	var checklen=$(".all").find("input[type=checkbox]").length;
	//alert(checklen);
	$(".check_head").click(function(){
		//alert($(this).parents(".tr").index()-1)
		var index=$(this).parents(".tr").index();
		//var index=$(this).index();
		//alert(index)
		var flag=$(".all").find("input[type=checkbox]").eq(index).prop("checked");
		//alert(flag)
		if(flag==true){
			$(".all").find("input[type=checkbox]").eq(index).prop("checked",false);
			$(".check_head").eq(index).find(".bged").removeClass("bged").addClass("bg").css({"background-position":"0px -0px"});//.css修正鼠标经过checkbox的背景位置;
		}else{
			$(".all").find("input[type=checkbox]").eq(index).prop("checked",true);
			$(".check_head").eq(index).find(".bg").removeClass("bg").addClass("bged").css({"background-position":"0px -35px"});//.css修正鼠标经过checkbox的背景位置;;
		}
		checkedall();
	})
	//鼠标经过未选中状态
	$(".check_head").mouseover(function(){	
		$(this).find(".bg").css("background-position","0 -70px");
	}).mouseout(function(){
		$(this).find(".bg").css("background-position","0 0px")
	})
	//鼠标经过选中状态
	$(".check_head").mouseover(function(){
		$(this).find(".bged").css("background-position","0 -105px");
	}).mouseout(function(){
		$(this).find(".bged").css("background-position","0 -35px")
	})
//确认是都全部选中
function checkedall(){
	var alllen=$(".all").find("input[type=checkbox]").length;
	for(i=0,j=0;i<alllen;i++){
		var flog=$(".all").find("input[type=checkbox]").eq(i).prop("checked");
		//alert(flog)
		if(flog==true){
			j++;
			//alert(j+"==")
			//alert(j)
			if(j==alllen){
				$("#check_head").prop("checked",true);
				$("#checkallhead").removeClass("bg").addClass("bged");
			}else{
				$("#check_head").prop("checked",false);
				$("#checkallhead").removeClass("bged").addClass("bg");
			}
		}
	}
}

//自适应屏幕高度
var docheight=document.documentElement.clientHeight-140+"px";
if(document.documentElement.clientHeight>740){
  	 	$(".public_right").css({"height":docheight})
  	 }
 if(document.documentElement.clientHeight<740){
 	$(".public_right").css({"height":"466px","min-height":"466px"})
 }
//alert(docheight)
$(window).resize(function() {
  		if(document.documentElement.clientHeight>740){
  	 	$(".public_right").css({"height":docheight})
  	 }
  	 if(document.documentElement.clientHeight<740){
  	 	$(".public_right").css({"height":"466px","min-height":"466px"})
  	 }
  	})

})

//全选按钮
function checkall(){
	//alert($("check_head").attr("checked"))
	var alllen=$(".all").find("input[type=checkbox]").length;
	if($("#check_head").prop("checked")==false){
		$("#check_head").prop("checked",true);
		$("#checkallhead").addClass("bged").removeClass("bg");
		for(i=0;i<alllen;i++){
			$(".all").find("input[type=checkbox]").eq(i).prop("checked",true);
			$(".check_head").eq(i).find(".bg").removeClass("bg").addClass("bged").css({"background-position":"0px -35px"});//.css修正鼠标经过checkbox的背景位置;;;
		}
	}else if($("#check_head").prop("checked")==true){
		$("#check_head").prop("checked",false);
		$("#checkallhead").addClass("bg").removeClass("bged");
		for(i=0;i<alllen;i++){
			$(".all").find("input[type=checkbox]").eq(i).prop("checked",false);
			$(".check_head").eq(i).find(".bged").removeClass("bged").addClass("bg").css({"background-position":"0px 0px"});//.css修正鼠标经过checkbox的背景位置;;;
		}	
	}
}