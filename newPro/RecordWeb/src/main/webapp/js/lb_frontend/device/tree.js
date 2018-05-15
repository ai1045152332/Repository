/**
 * Created by xinye on 2017/4/25.
 */
//var treeClick = function (e) {
//}
// 自动刷新iframe
videoShow();
function deviceIDByPage() {
    var hostIds = '';
    // if (curPage == 1) {
    //     first = 0;
    // } else {
    //     first = split * (curPage - 1);
    // }
    // if (first >= $('span[hostip]').length) {
    // if (first >= $('span[hostip]').length) {
    //     first = 0;
    // }
    // console.log($('span[hostip]')); .slice(0,$('span[hostip]').length)
    var array = $('span[hostip]');

    for (var i = 0; i < array.length; i++) {
        if (i != 0) {
            hostIds += ',';
        }
        hostIds += array.eq(i).attr('hostid');
    }
    console.log(hostIds);
    return hostIds;
}
//function countPage(hostIds) {
//    var split = $.cookie("screen");
//    if(!hostIds || hostIds.length == 0){
//        var sum = $('span[hostip]').length;
//    }else {
//        var sum = hostIds.length;
//    }
//
//    var size = split * split;
//    if (sum < size) {
//        return 1;
//    }
//    if (sum % size == 0) {
//        return sum / size;
//    } else {
//        return Math.floor(sum / size) + 1;
//    }
//}
function countPage(hostIds) {
    var split = $.cookie("screen");
    // var sum = $('span[hostip]').length;
    var sum;
    if(!hostIds || hostIds.length == 0){
        sum = $('span[hostip]').length;
    }else {
        sum = hostIds.length;
    }
    var size = split * split;
    if (sum < size) {
        return 1;
    }
    if (sum % size == 0) {
        return sum / size;
    } else {
        return Math.floor(sum / size) + 1;
    }
}
function countPageAjax(sum) {
    var split = $.cookie("screen");
    //var sum = $('span[hostip]').length;
    var size = split * split;
    if (sum < size) {
        return 1;
    }
    if (sum % size == 0) {
        return sum / size;
    } else {
        return Math.floor(sum / size) + 1;
    }
}
var treeClick = function (e) {
    var els = $(e).parents('li').first().find('span[hostip]');
    for (var i = 0; i < els.length; i++) {
        var ip = els.eq(i).attr('hostip');
        var video = $(window.frames["go"].document).find("div[ip='" + ip + "']");
        if (!video) {
            continue;
        }
        var split = $.cookie("screen");
        if (split == 2) {
            if ((els.eq(i).hasClass('m-checked') && video.hasClass('xk_video')) ||
                (!els.eq(i).hasClass('m-checked') && video.hasClass('xk_video_selected'))) {
                video.click();
            }
        } else {
            if ((els.eq(i).hasClass('m-checked') && video.hasClass('xk_video_nine')) ||
                (!els.eq(i).hasClass('m-checked') && video.hasClass('xk_video_selected_nine'))) {
                video.click();
            }
        }
    }
}
//<!--若功能出现问题请将$.cookie("screen");改为screenrecord进行尝试-->
//function videoShow(camera,hostIds) {
//    alert(hostIds);
//    var split = $.cookie("screen");//储存的值：2代表2*2，3代表3*3
//    var hostIdStr;
//    if (!split) {
//        $.cookie('screen', 2, {path: '/'});
//        split = 2;
//    }
//    var curPage = $.cookie("curPage");
//    var totalPage = countPage();
//    if (!curPage) {
//        $.cookie('curPage', 1, {path: '/'});
//        curPage = 1;
//    }
//    if(!hostIds || hostIds.length == 0){
//        hostIdStr = deviceIDByPage(split * split, curPage);
//    }else{
//        if(split == 2){
//            hostIdStr = hostIds.slice(0,4).join(',');
//        }else{
//            hostIdStr = hostIds.slice(0,9).join(',');
//        }
//    }
//
//
//    var src = $("#root_path").val();
//    var viewClassCameraName;
//    if (camera == null) {
//        viewClassCameraName = $.cookie("camera");
//        if (!viewClassCameraName) {
//            viewClassCameraName = "教师学生";
//            $.cookie('camera', "教师学生", {path: '/'});
//
//        }
//    } else {
//        viewClassCameraName = camera;
//    }
//    viewClassCameraName = encodeURI(encodeURI(viewClassCameraName));
//    src += "/viewclass/Viewclass_viewClass.do?viewClassCameraName=" + viewClassCameraName + "&hostid=" + hostIdStr +
//        "&pageCount=" + totalPage + "&currentPage=" + curPage + "&split=" + split;
//    $("#go").attr("src", src);
//    document.getElementById("go").onload = function () {
//        refreshSelected();
//    }
//}
<!--若功能出现问题请将$.cookie("screen");改为screenrecord进行尝试-->
function videoShow(camera,hostIds) {
    var split = $.cookie("screen");//储存的值：2代表2*2，3代表3*3
    var hostIdStr;
    if (!split) {
        $.cookie('screen', 2, {path: '/'});
        split = 2;
    }
    var curPage = $.cookie("curPage");
    var totalPage = countPage(hostIds);
    if (!curPage) {
        $.cookie('curPage', 1, {path: '/'});
        curPage = 1;
    }
    if(!hostIds || hostIds.length == 0){
        hostIdStr = deviceIDByPage();
    }else{
        hostIdStr = hostIds.join(',');
    }

    // else{
    //     if(split == 2){
    //         hostIdStr = hostIds.slice(0,4).join(',');
    //     }else{
    //         hostIdStr = hostIds.slice(0,9).join(',');
    //     }
    // }


    var src = $("#root_path").val();
    var viewClassCameraName;
    if (camera == null) {
        viewClassCameraName = $.cookie("camera");
        if (!viewClassCameraName) {
            viewClassCameraName = "教师学生";
            $.cookie('camera', "教师学生", {path: '/'});

        }
    } else {
        viewClassCameraName = camera;
    }
    viewClassCameraName = encodeURI(encodeURI(viewClassCameraName));
    src += "/viewclass/Viewclass_viewClass.do?viewClassCameraName=" + viewClassCameraName + "&hostid=" + hostIdStr +
        "&pageCount=" + totalPage + "&currentPage=" + curPage + "&split=" + split;
    $("#go").attr("src", src);
    document.getElementById("go").onload = function () {
        refreshSelected();
    }
}
function videoShowAjax(camera,hostIds) {
    var split = $.cookie("screen");//储存的值：2代表2*2，3代表3*3
    if (!split) {
        $.cookie('screen', 2, {path: '/'});
        split = 2;
    }
    var curPage = $.cookie("curPage");
    var totalPage = countPageAjax(hostIds.split(",").length);
    if (!curPage) {
        $.cookie('curPage', 1, {path: '/'});
        curPage = 1;
    }
    //var hostIds = deviceIDByPage(split * split, curPage);
    var src = $("#root_path").val();
    var viewClassCameraName;
    if (camera == null) {
        viewClassCameraName = $.cookie("camera");
        if (!viewClassCameraName) {
            viewClassCameraName = "教师学生";
            $.cookie('camera', "教师学生", {path: '/'});

        }
    } else {
        viewClassCameraName = camera;
    }
    viewClassCameraName = encodeURI(encodeURI(viewClassCameraName));
    src += "/viewclass/Viewclass_viewClass.do?viewClassCameraName=" + viewClassCameraName + "&hostid=" + hostIds +
        "&pageCount=" + totalPage + "&currentPage=" + curPage + "&split=" + split;
    $("#go").attr("src", src);
    document.getElementById("go").onload = function () {
        refreshSelected();
    }
}
function refreshSelected() {
    var checked = $('.m-checked[hostip]');
    for (var i = 0; i < checked.length; i++) {
        var ip = checked.eq(i).attr('hostip');
        var video = $(window.frames["go"].document).find("div[ip='" + ip + "']");
        if (!video) {
            continue;
        }
        video.click();
    }
}
/* 离线状态修改 */
function onlineStatus(ip, status) {
    $(".m-check[hostip='"+ip+"']").attr('status',status);//同步左侧树设备状态
    var line = $("span[hostip='" + ip + "']").parents('a').first().find('h6').next();
    if (line.hasClass('offlineshow') || line.hasClass('offlineviewhide')) {
        line.removeClass('offlineshow');
        line.removeClass('offlinehide');
        line.addClass('Online' == status ? 'offlinehide' : 'offlineshow');
    } else {
        var str = '<span class="offlineshow">(离线)</span>';
        if ('Online' == status) {
            str = '<span class="offlinehide">(在线)</span>';
        }
        $("span[hostip='" + ip + "']").parents('a').first().find('h6').append(str);
    }
}
/* 第一次加载离线状态 */
(function(){
    var e = $('span[hostip]');
    for(var i = 0;i< e.length;i++){
        var ip = e.eq(i).attr('hostip');
        var status = e.eq(i).attr('status');
        var hostId = e.eq(i).attr('hostid');
        var hostName = e.eq(i).parents('a').first().find('h6').html();
        var desc = e.eq(i).attr('desc');
        onlineStatus(ip,status);
        var str = '<span class="video_screen" style="display: none;" onclick="directorView('+
            "'"+hostId+"','"+ip+"','"+hostName+"','"+desc+"')"+
            '"></span><span class="split_screen" style="display: none;" onclick="directorCamera('+
        "'"+hostId+"')"+
        '"></span>';
        e.eq(i).parents('a').find("h6").append(str);
    }

})()
    /* 绑定鼠标指向事件 */
    $('.tree').find('span[hostip]').each(function(){
        $(this).parents('li').first().on("mouseover",function(){
            // 如果设备离线, 则不显示
            if($(this).find(".offlineshow").text()=="(离线)") {
                $(this).find(".video_screen").hide();
                $(this).find(".split_screen").hide();
            } else {
                $(this).find(".video_screen").show();
                $(this).find(".split_screen").show();
            }
        })
        $(this).parents('li').first().on("mouseleave",function(){
            $(this).find(".video_screen").hide();
            $(this).find(".split_screen").hide();
        })
    })
    $(document).on("mouseover",".tree a[id]",function(){
        $(this).find(".add_class_main_background").show();
    })

    $(document).on("mouseleave",".tree a[id]",function(){
        $(this).find(".add_class_main_background").hide();
    })
    $(".xk_split_camera").mouseover(function () {
        $(this).css("background-position", "0px -24px")
    }).mouseleave(function () {
        $(this).css("background-position", "0px 0px")
    })
    $(".xk_video_camera").mouseover(function () {
        $(this).css("background-position", "-117px -71px")
    }).mouseleave(function () {
        $(this).css("background-position", "-117px -92px")
    })
/* 多选中状态下，按钮状态的处理方法 多选四个按钮全部可用*/
function buttonStatus(cla){
    var selected = $(window.frames["go"].document).find(cla);
    if(selected.length>1){
        $("#turnon").attr("onclick", "wakeup()");
        $("#turnoff").attr("onclick", "shutdown()");
        $("#record_vedio").attr("onclick", "recording()");
        $("#stop_record_vedio").attr("onclick", "stoprecord()");
        $("#turnon_img").removeClass("xk_options_openicon_disable").removeClass('xk_options_openicon').addClass("xk_options_openicon");
        $("#turnoff_img").removeClass("xk_options_closeicon_disable").removeClass('xk_options_closeicon').addClass("xk_options_closeicon");
        $("#record_vedio_img").removeClass("xk_options_recordingicon_disable").removeClass('xk_options_recordingicon').addClass("xk_options_recordingicon");
        $("#stop_record_vedio_img").removeClass("xk_options_stoprecordingicon_disable").removeClass('xk_options_stoprecordingicon').addClass("xk_options_stoprecordingicon");
        return false;
    }
    return true;
}