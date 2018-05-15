<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link href="${pageContext.request.contextPath}/css/frontend/record/hpager.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/index.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/main.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/fd-slider.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
    <link href="${pageContext.request.contextPath}/css/frontend/record/main.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/frontend/record/index.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%

    response.setHeader("Cache-Control","no-store");

    response.setHeader("Pragrma","no-cache");

    response.setDateHeader("Expires",0);

%>
<%
    int nasId = Integer.parseInt(request.getParameter("nasId"));
    String nasRootpath = request.getParameter("nasRootpath");
    String nasUsername = request.getParameter("nasUsername");
    String nasPassword = request.getParameter("nasPassword");
    int hostCnt = Integer.parseInt(request.getParameter("hostcnt"));
    String timeNasDelete = request.getParameter("timeNasDelete");
    String nasTime= request.getParameter("nasTime");
    String[] nasTimeArr = timeNasDelete.split(";");
%>
<style>
    .tab_content_listinput{
        width:230px;
    }
    .tab_content_listle{
        width:100%;
        float:left;
        margin-top: 10px;
    }
    .tab_content_listle > p{
        float:left;
        width: 100px;
        height: 30px;
        line-height: 30px;
        text-align: right;
    }
    .tab_content_listle > span{
        height: 30px;
        line-height: 30px;
        float: left;
        margin-bottom: 20px;
    }
    .tab_content_listle span.select_bb{
        cursor: pointer;
        background: url(../../../image/frontend/radio.gif) 0 0 no-repeat;
        margin-top: 7px;
        margin-left: 10px;
        width:16px;
        height: 16px;
        margin-right: 10px;
    }
    .tab_content_listle span.select_bd{
        cursor: pointer;
        background: url(../../../image/frontend/radio.gif) 0 -35px no-repeat;
        margin-top: 7px;
        margin-left: 10px;
        width:16px;
        height: 16px;
        margin-right: 10px;
    }
    .tab_content_listle span p{
        float:left;
    }
    .selectdiv{
        text-align: left;
        text-indent:10px;
        width: 100px;
        height: 28px;
        line-height: 28px;
    }
    .selectdivul{
        width: 98px;
    }
    .selectdivul a,#selectdivall0,.select,.sall{
        width: 100px;
        height: 28px;
        line-height: 28px;
        text-align: center;
    }
    #selectdivall0{
        background: url("../../../image/frontend/selectdiv_iconcc.png") no-repeat;
    }
</style>
<div class="win_content" style="background:white;width: 380px;height:280px;margin: 0px;/*box-shadow: 0 0 0 1px #212121 inset;*/">
    <div class="tab_content_list" style="margin-top: 25px">
        <span class="tab_content_listtext" style="width: 100px;">NAS根路径</span>
        <input id="nasroot" type="text" class="tab_content_listinput" value="<%=nasRootpath%>" />
    </div>
    <div class="tab_content_list">
        <span class="tab_content_listtext" style="width: 100px;">用户名</span>
        <input id="nasuser" type="text" class="tab_content_listinput" value="<%=nasUsername%>"/>
    </div>
    <div class="tab_content_list">
        <span class="tab_content_listtext" style="width: 100px;">密码</span>
        <input id="naspwd" type="text" class="tab_content_listinput" value="<%=nasPassword%>"/>
    </div>
    <div class="tab_content_listle">
        <p>存储策略</p>
        <%--<span class="select_bb" id="select_icon_i"></span>--%>
        <%--<span>循环录像</span>--%>
        <%--<span class="select_bd" id="select_icon_i" style="clear: both;margin-left: 110px;"></span>--%>
        <span><p>&nbsp;&nbsp;自动删除：</p>
            <div class="sall">
            <select class="select" id="select0">
                <%
                  if(nasTimeArr != null && nasTimeArr.length > 0)
                  {
                      for(String day : nasTimeArr)
                      {
                %>
                 <option value="<%=day%>"><%=day%>天前</option>
                <%
                        }
                  }
                %>
            </select>

            <div class="selectdivall" id="selectdivall0">
                <div class="selectdiv" id="selectdiv0"></div>
                <div class="selectdivul" id="selectdivul0" style="overflow: hidden;border:1px solid #c5c5c5;border-top: 0;"></div>
            </div>
            </div>
            <p>的录像</p>
        </span>
    </div>
    <div class="win_btn" style="margin: 20px 20px 20px 0;">
        <div class="win_btn_sure" style="margin-right: 20px;">确定</div>
        <div class="win_btn_done">取消</div>
    </div>
