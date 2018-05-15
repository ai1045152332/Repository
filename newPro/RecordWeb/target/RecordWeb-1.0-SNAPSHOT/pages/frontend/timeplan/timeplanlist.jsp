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
    .chicoe_icn_t{
        margin-top: 20px;
    }
    .vipt_time span.curSubject,.vipt_time span.curTeacher , .vipt_timegreen span.curTeacher,.vipt_timegreen span.curSubject{
        text-align: left;
        margin-left: 35px;
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
        margin-left: 35px;
        float: left;
        width:70px;
        height:28px;
        line-height: 28px;
        text-overflow:ellipsis;
        white-space:nowrap;
        overflow:hidden;
    }
    .xubox_msg{
        margin-top: 35px;
        max-height: 220px;
        /*border: 1px solid #000;*/
        overflow-y: auto;
        padding-top:0 !important;
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
        Map<Integer, List<Object[]>> timeplanMaps = (Map<Integer, List<Object[]>>) request.getAttribute("timeplanMaps");
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
    <span class="chicoe_icn_t" hostid="<%=hostList.get(j)[0]%>" hostip="<%=hostList.get(j)[2]%>"></span>
        <%
            if(j%2 == 0)
            {
        %>
    <div class="vipt_group vipt_group_green vipt_gr_chicoe" title="<%=hostList.get(j)[1]%>" style="text-overflow:ellipsis;white-space:nowrap;overflow:hidden;">
        <%
        }
        else
        {
        %>
        <div title="<%=hostList.get(j)[1]%>" class="vipt_groupgreen vipt_group_green vipt_gr_chicoe" style="text-overflow:ellipsis;white-space:nowrap;overflow:hidden;">
            <%
                }
            %>
            <%--班级信息--%>
            <%=hostList.get(j)[1]%>
        </div>
        <%
            List<Object[]> timeplanList = timeplanMaps.get(Integer.parseInt(hostList.get(j)[0].toString()));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            List<Object[]> classtimeList = classtimeMaps.get(Integer.parseInt(hostList.get(j)[0].toString()));
            for(int i=0;i<classtimeList.size();i++) {
        %>
            <%
                if (timeplanList.size()>0)
                {
                    boolean flag = false;
                    for (Object[] timeplan : timeplanList) {
                        String curDateStr = "";
                        if (timeplan[2] == null) {
                            curDateStr = sdf.format(new Date());
                        } else {
                            curDateStr = sdf.format(timeplan[2]).toString();
                        }
                        if((classtimeList.get(i)[2].toString().equals(timeplan[3].toString()))
                            && ((timeplan[1] != null && week_id_ajax == Integer.parseInt(timeplan[1].toString()))
                                || date.equals(curDateStr)))
                        {
            %>
            <div class="<%=j%2==0?"vipt_timeselected del_timeplan":"vipt_timegreenselected del_timegreenplan"%>"
                 id="<%=hostList.get(j)[0]%>_<%=classtimeList.get(i)[2]%>_<%=classtimeList.get(i)[1]%>"
                 name = <%=timeplan[0]%>>
                <span class="curSubject"><%=classtimeList.get(i)[7]==null?"":classtimeList.get(i)[7]%></span>
                <span class="curTeacher"><%=classtimeList.get(i)[6]==null?"":classtimeList.get(i)[6]%></span>
                <input type="hidden" name="hostip" value="<%=hostList.get(j)[2]%>"/>
            </div>
            <%
                        flag = true;
                        break;
                    }
                }
                if(!flag)
                {
            %>
            <div class="<%=j%2==0?"vipt_time add_timeplan":"vipt_timegreen add_timegreenplan"%>"
                 id="<%=hostList.get(j)[0]%>_<%=classtimeList.get(i)[2]%>_<%=classtimeList.get(i)[1]%>">
                <span class="curSubject"><%=classtimeList.get(i)[7]==null?"":classtimeList.get(i)[7]%></span>
                <span class="curTeacher"><%=classtimeList.get(i)[6]==null?"":classtimeList.get(i)[6]%></span>
            </div>
            <%
                }
            }
            else
            {
            %>
            <div class="<%=j%2==0?"vipt_time add_timeplan":"vipt_timegreen add_timegreenplan"%>"
                 id="<%=hostList.get(j)[0]%>_<%=classtimeList.get(i)[2]%>_<%=classtimeList.get(i)[1]%>">
                <span class="curSubject"><%=classtimeList.get(i)[7]==null?"":classtimeList.get(i)[7]%></span>
                <span class="curTeacher"><%=classtimeList.get(i)[6]==null?"":classtimeList.get(i)[6]%></span>
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

