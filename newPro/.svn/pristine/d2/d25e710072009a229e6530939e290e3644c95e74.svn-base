<%@ page import="com.honghe.recordhibernate.dao.Page" %>
<%@ page import="com.honghe.recordhibernate.dao.Pager" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.io.File" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
    <title>资源管理 | 集控平台</title>

    <link href="${pageContext.request.contextPath}/css/frontend/record/hpager.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/common/jquery-1.8.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/jquery.cookie.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/common/autoheight.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/perfect-scrollbar.with-mousewheel.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/prettify.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/checkbox_res.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/laydate/laydate.js"></script>
</head>

<body>
<%

    response.setHeader("Cache-Control","no-store");

    response.setHeader("Pragrma","no-cache");

    response.setDateHeader("Expires",0);

%>
<div class="public">
    <%

        Page pageResource = (Page)request.getAttribute("pageResource"); //获取信息列表
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String pageType = (String) request.getAttribute("pageType");
        String userId = request.getAttribute("userId").toString();
        String pagers = "";
        int pageCount = 0;
        int pageNum = 0;
        String searchData = (String) request.getAttribute("searchCondition");//获取上一次的搜索结果
        String orderCondition = (String) request.getAttribute("orderCondition");//获取上一次排序结果
        // System.out.println(searchData + "~~~~~~~~~~~~~~~~~~~~~~");
        Integer currentpage = 0;
    %>
    <table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%">
      <%--  <jsp:include page="../header.jsp"></jsp:include>--%>
