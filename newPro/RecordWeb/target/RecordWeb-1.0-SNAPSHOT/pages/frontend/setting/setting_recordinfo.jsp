<%@ page import="com.honghe.recordhibernate.entity.Setting" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
    <title>系统设置 | 集控平台</title>

    <!--滚动条模拟码率等-->
    <link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/hpager.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/index.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/main.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/fd-slider.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/common/jquery-1.9.1.min.js"></script>
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
<body>
<div class="public" style="min-width:1680px;">
        <jsp:include page="/pages/frontend/lb_header.jsp"></jsp:include>
        <style>
            .set_vv_save {
                cursor: pointer;
            }

            .headr {
                margin-left: 10px;
                margin-top: 7px;
            }

            .sall {
                margin-left: 10px;
                width: 186px;
            }

            .selectdiv {
                background: none;
            }

            #selectdivall0, #selectdivall1, #selectdivall2, #selectdivall3, #selectdivall4, #selectdivall5, #selectdivall6 {
                background: none;
                width: 184px;
            }

            .selectdiv {
                background: url(${pageContext.request.contextPath}/image/frontend/n_icon_141016.png) 0 0 no-repeat;
                width: 184px;
                height: 27px;
                line-height: 27px;
            }

            .selectdivul {
                width: 182px;
                overflow: hidden;
            }

            .selectdivul a {
                text-indent: 10px;
                width: 182px;
            }

            #selectdivul0, #selectdivul1, #selectdivul2, #selectdivul3, #selectdivul4, #selectdivul5, #selectdivul6 {
                border: 1px solid #dbdbdb;
                margin-top: -2px;
                width: 182px;
            }

            #selectdivul1 a {
                width: 182px;
            }

            .tabs {
                background: #fff;
                width: 260px;
                height: 535px;
                float: left;
            }

            .tabs ul li {
                border-left: 5px solid #fff;
                border-bottom: 1px solid #dbdbdb;
                color: #333;
                width: 255px;
                height: 54px;
                line-height: 54px;
                text-align: center;
                float: left;
                list-style-type: none;
                cursor: pointer;
            }

            .tab_content {
                width: 900px;
                height: 535px;
                float: left;
                position: absolute;
                left: 300px;
                z-index: 1;
                /*	display:none;*/
                /*	border-bottom:1px solid red;*/
            }

            .c_selected {
                z-index: 1;
            }

            .t_selected {
                background: #f2f2f2;
                border-left: 5px solid #28b779;
                color: #333;
            }

            .tab_content_list {
                float: left;
                line-height: 30px;
                margin-top: 10px;
                width: 100%;
            }

            .tab_content_listtext {
                float: left;
                font-size: 12px;
                height: 30px;
                line-height: 30px;
                text-align: right;
                width: 150px;
            }

            .tab_content_listtext_gray {
                color: #bdbdbd;
                float: left;
                font-size: 12px;
                height: 30px;
                line-height: 30px;
                text-align: right;
                width: 150px;
            }

            .tab_content_listtext_black {
                float: left;
                font-size: 12px;
                height: 30px;
                line-height: 30px;
                text-indent: 2px;
                margin-left: 10px;
                width: 180px;
            }

            .tab_content_listinput {
                border: 1px solid #dbdbdb;
                float: left;
                font-size: 12px;
                height: 26px;
                line-height: 26px;
                text-indent: 2px;
                margin-left: 10px;
                outline: none;
                width: 180px;
            }
            .set_vv_linetext{
            	width: 160px;
            	font-size: 14px;
            }
        </style>
                <div class="set">
                    <div class="set_head">
                        <div class="settinghead">
                            <ul>
                                <li onclick="javascript:jump(0)"><div class="tb"></div>台标</li>
		                        <li onclick="javascript:jump(1)" style="color:#28b779;"><div class="videoinfo"></div>录像信息</li>
		                        <li onclick="javascript:jump(2)"><div class="living"></div>直播</li>
		                        <li onclick="javascript:jump(3)"><div class="nas"></div>NAS存储设置</li>
		                        <li onclick="javascript:jump(4)"><div class="passuser"></div>录播主机用户名密码设置</li>
		                        <li onclick="javascript:jump(5)" ><div class="ftp"></div>FTP设置</li>
		                        <li onclick="javascript:jump(6)" ><div class="license"></div>License设置</li>
                                <li onclick="javascript:jump(7)"><div class="second_nav_selected"></div>课表类型选择</li>
                                <li onclick="javascript:jump(8)"><div class="second_nav_schedule"></div>课表管理</li>
                                <li onclick="javascript:jump(9)"><div class="second_nav_celve"></div>作息时间策略</li>
                                <li onclick="javascript:jump(10)"><div class="record_name_setting"></div>录制文件名设置</li>
                            </ul>
                        </div>
                    </div>
                    <div class="win520_content" style="background: none;border:none">

                        <div id="opts">
                            <%
                                Setting setting = (Setting) request.getAttribute("setting");
                                int resolution = 1080;
                                int rate = 2;
                                if (setting != null) {
                                    resolution = setting.getRecordinfoResolution();
                                    rate = setting.getRecordinfoBitRate();
                                }
                            %>
                            <div class="set_bg" style="height: 70px;height: 100px;">


                                <div class="set_vv_line">
                                    <span class="set_vv_linetext">分辨率</span>

                                    <div class="sall">
                                        <select class="select" id="select0">
                                            <option value="1080" <%=resolution == 1080 ? "selected" : "" %>>全高清(1080p)
                                            </option>
                                            <option value="720" <%=resolution == 720 ? "selected" : "" %>>高清(720p)
                                            </option>
                                        </select>

                                        <div class="selectdivall" id="selectdivall0">
                                            <div class="selectdiv" id="selectdiv0"></div>
                                            <div class="selectdivul" id="selectdivul0"></div>
                                        </div>
                                    </div>
                                </div>

                                <div class="set_vv_line">
                                    <span class="set_vv_linetext">码率</span>

                                    <div class="set_vv_linescroll">
                                        <input type="text" name="lines" min="2040" max="8192"
                                               value="<%=resolution==1080?rate:2040%>">
                                    </div>
                                    <div class="litvaltext" style="width: 80px">
                                        <span id="opt-lines" class="litval">0</span><span
                                            style="float: right;">Kbp/s</span>
                                    </div>
                                </div>
                                <div class="set_vv_line " style="z-index: -1;margin-top: -36px;">
                                    <span class="set_vv_linetext">码率</span>

                                    <div class="set_vv_linescroll">
                                        <input type="text" name="lines1" min="1024" max="4096"
                                               value="<%=resolution==720?rate:1024%>">
                                    </div>
                                    <div class="litvaltext" style="width: 80px">
                                        <span id="opt-lines1" class="litval">0</span><span
                                            style="float: right;">Kbp/s</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="set_vv_foot">
                            <div class="set_vv_save" onclick="save()">保存</div>
                        </div>
                    </div>
                </div>
        <input type="hidden" value="" class="getval">
        <input type="hidden" value="" class="visits">

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
    })
    $(function () {
        var res = <%=resolution%>;
        var rate = <%=rate%>;
        if (res == 1080) {
            $("#selectdivul0").children("a").eq(0).click();
//            setTimeout(rateslider0,0)
        } else if (res == 720) {
            $("#selectdivul0").children("a").eq(1).click();
//            setTimeout(rateslider1,0)
        }
//        function rateslider0(){
//            var leftvaule = 100/6*(rate-2);
//            $("#fd-slider-fd-slider-form-elem-0").children(".fd-slider-handle").css("left",leftvaule+"px");
//            $("#fd-slider-fd-slider-form-elem-0").children(".fd-slider-range").css("width",leftvaule+"px");
//            $("#opt-lines").text(rate);
//        }
//        function rateslider1(){
//            var leftvaule = 100/3*(rate-1);
//            $("#fd-slider-fd-slider-form-elem-1").children(".fd-slider-handle").css("left",leftvaule+"px");
//            $("#fd-slider-fd-slider-form-elem-1").children(".fd-slider-range").css("width",leftvaule+"px");
//            $("#opt-lines1").text(rate);
//        }
    })
    function save() {
        saveAudioVedio();
    }
    function saveAudioVedio() {
        var resolution = $("#select0").val();
        var bitrate = "";
        if (resolution == 1080) {
            bitrate = $("#opt-lines").text();
        }
        else if (resolution == 720) {
            bitrate = $("#opt-lines1").text();
        }

        $.post('${pageContext.request.contextPath}/settings/Settings_saveSettingInfo.do', {
            settingType:1,
            recordinfoResolution: resolution,
            recordinfoBitRate: bitrate
        }, function (data) {
            if (data.success == true) {
                layer.msg(data.msg);
            }
            else {
                layer.msg(data.msg);
            }
        }, "json");
    }
</script>
