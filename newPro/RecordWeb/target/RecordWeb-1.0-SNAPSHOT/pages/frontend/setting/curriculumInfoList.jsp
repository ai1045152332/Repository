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
</style>
<%
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragrma", "no-cache");
    response.setDateHeader("Expires", 0);
    response.setHeader("Content-type", "text/html;charset=UTF-8");
    Setting setting1 = (Setting) request.getAttribute("setting");
    int settingType;
    if (setting1 == null) {
        settingType = Curriculum.CUR_WEEK_TYPE;
    }
    else
    {
        settingType = setting1.getCurriculumType();
    }
%>
<%
    try{
        int week_id_ajax = 0;
        String date = null;
        if (settingType == Curriculum.CUR_WEEK_TYPE) {
            week_id_ajax = Integer.parseInt(request.getAttribute("week_id").toString());
            if (date == null) {
                date = "0000-00-00";
            }
        } else if (settingType == Curriculum.CUT_DATE_TYPE) {
            date = (String)request.getAttribute("date");
        }
        Map<Integer, List<Curriculum>> curriculumMaps = (Map<Integer, List<Curriculum>>) request.getAttribute("curriculumMaps");
        Map<Integer,List<Object[]>> classtimeMaps = (Map<Integer,List<Object[]>>) request.getAttribute("classtimeMaps");
//        Map<Integer, String> intToUpper_1 = (Map<Integer, String>) request.getAttribute("intToUpper");
%>
<%
    List<Object[]> hostList = (List<Object[]>) request.getAttribute("hostlist");
//    List<Integer> sectionList = (List<Integer>) request.getAttribute("sectionList");
//    for (int i = 1; i <= sectionList.size(); i++) //共多少节课
//    {
%>
<%--<div class="vipt_node">第<%=intToUpper_1.get(i)%>节</div>--%>
<%--<%--%>
    <%--}--%>
