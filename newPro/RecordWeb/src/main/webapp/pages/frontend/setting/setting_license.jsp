<%@ page import="com.honghe.authority.AuthorityCheck" %>
<%@ page import="com.honghe.recordweb.action.SessionManager" %>
<%@ page import="com.honghe.recordweb.util.LicenseUtils" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.honghe.recordweb.util.CommonName" %>
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


</head>
<body>

<div class="public" style="min-width: 160px;">
    <jsp:include page="/pages/frontend/equipment_header.jsp"></jsp:include>
    <%
        String device_type = SessionManager.get(request.getSession(), SessionManager.Key.DeviceType);
    %>
    <%
        if (device_type.equals(CommonName.DEVICE_TYPE_SCREEN) || device_type.equals(CommonName.DEVICE_TYPE_WHITEBOARD)) {
    %>
    <script src="${pageContext.request.contextPath}/js/common/screendetail.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/frontend/setting/setting.js"></script>
    <link href="${pageContext.request.contextPath}/css/frontend/index.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css"/>
    <%
    } else if (device_type.equals(CommonName.DEVICE_TYPE_RECOURD)) {
    %>
    <script src="${pageContext.request.contextPath}/js/lb_common/screendetailforrecord.js"></script>
    <link href="${pageContext.request.contextPath}/css/frontend/record/index.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/main.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/lb_frontend/setting/setting.js"></script>
    <%
        }
    %>
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
        .Initialize{
            background: #fff url("../../../image/frontend/n_icon_141016.png") no-repeat scroll -175px -489px;
            float: left;
            height: 25px;
            margin-left: 38px;
            margin-top: 10%;
            padding-left: 10px;
            width: 25px;
        }
    </style>
    <div class="set">
        <div class="set_head">
            <div class="settinghead">
                <ul>
                    <%
                        if (device_type.equals(CommonName.DEVICE_TYPE_SCREEN) || device_type.equals(CommonName.DEVICE_TYPE_WHITEBOARD)) {
                    %>
                    <!--
                        描述：大屏
                    -->
                    <li style="color:#28b779;" onclick="javascript:turn(0)">
                        <div class="license" ></div>
                        License设置
                    </li>
                    <% if(device_type == "hhtc"){%>
                    <li onclick="javascript:turn(1)">
                        <div class="Initialize"></div>
                        大屏初始化设置
                    </li>
                    <%}%>

                    <%
                    } else if (device_type.equals(CommonName.DEVICE_TYPE_RECOURD)) {
                    %>
                    <!--
                        描述：集控
                    -->
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
                    <li onclick="javascript:jump(6)" style="color:#28b779;">
                        <div class="license"></div>
                        License设置
                    </li>
                    <li onclick="javascript:jump(7)">
                        <div class="second_nav_selected"></div>
                        课表类型选择
                    </li>
                    <li onclick="javascript:jump(8)">
                        <div class="second_nav_schedule"></div>
                        课表管理
                    </li>
                    <li onclick="javascript:jump(10)"><div class="record_name_setting"></div>录制文件名设置</li>
                    <%
                        }
                    %>


                </ul>
            </div>
        </div>
        <style type="text/css">
            .mm_right {
                height: 93%;
            }
        </style>
        <%--<div class="win520_content" style="background: none;min-height: 540px;border:none">--%>
        <%

            String reg_state = (String) request.getAttribute("reg_state");
            String register = (String) request.getAttribute("register");
            boolean isRegister = false;

            if ("true".equals(register) || LicenseUtils.STATE_WAITING_EXAMINE.equals(reg_state) || LicenseUtils.STATE_NOT_PASS_EXAMINE.equals(reg_state)) {
                isRegister = true;
            } else {
                isRegister = false;
            }

            AuthorityCheck.getInfo();
            Map<String, String> licenseInfo = (Map<String, String>) request.getAttribute("licenseInfo");
        %>
        <%--<div id="opts">--%>


        <%--<div style="height: 250px;" class="set_bg">--%>

        <%
            if (!isRegister) {
        %>
        <div class="mm_right" style="position: relative;" id="displaynone1">
            <style>
                .set_vv_linetext {
                    width: 160px;
                }
            </style>
            <div style="background: none;border:none;position:absolute;left:0;right:0;top:0;right:0;margin: auto;"
                 class="win520_content">
                <div style="height: 250px;" class="set_bg">
                    <div class="set_vv_line">
                        <div style="width: 100px;float: left;margin-top: 2px;" class="jkn_message_text">&nbsp;</div>
                        <div style="width: auto;float: left;margin-top: 2px;" class="jkn_message_radio1">
                            <div style="position: relative;z-index: 10;" class="headr" id="code">
                                <span class="bgr"></span>授权码激活
                            </div>
                        </div>
                    </div>
                    <div class="set_vv_line">
                        <span class="set_vv_linetext">授权码</span>
                        <input type="text" id="codeval" value="" maxlength="20" class="set_vv_linesinput"
                               onkeyup="upperCase(this.id)" onafterpaste="upperCase(this.id)">
                    </div>

                    <div class="set_vv_line">
                        <div style="width: 100px;float: left;margin-top: 2px;" class="jkn_message_text">&nbsp;</div>
                        <div style="width: auto;float: left;margin-top: 2px;" class="jkn_message_radio1">
                            <div style="position: relative;z-index: 10;" class="headr" id="net">
                                <span id="web" class="bgr"></span>网络激活
                            </div>
                        </div>
                    </div>
                    <div class="set_vv_line">
                        <span class="set_vv_linetext">申请人</span>
                        <input type="text" id="name" maxlength="20" value="" class="set_vv_linesinput"
                               onblur="symbols(this.id)"
                               onafterpaste="this.value=this.value.replace(//[/@#.,;:'!$%^&*()<>?{}\\|\[\]`~+=；：‘？/》《。，、·~！￥……（）——’\&quot;“”【】]/g,'')">
                    </div>
                    <div class="set_vv_line">
                        <span class="set_vv_linetext">电话</span>
                        <input type="text" id="phone" value="" maxlength="20" class="set_vv_linesinput"
                               onblur="this.value=this.value.replace(/[^0-9-]/g,'')"
                               onafterpaste="this.value=this.value.replace(/[^0-9-]/g,'')">
                    </div>
                    <div class="set_vv_line">
                        <span class="set_vv_linetext">公司/学校</span>
                        <input type="text" id="unit" value="" maxlength="22" class="set_vv_linesinput"
                               onblur="this.value=this.value.replace(/[/@#.,;:'!$%^&*()<>?{}\\|\[\]`~+=；：‘？/》《。，、·~！￥……（）——’\&quot;“”【】]/g,'')"
                               onafterpaste="this.value=this.value.replace(//[/@#.,;:'!$%^&*()<>?{}\\|\[\]`~+=；：‘？/》《。，、·~！￥……（）——’\&quot;“”【】]/g,'')">
                    </div>
                    <div class="set_vv_line">
                        <span class="set_vv_linetext">合同单号</span>
                        <input type="text" id="contractNumber" maxlength="24" value="" class="set_vv_linesinput"
                               onblur="this.value=this.value.replace(/\W/g,'')"
                               onafterpaste="this.value=this.value.replace(/\W/g,'')">
                    </div>
                </div>
                <div class="set_vv_foot">
                    <div onclick="save()" class="set_vv_save">提交</div>
                </div>
                <script>
                    $(function () {
                        //授权码单击事件
                        $(".headr").click(function () {
                            $(".headr").children("span").attr("class", "bgr")
                            $(this).children("span").attr("class", "bgred")
                            var flag = $(this).attr("id");
                            if (flag == "code") {
                                $("#codeval").removeAttr("disabled");
                                $("#name").attr("disabled", "disabled");
                                $("#phone").attr("disabled", "disabled");
                                $("#unit").attr("disabled", "disabled");
                                $("#contractNumber").attr("disabled", "disabled");
                            }
                            else {
                                $("#name").removeAttr("disabled");
                                $("#phone").removeAttr("disabled");
                                $("#unit").removeAttr("disabled");
                                $("#contractNumber").removeAttr("disabled");
                                $("#codeval").attr("disabled", "disabled");
                            }
                        })
                        $("#code").click()
                    })
                </script>
            </div>
        </div>
        <%
        } else if (LicenseUtils.STATE_WAITING_EXAMINE.equals(reg_state)) {
        %>
        <div class="mm_right" style="position: relative;" id="displaynone2">
            <div style="position: relative;left:50%;margin-left:-90px" class="nofind_newline">
                您的激活申请正在审核中.....
            </div> 
        </div>
        <%
        } else if (LicenseUtils.STATE_NOT_PASS_EXAMINE.equals(reg_state)) {
        %>
        <div class="mm_right" style="position: relative;" id="displaynone2">
            <div style="position: relative;left:50%;margin-left:-90px;text-align: left" class="nofind_newline">
                审核未通过,请联系客服或者输入授权码:
                <div class="set_vv_line">
                    <input type="text" id="codeval" value="" maxlength="20" class="set_vv_linesinput" style="margin: 0"
                           onkeyup="upperCase(this.id)" onafterpaste="upperCase(this.id)">
                </div>
                <div class="set_vv_foot">
                    <div onclick="save2()" class="set_vv_save">提交</div>
                </div>
            </div>
        </div>
        <%
        } else {
        %>
        <div class="mm_right" style="position: relative">
            <div style="height: 290px;padding: 20px;" class="set_bg">
                <div style="width: 100%;height: 260px;border:1px dashed #ccc">
                    <div class="set_vv_line" style="width: 300px;margin-left: 120px;height: 67px;line-height: 67px;">
                        软件名称：集中控制管理平台
                    </div>
                    <img src="../../image/frontend/pass.png" style="margin-left: 120px;"/>
                    <div class="set_vv_line"
                         style="width: 300px;margin-left: 120px;line-height: 30px;margin-top: 15px;">
                        可控制大屏设备的数量：<%=licenseInfo.get("hhtc_device_num") == null ? 0 : licenseInfo.get("hhtc_device_num").toString()%>
                    </div>
                    <div class="set_vv_line" style="width: 300px;margin-left: 120px;line-height: 30px;">
                        可控制录播设备的数量：<%=licenseInfo.get("hhtrec_device_num") == null ? 0 : licenseInfo.get("hhtrec_device_num").toString()%>
                    </div>
                    <div class="set_vv_line" style="width: 300px;margin-left: 120px;height: auto;">
                        描述：<%=licenseInfo.get("device_desc") == null ? 0 : licenseInfo.get("device_desc").toString()%>
                    </div>
                </div>
                <div style="width: 100%;line-height: 30px;float: left;font-size: 12px;text-align:center">
                    鸿合科技感谢您对本平台的大力支持。
                </div>
            </div>
        </div>
        <%
            }
        %>
    </div>
    <div class="foot">
        <jsp:include page="/pages/frontend/footer.jsp"></jsp:include>
    </div>
