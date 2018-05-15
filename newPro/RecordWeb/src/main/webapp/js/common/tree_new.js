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
	var indexson=$(this).parent(".tree_title").index();
    if(indexson>1){
        indexson=indexson-1
    }
	$(".tree_selected").attr("style","");
	$(".zhang").css("color","#333");
	var block=getindex.find(".tree_title").eq(indexson).find(".tree_content").css("display");
    //alert($(this).attr("name")+"++++++");
    var test=$("#hostlistInfo");
    if(test!=null)
    {
        var urlhead=$("#hostgroupSelected").attr("urlhead");
        var hostgroupId=$(this).attr("name");
        var url = urlhead + "/host/Host_loadHostByHostgroup.do";
        $.post(url,{'hostgroupId':hostgroupId},function(data){
            if(data!=null)
            {
                test.html(data);
            }
        },"html");
        //alert(test.attr("style"));
    }
    if(block=="none"){
        //alert($(this).attr("name")+"aaaaaaaa"
		getindex.find(".tree_title").eq(indexson).find(".tree_content").css("display","block");
		getindex.find(".tree_title").eq(indexson).removeClass("tree_title_close").addClass("tree_title_open");
		getindex.find(".tree_title").eq(indexson).find(".tree_titlea").addClass("zhang").css({"color":"#28b779"});
		getindex.find(".tree_title").eq(indexson).find(".tree_content .tree_contenta").click(function(){
			if($(this).find("span").attr("class")=="tree_content_nousebg"){
				getindex.find(".tree_title").eq(indexson).find(".tree_titlea").addClass("zhang").css({"color":"#28b779"});
				$(this).css({"color":"#333","cursor":"default"})
				$(this).attr("class","tree_contenta");
			}else if($(this).find("span").attr("class")=="tree_content_abg"||"tree_content_onlinebg"){
			getindex.find(".tree_title").eq(indexson).find(".tree_titlea").addClass("zhang").css({"color":"#28b779"});
			$(this).attr("class","tree_selected");
			$(this).css({"color":"#28b779"}).siblings().css({"color":"#333"}).removeClass("tree_selected");
                 var test=$("#hostlistInfo");
                 var nodisplay=$("#display");
                 //alert("aa")
                 if(nodisplay==null) {
                     if (test != null) {
                         var urlhead = $("#hostgroupSelected").attr("urlhead");
                         var hostid = $(this).attr("reqhostId");
                         var url = urlhead + "/device/Device_deviceList.do"
                         $.post(url, {'hostId': hostid}, function (data) {
                         if (data != null) {
                         test.html(data);
                            }
                        }, "html");
                     }
                }
			}
		})	
	}else{
		getindex.find(".tree_title").eq(indexson).find(".tree_content").css("display","none");
		getindex.find(".tree_title").eq(indexson).removeClass("tree_title_open").addClass("tree_title_close");
		change_size();
	}

    //alert($(this).attr("name")+"------")
    /*
     *Author:Wen
     *Date:2014.11.7
     */
    /*alert($(this).attr("name"));
    var urlhead=$("#hostgroupSelected").attr("urlhead");
    var hostgroupId=$(this).attr("name");
    var url = urlhead + "/host/Host_loadHostByHostgroup.do";
    $.post(url,{'hostgroupId':hostgroupId},function(data){
        $(.mm_right).html(data);
      //  window.location.reload();
    },"html");
*/

})
//二级菜单之间切换处理
$(".tree_contenta").click(function(){
	$(".tree_selected").attr("style","").removeClass("tree_selected").addClass("tree_contenta");
	$(".zhang").css("color","#333").removeClass("zhang");


    //alert(5555);
})
//$(".tree_content").eq(0).parent().find(".tree_titlea").click();
//树形菜单滚动条模拟
$('#otp_vedeoabout_rvideo').perfectScrollbar();
prettyPrint();
})
//树形菜单滚动条模拟
function change_size() {
    var width = parseInt($("#Width").val());
    var height = parseInt($("#Height").val());

    if(!width || isNaN(width)) {
        width = 260;
    }
    if(!height || isNaN(height)) {
        height = 654;
    }
	$("#otp_vedeoabout_rvideo").width(width).height(height);

    // update perfect scrollbar
    $('#otp_vedeoabout_rvideo').perfectScrollbar('update');
}
/*
function group(turl,tid,tname)
{
    $.post(turl,{'groupId':tid,'groupName':tname},function(data){
        window.location.reload();
    });
}*/
