<%@ page import="java.util.Map" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="com.honghe.recordweb.action.SessionManager" %>
<%@ page import="com.honghe.recordhibernate.entity.User" %>
<%@ page import="com.honghe.recordweb.service.frontend.user.UserService" %>
<%@ page import="com.honghe.recordweb.util.ConfigureUtil" %>
<%@ page import="com.honghe.recordweb.util.LicenseUtils" %>
<%--
  Created by IntelliJ IDEA.
  User: zhanghongbin
  Date: 14-10-8
  Time: 下午4:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="${pageContext.request.contextPath}/css/frontend/record/index.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/frontend/record/main.css" rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/js/common/header.js"></script>
<%

    response.setHeader("Cache-Control","no-store");

    response.setHeader("Pragma","no-cache");

    response.setDateHeader("Expires",0);

    boolean isHhrec = ConfigureUtil.isOnlyHhrec();//判断是否只是录播设备

%>
<style>
.tab_content_listinput{
	border:1px solid #bbb
}
.tab_content_listtext{
	color: #80858F;
}
.public_nav ul li{
    float: left;
    height: 70px;
    line-height: 70px;
    position: relative;
    width: 85px;
}
</style>

    <input type="hidden" value="${pageContext.request.contextPath}" id="root_path">

        <div class="public_head">
        	<div class="public_head_qp"></div>
            <%
                if(isHhrec){
            %>
            <div class="public_head_logo2"></div>
            <%
            }else{
            %>
            <div class="public_head_logo"></div>
            <%
                }
            %>

            <div class=" public_setting" >
                <%
                    User curruser = SessionManager.get(request.getSession(), SessionManager.Key.USER);
                    Integer uid = curruser.getUserId();
                %>
                <div class="public_setting_menu">
                    <li><span style="position: relative; top: -3px;">修改密码</span></li>
                    <%--<a href="${pageContext.request.contextPath}/user/User_logout.do"><li>退出登录</li></a>--%>
                    <a href="javascript:logout()"><li>退出登录</li></a>
                </div>
               <div class="public_setting_modifypassword">
               	<span class="tab_content_listtext" style="width:40px;">旧密码:</span>
                    <span style="padding-bottom: 2px; padding-top: 1px; padding-left: 0; padding-right: 0">
                        <input type="password" maxlength="20" name="old_pwd" class="tab_content_listinput" style="width:110px">
                    </span>
                    <span class="tab_content_listtext" style="width:50px;">新密码:</span>
                    <span style="padding-bottom: 2px; padding-top: 1px; padding-left: 0; padding-right: 0">
                        <input type="password" maxlength="20" name="pwd" class="tab_content_listinput" style="width:110px">
                    </span>
                    <span class="tab_content_listtext" style="width:60px;">确认密码:</span>
                    <input type="password" maxlength="20" name="confirm_pwd" class="tab_content_listinput" style="width:110px">
                    <input class="public_setting_sure" type="submit" value="确定" >
                </div>
            </div>
            <div class="public_nav">
                <ul>
                    <%
                        //add by xinye
                        //添加区域版权限判断
                        Map<String,String> license = LicenseUtils.findLicense();
                        Integer licenseNum = 0;
                        if(null!=license&&!license.isEmpty()){
                            licenseNum = Integer.parseInt(license.get("hhtrec_device_num"));
                        }
                        int hostNum = LicenseUtils.HHTREC_DEVICE_MAXNUM;
                        //add by xinye end
                        String role = "0";

                        String system = "dmanager";
                        //巡课权限
                        String viewClass = "0";
                        if (UserService.authCheck(uid.toString(), "viewclass:retrieve", system)) {
                            viewClass = "1";
                        }
                        //系统设置权限
                        String setting = "0";
                        if (UserService.authCheck(uid.toString(), "syssetting:retrieve", system)) {
                            setting = "1";
                        }
                        //录像计划权限
                        String timeplan = "0";
                        if (licenseNum<=hostNum&&UserService.authCheck(uid.toString(), "hhrecplan:retrieve", system)) {
                            timeplan = "1";
                        }
                        //直播计划权限
                        String liveplan = "0";
                        if (licenseNum<=hostNum && UserService.authCheck(uid.toString(), "hhrecplan:retrieve", system)) {
                            liveplan = "1";
                        }
                        //定时计划权限
                        String hhrecplan = "0";//定时计划
                        if (licenseNum<=hostNum && UserService.authCheck(uid.toString(),"hhrecplan:retrieve",system)) {
                            hhrecplan = "1";
                        }
                        //设备管理权限
                        String device = "0";//设备管理
                        if (UserService.authCheck(uid.toString(),"deviceManager:retrieve",system)) {
                            device = "1";
                        }
                        //用户管理权限
                        String user = "0";//用户管理
                        if (UserService.authCheck(uid.toString(),"userManager:retrieve",system)) {
                            user = "1";
                        }
                        //日志管理权限
                        String log = "0";//日志管理
                        if (licenseNum<=hostNum&&UserService.authCheck(uid.toString(),"hhrec_syslog:retrieve",system)) {
                            log = "1";
                        }
                        //数据统计权限
                        String report = "0";//日志管理
                        if (UserService.authCheck(uid.toString(),"onli:retrieve",system)) {
                            report = "1";
                        }
                        //资源管理权限
                        String resource = "0";//资源管理
                        if (licenseNum<=hostNum&&UserService.authCheck(uid.toString(),"resource:retrieve",system)) {
                            resource = "1";
                        }
                        //地点管理权限
                        String place = "0";//地点管理
                        if (licenseNum<=hostNum&&UserService.authCheck(uid.toString(),"placeManager:retrieve",system)) {
                            place = "1";
                        }
                        //网络侦测工具
                        String hnetdetecttool = "0";//网络侦测
                        if (licenseNum<=hostNum&&UserService.authCheck(uid.toString(),"hnetdetecttool:retrieve",system)) {
                            hnetdetecttool = "1";
                        }
                        Map<String, String[]> dic = new LinkedHashMap<String, String[]>();
                        dic.put("/pages/frontend/viewclass", new String[]{ServletActionContext.getRequest().getContextPath() + "/viewclass/Viewclass_getHostGroup.do", "&nbsp;巡&nbsp;&nbsp;课", viewClass});
                        dic.put("/pages/frontend/timeplan", new String[]{ServletActionContext.getRequest().getContextPath() + "/timeplan/Timeplan_plan.do", "录像计划", timeplan});
                        dic.put("/pages/frontend/liveplan", new String[]{ServletActionContext.getRequest().getContextPath() + "/liveplan/Liveplan_liveplan.do", "直播计划", liveplan});
                        String settingPath = licenseNum<=hostNum?"/settings/Settings_logoList.do":"/settings/Settings_findLicenseKey.do";
                        dic.put("/pages/frontend/setting", new String[]{ServletActionContext.getRequest().getContextPath() + settingPath, "系统设置", setting});
                        dic.put("/pages/frontend/device", new String[]{ServletActionContext.getRequest().getContextPath() + "/hostgroup/Hostgroup_management.do", "设备管理", device});
                        dic.put("/pages/frontend/user", new String[]{ServletActionContext.getRequest().getContextPath() + "/user/User_userList.do", "用户管理", user});
