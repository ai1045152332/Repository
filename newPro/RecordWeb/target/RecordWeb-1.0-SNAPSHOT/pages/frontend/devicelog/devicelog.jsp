<%@ page import="com.honghe.recordhibernate.entity.DeviceLog" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.honghe.recordhibernate.dao.Pager" %>
<%--
  Created by IntelliJ IDEA.
  User: lch
  Date: 2015/1/14
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
  <title>日志管理 | 集控平台</title>
  <link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css" />
  <link href="${pageContext.request.contextPath}/css/frontend/index.css" rel="stylesheet" type="text/css" />

  <script src="${pageContext.request.contextPath}/js/common/jquery-1.8.0.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/screendetail.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/perfect-scrollbar.with-mousewheel.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/prettify.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/checkbox_res.js"></script>

  <script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
  <!--layerdate-->
  <script src="${pageContext.request.contextPath}/js/common/laydate/laydate.js"></script>
</head>

<body>
<div class="public">
    <jsp:include page="/pages/frontend/lb_header.jsp"></jsp:include>
    <style>
.sall{height:36px;width: 146px;}

#selectdivall0{
	width: 115px;
	background-position: 0 0;

}
.selectdiv{
	background: none;
	width: 139px;
	height: 27px;
	line-height: 27px;
	margin-top: 3px;
	}
.selectdivul{
	width: 113px;
	}
.selectdivul a{
	text-indent: 10px;
	text-align: left;
	width: 113px;
	}

#selectdivul0{
	border:1px solid #dbdbdb;
/*	margin-top: -2px;*/
	height: 108px;
	width: 113px;
	overflow: hidden;
}
.tr{background: #fff;display: block;margin-bottom: 3px;}
.laydate-icon{
	float: left;
	text-indent: 5px;
	margin-top: 2px;
	width: 100px;
}
.laydate-icon{
	height: 25px;
	line-height: 25px;
}
.logsearch{
	border-radius: 8px;
	background: #28b779;
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
	height: 25px;
	line-height: 25px;
	margin-right: 30px;
	margin-top: 2px;
	outline: none;
	text-indent: 5px;
	width: 120px;
}
.wid_20,.wid_16,.wid_10,.wid_6,.wid_24,.wid_15{
	overflow: hidden;
	text-overflow: ellipsis;
	text-indent: 10px;
	white-space: nowrap;
}
#selectdivall0{
	background: url("${pageContext.request.contextPath}/image/frontend/n_icon_141006.png") no-repeat scroll 0px -458px transparent;
}
</style>
        <div class="mm floatleft">
          <div class="mm_head floatleft">
            <div class="logmargintop" style="width: 930px;margin-left: 30px;">
              <div style="width: 25px;float: left;line-height: 33px;margin-left: 20px;">IP：</div>
              <input type="text" class="deviceloginput" id="ipaddr">
              <div style="width: 85px;float: left;line-height: 33px;">日志时间段：</div>
              <div class="win360_content_date">
                <input id="start" class="laydate-icon">
                <%--<input type="hidden" id="startTime">--%>
              </div>
              <div style="width: 25px;display: block; float: left; text-align: center;line-height: 33px;">至</div>
              <div class="win360_content_date">
                <input id="end" class="laydate-icon">
                <%--<input type="hidden" id="endTime">--%>
              </div>
              <div style="width: 70px;float: left;line-height: 33px;text-align: center;margin-left: 30px;">日志类型：</div>
              <div class="sall">
                <select class="select" id="select0" >
                  <option value="ALL" selected="selected">全部信息</option>
                  <%
                    List<Object[]> logtypeList = (List<Object[]>)request.getAttribute("deviceLogtypeList");
                    if(logtypeList != null && logtypeList.size()>0){
                      for(int i=0;i<logtypeList.size();i++){
                  %>
                        <option value="<%=logtypeList.get(i)[0]%>" ><%=logtypeList.get(i)[1]%></option>
                  <%
                      }

                    }
                  %>
                </select>
                <div class="selectdivall" id="selectdivall0">
                  <div class="selectdiv" id="selectdiv0"></div>
                  <div class="selectdivul" id="selectdivul0" style="height: <%=(logtypeList.size()+1)*36%>px;max-height: 400px;overflow: auto"></div>
                </div>
              </div>
              <div class="logsearch" onclick="search()">搜索</div>
            </div>
          </div>
          <script>
            var start = {
              elem: '#start',
              format: 'YYYY-MM-DD',
              festival: true, //是否显示节日
              istoday: false,
              choose: function(datas){
                end.min = datas; //开始日选好后，重置结束日的最小日期
                end.start = datas //将结束日的初始值设定为开始日
//                $("#startTime").val(datas);
              }
            };
            var end = {
              elem: '#end',
              format: 'YYYY-MM-DD',
              festival: true, //是否显示节日
              istoday: false,
              choose: function(datas){
                start.max = datas; //结束日选好后，重置开始日的最大日期
//                $("#endTime").val(datas);
              }
            };
            laydate(start);
            laydate(end);
          </script>
          <div class="user" style="margin-left: 3%;min-width: 0;width: 94%;">
            	<div class="table_head " style="margin-top: 15px;">
            		<div class="wid_15 floatleft">用户名</div>
            		<div class="wid_15 floatleft">班级名称</div>
                    <div class="wid_20 floatleft">IP地址</div>
                    <div class="wid_10 floatleft">日志类型</div>
            		<div class="wid_24 floatleft">日志内容</div>
            		<div class="wid_16 floatleft">操作时间</div>
            	</div>
                    <div id="loglist" class="wid_100 floatleft hei_93">
                      <%
                        List<Object[]> deviceLogList = (List<Object[]>) request.getAttribute("deviceLogList");
                        int pageCount = Integer.parseInt(request.getAttribute("pageCount").toString());
                        if(deviceLogList!=null && !deviceLogList.isEmpty()){
                      %>

                      
                        <%
                          for(int i=0;i<deviceLogList.size();i++){
                            //log_id, dl.log_user,dl.device_name,dl.device_ip,dl.log_time,dl.log_desc,dl.log_type
                        %>
                           <div  class="table_recycle" >
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
                     
                        <link href="${pageContext.request.contextPath}/css/frontend/hpager.css" rel="stylesheet"
                              type="text/css"/>
                               <style>
                              #linkpage{
                              bottom: 45px;
                              }
                              </style>
                        <%
                            Integer currentpage = Integer.parseInt(request.getAttribute("currentPage").toString());
                            Pager pager = new Pager(pageCount, currentpage, 3,"<span class='pages_prevpage'></span>", "<span class='pages_nextpage'></span>", "", "", false);
                            // Pager pager = new Pager(hostcount,currentpage);
                            String pagers = pager.run();
                        %>
                        <style>
            	#linkpage{
								position: absolute;
								bottom: 4.5%;
								left: 55%;
								text-align:center;
								margin-left: -252px; 
								width: 476px;
							}
            </style>
                        <div id="linkpage">
                            <%=pagers%>
                        </div>
                      <%}else{
                      %>
                                 <style>
															    	.sall{height:36px;width: 146px;}
															
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
																			line-height: 28px;
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
                      }
                      %>

                    </div>
          </div>
        </div>
   <div class="foot">
   	 <jsp:include page="/pages/frontend/footer.jsp"></jsp:include>
   </div>

