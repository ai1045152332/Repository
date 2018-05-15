<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="com.honghe.recordhibernate.dao.Pager" %>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="${pageContext.request.contextPath}">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
    <title>软件统计 | 集控平台</title>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
    <!--layerdate-->
    <script src="${pageContext.request.contextPath}/js/common/laydate/laydate.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/jquery.cookie.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/common/screendetail.js"></script>
<script src="${pageContext.request.contextPath}/js/common/scrolldemo.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/selectmore.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/perfect-scrollbar.with-mousewheel.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/prettify.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/common/colorpicker/js/colorpicker.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/colorpicker/js/eye.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/common/colorpicker/js/layout.js?ver=1.0.2"></script>

</head>
<body>	<input type="hidden" id="whichscroll"> 
<div class="public">
   






    <jsp:include page="/pages/frontend/header.jsp"></jsp:include>
    <link href="${pageContext.request.contextPath}/js/common/layer/skin/layer.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/js/common/layer/skin/layer.ext.css" rel="stylesheet"
          type="text/css"/>
 <style>
    .jkn_message_90 {
    width: 95%;
    margin: 0 auto;
    }
    .jkn_message_radio1 {
        margin-top: 10px;
    }
    .headr{
        margin-left: 20px;
    }
    .sall {
        margin-top: 10px;
    }
    #selectdivall0, #selectdivall1, #selectdivall2 {
        background: url("${pageContext.request.contextPath}/image/frontend/n_icon_141006.png") no-repeat scroll 0px -456px transparent;
        float: left;
        height: 36px;
        line-height: 36px;
        width: 131px;
    }
    .selectdivul {
        width: 115px;
    }
#selectdiv0{
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}
    #selectdivul0 a{
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }
    .chart_inputbtn{
        border-radius: 8px;
        background: #28b779;
        color: #fff;
        cursor: pointer;
        float: right;
        font-size: 14px;
        height: 30px;
        line-height: 30px;
        margin-top: 2px;
        text-align: center;
        width: 82px;
    }
    .selectdivul a {
        text-indent: 10px;
        width: 115px;
    }
    </style>

    <%--<input type="hidden" id = "softName" value="<%=softName%>">--%>
    <%--<input type="hidden" id = "softUse" value="<%=softUse%>">--%>
    <%--<s:hidden id="devicesName" value="%{devicesName}"></s:hidden>--%>
    <%--<s:hidden id="devicesUse" value="%{devicesUse}"></s:hidden>--%>
    <div class="mm floatleft">
        <div class="mm_head floatleft">
            <%--<a href=""><span class="mm_nogroup_option" style="margin-left: 20px;"><span class="mm_nogroup_icon"></span>返回</span></a>--%>

            <%--<div class="analysis_checkbtn"><a href="<%=ServletActionContext.getRequest().getContextPath() + "/monitor/Monitor_monitorSearch.do"%>">查看详细信息</a></div>--%>
        </div>
        <div class="scrollfather" id="mm_right_video">
						<div class="scrollson">
        <div class="chart_head">
            <div style="float:right;width:1000px">
                <div class="jkn_message_90" style="margin-left: 70px;width:auto">
                    <div class="jkn_message_radio1" style="width: auto">
                        <div class="headr">
                            选择要查询的设备
                        </div>
                        <div class="sall" style="margin-top: -8px;margin-left: 10px;">
                            <select  class="selectdivall0" id="select0">
                                <%
                                    List<Object[]> logtypeList = (List<Object[]>)request.getAttribute("monitorList");
                                    if(logtypeList != null && logtypeList.size()>0){
                                        for(int i=0;i<logtypeList.size();i++){
                                %>
                                <option title="<%=logtypeList.get(i)[1]%>" value="<%=logtypeList.get(i)[0]%>" ><%=logtypeList.get(i)[1]%> </option>
                                <%
                                        }

                                    }
                                %>
                            </select>

                            <div class="selectdivall" id="selectdivall0">
                                <div class="selectdiv" id="selectdiv0"></div>
                                <div class="selectdivul" id="selectdivul0" style="z-index: 11;"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="chart_inputbtn" onclick="search()" >确定</div>
                <div style="width: 85px;float: left;line-height: 33px;margin-left:120px;text-align: right">时间段：</div>
                <li class="laydate-icon chart_input" id="end" style="width:100px;float: right;overflow: hidden;"></li>
                <div class="chart_inputtxt" style="float: right">至</div>
                <li class="laydate-icon chart_input" id="start" style="width:100px;float: right;overflow: hidden;"></li>
            </div>


        </div>

                            <%
                                String softName = (String)request.getAttribute("softName");
                                String softUse = (String)request.getAttribute("softUse");
                            %>
                            <s:hidden id="softName" value="%{#softName}"></s:hidden>
                            <s:hidden id="softUse" value="%{#softUse}"></s:hidden>
        <div class="chart_canvas" style="float: left;height: 900px;position: relative;">
            <canvas id="canvaschart" width="100%" height="100%" >您的浏览器版本过低,请升级浏览器</canvas>
            <canvas id="canvaschart2" width="100%" height="100%" >您的浏览器版本过低,请升级浏览器</canvas>
            <canvas id="canvaschart3" width="100%" height="100%">您的浏览器版本过低,请升级浏览器</canvas>
        </div>
        </div>
		<div class="scroll_ymove">
		<div class="scroll_y" unorbind="unbind"></div>
		</div>
		<div class="scroll_xmove">
		<div class="scroll_x" unorbind="unbind"></div>
		</div>
	</div>
    </div>
    <div class="foot">
        <jsp:include page="/pages/frontend/footer.jsp"></jsp:include>
    </div>
