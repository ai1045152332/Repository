<%@ page import="com.honghe.recordhibernate.entity.Dspecification" %>
<%@ page import="com.honghe.recordhibernate.entity.Dtype" %>
<%@ page import="java.util.List" %>
<%@ page import="com.honghe.recordhibernate.entity.Command" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.honghe.recordhibernate.entity.Spec2command" %>
<%--
  Created by IntelliJ IDEA.
  User: lch
  Date: 2014/10/29
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="${pageContext.request.contextPath}/js/common/jquery-1.11.1.min.js"></script>
<link href="${pageContext.request.contextPath}/js/common/layer/skin/layer.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/js/common/layer/skin/layer.ext.css" rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
<script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
<link href="${pageContext.request.contextPath}/css/project/main.css" rel="stylesheet" type="text/css" />
<style>
    .tab_content_listtext
    {
        width:100px;
    }
</style>
<%
    Dspecification dspecification = (Dspecification) request.getAttribute("dspecification");
    List<Dtype> dtypeList = (List<Dtype>)request.getAttribute("dtypelist");
    List<Command> commandList = (List<Command>)request.getAttribute("commandlist");
%>
<div style="margin-left: 50px;margin-bottom: 20px;margin-top: 20px">
<%
    if(dspecification != null)
    {
%>
    <form method="post" id="updateform">
        <input type="hidden" name="specId" value="<%=dspecification.getDspecId()%>">
        型号名称:
        <input type="text" maxlength="20" name="specname" value="<%=dspecification.getDspecName()%>">
        <br />
        <br />
        设备类型:
        <select name="spectype" >
            <%
                for(int i=0;i<dtypeList.size();i++)
                {
                    Dtype dtype = dtypeList.get(i);
            %>
            <option value="<%=dtype.getDtypeId()%>" <%=dtype.getDtypeId()==dspecification.getDtype().getDtypeId()?"selected":""%>><%=dtype.getDtypeName()%></option>
            <%
                }
            %>
        </select>
        <br />
        <br />
        连接参数:
        <input type="text" maxlength="20" name="connectparam" value="<%=dspecification.getConnectParam()%>">
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
                Iterator iterator1 = dspecification.getSpec2commands().iterator();
                if(iterator1.hasNext())
                {
                    boolean flag = false;
                    while (iterator1.hasNext())
                    {
                        Spec2command spec2command = (Spec2command)iterator1.next();
                        Command command = spec2command.getCommand();
                        if(command.equals(commandList.get(i)))
                        {
                            flag = true;
        %>
        <div class="head" style="float:left;width: 100%;" id="<%=commandList.get(i).getCmdId()%>">
            <input type="checkbox" checked style="display: none" id="c_<%=commandList.get(i).getCmdId()%>" name="cmdId1" value="<%=commandList.get(i).getCmdId()%>">
            <input type="hidden" id="h_<%=commandList.get(i).getCmdId()%>" value="<%=commandList.get(i).getCmdId()%>" name="cmdId">
            <label>
                <div id="l_<%=commandList.get(i).getCmdId()%>" class="bged" style="margin-top: 0px;"></div><%=commandList.get(i).getCmdName()%>
            </label>
        </div>
        <div id="p_<%=commandList.get(i).getCmdId()%>" style="display: none">
            <div style="width: 100%;float:left">
                <span class="tab_content_listtext">命令参数:</span>
                <input type="text" maxlength="20" name="cmdParam" class="tab_content_listinput" value="<%=spec2command.getCmdParam()==null?"":spec2command.getCmdParam()%>"/>
            </div>
            <div style="width: 100%;float:left">
                <span class="tab_content_listtext">命令WORD:</span>
                <input type="text" maxlength="20" name="cmdWord" class="tab_content_listinput" value="<%=spec2command.getCmdWord()==null?"":spec2command.getCmdWord()%>"/>
            </div>
            <div style="width: 100%;float:left">
                <span class="tab_content_listtext">命令RETURN:</span>
                <input type="text" maxlength="20" name="cmdReturn" class="tab_content_listinput" value="<%=spec2command.getCmdReturn()==null?"":spec2command.getCmdReturn()%>"/>
            </div>
            <div style="width: 100%;float:left">
                <span class="tab_content_listtext">命令FLAG:</span>
                <select name="cmdFlag" class="tab_content_listinput">
                    <%
                        if(spec2command.getCmdFlag()!=null)
                        {
                    %>
                    <option value="01" <%=spec2command.getCmdFlag().equals("01")?"selected":""%>>01</option>
                    <option value="10" <%=spec2command.getCmdFlag().equals("10")?"selected":""%>>10</option>
                    <option value="11" <%=spec2command.getCmdFlag().equals("11")?"selected":""%>>11</option>
                    <%
                        }
                        else
                        {
                    %>
                    <option value="01">01</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <%
                        }
                    %>
                </select>
            </div>
        </div>
        <%
                        }

                    }
                    if(flag == false) {
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
            </div>
        </div>
        <%
                    }
                }
                else
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
            </div>
        </div>
        <%
                }
            }
            }
        %>
        <input type="submit" style="float:left;" value="添加" onclick="return updateform()">
        <input type="reset" id="resetform" style="display: none">
    </form>
<%
    }
%>
</div>
<script>
    var root_path = parent.$("#root_path").val();
    function updateform()
    {
        $.ajax({
            url: root_path+'/project/Spec_edit.do',
            type: "POST",
            dataType:"json",
            data: $("#updateform").serialize(),
            success: function (data)
            {
                layer.msg(data.msg,1,function(r){
                    if(data.msg=="修改成功")
                    {
                        parent.closelayer();
                    }
                });
            }
        });
        return false;
    }
    var root_path = parent.$("#root_path").val();
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
