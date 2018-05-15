
<%@ page import="java.util.*" %>
<%@ page import="com.honghe.recordhibernate.entity.ClasstimePloy" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE" />
    <meta HTTP-EQUIV="pragma" CONTENT="no-cache">
    <meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
    <meta HTTP-EQUIV="expires" CONTENT="0">
    <title>录播计划-课表--导入excel课表文件之前</title>
    <link href="${pageContext.request.contextPath}/css/frontend/hpager.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/index.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/fd-slider.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/schedule.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/frontend/time_celve.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <link id="skinlayercss" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/layer.css"></link>
    <script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
    <link id="skinlayerextcss" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/layer.ext.css">
    <script src="${pageContext.request.contextPath}/js/timeplan_modify.js" type="text/javascript"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.mCustomScrollbar.css">
    <script src="${pageContext.request.contextPath}/js/jquery.mCustomScrollbar.concat.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/lb_frontend/setting/setting.js"></script>

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
    .choosem > span,.chooseh > span{
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
        background:url(../../../image/frontend/colse_icon_imglist.png) no-repeat 0 0;
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
    .sall{
        /*	border:1px solid red;*/
        float: left;
        margin-left: 50px;
        margin-top:10px;
        line-height: 28px !important;
        width: 165px !important;
    }
    select{
        display: none;
        float: left;
        height: 28px !important;
        line-height: 28px !important;
        width: 165px !important;
    }
    .selectdivall{
        float: left;
        line-height: 28px !important;
        width: 165px !important;
    }
    .selectdiv{
        text-indent: 0px !important;
        cursor: pointer;
        float: left;
        height: 28px !important;
        line-height: 28px !important;
        width: 155px !important;
    }
    .selectdivall30{
        background: url(../../../image/frontend/n_icon_141016.png) -70px -93px no-repeat;
        float: left;
        height: 28px !important;
        line-height: 28px !important;
        width:165px !important;
    }
    #selectdivall{
        background: url(../../../image/frontend/n_icon_141006.png) 0 -302px no-repeat;
        float: left;
        height: 28px !important;
        line-height: 28px !important;
        width:165px !important;
    }
    /*#selectdivall0{
        background: url(../../image/frontend/n_icon_141006.png) 0 0 no-repeat;
        float: left;
        height: 36px;
        line-height: 36px;
        width: 115px;
    }*/
    .selectdivall{
        border: 1px solid #bdbdbd;
        background: url(../../../image/frontend/select_icon_imgct.png) 150px 10px no-repeat !important;
        float: left;
        height: 28px !important;
        line-height: 28px !important;
        width: 165px !important;
    }
    .selectdivul{
        margin-left: -1px;
        border:1px solid #bdbdbd;
        background: #fff;
        display: none;
        float: left;
        line-height: 28px !important;
        max-height: 140px !important;
        overflow-y: auto !important;
        overflow-x: hidden !important;
        position: relative;
        width: 165px !important;
        z-index: 10;
    }
    .selectdivul a{
        color:#333;
        /*	border:1px solid red;*/
        float: left;
        line-height: 28px !important;
        width: 165px !important;
    }
    #selectdivul1 a{width: 165px !important; }
    #selectdivul a{width: 165px !important;line-height: 28px !important;}
    .selectdivul a:link{
        color:#333;
    }
    .selectdivul a:visited{
        color:#333;
    }
    .selectdivul a:hover{
        background: #dbdbdb;
        color:#333;
    }
    .selectdivul a:active{
        background: #28b779;
        color:#fff;
    }
    .selectdivulbingo{
        background: #28b779;
        color:#fff;
    }
