<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="com.honghe.recordhibernate.dao.Pager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="${pageContext.request.contextPath}">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
    <title>设备控制</title>
    <link href="${pageContext.request.contextPath}/css/frontend/hpager.css" rel="stylesheet"  type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
    <!--layerdate-->
    <script src="${pageContext.request.contextPath}/js/common/laydate/laydate.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/jquery.cookie.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/common/xk_autoheight.js"></script>

</head>
<body>
    <div class="public">
        <jsp:include page="/pages/frontend/header.jsp"></jsp:include>
        <link href="${pageContext.request.contextPath}/js/common/layer/skin/layer.css" rel="stylesheet" type="text/css"/>
    	<link href="${pageContext.request.contextPath}/js/common/layer/skin/layer.ext.css" rel="stylesheet" type="text/css"/>
    	    <style>
       
        #linkpage{
        	position: absolute;
			bottom: 45px;
			left: 55%;
			text-align:center
		}
		.tr {
	    background: none repeat scroll 0% 0% #FFF;
	    display: table;
	    margin-bottom: 3px;
	}
	.sall{height:36px;width: 115px;}
	
	#selectdivall0{
		width: 115px;
		background-position: 0 0;
	
	}
	.selectdiv{
		background: none;
		width: 115px;
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
	.table_recycle{background: none;}
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
	#linkpage{
		position: absolute;
		bottom: 25px;
		left: 55%;
		text-align:center
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
		background: url("/dmanager/image/frontend/n_icon_141006.png") no-repeat scroll 0px -458px transparent;
	}
	@media (min-height:684px) and (max-height:900px) {
		.deviceloginput,.laydate-icon,#selectdivall0,.logsearch{
			margin-top: 5px;
		}
	}

