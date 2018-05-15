<%@ page import="java.util.List" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
    <title>设备管理 | 镜头管理</title>
    <link href="${pageContext.request.contextPath}/css/frontend/index.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/common/jquery-1.9.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/checkbox.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/change49.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/jquery.cookie.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/common/autoheight.js"></script>
</head>

<body>
<div class="public">
    <jsp:include page="/pages/frontend/lb_header.jsp"></jsp:include>
    <%

        String type = request.getParameter("type");
        String goBackUrl = "";
        if(type.equals("1"))
        {
            String hostGroupId = request.getParameter("hostGroupId");
            String hostGroupName = URLDecoder.decode(request.getParameter("hostGroupName"), "UTF-8");
            goBackUrl = request.getContextPath() + "/host/Host_loadHostByHostgroup.do?hostgroupId="+hostGroupId+"&hostgroupName="+hostGroupName+"&flag=0";
        } else if (type.equals("2")) {
            goBackUrl = request.getContextPath() + "/host/Host_getDeviceBytype.do?type=hhrec";
        } else if (type.equals("3")) {
            goBackUrl = request.getContextPath() + "/host/Host_hostManagement.do?flag=0";
        } else
        {
            goBackUrl = request.getContextPath() + "/host/Host_hostManagement.do?flag=0";
        }

        String ip = request.getParameter("hostIp").toString();
        List<Object[]> deviceList = (List<Object[]>) request.getAttribute("deviceList");
        String hostId = request.getParameter("hostId");
        String hostName = URLDecoder.decode(request.getParameter("hostName"),"UTF-8");
        String hostFactory = request.getAttribute("hostFactory").toString();
    %>
    <div class="user floatleft">
        <div class="mm_head floatleft">
            <a href="javascript:goBack('<%=goBackUrl%>')">
            <%--<a href="javascript:history.go(-1)">--%>
                <span class="mm_head_option" style="margin-left: 20px;text-indent:12px"><span class="mm_nogroup_icon"></span>返回</span>
            </a>
            <span class="mm_head_option"><%=hostName%></span>
        </div>
        <%
            for (int i = 0; i < deviceList.size(); i++) {
                Object[] device = deviceList.get(i);
        %>
        <div class="mm_list" style="margin-top: 30px;">
            <div class="mm_list_group">
                <div class="mm_list_text" style="float: left;width: 170px;"><%=device[1]%>
                </div>
            </div>
            <div class="mm_list_option" style="width: 185px;">
                <%
                    if(hostFactory.equals("Arec") && (device[1].toString().equals("电影模式") || device[1].toString().equals("VGA")))
                    {
                %>
                <%--<span hostIp="<%=ip%>" hostId="<%=device[2]%>" deviceId="<%=device[0]%>" mainToken="<%=device[5]%>" class="mm_nogroup_list_machineset" style="background-position: -1px 0;"
                      title="重定向媒体流地址"></span>--%>
                <%
                    }
                %>
                <a  href="javascript:cameraInfo('<%=device[0]%>')"  style="cursor: default;" >
                    <div class="mm_list_options mm_nogroup_list_videoeye" style="margin-top: 5px;" title="镜头预览"></div>
                </a>
            </div>
        </div>
        <%
            }
        %>
    </div>
    <div class="foot">
        <jsp:include page="/pages/frontend/footer.jsp"></jsp:include>
    </div>
</div>

