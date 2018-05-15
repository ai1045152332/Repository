//判断浏览器是否重新加载，如果是，则默认为刷新操作
document.onreadystatechange = function(){
    if(document.readyState == "complete"){ //当页面加载状态为完全结束时进入
        $.cookie("settreeid","")
        //清除选中状态
        $(".jkn_treecheckboxed").addClass("jkn_treecheckbox").removeClass("jkn_treecheckboxed")
        $(".tree_titleaiconmained_jkn").addClass("tree_titleaiconmain_jkn").removeClass("tree_titleaiconmained_jkn")
        $(".iframe").contents().find(".xk_video_selected").find(".jkn_mc_bz").hide()
        $(".iframe").contents().find(".xk_video_selected").css("box-shadow","none")
        $(".iframe").contents().find(".xk_video_selected").addClass("xk_video").removeClass("xk_video_selected")
    }
}
function addidcookie(){
    var treeid="";
    var checkboxlened=$(".jkn_treecheckboxed").length;
    if(checkboxlened == 0){
        $.cookie("settreeid","");
    }
    for(i=0;i<checkboxlened;i++){
        var thisid=$(".jkn_treecheckboxed").eq(i).attr("hostid")

        treeid=treeid+thisid+",";//获取id数组
        // alert(treeid)
        $.cookie("settreeid",treeid)
    }
}
function addSpecCookie(dspec){
    var dspecs = $.cookie("dspec");
    if(dspecs){
        var dspecarr = dspecs.split(",");
        for(var y=0;y<dspecs.length;y++){
            if(dspecs[y] != dspec){
                $.cookie("dspec",dspecs+","+dspec);
            }
        }
    }else{
        $.cookie("dspec",dspec);
    }
}

