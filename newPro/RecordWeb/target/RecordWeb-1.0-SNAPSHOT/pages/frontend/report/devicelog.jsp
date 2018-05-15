 <%@ page import="com.honghe.recordhibernate.entity.DeviceLog" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.honghe.recordhibernate.dao.Pager" %>
 <%@ page import="com.honghe.recordweb.action.frontend.report.ReportDTO" %>
 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <%--
   Created by IntelliJ IDEA.
   User: lch
   Date: 2015/1/14
   Time: 12:59
   To change this template use File | Settings | File Templates.
 --%>
 <div class="table_head " style="margin-top: 0;">
     <div class="wid_10 floatleft">序号</div>
     <div class="wid_30 floatleft">名称</div>
     <div class="wid_30 floatleft">设备数量(台)</div>
     <div class="wid_30 floatleft">设备在线率</div>
 </div>
 <div id="loglist" class="wid_100 floatleft">
     <%
         List<ReportDTO> ReportDTOs = (List<ReportDTO>) request.getAttribute("reportDTOs");
         int pageCount = Integer.parseInt(request.getAttribute("pageCount").toString());
         if(ReportDTOs!=null && !ReportDTOs.isEmpty()){
     %>

     <%
         for(int i=0;i<ReportDTOs.size();i++){
             //log_id, dl.log_user,dl.device_name,dl.device_ip,dl.log_time,dl.log_desc,dl.log_type
     %>
     <div class="table_recycle">
         <div class="wid_10 floatleft hei_100"><%=i+1%></div>
         <div class="wid_30 floatleft hei_100"><%=ReportDTOs.get(i).getGroupName()%></div>
         <div class="wid_30 floatleft hei_100"><%=ReportDTOs.get(i).getCount()%></div>
         <div class="wid_30 floatleft hei_100"><%=ReportDTOs.get(i).getRate()%></div>
     </div>
     <%
         }
     %>

     <link href="${pageContext.request.contextPath}/css/frontend/hpager.css" rel="stylesheet"
           type="text/css"/>
     <%
         Integer currentpage = Integer.parseInt(request.getAttribute("currentPage").toString());
         System.out.println("currentpage: " + currentpage);
         Pager pager = new Pager(pageCount, currentpage, 5,"<span class='pages_prevpage'></span>", "<span class='pages_nextpage'></span>", "", "", false);
//                        Pager pager = new Pager(pageCount, currentpage, pageCount,"<span class='pages_prevpage'></span>", "<span class='pages_nextpage'></span>", "", "", false);
         // Pager pager = new Pager(hostcount,currentpage);
         String pagers = pager.run();
     %>
     <style>
         .table_head {
             line-height: 35px;
         }
         .wid_10, .wid_30 {
             overflow: hidden;
             white-space: nowrap;
             text-overflow: ellipsis;
         }
         #linkpage{
             position: absolute;
             bottom: 5%;
             left: 15%;
             text-align:center;
             width: 44%;
             margin-top: 15px;
         }

         #linkpage ul > span {
             float: left;
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
     <div class="error" style="position: static;">
         <%--<div class="error_text"></div>--%>
         <div class="error_pic">
             <span class="error_pictext">暂无统计信息</span>
         </div>
     </div>
     <%
         }
     %>

 </div>
 <div style="height: 0; line-height: 0; clear: both;"></div>

 <script>
     $(function(){
         var root_path = $("#root_path").val();
         $.post(root_path+'/report/Report_deviceLogList.do?currentPage=' + 1, {} , function (data) {
//            $('#devicelog').html('');
//            $('#devicelog').html(data);
         }, 'html');
     })
 </script>
