<%@ page import="com.honghe.recordhibernate.entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="com.honghe.recordweb.action.SessionManager" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.honghe.recordhibernate.dao.Pager" %>
<%--
  Created by IntelliJ IDEA.
  User: wj
  Date: 2014-10-10
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--<link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/frontend/index.css" rel="stylesheet" type="text/css" />-->

<%

    response.setHeader("Cache-Control","no-store");

    response.setHeader("Pragrma","no-cache");

    response.setDateHeader("Expires",0);

%>
<%
    Map<String,Object> listmap = (Map<String,Object>) request.getAttribute("userList");
    String pageCount = listmap.get("pageCount").toString();
    int currentPage = Integer.parseInt(request.getAttribute("currentPage").toString());
    Pager pager = new Pager(Integer.parseInt(pageCount),currentPage,"<span class='pages_prevpage'></span>","<span class='pages_nextpage'></span>","","",false);
    String pagers = pager.run();
    List<Object[]> userlists = (List<Object[]>) listmap.get("userlist") ;
    if(userlists == null || userlists.size()==0 )
    {
%>
<div class="error">
    <%--<div class="error_text"></div>--%>
    <div class="error_pic">
        <span class="error_pictext">该用户尚不存在哦</span>
    </div>
</div>
<%
}
else
{
%>
<div class="user_checkall" style="margin-top: 10px;margin-bottom: 5px;">
    <div class="checkbox">
        <input type="checkbox" id="checkall"/>
    </div>
    <div class="all">
        <div class="head" style="margin-left: 20px;">
            <div class="bg" id="ischeckall" style="margin-top: 0;"></div>全选
        </div>
    </div>
</div>
<%
    for(int i=0;i<userlists.size();i++)
    {
%>

<div class="user_list" id="<%=userlists.get(i)[0]%>">
    <a href="javascript:void(0)">
        <span class="user_list_pic"></span>
        <span class="user_list_right">
            <span class="user_list_text"><%=userlists.get(i)[1]%></span>
            <span class="user_list_text">
                <%if(userlists.get(i)[4] != null)
                {
                    out.println(userlists.get(i)[4]);
                }%>
            </span>
        </span>
        <span class="xk_video_selected_bz" id="span<%=userlists.get(i)[0]%>" style="display:none;top:10px;"></span>
    </a>
</div>
<%
        }
%>
<!--<div id = "linkpage_son" style="margin-top: 10px">
    <%=pagers%>
</div>-->
<%
    }
%>

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
        $(document.body).find("input[type=checkbox]").prop("checked",false).css("display","none");
        $(".head").click(function(){
            var index=$(this).index();
            //alert(index)
            var flag=$(".checkbox").find("input[type=checkbox]").eq(index).prop("checked");
            if(flag==true){
                //alert(flag)
                $(".checkbox").find("input[type=checkbox]").eq(index).prop("checked",false);
                $(".head").eq(index).find(".bged").removeClass("bged").addClass("bg").css({"background-position":"0px -0px"});//.css修正鼠标经过checkbox的背景位置
                //alert(flag+"a")
            }else{
                //alert(flag)
                $(".checkbox").find("input[type=checkbox]").eq(index).prop("checked",true);
                $(".head").eq(index).find(".bg").removeClass("bg").addClass("bged").css({"background-position":"0px -35px"});//.css修正鼠标经过checkbox的背景位置;
            }
            checkedall();
        })
        //鼠标经过未选中状态
        $(".head").mouseover(function(){
            $(this).find(".bg").css("background-position","0 -70px");
        }).mouseout(function(){
            $(this).find(".bg").css("background-position","0 0px")
        })
        //鼠标经过选中状态
        $(".head").mouseover(function(){
            $(this).find(".bged").css("background-position","0 -105px");
        }).mouseout(function(){
            $(this).find(".bged").css("background-position","0 -35px")
        })
    })
    //全选
    function checkall(){
        if($("#head").prop("checked")==false){
            $("#head").prop("checked",true);
            $(".allcheck").find(".bg").removeClass("bg").addClass("bged");
            $(".checkbox").find("input[type=checkbox]").prop("checked",true);
            $(".all").find(".bg").removeClass("bg").addClass("bged");
        }else{
            $("#head").prop("checked",false);
            $(".allcheck").find(".bged").removeClass("bged").addClass("bg");
            $(".checkbox").find("input[type=checkbox]").prop("checked",false);
            $(".all").find(".bged").removeClass("bged").addClass("bg");
        }
    }
    //确认是都全部选中
    function checkedall(){
        var alllen=$(".checkbox").find("input[type=checkbox]").length;
        for(i=0,j=0;i<alllen;i++){
            var flog=$(".checkbox").find("input[type=checkbox]").eq(i).prop("checked");
            //alert(flog)
            if(flog==true){
                j++;
                if(j==alllen){
                    $("#head").prop("checked",true);
                    $(".allcheck").find(".bg").removeClass("bg").addClass("bged");
                }else{
                    $("#head").prop("checked",false);
                    $(".allcheck").find(".bged").removeClass("bged").addClass("bg");
                }
            }
        }
    }
</script>