<%--%>--%>
<%-----------------------------------------------------------------------------------------------------%>
<%
    if (hostList != null && !hostList.isEmpty()) {
        for (int j = 0; j < hostList.size(); j++) //1. 共多少个班级(主机)
        {
%>
<div class="vipt_tablerecycle">
        <%
            if(j%2 == 0)
            {
        %>
        <div class="vipt_group vipt_group_green">
        <%
            }
            else
            {
        %>
        <div class="vipt_groupgreen vipt_group_green">
        <%
            }
        %>
            <%--班级信息--%>
            <%=hostList.get(j)[1]%>
        </div>
        <%
            List<Curriculum> curriculums = curriculumMaps.get(Integer.parseInt(hostList.get(j)[0].toString()));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            List<Object[]> classtimeList = classtimeMaps.get(Integer.parseInt(hostList.get(j)[0].toString()));
            for(int i=0;i<classtimeList.size();i++) {
        %>
        <div class="<%=j%2==0?"vipt_time":"vipt_timegreen"%>"
             id="<%=hostList.get(j)[0]%>_<%=classtimeList.get(i)[2]%>_<%=classtimeList.get(i)[1]%>_<%=settingType%>">
            <%
                if (curriculums.size()>0)
                {
                    boolean flag = false;
                    for (Curriculum curriculum : curriculums) {
                        // 处理空指针,给默认值
                        Date curDate = curriculum.getCurDate();
                        String curDateStr = "";
                        if (curDate == null) {
                            curDateStr = sdf.format(new Date());
                        } else {
                            curDateStr = sdf.format(curDate);
                        }
                        if((Integer.parseInt(classtimeList.get(i)[2].toString()) == curriculum.getCurSection())
                            && (week_id_ajax == curriculum.getCurWeek() || date.equals(curDateStr)))
                        {
            %>
            <div class="modif_liston">
                <span class="curSubject" title="<%=curriculum.getCurSubject()%>"><%=curriculum.getCurSubject()%></span>
                <span class="curTeacher" title="<%=curriculum.getCurTeacher()%>"><%=curriculum.getCurTeacher()%></span>
                <span class="curUnit" title="<%=curriculum.getCurUnit()%>"><%=curriculum.getCurUnit()%></span>
                <div class="delete_icon_img" onclick="delCurriculum(this)" curid="<%=curriculum.getCurId()%>"></div>
                <%--onclick="del(<%=curriculum.getCurId()%>,<%=classtimeList.get(i)[2]%>,<%=hostList.get(j)[0]%>,<%=classtimeList.get(i)[1]%>)"--%>
                <div class="modify_icon_img"></div>
            </div>
            <div class="modify_icon_show" style="display: none;height:115px;">
                <form action="/settings/Settings_updateCurriculum.do" style="height: 115px;" method="post"
                      id="formUpdate_<%=hostList.get(j)[0]%>_<%=classtimeList.get(i)[2]%>_<%=classtimeList.get(i)[1]%>">
                    <input type='text' name='curriculumName' class='modify_ic' value="<%=curriculum.getCurSubject()%>"
                           placeholder='请输入课程名称'/>
                    <input type='text' name='teacherName' class='modify_ic' value="<%=curriculum.getCurTeacher()%>"
                           placeholder='请输入老师名称'/>
                    <input type='text' name='unit' class='modify_ic' value="<%=curriculum.getCurUnit()%>"
                           placeholder='请输入教学单位'/>
                    <input type='button' style="float:left;border: 0;background: #bdbdbd;color:#fff;margin: 5px;display: block"
                           value='取消' onclick="cancel()"/>
                    <input type='button' style='float:left;border: 0;background: #28b779;color:#fff;margin: 5px;display: block'
                           value='确定' onclick="subUpdate(<%=classtimeList.get(i)[2]%>,<%=hostList.get(j)[0]%>,<%=classtimeList.get(i)[1]%>)"/>
                    <input type='hidden' name="curSection" value='<%=curriculum.getCurSection()%>'/>
                    <input type='hidden' name='hostid' value='<%=hostList.get(j)[0]%>'/>
                    <input type='hidden' name='week_id' value='<%=curriculum.getCurWeek()%>'/>
                    <input type='hidden' name='date' value='<%=curriculum.getCurDate()%>'/>
                </form>
            </div>
            <style>
                .modify_ic{
                    height:16px;
                    line-height:16px;
                    width:100px;
                    border:1px solid #bdbdbd;
                    margin-top: 5px;
                    margin-left: 0px;
                }
            </style>
            <%
                            flag = true;
                            break;
                        }
                    }
                    if(!flag)
                    {
            %>
            <div class="add_curriculum" onclick='addCurriculum(this)' style="height: 115px;width: 115px;"></div>
            <%
                    }
                }
                else
                {
            %>
            <div class="add_curriculum" onclick='addCurriculum(this)' style="height: 115px;width: 115px;"></div>
            <%
                }
            %>
            </div>
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
<script>
    // 点击修改图标
    $(".modify_icon_img").live("click",function() {
        $(this).parent().parent().find(".modif_liston").hide();
        $(this).parent().parent().find(".modify_icon_show").show();
    });
    function delCurriculum(obj)
    {
        var curid = $(obj).attr("curid");
        var curriculumId = $(obj).attr("curid");
        var div_id = $(obj).parent().parent().attr("id");
        $.post(
            "${pageContext.request.contextPath}/settings/Settings_delCurriculum.do",
            {curriculumId: curriculumId},
            function(data){
                if (data.flag == true) {
                    $("#"+div_id).find(".modif_liston").remove();
                    $("#"+div_id).find(".modify_icon_show").remove();
                    var str="<div class='add_curriculum' onclick='addCurriculum(this)' style='height: 115px;width: 115px;'></div>"
                    $("#"+div_id).html(str);;
                } else {
                    layer.msg(data.msg);
                }
            },
            "json"
        );
    }
    //添加课程
    function addCurriculum(obj){
        var div_id = $(obj).parent().attr("id");
        var _id = div_id.split("_");
        var settingType = _id[3];
        var curSection = _id[1];
        var hostid = _id[0];
        var date = settingType==1?$("#sall").children().attr("value"):"";
        var weekid = _id[2];
        var str = "<form action='/settings/Settings_saveCurriculum.do?' method='post' "+
                "id='formSave_"+hostid+"_"+curSection+"_"+weekid+"'>"+
        "<input type='text' name='curriculumName' class='modify_ic' placeholder='请输入课程名称'/>"+
        "<input type='text' name='teacherName' class='modify_ic'  placeholder='请输入老师名称'/>"+
        "<input type='text' name='unit' class='modify_ic'  placeholder='请输入教学单位'/>"+
        "<input type='button' style='border: 0;background: #a8a8a8;color:#fff;margin: 5px;' value='取消' "+
                "onclick='cancel1(\""+div_id+"\")'/>"+
        "<input type='button' style='border: 0;background: #28b779;color:#fff;margin: 5px;' value='确定' "+
                "onclick='subAdd("+curSection+","+hostid+","+weekid+",\""+date+"\")'/>"+
        "<input type='hidden' name='curSection' value='"+curSection+"'/>"+
        "<input type='hidden' name='hostid' value='"+hostid+"'/>"+
        "<input type='hidden' name='week_id' value='"+weekid+"'/>"+
        "<input type='hidden' name='date' value='"+date+"'/>"+
        "</form>";
        $(obj).parent().html(str);
        $(obj).remove();
    }
    // 提交表单
    function subAdd(curSection,hostid,weekid,date) {
        var div_id = "#"+hostid+"_"+curSection+"_"+weekid+"_"+"<%=settingType%>";
        var form_id = "#formSave_"+hostid+"_"+curSection+"_"+weekid;
        // 表单验证
        var curriculumName = $(div_id+" input[name='curriculumName']").eq(0).val().trim();
        var teacherName = $(div_id+" input[name='teacherName']").eq(0).val().trim();
        if (curriculumName == "" || teacherName == "") {
            layer.msg("请填写课程信息");
            return;
        }
        var options  = {
            dataType : 'json',
            success:function(jsonObj){
                if (jsonObj.flag == true) {
                    var str = '<div class="modif_liston">'+
                                '<span class="curSubject" title="'+jsonObj.data.curriculumName+'">'+jsonObj.data.curriculumName+'</span>'+
                                '<span class="curTeacher" title="'+jsonObj.data.teacherName+'">'+jsonObj.data.teacherName+'</span>'+
                                '<span class="curUnit" title="'+jsonObj.data.unit+'">'+jsonObj.data.unit+'</span>'+
                                '<div class="delete_icon_img"  onclick="delCurriculum(this)" curid="'+jsonObj.data.curriculumId+'"></div>'+
                                '<div class="modify_icon_img"></div>'+
                            '</div>'+
                            '<div class="modify_icon_show" style="display: none;height:115px;">'+
                                '<form action="/settings/Settings_updateCurriculum.do" style="height: 115px;" method="post" ' +
                                '      id="formUpdate_'+hostid+'_'+curSection+'_'+weekid+'" >'+
                                '<input type="text" name="curriculumName" class="modify_ic" value="'+jsonObj.data.curriculumName+
                                    '" placeholder="请输入科目名称"/>'+
                                '<input type="text" name="teacherName" class="modify_ic" value="'+jsonObj.data.teacherName+
                                    '" placeholder="请输入老师名称"/>'+
                                '<input type="text" name="unit" class="modify_ic" value="'+jsonObj.data.unit+
                                    '" placeholder="请输入教学单位"/>'+
                                '<input type="button" style="float:left;border: 0;background: #bdbdbd;color:#fff;margin: 5px;display: block" ' +
                                    'value="取消"'+'onclick="cancel()"/>'+
                                '<input type="button" style="float:left;border: 0;background: #28b779;color:#fff;margin: 5px;display: block" ' +
                                    'value="确定" onclick="subUpdate('+curSection+','+hostid+','+weekid+')"/>'+
                                '<input type="hidden" name="curSection" value="'+curSection+'"/>'+
                                '<input type="hidden" name="hostid" value="'+hostid+'"/>'+
                                '<input type="hidden" name="week_id" value="'+weekid+'"/>'+
                                '<input type="hidden" name="date" value="'+date+'"/>'+
                                '</form>'+
                            '</div>';
                    $(div_id).html(str);
                    //add by xinye
                    //msg的显示时间设定为1秒
                    layer.msg(jsonObj.data.msg,1);
                    //add by xinye end
                } else {
                    layer.msg(jsonObj.data.msg);
                }
            }
        };
        $(form_id).ajaxSubmit(options);
    };
    // 提交表单
    function subUpdate(curSection,hostid,weekid) {
        var div_id = "#"+hostid+"_"+curSection+"_"+weekid+"_"+"<%=settingType%>";
        var form_id = "#formUpdate_"+hostid+"_"+curSection+"_"+weekid;
        // 表单验证
        var curriculumName = $(div_id+" input[name='curriculumName']").eq(0).val().trim();
        var teacherName = $(div_id+" input[name='teacherName']").eq(0).val().trim();
        if (curriculumName == "" || teacherName == "") {
            layer.msg("请填写课程信息");
            return;
        }
        var options  = {
            dataType : 'json',
            success:function(jsonObj){
                if (jsonObj.flag == true) {
                    $(div_id+" input[name='curriculumName']").eq(0).val(jsonObj.data.curriculumName);
                    $(div_id+" input[name='teacherName']").eq(0).val(jsonObj.data.teacherName);
                    $(div_id+" input[name='unit']").eq(0).val(jsonObj.data.unit)
                    $(div_id+" .curSubject").html(jsonObj.data.curriculumName);
                    $(div_id+" .curTeacher").html(jsonObj.data.teacherName);
                    $(div_id+" .curUnit").html(jsonObj.data.unit);
                    $(div_id+" .curSubject").attr("title",jsonObj.data.curriculumName);
                    $(div_id+" .curTeacher").attr("title",jsonObj.data.teacherName);
                    $(div_id+" .curUnit").attr("title",jsonObj.data.unit);
                    $(div_id+" .modif_liston").show();
                    $(div_id+" .modify_icon_show").hide();
//                    layer.msg(jsonObj.data.msg);
                } else {
                    layer.msg(jsonObj.data.msg);
                }
            }
        };
        $(form_id).ajaxSubmit(options);
    };

    // 取消按钮
    function cancel() {
        $(".modify_icon_img").parent().parent().find(".modif_liston").show();
        $(".modify_icon_img").parent().parent().find(".modify_icon_show").hide();
    }
    // 取消按钮
    function cancel1(v) {
        $("#"+v).empty();
        var str="<div class='add_curriculum' onclick='addCurriculum(this)' style='height: 115px;width: 115px;'></div>"
        $("#"+v).html(str);
    }
</script>
