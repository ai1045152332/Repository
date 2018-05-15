<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="com.honghe.recordhibernate.dao.Page" %>
<%@ page import="com.honghe.recordweb.service.frontend.news.NewsService" %>
<%@ page import="com.honghe.recordhibernate.dao.Pager" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="com.honghe.recordweb.service.frontend.user.UserService" %>
<%@ page import="com.honghe.recordweb.action.SessionManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="${pageContext.request.contextPath}">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
    <title>纯文本消息 | 集控平台</title>
	<link href="${pageContext.request.contextPath}/css/frontend/hpager.css" rel="stylesheet"  type="text/css"/>
    <link href="${pageContext.request.contextPath}/js/common/layer/skin/layer.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/js/common/layer/skin/layer.ext.css" rel="stylesheet"
          type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/common/colorpicker/css/colorpicker.css" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/frontend/fd-slider.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
  <!--layerdate-->
  <script src="${pageContext.request.contextPath}/js/common/laydate/laydate.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/jquery.cookie.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/common/screendetail.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/tree_jkn_checkbox.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/selectmore.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/perfect-scrollbar.with-mousewheel.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/prettify.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/colorpicker/js/colorpicker.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/colorpicker/js/eye.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/colorpicker/js/layout.js?ver=1.0.2"></script>

    <!--
        作者：474569696@qq.com
        时间：2014-10-07
        描述：js引用顺序不可以变,会导致select模拟失效
    -->
    <style>
    	 .hostoverflow {
            float: left;
            margin-left: 23px;
            text-overflow: ellipsis;
            overflow: hidden;
            width: 80%;

        }

        .tree_titleb {
            display: none;
        }
        .fd-slider-handle{
            border:1px solid #a3a3a3;
            background: #f0f0f0;
            border-radius: 5px;
            height: 9px;
            top: 5px;
            width: 9px;
        }
		.unbindclick{
			display:none;
			position: absolute;
			width: 100px;
			height: 25px;
			z-index: 100;
		}
        #linkpage{
            position: absolute;
            bottom: 45px;
            text-align:center;
        }
    </style>

</head>
<body>
<%
	String device_type = SessionManager.get(request.getSession(), SessionManager.Key.DeviceType);
%>
<div class="public">

	<jsp:include page="/pages/frontend/equipment_header.jsp"></jsp:include>

	<div class="public_left22 floatleft">
		<input type="hidden" value="" id="selected_host" ip="">

        <div class="equipment">教室设备</div>
<style>
.tree_titleaiconmain_jkn,.tree_titleaiconmained_jkn{
    position: absolute;
    left: 5px;
    top: 0px;
}
.headr{
	margin-left: 20px;
}
.jkn_treecheckbox,.jkn_treecheckboxed{
        	left: 27px;	
        }
