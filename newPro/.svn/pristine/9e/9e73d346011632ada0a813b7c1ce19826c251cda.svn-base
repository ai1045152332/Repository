<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="com.honghe.recordhibernate.dao.Page" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page import="com.honghe.recordhibernate.entity.News" %>
<%--
  Created by IntelliJ IDEA.
  User: Tpaduser
  Date: 2015/9/10
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
  <link href="${pageContext.request.contextPath}/css/frontend/hpager.css" rel="stylesheet"  type="text/css"/>
  <link href="${pageContext.request.contextPath}/js/common/layer/skin/layer.css" rel="stylesheet" type="text/css"/>
  <link href="${pageContext.request.contextPath}/js/common/layer/skin/layer.ext.css" rel="stylesheet" type="text/css"/>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/js/common/colorpicker/css/colorpicker.css" type="text/css" />
  <link href="${pageContext.request.contextPath}/css/frontend/fd-slider.css" rel="stylesheet" type="text/css"/>
  <link href="${pageContext.request.contextPath}/css/frontend/index.css" rel="stylesheet" type="text/css"/>
  <link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css"/>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
  <!--layerdate-->
  <script src="${pageContext.request.contextPath}/js/common/laydate/laydate.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/jquery.cookie.js" type="text/javascript"></script>
  <script src="${pageContext.request.contextPath}/js/common/tree_jkn_checkbox.js"></script>
   <script src="${pageContext.request.contextPath}/js/common/perfect-scrollbar.with-mousewheel.min.js"></script>
    <!--滚动条模拟码率等引用顺序不能改变-->
    <script src="${pageContext.request.contextPath}/js/common/prettify.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/fd-slider.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/scroll.js"></script>

  <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/colorpicker/js/colorpicker.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/colorpicker/js/eye.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/colorpicker/js/layout.js?ver=1.0.2"></script>
</head>
<body>
<%
    Map<String,Object> newsInfo = (Map<String,Object>)ActionContext.getContext().get("newsInfo");
    News news = (News)newsInfo.get("news");
    String Loop = news.getnLoop()==null?"":news.getnLoop().toString();
    String week = news.getnWeek()==null?"":news.getnWeek().toString();
    String month = news.getnMonth()==null?"":news.getnMonth().toString();
    String start = news.getnStart()==null?"":news.getnStart().toString();
    String finish = news.getnFinish()==null?"":news.getnFinish().toString();
    String startTime = news.getnBegintime()==null?"":news.getnBegintime().toString();
    String endTime = news.getnEndtime()==null?"":news.getnEndtime().toString();
    List<Object[]> hostIdList = (List<Object[]>)newsInfo.get("hostList");
    String hostIdStr = "";
    if(hostIdList != null && hostIdList.size()>0){
        for (int i = 0;i<hostIdList.size();i++){
            hostIdStr += hostIdList.get(i)[0]+",";
        }
    }
%>

