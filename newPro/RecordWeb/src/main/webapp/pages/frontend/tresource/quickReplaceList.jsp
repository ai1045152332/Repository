<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.honghe.recordweb.util.base.entity.RefreshRandom" %>
<%@ page import="com.honghe.recordweb.util.base.entity.FunctionModule" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
RefreshRandom refreshRandom = new RefreshRandom();
int randomjs = refreshRandom.getRandomjs();
 %>
<link href="${pageContext.request.contextPath}/css/project/project.css?randomjs=<%=randomjs %>" type="text/css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/project/project-master.css?randomjs=<%=randomjs %>" type="text/css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/project/default.css?randomjs=<%=randomjs %>" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/project/quickupdate.css?randomjs=<%=randomjs %>" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/modules/project/default.js?randomjs=<%=randomjs %>"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/modules/project/projectList.js?randomjs=<%=randomjs %>" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/domUtil.js?randomjs=<%=randomjs %>" charset="utf-8"></script>

<input type="button" id="btnShowFlag" style="display:none" value="<s:property value='#parameters.navproject[0]'/>" />
<input type="button" id="btnSearchValue" style="display:none" value="<s:property value='#parameters.keyWords[0]'/>" />
<input type="button" id="btnSorterValue" style="display:none" value="<s:property value='#parameters.sorter[0]'/>" />
<script type="text/javascript">	
	$(function() {
		$("#themeCategory>span").click(function(){
			  if(0==$(this).index()){
				  return;
			  }
			  $("#ss").flushCache();
			  $("#themeCategory>span").each(function(){
				  if($(this).hasClass("checked")){
					  $(this).removeClass("checked");
				  }
	           });
			  $(this).addClass("checked"); 
			  doSearch("");
			});
		$("#showCategory>span").click(function(){
			  if(0==$(this).index()){
				  return;
			  }
			 $("#ss").flushCache();
			  $("#showCategory>span").each(function(){
				  if($(this).hasClass("checked")){
					  $(this).removeClass("checked");
				  }
	           });
			  $(this).addClass("checked"); 
			  doSearch("");
			});
		$("#resolutionCategory>span").click(function(){
			  if(0==$(this).index()){
				  return;
			  }
			 $("#ss").flushCache();
			  $("#resolutionCategory>span").each(function(){
				  if($(this).hasClass("checked")){
					  $(this).removeClass("checked");
				  }
	           });
			  $(this).addClass("checked"); 
			  doSearch("");
		});
		$(".searchmoreother>span").click(function(){
			 $("#ss").flushCache();
			  $(".searchmoreother>span").each(function(){
				  if($(this).hasClass("checked")){
					  $(this).removeClass("checked");
				  }
	           });
			  $(this).addClass("checked"); 
			  doSearch("");
		});
		var theme=$("#themeCategory>.checked").text();
		var showcategory=$("#showCategory>.checked").text();
		var searcherurl="project/Project_searcher.do";
		if($("#myproject").hasClass("checked")){
			searcherurl+="?navPro=1";
		}
		else if($("#pubproject").hasClass("checked")){
			searcherurl+="?navPro=5";
		}
		else if($("#temproject").hasClass("checked")){
			searcherurl+="?navPro=100";
		}
		else if($("#waitpassproject").hasClass("checked")){
			searcherurl+="?navPro=2";
		}
		$("#ss").autocomplete(searcherurl, {
			width : 210,
			selectFirst : true,
			autoFill: true,
			cacheLength : 0,
			extraParams:{theme:function(){return $("#themeCategory>.checked").text();},
				w:function(){
					if($("#resolutionCategory>.checked").text()!="全部"&&$("#resolutionCategory>.checked").text()!="其他")
						return $("#resolutionCategory>.checked").text().split("x")[0];
					else
						return "0";
					},
				h:function(){
					if($("#resolutionCategory>.checked").text()!="全部"&&$("#resolutionCategory>.checked").text()!="其他")
						return $("#resolutionCategory>.checked").text().split("x")[1];
					else
						return "0";
					},
				showcategory:function(){
					return $("#showCategory>.checked").text();
				}
			}
		});
	});
	
	function myrefresh(){
  		window.location.reload();
	}
	
	function clear(){
		$("#ss").flushCache();
	}
	//setTimeout('myrefresh()',1000); //指定1秒刷新一次
</script>
<iframe name="exportframe" style="display:none;"></iframe>
<%
//			UserSession userSession=(UserSession) session.getAttribute(UserSession.NAME);
//			String userName = userSession.getUserInfo().getLoginname();
//			boolean proadd=new FunctionModule().isValid(userName, 16384);
//			boolean proaaudit=new FunctionModule().isValid(userName, 32768);
//			boolean prodel=new FunctionModule().isValid(userName, 65536);
			boolean proadd=true;
			boolean proaaudit=true;
			boolean prodel=true;
		
		%>
<div id = "export" style="display:none">
</div>
<div class="nav">
	<div class="left">节目快速更新  </div>
 <div class="right"></div>
</div>


						<div class="section">
							<div class="header">
                  	    </div>        
				<ul>
				
				<s:iterator value="#request.listProjects" var="x" status="index">
					<li>
						<div class="content">
							<a href="#" title="点击进行快速更新" onclick="enterPublish('<s:property value="#x.prid"/>','<s:property value="#session.USER_SESSION.userInfo.loginname"/>')">
								<img src="<s:property value="#x.thumb"/>">
							</a>	
						<div class="mark"></div>
<!-- 						<div class="name">
							<h1>名字</h1>
						</div> -->
						<div class="name" style="height:30px; font-size:14px;padding-top: 6px;color:#ffffff; font-weight:bold;margin-top: 7px;text-align: center;"  title=<s:property value='#x.name' /> >
											<s:if test="#x.name.length() > 17">
												<s:property value='#x.name.substring(0,17)' escape="false" />...
											</s:if>
											<s:else>
												<s:property value='#x.name' escape="false"/>
											</s:else>						
						</div>	
						</div>	
						<div class="driver">
							<p>已指派终端:</p>
							<p><s:property value='#x.devices' /> </p>
						</div>
					</li>
				</s:iterator>				
	</ul>
</div>

<footer></footer>

<div id="teds" style="height:100%;width:100%;display:none;position:absolute;left:0;top:0;z-index:998;background-color:#333;opacity:0.5;"></div>
<div id="loading0" style="height:100px;width:100px;display:none;position:absolute;left:50%;top:40%;z-index:999;margin-left:-50px;margin-top:-50px;background-image:url('${pageContext.request.contextPath }/images/project/load.gif')"></div>
		

