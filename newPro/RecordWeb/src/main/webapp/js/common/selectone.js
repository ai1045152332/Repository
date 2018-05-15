$(function(){
			var sdefault=$(".select").find("option:selected");//获取默认选中值

			$(".selectdiv").text(sdefault.text())//赋值给模拟div的text
			var optlen=$(".select").find("option").length;//获取option的长度

			//alert(optlen)
			for(var i=0;i<optlen;i++){
				var optval=$(".select").find("option").eq(i).text()
				//alert(optval)
				//var htmlto="<a href=#>"+optval+"</a>";

				var htmlto="<a href='#'>"+optval+"</a>";
				//alert(htmlto);
				$(".selectdivul").append(htmlto)
			}
			//点击模拟下拉框，显示或隐藏内容
			$(".selectdiv").click(function(){
				var display=$(".selectdivul").css("display");
				if(display=="none"){
					$(".selectdivul").css("display","block")
				}else{
					$(".selectdivul").css("display","none")
				}
			})
            var optionvalue = parseInt(sdefault.val())-1;
			$(".selectdivul").find("a").eq(optionvalue).css({"color":"#fff","background":"#28b779"}).siblings().css({"color":"#333","background":"#fff"});
			//点击模拟a标签，重新选定原生select选项
			$(".selectdivul a").click(function(){
				var index=$(this).index();
				//alert(index)
				$(".select").find("option").eq(index).prop("selected","selected");
                var select_val = $(".select").val();
                //alert(select_val);
                getData(select_val);
				//alert($(".select").find("option:selected").index())
                $(".selectdivul").find("a").eq(index).css({"color":"#fff","background":"#28b779"}).siblings().css({"color":"#333","background":"#fff"});
				$(".selectdivul").css("display","none");
				$(".selectdiv").text($(".select").find("option").eq(index).text());
			})
			//
			$(".selectdivul a").mouseover(function(){
				//alert($(this).css("background-color"))
				if($(this).css("background-color")=="#fff"||$(this).css("background-color")=="rgb(255, 255, 255)"){
					$(this).css({"background":"#dbdbdb","color":"#333"})
				}else if($(this).css("background-color")=="#28b779"||$(this).css("background-color")=="rgb(40, 183, 121)"){
					return false;
				}
			}).mouseout(function(){
				if($(this).css("background-color")=="#333"||$(this).css("background-color")=="rgb(219, 219, 219)"){
					$(this).css({"background":"#fff","color":"#333"})
				}else if($(this).css("background-color")=="#28b779"||$(this).css("background-color")=="rgb(40, 183, 121)"){
					return false;
				}
			})
			//点击空白区域，隐藏select模拟
			   $(document).bind("click",function(e){
					var target = $(e.target);
					//alert(target.closest(".selectdivall").length)
					if(target.closest(".selectdivall").length == 0){
						$(".selectdivul").hide();
					}
				})

		})