<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="${pageContext.request.contextPath}">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
    <title>软件安装 | 集控平台</title>
    <link href="${pageContext.request.contextPath}/css/frontend/hpager.css" rel="stylesheet"  type="text/css"/>
    <link href="${pageContext.request.contextPath}/js/common/layer/skin/layer.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/js/common/layer/skin/layer.ext.css" rel="stylesheet"
          type="text/css"/>
    <%--<link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css" />--%>
    <link href="${pageContext.request.contextPath}/css/frontend/index.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/js/common/colorpicker/css/colorpicker.css" type="text/css" />
    <link href="${pageContext.request.contextPath}/css/frontend/fd-slider.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
    <!--layerdate-->
    <script src="${pageContext.request.contextPath}/js/common/laydate/laydate.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/jquery.cookie.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/common/screendetail.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/tree_jkn_checkbox_forinstall.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/selectmore.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/perfect-scrollbar.with-mousewheel.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/prettify.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/colorpicker/js/colorpicker.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/colorpicker/js/eye.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/colorpicker/js/layout.js?ver=1.0.2"></script>
    <script src="${pageContext.request.contextPath}/js/common/scrolldemo.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/jquery.form.js"></script>
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
        .treecheckboxfalse{
            background: rgba(0, 0, 0, 0) url("${pageContext.request.contextPath}/image/frontend/checkbox_jkn.png") no-repeat scroll -1px -72px;
            height: 14px;
            left: 27px;
            position: absolute;
            top: 10px;
            width: 14px;
        }
        .public_nav ul li a{
            text-align: center;
        }


    </style>
</head>
<%
    List<Map> groupTreeList = (List<Map>) request.getAttribute("groupTree"); //获取分组数据和设备
    Map<String,Object> listmap = (Map<String,Object>) request.getAttribute("installMap");
    String filePath = request.getAttribute("filePath").toString();
    List<Map<String,String>> installList = (List<Map<String,String>>) listmap.get("installList") ;
%>
<body>
	<input type="hidden" id="whichscroll"> 