</div>
<script>
$(function(){
	var vipt_videowid=$(".mm").width()
	$("#mm_right_video .scrollson").width(vipt_videowid)
	$("#mm_right_video").height($(".mm").height()*0.93)
	$("#mm_right_video").width($(".mm").width())

	$("#mm_right_video .scrollson").mouseover(function(){
		$("#whichscroll").val($.trim($(this).parent().attr("id")))
		if ((navigator.userAgent.match(/(iPhone|Android|iPad)/i))){
		var scrollfathter1=document.getElementById($.trim($(this).parent().attr("id")));
		scrollfathter1.addEventListener("touchstart", touchStart, false);
		scrollfathter1.addEventListener("touchmove", touchMove, false);
		scrollfathter1.addEventListener("touchend", touchEnd, false);
		}
	})
	scroll_y("mm_right_video","scrollson","scroll_y","scroll_ymove","scroll_x","scroll_xmove","","wheely","")
	$("#mm_right_video .scrollson").css("margin-top","0")
	$("#mm_right_video .scroll_y").css("top","0")

    $(".jkn_message_radio1").children(".headr").click(function () {
        $(this).children("span").addClass("bgred").removeClass("bgr")
        $(this).parents(".jkn_message_90").siblings().find(".jkn_message_radio1").children(".headr").children("span").addClass("bgr").removeClass("bgred")
        if ($(this).parents(".jkn_message_90").find(".weekradio").length != 1) {
            $(this).parents(".jkn_message_90").siblings().find(".weekradio").children(".headr").children("span").addClass("bgr").removeClass("bgred")
        }
    })
})
    var canvasheight =450;
    var canvaswidth = parseInt($(".public").width() * 0.60);
    $("#canvaschart").attr("width", canvaswidth + "px")
    $("#canvaschart").attr("height", canvasheight + "px")
   $("#canvaschart,#canvaschart2").css("margin-left",($(".public").width()-canvaswidth)/2+"px")
    var txtposition = canvasheight - 100 + 0.5
    //柱状图
    var canvas = document.getElementById("canvaschart");
    var canvastxt = canvas.getContext("2d");
    canvastxt.font = "12px 微软雅黑";
    canvastxt.fillStyle = "#333"
    canvastxt.fillText("活跃度", 0, 11)

    //纵坐标轴
    canvastxt.beginPath()
    canvastxt.moveTo(59.5, txtposition)
    canvastxt.lineTo(59.5, 0)
    canvastxt.strokeStyle = "#666666"
    canvastxt.stroke()
    //纵坐标轴箭头
    canvastxt.beginPath()
    canvastxt.moveTo(53.5, 7)
    canvastxt.lineTo(59.5, 0)
    canvastxt.lineTo(65.5, 7)
    canvastxt.strokeStyle = "#808080"
    canvastxt.stroke()
    //横坐标轴
    canvastxt.beginPath()
    canvastxt.moveTo(59.5, txtposition)
    canvastxt.lineTo((canvaswidth - 59.5), txtposition)
    canvastxt.strokeStyle = "#666666"
    canvastxt.stroke()
    //横坐标轴箭头
    canvastxt.beginPath()
    canvastxt.moveTo((canvaswidth - 65.5), (txtposition - 6))
    canvastxt.lineTo((canvaswidth - 59.5), (txtposition + 0))
    canvastxt.lineTo((canvaswidth - 65.5), (txtposition + 6))
    canvastxt.strokeStyle = "#808080"
    canvastxt.stroke()

    canvastxt.fillText("软件名称", (canvaswidth - 105.5), (txtposition - 15))
    //纵坐标轴值
    for (i = 1; i <= 10; i++) {
        var txt = (100 - 10 * (i - 1)) + "%";
        canvastxt.fillText(txt, 10, ((txtposition - 15) / 10 * i))
        canvastxt.beginPath()
        canvastxt.moveTo(59.5, txtposition - (txtposition - 15) / 10 * i + 0.5)
        canvastxt.lineTo(56.5, txtposition - (txtposition - 15) / 10 * i + 0.5)
        canvastxt.strokeStyle = "#808080"
        canvastxt.stroke()
        canvastxt.closePath()
    }
    //横坐标轴值
    var txtarray = $("#softName").val().split(",");
    //			var txtarray=["白板","PPT","Easinote","简易录播","精品录播","Word","Exce","智慧教室"];
    var canvastxtxposition = parseInt((canvaswidth - 100.5) / txtarray.length)
    for (i = 1; i <= txtarray.length; i++) {
        canvastxt.beginPath()
        var txtarr = txtarray[i - 1]
        for (j = 0; j < txtarr.length; j++) {
            canvastxt.fillText(txtarr[j], 100.5 + canvastxtxposition * (i - 1), (txtposition + 20.5 + j * 15))
        }
        canvastxt.closePath()
    }
    //活跃值
    var chart = $("#softUse").val().split(",");
    //			var chart=[15,12,50,65,70,15,12,50];
    for (i = 0; i < chart.length; i++) {
        canvastxt.beginPath()
        canvastxt.fillStyle = "#4db812"
        canvastxt.arc(100.5 + canvastxtxposition * i, (txtposition - (txtposition - 15) / 100 * parseInt(chart[i])), 5, 0, Math.PI * 2)
        canvastxt.fill()
        canvastxt.closePath()
        canvastxt.beginPath()
        canvastxt.moveTo(100.5 + canvastxtxposition * i, (txtposition - (txtposition - 15) / 100 * parseInt(chart[i])))
        canvastxt.lineTo(100.5 + canvastxtxposition * (i + 1), (txtposition - (txtposition - 15) / 100 * chart[i + 1]))
        canvastxt.strokeStyle = "#4db812"
        canvastxt.stroke()
        canvastxt.closePath()
    }

    $("#canvaschart2").attr("width", canvaswidth + "px")
    $("#canvaschart2").attr("height", canvasheight + "px")
    var txtposition = canvasheight - 100 + 0.5
    //柱状图
    var canvas = document.getElementById("canvaschart2");
    var canvastxt = canvas.getContext("2d");
    canvastxt.font = "12px 微软雅黑";
    canvastxt.fillStyle = "#333"
    canvastxt.fillText("总时长", 0, 11)

    //纵坐标轴
    canvastxt.beginPath()
    canvastxt.moveTo(59.5, txtposition)
    canvastxt.lineTo(59.5, 0)
    canvastxt.strokeStyle = "#666666"
    canvastxt.stroke()
    //纵坐标轴箭头
    canvastxt.beginPath()
    canvastxt.moveTo(53.5, 7)
    canvastxt.lineTo(59.5, 0)
    canvastxt.lineTo(65.5, 7)
    canvastxt.strokeStyle = "#808080"
    canvastxt.stroke()
    //横坐标轴
    canvastxt.beginPath()
    canvastxt.moveTo(59.5, txtposition)
    canvastxt.lineTo((canvaswidth - 59.5), txtposition)
    canvastxt.strokeStyle = "#666666"
    canvastxt.stroke()
    //横坐标轴箭头
    canvastxt.beginPath()
    canvastxt.moveTo((canvaswidth - 65.5), (txtposition - 6))
    canvastxt.lineTo((canvaswidth - 59.5), (txtposition + 0))
    canvastxt.lineTo((canvaswidth - 65.5), (txtposition + 6))
    canvastxt.strokeStyle = "#808080"
    canvastxt.stroke()

    canvastxt.fillText("软件名称", (canvaswidth - 105.5), (txtposition - 15))
    //纵坐标轴值
    for (i = 1; i <= 10; i++) {
        var txt = (100 - 10 * (i - 1))+"%";
        canvastxt.fillText(txt, 10, ((txtposition - 15) / 10 * i))
        canvastxt.beginPath()
        canvastxt.moveTo(59.5, txtposition - (txtposition - 15) / 10 * i + 0.5)
        canvastxt.lineTo(56.5, txtposition - (txtposition - 15) / 10 * i + 0.5)
        canvastxt.strokeStyle = "#808080"
        canvastxt.stroke()
        canvastxt.closePath()
    }
    //横坐标轴值
    var txtarray = $("#softName").val().split(",");
    //			var txtarray=["白板","PPT","Easinote","简易录播","精品录播","Word","Exce","智慧教室"];
    var canvastxtxposition = parseInt((canvaswidth - 100.5) / txtarray.length)
    for (i = 1; i <= txtarray.length; i++) {
        canvastxt.beginPath()
        var txtarr = txtarray[i - 1]
        for (j = 0; j < txtarr.length; j++) {
            canvastxt.fillText(txtarr[j], 100.5 + canvastxtxposition * (i - 1), (txtposition + 20.5 + j * 15))
        }
        canvastxt.closePath()
    }
    //活跃值
    var chart = $("#softUse").val().split(",");
    //			var chart=[15,12,50,65,70,15,12,50];
    for (i = 0; i < chart.length; i++) {
        canvastxt.beginPath()
        canvastxt.fillStyle = "#4db812"
        canvastxt.arc(100.5 + canvastxtxposition * i, (txtposition - (txtposition - 15) / 100 * parseInt(chart[i])), 5, 0, Math.PI * 2)
        canvastxt.fill()
        canvastxt.closePath()
        canvastxt.beginPath()
        canvastxt.moveTo(100.5 + canvastxtxposition * i, (txtposition - (txtposition - 15) / 100 * parseInt(chart[i])))
        canvastxt.lineTo(100.5 + canvastxtxposition * (i + 1), (txtposition - (txtposition - 15) / 100 * chart[i + 1]))
        canvastxt.strokeStyle = "#4db812"
        canvastxt.stroke()
        canvastxt.closePath()
    }

    var end = {
        elem: '#end',
        format: 'YYYY/MM/DD',
        min: '2015-01-01', //设定最小日期为当前日期
        max: laydate.now(), //最大日期
        istime: true,
        istoday: false,
        start: laydate.now(0, "YYYY/MM/DD"),
        choose: function (datas) {
            end.max = datas;
            //end.min = datas; //开始日选好后，重置结束日的最小日期
            //end.start = datas //将结束日的初始值设定为开始日
        }
    };
    var start = {
        elem: '#start',
        format: 'YYYY/MM/DD',
        min: '2015-01-01',
        max: laydate.now(),
        istime: true,
        istoday: false,
        start: laydate.now(0, "YYYY/MM/DD"),
        choose: function (datas) {
            start.min = datas; //开始日选好后，重置结束日的最小日期
            start.start = datas //将结束日的初始值设定为开始日
            //start.min = '2015-01-01 00:00:00';
            //start.max = datas; //结束日选好后，重置开始日的最大日期
        }
    };

    laydate(start);
    laydate(end);




    function changeoption(args) {
        //点击模拟下拉框，显示或隐藏内容
        $("#selectdiv" + args).click(function () {
            var display = $("#selectdivul" + args).css("display");//判断当前模拟下拉菜单的显示属性
            if (display == "none") {//隐藏，则显示
                $("#selectdivul" + args).css("display", "block");
//		$("#selectdivul"+args).find("a").eq(0).css("display","none");
            } else {//显示，则隐藏
                $("#selectdivul" + args).css("display", "none");
            }
        })

        /*   var selected = $("#select"+args).find("option:selected").selectedIndex();*/
        var selected = $("#select" + args).prop('selectedIndex');


        $("#selectdivul" + args).find("a").eq(selected).css({
            "color": "#fff",
            "background": "#28b779"
        }).siblings().css({"color": "#333", "background": "#fff"});
        //点击模拟a标签，重新选定原生select选项
        $("#selectdivul" + args + " a").click(function () {
            var index = $(this).index();//获取a标签的索引值
            $("#select" + args).find("option").eq(index).prop("selected", "selected");//根据a标签的索引值选择select原生的值
            $("#selectdivul" + args).find("a").eq(index).css({
                "color": "#fff",
                "background": "#28b779"
            }).siblings().css({"color": "#333", "background": "#fff"});
            $("#selectdivul" + args).css("display", "none");//选中模拟值后，隐藏该div
            $("#selectdiv" + args).text($("#select" + args).find("option").eq(index).text());//把选中的值赋值到模拟div默认值选项
//		alert("a")

        })

        $("#selectdivul" + args).find("a").mouseover(function () {
            //alert($(this).css("background-color"))
            if ($(this).css("background-color") == "#fff" || $(this).css("background-color") == "rgb(255, 255, 255)") {
                $(this).css({"background": "#dbdbdb", "color": "#333"})
            } else if ($(this).css("background-color") == "#28b779" || $(this).css("background-color") == "rgb(40, 183, 121)") {
                return false;
            }
        }).mouseout(function () {
            if ($(this).css("background-color") == "#333" || $(this).css("background-color") == "rgb(219, 219, 219)") {
                $(this).css({"background": "#fff", "color": "#333"})
            } else if ($(this).css("background-color") == "#28b779" || $(this).css("background-color") == "rgb(40, 183, 121)") {
                return false;
            }
        })
        //点击空白区域，隐藏select模拟
        $(document).bind("click", function (e) {
            var target = $(e.target);//获取点击时间
            if (target.closest("#selectdivall" + args).length == 0) {
                $("#selectdivul" + args).hide();//隐藏divli
            }
        })
    }
    function search() {
        var starttime = $("#start").text()+" 00:00:00";
        var endtime = $("#end").text()+" 23:59:59";
        var mac = $("#select0").val();

       //dltype = encodeURI(dltype);
       if ((starttime!="" && endtime!="")||(starttime=="" && endtime=="")) {
          var root_path = $("#root_path").val();
         $.post(root_path + '/monitor/Monitor_getMonitor.do', {
             mac: mac,
             startTime: starttime,
             endTime: endtime
         }, function (data) {
          //    $("#loglist").html(data);

//             var jsonobj=eval(data);
//             alert(jsonobj.softName);
//             alert(jsonobj.softUse);
             txtarray = data.success;
             chart =  data.msg;
             timer = data.str;
             refreshbrisk(txtarray,timer);
             changetime(txtarray,timer,chart);
           });
       } else {
           layer.msg("请选择正确的时间", 1);
       }

}
function refreshbrisk(txtarray,timer){
    var canvasheight =450;
    var canvaswidth = parseInt($(".public").width() * 0.60);
    $("#canvaschart").attr("width", canvaswidth + "px")
    $("#canvaschart").attr("height", canvasheight + "px")
    $("#canvaschart,#canvaschart2").css("margin-left",($(".public").width()-canvaswidth)/2+"px")
    var txtposition = canvasheight - 100 + 0.5
    //柱状图
    var canvas = document.getElementById("canvaschart");
    var canvastxt = canvas.getContext("2d");
    canvastxt.font = "12px 微软雅黑";
    canvastxt.fillStyle = "#333"
    canvastxt.fillText("活跃度", 0, 11)

    //纵坐标轴
    canvastxt.beginPath()
    canvastxt.moveTo(59.5, txtposition)
    canvastxt.lineTo(59.5, 0)
    canvastxt.strokeStyle = "#666666"
    canvastxt.stroke()
    //纵坐标轴箭头
    canvastxt.beginPath()
    canvastxt.moveTo(53.5, 7)
    canvastxt.lineTo(59.5, 0)
    canvastxt.lineTo(65.5, 7)
    canvastxt.strokeStyle = "#808080"
    canvastxt.stroke()
    //横坐标轴
    canvastxt.beginPath()
    canvastxt.moveTo(59.5, txtposition)
    canvastxt.lineTo((canvaswidth - 59.5), txtposition)
    canvastxt.strokeStyle = "#666666"
    canvastxt.stroke()
    //横坐标轴箭头
    canvastxt.beginPath()
    canvastxt.moveTo((canvaswidth - 65.5), (txtposition - 6))
    canvastxt.lineTo((canvaswidth - 59.5), (txtposition + 0))
    canvastxt.lineTo((canvaswidth - 65.5), (txtposition + 6))
    canvastxt.strokeStyle = "#808080"
    canvastxt.stroke()

    canvastxt.fillText("软件名称", (canvaswidth - 105.5), (txtposition - 15))
    //纵坐标轴值
    for (i = 1; i <= 10; i++) {
        var txt = (100 - 10 * (i - 1)) + "%";
        canvastxt.fillText(txt, 10, ((txtposition - 15) / 10 * i))
        canvastxt.beginPath()
        canvastxt.moveTo(59.5, txtposition - (txtposition - 15) / 10 * i + 0.5)
        canvastxt.lineTo(56.5, txtposition - (txtposition - 15) / 10 * i + 0.5)
        canvastxt.strokeStyle = "#808080"
        canvastxt.stroke()
        canvastxt.closePath()
    }
    //横坐标轴值
    var txtarray =txtarray.split(",");
    //			var txtarray=["白板","PPT","Easinote","简易录播","精品录播","Word","Exce","智慧教室"];
    var canvastxtxposition = parseInt((canvaswidth - 100.5) / txtarray.length)
    for (i = 1; i <= txtarray.length; i++) {
        canvastxt.beginPath()
        var txtarr = txtarray[i - 1]
        for (j = 0; j < txtarr.length; j++) {
            canvastxt.fillText(txtarr[j], 100.5 + canvastxtxposition * (i - 1), (txtposition + 20.5 + j * 15))
        }
        canvastxt.closePath()
    }
    //活跃值
    var chart =timer.split(",");
    //			var chart=[15,12,50,65,70,15,12,50];
    for (i = 0; i < chart.length; i++) {
        canvastxt.beginPath()
        canvastxt.fillStyle = "#4db812"
        canvastxt.arc(100.5 + canvastxtxposition * i, (txtposition - (txtposition - 15) / 100 * parseInt(chart[i])), 5, 0, Math.PI * 2)
        canvastxt.fill()
        canvastxt.closePath()
        canvastxt.beginPath()
        canvastxt.moveTo(100.5 + canvastxtxposition * i, (txtposition - (txtposition - 15) / 100 * parseInt(chart[i])))
        canvastxt.lineTo(100.5 + canvastxtxposition * (i + 1), (txtposition - (txtposition - 15) / 100 * parseInt(chart[i + 1])))
        canvastxt.strokeStyle = "#4db812"
        canvastxt.stroke()
        canvastxt.closePath()
    }

}
function changetime(txtarray,chart,time){
    $("#canvaschart2").attr("width", canvaswidth + "px")
    $("#canvaschart2").attr("height", canvasheight + "px")
    var txtposition = canvasheight - 100 + 0.5
    //柱状图
    var canvas = document.getElementById("canvaschart2");
    var canvastxt = canvas.getContext("2d");
    canvastxt.font = "12px 微软雅黑";
    canvastxt.fillStyle = "#333"
    canvastxt.fillText("总时长"+"("+time+")时", 0, 11)

    //纵坐标轴
    canvastxt.beginPath()
    canvastxt.moveTo(59.5, txtposition)
    canvastxt.lineTo(59.5, 0)
    canvastxt.strokeStyle = "#666666"
    canvastxt.stroke()
    //纵坐标轴箭头
    canvastxt.beginPath()
    canvastxt.moveTo(53.5, 7)
    canvastxt.lineTo(59.5, 0)
    canvastxt.lineTo(65.5, 7)
    canvastxt.strokeStyle = "#808080"
    canvastxt.stroke()
    //横坐标轴
    canvastxt.beginPath()
    canvastxt.moveTo(59.5, txtposition)
    canvastxt.lineTo((canvaswidth - 59.5), txtposition)
    canvastxt.strokeStyle = "#666666"
    canvastxt.stroke()
    //横坐标轴箭头
    canvastxt.beginPath()
    canvastxt.moveTo((canvaswidth - 65.5), (txtposition - 6))
    canvastxt.lineTo((canvaswidth - 59.5), (txtposition + 0))
    canvastxt.lineTo((canvaswidth - 65.5), (txtposition + 6))
    canvastxt.strokeStyle = "#808080"
    canvastxt.stroke()

    canvastxt.fillText("软件名称", (canvaswidth - 105.5), (txtposition - 15))
    //纵坐标轴值
    for (i = 1; i <= 10; i++) {
        var txt = (100 - 10 * (i - 1))+"%";
        canvastxt.fillText(txt, 10, ((txtposition - 15) / 10 * i))
        canvastxt.beginPath()
        canvastxt.moveTo(59.5, txtposition - (txtposition - 15) / 10 * i + 0.5)
        canvastxt.lineTo(56.5, txtposition - (txtposition - 15) / 10 * i + 0.5)
        canvastxt.strokeStyle = "#808080"
        canvastxt.stroke()
        canvastxt.closePath()
    }
    //横坐标轴值
    var txtarray = txtarray.split(",");
    //			var txtarray=["白板","PPT","Easinote","简易录播","精品录播","Word","Exce","智慧教室"];
    var canvastxtxposition = parseInt((canvaswidth - 100.5) / txtarray.length)
    for (i = 1; i <= txtarray.length; i++) {
        canvastxt.beginPath()
        var txtarr = txtarray[i - 1]
        for (j = 0; j < txtarr.length; j++) {
            canvastxt.fillText(txtarr[j], 100.5 + canvastxtxposition * (i - 1), (txtposition + 20.5 + j * 15))
        }
        canvastxt.closePath()
    }
    //活跃值
    var chart = chart.split(",");
    for (i = 0; i < chart.length; i++) {
        canvastxt.beginPath()
        canvastxt.fillStyle = "#4db812"
        canvastxt.arc(100.5 + canvastxtxposition * i, (txtposition - (txtposition - 15) / 100 *  parseInt(chart[i])), 5, 0, Math.PI * 2)
        canvastxt.fill()
        canvastxt.closePath()
        canvastxt.beginPath()
        canvastxt.moveTo(100.5 + canvastxtxposition * i, (txtposition - (txtposition - 15) / 100 *  parseInt(chart[i])))
        canvastxt.lineTo(100.5 + canvastxtxposition * (i + 1), (txtposition - (txtposition - 15) / 100 * chart[i + 1]))
        canvastxt.strokeStyle = "#4db812"
        canvastxt.stroke()
        canvastxt.closePath()
    }
}

</script>
</body>
</html>
