<%@ page import="java.util.List" %>
<%@ page import="com.honghe.recordhibernate.entity.User" %>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="com.honghe.recordhibernate.dao.Pager" %>
<%@ page import="com.honghe.recordweb.action.SessionManager" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.honghe.recordhibernate.entity.Role" %>
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
    <title>角色管理 | 集控平台</title>
    <link href="${pageContext.request.contextPath}/css/frontend/index.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/jquery.cookie.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/common/autoheight.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/checkbox.js"></script>

    <script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
    <script src="${pageContext.request.contextPath}/js/frontend/role/roleList.js"></script>
    <link href="${pageContext.request.contextPath}/css/frontend/hpager.css" rel="stylesheet" type="text/css"/>
    <style>
        .last{
            display: none;
        }
        #linkpage{
        	position: absolute;
			bottom: 45px;
			text-align:center;
		}
    </style>
</head>

<body>
	<script>
		$(function(){
            var pagelen=$(".page a").length;
            left=-(pagelen+3)*28/2+"px";
            $("#linkpage").css({"position":"absolute","bottom":"40px","left":"50%"});
		})
	</script>
<%--<%--%>
    <%--int pageCount = Integer.parseInt(request.getAttribute("pageCount").toString());--%>
    <%--int currentPage = Integer.parseInt(request.getAttribute("currentPage").toString());--%>
    <%--Pager pager = new Pager(pageCount,currentPage,"<span class='pages_prevpage'></span>","<span class='pages_nextpage'></span>","","",false);--%>
    <%--String pagers = pager.run();--%>
<%--%>--%>
<div class="public">
	
    <table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
        <jsp:include page="/pages/frontend/header.jsp"></jsp:include>
        <tr>
            <td css="bgf2f2f2" width="100%" height="100%" >
                <div class="user">
                    <div class="user_center">
                        <div class="user_head">
                            <a href="javascript:addRole()"><span class="user_head_option"><span class="user_head_addicon"></span ><span style="float: left;">新增角色</span></span></a>
                            <a href="javascript:alterRole()"><span class="user_head_option "><span class="user_head_editicon"></span><span style="float: left;">编辑角色</span></span></a>
                            <a href="javascript:delRole()"><span class="user_head_option "><span class="user_head_delicon"></span><span style="float: left;">删除角色</span></span></a>
                            <%
                                User currentuser = SessionManager.get(request.getSession(), SessionManager.Key.USER);
                            %>
                            <input type="hidden" id="sessionuser" name="<%=currentuser.getUserId()%>"/>
                        </div>
                        <%
                            List<Role> rolelists = (List<Role>)request.getAttribute("rolelist");
                            if(rolelists.size() == 0||rolelists == null)
                            {
                        %>
                        &nbsp;&nbsp;&nbsp;&nbsp;暂无角色信息
                        <%
                        }
                        else
                        {
                        %>
                        <div class="user_checkall">
                            <%--<div class="check_head" style="margin-left: 30px;">--%>
                                <%--<label><input type="checkbox" /><div class="bg" id="ischeckall"></div>全选</label>--%>
                            <%--</div>--%>

                                <div class="checkbox">
                                    <input type="checkbox" id="checkall"/>
                                </div>
                                <div class="all">
                                    <div class="head" style="margin-left: 30px;">
                                        <div class="bg" id="ischeckall" style="margin-top: 0;"></div>全选
                                    </div>
                                </div>
                        </div>
                        <div id="userdatalist">
                       <%
                            for(int i=0;i<rolelists.size();i++)
                            {
                        %>
                                <div class="user_list" id="<%=rolelists.get(i).getRoleId()%>">
                                    <a href="javascript:void(0)">
                                        <img class="user_list_pic" src="<%=ServletActionContext.getRequest().getContextPath()%>/image/frontend/video.png" width="88" height="88" alt="" title="" />
                                        <span class="user_list_right">
                                            <span class="user_list_text" title="<%=rolelists.get(i).getRoleName()%>"><%=rolelists.get(i).getRoleName()%></span>
                                            <%--
                                                int roleId = rolelists.get(i).getRoleId();
                                                if(roleId == 1 || roleId == 2 ||roleId == 3 ||roleId == 4 )
                                                {
                                            --%>
                                           <!-- <span class="user_list_edit" title="编辑角色"></span>-->
                                            <%--
                                                }
                                                else
                                                {
                                            --%>
                                            <!--<span class="user_list_edit" title="编辑角色"></span>
                                            <span class="user_list_del" title="删除角色"></span>-->
                                            <%--
                                                }
                                            --%>
                                        </span>
                                        <span class="xk_video_selected_bz" id="span<%=rolelists.get(i).getRoleId()%>" style="display:none;top:10px;"></span>
                                    </a>
                                </div>
                        <%
                            }
                        }
                            int pageCount = Integer.parseInt(request.getAttribute("pageCount").toString());
                            int currentPage = Integer.parseInt(request.getAttribute("currentPage").toString());
                            Pager pager = new Pager(pageCount,currentPage,"<span class='pages_prevpage'></span>","<span class='pages_nextpage'></span>","","",false);
                            String pagers = pager.run();
                        %>
                        </div>
                        <div id = "linkpage" >
                            <%=pagers%>
                        </div>
                    </div>

                </div>

            </td>
        </tr>

        <jsp:include page="../footer.jsp"></jsp:include>
        
    </table>
