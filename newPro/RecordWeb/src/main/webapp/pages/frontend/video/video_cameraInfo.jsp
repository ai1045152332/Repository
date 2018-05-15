<%@ page import="java.net.URLDecoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <title>预览</title>


    <link href="${pageContext.request.contextPath}/css/frontend/record/hpager.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/index.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/main.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/fd-slider.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/common/jquery-1.8.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/checkbox.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/ckplayer/ckplayer.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/offlights.js"></script>
    <style>
        .all {
            max-width: 200px;
            width: auto;
            float: left;
        }

        .head1, .head {
            margin-top: 5px;
        }

        body {
            overflow: hidden;
            top: 0;
            position: relative;
        }

        .sall {
            margin-left: 20px;
            width: 185px;
        }

        .selectdiv {
            background: none;
        }

        #selectdivall0, #selectdivall1, #selectdivall2, #selectdivall3, #selectdivall4, #selectdivall5, #selectdivall6 {
            background: none;
            width: 184px;
        }

        .selectdiv {
            background: url(${pageContext.request.contextPath}/image/frontend/n_icon_141016.png) 0 0 no-repeat;
            width: 184px;
            height: 27px;
            line-height: 27px;
        }

        .selectdivul {
            width: 182px;
        }

        .selectdivul a {
            /*text-align: center;*/
            text-indent: 10px;
            width: 182px;
        }

        #selectdivul0, #selectdivul1, #selectdivul2, #selectdivul3, #selectdivul4, #selectdivul5, #selectdivul6 {
            border: 1px solid #dbdbdb;
            margin-top: -2px;
            width: 182px;
        }

        #selectdivul1 {
            width: 182px;
        }

        #selectdivul1 a {
            width: 182px;
        }

        .jk_condition_check {
            cursor: pointer;
            float: left;
            line-height: 30px;
            margin-left: 10px;
            text-overflow: ellipsis;
            white-space: nowrap;
            overflow: hidden;
            width: 190px;
        }
        body,td,th {
            font-size: 14px;
            line-height: 26px;
        }
        body {
            margin-left: 0px;
            margin-top: 0px;
            margin-right: 0px;
            margin-bottom: 0px;
        }
        p {
            margin-top: 5px;
            margin-right: 0px;
            margin-bottom: 0px;
            margin-left: 0px;
            padding-left: 10px;
        }
        #a1{
            position:relative;
            z-index: 100;
            width:800px;
            height:480px;
            float: left;
        }
    </style>
</head>
<body>
<%
    String mediaUrl = request.getAttribute("mediaUrl").toString();
    //mediaUrl = "http://localhost:8080/11.mp4";
    /// System.out.println("mediaUrl="+mediaUrl);

%>
<div class="win520_content" style="height:268px;box-shadow: 0 0 0 1px #212121 inset;">
    <%--
      <div style="margin: 0 auto;width: 396px;">
        <div class="xk_video" style="background:white;position: relative;margin-left: 0;margin-top: 38px;" bingo="none">
    --%>

    <div id="a1"></div>
    <script type="text/javascript">
        var mediaUrl ="<%=mediaUrl%>";
       // alert(mediaUrl);

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
        //   CKobject.embed('ckplayer/ckplayer.swf','a1','ckplayer_a1','100%','100%',false,flashvars,video,params);

        if(CKobject.isHTML5()){
            support=['all'];
            CKobject._K_('a1').innerHTML='';
            CKobject.embedHTML5('a1','ckplayer_a1','100%','100%',video,flashvars,support);
        }
        else{
            CKobject._K_('a1').innerHTML='';
            CKobject.embed('/ckplayer/ckplayer.swf','a1','ckplayer_a1','100%','100%',false,flashvars,video,params);
            //alert('该环境不支持html5，无法切换');
        }
        CKobject.getObjectById('ckplayer_a1').changeVolume(50);
    </script>
    <%--   </div>

   </div>--%>
</div>

</body>
</html>