#selectdivall0, #selectdivall1,#selectdivall2 {
    background: url("${pageContext.request.contextPath}/image/frontend/n_icon_141006.png") no-repeat scroll 0px -456px transparent;
    float: left;
    height: 36px;
    line-height: 36px;
    width: 131px;
}
.selectdivul{width: 115px;}
.selectdivul a{text-indent: 10px;width: 115px;}
.sall{margin-top: 10px;}
.laydate-icon{
	float: left;
	text-indent: 5px;
	margin-top: 2px;
	width: 100px;
}
</style>
        <div id="otp_vedeoabout_rvideo">
            <div class='contentr'>
            	
                <div class="tree">
                    <a href="javascript:void(0)" class="tree_titleb tree_titleb_open">所有设备</a>
                    <%
                        List<Map> groupTreeList = (List<Map>) request.getAttribute("groupTree"); //获取分组数据和设备
						Page pageNews = (Page)request.getAttribute("pageNews"); //获取信息列表
						String hostIdsFlag = (String)request.getAttribute("HOST_ID_FLAG");
						int pageCount = 0;
						List<Map> mapList = new LinkedList<Map>();
                        int pageNum = 0;
                        if(pageNews != null)
                        {
                            pageCount = pageNews.getTotalPageSize();
                            mapList = pageNews.getResult();
                            pageNum = mapList.size();
                        }
						String userId = request.getAttribute("userId").toString();
						UserService userService = new UserService();
						int isAdmin = userService.getAuthorityValueByUserid(Integer.parseInt(userId));//返回是否是管理员，0为是，1为否
                    %>
                    <div class="public_left" style="float: left;">
                        <%
							if ( groupTreeList != null && groupTreeList.size() > 0)
							{

								for (Map groupTreeMap : groupTreeList)
								{
									List _groupList =(List)groupTreeMap.get("host_list");
									if (_groupList.size()>0){
									String group_id = groupTreeMap.get("group_id").toString();
									String group_name = groupTreeMap.get("group_name").toString();
									//out.println("~~~~~~~~~~~~~~~group_id=" + group_id + "*******group_name=" + group_name);
									List<Map> hostList = (List<Map>) groupTreeMap.get("host_list");
						%>
						<div class="tree_title tree_title_close ">
							<span  class="tree_titlea" groupId = "<%=group_id%>" style="text-indent: 42px;"><%=group_name%></span>
							<span class="tree_titleaiconmain_jkn"></span>
							<div class="tree_content">
								<%
									if (!hostList.isEmpty()&&hostList.size()>0) {
										for (Map host : hostList)
										{
											String host_id = host.get("id").toString();
											String host_name = host.get("name").toString();
                                            String statusStyle = "tree_content_onlinebg";
                                            if (host.get("status").toString().equals("Offline")) {
                                                statusStyle = "tree_content_nousebg";
                                            }
								%>
								<div class="tree_contenta" hostId = "<%=host_id%>">
									<span   class="<%=statusStyle%>" style="background: none;"></span>
									<span class="hostoverflow"> <%=host_name%></span>
									<div class="jkn_treecheckbox" hostId ="<%=host_id%>"></div>
								</div>
								<%
										}
									}
								%>
							</div>
						</div>
						<%
								}
                                }
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>
	</div>
	<div class="public_right floatleft">
		<div class="jkn_message_rightcenter">
			<div style="width: 100%;float: left;">
				<div class="jkn_message_text" >消息类型：</div>
				<div class="jkn_message_radio3"  style="margin-top: 19px;">

					<div class="headr"  style="position: relative;z-index: 10;">
						<span class="bgred"></span>普通
					</div>
					<div class="headr"  style="position: relative;z-index: 10;">
						<span class="bgr"></span>富文本
					</div>

				</div>
			</div>
			<%--<div><a href="${pageContext.request.contextPath}/pages/frontend/news/editor/index.html">发送富文本消息</a></div>--%>
			<%--<div><a href="${pageContext.request.contextPath}/news/News_add.do">发送富文本消息</a></div>--%>
			<div style="width: 100%;float: left;position: relative;">
                <div style="position: absolute;left: 0;top:60px;font-size: 12px;color: green" id="jkn_message_number"></div>
			     <div class="jkn_message_text" style="margin-top: 25px;">发布内容：</div>
			     <textarea style="font-size: 12px;overflow: hidden"  onkeyup="countNumber()" class="jkn_message_area" maxlength="120"  cols="" rows="" id="newsCont"></textarea>
		    </div>
		    <div style="width: 100%;float: left;">
		     <div class="jkn_message_text" >发布方式：</div>
		     <div class="jkn_message_radio" id ="newsShowType" style="margin-top: 19px;">
				 <%
				 	for(NewsService.ReleaseMode rm : NewsService.ReleaseMode.values())
					{
				 %>
					<div class="headr" releaseMode = "<%= rm.name()%>" style="position: relative;z-index: 10;">
						<span class="bgr"></span><%= rm.toString()%>
					</div>
				 <%
					}
				 %>
		     </div>
		     </div>
		     <div style="width: 100%;float: left;">
		     	<div class="jkn_message_text" >字体：</div>
		     <div class="sall">
                <select class="select" id="select0">
                    <%
                        for(NewsService.Font f : NewsService.Font.values())
                        {
                    %>
                    <option value="<%=f.name()%>" ><%=f.toString()%></option>
                    <%
                        }
                    %>
                </select>
                <div class="selectdivall" id="selectdivall0">
                    <div class="selectdiv" id="selectdiv0"></div>
                    <div class="selectdivul" id="selectdivul0" style="overflow-y: hidden;"></div>
                </div>
            </div>
		     <div class="jkn_message_text" style="width: 45px;">字号：</div>
		     <div class="sall">
                <select class="select" id="select1">
                    <option value="20" >20</option>
                    <option value="30" >30</option>
                    <option value="40" >40</option>
                    <option value="50" >50</option>
                    <option value="60" >60</option>
                </select>
                <div class="selectdivall" id="selectdivall1">
                    <div class="selectdiv" id="selectdiv1"></div>
					<div class="unbindclick"></div>
                    <div class="selectdivul" id="selectdivul1"></div>
                </div>
            </div>
		     <div class="jkn_message_text" style="width: 45px;">颜色：</div>
		     <div id="colorSelector"><div style="background-color: #ff00c6"></div></div>
		     </div>
		     <div class="jkn_message_text" >开始日期：</div>
		     <div class="win360_content_date" style="margin-top: 12px;">
                <div id="start" class="laydate-icon"></div>
                <input type="hidden" value="" id="startTime">
              </div>
		     <div class="jkn_message_text" style="margin-left: 10px;">结束日期：</div>
		     <div class="win360_content_date" style="margin-top: 12px;">
                <div id="end" class="laydate-icon"></div>
                <input type="hidden" value="" id="endTime">
              </div>
              <div class="jkn_message_tactics" id="newsPolicy">策略</div>
            <div class="jkn_message_sendbtn" id="newsRelease">发布</div>
            <div class="jkn_message_tactics" style="float: right;margin-right: 10px" id="cancelPolicy">取消发布</div>

	    </div>
	    <div class="jkn_message_rightcenter hei_50 floatleft" style="margin-left: 5%;">
	    	<style>
	    		.jkn_message_listhead,.jkn_message_listwhite,.jkn_message_listgray,.jkn_message_listheadtxt,.jkn_message_listtxt{
	    			height: 30px;
	    			line-height: 30px;
	    		}
	    	</style>
	     	<div class="jkn_message_listhead floatleft" style="margin-top: 5px;">
	     		<div class="wid_5 hei_100 floatleft jkn_message_listheadtxt">序号</div>
	     		<div class="wid_10 hei_100 floatleft jkn_message_listheadtxt">发布人</div>
	     		<div class="wid_15 hei_100 floatleft jkn_message_listheadtxt">执行设备</div>
	     		<div class="wid_30 hei_100 floatleft jkn_message_listheadtxt">发布内容</div>
	     		<div class="wid_18 hei_100 floatleft jkn_message_listheadtxt">发布时间</div>
	     		<div class="wid_7 hei_100 floatleft jkn_message_listheadtxt">持续时间</div>
	     		<div class="wid_15 hei_100 floatleft jkn_message_listheadtxt">操作</div>
	     	</div>
			 <%
				 //信息列表
				 int listNum = 0;
				 Integer currentpage = Integer.parseInt(request.getAttribute("currentPage").toString());
				 Pager pager = new Pager(pageCount, currentpage,3, "<span class='pages_prevpage'></span>", "<span class='pages_nextpage'></span>", "", "", false);
				 String pagers = pager.run();//分页样式
				 if(mapList.size() > 0)
				 {
			 %>
			 <%
					for (Map<String,String> newsInfoMap : mapList)
					{
						listNum ++;
						String rowStyle = "jkn_message_listwhite floatleft";
						if(listNum % 2 == 0)
						{
							rowStyle = "jkn_message_listgray floatleft";
						}
			 %>
			 <div class="<%=rowStyle%>">
				 <div class="wid_5 hei_100 floatleft jkn_message_listtxt"><%=listNum + pageNews.getPageSize() * (currentpage - 1)%></div>
				 <div class="wid_10 hei_100 floatleft jkn_message_listtxt"><%=newsInfoMap.containsKey("username") ? newsInfoMap.get("username") :""%></div>
				 <div class="wid_15 hei_100 floatleft jkn_message_listtxt" title="<%=newsInfoMap.containsKey("hostNames") ? newsInfoMap.get("hostNames") : ""%>"><%=newsInfoMap.containsKey("hostNames") ? newsInfoMap.get("hostNames") : ""%></div>
				 <div class="wid_30 hei_100 floatleft jkn_message_listtxt" title="<%=newsInfoMap.containsKey("n_cont") ? newsInfoMap.get("n_cont") : ""%>"><%=newsInfoMap.containsKey("n_cont") ? newsInfoMap.get("n_cont") : ""%></div>
				 <div class="wid_18 hei_100 floatleft jkn_message_listtxt"><%=newsInfoMap.containsKey("n_timeline") ? newsInfoMap.get("n_timeline") : ""%></div>
				 <div class="wid_7 hei_100 floatleft jkn_message_listtxt"><%=newsInfoMap.containsKey("duration") ? newsInfoMap.get("duration") : ""%></div>
				 <div class="wid_15 hei_100 floatleft jkn_message_listtxt">
				<%
					if(newsInfoMap.containsKey("uid") && newsInfoMap.get("uid") != null && !newsInfoMap.get("uid").equals("") && !newsInfoMap.get("uid").equals(userId))
					{//对于非本人发布的信息，无法编辑和删除
						if(userId.equals("1") || isAdmin == 0){//如果是管理员或admin可以删除
					%>
				 	<div class="jkn_message_listdel" newsId="<%=newsInfoMap.containsKey("n_id") ? newsInfoMap.get("n_id") : ""%>"></div>
					 <%
						 }
					}
					else
					{
				%>
					 <div class="jkn_message_listedit" newsId="<%=newsInfoMap.containsKey("n_id") ? newsInfoMap.get("n_id") : ""%>"></div>
					 <div class="jkn_message_listdel" newsId="<%=newsInfoMap.containsKey("n_id") ? newsInfoMap.get("n_id") : ""%>"></div>
				 <%
					 }
				 %>
				 </div>
			 </div>
			 <%
					}
				}
			%>
		</div>
		<div class="floatleft" id ="linkpage"><%=pagers%>
	</div>
		<input type="text" style="display: none" id="urlhead"
			   urlhead="${pageContext.request.contextPath}"/>
	</div>

    <div class="foot">
    	<jsp:include page="/pages/frontend/footer.jsp"></jsp:include>
    </div>
</div>

<style>
	.jkn_message_90{
		width: 95%;
		margin: 0 auto;
	}
	.jkn_message_radio{
		margin-top: 10px;
		width: 500px;
	}
	.headr{margin-left: 5px;}
	#linkpage{
	position: absolute;
	bottom: 0px;
	left: 55%;
	text-align:center
}
</style>
<div class="jkn_message_tacticsbg">
	<div class="win520" style="position: relative;left: 50%;margin-left: -260px;top: 50%;margin-top: -170px;width:550px">
		<div class="win_head">
			<div class="win_head_text">策略选择</div>
			<div class="win_close"></div>
		</div>
		<div class="win520_content" style="min-height: 300px;">
			<div class="jkn_message_text" style="margin-left: 20px;">策略选择：</div>
			<input type="hidden" id = "newsPolicyOld" policy="" week="" month="" start="" finish=""  />
			<%--<div class="jkn_message_90">
		     <div class="jkn_message_radio1">
		     	<div class="headr" >
                    <span class="bgr"></span>单次
                </div>
                <div class="floatleft"  style="margin-top: -3px;margin-left: 10px;">
	                <input id="onece" class="laydate-icon">
	                <input type="hidden" value="" id="oneceTime">
              	</div>
		     </div>
			</div>--%>
			<div class="jkn_message_90">
		     <div class="jkn_message_radio1">
		     	<div class="headr" >
                    <span class="bgr" newsPolicy ="day"></span>每日固定时间
                </div>
		     </div>
			</div>
			<div class="jkn_message_90">
		     <div class="jkn_message_radio1">
		     	<div class="headr">
                    <span class="bgr" newsPolicy ="week"></span>每周固定时间
                </div>
                <div class="weekradio">
                	<div class="headr" >
                    	<span class="bgr" newsWeek = "1"></span>周一
                	</div>
                	<div class="headr" >
                    	<span class="bgr" newsWeek = "2"></span>周二
                	</div>
                	<div class="headr" >
                    	<span class="bgr" newsWeek = "3"></span>周三
                	</div>
                	<div class="headr" >
                    	<span class="bgr" newsWeek = "4" ></span>周四
                	</div>
                	<div class="headr" >
                    	<span class="bgr" newsWeek = "5"></span>周五
                	</div>
                	<div class="headr" >
                    	<span class="bgr" newsWeek = "6"></span>周六
                	</div>
                	<div class="headr" >
                    	<span class="bgr" newsWeek = "7"></span>周日
                	</div>
                </div>
		     </div>
			</div>
			<div class="jkn_message_90">
		     <div class="jkn_message_radio1">
		     	<div class="headr" >
                    <span class="bgr" newsPolicy ="month"></span>每月固定时间
                </div>
                <div class="sall" style="margin-top: -8px;margin-left: 10px;">
	                <select class="select" id="select2">
	                    <script>
	                    	for(i=1;i<=31;i++){
	                    		var html="<option value="+i+">"+i+"</option>";
	                    		$("#select2").append(html)
	                    	}
	                    </script>
	                </select>
	                <div class="selectdivall" id="selectdivall2">
	                    <div class="selectdiv" id="selectdiv2"></div>
	                    <div class="selectdivul" id="selectdivul2" style="z-index: 11;max-height: 180px"></div>
	                </div>
	            </div>
		     </div>
			</div>
			<div class="jkn_message_90 floatleft">
				<div class="jkn_message_text" style="margin-left: 20px;">时间选择：</div>
			</div>
			<div class="jkn_message_90">
				<div class="jkn_message_timechoose" id="newsStart" timeBelong = "start">
					<div class="jkn_message_hour">小时</div>
					<div class="jkn_message_center">:</div>
					<div class="jkn_message_minutes">分钟</div>
					<div class="jkn_message_hourshow" id="newsStartHour"></div>
					<div class="jkn_message_minutesshow" id="newsStartMinutes"></div>
				</div>
				<div class="jkn_message_text" style="width: 30px;text-align: center;">至</div>
				<div class="jkn_message_timechoose" id="newsFinish" timeBelong = "finish">
					<div class="jkn_message_hour">小时</div>
					<div class="jkn_message_center">:</div>
					<div class="jkn_message_minutes">分钟</div>
					<div class="jkn_message_hourshow" id="newsFinishHour"></div>
					<div class="jkn_message_minutesshow" id="newsFinishMinutes"></div>
				</div>
			</div>
			<div class="win_btn" style="width: 90%;">
            <div class="win_btn_sure" >确定</div>
            <div class="win_btn_done">取消</div>
        </div>
		</div>
	</div>
