var rootpath = $("#root_path").val();
//添加用户(点击‘添加用户’链接)
function addUser() {
     var uid = -1;//
     $.layer({
     type: 2,
     title: '添加用户',
     shadeClose: true,
     maxmin: false,
     fix: false,
     //area: ['520px', '500px'],
     iframe: {
     src: rootpath + "/userback/Userback_userDetaile.do?userId=" + uid
     },
     end: function () {
  //   window.location.reload();
         dataReload(rootpath+"/userback/Userback_userList.do");
     }
     });
}
//编辑用户
function updateuser(selectId)
{
    var suid = $("#sessionuser").attr("name");
//    if(selectId==1 || selectId ==suid)
//    {
//        layer.alert("不能编辑自己哦");
//    }
//    else
//    {
        $.layer({
            type : 2,
            title: '编辑用户',
            shadeClose: true,
            maxmin: false,
            fix : false,
            //area: ['518px', '535px'],
            iframe: {
                src : rootpath+"/userback/Userback_userDetaile.do?userId="+selectId
            }
        });
//    }
}

function deluser(uid)
{
    layer.confirm("确定删除该用户吗？",function(){
        $.post(rootpath+"/userback/Userback_delUser.do",{userId:uid},function(data){
            if(data.success==false)
            {
                layer.alert(data.msg);
            }
            else
            {
                layer.msg(data.msg,1);
                dataReload(rootpath+"/userback/Userback_userList.do");
            }
        })
    })

}

//密码重置(点击‘密码重置’链接)
function resetPwd(uid)
{
        layer.prompt({title: '输入密码：'}, function(pwd){

            $.post(rootpath+"/userback/Userback_alterUser.do", {userId: uid,userpwd:pwd},function(data){
                if(data.success==true)
                {
                    layer.msg(data.msg);
                    //window.location.reload();
                }
                else
                {
                    layer.alert(data.msg);
                }
            },'json');
        });
}


