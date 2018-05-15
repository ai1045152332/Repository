//表格数据重加载
function  dataReload(url)
{
    $.get(url,function(data){
        $("#public_right_center").html(data);
    },'html')
}
$(function(){
//判断有无子节点，没有去除箭头
$(".tree_title:not(:has(div))").css("background","none");
//树形菜单

$(".tree_titlea").click(function(){
    var rootpath = $("#root_path").val();
	var index=$(this).parent(".tree_title").index();
	$(this).css("color","#28b779");
	$(this).parents(".tree_title").siblings().find("a").css("color","#333");
	$(".tree_selected").attr("style","");
    var linktype=$(this).attr("linktype");
    if(linktype!=null && linktype!="")
    {
        dataReload($(this).attr("linkpage"));
        /*$.get($(this).attr("linkpage"),function(data){
            $("#public_right_center").html(data);
        },'html')*/
    }
	var block=$(".tree_title").eq(index).find(".tree_content").css("display");
	if(block=="none"){
		$(".tree_title").eq(index).find(".tree_content").css("display","block");
		$(".tree_title").eq(index).removeClass("tree_title_close").addClass("tree_title_open");
		$(".tree_title").eq(index).find(".tree_content a").click(function(){
			//$(this).attr("click","clicked");//标识选中
			$(this).attr("class","tree_selected");
			$(this).parents(".tree_title").find("a").css("color","#28b779");
            var tree_name = $(this).attr("name");
            if(tree_name == "spec")
            {
                $.get(rootpath+"/project/Spec_specList.do",function(data){
                    $("#public_right_center").html(data);
                },'html')
            }
            else if(tree_name == "command")
            {
                $.get(rootpath+"/project/Devicecomm_commList.do",function(data){
                    $("#public_right_center").html(data);
                },'html')
            }
			$(this).css({"background":"#333","color":"#fff"}).siblings().css({"background":"#fff","color":"#333"}).removeClass("tree_selected");
		})	
	}else{
		$(".tree_title").eq(index).find(".tree_content").css("display","none");
		$(".tree_title").eq(index).removeClass("tree_title_open").addClass("tree_title_close");
		
	}

})
//二级菜单之间切换处理
$(".tree_content a").click(function(){
	$(".tree_selected").parents(".tree_title").find("a").css("color","#333");
	$(".tree_selected").parents(".tree_title").siblings().find("a").css("color","#333");
	$(".tree_selected").attr("style","").removeClass("tree_selected");
})
//checkbox美化


//自适应屏幕高度
var docheight=document.documentElement.clientHeight-104+"px";
//alert(docheight)
if(document.documentElement.clientHeight>740){
//alert(docheight+"a")
  	 	$(".public_right").css({"height":docheight});
		$(".public_left").css({"height":docheight});
  	 }
 if(document.documentElement.clientHeight<740){
 //alert(docheight+"aa")
 	$(".public_right").css({"height":"636px"});
	$(".public_left").css({"height":"636px"});
 }
//alert(docheight)
$(window).resize(function() {
  		if(document.documentElement.clientHeight>740){
  	 	$(".public_right").css({"height":docheight})
		$(".public_left").css({"height":docheight});
  	 }
  	 if(document.documentElement.clientHeight<740){
  	 	$(".public_right").css({"height":"636px"});
		$(".public_left").css({"height":"636px"});
  	 }
  	})

    $.ajaxSetup({

        contentType:"application/x-www-form-urlencoded;charset=utf-8",

        complete:function(XMLHttpRequest,textStatus){

            //通过XMLHttpRequest取得响应头，sessionstatus

            var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus");

            if(sessionstatus=="timeout"){

                //这里怎么处理在你，这里跳转的登录页面
                var rootpath = document.getElementById("root_path").value;
                window.location.replace(rootpath+"/pages/project/login.jsp");
            }
        }
    });
})