</div>
<script>
    /*function addDevice() {
     var path = "${pageContext.request.contextPath}/device/Device_addDeviceList.do?hostId=<%=hostId%>&hostIp=<%=ip%>"
     $.layer({
     isClose: false,
     type: 2,
     title: '添加设备',
     shadeClose: true,
     maxmin: false,
     fix: false,
     iframe: {
     src: path
     },
     close: function (index) {
     this.isClose = true;
     },
     end: function () {
     if (!this.isClose)
     $("#deviceForm").submit();
     }
     });
     }
     */

    $(function(){
        $(".mm_nogroup_list_machineset").click(function () {
            var deviceId = $(this).attr("deviceId");
            var mainToken = $(this).attr("mainToken");
            var hostId = $(this).attr("hostId");
            var hostIp = $(this).attr("hostIp");
            if(!mainToken || mainToken == "")
            {
                mainToken = "rtp://"
            }
            var url = "${pageContext.request.contextPath}/device/Device_updateCameraInfo.do";
            layer.prompt({title: '请输入重定向媒体流地址', val: mainToken, length: 40}, function (name) {
                name = $.trim(name);
                if (name != "") {
                    $.post(url, {'deviceId': deviceId, 'mediaAddr': name,'hostId':hostId,'hostIp':hostIp}, function (data) {
                        layer.msg(data.msg);
                        if(data.success == true)
                        {
                            setTimeout(function(){
                                window.location.reload();
                            },1000);

                        }
                    }, 'json');
                }
                else {
                    var val = $(".xubox_title em").text("端口号不能为空!");
                    $("#xubox_prompt").val("").focus();
                }
            });
        });
    });
    var deletePath = "${pageContext.request.contextPath}/device/Device_deleteDevice.do";
    function deleteDevice(deviceId) {
        $.layer({
            shade: [0.5, '#000'],
            maxmin: false,
            area: ['221', '129'],
            title:'删除设备',
            dialog: {
                msg: '确定删除该设备？',
                btns: 2,
                type: 4,
                btn: ['确定', '取消'],
                yes: function () {
                    $.get(deletePath, {'deviceId': deviceId}, function (data) {
                        if (data.success == true) {
                            layer.msg(data.msg, 1, 1);
                            setTimeout(function () {
                                $("#deviceForm").submit();
                            }, 1000);

                        }
                        else {
                            layer.msg(data.msg);
                        }
                    }, 'json')
                }, no: function () {

                }
            }
        });

    }

    function updateDevice(deviceId, deviceType) {
        var path = "${pageContext.request.contextPath}/device/Device_updateDeviceList.do?hostId=<%=hostId%>&hostIp=<%=ip%>&deviceId=" + deviceId + "&deviceType=" + deviceType;
        $.layer({
            isClose: false,
            type: 2,
            title: '修改设备',
            shadeClose: true,
            maxmin: false,
            fix: false,
            time:0,
            iframe: {
                src: path
            },
            close: function (index) {
                this.isClose = true;
            },
            end: function () {
                if (!this.isClose)
                    $("#deviceForm").submit();
            }
        });
    }

    function updateIPC(deviceId) {
        var path = "${pageContext.request.contextPath}/device/Device_updateIPC.do?hostId=<%=hostId%>&hostIp=<%=ip%>&deviceId=" + deviceId ;
        $.layer({
            isClose: false,
            type: 2,
            title: 'IPC设备',
            shadeClose: true,
            maxmin: false,
            fix: false,
            //time:0,
            area: ['520px', '420px'],
            iframe: {
                src: path
            },
            close: function (index) {
                this.isClose = true;
            },
            end: function () {
                if (!this.isClose)
                    $("#deviceForm").submit();
            }
        });
    }
    function goBack(url){
        location.href = encodeURI(url);
    }
    function cameraInfo(deviceId)
    {
        var path = "${pageContext.request.contextPath}/device/Device_cameraInfo.do?hostId=<%=hostId%>&hostIp=<%=ip%>&deviceId=" + deviceId;
        $.layer({
            isClose: false,
            type: 2,
            title: '预览镜头',
            shadeClose: true,
            maxmin: false,
            fix: false,
            area:['520px','355px'],
            iframe: {
                src: path
            },
            close: function (index) {
                this.isClose = true;
            },
            end: function (index) {
//                if (!this.isClose)
//                    $("#deviceForm").submit();
                this.isClose = true;
            }
        });
    }

</script>
</body>
</html>

