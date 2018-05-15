
<%@ page import="com.honghe.recordweb.action.SessionManager" %>
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
    <%--<script src="${pageContext.request.contextPath}/js/lb_common/screendetailforrecord.js"></script>--%>
    <script src="${pageContext.request.contextPath}/js/common/selectone.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/scrolldemo.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/laydate/laydate.js"></script>
</head>

<body style="overflow-x: hidden">
<input type="hidden" id="whichscroll">
<div class="public" style="height: auto;width: auto !important;">
    <jsp:include page="/pages/frontend/lb_header.jsp"></jsp:include>
    <div class="vip">
        <div class="vipt_head" style="height: 35px;line-height: 35px;background: #fff;">
            <div class="vipt_headtext" style="height: 35px; line-height: 35px;">注：鼠标点击可新增录像计划</div>

            <span id="clean" class="vipt_head_option" style="margin:0;">
                <a href="javascript:void(0)" onclick="clearPlan()">
                    <span class="vipt_head_clean"></span>清空所有录像计划
                </a>
            </span>
            <a href="${pageContext.request.contextPath}/timeplan/Tempplan_list.do"  style="float: right;">
                <span class="vipt_head_video"></span>临时录像计划设置
            </a>
            <%
                com.honghe.recordhibernate.entity.User user =  SessionManager.get(request.getSession(), SessionManager.Key.USER);
                boolean isSeting = user.getUser_salt().equals("true");
                Setting setting = (Setting) request.getAttribute("setting");
                int settingType = Curriculum.CUR_WEEK_TYPE;
                if (setting != null && setting.getCurriculumType() != null) {
                    settingType = setting.getCurriculumType();
                }
            %>
        </div>
        <input type="hidden" value="<%=isSeting%>" id="isSetting">
        <div id="vipt_video" style="float: left;">
            <div class="vapptvid_title" style="float:left;width:100%;height: 30px;line-height:30px;">
                <span class="copy_timeplaan">复制选中计划</span>
                <input type="hidden" id="copy_val"/>
                <span class="settimeplan">下发选中计划</span>
                <div class="setPlan">
                    <span class="copy_timeplaan" style="margin-top:6px;margin-left:10px;margin-right: 5px"></span>
                    <span id="settimeplan">设置录像计划模式</span>
                    <span id="cancelplan" >取消录像计划模式</span>
                </div>
            </div>
            <%--头信息--%>
            <div class="vipt_week" id="vipt_week" style="float: left;width: auto;">
                <span class="cheice_icon"></span>
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
            <div id = "timeplan_list" style="float: left;">
                <div id = "timeplan_list" style="float: left;">
                    <%@ include file="/pages/frontend/timeplan/timeplanlist.jsp" %>
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
        $("#vipt_video").css("min-height",$(document).height()-$(".public_head").height()-$(".vipt_head").height()-$(".foot").height()-2);
        $(window).resize(function () {
            $("#vipt_video").css("min-height",$(document).height()-$(".public_head").height()-$(".vipt_head").height()-$(".foot").height()-2);
        })
        //    		var vipt_weekwid=$("#vipt_video #timeplan_list .vipt_node").length*125+180
        //    		$("#vipt_video .scrollson").width(vipt_videowid)
        //    		//$("#vipt_video").height($(".vip").height()*0.93)
        //    		$("#vipt_video").width($(".vip").width())
        //    		var listlen=$("#vipt_video #timeplan_list").children(".vipt_tablerecycle").length;
        //    		$("#vipt_video .scrollson,#fixed").height(listlen*61+61)
        //
        //    		$("#vipt_video .scrollson").mouseover(function(){
        //					$("#whichscroll").val($.trim($(this).parent().attr("id")))
        //					if ((navigator.userAgent.match(/(iPhone|Android|iPad)/i))){
        //					var scrollfathter1=document.getElementById($.trim($(this).parent().attr("id")));
        //					scrollfathter1.addEventListener("touchstart", touchStart, false);
        //					scrollfathter1.addEventListener("touchmove", touchMove, false);
        //					scrollfathter1.addEventListener("touchend", touchEnd, false);
        //					}
        //				})
        //				scroll_y("vipt_video","scrollson","scroll_y","scroll_ymove","scroll_x","scroll_xmove","","wheely","")
        //				$("#vipt_video .scrollson").css("margin-top","0")
        //				$("#vipt_video .scroll_y").css("top","0")
        $(".vipt_tablerecycle").each(function(){
            var div_leng = $(this).children("div").length;
            $(this).width(125*(div_leng-1)+160+60)
        })
        // alert($(".vipt_node").length)
        $("#vipt_week").width(300+$(".vipt_node").length*115)
        $("span.cheice_icon").live("click",function(){
            // alert("sss")
            var flag = $(this).hasClass("global_cheted");
            if(flag == false){
                $(this).addClass("global_cheted");
                $(".chicoe_icn_t").addClass("global_cheted");
                $(".chicoe_icn_t").attr("id","global_cheted_i")
            }else{
                $(this).removeClass("global_cheted");
                $(".chicoe_icn_t").removeClass("global_cheted");
                $(".chicoe_icn_t").attr("id","")
            }
        })
        //        $(document).on("click",".copy_timeplaan",function(){
        //            var $this = $(this);
        //            var this_text = $this.text();
        //            var sele_leng = $("#timeplan_list").find(".vipt_tablerecycle").find("span.global_cheted").length;
        //            var tim_class = $("#timeplan_list").find("span.global_cheted").siblings().hasClass("vipt_group");
        //            var pim_class = $("#timeplan_list").find("span.global_cheted").siblings().hasClass("vipt_groupgreen");
        //            var copy_val = $("#copy_val").val();
        //            if(this_text == "复制选中计划"){
        //                var this_index="";
        //                if(sele_leng == 0){
        //                    layer.msg("请选择复制选项");
        //                }else if(sele_leng > 1){
        //                    layer.msg("请选择单个班级");
        //                }else if(sele_leng == 1){
        //                    if(tim_class){
        //                        $("#timeplan_list").find("span.global_cheted").siblings(".del_timeplan").each(function(){
        //                            this_index += $(this).index();
        //                        });
        //                    }else if(pim_class){
        //                        $("#timeplan_list").find("span.global_cheted").siblings(".del_timegreenplan").each(function(){
        //                            this_index += $(this).index();
        //                        });
        //                    }
        //                    $("#copy_val").val(this_index);
        //                   if($("#copy_val").val()!=""){
        //                       layer.msg("已复制到剪切板");
        //                        $this.text("粘贴选中计划");
        //                   }else{
        //                       layer.msg("请选择已设置录像计划班级")
        //                        return false
        //                    }
        //                }
        //            }else if(this_text == "粘贴选中计划"){
        //                if(sele_leng>0){
        //                    if(tim_class){
        //
        //                        $("#timeplan_list").find(".vipt_tablerecycle").find("span.global_cheted").each(function(){
        //                            $(this).siblings("div").each(function(i){
        //
        //                                if($(this).hasClass("del_timegreenplan")){
        //                                   $(this).attr("class","vipt_timegreen add_timegreenplan");
        //                                }
        //                                for(var y=0;y<copy_val.length;y++){
        //                                    copy_val_a = copy_val[y];
        //                                    if(copy_val_a==i+1){
        //                                        $(this).attr("class","vipt_timeselected del_timeplan");
        //                                    }
        //                                }
        //                            })
        //
        //                        });
        //                    }else if(pim_class){
        //
        //                        $("#timeplan_list").find(".vipt_tablerecycle").find("span.global_cheted").each(function(){
        //                            $(this).siblings("div").each(function(i){
        //                            if($(this).hasClass("del_timeplan")){
        //                                $(this).attr("class","vipt_time add_timeplan");
        //                            }
        //                            for(var y=0;y<copy_val.length;y++){
        //                                copy_val_a = copy_val[y];
        //                                if(copy_val_a==i+1){
        //                                    $(this).attr("class","vipt_timegreenselected del_timegreenplan");
        //                                }
        //                            }
        //                            })
        //                        });
        //                    }
        //                    var week_id = $("#week_id").val();
        //                    if (week_id == undefined) {
        //                        week_id = 1;
        //                    }
        //                    var date = $("#datashow").val();
        //                    if (date == undefined) {
        //                        date = Date();
        //                    }
        //                    //  console.log(week_id);
        //                    // console.log(date);
        //                    var hostids = "";
        //                    $(".vipt_tablerecycle > .global_cheted").each(function(){
        //                        hostids += $(this).attr("hostid")+","
        //                    });
        //                    console.log(hostids);
        //
        //                    $.post(root_path + '/timeplan/Timeplan_findNas.do', {hostids: hostids.toString()}, function (data) {
        //                        if (data.msg == "nas未设置") {
        //                            var isSetting = $("#isSetting").val();
        //                            if (isSetting == "true") {
        //                                layer.confirm("请设置Nas服务器", function () {
        //                                    window.location.href = root_path + "/settings/Settings_findNas.do";
        //                                    return false;
        //                                })
        //                            } else {
        //                                layer.msg("Nas服务器未设置", 1);
        //                            }
        //                        } else {
        //                            $.post(root_path + '/timeplan/Timeplan_addcopyplan.do', {
        //                                week_id: week_id,
        //                                date: date,
        //                                hostids: hostids.toString()
        //                            }, function (data) {
        //                                if (data.msg == "添加成功") {
        //                                    var timeplans = data.timeplans;
        //
        //                                    $(".vipt_tablerecycle span.chicoe_icn_t.global_cheted").each(function () {
        //                                        if ($(this).siblings().hasClass("vipt_group vipt_group_green vipt_gr_chicoe")) {
        //                                            $(this).siblings(".vipt_timeselected.del_timeplan").each(function () {
        //                                                var this_index = $(this).index();
        //                                                //console.log(this_index);
        //                                                $(".vipt_tablerecycle span.chicoe_icn_t.global_cheted").each(function () {
        //                                                    if ($(this).siblings().hasClass("vipt_groupgreen vipt_group_green vipt_gr_chicoe")) {
        //                                                        $(this).siblings("div").eq(this_index - 1).attr("class", "del_timegreenplan vipt_timegreenselected selext_xunze")
        //                                                    } else {
        //                                                        $(this).siblings("div").eq(this_index - 1).attr("class", "vipt_timeselected del_timeplan selext_xunze")
        //                                                    }
        //                                                })
        //                                            })
        //                                        } else {
        //                                            $(this).siblings(".del_timegreenplan.vipt_timegreenselected").each(function () {
        //                                                var this_index = $(this).index();
        //                                                //console.log(this_index);
        //                                                $(".vipt_tablerecycle span.chicoe_icn_t.global_cheted").each(function () {
        //                                                    if ($(this).siblings().hasClass("vipt_groupgreen vipt_group_green vipt_gr_chicoe")) {
        //                                                        $(this).siblings("div").eq(this_index - 1).attr("class", "del_timegreenplan vipt_timegreenselected selext_xunze")
        //                                                    } else {
        //                                                        $(this).siblings("div").eq(this_index - 1).attr("class", "vipt_timeselected del_timeplan selext_xunze")
        //                                                    }
        //                                                })
        //                                            })
        //                                        }
        //                                    });
        //                                    $(".selext_xunze").each(function (i) {
        //                                        $(this).attr("name", timeplans[i])
        //                                    })
        //
        //                                }
        //                            })
        //                        }
        //                    })
        //                   // layer.msg("粘贴成功");
        //                    $this.text("复制选中计划");
        //                   // copy_val = 0;
        //                }else{
        //                    layer.msg("请选择粘贴选项")
        //                }
        //            }
        //
        //        })
        //           // var chic_lenga = 0;
        $(".chicoe_icn_t").live("click",function(){
            var chic_leng = $(".chicoe_icn_t").length;
            var flag = $(this).hasClass("global_cheted");
            var chic_lenga = $("#timeplan_list").find("span#global_cheted_i").length
            if(flag == false){
                $(this).addClass("global_cheted");
                $(this).attr("id","global_cheted_i")
                chic_lenga = chic_lenga + 1 ;
            }else{
                $(this).removeClass("global_cheted");
                $(this).attr("id","")
                chic_lenga = chic_lenga - 1 ;
            }
            //  alert(chic_lenga)
            if(chic_leng==chic_lenga){
                $(".cheice_icon").addClass("global_cheted");
            }else{
                $(".cheice_icon").removeClass("global_cheted");
            }
            // alert(chic_lenga)

        })
        function winwidth(){
            var winwidth=$(window).width();
            return winwidth;
        }
        laydate({
            elem: '#datashow',
            event: 'click', //触发事件
            format: 'YYYY-MM-DD',
            festival: true, //是否显示节日
            istoday: false,
            choose: function(){ //选择好日期的回调
                $.post(
                        "${pageContext.request.contextPath}/timeplan/Timeplan_ajaxplan.do",
                        {date:$("#datashow").val()},
                        function(data) {
                            $("#timeplan_list").html("");
                            $("#timeplan_list").html(data);
                        }
                );
            }
        });

        <%--function getData(weekday)--%>
        <%--{--%>
        <%--$.post(--%>
        <%--"${pageContext.request.contextPath}/timeplan/Timeplan_ajaxplan.do?"+Math.random(),--%>
        <%--{week_id:weekday},--%>
        <%--function(data) {--%>
        <%--$("#timeplan_list").html(data);--%>
        <%--}--%>
        <%--);--%>
        <%--}--%>
        //        $(document).on("click",".copy_timeplaan",function(){
        //            $(".vipt_tablerecycle span.chicoe_icn_t.global_cheted").each(function(){
        //                if($(this).siblings().hasClass("vipt_group vipt_group_green vipt_gr_chicoe")){
        //                    console.log("sss")
        //                        $(this).siblings(".vipt_timeselected.del_timeplan").each(function(){
        //                            var this_index = $(this).index();
        //                            $(".vipt_tablerecycle span.chicoe_icn_t.global_cheted").each(function(){
        //                                if($(this).siblings().hasClass("vipt_groupgreen vipt_group_green vipt_gr_chicoe")){
        //                                    $(this).siblings("div").eq(this_index - 1 ).attr("class","del_timegreenplan vipt_timegreenselected")
        //                                }else{
        //                                    $(this).siblings("div").eq(this_index- 1 ).attr("class","vipt_timeselected del_timeplan")
        //                                }
        //
        //                            })
        //                        })
        //                 }else{
        //                        $(this).siblings(".del_timegreenplan.vipt_timegreenselected").each(function(){
        //                            var this_index = $(this).index();
        //                            $(".vipt_tablerecycle span.chicoe_icn_t.global_cheted").each(function(){
        //                                if($(this).siblings().hasClass("vipt_groupgreen vipt_group_green vipt_gr_chicoe")){
        //                                    $(this).siblings("div").eq(this_index - 1 ).attr("class","del_timegreenplan vipt_timegreenselected")
        //                                }else{
        //                                    $(this).siblings("div").eq(this_index- 1 ).attr("class","vipt_timeselected del_timeplan")
        //                                }
        //
        //                            })
        //                        })
        //                 }
        //            });
        //           // $(".vipt_tablerecycle span.chicoe_icn_t.global_cheted").parent().find("div").eq(index).attr("class","vipt_timegreenselected del_timegreenplan")
        //
        //        })

    </script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/lb_frontend/timeplan/timeplan.js"></script>

</body>
</html>




