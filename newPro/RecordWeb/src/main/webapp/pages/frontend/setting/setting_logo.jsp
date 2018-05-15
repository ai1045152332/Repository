<%@ page import="com.honghe.recordhibernate.entity.Logo" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
    <title>系统设置 | 集控平台</title>
    <link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/time_celve.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/frontend/hpager.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/index.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/fd-slider.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/schedule.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/frontend/time_celve.css" rel="stylesheet" type="text/css">
    <link id="skinlayercss" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/layer.css"></link>
    <link id="skinlayerextcss" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/layer.ext.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.mCustomScrollbar.css">
    <script src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/lb_common/screendetailforrecord.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/lb_frontend/setting/setting.js"></script>

</head>
<body>
	
<div class="public" style="min-width:1680px;">
        <jsp:include page="/pages/frontend/lb_header.jsp"></jsp:include>
        <style>
            .set_vv_save{
                cursor: pointer;
            }
            .headr {
                margin-left: 0px;
                margin-top: 0px;
                text-indent: 10px;
                margin-left: 30px;
            }
        </style>
        <div class="set">
            <div class="set_head">
               <div class="settinghead">
                    <ul>
                        <li onclick="javascript:jump(0)" style="color:#28b779;"><div class="tb"></div>台标</li>
                        <li onclick="javascript:jump(1)"><div class="videoinfo"></div>录像信息</li>
                        <li onclick="javascript:jump(2)"><div class="living"></div>直播</li>
                        <li onclick="javascript:jump(3)"><div class="nas"></div>NAS存储设置</li>
                        <li onclick="javascript:jump(4)"><div class="passuser"></div>录播主机用户名密码设置</li>
                        <li onclick="javascript:jump(5)" ><div class="ftp"></div>FTP设置</li>
                        <li onclick="javascript:jump(6)" ><div class="license"></div>License设置</li>
                        <li onclick="javascript:jump(7)" ><div class="second_nav_selected"></div>课表类型选择</li>
                        <li onclick="javascript:jump(8)"><div class="second_nav_schedule"></div>课表管理</li>
                        <li onclick="javascript:jump(9)" ><div class="second_nav_celve"></div>作息时间策略</li>
                        <li onclick="javascript:jump(10)"><div class="record_name_setting"></div>录制文件名设置</li>
                    </ul>
                </div>
            </div>
            <div class="win520_content" style="background: none;border:none">
                <div id="opts">
                    <div class="set_bg" style="height: 220px;">
                        <div style="width: 100%;float: left;height: 40px">
                        <%
                            List<Logo> logoList = (List<Logo>)request.getAttribute("logolist");
                            String usingLogoSite = "";
                            if(logoList != null && logoList.size() > 0)
                            {
                                for(int i=0;i<logoList.size();i++) {
                                    if(logoList.get(i).getLogoUsing()==1)
                                    {
                                        usingLogoSite=logoList.get(i).getLogoSite();
                                    }
                                }
                            }
                        %>
                        <div class="radio" id="radio0">
                            <input type="radio" name="flag" value="0" <%=!usingLogoSite.equals("")?"checked":""%>/>
                            <input type="radio" name="flag" value="1" <%=usingLogoSite.equals("")?"checked":""%>/>
                        </div>
                        <div class="allr" id="all0">
                            <div class="headr" style="margin-left: 0;">
                                <span class="<%=!usingLogoSite.equals("")?"bgred":"bgr"%>"></span>使用台标
                            </div>
                            <div class="headr">
                                <span class="<%=usingLogoSite.equals("")?"bgred":"bgr"%>"></span>禁用台标
                            </div>
                        </div>
                        </div>
                        <div class="set_logo_add">
                            <span class="set_logo_addjia">
                                <span class="set_logo_addjia_text">添加台标</span>
                            </span>
                            <input type="file" id="myFile" name="myFile" accept="image/png" style="display: none;">
                        </div>
                            <%--<progress id="progressBar" value="0" max="100">--%>
                            <%--</progress>--%>
                            <%--<span id="percentage"></span>--%>
                        <%


                            if(logoList != null && logoList.size() > 0)
                            {
                        %>
                            <div class="set_logo_text">使用过的台标</div>
                            <div class="set_logo_main">
                        <%

                                int size = logoList.size()>4?4:logoList.size();
                                for(int i=0;i<size;i++)
                                {
                                    String path ="";
                                    if(logoList.get(i).getLogoName() !=null && !logoList.get(i).getLogoName().equals(""))
                                    {
                                        path = request.getContextPath() +"/upload/" + logoList.get(i).getLogoName();
                                    }
                        %>
                            <div id="<%=logoList.get(i).getLogoId()%>" class="<%=logoList.get(i).getLogoUsing()==1?"set_logo_mainlogo_selected":"set_logo_mainlogo"%>">
                                <span class="xk_video_selected_bz"  style="display: <%=logoList.get(i).getLogoUsing()==1?"block":"none"%>"></span><img  src="<%=path%>" />

                            </div>
                            <%
                                }
                            %>
                        </div>
                        <%
                            }
                        %>
                        <div class="set_logocover">
                            <div class="set_logo_text" style="text-indent: 0px;">当前预览台标</div>
                            <div class="set_logo_maincover">
                                <div class="set_logo_cover">
                                    <img id="img_logocover" src="">
                                </div>
                            </div>
                        </div>
                        <div class="set_logo_position">
                            <div class="set_vv_line">
                            <span class="set_vv_linetext" style="width: 65px;font-size:14px;text-align: left;height: 18px;line-height: 18px;">台标位置:</span>

                            <div class="radio" id="radio1">
                                <input type="radio" name="site" value="1" <%=usingLogoSite.equals("1")?"checked":""%>/>
                                <input type="radio" name="site" value="2" <%=usingLogoSite.equals("2")?"checked":""%>/>
                                <input type="radio" name="site" value="3" <%=usingLogoSite.equals("3")?"checked":""%>/>
                                <input type="radio" name="site" value="4" <%=usingLogoSite.equals("4")?"checked":""%>/>
                            </div>
                            <div class="allr" id="all1">
                                <div class="headr" style="margin-left: 0;">
                                    <span class="<%=usingLogoSite.equals("1")?"bgred":"bgr"%>"></span>左上
                                </div>
                                <div class="headr">
                                    <span class="<%=usingLogoSite.equals("2")?"bgred":"bgr"%>"></span>右上
                                </div>
                                <div class="headr">
                                    <span class="<%=usingLogoSite.equals("3")?"bgred":"bgr"%>"></span>左下
                                </div>
                                <div class="headr">
                                    <span class="<%=usingLogoSite.equals("4")?"bgred":"bgr"%>"></span>右下
                                </div>
                            </div>
                            <div style="float: left;width:100%;line-height: 18px;margin-top: 15px;font-size: 12px;color: red">台标大小为16*16-128*128的png格式图片</div>
                        </div>
                    </div>
                    </div>
                </div>
                <div class="set_vv_foot">
                    <div class="set_vv_save" onclick="save()">保存</div>
                </div>
            </div>
        </div>
	    <div class="foot" >
			<jsp:include page="/pages/frontend/footer.jsp"></jsp:include>
		</div>