</div>
<script>
  /**
* 获取url地址中的参数
*/
function urloption(url){
		var totalpagesize = "<%=pageCount%>";
		if(url.indexOf("?")!=-1){
		var p=url.indexOf("?"); //返回所在位置
		var host="http://"+window.location.host+url.substr(0,p+1);
		var str = url.substr(p+1) //从这个位置开始截取
		strs = str.split("&"); //拆分
		var jumpval=$("#jump").val();
            var patrn = /^[0-9]*$/;
            if(!patrn.exec(jumpval)){
			jumpval=1;
		}else if(parseInt(jumpval)>=parseInt(totalpagesize)){
			jumpval=totalpagesize
		}
		var newurl=host;
		for(var i=0;i<strs.length;i++){
			if(i==0){
				newurl+=strs[i].split("=")[0]+"="+jumpval+"&";
			}else{
				newurl+=strs[i].split("=")[0]+"="+unescape(strs[i].split("=")[1])+"&";
				
			}
		}
		var urllen=newurl.length-1;
		var newurl = newurl.substr(0,urllen) //从这个位置开始截取
		location.href=newurl;
	}
}
  	$(function(){
	var totalpagesize = "<%=pageCount%>";
	//分页调整
	var page=totalpagesize;
    var html="<span style='float: left;margin-left: 2px;'>&nbsp;&nbsp;共"+page+"页 &nbsp;&nbsp;跳转到 "+"<input id=\"jump\" type=text style=\"width:20px;background:#d7dade;border:1px solid #5e6470\" title=\"点击回车即可跳转分页\" onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'')\" onafterpaste=\"this.value=this.value.replace(/[^0-9]/g,'')\"/> 页<span>";
    $(".yiiPager").append(html)
    left=-(18)*28/2+"px";
	$("#linkpage").css({"margin-left":left,"width":(17)*28+"px"});
    $(document).keydown(function(event){
	//判断当event.keyCode 为37时（即左方面键），执行函数to_left();
	//判断当event.keyCode 为39时（即右方面键），执行函数to_right();
	   if(event.ctrlKey || event.which == 13){
	   	//alert("aaa")
		var jumpval=$("#jump").val();
           var patrn = /^[0-9]*$/;
           if(!patrn.exec(jumpval)){
	        return false;
	        }else{
	        	var lilen=$("#linkpage ul li").length-1;
	        	var prevhref=$("#linkpage ul li").eq(0).find("a").attr("href");
	        	var nexthref=$("#linkpage ul li").eq(lilen).find("a").attr("href");
	        	var thisurl="";
	        	if(prevhref==""&&nexthref==""){
	        		return false;
	        	}else if(prevhref==""||prevhref==undefined){
	        		thisurl=nexthref;
	        		urloption(thisurl)
	        	}else if(nexthref==""||nexthref==undefined){
	        		thisurl=prevhref;
	        		urloption(thisurl)
	        	}else{
	        		thisurl=nexthref;
					urloption(thisurl)
	        	}
	        }
		}
	})
})

</script>
</body>
</html>