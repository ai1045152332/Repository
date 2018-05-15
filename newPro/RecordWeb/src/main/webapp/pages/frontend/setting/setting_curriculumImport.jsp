<%@ page import="com.honghe.recordweb.action.SessionManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
    <meta HTTP-EQUIV="pragma" CONTENT="no-cache">
    <meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
    <meta HTTP-EQUIV="expires" CONTENT="0">
    <link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/time_celve.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/frontend/record/hpager.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/index.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/main.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/fd-slider.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/schedule.css" rel="stylesheet" type="text/css"/>
    <title>系统设置 | 集控平台</title>
    <script>
        var url = "${pageContext.request.contextPath}";
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/jquery.cookie.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/common/selectone.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/laydate/laydate.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/laydate.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/js/lb_frontend/setting/curriculum.js"></script>--%>
    <script src="${pageContext.request.contextPath}/js/common/scrolldemo.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/lb_frontend/setting/setting.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/jquery.form.js"></script>
    <style type="text/css">
        .demotext {
            background: url(${pageContext.request.contextPath}/js/common/layer/skin/default/xubox_loading2.gif) left 10px no-repeat;
            color: #fff;
            width: 200px;
            height: 100px;
            line-height: 50px;
            position: absolute;
            padding-left: 40px;
            left: 0;
            top: 0;
            bottom: 0;
            right: 0;
            margin: auto;
        }
        .demo {
            background: rgba(0, 0, 0, 0.7);
            display: none;
            left: 0;
            position: absolute;
            top: 0;
            z-index: 10;
        }
        @media only screen and (min-width: 1601px) and (max-width: 1920px) {
            .vipt_head{
                border:1px solid #000;
            }
        }
    </style>
