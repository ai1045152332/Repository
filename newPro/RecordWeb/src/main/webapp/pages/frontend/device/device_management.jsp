<%@ page import="java.util.List" %>
<%@ page import="com.honghe.recordweb.util.ConfigureUtil" %>
<%@ page import="com.honghe.recordweb.action.SessionManager" %>
<%@ page import="com.honghe.recordweb.util.CommonName" %>
<%@ page import="com.honghe.recordweb.util.DeviceNameUtil" %>
<%@ page import="com.honghe.recordweb.util.LicenseUtils" %>
<%@ page import="java.util.Map" %>


<%--
  Created by IntelliJ IDEA.
  User: chby
  Date: 2014/10/9
  Time: 8:51
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
    <title>设备管理 | 集控平台</title>
    <link href="${pageContext.request.contextPath}/css/frontend/hpager.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/index.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/index.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/jquery.cookie.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/common/screendetail.js"></script>
    <link href="${pageContext.request.contextPath}/css/frontend/jquery.mCustomScrollbar.min.css" rel="stylesheet"
          type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/frontend/jquery.mCustomScrollbar.concat.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
    <%--<script src="${pageContext.request.contextPath}/js/common/tree.js"></script>--%>
    <script src="${pageContext.request.contextPath}/js/common/scrolldemo.js"></script>
    <style>
        .demotext {
            background: url(${pageContext.request.contextPath}/js/common/layer/skin/default/xubox_loading2.gif) left 10px no-repeat;
            color: #fff;
            width: 200px;
            height: 100px;
            line-height: 50px;
            position: absolute;
            padding-left: 40px;
            left: 0;
            top: 0;
            bottom: 0;
            right: 0;
            margin: auto;
        }

        .add_video_bigim {
            height: 90px;
            width: 120px;
            display: none;
            margin: 10px auto 0 auto;
        }
        .add_video_bigim object{
            margin:0;
            padding:0;
        }
        /*修改对表头文字位置的调整，误删*/
        .public_nav ul li a {
            text-align: center;
        }

        /*.demo {*/
        /*background: black;*/
        /*display: none;*/
        /*filter: alpha(opacity=70); *//*支持 IE 浏览器*/
        /*-moz-opacity: 0.70; *//*支持 FireFox 浏览器*/
        /*opacity: 0.70; *//*支持 Chrome, Opera, Safari 等浏览器*/
        /*left: 0;*/
        /*position: absolute;*/
        /*top: 0;*/
        /*z-index: 10;*/
        /*}*/
        .demo, .demo2 {
            background: rgba(0, 0, 0, 0.7);
            display: none;
            left: 0;
            position: absolute;
            top: 0;
            z-index: 10;
        }

        #demo2 {
            box-shadow: 0 0 1px 0 #ccc;
            height: 0px;
            float: left;
            position: relative;
            overflow: hidden;
            width: 100%;
        }

        .close {
            background: #fff;
            display: block;
            height: 100px;
            position: absolute;
            right: 0;
            top: 0;
            width: 100px;
            z-index: 2;
        }

    </style>

</head>

<body>
<%--<input type="hidden" id="whichscroll">--%>
<%
    //    int pageCount = Integer.parseInt(request.getAttribute("pageCount").toString());
//    int currentPage = Integer.parseInt(request.getAttribute("currentPage").toString());
//    Pager pager = new Pager(pageCount, currentPage, "<span class='pages_prevpage'></span>", "<span class='pages_nextpage'></span>", "", "", false);
//    String pagers = pager.run();
    List<Object[]> hostgrouplist = (List<Object[]>) request.getAttribute("hglist");
    List<Object[]> deviceConfigList_jp = (List<Object[]>) request.getAttribute("deviceConfig_jp");
    List<Object[]> deviceConfigList_fbs = (List<Object[]>) request.getAttribute("deviceConfig_fbs");
    List<Object[]> deviceConfigList_1u = (List<Object[]>) request.getAttribute("deviceConfig_1u");
    String discoverDevice = (String) request.getAttribute("discoverDevice");
    String device_type = SessionManager.get(request.getSession(), SessionManager.Key.DeviceType);
%>

