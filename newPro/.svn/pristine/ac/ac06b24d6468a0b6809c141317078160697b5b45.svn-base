<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.honghe.recordweb.service.frontend.user.UserService" %>
<%@ page import="com.honghe.recordweb.service.frontend.user.Role" %>
<%@ page import="com.honghe.recordweb.util.ConfigureUtil" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="${pageContext.request.contextPath}">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
    <title>设备控制 | 集控平台</title>
    <%@include file="websocket.jsp" %>
    <link href="${pageContext.request.contextPath}/js/common/layer/skin/layer.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/js/common/layer/skin/layer.ext.css" rel="stylesheet"
          type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/fd-slider.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>

    <script src="${pageContext.request.contextPath}/js/common/screendetail.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/tree_jkn.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/perfect-scrollbar.with-mousewheel.min.js"></script>
    <!--滚动条模拟码率等引用顺序不能改变-->
    <script src="${pageContext.request.contextPath}/js/common/prettify.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/fd-slider.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/scroll.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/jquery.cookie.js" type="text/javascript"></script>
    <!--
        作者：474569696@qq.com
        时间：2014-10-07
        描述：js引用顺序不可以变,会导致select模拟失效
    -->
    <style>
        .hostoverflow {
            float: left;
            text-overflow: elliis;
            overflow: hidden;
            max-width: 150px;
        }

        .tree_titleb {
            display: none;
        }

        .fd-slider-handle {
            border: 1px solid #a3a3a3;
            background: #f0f0f0;
            border-radius: 5px;
            height: 9px;
            top: 5px;
            width: 9px;
        }

        #htpower {
            width: 110px;
        }

    </style>
