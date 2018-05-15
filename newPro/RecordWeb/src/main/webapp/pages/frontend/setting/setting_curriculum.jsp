        <%@ page import="com.honghe.recordhibernate.entity.Setting" %>
        <%@ page import="com.honghe.recordhibernate.entity.Curriculum" %>
        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
    <title>系统设置 | 集控平台</title>

    <!--滚动条模拟码率等-->
    <link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/time_celve.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/frontend/record/hpager.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/index.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/main.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/fd-slider.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/schedule.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/jquery.cookie.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/lb_common/screendetailforrecord.js"></script>
    <script src="${pageContext.request.contextPath}/js/lb_common/radio.js"></script>
    <script src="${pageContext.request.contextPath}/js/lb_common/selectmore_set.js"></script>
    <script src="${pageContext.request.contextPath}/js/lb_common/checkbox.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/jquery.cookie.js" type="text/javascript"></script>

    <!--滚动条模拟码率等引用顺序不能改变-->
    <script src="${pageContext.request.contextPath}/js/common/prettify.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/fd-slider.js"></script>
    <script src="${pageContext.request.contextPath}/js/lb_common/scroll.js"></script>

    <script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/lb_frontend/setting/setting.js"></script>
</head>
<body style="min-width:1320px">
<div class="public" style="min-width:1680px;">
        <jsp:include page="/pages/frontend/lb_header.jsp"></jsp:include>
        <div class="set">
            <div class="set_head">
                <div class="settinghead">
                    <ul>
                        <li onclick="javascript:jump(0)"><div class="tb"></div>台标</li>
                        <li onclick="javascript:jump(1)"><div class="videoinfo"></div>录像信息</li>
                        <li onclick="javascript:jump(2)"><div class="living"></div>直播</li>
                        <li onclick="javascript:jump(3)"><div class="nas"></div>NAS存储设置</li>
                        <li onclick="javascript:jump(4)"><div class="passuser"></div>录播主机用户名密码设置</li>
                        <li onclick="javascript:jump(5)" ><div class="ftp"></div>FTP设置</li>
                        <li onclick="javascript:jump(6)" ><div class="license"></div>License设置</li>
                        <li onclick="javascript:jump(7)" ><div class="second_nav_selected"></div>课表类型选择</li>
                        <li onclick="javascript:jump(8)" style="color:#28b779;"><div class="second_nav_schedule"></div>课表管理</li>
                        <li onclick="javascript:jump(9)" ><div class="second_nav_celve"></div>作息时间策略</li>
                        <li onclick="javascript:jump(10)"><div class="record_name_setting"></div>录制文件名设置</li>
                    </ul>
                </div>
            </div>
            <%
                Setting setting = (Setting) request.getAttribute("setting");
                int curriculumType = Curriculum.CUR_WEEK_TYPE;
                if(setting != null && setting.getCurriculumType() != null)
                {
                    curriculumType = setting.getCurriculumType();
                }
            %>
            <div class="main_content">
                <div class="function_bar">
                    <ul>
                        <li class="function_bar_clear"><a href="javascript:void(0)"><span class="function_bar_clear"></span>清空课表</a></li>
                        <li class="function_bar_download"><a href="javascript:void(0)"><span class="function_bar_download"></span>下载模板</a></li>
                        <li class="function_bar_schedule"><a href="javascript:void(0)"><span class="function_bar_schedule"></span>课表导入</a></li>
                        <input type="file" id="myFile" name="myFile" accept="application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" style="display: none;">
                        <li class="function_bar_save" value="<%=curriculumType%>"><a href="javascript:void(0)"><button class="btn_disable" type="button">保存</button></a></li>
                    </ul>
                </div>
                <div class="schedule">
                    <div class="schedule_no_data">
                        <img src="../../../image/schedule/no_data.png" title="" /><br />
                        <span>哎呀~您还没有导入过课表文件哦！</span>
                    </div>
                </div>
            </div>
        </div>
        <%--<input type="hidden" value="" class="getval">--%>
        <%--<input type="hidden" value="" class="visits">--%>
    <div class="foot" >
	<jsp:include page="/pages/frontend/footer.jsp"></jsp:include>
    </div>
</div>

</body>
</html>

