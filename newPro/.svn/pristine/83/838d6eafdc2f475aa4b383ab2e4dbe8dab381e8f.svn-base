
<%@ page import="com.honghe.recordweb.action.SessionManager" %>
<%@ page import="com.honghe.recordhibernate.dao.Page" %>
<%@ page import="com.honghe.recordhibernate.entity.Temporaryplan" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
    <title>录像计划 | 集控平台</title>
    <script>
        var url = "${pageContext.request.contextPath}";
    </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/jquery.cookie.js" type="text/javascript"></script>
<%--    <script src="${pageContext.request.contextPath}/js/lb_common/screendetailforrecord.js"></script>--%>
	<script src="${pageContext.request.contextPath}/js/common/selectone.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/scrolldemo.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/laydate/laydate.js"></script>
    <%--<script src="${pageContext.request.contextPath}/layer-v2.4/layer/layer.js"></script>--%>
    <%--<script src="${pageContext.request.contextPath}/layer-v2.4/layer/skin/layer.css"></script>--%>
    <%--<script src="${pageContext.request.contextPath}/js/layer-v2.4/layer/layer.js"></script>--%>
    <link href="${pageContext.request.contextPath}/css/win_planclass_div.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/frontend/time_celve_two.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.mCustomScrollbar.css">
    <script src="${pageContext.request.contextPath}/js/common/selectmore.js"></script>
    <script src="${pageContext.request.contextPath}/js/lb_frontend/tempplan/tempplan.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.mCustomScrollbar.concat.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/schedule.css" rel="stylesheet" type="text/css">
    <%--<link href="${pageContext.request.contextPath}/css/frontend/time_celve.css" rel="stylesheet" type="text/css">--%>
</head>
<style>
    .tm_class{
        border:1px solid #dbdbdb;
        background: #fff;
        float: left;
        height: 23px;
        line-height: 23px;
        margin-right: 12px;
        margin-bottom: 6px;
        margin-top: 6px;
        position: relative;
        padding: 5px 0px;
        width: 120px;
        margin-left:75px;
    }
    .tm_warn_add,.tm_warn_starttime,.tm_warn_set,.tm_warn_setstart,.tm_warn_setover{
        color: #f00;
        display: none;
        float: left;
        height: 23px;
        line-height: 23px;
        width: 100%;
    }
    .tm_hour,.tm_minites,.tm_mh{
        cursor:pointer;
        float: left;
        height: 23px;
        line-height: 23px;
        min-width: 30px;
        padding: 0 5px;
        text-align: center;
        width: auto;
    }
    .tm_line{
        line-height: 23px;
        left: -28px;
        position: absolute;
        width: 28px;
    }
    .chapter{
        height: 23px;
        left: -70px;
        position: absolute;
        width: 60px;
    }
    .chooseh{
        border:1px solid #dbdbdb;
        background: #fff;
        cursor:pointer;
        display: none;
        height: 100px;
        top:47px;
        left: -1px;
        position: absolute;
        width:200px;
        z-index: 2;
    }
    .choosem{
        background: #fff;
        border:1px solid #dbdbdb;
        cursor:pointer;
        display: none;
        height: 225px;
        position: absolute;
        top:47px;
        left: -1px;
        z-index: 2;
        width:200px;
    }
    .choosem span,.chooseh span{
        height: 25px;
        cursor:pointer;
        float: left;
        line-height: 25px;
        margin-left: 2px;
        text-align: center;
        width: 25px;
    }
    .selected{
        background: #ccc;
    }
    .vip{
        min-height: 100px;
    }
    .vip_addbtn_frame{
        border:1px dashed #dbdbdb;
        cursor: pointer;
        height: 47px;
        margin-top: 3px;
        margin-bottom: 30px;
        width: 442px;
    }
    .vip_donebtn_frame{
        border:1px dashed #28B779;
        background: #28B779;
        border-radius: 5px;
        color: #fff;
        cursor: pointer;
        float: left;
        height: 47px;
        line-height: 47px;
        outline: none;
        text-align: center;
        width: 442px;
    }
    .vip_center_frame{
        border:1px solid #dbdbdb;
        background: #fff;
        height: 47px;
        margin-bottom: 8px;
        width: 442px;
    }
    .chapterdel{
        background:url(${pageContext.request.contextPath}/image/frontend/colse_icon_imglist.png) no-repeat 0 0;
        cursor: pointer;
        display: none;
        float: right;
        height: 16px;
        position: absolute;
        right: -30px;
        top:9px;
        width: 16px;
    }
    .timeplan{
        float: left;
        width: 100%;
    }
    .timeplanlist{
        float: left;
        width: 100%;
    }
