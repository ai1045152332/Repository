<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.honghe.recordweb.util.MD5Util" %>
<%--
  Created by IntelliJ IDEA.
  User: chby
  Date: 2014/10/13
  Time: 14:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
    <title>班级列表--分组</title>

    <script src="${pageContext.request.contextPath}/js/common/jquery-1.9.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/jquery-migrate-1.2.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/frontend/device/group.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/perfect-scrollbar.with-mousewheel.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/prettify.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
             <script src="${pageContext.request.contextPath}/js/common/scrolldemo.js"></script>
</head>
<body>
	<input type="hidden" id="whichscroll">
<div class="public">
    <jsp:include page="/pages/frontend/equipment_header.jsp"></jsp:include>

    <script src="${pageContext.request.contextPath}/js/common/jquery.cookie.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/common/screendetail.js"></script>
    <div class="mm floatleft">
        <div class="mm_head floatleft">
            <a href="${pageContext.request.contextPath}/hostgroup/Hostgroup_management.do">
                <div class="mm_head_option" style="text-indent:12px">
                    <span class="back"></span>
                    <span style="font-size:14px;">返回</span>
                </div>
            </a>
            <%
                String hostgroupName = request.getAttribute("hostgroupName").toString();
            %>
            <div class="groupname" style="width: 100%; text-indent: 27px; line-height: 260%;">
                <span style="font-size:14px;"><%=hostgroupName%></span>
            </div>
        </div>
        <style>
	        .mm_list_text,.mm_list_textdevice{
                text-indent: 0;
                margin-top: 0;
	        }
        </style>
        <div class="mm_right floatleft" style="height: 93%;">
            <div id="mm_nogroup_video">
                <div class='mm_nogroup_video_content'>
                    <%
                        String hostgroupId = request.getParameter("hostgroupId");
                        List<Map> hostRelationList = (List<Map>) request.getAttribute("hostRelationList");
                        for (Map<String, Object> obj : hostRelationList) {
                            String status = "mm_offline";
                            String font = "离线";
                            String style = "mm_list_logo_gray";
                            String ip = obj.get("host_ip").toString().trim();
                            String hostid = obj.get("id").toString();
                            String hostDesc = obj.get("host_desc").toString();
                            String dtypeName = obj.get("dtype_name").toString();
                            String hostname = obj.get("name") == null ? "" : obj.get("name").toString().trim();
                            String onClick = "onclick=\"device(" + hostid + ",'" + ip + "','" + hostname + "'," + hostgroupId + ",'" +hostgroupName+ "')\"";
                            if (obj.containsKey("status") && obj.get("status").toString().equals("Online")) {
                                style = "mm_list_logo";
                                status = "mm_online";
                                font = "在线";
                            }
                            if (obj.containsKey("host_username") && obj.get("host_username").equals("@error@")) {
                                style = "mm_list_logoerror";
                            }
                    %>
                    <% if(dtypeName.equals("NVR")){%>
                    <%
                        if(hostDesc.equals("0")){ //简易录播
                            style = "mm_list_jy_logo_gray";
                            if (obj.containsKey("status") && obj.get("status").toString().equals("Online")) {
                                style = "mm_list_jy_logo";
                            }
                    %>
                    <div class="mm_listout" style="margin-top: 15px;">
                        <div class="mm_nogroup_list" style="margin: 0;cursor: pointer" bingo="none"
                             onclick="device('<%=obj.get("id")%>','<%=obj.get("host_ip")%>','<%=obj.get("name")%>','<%=hostgroupId%>','<%=hostgroupName%>')">
                            <div class="mm_list_group">
                                <div class="<%=style%>"></div>
                                <div class="mm_list_textdevice"><%=obj.get("name")%></div>
                                <div class="mm_list_text" style="font-size: 14px;">
                                    <%
                                        out.println(ip);
                                    %>
                                </div>
                                <span class="<%=status%>"><%=font%></span>
                            </div>
                        </div>
                        <div class="mm_list_option">
                            <style>
                                .bg,.bged{
                                    margin-left: 5px;
                                }
                                .mm_list_option{
                                    width: 175px;
                                }
                            </style>
                            <a href="javascript:void(0)" title="从分组中移除" style="cursor: default;">
                                <div hostId="<%=obj.get("id")%>" hostgroupId="<%=hostgroupId%>" class="mm_list_options mm_list_del" style="float: right;"></div>
                            </a>
                            <%
                                if (obj.get("name").toString().equals("") || MD5Util.ipValid(obj.get("name").toString())) {
                            %>
                            <span hostId="<%=obj.get("id")%>" hostIp="<%=obj.get("host_ip")%>" class="mm_nogroup_list_machineset" style="background-position: -1px 0;" title="编辑班级信息"></span>
                            <%
                                }
                            %>
                            <%
                                if(font.equals("在线") && hostDesc.equals("0")){
                            %>
                            <a href="javascript:nvrView('<%=obj.get("host_ip").toString().trim()%>','<%=obj.get("host_username").toString()%>','<%=obj.get("host_password").toString()%>')" style="cursor: default;">
                                <span class="mm_list_options mm_list_setnvr" style="float: right;" title="查看NVR"></span>
                            </a>
                            <%
                                }
                            %>
                        </div>
                    </div>
                    <%
                    }else{//精品录播
                        style = "mm_list_jp_logo_gray";
                        if (obj.containsKey("status") && obj.get("status").toString().equals("Online")) {
                            style = "mm_list_jp_logo";
                        }
                    %>
                    <div class="mm_listout" style="margin-top: 15px;">
                        <div class="mm_nogroup_list" style="margin: 0;cursor: pointer" bingo="none"
                             onclick="device('<%=obj.get("id")%>','<%=obj.get("host_ip")%>','<%=obj.get("name")%>','<%=hostgroupId%>','<%=hostgroupName%>')">
                            <div class="mm_list_group">
                                <div class="<%=style%>"></div>
                                <div class="mm_list_textdevice"><%=obj.get("name")%>
                                </div>
                                <div class="mm_list_text" style="font-size: 14px;">
                                    <%
                                        out.println(ip);
                                    %>
                                </div>
                                <span class="<%=status%>"><%=font%></span>
                            </div>
                        </div>
                        <div class="mm_list_option">
                            <style>
                                .bg,.bged{
                                    margin-left: 5px;
                                }
                                .mm_list_option{
                                    width: 175px;
                                }
                            </style>
                            <a href="javascript:void(0)" title="从分组中移除" style="cursor: default;">
                                <div hostId="<%=obj.get("id")%>" hostgroupId="<%=hostgroupId%>" class="mm_list_options mm_list_del" style="float: right;"></div>
                            </a>
                            <%
                                if (obj.get("name").toString().equals("") || MD5Util.ipValid(obj.get("name").toString())) {
                            %>
                            <span hostId="<%=obj.get("id")%>" hostIpaddr="<%=obj.get("host_ip")%>" class="mm_nogroup_list_machineset" title="编辑班级信息"></span>
                            <%
                                }
                            %>
                            <%
                                if(font.equals("在线")){
                            %>
                            <a href="javascript:nvrView('<%=obj.get("host_ip").toString().trim()%>','<%=obj.get("host_username").toString()%>','<%=obj.get("host_password").toString()%>')" style="cursor: default;">
                                <%--<span class="mm_list_options mm_list_setnvr" style="float: right;"--%>
                                 <%--title="查看NVR"></span>--%>
                            </a>
                            <%}%>
                        </div>
                    </div>
                    <%
                        }
                    %>
                    <%
                    }else if(dtypeName.equals("HHTC")){//大屏设备
                        style = "mm_list_dp_logo_gray";
                        if (obj.containsKey("status") && obj.get("status").toString().equals("Online")) {style = "mm_list_dp_logo";
                        }
                    %>
                    <div class="mm_listout" style="margin-top: 15px;">
                        <div class="mm_nogroup_list" style="margin: 0;cursor: default" bingo="none">
                            <div class="mm_list_group">
                                <div class="<%=style%>"></div>
                                <div class="mm_list_textdevice"><%=obj.get("name")%>
                                </div>
                                <div class="mm_list_text" style="font-size: 14px;">
                                    <%
                                        out.println(ip);
                                    %>
                                </div>
                                <span class="<%=status%>"><%=font%></span>
                            </div>
                        </div>
                        <div class="mm_list_option">
                            <style>
                                .bg,.bged{
                                    margin-left: 5px;
                                }
                                .mm_list_option{
                                    width: 175px;
                                }
                            </style>
                            <a href="javascript:void(0)" title="从分组中移除" style="cursor: default;">
                                <div hostId="<%=obj.get("id")%>" hostgroupId="<%=hostgroupId%>" class="mm_list_options mm_list_del" style="float: right;"></div>
                            </a>
                            <%
                                if (obj.get("name").toString().equals("") || MD5Util.ipValid(obj.get("name").toString())) {
                            %>
                            <span hostId="<%=obj.get("id")%>" hostIpaddr="<%=obj.get("host_ip")%>" class="mm_nogroup_list_machineset" title="编辑班级信息"></span>
                            <%
                                }
                            %>
                            <%
                                if(font.equals("在线")){
                            %>
                            <a href="javascript:nvrView('<%=obj.get("host_ip").toString().trim()%>','<%=obj.get("host_username").toString()%>','<%=obj.get("host_password").toString()%>')" style="cursor: default;">
                                                        <%--<span class="mm_list_options mm_list_setnvr" style="float: right;"--%>
                                                        <%--title="查看NVR"></span>--%>
                            </a>
                            <%}%>
                        </div>
                    </div>
                    <%
                    } else if (dtypeName.equals("HTPR")) {//投影仪设备
                        style = "mm_list_htprojectorlogo_gray";
                        if (obj.containsKey("status") && obj.get("status").toString().equals("Online")) {
                            style = "mm_list_htprojectorlogo";
                        }
                    %>
                    <div class="mm_listout" style="margin-top: 15px;">
                        <div class="mm_nogroup_list" style="margin: 0;cursor: default" bingo="none">
                            <div class="mm_list_group">
                                <div class="<%=style%>"></div>
                                <div class="mm_list_textdevice"><%=obj.get("name")%>
                                </div>
                                <div class="mm_list_text" style="font-size: 14px;">
                                    <%
                                        out.println(ip);
                                    %>
                                </div>
                                <span class="<%=status%>"><%=font%></span>
                            </div>
                        </div>
                        <div class="mm_list_option">
                            <style>
                                .bg, .bged {
                                    margin-left: 5px;
                                }

                                .mm_list_option {
                                    width: 175px;
                                }
                            </style>
                            <span class="bg" hostId="<%=obj.get("id")%>" hostname="<%=obj.get("name")%>"></span>
                        <span class="mm_nogroup_del" style="float: right;" title="删除班级"
                              onclick="delhost('<%=obj.get("id")%>','<%=obj.get("host_ip")%>')"></span>
                            <%
                                if (obj.get("name").toString().equals("") || MD5Util.ipValid(obj.get("name").toString())) {
                            %>
                        <span hostId="<%=obj.get("id")%>" hostIpaddr="<%=obj.get("host_ip")%>"
                              class="mm_nogroup_list_machineset" title="编辑班级信息"></span>
                            <%
                                }
                            %>
                            <%
                                if (font.equals("在线")) {
                            %>
                            <a href="javascript:nvrView('<%=obj.get("host_ip").toString().trim()%>','<%=obj.get("host_username").toString()%>','<%=obj.get("host_password").toString()%>')"
                               style="cursor: default;">
                                <%--<span class="mm_list_options mm_list_setnvr" style="float: right;"--%>
                                <%--title="查看NVR"></span>--%>
                            </a>
                            <%}%>
                        </div>
                    </div>
                    <%
                    } else{
                        style = "mm_list_dp_unkonwnlogo_gray";
                        if (obj.containsKey("status") && obj.get("status").toString().equals("Online")) {
                            style = "mm_list_dp_unkonwnlogo";
                        }
                    %>
                    <div class="mm_listout" style="margin-top: 15px;">
                        <div class="mm_nogroup_list" style="margin: 0;cursor: default" bingo="none">
                            <div class="mm_list_group">
                                <div class="<%=style%>"></div>
                                <div class="mm_list_textdevice"><%=obj.get("name")%></div>
                                <div class="mm_list_text" style="font-size: 14px;">
                                    <%
                                        out.println(ip);
                                    %>
                                </div>
                                <span class="<%=status%>"><%=font%></span>
                            </div>
                        </div>
                        <div class="mm_list_option">
                            <style>
                                .bg,.bged{
                                    margin-left: 5px;
                                }
                                .mm_list_option{
                                    width: 175px;
                                }
                            </style>
                            <a href="javascript:void(0)" title="从分组中移除" style="cursor: default;">
                                <div hostId="<%=obj.get("id")%>" hostgroupId="<%=hostgroupId%>" class="mm_list_options mm_list_del" style="float: right;"></div>
                            </a>
                        </div>
                    </div>

                    <%    }%>
                    <%
                        }//for end
                    %>
                </div>
            </div>
        </div>
        <script>
            function change_size() {
                $("#mm_nogroup_video").width($(".mm_right").width()).height($(".mm_right").height());
                                        // update perfect scrollbar
                $('#mm_nogroup_video').perfectScrollbar('update');
            }
            $(function () {
                $("#mm_nogroup_video").width($(".mm_right").width()).height($(".mm_right").height());
                $('#mm_nogroup_video').perfectScrollbar();
                                         prettyPrint();
            });
        </script>

    </div>
    <div class="foot">
        <jsp:include page="../footer.jsp"></jsp:include>
    </div>
    <input type="text" style="display: none" id="hostgroupSelected" value="" class="test" urlhead="${pageContext.request.contextPath}"/>
    <form id="deviceForm" action="${pageContext.request.contextPath}/device/Device_deviceList.do" method="POST">
        <input type="hidden" id="hostId" name="hostId"/>
        <input type="hidden" id="hostIp" name="hostIp"/>
        <input type="hidden" id="hostName" name="hostName">
        <input type="hidden" id="goBackUrl" name="goBackUrl" value="${pageContext.request.contextPath}/host/Host_loadHostByHostgroup.do?hostgroupId=<%=hostgroupId%>&hostgroupName=<%=hostgroupName%>"/>
    </form>

