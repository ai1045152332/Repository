<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.honghe.recordhibernate.dao.Pager" %>
<%--
  Created by IntelliJ IDEA.
  User: lch
  Date: 2015/1/14
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="${pageContext.request.contextPath}/css/frontend/index.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/frontend/hpager.css" rel="stylesheet"  type="text/css"/>
<script src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/common/screendetail.js"></script>
<%
  List<Object[]> deviceLogList = (List<Object[]>) request.getAttribute("deviceLogList");
  int pageCount = Integer.parseInt(request.getAttribute("pageCount").toString());
  if(deviceLogList!=null && !deviceLogList.isEmpty()){
%>
<%
    Integer currentpage = Integer.parseInt(request.getAttribute("currentPage").toString());
    Pager pager = new Pager(pageCount, currentpage,3, "<span class='pages_prevpage'></span>", "<span class='pages_nextpage'></span>", "", "", false);
    // Pager pager = new Pager(hostcount,currentpage);
    String pagers = pager.run();
%>
  <style>
.sall{height:36px;width: 146px;margin-top: 2px;}

#selectdivall0{
	background-position: 0 -460px;
	height: 28px;
	line-height: 28px;
	width: 115px;

}
.selectdiv{
	background: none;
	width: 139px;
	height: 28px;
	line-height: 28px;
	margin-top: 0;
	}
.selectdivul{
	height: 140px;
	max-height: 0;
	width: 113px;
	}
.selectdivul a{
	text-indent: 10px;
	text-align: left;
	line-height: 36px;
	width: 113px;
	}

#selectdivul0{
	border:1px solid #dbdbdb;
/*	margin-top: -2px;*/
	height: 108px;
	width: 113px;
	overflow: hidden;
}
.laydate-icon{
	float: left;
	text-indent: 5px;
	width: 100px;
}
.logsearch{
	  background: #28b779 none repeat scroll 0 0;
    border-radius: 8px;
    color: #fff;
    cursor: pointer;
    float: left;
    font-size: 14px;
    height: 30px;
    line-height: 30px;
    margin-top: 2px;
    text-align: center;
    width: 82px;
}   
.deviceloginput{
	border:1px solid #C6C6C6;
	float: left;
	height: 24px;
	line-height: 24px;
	margin-right: 30px;
	outline: none;
	text-indent: 5px;
	width: 120px;
}
</style>
    <style>
       
        #linkpage{
        	position: absolute;
			bottom: 4.5%;
			left: 55%;
			text-align:center;
			width: 100%;
		}
		.tr {
    background: none repeat scroll 0% 0% #FFF;
    display: table;
    margin-bottom: 3px;
}
    </style>


        <%
          for(int i=0;i<deviceLogList.size();i++){
            //log_id, dl.log_user,dl.device_name,dl.device_ip,dl.log_time,dl.log_desc,dl.log_type
        %>
          <div class="table_recycle">
          	<div class="wid_15 floatleft hei_100"><%=deviceLogList.get(i)[1]%><!--操作用户名称--></div>
            <div class="wid_15 floatleft hei_100"><%=deviceLogList.get(i)[3]%><!--班级名称--></div>
            <div class="wid_20 floatleft hei_100"><%=deviceLogList.get(i)[4]%><!--IP地址--></div>
										<div class="wid_10 floatleft hei_100"><%=deviceLogList.get(i)[6]%><!--日志类型--></div>
		            		<div class="wid_24 floatleft hei_100"><%=deviceLogList.get(i)[2]%><!--日志内容--></div>
		            		<div class="wid_16 floatleft hei_100"><%
                               Long time = Long.parseLong(deviceLogList.get(i)[5].toString());
                               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                               String date = sdf.format(new Date(time*1000));
                               out.print(date);
                              %><!--操作时间-->
		            		</div>
          </div>
          
        <%
          }
        %>
                     
<div id="linkpage">
    <%=pagers%>
</div>
<%}
else{
%>
 <style>
  .sall{height:36px;width: 146px;margin-top: 2px;}

#selectdivall0{
	background-position: 0 -460px;
	height: 28px;
	line-height: 28px;
	width: 115px;

}
.selectdiv{
	background: none;
	width: 139px;
	height: 28px;
	line-height: 28px;
	margin-top: 0;
	}
.selectdivul{
	height: 140px;
	max-height: 0;
	width: 113px;
	}
.selectdivul a{
	text-indent: 10px;
	text-align: left;
	line-height: 36px;
	width: 113px;
	}
#selectdivul0{
	border:1px solid #dbdbdb;
/*	margin-top: -2px;*/
	height: 108px;
	width: 113px;
	overflow: hidden;
}
.laydate-icon{
	float: left;
	text-indent: 5px;
	width: 100px;
}

.logsearch{
	border-radius: 8px;
	background: #28b779;
	color: #fff;
	cursor: pointer;
	float: left;
	font-size: 14px;
	height: 27px;
	line-height: 27px;
	text-align: center;
	width: 82px;
}   
.deviceloginput{
	border:1px solid #C6C6C6;
	float: left;
	height: 24px;
	line-height: 24px;
	margin-right: 30px;
	outline: none;
	text-indent: 5px;
	width: 120px;
}

</style>
<div class="error">
  <%--<div class="error_text"></div>--%>
  <div class="error_pic">
    <span class="error_pictext">暂无日志信息</span>
  </div>
</div>
<%
  }%>
  <script>
	var totalpagesize = "<%=pageCount%>";
	//分页调整
	var page=totalpagesize;
    var html="<span style='float: left;margin-left: 2px;'>&nbsp;&nbsp;共<span id='pageCount'>"+page+"</span>页 &nbsp;&nbsp;跳转到 "+"<input id=\"jump\" type=text style=\"width:20px;background:#d7dade;border:1px solid #5e6470\" title=\"点击回车即可跳转分页\" onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'')\" onkeydown=\"jump(event)\" onafterpaste=\"this.value=this.value.replace(/[^0-9]/g,'')\"/> 页<span>";
    $(".yiiPager").append(html)
    left=-(18)*28/2+"px";
	$("#linkpage").css({"margin-left":left,"width":(17)*28+"px"});
	$(function(){
		$(".wid_6,.wid_16,wid_10,.wid_36").css({"overflow":"hidden","text-overflow":"ellipsis","white-space":"nowrap"})
	})
</script>