</head>
<body>
<div class="public">
    <jsp:include page="/pages/frontend/pr_header.jsp"></jsp:include>
    <style>
        .hostoverflow {
            float: left;
            text-overflow: ellipsis;
            overflow: hidden;
            margin-left: 25px;
            width: 150px;

        }

        .tree_titleaiconmain_jkn, .tree_titleaiconmained_jkn {
            position: absolute;
            left: 10px;
            top: 0px;
        }

        .jkn_moresonmenu::before {
            border: 1px solid #dbdbdb;
            background: #fff;
            content: "";
            height: 8px;
            left: 35px;
            position: absolute;
            top: -5px;
            transform: rotate(45deg);
            width: 8px;
            z-index: -30;
        }

        .jkn_moresonmenu::after {
            background: #fff none repeat scroll 0 0;
            content: "";
            height: 8px;
            left: 34px;
            position: absolute;
            top: 0;
            width: 12px;
            z-index: -30;
        }

        .jkn_moresonmenu {
            border: 1px solid #dbdbdb;
            border-radius: 3px;
            background: #fff;
            position: absolute;
            height: 96px;
            top: 30px;
            width: 78px;
            z-index: 1;
        }
    </style>
    <div class="public_left22 floatleft">
        <input type="hidden" value="" id="selected_host" ip="">

        <div class="equipment" opening="no">
            教室设备
            <%--<span class="refrash"></span>--%>
            <%
                if (!ConfigureUtil.isOnlyHtpr()) {
            %>
                <div class="public_head_changesystem"></div>
            <%
                }
            %>
        </div>
        <%
            if (!ConfigureUtil.isOnlyHtpr()) {
        %>
        <jsp:include page="/pages/frontend/equipmentlist_fragment.jsp"></jsp:include>
        <%
            }
        %>

        <div id="otp_vedeoabout_rvideo">
            <div class='contentr'>
                <%
                    Map<String, List<Map<String, String>>> cmdMap = (Map<String, List<Map<String, String>>>) request.getAttribute("cmdMap");
                    Map<String, String> map = new HashMap();
                    map.put("电源管理", "power");
                    map.put("信号源切换", "signal");
                    map.put("音量调节", "audio_control");
                    map.put("音响模式调节", "audio_mode");
                    map.put("触摸功能", "touch_screen");
                    map.put("远程节能", "remote_energy");
                    map.put("投影仪开关", "htpower");
                    if (cmdMap != null && cmdMap.size() > 0) {
                        Iterator iterator = cmdMap.entrySet().iterator();
                        while (iterator.hasNext()) {
                            Map.Entry entry = (Map.Entry) iterator.next();
                            String dspec_id = entry.getKey().toString();
                            List<Map<String, String>> list = (List<Map<String, String>>) entry.getValue();
                            if (list != null && list.size() > 0) {
                                for (int k = 0; k < list.size(); k++) {
                                    String cmdgroup = map.get(list.get(k).get("cmd_group").toString());
                %>
                <input type="hidden" id="cmd_<%=cmdgroup%>_<%=dspec_id%>" cmdgroup="<%=cmdgroup%>"
                       cmdcode="<%=list.get(k).get("code_cmd").toString()%>"
                       cmdname="<%=list.get(k).get("codetype").toString()%>">
                <%
                                }
                            }
                        }

                    }
                %>
                <%--<input id="host_cmd_all" type="hidden" remote_energy="<%=map_cmd.get("remote_energy")%>" touch_screen="<%=map_cmd.get("touch_screen")%>" audio_mode="<%=map_cmd.get("audio_mode")%>" audio_control="<%=map_cmd.get("audio_control")%>" signal="<%=map_cmd.get("signal")%>" power="<%=map_cmd.get("power")%>">--%>
                <%--<input id="host_cmd_code_all" type="hidden" remote_energy_cmd="<%=map_cmd.get("remote_energy_cmd")%>" touch_screen_cmd="<%=map_cmd.get("touch_screen_cmd")%>" audio_mode_cmd="<%=map_cmd.get("audio_mode_cmd")%>" audio_control_cmd="<%=map_cmd.get("audio_control_cmd")%>" signal_cmd="<%=map_cmd.get("signal_cmd")%>" power_cmd="<%=map_cmd.get("power_cmd")%>">--%>
                <%
                    //}
                %>
                <div class="tree">
                    <a href="javascript:void(0)" class="tree_titleb tree_titleb_open">所有设备</a>

                    <div class="public_left" style="float: left;">

                        <%
                            List<Map> groupTreeList = (List<Map>) request.getAttribute("groupTree");
                            if (groupTreeList.size() > 0) {
                        %>
                        <input type="hidden" value="<%=groupTreeList.get(0).get("group_id").toString()%>"
                               id="group_id">
                        <%
                            for (Map groupTreeMap : groupTreeList) {
                                String group_id = groupTreeMap.get("group_id").toString();
                                String group_name = groupTreeMap.get("group_name").toString();
                                List<Map> hostList = (List<Map>) groupTreeMap.get("host_list");
                                if (null == hostList || hostList.size() == 0) {
                                    continue;
                                }
                        %>
                        <div class="tree_title tree_title_close ">
                            <%
                                if (!hostList.isEmpty()) {
                            %>
                            <a href="javascript:void(0)" class="tree_titlea"
                               name="<%=group_id%>" style="text-indent: 52px;"><%=group_name.trim()%>
                            </a>
                            <span class="tree_titleaiconmain_jkn"></span>

                            <div class="tree_content">
                                <%
                                    for (Map host : hostList) {
                                        String host_id = host.get("id").toString();
                                        String host_name = host.get("name").toString();
                                        //String host_dspec = host.get("dspec").toString();
                                        String host_type = host.get("type").toString();
                                        String host_token = host.get("token").toString();
                                        //String recordType = host.get("host_desc").toString();
                                        String host_ip = host.get("host_ip").toString();
                                        String statusStyle = "tree_content_onlinebg";
                                        String dspecid = host.get("dspecid").toString();
                                        String status = host.get("status").toString();
                                        if (status.equals("Offline")) {
                                            statusStyle = "tree_content_nousebg";
                                        }
                                %>
                                <div class="tree_contenta" title="<%=host_ip%>"  <% //=onclick%> id="<%=host_id%>"

                                     type="<%=host_type%>" token="<%=host_token%>"><span
                                        class="<%=statusStyle%>" style="background: none"></span>
                                    <span class="hostoverflow"> <%=host_name%></span>

                                    <div id="jkn_checkbox_<%=host_id%>" class="jkn_treecheckbox" hostId="<%=host_id%>"
                                         status="<%=status%>" dspec="<%=dspecid%>" hostIp="<%=host_ip%>"></div>
                                </div>
                                <%
                                    }
                                %>
                            </div>
                            <%
                                }
                            %>
                        </div>
                        <%
                            }
                        %>
                    </div>
                </div>
                <input type="hidden" value="<%=groupTreeList.get(0).get("group_id")%>" id="input_gorupid"/>
                <%} else {%>
            </div>
        </div>
        <%
            }
        %>
    </div>
</div>
</div>
<input type="hidden" id="perfectscrollval" top="" height="" num="" which="" scrolltop=""/>