<div class="win520_content" style="min-height: 354px;margin-top: 45px;background: #fff;">
	<div class="floatleft" style="width: 200px;min-height: 354px;border-right: 1px solid #dbdbdb;">
  <input type="hidden" value="" id="selected_host" ip="">

  <div class="equipment" style="text-indent: 0;text-align: center;height: 40px;">教室设备</div>

  <style>
  	 #selectdivall0 {
            background: url("${pageContext.request.contextPath}/image/frontend/n_icon_141006.png") no-repeat scroll 0px -456px transparent;
            float: left;
            height: 36px;
            line-height: 36px;
            width: 131px;
        }
    .selectdivul{
    	line-height: 25px;
    	height: 80px;
    	width: 115px;
    }
    .selectdivul a{
    	height: 25px;
    	line-height: 25px;
    }
    	.hostoverflow {
            float: left;
            text-overflow: ellipsis;
            overflow: hidden;
            margin-left: 25px;
            width: 150px;

        }
        .tree_titleaiconmain_jkn,.tree_titleaiconmained_jkn{
            position: absolute;
            left: 10px;
            top: 0px;
        }
        .hostoverflow {
				    float: left;
				    margin-left: 25px;
				    overflow: hidden;
				    text-overflow: ellipsis;
				    width: 150px;
				}
        .jkn_moresonmenu::before{
            border:1px solid #dbdbdb;
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
        .jkn_moresonmenu::after{
            background: #fff none repeat scroll 0 0;
            content: "";
            height: 8px;
            left: 34px;
            position: absolute;
            top: 0;
            width: 12px;
            z-index: -30;
        }
        .jkn_moresonmenu{
            border:1px solid #dbdbdb;
            border-radius: 3px;
            background: #fff;
            position: absolute;
            height: 96px;
            top:30px ;
            width: 78px;
            z-index: 1;
        }

  </style>
<div id="otp_vedeoabout_rvideo" style="height: 310px;">
    <div class='contentr'>
        <div class="tree">
            <a href="javascript:void(0)" class="tree_titleb tree_titleb_open">所有设备</a>
            <%
                List<Map> groupTreeList = (List<Map>) request.getAttribute("groupTree"); //获取分组数据和设备
                String prid =  (String)request.getAttribute("prid");
                Page pageNews = (Page)request.getAttribute("pageNews"); //获取信息列表
                String hostIdsFlag = (String)request.getAttribute("HOST_ID_FLAG");
                int pageCount = 0;
                List<Map> mapList = new LinkedList<Map>();
                //int pageNum = 0;
                if(pageNews != null)
                {
                    pageCount = pageNews.getTotalPageSize();
                    mapList = pageNews.getResult();
                    //pageNum = mapList.size();
                }
                String userId = request.getAttribute("userId").toString();
            %>
            <div class="public_left" style="float: left;">
                <%
                    if ( groupTreeList != null && groupTreeList.size() > 0)
                    {
                        for (Map groupTreeMap : groupTreeList)
                        {
                            String group_id = groupTreeMap.get("group_id").toString();
                            String group_name = groupTreeMap.get("group_name").toString();
                            //out.println("~~~~~~~~~~~~~~~group_id=" + group_id + "*******group_name=" + group_name);
                            List<Map> hostList = (List<Map>) groupTreeMap.get("host_list");
                %>
                <div class="tree_title tree_title_close ">
                    <span  class="tree_titlea" groupId = "<%=group_id%>" style="text-indent: 52px;"><%=group_name%></span>
                    <span class="tree_titleaiconmain_jkn"></span>
                    <div class="tree_content">
                    <%
                    if (!hostList.isEmpty()) {
                        for (Map host : hostList)
                        {
                            String host_id = host.get("id").toString();
                            String host_name = host.get("name").toString();
                            String statusStyle = "tree_content_onlinebg";
                            if (host.get("status").toString().equals("Offline")) {
                                statusStyle = "tree_content_nousebg";
                        }
                    %>
                        <div class="tree_contenta" hostId = "<%=host_id%>">
                        <span   class="<%=statusStyle%>" style="background: none;"></span>
                        <span class="hostoverflow"> <%=host_name%></span>
                        <div class="jkn_treecheckbox" hostId ="<%=host_id%>"></div>
                        </div>
                    <%
                        }
                    }
                    %>
                    </div>
                </div>
                <%
                    }
                }
                %>
            </div>
        </div>
    </div>
</div>
</div>
<div class="floatleft" style="width: 460px;overflow: hidden;margin-left: 10px;" id="rightheight">
		<div style="float: left;width: 100%;">
			<div class="jkn_message_text" >开始日期：</div>
			<div class="win360_content_date" style="margin-top: 12px;">
			    <div id="start" class="laydate-icon"></div>
			    <input type="hidden" value="" id="startTime">
			</div>
			<div class="jkn_message_text" style="margin-left: 10px;">结束日期：</div>
			<div class="win360_content_date" style="margin-top: 12px;">
			    <div id="end" class="laydate-icon"></div>
			    <input type="hidden" value="" id="endTime">
			</div>
		</div>
		<div style="float: left;width: 100%;">
    <div class="jkn_message_text" >策略选择：</div>
    <input type="hidden" id = "newsPolicyOld" policy="" week="" month="" start="" finish=""  />
    <div class="jkn_message_90">
        <div class="jkn_message_radio1">
            <div class="headr" >
                <span class="bgr" newsPolicy ="day"></span>每日固定时间
            </div>
        </div>
    </div>
    <div class="jkn_message_90">
        <div class="jkn_message_radio1">
            <div class="headr">
                <span class="bgr" newsPolicy ="week"></span>每周固定时间
            </div>
            <div class="weekradio">
                <div class="headr" >
                    <span class="bgr" newsWeek = "1"></span>周一
                </div>
                <div class="headr" >
                    <span class="bgr" newsWeek = "2"></span>周二
                </div>
                <div class="headr" >
                    <span class="bgr" newsWeek = "3"></span>周三
                </div>
                <div class="headr" >
                    <span class="bgr" newsWeek = "4" ></span>周四
                </div>
                <div class="headr" >
                    <span class="bgr" newsWeek = "5"></span>周五
                </div>
                <div class="headr" >
                    <span class="bgr" newsWeek = "6"></span>周六
                </div>
                <div class="headr" >
                    <span class="bgr" newsWeek = "7"></span>周日
                </div>
            </div>
        </div>
    </div>
    <div class="jkn_message_90">
        <div class="jkn_message_radio1">
            <div class="headr" >
                <span class="bgr" newsPolicy ="month"></span>每月固定时间
            </div>
            <div class="sall" style="margin-top: -8px;margin-left: 10px;">
                <select class="select" id="select0">
                    <%
                        for(int i =1;i<=31;i++){
                            %>
                            <option value="<%=i%>"><%=i%></option>
                            <%
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
    </div>
    <div style="float: left;width: 100%;">
    <div class="jkn_message_90 floatleft">
        <div class="jkn_message_text">时间选择：</div>
    </div>
    </div>
    <div class="jkn_message_90">
        <div class="jkn_message_timechoose" id="newsStart" timeBelong = "start">
            <div class="jkn_message_hour">小时</div>
            <div class="jkn_message_center">:</div>
            <div class="jkn_message_minutes">分钟</div>
            <div class="jkn_message_hourshow" id="newsStartHour"></div>
            <div class="jkn_message_minutesshow" id="newsStartMinutes"></div>
        </div>
        <div class="jkn_message_text" style="width: 30px;text-align: center;">至</div>
        <div class="jkn_message_timechoose" id="newsFinish" timeBelong = "finish">
            <div class="jkn_message_hour">小时</div>
            <div class="jkn_message_center">:</div>
            <div class="jkn_message_minutes">分钟</div>
            <div class="jkn_message_hourshow" id="newsFinishHour"></div>
            <div class="jkn_message_minutesshow" id="newsFinishMinutes"></div>
        </div>
    </div>
    </div>
</div>
</div>
<input type="text" style="display: none" id="newsParams" value="" newsId="<%=prid%>"
       urlhead="${pageContext.request.contextPath}"/>
<script src="${pageContext.request.contextPath}/js/common/selectmore.js"></script>
<script>
  //日期格式转换 2015-02-03 ===> 2015/02/03
  function parseDate(dateStr)
  {
    if(dateStr != null && dateStr != "")
    {
      var regEx = new RegExp("\\-","gi");
      dateStr=dateStr.replace(regEx,"/");
      var date = new Date(dateStr);
      return date;
    }
    return null;
  }
  //策略有效验证
  function policyVerify(beginDateStr,endDateStr,policyType,policyValue)
  {
    var result = 0; //0 为成功，1为周策略警告，2为月策略警告
    var showText = "";
    var beginDate= parseDate(beginDateStr);
    var endDate = parseDate(endDateStr);
    var date = endDate.getTime() - beginDate.getTime();
    var daysNum = Math.floor(date / (1000 * 60 * 60 * 24));
    var monthsNum = 12 * (endDate.getYear() - beginDate.getYear()) + endDate.getMonth() -  beginDate.getMonth();
    switch (policyType)
    {
      case "week" :
        if(daysNum < 7)
        {
          showText = '当前信息的设置的持续时间小于7天，不推荐按周设置策略'
          result = 1;
          //不到一周需要判断时间是否有效
        }
        break;
      case "month" :
        if(monthsNum < 1)
        {
          showText ='当前信息的设置的有效期在同一月内，不推荐按月设置策略';
          result = 2;
        }
        break;
      default :
        break;
    }
    return result;
  }
  //取消策略设置
  $(".win_btn_done").click(function(){
    $(".jkn_message_tacticsbg").hide()
  });
  $(function(){

    //发布方式
    $("#selectdivall1").append("<div style='color: red;position: relative;left:30px;top:-36px;display: none'>(默认)</div>");
    //时间选择
    //向小时div添加子元素
    for(i=0;i<24;i++){
      if(i<10){
        var html="<span>0"+i+"</span>";
      }else{
        var html="<span>"+i+"</span>";
      }

      if(i<10)
      {
        html="<span>0"+i+"</span>";
      }
      $(".jkn_message_hourshow").append(html)
    }
    //向分钟div添加子元素
    for(i=0;i<60;i++){

      if(i<10){
        var html="<span>0"+i+"</span>";
      }else{
        var html="<span>"+i+"</span>";
      }

      if(i<10)
      {
        html="<span>0"+i+"</span>";
      }
      $(".jkn_message_minutesshow").append(html)
    }
    //点击小时隐藏分钟子菜单，显示小时子菜单
    $(".jkn_message_hour").click(function(){
      $(this).parents(".jkn_message_timechoose").children(".jkn_message_minutesshow").hide()
      $(this).parents(".jkn_message_timechoose").children(".jkn_message_hourshow").show()
    })
    //点击分钟隐藏小时子菜单，显示分钟子菜单
    $(".jkn_message_minutes").click(function(){
      $(this).parents(".jkn_message_timechoose").children(".jkn_message_minutesshow").show()
      $(this).parents(".jkn_message_timechoose").children(".jkn_message_hourshow").hide()
    })
    //子菜单鼠经过效果
    $(".jkn_message_timechoose span").mouseover(function(){
      $(this).css("background","#ccc")
    }).mouseout(function(){
      $(this).css("background","#fff")
    })
    //点击小时子菜单元素赋值操作
    $(".jkn_message_hourshow span").click(function(){
      var tmpSTH = newsStartTimeHour;//保存选择前原值
      var tmpFTH = newsFinishTimeHour;//保存选择前原值
      var timeBelong =$(this).parents(".jkn_message_timechoose").attr("timeBelong");
      if(timeBelong == "start")
      {
        newsStartTimeHour = $(this).text();
      }
      else if(timeBelong == "finish")
      {
        newsFinishTimeHour = $(this).text();
      }
      if(newsFinishTimeHour != null && newsStartTimeHour != null)
      {
        try
        {
          if(parseInt(newsFinishTimeHour)<parseInt(newsStartTimeHour))
          {
            layer.msg("结束时间不能小于开始时间！");
            newsFinishTimeHour = tmpFTH; //选择时间失败，恢复原值
            newsStartTimeHour = tmpSTH;
            return;
          }
          else if(newsFinishTimeHour == newsStartTimeHour && (newsFinishTimeMinutes != null )&& (newsStartTimeMinutes != null)  &&(parseInt(newsFinishTimeMinutes)<parseInt(newsStartTimeMinutes)))
          {
            layer.msg("结束时间不能小于开始时间！");
            newsFinishTimeHour = tmpFTH;//选择时间失败，恢复原值
            newsStartTimeHour = tmpSTH;
            return;
          }
        }
        catch (err)
        {
          layer.msg("无效操作");
          newsFinishTimeHour = tmpFTH;//选择时间失败，恢复原值
          newsStartTimeHour = tmpSTH;
          return;
        }
      }
      $(this).parents(".jkn_message_timechoose").children(".jkn_message_hour").text($(this).text());

    })
    //点击分钟子菜单元素赋值操作
    $(".jkn_message_minutesshow span").click(function(){
      var tmpSTM= newsStartTimeMinutes;//保存原值
      var tmpFTM = newsFinishTimeMinutes;//保存原值
      var timeBelongM =$(this).parents(".jkn_message_timechoose").attr("timeBelong");
      if(timeBelongM == "start")
      {
        newsStartTimeMinutes = $(this).text();
      }
      else if(timeBelongM == "finish")
      {
        newsFinishTimeMinutes = $(this).text();
      }
      if(newsFinishTimeHour != null && newsFinishTimeMinutes != null && newsStartTimeHour != null && newsStartTimeMinutes != null)
      {
        try
        {
          if((parseInt(newsFinishTimeHour)==parseInt(newsStartTimeHour)) && (parseInt(newsFinishTimeMinutes) < parseInt(newsStartTimeMinutes)))
          {
            layer.msg("结束时间不能小于开始时间！");
            newsStartTimeMinutes = tmpSTM;//选择时间失败，恢复原值
            newsFinishTimeMinutes = tmpFTM;//选择时间失败，恢复原值
            return;
          }
        }
        catch (err)
        {
          layer.msg("无效操作");
          newsStartTimeMinutes = tmpSTM;//选择时间失败，恢复原值
          newsFinishTimeMinutes = tmpFTM;//选择时间失败，恢复原值
          return;
        }
      }
      $(this).parents(".jkn_message_timechoose").children(".jkn_message_minutes").text($(this).text());
    })

    //点击空白区域，隐藏操作项子菜单
    $(document).bind("click",function(e){
      var target = $(e.target);
      if(target.closest(".jkn_message_hour").length == 0){
        $(".jkn_message_hourshow").hide()
      }
      if(target.closest(".jkn_message_minutes").length == 0){
        $(".jkn_message_minutesshow").hide()
      }
    })

    //策略选择单选按钮操作
    $(".jkn_message_radio1").children(".headr").click(function(){

      $(this).children("span").addClass("bgred").removeClass("bgr");
      newsPolicy = $(this).children("span").attr("newsPolicy");
      switch (newsPolicy)
      {
        case "day" :
          newsMonth = "";
          newsWeek = "";
          break;
        case "week" :
          newsMonth = "";
          break;
        case "month":
          newsWeek = "";
      }
      $(this).parents(".jkn_message_90").siblings().find(".jkn_message_radio1").children(".headr").children("span").addClass("bgr").removeClass("bgred")
      if($(this).parents(".jkn_message_90").find(".weekradio").length!=1){
        $(this).parents(".jkn_message_90").siblings().find(".weekradio").children(".headr").children("span").addClass("bgr").removeClass("bgred")
      }
      if($.trim($(this).text())!="每月固定时间")
	    {
	    	//隐藏每月固定时间下拉框
	    	$("#select0").parent().hide()
	    }
	    else
	    {
	    	$("#select0").parent().show()
	    }
    })
      <%--//初始化策略选择值--%>
      <%--var loopval="<%=Loop%>";--%>
      <%--console.log(loopval)--%>
      <%--if(loopval=="")--%>
      <%--{--%>
          <%--$("#newsPolicyOld").next().find(".jkn_message_radio1").click()--%>
      <%--}--%>
      <%--else if(loopval=="month")--%>
      <%--{--%>
          <%--console.log("a")--%>
          <%--$("#newsPolicyOld").next().next().next().find(".jkn_message_radio1").click()--%>
          <%--console.log("aa")--%>
      <%--}--%>
      <%--else if(loopval=="week")--%>
      <%--{--%>
          <%--$("#newsPolicyOld").next().next().find(".jkn_message_radio1").click()--%>
      <%--}--%>
      <%--else if(loopval=="day")--%>
      <%--{--%>
          <%--$("#newsPolicyOld").next().find(".jkn_message_radio1").click()--%>
      <%--}--%>

    //策略选择周选择策略

    $(".weekradio").children(".headr").click(function(){
      $(".jkn_message_radio1").children(".headr").eq(1).click()
      $(this).children("span").addClass("bgred").removeClass("bgr");
      newsWeek = $(this).children("span").attr("newsWeek");
      newsMonth = "";
      $(this).siblings().children("span").addClass("bgr").removeClass("bgred")
    })
    //点击策略按钮，显示弹窗
    $("#newsPolicy").click(function(){
      $(".jkn_message_tacticsbg").show()
    })
    $("#cancelPolicy").click(function(){
      window.location.reload();
    });
    //点击弹窗关闭按钮，关闭弹窗
    $(".jkn_message_tacticsbg").find(".win_close").click(function(){
      $(".jkn_message_tacticsbg").hide()
    })

    //页面状态初始化
    $("#newsShowType").find("div").eq(0).click();//发布方式默认常驻桌面
    var myDate = new Date();
    var myDateStr = myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate();
    $("#startTime").val(myDateStr);//开始日期初始化
    $("#start").text(myDateStr);
    $("#endTime").val(myDateStr);//结束日期初始化
    $("#end").text(myDateStr);
      var loopval="<%=Loop%>";
      if(loopval != ""){
          $("#newsPolicyOld").attr("policy",loopval);
          $("span[newsPolicy ='"+loopval+"']").click();
          if(loopval=="week"){
              var week = "<%=week%>"
              $(".weekradio").children(".headr").eq(week-1).click();
          }else if(loopval == "month"){
              var month = "<%=month%>";
              $("#selectdivul0 a").eq(month-1).click();
          }
      }else{
          $("span[newsPolicy ='day']").click();
          $("#newsPolicyOld").attr("policy","day");
      }

    //开始时间初始化
      var myDate = new Date();
      var myDateStr = myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate();
      var myEndDateStr = myDateStr;
      var startTime = "<%=startTime%>";
      var endTime = "<%=endTime%>";
      if(startTime != ""){
          myDateStr = startTime.split(" ")[0];
      }
      ///console.log(startTime.split(" ")[0]+"==========="+endTime.split(" ")[0]+"========="+myEndDateStr);
      if(endTime != ""){
          myEndDateStr = endTime.split(" ")[0];
      }
      $("#startTime").val(myDateStr);//开始日期初始化
      $("#start").text(myDateStr)
      $("#endTime").val(myEndDateStr);//结束日期初始化
      $("#end").text(myEndDateStr);
      var start = "<%=start%>";
      var startText = "08";
      var startEnd = "00";
      if(start != ""){
          var startArr = start.split(":");
          startText=startArr[0];
          startEnd = startArr[1];
      }else{
          start = "08:00";
      }
      var finish = "<%=finish%>";
      var finishText = "17";
      var finishEnd = "59";
      if(finish != ""){
          var finishArr = finish.split(":");
          finishText=finishArr[0];
          finishEnd = finishArr[1];
      }else{
          finish = "17:59";
      }
    $("#newsStart").find(".jkn_message_hour").text(startText);
    newsStartTimeHour = startText;
    newsStartTimeMinutes = startEnd;
    $("#newsStart").find(".jkn_message_minutes").text(startEnd);
    $("#newsPolicyOld").attr("start",start);
    //结束时间初始化
    $("#newsFinish").find(".jkn_message_hour").text(finishText);
    $("#newsFinish").find(".jkn_message_minutes").text(finishEnd);
    newsFinishTimeHour = finishText;
    newsFinishTimeMinutes = finishEnd;
    $("#newsPolicyOld").attr("finish",finish);
      //选择设备
      var hostIdStr = "<%=hostIdStr%>";
      if(hostIdStr != "") {
          var arr = hostIdStr.split(",");
          var hostTrees = $(".jkn_treecheckbox");
          for (var m = 0; m < hostTrees.length; m++) {
              for (i = 0; i < arr.length-1; i++) {
                  //console.log(hostTrees.eq(m).attr("hostId")+"======="+arr[i]);
                  if (hostTrees.eq(m).attr("hostId") == arr[i]) {
                      hostTrees.eq(m).click();
                  }
              }
          }
      }

  })
  function change_size() {

    width = $("#otp_vedeoabout_rvideo").width();
    height =310

    $("#otp_vedeoabout_rvideo").width(width).height(height);

    // update perfect scrollbar
    $('#otp_vedeoabout_rvideo').perfectScrollbar('update');
  }
  $(function () {
    $('#otp_vedeoabout_rvideo').perfectScrollbar();
    prettyPrint();
  });
  var start = {
    elem: '#start',
    format: 'YYYY-MM-DD',
    min: laydate.now(), //设定最小日期为当前日期
    max: '2099-06-16 23:59:59', //最大日期
    istoday: false,
    festival: true, //是否显示节日
    choose: function(datas){
      end.min = datas; //开始日选好后，重置结束日的最小日期
      end.start = datas //将结束日的初始值设定为开始日
      $("#startTime").val(datas);
    }
  };
  var end = {
    elem: '#end',
    format: 'YYYY-MM-DD',
    min: laydate.now(), //设定最小日期为当前日期
    max: '2099-06-16 23:59:59', //最大日期
    festival: true, //是否显示节日
    istoday: false,
    choose: function(datas){
      start.max = datas; //结束日选好后，重置开始日的最大日期
      $("#endTime").val(datas);
    }
  };
  laydate(start);
  laydate(end);
  var newsPolicy;//记录策略类型临时变量
  var newsWeek;//周策略临时变量
  var newsMonth;//月策略临时变量
  var newsStartTimeHour;//策略开始时间的hour
  var newsStartTimeMinutes;//策略开始时间的minute
  var newsFinishTimeHour;//策略结束时间的hour
  var newsFinishTimeMinutes;//策略结束时间的minute
  var urlHead = $("#newsParams").attr("urlhead");
  var clickNum = 0;
  function updateProjectVal() {
      //开始日期与结束日期
      var newsBeginDate = $("#startTime").val();
      var newsEndDate = $("#endTime").val();
      if (newsBeginDate == null || newsBeginDate == "" || newsEndDate == null || newsEndDate == "") {
          layer.msg("信息有效期限设置失败！");
          return;
      }
      //策略
      if(newsPolicy == "month")
      {
          newsMonth = $("#select0").val();
      }
      if(newsStartTimeHour == null || newsFinishTimeHour == null)
      {
          layer.msg("时间设置不正确");
          return ;
      } else {
          if(newsStartTimeMinutes == null) {
              newsStartTimeMinutes = "00";
          }
          if(newsFinishTimeMinutes == null) {
              newsFinishTimeMinutes = "59";
          }
          $("#newsPolicyOld").attr("start",(newsStartTimeHour + ":" + newsStartTimeMinutes));
          $("#newsPolicyOld").attr("finish",(newsFinishTimeHour + ":" + newsFinishTimeMinutes));
      }
      var result = policyVerify($("#startTime").val(),$("#endTime").val(),newsPolicy,"");
      if (newsPolicy == "month") {
          newsMonth = $("#select0").val();
      }
      if (newsStartTimeHour == null || newsFinishTimeHour == null) {
          layer.msg("时间设置不正确");
          return;
      } else {
          if (newsStartTimeMinutes == null) {
              newsStartTimeMinutes = "00";
          }
          if (newsFinishTimeMinutes == null) {
              newsFinishTimeMinutes = "59";
          }
          $("#newsPolicyOld").attr("start", (newsStartTimeHour + ":" + newsStartTimeMinutes));
          $("#newsPolicyOld").attr("finish", (newsFinishTimeHour + ":" + newsFinishTimeMinutes));
      }
      var result = policyVerify($("#startTime").val(), $("#endTime").val(), newsPolicy, "");
      if (clickNum < 1) {
              var checkedHostsArr = $(".jkn_treecheckboxed");
              var hostIdsStr = "";
              if (checkedHostsArr.length < 1) {
                  layer.msg("未选择设备！");
                  return;
              }
              for (var i = 0; i < checkedHostsArr.length; i++) {
                  hostIdsStr += checkedHostsArr.eq(i).attr("hostId") + "<%=hostIdsFlag%>";//获取选中的host,以间隔符连接
              }

              if ($("#newsPolicyOld").attr("policy") == "") {
                    layer.msg("未设置信息显示策略！");
                    return;
              }
              if ($("#newsPolicyOld").attr("start") == "" || $("#newsPolicyOld").attr("finish") == "") {
                    layer.msg("策略时间无效！");
                    return;
              }

              if(newsEndDate<newsBeginDate){
                  layer.msg("结束日期不能小于开始日期");
                  //return;
              }else{
                  clickNum++;
                  $.post(urlHead + "/news/News_publishNews.do", {
                      //newsCont: "/msgResource/publish/423/preview/",
                      newsBeginDate: newsBeginDate,
                      newsEndDate: newsEndDate,
                      newsStartTime: $("#newsPolicyOld").attr("start"),
                      newsFinishTime: $("#newsPolicyOld").attr("finish"),
                      newsPolicy: newsPolicy,
                      newsWeek: newsWeek,
                      newsMonth: newsMonth,
                      hostIdsStr: hostIdsStr,
                      newsId: $("#newsParams").attr("newsId")
                  }, function (data) {
                      layer.msg(data.msg);
                      clickNum = 0;
                      if (data.success == true) {
                            //	window.location.reload();
                            setTimeout(function () {
                                parent.window.location.href= "${pageContext.request.contextPath}/news/News_richNewsList.do";
                            }, 1000);
                      }

                  }, "json");
              }
          }
      //}
  }
</script>
</body>
</html>