</div>
<input type="hidden" style="display: none" id="licenseParams" value="" newsId=""
       urlhead="${pageContext.request.contextPath}"/>
</body>
<script>

    var urlHead = $("#licenseParams").attr("urlhead");

    function save2() {
        var num = $("#codeval").val().trim();
        var name = "";
        var phone = "";
        var unit = "";
        var activationType = "1";
        var contractNumber = "";

        if (num.length == 0) {
            layer.alert("授权码不能为空");
            return;
        } else {
            var patrn = /^[A-Z0-9]{20}$/;
            if (!patrn.exec(num)) {
                layer.alert("请输入正确的授权码");
                return;
            }
        }

        $.post('${pageContext.request.contextPath}/settings/Settings_saveLicense.do', {
                    licenseNum: num,
                    applicationname: name,
                    applicationcompany: unit,
                    applicationtell: phone,
                    applicationorder: contractNumber,
                    type_activation: activationType
                }
                ,
                function (data) {
                    if (data.success == true) {
                        location.reload();
                    }
                    else {
                        if (data.msg == "等待申请通过") {
                            location.reload();
                        }
                        else {
                            layer.msg(data.msg);
                        }
                    }
                }
                ,
                "json"
        )
        ;
    }

    function save() {
        var num = $("#codeval").val().trim();
        var name = $("#name").val().trim();
        var phone = $("#phone").val().trim();
        var unit = $("#unit").val().trim();
        var activationType = "1";
        var contractNumber = $("#contractNumber").val().trim();

        if ($("#web").attr("class") == "bgred") {

            if (name.length == 0) {
                layer.alert("申请人不能为空");
                return;
            }
            if (phone.length == 0) {
                layer.alert("电话不能为空");
                return;
            } else {
                var patrn = /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/;
                if (!patrn.exec(phone)) {
                    layer.alert("请输入正确的电话号码");
                    return;
                }
            }


            if (unit.length == 0) {
                layer.alert("公司/或学校不能为空");
                return;
            } else {
                if (name.length > 30) {
                    layer.alert("请输入正确的公司/或学校");
                    return;
                }
            }

            if (contractNumber.length == 0) {
                layer.alert("合同单号不能为空");
                return;
            } else {
                var patrn = /^[A-Za-z0-9_]{1,20}$/;
                if (!patrn.exec(contractNumber)) {
                    layer.alert("请输入正确的合同单号");
                    return;
                }
            }
            activationType = "2";
        } else {
            if (num.length == 0) {
                layer.alert("授权码不能为空");
                return;
            } else {
                var patrn = /^[A-Z0-9]{20}$/;
                if (!patrn.exec(num)) {
                    layer.alert("请输入正确的授权码");
                    return;
                }
            }
        }

        layer.msg("注册中，请等待");
        $.post('${pageContext.request.contextPath}/settings/Settings_saveLicense.do', {
                    licenseNum: num,
                    applicationname: name,
                    applicationcompany: unit,
                    applicationtell: phone,
                    applicationorder: contractNumber,
                    type_activation: activationType
                }
                ,
                function (data) {
                    if (data.success == true) {
                        location.reload();
                    }
                    else {
                        if (data.msg == "等待申请通过") {
                            location.reload();
                        }
                        else {
                            layer.msg(data.msg);
                        }
                    }
                }
                ,
                "json"
        )
        ;
    }

    function register() {
        location.href = urlHead + "/settings/Settings_register.do";
    }


    //消息类型切换
    $(".jkn_message_radio3 div").click(function () {
        var ind = $(this).index();
        if (ind == 0) {
            location.href = urlHead + "/settings/Settings_findLicense.do"
        } else {
            location.href = urlHead + "/settings/Settings_findLicenseKey.do"
        }
        $(this).children("span").addClass("bgred").removeClass("bgr")
        $(this).siblings().children("span").addClass("bgr").removeClass("bgred")
    })

    function upperCase(x) {
        var y = document.getElementById(x).value
        document.getElementById(x).value = y.toUpperCase()
    }
    function symbols(x) {
        var y = document.getElementById(x).value
        document.getElementById(x).value = y.replace(/[/@#.,;:'!$%^&*()<>?{}\\|\[\]`~+=；：‘？/》《。，、·~！￥……（）——’\&quot;“”【】]/g, '')
    }
</script>
</html>

