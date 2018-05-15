var rootpath ;
$(function() {
    rootpath = $("#root_path").val();
    //搜索
    $(".user_search_btn").live('click', function () {
        var uname = $(".user_search_input").val();
       // alert(rootpath);
        $.get(rootpath+"/user/User_userSearch.do", {username: uname}, function (data) {
            $("#user_search_input").blur();
            $("#userdatalist").empty();
            //alert("123");
            $("#userdatalist").html(data);
            var pagelen=$(".page a").length;
            left=-(pagelen+3)*28/2+"px";
            $("#linkpage_son").css({"margin-left":"left","width":(pagelen+4)*28+"px","position":"absolute","bottom":"40px","left":"45%"});
        }, 'html');
    });
    $("#user_search_input").live("keyup",function(event){
        var keycode = (event.keyCode ? event.keyCode : event.which);
        if(keycode == '13'){
            $(".user_search_btn").click();
        }
    })

 //鼠标点击选中
    $(".user_list").live('click', function () {
        var uid = $(this).attr('id');
        $("#"+uid).attr("class","user_list_selected");
        $("#span"+uid).css("display","block");
        if($(".user_list").length==0)
        {
            $("#ischeckall").attr("class","bged").css({"background-position":"0px -35px"});//.css修正鼠标经过checkbox的背景位置;;
            $("#checkall").prop("checked",true);
        }
    });
    //鼠标点击取消选中
    $(".user_list_selected").live('click', function () {
        var uid = $(this).attr('id');
        $("#"+uid).attr("class","user_list");
        $("#span"+uid).css("display","none");
        $("#ischeckall").attr("class","bg").css({"background-position":"0px -0px"});//.css修正鼠标经过checkbox的背景位置;
        $("#checkall").prop("checked",false);
    });
    // 全选、取消全选
    $(".head").live('click', function () {
        var checkstate = $("#checkall").prop("checked");
        if(checkstate==true)
        {
            $(".user_list").each(function(){
                $(this).attr("class","user_list_selected");
            });
            $(".xk_video_selected_bz").each(function(){
                $(this).css("display","block");
            });
        }
        else
        {
            $(".user_list_selected").each(function(){
                //alert(this);
                $(this).attr("class","user_list");
            });
            $(".xk_video_selected_bz").each(function(){
                $(this).css("display","none");
            });
        }
    });
    //删除用户（点击删除图标）
    $(".user_list_del").live("click",function(){
        var selectId =$(this).parent().parent().parent().attr("id");
        var suid = $("#sessionuser").attr("name");
        if(selectId==1 || selectId ==suid)
        {
            layer.msg("请勿删除系统管理员");
        }
        else
        {
            $.layer({
                shade: [0.5],
                maxmin: false,
                area: ['310px','129px'],
                dialog: {
                    msg: '确定删除该用户吗？',
                    btns: 2,
                    type: 4,
                    btn: ['确定','取消'],
                    yes: function(){
                        var delUrl =rootpath+"/user/User_delUser.do";
                        $.post(delUrl,{'userId':selectId},function(data){
                            if(data.success==true)
                            {
//                                window.location.reload();
                                window.location.href=rootpath+"/user/User_userList.do";
                                layer.msg(data.msg,1,1);
                            }
                            else
                            {
                                layer.msg(data.msg);
                            }
                        },'json')
                    }, no: function(){
                        // layer.msg('奇葩', 1, 13);
                    }
                }
            });
        }
    });
    //编辑用户
    $(".user_list_edit").live("click",function(){
        var selectId =$(this).parent().parent().parent().attr("id");
        var suid = $("#sessionuser").attr("name");
        if(selectId==1 )
        {
            layer.msg("请勿编辑系统管理员");
        }
        else
        {
            $.layer({
                type : 2,
                title: '编辑用户',
                shadeClose: true,
                maxmin: false,
                fix : false,
                area:['640px','447px'],
                iframe: {
                    src : rootpath+"/user/User_userDetaile.do?userId="+selectId
                }
            });
        }
    });

   
});
//删除用户（点击‘删除用户’链接，可批量删除）
function delUser()
{
    var selectIdstr="";
    var length = $(".user_list_selected").length;
    if(length<=0)
    {
    	//当未选择用户时，去掉弹窗，顶部按钮灰化
          layer.msg("未选择用户，不能进行操作哦");
//      $.layer({
//		    shade: [0.5],
//		    title:'提示信息',
//		    area: ['auto','100'],
//		    time: 2,
//		    dialog: {
//		        msg: '未选择用户，不能进行操作!',
//		        btns: 0,                    
//		        type: 0
//		    }
//		});
         
    }
    else if(!delValidate())
    {
        //layer.alert("不能删除自己哦");
        $.layer({
		    shade: [0.5],
		    title:'删除用户',
		    area: ['310px','100px'],
		    time: 2,
		    dialog: {
		        msg: '请勿删除系统管理员',
		        btns: 0,                    
		        type: 0
		    }
		});
    }
    else
    {
        $(".user_list_selected").each(function(){
            var uid = $(this).attr("id");
            selectIdstr += uid+",";
        });
        delUsers(selectIdstr);
    }
}
//批量删除用户验证用户是否为admin或登录用户自己
function delValidate()
{
    var flag = true;
    var suid = $("#sessionuser").attr("name");
    $(".user_list_selected").each(function(){
        var uid = $(this).attr("id");
        if(uid ==1 || uid == suid)
        {
            flag = false;
            return false;
        }
    });
    return flag;
}
//内置函数，删除用户
function delUsers(selectIdstr)
{
        $.layer({
            shade: [0.5],
            area: ['310px','129px'],
            title:'删除用户',
            maxmin: false,
            dialog: {
                msg: '确定删除所选用户？',
                btns: 2,
                type: 4,
                btn: ['确定','取消'],
                yes: function(){
                    var delUrl =rootpath+"/user/User_delUserList.do";
                    $.post(delUrl,{'userIdStr':selectIdstr},function(data){

                        if(data.success==true)
                        {
//                            window.location.reload();
//                            $(".user_list_selected").each(function(){
//                                var uid = $(this).attr("id");
//                                $("#"+uid).css("display","none");
//                                $(this).attr("class","user_list");
//                            });
                            window.location.href=rootpath+"/user/User_userList.do";
                            layer.msg(data.msg);
                        }
                        else
                        {
                            layer.msg(data.msg);
                        }
                    },'json')
                }
            }
        });
}