<%--   <jsp:include page="/pages/frontend/lb_header.jsp"></jsp:include>--%>
 <style>

     .tab_content_listinput{
         border:1px solid #bbb
     }
     .tab_content_listtext{
         color: #80858F;
     }
     #linkpage{
         position: absolute;
         bottom: 29px;
         left: 55%;
         text-align: center;
         margin-left: -252px;
         width: 476px;
     }
     .mm_list_options{
         margin-right: 5px;
     }
 </style>

 <tr>
     <td class="bgf2f2f2" width="100%" height="100%">
         <div class="mm">
             <div class="mm_head">
                 <div class="mm_head_option" style="text-indent: 12px;display:none;">
                     <a href="javascript:history.go(-1);" onclick="clearcookie()">
                         <span class="back"></span>
                         <span style="font-size:14px;">返回</span></a>
                 </div>
                 <span class="mm_nogroup_option" style="margin-left: 23px;text-indent: 0;"></span>
                 <span class="sm_pink">
                     <span class="sm_pinkmenu">
                         <li id="res_name" class="orderCondition">按文件名称</li>
                         <li id="res_updatetime" class="orderCondition">按修改日期</li>
                         <li id="host_name" class="orderCondition">按班级名称</li>
                     </span>
                 </span>
                 <span class="sm_search">
                     <input class="sm_search_input" type="text" id = "searchData" value="<%=searchData != null && !searchData.trim().equals("") ? searchData : ""%>"   placeholder="输入主讲人、课程或科目"/>
                     <input type="submit" class="sm_search_btn" id="searchBtn" value="" />
                 </span>

             </div>
             <%
                 // pageResource = null;
                 if(pageResource != null && pageResource.getResult() != null)
                 {
                     // System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                     List<Map<String,Object>> mapList = pageResource.getResult();
                     pageCount = pageResource.getTotalPageSize();
                     pageNum = mapList.size();
                     currentpage = Integer.parseInt(request.getAttribute("currentPage").toString());
                     if(currentpage > pageCount)
                         currentpage = pageCount;



             %>
             <table border="0" cellpadding="0" cellspacing="0" width="100%">
                 <tr>
                     <td width="100%">
                         <div class="mm_right">
                             <div id="mm_right_video">

                                 <div class='mm_right_video_content' style="width: 100%">
                                     <%
                                         String noDataStr = "--";
                                         String videoUrl ="";
                                         if(mapList.size() > 0)
                                         {
                                             Pager pager = new Pager(pageCount, currentpage,3, "<span class='pages_prevpage'></span>", "<span class='pages_nextpage'></span>", "", "", false);
                                             pagers = pager.run();//分页样式
                                             for (Map<String,Object> resInfo : mapList)
                                             {
//                                                String path=application.getRealPath("/upload");
//                                                System.out.println(resInfo.get("res_thumb"));
//                                                File file=new File(path+File.separator + resInfo.get("res_thumb"));
//
//                                                if(!file.exists()) {
//                                                    videoUrl = request.getContextPath() + "/image/frontend/video.png";
//                                                }
//                                                else{
//                                                    videoUrl= request.getContextPath() + "/upload/" + resInfo.get("res_thumb");
//
//                                                 }
                                        /* String ss1 = resInfo.get("res_path").toString();
                                         if(ss1.equals(""))
                                         {
                                             ss1 =null;
                                         }*/
                                                 videoUrl = request.getContextPath() + "/image/frontend/video.png";
                                                 if(resInfo.get("res_path")!=null && resInfo.get("res_thumb")!= null && !resInfo.get("res_path").toString().equals("") && !resInfo.get("res_thumb").toString().equals("")) {
                                                     String ss1 = resInfo.get("res_path").toString();
                                                     String ss2 = ss1.substring(2, ss1.length());
                                                     String[] s1 = ss2.split("\\\\\\\\");
                                                     if (s1 != null && s1.length > 2) {
                                                         String r1 = s1[s1.length - 1];
                                                         String r2 = s1[s1.length - 2];
                                                         //String r3 = s1[s1.length - 3];
                                                         // System.out.println(ss1+"aaa"+ss2+"bbb"+r1+"ccc"+r2+"ddd");
                                                         File file = new File(ss1 + "\\" + resInfo.get("res_thumb").toString());
                                                         if (file.exists() && file.isFile()) {
                                                             videoUrl = "/res/" + r2 + "/" + r1 + "/" + resInfo.get("res_thumb").toString();
                                                         }
                                                     }
                                                 }
                                     %>
                                     <div class="sm_list" resId = "<%=resInfo.get("res_id")%>">
                                         <a href="${pageContext.request.contextPath}/video/Video_videoList.do?rId=<%=resInfo.get("res_id")%>">
                                             <img src="<%=videoUrl%>" class="sm_listimg" />
                                             <span class="sm_listdate"><%=resInfo.get("res_name") != null ? resInfo.get("res_name") : noDataStr%></span>
                                             <span class="sm_listtext"><span class="sm_listtext30" <%=resInfo.get("host_name")!= null && !resInfo.get("host_name").equals("") ? "title='班级名称：" + resInfo.get("host_name") + "'" : ""%>><%=resInfo.get("host_name")!= null && !resInfo.get("host_name").equals("") ? resInfo.get("host_name") : noDataStr%></span><span class="sm_listtext30"  <%=resInfo.get("res_subject")!= null && !resInfo.get("res_subject").equals("") ? "title='科目：" + resInfo.get("res_subject") + "'" : ""%>><%=resInfo.get("res_subject") != null && !resInfo.get("res_subject").equals("") ? resInfo.get("res_subject") : noDataStr%></span></span>
                                             <span class="sm_listtext"><span class="sm_listtext26" <%=resInfo.get("res_speaker")!= null && !resInfo.get("res_speaker").equals("") ? "title='主讲人：" + resInfo.get("res_speaker") + "'" : ""%>><%=resInfo.get("res_speaker") != null && !resInfo.get("res_speaker").equals("") ? resInfo.get("res_speaker") : noDataStr%></span><span class="sm_listtext45" <%=resInfo.get("res_course")!= null && !resInfo.get("res_course").equals("") ? "title='课程：" + resInfo.get("res_course") + "'" : ""%>><%=resInfo.get("res_course") != null && !resInfo.get("res_course").equals("") ? resInfo.get("res_course") : noDataStr%></span><span class="sm_listtext26" style="text-indent: 10px" <%=resInfo.get("res_size")!= null ? "title='文件大小：" + resInfo.get("res_size") + "'" : ""%> ><%=resInfo.get("res_size") != null ? resInfo.get("res_size") : noDataStr%></span></span>

                                         </a>
                                         <div class="mm_list_option" style="width: 240px;" resId = "<%=resInfo.get("res_id")%>">
                                             <div class="mm_list_options mm_list_edit" title="修改名称"></div>
                                             <div class="mm_list_options mm_list_del" title="删除" hostnumber="0"></div>
                                         </div>
                                     </div>

                                     <%
                                         }
                                     }
                                     else
                                     {
                                     %>
                                     <div class="nofind" style="position: relative;left:50%;margin-left:-90px">
                                         找不到您想要的内容
                                     </div>
                                     <%
                                         }
                                     %>
                                 </div>
                             </div>
                         </div>
                     </td>
                 </tr>
                 <tr>
                     <td>
                         <div class="floatleft" id ="linkpage"><%=pagers%></div>
                     </td>
                 </tr>
             </table>

             <%
             }
             else
             {

             %>
             <div class="nofind" style="position: relative;left:50%;margin-left:-90px">
                 找不到您想要的内容
             </div>
             <%
                 }
             %>
         </div>
     </td>
 </tr>