//                        dic.put("/pages/frontend/role", new String[]{ServletActionContext.getRequest().getContextPath() + "/role/Role_roleList.do", "角色管理", role});
                        dic.put("/pages/frontend/place", new String[]{ServletActionContext.getRequest().getContextPath() + "/place/Place_placeManagement.do", "地点管理", place});
                        dic.put("/pages/frontend/devicelog", new String[]{ServletActionContext.getRequest().getContextPath() + "/syslog/SysLog_deviceLogList.do", "日志管理", log});
                        dic.put("/pages/frontend/resource", new String[]{ServletActionContext.getRequest().getContextPath() + "/resource/Resource_resourceListNew.do", "资源管理", resource});
//                        dic.put("/pages/frontend/report", new String[]{ServletActionContext.getRequest().getContextPath() + "/report/Report_deviceLogList.do", "数据统计", report});
//                        dic.put("/pages/frontend/policy", new String[]{ServletActionContext.getRequest().getContextPath() + "/policy/Policy_policyList.do", "定时计划", hhrecplan});
//                        dic.put("/pages/frontend/hnetdetecttool", new String[]{ServletActionContext.getRequest().getContextPath() + "/hnetdetecttool/HNetDetectTool_resourceListNew.do", "网络侦测", hnetdetecttool});
                        String currentSelect = "class=\"public_headbg\"";
                        String path = request.getServletPath().substring(0, request.getServletPath().lastIndexOf("/"));
                        Iterator<String> iterator = dic.keySet().iterator();
                        while (iterator.hasNext()) {
                            String key = iterator.next();
                            if (dic.get(key)[2].equals("0")) {
                                continue;
                            }
                            if (key.equals(path)) {
                                out.println("<li><a name='header_href' href=\"" + dic.get(key)[0] + "\"" + currentSelect + "><span></span>" + dic.get(key)[1] + "</a><div class=\"public_navcircle\"></div></li>");
                            } else {
                                out.println("<li><a name='header_href' href=\"" + dic.get(key)[0] + "\"><span></span>" + dic.get(key)[1] + "</a><div class=\"public_navcircle\"></div></li>");
                            }
                        }

                    %>
                </ul>
            </div>
        </div>