</style>
<body>
<div class="publics" style="background-color: #f2f2f2;min-width: 1680px;">
    <jsp:include page="/pages/frontend/lb_header.jsp"></jsp:include>
    <!-- 二级导航 -->
    <div class="settinghead" style="height:50px !important;border-bottom:1px solid #28b779;">
        <ul>
            <li onclick="javascript:jump(0)"><div class="tb"></div>台标</li>
            <li onclick="javascript:jump(1)"><div class="videoinfo"></div>录像信息</li>
            <li onclick="javascript:jump(2)"><div class="living"></div>直播</li>
            <li onclick="javascript:jump(3)"><div class="nas"></div>NAS存储设置</li>
            <li onclick="javascript:jump(4)"><div class="passuser"></div>录播主机用户名密码设置</li>
            <li onclick="javascript:jump(5)" ><div class="ftp"></div>FTP设置</li>
            <li onclick="javascript:jump(6)" ><div class="license"></div>License设置</li>
            <li onclick="javascript:jump(7)" ><div class="second_nav_selected"></div>课表类型选择</li>
            <li onclick="javascript:jump(8)"><div class="second_nav_schedule"></div>课表管理</li>
            <li onclick="javascript:jump(9)"  style="color:#28b779;" ><div class="second_nav_celve"></div>作息时间策略</li>
            <li onclick="javascript:jump(10)"><div class="record_name_setting"></div>录制文件名设置</li>
        </ul>
    </div>

    <div class="main_content">
        <%--删除作息时间策略--%>
        <div class="main_content_titleer">
            <span class="delete_title">删除作息时间策略
                <div class="add_delete_titleic">
                    <%
                        List<ClasstimePloy> classtimePloys = (List<ClasstimePloy>) request.getAttribute("classtimePloys");
                        for (ClasstimePloy classtimePloy : classtimePloys) {
                    %>
                    <div class="add_delete_titleicli">
                        <span></span><p value="<%=classtimePloy.getPloyName()%>"><%=classtimePloy.getPloyName()%></p>
                    </div>
                    <%
                        }
                    %>
                    <div class="bottom_iconict">
                        <span>取消</span>
                        <span onclick="del()">删除</span>
                    </div>
                </div>
            </span>
        </div>

        <%--显示作息时间策略--%>
        <div class="main_content_contl">
            <div class="main_content_contlst">
                <div class="main_content_contlst_title">
                    <span>分组名称</span>
                    <span>作息时间策略</span>
                    <span>操作</span>
                </div>

                <%
                    List<Object[]> ployGroups = (List<Object[]>) request.getAttribute("ployGroups");

                    for (int i=0; i<ployGroups.size(); i++) {
                %>
                <%--信息显示--%>
                <div class="main_content_contlst_li_tit">
                    <div class="main_content_contlst_li" id="<%=ployGroups.get(i)[0]%>">
                        <span><%=ployGroups.get(i)[1]%></span>
                    <span>
                        <div class="sall">
                            <select class="select" id="select<%=i%>">
                                <%
                                    if (ployGroups.get(i)[2] == null) {
                                %>
                                <option selected="selected" value="<%=ployGroups.get(i)[0]%>_null" nullName="">未分配作息策略<option>
                                     <%} else {%>
                                <option selected="selected" value="<<%=ployGroups.get(i)[0]%>_<%=ployGroups.get(i)[4]%>"><%=ployGroups.get(i)[2]%><option>
                                    <%}
                                %>
                                    <%
                                        for (ClasstimePloy classtimePloy : classtimePloys) {
                                             if (classtimePloy.getPloyId() == null) {
                                                   if (ployGroups.get(i)[2] == null) {
                                                        continue;
                                                    }
                                                %>
                                <option value="<%=ployGroups.get(i)[0]%>_<%=classtimePloy.getPloyId()%>"><%=classtimePloy.getPloyName()%><option>
                                    <%} else if (!classtimePloy.getPloyName().equals(ployGroups.get(i)[2])) {%>
                                <option value="<%=ployGroups.get(i)[0]%>_<%=classtimePloy.getPloyId()%>"><%=classtimePloy.getPloyName()%><option>
                                    <%}
                                        }
                                    %>
                            </select>

                            <div class="selectdivall" id="selectdivall<%=i%>">
                                <div class="selectdiv" id="selectdiv<%=i%>"></div>
                                <div class="selectdivul" id="selectdivul<%=i%>"></div>
                            </div>
                        </div>
                    </span>
                        <span><span class="modifyspan_imgicon"></span></span>
                    </div>
                    <%--新建时间策略--%>
                    <div class="add_wincelveneme">
                        <div class="add_wincelveneme_li">
                            <div class="add_wincelveneme_neme">
                                <span>作息时间策略名称：</span>
                                <%
                                    if (ployGroups.get(i)[2] == null) {
                                        %>
                                <input type="text" name="classtimePloyName" value="未分配作息策略" disabled="disabled"/>
                                    <%} else {%>
                                <input type="text" name="classtimePloyName" value="<%=ployGroups.get(i)[2]%> " disabled="disabled"/>
                                    <%}
                                %>
                            </div>
                            <div class="add_wincelveneme_neme">
                                <span>作息时间策略设置：</span>
                                <div class="add_wincelveneme_shez_ul">
                                    <form id="updateform_<%=ployGroups.get(i)[0]%>" method="post" action="/settings/Settings_updateTimePloy.do">
                                        <input type="hidden" name="classtimePloy" value="<%=ployGroups.get(i)[4]%>"/>
                                        <%
                                        String[] dates = {"星期一","星期二","星期三","星期四","星期五","星期六","星期日"};
                                        for (int k=0; k<dates.length; k++) {
                                        %>
                                                <div class="add_wincelveneme_shez_li">
                                            <div class="add_wincelveneme_shez_title">
                                                <span value="<%=(k+1)%>"><%=dates[k]%></span>
                                            </div>
                                            <div class="add_wincelveneme_shez_main">
                                                    <div class="timeplan" weekid="<%=k+1%>">
                                                        <div class="timeplanlist">

                                                        </div>
                                                    </div>
                                                <div class="add_wincelveneme_shez_main_lilast">
                                                    <span class="tm_add">添加一节课</span>
                                                    <span class="fzwbicon">复制本日到</span>
                                                    <div class="show_wintishi">
                                                        <%
                                                            String[] dates1 = {"星期一","星期二","星期三","星期四","星期五","星期六","星期日"};
                                                            for (int k1=0; k1<dates1.length; k1++) {
                                                        %>
                                                        <div class="show_wintishi_li">
                                                            <span></span>
                                                            <p><%=dates1[k1]%></p>
                                                        </div>
                                                        <%
                                                            }
                                                        %>
                                                        <div class="show_wintishi_li">
                                                            <a class="fuzhiicon">复制</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <%
                                            }
                                        %>
                                    </form>
                                </div>
                            </div>
                            <div class="add_wincelveneme_neme">
                                <span class="qdtdel" onclick="update(<%=ployGroups.get(i)[0]%>,this)">确定</span>
                                <span class="qvxdel">取消</span>
                            </div>
                        </div>
                    </div>
                </div>
                <%
                    }
                %>

                <div class="add_iconimgbig">
                    <span>新建作息时间策略</span>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        $(function(){
            $('.main_content').css('min-height',$(document).height()-20-$('.public_head').height()-50-$('.foot').height()-3+'px');

            $(document).on('click','span.delete_title',function(){
                var flag = $(this).find('.add_delete_titleic').css('display')
                if(flag=='none'){
                    $(this).find('.add_delete_titleic').show();
                    $('body').on('click',function(){
                        $('.add_delete_titleic').hide();
                    })
                }else{
                    $(this).find('.add_delete_titleic').hide();
                }
                return false;
            })
            function checkbox(){
                $('.add_delete_titleicli > span').live('click',function(){
                    var flage = $(this).attr('class')
                    if(flage == 'global_checkbox'){
                        $(this).removeClass();
                    }else{
                        $(this).attr('class','global_checkbox')
                    }
                })
            }
            checkbox();
            $(document).on('click','.bottom_iconict span',function(){
                $(this).parent().parent().hide();
                var flagit = $(this).text();
                if(flagit == "删除"){
                    $(".add_delete_titleicli").each(function(){
                        var flat = $(this).find("span").attr("class");
                        if(flat == "global_checkbox"){
                            var globtext =  $(this).text().trim();
                            $(".selectdiv").each(function(){
                                var selectdiv_txt =  $(this).text().trim();
                                if(selectdiv_txt == globtext){
                                    $(this).html("未分配作息策略")
                                }
                            })
                            $(".selectdivul").find("a").each(function(){
                                var seletext =  $(this).text().trim();
                                if(globtext==seletext){
                                    $(this).remove();
                                }else{
                                }
                            })
                            $(this).remove();
                        }else{
                        }
                    })
                }else{
                }
                return false;
            })


            $('.modifyspan_imgicon').live('click',function(){
                $(".add_wincelveneme").slideUp();
                var isNull = $(this).parents(".main_content_contlst_li").find("option[nullname]");
                if(isNull!=null&&isNull.length!=0){
                    layer.msg("请先分配作息策略");
                    return;
                }
                var groupid = $(this).parents(".main_content_contlst_li").attr("id");
                var flages = $(this).parents(".main_content_contlst_li_tit").find(".add_wincelveneme").css("display")
                if(flages == "none"){
                    $(this).parents(".main_content_contlst_li_tit").find(".add_wincelveneme").slideDown();
                }else{
                    $(this).parents(".main_content_contlst_li_tit").find(".add_wincelveneme").slideUp();
                }
                $(".add_iconimgbig").show();
                $(".main_content_contlst > div.add_wincelveneme").remove();

                var timePloy = $(this).parents(".main_content_contlst_li").next().find("input[name='classtimePloyName']").attr("value");
                $.post(
                        "${pageContext.request.contextPath}/settings/Settings_getAllClassTime.do",
                        {timePloy:timePloy},
                        function(jsonObj) {
                            if (jsonObj.flag == true) {
                                var dataObj = jsonObj.data;

                                // 接受到的日期
                                //  var week = dataObj[0][2];
                                var weeks = new Array(1, 2, 3, 4, 5, 6, 7);
                                var chapters = new Array("一","二","三","四","五","六","七","八","九","十","十一","十二","十三","十四","十五","十六","十七","十八","十九","二十","二十一");

                                var htmlStr = '';
                                if (dataObj != null) {
                                    $(".timeplanlist").find(".tm_class").remove();
                                    var flag = 1;
                                    $.each(dataObj, function(i, v) {
                                        //如果当前这条与下一条不在一天,flag置为0
                                        if(i < dataObj.length-1 && dataObj[i][0] != dataObj[i+1][0])
                                        {
                                            flag = 0;
                                        }
                                        else if(i==dataObj.length-1 )
                                        {
                                            flag = 0;
                                        }
                                        else
                                        {
                                            flag = 1;
                                        }
                                        var index = v[2].indexOf(":");
                                        var starttime = v[2].substring(0, index);
                                        var starttime_1 = v[2].substring(index+1, v[2].length);
                                        var endtime = v[3].substring(0, index);
                                        var endtime_1 = v[3].substring(index+1, v[3].length);

                                        htmlStr = '<div class="tm_class">'+
                                        '<div class="tm_hour">'+starttime+'</div><input type="hidden" name="starttime" class="hourtime" value="'+starttime+'"/>'+
                                        '<div class="tm_mh">:</div>'+
                                        '<div class="tm_minites">'+starttime_1+'</div><input type="hidden" name="starttime_1" class="minutetime" value="'+starttime_1+'"/>'+
                                        '<div class="chooseh"></div>'+
                                        '<div class="choosem"></div>';
                                        $.each(chapters, function(item) {
                                            if (item == (v[1]-1)){
                                                htmlStr += '<div class="chapter">第'+chapters[item]+'节</div>';
                                                return;
                                            }
                                        });
                                        htmlStr += '<input type="hidden" name="section_'+v[0]+'" value="'+v[1]+'" class="section_val"/>'+
                                        '</div>'+
                                        '<div class="tm_class" style=margin-left:28px>'+
                                        '<div class="tm_line">~</div>'+
                                        '<div class="tm_hour">'+endtime+'</div><input type="hidden" name="endtime" class="hourtime" value="'+endtime+'" />'+
                                        '<div class="tm_mh">:</div>'+
                                        '<div class="tm_minites">'+endtime_1+'</div><input type="hidden" name="endtime_1" class="minutetime" value="'+endtime_1+'" />'+
                                        '<div class="chooseh"></div>'+
                                        '<div class="choosem"></div>';

                                        if (flag == 0) {
                                            htmlStr +='<div class="chapterdel" style="display: block;"></div>';
                                        }else{
                                            htmlStr +='<div class="chapterdel" style="display:none;"></div>';
                                        }
                                        /*htmlStr +='</div>';*/
//                                        '</div>'
                                        // 遍历周期数组, 找到与后台相匹配的周期进行页面填充
                                        $.each(weeks, function(k, week) {
                                            if(week == v[0]) {
                                                $("#"+groupid).next(".add_wincelveneme").find(".timeplanlist").eq(k).parents(".timeplan").attr("id",v[2]);
                                                $("#"+groupid).next(".add_wincelveneme").find(".timeplanlist").eq(k).append(htmlStr);
                                            };
                                        });
                                    });
                                }
                            }
                        },
                        "json"
                );

            })
//            $(".add_wincelveneme_neme span.qdtdel").live("click",function(){
//                $(this).parents(".add_wincelveneme").slideUp();
//                $(this).parents(".main_content_contlst_li_tit").find(".main_content_contlst_li").show();
//                $(".add_iconimgbig").show();
//                $(".main_content_contlst > div.add_wincelveneme").remove();
//            })
            $(".add_wincelveneme_neme span.qvxdel").live("click",function(){
                $(this).parents(".add_wincelveneme").slideUp();
                $(this).parents(".main_content_contlst_li_tit").find(".main_content_contlst_li").show();
                $(".add_iconimgbig").show();
                $(".main_content_contlst > div.add_wincelveneme").remove();
            })
            $(".add_wincelveneme_shez_title").live("click",function(){
                $(".add_wincelveneme_shez_main").slideUp();
                var flag = $(this).parents(".add_wincelveneme_shez_li").find(".add_wincelveneme_shez_main").css("display")
                if(flag == "none"){
                    $(this).parents(".add_wincelveneme_shez_li").find(".add_wincelveneme_shez_main").slideDown();
                }else{
                    $(this).parents(".add_wincelveneme_shez_li").find(".add_wincelveneme_shez_main").slideUp();
                }
            })
            $("span.fzwbicon").live("click",function(){
                var fsx = $(this).parents(".add_wincelveneme_shez_li").find(".add_wincelveneme_shez_title").find("span:eq(0)").text();
                $(this).parent().find(".show_wintishi_li").each(function(){
                    var fax =    $(this).find("p").text()
                    if(fax == fsx ){
                        $(this).hide();
                    }
                })
                var flase = $(this).parents(".add_wincelveneme_shez_main_lilast").find(".show_wintishi").css("display")
                if(flase == "none"){
                    $(this).parents(".add_wincelveneme_shez_main_lilast").find(".show_wintishi").show();
                    $(this).parents(".add_wincelveneme_shez_li").find(".fuzhiicon").on("click",function(){
                        var tm_leng = $(this).parents(".add_wincelveneme_shez_li").find(".tm_class")
                        var hasca_leng = $(this).parents(".show_wintishi").find(".show_wintishi_li").find("span")
                        var zong_xing = $(this).parents(".add_wincelveneme_shez_ul").find(".add_wincelveneme_shez_li")
                        if (tm_leng.length != 0) {
                            var htmlstr_ =  $(this).parents(".add_wincelveneme_shez_li").find(".timeplan").html();
                            hasca_leng.each(function(){
                                var hasca_leng_i =  $(this).hasClass("global_checkbox")
                                var hasca_leng_i_text = $(this).parents(".show_wintishi_li").find("p").text();
                                zong_xing.find(".add_wincelveneme_shez_title").each(function(){
                                    var zong_xing_i = $(this).find("span").text();
                                    if(hasca_leng_i == true){
                                        if(zong_xing_i==hasca_leng_i_text){
                                            $(this).parents(".add_wincelveneme_shez_ul").find(".add_wincelveneme_shez_title").each(function(){
                                                var title_teti = $(this).find("span").text();
                                                if(zong_xing_i ==title_teti || hasca_leng_i_text == title_teti ){
                                                    $(this).parents(".add_wincelveneme_shez_li").find(".timeplan").html(htmlstr_);
                                                    var this_val = $(this).parents(".add_wincelveneme_shez_li").find(".timeplan").attr("weekid");
                                                    // console.log(this_val);
                                                     $(this).parents(".add_wincelveneme_shez_li").find(".tm_class").find(".section_val").attr("name","section_"+this_val)
                                                }else{

                                                }
                                            })
                                        } else{

                                        }

                                    }else{

                                    }
                                });

                            })
                        } else {

                        }

                        $(this).parents(".show_wintishi").hide();
                        var str = "";
                        str = "<div class='win_tishi_i'>"+
                        "<span>复制成功！</span>"
                        "</div>"
                        var flagt = $(".win_tishi_i").length;
                        if(flagt == 0 ){
                            $(this).parents(".main_content_contlst_li_tit").append(str);
                            setTimeout(function () {
                                $(".win_tishi_i").remove();
                            }, 1000);
                        }else{
                            $(this).parents(".main_content_contlst_li_tit").append(str);
                            $(".win_tishi_i").find("span").html("复制失败！")
                            setTimeout(function () {
                                $(".win_tishi_i").remove();
                            }, 1000);
                        }

                        $(this).attr("class","fuzhiiconed")
                    })

                }else{
                    $(this).parents(".add_wincelveneme_shez_main_lilast").find(".show_wintishi").hide();
                }

            })
            $(".show_wintishi_li span").live("click",function(){
                var flag = $(this).attr("class")
                var x = $(this).parent().index();
                if(flag == "global_checkbox"){
                    $(this).removeClass();
                }else{
                    $(this).attr('class','global_checkbox');
                }
            })
            /*添加时间策略*/
            $(document).on('click','.add_iconimgbig',function(){
                $(".add_wincelveneme").slideUp();
                var add_licon_leng = $(".main_content_contlst_li").length;
                var dates = ["星期一", "星期二", "星期三", "星期四","星期五","星期六","星期日"];
                var str = "";
                str = '<div class="add_wincelveneme"><form id="addform" method="post" action="/settings/Settings_addTimePloy.do"><div class="add_wincelveneme_li content mCustomScrollbar light"><div class="add_wincelveneme_neme"><span>作息时间策略名称：</span><input type="text" name="timePloy" id="timePloy" maxlength="20"></div>' +
                '<div class="add_wincelveneme_neme"><span>作息时间策略设置：</span><div class="add_wincelveneme_shez_ul"><div class="add_wincelveneme_shez_li">';

                $(dates).each(function(i,v){
                    str += '<div class="add_wincelveneme_shez_title"><span id="week_'+(i+1)+'" value="'+(i+1)+'" class="week_val">'+v+'</span></div><div class="add_wincelveneme_shez_main"><div class="add_wincelveneme_shez_main_li">' +'<div class="timeplan" weekid="'+(i+1)+'"><div class="timeplanlist"></div></div>'+
                    '</div><div class="add_wincelveneme_shez_main_lilast"><span class="tm_add">添加一节课</span><span class="fzwbicon">复制本日到</span>'+
                    '<div class="show_wintishi">' +

                    '<div class="show_wintishi_li"><span></span><p>星期一</p></div>'+
                    '<div class="show_wintishi_li"><span></span><p>星期二</p></div>' +
                    '<div class="show_wintishi_li"><span></span><p>星期三</p></div>' +
                    '<div class="show_wintishi_li"><span></span><p>星期四</p></div>' +
                    '<div class="show_wintishi_li"><span></span><p>星期五</p></div>' +
                    '<div class="show_wintishi_li"><span></span><p>星期六</p></div>' +
                    '<div class="show_wintishi_li"><span></span><p>星期日</p></div>'+
                    '<a class="fuzhiicon">复制</a>'+
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '<div class="add_wincelveneme_shez_li">';
                });

                str += '<span class="qdtdel" onclick="sub(this)">确定</span><span class="qvxdel">取消</span></div></div></form></div>';
                var li_length_ma = $(".main_content_contlst_li_tit").length;
                var add_win = $(".main_content_contlst > div.add_wincelveneme").length;

                if(add_win == 0 ){
                    if(li_length_ma == 0){
                        $(".main_content_contlst_title").after(str);
                        $(".main_content_contlst > div.add_wincelveneme").slideDown();
                    }else{
                        $(".main_content_contlst_li_tit:last").after(str);
                        $(".main_content_contlst > div.add_wincelveneme").slideDown();
                    }
                }

                $(this).hide();
            })
        })

        // 提交表单
        function sub(obj) {
            $.ajax({
                url: "${pageContext.request.contextPath}/settings/Settings_addTimePloy.do",
                type: "POST",
                dataType: "json",
                data: $("#addform").serialize(),
                beforeSend:function() {
                    var text = $("#timePloy").val().trim();
                    if (text == "") {
                        layer.msg("请填写作息时间策略名称");
                        return false;
                    }
//                    if (text.length > 20) {
//                        layer.msg("填写作息时间策略名称过长,请重新填写")
//                        return false;
//                    }
                },

                success: function(jsonObj){
                    if (jsonObj.flag == true) {
                        //添加成功收起时间span
                        $(obj).parents(".add_wincelveneme").slideUp();
                        $(obj).parents(".main_content_contlst_li_tit").find(".main_content_contlst_li").show();
                        $(".add_iconimgbig").show();
                        $(".main_content_contlst > div.add_wincelveneme").remove();

                        layer.msg(jsonObj.msg);
                        /*添加新的时间策略*/
                        var str = '';
                        var ployId = jsonObj.ployId;
                        // 2_2_2 p2g_id groupid ployid
                        str += '<a value="'+ployId+'" title="'+jsonObj.data+'" style="color: rgb(51,51,51); background: rgb(255,255,255) none repeat scroll 0% 0%;">'+jsonObj.data+'</a>';
                        $(".selectdivul").append(str);

                        var str1 = '';
                        str1 += '<div class="add_delete_titleicli"><span></span><p value="'+jsonObj.data+'">'+jsonObj.data+'</p></div>';
                        if($(".add_delete_titleicli").length == 0){
                                $(".bottom_iconict").before(str1);
                        }else {
                            $(".add_delete_titleicli:last").after(str1);
                        }
                        checkbox();
                    } else {
                        layer.msg(jsonObj.msg);
                    }
                }
            });
        };

        // 切换select
        $(".selectdivall a").live('click',function(){
            var val = $(this).attr("value");
            // 2_2_2 p2g_id groupid ployid
            // var p2gId = val.substr(0,val.indexOf("_")); //组跟策略关系id
            // var groupId = val.substr(val.indexOf("_")+1,1); // 组id
            var groupId = $(this).parents(".main_content_contlst_li").attr("id");
            var ployId = val.substring(val.lastIndexOf("_")+1); // 策略id

           $.post(
                "${pageContext.request.contextPath}/settings/Settings_updateGroupIdAndPloyId.do",
                //{p2gId:p2gId, groupId:groupId, ployId:ployId},
                {groupId:groupId, ployId:ployId},
                function(){
                    window.location.reload();
                }
            );
       });

        // 修改时间策略
       function update(groupid,obj) {
           $.ajax({
               url: "${pageContext.request.contextPath}/settings/Settings_updateTimePloy.do",
               type: "POST",
               dataType: "json",
               data: $("#updateform_"+groupid).serialize(),
               success: function(jsonObj){
                   if (jsonObj.flag == true) {
                       //修改成功收起时间span
                       $(obj).parents(".add_wincelveneme").slideUp();
                       $(obj).parents(".main_content_contlst_li_tit").find(".main_content_contlst_li").show();
                       $(".add_iconimgbig").show();
                       $(".main_content_contlst > div.add_wincelveneme").remove();

                       layer.msg(jsonObj.msg);
                       $(".timeplan").each(function(){
                           if($(this).attr("weekid") == $(this).attr("weekid")){
                                $(this).find(".tm_class").remove();
                           }
                       })

                   } else {
                       layer.msg(jsonObj.msg);
                   }
               }
           });
       }

        // 删除时间策略
        function del() {
            var timeploys = new Array();
            $(".global_checkbox").next().each(function() {
                timeploys.push($(this).text());
            });

            $.post(
                    "/settings/Settings_delTimePloy.do",
                    {timeploys: timeploys.toString()},
                    function(jsonObj){
                        if (jsonObj.flag == null) {
                            layer.msg(jsonObj.msg);
                        } else {
                            layer.msg(jsonObj.msg);
                        }
                    },
                    "json"
            );
        }
        $(document).on("mouseover",".selectdivul a",function(){
            $(this).css("background","#dbdbdb")
        })
        $(document).on("mouseleave",".selectdivul a",function(){
            $(this).css("background","#fff")
        })

    </script>
    <div class="foot">
        <jsp:include page="/pages/frontend/footer.jsp"></jsp:include>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/js/common/selectmore.js"></script>

</html>