<div class="public">
    <jsp:include page="/pages/frontend/equipment_header.jsp"></jsp:include>
    <div class="mm">
        <div class="mm_head">
            <span title="<%=hostgrouplist.size()%>个分组"
                  class="mm_head_option mm_head_group"
                  style="width:auto"><%=hostgrouplist.size()%>个分组
            </span>

            <%
                if (request.getAttribute("sumNumberHHTCHost") != null && !request.getAttribute("sumNumberHHTCHost").toString().equals("0")) {

            %>
            <a href="${pageContext.request.contextPath}/host/Host_getDeviceBytype.do?type=hhtc">
                <span title="<%=request.getAttribute("sumNumberHHTCHost")%>个大屏设备"
                      class="mm_head_option mm_head_record"><%=request.getAttribute("sumNumberHHTCHost")%>个大屏设备
                </span>
            </a>
            <%}%>

            <%
                if (request.getAttribute("sumNumberHrecHost") != null && !request.getAttribute("sumNumberHrecHost").toString().equals("0")) {

            %>
            <a href="${pageContext.request.contextPath}/host/Host_getDeviceBytype.do?type=hhrec">
                <span title="<%=request.getAttribute("sumNumberHrecHost")%>个录播设备"
                      class="mm_head_option mm_head_user"><%=request.getAttribute("sumNumberHrecHost")%>个录播设备
                </span>
            </a>
            <%}%>

            <%
                if (request.getAttribute("sumNumberHTPrHost") != null && !request.getAttribute("sumNumberHTPrHost").toString().equals("0")) {

            %>
            <a href="${pageContext.request.contextPath}/host/Host_getDeviceBytype.do?type=htpr">
                <span title="<%=request.getAttribute("sumNumberHTPrHost")%>个投影仪设备"
                      class="mm_head_option mm_head_htp"><%=request.getAttribute("sumNumberHTPrHost")%>个投影仪设备
                </span>
            </a>
            <%
                }
                if (request.getAttribute("sumNumberHhtwbHost") != null && !request.getAttribute("sumNumberHhtwbHost").toString().equals("0")) {
            %>
            <a href="${pageContext.request.contextPath}/host/Host_getDeviceBytype.do?type=hhtwb">
                <span title="<%=request.getAttribute("sumNumberHhtwbHost")%>个白板一体机设备"
                      class="mm_head_option mm_head_hhtwb"><%=request.getAttribute("sumNumberHhtwbHost")%>个白板一体机设备
                </span>
            </a>
            <%}%>
            <%
                if (request.getAttribute("sumNumberUnknownHost") != null && !request.getAttribute("sumNumberUnknownHost").toString().equals("0")) {
            %>
            <a href="${pageContext.request.contextPath}/host/Host_getDeviceBytype.do?type=other">
                <span title="<%=request.getAttribute("sumNumberUnknownHost")%>个其它设备"
                      class="mm_head_option mm_head_other"><%=request.getAttribute("sumNumberUnknownHost")%>个其它设备
                </span>
            </a>
            <%}%>
            <a href="${pageContext.request.contextPath}/host/Host_hostManagement.do">
                <div class="mm_head_nummachine"><%=request.getAttribute("sumNumberDeviceNoRelation")%>个未分组班级
                </div>
            </a>
            <a href="javascript:addNewHost()" style="float:right;margin-right: 20px" class="refreshpage">
                <span class="mm_nogroup_option"> <span class="mmn_refrashauto"></span>手动添加</span>
            </a>
            <%--<a href="javascript:location.reload()" style="float:right;margin-right: 20px"--%>
            <%--class="refreshpage"><span class="mm_nogroup_option"><span class="mmn_refrashauto"></span>自动添加</span></a>--%>
            <a href="javascript:location.reload()" style="float:right;margin-right: 20px" class="refreshpage">
                <span class="mm_nogroup_option"><span class="mmn_refrash"></span>设备刷新</span>
            </a>
            <%--<a href="${pageContext.request.contextPath}/host/Host_batchSettingInfo.do">--%>
            <%--<div class="mm_head_order">批量设置--%>
            <%--</div>--%>
            <%--</a>--%>
        </div>
        <div class="mm_right">
            <div class="scrollfather" id="mm_right_video" style="width: 100%;overflow: hidden;float: left;">
                <div class="scrollson" style="min-height: 680px;position:relative">
                    <%

                        for (Object[] obj : hostgrouplist) {
                            String hostNum = obj[3].toString();
                            String hostgroupName = "";
                            if (obj[1] != null && !obj[1].equals("")) {
                                hostgroupName = obj[1].toString().trim();

                            }
                    %>
                    <div class="mm_listout">
                        <a href="javascript:jumpHost('<%=obj[0]%>','<%=hostgroupName%>')">
                            <div class="mm_list" style="background:white;margin: 0;">
                                <div class="mm_list_group">
                                    <div class="mm_list_logomain"></div>
                                    <div class="mm_list_textdevice" title="<%=obj[1]%>"><%=obj[1]%>
                                    </div>
                                    <div class="mm_list_text" style="font-size: 14px;"><%=hostNum%>个班级</div>
                                </div>

                            </div>
                        </a>
                        <%
                            //添加区域版权限判断
                            Map<String,String> license = LicenseUtils.findLicense();
                            Integer licenseNum = 0;
                            if(null!=license&&!license.isEmpty()){
                                licenseNum = Integer.parseInt(license.get("hhtrec_device_num"));
                            }
                            int hostCount = LicenseUtils.HHTREC_DEVICE_MAXNUM;
                            if (licenseNum < hostCount) {
                                if(ConfigureUtil.isHhrec()){
                                    if(obj[4].toString() !=null && !"".equals(obj[4].toString())){
                            %>
                            <div class="mm_list_celier" onclick="timeploy()" style="cursor:default;"><%=obj[4].toString()%></div>
                            <%
                            }else{
                            %>
                            <div class="mm_list_zanwu" onclick="timeploy()" style="cursor:default;">暂无作息策略</div>
                            <%
                                    }
                                }
                            }
                        %>

                        <div class="mm_list_option" hostgroupId="<%=obj[0]%>" hostgroupName="<%=obj[1]%>">
                            <div class="mm_list_options mm_list_edit" title="修改名称"></div>
                            <div class="mm_list_options mm_list_add" title="分配班级"></div>
                            <div class="mm_list_options mm_list_del" title="删除" hostNumber="<%=hostNum%>"
                                 style="float: right;"></div>
                        </div>
                    </div>
                    <%
                        }
                    %>
                    <a href="javascript:void(0)" class="newHostgroup">
                        <div class="mm_add">
                            <div class="mm_add_logo"><span class="mm_add_text">新建分组</span></div>
                        </div>
                    </a>
                    <%--<div style="margin-left: 300px;margin-top: 10px" id="linkpage">--%>
                    <%--<%=pagers%>--%>
                    <%--</div>--%>
                </div>
                <div class="scroll_ymove">
                    <div class="scroll_y" unorbind="unbind"></div>
                </div>
                <div class="scroll_xmove">
                    <div class="scroll_x" unorbind="unbind"></div>
                </div>


            </div>
        </div>

        <script>
            //            function change_size() {
            //                $("#mm_right_video").width($(".mm_right").width()).height($(".mm_right").height());
            //                $('#mm_right_video').perfectScrollbar('update');
            //            }
            $(function () {
                var height = $(".mm_right").height()
                $("#mm_right_video").height(height)
                $("#mm_right_video").width($(".mm_right").width())
//                $('#mm_right_video').perfectScrollbar();
//                prettyPrint();
            });
        </script>

    </div>

    <input type="text" style="display: none" id="hostgroupSelected" value="" class="test"
           urlhead="${pageContext.request.contextPath}"/>

    <div class="foot">
        <jsp:include page="/pages/frontend/footer.jsp"></jsp:include>
    </div>
    <%-- <jsp:include page="../footer.jsp"></jsp:include>--%>


</div>

