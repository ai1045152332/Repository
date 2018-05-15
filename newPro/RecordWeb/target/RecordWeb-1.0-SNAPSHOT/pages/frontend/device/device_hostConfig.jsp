<%--
  Created by IntelliJ IDEA.
  User: chby
  Date: 2014/10/24
  Time: 8:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="${pageContext.request.contextPath}/css/frontend/index.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/js/common/jquery-1.8.0.min.js"></script>
<script src="${pageContext.request.contextPath}/js/common/jquery.cookie.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/common/autoheight.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frontend/device/group.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
<script src="${pageContext.request.contextPath}/js/common/perfect-scrollbar.with-mousewheel.min.js"></script>
<script src="${pageContext.request.contextPath}/js/common/prettify.js"></script>
<script src="${pageContext.request.contextPath}/js/frontend/device/machinemanage.js"></script>
<script src="${pageContext.request.contextPath}/js/common/checkbox.js"></script>
<%

    response.setHeader("Cache-Control","no-store");

    response.setHeader("Pragrma","no-cache");

    response.setDateHeader("Expires",0);

%>
<div class="win520">
    <%--<div class="win_head">
        <div class="win_head_text">NVR设置</div>
        <div class="win_close"></div>
    </div>--%>
    <div class="win520_content">
        <div class="tabs">
            <ul>
                <li class="t_selected" style="border-left:5px solid #28b779 ;">设备参数</li>
                <li>网络设置</li>
            </ul>
        </div>
        <div class="tab_content c_selected" id="content0">
            <div class="tab_content_list" style="margin-top: 25px;">
                <span class="tab_content_listtext">设备名称&nbsp;&nbsp;</span>
                <input type="text" class="tab_content_listinput" />
            </div>
            <div class="tab_content_list">
                <span class="tab_content_listtext_gray">设备型号：</span>
                <span class="tab_content_listtext_black">ALS0320</span>
            </div>
            <div class="tab_content_list">
                <span class="tab_content_listtext_gray">设备序列号：</span>
                <span class="tab_content_listtext_black">00000-0000</span>
            </div>
            <div class="tab_content_list">
                <span class="tab_content_listtext_gray">硬件版本：</span>
                <span class="tab_content_listtext_black">0.1</span>
            </div>
            <div class="tab_content_list">
                <span class="tab_content_listtext_gray">软件版本：</span>
                <span class="tab_content_listtext_black">0.1</span>
            </div>
        </div>
        <div class="tab_content " id="content1">
            <div class="tab_content_list" style="margin-top: 25px;">
                <div class="checkbox">
                    <input type="checkbox" />
                </div>
                <div class="all">
                    <div class="head" style="margin-left: 130px;">
                        <div class="bg" style="margin-top: 0;"></div>自动获取IP
                    </div>
                </div>
            </div>
            <div class="tab_content_list">
                <span class="tab_content_listtext">IP地址</span>
                <input type="text" class="tab_content_listinput" />
            </div>
            <div class="tab_content_list">
                <span class="tab_content_listtext">子网掩码</span>
                <input type="text" class="tab_content_listinput" />
            </div>
            <div class="tab_content_list">
                <span class="tab_content_listtext">网关</span>
                <input type="text" class="tab_content_listinput" />
            </div>
            <div class="tab_content_list">
                <span class="tab_content_listtext">主要DNS</span>
                <input type="text" class="tab_content_listinput" />
            </div>
            <div class="tab_content_list">
                <span class="tab_content_listtext">次要DNS</span>
                <input type="text" class="tab_content_listinput" />
            </div>
        </div>
        <script>

            $(function(){
                var win520height=$(".win520").height();
                var win520width=$(".win520").width();
                //  alert(win520height)
                parent.$(".xubox_main").find(".xubox_iframe").css({"width":win520width+"px","height":win520height+"px"});
                parent.$(".xubox_main").css({"width":win520width+"px","margin-left":"-120px","margin-top":-win520height/2+"px"});

                $(".t_selected").attr("style","border-left:5px solid #28b779");
                $(".tabs li").click(function(){
                    var index = $(this).index();
                    // alert(index);
                    $(this).addClass("t_selected").css("border-left","5px solid #28b779").siblings().removeClass("t_selected").css("border-left","5px solid #fff");
                    $("#content"+$(this).index()).addClass("c_selected").siblings().removeClass("c_selected");
                })

            })
        </script>

    </div>
    <div class="win_btn" style="margin: 20px 20px 20px 0;">
        <a href="javascript:void(0)"><div class="win_btn_sure" style="margin-right: 20px;">确定</div></a>
        <a href="javascript:void(0)"><div class="win_btn_done">取消</div></a>
    </div>

</div>
<script>
    $(".win_btn_sure").click(function(){
        layer.msg("保存成功");
    });
    $(".win_btn_done").click(function(){
        window.parent.layer.closeAll();
    });
</script>