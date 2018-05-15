<%@ page import="com.honghe.recordhibernate.entity.DeviceLog" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.honghe.recordhibernate.dao.Pager" %>

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

    <script src="<%=path%>/js/common/jquery-1.8.0.min.js"></script>
    <script src="<%=path%>/js/common/screendetail.js"></script>
    <script src="<%=path%>/js/common/perfect-scrollbar.with-mousewheel.min.js"></script>
    <script src="<%=path%>/js/common/prettify.js"></script>
    <script src="<%=path%>/js/common/checkbox_res.js"></script>
    <script src="<%=path%>/js/common/selectmore.js"></script>
    <script src="<%=path%>/js/common/layer/layer.min.js"></script>
    <script src="<%=path%>/js/common/layer/extend/layer.ext.js"></script>
    <!--layerdate-->
    <script src="<%=path%>/js/common/laydate/laydate.js"></script>
</head>
<script type="text/javascript" src="<%=path%>/js/common/charts.js"></script>
<script type="text/javascript" src="<%=path%>/js/common/exporting.js"></script>
<script>


</script>
<body>
<div class="public">
    <jsp:include page="../header.jsp"></jsp:include>
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

    </style>
    <div class="mm floatleft">
        <div class="mm_head floatleft">
            <div class="logmargintop" style="width: 94%;margin-left: 3%;">
                <div style="width: 70px;float: left;line-height: 33px;text-align: center;">图表内容：</div>
                <div class="sall">
                    <select class="select" id="select0">
                        <option value="kaijishichang" selected="selected">开机时长</option>
                        <option value="kaijilv">开机率</option>
                        <option value="huoyuedu">活跃度</option>
                    </select>

                    <div class="selectdivall" id="selectdivall0">
                        <div class="selectdiv" id="selectdiv0"></div>
                        <div class="selectdivul" id="selectdivul0"
                             style="height: 90px;max-height: 400px;overflow: auto"></div>
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
                <a class="logsearch" style="float: right;margin-left: 33px;color: #fff" href="<%=path%>/onlinetime/Onlinetime_report.do">查看列表</a>
            </div>
        </div>
        <script>
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
            <div id="reportlist" class="wid_100 floatleft hei_93">
                <div id="chart" style="min-width:700px;height:100%;width: 90%"></div>
                <link href="<%=path%>/css/frontend/hpager.css" rel="stylesheet"
                      type="text/css"/>
                <style>
                    #linkpage {
                        bottom: 45px;
                    }
                </style>
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
    </div>
    <div class="foot">
        <jsp:include page="/pages/frontend/footer.jsp"></jsp:include>
    </div>

</div>
</body>
<script>
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
    var mingcheng = [];
    var shuzhi = [];
    var biaotou = '';
    var danwei = '';
    var geshi = '';
    $(function () {
        Highcharts.theme = {
            colors: ['#28b779']
        };
        var highchartsOptions = Highcharts.setOptions(Highcharts.theme);
        search();
    });

    <!--下面是开机时长 -->
    function tubiao() {
        $('#chart').highcharts({
            chart: {
                type: 'column',
                margin: [50, 50, 100, 100]
            },
            title: {
                text: biaotou
            },
            plotOptions: {
                column : {
                    pointPadding: 0.3,
                    groupPadding: 0.1
                }
            },
            xAxis: {
                categories: mingcheng,
                labels: {
                    rotation: 0,
                    align: 'center',
                    style: {
                        fontSize: '13px',
                        fontFamily: 'Verdana, sans-serif'
                    },
                    staggerLines:'1'
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: danwei
                }
            },
            legend: {
                enabled: false
            },
            tooltip: {
                pointFormat: geshi
            },
            series: [{
                name: '',
                data: shuzhi,
                dataLabels: {
                    enabled: false,
                    rotation: -90,
                    color: '#FFFFFF',
                    align: 'right',
                    x: 4,
                    y: 10,
                    style: {
                        fontSize: '13px',
                        fontFamily: 'Verdana, sans-serif',
                        textShadow: '0 0 3px black'
                    }
                }
            }]
        });
    }
    function search() {

        var charttype = $("#select0").val();
        $('#chart').empty();
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

            var root_path = $("#root_path").val();
            $.post(root_path + '/onlinetime/Onlinetime_viewChartAjax.do', {
                chartType: charttype,
                startTime: starttime,
                endTime: endtime
            }, function (data) {
                mingcheng = data.dataName;
                shuzhi = data.dataPoint;
                if (charttype == "kaijishichang") {
                    biaotou = '开机时长统计图';
                    danwei = '时间(单位：小时)';
                    geshi = '开机时长: <b>{point.y:.2f} 小时</b>';

                }
                else if (charttype == "kaijilv") {
                    biaotou = '开机率统计图';
                    danwei = '开机率(单位:%)';
                    geshi = '开机率: <b>{point.y:.2f}%</b>';
                }
                else if (charttype == "huoyuedu") {
                    biaotou = '活跃度统计图';
                    danwei = '活跃度';
                    geshi = '活跃度: <b>{point.y:.2f}</b>';
                }
                tubiao();
            }, 'json');
        } else {
            layer.msg("请选择正确的时间", 1);
        }
    }

</script>
</html>