</div>
<form target="_blank" method="GET" id="nvrForm">
    <input type="hidden" value="honghe2015" name="username" id="userName"/>
    <input type="hidden" value="2015honghe" name="password" id="password"/>
</form>
</body>
</html>
<script>
    var urlhead = $("#hostgroupSelected").attr("urlhead");
    var _hostgroupId = "<%=hostgroupId%>";
    $(".mm_list_edit").live("click", function () {
        var selectId = $(this).attr("hostId");
        // alert(selectId);
        $.layer({
            type: 2,
            title: '编辑班级信息',
            shadeClose: true,
            maxmin: false,
            fix: false,
            area: ['412px', 427],
            iframe: {
                src: urlhead + '/host/Host_editHostInfo.do?hostId=' + selectId
            }
        });
    });
    $(".mm_list_del").live("click", function () {
        var selectId = $(this).attr("hostId");
        var delUrl = urlhead + "/host/Host_delHostRelation.do";
         var selectName = $(this).parents(".mm_listout").find(".mm_list_textdevice").text();
        //  alert(selectId);
        var hostgroupId = $(this).attr("hostgroupId");
        $.layer({
            shade: [0.5, '#000'],
            area: ['310px', '129px'],
            title: '移除班级',
            dialog: {
                msg: '您确定移除&nbsp;'+selectName+'&nbsp;吗？',
                btns: 2,
                type: 4,
                btn: ['确认', '取消'],
                yes: function () {
                    $.post(delUrl, {'hostIds': selectId, 'hostgroupId': hostgroupId}, function (data) {
                        layer.msg(data.msg);
                        if (data.success == true) {
                            window.location.reload();
                        }
                    }, 'json')
                }, no: function () {

                }
            }
        });
    });
    function device(hostId, hostIp, hostName,hostGroupId,hostGroupName) {
        <%--$("#hostId").attr("value", hostId);--%>
        <%--$("#hostIp").attr("value", hostIp);--%>
        <%--$("#hostName").attr("value", hostName);--%>
        <%--//$("#deviceForm").submit();--%>
        <%--var goBackUrl = $("#goBackUrl").attr("value");--%>
        <%--location.href = "${pageContext.request.contextPath}/device/Device_deviceList.do?hostId="+hostId+"&hostName="+hostName+"&goBackUrl="+goBackUrl+"&hostIp="+hostIp;--%>

        var hostNameEncode = encodeURI(hostName);
        var hostGroupNameEncode = encodeURI(hostGroupName);
        location.href = "${pageContext.request.contextPath}/device/Device_deviceList.do?hostId="+hostId+"&hostName="+hostNameEncode+"&hostGroupId="+hostGroupId+"&hostGroupName="+hostGroupNameEncode+"&hostIp="+hostIp+"&type=1";

    }
    function deviceOffLine() {
        layer.msg("主机离线，无法查看附属设备！");
    }
    function config(hostId) {
        // alert(hostId);
        $.layer({
            type: 2,
            title: 'NVR设置',
            shadeClose: true,
            maxmin: false,
            fix: false,
            iframe: {
                src: urlhead + '/host/Host_hostConfig.do?hostId=' + hostId,
                scrolling: 'no'
            }
        });
    }
    function nasSetting() {
       // location.href = urlhead + "/host/Host_loadHostAndNasByHostgroup.do?hostgroupId=" + _hostgroupId;
    }

    function nvrView(ip, userName, password) {
        var nvrForm = document.getElementById("nvrForm");
        nvrForm.action = "http://" + ip+"/Custom/userCheck/";
//        $("#userName").attr("value", userName);
//        $("#password").attr("value", password);
        nvrForm.submit();
    }
    $(".mm_nogroup_list_machineset").click(function () {
        var selectId = $(this).attr("hostId");
        var hostIpaddr = $(this).attr("hostIpaddr");
        var url = urlhead + "/host/Host_updateHost.do";
        layer.prompt({title: '输入班级名称', val: "", length: 20}, function (name) {
            name = $.trim(name);
            if (name != "") {
                $.post(url, {'hostId': selectId,hostIpaddr:hostIpaddr, 'hostName': name}, function (data) {
                    layer.msg(data.msg);
                    if (data.success == true) {
                        window.parent.location.reload();
                    }
                }, 'json');
            }
            else {
                var val = $(".xubox_title em").text("班级名称不能为空!");
                $("#xubox_prompt").val("").focus();
            }
        });
    });
</script>