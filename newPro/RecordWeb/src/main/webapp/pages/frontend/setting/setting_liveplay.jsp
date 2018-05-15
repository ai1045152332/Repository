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
    <link href="${pageContext.request.contextPath}/css/frontend/time_celve.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/frontend/record/hpager.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/index.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/main.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/fd-slider.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/common/jquery-1.9.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/jquery.cookie.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/lb_common/screendetailforrecord.js"></script>
    <script src="${pageContext.request.contextPath}/js/lb_common/radio.js"></script>
    <script src="${pageContext.request.contextPath}/js/lb_common/selectmore_set.js"></script>

    <!--滚动条模拟码率等引用顺序不能改变-->
    <script src="${pageContext.request.contextPath}/js/common/prettify.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/fd-slider.js"></script>
    <script src="${pageContext.request.contextPath}/js/lb_common/scroll.js"></script>

    <script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/lb_frontend/setting/setting.js"></script>
</head>
<body>
<div class="public" style="min-width: 1680px;">
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
		                        <li onclick="javascript:jump(1)"><div class="videoinfo"></div>录像信息</li>
		                        <li onclick="javascript:jump(2)" style="color:#28b779;"><div class="living"></div>直播</li>
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
                        <%
                         Setting setting = (Setting) request.getAttribute("setting");
                         String liveAddr = "";
                         if(setting != null){
                         liveAddr = setting.getLiveAddr();
                         }
                        %>
                    <div class="win520_content" style="background: none;min-height: 540px;border:none">

                        <div id="opts">
                            <div class="set_bg" style="height: 60px;">
                                <%--<div class="set_vv_line">--%>
                                    <%--<span class="set_vv_linetext">分辨率</span>--%>

                                    <%--<div class="selectdiv" style="background: NONE;">--%>
                                        <%--<%=liveResolution%>P--%>
                                    <%--</div>--%>
                                    <%--<!--<div class="sall">--%>
                                        <%--<select class="select" id="select0">--%>
                                            <%--<option value="" selected="selected">高清(1024p)</option>--%>
                                            <%--<option value="">超清(1080p)</option>--%>
                                        <%--</select>--%>

                                        <%--<div class="selectdivall" id="selectdivall0">--%>
                                            <%--<div class="selectdiv" id="selectdiv0"></div>--%>
                                            <%--<div class="selectdivul" id="selectdivul0"></div>--%>
                                        <%--</div>--%>
                                    <%--</div>-->--%>
                                <%--</div>--%>
                                <%--<!--<div class="set_vv_line">--%>
                                    <%--<span class="set_vv_linetext">帧率</span>--%>

                                    <%--<div class="selectdiv" style="background: NONE;">--%>
                                        <%--<%=liveFrameRate%>帧/秒--%>
                                    <%--</div>--%>
                                    <%--<div class="sall">--%>
                                        <%--<select class="select" id="select2">--%>
                                            <%--<option value="" selected="selected">30帧/秒</option>--%>
                                            <%--<option value="">60帧/秒</option>--%>
                                        <%--</select>--%>

                                        <%--<div class="selectdivall" id="selectdivall2">--%>
                                            <%--<div class="selectdiv" id="selectdiv2"></div>--%>
                                            <%--<div class="selectdivul" id="selectdivul2"></div>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>-->--%>
                                <%--<div class="set_vv_line">--%>
                                    <%--<span class="set_vv_linetext">码率</span>--%>

                                    <%--<div class="selectdiv" style="background: NONE;">--%>
                                        <%--<%=liveBitRate%>kbp/s--%>
                                    <%--</div>--%>
                                    <%--<!--<div class="set_vv_linescroll">--%>
                                        <%--<input type="text" name="lines3" min="0" max="1024" value="0">--%>
                                    <%--</div>--%>
                                    <%--<div class="litvaltext">--%>
                                        <%--<span id="opt-lines3" class="litval">0</span><span style="float: right;">kbp/s</span>--%>
                                    <%--</div>-->--%>

                                <%--</div>--%>
                                    <br/><br/>
                                <div class="set_vv_line">
                                    <span class="set_vv_linetext">直播服务器地址</span>
                                    <input class="set_vv_linesinput" value="<%=liveAddr%>" id="addr"
                                           type="text"/>
                                </div>

                                <div class="set_vv_line" style="margin-top: 10px;">
                                    <span class="set_vv_linetext" style="margin-right: 10px;"></span>


                                    <%--<div class="all">--%>
                                        <%--<div class="head" bingo="false">--%>
                                            <%--<div class="bg" style="margin-top: 0;"></div>--%>
                                            <%--断开后提示重新发布--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                </div>
                            </div>


                        </div>
                        <div class="set_vv_foot">
                            <div class="set_vv_save" onclick="save()">保存</div>
                        </div>
                    </div>
                    </div>
    <div class="foot" >
	<jsp:include page="/pages/frontend/footer.jsp"></jsp:include>
</div>
</div>

</body>
</html>
<input type="hidden" value="" class="getval">
<input type="hidden" value="" class="visits">
<script>

    $(function () {
        var liveIsreconnection = $("#liveIsreconnection").val();
        if (liveIsreconnection == "1") {
            $(".head").attr("bingo", "true");
            $(".head").children("div").addClass("bged").removeClass("bg")
        }
        $(".head").click(function () {
            var flag = $(this).attr("bingo");
            if (flag == "false") {
                $(this).attr("bingo", "true");
                $(this).children("div").addClass("bged").removeClass("bg")
            } else {
                $(this).attr("bingo", "false");
                $(this).children("div").addClass("bg").removeClass("bged")
            }
        })
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
    function save() {

        var addr = $("#addr").val().trim();
        if (addr.length == 0) {
            layer.alert("地址不能为空");
            return;
        }
//        var checkbox = $(".head").attr("bingo");
//        var liveIsreconnection = "1";
//        if (checkbox == "false") {
//            liveIsreconnection = "0";
//        }
        $.post('${pageContext.request.contextPath}/settings/Settings_saveSettingInfo.do', {
            settingType: 2,
            liveAddr: addr
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
