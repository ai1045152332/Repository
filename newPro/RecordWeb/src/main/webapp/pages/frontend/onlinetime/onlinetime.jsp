<%@ page import="com.honghe.recordhibernate.entity.DeviceLog" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.honghe.recordhibernate.dao.Pager" %>
<%@ page import="com.honghe.recordweb.action.SessionManager" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%--
  Created by IntelliJ IDEA.
  User: lch
  Date: 2015/1/14
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
    <title>报表统计 | 集控平台</title>
    <link href="<%=path%>/css/frontend/main.css" rel="stylesheet" type="text/css"/>
    <link href="<%=path%>/css/frontend/index.css" rel="stylesheet" type="text/css"/>
    <link href="<%=path%>/layer-v2.4/layer/skin/layer.css" rel="stylesheet" type="text/css"/>

    <script src="<%=path%>/js/common/jquery-1.8.0.min.js"></script>
    <script src="<%=path%>/js/common/screendetail.js"></script>
    <script src="<%=path%>/js/common/perfect-scrollbar.with-mousewheel.min.js"></script>
    <script src="<%=path%>/js/common/prettify.js"></script>
    <script src="<%=path%>/js/common/checkbox_res.js"></script>
    <script src="<%=path%>/js/common/selectmore.js"></script>
    <script src="<%=path%>/layer-v2.4/layer/layer.js"></script>
    <%--<script src="<%=path%>/js/common/layer/layer.min.js"></script>--%>
    <%--<script src="<%=path%>/js/common/layer/extend/layer.ext.js"></script>--%>
    <!--layerdate-->
    <script src="<%=path%>/js/common/laydate/laydate.js"></script>
</head>

