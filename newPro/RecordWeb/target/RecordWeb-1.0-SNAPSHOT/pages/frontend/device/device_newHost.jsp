<%@ page import="com.honghe.recordhibernate.entity.Dspecification" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: chby
  Date: 2014/10/13
  Time: 8:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="${pageContext.request.contextPath}/css/frontend/index.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/js/common/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/frontend/device/group.js"></script>
<script src="${pageContext.request.contextPath}/js/common/jquery.cookie.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/common/autoheight.js"></script>
<script src="${pageContext.request.contextPath}/js/common/selectmore.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
<%--<style>
    .selecttype
    {
        float:left;
        width: 100%;
    }
</style>
<div class="win410" style="height: 370px;line-height: 45px;">
    <form >
        <%
            String hostIpaddr = (String)request.getParameter("hostIpaddr");
            List<Dspecification> dspecList=(List<Dspecification>)request.getAttribute("dspecificationList");
//            HostgroupService hostgroupService = (HostgroupService)request.getAttribute("hostgroupService");
//            List<Dspecification> dspecList = hostgroupService.getSpecList();
            System.out.println("in the newHost page!");
            System.out.println("hostIpaddr="+hostIpaddr);

        %>
        <div class="selecttype">
            <span style="margin-left:20px;">名称：</span><input type="text" height="35px" width="150px" value="" id="hostName" name="hostName">
        </div>
        <div class="selecttype">
            <span style="margin-left:20px;float: left">类型：</span>
            <select id="dspecId" style="display: block;height: 25px;margin-top: 10px; line-height: 30px;width: 145px;">
                <%
                    for(int i=0;i<dspecList.size();i++)
                    {
                        if(i==0)
                        {
                        %>
                    <option value ="<%=dspecList.get(i).getDspecId()%>" selected="selected"><%=dspecList.get(i).getDspecName()%></option>
            <%
            }
            else
            {
            %>
            <option value ="<%=dspecList.get(i).getDspecId()%>"><%=dspecList.get(i).getDspecName()%></option>

            <%
                    }
                }
            %>

            </select>
        </div>
        <div  class="selecttype">
            <span  style="margin-left:35px;">IP：</span><input type="text" height="35px"  width="150px" readonly="true" ipstr="<%=hostIpaddr.replace('.','-')%>" value="<%=hostIpaddr%>" id="hostIpaddr" name="hostIpaddr">
        </div>
        <div  class="selecttype">
            <span style="margin-left:20px;">描述：</span><input type="text" height="35px" width="150px" value="" id="hostDesc" name="hostDesc">
        </div>
        <div  class="selecttype">
            <span style="margin-left:3px;">用户名：</span><input type="text" height="35px" width="150px" value="" id="hostUserName" name="hostUserName">
        </div>
        <div  class="selecttype">
            <span style="margin-left:20px;">密码：</span><input type="text" height="35px" width="150px" value="" id="hostPassWord" name="hostPassWord">
        </div>
        <div  class="selecttype">
            <a href="javascript:void(0)"><input type="button" value="确认" id="confirm" width="80px" height="35px" ></a>
            <a href="javascript:void(0)"><input type="button" value="取消" id="cancel" width="80px" height="35px" ></a>
        </div>
    </form>
    <input type="text" style="display: none" ulrhead="${pageContext.request.contextPath}" id="paramhidden">

</div>--%>
<style>

    .sall {
        margin-left: 10px;
        width: 186px;
    }

    .selectdiv {
        background: none;
    }

    #selectdivall0, #selectdivall1, #selectdivall2, #selectdivall3, #selectdivall4, #selectdivall5, #selectdivall6 {
        background: none;
        width: 184px;
    }

    #selectdivul1 a {
        width: 182px;
    }

    .selectdiv {
        background: url(${pageContext.request.contextPath}/image/frontend/n_icon_141016.png) 0 0 no-repeat;
        width: 184px;
        height: 27px;
        line-height: 27px;
        font-size: 12px;
    }

    .selectdivul {
        width: 182px;
    }

    .selectdivul a {
        text-indent: 10px;
        width: 182px;
        font-size: 12px;
    }

    #selectdivul0, #selectdivul1, #selectdivul2, #selectdivul3, #selectdivul4, #selectdivul5, #selectdivul6 {
        border: 1px solid #dbdbdb;
        margin-top: -2px;
        width: 182px;
    }

    input {
        font-size: 12px;
        font-family: "微软雅黑";
    }
