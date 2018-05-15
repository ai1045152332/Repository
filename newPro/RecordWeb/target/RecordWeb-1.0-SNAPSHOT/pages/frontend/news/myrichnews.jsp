<%@ page import="java.util.List" %>
<%@ page import="com.honghe.recordhibernate.dao.Page" %>
<%@ page import="com.honghe.recordweb.service.frontend.news.NewsService" %>
<%@ page import="com.honghe.recordhibernate.dao.Pager" %>
<%@ page import="com.honghe.recordweb.action.frontend.news.entity.Program" %>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <base href="${pageContext.request.contextPath}">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
  <title>我编辑的消息 | 集控平台</title>
  <link href="${pageContext.request.contextPath}/css/frontend/hpager.css" rel="stylesheet" type="text/css"/>
  <link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css"/>
  <link href="${pageContext.request.contextPath}/css/frontend/index.css" rel="stylesheet" type="text/css"/>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/screendetail.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/scrolldemo.js"></script>
</head>
<body>
	<input type="hidden" id="whichscroll" value="mm_right_video">
<div class="public">
    <jsp:include page="/pages/frontend/equipment_header.jsp"></jsp:include>
    <div class="mm floatleft">
      <div class="mm_head">
        <a href="${pageContext.request.contextPath}/news/News_newsManage.do"  class="mm_head_nummachine">新建纯文本消息</a>
          <a href="${pageContext.request.contextPath}/news/News_add.do"  class="mm_head_nummachine">新建富文本消息</a>
          <a href="${pageContext.request.contextPath}/news/News_richNewsList.do"  class="mm_head_nummachine">全部</a>
      </div>
    <div class="scrollfather" id="mm_right_video" style="width: 100%;overflow: hidden;float: left;">
       <div class="scrollson" style="min-height: 680px;position:relative">

    <%
      Page myNewsList = (Page)  ActionContext.getContext().get("newsList");
      int pageCount = 0;
      pageCount = myNewsList.getTotalPageSize();
      Integer currentpage = Integer.parseInt(request.getAttribute("currentPage").toString());
      Pager pager = new Pager(pageCount, currentpage,3, "<span class='pages_prevpage'></span>", "<span class='pages_nextpage'></span>", "", "", false);
      String pagers = pager.run();//分页样式
      List<Object[]> newsList = null;
      if(myNewsList != null){
        newsList = myNewsList.getResult();
        if(newsList != null && newsList.size()>0){
          for(int i =0;i<newsList.size();i++){
            try{
              String nid = newsList.get(i)[0]!=null?newsList.get(i)[0].toString():"";
              String uid = newsList.get(i)[2]!=null?newsList.get(i)[2].toString():"";
              NewsService newsService = new NewsService();
              String imgpath = ServletActionContext.getServletContext().getRealPath("msgResource")+"/"+uid+"/"+nid+"/project.xml";
              Program progra = newsService.parseProgramXml(imgpath);
              if(progra == null){
                continue;
              }
    %>
    <div  class="sm_list"  id="news_<%=nid%>">
      <a target="_blank" href="${pageContext.request.contextPath}/news/Page_preprogram.do?prid=<%=nid%>&pid=1">
      <%--<a href="${pageContext.request.contextPath}/pages/frontend/news/editor/index.jsp?prid=<%=nid%>&w=<%=progra.getW()%>&h=<%=progra.getH()%>">--%>
        <img src="${pageContext.request.contextPath}/msgResource/<%=uid%>/<%=nid%>/thumb.png" class="sm_listimg">
        <span class="sm_listdate"><%=newsList.get(i)[3]==null?"":newsList.get(i)[3]%></span>
        <%if(newsList.get(i)[1].toString().equals("0")){%><!--0是未发布-->
          <span class="sm_listtext">未发布</span>
        <%}else if(newsList.get(i)[1].toString().equals("2")){%>
          <span class="sm_listtext">修改未发布</span>
        <%}else{%>
          <span class="sm_listtext">已发布</span>
        <%}%>
        <%--<span class="sm_listtext"><span class="sm_listtext26" title="发布人："><%=newsList.get(i).get("username")!=null?newsList.get(i).get("username"):""%></span></span>--%>

      </a>
      <div class="mm_list_option" style="width: 240px;" resid="191">
        <a target="_blank" href="${pageContext.request.contextPath}/pages/frontend/news/editor/index.jsp?prid=<%=nid%>&w=<%=progra.getW()%>&h=<%=progra.getH()%>"><div class="mm_list_options mm_list_edit" newsId="<%=nid%>" title="编辑"></div></a>
        <div class="mm_list_options mm_list_del" newsId="<%=nid%>" title="删除" hostnumber="0"></div>
      </div>
    </div>
    <%
            }catch (Exception e){
              e.printStackTrace();
              continue;
            }
          }
        }
      }
    %>
           <style>
               #linkpage {
                   position: absolute;
                   bottom: 0px;
                   left: 55%;
                   text-align: center
               }
           </style>

           <div class="floatleft" id="linkpage"><%=pagers%></div>
	     </div>
            <div class="scroll_ymove">
                <div class="scroll_y" unorbind="unbind"></div>
            </div>
            <div class="scroll_xmove">
                <div class="scroll_x" unorbind="unbind"></div>
            </div>
        </div>
    </div>
  <div class="foot">
    <jsp:include page="/pages/frontend/footer.jsp"></jsp:include>
  </div>