</div>
<input type="text" style="display: none" id="newsParams" value="" newsId=""
	   urlhead="${pageContext.request.contextPath}"/>
<script>
    function change_size() {

        width = $("#otp_vedeoabout_rvideo").width();
        height = parseInt($(".public_right").height())-parseInt($(".equipment").height());

        $("#otp_vedeoabout_rvideo").width(width).height(height);

        // update perfect scrollbar
        $('#otp_vedeoabout_rvideo').perfectScrollbar('update');
    }
    $(function () {
        $('#otp_vedeoabout_rvideo').perfectScrollbar();
        prettyPrint();
    });
var start = {
      elem: '#start',
      format: 'YYYY-MM-DD',
	min: laydate.now(), //设定最小日期为当前日期
	max: '2099-06-16 23:59:59', //最大日期
	istoday: false,
      festival: true, //是否显示节日
      choose: function(datas){
        end.min = datas; //开始日选好后，重置结束日的最小日期
        end.start = datas //将结束日的初始值设定为开始日
        $("#startTime").val(datas);
      }
    };
var end = {
  elem: '#end',
  format: 'YYYY-MM-DD',
	min: laydate.now(), //设定最小日期为当前日期
	max: '2099-06-16 23:59:59', //最大日期
  festival: true, //是否显示节日
	istoday: false,
  choose: function(datas){
    start.max = datas; //结束日选好后，重置开始日的最大日期
    $("#endTime").val(datas);
  }
};
/*var onece = {
      elem: '#onece ',
      format: 'YYYY-MM-DD',
      festival: true, //是否显示节日
      choose: function(datas){
        end.min = datas; //开始日选好后，重置结束日的最小日期
        end.start = datas //将结束日的初始值设定为开始日
        $("#oneceTime").val(datas);
      }
    };*/
    laydate(start);
    laydate(end);
	var newsReleaseType;//记录发布方式临时变量
	var newsPolicy;//记录策略类型临时变量
	var newsWeek;//周策略临时变量
	var newsMonth;//月策略临时变量
	var newsStartTimeHour;//策略开始时间的hour
	var newsStartTimeMinutes;//策略开始时间的minute
	var newsFinishTimeHour;//策略结束时间的hour
	var newsFinishTimeMinutes;//策略结束时间的minute
	var urlHead = $("#newsParams").attr("urlhead");
	var clickNum = 0;
	$
    /*
	* 获取url地址中的参数
	*/
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
	$(function(){
        //鼠标经过显示发布方式图片
        $(".jkn_message_radio div").eq(0).mouseover(function(){
            $(this).append("<div class=\"jkn_czzm\"></div>")
        }).mouseout(function(){
            $(this).find(".jkn_czzm").remove();
        })
        $(".jkn_message_radio div").eq(1).mouseover(function(){
            $(this).append("<div class=\"jkn_gdxs\"></div>")
        }).mouseout(function(){
            $(this).find(".jkn_gdxs").remove();
        })
        $(".jkn_message_radio div").eq(2).mouseover(function(){
            $(this).append("<div class=\"jkn_qpts\"></div>")
        }).mouseout(function(){
            $(this).find(".jkn_qpts").remove();
        })
		//发布方式
        $("#selectdivall1").append("<div style='color: red;position: relative;left:30px;top:-36px;display: none'>(默认)</div>");
		$(".jkn_message_radio div").click(function(){
			var ind=$(this).index();
			$("#selectdivul1 a").eq(0).click();

			if(ind == 1)
			{
				$("#selectdiv1").removeAttr("disabled","disabled");
				$("#selectdiv1").text("20")
				$("#selectdivall1").children("div:last").hide()
				$(".unbindclick").hide()
				$("#selectdivall1").css("background","url(${pageContext.request.contextPath}/image/frontend/n_icon_141006.png) no-repeat scroll 0px -456px transparent");
		}
			else
			{
				$("#selectdiv1").attr("disabled","disabled");
				$("#selectdiv1").text("20");
				$("#selectdivall1").children("div:last").show();
				$(".unbindclick").show()
				$("#selectdivall1").css("background","none");
			}
			$(this).children("span").addClass("bgred").removeClass("bgr")
			$(this).siblings().children("span").addClass("bgr").removeClass("bgred")
			newsReleaseType = $(this).attr("releaseMode"); //获取当前选中的发布方式
		})
		//时间选择
		//向小时div添加子元素
		for(i=0;i<24;i++){
            if(i<10){
                var html="<span>0"+i+"</span>";
            }else{
                var html="<span>"+i+"</span>";
            }

			if(i<10)
			{
				html="<span>0"+i+"</span>";
			}
			$(".jkn_message_hourshow").append(html)
		}
		//向分钟div添加子元素
		for(i=0;i<60;i++){

            if(i<10){
                var html="<span>0"+i+"</span>";
            }else{
                var html="<span>"+i+"</span>";
            }

			if(i<10)
			{
				html="<span>0"+i+"</span>";
			}
			$(".jkn_message_minutesshow").append(html)
		}
		//点击小时隐藏分钟子菜单，显示小时子菜单
		$(".jkn_message_hour").click(function(){
			$(this).parents(".jkn_message_timechoose").children(".jkn_message_minutesshow").hide()
			$(this).parents(".jkn_message_timechoose").children(".jkn_message_hourshow").show()
		})
		//点击分钟隐藏小时子菜单，显示分钟子菜单
		$(".jkn_message_minutes").click(function(){
			$(this).parents(".jkn_message_timechoose").children(".jkn_message_minutesshow").show()
			$(this).parents(".jkn_message_timechoose").children(".jkn_message_hourshow").hide()
		})
		//子菜单鼠经过效果
		$(".jkn_message_timechoose span").mouseover(function(){
			$(this).css("background","#ccc")
		}).mouseout(function(){
			$(this).css("background","#fff")
		})
		//点击小时子菜单元素赋值操作
		$(".jkn_message_hourshow span").click(function(){
			var tmpSTH = newsStartTimeHour;//保存选择前原值
			var tmpFTH = newsFinishTimeHour;//保存选择前原值
			var timeBelong =$(this).parents(".jkn_message_timechoose").attr("timeBelong");
			if(timeBelong == "start")
			{
				newsStartTimeHour = $(this).text();
			}
			else if(timeBelong == "finish")
			{
				newsFinishTimeHour = $(this).text();
			}
			if(newsFinishTimeHour != null && newsStartTimeHour != null)
			{
				try
				{
					if(parseInt(newsFinishTimeHour)<parseInt(newsStartTimeHour))
					{
						layer.msg("结束时间不能小于开始时间！");
						newsFinishTimeHour = tmpFTH; //选择时间失败，恢复原值
						newsStartTimeHour = tmpSTH;
						return;
					}
					else if(newsFinishTimeHour == newsStartTimeHour && (newsFinishTimeMinutes != null )&& (newsStartTimeMinutes != null)  &&(parseInt(newsFinishTimeMinutes)<parseInt(newsStartTimeMinutes)))
					{
						layer.msg("结束时间不能小于开始时间！");
						newsFinishTimeHour = tmpFTH;//选择时间失败，恢复原值
						newsStartTimeHour = tmpSTH;
						return;
					}
				}
				catch (err)
				{
					layer.msg("无效操作");
					newsFinishTimeHour = tmpFTH;//选择时间失败，恢复原值
					newsStartTimeHour = tmpSTH;
					return;
				}
			}
			$(this).parents(".jkn_message_timechoose").children(".jkn_message_hour").text($(this).text());

		})
		//点击分钟子菜单元素赋值操作
		$(".jkn_message_minutesshow span").click(function(){
			var tmpSTM= newsStartTimeMinutes;//保存原值
			var tmpFTM = newsFinishTimeMinutes;//保存原值
			var timeBelongM =$(this).parents(".jkn_message_timechoose").attr("timeBelong");
			if(timeBelongM == "start")
			{
				newsStartTimeMinutes = $(this).text();
			}
			else if(timeBelongM == "finish")
			{
				newsFinishTimeMinutes = $(this).text();
			}
			if(newsFinishTimeHour != null && newsFinishTimeMinutes != null && newsStartTimeHour != null && newsStartTimeMinutes != null)
			{
				try
				{
					if((parseInt(newsFinishTimeHour)==parseInt(newsStartTimeHour)) && (parseInt(newsFinishTimeMinutes) < parseInt(newsStartTimeMinutes)))
					{
						layer.msg("结束时间不能小于开始时间！");
						newsStartTimeMinutes = tmpSTM;//选择时间失败，恢复原值
						newsFinishTimeMinutes = tmpFTM;//选择时间失败，恢复原值
						return;
					}
				}
				catch (err)
				{
					layer.msg("无效操作");
					newsStartTimeMinutes = tmpSTM;//选择时间失败，恢复原值
					newsFinishTimeMinutes = tmpFTM;//选择时间失败，恢复原值
					return;
				}
			}
			$(this).parents(".jkn_message_timechoose").children(".jkn_message_minutes").text($(this).text());
		})
		
		//点击空白区域，隐藏操作项子菜单
	   $(document).bind("click",function(e){
			var target = $(e.target);
			//alert(target.closest(".xk_options").length)
			if(target.closest(".jkn_message_hour").length == 0){
				$(".jkn_message_hourshow").hide()
			}
			if(target.closest(".jkn_message_minutes").length == 0){
				$(".jkn_message_minutesshow").hide()
			}
		})

	  //策略选择单选按钮操作
	  $(".jkn_message_radio1").children(".headr").click(function(){
	  		$(this).children("span").addClass("bgred").removeClass("bgr");
		    newsPolicy = $(this).children("span").attr("newsPolicy");
		  //	alert(newsPolicy);
			switch (newsPolicy)
			{
				case "day" :
					newsMonth = "";
					newsWeek = "";
					$("#selectdivall2").hide()
					break;
				case "week" :
					newsMonth = "";
					$("#selectdivall2").hide()
					break;
				case "month":
					newsWeek = "";
					$("#selectdivall2").show()
			}
		//  alert("newsWeek="+newsWeek + "---newsMonth="+newsMonth);
	  		$(this).parents(".jkn_message_90").siblings().find(".jkn_message_radio1").children(".headr").children("span").addClass("bgr").removeClass("bgred")
	  		if($(this).parents(".jkn_message_90").find(".weekradio").length!=1){
	  			$(this).parents(".jkn_message_90").siblings().find(".weekradio").children(".headr").children("span").addClass("bgr").removeClass("bgred")
	  		}
	  })
	  //策略选择周选择策略
	  $(".weekradio").children(".headr").click(function(){
	  	$(".jkn_message_radio1").children(".headr").eq(1).click()
	  	$(this).children("span").addClass("bgred").removeClass("bgr");
		  newsWeek = $(this).children("span").attr("newsWeek");
		  newsMonth = "";
		//  alert(newsWeek);
	  	$(this).siblings().children("span").addClass("bgr").removeClass("bgred")	
	  })
	  //点击策略按钮，显示弹窗
	  $("#newsPolicy").click(function(){
	  	$(".jkn_message_tacticsbg").show()
	  })
		$("#cancelPolicy").click(function(){
				window.location.reload();
		});
	  //点击弹窗关闭按钮，关闭弹窗
		$(".jkn_message_tacticsbg").find(".win_close").click(function(){
			$(".jkn_message_tacticsbg").hide()
		})
	 
		var totalpagesize = "<%=pageCount%>";
		 if(totalpagesize<=1){
           $("#linkpage").hide()
        }else{
        	$("#linkpage").show()
        }
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
			  if(jumpval==""){return;}
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
		//页面状态初始化
		$("#newsShowType").find("div").eq(0).click();//发布方式默认常驻桌面
		var myDate = new Date();
		var myDateStr = myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate();
		$("#startTime").val(myDateStr);//开始日期初始化
		$("#start").text(myDateStr);
		$("#endTime").val(myDateStr);//结束日期初始化
		$("#end").text(myDateStr);
		$("span[newsPolicy ='day']").click();//策略初始化按天
		$("#newsPolicyOld").attr("policy","day");
		//开始时间初始化
		$("#newsStart").find(".jkn_message_hour").text("08");
		newsStartTimeHour = "08";
		newsStartTimeMinutes = "00";
		$("#newsStart").find(".jkn_message_minutes").text("00");
		$("#newsPolicyOld").attr("start","08:00");
		//结束时间初始化
		$("#newsFinish").find(".jkn_message_hour").text("17");
		$("#newsFinish").find(".jkn_message_minutes").text("59");
		newsFinishTimeHour = "17";
		newsFinishTimeMinutes = "59";
		$("#newsPolicyOld").attr("finish","17:59");
	})
