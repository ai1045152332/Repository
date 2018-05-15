<%--
  Created by IntelliJ IDEA.
  User: hthwx
  Date: 2015/7/4
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.honghe.recordhibernate.dao.Page" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.honghe.recordhibernate.dao.Pager" %>
<%@ page import="net.sf.json.JSONObject" %>
<%@ page import="com.honghe.recordhibernate.entity.Resource" %>
<%@ page import="java.io.File" %>
<%@ page import="com.honghe.recordhibernate.entity.User" %>
<%@ page import="com.honghe.recordweb.action.SessionManager" %>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.honghe.recordweb.service.frontend.user.Authority" %>
<%@ page import="com.honghe.recordweb.service.frontend.hostgroup.HostgroupService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
    <title>资源文件管理</title>
<%--    <link href="${pageContext.request.contextPath}/css/frontend/record/hpager.css" rel="stylesheet" type="text/css"/>--%>
    <link href="${pageContext.request.contextPath}/css/frontend/record/index.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/main.css" rel="stylesheet" type="text/css"/>
    <%--<link href="${pageContext.request.contextPath}/css/frontend/record/fd-slider.css" rel="stylesheet" type="text/css"/>--%>
    <script src="${pageContext.request.contextPath}/js/common/jquery-1.8.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/jquery.cookie.js" type="text/javascript"></script>
 <%--   <script src="${pageContext.request.contextPath}/js/common/autoheight.js"></script>--%>
    <script src="${pageContext.request.contextPath}/js/common/perfect-scrollbar.with-mousewheel.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/prettify.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/checkbox_res.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/laydate/laydate.js"></script>
    <link href="${pageContext.request.contextPath}/css/frontend/hpager.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/ckplayer/ckplayer.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/offlights.js"></script>
</head>
<body>
<input type="hidden" id="fullscreen" value="fullscreen">
<%

    response.setHeader("Cache-Control","no-store");

    response.setHeader("Pragrma","no-cache");

    response.setDateHeader("Expires",0);

%>
<div class="public">
    <%
        Page pageVideo = (Page)request.getAttribute("pageVideo"); //获取信息列表
        String resPath = (String)request.getAttribute("path");
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
        // Resource resource = (Resource)request.getAttribute("resources");
        JSONObject resInfoJO = (JSONObject) request.getAttribute("resInfo");
        String userId = request.getAttribute("userId").toString();
        HostgroupService hostgroupService = new HostgroupService();
        String ip = hostgroupService.getIp();
//        String ip = "192.168.16.116";
        //String fullUrl = "http://" +  System.getProperty("NetworkInterface") + ":" + request.getLocalPort();