</div>

</body>
</html>
<script>
    $(function () {
        //使用台标
        var flag=$("input[name=flag]").eq(0).prop("checked");
        if(flag==true)
        {
            $("#myFile").removeAttr("disabled");
        }
        //禁用台标
        var flag=$("input[name=flag]").eq(1).prop("checked");
        if(flag==true)
        {
            $("#myFile").attr("disabled","disabled");
            $(".set_logo_mainlogo_selected").addClass("set_logo_mainlogo").removeClass("set_logo_mainlogo_selected");
            $(".xk_video_selected_bz").css('display','none');
            $("#all1 .headr").find(".bgred").addClass("bgr").removeClass("bgred");
            $("#all1").css("color","#ccc")
            $(".set_logo_addjia_text").css("color","#ccc")
            $("#all1").parents(".set_vv_line").children(".set_vv_linetext").css("color","#ccc")
            $(".set_logo_add").css("background","rgba(0,0,0,0.01)")
        }
        $(".set_logo_main div").click(function(){
            if($(this).attr("class")=="set_logo_mainlogo_selected"){//单机选中图标禁用台标
	    		$(this).find(".xk_video_selected_bz").hide();
	    		$("#all0 div").eq(1).click();
	    		$(this).addClass("set_logo_mainlogo").removeClass("set_logo_mainlogo_selected");

            }else{
            	$(".set_logo_mainlogo_selected").addClass("set_logo_mainlogo").removeClass("set_logo_mainlogo_selected");
	            $(".xk_video_selected_bz").css('display','none');
	            $(this).addClass("set_logo_mainlogo_selected").removeClass("set_logo_mainlogo");
	            $(this).find(".xk_video_selected_bz").css('display','block');
                var path=$(this).find(".xk_video_selected_bz").value
                $("#img_logocover").attr("src",path);
	            $("#all0 div").eq(0).click()

            }
           
        });
        $("input[type=radio]").css("display","none");
        var checklen=$("#all1 .headr").length;
        $("#all1 .headr").click(function(){
            var index=$(this).index();
            var flag=$("input[name=site]").eq(index).prop("checked");
            //alert(flag);
            if($("input[name=flag]").eq(0).prop("checked") == true)//如果是使用台标状态
            {
                if(flag==false){
                    $("input[name=site]").eq(index).prop("checked",true);
                    for(j=0;j<checklen;j++){
                        if(j==index){
                            $("#all1 .headr").eq(j).find(".bgr").addClass("bgred").removeClass("bgr");
                        }else{
                            $("#all1 .headr").eq(j).find(".bgred").addClass("bgr").removeClass("bgred");
                        }
                    }
                }
            }
        });

        var checklen0=$("#all0 .headr").length;
        $("#all0 .headr").click(function(){
            var index=$(this).index();
            var flag=$("input[name=flag]").eq(index).prop("checked");
            if(flag==false){
                $("input[name=flag]").eq(index).prop("checked",true);
                for(j=0;j<checklen0;j++){
                    if(j==index){
                        $("#all0 .headr").eq(j).find(".bgr").addClass("bgred").removeClass("bgr");
                    }else{
                        $("#all0 .headr").eq(j).find(".bgred").addClass("bgr").removeClass("bgred");
                    }
                }
                //使用台标
                if(index == 0)
                {
                    $("#myFile").removeAttr("disabled");
                    $("#all1").css("color","#212121")
                    $("#all1").parents(".set_vv_line").children(".set_vv_linetext").css("color","#212121")
                    $(".set_logo_addjia_text").css("color","#212121")
                    $(".set_logo_add").css("background","#fff")
                }
                //禁用台标
                else if(index ==1)
                {
                    $("#myFile").attr("disabled","disabled");
                    $(".set_logo_mainlogo_selected").addClass("set_logo_mainlogo").removeClass("set_logo_mainlogo_selected");
                    $(".xk_video_selected_bz").css('display','none');
                    $("#all1 .headr").find(".bgred").addClass("bgr").removeClass("bgred");
                    $("input[name=site]").prop("checked",false);
                    $("#all1").css("color","#ccc")
                    $(".set_logo_addjia_text").css("color","#ccc")
                    $("#all1").parents(".set_vv_line").children(".set_vv_linetext").css("color","#ccc")
                    $(".set_logo_add").css("background","rgba(0,0,0,0.01)")
                }
            }
        });

        $(".set_vv_save").mouseover(function(){
            $(this).css("background","#5ec699");
        }).mouseout(function(){
            $(this).css("background","#28b779");
        })
        $(".set_logo_addjia").click(function(){
           $("#myFile").click();
        });
       
    })
