//退出登录按钮click事件
function logout () {
    var rootpath =  $("#root_path").val();
    $.post(rootpath+"/user/User_logout.do", {
    }, function (data) {
        if (data.path !="") {
            var path = data.path;
            location.href = path;
        }
        else {
            location.href ="/login.jsp";
        }
    }, 'json');
}