<div class="demo">
    <div style="color:white;font-size: 15px" class="demotext">发现<%=discoverDevice%>个设备正在进行配置</div>
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
    <div class="win800" style="height:250px;position: absolute;left: 0;right:0;top:0;bottom:0;margin:auto">
        <div class="win_head">
            <div class="win_head_text">手动添加</div>
            <div class="win_close"></div>
        </div>
        <div class="win_content" style="width: 100%;margin-left: 0;">
            <div class="set_vv_line">
                <span class="set_vv_linetext">设备类型</span>

                <div class="sall" style="margin-left: 10px">
                    <select class="select" id="select0">
                        <option value="请选择" style="display: none">请选择</option>
                        <%

                            if (ConfigureUtil.isHhrec()) {
                        %>
                        <option value="<%=DeviceNameUtil.DEVICE_NORMALIZATION_RECOURD%>">常态化录播主机
                        </option>
						<%--添加wbox--%>
                        <option value="<%=DeviceNameUtil.DEVICE_WBOX_RECOURD%>">常态化录播主机(ZF0100)</option>
                        <option value="<%=DeviceNameUtil.DEVICE_FINE_RECOURD%>">服务器架构精品</option>
                        <option value="<%=DeviceNameUtil.DEVICE_EMBEDDED_RECOURD%>">嵌入式精品录播主机(ZJ0500)</option>
                        <option value="<%=DeviceNameUtil.DEVICE_CLASSROOMMONITOR%>">教室监控</option>
                        <option value="<%=DeviceNameUtil.DEVICE_TBOX_RECOURD%>">TBOX</option>
                        
                        <%
                            }
                            if (ConfigureUtil.isHhtc()) {
                        %>
                        <option value="<%=DeviceNameUtil.DEVIC_SCREEN%>">大屏</option>
                        <%
                            }
                            if (ConfigureUtil.isHhtwb()) {
                        %>
                        <option value="<%=DeviceNameUtil.DEVICE_WHITEBOARD%>">白板一体机</option>
                        <%
                            }
                            if (ConfigureUtil.isHtpr()) {
                        %>
                        <option value="<%=DeviceNameUtil.DEVICE_PROJECTOR%>">投影机</option>
                        <%
                            }
                        %>

                    </select>
                    <style>
                        #selectdivall0 {
                            background: url(${pageContext.request.contextPath}/image/frontend/shoudongxiugaiselect.png) 0px 0px no-repeat;
                            width: 230px;
                        }

                        #selectdiv0, #selectdivul0 {
                            overflow: hidden;
                            width: 230px;
                        }
                        .selectdivul{
                            border: 1px solid #ccc;
                            border-top: 0;
                        }
                        #selectdivul0 a {
                            overflow: hidden;
                            height: 28px;
                            width: 230px;
                        }

                        .mCustomScrollbar {
                            width: 86%;
                            margin: 0 auto;
                            max-height: 200px;
                        }
                    </style>

                    <div class="selectdivall" id="selectdivall0">
                        <div class="selectdiv" id="selectdiv0" style="line-height: 30px;"></div>
                        <div class="selectdivul" id="selectdivul0"
                             style="top:-8px;width: 230px; overflow-y: hidden;"></div>
                    </div>
                </div>

            </div>
            <div class="set_vv_line">
                <span class="set_vv_linetext">班级IP</span>
                <input class="set_vv_linesinput" id="ip" type="text">
            </div>
            <div class="set_vv_line">
                <span class="set_vv_linetext">班级名称</span>
                <input class="set_vv_linesinput" id="name" type="text" >
            </div>
            <%--  <div class="set_vv_line">

              </div>--%>
            <div class="set_vv_line_f">
                <div class="mCustomScrollbar">
                    <input type="hidden" class="add_list_ki"/>
                    <%--<div class="list_add_line_f_wq">
                        <div class="list_add_line_f">
                            <div class="list_add_t">
                                <span class="list_add_span">IPC:镜头名称</span>
                                <input class="add_linesinput" type="text">
                                <span  class="list_add_span">媒体流地址</span>
                                <input class="add_linesinput" type="text">
                            </div>
                            <span class="preview_spa_c">预览</span>
                            <span class="close_f_line"></span>
                        </div>
                    </div>--%>
                </div>
                <div class="add_list_tas">
                    <span>添加通道</span>
                </div>
            </div>
            <%--<div class="set_vv_line" style="width: 100%;" >--%>
            <%--<span class="set_vv_linetext" style="color: #28B779;" id="wwset">外网设置</span>--%>
            <%--</div>--%>
            <div class="scrollfather" id="demo2">
                <div class="scrollson">
                    <div class="set_vv_line" style="width: 100%;">
                        <div id="jinttouclick"
                             style="cursor:pointer;color: #fff;background: #28B779;border-radius: 5px;height: 26px;width: 75px;line-height: 26px;text-align: center;margin-left: 63px;">
                            镜头添加
                        </div>
                    </div>
                    <div id="addjingtou" style="float: left;width: 100%;"></div>
                    <div class="set_vv_line">
                        <span class="set_vv_linetext">设备访问</span>
                        <input class="set_vv_linesinput" id="sbfw" type="text" value="">
                    </div>

                    <div class="set_vv_line">
                        <span class="set_vv_linetext">事件监听</span>
                        <input class="set_vv_linesinput" id="sjjt" type="text" value="">
                    </div>
                    <div class="set_vv_line">
                        <span class="set_vv_linetext">媒体访问</span>
                        <input class="set_vv_linesinput" id="mtfw" type="text" value="">
                    </div>
                    <div class="set_vv_line">
                        <span class="set_vv_linetext">云台控制</span>
                        <input class="set_vv_linesinput" id="ytkz" type="text" value="">
                    </div>
                    <div class="set_vv_line">
                        <span class="set_vv_linetext">导播控制</span>
                        <input class="set_vv_linesinput" id="dbkz" type="text" value="">
                    </div>

                </div>
                <div class="scroll_ymove">
                    <div class="scroll_y" unorbind="unbind"></div>
                </div>
                <div class="scroll_xmove">
                    <div class="scroll_x" unorbind="unbind"></div>
                </div>
            </div>
            <div class="win_btn" style="margin: 20px 20px 20px 0;">
                <div class="win_btn_sure" style="margin-right: 20px;" onclick="javascript:manualAddHost()">确定</div>
                <div class="win_btn_done">取消</div>
            </div>
        </div>

    </div>
</div>
<input type="hidden" id="deviceAddHidden" value=""/>

