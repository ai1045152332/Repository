$(function(){
	$(document.body).find("input[type=checkbox]").prop("checked",false).css("display","none");
	$(".head").click(function(){
		var index=$(this).index();
		//alert(index)
		var flag=$(".checkbox").find("input[type=checkbox]").eq(index).prop("checked");
		if(flag==true){
			//alert(flag)
			$(".checkbox").find("input[type=checkbox]").eq(index).prop("checked",false);
			$(".head").eq(index).find(".bged").removeClass("bged").addClass("bg").css({"background-position":"0px -0px"});//.css修正鼠标经过checkbox的背景位置
			//alert(flag+"a")
		}else{
			//alert(flag)
			$(".checkbox").find("input[type=checkbox]").eq(index).prop("checked",true);
			$(".head").eq(index).find(".bg").removeClass("bg").addClass("bged").css({"background-position":"0px -35px"});//.css修正鼠标经过checkbox的背景位置;
		}
		checkedall();
	})
	//鼠标经过未选中状态
	$(".head").mouseover(function(){	
		$(this).find(".bg").css("background-position","0 -70px");
	}).mouseout(function(){
		$(this).find(".bg").css("background-position","0 0px")
	})
	//鼠标经过选中状态
	$(".head").mouseover(function(){
		$(this).find(".bged").css("background-position","0 -105px");
	}).mouseout(function(){
		$(this).find(".bged").css("background-position","0 -35px")
	})
})
//全选
function checkall(){
	if($("#head").prop("checked")==false){
		$("#head").prop("checked",true);
		$(".allcheck").find(".bg").removeClass("bg").addClass("bged");
		$(".checkbox").find("input[type=checkbox]").prop("checked",true);
		$(".all").find(".bg").removeClass("bg").addClass("bged");
	}else{
		$("#head").prop("checked",false);
		$(".allcheck").find(".bged").removeClass("bged").addClass("bg");
		$(".checkbox").find("input[type=checkbox]").prop("checked",false);
		$(".all").find(".bged").removeClass("bged").addClass("bg");
	}
}
//确认是都全部选中
function checkedall(){
	var alllen=$(".checkbox").find("input[type=checkbox]").length;
	for(i=0,j=0;i<alllen;i++){
		var flog=$(".checkbox").find("input[type=checkbox]").eq(i).prop("checked");
		//alert(flog)
		if(flog==true){
			j++;
			if(j==alllen){
				$("#head").prop("checked",true);
				$(".allcheck").find(".bg").removeClass("bg").addClass("bged");
			}else{
				$("#head").prop("checked",false);
				$(".allcheck").find(".bged").removeClass("bged").addClass("bg");
			}
		}
	}
}