</div>

<input type="hidden" id="nasid" value="<%=nasId%>"/>
</body>
<input type="hidden" id="hostcnt" value="<%=hostCnt%>"/>
</body>
<script type="text/javascript">
    $(function () {
        $(".win_btn_done").click(function () {
            parent.layer.closeAll();
        });
        $(document).on("click","span#select_icon_i",function(){
            $("span#select_icon_i").attr("class","select_bb");
            var flag = $(this).attr("class");
            if(flag=="select_bb"){
                $(this).attr("class","select_bd");
            }else{
                $(this).attr("class","select_bb");
            }
        })
        var nas_time = "<%=nasTime == null ? "" :nasTime%>";
        if(nas_time != "" && nas_time > 0)
        {
            $("#select0").val(nas_time);
            $(".selectdivall").click(function () {
                $(this).parent().find(".selectdivul").find("a").each(function () {
                    //alert($(this).text().replace(/[^0-9]/ig,""))
                    if($(this).text().replace(/[^0-9]/ig,"")==nas_time){
                            $(this).css({"background":"#28b779","color":"#333"})
                    }else {
                            $(this).css({"background":"#fff","color":"#333"})
                    }
                })
            })
        }
        //alert($("#select0").val() + "----------------");
        $(".win_btn_sure").click(function () {
            var nid = $("#nasid").val();
            var nroot = $("#nasroot").val();
            var nasTime = $("#select0").val();
          //  alert(nasTime);
            if(nroot=="")
            {
                layer.msg("请输入NAS根路径");
                return;
            }
            var nuser = $("#nasuser").val();
            var npwd = $("#naspwd").val();
            var hcnt = $("#hostcnt").val();

            if(nid==-1)
            {
                $.post("${pageContext.request.contextPath}/settings/Settings_hasNasPath.do", {nasId: nid,nasRootpath:nroot}, function (data) {
                    if(data.success==true)
                    {
                        layer.msg(data.msg);
                    }
                    else
                    {
                        $.post("${pageContext.request.contextPath}/settings/Settings_addNas.do", {nasId: nid,nasRootpath:nroot,nasUsername:nuser,nasPassword:npwd,hostCnt:hcnt,nasTime:nasTime}, function (data) {
                            if(data.success==true)
                            {
                                parent.window.location.href="${pageContext.request.contextPath}/settings/Settings_findNas.do";
                            }
                            else
                            {
                                layer.msg(data.msg);
                            }
                        },'json');

                    }
                },'json');

            }
            else
            {
                $.post("${pageContext.request.contextPath}/settings/Settings_hasNasPath.do", {nasId: nid,nasRootpath:nroot}, function (data) {
                    if(data.success==true)
                    {
                        layer.msg(data.msg);
                    }else
                    {
                        $.post("${pageContext.request.contextPath}/settings/Settings_updateNas.do", {nasId: nid,nasRootpath:nroot,nasUsername:nuser,nasPassword:npwd,hostCnt:hcnt,nasTime:nasTime}, function (data) {
                            if(data.success==true)
                            {
                                parent.window.location.href="${pageContext.request.contextPath}/settings/Settings_findNas.do";
                            }
                            else
                            {
                                layer.msg(data.msg);
                            }
                        },'json');

                    }
                },'json');


            }
        });
    })
</script>
<script src="${pageContext.request.contextPath}/js/lb_common/selectmore_set.js"></script>