<script>
    $(function(){

        $.ajaxSetup({

            contentType:"application/x-www-form-urlencoded;charset=utf-8",

            complete:function(XMLHttpRequest,textStatus){

                //通过XMLHttpRequest取得响应头，sessionstatus

                var sessionstatus=XMLHttpRequest.getResponseHeader("frontend-sessionstatus");

                if(sessionstatus=="frontend-timeout"){

                    //这里怎么处理在你，这里跳转的登录页面
                    var rootpath = document.getElementById("root_path").value;
                    window.location.replace(rootpath+"/login.jsp");
                }
            }
        });
        $(".public_setting_menu li").eq(0).mouseover(function(){
        	$(".public_setting_menu li").eq(0).css({"background":"url(${pageContext.request.contextPath}/image/frontend/n_icon_141009.png) #eee 5px -388px no-repeat"});
        	//$(".public_setting_menu li").eq(1).css({"background":"url(${pageContext.request.contextPath}/image/frontend/n_icon_141009.png) #fff -100px -370px no-repeat"});
        }).mouseout(function(){
        	//$(".public_setting_menu li").eq(1).css({"background":"url(${pageContext.request.contextPath}/image/frontend/n_icon_141009.png) #eee -100px -370px no-repeat"});
        	$(".public_setting_menu li").eq(0).css({"background":"url(${pageContext.request.contextPath}/image/frontend/n_icon_141009.png) #fff 5px -388px no-repeat"});
        }).trigger("mouseout");
        $(".public_setting_menu li").eq(1).mouseover(function(){
        	//$(".public_setting_menu li").eq(0).css({"background":"url(${pageContext.request.contextPath}/image/frontend/n_icon_141009.png) #eee 5px -370px no-repeat"});
        	$(".public_setting_menu li").eq(1).css({"background":"url(${pageContext.request.contextPath}/image/frontend/n_icon_141009.png) #eee -101px -385px no-repeat"});
        }).mouseout(function(){
       		$(".public_setting_menu li").eq(1).css({"background":"url(${pageContext.request.contextPath}/image/frontend/n_icon_141009.png) #fff -101px -385px no-repeat"});
        	//$(".public_setting_menu li").eq(0).css({"background":"url(${pageContext.request.contextPath}/image/frontend/n_icon_141009.png) #fff 5px -370px no-repeat"});
        })
        $(".public_setting").click(function(){
        	var flag=$(".public_setting_menu").css("display");
        	var flagone=$(".public_setting_modifypassword").css("display");
        	if(flag=="none"&&flagone=="none"){
        		$(".public_setting_menu").show()
        	}else{
        		$(".public_setting_menu").hide()
        	}
        })
        $(".public_setting_menu li").eq(0).click(function(){
            $("input[name='pwd']").val("");
            $("input[name='confirm_pwd']").val("");
            $(".public_setting_modifypassword").show();
    	})
        //点击空白区域，隐藏select模拟
	$(document).bind("click",function(e){
		var target = $(e.target);//获取点击时间
		if(target.closest(".public_setting").length == 0)
		{
			$(".public_setting_menu").hide();
			$(".public_setting_modifypassword").hide();
		}
		
		if(target.closest(".xk_stage_change").length == 0){
			if($(".xk_stage_change").attr("changing")=="yes"){
				//console.log("aaaaaa")
				$(".xk_stage").hide()
				$(".xk_stage_change").attr("changing","no")
			}
		}
		if(target.closest(".xk_fournine").length == 0){
			if($(".xk_fournine div").eq(0).attr("class")=="xk_four_selected"){
				$(".xk_fournine div").eq(0).css({"border":"1px solid #bbb","border-radius":" 3px 0 0 3px"});
				$(".xk_fournine div").eq(1).hide()
			}else if($(".xk_fournine div").eq(1).attr("class")=="xk_nine_selected"){
				$(".xk_fournine div").eq(1).css({"border":"1px solid #bbb","border-radius":" 3px 0 0 3px"});
				$(".xk_fournine div").eq(0).hide()
			}
		}
	})
        $(".public_setting_sure").click(function () {
            $("input[name='old_pwd']").css({border:"solid 1px silver"});
            $("input[name='pwd']").css({border:"solid 1px silver"});
            $("input[name='confirm_pwd']").css({border:"solid 1px silver"});
            var rootpath = $("#root_path").val();
            var old_pwd = $("input[name='old_pwd']").val();
            if(old_pwd == "")
            {
                $("input[name='old_pwd']").css({border:"solid 1px red"});
                return false;
            }
            var pwd = $("input[name='pwd']").val();
            if(pwd=="")
            {
                $("input[name='pwd']").css({border:"solid 1px red"});
                return false;
            }
            var confirm_pwd = $("input[name='confirm_pwd']").val();
            if(confirm_pwd == "")
            {
                $("input[name='confirm_pwd']").css({border:"solid 1px red"});
                return false;
            }
            if(pwd != confirm_pwd)
            {
                $("input[name='pwd']").css({border:"solid 1px red"});
                $("input[name='confirm_pwd']").css({border:"solid 1px red"});
                return false;
            }
            if(pwd == old_pwd)
            {
                $("input[name='pwd']").css({border:"solid 1px red"});
                $("input[name='old_pwd']").css({border:"solid 1px red"});
                return false;
            }
            var uid = <%=uid%>;
            if(uid)
            {
                $.post(rootpath+"/user/User_resetPwd.do", {userId: uid,userpwd:pwd,oldpwd:old_pwd},function(data){
                    if(data.success==true)
                    {
                        $(".public_setting_menu").hide();
                        $(".public_setting_modifypassword").hide()
                    }
                    else
                    {
                        $("input[name='old_pwd']").css({border:"solid 1px red"});
                        $("input[name='pwd']").css({border:"solid 1px red"});
                        $("input[name='confirm_pwd']").css({border:"solid 1px red"});
                    }
                },'json');
            }
            else
            {
                $("input[name='old_pwd']").css({border:"solid 1px red"});
                $("input[name='pwd']").css({border:"solid 1px red"});
                $("input[name='confirm_pwd']").css({border:"solid 1px red"});
            }
        });
        setInterval(function(){
            uploadnum()
        },120000);
    });
    function uploadnum()
    {
        $.get("${pageContext.request.contextPath}/video/Video_uploadNumber.do",function(data){
            if(data.success==true)
            {
                if(parseInt(data.msg)==0){
                    $(".public_head_qp").hide();
                }
                else
                {
                    $(".public_head_qp").show();
                }
            }
        },"json");
    }
</script>
