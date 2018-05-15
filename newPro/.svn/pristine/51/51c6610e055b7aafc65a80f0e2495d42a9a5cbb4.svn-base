<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.honghe.recordweb.service.frontend.resource.NasAutoDel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
    <title>系统设置 | 集控平台</title>
    <link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/time_celve.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/frontend/record/hpager.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/index.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/main.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/fd-slider.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/common/jquery-1.9.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/lb_common/screendetailforrecord.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/lb_frontend/setting/setting.js"></script>
</head>
<body>
	
<div class="public" style="min-width:1680px;">
        <jsp:include page="/pages/frontend/lb_header.jsp"></jsp:include>
        <style>
		.mm_list_text {
			text-indent: 0;
			
		}
	</style>
                <div class="set">
                    <div class="set_head">
                        <div class="settinghead">
                            <ul>
                                <li onclick="javascript:jump(0)"><div class="tb"></div>台标</li>
			                    <li onclick="javascript:jump(1)"><div class="videoinfo"></div>录像信息</li>
			                    <li onclick="javascript:jump(2)"><div class="living"></div>直播</li>
			                    <li onclick="javascript:jump(3)" style="color:#28b779;"><div class="nas"></div>NAS存储设置</li>
			                    <li onclick="javascript:jump(4)"><div class="passuser"></div>录播主机用户名密码设置</li>
			                    <li onclick="javascript:jump(5)" ><div class="ftp"></div>FTP设置</li>
			                    <li onclick="javascript:jump(6)" ><div class="license"></div>License设置</li>
                                <li onclick="javascript:jump(7)"><div class="second_nav_selected"></div>课表类型选择</li>
                                <li onclick="javascript:jump(8)"><div class="second_nav_schedule"></div>课表管理</li>
                                <li onclick="javascript:jump(9)"><div class="second_nav_celve"></div>作息时间策略</li>
                                <li onclick="javascript:jump(10)"><div class="record_name_setting"></div>录制文件名设置</li>
                            </ul>
                        </div>
                    </div>
                    <div class="win520_content" style="background: none;min-height: 540px;border:none">
                        <%
                            List<Object[]> nasList = (List<Object[]>) request.getAttribute("naslist");
                            String timeNasDelete = request.getAttribute("timeNasDelete") != null ? request.getAttribute("timeNasDelete").toString() : "";
                            if (nasList != null && nasList.size() > 0) {
                                for (Object[] obj : nasList) {
                        %>
                        <div class="mm_listout">

                            <div class="mm_list" style="background:white;margin: 0;cursor:default;">
                                <div class="mm_list_group">
                                    <div class="mm_list_text" style="float: left;width: 175px;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;" title="<%=obj[1]%>">
                                        根路径：<%=obj[1]%>
                                    </div>
                                    <div class="mm_list_text"
                                         style="font-size: 14px;float: left;width: 175px;color: #a3a3a3;"><%=obj[4]%>个班级
                                    </div>
                                </div>
                            </div>

                            <div class="mm_list_option" nasid="<%=obj[0]%>" nasroot="<%=obj[1]%>" nasuser="<%=obj[2]%>"
                                 naspwd="<%=obj[3]%>" hostcnt=<%=obj[4]%>" nastime="<%=NasAutoDel.getValue(obj[0].toString())%>">
                                <div class=" mm_list_options mm_list_edit
                            " title="修改NAS">
                        </div>
                        <div class="mm_list_options mm_list_add" id="<%=obj[0]%>" title="分配班级"></div>
                        <div class="mm_list_options mm_list_del" id="<%=obj[0]%>" title="删除"
                             style="float: right;"></div>

                    </div>
                </div>
                    <%
                            }
                        }
                    %>
                    <%
                    if(nasList == null || nasList.size()  < 3){


                    %>
                <a href="javascript:void(0)" class="newNas">
                    <div class="mm_add">
                        <div class="mm_add_logo">
                            <span class="mm_add_text">新建</span>
                        </div>
                    </div>
                </a>
                    <%}%>
</div>
</div>

<div class="foot" >
	<jsp:include page="/pages/frontend/footer.jsp"></jsp:include>
</div>
</div>
</body>
</html>
<script>
    $(function () {
        $(".newNas").click(function () {
            var url = "${pageContext.request.contextPath}/pages/frontend/setting/setting_nasAdd.jsp?nasId=-1&nasRootpath=&nasUsername=&nasPassword=&hostcnt=0&timeNasDelete=<%=timeNasDelete%>";
            $.layer({
                type: 2,
                title: '添加NAS存储路径',
                shadeClose: true,
                maxmin: false,
                fix: false,
                area: ['400px', '320px'],
                iframe: {
                    src: url
                }
            });
        });
        $(".mm_list_edit").click(function () {
            var nid = $(this).parent().attr("nasid");
            var nfile = $(this).parent().attr("nasroot");
            var nuser = $(this).parent().attr("nasuser");
            var npwd = $(this).parent().attr("naspwd");
            var hcnt = $(this).parent().attr("hostcnt");
            var nasTIme = $(this).parent().attr("nasTIme");
            var url = "${pageContext.request.contextPath}/pages/frontend/setting/setting_nasAdd.jsp?nasId=" + nid + "&nasRootpath=" + nfile + "&nasUsername=" + nuser + "&nasPassword=" + npwd + "&timeNasDelete=<%=timeNasDelete%>&nasTime="+nasTIme+"&hostcnt=" + hcnt;

            $.layer({
                type: 2,
                title: '添加NAS存储路径',
                shadeClose: true,
                maxmin: false,
                fix: false,
                area: ['400px', '420px'],
                iframe: {
                    src: url
                }
            });
        });
        $(".mm_list_del").click(function () {
            var nid = $(this).attr("id");
             	$.layer({
                shade: [0.5],
                maxmin: false,
                area: ['310px', '129px'],
                dialog: {
                    msg: '确定删除此nas根路径吗？',
                    btns: 2,
                    type: 4,
                    btn: ['确定','取消'],
                    yes: function(){
                         $.post("${pageContext.request.contextPath}/settings/Settings_deleteNas.do", {nasId: nid}, function (data) {
			                if (data.success == true) {
					                    location.reload();
					                }
					                else {
					                    layer.msg(data.msg);
					                }
					            }, 'json');
		                    }, no: function(){
                        	layer.closeAll()
                    }
                }
            });
           
        });
        $(".mm_list_add").click(function () {
            var nid = $(this).attr("id");
            $.layer({
                type: 2,
                title: '添加班级',
                shadeClose: true,
                maxmin: false,
                fix: false,
                iframe: {
                    src: "${pageContext.request.contextPath}/settings/Settings_nasAssignHost.do?nasId=" + nid,
                    scrolling: 'no'
                }
            });
        });
    })
</script>