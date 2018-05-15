<%@ page import="com.honghe.recordhibernate.entity.DeviceLog" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.honghe.recordhibernate.dao.Pager" %>
<%@ page import="com.honghe.recordweb.action.frontend.report.ReportDTO" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.ParsePosition" %>
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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
    <title>数据统计 | 集控平台</title>
    <link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/frontend/index.css" rel="stylesheet" type="text/css" />

    <script src="${pageContext.request.contextPath}/js/common/jquery-1.8.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/echarts.common.min.js"></script>
</head>

<body>
<div class="public">
    <jsp:include page="/pages/frontend/lb_header.jsp"></jsp:include>
    <style>
        .data-title {
            margin-left: 3%;
            margin-top: 15px;
            margin-bottom: 10px;
        }
        .data-title img, .data-title > span {
            display: block;
            float: left;
            line-height: 27px;
        }
        .data-title > span {
            margin-left: 10px;
        }
    </style>
    <div class="mm floatleft">

        <div class="user" style="margin-left: 3%;min-width: 0;width: 44%;">
            <div class="data-title">
                <%
                    int pageCount = Integer.parseInt(request.getAttribute("pageCount").toString());
                %>
                <img src="/image/frontend/data-device.png" > <span>设备统计</span>
                <%
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    String currentTime = sdf.format(new Date());
                %>
                <img src="/image/frontend/data-time.png" style="margin-left: 30px;"> <span class="data-time"><%=currentTime%></span>
                <div style="height: 0; line-height: 0; clear: both;"></div>
            </div>
            <div style="height: 502px;border: 1px solid #ddd;" id="devicelog">
                <%--<iframe src="/pages/frontend/report/devicelog.jsp" />--%>
                <jsp:include page="/pages/frontend/report/devicelog.jsp"></jsp:include>
            </div>
        </div>
        <div class="floatleft" style="margin-left: 3%; width: 46%;">
            <div class="data-title">
                <img src="/image/frontend/data-user.png" > <span>用户统计</span>
                <%
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd ");
                    Calendar c = Calendar.getInstance();
                    c.add(Calendar.DATE, -7);
                    String startDay = format.format(c.getTime());
                    String endDay = format.format(new Date());
                %>
                <img src="/image/frontend/data-times.png" style="margin-left: 30px;"> <span class="data-time">七日内总排行&nbsp;&nbsp;&nbsp;<%=startDay%> 至 <%=endDay%></span>
                <div style="height: 0; line-height: 0; clear: both;"></div>
            </div>
            <div id="main" style="width: 100%;height:500px;border: 1px solid #ddd; padding-top: 5px;"></div>
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
    /**
     * 获取url地址中的参数
     */
    <%--function urloption(url){--%>

        <%--var totalpagesize = "<%=pageCount%>";--%>
        <%--//分页调整--%>
        <%--var page=totalpagesize;--%>
        <%--if(url.indexOf("?")!=-1){--%>
            <%--var p=url.indexOf("?"); //返回所在位置--%>
            <%--var host="http://"+window.location.host+url.substr(0,p+1);--%>
            <%--var str = url.substr(p+1) //从这个位置开始截取--%>
            <%--strs = str.split("&"); //拆分--%>
            <%--var jumpval=$("#jump").val();--%>
            <%--var patrn = /^[0-9]*$/;--%>
            <%--if(!patrn.exec(jumpval) || jumpval<=0){--%>
                <%--jumpval=1;--%>
            <%--}else if(parseInt(jumpval)>=parseInt(totalpagesize)){--%>
                <%--jumpval=totalpagesize--%>
            <%--}--%>
            <%--var newurl=host;--%>
            <%--for(var i=0;i<strs.length;i++){--%>
                <%--if(i==0){--%>
                    <%--newurl+=strs[i].split("=")[0]+"="+jumpval+"&";--%>
                <%--}else{--%>
                    <%--newurl+=strs[i].split("=")[0]+"="+unescape(strs[i].split("=")[1])+"&";--%>

                <%--}--%>
            <%--}--%>
            <%--var urllen=newurl.length-1;--%>
            <%--var newurl = newurl.substr(0,urllen) //从这个位置开始截取--%>
            <%--console.log("url: " +  newurl);--%>
            <%--newurl =  newurl.indexOf('deviceLogList') > 0 ? newurl.replace('deviceLogList', 'deviceLogListAjax') : newurl;--%>
            <%--console.log('****' + $(this));--%>
<%--//            var className = $(this).parent('li').attr('class');--%>
            <%--$.get(newurl,{},function(data){--%>
                <%--var reportDTOs = data.reportDTOs;--%>
                <%--$('#loglist').find('.table_recycle').empty();--%>
                <%--var str = '';--%>
                <%--$.each(reportDTOs, function(i, v) {--%>
                    <%--str += '<div class="wid_10 floatleft hei_100">' + (i+1) + '</div>';--%>
                    <%--str += '<div class="wid_30 floatleft hei_100">' + v.groupName + '</div>';--%>
                    <%--str += '<div class="wid_30 floatleft hei_100">' + v.count + '</div>';--%>
                    <%--str += '<div class="wid_30 floatleft hei_100">' + v.rate + '</div>';--%>
                <%--})--%>
                <%--$('#loglist').find('.table_recycle').append(str);--%>
                <%--var currentPage = newurl.substring(newurl.indexOf('currentPage')+('currentPage=').length);--%>
                <%--console.log("page : " + currentPage);--%>

                <%--$('.yiiPager').find('li').each(function() {--%>
                    <%--if ($(this).hasClass('selected') && $(this).text() != currentPage) {--%>
<%--//                        if (className.trim() != 'next' && className.trim() != 'previous') {--%>
                        <%--var page = $(this).text();--%>
                        <%--$(this).removeClass('selected');--%>
                        <%--$(this).addClass('page');--%>
                        <%--$(this).empty();--%>
                        <%--$(this).append('<a href="/report/Report_deviceLogList.do?currentPage=' + page + '">' + page + '</a>');--%>
                    <%--}--%>

                    <%--if ($(this).text() == currentPage) {--%>
                        <%--$(this).removeClass('page');--%>
                        <%--$(this).addClass('selected');--%>
                        <%--$(this).text(currentPage);--%>
<%--//                        return false;--%>
                    <%--}--%>



<%--//                            that.parent('li').removeClass('page');--%>
<%--//                            that.parent('li').addClass('selected');--%>
<%--//                            that.parent('li').text(currentPage);--%>
<%--//                            that.remove();--%>
<%--//--%>
<%--//                            if (currentPage != totalpagesize) {--%>
<%--//                                next.empty();--%>
<%--//                                next.append('<a href="/report/Report_deviceLogList.do?currentPage=' + (parseInt(currentPage) + 1) + '"><span class="pages_nextpage"></span></a>');--%>
<%--//                            }--%>
<%--//                            prev.empty();--%>
<%--//                            if (currentPage != 1) {--%>
<%--//                                prev.append('<a href="/report/Report_deviceLogList.do?currentPage=' + (parseInt(currentPage) - 1) + '"><span class="pages_prevpage"></span></a>');--%>
<%--//                            } else {--%>
<%--//                                prev.append('<span class="pages_prevpage"></span>');--%>
<%--//                            }--%>
<%--//                        } else if (className.trim() == 'next') {--%>
<%--//                            var page = $(this).text();--%>
<%--//                            $(this).removeClass('selected');--%>
<%--//                            $(this).addClass('page');--%>
<%--//                            $(this).empty();--%>
<%--//                            $(this).append('<a href="/report/Report_deviceLogList.do?currentPage=' + page + '">' + page + '</a>');--%>
<%--//                            $(this).next('li').removeClass('page');--%>
<%--//                            $(this).next('li').addClass('selected');--%>
<%--//                            $(this).next('li').text(currentPage);--%>
<%--//                            if (currentPage != totalpagesize) {--%>
<%--//                                that.remove();--%>
<%--//                                next.empty();--%>
<%--//                                next.append('<a href="/report/Report_deviceLogList.do?currentPage=' + (parseInt(currentPage) + 1) + '"><span class="pages_nextpage"></span></a>');--%>
<%--//                            }--%>
<%--//                            prev.empty();--%>
<%--//                            if (currentPage != 1) {--%>
<%--//                                prev.append('<a href="/report/Report_deviceLogList.do?currentPage=' + (parseInt(currentPage) - 1) + '"><span class="pages_prevpage"></span></a>');--%>
<%--//                            } else {--%>
<%--//                                prev.append('<span class="pages_prevpage"></span>');--%>
<%--//                            }--%>
<%--//                        } else if (className.trim() == 'previous') {--%>
<%--//                            var page = $(this).text();--%>
<%--//                            $(this).removeClass('selected');--%>
<%--//                            $(this).addClass('page');--%>
<%--//                            $(this).empty();--%>
<%--//                            $(this).append('<a href="/report/Report_deviceLogList.do?currentPage=' + page + '">' + page + '</a>');--%>
<%--//                            $(this).prev('li').removeClass('page');--%>
<%--//                            $(this).prev('li').addClass('selected');--%>
<%--//                            $(this).prev('li').text(currentPage);--%>
<%--//                            that.remove();--%>
<%--//                            if (currentPage != totalpagesize) {--%>
<%--//                                next.empty();--%>
<%--//                                next.append('<a href="/report/Report_deviceLogList.do?currentPage=' + (parseInt(currentPage) + 1) + '"><span class="pages_nextpage"></span></a>');--%>
<%--//                            }--%>
<%--//                            prev.empty();--%>
<%--//                            if (currentPage != 1) {--%>
<%--//                                prev.append('<a href="/report/Report_deviceLogList.do?currentPage=' + (parseInt(currentPage) - 1) + '"><span class="pages_prevpage"></span></a>');--%>
<%--//                            } else {--%>
<%--//                                prev.append('<span class="pages_prevpage"></span>');--%>
<%--//                            }--%>
<%--//                        }--%>
<%--//                    }--%>
                <%--})--%>
            <%--},"json");--%>
        <%--}--%>
    <%--}--%>


    function urloption(url){
        // alert(url);
        var totalpagesize = $("#pageCount").html();
        if(url.indexOf("?")!=-1){
            var p=url.indexOf("?"); //返回所在位置
            var host="http://"+window.location.host+url.substr(0,p+1);
            var str = url.substr(p+1) //从这个位置开始截取
            strs = str.split("&"); //拆分
            var jumpval=$("#jump").val();
            var patrn = /^[0-9]*$/;
            if(!patrn.exec(jumpval) || jumpval<=0){
                jumpval=1;
            }else if(parseInt(jumpval)>=parseInt(totalpagesize)){

                jumpval=totalpagesize
            }
            var newurl=host;
            for(var i=0;i<strs.length;i++){
                if(i==0){
                    newurl+=strs[i].split("=")[0]+"="+jumpval+"&";
                }else{
                    newurl+=strs[i].split("=")[0]+"="+unescape(strs[i].split("=")[1])+"&";

                }
            }
            var urllen=newurl.length-1;
            var newurl = newurl.substr(0,urllen) //从这个位置开始截取
            if (newurl.indexOf('deviceLogListAjax') == -1) {
                newurl = newurl.replace('deviceLogList', 'deviceLogListAjax');
            }
            var total = "<%=pageCount%>";
            //分页调整
            var page=total;
            $.get(newurl,{},function(data){
                $('#devicelog').html('');
                $('#devicelog').html(data);
                var html="<span>&nbsp;&nbsp;共"+page+"页 &nbsp;&nbsp;跳转到 "+"<input id=\"jump\" type=text style=\"width:20px;background:#d7dade;border:1px solid #5e6470\" title=\"点击回车即可跳转分页\" onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'')\" onkeydown=\"jump(event)\" onafterpaste=\"this.value=this.value.replace(/[^0-9]/g,'')\"/> 页<span>";
                $(".yiiPager").append(html)
            },"html");
        }
    }

    function search(){
        var logtype = $("#select0").val();
        var starttime = $("#start").val()+" 00:00:00";
        var endtime = $("#end").val()+" 23:59:59";
        var ip = $("#ipaddr").val();
        starttime=DateToUnix(starttime);
        endtime = DateToUnix(endtime);
        if(starttime<0 && endtime<0) {
            starttime = 0;
            endtime=0
        }
        //dltype = encodeURI(dltype);
        if(starttime>=0 && endtime>=0){
            var root_path = $("#root_path").val();
            $.post(root_path+'/syslog/SysLog_deviceLogList.do',{logType:logtype,startTime:starttime,endTime:endtime,ip:ip},function(data){
                $("#loglist").html(data);
            },'html');
        }else{
            layer.msg("请选择正确的时间",1);
        }
    }
    function jump(event){
        //判断当event.keyCode 为37时（即左方面键），执行函数to_left();
        //判断当event.keyCode 为39时（即右方面键），执行函数to_right();
        if(event.keyCode == 13){
            //alert("aaa")
            var jumpval=$("#jump").val();
            var patrn = /^[0-9]*$/;
            if(!patrn.exec(jumpval)){
                return false;
            }else{
                var lilen=$("#linkpage ul li").length-1;
                var prevhref=$("#linkpage ul li").eq(0).find("a").attr("href");
                var nexthref=$("#linkpage ul li").eq(lilen).find("a").attr("href");
                var thisurl="";
                if(prevhref==""&&nexthref==""){
                    return false;
                }else if(prevhref==""||prevhref==undefined){
                    thisurl=nexthref;
                    urloption(thisurl)
                }else if(nexthref==""||nexthref==undefined){
                    thisurl=prevhref;
                    urloption(thisurl)
                }else{
                    thisurl=nexthref;
                    urloption(thisurl)
                }
            }
        }
    }

    /**
     * ajax分页
     */
    <%--$("#linkpage li a").live("click",function(){--%>
        <%--var that = $(this);--%>
        <%--var className = $(this).parent('li').attr('class');--%>
        <%--var href = $(this).attr("href");--%>
        <%--var newHref =  href.indexOf('deviceLogList') > 0 ? href.replace('deviceLogList', 'deviceLogListAjax') : href;--%>
        <%--var next = $('#linkpage').find('.next');--%>
        <%--var prev = $('#linkpage').find('.previous');--%>
        <%--var totalpagesize = "<%=pageCount%>";--%>

        <%--$.get(newHref,{},function(data){--%>
            <%--var reportDTOs = data.reportDTOs;--%>
            <%--$('#loglist').find('.table_recycle').empty();--%>
            <%--$.each(reportDTOs, function(i, v) {--%>
                <%--$('#loglist').find('.table_recycle').each(function (item) {--%>
                    <%--if (i == item) {--%>
                        <%--var str = '';--%>
                        <%--str += '<div class="wid_10 floatleft hei_100">' + (i+1) + '</div>';--%>
                        <%--str += '<div class="wid_30 floatleft hei_100">' + v.groupName + '</div>';--%>
                        <%--str += '<div class="wid_30 floatleft hei_100">' + v.count + '</div>';--%>
                        <%--str += '<div class="wid_30 floatleft hei_100">' + v.rate + '</div>';--%>
                        <%--$(this).append(str);--%>
                        <%--return false;--%>
                    <%--}--%>
                <%--})--%>

<%--//                str += '<div class="wid_10 floatleft hei_100">' + (i+1) + '</div>';--%>
<%--//                str += '<div class="wid_30 floatleft hei_100">' + v.groupName + '</div>';--%>
<%--//                str += '<div class="wid_30 floatleft hei_100">' + v.count + '</div>';--%>
<%--//                str += '<div class="wid_30 floatleft hei_100">' + v.rate + '</div>';--%>
<%--//                $('#loglist').find('.table_recycle').append(str);--%>
            <%--})--%>

<%--//            $('#loglist').find('.table_recycle').append(str);--%>
            <%--var currentPage = newHref.substring(newHref.indexOf('currentPage')+('currentPage=').length);--%>

            <%--$('.yiiPager').find('li').each(function() {--%>
                <%--if ($(this).hasClass('selected') && $(this).text() != currentPage) {--%>
                    <%--if (className.trim() != 'next' && className.trim() != 'previous') {--%>
                        <%--var page = $(this).text();--%>
                        <%--$(this).removeClass('selected');--%>
                        <%--$(this).addClass('page');--%>
                        <%--$(this).empty();--%>
                        <%--$(this).append('<a href="/report/Report_deviceLogList.do?currentPage='+page+'">'+ page + '</a>');--%>
                        <%--that.parent('li').removeClass('page');--%>
                        <%--that.parent('li').addClass('selected');--%>
                        <%--that.parent('li').text(currentPage);--%>
                        <%--that.remove();--%>

                        <%--if (currentPage != totalpagesize) {--%>
                            <%--next.empty();--%>
                            <%--next.append('<a href="/report/Report_deviceLogList.do?currentPage='+(parseInt(currentPage)+1)+'"><span class="pages_nextpage"></span></a>');--%>
                        <%--}--%>
                        <%--prev.empty();--%>
                        <%--if (currentPage != 1) {--%>
                            <%--prev.append('<a href="/report/Report_deviceLogList.do?currentPage='+(parseInt(currentPage)-1)+'"><span class="pages_prevpage"></span></a>');--%>
                        <%--} else {--%>
                            <%--prev.append('<span class="pages_prevpage"></span>');--%>
                        <%--}--%>
                    <%--} else if (className.trim() == 'next') {--%>
                        <%--var page = $(this).text();--%>
                        <%--$(this).removeClass('selected');--%>
                        <%--$(this).addClass('page');--%>
                        <%--$(this).empty();--%>
                        <%--$(this).append('<a href="/report/Report_deviceLogList.do?currentPage='+page+'">'+ page + '</a>');--%>
                        <%--$(this).next('li').removeClass('page');--%>
                        <%--$(this).next('li').addClass('selected');--%>
                        <%--$(this).next('li').text(currentPage);--%>
                        <%--if (currentPage != totalpagesize) {--%>
                            <%--that.remove();--%>
                            <%--next.empty();--%>
                            <%--next.append('<a href="/report/Report_deviceLogList.do?currentPage='+(parseInt(currentPage)+1)+'"><span class="pages_nextpage"></span></a>');--%>
                        <%--}--%>
                        <%--prev.empty();--%>
                        <%--if (currentPage != 1) {--%>
                            <%--prev.append('<a href="/report/Report_deviceLogList.do?currentPage='+(parseInt(currentPage)-1)+'"><span class="pages_prevpage"></span></a>');--%>
                        <%--} else {--%>
                            <%--prev.append('<span class="pages_prevpage"></span>');--%>
                        <%--}--%>
                    <%--} else if (className.trim() == 'previous') {--%>
                        <%--var page = $(this).text();--%>
                        <%--$(this).removeClass('selected');--%>
                        <%--$(this).addClass('page');--%>
                        <%--$(this).empty();--%>
                        <%--$(this).append('<a href="/report/Report_deviceLogList.do?currentPage='+page+'">'+ page + '</a>');--%>
                        <%--$(this).prev('li').removeClass('page');--%>
                        <%--$(this).prev('li').addClass('selected');--%>
                        <%--$(this).prev('li').text(currentPage);--%>
                        <%--that.remove();--%>
                        <%--if (currentPage != totalpagesize) {--%>
                            <%--next.empty();--%>
                            <%--next.append('<a href="/report/Report_deviceLogList.do?currentPage='+(parseInt(currentPage)+1)+'"><span class="pages_nextpage"></span></a>');--%>
                        <%--}--%>
                        <%--prev.empty();--%>
                        <%--if (currentPage != 1) {--%>
                            <%--prev.append('<a href="/report/Report_deviceLogList.do?currentPage='+(parseInt(currentPage)-1)+'"><span class="pages_prevpage"></span></a>');--%>
                        <%--} else {--%>
                            <%--prev.append('<span class="pages_prevpage"></span>');--%>
                        <%--}--%>
                    <%--}--%>
                <%--}--%>
            <%--})--%>
        <%--},"json");--%>
        <%--return false;--%>
    <%--});--%>

    /**
     * ajax分页
     */
    $("#linkpage li a").live("click",function(){
        //alert("aa");
        var href = $(this).attr("href");
//        href =  href.indexOf('deviceLogList') > 0 ?
        if (href.indexOf('deviceLogListAjax') == -1) {
            href = href.replace('deviceLogList', 'deviceLogListAjax');
        }
//        alert(href.indexOf('deviceLogAjax'))
//        alert(href);
        var totalpagesize = "<%=pageCount%>";
        //分页调整
        var page=totalpagesize;

        $.get(href,{},function(data){
            $('#devicelog').html('');
            $('#devicelog').html(data);
            var html="<span>&nbsp;&nbsp;共"+page+"页 &nbsp;&nbsp;跳转到 "+"<input id=\"jump\" type=text style=\"width:20px;background:#d7dade;border:1px solid #5e6470\" title=\"点击回车即可跳转分页\" onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'')\" onkeydown=\"jump(event)\" onafterpaste=\"this.value=this.value.replace(/[^0-9]/g,'')\"/> 页<span>";
            $(".yiiPager").append(html)
        },"html");
        return false;
    });


    $(function(){
        var root_path = $("#root_path").val();
        $.post(root_path+'/onlinetime/Onlinetime_getCountRecordVedio.do', {} , function (data) {
           // var countRecordList = data.data;
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));

            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '一周录制次数排名',
                    textStyle: {
                        color: '#666',
                        fontSize: 14
                    },
                    left: 'center',
                },
                tooltip: {},
                legend: {
                    data:[' 录制次数']
                },
                xAxis: {
                    data:data.data[0],
                    axisLabel: {
                        rotate: "45",
                    },
                },
                color: ['#36c681'],
                yAxis: {
                    name: '单位:次'
                },
                series: [{
                    name: '次数',
                    type: 'bar',
                    data: data.data[1]
                }],
                grid:{//直角坐标系内绘图网格
                    show:true,//是否显示直角坐标系网格。[ default: false ]
                    borderColor:"#c45455",//网格的边框颜色
                    bottom:"20%" //
                },
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }, 'json');
        var totalpagesize = "<%=pageCount%>";
        //分页调整
        var page=totalpagesize;
        var html="<span>&nbsp;&nbsp;共"+page+"页 &nbsp;&nbsp;跳转到 "+"<input id=\"jump\" type=text style=\"width:20px;background:#d7dade;border:1px solid #5e6470\" title=\"点击回车即可跳转分页\" onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'')\" onkeydown=\"jump(event)\" onafterpaste=\"this.value=this.value.replace(/[^0-9]/g,'')\"/> 页<span>";
        $(".yiiPager").append(html)

        $(".wid_6,.wid_16,wid_10,.wid_36").css({"overflow":"hidden","text-overflow":"ellipsis","white-space":"nowrap"})
    })
</script>
<script src="${pageContext.request.contextPath}/js/common/selectmore.js"></script>
</html>