</div>
<script>
$(function(){
    //分页处理
    var cardlen=$(".sm_list").length;
    if(cardlen<=5){
        $("#linkpage").css({"position":"absolute","bottom":"5px"})
    }
		var vipt_videowid=$(".mm").width()
		$("#mm_right_video .scrollson").width(vipt_videowid)
		$("#mm_right_video").height($(".mm").height()*0.93)
		$("#mm_right_video").width(vipt_videowid)
    //重置scrollson高度
    var scrollfatherhei=$("#mm_right_video").height();
    if(scrollfatherhei>680)
    {
        $("#mm_right_video .scrollson").height($("#mm_right_video").height()-5);
    }
		//调整卡片间距
				var smlistwid=$(".sm_list").width();
				var marginleft=(vipt_videowid/5-smlistwid)/3
				$(".sm_list").css("margin-left",marginleft*2+"px")

		
		$("#mm_right_video .scrollson").mouseover(function(){
				$("#whichscroll").val($.trim($(this).parent().attr("id")))
				if ((navigator.userAgent.match(/(iPhone|Android|iPad)/i))){
				var scrollfathter1=document.getElementById($.trim($(this).parent().attr("id")));
				scrollfathter1.addEventListener("touchstart", touchStart, false);
				scrollfathter1.addEventListener("touchmove", touchMove, false);
				scrollfathter1.addEventListener("touchend", touchEnd, false);
				}
			})
			scroll_y("mm_right_video","scrollson","scroll_y","scroll_ymove","scroll_x","scroll_xmove","","wheely","")	
			$("#mm_right_video .scrollson").css("margin-top","0")
			$("#mm_right_video .scroll_y").css("top","0")
			var totalpagesize = "<%=pageCount%>";
			//分页调整
			var page=totalpagesize;
        if(page<=1){
           $("#linkpage").hide()
        }else{
        	$("#linkpage").show()
        }
    var html="<span  style='float: left;margin-left: 2px;color: #333;'>&nbsp;&nbsp;共"+page+"页 &nbsp;&nbsp;跳转到 "+"<input id=\"jump\" type=text style=\"width:24px;background:#d7dade;border:1px solid #5e6470\" title=\"点击回车即可跳转分页\" onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'')\" onafterpaste=\"this.value=this.value.replace(/[^0-9]/g,'')\"/> 页<span>";
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
  $(".mm_list_del").click(function(){
    var newsId = $(this).attr("newsId");
    var pcount = "<%=pageCount%>";
    var cpage="<%=currentpage%>";
      var url = "${pageContext.request.contextPath}/news/News_myRichNews.do";
    if(newsId != null && newsId != "")
    {
      $.layer({
        shade: [0.5, '#000'],
        area: ['310px', '129px'],
        title: '删除信息',
        dialog: {
          msg: '您确定要删除此条信息？',
          btns: 2,
          type: 4,
          btn: ['确定', '取消'],
          yes: function () {
            $.post("${pageContext.request.contextPath}/news/News_delNews.do",{newsId:newsId,newsType:1},function(data){
              layer.msg(data.msg);
              if (data.success == true) {
                setTimeout(function(){
                  //window.location.reload();
                  var divsize = $("div[id^='news_']").size();
                    //alert(divsize)
                  if(pcount == 1 || divsize <= 1)
                  {
                    cpage --;
                  }
                  if(cpage > 0)
                  {
                    url += "?currentPage=" + cpage;
                  }
                  location.href = url;
                },1000);
              }

            });
          }, no: function () {
            // layer.msg('奇葩', 1, 13);
          }
        }
      });

    }
  });
function urloption(url){
    //alert(url);
    var totalpagesize = "<%=pageCount%>";
    if(url.indexOf("?")!=-1){
        var p=url.indexOf("?"); //返回所在位置
        var host="http://"+window.location.host+url.substr(0,p+1);
        var str = url.substr(p+1) //从这个位置开始截取
        strs = str.split("&"); //拆分
        var jumpval=$("#jump").val();
        var patrn = /^[0-9]*$/;
        if(!patrn.exec(jumpval)|| jumpval<=0){
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
        location.href=newurl
    }
}
  </script>
  
</body>
</html>