//添加用户(点击‘添加用户’链接)
function addUser()
{
    var uid = -1;//
    $.layer({
        type : 2,
        title: '增加用户',
        shadeClose: true,
        maxmin: false,
        fix : false,
        area: ['520px', '448px'],
        iframe: {
            src : rootpath+"/user/User_userDetaile.do?userId="+uid
       }
//        end:function(){
////            window.location.reload();
//            window.location.href=rootpath+"/user/User_userList.do";
//        }
    });
}
//修改用户(点击‘修改用户’链接)
function alterUser()
{
    var length = $(".user_list_selected").length;
    if(length<=0)
    {
          layer.msg("未选择用户，不能进行操作哦");
//      $.layer({
//		    shade: [0.5],
//		    title:'提示信息',
//		    area: ['auto','100'],
//		    time: 2,
//		    dialog: {
//		        msg: '未选择用户，不能进行操作!',
//		        btns: 0,                    
//		        type: 0
//		    }
//		});
    }
    else if(length>1)
    {
        layer.msg("一次只能修改一个用户");
//      $.layer({
//		    shade: [0.5],
//		    title:'提示信息',
//		    area: ['auto','100'],
//		    time: 2,
//		    dialog: {
//		        msg: '一次只能修改一个用户!',
//		        btns: 0,                    
//		        type: 0
//		    }
//		});
    }
//    else if(!delValidate())
//    {
//        layer.alert("不能编辑自己哦");
//    }
    else
    {
        var uid = $(".user_list_selected").attr("id");
        $.layer({
            type : 2,
            title: '编辑用户',
            shadeClose: true,
            maxmin: false,
            fix : false,
			area:['520px','448px'],
            iframe: {
                src : rootpath+"/user/User_userDetaile.do?userId="+uid
            }
//            end:function(){
////                window.location.reload();
//                window.location.href=rootpath+"/user/User_userList.do";
//            }

        });
    }
}
//密码重置(点击‘密码重置’链接)
function resetPwd()
{
    var length = $(".user_list_selected").length;
    if(length<=0)
    {
          layer.msg("未选择用户，不能进行操作哦");
//      $.layer({
//		    shade: [0.5],
//		    title:'提示信息',
//		    area: ['auto','100'],
//		    time: 2,
//		    dialog: {
//		        msg: '未选择用户，不能进行操作!',
//		        btns: 0,                    
//		        type: 0
//		    }
//		});
    }
    else if(length>1)
    {
          layer.msg("一次只能重置一个用户密码");
//      $.layer({
//		    shade: [0.5],
//		    title:'提示信息',
//		    area: ['auto','100'],
//		    time: 2,
//		    dialog: {
//		        msg: '一次只能重置一个用户密码!',
//		        btns: 0,                    
//		        type: 0
//		    }
//		});
    }
    else
    {
        var uid = $(".user_list_selected").attr("id");
        $.layer({
            type : 2,
            title: '密码重置',
            shadeClose: true,
            maxmin: false,
            fix : false,
            area: ['400px', '200px'],
            iframe: {
                src : rootpath+"/pages/frontend/user/user_edit.jsp?uid="+uid
            }
        });
        //var uid = $(".user_list_selected").attr("id");
        //layer.prompt({title: '密码重置'}, function(pwd){
        //});
    }
}
$("#linkpage_son li a").live("click",function(){
    //alert("aa");
    var href = $(this).attr("href");
    $.get(href,{},function(data){
        $("#userdatalist").html(data);
        var pagelen=$(".page a").length;
        left=-(pagelen+3)*28/2+"px";
        $("#linkpage_son").css({"margin-left":"left","width":(pagelen+4)*28+"px","position":"absolute","bottom":"40px","left":"45%"});
    },"html");
    return false;
});

/*
$("#user_search_input").blur(function(){
        alert("123")
        var uname = $("#user_search_input").val();
        if(!uname)
        {
            $.get(rootpath+"/user/User_userSearch.do", {username: uname}, function (data) {
                $("#user_search_input").blur();
                $("#userdatalist").empty();
                //alert("123");
                $("#userdatalist").html(data);
                var pagelen=$(".page a").length;
                left=-(pagelen+3)*28/2+"px";
                $("#linkpage_son").css({"margin-left":"left","width":(pagelen+4)*28+"px","position":"absolute","bottom":"40px","left":"45%"});
            }, 'html');
        }
    })*/
/*$("#user_search_input").live("blur",function(){
   // alert("123")

})*/