$(function(){
    $.cookie("settreeid","")
//页面加载时判断各个分组里面是否全部选中，如果选中，勾选分组
    var firsttreeinfo=$(".tree_title").length;
    for(i=0;i<firsttreeinfo;i++){
        var tree_contenta= $(".tree_title").eq(i).find(".tree_contenta");
        for(n=0,k=0;n<tree_contenta.length;n++){
            var tree_contentaid=tree_contenta.eq(n).attr("id");
            var idstr = $.cookie("settreeid");
            var idarr= $.cookie("settreeid").split(",");
            var idcookie="";
            for(j=0;j<(idarr.length-1);j++){
                if(tree_contentaid==idarr[j]){
                    k++;
                    if(tree_contenta.length==k){
                        $(".tree_title").eq(i).find(".tree_titleaiconmain_jkn").addClass("tree_titleaiconmained_jkn").removeClass("tree_titleaiconmain_jkn")
                    }
                }
            }
        }

    }
//判断在线和离线，处理文字变色
    var onofflen=$(".hostoverflow").length;
    for(i=0;i<onofflen;i++){
        var onlinetext=$(".hostoverflow").eq(i).prev().attr("class");
        if(onlinetext=="tree_content_onlinebg"){
            $(".hostoverflow").eq(i).attr("style","color:#5cb75a")
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
    //$(".tree_titleb").next().find(".tree_titlea").click(function(){
    //    var getindex=$(".tree_titleb").next();
    //    var indexson=$(this).parent(".tree_title").index()-1;
    //    $.cookie("group_selected",indexson)
    //    var groupid = $(this).attr("name");
    //    $("#group_id").val(groupid);
    //    $(".tree_selected").attr("style","");
    //    $(".tree_selected").find(".tree_content_onlinebg").css({"background-position":"-144px -24px","margin-left":"0px"})
    //    $(".zhang").css("color","#333");
    //    var block=getindex.find(".tree_title").eq(indexson).find(".tree_content").css("display");
    //    var groupid = $("#group_id").val();
    //    var src = $("#root_path").val();
    //    var pageSize = $.cookie("screen");
    //    //alert(pagesize);
    //    if(!pageSize)
    //    {
    //        $.cookie('screen', 4, {path: '/'});
    //        pageSize = 4;
    //    }
    //    src += "/dmanager/DManager_deviceList.do?groupid="+groupid+"&currentPage=1&pageSize="+pageSize;
    //    if(block=="none"){
    //        //$("#go").attr("src",src);
    //        getindex.find(".tree_title").find(".tree_content").css("display","none");
    //        getindex.find(".tree_title").removeClass("tree_title_open").addClass("tree_title_close");
    //        getindex.find(".tree_title").eq(indexson).find(".tree_content").css("display","block");
    //        getindex.find(".tree_title").eq(indexson).removeClass("tree_title_close").addClass("tree_title_open");
    //        getindex.find(".tree_title").eq(indexson).find(".tree_titlea").addClass("zhang").css({"color":"#28b779"});
    //      if(getindex.find(".tree_title").eq(indexson).find(".tree_content").css("display")=="block"){
    //          getindex.find(".tree_title").find(".tree_content").find(".jkn_treecheckbox").css("left","0px")
    //          getindex.find(".tree_title").find(".tree_content").find(".jkn_treecheckboxed").css("left","0px")
    //      }else{
    //          getindex.find(".tree_title").find(".tree_content").find(".jkn_treecheckbox").css("left","54px")
    //          getindex.find(".tree_title").find(".tree_content").find(".jkn_treecheckboxed").css("left","54px")
    //      }
    //        change_size();
    //    }else{
    //        getindex.find(".tree_title").eq(indexson).find(".tree_content").css("display","none");
    //        getindex.find(".tree_title").eq(indexson).removeClass("tree_title_open").addClass("tree_title_close");
    //        getindex.find(".tree_title").eq(indexson).find(".tree_titlea").addClass("zhang").css({"color":"#28b779"});
    //        change_size();
    //    }
    //})


//点击分组，选中子选项
    $(".tree_titleaiconmain_jkn").live("click",function(){
        var nowgroup=$(this).parents(".tree_title").index()-1;
        $(this).next().find(".jkn_treecheckbox").addClass("jkn_treecheckboxed").removeClass("jkn_treecheckbox");
        $(this).addClass("tree_titleaiconmained_jkn").removeClass("tree_titleaiconmain_jkn");
        //需要选中选择iframe大屏设备
        if( $(this).prev().prev().attr("class")=="tree_title tree_title_open"|| $.cookie("group_selected")==nowgroup){
            var devicelen=$(".iframe").contents().find(".showhidden");
            for(i=0;i<devicelen.length;i++){
                if(devicelen.eq(i).find(".allarea").children("div").eq(0).attr("class")=="xk_video"){
                    devicelen.eq(i).find(".allarea").children("div").eq(0).click()

                }
            }
        }
        //alert($(this).next().find(".jkn_treecheckbox").length+"-----")
        addidcookie()

    })
    $(".tree_titleaiconmained_jkn").live("click",function(){
        $(this).next().find(".jkn_treecheckboxed").addClass("jkn_treecheckbox").removeClass("jkn_treecheckboxed");
        $(this).addClass("tree_titleaiconmain_jkn").removeClass("tree_titleaiconmained_jkn");
        //需要取消iframe大屏设备,如果是选中的当前分组
        //tree_title_open
        if($(this).parents(".tree_title").attr("class")=="tree_title tree_title_open"||$(this).parents(".tree_title").attr("class")=="tree_title tree_title_close"){
            var devicelen=$(".iframe").contents().find(".showhidden");
            for(i=0;i<devicelen.length;i++){
                if(devicelen.eq(i).find(".allarea").children("div").eq(0).attr("class")=="xk_video_selected"){
                    devicelen.eq(i).find(".allarea").children("div").eq(0).click()
                }
            }
        }
        //需要删除cookie;遍历左侧树所有id,从cookie中删除
        var treecontentalen=$(this).next().find(".tree_contenta");
        for(i=0;i<treecontentalen.length;i++){
            var treeid=treecontentalen.eq(i).attr("id");
            var idstr = $.cookie("settreeid");
            var idarr= $.cookie("settreeid").split(",");
            var idcookie="";
            for(j=0;j<(idarr.length-1);j++){
                if(idarr[j]==treeid){
                    idarr[j]="";
                    idcookie=idcookie;
                }else{
                    idarr[j]= idarr[j]
                    idcookie=idcookie+ idarr[j]+",";
                }
                $.cookie("settreeid",idcookie)
            }
        }
        //alert($(this).next().find(".jkn_treecheckbox").length+"=======")
    })
//点击子选项，勾选分组
    $(".tree_contenta div").click(function(){
        var classes=$(this).attr("class");
        //取消
        //alert(classes)
        if(classes=="jkn_treecheckboxed"){
            $(this).addClass("jkn_treecheckbox").removeClass("jkn_treecheckboxed");
            //点击取消左侧树选中操作，取消iframe大屏设备选中
            var thisid=$(this).attr("hostid")
            //alert("取消")
            var dspec = $("#"+thisid).attr("dspec");
            $(".iframe").contents().find("#"+thisid).addClass("xk_video").removeClass("xk_video_selected")
            $(".iframe").contents().find("#"+thisid).attr("bingo","none")
            $(".iframe").contents().find("#"+thisid).css("box-shadow","none")
            $(".iframe").contents().find("#"+thisid).find(".jkn_mc_bz").hide()
            addidcookie()
        }else{ //选中
            $(this).addClass("jkn_treecheckboxed").removeClass("jkn_treecheckbox");
            var thisid=$(this).attr("hostid")
           // alert("选中");
            $(".iframe").contents().find("#"+thisid).addClass("xk_video_selected").removeClass("xk_video")
            $(".iframe").contents().find("#"+thisid).attr("bingo","done")
            $(".iframe").contents().find("#"+thisid).css("box-shadow","0px 0px 0px 3px rgb(92, 183, 90)")
            $(".iframe").contents().find("#"+thisid).find(".jkn_mc_bz").show()
            addidcookie()
        }
        //addSpecCookie(dspec);
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
//          $(this).find(".tree_content_nousebg").css("margin-left","57px");
//          $(this).find(".tree_content_onlinebg").css("margin-left","57px")
//          $(this).find(".jkn_treecheckbox").css("left","54px")
//          $(this).find(".jkn_treecheckboxed").css("left","54px")
        }
    }).mouseout(function(){
        if($(this).attr("class")=="tree_contenta"){
            $(this).css({"color":"#333","width":"100%","margin-left":"0px","border":"none"});
//          $(this).find(".tree_content_onlinebg").css("margin-left","3px")
//          $(this).find(".tree_content_nousebg").css("margin-left","3px")
//          $(this).find(".jkn_treecheckbox").css("left","0px")
//          $(this).find(".jkn_treecheckboxed").css("left","0px")
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
function change_size() {
 	var exphei=document.documentElement.clientHeight;
	var expwidth=document.documentElement.clientWidth;
	if(expwidth<=1200){
		expwidth=1200
	}
	if(exphei<=590){
		exphei=590
	}
	var treewid=expwidth*0.22
    $("#otp_vedeoabout_rvideo").width(treewid).height(exphei*0.86*0.93);
    // update perfect scrollbar
    $('#otp_vedeoabout_rvideo').perfectScrollbar('update');
}