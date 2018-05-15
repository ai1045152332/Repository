<%@ page import="com.honghe.recordhibernate.dao.Pager" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lch
  Date: 2015/1/14
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
    <title>实时监控 | 集控平台</title>
    <%@include file="../dmanager/websocket.jsp" %>
    <link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/index.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/common/jquery-1.8.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/screendetail.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/perfect-scrollbar.with-mousewheel.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/prettify.js"></script>
    <%--<script src="${pageContext.request.contextPath}/js/common/checkbox_res.js"></script>--%>
    <script src="${pageContext.request.contextPath}/js/common/selectmore.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
    <!--layerdate-->
    <script src="${pageContext.request.contextPath}/js/common/laydate/laydate.js"></script>
</head>
<%
    String device_type = SessionManager.get(request.getSession(),SessionManager.Key.DeviceType);
%>
<body>
<div class="public">
    <input type="hidden" id="device_type" value="<%=device_type%>"/>
    <jsp:include page="/pages/frontend/equipment_header.jsp"></jsp:include>
    <style>
        .demo {
            background:rgba(0,0,0,1);
            display: none;
            left: 0;
            position: absolute;
            top: 0;
            z-index: 10;
        }
        .select{
            display: block;
            height:25px ;
        }
        .sall {
            height: 36px;
            width: 146px;
        }

        #selectdivall0 {
            width: 115px;
            background-position: 0 0;

        }

        .selectdiv {
            background: none;
            width: 139px;
            height: 27px;
            line-height: 27px;
            margin-top: 3px;
        }

        .selectdivul {
            width: 113px;
        }

        .selectdivul a {
            text-indent: 10px;
            text-align: left;
            width: 113px;
        }

        #selectdivul0 {
            border: 1px solid #dbdbdb;
            /*	margin-top: -2px;*/
            height: 108px;
            width: 113px;
            overflow: hidden;
        }

        .tr {
            background: #fff;
            display: block;
            margin-bottom: 3px;
        }

        .laydate-icon {
            float: left;
            text-indent: 5px;
            margin-top: 2px;
            width: 100px;
        }

        .laydate-icon {
            height: 25px;
            line-height: 25px;
        }

        .logsearch,.zdeplay {
            border-radius: 8px;
            background: #28b779;
            color: #fff;
            cursor: pointer;
            float: left;
            font-size: 14px;
            height: 30px;
            line-height: 30px;
            margin-top: 2px;
            text-align: center;
            width: 85px;
        }

        .deviceloginput {
            border: 1px solid #C6C6C6;
            float: left;
            height: 25px;
            line-height: 25px;
            margin-right: 30px;
            margin-top: 2px;
            outline: none;
            text-indent: 5px;
            width: 120px;
        }

        .wid_20, .wid_16, .wid_10, .wid_6, .wid_24, .wid_15,.wid_9  {
            overflow: hidden;
            text-overflow: ellipsis;
            text-indent: 3px; /*首行缩进文本*/
            white-space: nowrap;
        }

        #selectdivall0 {
            background: url("${pageContext.request.contextPath}/image/frontend/n_icon_141006.png") no-repeat scroll 0px -458px transparent;
        }
        input[type="checkbox"] {
            display: block;
            float:left
        }
        .checkline{
            float:left;
            margin:13px 13px;
            width:100px;
            /*global_checkbox*/
        }
        .aaaa{
            display: none;
        }

    </style>
    <div class="mm floatleft">
        <div class="mm_head floatleft">
            <div class="logmargintop" style="width: 1000px;margin-left: 30px;">
                <div style="width: 70px;float: left;line-height: 36px;text-align: center;margin-left: 30px;">班级名称：</div>
                <div class="sall" style="margin-top: 5px">
                    <select class="select" id="select0">
                        <option value="ALL" selected="selected">所有设备</option>
                        <c:forEach var="device" items="${hostingroup.hostInfo}">
                            <option value="${device.host_mac}">${device.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="logsearch" style="left: 4px" onclick="search()">确定</div>

                <div style="width: 95px;float: left;line-height: 33px;text-align: center;margin-left: 30px;">设备总数：<span
                        id="device_number"></span></div>
                <div style="width: 95px;float: left;line-height: 33px;text-align: center;margin-left: 30px;">在线设备：<span
                        id="device_online_number"></span></div>

                <div class="zdeplay" style="float:right; line-height: 30px;"  onclick="fhide()">自定义筛选</div>

            </div>
        </div>
        <div class="demo">
            <div class="win800" style="height:250px;width:400px;position: absolute;left: 600px ;top:220px;margin:auto ">
                <div class="win_head" >
                    <div class="win_head_text" style="text-align: center" >自定义筛选</div>
                    <div class="win_close"></div>
                </div>
                <div class="win_content" style="width: 100%;margin-left: 0;">
                    <%
                        if (device_type.equals("hhtwb")){
                    %>
                    <%--<div class="checkline"><input type="checkbox" name="functions" value="signal" checked="checked" onclick="sels()" />信号源</div>--%>
                    <div class="checkline"><input type="checkbox" name="functions" value="volume" checked="checked" onclick="sels()" />音量</div>
                    <%--<div class="checkline"><input type="checkbox" name="functions" value="audio_mode" checked="checked" onclick="sels()" />音响模式</div>--%>
                    <%--<div class="checkline"><input type="checkbox" name="functions" value="remote_energy" checked="checked" onclick="sels()" />节能模式</div><br/>--%>
                    <div class="checkline"><input type="checkbox" name="functions" value="memmary" checked="checked" onclick="sels()" />内存使用率</div>
                    <div class="checkline"><input type="checkbox" name="functions" value="cpuUsage" checked="checked" onclick="sels()" />cpu使用率</div>
                    <div class="checkline"><input type="checkbox" name="functions" value="disk_C" checked="checked" onclick="sels()" />c盘使用率</div><br/>
                    <div class="checkline"><input type="checkbox" name="functions" value="top_softWare" checked="checked" onclick="sels()"/>置顶软件</div>
                    <%--<div class="checkline"><input type="checkbox" name="all1" id="checkedall" checked="checked" onclick="selectall()"/>反选</div>--%>
                    <%
                    }else if (device_type.equals("hhtc")){
                    %>
                    <div class="checkline"><input type="checkbox" name="functions" value="signal" checked="checked" onclick="sels()" />信号源</div>
                    <div class="checkline"><input type="checkbox" name="functions" value="volume" checked="checked" onclick="sels()" />音量</div>
                    <div class="checkline"><input type="checkbox" name="functions" value="audio_mode" checked="checked" onclick="sels()" />音响模式</div>
                    <div class="checkline"><input type="checkbox" name="functions" value="remote_energy" checked="checked" onclick="sels()" />节能模式</div><br/>
                    <div class="checkline"><input type="checkbox" name="functions" value="memmary" checked="checked" onclick="sels()" />内存使用率</div>
                    <div class="checkline"><input type="checkbox" name="functions" value="cpuUsage" checked="checked" onclick="sels()" />cpu使用率</div>
                    <div class="checkline"><input type="checkbox" name="functions" value="disk_C" checked="checked" onclick="sels()" />c盘使用率</div><br/>
                    <div class="checkline"><input type="checkbox" name="functions" value="top_softWare" checked="checked" onclick="sels()"/>置顶软件</div>
                    <%--<div class="checkline"><input type="checkbox" name="all1" id="checkedall" checked="checked" onclick="selectall()"/>反选</div>--%>
                    <%
                        }
                    %>

                    <div class="win_btn" style="margin: 20px 20px 20px 0;">
                        <a href="javascript:void(0);" style=" margin-left:15px;" id="all" onclick="selall('all')">全选</a>
                        <a href="javascript:void(0);" style=" margin-left:10px; color:gray;"  id="allnot" onclick="selall('allnot')">全不选</a>
                        <div class="win_btn_sure" style="margin-right: 20px;" onclick="isure()">确定</div>
                        <div class="win_btn_done">取消</div>
                    </div>
                </div>
            </div>
        </div>
        <div class="user" style="margin-left: 3%;min-width: 0;width: 94%;">
            <div class="table_head " style="margin-top: 15px;">
                <%
                    if (device_type.equals("hhtwb")){
                %>
                <div class="wid_12 floatleft">班级名称</div>
                <div class="wid_12 floatleft">IP地址</div>
                <div class="wid_12 floatleft">在线状态</div>
                <div class="wid_12 floatleft" name="volume">音量</div>
                <%--<div class="wid_9 floatleft" name="signal">信号源</div>--%>
                <%--<div class="wid_9 floatleft" name="audio_mode">音响模式</div>--%>
                <%--<div class="wid_9 floatleft" name="remote_energy">节能模式</div>--%>
                <div class="wid_12 floatleft" name="memmary">内存使用率</div>
                <div class="wid_12 floatleft" name="cpuUsage">CPU使用率</div>
                <div class="wid_12 floatleft" name="disk_C">C盘使用率</div>
                <div class="wid_12优化页面显示 floatleft" name="top_softWare">置顶软件</div>
                <%
                } else if (device_type.equals("hhtc")){
                %>
                <div class="wid_9 floatleft">班级名称</div>
                <div class="wid_9 floatleft">IP地址</div>
                <div class="wid_9 floatleft">在线状态</div>
                <div class="wid_9 floatleft" name="signal">信号源</div>
                <div class="wid_9 floatleft" name="volume">音量</div>
                <div class="wid_9 floatleft" name="audio_mode">音响模式</div>
                <div class="wid_9 floatleft" name="remote_energy">节能模式</div>
                <div class="wid_9 floatleft" name="memmary">内存使用率</div>
                <div class="wid_9 floatleft" name="cpuUsage">CPU使用率</div>
                <div class="wid_9 floatleft" name="disk_C">C盘使用率</div>
                <div class="wid_9 floatleft" name="top_softWare">置顶软件</div>
                <%

                    }
                %>
            </div>
            <div id="loglist" class="wid_100 floatleft hei_93" style="${hostList.size()>12?'overflow-y:scroll;height:90%':''} overflow:auto;height: 85%">
                <%
                    Map hostListMap = (Map) request.getAttribute("hostingroup");
                    List<Map> hostList = new ArrayList();
                    int i = 0;
                    String host_count;

                    if (hostListMap != null && !hostListMap.isEmpty()) {
                        hostList = (List<Map>) hostListMap.get("hostInfo");
                        host_count = hostList.size() + "";
                    } else {
                        host_count = "0";
                    }
                    int hostcount = Integer.parseInt(host_count);
                    int hostListSize = 0;  //设备总数
                    int host_online_size = 0; //设备在线数

                    Map<String, String> map = new HashMap();
                    map.put("电源管理", "power");
                    map.put("信号源切换", "signal");
                    map.put("音量调节", "audio_control");
                    map.put("音响模式调节", "audio_mode");
                    map.put("触摸功能", "touch_screen");
                    map.put("远程节能", "remote_energy");
                    map.put("置顶软件","top_softWare");

                    if (!hostList.isEmpty()) {
                        hostListSize = hostList.size();
                        for (Map host : hostList) {
                            String host_id = host.get("id").toString();
                            String host_name = host.get("name").toString();
                            // String host_type = host.get("type").toString();
                            String hostip = host.get("host_ip").toString();
                            String host_token = host.get("token").toString();

                            String status = host.get("status").toString();
                            if (status.equals("Offline")){
                                status="离线";
                            }
                            if (status.equals("Online")){
                                status="在线";
                            }



                            //功能列表信息
                            Map<String, String> map_cmds = (Map<String, String>) host.get("allinfo");


                            String signal = "";
                            String remote_energy = "";
                            String audio_control = "";
                            String audio_mode = "";
                            String touch_screen = "";
                            String top_softWare="";

                            if (map_cmds != null) {
                                signal = map_cmds.get("ChannelName");
                                remote_energy = map_cmds.get("EnergyMode");
                                audio_control = map_cmds.get("Volume");
                                audio_mode = map_cmds.get("AudioMode");
                                touch_screen = map_cmds.get("TouchMode");

                                top_softWare=map_cmds.get("TopSoftWare");//置顶软件

                            }

                            List<Map<String, String>> cmdlist = (List<Map<String, String>>) host.get("host_cmd");
                            Map<String, String> map_cmd = new HashMap();
                            //String currentSignal = "";
                            if (cmdlist.size() > 0) {
                                for (int j = 0; j < cmdlist.size(); j++) {//codetype,b.cmd_group, code_cmd
                                    if (map.get(cmdlist.get(j).get("cmd_group")) != null) {
                                        map_cmd.put(map.get(cmdlist.get(j).get("cmd_group").toString()), cmdlist.get(j).get("codetype").toString());
                                        map_cmd.put(map.get(cmdlist.get(j).get("cmd_group").toString()) + "_cmd", cmdlist.get(j).get("code_cmd").toString());
                                    }
                                }
                            }
                %>

                <div class="table_recycle" >

                    <input id="host_cmd_<%=host_id%>" type="hidden" remote_energy="<%=map_cmd.get("remote_energy")%>"
                           touch_screen="<%=map_cmd.get("touch_screen")%>" audio_mode="<%=map_cmd.get("audio_mode")%>"
                           audio_control="<%=map_cmd.get("audio_control")%>" signal="<%=map_cmd.get("signal")%>"
                           power="<%=map_cmd.get("power")%>"
                            >
                    <input id="host_cmd_code_<%=host_id%>" type="hidden"
                           remote_energy_cmd="<%=map_cmd.get("remote_energy_cmd")%>"
                           touch_screen_cmd="<%=map_cmd.get("touch_screen_cmd")%>"
                           audio_mode_cmd="<%=map_cmd.get("audio_mode_cmd")%>"
                           audio_control_cmd="<%=map_cmd.get("audio_control_cmd")%>"
                           signal_cmd="<%=map_cmd.get("signal_cmd")%>"
                           power_cmd="<%=map_cmd.get("power_cmd")%>"
                            >
                    <input id="host_status_<%=host_id%>" type="hidden" value="<%=status%>"/>
                    <input class="hoststatus" type="hidden" value="status_<%=host_id%>_<%=hostip%>"/>
                    <input id="status_<%=host_id%>" type="hidden" remote_energy="<%=remote_energy%>"
                           touch_screen="<%=touch_screen%>" audio_mode="<%=audio_mode%>"
                           audio_control="<%=audio_control%>" signal="<%=signal%>"
                           top_softWare="<%=top_softWare%>"/>
                    <%
                        if (device_type.equals("hhtwb")){
                    %>
                    <div class="wid_12 floatleft hei_100"><span id="current_host_name_<%=host_id%>"><%=host_name%><!--班级名称--></span></div>
                    <div class="wid_12 floatleft hei_100"><span id="current_host_ip_<%=host_id%>"><%=hostip%><!--IP地址--></span></div>
                    <div class="wid_12 floatleft hei_100"><span id="current_status_<%=host_id%>"><%=status%><!--是否在线--></span></div>
                    <%if (!status.equals("在线")) {%>
                    <%--<div class="wid_9 floatleft hei_100" name="signal"><span id="current_signal_<%=host_id%>"  ><%="--"%><!--信号源--></span></div>--%>
                    <div class="wid_12 floatleft hei_100" name="volume"><span id="current_audio_control_<%=host_id%>"  ><%="--"%><!--音量--></span></div>
                    <%--<div class="wid_9 floatleft hei_100" name="audio_mode"><span id="current_audio_mode_<%=host_id%>"  ><%="--"%><!--音响模式--></span></div>--%>
                    <%--<div class="wid_9 floatleft hei_100" name="remote_energy"><span id="current_remote_energy_<%=host_id%>"  ><%="--"%><!--节能模式--></span></div>--%>
                    <div class="wid_12 floatleft hei_100" name="memmary"><span id="current_memmary_<%=host_id%>" ><%="--"%><!--内存使用率--></span></div>
                    <div class="wid_12 floatleft hei_100" name="cpuUsage"><span id="current_cpuUsage_<%=host_id%>" ><%="--"%><!--CPU使用率--></span></div>
                    <div class="wid_12 floatleft hei_100" name="disk_C"><span id="current_disk_C_<%=host_id%>" ><%="--"%><!--C盘使用率--></span></div>
                    <div class="wid_12 floatleft hei_100" name="top_softWare"><span id="current_top_soft_<%=host_id%>" ><%="--"%><!--置顶软件--></span></div>
                    <%
                    } else {
                        host_online_size++;
                    %>
                    <%--<div class="wid_9 floatleft hei_100" name="signal"><span id="current_signal_<%=host_id%>" ><%=signal%><!--信号源--></span></div>--%>
                    <div class="wid_12 floatleft hei_100" name="volume"><span id="current_audio_control_<%=host_id%>"><%=audio_control%><!--音量--></span></div>
                    <%--<div class="wid_9 floatleft hei_100" name="audio_mode"><span id="current_audio_mode_<%=host_id%>"><%=audio_mode%><!--音响模式--></span></div>--%>
                    <%--<div class="wid_9 floatleft hei_100" name="remote_energy"><span id="current_remote_energy_<%=host_id%>"><%=remote_energy%><!--节能模式--></span></div>--%>
                    <div class="wid_12 floatleft hei_100" name="memmary"><span id="current_memmary_<%=host_id%>"><%=""%><!--内存使用率--></span></div>
                    <div class="wid_12 floatleft hei_100"  name="cpuUsage"><span id="current_cpuUsage_<%=host_id%>" ><%=""%><!--CPU使用率--></span></div>
                    <div class="wid_12 floatleft hei_100"  name="disk_C"><span id="current_disk_C_<%=host_id%>"><%=""%><!--C盘使用率--></span></div>
                    <div class="wid_12 floatleft hei_100" name="top_softWare" title=""><span id="current_top_soft_<%=host_id%>" ><%=top_softWare%><!--置顶软件--></span></div>
                    <%}

                    }else if (device_type.equals("hhtc")){
                    %>
                    <div class="wid_9 floatleft hei_100"><span id="current_host_name_<%=host_id%>"><%=host_name%><!--班级名称--></span></div>
                    <div class="wid_9 floatleft hei_100"><span id="current_host_ip_<%=host_id%>"><%=hostip%><!--IP地址--></span></div>
                    <div class="wid_9 floatleft hei_100"><span id="current_status_<%=host_id%>"><%=status%><!--是否在线--></span></div>
                    <%if (!status.equals("在线")) {%>
                    <div class="wid_9 floatleft hei_100" name="signal"><span id="current_signal_<%=host_id%>"  ><%="--"%><!--信号源--></span></div>
                    <div class="wid_9 floatleft hei_100" name="volume"><span id="current_audio_control_<%=host_id%>"  ><%="--"%><!--音量--></span></div>
                    <div class="wid_9 floatleft hei_100" name="audio_mode"><span id="current_audio_mode_<%=host_id%>"  ><%="--"%><!--音响模式--></span></div>
                    <div class="wid_9 floatleft hei_100" name="remote_energy"><span id="current_remote_energy_<%=host_id%>"  ><%="--"%><!--节能模式--></span></div>
                    <div class="wid_9 floatleft hei_100" name="memmary"><span id="current_memmary_<%=host_id%>" ><%="--"%><!--内存使用率--></span></div>
                    <div class="wid_9 floatleft hei_100" name="cpuUsage"><span id="current_cpuUsage_<%=host_id%>" ><%="--"%><!--CPU使用率--></span></div>
                    <div class="wid_9 floatleft hei_100" name="disk_C"><span id="current_disk_C_<%=host_id%>" ><%="--"%><!--C盘使用率--></span></div>
                    <div class="wid_9 floatleft hei_100" name="top_softWare"><span id="current_top_soft_<%=host_id%>" ><%="--"%><!--置顶软件--></span></div>

                    <%
                    } else {
                        host_online_size++;
                    %>
                    <div class="wid_9 floatleft hei_100" name="signal"><span id="current_signal_<%=host_id%>" ><%=signal%><!--信号源--></span></div>
                    <div class="wid_9 floatleft hei_100" name="volume"><span id="current_audio_control_<%=host_id%>"><%=audio_control%><!--音量--></span></div>
                    <div class="wid_9 floatleft hei_100" name="audio_mode"><span id="current_audio_mode_<%=host_id%>"><%=audio_mode%><!--音响模式--></span></div>
                    <div class="wid_9 floatleft hei_100" name="remote_energy"><span id="current_remote_energy_<%=host_id%>"><%=remote_energy%><!--节能模式--></span></div>
                    <div class="wid_9 floatleft hei_100" name="memmary"><span id="current_memmary_<%=host_id%>"><%=""%><!--内存使用率--></span></div>
                    <div class="wid_9 floatleft hei_100"  name="cpuUsage"><span id="current_cpuUsage_<%=host_id%>" ><%=""%><!--CPU使用率--></span></div>
                    <div class="wid_9 floatleft hei_100"  name="disk_C"><span id="current_disk_C_<%=host_id%>"><%=""%><!--C盘使用率--></span></div>
                    <div class="wid_9 floatleft hei_100" name="top_softWare" title=""><span id="current_top_soft_<%=host_id%>" ><%=top_softWare%><!--置顶软件--></span></div>
                    <%
                            }
                        }
                    %>
                </div>
                <%
                    }
                %>

                <%
                } else {
                %>
                <style>
                    .sall {
                        height: 36px;
                        width: 146px;
                    }

                    #selectdivall0 {
                        background-position: 0 -460px;
                        height: 28px;
                        line-height: 28px;
                        width: 115px;

                    }

                    .selectdiv {
                        background: none;
                        width: 139px;
                        height: 28px;
                        line-height: 28px;
                        margin-top: 0;
                    }

                    .selectdivul {
                        height: 140px;
                        max-height: 0;
                        width: 113px;
                    }

                    .selectdivul a {
                        text-indent: 10px;
                        text-align: left;
                        line-height: 28px;
                        width: 113px;
                    }

                    #selectdivul0 {
                        border: 1px solid #dbdbdb;
                        /*	margin-top: -2px;*/
                        height: 108px;
                        width: 113px;
                        overflow: hidden;
                    }

                    .laydate-icon {
                        float: left;
                        text-indent: 5px;
                        width: 100px;
                    }
                    .logsearch , .zdeplay{
                        border-radius: 8px;
                        background: #28b779;
                        color: #fff;
                        cursor: pointer;
                        float: left;
                        font-size: 14px;
                        height: 27px;
                        line-height: 27px;
                        text-align: center;
                        width: 82px;
                    }
                    .deviceloginput {
                        border: 1px solid #C6C6C6;
                        float: left;
                        height: 24px;
                        line-height: 24px;
                        margin-right: 30px;
                        outline: none;
                        text-indent: 5px;
                        width: 120px;
                    }

                </style>
                <div class="error">
                    <%--<div class="error_text"></div>--%>
                    <div class="error_pic">
                        <span class="error_pictext">暂无设备</span>
                    </div>
                </div>
                <%
                    }
                %>

            </div>
        </div>
    </div>
    <div class="foot">
        <jsp:include page="/pages/frontend/footer.jsp"></jsp:include>
    </div>