<div class="public">
    <jsp:include page="/pages/frontend/equipment_header.jsp"></jsp:include>
    <style>
        .tree_titleaiconmain_jkn,.tree_titleaiconmained_jkn{
            position: absolute;
            left: 5px;
            top: 0px;
        }

        .jkn_treecheckbox,.jkn_treecheckboxed{
        	left: 27px;	
        }

    </style>
    <div class="public_left22 floatleft">
        <input type="hidden" value="" id="selected_host" ip="">
        <div class="equipment">教室设备</div>
        <div id="otp_vedeoabout_rvideo">
            <div class='contentr'>
                <div class="tree">
                    <a href="javascript:void(0)" class="tree_titleb tree_titleb_open">所有设备</a>
                    <div class="public_left">
                        <%
                            if (groupTreeList !=null && !groupTreeList.isEmpty())
                            {
                                for (Map groupTreeMap : groupTreeList)
                                {
                                    String group_id = groupTreeMap.get("group_id").toString();
                                    String group_name = groupTreeMap.get("group_name").toString();
                                    List<Map> hostList = (List<Map>) groupTreeMap.get("host_list");
                                    if (hostList == null || hostList.isEmpty()){
                                        continue;
                                    }
                        %>
                        <div class="tree_title tree_title_close ">
                            <span  class="tree_titlea" groupId = "<%=group_id%>" style="text-indent: 42px;"><%=group_name%></span>
                            <span class="tree_titleaiconmain_jkn"></span>
                            <div class="tree_content">
                                <%
                                    if (hostList != null && !hostList.isEmpty()) {
                                        for (Map host : hostList)
                                        {
                                            String host_id = host.get("id").toString();
                                            String host_name = host.get("name").toString();
                                            String host_ip = host.get("host_ip").toString();
                                            String statusStyle = "tree_content_onlinebg";
                                            if (host.get("status").toString().equals("Offline")) {
                                                statusStyle = "tree_content_nousebg";
                                                //continue;
                                            }
                                %>
                                <div class="tree_contenta" hostId = "<%=host_id%>" hostIp = "<%=host_ip%>">
                                    <span   class="<%=statusStyle%>" style="background: none;"></span>
                                    <span class="hostoverflow"> <%=host_name%></span>
                                    <%if(!host.get("status").toString().equals("Offline")){%>
                                    <div class="jkn_treecheckbox"></div>
                                    <%}else{%>
                                        <div class="treecheckboxfalse" style = ""></div>
                                    <%}%>
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
                        %>
                    </div>
                </div>
            </div>
        </div>
    </div>
<style>

</style>
    <div class="public_right floatleft" >
        <div id="upload" class='mm_nogroup_option' style="cursor: pointer; line-height: 35px; margin-top: 0.5% !important;">
            <img src="${pageContext.request.contextPath}/image/frontend/icon/resource-upload.png" style="width:16px"/><span class="content"  onclick="javascript:demo()">上传</span>
        </div>
        <div id="delete" class="mm_nogroup_option"  style="cursor: pointer; line-height: 35px; margin-top: 0.5% !important;">
            <img src="${pageContext.request.contextPath}/image/frontend/icon/delete.png" style="width:16px"/><span class="content" onclick="deleteFile()">删除</span>
        </div>
        <div >
            <%
                if(filePath != null && !filePath.equals(""))
                {
                    String[] paths = filePath.split("\\\\");
                    String _path = "";
                    for(int i = 1;i<paths.length-1;i++) {
                        _path += "\\" + paths[i];
                    }
            %>
            <a href="${pageContext.request.contextPath}/install/Install_installList.do?filePath=<%=_path%>"><span class="mm_nogroup_option" style="margin-left: 20px;"><span class="mm_nogroup_icon"></span>返回</span></a>
            <%
                }
            %>
            <div class="soft_installbtn" style="cursor: pointer ; margin:1% " onclick="javascript:oneKeyuninstall()">卸载</div>
            <div class="soft_installbtn" style="cursor: pointer ; margin:1% " onclick="javascript:oneKeyInstall()">安装</div>
        </div>
        <div class="scrollfather" style="background: none;border-top: 1px solid #28b779" id="mm_right_video">
            <div class="scrollson">
                <%
                    if(installList != null && !installList.isEmpty())
                    {
                        for(int i = 0;i < installList.size();i++)
                        {
                            Map<String,String> install = installList.get(i);
                %>
                  <div title="<%=install.get("fileName")%>" class="<%=Integer.parseInt(install.get("fileType").toString())==0?"soft_card_file":"soft_card"%>">
                    <div class="soft_card_list_into" path="<%=install.get("filePath")%>"></div>
                    <div class="soft_card_list">
                        <div class="cark_checkbox"></div>
                        <div class="soft_card_checkboxtxt"><%=install.get("fileName")%></div>
                    </div>
                   </div>
                <%
                        }
                    }
                %>
                <div class="scroll_ymove">
                    <div class="scroll_y" unorbind="unbind"></div>
                </div>
                <div class="scroll_xmove">
                    <div class="scroll_x" unorbind="unbind"></div>
                </div>
            </div>
        </div>
        <div class="demo2">
            <style>
                #selectdivall0, #selectdivall1, .selectdivall {
                    background: url(${pageContext.request.contextPath}/image/frontend/n_icon_141006.png) 0px -460px no-repeat;
                }

                .set_vv_line {
                    margin-top: 15px;
                    width: 50%;
                }

                .selectdivul a {
                    text-indent: 10px;
                    line-height: 28px
                }
            </style>
            <div class="win_200" style="border:1px solid #000;height:312px;width:548px;position: absolute;left: 0;right:0;top:0;bottom:0;margin:auto">
                <div class="win_head">
                    <div class="win_head_text">软件上传</div>
                    <div class="win_close"></div>
                </div>
                <div class="win_content" style="width: 100%;margin-left: 0;">
                    <div class="set_vv_line">
                        <form id="uploadFile" action="./install/Install_uploadFile.do" enctype="multipart/form-data" method="post">
                            <input style="display: none;" id="myfileName" name="myfileName" value=""/>
                        <input style="margin-left: 60px;margin-top:40px;width: 230px;height: 35px" type="file" id="myfile" name="myfile" />
                           <style>
                          .cancle ,.submit{ background: #28b779 none repeat scroll 0 0;
                            border: 0 none;
                            border-radius: 3px;
                            color: #fff;
                            cursor: pointer;
                            height: 29px;
                            line-height: 29px;
                            outline: medium none;
                            text-align: center;
                            width: 80px;
                          }
                           </style>

                        <input type="button" class="submit" onclick="UpladFile()" value="上传" style="position: absolute;left:330px;top:190px;" />
                            <input type="button" class="cancle" onclick="cancle()" value="取消" style="position: absolute;left:440px;top:190px;" />
                        </form>
                    </div>
                </div>

            </div>
        </div>
    </div>
            <div class="foot">
                <jsp:include page="/pages/frontend/footer.jsp"></jsp:include>
            </div>
    <script>
        //重置public_right高度
    var docwidth = document.documentElement.clientWidth;
    var docheight = parseInt(document.documentElement.clientHeight);
    function change_size() {
        width = $("#otp_vedeoabout_rvideo").width();
        height = parseInt($(".public_right").height() - $(".equipment").height());
        $("#otp_vedeoabout_rvideo").width(width).height(height);
        $('#otp_vedeoabout_rvideo').perfectScrollbar('update');
    }
        $(".soft_card_file .soft_card_list_into").click(function(){
            var path = encodeURI(encodeURI($(this).attr("path")));
            window.location.href="${pageContext.request.contextPath}/install/Install_installList.do?filePath="+path;
        })
        //卡片check
        $(".soft_card_list").click(function(){
            var flag=$(this).find("div").attr("class");
            if(flag=="cark_checkbox"){
                $(this).find("div").eq(0).attr("class","cark_checkboxed")
            }else{
                $(this).find("div").eq(0).attr("class","cark_checkbox")
            }
        })
        function oneKeyuninstall()
        {
            var checkedfileArr = $(".cark_checkboxed");
            var fileName = "unselect:";//若受控端支持指定软件卸载时需要将此名称改成""即可
            var filePath = "";
            /*由于受控端暂时不支持卸载指定软件 故将此判断注释掉 等功能实现后打开即可
            if(checkedfileArr.length < 1)
            {
                layer.msg("未选择安装包！");
                return;
            }
            */
            var checkedHostsArr = $(".jkn_treecheckboxed");
            var host_Ips = "";
            if(checkedHostsArr.length < 1)
            {
                layer.msg("未选择设备！");
                return;
            }
            for(var i = 0;i<checkedfileArr.length;i++)
            {
                fileName += checkedfileArr.eq(i).next(".soft_card_checkboxtxt").text() ;//获取选中的安装包名称
                filePath += checkedfileArr.eq(i).parent().prev(".soft_card_list_into").attr("path") ;//获取选中的安装包名称
            }
            for(var i = 0;i<checkedHostsArr.length;i++)
            {
                host_Ips += checkedHostsArr.eq(i).parent().attr("hostIp") ;//获取选中的host
            }
            $.post("${pageContext.request.contextPath}/install/Install_oneKeyuninstall.do",{hostIps:host_Ips,fileName:encodeURI(fileName),filePath:encodeURI(filePath)},function(data){
                layer.msg(data.msg);
            });
        }
        function oneKeyInstall()
        {
            var checkedfileArr = $(".cark_checkboxed");
            var fileName = "";
            var filePath = "";

            if(checkedfileArr.length < 1)
            {
                layer.msg("未选择安装包！");
                return;
            }
            if(checkedfileArr.length > 1)
            {
                layer.msg("请选择一个安装包！");
                return;
            }
            var checkedHostsArr = $(".jkn_treecheckboxed");
            var host_Ips = "";
            if(checkedHostsArr.length < 1)
            {
                layer.msg("未选择设备！");
                return;
            }
            for(var i = 0;i<checkedfileArr.length;i++)
            {
                fileName += checkedfileArr.eq(i).next(".soft_card_checkboxtxt").text() ;//获取选中的安装包名称
                filePath += checkedfileArr.eq(i).parent().prev(".soft_card_list_into").attr("path") ;//获取选中的安装包名称
            }
            for(var i = 0;i<checkedHostsArr.length;i++)
            {
                if(host_Ips!=""){
                    host_Ips+=":";
                }
                host_Ips += checkedHostsArr.eq(i).parent().attr("hostIp") ;//获取选中的host
            }
            $.post("${pageContext.request.contextPath}/install/Install_oneKeyInstall.do",{hostIps:host_Ips,fileName:encodeURI(fileName),filePath:encodeURI(filePath)},function(data){
                layer.msg(data.msg);
            });
        }
        $(function () {
        	$("#mm_right_video").height($(".public_right").height()*0.93).width($(".public_right").width())
            $(".scrollson").mouseover(function(){
                $("#whichscroll").val($.trim($(this).parent().attr("id")));
                if ((navigator.userAgent.match(/(iPhone|Android|iPad)/i))){
                    var scrollfathter1=document.getElementById($.trim($(this).parent().attr("id")));
                    scrollfathter1.addEventListener("touchstart", touchStart, false);
                    scrollfathter1.addEventListener("touchmove", touchMove, false);
                    scrollfathter1.addEventListener("touchend", touchEnd, false);
                }
            })
            scroll_y("mm_right_video","scrollson","scroll_y","scroll_ymove","scroll_x","scroll_xmove","","wheely","")
            $(".scrollson").css("margin-top","0")
            $(".scroll_y").css("top","0")
            $(".win_200").hide();
            width = $("#otp_vedeoabout_rvideo").width();
            height = parseInt($(".public_right").height())-parseInt($(".equipment").height());

            $(".win_close").click(function(){
                $(".win_200").hide();
            })
            $("#otp_vedeoabout_rvideo").width(width).height(height);
            $('#otp_vedeoabout_rvideo').perfectScrollbar();
            prettyPrint();
        });
        function demo() {
            $(".win_200").show();
        }
      function cancle(){
          $(".win_200").hide();
      }
        function UpladFile() {
            var file = $("#uploadFile"); // 获取文件对象
            var fileName = $("#myfile").val();
            var extension = (fileName.substring(fileName.lastIndexOf(".")+1,fileName.length)).toLowerCase();//截取文件后缀名
            var name= fileName.split("\\");
            if ("exe" == extension) {
                $("#myfileName").val(name[name.length-1]);
                var options  = {
                    url:'./install/Install_uploadFile.do',
                    type:'post',
                    dataType : 'json',
                    success:function(data){
                        layer.msg(data.message);
                        window.location="./Install_installList.do";
                    }
                };
                file.ajaxSubmit(options);
                layer.msg("文件正在上传中，请等待");
            }
            else if(!extension)
            {
                layer.msg("请选择一个文件！");
            }

            else {
                layer.msg("请选择正确的文件格式！");
            }
        }
        function deleteFile(){
            var checkedfileArr = $(".cark_checkboxed");
            var fileName = "";
            var filePath = "";
            if(checkedfileArr.length < 1)
            {
                layer.msg("未选择安装包！");
                return;
            }
            for(var i = 0;i<checkedfileArr.length;i++)
            {
                fileName += checkedfileArr.eq(i).next(".soft_card_checkboxtxt").text() + ":";//获取选中的安装包名称 使用:是因为文件名中不能使用:
            }
            //alert(fileName);
            $.post("./install/Install_deleteFile.do",{deleteName:fileName},function(data){
                layer.msg(data.message);
                window.location="./Install_installList.do";
            },"json");
        }
    </script>

</body>
</html>
