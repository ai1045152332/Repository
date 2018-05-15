<%@ page import="com.honghe.recordhibernate.entity.DeviceLog" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.honghe.recordhibernate.dao.Pager" %>
<%@ taglib prefix="s" uri="/struts-tags" %>-
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
    <title>软件监控统计</title>
    <link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/index.css" rel="stylesheet" type="text/css"/>

    <script src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/autoheight.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/perfect-scrollbar.with-mousewheel.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/prettify.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/checkbox_res.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/selectmore.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
    <!--layerdate-->
    <script src="${pageContext.request.contextPath}/js/common/laydate/laydate.js"></script>
</head>

<body>
<div class="public">
    <jsp:include page="/pages/frontend/header.jsp"></jsp:include>
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

        .table_recycle {
            background: none;
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

        #linkpage {
            position: absolute;
            bottom: 25px;
            left: 55%;
            text-align: center
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
            background: url("${pageContext.request.contextPath}/image/frontend/n_icon_141006.png") no-repeat scroll 0px -458px transparent;
        }

        @media (min-height: 684px) and (max-height: 900px) {
            .deviceloginput, .laydate-icon, #selectdivall0, .logsearch {
                margin-top: 5px;
            }
        }

    </style>
    <div class="res floatleft">
        <div class="res_head floatleft">
            <div class="loghead_skew">
                <div style="width: 70px;float: left;line-height: 33px;margin-left: 20px;">设备名称：</div>
                <input type="text" class="deviceloginput" id="deviceName">

                <div style="width: 85px;float: left;line-height: 33px;">时间段：</div>
                <div class="win360_content_date">
                    <input id="start" class="laydate-icon">
                    <input type="hidden" value="" id="startTime">
                </div>
                <div style="width: 25px;display: block; float: left; text-align: center;line-height: 33px;">至</div>
                <div class="win360_content_date">
                    <input id="end" class="laydate-icon">
                    <input type="hidden" value="" id="endTime">
                </div>
                <%-- <div style="width: 70px;float: left;line-height: 33px;text-align: center;margin-left: 30px;">日志类型：</div>
                 <div class="sall">
                  <select class="select" id="select0" >
                     <option value="ALL" selected="selected">全部信息</option>
                     <%
                       List<Object[]> logtypeList = (List<Object[]>)request.getAttribute("deviceLogtypeList");
                       if(logtypeList != null && logtypeList.size()>0){
                         for(int i=0;i<logtypeList.size();i++){
                     %>
                           <option value="<%=logtypeList.get(i)[0]%>" ><%=logtypeList.get(i)[1]%></option>
                     <%
                         }

                       }
                     %>
                   </select>
                   <div class="selectdivall" id="selectdivall0">
                     <div class="selectdiv" id="selectdiv0"></div>
                     <div class="selectdivul" id="selectdivul0" style="height: <%=(logtypeList.size()+1)*36%>px;max-height: 400px;overflow: auto"></div>
                   </div>
                 </div>--%>
                <div class="logsearch" onclick="search()">搜索</div>
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
        <div class="res_center floatleft">

            <s:hidden name="hostNames" id="hostNames"></s:hidden>
            <s:hidden name="hostMacs" id="hostMacs"></s:hidden>


            <div class="wid_100 floatleft hei_7 lh300" style="background: #28B779;color: #fff;text-align: center">
                <div class="wid_15 floatleft">设备名称</div>
                <div class="wid_15 floatleft">物理地址</div>
                <div class="wid_20 floatleft">软件名</div>
                <div class="wid_10 floatleft">使用次数</div>
                <div class="wid_16 floatleft">操作时间</div>
            </div>
            <div id="loglist" class="wid_100 floatleft hei_93">

                <div class="wid_100 floatleft hei_99" style="margin-top: 1%;">


                    <s:iterator value="#monitorList">
                        <div class="wid_100 floatleft hei_7 lh300"
                             style="margin-top: 0.25%; background: white;text-align: center">
                            <div class="wid_15 floatleft hei_100">${deviceName}<!--设备名称--></div>
                            <div class="wid_15 floatleft hei_100">${mac}<!--物理地址--></div>
                            <div class="wid_20 floatleft hei_100">${softName}<!--软件名--></div>
                            <div class="wid_10 floatleft hei_100">${usetime}<!--使用次数--></div>
                            <div class="wid_24 floatleft hei_100">保留<!--时间段--></div>
                            <div class="wid_16 floatleft hei_100">${createTime}<!--操作时间-->    </div>

                        </div>

                    </s:iterator>

                </div>
                <link href="${pageContext.request.contextPath}/css/frontend/hpager.css" rel="stylesheet"
                      type="text/css"/>
                <%
                    int pageCount = Integer.parseInt(request.getAttribute("pageCount").toString());
                    Integer currentpage = Integer.parseInt(request.getAttribute("currentPage").toString());
                    Pager pager = new Pager(pageCount, currentpage, 3, "<span class='pages_prevpage'></span>", "<span class='pages_nextpage'></span>", "", "", false);
                    // Pager pager = new Pager(hostcount,currentpage);
                    String pagers = pager.run();
                %>
                <div id="linkpage">
                    <%=pagers%>
                </div>
            </div>
        </div>
    </div>
    <div class="foot">
        <jsp:include page="/pages/frontend/footer.jsp"></jsp:include>
    </div>