</body>
</html>
<script>
    var flag = 0;//0:手动添加,1:手动添加外网设备
    changeselectelse(0)

    //jquery模拟select
    function changeselectelse(args) {
        var sdefault = $("#select" + args).find("option:selected");//获取默认选中值
        $("#selectdiv" + args).text(sdefault.text())//赋值给模拟div的text
        $("#selectdiv" + args).attr("main", sdefault.attr("main"))
        $("#selectdiv" + args).attr("sub", sdefault.attr("sub"))

        var optlen = $("#select" + args).find("option").length;//获取option的长度
        for (i = 0; i < optlen; i++) {
            var optval = $("#select" + args).find("option").eq(i).text();//获取每一个select的值
            var optvalmain = $("#select" + args).find("option").eq(i).attr("main");//获取每一个select的主码流
            var optvalsub = $("#select" + args).find("option").eq(i).attr("sub");//获取每一个select的主码流
            var htmlto = "<a href='javascript:void(0)' main=\"" + optvalmain + "\" sub=\"" + optvalsub + "\">" + optval + "</a>";//赋值给a标签
            $("#selectdivul" + args).append(htmlto);//将a标签添加到模拟div
        }
        //点击模拟下拉框，显示或隐藏内容
        $("#selectdiv" + args).click(function () {
           // alert($(".selectdivul").find("a").length)
            $(".selectdivul").find("a").each(function(){
                var a_text = $(this).text();
                if(a_text=="请选择"){
                    $(this).hide();
                }else{
                    $(this).show();
                }

            })
           // alert("ssss")
            $("#selectdivul" + args).css("min-height",($(".selectdivul a").length-1)*28+"px")
            var display = $("#selectdivul" + args).css("display");//判断当前模拟下拉菜单的显示属性

            if (display == "none") {//隐藏，则显示
                //   console.log("#selectdiv" + args + "--------------------" + display + args)
                $("#selectdivul" + args).css("display", "block");
//		$("#selectdivul"+args).find("a").eq(0).css("display","none");
            } else {//显示，则隐藏
                //     console.log("#selectdiv" + args + "============" + display + args)
                $("#selectdivul" + args).css("display", "none");
            }
        })
        $("#selectdivul" + args).find("a").eq(0).css({
            "color": "#fff",
            "background": "#28b779"
        }).siblings().css({"color": "#333", "background": "#fff"});
        //点击模拟a标签，重新选定原生select选项
        $("#selectdivul" + args + " a").click(function () {
            //  console.log("aaaaaaaaaaaaaaaaaaa")
            var index = $(this).index();//获取a标签的索引值
            var text_ind = $(this).text();
            $("#select" + args).find("option").eq(index).prop("selected", "selected");//根据a标签的索引值选择select原生的值
            $("#selectdivul" + args).find("a").eq(index).css({
                "color": "#fff",
                "background": "#28b779"
            }).siblings().css({"color": "#333", "background": "#fff"});
            $("#selectdivul" + args).css("display", "none");//选中模拟值后，隐藏该div
            $("#selectdiv" + args).text($("#select" + args).find("option").eq(index).text());//把选中的值赋值到模拟div默认值选项
            var mainval = $("#selectdivul" + args).find("a").eq(index).attr("main")
            var subval = $("#selectdivul" + args).find("a").eq(index).attr("sub")
            $("#selectdiv" + args).attr("main", mainval)
            $("#selectdiv" + args).attr("sub", subval)


//		alert("a")
            var gettext = $("#select" + args).find("option").eq(index).text();
            //args为0的时候，需要判断大屏设备，禁用班级名称和外网设置
            if (args == 0) {
                if (gettext == "大屏" || gettext == "投影机") {
                    $("#name").val("")
                    $("#name").attr("disabled", "disabled")
                    $("#wwset").parent().hide()
                    $("#demo2").hide()
                    $(".win800").height(283)
                }
                else {
                    $("#name").removeAttr("disabled")
                    $("#wwset").parent().show()
                    //   console.log($("#demo2").css("display"))
                }
                var sdefault = $("#select" + args).find("option:selected");//获取默认选中值
                $("#pagesize").val(sdefault.val());
                var sdefault = $("#select" + args).find("option:selected");//获取默认选中值
                var groupid = $("#group_id").val();
                var pagesize = $("#pagesize").val();
                var root_path = $("#root_path").val();

            }
           // console.log(index)
            if (text_ind == "教室监控") {
                $(".win800").height(500)
                $(".set_vv_line_f").show();
                $(".set_vv_line:eq(2)").hide();
                $(".set_vv_line:eq(1)").find("span").html("班级名称")
            } else {
                $(".win800").height(250)
                $(".set_vv_line_f").hide();
                $(".set_vv_line:eq(1)").find("span").html("班级IP")
                $(".set_vv_line:eq(2)").show();
            }
        })
        $("#selectdivul" + args).find("a").mouseover(function () {
            //alert($(this).css("background-color"))
            if ($(this).css("background-color") == "#fff" || $(this).css("background-color") == "rgb(255, 255, 255)") {
                $(this).css({"background": "#dbdbdb", "color": "#333"})
            } else if ($(this).css("background-color") == "#28b779" || $(this).css("background-color") == "rgb(40, 183, 121)") {
                return false;
            }
        }).mouseout(function () {
            if ($(this).css("background-color") == "#333" || $(this).css("background-color") == "rgb(219, 219, 219)") {
                $(this).css({"background": "#fff", "color": "#333"})
            } else if ($(this).css("background-color") == "#28b779" || $(this).css("background-color") == "rgb(40, 183, 121)") {
                return false;
            }
        })
        //点击空白区域，隐藏select模拟
        $(document).bind("click", function (e) {
            var target = $(e.target);//获取点击时间
            if (target.closest("#selectdivall" + args).length == 0) {
                $("#selectdivul" + args).hide();//隐藏divli
            }
        })


    }
    //demo2
    var exphei = document.documentElement.clientHeight;
    var expwidth = document.documentElement.clientWidth;
    $(".demo2").height(exphei)
    $(".demo2").width(expwidth)


    var urlhead = $("#hostgroupSelected").attr("urlhead");
    var discoverDevice = "<%=discoverDevice%>";
    function timeploy(){
        location.href = '${pageContext.request.contextPath}' +"/settings/Settings_timeploy.do";
    }
    function jumpHost(groupId, groupName) {

        location.href = '${pageContext.request.contextPath}' +
                "/host/Host_loadHostByHostgroup.do?hostgroupId=" + groupId + "&hostgroupName=" + encodeURI(encodeURI(groupName));
//        alert(encodeURI(groupName));
    }
    function editannotatediv(content) {
        var height = $(document).height() + "px";
        var width = $(document).width() + "px";
        $(".demotext").text(content);
        $(".demo").css({"height": height, "width": width, "display": "block"});
    }

    $(function () {
        //处理大屏flag==1或者投影仪flag=2时，界面异常
//        var thisurl = location.href.split("?")[1].split("=");
//        console.log(thisurl[0] + "-----" + thisurl[1])
//        if (thisurl[1] == "1" || thisurl[1] == "2") {
//            $(".mm").css({"float": "left"})
//            $(".mm_right").css({"height": "93%"})
//        }

        if ("<%=device_type%>" != "<%=CommonName.DEVICE_TYPE_RECOURD%>") {
            $(".mm").css({"float": "left"})
            $(".mm_right").css({"height": "93%"})
        }

        //        //点击背景隐藏
//        $(".demo2").click(function(){
//            $(this).hide()
//        })
        //删除镜头
        $(".chapterdel").live("click", function () {
            $(this).parent(".set_vv_line").remove()
        })
        var vipt_videowid = $(".mm_right").width()
        $("#mm_right_video .scrollson").width(vipt_videowid)
        $("#mm_right_video").height($(".mm").height() * 0.93)
        $("#mm_right_video").width($(".mm_right").width())

        $("#mm_right_video .scrollson").mouseover(function () {
            $("#whichscroll").val($.trim($(this).parent().attr("id")))
            if ((navigator.userAgent.match(/(iPhone|Android|iPad)/i))) {
                var scrollfathter1 = document.getElementById($.trim($(this).parent().attr("id")));
                scrollfathter1.addEventListener("touchstart", touchStart, false);
                scrollfathter1.addEventListener("touchmove", touchMove, false);
                scrollfathter1.addEventListener("touchend", touchEnd, false);
            }
        })
        scroll_y("mm_right_video", "scrollson", "scroll_y", "scroll_ymove", "scroll_x", "scroll_xmove", "", "wheely", "")
        $("#mm_right_video .scrollson").css("margin-top", "0")
        $("#mm_right_video .scroll_y").css("top", "0")

        $("#demo2").width(800)
        $("#demo2 .scrollson").mouseover(function () {
            $("#whichscroll").val($.trim($(this).parent().attr("id")))
            if ((navigator.userAgent.match(/(iPhone|Android|iPad)/i))) {
                var scrollfathter2 = document.getElementById($.trim($(this).parent().attr("id")));
                scrollfathter2.addEventListener("touchstart", touchStart, false);
                scrollfathter2.addEventListener("touchmove", touchMove, false);
                scrollfathter2.addEventListener("touchend", touchEnd, false);
            }
        })
        scroll_y("demo2", "scrollson", "scroll_y", "scroll_ymove", "scroll_x", "scroll_xmove", "", "wheely", "")
        $("#demo2 .scrollson").css("margin-top", "0")
        $("#demo2 .scroll_y").css("top", "0")


        $(".mm_add,.mm_list_edit").click(function () {
            setTimeout(function () {
                $(".xubox_dialog").parents(".xubox_main").css({"width": "310px"})
                $(".xubox_form").width(257)
            }, 0)

        })

        if (discoverDevice != 0) {

            editannotatediv("发现" + discoverDevice + "个设备正在进行配置");

            $.get(urlhead + "/host/Host_loadNewHost.do", {}, function (data) {
                if (data.isMuti == true) {
                    setTimeout(function () {
                        $.get(urlhead + "/host/Host_loadNewHost.do", {}, function (innerdata) {
                            if (innerdata.result == false) {
//                                location.reload();
                            }
                        }, "json");
                    }, 3000);
                }
                if (data.result == true) {
                    //if (data.isRefresh == true) {
                    // location.href = urlhead + "/host/Host_hostManagement.do";
                    location.reload();
                    // } else {
                    //   $(".demo").css("display", "none");
                    //}
                }
            }, "json");
        }


        $("#jinttouclick").click(function () {
            var index = 0;
            var vvlinelen = $("#addjingtou").find(".set_vv_line").length + 1;
            var tokenName = $("#select0 option:selected").text();
            //添加镜头
            var jingtouhtml = "<div class=\"set_vv_line\"  style=\"width: 100%;position:relative\">";
            jingtouhtml += "<span class=\"set_vv_linetext\">镜头类型</span>";
            jingtouhtml += "<div class=\"sall\" style=\"margin-left: 10px\">";
            jingtouhtml += "<select class=\"select\" id=\"select" + vvlinelen + "\">";
            if (tokenName == "精品") {
                <% if(deviceConfigList_jp != null && !deviceConfigList_jp.isEmpty())
                {
                    for(int i = 0;i < deviceConfigList_jp.size();i++)
                    {
                %>
                if (index == 0) {
                    jingtouhtml += "<option value=\"" + index + "\" selected=\"selected\" main=\"<%=deviceConfigList_jp.get(i)[2].toString()%>\"  sub=\"<%=deviceConfigList_jp.get(i)[3].toString()%>\"><%=deviceConfigList_jp.get(i)[1].toString()%></option>";
                }
                else {
                    jingtouhtml += "<option value=\"" + index + "\"  main=\"<%=deviceConfigList_jp.get(i)[2].toString()%>\"  sub=\"<%=deviceConfigList_jp.get(i)[3].toString()%>\"><%=deviceConfigList_jp.get(i)[1].toString()%></option>";
                }
                index++;
                <%
                   }
                }
                %>
            }
            else if (tokenName == "分布式") {
                <% if(deviceConfigList_fbs != null && !deviceConfigList_fbs.isEmpty())
                 {
                     int jj = 0;
                     for(int i = 0;i < deviceConfigList_fbs.size();i++)
                     {
                 %>
                if (index == 0) {
                    jingtouhtml += "<option value=\"" + index + "\" selected=\"selected\"  main=\"<%=deviceConfigList_fbs.get(i)[2].toString()%>\"  sub=\"<%=deviceConfigList_fbs.get(i)[3].toString()%>\"><%=deviceConfigList_fbs.get(i)[1].toString()%></option>";
                }
                else {
                    jingtouhtml += "<option value=\"" + index + "\"  main=\"<%=deviceConfigList_fbs.get(i)[2].toString()%>\"  sub=\"<%=deviceConfigList_fbs.get(i)[3].toString()%>\"><%=deviceConfigList_fbs.get(i)[1].toString()%></option>";
                }
                index++;
                <%
                   }
                }
                %>
            }
            else if (tokenName == "精品Arec") {
                <% if(deviceConfigList_1u != null && !deviceConfigList_1u.isEmpty())
                     {
                         int jj = 0;
                         for(int i = 0;i < deviceConfigList_1u.size();i++)
                         {
                     %>
                if (index == 0) {
                    jingtouhtml += "<option value=\"" + index + "\" selected=\"selected\" main=\"<%=deviceConfigList_1u.get(i)[2].toString()%>\"  sub=\"<%=deviceConfigList_1u.get(i)[3].toString()%>\"><%=deviceConfigList_1u.get(i)[1].toString()%></option>";
                }
                else {
                    jingtouhtml += "<option value=\"" + index + "\" main=\"<%=deviceConfigList_1u.get(i)[2].toString()%>\"  sub=\"<%=deviceConfigList_1u.get(i)[3].toString()%>\"><%=deviceConfigList_1u.get(i)[1].toString()%></option>";
                }
                index++;
                <%
                   }
                }
                %>
            }
            jingtouhtml += "</select>";
            jingtouhtml += "<div class=\"selectdivall\" id=\"selectdivall" + vvlinelen + "\">";
            jingtouhtml += "<div class=\"selectdiv\" id=\"selectdiv" + vvlinelen + "\" style=\"line-height: 30px;\"></div>";
            jingtouhtml += "<div class=\"selectdivul\" id=\"selectdivul" + vvlinelen + "\" style=\"top:-8px;width: 115px;\"></div>";
            jingtouhtml += "</div>";
            jingtouhtml += "</div>";
            jingtouhtml += "<span class=\"set_vv_linetext\" style=\"width: 40px;\">主码流</span>";
            jingtouhtml += "<input class=\"set_vv_linesinput main_tokenurl\" id=\"vvlineidmain" + vvlinelen + "\"  type=\text\" value=\"\">";
            jingtouhtml += "<span class=\"set_vv_linetext\" style=\"width: 40px;\">子码流</span>";
            jingtouhtml += "<input class=\"set_vv_linesinput sub_tokenurl\" id=\"vvlineidson" + vvlinelen + "\"  type=\"text\" value=\"\">";
            jingtouhtml += "<div class=\"chapterdel\" style=\"display: block;right:40px;top:10px\"></div>"
            jingtouhtml += "</div>";
            $("#addjingtou").append(jingtouhtml)
            var slen = $(".sall").length;//获取当前所属div(分组)长度
            //alert(slen)
            changeselectelse(vvlinelen)//循环调用赋值方法
            scroll_y("demo2", "scrollson", "scroll_y", "scroll_ymove", "scroll_x", "scroll_xmove", "add", "wheely", "")
        })


        //wwset外网设置
        $("#wwset").click(function () {
            $(".win800").height(500)
            $("#demo2").height(217).show()
            flag = 1;
        })
        //隐藏demo2
        $(".win_close").click(function () {
            $(".demo2").hide()
        })
        $(".demo2 .win_btn_done").click(function () {
            $(".demo2").hide()
        })

    });


    $(".mm_list_edit").live("click", function () {
        var selectId = $(this).parent().attr("hostgroupId");
        $("#hostgroupSelected").val(selectId);
        var selectName = $(this).parent().attr("hostgroupName");
        var url = urlhead + "/hostgroup/Hostgroup_updateHostgroup.do";
        group(url, selectId, selectName);
    });

    $(".mm_list_add").live("click", function () {
        // alert("分配教室");
        var selectId = $(this).parent().attr("hostgroupId");
        $.layer({
            type: 2,
            title: '添加班级',
            shadeClose: true,
            maxmin: false,
            fix: false,
            area: ['400px', '405px'],
            iframe: {
                src: urlhead + '/host/Host_assignHost.do?hostgroupId=' + selectId,
                scrolling: 'yes'
            }
        });
    });
    $(".mm_list_del").live("click", function () {
        //alert("删除分组");
        var selectId = $(this).parent().attr("hostgroupId");
        var selectName = $(this).parent().attr("hostgroupName");
        var hostNumber = $(this).attr("hostNumber");
        $.layer({
            shade: [0.5, '#000'],
            area: ['310px', '129px'],
            title: '删除分组',
            dialog: {
                msg: '您确定删除该组吗？',
                btns: 2,
                type: 4,
                btn: ['确定', '取消'],
                yes: function () {
                    //  layer.msg('重要', 1, 1);
                    var delUrl = urlhead + "/hostgroup/Hostgroup_delHostgroup.do?hostNumber=" + hostNumber;
                    $.post(delUrl, {'strGroupIds': selectId}, function (data) {
                        layer.msg(data.msg);
                        if (data.success == true) {
                            window.location.reload();
                        }
                    }, 'json')
                }, no: function () {
                    // layer.msg('奇葩', 1, 13);
                }
            }
        });
    });
    $(".newHostgroup").live("click", function () {
        //alert("新增分组");
        var url = urlhead + "/hostgroup/Hostgroup_addHostgroup.do";
        group(url, 0, '');
    });
    //    $(".mm_left_create").live("click",function(){
    //        var url =urlhead+"/host/Host_addHost.do";
    //        layer.prompt({title: '创建班级：'}, function(name){
    //
    //            $.post(url,{'hostName':name},function(data){
    //                layer.msg(data.msg);
    //                if(data.success==true)
    //                {
    //                    window.location.reload();
    //                }
    //            },'json');
    //
    //        });
    //    });
    $(".mm_head_nummachine").live("click", function () {
        location.href = urlhead + "/host/Host_hostManagement.do";
    });
    function group(turl, tid, tname) {
        layer.prompt({title: '输入分组名称', val: tname, length: 20}, function (name) {
            name = $.trim(name);
            if (name != "") {
                $.post(turl, {'groupId': tid, 'groupName': name}, function (data) {

                    if (data.success == true) {
                        window.location.reload();
                    }
                    else {
                        var val = $(".xubox_title em").text("该分组已存在!");
                        $("#xubox_prompt").val("").focus();
                    }
                }, 'json');
            }
            else {
                name = "";
                //layer.alert("分组名称不能为空","确定");
                var val = $(".xubox_title em").text("分组名称不能为空!");
                $("#xubox_prompt").val("").focus();
            }
        });
    }


    //分页
    function jumpPage(num) {

        var url = window.location.href;
        var arr_param = url.split("?");
        var path = arr_param[0];
        if (arr_param.length > 1)//http:xxxx.do?currentPage=1
        {

            var reg = /^currentPage./;
            var arr = arr_param[1].split("&");//将?后面的参数以&分割
            //alert(arr_param[1]);
            if (reg.test(arr_param[0]))//?后面的参数如果是以currentPage=开始的
            {
                //alert("aa");
                path += "?currentPage=" + num;
                if (arr.length > 1)//如果有两个参数及以上直接在currentPage后加上即可
                {
                    for (var i = 1; i < arr.length; i++) {
                        path += "&" + arr[i];
                    }
                }
            }
            else //?后面的参数如果不是以currentPage=开始的
            {
                path += "?" + arr[0];//第一个参数肯定不是以currentPage开始
                if (arr.length > 1) {
                    for (var i = 1; i < arr.length; i++) {
                        if (!reg.test(arr[i])) {
                            path += "&" + arr[i];
                        }
                        else {
                            path += "&currentPage=" + num;
                        }
                    }
                }
            }
        }
        else {
            path += "?currentPage=" + num;
        }
        window.location.href = path;
    }

    function addNewHost() {
        $(".demo2").show();
    }

    // todo
    function manualAddHost() {
        var ip = $("#ip").attr("value").trim();
        var name = $("#name").attr("value").trim();
        var type = $("#select0").val();
        // 设备类型
        console.log("type---------->" + type);

        if(type == "请选择"){
            layer.msg("请选择所要添加的设备类型");
            return;
        }
        if (type == <%=DeviceNameUtil.DEVICE_CLASSROOMMONITOR%>) {
            name = ip;
        }

        if (ip.length == 0 && type != <%=DeviceNameUtil.DEVICE_CLASSROOMMONITOR%>) {
            layer.msg("班级ip不能为空");
            return;
        }
        if (name.length == 0 && type != <%=DeviceNameUtil.DEVIC_SCREEN%>) {
            layer.msg("班级名称不能为空");
            return;
        }
        if (name.length > 16) {
            layer.msg("班级名称不能超过15个字符");
            return;
        }


        if (type == <%=DeviceNameUtil.DEVICE_CLASSROOMMONITOR%>) {
            var netUrl = "";
            var cameraName = "";
            var length = $("input[name=ipc_name]").length;
            if($("input[name=ipc_name]").length==0){
                layer.msg("请添加通道");
                return;
            }
            for (var i = 0; i < $("input[name=ipc_name]").length; i++) {
                var curName = $("input[name=ipc_name]").eq(i).attr("value").trim();
                if (curName.length == 0) {
                    layer.msg("通道名称不能为空");
                    return;
                }
                cameraName +=  curName+ ",";
                var curUrl =$("input[name=media_stream_url]").eq(i).attr("value").trim();
                if (curUrl.length == 0) {
                    layer.msg("通道地址不能为空");
                    return;
                }
                netUrl +=  curUrl+ ",";
            }

            $(".demo2").css("display", "none");
            editannotatediv("设备正在进行配置");
            $.post(urlhead + '/host/Host_addVirtualHost.do',
                    {
                        'hostIpaddr': ip,
                        'hostName': name,
                        'deviceType': type,
                        'netUrl': netUrl,
                        'cameraName': cameraName
                    },
                    function (data) {
                        if (data.result == 1) {
                            setTimeout(function () {
                                $(".demo").css("display", "none");
                                layer.msg("班级已存在");
                            }, 2000);
                        } else if (data.result == -1) {
                            $(".demo").css("display", "none");
                            layer.msg("班级添加失败");
                        } else if (data.result == -2) {
                            $(".demo").css("display", "none");
                            layer.msg("已超过授权数量限制，添加失败");
                        } else {
                            $(".demo").css("display", "none");
                            layer.msg("班级增加成功");
                            location.reload();
                        }
                    }, 'json');
        }
        else {

            $(".demo2").css("display", "none");
            editannotatediv("设备正在进行配置");
            $.post(urlhead + '/host/Host_addNewHost.do', {
                'hostIpaddr': ip,
                'hostName': name,
                'deviceType': type
            }, function (data) {
                if (data.result == 1) {
                    setTimeout(function () {
                        $(".demo").css("display", "none");
                        layer.msg("班级已存在");
                    }, 2000);
                } else if (data.result == -1) {
                    $(".demo").css("display", "none");
                    layer.msg("班级添加失败");
                } else if (data.result == -2) {
                    $(".demo").css("display", "none");
                    layer.msg("已超过授权数量限制，添加失败");
                } else {
                    $(".demo").css("display", "none");
                    layer.msg("班级增加成功");
                    location.reload();
                }
            }, 'json');

        }


//        if (flag == 0 || type == 3) {
//            $(".demo2").css("display", "none");
//            editannotatediv("设备正在进行配置");
//            $.post(urlhead + '/host/Host_addNewHost.do', {
//                'hostIpaddr': ip,
//                'hostName': name,
//                'deviceType': type
//            }, function (data) {
//                if (data.result == 1) {
//                    setTimeout(function () {
//                        $(".demo").css("display", "none");
//                        layer.msg("班级已存在");
//                    }, 2000);
//                } else if (data.result == -1) {
//                    $(".demo").css("display", "none");
//                    layer.msg("班级添加失败");
//                } else if (data.result == -2) {
//                    $(".demo").css("display", "none");
//                    layer.msg("已超过授权数量限制，添加失败");
//                } else {
//                    $(".demo").css("display", "none");
//                    layer.msg("班级增加成功");
//                    location.reload();
//                }
//            }, 'json');
//        }
//        else {
//            var tokenName = "";
//            var mainTokenName = "";
//            var subTokenName = "";
//            for (var i = 1; i < $(".selectdiv").length; i++) {
//                tokenName += $(".selectdiv").eq(i).text().trim() + ",";
//                mainTokenName += $(".selectdiv").eq(i).attr("main").trim() + ",";
//                subTokenName += $(".selectdiv").eq(i).attr("sub").trim() + ",";
//            }
//            var mainTokenUrl = "";
//            for (var i = 0; i < $(".main_tokenurl").length; i++) {
//                mainTokenUrl += $(".main_tokenurl").eq(i).val().trim() + ",";
//            }
//            var subTokenUrl = "";
//            for (var i = 0; i < $(".sub_tokenurl").length; i++) {
//                subTokenUrl += $(".sub_tokenurl").eq(i).val().trim() + ",";
//            }
//            var sbfw = "";
//            sbfw = $("#sbfw").val().trim();
//            if (sbfw == "") {
//                layer.msg("设备访问不能为空");
//                return;
//            }
//            sbfw = "http://www.onvif.org/ver10/device/wsdl " + sbfw;
//            var sjjt = "";
//            sjjt = $("#sjjt").val().trim();
//            if (sjjt == "") {
//                layer.msg("事件监听不能为空");
//                return;
//            }
//            sjjt = "http://www.onvif.org/ver10/events/wsdl " + sjjt;
//            var mtfw = "";
//            mtfw = $("#mtfw").val().trim();
//            if (mtfw == "") {
//                layer.msg("媒体访问不能为空");
//                return;
//            }
//            mtfw = "http://www.onvif.org/ver10/media/wsdl " + mtfw;
//            var ytkz = "";
//            ytkz = $("#ytkz").val().trim();
//            if (ytkz == "") {
//                layer.msg("云台控制不能为空");
//                return;
//            }
//            ytkz = "http://www.onvif.org/ver20/ptz/wsdl " + ytkz;
//            var dbkz = "";
//            dbkz = $("#dbkz").val().trim();
//            if (dbkz == "") {
//                layer.msg("导播控制不能为空");
//                return;
//            }
//            dbkz = "http://www.honghe-tech.com/spec/hrec/wsdl " + dbkz;
//            var netUrl = sbfw + "," + sjjt + "," + mtfw + "," + ytkz + "," + dbkz;
//            $(".demo2").css("display", "none");
//            editannotatediv("设备正在进行配置");
//            $.post(urlhead + '/host/Host_addNetHost.do',
//                    {
//                        'hostIpaddr': ip,
//                        'hostName': name,
//                        'deviceType': type,
//                        'netUrl': netUrl,
//                        'deviceNames': tokenName,
//                        'mainTokens': mainTokenName,
//                        'subTokens': subTokenName,
//                        'mianTokenStreams': mainTokenUrl,
//                        'subTokenStreams': subTokenUrl
//                    },
//                    function (data) {
//                        if (data.result == 1) {
//                            setTimeout(function () {
//                                $(".demo").css("display", "none");
//                                layer.msg("班级已存在");
//                            }, 2000);
//                        } else if (data.result == -1) {
//                            $(".demo").css("display", "none");
//                            layer.msg("班级添加失败");
//                        } else if (data.result == -2) {
//                            $(".demo").css("display", "none");
//                            layer.msg("已超过授权数量限制，添加失败");
//                        } else {
//                            $(".demo").css("display", "none");
//                            layer.msg("班级增加成功");
//                            location.reload();
//                        }
//                    }, 'json');
//        }
    }
    $(function () {
        $(document).on("click", ".add_list_tas", function () {
            $(".add_video_bigim").hide();
            $(".preview_spa_c").text("预览");
            var str = "";
            str = "<div class='list_add_line_f_wq'>" +
                    "<div class='list_add_line_f'>" +
                    "<div class='list_add_t'>" +
                    "<span  class='list_add_span'>通道:通道名称</span>" +
                    "<input  name ='ipc_name' class='add_linesinput' type='text'>" +
                    "<span  class='list_add_span'>通道地址</span>" +
                    "<input name ='media_stream_url' class='add_linesinput' type='text'>" +
                    "</div>" +
                    "<span class='preview_spa_c'>预览</span>" +
                    "<span class='close_f_line'></span>" +
                    "</div>" +
                    "<div class='add_video_bigim'>" +
                    "<object CLASSID= 'CLSID:3a22176d-118f-4185-9653-cdea558a6df8' id='video_obj_bid' name='ScriptableObject'" +
                    " url='' WIDTH='100%' HEIGHT='100%' style='border:1px solid #bdbdbd'></object>" +
                    "</div>" +
                    "</div>"
            // console.log(str)
            if ($(".list_add_line_f").length < 9) {
                $(".add_list_ki").after(str)
            }
            if ($(".list_add_line_f_wq").length == 9) {
                $(".add_list_tas").hide();
                $(".mCustomScrollbar").css("max-height", "260px")
            } else if ($(".list_add_line_f_wq").length < 9) {
                $(".add_list_tas").show();
                $(".mCustomScrollbar").css("max-height", "200px")
            }

            $("span.close_f_line").click(function () {
                $(this).parents(".list_add_line_f_wq").remove();
                if ($(".list_add_line_f_wq").length == 9) {
                    $(".add_list_tas").hide();
                    $(".mCustomScrollbar").css("max-height", "260px")
                } else if ($(".list_add_line_f_wq").length < 9) {
                    $(".add_list_tas").show();
                    $(".mCustomScrollbar").css("max-height", "200px")
                }
                return false
            })

        })

    })
    $(document).on("click", ".preview_spa_c", function () {
        if ($(this).text() == "预览") {
            var index = $(this).parents(".list_add_line_f_wq").index();
            $(".add_video_bigim").hide();
            $(".preview_spa_c").html("预览");
            $(this).parents(".list_add_line_f_wq").find(".add_video_bigim").show();
            $(this).html("取消预览");
            var url = $(this).parents(".list_add_line_f").find(".list_add_t").find("input.add_linesinput:last").val();
            //$(this).parents(".list_add_line_f_wq").find(".add_video_bigim").find("#video_obj_bid").attr("url", x);
            var obj = $(this).parents(".list_add_line_f_wq").find(".add_video_bigim").find("#video_obj_bid");
            var names = document.getElementsByName("ScriptableObject");
            names[index - 1].play(url);

            // console.log(val);

        } else {
            $(this).parents(".list_add_line_f_wq").find(".add_video_bigim").hide();
            $(this).html("预览")
        }
    })
</script>