</div>
</body>
<script>
function DateToUnix(string) {
  var f = string.split(' ', 2);
  var d = (f[0] ? f[0] : '').split('-', 3);
  var t = (f[1] ? f[1] : '').split(':', 3);
  return (new Date(
                  parseInt(d[0], 10) || null,
                  (parseInt(d[1], 10) || 1) - 1,
                  parseInt(d[2], 10) || null,
                  parseInt(t[0], 10) || null,
                  parseInt(t[1], 10) || null,
                  parseInt(t[2], 10) || null
          )).getTime() / 1000;
}
 /**
* 获取url地址中的参数
*/
function urloption(url){
  // alert(url);
        var totalpagesize = $("#pageCount").html();
		if(url.indexOf("?")!=-1){
		var p=url.indexOf("?"); //返回所在位置
		var host="http://"+window.location.host+url.substr(0,p+1);
		var str = url.substr(p+1) //从这个位置开始截取
		strs = str.split("&"); //拆分
		var jumpval=$("#jump").val();
            var patrn = /^[0-9]*$/;
		if(!patrn.exec(jumpval) || jumpval<=0){
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
          $.get(newurl,{},function(data){
            $("#loglist").html(data);
          },"html");
	}
}

  function search(){
    var logtype = $("#select0").val();
    var starttime = $("#start").val()+" 00:00:00";
    var endtime = $("#end").val()+" 23:59:59";
    var ip = $("#ipaddr").val();
    starttime=DateToUnix(starttime);
    endtime = DateToUnix(endtime);
    if(starttime<0 && endtime<0) {
      starttime = 0;
      endtime=0
    }
    //dltype = encodeURI(dltype);
    if(starttime>=0 && endtime>=0){
      var root_path = $("#root_path").val();
      $.post(root_path+'/syslog/SysLog_deviceLogList.do',{logType:logtype,startTime:starttime,endTime:endtime,ip:ip},function(data){
        $("#loglist").html(data);
      },'html');
    }else{
      layer.msg("请选择正确的时间",1);
    }
  }
    function jump(event){
            //判断当event.keyCode 为37时（即左方面键），执行函数to_left();
            //判断当event.keyCode 为39时（即右方面键），执行函数to_right();
            if(event.keyCode == 13){
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
    }

  /**
   * ajax分页
   */
  $("#linkpage li a").live("click",function(){
    //alert("aa");
    var href = $(this).attr("href");
    $.get(href,{},function(data){
      $("#loglist").html(data);
    },"html");
    return false;
  });
  $(function(){
    var totalpagesize = "<%=pageCount%>";
    //分页调整
    var page=totalpagesize;
    var html="<span style='float: left;margin-left: 2px;'>&nbsp;&nbsp;共"+page+"页 &nbsp;&nbsp;跳转到 "+"<input id=\"jump\" type=text style=\"width:20px;background:#d7dade;border:1px solid #5e6470\" title=\"点击回车即可跳转分页\" onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'')\" onkeydown=\"jump(event)\" onafterpaste=\"this.value=this.value.replace(/[^0-9]/g,'')\"/> 页<span>";
    $(".yiiPager").append(html)
    left=-(18)*28/2+"px";
    $("#linkpage").css({"margin-left":left,"width":(17)*28+"px"});

$(".wid_6,.wid_16,wid_10,.wid_36").css({"overflow":"hidden","text-overflow":"ellipsis","white-space":"nowrap"})
  })
</script>
<script src="${pageContext.request.contextPath}/js/common/selectmore.js"></script>
</html>