</style>
<div class="win410">

    <div class="win_content" style="width: 410px;height:300px;margin: 20px 0px 0px 0px;">
        <%
            String hostIpaddr = (String) request.getParameter("hostIpaddr");
            String deviceType = request.getAttribute("deviceType").toString();
           // List<Dspecification> dspecList = (List<Dspecification>) request.getAttribute("dspecificationList");
            String deviceClassName = (String) request.getAttribute("hostSerialno");
            String protocolType = (String) request.getAttribute("protocolType");
        %>


        <div class="tab_content_list">
            <span class="tab_content_listtext" style="width: 100px;">名称：</span>
            <input type="text" maxlength="20" class="tab_content_listinput" value="" id="hostName" name="hostName"/>
        </div>
        <%--<div class="tab_content_list">--%>
            <%--<span class="tab_content_listtext" style="width: 100px;">类型：</span>--%>

            <%--<div class="sall">--%>
                <%--<select class="select" id="select0">--%>
                    <%--<%--%>
                        <%--for (int i = 0; i < dspecList.size(); i++) {--%>
                            <%--if (i == 0) {--%>
                    <%--%>--%>
                    <%--<option value="<%=dspecList.get(i).getDspecId()%>"--%>
                            <%--selected="selected"><%=dspecList.get(i).getDspecName()%>--%>
                    <%--</option>--%>
                    <%--<%--%>
                    <%--} else {--%>
                    <%--%>--%>
                    <%--<option value="<%=dspecList.get(i).getDspecId()%>"><%=dspecList.get(i).getDspecName()%>--%>
                    <%--</option>--%>

                    <%--<%--%>
                            <%--}--%>
                        <%--}--%>
                    <%--%>--%>


                <%--</select>--%>

                <%--<div class="selectdivall" id="selectdivall0">--%>
                    <%--<div class="selectdiv" id="selectdiv0"></div>--%>
                    <%--<div class="selectdivul" id="selectdivul0"></div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
        <div class="tab_content_list">
            <span class="tab_content_listtext" style="width: 100px;">IP：</span>
            <input type="text" class="tab_content_listinput" readonly="true" ipstr="<%=hostIpaddr.replace('.','-')%>"
                   value="<%=hostIpaddr%>" id="hostIpaddr" name="hostIpaddr"/>
        </div>
        <div class="tab_content_list">
            <span class="tab_content_listtext" style="width: 100px;">描述：</span>
            <input type="text" maxlength="50" class="tab_content_listinput" value="" id="hostDesc" name="hostDesc"/>
        </div>
        <div class="tab_content_list">
            <span class="tab_content_listtext" style="width: 100px;">用户名：</span>
            <input type="text" maxlength="20" class="tab_content_listinput" value="" id="hostUserName"
                   name="hostUserName"/>
        </div>
        <div class="tab_content_list">
            <span class="tab_content_listtext" style="width: 100px;">密码：</span>
            <input type="password" maxlength="20" class="tab_content_listinput" value="" id="hostPassWord"
                   name="hostPassWord"/>
        </div>
        <div class="tab_content_list">
            <span class="tab_content_listtext" style="width: 100px;">确认密码：</span>
            <input type="password" maxlength="20" class="tab_content_listinput" value="" id="hostPassWordconfirm"
                   name="hostPassWordconfirm"/>
        </div>
    </div>
    <div class="win_btn" style="margin: 20px 20px 20px 0;">
        <a href="javascript:void(0)">
            <div class="win_btn_sure" style="margin-right: 20px;" id="confirm">确定</div>
        </a>
        <a href="javascript:void(0)">
            <div class="win_btn_done" id="cancel">取消</div>
        </a>
    </div>
    <input type="text" style="display: none" urlhead="${pageContext.request.contextPath}" id="paramhidden">

</div>

<script>
    var a = 0;
    var deviceClassName = "<%=deviceClassName%>";
    var protocolType = "<%=protocolType%>";
    var deviceType = "<%=deviceType%>";
    $("#confirm").live("click", function () {

        var hostName = $.trim($("#hostName").val());
        if (hostName == "") {
            layer.msg("班级名称不能为空", 2, 8);
            return false;
        }
        var hostIpaddr = $("#hostIpaddr").val();
        if (hostIpaddr == "") {
            layer.msg("IP不能为空", 2, 8);
            return false;
        }
        var hostDesc = $("#hostDesc").val();
        var hostUserName = $("#hostUserName").val();
        var hostPassWord = $("#hostPassWord").val();
        var parentDivId = $("#hostIpaddr").attr("ipstr");
      //  var hostDspecId = $("#select0").find("option:selected").val();
        var hostDspecId = "1";//临时暂时不用
//        if (hostDspecId == "") {
//            layer.msg("设备型号不能为空", 2, 8);
//            return false;
//        }
        var confirm_pwd = $("input[name='hostPassWordconfirm']").val();
        if (hostPassWord != confirm_pwd) {
            $("input[name='confirm_pwd']").val("");
            layer.msg("两次密码输入不一致", 2, 8);
            return false;
        }
        //  alert(urlhead);
        var url = $("#paramhidden").attr("urlhead") + "/host/Host_addHost.do";
        if (a < 1) {
            a++;
            var l = layer.load("执行中…");
            $.post(url, {
                'hostName': hostName,
                'hostIpaddr': hostIpaddr,
                'hostDesc': hostDesc,
                'hostUserName': hostUserName,
                'hostPassWord': hostPassWord,
                'dspecId': hostDspecId,
                'hostSerialno': deviceClassName,
                'protocolType':protocolType,
                'deviceType':deviceType
            }, function (data) {
                layer.close(l);
                if (data.success == true) {

                    window.parent.divdisplay(parentDivId);

                } else {
                    layer.msg(data.msg);
                }

                a = 0;
            }, 'json');
        }
    });
    $("#cancel").click(function () {
        window.parent.layer.closeAll();
    });
</script>