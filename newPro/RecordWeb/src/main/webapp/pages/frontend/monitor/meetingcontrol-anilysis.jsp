<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>login</title>
		<%@ taglib prefix="s" uri="/struts-tags" %>
		<script src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${pageContext.request.contextPath}/js/frontend/monitor/scrolldemo.js" type="text/javascript" charset="utf-8"></script>
		<script src="${pageContext.request.contextPath}/js/frontend/monitor/tree.js" type="text/javascript" charset="utf-8"></script>
		<script src="${pageContext.request.contextPath}/js/frontend/monitor/onlyselectmore.js" type="text/javascript" charset="utf-8"></script>
		<script src="${pageContext.request.contextPath}/js/frontend/monitor/radio.js" type="text/javascript" charset="utf-8"></script>
		<script src="${pageContext.request.contextPath}/js/common/laydate/laydate.js" type="text/javascript" charset="utf-8"></script>
		
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/frontend/monitor/main.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/frontend/monitor/index.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/common/laydate/skins/default/laydate.css"/>
<style type="text/css">
	.sall{
		margin-right: 7px;
	}
	.Meet_right_investigate_txt{
		text-align: center;
	}
</style>
	</head>
	<body>
		<div class="Meet_login_head1">
			<div class="public">
				<div class="Meet_login_head_logo1"></div>
				<div class="Meet_login_head_txt1">综合会议管理平台</div>
				<div class="Meet_login_nav">
					<li>会议室查询</li>
					<li>会议预约</li>
					<li>会议控制</li>
					<li>统计分析</li>
					<li>系统管理</li>
				</div>
				<div class="Meet_login_admin">
					<div class="Meet_login_admin_txt">
						用户名：admin
					</div>
					<div class="Meet_login_admin_pic"></div>
				</div>
			</div>
		</div>
		<div class="public_bg">
			<div class="public_left">
				<div class="Meet_order">统计分析</div>
			</div>
			<div class="public_right">
				
				<div class="Meet_right_center">
					<div class="Meet_right_Chead">
						<div class="Meet_right_Chead_txt">统计分析</div>
						<div class="Meet_right_Chead_txts">返回列表</div>
					</div>
					<!--<div class="Meet_right_Cheads_txt" style="margin-top: 20px;">会议预约</div>-->
					<div class="Meet_right_list" style="margin-top: 20px;">
						<div class="sall" >
							<div class="selectdivall" id="selectdivall0">
								<div class="selectdiv" id="selectdiv0"></div>
								<div class="selectdivicon"></div>
								<div class="scrollfather" id="scrollfather1">
									<div class="scrollson">
										<div class="selectdivul" id="selectdivul0">
											<a href="#">会议室名称</a>
											<a href="#">22</a>
											<a href="#">33</a>
											<a href="#">11</a>
											<a href="#">22</a>
											<a href="#">33</a>
										</div>	
									</div>
									<div class="scroll_ymove">
									<div class="scroll_y" unorbind="unbind"></div>
									</div>
									<div class="scroll_xmove">
									<div class="scroll_x" unorbind="unbind"></div>
									</div>
								</div>
								
							</div>
						</div>
						<div class="sall" >
							<div class="selectdivall" id="selectdivall1">
								<div class="selectdiv" id="selectdiv1"></div>
								<div class="selectdivicon"></div>
								<div class="scrollfather" id="scrollfather2">
									<div class="scrollson">
										<div class="selectdivul" id="selectdivul1">
											<a href="#">2015年</a>
											<a href="#">2014年</a>
											<a href="#">2013年</a>
											<a href="#">2012年</a>
											<a href="#">2011年</a>
											<a href="#">2010年</a>
										</div>	
									</div>
									<div class="scroll_ymove">
									<div class="scroll_y" unorbind="unbind"></div>
									</div>
									<div class="scroll_xmove">
									<div class="scroll_x" unorbind="unbind"></div>
									</div>
								</div>
								
							</div>
						</div>
						<div class="sall" >
							<div class="selectdivall" id="selectdivall2">
								<div class="selectdiv" id="selectdiv2"></div>
								<div class="selectdivicon"></div>
								<div class="scrollfather" id="scrollfather3">
									<div class="scrollson">
										<div class="selectdivul" id="selectdivul2">
											<a href="#">1月</a>
											<a href="#">2月</a>
											<a href="#">3月</a>
											<a href="#">4月</a>
											<a href="#">5月</a>
											<a href="#">6月</a>
											<a href="#">7月</a>
											<a href="#">8月</a>
											<a href="#">9月</a>
											<a href="#">10月</a>
											<a href="#">11月</a>
											<a href="#">12月</a>
										</div>	
									</div>
									<div class="scroll_ymove">
									<div class="scroll_y" unorbind="unbind"></div>
									</div>
									<div class="scroll_xmove">
									<div class="scroll_x" unorbind="unbind"></div>
									</div>
								</div>
								
							</div>
						</div>
						<div class="sall" >
							<div class="selectdivall" id="selectdivall3">
								<div class="selectdiv" id="selectdiv3"></div>
								<div class="selectdivicon"></div>
								<div class="scrollfather" id="scrollfather4">
									<div class="scrollson">
										<div class="selectdivul" id="selectdivul3">
											<a href="#">1日</a>
											<a href="#">2日</a>
											<a href="#">3日</a>
											<a href="#">4日</a>
											<a href="#">5日</a>
											<a href="#">6日</a>
											<a href="#">7日</a>
											<a href="#">8日</a>
											<a href="#">9日</a>
											<a href="#">10日</a>
											<a href="#">11日</a>
											<a href="#">12日</a>
											<a href="#">13日</a>
											<a href="#">14日</a>
											<a href="#">15日</a>
											<a href="#">16日</a>
											<a href="#">17日</a>
											<a href="#">18日</a>
											<a href="#">19日</a>
											<a href="#">20日</a>
											<a href="#">21日</a>
											<a href="#">22日</a>
											<a href="#">23日</a>
											<a href="#">24日</a>
											<a href="#">25日</a>
											<a href="#">26日</a>
											<a href="#">27日</a>
											<a href="#">28日</a>
											<a href="#">29日</a>
											<a href="#">30日</a>
											<a href="#">31日</a>
										</div>	
									</div>
									<div class="scroll_ymove">
									<div class="scroll_y" unorbind="unbind"></div>
									</div>
									<div class="scroll_xmove">
									<div class="scroll_x" unorbind="unbind"></div>
									</div>
								</div>
								
							</div>
						</div>
						<div class="Meet_right_searchbtn">查询</div>
					</div>
					<div class="Meet_right_canvas">
						<canvas id="canvaschart" width="730px" height="428px">您的浏览器版本过低,请升级浏览器</canvas>
					</div>

					<s:hidden name="hostNames" id="hostNames"></s:hidden>
					<s:hidden name="hostMacs" id="hostMacs"></s:hidden>


					<div class="Meet_right_investigate_gray" style="margin-top: 30px;">
							<div class="Meet_right_investigate_txt"></div>
							<div class="Meet_right_investigate_txt">总票数</div>
							<div class="Meet_right_investigate_txt">非常好</div>
							<div class="Meet_right_investigate_txt">较好</div>
							<div class="Meet_right_investigate_txt">正常</div>
							<div class="Meet_right_investigate_txt">待提高</div>
							
						</div>
						<div class="Meet_right_investigate_white">
							<div class="Meet_right_investigate_txt">画面清晰度</div>
							<div class="Meet_right_investigate_txt">60</div>
							<div class="Meet_right_investigate_txt">20</div>
							<div class="Meet_right_investigate_txt">20</div>
							<div class="Meet_right_investigate_txt">10</div>
							<div class="Meet_right_investigate_txt">10</div>
						</div>
						<div class="Meet_right_investigate_gray">
							<div class="Meet_right_investigate_txt">接通及时性</div>
							<div class="Meet_right_investigate_txt">60</div>
							<div class="Meet_right_investigate_txt">20</div>
							<div class="Meet_right_investigate_txt">20</div>
							<div class="Meet_right_investigate_txt">10</div>
							<div class="Meet_right_investigate_txt">10</div>
						</div>
						<div class="Meet_right_investigate_white">
							<div class="Meet_right_investigate_txt">会议正常性</div>
							<div class="Meet_right_investigate_txt">60</div>
							<div class="Meet_right_investigate_txt">20</div>
							<div class="Meet_right_investigate_txt">20</div>
							<div class="Meet_right_investigate_txt">10</div>
							<div class="Meet_right_investigate_txt">10</div>
						</div>
						<div class="Meet_right_investigate_gray">
							<div class="Meet_right_investigate_txt">管理员态度</div>
							<div class="Meet_right_investigate_txt">60</div>
							<div class="Meet_right_investigate_txt">20</div>
							<div class="Meet_right_investigate_txt">20</div>
							<div class="Meet_right_investigate_txt">10</div>
							<div class="Meet_right_investigate_txt">10</div>
						</div>
						<div class="Meet_right_investigate_white">
							<div class="Meet_right_investigate_txt">总体满意度</div>
							<div class="Meet_right_investigate_txt">60</div>
							<div class="Meet_right_investigate_txt">20</div>
							<div class="Meet_right_investigate_txt">20</div>
							<div class="Meet_right_investigate_txt">10</div>
							<div class="Meet_right_investigate_txt">10</div>
						</div>
						<div class="Meet_right_exportbtn">导出到Excel</div>
						

				</div>
				<div class="foot"></div>
			</div>
		</div>
	<input type="hidden" id="whichscroll"> 
		<script>
			$(function(){
				$(".scrollson").mouseover(function(){
					$("#whichscroll").val($.trim($(this).parent().attr("id")))
					if ((navigator.userAgent.match(/(iPhone|Android|iPad)/i))){
					var scrollfathter1=document.getElementById($.trim($(this).parent().attr("id")));
					scrollfathter1.addEventListener("touchstart", touchStart, false);
					scrollfathter1.addEventListener("touchmove", touchMove, false);
					scrollfathter1.addEventListener("touchend", touchEnd, false);
					}
				})
				scroll_y("scrollfather1","scrollson","scroll_y","scroll_ymove","scroll_x","scroll_xmove","","wheely","")	
				scroll_y("scrollfather2","scrollson","scroll_y","scroll_ymove","scroll_x","scroll_xmove","","wheely","")
				scroll_y("scrollfather3","scrollson","scroll_y","scroll_ymove","scroll_x","scroll_xmove","","wheely","")
				scroll_y("scrollfather4","scrollson","scroll_y","scroll_ymove","scroll_x","scroll_xmove","","wheely","")
				$(".scrollson").css("margin-top","0")
				$(".scroll_y").css("top","0")
			})
			//柱状图
			var canvas=document.getElementById("canvaschart");
			var canvastxt=canvas.getContext("2d");
			canvastxt.font="14px 微软雅黑" ;
			canvastxt.fillStyle="#333"
			canvastxt.fillText("正确率/百分比",0,15)
			//纵坐标轴
			canvastxt.beginPath()
			canvastxt.moveTo(59.5,400)
			canvastxt.lineTo(59.5,50)
			canvastxt.strokeStyle="#666666"
			canvastxt.stroke()
			//纵坐标轴箭头
			canvastxt.beginPath()
			canvastxt.moveTo(53.5,57)
			canvastxt.lineTo(59.5,50)
			canvastxt.lineTo(65.5,57)
			canvastxt.strokeStyle="#808080"
			canvastxt.stroke()
			//横坐标轴
			canvastxt.beginPath()
			canvastxt.moveTo(59.5,400.5)
			canvastxt.lineTo(679.5,400.5)
			canvastxt.strokeStyle="#666666"
			canvastxt.stroke()
			//横坐标轴箭头
			canvastxt.beginPath()
			canvastxt.moveTo(673.5,394.5)
			canvastxt.lineTo(679.5,400.5)
			canvastxt.lineTo(673.5,406.5)
			canvastxt.strokeStyle="#808080"
			canvastxt.stroke()
			//纵坐标轴值
			for(i=1;i<=10;i++){
				var txt=(100-10*(i-1))+"%";
				canvastxt.fillText(txt,10,(35*i+25))
			}
			//横坐标轴值
			var txtarray=["画面清晰度","接通及时性","会议正常性","管理员态度","总体满意度"];
			for(i=1;i<=5;i++){
				switch(i){
					case 1:
						canvastxt.fillText(txtarray[i-1],35*i+60*i,420)
						break;
					case 2:
					case 3:
						canvastxt.fillText(txtarray[i-1],35*i+69*i,420)
						break;
					case 4:
						canvastxt.fillText(txtarray[i-1],35*i+71*i,420)
						break;
					case 5:
						canvastxt.fillText(txtarray[i-1],35*i+72*i,420)
						break;
				}
			}
			
			var chart=[[15,20,25,30],[15,20,25,60],[15,20,25,30],[15,20,25,30],[15,20,25,30]];
			for(i=0;i<5;i++){
				for(j=0;j<4;j++){
	//				alert(chart[i][j])
					switch(j){
						case 0:
							canvastxt.fillStyle="#fea156"
							canvastxt.fillRect(50+50*(i+1)+15*j+60*i,400.5,15,-350*chart[i][j]/100)
							break;
						case 1:
							canvastxt.fillStyle="#6294fb"
							canvastxt.fillRect(50+50*(i+1)+15*j+60*i,400.5,15,-350*chart[i][j]/100)
							break;
						case 2:
							canvastxt.fillStyle="#87c143"
							canvastxt.fillRect(50+50*(i+1)+15*j+60*i,400.5,15,-350*chart[i][j]/100)
							break;
						case 3:
							canvastxt.fillStyle="#a89071"
							canvastxt.fillRect(50+50*(i+1)+15*j+60*i,400.5,15,-350*chart[i][j]/100)
							break;
					}
					
					
				}
			}
			//坐标图标注释
			var comments=[["非常好","较好","正常","待提高"],["#fea156","#6294fb","#87c143","#a89071"]];
			
			for(j=0;j<4;j++){
				canvastxt.beginPath();
				canvastxt.lineJoin="round"
				canvastxt.lineWidth=5;
				//alert(comments[i][j])
				canvastxt.moveTo(645, 15.5+33*j);
				canvastxt.lineTo(670, 15.5+33*j);
				canvastxt.lineTo(670, 30.5+33*j);
				canvastxt.lineTo(645, 30.5+33*j);
				canvastxt.lineTo(645, 15.5+33*j);
				canvastxt.closePath()
				canvastxt.strokeStyle=comments[1][j]
				canvastxt.stroke()
				canvastxt.fillStyle=comments[1][j]
				canvastxt.fill()
				canvastxt.font="14px 微软雅黑" ;
				canvastxt.fillStyle="#333"
				canvastxt.fillText(comments[0][j],680,28+33*j)
			}
		



		</script>
	</body>
</html>
