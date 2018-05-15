<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: chby
  Date: 2014/10/11
  Time: 8:45
  To change this template use File | Settings | File Templates.

  此页面作废，添加录播网络设备时，在device_management.jsp中重写了此页面的方法，此页面作废
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
    <title>设备管理 | 集控平台</title>
    <link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/index.css" rel="stylesheet" type="text/css"/>

    <script src="${pageContext.request.contextPath}/js/common/jquery-1.8.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/selectmore_device.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>

</head>
<style>
    #selectdivall0 {
        background: url(${pageContext.request.contextPath}/image/frontend/n_icon_141006.png) 0px -460px no-repeat;
    }

    .set_vv_line {
        margin-top: 15px;
        width: 50%;
    }

    .selectdivul a {
        text-indent: 10px;
        line-height: 28px;
    }
</style>
<%
    List<Object[]> deviceConfigList_jp = (List<Object[]>) request.getAttribute("deviceConfig_jp");
    List<Object[]> deviceConfigList_fbs = (List<Object[]>) request.getAttribute("deviceConfig_fbs");
    List<Object[]> deviceConfigList_1u = (List<Object[]>) request.getAttribute("deviceConfig_1u");
%>
<body style="background: white">
<div class="win800" style="height:280px;position: absolute;left: 0;right:0;top:0;bottom:0;margin:auto">
    <div class="set_vv_line">
        <span class="set_vv_linetext">设备类型</span>

        <div class="sall" style="margin-left: 10px">
            <select class="select" id="select0">
                <option value="0" selected="selected">分布式</option>
                <option value="1">精品</option>
                <option value="2">精品Arec</option>
                <option value="3">大屏</option>
                <option value="4">TBOX</option>
                <option value="5">投影机</option>
            </select>

            <div class="selectdivall" id="selectdivall0">
                <div class="selectdiv" id="selectdiv0" style="line-height: 30px;"></div>
                <div class="selectdivul" id="selectdivul0" style="top:-8px;overflow-y: hidden;width: 115px"></div>
            </div>
        </div>
    </div>
    <div class="set_vv_line">
        <span class="set_vv_linetext">班级IP</span>
        <input class="set_vv_linesinput" id="ip" type="text"/>
    </div>
    <div class="set_vv_line">
        <span class="set_vv_linetext">班级名称</span>
        <input class="set_vv_linesinput" id="name" type="text"/>
    </div>

    <div class="set_vv_line">

    </div>

    <div class="set_vv_line" style="width: 100%;">
        <span class="set_vv_linetext" style="color: #28B779;" id="wwset">外网设置</span>
    </div>

    <div class="scrollfather" id="demo2">
        <div class="scrollson">
            <div class="set_vv_line" style="width: 100%;">
                <div id="jinttouclick"
                     style="cursor:pointer;color: #fff;background: #28B779;border-radius: 5px;height: 26px;width: 75px;line-height: 26px;text-align: center;margin-left: 63px;">
                    镜头添加
                </div>
            </div>
            <div id="addjingtou" style="float: left;width: 100%;"></div>
            <div class="set_vv_line">
                <span class="set_vv_linetext">设备访问</span>
                <input class="set_vv_linesinput" id="sbfw" type="text" value="">
            </div>

            <div class="set_vv_line">
                <span class="set_vv_linetext">事件监听</span>
                <input class="set_vv_linesinput" id="sjjt" type="text" value="">
            </div>
            <div class="set_vv_line">
                <span class="set_vv_linetext">媒体访问</span>
                <input class="set_vv_linesinput" id="mtfw" type="text" value="">
            </div>
            <div class="set_vv_line">
                <span class="set_vv_linetext">云台控制</span>
                <input class="set_vv_linesinput" id="ytkz" type="text" value="">
            </div>
            <div class="set_vv_line">
                <span class="set_vv_linetext">导播控制</span>
                <input class="set_vv_linesinput" id="dbkz" type="text" value="">
            </div>

        </div>
        <div class="scroll_ymove">
            <div class="scroll_y" unorbind="unbind"></div>
        </div>
        <div class="scroll_xmove">
            <div class="scroll_x" unorbind="unbind"></div>
        </div>
    </div>

    <div class="win_btn" style="margin: 20px 20px 20px 0;">
        <div class="win_btn_sure" style="margin-right: 20px;" onclick="add()">完成</div>
        <div class="win_btn_done" onclick="parent.layer.closeAll();">取消</div>
    </div>
