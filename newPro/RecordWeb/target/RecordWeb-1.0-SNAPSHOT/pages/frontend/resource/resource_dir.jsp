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

    <%--<link href="${pageContext.request.contextPath}/css/frontend/record/hpager.css" rel="stylesheet" type="text/css"/>--%>
    <%--<link href="${pageContext.request.contextPath}/css/frontend/record/index.css" rel="stylesheet" type="text/css"/>--%>
    <%--<link href="${pageContext.request.contextPath}/css/frontend/record/main.css" rel="stylesheet" type="text/css"/>--%>
    <%--<link href="${pageContext.request.contextPath}/css/frontend/record/fd-slider.css" rel="stylesheet" type="text/css"/>--%>
    <%--<script src="${pageContext.request.contextPath}/js/common/jquery-1.8.0.min.js"></script>--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cxmenu.css"/>
    <script src="${pageContext.request.contextPath}/js/common/jquery-1.9.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/jquery.cookie.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/common/perfect-scrollbar.with-mousewheel.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/prettify.js"></script>
    <%--<script src="${pageContext.request.contextPath}/js/lb_common/screendetailforrecord.js"></script>--%>
    <script src="${pageContext.request.contextPath}/js/lb_common/checkbox_res.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/laydate/laydate.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/scrolldemo.js"></script>

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
        /*List<Map> groupTreeList = (List<Map>) request.getAttribute("groupTree");*/
        String userId = request.getAttribute("userId").toString();
        String pagers = "";
        int pageCount = 0;
        int pageNum = 0;
        String searchData = (String) request.getAttribute("searchCondition");//获取上一次的搜索结果
        String orderCondition = (String) request.getAttribute("orderCondition");//获取上一次排序结果
        Integer currentpage = 0;
        String connectFlag = (String) request.getAttribute("callback");
    %>
          <jsp:include page="/pages/frontend/lb_header.jsp"></jsp:include>
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
    .mm_right,.mm{
      min-height: 0;
      position: relative;
    }
            #scrollfatherLeft{
                min-height: 350px !important;
                margin-left: 0;
                width: 100%;
                overflow: auto;
            }

            .scroll_y{
                border-radius: 8px;
                width: 8px;
            }
            .res_tree{
                position: relative;
            }
            .res_treeclasstxt{
                margin-left:0 !important;
            }
        </style>
        <div class="mm" >
            <div class="res_tree">
                <input type="hidden" id="whichscroll" />
                <div class="scrollfather" id="scrollfatherLeft">
                    <%--<div class="scrollson">--%>
                            <%--<div class="tree">--%>
                                <%--&lt;%&ndash;<a href="javascript:void(0)" class="tree_titleb tree_titleb_open">所有设备</a>&ndash;%&gt;--%>
                                <%--<div class="public_left">--%>
                                    <%--${groupTree}--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        ${groupTree}
                   <%-- <%
                     if (groupTreeList.size() > 0) {
                         for (Map groupTreeMap : groupTreeList) {
                             String group_id = groupTreeMap.get("group_id").toString();
                             String group_name = groupTreeMap.get("group_name").toString();
                             List<Map> hostList = (List<Map>) groupTreeMap.get("host_list");
                             if(hostList==null||hostList.isEmpty()){
                                 continue;
                             }

                             boolean flag = false;
                             if (!hostList.isEmpty()) {
                                 for (Map host : hostList) {
                                     String host_dspec = host.get("dspec").toString();
                                     if (!"17".equals(host_dspec)) {
                                         flag = true;
                                     }
                                 }
                             }

                             if (flag) {
                %>--%>
                <%--<div class="res_treegroup" isShow="false" id="group_<%=group_id%>"><div class="res_treegroupcover"></div><%=group_name.trim()%></div>
                <div class="res_treeclass">--%>
                   <%-- <%
                        if (!hostList.isEmpty()) {
                            for (Map host : hostList) {
                                String host_id = host.get("id").toString();
                                String host_name = host.get("name").toString();
                                String host_dspec = host.get("dspec").toString();
                                String host_type = host.get("type").toString();
                                String host_token = host.get("token").toString();
                                String recordType = host.get("host_desc").toString();
                                String statusStyle = "tree_content_onlinebg";
                                String host_ip =host.get("host_ip").toString();
                                String host_div_id = host_ip.replace(".","-");
//                                                    if(host_token != null && !host_token.trim().equals("") && host_token.indexOf("-") > 0){
//                                                        host_ip = host_token.substring(0,(host_token.indexOf("-")));
//                                                    }
                                                //  String onclick = "onclick_1=\"directorView('" + host_id + "','" + host_token + "','" + host_name + "','" + recordType + "')\"";
                    %>--%>
                   <%-- <%if (!"17".equals(host_dspec)) {%>--%>
                    <%--<div class="res_treeclasslist">--%>
                       <%-- <%
                            if("2".equals(host_dspec) || "3".equals(host_dspec)){
                        %>
                            <div class="res_treeclassicon2"></div>
                        <%
                        }else{
                        %>
                            <div class="res_treeclassicon"></div>
                        <%
                            }
                        %>
                        <div class="res_treeclasstxt" id = "<%=host_div_id%>"   ip="<%=host_ip%>" recordType="<%=recordType%>" dspec="<%=host_dspec%>" >  <%=host_name.trim()%></div>
                        <div class="res_treeclasscheckbox"></div>
                    </div>
                    <%}
                                }
                            }
                    %>
                </div>
                <%
                            }
                        }
                    }   
                %>--%>
                    <%--</div>--%>
                    <div class="scroll_ymove">
                        <div class="scroll_y" unorbind="unbind"></div>
                    </div>
                    <div class="scroll_xmove">
                        <div class="scroll_x" unorbind="unbind"></div>
                    </div>
                </div>

            </div>
            <div class="res_card">
                <iframe name ="iframePage"  src="" allowfullscreen="true" class="resiframe" scrolling="no" frameborder="0" width="100%" height="100%"></iframe>
            </div>
        </div>

   <div class="foot">
	<jsp:include page="/pages/frontend/footer.jsp"></jsp:include>