<div class="public_right floatleft">
    <div class="xk_option">
        <div class="xk_options" style="width: auto">
            <span class="xk_options_open" id="power">
                <span class="jkn_mc_powericon" id="power_img"></span>
                <span class="xk_options_text">电源管理</span>
                <div class="jkn_mc_powerson" style="z-index: -1;">
                </div>
            </span>
            <span class="xk_options_open" id="htpower">
                <span class="jkn_mc_powericon" id="htppower_img"></span>
                <span class="xk_options_text" style="width:80px;">投影仪开关</span>
                <div class="jkn_mc_powerson" style="z-index: -1;">
                </div>
            </span>
            <%--<button onclick="dddd()">ssssss</button>--%>
            <span class="xk_options_close" id="signal">
                <span class="jkn_mc_signalicon" id="signal_img"></span>
                <span class="xk_options_text">信号切换</span>
                <div class="jkn_mc_powerson" style="z-index: -1;">
                </div>
            </span>
        </div>
        <div class="xk_fournine">
            <div class="xk_four" title="四屏显示" onclick="change_screen(4)"></div>
            <div class="xk_nine" title="九屏显示" onclick="change_screen(9)"></div>
        </div>
        <div id="classmessage" class="xk_video_group_warn" style="float:left;width:98px;left: 700px"></div>
    </div>
    <iframe style="z-index: 1" id="go" src="" frameborder="0" class="iframe" scrolling="no"></iframe>
</div>
<div class="foot">
    <jsp:include page="/pages/frontend/footer.jsp"></jsp:include>
