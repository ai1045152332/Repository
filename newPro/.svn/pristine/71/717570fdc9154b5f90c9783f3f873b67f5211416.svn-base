<%--
  Created by IntelliJ IDEA.
  User: hthwx
  Date: 2015/6/26
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: chby
  Date: 2014/11/1
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.honghe.recordhibernate.dao.Page" %>
<%@ page import="com.honghe.recordhibernate.dao.Pager" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.io.File" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
    <title>资源管理</title>
    <link href="${pageContext.request.contextPath}/css/frontend/record/hpager.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/index.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/main.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/fd-slider.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/common/jquery-1.8.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/jquery.cookie.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/common/perfect-scrollbar.with-mousewheel.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/prettify.js"></script>
    <script src="${pageContext.request.contextPath}/js/lb_common/checkbox_res.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
    <!--layerdate-->
    <script src="${pageContext.request.contextPath}/js/lb_common/screendetailforrecord.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/laydate/laydate.js"></script>
    	<script src="${pageContext.request.contextPath}/js/common/scrolldemo.js"></script>
</head>
<body>
<%

    response.setHeader("Cache-Control","no-store");

    response.setHeader("Pragrma","no-cache");

    response.setDateHeader("Expires",0);

%>

    <%
        Page pageResource = (Page)request.getAttribute("pageResource"); //获取信息列表
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        SimpleDateFormat DATE_FORMAT_SHORT = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String pageType = (String) request.getAttribute("pageType");
        String pagers = "";
        int pageCount = 0;
        int pageNum = 0;
        String searchData = (String) request.getAttribute("searchCondition");//获取上一次的搜索结果
        String orderCondition = (String) request.getAttribute("orderCondition");//获取上一次排序结果
        String hostIp = (String) request.getAttribute("hostIp");//获取当前选择的设备ip
        String recordType = request.getAttribute("recordType").toString();//获取当前选择设备是常态化还是精品，精品为1
        Integer currentpage = 0;
        String beginDate = (String) request.getAttribute("beginDate");
        if(beginDate != null && !beginDate.equals("")) {
            try {
                beginDate = DATE_FORMAT_SHORT.format(DATE_FORMAT.parse(beginDate));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            beginDate = "";
        }
        String endDate = (String) request.getAttribute("endDate");
        if(endDate != null && !endDate.equals("")) {
            try {
                endDate = DATE_FORMAT_SHORT.format(DATE_FORMAT.parse(endDate));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else {
            endDate = "";
        }
    %>
 <style>

            .tab_content_listinput{
                border:1px solid #bbb
            }
            .tab_content_listtext{
                color: #80858F;
            }
            #linkpage{
                position: absolute;
                bottom: 2px;
                left: 55%;
                text-align: center;
                margin-left: -252px;
                width: 476px;
            }
            .mm_list_options{
                margin-right: 5px;
            }
            .mm{
                min-height: 0;
                min-width: 0;
            }
            #mm_right_video{
                width: 100%;
            }
            .win360_content_date{
                margin-top: 0.2%;
                width: 120px;
            }
            #start,#end{
                width: 100px;
            }
            .sm_list{
                margin-left: 30px;
            }
        </style>
                <div class="mmm floatleft">
                    <div class="mm_head">
                        <%--<div class="mm_head_option" style="text-indent: 12px;display:none;">--%>
                            <%--&lt;%&ndash;<a href="javascript:history.go(-1);" onclick="clearcookie()">&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<span class="back"></span>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<span style="font-size:14px;">返回</span></a>&ndash;%&gt;--%>
                        <%--</div>--%>
                        <div class="title-nm" style="width: 85px;float: left;margin-left:10px">选择时间：</div>
                        <div class="win360_content_date">
                            <input id="start" value="<%=beginDate%>" class="laydate-icon">
                            <input type="hidden" value="<%=beginDate%>" id="startTime">
                        </div>
                        <div class="title-nm" style="width: 25px;display: block; float: left; text-align: center;">至</div>
                        <div class="win360_content_date">
                            <input id="end" value="<%=endDate%>"  class="laydate-icon">
                            <input type="hidden" value="<%=endDate%>" id="endTime">
                        </div>
                        <script>
                            var start = {
                                elem: '#start',
                                format: 'YYYY-MM-DD',
                                festival: true, //是否显示节日
                                istoday: false,
                                choose: function(datas){
                                    var end = $("#end").val();
                                    if(end!=null&&end!=''){
                                        var startTime = new Date(datas);
                                        var endTime = new Date($("#end").val());
                                        var result =startTime<=endTime;
                                        console.log(result);
                                        if(!result){
                                            $("#start").val('');
                                            layer.msg("开始时间不能晚于结束时间");
                                            return;
                                        }
                                    }
                                    $("#startTime").val(datas);
                                    refreshPage();
                                }
                            };
                            var end = {
                                elem: '#end',
                                format: 'YYYY-MM-DD',
                                istoday: false,
                                festival: true, //是否显示节日
                                choose: function(datas){
                                    var start =$("#start").val();
                                    if(start!=null&&start!=''){
                                        var startTime = new Date($("#start").val());
                                        var endTime = new Date(datas);
                                        var result =startTime<=endTime;
                                        console.log(result);
                                        if(!result){
                                            $("#end").val('');
                                            layer.msg("结束时间不能早于开始时间");
                                            return;
                                        }
                                    }
                                    $("#endTime").val(datas);
                                    refreshPage();
                                }
                            };
                            laydate(start);
                            laydate(end);
                        </script>
                        <span class="mm_nogroup_option" style="margin-left: 23px;text-indent: 0;margin-top: 0;"></span>
                        <span class="sm_pink">
                        	<span class="sm_pinkmenu">
                        		<li id="res_name" class="orderCondition">按文件名称</li>
                        		<li id="res_updatetime" class="orderCondition">按修改日期</li>
                        		<%--<li id="host_name" class="orderCondition">按班级名称</li>--%>
                        	</span>
                        </span>
                        <span class="sm_search">
                        	<input class="sm_search_input" type="text" id = "searchData" value="<%=searchData != null && !searchData.trim().equals("") ? searchData : ""%>"   placeholder="输入主讲人、课程或科目"/>
                        	<input type="submit" class="sm_search_btn" id="searchBtn" value="" />
                        </span>

                    </div>
                       <div class="mm_right">
	                       <div class="scrollfather" id="mm_right_video" >
													 <div class="scrollson" style="min-height: 720px;">
                    <%
                        if(pageResource != null && pageResource.getResult() != null)
                        {
                            // System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                            List<Map<String,Object>> mapList = pageResource.getResult();
                            pageCount = pageResource.getTotalPageSize();
                            pageNum = mapList.size();
                            currentpage = Integer.parseInt(request.getAttribute("currentPage").toString());
                            if(currentpage > pageCount)
                                currentpage = pageCount;
                            String noDataStr = "--";
                            String videoUrl ="";
                            if(mapList.size() > 0)
                            {
                                Pager pager = new Pager(pageCount, currentpage,3, "<span class='pages_prevpage'></span>", "<span class='pages_nextpage'></span>", "", "", false);
                                pagers = pager.run();//分页样式
                                for (Map<String,Object> resInfo : mapList)
                                {
                                    videoUrl = request.getContextPath() + "/image/frontend/video.png";
                                    if(resInfo.get("res_path")!=null && resInfo.get("res_thumb")!= null && !resInfo.get("res_path").toString().equals("") && !resInfo.get("res_thumb").toString().equals("")) {
                                        String ss1 = resInfo.get("res_path").toString();
                                        String ss2 = ss1.substring(2, ss1.length());
                                        String[] s1 = ss2.split("\\\\\\\\");
                                        if (s1 != null && s1.length > 2) {
                                            String r1 = s1[s1.length - 1];
                                            String r2 = s1[s1.length - 2];
                                            if(recordType != null && recordType.equals("1")){
                                                videoUrl = "http://" + hostIp + "/res/" + r2 + "/" + r1 + "/" + resInfo.get("res_thumb").toString();;
                                            }else {
                                                File file = new File(ss1 + "\\" + resInfo.get("res_thumb").toString());
                                                if (file.exists() && file.isFile()) {
                                                    videoUrl = "/res/" + r2 + "/" + r1 + "/" + resInfo.get("res_thumb").toString();
                                                }
                                            }
                                        }
                                    }
                    %>
                    <div class="sm_list" resId = "<%=resInfo.get("res_id")%>">
                        <a href="${pageContext.request.contextPath}/video/Video_videoList.do?rId=<%=resInfo.get("res_id")%>&hostIp=<%=hostIp%>&recordType=<%=recordType%>">
                            <img src="<%=videoUrl%>" class="sm_listimg" />
                            <span class="sm_listdate"><%=resInfo.get("res_name") != null ? resInfo.get("res_name") : noDataStr%></span>
                            <span class="sm_listtext"><span class="sm_listtext30" <%=resInfo.get("host_name")!= null ? "title='班级名称：" + resInfo.get("host_name") + "'" : ""%>><%=resInfo.get("host_name")!= null ? resInfo.get("host_name") : ""%></span><span class="sm_listtext30"  <%=resInfo.get("res_subject")!= null ? "title='科目：" + resInfo.get("res_subject") + "'" : ""%>><%=resInfo.get("res_subject") != null ? resInfo.get("res_subject") : noDataStr%></span></span>
                            <span class="sm_listtext"><span class="sm_listtext26" <%=resInfo.get("res_speaker")!= null ? "title='主讲人：" + resInfo.get("res_speaker") + "'" : ""%>><%=resInfo.get("res_speaker") != null ? resInfo.get("res_speaker") : noDataStr%></span><span class="sm_listtext45" <%=resInfo.get("res_course")!= null ? "title='课程：" + resInfo.get("res_course") + "'" : ""%>><%=resInfo.get("res_course") != null ? resInfo.get("res_course") : noDataStr%></span><span class="sm_listtext26" style="text-indent: 10px" <%=resInfo.get("res_size")!= null ? "title='文件大小：" + resInfo.get("res_size") + "'" : ""%> ><%=resInfo.get("res_size") != null ? resInfo.get("res_size") : noDataStr%></span></span>

                        </a>
                        <div class="mm_list_option" style="width: 240px;" resId = "<%=resInfo.get("res_id")%>">
                            <div class="mm_list_options mm_list_edit" title="修改名称"></div>
                            <div class="mm_list_options mm_list_del" title="删除" hostnumber="0"></div>
                        </div>
                    </div>

                    <%
                        }
                    }
                    else
                    {
                    %>
                    <div class="nofind" style="position: relative;left:50%;margin-left:-90px">
                        找不到您想要的内容
                    </div>
                    <%
                        }
						                    }
						                    else
						                    {
						
						                    %>
						                    <div class="nofind" style="position: relative;left:50%;margin-left:-90px">
						                        找不到您想要的内容
						                    </div>
						                    <%
						                        }
						                    %>
						               
                   					 <style>
														    #linkpage{
														        position: relative;
														        bottom: 0px;
														        left: 55%;
														        /*margin: 30px 0 0 0;*/
														        text-align: center;
														        margin-left: -252px;
														        width: 100%;
														    }

														</style>
														 

           							 </div>
												<div class="scroll_ymove">
												<div class="scroll_y" unorbind="unbind"></div>
												</div>
												<div class="scroll_xmove">
												<div class="scroll_x" unorbind="unbind"></div>
												</div>
                               <div style="float: left;width: 100%;">
                                   <div class="floatleft" id ="linkpage"><%=pagers%></div>
                               </div>
											</div>

      						  </div>

                </div>

