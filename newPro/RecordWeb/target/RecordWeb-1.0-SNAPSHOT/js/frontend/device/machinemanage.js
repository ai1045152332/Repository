$(function(){
	var senlen=$(".win_list").eq(1).find("ul a").length-1;
	for(i=senlen;i>=0;i--){
		//alert(i)
		var opthtml=$(".win_list").eq(1).find("ul a li").eq(i).html()
        var title=$(".win_list").eq(1).find("ul a li").eq(i).attr("hostId");
		var htmlto="<a href=# style=display:none><li  hostId='" + title +"' >"+opthtml+"</li></a>";
        if($(".win_list").eq(0).find("ul").html().trim()==null || $(".win_list").eq(0).find("ul").html().trim()=="")
        {
            $(".win_list").eq(0).find("ul").append(htmlto);
        }
        else
        {
            $(".win_list").eq(0).find("ul a:eq(0)").before(htmlto);
        }

	}
	var firselen=$(".win_list").eq(0).find("ul a").length;
	var show=$(".win_list").eq(0).find("ul a:visible").length;
	if(firselen>show){
		for(i=firselen-show;i<firselen;i++){
		var opthtml=$(".win_list").eq(0).find("ul a").eq(i).html()
		var htmlto="<a href=# style=display:none>"+opthtml+"<span class='win_list_close'></span></a>";
		$(".win_list").eq(1).find("ul").append(htmlto)
		}
	}else{
		for(i=0;i<firselen;i++){
			var opthtml=$(".win_list").eq(0).find("ul a").eq(i).html()
			var htmlto="<a href=# style=display:none>"+opthtml+"<span class='win_list_close'></span></a>";
			$(".win_list").eq(1).find("ul").append(htmlto)
		}	
	}
	
	var first=$(".win_list").eq(0).find("ul a");
	var second=$(".win_list").eq(1).find("ul a");
	first.click(function(){
		var lefta_index=$(this).index();
		var lefta_content=$(this).html();
		first.eq(lefta_index).css("display","none")
		second.eq(lefta_index).css("display","block")
		scroll_y("win_lists","scrollson","scroll_y","scroll_ymove","scroll_x","scroll_xmove","del","wheely","");
		$(this).parents(".scrollson").css("margin-top","0px");
	})
	setTimeout(getsecond());
})
		
function getsecond(){
	var second=$(".win_list").eq(1).find("ul a");
	second.click(function(){
		var getindex=$(this).find("li").attr("hostId");
        console.log(getindex);
		var win_listlen=$(document).find(".win_list").eq(0).find("a").length;
        console.log(win_listlen);
		for(i=0;i<win_listlen;i++){
			var compareindex=$(".win_list").eq(0).find("ul a li").eq(i).attr("hostId");
            console.log(compareindex);
			if(getindex==compareindex){
				$(".win_list").eq(0).find("ul a").eq(i).css("display","block");
				$(this).css("display","none");
				scroll_y("win_lists1","scrollson","scroll_y","scroll_ymove","scroll_x","scroll_xmove","del","wheely","")
			}
		}
		$(this).parents(".scrollson").css("margin-top","0px");
	})
}			
