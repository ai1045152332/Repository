var rootpath = $("#root_path").val();
$(function() {
//    //搜索
//    $(".user_search_btn").live('click', function () {
//        var uname = $(".user_search_input").val();
//        $.get(rootpath+"/user/User_userSearch.do", {username: uname}, function (data) {
//            $("#userdatalist").html("");
//            $("#userdatalist").html(data);
//        }, 'html');
//    });
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
    //删除角色（点击删除图标）
    $(".user_list_del").live("click",function(){
        var selectId =$(this).parent().parent().parent().attr("id");
        if(selectId==1 || selectId ==2 || selectId==3 || selectId ==4)
        {
            layer.alert("不能删除自己哦");
        }
        else
        {
            $.layer({
                shade: [0],
                maxmin: false,
                area: ['auto','auto'],
                dialog: {
                    msg: '确定删除该角色？',
                    btns: 2,
                    type: 4,
                    btn: ['确定','取消'],
                    yes: function(){
                        var delUrl =rootpath+"/role/Role_delRole.do";
                        $.post(delUrl,{'roleId':selectId},function(data){
                            if(data.success==true)
                            {
//                                window.location.reload();
//                                $("#"+selectId).css("display","none");
//                                $("#"+selectId).attr("class","user_list");
                                window.location.href=rootpath+"/role/Role_roleList.do";
                                layer.msg(data.msg,1,1);
                            }
                            else
                            {
                                layer.alert(data.msg);
                            }
                        },'json')
                    },
                    no: function(){
                    }
                }
            });
        }
    });
    //编辑角色
    $(".user_list_edit").live("click",function(){
        var selectId =$(this).parent().parent().parent().attr("id");
        var suid = $("#sessionuser").attr("name");
//        if(selectId==1 || selectId ==suid)
//        {
//            layer.alert("不能编辑自己哦");
//        }
//        else
//        {
            $.layer({
                type : 2,
                title: '编辑角色',
                shadeClose: true,
                maxmin: false,
                fix : false,
                //area: ['518px', '535px'],
                iframe: {
                    src : rootpath+"/role/Role_roleDetaile.do?roleId="+selectId+"&userId="+suid
                }
            });
//        }
    });
});
//删除用户（点击‘删除用户’链接，可批量删除）
function delRole()
{
    var selectIdstr="";
    var length = $(".user_list_selected").length;
    if(length<=0)
    {
        //layer.alert("未选择角色，不能进行操作哦");
         $.layer({
		    shade: [0.5],
		    title:'提示信息',
		    area: ['auto','100'],
		    time: 2,
		    dialog: {
		        msg: '未选择角色，不能进行操作!',
		        btns: 0,                    
		        type: 0
		    }
		});
    }
    else if(!delValidate())
    {
        //layer.alert("不能删除系统默认角色哦");
        $.layer({
		    shade: [0.5],
		    title:'提示信息',
		    area: ['auto','100'],
		    time: 2,
		    dialog: {
		        msg: '不能删除系统默认角色!',
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
        delRoles(selectIdstr);
    }
}
//批量删除角色验证角色是否为系统默认的4个角色
function delValidate()
{
    var flag = true;
    $(".user_list_selected").each(function(){
        var rid = $(this).attr("id");
        if(rid ==1 || rid == 2 || rid ==3 || rid == 4)
        {
            flag = false;
            return false;
        }
    });
    return flag;
}
//内置函数，删除用户
function delRoles(selectIdstr)
{
        $.layer({
            shade: [0],
            area: ['auto','auto'],
            title:'删除角色',
            maxmin: false,
            dialog: {
                msg: '确定删除选中角色吗？',
                btns: 2,
                type: 4,
                btn: ['确定','取消'],
                yes: function(){
                    var delUrl =rootpath+"/role/Role_delRoleList.do";
                    $.post(delUrl,{'roleIdStr':selectIdstr},function(data){

                        if(data.success==true)
                        {
//                            window.location.reload();
//                            $(".user_list_selected").each(function(){
//                                var uid = $(this).attr("id");
//                                $("#"+uid).css("display","none");
//                                $(this).attr("class","user_list");
//                            });
                            window.location.href=rootpath+"/role/Role_roleList.do";
                            layer.msg(data.msg,1,1);
                        }
                        else
                        {
                            layer.alert(data.msg);
                        }
                    },'json')
                }
            }
        });
}

//添加角色(点击‘添加角色’链接)
function addRole()
{
    var rid = -1;
    var suid = $("#sessionuser").attr("name");
    $.layer({
        type : 2,
        title: '添加角色',
        shadeClose: true,
        maxmin: false,
        fix : false,
        //area: ['520px', '500px'],
        iframe: {
            src : rootpath+"/role/Role_roleDetaile.do?roleId="+rid+"&userId="+suid
        }
//        end:function(){
////            window.location.reload();
//            window.location.href=rootpath+"/role/Role_roleList.do";
//        }
    });
}
//修改角色(点击‘修改角色’链接)
function alterRole()
{
    var length = $(".user_list_selected").length;
    if(length<=0)
    {
        //layer.alert("未选择角色，不能进行操作哦");
        $.layer({
		    shade: [0.5],
		    title:'提示信息',
		    area: ['auto','100'],
		    time: 2,
		    dialog: {
		        msg: '未选择角色，不能进行操作!',
		        btns: 0,                    
		        type: 0
		    }
		});
    }
    else if(length>1)
    {
        //layer.alert("一次只能修改一个角色");
        $.layer({
		    shade: [0.5],
		    title:'提示信息',
		    area: ['auto','100'],
		    time: 2,
		    dialog: {
		        msg: '一次只能修改一个角色!',
		        btns: 0,                    
		        type: 0
		    }
		});
    }
//    else if(!delValidate())
//    {
//        layer.alert("不能编辑自己哦");
//    }
    else
    {
        var rid = $(".user_list_selected").attr("id");
        var suid = $("#sessionuser").attr("name");
        $.layer({
            type : 2,
            title: '编辑角色',
            shadeClose: true,
            maxmin: false,
            fix : false,

            iframe: {
                src : rootpath+"/role/Role_roleDetaile.do?roleId="+rid+"&userId="+suid
            }
        });
    }
}