</div>
</body>
<script>

    //自定义筛选
    function fhide(){
        $(".demo").show();
    }
    $(".win_close").click(function () {
        $(".demo").hide()
    })
    $(".win_btn_done").click(function () {
        $(".demo").hide()
    })
    function fclose(){
        $(".demo").hide();
    }
    $('#all').click(function(){
        $('input').attr('checked','true');
    });
    //    function selectall(){
    //        var checkboxs=document.getElementsByName("functions");
    //        for (var i=0;i<checkboxs.length;i++) {
    //            var e=checkboxs[i];
    //            e.checked=!e.checked;
    //        }
    //    }
    function selall(sign){
        var checkboxs=document.getElementsByName("functions");
        for(var i=0;i<checkboxs.length;i++){
            if(sign=="all"){
                checkboxs[i].checked=true;
                $("#all").attr("style", "margin-left:15px; color:black ;");
                $("#allnot").attr("style", "margin-left:10px; color:gray ;");
            }else{
                checkboxs[i].checked=false;
                $("#allnot").attr("style", "margin-left:10px; color: black ;");
                $("#all").attr("style", "margin-left:15px; color:gray;");
            }

        }
    }
    function sels(){
        var checkboxs=document.getElementsByName("functions");
        var count=0;
        for(var i=0;i<checkboxs.length;i++) {
            if (checkboxs[i].checked==true) {
                count++;
            }
        }
        if(count<checkboxs.length&&count>0){

            $("#allnot").attr("style", "margin-left:10px; color: gray;");
            $("#all").attr("style", "margin-left:15px; color: gray;");
        }else if(count==checkboxs.length){
            $("#allnot").attr("style", "margin-left:10px; color: gray;");
            $("#all").attr("style", "margin-left:15px; color: black;");
        }else if(count==0){
            $("#allnot").attr("style", "margin-left:10px; color: black;");
            $("#all").attr("style", "margin-left:15px; color: gray;");
        }

    }

    //自定义筛选的确定按钮
    function isure(){
        var funcs="";
        var fstr="";
        var f=document.getElementsByName("functions");
        for(var i=0;i<f.length;i++){
            var func=f[i];
            if(!func.checked){
                var fstr=func.value;
                var funcs= document.getElementsByName(fstr);
                for(var j=0;j<funcs.length;j++){
                    $("[name="+fstr+"]").css("display", "none");
                }
            }
            if(func.checked){
                var fstr=func.value;
                var funcs= document.getElementsByName(fstr);
                for(var j=0;j<funcs.length;j++){
                    $("[name="+fstr+"]").css("display", "inline");
                }
            }

        }

        fclose();
    }
    var root_path = $("#root_path").val();
    //搜索功能

    function search() {
        //todo  实现搜索功能
        var devicetype = $("#device_type").val();
        selall('all');//执行一遍自定义筛选中的全选功能
        isure();//显示所有的数据，使得搜索后数据仍能全部显示
        var mac=$("#select0").val();
        //方法一：直接加载deviceview.jsp
//        window.location="./deviceview/Deviceview_searchList.do?mac="+mac;
        //方法二：通过ajax不新建jsp页面实现局部刷新搜索功能


        $.post("./deviceview/Deviceview_searchList.do", {mac: mac},function(data){
            var html ="";

            $("#loglist").html("");
            $.each(data.hostingroup,function(key,value){//拼字符串显示数据


                var topsoft=value['TopSoftWare'];//置顶软件数据值



                if(devicetype == "hhtwb"){

                    if(value['status']!="Online"){
                        value['status']="离线";}
                    else{
                        value['status']="在线";}
                    html+="<div class=\'table_recycle\'><div class='wid_12 floatleft hei_100'><span id='current_host_name_'" +value['id']+">"+value['name']+"</span></div>"
                    +"<div class='wid_12 floatleft hei_100'><span id='current_host_ip_'" +value['id']+">"+value['host_ip']+"</span></div>"
                    +"<div class='wid_12 floatleft hei_100'><span id='current_status_'" +value['id']+">"+value['status']+"</span></div>";
                    if(value['status']!="在线"){
                        html+="<div class='wid_12 floatleft hei_100' name='volume'><span id='current_audio_control_'" +value['id']+">"+"--"+"</span></div>"
//                    +"<div class='wid_12 floatleft hei_100' name='signal'><span id='current_signal_'" +value['id']+">"+"--"+"</span></div>"
//                    +"<div class='wid_12 floatleft hei_100' name='audio_mode'><span id='current_audio_mode_'" +value['id']+">"+"--"+"</span></div>"
//                    +"<div class='wid_12 floatleft hei_100' name='remote_energy'><span id='current_remote_energy_'" +value['id']+">"+"--"+"</span></div>"
                        +"<div class='wid_12 floatleft hei_100' name='memmary'><span id='current_memmary_'" +value['id']+">"+"--"+"</span></div>"
                        +"<div class='wid_12 floatleft hei_100' name='cpuUsage'><span id='current_cpuUsage_'" +value['id']+">"+"--"+"</span></div>"
                        +"<div class='wid_12 floatleft hei_100' name='disk_C'><span id='current_disk_C_'" +value['id']+">"+"--"+"</span></div>"
                        +"<div class='wid_12 floatleft hei_100' name='top_softWare'><span id='current_top_softWare_'" +value['id']+">"+"--"+"</span></div>";
                    }else{

                        html+="<div class='wid_12 floatleft hei_100' name='volume'><span id='current_audio_control_'" +value['id']+">"+value['Volume']+"</span></div>"
//                    + "<div class='wid_12 floatleft hei_100' name='signal'><span id='current_signal_'" +value['id']+">"+value['ChannelName']+"</span></div>"
//                    +"<div class='wid_12 floatleft hei_100' name='audio_mode'><span id='current_audio_mode_'" +value['id']+">"+value['AudioMode']+"</span></div>"
//                    +"<div class='wid_12 floatleft hei_100' name='remote_energy'><span id='current_remote_energy_'" +value['id']+">"+value['EnergyMode']+"</span></div>"
                        +"<div class='wid_12 floatleft hei_100' name='memmary'><span id='current_memmary_'" +value['id']+">"+value['Memmary']+"</span></div>"
                        +"<div class='wid_12 floatleft hei_100' name='cpuUsage'><span id='current_cpuUsage_'" +value['id']+">"+value['CpuUsage']+"</span></div>"
                        +"<div class='wid_12 floatleft hei_100' name='disk_C'><span id='current_disk_C_'" +value['id']+">"+value['Disk_C']+"</span></div>"
                        +"<div class='wid_12 floatleft hei_100' name='top_softWare' title='"+topsoft+"'><span id='current_top_softWare_'" +value['id']+">"+value['TopSoftWare']+"</span></div>";
                    }
                }else if(devicetype=="hhtc"){
                    if(value['status']!="Online"){
                        value['status']="离线";}
                    else{
                        value['status']="在线";}
                    html+="<div class=\'table_recycle\'>" +
                    "<div class='wid_9 floatleft hei_100'><span id='current_host_name_'" +value['id']+">"+value['name']+"</span></div>"
                    +"<div class='wid_9 floatleft hei_100'><span id='current_host_ip_'" +value['id']+">"+value['host_ip']+"</span></div>"
                    +"<div class='wid_9 floatleft hei_100'><span id='current_status_'" +value['id']+">"+value['status']+"</span></div>";

                    if(value['status']!="在线"){
                        html+="<div class='wid_9 floatleft hei_100' name='signal'><span id='current_signal_'" +value['id']+">"+"--"+"</span></div>"
                        +"<div class='wid_9 floatleft hei_100' name='volume'><span id='current_audio_control_'" +value['id']+">"+"--"+"</span></div>"
                        +"<div class='wid_9 floatleft hei_100' name='audio_mode'><span id='current_audio_mode_'" +value['id']+">"+"--"+"</span></div>"
                        +"<div class='wid_9 floatleft hei_100' name='remote_energy'><span id='current_remote_energy_'" +value['id']+">"+"--"+"</span></div>"
                        +"<div class='wid_9 floatleft hei_100' name='memmary'><span id='current_memmary_'" +value['id']+">"+"--"+"</span></div>"
                        +"<div class='wid_9 floatleft hei_100' name='cpuUsage'><span id='current_cpuUsage_'" +value['id']+">"+"--"+"</span></div>"
                        +"<div class='wid_9 floatleft hei_100' name='disk_C'><span id='current_disk_C_'" +value['id']+">"+"--"+"</span></div>"
                        +"<div class='wid_9 floatleft hei_100' name='top_softWare'><span id='current_top_softWare_'" +value['id']+">"+"--"+"</span></div>";
                    }else{
                        value['status']="在线";
                        html+="<div class='wid_9 floatleft hei_100' name='signal'><span id='current_signal_'" +value['id']+">"+value['ChannelName']+"</span></div>"
                        +"<div class='wid_9 floatleft hei_100' name='volume'><span id='current_audio_control_'" +value['id']+">"+value['Volume']+"</span></div>"
                        +"<div class='wid_9 floatleft hei_100' name='audio_mode'><span id='current_audio_mode_'" +value['id']+">"+value['AudioMode']+"</span></div>"
                        +"<div class='wid_9 floatleft hei_100' name='remote_energy'><span id='current_remote_energy_'" +value['id']+">"+value['EnergyMode']+"</span></div>"
                        +"<div class='wid_9 floatleft hei_100' name='memmary'><span id='current_memmary_'" +value['id']+">"+value['Memmary']+"</span></div>"
                        +"<div class='wid_9 floatleft hei_100' name='cpuUsage'><span id='current_cpuUsage_'" +value['id']+">"+value['CpuUsage']+"</span></div>"
                        +"<div class='wid_9 floatleft hei_100' name='disk_C'><span id='current_disk_C_'" +value['id']+">"+value['Disk_C']+"</span></div>"
                        +"<div class='wid_9 floatleft hei_100' name='top_softWare' title='"+topsoft+"'><span id='current_top_softWare_'" +value['id']+">"+value['TopSoftWare']+"</span></div>";
                    }
                }
                html+="</div>";
            });
            isure();
            $("#loglist").html(html);
        },'json');
    }
    $(function () {
        var hostSize = "<%=hostListSize%>";
        var hostOnlineSize = <%=host_online_size%>
                $("#device_number").html(hostSize);
        $("#device_online_number").html(hostOnlineSize);
        hostSize = parseInt(hostSize);
        //alert(decodeURI(viewClassCameraName));
        var hostid = "";
        var hostip = "";
        if (hostSize != 0) {
            for (var i = 0; i < hostSize; i++) {
                var hostStatus = $(".table_recycle").eq(i).find(".hoststatus").val();
                var hostarr = hostStatus.split("_");

                hostid = hostarr[1];
                hostip = hostarr[2];
                var status = $("#host_status_" + hostid).val();
                if (status == "在线" && hostid != "" && hostip != "") {
                    $.post("${pageContext.request.contextPath}/dmanager/DManager_getInfo.do",
                            {hostid: hostid, hostIp: hostip},
                            function (data) {
                                //console.log(data);
                                for (var j = 0; j < data.length; j++) {
                                    var hostidstr = data[j].id;
                                    var signal = data[j].ChannelName; //信号源
                                    //alert(signal);
                                    var energyMode = data[j].EnergyMode;//节能模式
                                    var volume = data[j].Volume;  //音量
                                    var audioMode = data[j].AudioMode; //音响模式
                                    var touchMode = data[j].TouchMode; //触控模式
                                    var cpuUsage = data[j].CpuUsage; //Cpu使用率
                                    var disk_C = data[j].Disk_C;  //C盘使用率
                                    var memmary = data[j].Memmary; // 内存
                                    var topSoftWare=data[j].TopSoftWare;//置顶软件

                                    $("#status_" + hostidstr).attr("remote_energy", energyMode);
                                    $("#status_" + hostidstr).attr("touch_screen", touchMode);
                                    $("#status_" + hostidstr).attr("audio_mode", audioMode);
                                    $("#status_" + hostidstr).attr("audio_control", volume);
                                    $("#status_" + hostidstr).attr("signal", signal);
                                    $("#status_" + hostidstr).attr("top_softWare", topSoftWare);

                                    var currentSignal = "";
                                    var power = $("#host_cmd_" + hostidstr).attr("signal");

                                    var power_cmd = $("#host_cmd_code_" + hostidstr).attr("signal_cmd");
                                    if (power != "null") {
                                        var arr = power.split(",");
                                        var arr_cmd = power_cmd.split(",");
                                        for (var i = 0; i < arr.length; i++) {
                                            if (arr_cmd[i] == signal) {
                                                currentSignal = arr[i];
                                            }
                                        }
                                        //信号源
                                        $("#current_signal_" + hostidstr).html(currentSignal);
                                    }
                                    var currentAudioMode = "";
                                    var audio = $("#host_cmd_" + hostidstr).attr("audio_mode");
                                    var audio_cmd = $("#host_cmd_code_" + hostidstr).attr("audio_mode_cmd");
                                    if (audio != "null") {
                                        var arr = audio.split(",");
                                        var arr_cmd = audio_cmd.split(",");
                                        for (var i = 0; i < arr.length; i++) {
                                            if (arr_cmd[i] == audioMode) {
                                                currentAudioMode = arr[i];
                                            }
                                        }
                                        //音响模式
                                        $("#current_audio_mode_" + hostidstr).html(currentAudioMode);
                                    }
                                    var currentEnergyMode = "";
                                    var energy = $("#host_cmd_" + hostidstr).attr("remote_energy");
                                    var energy_cmd = $("#host_cmd_code_" + hostidstr).attr("remote_energy_cmd");
                                    if (energy != "null") {
                                        var arr = energy.split(",");
                                        var arr_cmd = energy_cmd.split(",");
                                        for (var i = 0; i< arr.length; i++) {
                                            if (arr_cmd[i] == energyMode) {
                                                currentEnergyMode = arr[i];
                                            }
                                        }
                                        //节能模式
                                        $("#current_remote_energy_" + hostidstr).html(currentEnergyMode);
                                    }
                                    //音量
                                    $("#current_audio_control_" + hostidstr).html(volume);
                                    $("#current_cpuUsage_" + hostidstr).html(cpuUsage);
                                    $("#current_disk_C_" + hostidstr).html(disk_C);
                                    $("#current_memmary_" + hostidstr).html(memmary);
                                    //置顶软件
                                    $("#current_top_soft_" + hostidstr).html(topSoftWare);
                                    document.getElementById("current_top_soft_"+hostidstr).title=topSoftWare;//隐藏的置顶软件数据的显示

                                }
                            }, 'json');
                }
            }
        }

        /**
         * 重写websocket.jsp中的消息类的上下线的方法，当有设备上下线时，调用此方法
         *
         * @param token  设备令牌（一般为IP）
         * @param eventType （事件类型）
         */
        message.processOnlineOfflineEvent = function (token, eventType) {


            var dapingip = token;//获取ip

            var pinglen =<%=hostListSize%>;//获取大屏个数
//        console.log((pinglen));
            var hostid = "";
            var hostip = "";
            for (i = 0; i < pinglen; i++) {
                var hostStatus = $(".table_recycle").eq(i).find(".hoststatus").val();
                var hostarr = hostStatus.split("_");

                hostid = hostarr[1];
                hostip = hostarr[2];

                if (hostip == dapingip) {//比对ip

                    if (eventType == "102") { //离线
//                        $("#host_status_" + hostid).Val("Offline");
                        var hostOnlineNumber = $("#device_online_number").html();
                        hostOnlineNumber = parseInt(hostOnlineNumber) - 1;
                        if (hostOnlineNumber < 0) {
                            hostOnlineNumber = 0;
                        }
                        $("#device_online_number").html(hostOnlineNumber);


                        $("#current_status_" + hostid).html("离线");
                        $("#current_signal_" + hostid).html("--");
                        $("#current_audio_mode_" + hostid).html("--");
                        $("#current_remote_energy_" + hostid).html("--");
                        $("#current_audio_control_" + hostid).html("--");
                        $("#current_cpuUsage_" + hostid).html("--");
                        $("#current_disk_C_" + hostid).html("--");
                        $("#current_memmary_" + hostid).html("--");
                        $("#current_top_softWare_" + hostid).html("--");

                    } else {  //101 在线

                        var hostOnlineNumber = $("#device_online_number").html();
                        hostOnlineNumber = parseInt(hostOnlineNumber) + 1;
                        $("#device_online_number").html(hostOnlineNumber);

//                        $("#host_status_" + hostid).Val("Online");
                        $("#current_status_" + hostid).html("在线");
                        if (hostid != "" && hostip != "") {
                            $.post("${pageContext.request.contextPath}/dmanager/DManager_getInfo.do",
                                    {hostid: hostid, hostIp: hostip},
                                    function (data) {
                                        //console.log(data);
                                        for (var j = 0; j < data.length; j++) {
                                            var hostidstr = data[j].id;
                                            var signal = data[j].ChannelName;
                                            //alert(signal);
                                            var energyMode = data[j].EnergyMode;
                                            var volume = data[j].Volume;
                                            var audioMode = data[j].AudioMode;
                                            var touchMode = data[j].TouchMode;
                                            var cpuUsage = data[j].CpuUsage;
                                            var disk_C = data[j].Disk_C;
                                            var memmary = data[j].Memmary;

                                            var topSoftWare=data[j].TopSoftWare;//置顶软件

                                            $("#status_" + hostidstr).attr("remote_energy", energyMode);
                                            $("#status_" + hostidstr).attr("touch_screen", touchMode);
                                            $("#status_" + hostidstr).attr("audio_mode", audioMode);
                                            $("#status_" + hostidstr).attr("audio_control", volume);
                                            $("#status_" + hostidstr).attr("signal", signal);
                                            $("#status_" + hostidstr).attr("top_softWare", topSoftWare);

                                            var currentSignal = "";
                                            var power = $("#host_cmd_" + hostidstr).attr("signal");

                                            var power_cmd = $("#host_cmd_code_" + hostidstr).attr("signal_cmd");
                                            if (power != "null") {
                                                var arr = power.split(",");
                                                var arr_cmd = power_cmd.split(",");
                                                for (var i = 0; i < arr.length; i++) {
                                                    if (arr_cmd[i] == signal) {
                                                        currentSignal = arr[i];
                                                    }
                                                }
                                                $("#current_signal_" + hostidstr).html(currentSignal);
                                            }
                                            var currentAudioMode = "";
                                            var audio = $("#host_cmd_" + hostidstr).attr("audio_mode");
                                            var audio_cmd = $("#host_cmd_code_" + hostidstr).attr("audio_mode_cmd");
                                            if (audio != "null") {
                                                var arr = audio.split(",");
                                                var arr_cmd = audio_cmd.split(",");
                                                for (var i = 0; i < arr.length; i++) {
                                                    if (arr_cmd[i] == audioMode) {
                                                        currentAudioMode = arr[i];
                                                    }
                                                }
                                                //音响模式
                                                $("#current_audio_mode_" + hostidstr).html(currentAudioMode);
                                            }
                                            var currentEnergyMode = "";
                                            var energy = $("#host_cmd_" + hostidstr).attr("remote_energy");
                                            var energy_cmd = $("#host_cmd_code_" + hostidstr).attr("remote_energy_cmd");
                                            if (energy != "null") {
                                                var arr = energy.split(",");
                                                var arr_cmd = energy_cmd.split(",");
                                                for (var i = 0; i < arr.length; i++) {
                                                    if (arr_cmd[i] == energyMode) {
                                                        currentEnergyMode = arr[i];
                                                    }
                                                }
                                                //节能模式
                                                $("#current_remote_energy_" + hostidstr).html(currentEnergyMode);
                                            }
                                            //音量
                                            $("#current_audio_control_" + hostidstr).html(volume);
                                            $("#current_cpuUsage_" + hostidstr).html(cpuUsage);
                                            $("#current_disk_C_" + hostidstr).html(disk_C);
                                            $("#current_memmary_" + hostidstr).html(memmary);
                                            //置顶软件
                                            $("#current_top_softWare_" + hostidstr).html(topSoftWare);
                                            document.getElementById("current_top_softWare_"+hostidstr).title=topSoftWare;
                                        }
                                    }, 'json');
                        }
                    }
                }
            }

        }
        /**
         *重写websocket.jsp中的消息类的其他时间的方法，当有其他事件时（例如，信号源切换，节能模式切换等），调用此方法
         * @param token
         * @param eventType
         * @param desc
         */
        message.processOtherEvent = function (token, eventType, desc) {
            var signalclass = desc;
            var dapingip = token;//获取ip

            var parames = signalclass.split(":::::::")[2];//获取名称
            var types = signalclass.split(":::::::")[3];
            setval(parames, dapingip, types);

        }


    })

    /**
     *
     * @param parames  获取的信息
     * @param dapingip 设备IP地址
     * @param types   事件的具体类型
     */
    function setval(parames, dapingip, types) {
//        console.log(parames+"=="+dapingip+"===="+types);
        var pinglen = <%=hostListSize%>;//获取页面大屏个数
//        console.log((pinglen));
        var hostid = "";
        var hostip = "";
        for (i = 0; i < pinglen; i++) {
            var hostStatus = $(".table_recycle").eq(i).find(".hoststatus").val();
            var hostarr = hostStatus.split("_");
            hostid = hostarr[1];
            hostip = hostarr[2];

            if (hostip == dapingip) {//比对ip
                var cid = hostid;
//                console.log(cid);
                if (types == "Channel") {
                    $("#status_" + cid).attr("signal", parames);
                    var currentSignal = "";
                    var power = $("#host_cmd_" + cid).attr("signal");
                    var power_cmd = $("#host_cmd_code_" + cid).attr("signal_cmd");
                    if (power != "null") {
                        var arr = power.split(",");
                        var arr_cmd = power_cmd.split(",");
                        for (var i = 0; i < arr.length; i++) {
                            if (arr_cmd[i] == parames) {
                                currentSignal = arr[i];
                            }
                        }
                        $("#current_signal_" + cid).html(currentSignal);
                    }
                    //$(".showhidden").eq(i).find(".allarea").find("jkn_mc_hover").children("div").eq(1).children("span").text(parames)
                }
                else if (types == "AudioMode") {
                    $("#status_" + cid).attr("audio_mode", parames);
                    var currentSignal = "";
                    var power = $("#host_cmd_" + cid).attr("audio_mode");
                    var power_cmd = $("#host_cmd_code_" + cid).attr("audio_mode_cmd");
                    if (power != "null") {
                        var arr = power.split(",");
                        var arr_cmd = power_cmd.split(",");
                        for (var i = 0; i < arr.length; i++) {
                            if (arr_cmd[i] == parames) {
                                currentSignal = arr[i];
                            }
                        }
                        $("#current_audio_mode_" + cid).html(currentSignal);
                    }
                }
                else if (types == "TouchMode") {
                    $("#status_" + cid).attr("touch_screen", parames);
                }
                else if (types == "EnergyMode") {
                    $("#status_" + cid).attr("remote_energy", parames);
                    var currentSignal = "";
                    var power = $("#host_cmd_" + cid).attr("remote_energy");
                    var power_cmd = $("#host_cmd_code_" + cid).attr("remote_energy_cmd");
                    if (power != "null") {
                        var arr = power.split(",");
                        var arr_cmd = power_cmd.split(",");
                        for (var i = 0; i < arr.length; i++) {
                            if (arr_cmd[i] == parames) {
                                currentSignal = arr[i];
                            }
                        }
                        $("#current_remote_energy_" + cid).html(currentSignal);
                    }
                }

                else if (types == "TopSoftWare") {
                    $("#status_" + cid).attr("top_softWare", parames);
                    $("#current_top_softWare_" + cid).html(parames);
                }
                else if (types == "Volume") {
                    $("#status_" + cid).attr("audio_control", parames);
                    $("#current_audio_control_" + cid).html(parames);
                }
                else if (types == "ClassName") {
                    if ($(".showhidden").eq(i).find(".allarea").find(".jkn_mc_hover").children("div").eq(0).children("span").text() != parames) {
                        $(".showhidden").eq(i).find(".allarea").find(".jkn_mc_hover").children("div").eq(0).children("span").text(parames);
                        var treelen = parent.$(".tree_title_open").find(".tree_content").children(".tree_contenta");
                        for (i = 0; i<treelen.length; i++) {
                            var treeip = treelen.eq(i).attr("token");
                            if (treeip == dapingip) {
                                treelen.eq(i).find(".hostoverflow").text(parames);
                                $("#current_class_" + cid).html(parames);
                            }
                        }
                    }
                }
            }
        }
    }
</script>

</html>