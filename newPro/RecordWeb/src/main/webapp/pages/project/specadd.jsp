<%@ page import="com.honghe.recordhibernate.entity.Command" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: lch
  Date: 2014/10/29
  Time: 8:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="${pageContext.request.contextPath}/js/common/jquery-1.11.1.min.js"></script>
<link href="${pageContext.request.contextPath}/js/common/layer/skin/layer.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/js/common/layer/skin/layer.ext.css" rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
<script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
<link href="${pageContext.request.contextPath}/css/project/main.css" rel="stylesheet" type="text/css" />
<%--<script src="${pageContext.request.contextPath}/js/project/checkbox-user.js"></script>--%>
<style>
    .tab_content_listtext
    {
        width:100px;
    }
</style>
<div style="margin-left: 50px;margin-bottom: 20px;margin-top: 20px">
<%
    List<Command> commandList = (List<Command>)request.getAttribute("commandlist");
%>
<form method="post" id="addform">
    型号名称:
    <input type="text" maxlength="20" name="specname">
    <br />
    <br />
    设备类型:
    <select name="spectype">
        <option value="1">NVR</option>
        <option value="2">IPC</option>
    </select>
    <br />
    <br />
    连接参数:
    <input type="text" maxlength="20" name="connectparam">
    <br />
    <br />
    <%
        if(commandList.size()>0)
        {
    %>
            添加命令：
            <%
                for(int i=0;i<commandList.size();i++)
                {
            %>
                <div class="head" style="float:left;width: 100%;" id="<%=commandList.get(i).getCmdId()%>">
                    <input type="checkbox" style="display: none" id="c_<%=commandList.get(i).getCmdId()%>" name="cmdId1" value="<%=commandList.get(i).getCmdId()%>">
                    <input type="hidden" id="h_<%=commandList.get(i).getCmdId()%>" value="" name="cmdId">
                    <label>
                        <div id="l_<%=commandList.get(i).getCmdId()%>" class="bg" style="margin-top: 0px;"></div><%=commandList.get(i).getCmdName()%>
                    </label>
                </div>
                <div id="p_<%=commandList.get(i).getCmdId()%>" style="display: none">
                    <div style="width: 100%;float:left">
                        <span class="tab_content_listtext">连接参数:</span>
                        <input type="text" maxlength="20" name="cmdParam" class="tab_content_listinput" />
                    </div>
                    <div style="width: 100%;float:left">
                    <span class="tab_content_listtext">命令WORD:</span>
                    <input type="text" maxlength="20" name="cmdWord" class="tab_content_listinput" />
                    </div>
                    <div style="width: 100%;float:left">
                    <span class="tab_content_listtext">命令RETURN:</span>
                    <input type="text" maxlength="20" name="cmdReturn" class="tab_content_listinput" />
                    </div>
                    <div style="width: 100%;float:left">
                    <span class="tab_content_listtext">命令FLAG:</span>
                    <select name="cmdFlag" class="tab_content_listinput">
                        <option value="01">01</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                    </select>
                    <%--<input type="text" name="cmdFlag" class="tab_content_listinput" />--%>
                    </div>
                </div>
            <%
                }
            %>
    <%
        }
    %>
    <input type="submit" style="float:left;" value="添加" onclick="return submitform()">
    <input type="reset" id="resetform" style="display: none">
</form>
</div>
<script>
    function submitform()
    {
        var root_path = parent.$("#root_path").val();
        $.ajax({
            url: root_path+'/project/Spec_add.do',
            type: "POST",
            dataType:"json",
            data: $("#addform").serialize(),
            success: function (data)
            {
                layer.msg(data.msg,1,function(r){
                    if(data.success)
                    {
                        //$("#resetform").click();
                        $.get(root_path+'/project/Spec_specList.do',function(data){
                            parent.$("#public_right_center").html(data);
                            parent.layer.closeAll();

                        });
                    }
                    //parent.location.reload();
                });
            }
        });
        return false;
    }
    $(".head").click(function(){
        var id = $(this).attr("id");
        var flag=$("#c_"+id).prop("checked");
        if(flag==true){
            $("#c_"+id).prop("checked",false);
            $("#l_"+id).removeClass("bged").addClass("bg");
            $("#h_"+id).val("");
            $("#p_"+id).hide();
           // $(".head").eq(index).find(".bged").removeClass("bged").addClass("bg");

        }else{
            $("#c_"+id).prop("checked",true);
            $("#l_"+id).removeClass("bg").addClass("bged");
            $("#h_"+id).val(id);
            $("#p_"+id).show();
//            $(".checkbox").find("input[type=checkbox]").eq(index).prop("checked",true);
//            $(".head").eq(index).find(".bg").removeClass("bg").addClass("bged");
        }
    })
</script>
