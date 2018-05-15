<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: chby
  Date: 2014/10/24
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="${pageContext.request.contextPath}/css/frontend/index.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/js/common/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frontend/device/group.js"></script>
<script src="${pageContext.request.contextPath}/js/common/jquery.cookie.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/common/autoheight.js"></script>
<script src="${pageContext.request.contextPath}/js/common/checkbox.js"></script>
<script src="${pageContext.request.contextPath}/js/frontend/device/group.js"></script>
<script src="${pageContext.request.contextPath}/js/common/perfect-scrollbar.with-mousewheel.min.js"></script>
<script src="${pageContext.request.contextPath}/js/common/prettify.js"></script>
<script src="${pageContext.request.contextPath}/js/frontend/device/machine_tree.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
<style>
    .tree_contenta{
        margin-left: 30px;
        width: 190px;
    }
    .tree_title_close{
    	background: none;
    	margin-left: 0;
    }
    .margin_right{margin-right: 8px;}
</style>
<div class="win320" style="width: 300px;">
   <%-- <div class="win_head">
        <div class="win_head_text">移动设备</div>
        <div class="win_close"></div>
    </div>--%>
    <div class="win360_content" style="height: 360px;width: 300px;">
        <div class="win_content_text" style="margin-left: 20px;margin-top: 13px;">移动到：</div>
        <div id="win_moveto">
            <div class="win_moveto_list">
                <div class="tree" style="min-height: 255px;">
                    <a href="#" class="tree_titleb tree_titleb_open">所有设备</a>
                    <div class="public_left">
                        <%
                            //获取分组tree数据
                            List<Map> hostgroupList =(List<Map>)request.getAttribute("hostRelationList");
                            for(Map<String,Object> group : hostgroupList)
                            {
                         %>

                        <div class="tree_title tree_title_close">
                            <span  hostgroupId="<%=group.get("group_id")%>"  class="tree_titlea group"><span class="tree_titleaiconmain"></span><%=group.get("group_name")%></span>
                            <%
                                //分组下的教室
                                List<Map> hostList =(List<Map>)group.get("host_list");
                               // System.out.println(hostList.size());
                                if(!hostList.isEmpty()  && !hostList.toString().trim().equals("") && hostList.size()>0)
                                {
                            %>

                            <div class="tree_content" style="margin-left: 0;">
                                <%
                                        for(Map<String,Object> b : hostList)
                                        {
                                %>

                                <div class="tree_contenta"  onclick="deviceMove('<%=b.get("id").toString()%>')"><span class="tree_content_onlinebg"></span><%=b.get("name").toString()%></div>
                                <%
                                        }
                                %>
                            </div>
                            <%
                                }
                            %>
                        </div>
                        <%
                            }
                            //获取hostTree标志位，如果为1，则是设备页面的移动分组tree，要显示未分组的班级
                            String hostTree = (String)request.getAttribute("hostTreeFlag");
                            if(hostTree.trim().equals("1"))
                            {
                            %>
                        <div class="tree_content" style="display: block;margin-left: 0;">
                            <%
                                List<Map> hostNoRelationList = (List<Map>)request.getAttribute("hostNoRelationList");
                                //   System.out.println(hostNoRelationList);
                                for (Map<String,Object> obj : hostNoRelationList) {
                            %>
                            <div class="tree_contenta" onclick="deviceMove('<%=obj.get("host_id")%>')"><span class="tree_content_onlinebg margin_right"></span><%=obj.get("host_name").toString()%></div>
                            <%
                                }
                            %>
                        </div>

                        <%
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>
        <div class="win_btn" style="width: 93%">
            <div class="win_btn_sure" onclick="confirm('<%=hostTree%>')">完成</div>
            <div class="win_btn_done" onclick="cancel()">取消</div>
        </div>
        <input type="text" id ="paramstext" hostgroupId="" hostId="" hostgroupIdOld="" hostIdOld="" urlhead="${pageContext.request.contextPath}" style="display: none" />
    </div>
</div>
<script>
//  $(function(){
//      var win520height=$(".win360_content").height();
//      var win520width=$(".win360_content").width();
//      //  alert(win520height)
//      parent.$(".xubox_main").find(".xubox_iframe").css({"width":win520width+"px","height":win520height+"px"});
//      parent.$(".xubox_main").css({"width":win520width+"px","margin-left":"-120px","margin-top":-win520height/2+"px"});
//
//
//  })
    function deviceMove(hostId)
    {
        alert("选择移动的班级");
        $("#paramstext").attr("hostId",hostId);
    }

    $(".group").click(function(){
        $(".mm_moveto_selected").removeClass("mm_moveto_selected");
         $(this).addClass("mm_moveto_selected");
        var groupId=$(this).attr("hostgroupId");
        $("#paramstext").attr("hostgroupId",groupId);
    });
    function confirm(flag)
    {
        //alert("确认");
        var urlhead=$("#paramstext").attr("urlhead");
        if(flag=="1")
        {
            alert("设备移动到班级确认");
        }
        else
        {
            var hostgroupId=$("#paramstext").attr("hostgroupId");
            var hostId =$("#paramstext").attr("hostId");
            if(hostgroupId!="" && hostgroupId!=null)
            {
//                var url = urlhead+"/host/Host_moveHostRelation.do";
//                $.get(url,{"hostId":});
                window.parent.moveHosts(hostgroupId,""); //调用父页面的方法，执行移动操作
            }
            else
            {
                layer.msg("未选择分组，移动失败！")
            }
        }
    }
    function cancel(flag)
    {
        //alert('123456789');
        window.parent.layerclose();
    }
</script>