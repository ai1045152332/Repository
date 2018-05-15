<%@ page import="java.util.Map" %>
<%@ page import="com.honghe.recordweb.util.LicenseUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
    <title>系统设置 | 集控平台</title>
    <link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/time_celve.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/frontend/record/hpager.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/fd-slider.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
    <script src="${pageContext.request.contextPath}/js/lb_common/screendetailforrecord.js"></script>
    <link href="${pageContext.request.contextPath}/css/frontend/record/index.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/main.css" rel="stylesheet" type="text/css"/>
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
            margin-left: 0px;
            margin-top: 0px;
            text-indent: 10px;
            margin-left: 30px;
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

        .set_vv_linetext {
            width: 160px;
            font-size: 14px;
        }
    </style>
    <div class="set">
        <div class="set_head">
            <div class="settinghead">
                <%
                    Map<String, String> licenseInfo = (Map<String, String>) request.getAttribute("licenseInfo");
                    boolean isArea = !licenseInfo.isEmpty()&&Integer.parseInt(licenseInfo.get("hhtrec_device_num")) > LicenseUtils.HHTREC_DEVICE_MAXNUM;
                %>
                <ul>
                    <%if(!isArea){%>
                    <li onclick="javascript:jump(0)">
                        <div class="tb"></div>
                        台标
                    </li>
                    <li onclick="javascript:jump(1)">
                        <div class="videoinfo"></div>
                        录像信息
                    </li>
                    <li onclick="javascript:jump(2)">
                        <div class="living"></div>
                        直播
                    </li>
                    <li onclick="javascript:jump(3)">
                        <div class="nas"></div>
                        NAS存储设置
                    </li>
                    <li onclick="javascript:jump(4)">
                        <div class="passuser"></div>
                        录播主机用户名密码设置
                    </li>
                    <li onclick="javascript:jump(5)">
                        <div class="ftp"></div>
                        FTP设置
                    </li>
                    <%}%>
                    <li onclick="javascript:jump(6)" style="color:#28b779;">
                        <div class="license"></div>
                        License设置
                    </li>
                    <%if(!isArea){%>
                    <li onclick="javascript:jump(7)"><div class="second_nav_selected"></div>课表类型选择</li>
                    <li onclick="javascript:jump(8)"><div class="second_nav_schedule"></div>课表管理</li>
                    <li onclick="javascript:jump(9)"><div class="second_nav_celve"></div>作息时间策略</li>
                    <li onclick="javascript:jump(10)"><div class="record_name_setting"></div>录制文件名设置</li>
                    <%}%>
                </ul>
            </div>
        </div>
        <div class="win520_content" style="background: none;min-height: 540px;border:none">
           
            <div id="opts">
                    <%
                        if (licenseInfo.isEmpty()) {
                    %>

                <div class="set_bg" style="height: 290px;padding: 20px;">
                    <br/>

                    <div class="set_vv_line">
                        <div class="jkn_message_text" style="width: 80px;float: left;">&nbsp;</div>
                        <div class="jkn_message_radio3" style="width: auto;float: left;">

                            <%--<div class="headr" style="position: relative;z-index: 10;">--%>
                            <%--<span class="bgr"></span>网络激活--%>
                            <%--</div>--%>
                            <div class="headr" style="position: relative;z-index: 10;">
                                <span class="bgred"></span>授权码激活
                            </div>

                        </div>
                    </div>
                    <div class="set_vv_line">
                        <span class="set_vv_linetext">授权码</span>
                        <input class="set_vv_linesinput" value="" id="number"
                               type="text"/>
                    </div>
                </div>



                    <%
                    } else {
                    %>
                    <div class="mm_right" style="position: relative">
                        <div style="height: 290px;padding: 20px;" class="set_bg">
                            <div style="width: 100%;height: 260px;border:1px dashed #ccc">
                                <div class="set_vv_line" style="width: 300px;margin-left: 120px;height: 67px;line-height: 67px;">软件名称：集中控制管理平台<span><%=Integer.parseInt(licenseInfo.get("hhtrec_device_num")) > LicenseUtils.HHTREC_DEVICE_MAXNUM?"(区域版)":"(校园版)"%></span></div>
                                <img src="../../image/frontend/pass.png"  style="margin-left: 120px;"/>
                                <%--<div class="set_vv_line" style="width: 300px;margin-left: 120px;line-height: 30px;margin-top: 15px;">可控制大屏设备的数量：<%=licenseInfo.get("hhtc_device_num")==null?0:licenseInfo.get("hhtc_device_num").toString()%></div>--%>
                                <div class="set_vv_line" style="width: 300px;margin-left: 120px;line-height: 30px;">可控制录播设备的数量：<%=licenseInfo.get("hhtrec_device_num")==null?0:licenseInfo.get("hhtrec_device_num").toString()%></div>
                                <div class="set_vv_line" style="width: 300px;margin-left: 120px;height: auto;">描述：<%=licenseInfo.get("device_desc")==null?"无":licenseInfo.get("device_desc").toString()%></div>
                            </div>
                            <div style="width: 100%;line-height: 30px;float: left;font-size: 12px;text-align:center">鸿合科技感谢您对本平台的大力支持</div>
                        </div>
                    </div>
                    <%
                        }
                    %>
            </div>
            <%
                if (licenseInfo.isEmpty()) {
            %>
            <div class="set_vv_foot">
                <div class="set_vv_save" onclick="save()">提交</div>
            </div>
            <%
                }
            %>

        </div>
    </div>
        <div class="foot">
            <jsp:include page="/pages/frontend/footer.jsp"></jsp:include>
        </div>
<input type="hidden" style="display: none" id="licenseParams" value="" newsId=""
       urlhead="${pageContext.request.contextPath}"/>
</body>
<script>

    var urlHead = $("#licenseParams").attr("urlhead");
    var flag_key = 0;
    function save() {
        if(flag_key == 1) {
            return;
        }
        flag_key=1;
        var number = $("#number").val().trim();
        if (number.length == 0) {
            layer.alert("授权码不能为空");
            flag_key=0;
            return;
        }

        var regex = /(^[A-Za-z0-9]+$)/;

        var ret = regex.test(number);
        if(ret==false || number.length!=20){
            layer.alert("授权码必须是20位数字或字母组成");
            flag_key=0;
            return;
        }
        layer.msg("注册中，请等待");
        $.post('${pageContext.request.contextPath}/settings/Settings_saveLicense.do', {
            licenseNum: number,
            applicationname: "",
            applicationcompany:"",
            applicationtell:"",
            applicationorder:"",
            type_activation:"1"
        }, function (data) {
            if (data.success) {
                flag_key=0;
                location.reload();
            }
            else {
                layer.msg(data.msg);
                flag_key=0;
            }
        }, "json");
    }


    //消息类型切换
//    $(".jkn_message_radio3 div").click(function () {
//        var ind = $(this).index();
//        if (ind == 0) {
//            location.href = urlHead + "/settings/Settings_findLicense.do"
//        } else {
//            location.href = urlHead + "/settings/Settings_findLicenseKey.do"
//        }
//        $(this).children("span").addClass("bgred").removeClass("bgr")
//        $(this).siblings().children("span").addClass("bgr").removeClass("bgred")
//    })

</script>
</html>

