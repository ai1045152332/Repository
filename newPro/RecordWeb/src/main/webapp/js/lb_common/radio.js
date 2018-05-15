$(function(){
	$(document.body).find("input[type=radio]").css("display","none");//初始化隐藏radio
	var radiolen=$(".radio").length;
	//alert(rialen)
	for(i=0;i<radiolen;i++){
		check(i);
	}
//	$("#all0").find(".headr").eq(0).click();
})
function check(args){
	var checklen=$("#all"+args).find(".headr").length;
	//alert(checklen)
	$("#all"+args).find(".headr").click(function(){
		var index=$(this).index();
		var flag=$("#radio"+args).find("input[type=radio]").eq(index).prop("checked");
		if(flag==false){
			$("#radio"+args).find("input[type=radio]").eq(index).prop("checked",true);
			for(j=0;j<checklen;j++){
				if(j==index){
					$("#all"+args).find(".headr").eq(j).find(".bgr").addClass("bgred").removeClass("bgr");
				}else{
					$("#all"+args).find(".headr").eq(j).find(".bgred").addClass("bgr").removeClass("bgred");
				}
			}
		}
	})
	
}