</style>
        <div class="res floatleft">
          <div class="res_head floatleft">
            <div class="loghead_skew">
              <div style="width: 25px;float: left;line-height: 33px;margin-left: 20px;">IP：</div>
              <input class="deviceloginput" id="ipaddr" type="text">
              <div style="width: 85px;float: left;line-height: 33px;">日志时间段：</div>
              <div class="win360_content_date">
                <input id="start" class="laydate-icon">
                <input value="" id="startTime" type="hidden">
              </div>
              <div style="width: 25px;display: block; float: left; text-align: center;line-height: 33px;">至</div>
              <div class="win360_content_date">
                <input id="end" class="laydate-icon">
                <input value="" id="endTime" type="hidden">
              </div>
              <div style="width: 70px;float: left;line-height: 33px;text-align: center;margin-left: 30px;">日志类型：</div>
              <div class="sall">
                <select class="select" id="select0">
                  <option value="ALL" selected="selected">全部信息</option>
                  
                        <option value="SYSTEM">系统日志</option>
                  
                        <option value="MESSAGE">消息日志</option>
                  
                        <option value="PLAN">定时计划日志</option>
                  
                        <option value="DCONTROL">设备控制日志</option>
                  
                        <option value="LOGIN">登录日志</option>
                  
                </select>
                <div class="selectdivall" id="selectdivall0">
                  <div class="selectdiv" id="selectdiv0">全部信息</div>
                  <div class="selectdivul" id="selectdivul0" style="height: 216px;max-height: 400px;overflow: auto"><a style="color: rgb(255, 255, 255); background: rgb(40, 183, 121) none repeat scroll 0% 0%;" href="javascript:void(0)">全部信息</a><a style="color: rgb(51, 51, 51); background: rgb(255, 255, 255) none repeat scroll 0% 0%;" href="javascript:void(0)">系统日志</a><a style="color: rgb(51, 51, 51); background: rgb(255, 255, 255) none repeat scroll 0% 0%;" href="javascript:void(0)">消息日志</a><a style="color: rgb(51, 51, 51); background: rgb(255, 255, 255) none repeat scroll 0% 0%;" href="javascript:void(0)">定时计划日志</a><a style="color: rgb(51, 51, 51); background: rgb(255, 255, 255) none repeat scroll 0% 0%;" href="javascript:void(0)">设备控制日志</a><a style="color: rgb(51, 51, 51); background: rgb(255, 255, 255) none repeat scroll 0% 0%;" href="javascript:void(0)">登录日志</a></div>
                </div>
              </div>
              <div class="logsearch" onclick="search()">搜索</div>
               <div class="logsearch" style="margin-left: 15px;width: 120px;background: none;border:1px solid #dbdbdb;color: #333;">查看折线图</div>
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
                $("#startTime").val(datas);
              }
            };
            var end = {
              elem: '#end',
              format: 'YYYY-MM-DD',
              festival: true, //是否显示节日
              istoday: false,
              choose: function(datas){
                start.max = datas; //结束日选好后，重置开始日的最大日期
                $("#endTime").val(datas);
              }
            };
            laydate(start);
            laydate(end);
          </script>
          <div class="res_center floatleft">
            	<div class="wid_100 floatleft hei_7 lh300" style="background: #28B779;color: #fff;text-align: center">
            		<div class="wid_15 floatleft">用户名</div>
            		<div class="wid_15 floatleft">班级名称</div>
                    <div class="wid_20 floatleft">IP地址</div>
                    <div class="wid_10 floatleft">日志类型</div>
            		<div class="wid_24 floatleft">日志内容</div>
            		<div class="wid_16 floatleft">操作时间</div>
            	</div>
                    <div id="loglist" class="wid_100 floatleft hei_93">
                      

                      <div class="wid_100 floatleft hei_99" style="margin-top: 1%;">
                        
                          <div class="wid_100 floatleft hei_7 lh300" style="margin-top: 0.25%; background: white;text-align: center">
                          	<div class="wid_15 floatleft hei_100">admin<!--操作用户名称--></div>
                            <div class="wid_15 floatleft hei_100">127.0.0.1<!--班级名称--></div>
                            <div class="wid_20 floatleft hei_100">127.0.0.1<!--IP地址--></div>
							<div class="wid_10 floatleft hei_100">登录日志<!--日志类型--></div>
		            		<div class="wid_24 floatleft hei_100">用户admin登陆系统<!--日志内容--></div>
		            		<div class="wid_16 floatleft hei_100">2015-08-14 15:50:47<!--操作时间-->
		            		</div>

                          </div>
                        
                          <div class="wid_100 floatleft hei_7 lh300" style="margin-top: 0.25%; background: white;text-align: center">
                          	<div class="wid_15 floatleft hei_100">System<!--操作用户名称--></div>
                            <div class="wid_15 floatleft hei_100">daping<!--班级名称--></div>
                            <div class="wid_20 floatleft hei_100"><!--IP地址--></div>
							<div class="wid_10 floatleft hei_100">系统日志<!--日志类型--></div>
		            		<div class="wid_24 floatleft hei_100">192.168.17.79-HHTC----<!--日志内容--></div>
		            		<div class="wid_16 floatleft hei_100">2015-08-14 15:50:30<!--操作时间-->
		            		</div>

                          </div>
                        
                          <div class="wid_100 floatleft hei_7 lh300" style="margin-top: 0.25%; background: white;text-align: center">
                          	<div class="wid_15 floatleft hei_100">admin<!--操作用户名称--></div>
                            <div class="wid_15 floatleft hei_100">127.0.0.1<!--班级名称--></div>
                            <div class="wid_20 floatleft hei_100">127.0.0.1<!--IP地址--></div>
							<div class="wid_10 floatleft hei_100">登录日志<!--日志类型--></div>
		            		<div class="wid_24 floatleft hei_100">用户admin登陆系统<!--日志内容--></div>
		            		<div class="wid_16 floatleft hei_100">2015-08-14 15:48:58<!--操作时间-->
		            		</div>

                          </div>
                        
                          <div class="wid_100 floatleft hei_7 lh300" style="margin-top: 0.25%; background: white;text-align: center">
                          	<div class="wid_15 floatleft hei_100">System<!--操作用户名称--></div>
                            <div class="wid_15 floatleft hei_100">daping<!--班级名称--></div>
                            <div class="wid_20 floatleft hei_100"><!--IP地址--></div>
							<div class="wid_10 floatleft hei_100">系统日志<!--日志类型--></div>
		            		<div class="wid_24 floatleft hei_100">192.168.17.79-HHTC----<!--日志内容--></div>
		            		<div class="wid_16 floatleft hei_100">2015-08-14 15:48:31<!--操作时间-->
		            		</div>

                          </div>
                        
                          <div class="wid_100 floatleft hei_7 lh300" style="margin-top: 0.25%; background: white;text-align: center">
                          	<div class="wid_15 floatleft hei_100">System<!--操作用户名称--></div>
                            <div class="wid_15 floatleft hei_100">class<!--班级名称--></div>
                            <div class="wid_20 floatleft hei_100"><!--IP地址--></div>
							<div class="wid_10 floatleft hei_100">系统日志<!--日志类型--></div>
		            		<div class="wid_24 floatleft hei_100">192.168.17.79-HHTC----<!--日志内容--></div>
		            		<div class="wid_16 floatleft hei_100">2015-08-14 15:48:14<!--操作时间-->
		            		</div>

                          </div>
                        
                          <div class="wid_100 floatleft hei_7 lh300" style="margin-top: 0.25%; background: white;text-align: center">
                          	<div class="wid_15 floatleft hei_100">System<!--操作用户名称--></div>
                            <div class="wid_15 floatleft hei_100">class<!--班级名称--></div>
                            <div class="wid_20 floatleft hei_100"><!--IP地址--></div>
							<div class="wid_10 floatleft hei_100">系统日志<!--日志类型--></div>
		            		<div class="wid_24 floatleft hei_100">192.168.17.79-HHTC----<!--日志内容--></div>
		            		<div class="wid_16 floatleft hei_100">2015-08-14 15:48:03<!--操作时间-->
		            		</div>

                          </div>
                        
                          <div class="wid_100 floatleft hei_7 lh300" style="margin-top: 0.25%; background: white;text-align: center">
                          	<div class="wid_15 floatleft hei_100">System<!--操作用户名称--></div>
                            <div class="wid_15 floatleft hei_100">daping<!--班级名称--></div>
                            <div class="wid_20 floatleft hei_100">192.168.17.79<!--IP地址--></div>
							<div class="wid_10 floatleft hei_100">系统日志<!--日志类型--></div>
		            		<div class="wid_24 floatleft hei_100">Read timed out<!--日志内容--></div>
		            		<div class="wid_16 floatleft hei_100">2015-08-14 15:47:54<!--操作时间-->
		            		</div>

                          </div>
                        
                          <div class="wid_100 floatleft hei_7 lh300" style="margin-top: 0.25%; background: white;text-align: center">
                          	<div class="wid_15 floatleft hei_100">System<!--操作用户名称--></div>
                            <div class="wid_15 floatleft hei_100">daping<!--班级名称--></div>
                            <div class="wid_20 floatleft hei_100">192.168.17.79<!--IP地址--></div>
							<div class="wid_10 floatleft hei_100">系统日志<!--日志类型--></div>
		            		<div class="wid_24 floatleft hei_100">Read timed out<!--日志内容--></div>
		            		<div class="wid_16 floatleft hei_100">2015-08-14 15:47:44<!--操作时间-->
		            		</div>

                          </div>
                        
                          <div class="wid_100 floatleft hei_7 lh300" style="margin-top: 0.25%; background: white;text-align: center">
                          	<div class="wid_15 floatleft hei_100">admin<!--操作用户名称--></div>
                            <div class="wid_15 floatleft hei_100">127.0.0.1<!--班级名称--></div>
                            <div class="wid_20 floatleft hei_100">127.0.0.1<!--IP地址--></div>
							<div class="wid_10 floatleft hei_100">登录日志<!--日志类型--></div>
		            		<div class="wid_24 floatleft hei_100">用户admin登陆系统<!--日志内容--></div>
		            		<div class="wid_16 floatleft hei_100">2015-08-14 15:41:54<!--操作时间-->
		            		</div>

                          </div>
                        
                          <div class="wid_100 floatleft hei_7 lh300" style="margin-top: 0.25%; background: white;text-align: center">
                          	<div class="wid_15 floatleft hei_100">System<!--操作用户名称--></div>
                            <div class="wid_15 floatleft hei_100">class<!--班级名称--></div>
                            <div class="wid_20 floatleft hei_100"><!--IP地址--></div>
							<div class="wid_10 floatleft hei_100">系统日志<!--日志类型--></div>
		            		<div class="wid_24 floatleft hei_100">192.168.17.79-HHTC----<!--日志内容--></div>
		            		<div class="wid_16 floatleft hei_100">2015-08-14 15:41:35<!--操作时间-->
		            		</div>

                          </div>
                        
                      </div>
                        <link href="/dmanager/css/frontend/hpager.css" rel="stylesheet" type="text/css">
                        
                        <div style="margin-left: -252px; width: 476px;" id="linkpage">
                            <ul class="yiiPager"><li class="previous "><span class="pages_prevpage"></span></li><li class="selected ">1</li><li class="page"><a href="/dmanager/syslog/SysLog_deviceLogList.do?currentPage=2">2</a></li><li class="page"><a href="/dmanager/syslog/SysLog_deviceLogList.do?currentPage=3">3</a></li><li class="next"><a href="/dmanager/syslog/SysLog_deviceLogList.do?currentPage=2"><span class="pages_nextpage"></span></a></li><span style="float: left;margin-left: 2px;">&nbsp;&nbsp;共284页 &nbsp;&nbsp;跳转到 <input id="jump" style="width:20px;background:#d7dade;border:1px solid #5e6470" title="点击回车即可跳转分页" type="text" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')"> 页<span></span></span></ul>
                        </div>
                      

                    </div>
          </div>
        </div>
        
        
        
</body>
</html>
