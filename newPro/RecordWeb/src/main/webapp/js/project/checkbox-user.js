/**
 * Created by lch on 2014/10/28.
 */
$(function(){
    $(document.body).find("input[type=checkbox]").css("display","none");
    $(".head").click(function(){
        var index=$(this).index();
        var flag=$(".checkbox").find("input[type=checkbox]").eq(index).prop("checked");
        if(flag==true){
            $(".checkbox").find("input[type=checkbox]").eq(index).prop("checked",false);
            $(".head").eq(index).find(".bged").removeClass("bged").addClass("bg");
        }else{
            $(".checkbox").find("input[type=checkbox]").eq(index).prop("checked",true);
            $(".head").eq(index).find(".bg").removeClass("bg").addClass("bged");
        }
    })
    $(".head1").click(function(){
        var index=$(this).index();
        var flag=$(".checkbox1").find("input[type=checkbox]").eq(index).prop("checked");
        if(flag==true){
            $(".checkbox1").find("input[type=checkbox]").eq(index).prop("checked",false);
            $(".head1").eq(index).find(".bged").removeClass("bged").addClass("bg");
        }else{
            $(".checkbox1").find("input[type=checkbox]").eq(index).prop("checked",true);
            $(".head1").eq(index).find(".bg").removeClass("bg").addClass("bged");
        }
    })
})