<input type="text" style="display: none" id="newsParams"  urlhead="${pageContext.request.contextPath}"/>
<input type="hidden" id="whichscroll"> 
</body>
</html>
<script>
    var host_ip = "<%=hostIp%>";
    var record_type = "<%=recordType%>";
    $(function(){
        var iframehei=parent.$(".resiframe").height()
        $(".mmm").height(iframehei)
        var exphei=parent.document.documentElement.clientHeight;
        if(screen.width==1920&&screen.height==1080||screen.width==1680&&screen.height==1050||screen.width==1400&&screen.height==1050||screen.width==1280&&screen.height==1024||screen.width==1600&&screen.height==900||screen.width==1440&&screen.height==900||screen.width==1366&&screen.height==768||screen.width==1360&&screen.height==768||screen.width==1280&&screen.height==800||screen.width==1280&&screen.height==960||screen.width==1280&&screen.height==720||screen.width==1280&&screen.height==600||screen.width==1024&&screen.height==738){
//					//判断浏览器当前的高度，依据高度区间重新定义样式表
            if(exphei>=900){
                //parent.$(".foot_center").append(11+"--"+exphei)
                $(".sm_search").css({"margin-top":"14px"})
                $(".sm_pink").css({"margin-top":"14px"})
                $(".resmargintop").css({"margin-top":"10px"})
                $(".win360_content_date").css({"margin-top":"14px"})
            }else if(exphei>=800&&exphei<900){
                //parent.$(".foot_center").append(2+"--"+exphei)
                $(".sm_search").css({"margin-top":"12px"})
                $(".sm_pink").css({"margin-top":"12px"})
                $(".resmargintop").css({"margin-top":"8px"})
                $(".win360_content_date").css({"margin-top":"12px"})
            }else if(exphei>=700&&exphei<800){
                //parent.$(".foot_center").append(3+"--"+exphei)
                $(".sm_search").css({"margin-top":"8px"})
                $(".sm_pink").css({"margin-top":"8px"})
                $(".resmargintop").css({"margin-top":"4px"})
                $(".win360_content_date").css({"margin-top":"8px"})
            }else if(exphei>=620&&exphei<700){
                //parent.$(".foot_center").append(4+"--"+exphei)
                $(".sm_search").css({"margin-top":"6px"})
                $(".sm_pink").css({"margin-top":"6px"})
                $(".resmargintop").css({"margin-top":"2px"})
                $(".win360_content_date").css({"margin-top":"6px"})
            }else{
                //parent.$(".foot_center").append(5+"--"+exphei)
                $(".sm_search").css({"margin-top":"3px"})
                $(".sm_pink").css({"margin-top":"3px"})
                $(".resmargintop").css({"margin-top":"1px"})
                $(".win360_content_date").css({"margin-top":"3px"})
            }
        }
        //模拟滚动条初始化操作
        var vipt_videowid=parent.$(".resiframe").width()
        $("#mm_right_video .scrollson").width(vipt_videowid)
        $("#mm_right_video").height($(".mm_right").height())
        $("#mm_right_video").width($(".mm_right").width())

        //模拟滚动条移动端监听
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
        //调整卡片间距
        var smlistwid=$(".sm_list").width();
        var marginleft=(vipt_videowid/3-smlistwid)/3
        $(".sm_list").css("margin-left",marginleft*2+"px")



        var iframewidth=parent.$(".resiframe").width();
        var iframeheight=parent.$(".resiframe").height();
        // alert(iframewidth+"---"+iframeheight)
        $(".public").css({"width":iframewidth+"px","height":iframeheight+"px"});

        if(window.location.href.indexOf("?")< 0)
        {
            // $.cookie("back","0",{path:'/resource'});
            $(".mm_head_option").hide();
        }
        else
        {
            // $.cookie("back","1",{path:'/resource'});
            $(".mm_head_option").show();
        }

        var totalpagesize = "<%=pageCount%>";
        //分页调整
        var page=totalpagesize;
        var html="<span style='float: left;margin-left: 2px;'>&nbsp;&nbsp;共"+page+"页 &nbsp;&nbsp;跳转到 "+"<input id=\"jump\" type=text style=\"width:20px;background:#d7dade;border:1px solid #5e6470\" title=\"点击回车即可跳转分页\" onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'')\" onafterpaste=\"this.value=this.value.replace(/[^0-9]/g,'')\"/> 页<span>";
        $(".yiiPager").append(html)
        left=-(18)*28/2+"px";
        $("#linkpage").css({"margin-left":left,"width":(17)*28+"px"});
        $(document).keydown(function(event){
            //alert(event.ctrlKey+"=="+event.which)
            //判断当event.keyCode 为37时（即左方面键），执行函数to_left();
            //判断当event.keyCode 为39时（即右方面键），执行函数to_right();
            if(event.ctrlKey || event.which == 13){
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
        })
        //iframe分页处理
        var pagelen=  $("#mm_right_video .scrollson").find(".sm_list").length;
        if(pagelen<=3)
        {
//            console.log("aaa")
            $("#linkpage").css({"position":"absolute","bottom":"10px"})
        }
        else
        {
//            console.log("bbbb")
            $("#linkpage").css({"position":"relative","bottom":"0px"})
        }


    })
    /**
     * 获取url地址中的参数
     */
    function urloption(url){
        // alert(url);
        var totalpagesize = "<%=pageCount%>";
        if(url.indexOf("?")!=-1){
            var p=url.indexOf("?"); //返回所在位置
            var host="http://"+window.location.host+url.substr(0,p+1);
            var str = url.substr(p+1) //从这个位置开始截取
            strs = str.split("&"); //拆分
            var jumpval=$("#jump").val();
            var patrn = /^[0-9]*$/;
            if(!patrn.exec(jumpval)|| jumpval<=0){
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
            location.href=newurl
        }
    }
    var urlHead = $("#newsParams").attr("urlhead");
    var pageType = "<%=pageType%>";//0,页面加载；1,页面搜索
    var pageNum = "<%=pageNum%>";
    var pageCount = "<%=pageCount%>";
    var order_condition = "<%=orderCondition%>";
    var currentPage = "<%=currentpage%>";
    $(function(){
        $(".orderCondition").click(function(){
            order_condition = $(this).attr("id");
            currentPage = 1;
            $.cookie("back","1",{path:'/resource'});
            var starttime = $("#startTime").val();
            var endtime = $("#endTime").val();
            if(starttime != null && starttime.trim() != "")
            {
                starttime = starttime + " 00:00:00";
            }
            else
            {
                starttime = "";
            }
            if(endtime != null && endtime.trim() != "")
            {
                endtime = endtime + " 23:59:59";
            }
            else
            {
                endtime = "";
            }
//            if(endtime != null && endtime != "")
//            {
//                if(starttime != null && starttime.trim("") != "")
//                {
//                    if(endtime <= starttime)
//                    {
//                        layer.msg("结束时间不能早于开始时间！");
//                        return;
//                    }
//                }
//            }
            var url = urlHead;
            var tmp_search_data = $("#searchData").val();
            if(pageType == 1 || pageType == "1")
            {
                var tmp_search_data = encodeURI(encodeURI(tmp_search_data));
            }
            url = url + "/resource/Resource_resourceListByIp.do?searchCondition=" + tmp_search_data + "&orderCondition=" + order_condition +"&beginDate=" + starttime  + "&endDate=" + endtime  + "&hostIp=" + host_ip + "&recordType=" + record_type;
            location.href = url;
        });
        $(".sm_pink").click(function(){
            var flag=$(".sm_pinkmenu").css("display");
            if(flag=="none"){
                $(".sm_pinkmenu").css("display","block")
            }else{
                $(".sm_pinkmenu").css("display","none")
            }
        })
        //点击空白区域，隐藏巡课select模拟
        $(document).bind("click", function (e) {
            var target = $(e.target);//获取点击事件
            if (target.closest(".sm_pink").length == 0) {
                $(".sm_pinkmenu").hide();//隐藏divli
            }
        })

    })
    $(".mm_list_edit").click(function(){
        var resId =$(this).parent().attr("resId");
        if(resId == null || resId.trim(" ") == "")
        {
            layer.msg("未找到数据！");
        }
        else
        {
            $.layer({
                type: 2,
                title: '编辑信息',
                shadeClose: true,
                maxmin: false,
                fix: false,
                area:['366px','405px'],
                iframe: {
                    src: urlHead + '/resource/Resource_getResource.do?resId=' + resId + '&hostIp=' + host_ip + '&recordType=' + record_type,
                    scrolling: 'no'
                }
            });
        }
    });
    $(".mm_list_del").click(function(){
        var resId = $(this).parent().attr("resId");
        if(resId == null || resId.trim(" ") == "")
        {
            layer.msg("未找到数据！");
        }
        else
        {
            $.layer({
                shade: [0.5, '#000'],
                area: ['310px', '129px'],
                title: '删除',
                dialog: {
                    msg: '您确定要删除该文件夹？',
                    btns: 2,
                    type: 4,
                    btn: ['确定', '取消'],
                    yes: function () {
                        $.post(urlHead + "/resource/Resource_deleteResource.do",{resId:resId,hostIp:host_ip,recordType:record_type},function(data){
                            layer.msg(data.msg);
                            if (data.success == true) {
                                if(pageNum == 1 || pageNum == "1")
                                {
                                    currentPage --;
                                }
                                if(currentPage < 1)
                                {
                                    currentPage =1;
                                }
                                setTimeout(function(){
                                    refreshPage();
                                },1000);
                            }

                        });
                    }, no: function () {
                        // layer.msg('奇葩', 1, 13);
                    }
                }
            });

        }
    });

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

    $("#searchBtn").click(function(){
        var search_data = $("#searchData").val();
        var starttime = $("#startTime").val();
        var endtime = $("#endTime").val();
        if(starttime != null && starttime.trim("") != "")
        {
            starttime += " 00:00:00";
        }
        if(endtime != null && endtime != "")
        {
            endtime += " 23:59:59";
        }
        if(search_data == null || search_data.trim(" ") == "")
        {
            layer.msg("请输入查询条件！");
            return null;
        }
        else
        {
            $.cookie("back","1",{path:'/resource'});
            pageType = 1;
            var tmp_search_data = encodeURI(encodeURI(search_data));
            // alert(search_data + "~~~~" + encodeURI(search_data) + "~~"+decodeURI(encodeURI(search_data))+"~~" + tmp_search_data);
            location.href = urlHead + "/resource/Resource_resourceListByIp.do?searchCondition=" + tmp_search_data + "&orderCondition=" + order_condition +"&beginDate=" + starttime  + "&endDate=" + endtime +  "&hostIp=" + host_ip  + "&recordType=" + record_type;;
            //$(".mm_head_option").show();

        }

    });
    function refreshPage()
    {
        var search_data = $("#searchData").val();
        var starttime = $("#startTime").val();
        var endtime = $("#endTime").val();
        if(starttime != null && starttime.trim("") != "")
        {
            starttime += " 00:00:00";
        }
        if(endtime != null && endtime != "")
        {
            endtime += " 23:59:59";
        }
//        if(endtime != null && endtime != "")
//        {
//            if(starttime != null && starttime.trim("") != "")
//            {
//                if(endtime <= starttime)
//                {
//                    layer.msg("结束时间不能早于开始时间！");
//                    return;
//                }
//            }
//        }

        $.cookie("back","1",{path:'/resource'});
        pageType = 1;
        var tmp_search_data = encodeURI(encodeURI(search_data));
        // alert(search_data + "~~~~" + encodeURI(search_data) + "~~"+decodeURI(encodeURI(search_data))+"~~" + tmp_search_data);
        location.href = urlHead + "/resource/Resource_resourceListByIp.do?searchCondition=" + tmp_search_data + "&orderCondition=" + order_condition +"&beginDate=" + starttime  + "&endDate=" + endtime +  "&hostIp=" + host_ip  + "&recordType=" + record_type;;
        //$(".mm_head_option").show();

    }
    function clearcookie()
    {
        // $.cookie("back","0",{path:'/resource'});
    }
    $(function(){
        $(".title-nm").css("line-height",$(".mm_head").height()+"px");
    })
</script>