</table>
<div class="foot">
<jsp:include page="/pages/frontend/footer.jsp"></jsp:include>
</div>
</div>
<input type="text" style="display: none" id="newsParams"  urlhead="${pageContext.request.contextPath}"/>
</body>
</html>
<script>
var userId = "<%=userId%>";
$(function(){
 if($.cookie("back")=="1")
 {
     $(".mm_head_option").show();
 }
 $.cookie("back","0",{path:'/resource'});

 var totalpagesize = "<%=pageCount%>";
 //分页调整
 var page=totalpagesize;
 var html="<span style='float: left;margin-left: 2px;'>&nbsp;&nbsp;共"+page+"页 &nbsp;&nbsp;跳转到 "+"<input id=\"jump\" type=text style=\"width:20px;background:#d7dade;border:1px solid #5e6470\" title=\"点击回车即可跳转分页\" onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'')\" onafterpaste=\"this.value=this.value.replace(/[^0-9]/g,'')\"/> 页<span>";
 $(".yiiPager").append(html)
 left=-(18)*28/2+"px";
 $("#linkpage").css({"margin-left":left,"width":(17)*28+"px"});
 $(document).keydown(function(event){
     //alert(event.ctrlKey+"=="+event.which)
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
/**
* 获取url地址中的参数
*/
function urloption(url){
 // alert(url);
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
var urlHead = $("#newsParams").attr("urlhead");
var pageType = "<%=pageType%>";//0,页面加载；1,页面搜索
var pageNum = "<%=pageNum%>";
var pageCount = "<%=pageCount%>";
var order_condition = "<%=orderCondition%>";
var currentPage = "<%=currentpage%>";
$(".orderCondition").click(function(){
 order_condition = $(this).attr("id");
 currentPage = 1;
 $.cookie("back","1",{path:'/resource'});
 refreshPage();
});
$(function(){
 $(".sm_pink").click(function(){
     var flag=$(".sm_pinkmenu").css("display");
     if(flag=="none"){
         $(".sm_pinkmenu").css("display","block")
     }else{
         $(".sm_pinkmenu").css("display","none")
     }
 })
 //点击空白区域，隐藏巡课select模拟
 $(document).bind("click", function (e) {
     var target = $(e.target);//获取点击事件
     if (target.closest(".sm_pink").length == 0) {
         $(".sm_pinkmenu").hide();//隐藏divli
     }
 })

})
$(".mm_list_edit").click(function(){
 var resId =$(this).parent().attr("resId");
 if(resId == null || resId.trim(" ") == "")
 {
     layer.msg("未找到数据！");
 }
 else
 {
     $.layer({
         type: 2,
         title: '编辑信息',
         shadeClose: true,
         maxmin: false,
         fix: false,
         area:['362px','403px'],
         iframe: {
             src: urlHead + '/resource/Resource_getResource.do?resId=' + resId,
             scrolling: 'no'
         }
     });
 }
});
$(".mm_list_del").click(function(){
 var resId = $(this).parent().attr("resId");
 if(resId == null || resId.trim(" ") == "")
 {
     layer.msg("未找到数据！");
 }
 else
 {
     $.layer({
         shade: [0.5, '#000'],
         area: ['310px', '129px'],
         title: '删除',
         dialog: {
             msg: '您确定要删除该文件夹？',
             btns: 2,
             type: 4,
             btn: ['确定', '取消'],
             yes: function () {
                 $.post(urlHead + "/resource/Resource_deleteResource.do",{resId:resId},function(data){
                     layer.msg(data.msg);
                     if (data.success == true) {
                         if(pageNum == 1 || pageNum == "1")
                         {
                             currentPage --;
                         }
                         if(currentPage < 1)
                         {
                             currentPage =1;
                         }
                         setTimeout(function(){
                             // window.location.reload();

                             refreshPage();
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
$("#searchBtn").click(function(){

 var search_data = $("#searchData").val();
 if(search_data == null || search_data.trim(" ") == "")
 {
     layer.msg("请输入查询条件！");
     return null;
 }
 else
 {
     $.cookie("back","1",{path:'/resource'});
     pageType = 1;
     // alert("----"+search_data+"-----");
     var tmp_search_data = encodeURI(encodeURI(search_data));
     // alert(search_data + "~~~~" + encodeURI(search_data) + "~~"+decodeURI(encodeURI(search_data))+"~~" + tmp_search_data);
     location.href = urlHead + "/resource/Resource_resourceSearch.do?searchCondition=" + tmp_search_data + "&orderCondition=" + order_condition;
     //$(".mm_head_option").show();

 }
 // window.open("${pageContext.request.contextPath}/video/Video_cameraInfo.do");
});
function refreshPage()
{
 var url = urlHead;
 if(pageType == 1 || pageType == "1")
 {
     // alert(search_data + "~~~~" + encodeURI(search_data) + "~~"+decodeURI(encodeURI(search_data))+"~~" + tmp_search_data);
     var search_data = $("#searchData").val();
     var tmp_search_data = encodeURI(encodeURI(search_data));
     url += "/resource/Resource_resourceSearch.do?pageType=" + pageType + "&orderCondition=" + order_condition + "&searchCondition=" + tmp_search_data + "&currentPage=" + currentPage;
 }
 else
 {
     url += "/resource/Resource_resourceList.do?pageType=" + pageType + "&orderCondition=" + order_condition + "&currentPage=" + currentPage;
 }
 location.href = url;
}
function clearcookie()
{
 $.cookie("back","0",{path:'/resource'});
}
</script>