</div>
</div>
<input type="text" style="display: none" id="newsParams"  urlhead="${pageContext.request.contextPath}"/>
</body>
</html>
<script>
    var userId = "<%=userId%>";
    $(function() {
        setTimeout(function(){
            $('#left_nav_ul').cxMenu();
        },0)
        $("#left_nav_ul #spanhost").each(function() {
            if ($(this).attr("hostid") != undefined) {
//                console.log($(this));
//                console.log($(this).attr("hostid"));
//                console.log($(this).attr("hostip"));
//                console.log($(this).attr("dspec"));
//                var host_dspec = $(this).attr("dspec");
//                if ("17" != host_dspec) {
//                    if ("2" == host_dspec || "3" == host_dspec) {
//                        $(this).parent().prepend("<div class='res_treeclassicon2'></div>");
//                    } else {
//                        $(this).parent().prepend("<div class='res_treeclassicon'></div>");
//                    }
//                }
                $(this).parent().children('h6').addClass("res_treeclasstxt");

            }
        });
    })
    $(function(){
        $(".resiframe").height($(".rescard").height())

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
        //点击空白区域，隐藏select模拟
		$(document).bind("click",function(e){
			var target = $(e.target);//获取点击时间
			if(target.closest(".public_setting").length == 0)
			{
				parent.$(".public_setting_menu").hide();
				parent.$(".public_setting_modifypassword").hide();
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
    var connect_flag ="<%=connectFlag%>";
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
        $(".res_treeclasstxt").click(function(){
            $(".res_treeclasstxt").css("color","#000");
            $(this).css("color","#28b779");
            var tmp_ip = $(this).attr("ip");
            var tmp_dspec = $(this).attr("dspec");
            var recordType = $(this).attr("recordType");
            $.cookie("tree_host_id",$(this).attr("id"),{path:'/resource'});
            //     var html='&lt;'+"script src='http://"+tmp_ip+"/js/common/connect.js' "+'&gt;'+'  &lt;'+"/script"+'&gt;';
            // var html='<'+"script src='http://"+tmp_ip+"/js/common/connect.js' "+'>'+'  <'+"/script"+'>';
            var url = urlHead;
            var nasFrom = 1;
            if(tmp_dspec == "2")
            {
                url = "http://" + tmp_ip;
                // url = "https://www.baidu.com/";
                //   alert(connect_flag);
                if(connect_flag == null || connect_flag =="null" || connect_flag.trim() == "")
                {
                    /*   $("#appscript").html("")
                     $("#appscript").append(html)*/
                }
                nasFrom = 2;
            }else if(tmp_dspec== "3"){
                window.open("http://" + tmp_ip);
            }
            //url = url + "/resource/Resource_resourceListByIp.do?hostIp=" + tmp_ip;
            /*if(nasFrom > 1 && connect_flag != null && connect_flag != "null" && connect_flag.trim() != "")*/
            /* if(recordType == 1 || recordType =="1")
             {
             url = url + "/resource/Resource_resourceListByIp.do?hostIp=" + tmp_ip;
             }
             else if(recordType == 0 || recordType == "0")
             {
             url = urlHead + "/resource/Resource_resourceListByIp.do?hostIp=" + tmp_ip;
             }*/
            url = urlHead + "/resource/Resource_resourceListByIp.do?hostIp=" + tmp_ip + "&recordType=" + recordType;
            //url="http://192.168.17.85:8081" + "/resource/Resource_resourceListByIp.do?hostIp=" + tmp_ip;
            $(".resiframe").attr("src",url)

        });
        $(".res_tree,.res_card").height($(".mm").height())
        //初始化滚动条
        $(".scrollson").mouseover(function() {
            //兼容pad的监听事件
            $("#whichscroll").val($.trim($(this).parent().attr("id")));
        })
        //初始化调用
        scroll_y("scrollfatherLeft", "scrollson", "scroll_y", "scroll_ymove", "scroll_x", "scroll_xmove", "", "wheely", "");
        $(".scrollson").css("margin-top", "0");
        $(".scroll_y").css("top", "0");

        var exphei=parent.document.documentElement.clientHeight;
        var param =  $(".resiframe").width() + "-" + $(".resiframe").height()+ "-" +exphei;
        $(".resiframe").attr("name",param);
        $(".res_tree .res_treegroup").click(function(){
            $(this).siblings(".res_treeclass").hide();
            var showing = $(this).attr("isShow");
            if (showing == "false"){
                $(this).next().show();
                $(this).attr("isShow", "true");
                $(this).css("background-position","-149px -276px");
            }
            else if (showing == "true"){
                $(this).next().hide();
                $(this).attr("isShow", "false");
                $(this).css("background-position","-148px -250px");
            }

            $(this).siblings().attr("isShow", "false");
            $(this).siblings(".res_treegroup").css("background-position","-148px -250px");
            $(this).next().children(".res_treeclasslist").find(".res_treeclasstxt").eq(0).click();
            $.cookie("tree_group_id",$(this).attr("id"),{path:'/resource'});
            scroll_y("scrollfatherLeft", "scrollson", "scroll_y", "scroll_ymove", "scroll_x", "scroll_xmove", "", "wheely", "");
            $("#scrollfatherLeft .scrollson").css("margin-top", "0");
            $("#scrollfatherLeft .scroll_y").css("top", "0");
        })
        if($.cookie("tree_group_id") != undefined)
        {
            var groupId = $.cookie("tree_group_id");
            $("#" + groupId).click();
        }
        else{
            $(".res_tree .res_treegroup").eq(0).click()
        }

        if($.cookie("tree_host_id") != undefined)
        {
            console.log("tree_host_id111" + "----------" + $.cookie("tree_host_id"));
            var hostId = $.cookie("tree_host_id");
            $("#" + hostId).click();
        }
        else
        {
            console.log("tree_host_id2222");
            $(".res_tree .res_treegroup").eq(0).next().find(".res_treeclasslist").eq(0).children("div").eq(1).click()
        }
        var el = $('#left_nav_ul').find('a').first();
        setTimeout(function () {
            enterClick(el);
        }, 1000);

    });
    function enterClick(el){
        el.click();
        if(el.next().find('a').first().length!=0){
            enterClick(el.next().find('a').first());
        }else{
            el.find(".res_treeclasstxt").click();
        }
    }
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/lb_common/tree.cxmenu.js"></script>