<body>
<div class="public">
    <jsp:include page="../equipment_header.jsp"></jsp:include>
    <style>
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

        .logsearch {
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
            width: 82px;
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

        .wid_20, .wid_16, .wid_10, .wid_6, .wid_24, .wid_15 {
            overflow: hidden;
            text-overflow: ellipsis;
            text-indent: 10px;
            white-space: nowrap;
        }

        #selectdivall0 {
            background: url("/image/frontend/n_icon_141006.png") no-repeat scroll 0px -458px transparent;
        }

        .reportlistbtn {
            background: #ffffff none repeat scroll 0 0;
            border: solid 1px #000000;
            border-radius: 8px;
            color: #000000;
            cursor: pointer;
            float: left;
            font-size: 14px;
            height: 27px;
            line-height: 27px;
            text-align: center;
            width: 120px;
        }

        .demo {
            background: rgba(0, 0, 0, 1);
            display: none;
            left: 0;
            position: absolute;
            top: 0;
            z-index: 10;
        }

        .delete {
            background: rgba(0, 0, 0, 1);
            display: none;
            left: 0;
            position: absolute;
            top: 0;
            z-index: 10;
        }

        .selectdel {
            display: inline;
            border: solid 1px #000;
            float: none;
            height: 23px;
        }
    </style>
    <%
        String deviceType = SessionManager.get(request.getSession(), SessionManager.Key.DeviceType);
    %>
    <div class="mm floatleft">
        <div class="mm_head floatleft">
            <div class="logmargintop" style="width: 94%;margin-left: 3%;">
                <div style="width: 70px;float: left;line-height: 33px;text-align: center;">设备名称：</div>
                <div class="sall">

                    <select class="select" id="select0">
                        <option value="ALL" selected="selected">全部设备</option>
                        <c:forEach var="device" items="${deviceList}" varStatus="st">
                            <option value="${device.mac}">${device.name}</option>
                        </c:forEach>
                    </select>
                    <div class="selectdivall" id="selectdivall0">
                        <div class="selectdiv" id="selectdiv0"></div>
                        <div class="selectdivul" id="selectdivul0"
                             style="height: ${(deviceList.size()+1)*36}px;max-height: 400px;overflow: auto"></div>
                    </div>
                </div>
                <div style="width: 85px;float: left;line-height: 33px;">统计时间段：</div>
                <div class="win360_content_date">
                    <input id="start" class="laydate-icon">
                    <input type="hidden" value="" id="startTime">
                </div>
                <div style="width: 25px;display: block; float: left; text-align: center;line-height: 33px;">至</div>
                <div class="win360_content_date">
                    <input id="end" class="laydate-icon">
                    <input type="hidden" value="" id="endTime">
                </div>
                <div class="logsearch" style="float: left;margin-left: 33px" onclick="search()">搜索</div>
                <a class="logsearch" style="float: right;margin-left: 33px;color: #fff"
                   href="<%=path%>/onlinetime/Onlinetime_viewChart.do?chartType=kaijishichang">查看图表</a>
                <div class="logsearch" style="float: right;margin-left: 33px" onclick="deleteData()">清理数据</div>
                <div class="logsearch" style="float: right;margin-left: 33px" onclick="excel()">导出数据</div>
            </div>
        </div>
        <div class="demo">
            <div class="win800" style="height:200px;width:400px;position: absolute;left: 600px ;top:220px;margin:auto ">
                <div class="win_head">
                    <div class="win_head_text">导出当前数据</div>
                    <div class="win_close"></div>
                </div>
                <div class="win_content" style="width: 100%;margin-left: auto;">
                    <br/>
                    <div>&nbsp;&nbsp;&nbsp;&nbsp;是否确定导出当前时间段内的数据</div>
                    <br/><br/>
                    <div class="win_btn" style="margin: 20px 20px 20px 0;">
                        <div class="win_btn_sure" style="margin-right: 20px;" onclick="exportexcel()">确定</div>
                        <div class="win_btn_done">取消</div>
                    </div>
                </div>
            </div>
        </div>

        <div class="delete">
            <div class="win800" style="height:200px;width:400px;position: absolute;left: 600px ;top:220px;margin:auto ">
                <div class="win_head">
                    <div class="win_head_text">清理数据</div>
                    <div class="win_close" id="win_close_del"></div>
                </div>
                <div class="win_content" style="width: 100%;margin-left: auto;">
                    <br/>
                    <div>&nbsp;&nbsp;&nbsp;&nbsp;请选择要清理的时间段：
                        <select class="selectdel" id="selectdel">
                            <option selected="selected" value="365">一年前</option>
                            <option value="180">半年前</option>
                            <option value="90">三个月前</option>
                            <option value="30">一个月前</option>
                        </select>
                    </div>
                    <br/>
                    <div class="win_btn" style="margin: 20px 20px 20px 0;">
                        <div class="win_btn_sure" style="margin-right: 20px;" onclick="cleardata()">确定</div>
                        <div class="win_btn_done" id="win_btn_done_del">取消</div>
                    </div>
                </div>
            </div>
        </div>

        <script>
            function exportexcel() {
                var deviceName = $("#select0").val();;
                var starttime = $("#startTime").val() + " 00:00:00";
                var endtime = $("#endTime").val() + " 23:59:59";
                starttime = DateToUnix(starttime);
                endtime = DateToUnix(endtime);
                if (starttime < 0 && endtime < 0) {
                    starttime = 0;
                    endtime = 0
                }
                //dltype = encodeURI(dltype);
                if (starttime >= 0 && endtime >= 0) {
                    console.log('shade open!');
                    layer.load(2, {
                        shade: [0.1, '#fff'] //0.1透明度的白色背景
                    });
                    var root_path = $("#root_path").val();
                    $("iframe").remove();
                    var iframe = document.createElement("iframe");
                    $(iframe).css("display","none");
                    iframe.src = root_path + "/onlinetime/Onlinetime_exportExcel.do?startTime=" + starttime + "&endTime=" + endtime+"&deviceMac="+deviceName;
                    iframe.onload = function(){
                        layer.closeAll();
                    };
                    document.body.appendChild(iframe);
                    if (!!window.ActiveXObject || "ActiveXObject" in window){//ie 下监听
                        self.frames[0].document.onreadystatechange = function(){
                            if(this.readyState=='interactive'){
                                layer.closeAll();
                                console.log('shade closed!');
                            }
                        }
                    }
                } else {
                    layer.msg("请选择正确的时间", 1);
                }
                $(".demo").hide();
            }
            function excel() {
                var starttime = $("#startTime").val() + " 00:00:00";
                var endtime = $("#endTime").val() + " 23:59:59";
                starttime = DateToUnix(starttime);
                endtime = DateToUnix(endtime);
                if (starttime >= 0 && endtime >= 0) {
                    $(".demo").show();
                } else {
                    layer.msg("请选择正确的时间", 1);
                }
            }
            function deleteData() {
                $(".delete").show();
            }
            $(".win_close").click(function () {
                $(".demo").hide()
            })
            $(".win_btn_done").click(function () {
                $(".demo").hide()
            })
            $("#win_close_del").click(function () {
                $(".delete").hide()
            })
            $("#win_btn_done_del").click(function () {
                $(".delete").hide()
            })

            var start = {
                elem: '#start',
                format: 'YYYY-MM-DD',
                festival: true, //是否显示节日
                istoday: false,
                choose: function (datas) {
                    end.min = datas; //开始日选好后，重置结束日的最小日期
                    end.start = datas //将结束日的初始值设定为开始日
                    $("#startTime").val(datas);
                }
            };
            var end = {
                elem: '#end',
                format: 'YYYY-MM-DD',
                festival: true, //是否显示节日
                istoday: false,
                choose: function (datas) {
                    start.max = datas; //结束日选好后，重置开始日的最大日期
                    $("#endTime").val(datas);
                }
            };
            laydate(start);
            laydate(end);

        </script>
        <div class="user" style="margin-left: 3%;min-width: 0;width: 94%;">
            <div class="table_head " style="margin-top: 15px;">
                <div class="wid_15 floatleft">设备名称</div>
                <div class="wid_15 floatleft">IP</div>
                <div class="wid_15 floatleft">开机率</div>
                <div class="wid_15 floatleft">使用时长</div>
                <div class="wid_15 floatleft">活跃度</div>
                <div class="wid_25 floatleft"></div>
            </div>
            <div id="reportlist">
                <div class="wid_100 floatleft hei_93"
                     style="${onlineTimeData.size()>11?'overflow-y:scroll;height:90%':''}">
                    <c:forEach var="online" items="${onlineTimeData}" varStatus="st">
                        <div class="table_recycle">
                            <div class="wid_15 floatleft hei_100">${online.name}</div>
                            <div class="wid_15 floatleft hei_100">${online.ip}</div>
                            <div class="wid_15 floatleft hei_100">${online.openRate}</div>
                            <div class="wid_15 floatleft hei_100">${online.openDuration}</div>
                            <div class="wid_15 floatleft hei_100">${online.acitivity}</div>
                            <div class="wid_25 floatleft hei_100">
                                <a class="reportlistbtn" style="margin-top: 2%"
                                   href="<%=path%>/onlinetime/Onlinetime_softInUse.do?softMac=${online.mac}">软件使用情况</a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <link href="${pageContext.request.contextPath}/css/frontend/hpager.css" rel="stylesheet"
                      type="text/css"/>
                <style>
                    #linkpage {
                        bottom: 45px;
                    }
                </style>
                <%
                    Integer currentpage = Integer.parseInt(request.getAttribute("currentPage").toString());
                    int pageCount = Integer.parseInt(request.getAttribute("pageCount").toString());
                    Pager pager = new Pager(pageCount, currentpage, 3, "<span class='pages_prevpage'></span>", "<span class='pages_nextpage'></span>", "", "", false);
                    String pagers = pager.run();
                %>
                <style>
                    #linkpage {
                        position: absolute;
                        bottom: 4.5%;
                        left: 55%;
                        text-align: center;
                        margin-left: -252px;
                        width: 476px;
                    }
                </style>
                <div id="linkpage">
                    <%=pagers%>
                </div>
            </div>
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
                    width: 93px;
                    height: 28px;
                    padding-right: 20px;
                    line-height: 28px;
                    margin-top: 0;
                    overflow: hidden;
                    white-space: nowrap;
                    text-overflow: ellipsis;
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
                    overflow: hidden;
                    white-space: nowrap;
                    text-overflow: ellipsis;
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

                .logsearch {
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
        </div>
    </div>
    <div class="foot">
        <jsp:include page="/pages/frontend/footer.jsp"></jsp:include>
    </div>

</div>
</body>
<script>
    var totalpagesize = "<%=pageCount%>";
    //分页调整
    var page = totalpagesize;
    var html = "<span style='float: left;margin-left: 2px;'>&nbsp;&nbsp;共<span id='pageCount'>" + page + "</span>页 &nbsp;&nbsp;跳转到 " + "<input id=\"jump\" type=text style=\"width:20px;background:#d7dade;border:1px solid #5e6470\" title=\"点击回车即可跳转分页\" onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'')\" onkeydown=\"jump(event)\" onafterpaste=\"this.value=this.value.replace(/[^0-9]/g,'')\"/> 页<span>";
    $(".yiiPager").append(html)
    left = -(18) * 28 / 2 + "px";
    $("#linkpage").css({"margin-left": left, "width": (17) * 28 + "px"});
    $(function () {
        $(".wid_6,.wid_16,wid_10,.wid_36").css({
            "overflow": "hidden",
            "text-overflow": "ellipsis",
            "white-space": "nowrap"
        })
    })
    $("#linkpage li a").live("click", function () {
        var href = $(this).attr("href");
        if (href.indexOf("Ajax") == -1) {
            var href = href.replace('report', 'reportAjax');
        }
        $.get(href, {}, function (data) {
            $("#reportlist").html(data);
        }, 'html');
        return false;
    });
    function jump(event) {
        //判断当event.keyCode 为37时（即左方面键），执行函数to_left();
        //判断当event.keyCode 为39时（即右方面键），执行函数to_right();
        if (event.keyCode == 13) {
            //alert("aaa")
            var jumpval = $("#jump").val();
            var patrn = /^[0-9]*$/;
            if (!patrn.exec(jumpval)) {
                return false;
            } else {
                var lilen = $("#linkpage ul li").length - 1;
                var prevhref = $("#linkpage ul li").eq(0).find("a").attr("href");
                var nexthref = $("#linkpage ul li").eq(lilen).find("a").attr("href");
                var thisurl = "";
                if (prevhref == "" && nexthref == "") {
                    return false;
                } else if (prevhref == "" || prevhref == undefined) {
                    thisurl = nexthref;
                    urloption(thisurl)
                } else if (nexthref == "" || nexthref == undefined) {
                    thisurl = prevhref;
                    urloption(thisurl)
                } else {
                    thisurl = nexthref;
                    urloption(thisurl)
                }
            }
        }
    }
    /**
     * 获取url地址中的参数
     */
    function urloption(url) {
        if (url.indexOf("Ajax") == -1) {
            var url = url.replace('report', 'reportAjax');
        }
        var totalpagesize = $("#pageCount").html();
        if (url.indexOf("?") != -1) {
            var p = url.indexOf("?"); //返回所在位置
            var host = "http://" + window.location.host + url.substr(0, p + 1);
            var str = url.substr(p + 1) //从这个位置开始截取
            strs = str.split("&"); //拆分
            var jumpval = $("#jump").val();
            var patrn = /^[0-9]*$/;
            if (!patrn.exec(jumpval) || jumpval <= 0) {
                jumpval = 1;
            } else if (parseInt(jumpval) >= parseInt(totalpagesize)) {
                jumpval = totalpagesize
            }
            var newurl = host;
            for (var i = 0; i < strs.length; i++) {
                if (i == 0) {
                    newurl += strs[i].split("=")[0] + "=" + jumpval + "&";
                } else {
                    newurl += strs[i].split("=")[0] + "=" + unescape(strs[i].split("=")[1]) + "&";
                }
            }
            var urllen = newurl.length - 1;
            var newurl = newurl.substr(0, urllen) //从这个位置开始截取
            $.get(newurl, {}, function (data) {
                $("#reportlist").html(data);
            }, "html");
        }
    }
    function DateToUnix(string) {
        var f = string.split(' ', 2);
        var d = (f[0] ? f[0] : '').split('-', 3);
        var t = (f[1] ? f[1] : '').split(':', 3);
        return (new Date(
                        parseInt(d[0], 10) || null,
                        (parseInt(d[1], 10) || 1) - 1,
                        parseInt(d[2], 10) || null,
                        parseInt(t[0], 10) || null,
                        parseInt(t[1], 10) || null,
                        parseInt(t[2], 10) || null
                )).getTime() / 1000;
    }

    function search() {
        var deviceName = $("#select0").val();
        var starttime = $("#startTime").val() + " 00:00:00";
        var endtime = $("#endTime").val() + " 23:59:59";
        starttime = DateToUnix(starttime);
        endtime = DateToUnix(endtime);
        if (starttime < 0 && endtime < 0) {
            starttime = 0;
            endtime = 0
        }
        if (starttime > 0 && endtime > 0) {
            var root_path = $("#root_path").val();
            $.post(root_path + '/onlinetime/Onlinetime_reportAjax.do', {
                deviceMac: deviceName,
                startTime: starttime,
                endTime: endtime
            }, function (data) {
                $("#reportlist").html(data);
            }, 'html');
        } else {
            layer.msg("请选择正确的时间", 1);
        }
    }
    function cleardata() {
        var deletetime = $("#selectdel").val();
        var root_path = $("#root_path").val();
        $.ajax({
            type: "post",
            url: root_path + "/onlinetime/Onlinetime_clear.do",
            data: {deletetime: deletetime, deviceType: "<%=deviceType%>"},
            dataType: "json",
            success: function (data) {
                layer.msg(data.msg);
                $(".delete").hide()
            },
            error: function (data) {
                layer.msg(data.msg);
                $(".delete").hide()
            }
        });
    }
</script>
</html>