//        String fullUrl = "http://" +  ip + ":" + request.getLocalPort() + "/";
        String fullUrl = "http://" +  ip + ":" + "8181/";
        String rId = (String) request.getAttribute("rId");
        String hostIp = request.getAttribute("hostIp").toString();//对应录播主机的ip
        String recordType = request.getAttribute("recordType").toString(); //对应的录播主机是否为精品，精品为1
        String pagers = "";
    %>
    <table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
        <style>
            .tab_content_listinput{
                border:1px solid #bbb
            }
            .tab_content_listtext{
                color: #80858F;
            }
            #linkpage{
                position: absolute;
                bottom: 29px;
                left: 55%;
                text-align: center;
                margin-left: -252px;
                width: 476px;
            }
            .sm_list{
                height: 220px;
                width: 210px;
                margin-left: 18px;
            }
            .sm_listimg{
                height: 120px;
                width: 210px;
            }
            .sm_list::before{
                box-shadow: 0 0 5px 0 rgba(0,0,0,0.8);
                border-radius: 3px;
                content: "";
                height: 215px;
                float: left;
                left: 0;
                position: absolute;
                top: 5px;
                width: 210px;
                z-index: -1;
            }
            .sm_listtext45{
                max-width: 60%;
            }
            .sm_listdate{
                height: 34px;
                line-height: 34px;
            }
            .mm_head_option{
                overflow: hidden;
                text-indent: 0px;
                text-overflow: ellipsis;
                white-space: nowrap;
                max-width: 190px;
            }
            .mm_list_options{
                margin-right: 5px;
            }

            .democenter{
                left: 50%;
                height: 358px;
                position: absolute;
                top: 0;
                bottom: 0;
                right: 0;
                margin: auto;
                margin-left: -260px;
                width: 520px;
            }
            .demo{
                background: rgba(0,0,0,0.6);
                display: none;
                left: 0;
                position: absolute;
                top: 0;
                bottom: 0;
                right: 0;
                overflow: hidden;
                z-index: 100;
            }
        </style>



        <tr>
            <td class="bgf2f2f2" width="100%" height="100%">
                <div class="mm" style="min-width: 0px">
                    <div class="mm_head">
                        <span class="mm_head_option" ><%=resInfoJO != null ? resInfoJO.get("resName") : ""%></span>
                        <span class="mm_head_option" title="<%=resInfoJO != null ? resInfoJO.get("resCourse") : ""%>">课程：<%=resInfoJO.get("resCourse") != null && !resInfoJO.get("resCourse").equals("") ? resInfoJO.get("resCourse") : "--"%></span>
                        <span class="mm_head_option" title="<%=resInfoJO != null ? resInfoJO.get("resSubject") : ""%>" style="max-width: 180px;">科目：<%=resInfoJO.get("resSubject") != null && !resInfoJO.get("resSubject").equals("") ? resInfoJO.get("resSubject") : "--"%></span>
                        <span class="mm_head_option" title="<%=resInfoJO != null ? resInfoJO.get("resSpeaker") : ""%>" style="max-width: 120px;">主讲人：<%=resInfoJO.get("resSpeaker") != null && !resInfoJO.get("resSpeaker").equals("") ? resInfoJO.get("resSpeaker") : "--"%></span>
                        <span class="mm_head_option" title="<%=resInfoJO != null ? resInfoJO.get("hostName") : ""%>"
                              style="max-width: 150px;display: none">班级：<%=resInfoJO.get("hostName") != null && !resInfoJO.get("hostName").equals("") ? resInfoJO.get("hostName") : "--"%></span>

                    </div>
                    <%
                        if(pageVideo != null && pageVideo.getResult() != null)
                        {
                            try{
                                List<Map<String,Object>> mapList = pageVideo.getResult();
                                int pageCount = pageVideo.getTotalPageSize();
                                Integer currentpage = Integer.parseInt(request.getAttribute("currentPage").toString());

                    %>

                    <table border="0" cellpadding="0" cellspacing="0" width="100%">
                        <tr>
                            <td width="100%">
                                <div class="mm_right" >
                                    <div id="mm_right_video" style="width: 100%">
                                        <div class='mm_right_video_content' style="width: 100%">
                                            <%
                                                String noDataStr = "--";
                                                if(mapList.size() > 0)
                                                {
                                                    Pager pager = new Pager(pageCount, currentpage,3, "<span class='pages_prevpage'></span>", "<span class='pages_nextpage'></span>", "", "", false);
                                                    pagers = pager.run();//分页样式
                                                    for (Map<String,Object> resInfo : mapList)
                                                    {

                                                        String videoUrl = request.getContextPath() + "/image/frontend/video.png";
                                                        String videoPath = "";
                                                        if(resPath!=null &&  !resPath.trim().equals("")) {
                                                            String ss1 = resPath;
                                                            String ss2 = ss1.substring(2, ss1.length());
                                                            if(ss2.indexOf("@@") < 0) {
                                                                ss2 = ss2.replace("\\\\", "@@");
                                                                ss2 = ss2.replace("\\", "@@");
                                                            }

                                                            String[] s1 = ss2.split("@@");
                                                            if (s1 != null && s1.length > 2) {
                                                                String r1 = s1[s1.length - 1];
                                                                String r2 = s1[s1.length - 2];
                                                                if(recordType != null && recordType.equals("1")){
                                                                    if (resInfo.get("video_thumb") != null && !resInfo.get("video_thumb").toString().equals("")) {
                                                                        videoUrl = "http://" + hostIp + "/res/" + r2 + "/" + r1 + "/" + resInfo.get("video_thumb").toString();
                                                                    }
                                                                    videoPath = "http://" + hostIp + "/res/" + r2 + "/" + r1 + "/" + resInfo.get("video_name").toString();
                                                                    System.out.println(videoPath);
                                                                }else {
                                                                    if (resInfo.get("video_thumb") != null && !resInfo.get("video_thumb").toString().equals("")) {
                                                                        File file = new File(ss1 + "\\" + resInfo.get("video_thumb").toString());
                                                                        if (file.exists() && file.isFile()) {
                                                                            videoUrl = "/res/" + r2 + "/" + r1 + "/" + resInfo.get("video_thumb").toString();
                                                                            System.out.println(videoUrl);
                                                                        }
                                                                    }
                                                                    if (resInfo.get("video_name") != null && !resInfo.get("video_name").toString().equals("")) {
                                                                        File file = new File(ss1 + "\\" + resInfo.get("video_name").toString());
                                                                        if (file.exists() && file.isFile()) {
                                                                            videoPath = fullUrl  + r2 + "/" + r1 + "/" + resInfo.get("video_name").toString();
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                            %>

                                            <div class="sm_list" videoId = "<%=resInfo.get("video_id")%>">

                                                <%-- <a  href="javascript:cameraInfo('<%=resInfo.get("video_id")%>','<%=resInfo.get("video_url")%>')"  style="cursor: default;" >
                                                --%>
                                                <a  href="javascript:editannotatediv('<%=resInfo.get("video_id")%>','<%=videoPath%>')"  style="cursor: default;" >
                                                    <div class="sm_play"></div>
                                                    <img src="<%=videoUrl%>" class="sm_listimg" title="点播">
                                                </a>
                                                    <span class="sm_listdate" <%=resInfo.get("video_name")!= null ? "title='" + resInfo.get("video_name") + "'" : ""%>><%=resInfo.get("video_name")!= null ? resInfo.get("video_name") : noDataStr%></span>
                        <span class="sm_listtext"><span class="sm_listtext26"  <%=resInfo.get("video_size")!= null ? "title='" + resInfo.get("video_size") + "'" : ""%> ><%=resInfo.get("video_size") != null ? resInfo.get("video_size") : noDataStr%></span>
                          <span class="sm_listtext45" style="max-width: 55%"><%= resInfo.get("video_time") != null ? resInfo.get("video_time") : noDataStr  %></span></span>
                                                <div class="mm_list_option" style="width: 180px;"  videoId = "<%=resInfo.get("video_id")%>" >
                                                    <%
                                                        if(resInfo.get("video_upload") != null && ("9").equals(resInfo.get("video_upload").toString())) {
                                                    %>
                                                    <div class="mm_list_options sm_up" style="background:url(${pageContext.request.contextPath}/image/frontend/uploaddone.png) 1px 0.5px no-repeat "  upload="<%= resInfo.get("video_upload")%>" title="已上传"></div>
                                                    <%
                                                    }
                                                    else {
                                                    %>
                                                    <div class="mm_list_options sm_up"  title="上传"></div>
                                                    <%
                                                        }
                                                    %>
                                                    <div class="mm_list_options sm_down" onclick="downloadVideo(<%=rId%>,<%=resInfo.get("video_id")%>)" rId="<%=rId%>"  videoId = "<%=resInfo.get("video_id")%>" title="下载"></div>
                                                    <div class="mm_list_options mm_list_del" title="删除" hostnumber="0" rId="<%=rId%>"  videoId = "<%=resInfo.get("video_id")%>"></div>
                                                </div>
                                            </div>
                                            <%
                                                }
                                            }
                                            else {
                                            %>
                                            <div class="nofind" style="position: relative;left:50%;margin-left:-90px">
                                                找不到您想要的内容
                                            </div>
                                            <%
                                                }
                                            %>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr>

                    </table>
                    <%
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                    else {
                    %>
                    <div class="nofind" style="position: relative;left:50%;margin-left:-90px">
                        找不到您想要的内容
                    </div>
                    <%
                        }
                    %>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <div id="linkpage">
                    <div class="floatleft" id ="linkpage"><%=pagers%></div>  </div>
            </td>
        </tr>
    </table>
    <jsp:include page="/pages/frontend/footer.jsp"></jsp:include>
    <input type="text" style="display: none" id="newsParams"  urlhead="${pageContext.request.contextPath}"/>
    <div class="demo">
        <div class="democenter">
            <div class="win_head">
                <div class="win_head_text">预览</div>
                <div class="win_close"></div>
            </div>
            <div class="win520_content" style="height:265px;box-shadow: 0 0 0 1px #212121 inset;">
                <div style="margin: 0 auto;width: 476px;">
                    <div class="xk_video" style="background:white;position: relative;margin-left: 0;margin-top: 23px;width:475px" bingo="none">
                        <div id="onecamera">
                            <div id="a1" style="width: 100%;height: 100%"></div>
                            <script type="text/javascript">

                            </script>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
<script>



    $(window).resize(function() {
        $(".demo").css({"height":parseInt(document.documentElement.clientHeight)+"px"});
    });
    $(".sm_list").mouseover(function(){
        $(this).find(".sm_play").show();
    }).mouseout(function(){
        $(this).find(".sm_play").hide();
    })

    var userId = "<%=userId%>";
    var urlHead = $("#newsParams").attr("urlhead");
    var r_id = "<%=rId%>";
    var host_ip = "<%=hostIp%>";
    var record_type = "<%=recordType%>";

    $(function(){
        var iframehei=parent.$(".resiframe").height()
        $(".mmm").height(iframehei)
        var exphei=document.documentElement.clientHeight;
        var exphei86=exphei*0.86;
        if(exphei86<590)
        {
            exphei86=590
        }
        $(".mmm").css("height",exphei86+"px")


        $(".win_close").click(function(){
            CKobject.getObjectById('ckplayer_a1').videoPause();
            CKobject.getObjectById('ckplayer_a1').videoClear();
            $(".demo").css("display","none");
        })
        var iframewidth=parent.$(".resiframe").width();
        var iframeheight=parent.$(".resiframe").height();
        $(".public,#mm_right_video,.mm").css({"width":iframewidth+"px","height":iframeheight+"px"});
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

    $(".sm_up").click(function() {
        var video_id = $(this).parent().attr("videoId");
        var video_upload = $(this).attr("upload");
        if(video_id != null && video_id!= "")
        {
            if(video_upload == "9")
            {
                $.layer({
                    shade: [0.5, '#000'],
                    area: ['310px', '159px'],
                    title: '重复上传',
                    dialog: {
                        msg: '该文件此前已经上传过，再次上传会产生重复视频！是否继续？',
                        btns: 2,
                        type: 4,
                        btn: ['确定', '取消'],
                        yes: function () {
                            $.post(urlHead +  '/video/Video_uploadVideo.do',{videoId:video_id,rId:r_id,hostIp:host_ip,recordType:record_type},function(data){
                                layer.msg(data.msg);
                            },"json");
                        }, no: function () {
                        }
                    }
                });
            }
            else
            {
                $.post(urlHead +  '/video/Video_uploadVideo.do',{videoId:video_id,rId:r_id,hostIp:host_ip,recordType:record_type},function(data){
                    layer.msg(data.msg);
                },"json");
            }
        }
        else
        {
            layer.msg("未找到数据！");
        }

    });


    $(".mm_list_del").click(function(){
        var videoId =$(this).parent().attr("videoId")
        if(videoId == null || videoId.trim(" ") == "")
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
                    msg: '您确定要删除该文件？',
                    btns: 2,
                    type: 4,
                    btn: ['确定', '取消'],
                    yes: function () {
                        $.post(urlHead + "/video/Video_deleteVideo.do",{videoId:videoId,rId:r_id,hostIp:host_ip,recordType:record_type},function(data){
                            layer.msg(data.msg);
                            if (data.success == true) {
                                window.location.reload();
                                setTimeout(function(){
                                    // window.location.reload();

                                },1000);
                            }

                        });
                    }, no: function () {
                    }
                }
            });

        }
    });
    //预览
    var ttt;
    function editannotatediv(videoId,videoUrl)
    {
        //videoUrl="http://localhost:8080/a.MP4";
        var path = "${pageContext.request.contextPath}/video/Video_cameraInfo.do?videoId=" + videoId+"&videoUrl="+videoUrl;
        console.log(path);
        var height=parseInt(document.documentElement.clientHeight)+"px";
        var width=$(document).width()+"px";
        var mediaUrl =videoUrl;
        var flashvars={
            f:mediaUrl,
            c:0,
            p:1,
            v:40,
            h:4,
            loaded:'loadedHandler',
            b:0
        };
        var params={bgcolor:'#FFF',allowFullScreen:false,allowScriptAccess:'always',wmode:'transparent'};
        var video=[mediaUrl];
        if(CKobject.isHTML5()){
            support=['all'];
            CKobject._K_('a1').innerHTML='';
            CKobject.embedHTML5('a1','ckplayer_a1','475px','280px',video,flashvars,support);
        }
        else{
            CKobject._K_('a1').innerHTML='';
            CKobject.embed('${pageContext.request.contextPath}/js/common/ckplayer/ckplayer.swf','a1','ckplayer_a1','475px','280px',false,flashvars,video,params);
            //alert('该环境不支持html5，无法切换');
        }
        CKobject.getObjectById('ckplayer_a1').changeVolume(50);

        $(".demo").css({"height":height,"width":"1200px","display":"block"});
    }
    function downloadVideo(rId,vId)
    {
        $.get(urlHead +  '/video/Video_isDownload.do',{videoId:vId,rId:rId,hostIp:host_ip,recordType:record_type},function(data){
            if(data.success == false)
            {
                layer.msg(data.msg)
            }
            else
            {
                location.href = "${pageContext.request.contextPath}/video/Video_downloadVideo.do?rId=" + rId + "&videoId=" + vId + "&hostIp=" + host_ip + "&recordType=" + record_type;
            }
        });
    }

</script>


