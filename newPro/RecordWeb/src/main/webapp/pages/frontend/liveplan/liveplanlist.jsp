<%@ page import="java.util.*" %>
<%@ page import="com.honghe.recordhibernate.entity.Curriculum" %>
<%@ page import="com.honghe.recordhibernate.entity.Setting" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .modif_liston > span{
        text-align: left;
        float: left;
        width:100px;
        margin-left:3px;
        height:20px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        line-height:20px;
    }
    .vipt_group{
        margin-left: 25px;
    }
    .vipt_gr_chicoe > span{
        display: block;
        float:left;
        width:16px;
        height:16px;
        margin-top:35px;
        margin-left:10px;
        border:1px solid #bdbdbd;
        cursor: pointer;
    }
    .vipt_group,.vipt_tablerecycle,.vipt_groupgreen{
        height:60px;
        line-height: 60px;
    }
    .vipt_timeselected,.vipt_time,.vipt_timegreen,.vipt_timegreenselected{
        height:60px;
    }

    .vipt_time span.curSubject,.vipt_time span.curTeacher , .vipt_timegreen span.curTeacher,.vipt_timegreen span.curSubject{
        text-align: left;
        /*margin-left: 35px;*/
        float: left;
        width:70px;
        height:28px;
        margin-top: 0;
        line-height: 28px;
        text-overflow:ellipsis;
        white-space:nowrap;
        overflow:hidden;
    }
    .curSubject,.curTeacher{
        text-align: left;
        /*margin-left: 35px;*/
        float: left;
        width:70px;
        height:28px;
        line-height: 28px;
        text-overflow:ellipsis;
        white-space:nowrap;
        overflow:hidden;
    }

    .win_ziychicoe{
        float: left;
        width: 380px;
    }
   /*  .win_ziychicoe > span{
       display: block;
       float: left;
       width: 100%;
       height: 30px;
       line-height: 30px;
       text-align: center;
   } */
    .win_ziychicoe > div.chice_div{
        float: left;
        width: 100%;
    }
    .win_ziychicoe > div.chice_div > div.chice_div_li{
        margin-left: 20px;
        margin-right: 20px;
        margin-top: 10px;
        float: left;
    }
    .win_ziychicoe > div.chice_div > div.chice_div_li>p{
        height: 30px;
        line-height: 30px;
        float: left;
    }
    .win_ziychicoe > div.chice_div > div.chice_div_li > span{
        cursor: pointer;
        display: block;
        width: 16px;
        height: 16px;
        float: left;
        border: 1px solid #bdbdbd;
        margin-right: 5px;
        margin-top: 6px;
    }
    .global_checkboxicon{
        background: url(${pageContext.request.contextPath}/image/frontend/global_checkbox.png) no-repeat 2px 3px;
    }
    .win_ziychicoe > div.chice_div > div.chice_div_li > p{
        display: block;
        float: left;
    }
    /*.xubox_msg{
        margin-top: 35px;
        max-height: 220px;
        border: 1px solid #000;
        overflow-y: auto;
        padding-top:0 !important;
    }*/
    .btn_bottoms{
        float: left;
        width: 100%;
    }
    .btn_bottoms > span{
        float: left !important;
        width:60px !important;
        height: 30px;
        line-height: 30px;
        text-align: center !important;
        display: block;
        clear: none;
        border-radius: 3px;
        cursor: pointer;
        margin-top: 0px !important;
    }
    .btn_bottoms > span:nth-child(1){
        margin-left: 30%;
        background: #ccc;
        color: #000;
    }
    .btn_bottoms > span:nth-child(2){
        margin-left: 2%;
        background: #28b779;
        color: #fff;
    }
    .main_tab_thcon_li{
        margin-left: 20px;
        width: 100% !important;
    }
    .main_tab_thcon{
        width: 100% !important;
    }
    .cl_name_a{
        float: right;
        width: 70px;
    }
    .cl_name_aimg{
        display: none;
        cursor: pointer;
        float: left;
        margin-top: 15px;
        margin-left: 10px;
        width: 22px;
        height: 26px;
        background: url(${pageContext.request.contextPath}/image/frontend/direct_seeding.png) no-repeat -28px 0;
    }
  
    .vipt_time,.vipt_timegreen{
        position: relative;
    }
    .lis_bottom_a{
        width: 100%;
        float: left;
    }
    .lis_bottom_a > span{
        margin-right: 20px;
        margin-bottom: 10px;
        float: right;
        display: block;
        width: 60px;
        background: #28b779;
        text-align: center !important;
        color: #fff;
        border-radius: 3px;
        cursor: pointer;
        display: block;
        height: 30px;
        line-height: 30px;
    }