<script>
    $(function () {

        $(".set_vv_save").mouseover(function () {
            $(this).css("background", "#5ec699");
        }).mouseout(function () {
            $(this).css("background", "#28b779");
        })
        //开关
        $("#close").click(function () {
            $(this).attr("class", "setopen set_vv_rightborder");
            $("#open").attr("class", "setclose set_vv_leftborder");
        })
        $("#open").click(function () {
            $(this).attr("class", "setopen set_vv_leftborder");
            $("#close").attr("class", "setclose set_vv_rightborder");
        })

        // 课表导入
        $(".function_bar_schedule").click(function(){
            $("#myFile").click();
        });

        $(".win_colse_t").live("click",function(){
            $(this).parents(".win_alert").remove();
            window.location = "${pageContext.request.contextPath}/settings/Settings_curriculumImport.do";
        })
    })
    // 课表导入
    var xhr;
    var settingType = $(".function_bar_save").val();
    $(".function_bar_save").click(function(){
        var form = new FormData();
        var fileObj = document.getElementById("myFile").files[0]; // js 获取文件对象
        if(fileObj!=undefined && fileObj != null)
        {
            var filename = $("#myFile").val();
            if(!/.(xls|xlsx)$/.test(filename)){
                layer.msg("文件类型必须是excel");
                return;
            }
            // 文件对象
            form.append("file", fileObj);
        }
        editannotatediv("正在导入课表，请稍候");
        var FileController =
                "${pageContext.request.contextPath}/settings/Settings_importExcel.do?settingType="+settingType;
        // XMLHttpRequest 对象
        xhr = new XMLHttpRequest();
        xhr.open("post", FileController, true);
        xhr.onload = function () {};
        xhr.send(form);
        xhr.onreadystatechange = callback;
    });
    function callback()
    {
        closeannotatediv("");
        if(xhr.readyState == 4 && xhr.status == 200)
        {
            var data = xhr.responseText;
            var json = eval("("+data+")");
            if(json.success == "true"){
                if(json.msg == "导入成功")
                {
                    layer.alert('课表导入成功', {
                        title:false,
                        closeBtn: 0
                    }, function(){
                        window.location = "${pageContext.request.contextPath}/settings/Settings_curriculumImport.do";
                    });
                }
                else
                {
                    $.layer({
                        shade: [0.5],
                        maxmin: false,
                        area: ['310px','129px'],
                        dialog: {
                            msg: '部分课表导入失败！',
                            btns: 2,
                            type: 4,
                            btn: ['错误详情','取消'],
                            yes: function(){
                                var str = '<div class="win_alert" style="width:500px;height: 260px;border: 1px solid #f00;position: fixed;top:165px;left:38%;background: #fff;border-radius: 3px;">' +
                                        '<div class="win_alert_title" style="border-bottom: 1px solid #f00;width: 100%;float: left;height: 30px;line-height: 30px;text-align: left;color: #f00;">未成功导入信息<div class="win_colse_t" style="float: right;cursor: pointer">我知道了</div></div>'+
                                        '<div class="win_alert_mian" style="width: 100%;float: left;overflow-y: auto;max-height: 230px">' +
                                        json.msg +
                                        '</div>'+
                                        '</div>';
                                if($(".win_alert").length == 0){
                                    $("body").append(str);
                                }
                                parent.layer.closeAll();
                            }, no: function(){
                                window.location = "${pageContext.request.contextPath}/settings/Settings_curriculumImport.do";
                            }
                        }
                    });
                }
            } else {
                layer.alert(json.msg);
            }
        }
    }
    //下载模板
    $(".function_bar_download").click(function(){
        window.location = "${pageContext.request.contextPath}/settings/Settings_curriculumDownload.do";
    });

    // 清空课表
    $(".function_bar_clear").click(function() {
        $.post(
                "${pageContext.request.contextPath}/settings/Settings_curriculumDelete.do",
                function(jsonObj) {
                    if (jsonObj.flag == true) {
                        layer.msg(jsonObj.msg);
                        $(".modif_liston").empty();
                    } else {
                        layer.msg(jsonObj.msg);
                    }
                }
        );
    });

    function editannotatediv(content) {
        var height = $(document).height() + "px";
        var width = $(document).width() + "px";
        $(".demotext").text(content).css("margin-top","300px");
        $(".demo").css({"height": height, "width": width, "display": "block"});
        //  alert($(".demo").length)
    }
    function closeannotatediv(content) {
        var height = "0px";
        var width = "0px";
        $(".demotext").text(content).css("margin-top","300px");
        $(".demo").css({"height": height, "width": width, "display": "none"});
        //   alert($(".demo").length)
    }

</script>