//保存策略规则
$(".win_btn_sure").click(function(){
	if(newsPolicy == "month")
	{
		newsMonth = $("#select2").val();
	}
	if(newsStartTimeHour == null || newsFinishTimeHour == null)
	{
		layer.msg("时间设置不正确");
		return ;
	}
	else
	{
		if(newsStartTimeMinutes == null)
		{
			newsStartTimeMinutes = "00";
		}
		if(newsFinishTimeMinutes == null)
		{
			newsFinishTimeMinutes = "59";
		}
		$("#newsPolicyOld").attr("start",(newsStartTimeHour + ":" + newsStartTimeMinutes));
		$("#newsPolicyOld").attr("finish",(newsFinishTimeHour + ":" + newsFinishTimeMinutes));
	}
	var result = policyVerify($("#startTime").val(),$("#endTime").val(),newsPolicy,"");
	var showText;
	if(result > 0)
	{
		if(result == 1)
		{
			showText = '当前信息的设置的持续时间小于7天，不推荐按周设置策略'
		}
		else
		{
			showText ='当前信息的设置的有效期在同一月内，不推荐按月设置策略';
		}
		$.layer({
			shade: [0.5, '#000'],
			area: ['310px', '149px'],
			title: '提醒',
			dialog: {
				msg: showText,
				btns: 2,
				type: 4,
				btn: ['忽略', '修改'],
				yes: function () {
					$("#newsPolicyOld").attr("policy",newsPolicy);
					$("#newsPolicyOld").attr("week",newsWeek);
					$("#newsPolicyOld").attr("month",newsMonth);
					//		alert("确定" + $("#newsPolicyOld").attr("policy")+"*********" + newsPolicy);
					layer.msg("策略设置成功！");
					$(".jkn_message_tacticsbg").hide()
				}, no: function () {

				}
			}
		});
	}
	else
	{
		$("#newsPolicyOld").attr("policy",newsPolicy);
		$("#newsPolicyOld").attr("week",newsWeek);
		$("#newsPolicyOld").attr("month",newsMonth);
		//		alert("确定" + $("#newsPolicyOld").attr("policy")+"*********" + newsPolicy);
		layer.msg("策略设置成功！");
		$(".jkn_message_tacticsbg").hide()
	}
});
//取消策略设置
$(".win_btn_done").click(function(){
	$(".jkn_message_tacticsbg").hide()
});
	//点击发布按钮
	$("#newsRelease").click(function(){
		if(clickNum < 1)
		{
			var checkedHostsArr = $(".jkn_treecheckboxed");
			var hostIdsStr = "";
			if(checkedHostsArr.length < 1)
			{
				layer.msg("未选择设备！");
				return;
			}
			for(var i = 0;i<checkedHostsArr.length;i++)
			{
				hostIdsStr += checkedHostsArr.eq(i).attr("hostId") + "<%=hostIdsFlag%>";//获取选中的host,以间隔符连接
			}
			if($("#newsCont").val() == null || $("#newsCont").val().toString().trim() == "")
			{
				layer.msg("信息内容不能为空！");
				$("#newsCont").focus();
				return;
			}
			var newsCont =encodeURI($("#newsCont").val());
			var newsFont = $("#select0").val();
			var newsFontSize = $("#select1").val();
			var newsFontColor ="#"+$(".colorpicker_hex").find("input[type=text]").val();
			var newsBeginDate = $("#startTime").val();
			var newsEndDate = $("#endTime").val();
			if(newsBeginDate == null || newsBeginDate == "" || newsEndDate == null || newsEndDate == "")
			{
				layer.msg("信息有效期限设置失败！");
				return;
			}
			//alert($("#newsPolicyOld").attr("policy")+"@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			if($("#newsPolicyOld").attr("policy") == "")
			{
				layer.msg("未设置信息显示策略！");
				return;
			}
			if($("#newsPolicyOld").attr("start") == "" || $("#newsPolicyOld").attr("finish") == "")
			{
				layer.msg("策略时间无效！");
				return;
			}
			clickNum ++ ;
			$.post(urlHead + "/news/News_addNews.do",{newsCont:newsCont,newsShowType:newsReleaseType,newsFont:newsFont,newsFontSize:newsFontSize,newsFontColor:newsFontColor,
				newsBeginDate:newsBeginDate,newsEndDate:newsEndDate,newsStartTime:$("#newsPolicyOld").attr("start"),newsFinishTime:$("#newsPolicyOld").attr("finish"),newsPolicy:$("#newsPolicyOld").attr("policy"),
				newsWeek:$("#newsPolicyOld").attr("week"),newsMonth:$("#newsPolicyOld").attr("month"),hostIdsStr:hostIdsStr,newsId:$("#newsParams").attr("newsId")
			},function(data){
				layer.msg(data.msg);
				clickNum = 0;
				if (data.success == true) {
					//	window.location.reload();
					setTimeout(function(){
						window.location.reload();
					},1000);
				}

			},"json");
		}

	});