</div>
</body>
<script>
    var flag = 0;//0:手动添加,1:手动添加外网设备


    function add() {
        var ip = $("#ip").attr("value").trim();
        var name = $("#name").attr("value").trim();
        var type = $("#select0").val();
        if (ip.length == 0) {
            layer.msg("班级ip不能为空");
            return;
        }
        if (type != 3) {
            if (name.length == 0) {
                layer.msg("班级名称不能为空");
                return;
            }
        }
        if (name.length > 16) {
            layer.msg("班级名称不能超过15个字符");
            return;
        }

        if (flag == 0) {
            $(".demo2").css("display", "none");
            editannotatediv("设备正在进行配置");
            $.post(urlhead + '/host/Host_addNewHost.do', {
                'hostIpaddr': ip,
                'hostName': name,
                'deviceType': type
            }, function (data) {
                if (data.result == 1) {
                    setTimeout(function () {
                        $(".demo").css("display", "none");
                        layer.msg("班级已存在");
                    }, 2000);
                } else if (data.result == -1) {
                    $(".demo").css("display", "none");
                    layer.msg("班级添加失败");
                } else if (data.result == -2) {
                    $(".demo").css("display", "none");
                    layer.msg("已超过授权数量限制，添加失败");
                } else {
                    $(".demo").css("display", "none");
                    layer.msg("班级增加成功");
                    location.reload();
                }
            }, 'json');
        }
        else {
            var tokenName = "";
            var mainTokenName = "";
            var subTokenName = "";
            for (var i = 1; i < $(".selectdiv").length; i++) {
                tokenName += $(".selectdiv").eq(i).text().trim() + ",";
                mainTokenName += $(".selectdiv").eq(i).attr("main").trim() + ",";
                subTokenName += $(".selectdiv").eq(i).attr("sub").trim() + ",";
            }
            var mainTokenUrl = "";
            for (var i = 0; i < $(".main_tokenurl").length; i++) {
                mainTokenUrl += $(".main_tokenurl").eq(i).val().trim() + ",";
            }
            var subTokenUrl = "";
            for (var i = 0; i < $(".sub_tokenurl").length; i++) {
                subTokenUrl += $(".sub_tokenurl").eq(i).val().trim() + ",";
            }
            var sbfw = "";
            sbfw = $("#sbfw").val().trim();
            if (sbfw == "") {
                layer.msg("设备访问不能为空");
                return;
            }
            sbfw = "http://www.onvif.org/ver10/device/wsdl " + sbfw;
            var sjjt = "";
            sjjt = $("#sjjt").val().trim();
            if (sjjt == "") {
                layer.msg("事件监听不能为空");
                return;
            }
            sjjt = "http://www.onvif.org/ver10/events/wsdl " + sjjt;
            var mtfw = "";
            mtfw = $("#mtfw").val().trim();
            if (mtfw == "") {
                layer.msg("媒体访问不能为空");
                return;
            }
            mtfw = "http://www.onvif.org/ver10/media/wsdl " + mtfw;
            var ytkz = "";
            ytkz = $("#ytkz").val().trim();
            if (ytkz == "") {
                layer.msg("云台控制不能为空");
                return;
            }
            ytkz = "http://www.onvif.org/ver20/ptz/wsdl " + ytkz;
            var dbkz = "";
            dbkz = $("#dbkz").val().trim();
            if (dbkz == "") {
                layer.msg("导播控制不能为空");
                return;
            }
            dbkz = "http://www.honghe-tech.com/spec/hrec/wsdl " + dbkz;
            var netUrl = sbfw + "," + sjjt + "," + mtfw + "," + ytkz + "," + dbkz;
            $(".demo2").css("display", "none");
            editannotatediv("设备正在进行配置");
            $.post(urlhead + '/host/Host_addNetHost.do',
                    {
                        'hostIpaddr': ip,
                        'hostName': name,
                        'deviceType': type,
                        'netUrl': netUrl,
                        'deviceNames': tokenName,
                        'mainTokens': mainTokenName,
                        'subTokens': subTokenName,
                        'mianTokenStreams': mainTokenUrl,
                        'subTokenStreams': subTokenUrl
                    },
                    function (data) {
                        if (data.result == 1) {
                            setTimeout(function () {
                                $(".demo").css("display", "none");
                                layer.msg("班级已存在");
                            }, 2000);
                        } else if (data.result == -1) {
                            $(".demo").css("display", "none");
                            layer.msg("班级添加失败");
                        } else if (data.result == -2) {
                            $(".demo").css("display", "none");
                            layer.msg("已超过授权数量限制，添加失败");
                        } else {
                            $(".demo").css("display", "none");
                            layer.msg("班级增加成功");
                            location.reload();
                        }
                    }, 'json');
        }

        parent.document.getElementById("deviceAddHidden").value = ip + "@_@" + name + "@_@" + type;
        parent.layer.closeAll();
    }


    $("#jinttouclick").click(function () {
        var index = 0;
        var vvlinelen = $("#addjingtou").find(".set_vv_line").length + 1;
        var tokenName = $("#select0 option:selected").text();
        //添加镜头
        var jingtouhtml = "<div class=\"set_vv_line\"  style=\"width: 100%;position:relative\">";
        jingtouhtml += "<span class=\"set_vv_linetext\">镜头类型</span>";
        jingtouhtml += "<div class=\"sall\" style=\"margin-left: 10px\">";
        jingtouhtml += "<select class=\"select\" id=\"select" + vvlinelen + "\">";
        if (tokenName == "精品") {
            <% if(deviceConfigList_jp != null && !deviceConfigList_jp.isEmpty())
            {
                for(int i = 0;i < deviceConfigList_jp.size();i++)
                {
            %>
            if (index == 0) {
                jingtouhtml += "<option value=\"" + index + "\" selected=\"selected\" main=\"<%=deviceConfigList_jp.get(i)[2].toString()%>\"  sub=\"<%=deviceConfigList_jp.get(i)[3].toString()%>\"><%=deviceConfigList_jp.get(i)[1].toString()%></option>";
            }
            else {
                jingtouhtml += "<option value=\"" + index + "\"  main=\"<%=deviceConfigList_jp.get(i)[2].toString()%>\"  sub=\"<%=deviceConfigList_jp.get(i)[3].toString()%>\"><%=deviceConfigList_jp.get(i)[1].toString()%></option>";
            }
            index++;
            <%
               }
            }
            %>
        }
        else if (tokenName == "分布式") {
            <% if(deviceConfigList_fbs != null && !deviceConfigList_fbs.isEmpty())
             {
                 int jj = 0;
                 for(int i = 0;i < deviceConfigList_fbs.size();i++)
                 {
             %>
            if (index == 0) {
                jingtouhtml += "<option value=\"" + index + "\" selected=\"selected\"  main=\"<%=deviceConfigList_fbs.get(i)[2].toString()%>\"  sub=\"<%=deviceConfigList_fbs.get(i)[3].toString()%>\"><%=deviceConfigList_fbs.get(i)[1].toString()%></option>";
            }
            else {
                jingtouhtml += "<option value=\"" + index + "\"  main=\"<%=deviceConfigList_fbs.get(i)[2].toString()%>\"  sub=\"<%=deviceConfigList_fbs.get(i)[3].toString()%>\"><%=deviceConfigList_fbs.get(i)[1].toString()%></option>";
            }
            index++;
            <%
               }
            }
            %>
        }
        else if (tokenName == "1U") {
            <% if(deviceConfigList_1u != null && !deviceConfigList_1u.isEmpty())
                 {
                     int jj = 0;
                     for(int i = 0;i < deviceConfigList_1u.size();i++)
                     {
                 %>
            if (index == 0) {
                jingtouhtml += "<option value=\"" + index + "\" selected=\"selected\" main=\"<%=deviceConfigList_1u.get(i)[2].toString()%>\"  sub=\"<%=deviceConfigList_1u.get(i)[3].toString()%>\"><%=deviceConfigList_1u.get(i)[1].toString()%></option>";
            }
            else {
                jingtouhtml += "<option value=\"" + index + "\" main=\"<%=deviceConfigList_1u.get(i)[2].toString()%>\"  sub=\"<%=deviceConfigList_1u.get(i)[3].toString()%>\"><%=deviceConfigList_1u.get(i)[1].toString()%></option>";
            }
            index++;
            <%
               }
            }
            %>
        }
        jingtouhtml += "</select>";
        jingtouhtml += "<div class=\"selectdivall\" id=\"selectdivall" + vvlinelen + "\">";
        jingtouhtml += "<div class=\"selectdiv\" id=\"selectdiv" + vvlinelen + "\" style=\"line-height: 30px;\"></div>";
        jingtouhtml += "<div class=\"selectdivul\" id=\"selectdivul" + vvlinelen + "\" style=\"top:-8px;width: 115px;\"></div>";
        jingtouhtml += "</div>";
        jingtouhtml += "</div>";
        jingtouhtml += "<span class=\"set_vv_linetext\" style=\"width: 40px;\">主码流</span>";
        jingtouhtml += "<input class=\"set_vv_linesinput main_tokenurl\" id=\"vvlineidmain" + vvlinelen + "\"  type=\text\" value=\"\">";
        jingtouhtml += "<span class=\"set_vv_linetext\" style=\"width: 40px;\">子码流</span>";
        jingtouhtml += "<input class=\"set_vv_linesinput sub_tokenurl\" id=\"vvlineidson" + vvlinelen + "\"  type=\"text\" value=\"\">";
        jingtouhtml += "<div class=\"chapterdel\" style=\"display: block;right:40px;top:10px\"></div>"
        jingtouhtml += "</div>";
        $("#addjingtou").append(jingtouhtml)
        var slen = $(".sall").length;//获取当前所属div(分组)长度
        //alert(slen)
        changeselectelse(vvlinelen)//循环调用赋值方法
        scroll_y("demo2", "scrollson", "scroll_y", "scroll_ymove", "scroll_x", "scroll_xmove", "add", "wheely", "")
    })


    //wwset外网设置
    $("#wwset").click(function () {
        $(".win800").height(500)
        $("#demo2").height(517)
        flag = 1;
    })

    //隐藏demo2
    $(".demo2 .win_btn_done").click(function () {
        $(".demo2").hide()
    })

    //    $(function () {
    //        //点击背景隐藏
    //        $(".demo2").click(function(){
    //            $(this).hide()
    //        })
    //删除镜头
    $(".chapterdel").live("click", function () {
        $(this).parent(".set_vv_line").remove()
    })
    var vipt_videowid = $(".mm_right").width()
    $("#mm_right_video .scrollson").width(vipt_videowid)
    $("#mm_right_video").height($(".mm").height() * 0.93)
    $("#mm_right_video").width($(".mm_right").width())

    $("#mm_right_video .scrollson").mouseover(function () {
        $("#whichscroll").val($.trim($(this).parent().attr("id")))
        if ((navigator.userAgent.match(/(iPhone|Android|iPad)/i))) {
            var scrollfathter1 = document.getElementById($.trim($(this).parent().attr("id")));
            scrollfathter1.addEventListener("touchstart", touchStart, false);
            scrollfathter1.addEventListener("touchmove", touchMove, false);
            scrollfathter1.addEventListener("touchend", touchEnd, false);
        }
    })
    scroll_y("mm_right_video", "scrollson", "scroll_y", "scroll_ymove", "scroll_x", "scroll_xmove", "", "wheely", "")
    $("#mm_right_video .scrollson").css("margin-top", "0")
    $("#mm_right_video .scroll_y").css("top", "0")

    $("#demo2").width(800)

    $("#demo2 .scrollson").mouseover(function () {
        $("#whichscroll").val($.trim($(this).parent().attr("id")))
        if ((navigator.userAgent.match(/(iPhone|Android|iPad)/i))) {
            var scrollfathter2 = document.getElementById($.trim($(this).parent().attr("id")));
            scrollfathter2.addEventListener("touchstart", touchStart, false);
            scrollfathter2.addEventListener("touchmove", touchMove, false);
            scrollfathter2.addEventListener("touchend", touchEnd, false);
        }
    })
    scroll_y("demo2", "scrollson", "scroll_y", "scroll_ymove", "scroll_x", "scroll_xmove", "", "wheely", "")
    $("#demo2 .scrollson").css("margin-top", "0")
    $("#demo2 .scroll_y").css("top", "0")


    //   });

</script>


</html>

<script>

</script>