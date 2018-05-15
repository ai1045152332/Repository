<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: chby
  Date: 2014/10/10
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="${pageContext.request.contextPath}/css/frontend/index.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/common/jquery.cookie.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/common/screendetail.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frontend/device/group.js"></script>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
<script src="${pageContext.request.contextPath}/js/common/perfect-scrollbar.with-mousewheel.min.js"></script>
<script src="${pageContext.request.contextPath}/js/common/prettify.js"></script>
<script src="${pageContext.request.contextPath}/js/frontend/device/machinemanage.js"></script>
<script src="${pageContext.request.contextPath}/js/common/scrolldemo.js"></script>
<%

    response.setHeader("Cache-Control","no-store");

    response.setHeader("Pragrma","no-cache");

    response.setDateHeader("Expires",0);

%>
<input type="hidden" id="whichscroll">
<div class="win410">
    <%--<div class="win_head">
        <div class="win_head_text">分配教室</div>
        <div class="win_close"></div>
    </div>--%>
    <div class="win_content" style="margin-left: 40px;">
        <div class="win_content_text">请选择：</div>
        <div class="win_content_space"></div>
        <div class="win_content_text">已分配</div>
        <%
            String nasId =request.getAttribute("nasId").toString();
            //   System.out.println(hostgroupId+"---------");
        %>
        <input type="text" id="nasId" style="display: none" value="<%=nasId%>">
        <div class="scrollfather" id="win_lists">
            <div class="scrollson">
                <div class="win_list">
                    <ul>
                        <%
                            List<Map<String,Object>> noNas2hostList = (List<Map<String,Object>>) request.getAttribute("noNas2hostList");
                            for (int i = 0;i<noNas2hostList.size();i++) {
                                Map<String,Object> objects = noNas2hostList.get(i);
                        %>
                        <a href="javascript:void(0)"><li hostId="<%=objects.get("id")%>"  title="<%=objects.get("name")%>"><%=objects.get("name")%></li></a>
                        <%
                            }
                        %>

                    </ul>
                </div>
            </div>
            <div class="scroll_ymove">
                <div class="scroll_y" unorbind="unbind"></div>
            </div>
            <div class="scroll_xmove">
                <div class="scroll_x" unorbind="unbind"></div>
            </div>
        </div>
        <div  class="win_content_space" style="height: 250px;">
            <div class="win_content_space_arrow"></div>
        </div>
        <div class="scrollfather" id="win_lists1">
            <div class="scrollson">
                <div class="win_list">
                    <ul>
                        <%
                            Map<Integer, String> nas2hostMap = (Map<Integer, String>) request.getAttribute("nas2hostMap");
                            for (Integer hostId: nas2hostMap.keySet()) {
                                String name =  nas2hostMap.get(hostId);
                        %>
                        <a href="javascript:void(0)"><li hostId="<%=hostId%>" title="<%=name%>" ><%=name%></li><span class='win_list_close'></span></a>
                        <%
                            }
                        %>
                    </ul>
                </div>
            </div>
            <div class="scroll_ymove">
                <div class="scroll_y" unorbind="unbind"></div>
            </div>
            <div class="scroll_xmove">
                <div class="scroll_x" unorbind="unbind"></div>
            </div>
        </div>
        <div class="win_btn">
            <div class="win_btn_sure">完成</div>
            <div class="win_btn_done">取消</div>
        </div>
    </div>
    <input type="text" style="display: none" ulrhead="${pageContext.request.contextPath}" id="paramhidden">