</style>
<%
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragrma", "no-cache");
    response.setDateHeader("Expires", 0);
    response.setHeader("Content-type", "text/html;charset=UTF-8");
    Setting setting_ajax = (Setting) request.getAttribute("setting");
    int settingType_ajax;
    if (setting_ajax == null) {
        settingType_ajax = Curriculum.CUR_WEEK_TYPE;
    }
    else
    {
        settingType_ajax = setting_ajax.getCurriculumType();
    }
%>
<%
    try{
        int week_id_ajax = 0;
        String date = null;
        if (settingType_ajax == Curriculum.CUR_WEEK_TYPE) {
            week_id_ajax = Integer.parseInt(request.getAttribute("week_id").toString());
            if (date == null) {
                date = "0000-00-00";
            }
        } else if (settingType_ajax == Curriculum.CUT_DATE_TYPE) {
            date = (String)request.getAttribute("date");
        }
        Map<Integer, List<Object[]>> liveplanMaps = (Map<Integer, List<Object[]>>) request.getAttribute("liveplanMaps");
        Map<Integer,List<Object[]>> classtimeMaps = (Map<Integer,List<Object[]>>) request.getAttribute("classtimeMaps");
        List<Object[]> hostList = (List<Object[]>) request.getAttribute("hostlist");
%>
<%-----------------------------------------------------------------------------------------------------%>
<%
    if (hostList != null && !hostList.isEmpty()) {
        for (int j = 0; j < hostList.size(); j++) //1. 共多少个班级(主机)
        {
%>
<div class="vipt_tablerecycle" style="min-width: 100%;">
    <span hostid="<%=hostList.get(j)[0]%>" hostip="<%=hostList.get(j)[2]%>"></span>
        <%
            if(j%2 == 0)
            {
        %>
    <div class="vipt_group vipt_group_green vipt_gr_chicoe" style="height: 60px;">
        <%
        }
        else
        {
        %>
        <div class="vipt_groupgreen vipt_group_green vipt_gr_chicoe" style="height: 60px;">
            <%
                }
            %>
            <%--班级信息--%>
            <%=hostList.get(j)[1]%>
        </div>
        <%
            List<Object[]> liveplanList = liveplanMaps.get(Integer.parseInt(hostList.get(j)[0].toString()));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            List<Object[]> classtimeList = classtimeMaps.get(Integer.parseInt(hostList.get(j)[0].toString()));
            for(int i=0;i<classtimeList.size();i++) {
        %>
        <%
            if (liveplanList.size()>0)
            {
                boolean flag = false;
                for (Object[] liveplan : liveplanList) {
                    String curDateStr = "";
                    if (liveplan[2] == null) {
                        curDateStr = sdf.format(new Date());
                    } else {
                        curDateStr = sdf.format(liveplan[2]).toString();
                    }
                    if((classtimeList.get(i)[2].toString().equals(liveplan[3].toString()))
                            && ((liveplan[1] != null && week_id_ajax == Integer.parseInt(liveplan[1].toString()))
                            || date.equals(curDateStr)))
                    {
        %>

        <div style="height: 60px;" class="<%=j%2==0?"vipt_time":"vipt_timegreen"%>"
             id="<%=hostList.get(j)[0]%>_<%=classtimeList.get(i)[2]%>_<%=classtimeList.get(i)[1]%>">
             <%
                 if (liveplan[0] != null) {
                     %>
                    <div class="cl_name_aimg" style="display: block"></div>
                 <%} else {%>
                     <div class="cl_name_aimg"></div>
                 <%}
             %>
                <div class="cl_name_a">
                    <span class="curSubject"><%=classtimeList.get(i)[7]==null?"":classtimeList.get(i)[7]%></span>
                    <span class="curTeacher"><%=classtimeList.get(i)[6]==null?"":classtimeList.get(i)[6]%></span>
                </div>
        </div>
        <%
                    flag = true;
                    break;
                }
            }
            if(!flag)
            {
        %>
        <div class="<%=j%2==0?"vipt_time":"vipt_timegreen"%>"
             id="<%=hostList.get(j)[0]%>_<%=classtimeList.get(i)[2]%>_<%=classtimeList.get(i)[1]%>">
             <div class="cl_name_aimg"></div>
            <div class="cl_name_a">
                <span class="curSubject"><%=classtimeList.get(i)[7]==null?"":classtimeList.get(i)[7]%></span>
                <span class="curTeacher"><%=classtimeList.get(i)[6]==null?"":classtimeList.get(i)[6]%></span>
            </div>
        </div>
        <%
            }
        }
        else
        {
        %>
        <div class="<%=j%2==0?"vipt_time":"vipt_timegreen"%>"
             id="<%=hostList.get(j)[0]%>_<%=classtimeList.get(i)[2]%>_<%=classtimeList.get(i)[1]%>">
             <div class="cl_name_aimg"></div>
            <div class="cl_name_a">
                <span class="curSubject"><%=classtimeList.get(i)[7]==null?"":classtimeList.get(i)[7]%></span>
                <span class="curTeacher"><%=classtimeList.get(i)[6]==null?"":classtimeList.get(i)[6]%></span>
            </div>
        </div>
        <%
            }
        %>
        <%
            }
        %>
    </div>
        <%
        }
        }
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }

%>
<script type="text/javascript">
    $(function(){
        function getMousePos(event) {
            var e = event || window.event;
            var scrollX = document.documentElement.scrollLeft || document.body.scrollLeft;
            var scrollY = document.documentElement.scrollTop || document.body.scrollTop;
            var x = e.pageX || e.clientX + scrollX;
            var y = e.pageY || e.clientY + scrollY;
            var x_w = $(document).width();
            var y_w = $(document).height();
            var zj_w = $(".main_tab_thcon_li_win").width();
            var zj_h = $(".main_tab_thcon_li_win").height();
//            console.log('x:'+ x +'y:'+ y);
//            console.log('x_w:'+ x_w +'y_w:'+ y_w);
//            console.log('zj_w:'+ zj_w +'zj_h:'+ zj_h);
            if(x_w - x < zj_w+115 ){
                $(".main_tab_thcon_li_win").css("right","115px");
                $("span.sjback").attr("class","sjbackd")
            }else{
                $(".main_tab_thcon_li_win").css("right","-390px");
            }
            if(y_w -y < zj_h){
                $(".main_tab_thcon_li_win").css("top", -(zj_h -58) +"px");
                $("span.sjback").css("top",(zj_h -38) +"px")
                $("span.sjbackd").css("top",(zj_h -38) +"px")
//                $("span.sjback").attr("class","sjbackd")
            }else{
                $(".main_tab_thcon_li_win").css("top", "0px");
            }
        }
        $(".vipt_tablerecycle").each(function(){
            var div_leng = $(this).children("div").length;
            $(this).width(125*(div_leng-1)+160+60)
        })
        // alert($(".vipt_node").length)
        $("#vipt_week").width(300+$(".vipt_node").length*115)
        $(document).on("click",".cl_name_aimg",function(){
                $(this).hide();
        })

        // 加载通道
        var that;
        $(document).on("mouseenter",".vipt_time",function(event){
            $(".main_tab_thcon_li_win").remove();
            var host_id = $(this).attr("id");
            var section_id = host_id.split("_")[1];
            that = $(this);
            host_id = host_id.substring(0, host_id.indexOf("_"));

            var week_id = $("#week_id").val();
            if(week_id == undefined)
            {
                week_id = 1;
            }
            var date = $("#datashow").val();
            if(date == undefined)
            {
                date = Date();
            }

            $.post(
                    "${pageContext.request.contextPath}/liveplan/Liveplan_getTokenAndChannel.do",
                    {host_id: host_id, section_id:section_id, week_id:week_id, date:date},
                    function(data){
                        var str = '';
                        if (data.flag) {
                            //var str = '';
                            var tokens = data.tokens;
                            var channels = data.channels;
                            $(that).find(".main_tab_thcon_li_win").remove();
                            str = '<div class="main_tab_thcon_li_win">'+
                            '<div class="main_tab_thcon_li_win_man">'+
                            '<span class="sjback"></span>'+
                            '<span>资源选择：</span>'+
                            '<div class="win_ziychicoe">'+
                            '<div class="chice_div">';
                            for(var i=0; i<tokens.length; i++) {
                                str +='<div class="chice_div_li">';
                                var flag = false;
                                if (channels == undefined) {
                                    //str +='<span></span><p>'+tokens[i]+'</p>';
                                    str +='<span value='+tokens[i]+'></span><p>'+tokens[i]+'</p>';
                                } else {
                                    for (var j=0; j<channels.length; j++) {
                                        if (tokens[i] == channels[j]) {
                                            flag = true;
                                            break;
                                        }
                                    }
                                    if (flag) {
                                        str +='<span updata="true" class="global_checkboxicon" value='+tokens[i]+'></span><p>'+tokens[i]+'</p>';
                                    } else {
                                        str +='<span value='+tokens[i]+'></span><p>'+tokens[i]+'</p>';
                                    }
                                }

                                str += '</div>';
                            }
                            str +='</div>'+
                            '</div>'+
                            '<div class="lis_bottom_a">'+
                            '<span class="lis_bottom">确定</span>'+
                            '</div>'+
                            '</div>'+

                            '</div>';
                        } else {
                            $(that).find(".main_tab_thcon_li_win").remove();
                            str = '<div class="main_tab_thcon_li_win">'+
                            '<div class="main_tab_thcon_li_win_man">'+
                            '<span class="sjback"></span>'+
                            '<span>资源选择：</span>'+
                            '<div class="win_ziychicoe">'+
                            '<div class="chice_div">';

                            str +='无资源通道</div>'+
                            '</div>'+
                            '</div>'+

                            '</div>'
                        }
                        $(that).append(str);
                        getMousePos(event);
                        setTimeout(function(){
                            $(that).find(".main_tab_thcon_li_win").show();
                        },1000);
                        //$(that).addClass("name", "abb");

                    },
                    "json"
            );

        })

        $(document).on("mouseenter",".vipt_timegreen",function(event){
            $(".main_tab_thcon_li_win").remove();
            var host_id = $(this).attr("id");
            var section_id = host_id.split("_")[1];
            that = $(this);
            host_id = host_id.substring(0, host_id.indexOf("_"));

            var week_id = $("#week_id").val();
            if(week_id == undefined)
            {
                week_id = 1;
            }
            var date = $("#datashow").val();
            if(date == undefined)
            {
                date = Date();
            }

            $.post(
                    "${pageContext.request.contextPath}/liveplan/Liveplan_getTokenAndChannel.do",
                    {host_id: host_id, section_id:section_id, week_id:week_id, date:date},
                    function(data){
                        var str = '';
                        if (data.flag) {
                            var tokens = data.tokens;
                            var channels = data.channels;

                            $(that).find(".main_tab_thcon_li_win").remove();

                            str = '<div class="main_tab_thcon_li_win">' +
                            '<div class="main_tab_thcon_li_win_man">' +
                            '<span class="sjback"></span>' +
                            '<span>资源选择：</span>' +
                            '<div class="win_ziychicoe">' +
                            '<div class="chice_div">';

                            for (var i = 0; i < tokens.length; i++) {
                                str += '<div class="chice_div_li">';
                                var flag = false;
                                if (channels == undefined) {
                                    //str +='<span></span><p>'+tokens[i]+'</p>';
                                    str += '<span value=' + tokens[i] + '></span><p>' + tokens[i] + '</p>';
                                } else {
                                    for (var j = 0; j < channels.length; j++) {
                                        if (tokens[i] == channels[j]) {
                                            flag = true;
                                            break;
                                        }
                                    }
                                    if (flag) {
                                        str += '<span updata="true" class="global_checkboxicon" value=' + tokens[i] + '></span><p>' + tokens[i] + '</p>';
                                    } else {
                                        str += '<span value=' + tokens[i] + '></span><p>' + tokens[i] + '</p>';
                                    }
                                }

                                str += '</div>';
                            }
                            str += '</div>' +
                            '</div>' +
                            '<div class="lis_bottom_a">' +
                            '<span class="lis_bottom">确定</span>' +
                            '</div>' +
                            '</div>' +

                            '</div>';
                        } else {
                            $(that).find(".main_tab_thcon_li_win").remove();
                            str = '<div class="main_tab_thcon_li_win">'+
                            '<div class="main_tab_thcon_li_win_man">'+
                            '<span class="sjback"></span>'+
                            '<span>资源选择：</span>'+
                            '<div class="win_ziychicoe">'+
                            '<div class="chice_div">';

                            str +='无资源通道</div>'+
                            '</div>'+
                            '</div>'+

                            '</div>'
                        }
                        $(that).append(str);
                        getMousePos(event);
                        setTimeout(function(){
                            $(that).find(".main_tab_thcon_li_win").show();
                        },1000);
                    },
                    "json"
            );

        })
        $(document).on("mouseleave",".vipt_time",function(){
            $(this).find(".main_tab_thcon_li_win").remove();
        })

        $(document).on("mouseleave",".vipt_timegreen",function(){
            $(this).find(".main_tab_thcon_li_win").remove();
        })

    })


    // 删除直播计划 并关闭直播间
    $(".cl_name_aimg").click(function() {

        var id = $(this).parent().attr("id");
        var host_id = id.substring(0, id.indexOf("_"));
        var week_id = id.substring(id.lastIndexOf("_")+1);
        var section_id = id.split("_")[1];
        var date = $("#datashow").val();
        if(date == undefined)
        {
            date = Date();
        }

        $.post('${pageContext.request.contextPath}/liveplan/Liveplan_delLiveplan.do',{host_id: host_id, section_id:section_id, week_id:week_id, date:date},function(data){
            if (data.flag == 'true') {
                layer.msg(data.msg);
            } else {
                layer.msg(data.msg);
            }
        });
    });

  /* $(document).on("click",".lis_bottom_a > span",function(){
        $(this).parents(".main_tab_thcon_li_win").siblings(".cl_name_aimg").show();
   })*/

    function clearPlan()//清空计划 清空本天的还是所有的
    {
        layer.confirm("您确定清空所有的直播计划吗?",function() {
            $.post(root_path + '/liveplan/Liveplan_clearplan.do',function(data){
                if(data.flag == true) {
                    layer.msg(data.msg);
                    $(".cl_name_aimg").hide();
                } else {
                    layer.msg(data.msg);
                }
            });
        },'清空计划')
    }

</script>
