/**
 * Created by lch on 2014/10/28.
 */
$(document.body).find("input[type=checkbox]").prop("checked",false).css("display","none");
var checklen=$(".all").find("input[type=checkbox]").length;
//alert(checklen);
$(".check_head").click(function(){
    //alert($(this).parents(".tr").index()-1)
    var index=$(this).parents(".tr").index()-1;
    //var index=$(this).index();
    //alert(index)
    var flag=$(".all").find("input[type=checkbox]").eq(index).prop("checked");
    //alert(flag)
    if(flag==true){
        $(".all").find("input[type=checkbox]").eq(index).prop("checked",false);
        $(".check_head").eq(index).find(".bged").removeClass("bged").addClass("bg");
    }else{
        $(".all").find("input[type=checkbox]").eq(index).prop("checked",true);
        $(".check_head").eq(index).find(".bg").removeClass("bg").addClass("bged");
    }
    checkedall();
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
//全选按钮
function checkall(){
    //alert($("check_head").attr("checked"))
    var alllen=$(".all").find("input[type=checkbox]").length;
    if($("#check_head").prop("checked")==false){
        $("#check_head").prop("checked",true);
        $("#checkallhead").addClass("bged").removeClass("bg");
        for(i=0;i<alllen;i++){
            $(".all").find("input[type=checkbox]").eq(i).prop("checked",true);
            $(".check_head").eq(i).find(".bg").removeClass("bg").addClass("bged");
        }
    }else if($("#check_head").prop("checked")==true){
        $("#check_head").prop("checked",false);
        $("#checkallhead").addClass("bg").removeClass("bged");
        for(i=0;i<alllen;i++){
            $(".all").find("input[type=checkbox]").eq(i).prop("checked",false);
            $(".check_head").eq(i).find(".bged").removeClass("bged").addClass("bg");
        }
    }
}