<%@ page import="java.util.Map" %>
<%--
  Created by IntelliJ IDEA.
  User: hthwx
  Date: 2015/3/26
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
    <title></title>
    <link href="${pageContext.request.contextPath}/css/frontend/record/hpager.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/index.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/main.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/fd-slider.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/common/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
</head>
<body>
<%
    Map<String,String> map = (Map<String,String>)request.getAttribute("resMapUpdate");
    String hostIp = request.getAttribute("hostIp").toString();
    String recordType = request.getAttribute("recordType").toString();
%>
<style>
	.tab_content_list{
    float: left;
    min-height: 30px;
    margin-top: 15px;
    min-width: 88px;
    position: relative;
    width: auto;
}
.tab_content_listtext{
    float: left;
    font-size: 12px;
    height: 30px;
    line-height: 30px;
    text-align: right;
    min-width: 88px;
    width: auto;
}
</style>
<div class="win_content" style="background:white;width: 366px;height:370px;margin: 0px;box-shadow: 0 0 0 1px #212121 inset;">
    <div class="tab_content_list" style="margin-top: 25px;">
        <span class="tab_content_listtext">标题：</span>
        <input id="resTitle" class="tab_content_listinput" maxlength="30" value="<%=map != null && !map.isEmpty() && map.containsKey("res_title") && map.get("res_title")!= null ? map.get("res_title").toString() : ""%>" type="text">
    </div>
    <div class="tab_content_list">
        <span class="tab_content_listtext">主讲课程：</span>
        <input id="resCourse" class="tab_content_listinput" maxlength="30" value="<%=map != null && !map.isEmpty() && map.containsKey("res_course") && map.get("res_course")!= null ? map.get("res_course").toString() : ""%>" type="text">
    </div>
    <div class="tab_content_list">
        <span class="tab_content_listtext">年级：</span>
        <input id="resGrade" class="tab_content_listinput" maxlength="30" value="<%=map != null && !map.isEmpty() && map.containsKey("res_grade") && map.get("res_grade")!= null ? map.get("res_grade").toString() : ""%>" type="text">
    </div>
    <div class="tab_content_list" >
        <span class="tab_content_listtext">科目：</span>
        <input id="resSubject" class="tab_content_listinput" maxlength="30" value="<%=map != null && !map.isEmpty() && map.containsKey("res_subject") && map.get("res_subject")!= null ? map.get("res_subject").toString() : ""%>" type="text">
    </div>
    <div class="tab_content_list">
        <span class="tab_content_listtext">主讲人：</span>
        <input id="resSpeaker" class="tab_content_listinput" maxlength="30" value="<%=map != null && !map.isEmpty() && map.containsKey("res_speaker") && map.get("res_speaker")!= null ? map.get("res_speaker").toString() : ""%>" type="text">
    </div>
    <div class="tab_content_list">
        <span class="tab_content_listtext">学校：</span>
        <input id="resSchool" class="tab_content_listinput" maxlength="30" value="<%=map != null && !map.isEmpty() && map.containsKey("res_school") && map.get("res_school")!= null ? map.get("res_school").toString() : ""%>" type="text">
    </div>
    <div class="win_btn" style="margin: 35px 20px 20px 0;">
        <div class="win_btn_sure" style="margin-right: 20px;" resId ="<%=map != null && !map.isEmpty() && map.containsKey("res_id") && map.get("res_id")!= null ? map.get("res_id").toString() : ""%>">确定</div>
        <div class="win_btn_done">取消</div>
    </div>
</div>
</body>
</html>
<script>
    var clickNum = 0;
    var urlHead = "${pageContext.request.contextPath}";
    var host_ip = "<%=hostIp%>";
    var record_type = "<%=recordType%>";
    $(".win_btn_sure").click(function(){
        var resId = $(this).attr("resId");

        clickNum ++ ;
        if(resId != null && resId.trim(" ") != "")
        {
            $.post(urlHead + "/resource/Resource_updateResource.do",{
                resTitle:$("#resTitle").val(),resGrade:$("#resGrade").val(),resCourse:$("#resCourse").val(),resSubject:$("#resSubject").val(),
                resSpeaker:$("#resSpeaker").val(),resSchool:$("#resSchool").val(),resId:resId,hostIp:host_ip,recordType:record_type
            },function(data){
                layer.msg(data.msg);
                clickNum = 0;
                if (data.success == true) {
                    //	window.location.reload();
                    setTimeout(function(){
                        parent.window.location.reload();
                    },1000);
                }

            },"json");
        }
       else
        {
            layer.msg("未找到数据！");
            clickNum = 0;
        }
    });
    $(".win_btn_done").click(function(){
        window.parent.layer.closeAll();
    });
</script>