</div>
</body>
<script>
   $(function(){
       $("#laydate_clear").live("click",function(){
           $("#endTime,#startTime").val("");
           $("#start,#end").val("")
       })
   })
    /*  (function($) {
     $.extend({
     myTime: {
     *//**
     * 当前时间戳
     * @return <int>        unix时间戳(秒)
     *//*
     CurTime: function(){
     return Date.parse(new Date())/1000;
     },
     *//**
     * 日期 转换为 Unix时间戳
     * @param <string> 2014-01-01 20:20:20  日期格式
     * @return <int>        unix时间戳(秒)
     *//*
     DateToUnix: function(string) {
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
     },
     *//**
     * 时间戳转换日期
     * @param <int> unixTime    待时间戳(秒)
     * @param <bool> isFull    返回完整时间(Y-m-d 或者 Y-m-d H:i:s)
     * @param <int>  timeZone   时区
     *//*
     UnixToDate: function(unixTime, isFull, timeZone) {
     if (typeof (timeZone) == 'number')
     {
     unixTime = parseInt(unixTime) + parseInt(timeZone) * 60 * 60;
     }
     var time = new Date(unixTime * 1000);
     var ymdhis = "";
     ymdhis += time.getUTCFullYear() + "-";
     ymdhis += (time.getUTCMonth()+1) + "-";
     ymdhis += time.getUTCDate();
     if (isFull === true)
     {
     ymdhis += " " + time.getUTCHours() + ":";
     ymdhis += time.getUTCMinutes() + ":";
     ymdhis += time.getUTCSeconds();
     }
     return ymdhis;
     }
     }
     });
     })(jQuery);*/
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
    /**
     * 获取url地址中的参数
     */
    function urloption(url) {
        // alert(url);
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
                $("#loglist").html(data);
            }, "html");
        }
    }

    function search() {
        var starttime = $("#startTime").val();
        var endtime = $("#endTime").val();
        var deviceName = $("#deviceName").val();
        var hostName = $("#hostNames").val().split(",");
        var hostMac = $("#hostMacs").val().split(",");
        var mac = "60:02:92:38:A9:8C";
        if (jQuery.trim(deviceName) != "") {
            for (var i = 0; i < hostName.length; i++) {
                if (deviceName == hostName[i]) {
                    mac = hostMac[i];
                }
            }
        }


        //dltype = encodeURI(dltype);
        if ((starttime!="" && endtime!="")||(starttime=="" && endtime=="")) {
            var root_path = $("#root_path").val();
            $.post(root_path + '/monitor/Monitor_getLivenessByHost.do', {
                mac: mac,
                startTime: starttime,
                endTime: endtime
            }, function (data) {
                $("#loglist").html(data);
            }, 'html');
        } else {
            layer.msg("请选择正确的时间", 1);
        }
    }
    /**
     * ajax分页
     */
    $("#linkpage li a").live("click", function () {
        //alert("aa");
        var href = $(this).attr("href");
        $.get(href, {}, function (data) {
            $("#loglist").html(data);
        }, "html");
        return false;
    });
    $(function () {
        var totalpagesize = "<%=pageCount%>";
        //分页调整
        var page = totalpagesize;
        var html = "<span style='float: left;margin-left: 2px;'>&nbsp;&nbsp;共" + page + "页 &nbsp;&nbsp;跳转到 " + "<input id=\"jump\" type=text style=\"width:20px;background:#d7dade;border:1px solid #5e6470\" title=\"点击回车即可跳转分页\" onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'')\" onafterpaste=\"this.value=this.value.replace(/[^0-9]/g,'')\"/> 页<span>";
        $(".yiiPager").append(html)
        left = -(18) * 28 / 2 + "px";
        $("#linkpage").css({"margin-left": left, "width": (17) * 28 + "px"});
        $(document).keydown(function (event) {
            //判断当event.keyCode 为37时（即左方面键），执行函数to_left();
            //判断当event.keyCode 为39时（即右方面键），执行函数to_right();
            if (event.ctrlKey || event.which == 13) {
                //alert("aaa")
                var jumpval = $("#jump").val();
                var patrn = /^[0-9]*$/;
                if(!patrn.exec(jumpval)){
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
        })

    })
</script>

</html>