</style>
<body>
<input type="hidden" id="whichscroll"> 
<div class="public" <%--style="height: auto;width: auto !important;"--%>>
    <jsp:include page="/pages/frontend/lb_header.jsp"></jsp:include>
    <div class="second_nav">
        <ul>
            <li onclick="history.go(-1);"><span class="user_head_return_icon"></span><a href="javascript:void(0)">返回</a></li>
        </ul>
    </div>

    <div class="main_content" style="margin-bottom:0;">
        <div class="main_content_titleer">
        </div>
        <div class="main_content_contl">
            <div class="main_content_contlstd">
                <div class="main_content_contlst_titled">
                    <span>临时录像计划名称</span>
                    <span>时间</span>
                    <span>选择班级</span>
                    <span>操作</span>
                </div>
                <%List<Temporaryplan> tempList = (List<Temporaryplan>)request.getAttribute("tempList");
                    for(Temporaryplan temp : tempList){
                %>
                <div class="main_content_contlst_li_tit">
                    <div class="main_content_contlst_lid">
                        <span><%=temp.getName()%></span>
                        <span style="text-overflow:ellipsis;white-space:nowrap;overflow:hidden;" title="<%=temp.getTimeStart()%> — <%=temp.getTimeEnd()%>"><%=temp.getTimeStart()%> — <%=temp.getTimeEnd()%></span>
                        <span style="color:#28b779;cursor: pointer;" tempId="<%=temp.getTemporaryplanId()%>">查看</span>
                        <span><span class="modifyspan_imgicon" tempId="<%=temp.getTemporaryplanId()%>" tempName="<%=temp.getName()%>" time_start="<%=temp.getTimeStart()%>" time_end="<%=temp.getTimeEnd()%>"></span>
                            <span class="delete_icontd" tempId="<%=temp.getTemporaryplanId()%>"></span></span>
                    </div>
                </div>
                <%}%>
                <div class="add_iconimgbig" onclick="addTemp();">
                    <span>新建临时录像计划</span>
                </div>

            </div>
            <div class="win_planclass_div" style="border:1px solid  #000;">
                <div class="win_planclass_divli">
                    <span class="planclass_tit">计划名称：</span>
                    <input class="win_planclass_divli_name" type="text">
                </div>
                <div class="win_planclass_divli">
                    <span class="planclass_tit">起止时间：</span>
                    <input id="start" class="win_planclass_input_time">
                    <span class="planclass_tit_time">至</span>
                    <input id="end" class="win_planclass_input_time">
                </div>
                <div class="win_planclass_divli">
                    <div class="chico_class">
                        <span>选择班级</span>
                        <div class="chico_class_title mCustomScrollbar" id="addClass">
                        </div>
                    </div>
                    <div class="chico_conten">
                        <span></span>
                    </div>
                    <div class="chico_class">
                        <span>临时录像计划班组</span>
                        <div class="chico_class_titletwo mCustomScrollbar">
                        </div>
                    </div>
                </div>
                <div class="win_planclass_bottom">
                    <span>完成</span>
                    <span>取消</span>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        var start = {
            elem: '#start',
            format: 'YYYY-MM-DD hh:mm:ss',
            festival: true, //是否显示节日
            istoday: false,
            istime: true, //是否开启时间选择
            choose: function(datas){
                var end = $("#end").val();
                if(end!=null&&end!=''){
                    var startTime = newDateAndTime(datas);
                    var endTime = newDateAndTime(end);
                    var result =startTime<endTime;
                    console.log(result);
                    if(!result){
                        $("#start").val('');
                        layer.msg("开始时间不能晚于结束时间");
                    }
                }
            }
        };
        var end = {
            elem: '#end',
            format: 'YYYY-MM-DD hh:mm:ss',
            festival: true, //是否显示节日
            istoday: false,
            istime: true, //是否开启时间选择
            choose: function(datas){
                var start =$("#start").val();
                if(start!=null&&start!=''){
                    var startTime = newDateAndTime($("#start").val());
                    var endTime = newDateAndTime(datas);
                    var result =startTime<endTime;
                    console.log(result);
                    if(!result){
                        $("#end").val('');
                        layer.msg("结束时间不能早于开始时间");
                    }
                }
            }
        };
        laydate(start);
        laydate(end);
        $(".chico_class_title a:not([hasSetNas=false])").live("click",function(){
            var a_text = $(this).text();
            var hostId = $(this).attr("hostId");
            if($(".chico_class_titletwo a").length == 0){
                $(".chico_class_titletwo .mCSB_container").append('<a href="javascript:void(0)" hostId="'+hostId+'">'+a_text+'</a>');
            }else{
                $(".chico_class_titletwo a:first").before('<a href="javascript:void(0)" hostId="'+hostId+'">'+a_text+'</a>');
            }
            $(this).remove();
        })
        $(".chico_class_titletwo a").live("click",function(){
            var a_text = $(this).text();
            var hostId = $(this).attr("hostId");
//            alert(hostId);
            if($(".chico_class_title a").length == 0){
                $(".chico_class_title .mCSB_container").append('<a href="javascript:void(0)" hostId="'+hostId+'">'+a_text+'</a>');
            }else{
                $(".chico_class_title a:first").before('<a href="javascript:void(0)" hostId="'+hostId+'">'+a_text+'</a>');
            }
            $(this).remove();
        })
        $(".chico_class a").each(function(){
            var a_txt = $(this).text();
            if(a_txt.length > 8 ){
                $(this).text(a_txt.substring(0,8)+"...");
            }else{
                $(this).text(a_txt);
            }
        })


        function newDateAndTime(dateStr){
            var ds = dateStr.split(" ")[0].split("-");
            var ts = dateStr.split(" ")[1].split(":");
            var r = new Date();
            r.setFullYear(ds[0],ds[1] - 1, ds[2]);
            r.setHours(ts[0], ts[1], ts[2], 0);
            return r;
        }
    </script>
    <div class="foot">
        <jsp:include page="/pages/frontend/footer.jsp"></jsp:include>
    </div>
</div>
</body>


</html>