</head>
<body>
<input type="hidden" id="whichscroll">
<div class="public" style="min-width:1680px !important; height: auto;">
    <jsp:include page="/pages/frontend/lb_header.jsp"></jsp:include>
    <div class="vip">
        <div class="vipt_head">
            <div class="settinghead" style="height:50px;border-bottom:1px solid #28b779">
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
        <div class="main_content" style="float:left;overflow: hidden">
            <div class="function_bar" style="height: 45px;">
                <ul>
                    <li class="function_bar_clear"><a href="javascript:void(0)"><span class="function_bar_clear"></span>清空课表</a></li>
                    <li class="function_bar_download"><a href="javascript:void(0)"><span class="function_bar_download"></span>下载模板</a></li>
                    <li class="function_bar_schedule"><a href="javascript:void(0)"><span class="function_bar_schedule"></span>课表导入</a></li>
                    <input type="file" id="myFile" name="myFile" accept="application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" style="display: none;">
                    <li class="function_bar_save" value="<%=curriculumType%>"><button class="btn_disable" type="button">保存</button></li>
                </ul>
            </div>
            <%--<input type="hidden" value="<%=isSeting%>" id="isSetting">--%>
            <div id="vipt_video" style="float: left;margin-bottom: 50px;">
                    <%--头信息--%>
                <div class="vipt_week" id="vipt_week" style="float: left;width: auto;">
                    <div class="sall" style="margin: 0;width: 155px;height: 46px;float:left;" id="sall">
                    <%
                        Map<Integer, String> intToUpper_1 = (Map<Integer, String>) request.getAttribute("intToUpper");
                        if(curriculumType == Curriculum.CUR_WEEK_TYPE)
                        {
                    %>
                        <select id="week_id" class="select">
                            <%
                                int week_id_ajax = Integer.parseInt(request.getAttribute("week_id").toString());
                                for(int a=1;a<=7;a++)
                                {
                                    if(a == week_id_ajax)
                                    {
                                        if(a==7) //选中状态
                                        {
                            %>week_id
                            <option value="<%=a%>" selected="selected">星期日</option>
                            <%
                            }
                            else
                            {
                            %>
                            <option value="<%=a%>" selected="selected">星期<%=intToUpper_1.get(a)%></option>
                            <%
                                }
                            }
                            else
                            {
                                if(a==7)
                                {
                            %>
                            <option value="<%=a%>">星期日</option>
                            <%
                            }
                            else
                            {
                            %>
                            <option value="<%=a%>">星期<%=intToUpper_1.get(a)%></option>
                            <%
                                        }
                                    }
                                }
                            %>
                        </select>
                        <style>
                            .selectdiv,#selectdivul{
                                color: #333;
                                font-weight: bold;
                            }
                            #selectdivul{
                                height: 320px;
                                max-height: 320px;
                            }
                        </style>
                        <div class="selectdivall" id="selectdivall">
                            <div class="selectdiv" id="selectdiv" style="width: 155px;height: 46px;line-height: 46px;text-align: left;text-indent: 40px;"></div>
                            <div class="selectdivul" id="selectdivul" style="margin: 0;width: 155px;line-height: 46px;height:320px;overflow:hidden;text-indent: 40px;"></div>
                        </div>
                    <%--</div>--%>
                    <%
                        }
                        else
                        {
                            String date = (String)request.getAttribute("date");
                    %>
                    <%--<div class="sall" style="margin: 0;width: 155px;height: 46px;" id="sall">--%>
                        <input id="datashow" class="laydate-icon" value="<%=date%>" style="width: 132px;height: 42px;text-align: center;font-size: 14px;">
                    <%--</div>--%>
                    <%
                        }
                    %>
                    </div>
                    <%

                        List<Integer> sectionList = (List<Integer>) request.getAttribute("sectionList");
                        for (int i = 1; i <= sectionList.size(); i++) //共多少节课
                        {
                    %>
                    <div class="vipt_node">第<%=intToUpper_1.get(i)%>节</div>
                    <%
                        }
                    %>
                    <%--<div class="sall" style="margin: 0;width: 155px;height: 46px;" id="sall">--%>
                        <%--<script>--%>
                            <%--$(function() {--%>
                                <%--$.post("${pageContext.request.contextPath}/settings/Settings_headInit.do",function(data){--%>
                                    <%--$("#vipt_week").html(data);--%>
                                <%--});--%>
                            <%--});--%>
                        <%--</script>--%>
                    <%--</div>--%>
                </div>
                <%--主体信息--%>
                <div id = "timeplan_list">
                    <%@ include file="/pages/frontend/setting/curriculumInfoList.jsp" %>
                </div>
            </div>
        </div>
    </div>
    <div class="demo">
        <div style="color:white;font-size: 15px" class="demotext"></div>
    </div>
    <script>
        $(function(){
            $('.main_content').css('min-height',$(document).height()-20-$('.public_head').height()-50-$('.foot').height()-3+'px');
            var vipt_videowid=$("#vipt_video #timeplan_list .vipt_node").length*125+180
            $("#vipt_video .scrollson").width(vipt_videowid)
            $("#vipt_video").width($(".vip").width())
            var listlen=$("#vipt_video #timeplan_list").children(".vipt_tablerecycle").length;
            $("#vipt_video .scrollson").mouseover(function(){
                $("#whichscroll").val($.trim($(this).parent().attr("id")))
                if ((navigator.userAgent.match(/(iPhone|Android|iPad)/i))){
                    var scrollfathter1=document.getElementById($.trim($(this).parent().attr("id")));
                    scrollfathter1.addEventListener("touchstart", touchStart, false);
                    scrollfathter1.addEventListener("touchmove", touchMove, false);
                    scrollfathter1.addEventListener("touchend", touchEnd, false);
                }
            })
            scroll_y("vipt_video","scrollson","scroll_y","scroll_ymove","scroll_x","scroll_xmove","","wheely","")
            $("#vipt_video .scrollson").css("margin-top","0")
            $("#vipt_video .scroll_y").css("top","0")
            // 课表导入
            $(".function_bar_schedule").click(function(){
                $("#myFile").click();

            });
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
                console.log(data);
                var json =  eval("("+data+")");
                console.log('111111111');
                console.log(json);

                /*///////////////////////*/
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
        $(".win_colse_t").live("click",function(){
            $(this).parents(".win_alert").remove();
            window.location = "${pageContext.request.contextPath}/settings/Settings_curriculumImport.do";
        })
        function winwidth(){
            var winwidth=$(window).width();
            return winwidth;
        }
        //下载模板
        $(".function_bar_download").click(function(){
            window.location = "${pageContext.request.contextPath}/settings/Settings_curriculumDownload.do";
        });
        function getData(weekday)
        {
            $.post(
                    "${pageContext.request.contextPath}/settings/Settings_getCurWeek.do",
                    {week_id:weekday},
                    function(data) {
                        $("#timeplan_list").html(data);
                    }
            );
        }
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
        laydate({
            elem: '#datashow',
            event: 'click', //触发事件
            format: 'YYYY-MM-DD',
            festival: true, //是否显示节日
            istoday: false,
            choose: function(){ //选择好日期的回调
                $.post(
                        "${pageContext.request.contextPath}/settings/Settings_getCurWeek.do",
                        {date:$("#datashow").val()},
                        function(data) {
                            $("#timeplan_list").html("");
                            $("#timeplan_list").html(data);
                        }
                );
            }
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
        $(".vipt_tablerecycle").each(function(){
            var div_leng = $(this).children("div").length;
            $(this).width(125*(div_leng-1)+160+60)
        })
        // alert($(".vipt_node").length)
        $("#vipt_week").width(300+$(".vipt_node").length*115)
    </script>
    <div class="foot">
        <jsp:include page="/pages/frontend/footer.jsp"></jsp:include>
    </div>
</div>

</body>
</html>