</script>
<script>
    //用户选择文件后随即上传至服务器
    var xhr;
    $("#myFile").change(function(){
        var form = new FormData();
        var fileObj = document.getElementById("myFile").files[0]; // js 获取文件对象
        if(fileObj!=undefined && fileObj != null)
        {
            var filename = $("#myFile").val();
            if(!/.(png|PNG)$/.test(filename)){
                layer.msg("图片类型必须是png");
                return;
            }
            var filesize = fileObj.size / 1024 ;
            if(filesize>1024) {
                layer.msg('请上传大小小于1M的图片');
                return;
            }
            // 文件对象
            form.append("file", fileObj);
        }
        var FileController =
                "${pageContext.request.contextPath}/settings/Settings_uploadLogoToServer.do";
        // XMLHttpRequest 对象
        xhr = new XMLHttpRequest();
        xhr.open("post", FileController, true);
        xhr.onload = function () {
            // alert("上传完成!");
        };
        xhr.onreadystatechange = callback;
        xhr.send(form);
    });
    function callback()
    {
        if(xhr.readyState == 4 && xhr.status == 200)
        {
            var data = xhr.responseText;
            var json = eval("("+data+")");
            if(json.success == "true"){
                if(json.width >= 16 && json.height >= 16 && json.width <= 128 && json.height <= 128)
                {
                    $("#img_logocover").attr("src","${pageContext.request.contextPath}"+"/upload/"+json.msg);
                    $(".set_logocover").show();
                }
                else
                {
                    layer.msg('图片宽高不符合要求');
                }
            }
        }
    }
    //添加保存按钮鼠标经过事件
    function save() {
        var flag = $("input[name='flag']:checked").val();
        var logoid = $(".set_logo_mainlogo_selected").attr("id");
        var site = $("input[name='site']:checked").val();
        var filepath = $("#img_logocover").attr("src").split("/");
        var filename = filepath[filepath.length-1];
        if (flag == "0") {
            if (filename == "") {
                if (!logoid) {
                    layer.msg('请选择台标');
                    return;
                }
            }
            if (!site || site == undefined) {
                layer.msg('请选择台标位置');
                return;
            }
        }
        $.post("${pageContext.request.contextPath}/settings/Settings_uploadLogo.do",
                {
                    fileName:filename,
                    logoFlag: flag,
                    logoId: logoid,
                    logoSite: site
                },
                function (data) {
                    if (data.success == "true") {
                        layer.msg(data.msg);
                        setTimeout(function () {
                            location.href = "${pageContext.request.contextPath}/settings/Settings_logoList.do";
                        }, 2000);
                    } else {
                        layer.msg(data.msg);
                    }
                }, 'json');
    }

</script>