<%@ page import="com.honghe.recordweb.action.SessionManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE" />
    <meta HTTP-EQUIV="pragma" CONTENT="no-cache">
    <meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
    <meta HTTP-EQUIV="expires" CONTENT="0">
    <title>直播计划</title>
    <link href="${pageContext.request.contextPath}/css/schedule.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/direct_seed.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/frontend/time_celve.css" rel="stylesheet" type="text/css">
    <link id="skinlayercss" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/layer.css"></link>
    <link id="skinlayerextcss" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/layer.ext.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.mCustomScrollbar.css">
    <link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/laydate/laydate.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
    <%--<script src="${pageContext.request.contextPath}/js/directplan_modify.js" type="text/javascript"></script>--%>
    <script src="${pageContext.request.contextPath}/js/jquery.mCustomScrollbar.concat.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/selectone.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/liveplan.js"></script>
</head>
<style>

</style>
<body>
    <div class="public" style="background-color: #f2f2f2; height: auto;">
            <jsp:include page="/pages/frontend/lb_header.jsp"></jsp:include>
        <!-- 二级导航 -->
        <div class="vip">
            <div class="vipt_head" style="height: 35px;line-height: 35px;background: #fff;">
                <div class="vipt_headtext" style="height: 35px; line-height: 35px;">注：鼠标点击选中，可增加或者取消直播计划。</div>
            <span id="clean" class="vipt_head_option" style="margin:0;">
                <a href="javascript:void(0)" onclick="clearPlan()">
                    <span class="vipt_head_clean"></span>清空所有直播计划
                </a>
            </span>
                <%
                    com.honghe.recordhibernate.entity.User user =  SessionManager.get(request.getSession(), SessionManager.Key.USER);
                    boolean isSeting = user.getUser_salt().equals("true");
                    Setting setting = (Setting) request.getAttribute("setting");
                    int settingType;
                    if (setting == null) {
                        settingType = Curriculum.CUR_WEEK_TYPE;
                    }
                    else
                    {
                        settingType = setting.getCurriculumType();
                    }
                %>
            </div>
            <input type="hidden" value="<%=isSeting%>" id="isSetting">
            <div id="vipt_video" style="float: left;/*margin-bottom: 50px;*/overflow:auto;">
                <div class="vapptvid_title" style="float:left;width:100%;height: 30px;line-height:30px;"></div>
                <%--头信息--%>
                <div class="vipt_week" id="vipt_week" style="float: left;width: auto;">
                    <%--<span class="cheice_icon"></span>--%>
                    <div class="sall" style="margin: 0;width: 155px;height: 46px;float:left;" id="sall">
                        <%
                            Map<Integer, String> intToUpper_1 = (Map<Integer, String>) request.getAttribute("intToUpper");
                            if(settingType == Curriculum.CUR_WEEK_TYPE)
                            {
                        %>
                        <select id="week_id" class="select">
                            <%
                                int week_id_ajax = Integer.parseInt(request.getAttribute("week_id").toString());
                                for(int a=1;a<=7;a++)
                                {
                                    if(a == week_id_ajax)
                                    {
                                        if(a==7) //选中状态
                                        {
                            %>week_id
                            <option value="<%=a%>" selected="selected">星期日</option>
                            <%
                            }
                            else
                            {
                            %>
                            <option value="<%=a%>" selected="selected">星期<%=intToUpper_1.get(a)%></option>
                            <%
                                }
                            }
                            else
                            {
                                if(a==7)
                                {
                            %>
                            <option value="<%=a%>">星期日</option>
                            <%
                            }
                            else
                            {
                            %>
                            <option value="<%=a%>">星期<%=intToUpper_1.get(a)%></option>
                            <%
                                        }
                                    }
                                }
                            %>
                        </select>
                        <style>
                            .selectdiv,#selectdivul{
                                color: #333;
                                font-weight: bold;
                            }
                            #selectdivul{
                                height: 320px;
                                max-height: 320px;
                            }
                        </style>
                        <div class="selectdivall" id="selectdivall">
                            <div class="selectdiv" id="selectdiv" style="width: 155px;height: 46px;line-height: 46px;text-align: left;text-indent: 40px;"></div>
                            <div class="selectdivul" id="selectdivul" style="margin: 0;width: 155px;line-height: 46px;height:320px;overflow:hidden;text-indent: 40px;"></div>
                        </div>
                        <%--</div>--%>
                        <%
                        }
                        else
                        {
                            String date = (String)request.getAttribute("date");
                        %>
                        <input id="datashow" class="laydate-icon" value="<%=date%>" style="width: 132px;height: 42px;text-align: center;font-size: 14px;">
                        <%
                            }
                        %>
                    </div>
                    <%
                        List<Integer> sectionList = (List<Integer>) request.getAttribute("sectionList");
                        for (int i = 1; i <= sectionList.size(); i++) //共多少节课
                        {
                    %>
                    <div class="vipt_node">第<%=intToUpper_1.get(i)%>节</div>
                    <%
                        }
                    %>
                </div>
                <div id = "timeplan_list" style="float: left;/*overflow: hidden*/">
                    <%@ include file="/pages/frontend/liveplan/liveplanlist.jsp" %>
                </div>
               <%-- <div class="scroll_ymove">
                    <div class="scroll_y" unorbind="unbind"></div>
                </div>
                <div class="scroll_xmove">
                    <div class="scroll_x" unorbind="unbind"></div>
                </div>--%>
            </div>
        </div>
        <div class="foot">
            <jsp:include page="/pages/frontend/footer.jsp"></jsp:include>
        </div>

    </div>
<script type="text/javascript">
    $("#vipt_video").css("min-height",$(document).height()-$(".public_head").height()-$(".vipt_head").height()-$(".foot").height()-2);
    $(window).resize(function () {
        $("#vipt_video").css("min-height",$(document).height()-$(".public_head").height()-$(".vipt_head").height()-$(".foot").height()-2);
    })
    function getData(weekday)
    {
        $.post(
                "${pageContext.request.contextPath}/liveplan/Liveplan_ajaxLiveplan.do",
                {week_id:weekday},
                function(data) {
                    $("#timeplan_list").html("");
                    $("#timeplan_list").html(data);
                }
        );
    }

    $(function(){

        laydate({
            elem: '#datashow',
            event: 'click', //触发事件
            format: 'YYYY-MM-DD',
            festival: true, //是否显示节日
            istoday: false,
            choose: function(){ //选择好日期的回调
                $.post(
                        "${pageContext.request.contextPath}/liveplan/Liveplan_ajaxLiveplan.do",
                        {date:$("#datashow").val()},
                        function(data) {
                            $("#timeplan_list").html("");
                            $("#timeplan_list").html(data);
                        }
                );
            }
        });

        $(".vipt_tablerecycle").each(function(){
            var div_leng = $(this).children("div").length;
            $(this).width(125*(div_leng-1)+160+60)
        })
        // alert($(".vipt_node").length)
        $("#vipt_week").width(300+$(".vipt_node").length*115)
    })


</script>

</body>
    <script src="${pageContext.request.contextPath}/js/common/selectmore.js"></script>
</html>