</div>
<input type="hidden" value="" id="selectid">
</body>
<script>
    function change_size() {
        $('#otp_vedeoabout_rvideo').perfectScrollbar();
        prettyPrint();
    }

    $(function () {
        //获取命令行高度
        var optionshei = $(".xk_option").height()
        //更多
        $(".xk_options_more").click(function () {
            $(".xk_options").find(".jkn_mc_powerson").css("z-index", "-1")

            $(".jkn_moresonmenu").css("top", optionshei + "px");
            var zind = $(".jkn_moresonmenu").css("z-index");
            if (zind > 0) {
                $(".jkn_moresonmenu").css("z-index", "-1");
            } else {
                $(".jkn_moresonmenu").css("z-index", "10");
            }
        })
        var exphei = document.documentElement.clientHeight;
        var expwidth = document.documentElement.clientWidth;
        if (expwidth <= 1200) {
            expwidth = 1200
        }
        if (exphei <= 590) {
            exphei = 590
        }
        var treewid = expwidth * 0.22
        $("#otp_vedeoabout_rvideo").width(treewid).height(exphei * 0.86 * 0.93);
        $('#otp_vedeoabout_rvideo').perfectScrollbar();
        prettyPrint();
        //鼠标经过
        $(".jkn_moresonmenu").children("span").mouseover(function () {
            $(this).css("background", "#eeeeee")
        }).mouseout(function () {
            $(this).css("background", "#fff")
        })
        $('#otp_vedeoabout_rvideo').perfectScrollbar();
        prettyPrint();
    });


    $(function () {
        //切换系统
        $(".equipment").click(function () {
            $(".public_head_system").css("top", $(".equipment").height() + "px")
            $(".public_head_system").height($("#otp_vedeoabout_rvideo").height())
            var flag = $(this).attr("opening");
            if (flag == "no") {
                $(".public_head_system").show()
                //$(".public_head_changesystem").css("transform","rotate(180deg)")
                $(this).attr("opening", "true");
            } else {
                $(".public_head_system").hide()
                //$(".public_head_changesystem").css("transform","rotate(0deg)")
                $(this).attr("opening", "no");
            }
        })
        $(".public_head_system li").click(function () {
            var ind = $(this).index();
            if ($.trim($(this).text()) == "录播设备") {
                location.href = "${pageContext.request.contextPath}/viewclass/Viewclass_getHostGroup.do";
            } else if ($.trim($(this).text()) == "大屏设备") {
                location.href = "${pageContext.request.contextPath}/dmanager/DManager_getHostGroup.do"
            } else if ($.trim($(this).text()) == "投影仪设备") {
                location.href = "${pageContext.request.contextPath}/htprojector/HTProjector_getHostGroup.do"
            }else if ($.trim($(this).text()) == "白板一体机设备") {
                location.href = "${pageContext.request.contextPath}/hhtwhiteboard/HHTWhiteboard_getHostGroup.do"
            }
        })

    });
    var root_path;

    $(function () {
        function findsame(dspecArr, cmdArr, cmd_codeArr) {
            if (dspecArr.length > 1) {

            }
        }

        var cmd_code = [];
        var cmd_name = [];

        function getSame_1(cmd_codeArr, cmd_nameArr) {
            var arr = [];
            var arr_name = [];
            var arr_cmd = [];
            for (var n = 0; n < cmd_codeArr.length; n++) {
                var signal_cmdArr = cmd_codeArr[n].split(",");
                var signal_nameArr = cmd_nameArr[n].split(",");
                var signal_cmdArr_1 = [];
                var signal_nameArr_1 = [];
                if (cmd_codeArr[n + 1]) {
                    signal_cmdArr_1 = cmd_codeArr[n + 1].split(",");
                    signal_nameArr_1 = cmd_nameArr[n + 1].split(",");
                }

                if (signal_cmdArr_1.length > 0) {
                    var same = getSame(signal_cmdArr, signal_cmdArr_1, signal_nameArr, signal_nameArr_1);
                    arr_cmd.push(same[0].join(","));
                    arr_name.push(same[1].join(","));
                }
            }
            cmd_code = arr_cmd;
            cmd_name = arr_name;
            if (cmd_code.length > 1) {
                getSame_1(cmd_code, cmd_name);
            }
        }

        function getSame(a, b, a_name, b_name) {
            var c = [];
            var c_name = [];
            var tmp = a.concat(b);
            var tmp_name = a_name.concat(b_name);
            //tmp = tmp.concat(s);
            var o = [];
            var o_name = [];
            for (var i = 0; i < tmp.length; i++) {
                (tmp[i] in o) ? o[tmp[i]] = 1 : o[tmp[i]]++;
                (tmp_name[i] in o_name) ? o_name[tmp_name[i]] = 1 : o_name[tmp_name[i]]++;
            }
            for (x in o) if (o[x] == 1) c.push(x);
            for (y in o_name) if (o_name[y] == 1) c_name.push(y);
            var arr = [c, c_name];
            return arr;
        }

        function getInfo(name) {
//            alert(name);
            var hostidstr = $(".jkn_treecheckboxed").length;
            var dspecArr = new Array();//记录型号
            //var dspecArr = [6,7];
            var cmdArr = new Array();
            var cmd_codeArr = new Array();
            for (var k = 0; k < hostidstr; k++) {
                var type = $(".jkn_treecheckboxed").eq(k).attr("dspec");
//                alert(type);
//                alert(dspecArr.length);
                if (dspecArr.length == 0) {
                    dspecArr[0] = type;

                    cmdArr[0] = $("#cmd_" + name + "_" + type).attr("cmdname");
                    cmd_codeArr[0] = $("#cmd_" + name + "_" + type).attr("cmdcode");
//                    alert(cmd_codeArr[0]);
//                    alert(cmdArr[0]);

                } else {
                    var flag = 1;
                    for (var m = 0; m < dspecArr.length; m++) {

                        if (dspecArr[m] == type) {
                            flag = 0;
                        }
                    }
                    if (flag) {
                        dspecArr[dspecArr.length] = type;
                        cmdArr[dspecArr.length - 1] = $("#cmd_" + name + "_" + type).attr("cmdname");
                        cmd_codeArr[dspecArr.length - 1] = $("#cmd_" + name + "_" + type).attr("cmdcode");
                    }
                }
            }
            return [cmdArr, cmd_codeArr];
        }

        $("#power").click(function () {
            var hostidstr = $(".jkn_treecheckboxed").length;
            if (hostidstr > 0) {
                var signalArr = getInfo("power");
//                alert(signalArr);
                var cmdArr = signalArr[0];
                var cmd_codeArr = signalArr[1];
                var hostid = "";
                var status = "";
                if (hostidstr == 1) {
                    hostid = $(".jkn_treecheckboxed").eq(0).attr("hostId");
                    status = $("#go").contents().find("#host_status_" + hostid).val();
                }
                var hostid = $(".jkn_treecheckboxed").eq(0).attr("hostId");
                if (cmdArr[0]) {
                    var arr = cmdArr[0].split(",");
                }

                var zindex = $(this).find(".jkn_mc_powerson").css("z-index");
                if (zindex >= 0) {
                    $(this).find(".jkn_mc_powerson").css("z-index", "-1")
                } else {
                    $(this).find(".jkn_mc_powerson").css("z-index", "1")
                    $(this).siblings().find(".jkn_mc_powerson").css("z-index", "-1")
                }
                var str = "";


                if (hostidstr == 1 && status == "Online") {
                    //for(var i=0;i<arr.length;i++){
                    //   if(arr[i]=="开机"){continue;}
                    //   else if(arr[i]=="关机"){
                    str += "<li onclick='shutdown(" + hostidstr + ")'>关机</li>";
                    //  }
                    //}
                } else if (hostidstr == 1 && status == "Offline") {
                    str += "<li onclick='wakeup(" + hostidstr + ")'>开机</li>";
                } else {
                    str += "<li onclick='wakeup(" + hostidstr + ")'>开机</li>";
                    str += "<li onclick='shutdown(" + hostidstr + ")'>关机</li>";
                }
                $(this).find(".jkn_mc_powerson").html(str);
                //alert(str);

            }
            else {
                layer.msg("请选择一个班级", 1);
            }
        });
        //投影仪开关机
        $("#htpower").click(function () {
            var hostidstr = $(".jkn_treecheckboxed").length;
            if (hostidstr > 0) {
                var signalArr = getInfo("power");
                var cmdArr = signalArr[0];
                var cmd_codeArr = signalArr[1];
                var hostid = "";
                var hostip = "";
                var status = "";
                var turnState = "";
                if (hostidstr == 1) {
                    hostid = $(".jkn_treecheckboxed").eq(0).attr("hostId");

                    hostip = $(".jkn_treecheckboxed").eq(0).attr("hostIp");
                    status = $("#go").contents().find("#host_status_" + hostid).val();
                }
                if (status == "Offline") {
                } else {
                    var hostid = $(".jkn_treecheckboxed").eq(0).attr("hostId");

                    if (cmdArr[0]) {
                        var arr = cmdArr[0].split(",");
                    }

                    var zindex = $(this).find(".jkn_mc_powerson").css("z-index");
                    if (zindex >= 0) {
                        $(this).find(".jkn_mc_powerson").css("z-index", "-1")
                    } else {
                        $(this).find(".jkn_mc_powerson").css("z-index", "1")
                        $(this).siblings().find(".jkn_mc_powerson").css("z-index", "-1")
                    }
                    var str = "";
                    if (hostidstr == 1) {
                        $.post("${pageContext.request.contextPath}/htprojector/HTProjector_getInfo.do",
                                {hostid: hostid, hostIp: hostip}, function (data) {
                                    //alert("ddddd");
                                    for (var j = 0; j < data.length; j++) {
                                        if (hostid == data[j].id) {
                                            turnState = data[j].TurnState;
                                            $("#go").contents().find("#status_" + hostid).attr("turnState", turnState);
                                        }
                                    }
                                }, 'json');
                        turnState = $("#go").contents().find("#status_" + hostid).attr("turnState");
//                        alert(turnState);
                        if (turnState == "TurnOn") {
                            str += "<li onclick='htpshutdown(" + hostidstr + ")'>关机</li>";
                        } else if("CoolDown" == turnState){
                            $(this).find(".jkn_mc_powerson").css("z-index", "-1")
                            layer.msg("正在关机",1);
                        }else if ("TurnOff" == turnState ) {
                            str += "<li onclick='htpwakeup(" + hostidstr + ")'>开机</li>";
                        } else {
                            $(this).find(".jkn_mc_powerson").css("z-index", "-1")
                            $("#go").contents().find("#current_turnState_" + hostid).html("状态出错");
                            layer.msg("状态出错");
                        }
                    } else {
                        str += "<li onclick='htpwakeup(" + hostidstr + ")'>开机</li>";
                        str += "<li onclick='htpshutdown(" + hostidstr + ")'>关机</li>";


                    }
                }
                $(this).find(".jkn_mc_powerson").html(str);
                //alert(str);

            }
            else {
                layer.msg("请选择一个班级", 1);
            }
//            alert(turnState);
        });


        //信号切换
        $("#signal").click(function () {
            var hostidstr = $(".jkn_treecheckboxed").length;

            //获取选中大屏的vga名称
            var hostid = "";
            if (hostidstr > 0) {
                var signalArr = getInfo("signal");
                var cmdArr = signalArr[0];

                var cmd_codeArr = signalArr[1];
                var arr;
                var arr_cmd;
                if (cmdArr.length == 1) {
                    arr = cmdArr[0].split(",");
                    arr_cmd = cmd_codeArr[0].split(",");
                } else {
                    getSame_1(cmd_codeArr, cmdArr);
                    arr_cmd = cmd_code[0].split(",");
                    arr = cmd_name[0].split(",");
                }
                var current = "";
                var status = "";
                var turnState = "";
                if (hostidstr == 1) {
                    hostid = $(".jkn_treecheckboxed").eq(0).attr("hostId");
                    current = $("#go").contents().find("#status_" + hostid).attr("signal");
                    turnState = $("#go").contents().find("#status_" + hostid).attr("turnState");
                    status = $("#go").contents().find("#host_status_" + hostid).val();
                }
                if (status == "Offline") {
                } else {
                    if ("TurnOff" == turnState||"CoolDown"==turnState) {
                        layer.msg("投影仪未开，请先开机", 1);

                    } else if ("TurnOn" == turnState) {
                        //目前的信号源
//                      current = $("#go").contents().find("#status_"+hostid).attr("signal");
                        var zindex = $(this).find(".jkn_mc_powerson").css("z-index");
                        if (zindex >= 0) {
                            $(this).find(".jkn_mc_powerson").css("z-index", "-1")
                        } else {
                            $(this).find(".jkn_mc_powerson").css("z-index", "1")
                            $(this).siblings().find(".jkn_mc_powerson").css("z-index", "-1")
//                      }
                            var str = '';
                            //if(status == "Online"){
                            for (var i = 0; i < arr_cmd.length; i++) {
                                if (arr_cmd[i] != current) {
//                        alert(arr_cmd[i]+"-----"+current)
                                    if (arr_cmd[i]) {
//                            alert(arr_cmd[3]+"-----"+current)
//                            alert(arr[3]+"-----"+current)
                                        str += '<li onclick="change_signal(\'' + arr_cmd[i] + '\',\'' + arr[i] + '\',\'' + hostidstr + '\')">' + arr[i] + '</li>';
                                    } else {
                                        str += '<li onclick="change_signal(\'null\',\'' + arr[i] + '\',\'' + hostidstr + '\')">' + arr[i] + '</li>';
                                    }
                                } else {
                                    if (current != "")
                                        str += '<li style="color:green;font-weight:bold" onclick="change_signal(\'null\',\'' + arr[i] + '\',\'' + hostidstr + '\')">' + arr[i] + '</li>';
                                }
                            }
                        }
                        $(this).find(".jkn_mc_powerson").html(str);
                        // }
                    } else {
                        layer.msg("状态出错");
                    }
                }
            } else {
                //$("#classmessage").html("请选择一个班级!");
                layer.msg("请选择一个班级", 1);
            }
        });
        //点击空白区域，隐藏操作项子菜单
        $(document).bind("click", function (e) {
            var target = $(e.target);
            if (target.closest(".xk_options").length == 0) {
                $(".jkn_mc_powerson").css("z-index", "-1");
            }
        })
        $(document).bind("click", function (e) {
            var target = $(e.target);
            if (target.closest(".xk_options_more").length == 0) {
                $(".jkn_moresonmenu").css("z-index", "-1");
            }
        })
        root_path = $("#root_path").val();
        message.processResponse = function (tokenParams, eventType, desc) {
            if (eventType == '0x005004') {//开始录制
                var json = eval("(" + desc + ")");
                var hostid_string = json.hostId;
            } else if (eventType == '0x005007') {// 停止
                var json = eval("(" + desc + ")");
                var hostid_string = json.hostId;
            }
        }
        message.processOnlineOfflineEvent = function (token, eventType) {
            var hostArray = $("div[token='" + token + "']");
            var isRightRefresh = false;
            for (var i = 0; i < hostArray.length; i++) {
                var host = hostArray[i];
                if (eventType == "102") {   //离线
                    //判断是否已经是离线状态
                    var span = $(host).children(":first");
                    span.attr("class", "tree_content_nousebg");
                    var spanP = $($(host).children()[1]).children();
                    var span_2 = $(host).children().eq(1);
                    $(spanP[0]).attr("style", "float: left; color: rgb(128, 128, 128);");
                    $(spanP[1]).attr("class", "offlineshow");
                    span_2.attr("style", "");
                    isRightRefresh = true;
                } else { //101
                    //上线
                    var span = $(host).children(":first");
                    span.attr("class", "tree_content_onlinebg");
                    var span_2 = $(host).children().eq(1);
                    var spanP = $($(host).children()[1]).children();
                    $(spanP[0]).attr("style", "float: left;");
                    $(spanP[1]).attr("class", "offlinehide");
                    span_2.attr("style", "color:#5cb75a");
                    isRightRefresh = true;
                }
            }
            //有事件通知时刷新右侧列表
            if (hostArray.length > 0 && isRightRefresh) {
                var groupid = $("#group_id").val();
                var src = $("#root_path").val();
                var pageSize = $.cookie("screen");
                var currentPage=$("#go").contents().find("#current").val();
                src += "/htprojector/HTProjector_tdeviceList.do?groupid=" + groupid + "&currentPage="+currentPage+"&pageSize=" + pageSize;
                $("#go").attr("src", src);
                $("#selected_host").val("");
            }
        }

        message.processOtherEvent = function (token, eventType, desc) {
            var signalclass = desc;
            var dapingip = token;//获取ip

            var parames = signalclass.split(":::::::")[2];//获取名称
            var types = signalclass.split(":::::::")[3];
            self.frames[0].setval(parames, dapingip, types);

        }
        //谷歌浏览器
        var isChrome = navigator.userAgent.toLowerCase().match(/chrome/) != null
        if (isChrome) {
            $(".public_right_center").css("margin-top", "0")
        }
        var isie = navigator.userAgent.toLowerCase().match(/(ie)|(clr)|(msie)/) != null
        if (isie) {
        }
    })
    function closeVideo() {
        var soArray = $(window.frames["go"].document).find("object[name='ScriptableObject']");
        $.each(soArray, function (i, value) {
            try {
                value.stop();
            } catch (e) {
            }
        });
    }
    //切换信号源
    function change_signal(strcode, str, hostidstr) {
        for (var i = 0; i < hostidstr; i++) {
            var hostid = $(".jkn_treecheckboxed").eq(i).attr("hostId");
            var hostip = $(".jkn_treecheckboxed").eq(i).attr("hostIp");
            if (strcode != 'null') {
                $.post(root_path + '/htprojector/HTProjector_changeSignal.do', {
                    hostIp: hostip,
                    cmdCode: strcode
                }, function (data) {
                    if (data.success == "true") {

                        //将状态修改成最新的
                        $("#go").contents().find("#current_signal_" + hostid).html(str);
                        $("#go").contents().find("#status_" + hostid).attr("signal", strcode);
                    }
                    else {
                        //layer.msg("切换失败",1);
                    }
                })
            }
        }
    }

    //开机
    function wakeup(hostidstr) {
        //alert(hostidstr)
        for (var i = 0; i < hostidstr; i++) {
            var hostid = $(".jkn_treecheckboxed").eq(i).attr("hostip");
            var status = $(".jkn_treecheckboxed").eq(i).attr("status");
            $.post(root_path + '/htprojector/HTProjector_start.do', {hostIp: hostid}, function (data) {
                if (data.success == "true") {
                    //$("#go").contents().find("#host_status_"+hostid).val("Online");
                    //window.location.reload();
                } else {
                    //layer.msg("开机失败",1)
                }
            });
        }
    }
    //关机
    function shutdown(hostidstr) {
//        alert("ddddd");
        for (var i = 0; i < hostidstr; i++) {
            var hostid = $(".jkn_treecheckboxed").eq(i).attr("hostId");
            var hostip = $(".jkn_treecheckboxed").eq(i).attr("hostIp");
            var status = $(".jkn_treecheckboxed").eq(i).attr("status");
            var str = "ShutDown";

            $.post(root_path + '/htprojector/HTProjector_shutdown.do', {
                hostIp: hostip,
                cmdCode: "TurnOff "
            }, function (data) {
                if (data.success == "true") {
                    $("#go").contents().find("#host_status_" + hostid).val("Offline");
                    window.location.reload();
                } else {
                    //layer.msg("关机失败",1)
                }
            });
        }
    }
    function refreshPage() {
//        window.location.reload();
        $(function () {

            var groupid = $("#group_id").val();
            if (groupid == null) {
                groupid = "0";
            }
            var pageSize = $.cookie("screen");
            var src = $("#root_path").val();
            if (!pageSize) {
                $.cookie('screen', 4, {path: '/'});
                pageSize = 4;
            }
            src += "/htprojector/HTProjector_tdeviceList.do?groupid=" + groupid + "&currentPage=1&pageSize=" + pageSize;
            $("#go").attr("src", src);
        });
    }


    //投影仪开机
    function htpwakeup(hostidstr) {
        //alert(hostidstr);
        for (var i = 0; i < hostidstr; i++) {
            var hostid = $(".jkn_treecheckboxed").eq(i).attr("hostId");
            var hostip = $(".jkn_treecheckboxed").eq(i).attr("hostIp");
            var status = $(".jkn_treecheckboxed").eq(i).attr("status");
            $.post(root_path + '/htprojector/HTProjector_setProjectorTurnState.do', {
                hostIp: hostip,
                cmdCode: "TurnOn"
            }, function (data) {
                if (data.success == "true") {
                    $("#go").contents().find("#current_turnState_" + hostid).html("开机");
                    window.setTimeout("refreshPage()", 3000);
                } else {
                    layer.msg("开机失败", 1)
                }
            });

        }
    }
    //投影仪关机
    function htpshutdown(hostidstr) {
        //alert(hostidstr);
        for (var i = 0; i < hostidstr; i++) {
            var hostid = $(".jkn_treecheckboxed").eq(i).attr("hostId");
            var hostip = $(".jkn_treecheckboxed").eq(i).attr("hostIp");
            var turnState = $("#go").contents().find("#status_" + hostid).attr("turnState");
//            var status = $(".jkn_treecheckboxed").eq(i).attr("status");
//            if(cmdCode.equal("TurnOff")) {
            $.post(root_path + '/htprojector/HTProjector_setProjectorTurnState.do', {
                hostIp: hostip,
                cmdCode: "TurnOff"
            }, function (data) {
                if (data.success == "true") {
//                        alert(data.success);
                    $("#go").contents().find("#current_turnState_" + hostid).html("关机");
                    $("#go").contents().find("#current_signal_" + hostid).html("");

                } else {
                    layer.msg("关机失败", 1)
                }
            });
        }
    }

    function change_screen(screen) {
        var groupid = $("#group_id").val();
        var pageSize = $.cookie("screen");
        if (pageSize != screen) {
            $(".xk_fournine div").eq(0).attr("class", "xk_four");
            $(".xk_fournine div").eq(1).attr("class", "xk_nine");
            if (screen == 4) {
                $("#go").attr("src", "")
                $("#go").attr("src", root_path + '/htprojector/HTProjector_tdeviceList.do?groupid=' + groupid + '&pageSize=' + screen + '&currentPage=1');
            }
            else {
                $("#go").attr("src", "")
                $("#go").attr("src", root_path + '/htprojector/HTProjector_tdeviceList.do?groupid=' + groupid + '&pageSize=' + screen + '&currentPage=1');
            }

            $.cookie('screen', screen, {path: '/'});

            $("#selected_host").val("");
            $("#selected_host").attr("ip", "");
            /*$("#turnon").attr("onclick","wakeup()");
             $("#turnoff").attr("onclick","shutdown()");
             $("#turnon_img").removeClass("xk_options_openicon_disable").addClass("xk_options_openicon");
             $("#turnoff_img").removeClass("xk_options_closeicon_disable").addClass("xk_options_closeicon");
             $("#record_vedio").attr("onclick", "recording()");
             $("#record_vedio_img").removeClass("xk_options_recordingicon_disable").addClass("xk_options_recordingicon");
             $("#stop_record_vedio").attr("onclick", "stoprecord()");
             $("#stop_record_vedio_img").removeClass("xk_options_stoprecordingicon_disable").addClass("xk_options_stoprecordingicon");*/
        } else {
            if (screen == 4) {
                $(".xk_fournine div").eq(1).css({"display": "block", "float": "left"});
                $(".xk_fournine div").eq(1).css({"border": "1px solid #bbb", "border-radius": " 3px 0 0 3px"});
                $(".xk_fournine div").eq(0).css({
                    "border": "1px solid #bbb",
                    "border-left": "none",
                    "border-radius": " 0",
                    "float": "right"
                })
            }
            else {
                $(".xk_fournine div").eq(0).css({"display": "block", "float": "left"});
                $(".xk_fournine div").eq(0).css({"border": "1px solid #bbb", "border-radius": " 3px 0 0 3px"});
                $(".xk_fournine div").eq(1).css({
                    "border": "1px solid #bbb",
                    "border-left": "none",
                    "border-radius": " 0",
                    "float": "right"
                })
            }
        }
    }

    $(function () {

        var groupid = $("#group_id").val();
        if (groupid == null) {
            groupid = "0";
        }
        var pageSize = $.cookie("screen");
        var src = $("#root_path").val();
        if (!pageSize) {
            $.cookie('screen', 4, {path: '/'});
            pageSize = 4;
        }
        src += "/htprojector/HTProjector_tdeviceList.do?groupid=" + groupid + "&currentPage=1&pageSize=" + pageSize;
        $("#go").attr("src", src);
    });
    $(".tree_titleb").next().find(".tree_titlea").click(function () {
        var getindex = $(".tree_titleb").next();
        var indexson = $(this).parent(".tree_title").index() - 1;
        $.cookie("group_selected", indexson)
        var groupid = $(this).attr("name");
        $("#group_id").val(groupid);
        $(".tree_selected").attr("style", "");
        $(".tree_selected").find(".tree_content_onlinebg").css({
            "background-position": "-144px -24px",
            "margin-left": "0px"
        })
        $(".zhang").css("color", "#333");
        var block = getindex.find(".tree_title").eq(indexson).find(".tree_content").css("display");
        var groupid = $("#group_id").val();
        var src = $("#root_path").val();
        var pageSize = $.cookie("screen");
        //alert(pagesize);
        if (!pageSize) {
            $.cookie('screen', 4, {path: '/'});
            pageSize = 4;
        }
        src += "/htprojector/HTProjector_tdeviceList.do?groupid=" + groupid + "&currentPage=1&pageSize=" + pageSize;
        if (block == "none") {
            $("#go").attr("src", src);
            getindex.find(".tree_title").find(".tree_content").css("display", "none");
            getindex.find(".tree_title").removeClass("tree_title_open").addClass("tree_title_close");
            getindex.find(".tree_title").eq(indexson).find(".tree_content").css("display", "block");
            getindex.find(".tree_title").eq(indexson).removeClass("tree_title_close").addClass("tree_title_open");
            getindex.find(".tree_title").eq(indexson).find(".tree_titlea").addClass("zhang").css({"color": "#28b779"});
//          if(getindex.find(".tree_title").eq(indexson).find(".tree_content").css("display")=="block"){
//              getindex.find(".tree_title").find(".tree_content").find(".jkn_treecheckbox").css("left","0px")
//              getindex.find(".tree_title").find(".tree_content").find(".jkn_treecheckboxed").css("left","0px")
//          }else{
//              getindex.find(".tree_title").find(".tree_content").find(".jkn_treecheckbox").css("left","54px")
//              getindex.find(".tree_title").find(".tree_content").find(".jkn_treecheckboxed").css("left","54px")
//          }
            change_size();
        } else {
            getindex.find(".tree_title").eq(indexson).find(".tree_content").css("display", "none");
            getindex.find(".tree_title").eq(indexson).removeClass("tree_title_open").addClass("tree_title_close");
            getindex.find(".tree_title").eq(indexson).find(".tree_titlea").addClass("zhang").css({"color": "#28b779"});
            change_size();
        }
    })
</script>
</html>