//删除信息
	$(".jkn_message_listdel").click(function(){
		var newsId = $(this).attr("newsId");
        var pcount = "<%=pageNum%>";
        var cpage="<%=currentpage%>";
        var url = urlHead + "/news/News_newsManage.do";
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
						$.post(urlHead + "/news/News_delNews.do",{newsId:newsId},function(data){
							layer.msg(data.msg);
							if (data.success == true) {
								setTimeout(function(){
									//window.location.reload();
                                    if(pcount == 1)
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
//编辑信息
	$(".jkn_message_listedit").click(function(){
		$(".jkn_treecheckboxed").addClass("jkn_treecheckbox").removeClass("jkn_treecheckboxed");
		var newsIdUpadate = $(this).attr("newsId");
		$("#newsParams").attr("newsId",newsIdUpadate);//记录当前选择要编辑的信息id
		if(newsIdUpadate != null && newsIdUpadate != "")
		{
			$.post(urlHead + "/news/News_updateNews.do",{newsId:newsIdUpadate},function(data){
				if(data != null)
				{
					if(data.cont != null)
					{
						$("#newsCont").val(data.cont);//初始化信息内容
					}
					if(data.showType != null && data.showType != "") //初始化发布方式
					{
						var showDiv =$("#newsShowType").find("div");
						for(i=0;i<showDiv.length;i++)
						{
							if(showDiv.eq(i).attr("releaseMode") == data.showType)
							{
								showDiv.eq(i).click();
							}
						}
					}
					if(data.font != null && data.font != "")//初始化字体
					{
						var fort = data.font
						if(fort == "BLACK"){
							fort = "黑体"
						}
						if(fort == "SONG"){
							fort = "宋体"
						}
						//$("#select0").find("option[value='"+data.font+"']").attr("selected",true);
						//var fontText = $("#select0").find("option[value='"+data.font+"']").text();
						for(i=0;i<$("#selectdivul0 a").length;i++){
							if($("#selectdivul0 a").eq(i).text()==fort){
								$("#selectdivul0 a").eq(i).click()
							}
						}
					}
					if(data.fontSize != null && data.fontSize != "")//初始化字体大小
					{
						//$("#select1").find("option[value='"+data.fontSize+"']").attr("selected",true);
						for(i=0;i<$("#selectdivul1 a").length;i++){
							if($("#selectdivul1 a").eq(i).text()==data.fontSize){
								$("#selectdivul1 a").eq(i).click()
							}
						}
					}
					if(data.fontColor != null && data.fontColor != "")//字体颜色
					{
						$(".colorpicker_hex").find("input[type=text]").val(data.fontColor);
						$(".colorpicker_hex").find("input[type=text]").change();
					}
					if(data.beginDate != null && data.beginDate != "")//开始日期
					{
						$("#startTime").val(data.beginDate);
						$("#start").text(data.beginDate);
					}
					if(data.endDate != null && data.endDate != "")//结束日期
					{
						$("#endTime").val(data.endDate);
						$("#end").text(data.endDate);
					}
					if(data.loop != null && data.loop != "")//策略方式
					{
						$("span[newsPolicy ='"+data.loop+"']").click();
						$("#newsPolicyOld").attr("policy",data.loop);
						switch (data.loop)
						{
							case "week":
								if(data.week != null && data.week != "")
								{
									$("span[newsWeek='"+data.week+"']").click();//如果为周策略，则初始化周几
								}
								break;
							case "month":
								if(data.month != null && data.month != "")
								{
									for(i=0;i<$("#selectdivall2 a").length;i++){
										if($("#selectdivall2 a").eq(i).text()==data.month){
											$("#selectdivall2 a").eq(i).click() //月策略初始化
										}
									}
								}
								break;
						}
					}
					if(data.hostIds != null && data.hostIds.trim() != "")//选中对应设备
					{
						//alert(data.hostIds);
						var arr = data.hostIds.split("<%=hostIdsFlag%>");
						var hostTrees = $(".jkn_treecheckbox");
						for (var m = 0; m < hostTrees.length; m++ )
						{
							for(i = 0;i < arr.length;i++ )
							{
								if(hostTrees.eq(m).attr("hostId") == arr[i])
								{
									hostTrees.eq(m).click();
								}


							}
						}
					}
					if(data.start != null && data.start != "")//策略开始时间
					{
						var arr = data.start.split(":");
						if(arr != null && arr.length >1)
						{
							//alert($("#newsStart").find(".jkn_message_hour div").attr("class"));
							$("#newsStart").find(".jkn_message_hour").text(arr[0]);
							newsStartTimeHour = arr[0];
							newsStartTimeMinutes = arr[1];
							$("#newsStart").find(".jkn_message_minutes").text(arr[1]);
							$("#newsPolicyOld").attr("start",data.start);
						}
					}
					if(data.finish != null && data.finish != "")//策略结束时间
					{
						var arr = data.finish.split(":");
						if(arr != null && arr.length >1)
						{
							$("#newsFinish").find(".jkn_message_hour").text(arr[0]);
							$("#newsFinish").find(".jkn_message_minutes").text(arr[1]);
							newsFinishTimeHour = arr[0];
							newsFinishTimeMinutes = arr[1];
							$("#newsPolicyOld").attr("finish",data.finish);
						}
					}
				}

			});
		}
	});
    var outLength = 0;
//发布内容字数提醒
function countNumber()
{
	var num = $(".jkn_message_area").val().length;
	if(num < 120)
	{
		$("#jkn_message_number").text("(剩" + (120 - num) + "字)");
        outLength = 0;
	}
    else if(num == 120)
    {
        $("#jkn_message_number").text("(剩0字)");
        if(outLength == 2)
        {
            layer.msg("输入内容达到限制！",1);
            outLength =0;
        }
        else
        {
            outLength ++;
        }

    }

	else
	{
		layer.msg("输入内容超出限制！");
		$("#jkn_message_number").text("(剩0字)");
	}

}
	//策略有效验证
	function policyVerify(beginDateStr,endDateStr,policyType,policyValue)
	{
		var result = 0; //0 为成功，1为周策略警告，2为月策略警告
		var showText = "";
		var beginDate= parseDate(beginDateStr);
		var endDate = parseDate(endDateStr);
		var date = endDate.getTime() - beginDate.getTime();
		var daysNum = Math.floor(date / (1000 * 60 * 60 * 24));
		var monthsNum = 12 * (endDate.getYear() - beginDate.getYear()) + endDate.getMonth() -  beginDate.getMonth();
		switch (policyType)
		{
			case "week" :
				if(daysNum < 7)
				{
				//	layer.alert('当前信息的设置的持续时间小于7天，不推荐按周设置策略');
					showText = '当前信息的设置的持续时间小于7天，不推荐按周设置策略'
					result = 1;
					//不到一周需要判断时间是否有效
				}
				break;
			case "month" :
				if(monthsNum < 1)
				{
					//layer.alert('当前信息的设置的有效期在同一月内，不推荐按月设置策略');
					showText ='当前信息的设置的有效期在同一月内，不推荐按月设置策略';
					result = 2;
				}
			    break;
			default :
				break;
		}
		return result;
	}
	//日期格式转换 2015-02-03 ===> 2015/02/03
	function parseDate(dateStr)
	{
		if(dateStr != null && dateStr != "")
		{
			//alert("转换前dateStr=" + dateStr);
			var regEx = new RegExp("\\-","gi");
			dateStr=dateStr.replace(regEx,"/");
			var date = new Date(dateStr);
			//alert("转换后dateStr=" + dateStr + "；date=" + date.toDateString());
			return date;
		}
		return null;
	}
	//消息类型切换
	$(".jkn_message_radio3 div").click(function(){
		var ind=$(this).index();
		if(ind==0){
			location.href=urlHead + "/news/News_newsManage.do"
		}else{
			location.href=urlHead + "/news/News_richNewsList.do"
		}
		$(this).children("span").addClass("bgred").removeClass("bgr")
		$(this).siblings().children("span").addClass("bgr").removeClass("bgred")
	})
</script>
</body>
</html>