</div>
<script>
    $(function(){
        var vipt_videowid=128
        $("#win_lists .scrollson").width(vipt_videowid)
        $("#win_lists1 .scrollson").width(vipt_videowid)
        $("#win_lists,#win_lists1").height(252)
        $("#win_lists,#win_lists1").width(vipt_videowid)

        $("#win_lists .scrollson").mouseover(function(){
            $("#whichscroll").val($.trim($(this).parent().attr("id")))
            if ((navigator.userAgent.match(/(iPhone|Android|iPad)/i))){
                var scrollfathter1=document.getElementById($.trim($(this).parent().attr("id")));
                scrollfathter1.addEventListener("touchstart", touchStart, false);
                scrollfathter1.addEventListener("touchmove", touchMove, false);
                scrollfathter1.addEventListener("touchend", touchEnd, false);
            }
        })
        $("#win_lists1 .scrollson").mouseover(function(){
            $("#whichscroll").val($.trim($(this).parent().attr("id")))
            if ((navigator.userAgent.match(/(iPhone|Android|iPad)/i))){
                var scrollfathter1=document.getElementById($.trim($(this).parent().attr("id")));
                scrollfathter1.addEventListener("touchstart", touchStart, false);
                scrollfathter1.addEventListener("touchmove", touchMove, false);
                scrollfathter1.addEventListener("touchend", touchEnd, false);
            }
        })
        scroll_y("win_lists","scrollson","scroll_y","scroll_ymove","scroll_x","scroll_xmove","","wheely","")
        scroll_y("win_lists1","scrollson","scroll_y","scroll_ymove","scroll_x","scroll_xmove","","wheely","")
        $("#win_lists .scrollson").css("margin-top","0")
        $("#win_lists .scroll_y").css("top","0")
        $("#win_lists1 .scrollson").css("margin-top","0")
        $("#win_lists1 .scroll_y").css("top","0")
        var win520height=$(".win410").height();
        var win520width=$(".win410").width();
        //  alert(win520height)
        parent.$(".xubox_main").find(".xubox_iframe").css({"width":win520width+"px","height":win520height+"px"});
        parent.$(".xubox_main").css({"width":win520width+"px","margin-left":"-60px","margin-top":-win520height/2+"px"});

        var getLi =$('.win_list').find("ul a li");
        // alert(getLi.length);
        for(var i=0;i<getLi.length;i++)
        {
            getLi.eq(i).text(autoAddEllipsis(getLi.eq(i).text(),8));
        }
    });
    var urlhead="${pageContext.request.contextPath}";
    $(".win_btn_sure").live("click",function(){
        var url =urlhead+"/settings/Settings_assignHost.do";
        var nasId = $("#nasId").val();
        //  alert(hostgroupId);
        var hostIds="";
        var isNameNull = false;
        var alllen=$(".win_list").eq(1).find("ul a").length;

        for(i=0;i<alllen;i++){
            var getval=$(".win_list").eq(1).find("ul a").eq(i);
            if(getval.css("display")=="block"){
                // alert(getval.find("li").attr("hostid"))
                // alert(i+"----"+getval)
                hostIds+=getval.find("li").attr("hostid")+",";
                if(getval.find("li").attr("title").trim() == ""){
                    isNameNull = true;
                    break;
                }
            }
        }

        if(isNameNull == true){
            layer.msg("班级名称不能为空,请在设备管理中进行修改");
            return;
        }


        $.post(url,{'nasId':nasId,'hostIds':hostIds},function(data){
            if(data.success==true)
            {
                window.parent.location.reload();
            }else{
                layer.msg(data.msg);
            }
        },'json');
    });
    $('.win_btn_done').click(function(){
        parent.layer.closeAll();
    });
    /*
     * 处理过长的字符串，截取并添加省略号
     * 注：半角长度为1，全角长度为2
     *
     * pStr:字符串
     * pLen:截取长度
     *
     * return: 截取后的字符串
     */
    function autoAddEllipsis(pStr, pLen) {
        var _ret = cutString(pStr, pLen);
        var _cutFlag = _ret.cutflag;
        var _cutStringn = _ret.cutstring;
        if ("1" == _cutFlag) {
            return _cutStringn + "...";
        } else {
            return _cutStringn;
        }
    }
    /*
     * 取得指定长度的字符串
     * 注：半角长度为1，全角长度为2
     *
     * pStr:字符串
     * pLen:截取长度
     *
     * return: 截取后的字符串
     */
    function cutString(pStr, pLen) {

        // 原字符串长度
        var _strLen = pStr.length;

        var _tmpCode;

        var _cutString;

        // 默认情况下，返回的字符串是原字符串的一部分
        var _cutFlag = "1";

        var _lenCount = 0;

        var _ret = false;

        if (_strLen <= pLen/2) {
            _cutString = pStr;
            _ret = true;
        }

        if (!_ret) {
            for (var i = 0; i < _strLen ; i++ ) {
                if (isFull(pStr.charAt(i))) {
                    _lenCount += 2;
                } else {
                    _lenCount += 1;
                }

                if (_lenCount > pLen) {
                    _cutString = pStr.substring(0, i);
                    _ret = true;
                    break;
                } else if (_lenCount == pLen) {
                    _cutString = pStr.substring(0, i + 1);
                    _ret = true;
                    break;
                }
            }
        }

        if (!_ret) {
            _cutString = pStr;
            _ret = true;
        }

        if (_cutString.length == _strLen) {
            _cutFlag = "0";
        }

        return {"cutstring":_cutString, "cutflag":_cutFlag};
    }

    /*
     * 判断是否为全角
     *
     * pChar:长度为1的字符串
     * return: true:全角
     *          false:半角
     */
    function isFull (pChar) {
        if ((pChar.charCodeAt(0) > 128)) {
            return true;
        } else {
            return false;
        }
    }
</script>
