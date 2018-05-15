<%@ page import="java.util.List" %>
<%@ page import="com.honghe.recordhibernate.entity.User" %>
<%@ page import="com.honghe.recordhibernate.dao.Pager" %>
<%@ page import="java.util.*" %>
<%--
  Created by IntelliJ IDEA.
  User: wj
  Date: 2014-10-08
  Time: 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
    <meta HTTP-EQUIV="pragma" CONTENT="no-cache">
    <meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
    <meta HTTP-EQUIV="expires" CONTENT="0">
    <title>用户管理 | 集控平台</title>
    <link href="${pageContext.request.contextPath}/css/frontend/index.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/frontend/hpager.css" rel="stylesheet"  type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/jquery.cookie.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/common/screendetail.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/checkbox.js"></script>

    <script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
    <script src="${pageContext.request.contextPath}/js/frontend/user/userList.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/scrolldemo.js"></script>

    <style>
        .last{
            display: none;
        }
        .head{
        	width: 90px;
        	text-overflow: ellipsis;
        	overflow: hidden;
        	white-space:nowrap;
        }
        #linkpage{
        	position: absolute;
			bottom: 45px;
text-align:center
		}
    </style>
</head>

<body>
<input type="hidden" id="whichscroll"> 
	<script>
		$(function(){
            var pagelen=$(".page a").length;
            left=-(pagelen+3)*28/2+"px";
            $("#linkpage").css({"position":"absolute","bottom":"40px","left":"50%"});
		})

	</script>
<%
    Map<String,Object> listmap = (Map<String,Object>) request.getAttribute("userList");
    String pageCount = listmap.get("pageCount").toString();
    int currentPage = Integer.parseInt(request.getAttribute("currentPage").toString());
    List<Object[]> userlists = (List<Object[]>) listmap.get("userlist") ;
    Pager pager = new Pager(Integer.parseInt(pageCount),currentPage,"<span class='pages_prevpage'></span>","<span class='pages_nextpage'></span>","","",false);
    String pagers = pager.run();
%>
<div class="public">
    <jsp:include page="/pages/frontend/equipment_header.jsp"></jsp:include>
                <div class="user floatleft">
                        <div class="user_head floatleft">
                            <a href="javascript:addUser()"><span class="user_head_option"><span class="user_head_addicon"></span ><span style="float: left;">新增用户</span></span></a>
                            <a href="javascript:alterUser()"><span class="user_head_option "><span class="user_head_editicon"></span><span style="float: left;">编辑用户</span></span></a>
                            <a href="javascript:delUser()"><span class="user_head_option "><span class="user_head_delicon"></span><span style="float: left;">删除用户</span></span></a>
                            <a href="javascript:resetPwd()"><span class="user_head_option "><span class="user_head_reseticon"></span><span style="float: left;">密码重置</span></span></a>
                                <span class="user_search">
                                    <input type="text" id="user_search_input" class="user_search_input" placeholder="请输入用户名"/>
                                    <input type="button" class="user_search_btn" />
                                </span>
                            <%
                                User currentuser =(User)request.getAttribute("user");
                            %>
                            <input type="hidden" id="sessionuser" name="<%=currentuser.getUserId()%>"/>
                        </div>
                        <%
                            if(userlists == null||userlists.size() == 0)
                            {
                        %>
                        <div class="error">
                            <%--<div class="error_text"></div>--%>
                            <div class="error_pic">
                                <span class="error_pictext">暂无用户，请先添加哦</span>
                            </div>
                        <%--</div>--%>
                        <%
                        }
                        else
                        {
                        %>

                        <div id="userdatalist" style="height: 93%;float: left;width:100%">
                        	<div class="scrollfather" id="userlistid" style="border: none;overflow: hidden;width: 100%;">
															<div class="scrollson">
                            <div class="user_checkall floatleft"  >
                                <div class="checkbox" >
                                    <input type="checkbox" id="checkall"/>
                                </div>
                                <div class="all">
                                    <div class="head" style="margin-top:10px;margin-left: 20px;">
                                        <div class="bg" id="ischeckall" style="margin-top: 0;"></div>全选
                                    </div>
                                </div>
                            </div>
                            
                        <%
                            for(int i=0;i<userlists.size();i++)
                            {
                                int currentuserid = currentuser.getUserId();
                                int uid = Integer.parseInt(userlists.get(i)[0].toString());
                                if(currentuserid == uid){
                                    continue;
                                }
                        %>
                                <div class="user_list" id="<%=userlists.get(i)[0]%>">
                                    <a href="javascript:void(0)">
                                        <span class="user_list_pic"></span>
                                        <span class="user_list_right">
                                            <span class="user_list_text" title="<%=userlists.get(i)[1]%>"><%=userlists.get(i)[1]%></span>
                                            <span class="user_list_text" title="<%=userlists.get(i)[4]==null?"":userlists.get(i)[4]%>"><%=userlists.get(i)[4]==null?"":userlists.get(i)[4]%></span>
                                        </span>
                                        <span class="xk_video_selected_bz" id="span<%=userlists.get(i)[0]%>" style="display:none;top:10px;"></span>
                                    </a>
                                </div>
                        <%
                            }
                        %>
                         </div>
												<div class="scroll_ymove">
												<div class="scroll_y" unorbind="unbind"></div>
												</div>
												<div class="scroll_xmove">
												<div class="scroll_x" unorbind="unbind"></div>
												</div>
											</div>
                    
                        <%
                        }
                        %>
                        </div>

                </div>

<div class="foot">
        	<jsp:include page="../footer.jsp"></jsp:include>
        </div>

</div>
<script>
	$(function(){
		//调整卡片间距
		var vipt_videowid=$("#userdatalist").width()
		var smlistwid=$(".user_list").width()||$(".user_list_selected").width();
		if(smlistwid!=0){
			var marginleft=(vipt_videowid/5-smlistwid)/4
			$(".user_list").css("margin-left",marginleft*3+"px")
			$(".user_list_selected").css("margin-left",marginleft*3+"px")
		}
		
		var vipt_videowid=$("#userdatalist").width()
		$("#userlistid .scrollson").width(vipt_videowid)
		$("#userlistid").height($(".user").height()*0.93)
		
		$("#userlistid .scrollson").mouseover(function(){
				$("#whichscroll").val($.trim($(this).parent().attr("id")))
				if ((navigator.userAgent.match(/(iPhone|Android|iPad)/i))){
				var scrollfathter1=document.getElementById($.trim($(this).parent().attr("id")));
				scrollfathter1.addEventListener("touchstart", touchStart, false);
				scrollfathter1.addEventListener("touchmove", touchMove, false);
				scrollfathter1.addEventListener("touchend", touchEnd, false);
				}
			})
			scroll_y("userlistid","scrollson","scroll_y","scroll_ymove","scroll_x","scroll_xmove","","wheely","")	
			$("#userlistid .scrollson").css("margin-top","0")
			$("#userlistid .scroll_y").css("top","0")
	})
</script>
</body>
</html>
