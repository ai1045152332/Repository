<%@ page import="com.honghe.recordhibernate.entity.Dspecification" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.honghe.recordhibernate.dao.Pager" %>
<%@ page import="org.apache.struts2.ServletActionContext" %>
<%@ page import="com.honghe.recordhibernate.dao.Page" %>
<%--
  Created by IntelliJ IDEA.
  User: lch
  Date: 2014/10/28
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="${pageContext.request.contextPath}/js/common/layer/skin/layer.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/js/common/layer/skin/layer.ext.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/frontend/hpager.css" rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
<script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
<%--<script src="${pageContext.request.contextPath}/js/project/checkbox.js"></script>--%>
<div class="amd">
    <a href="javascript:void(0)" class="amd_avarage" onclick="addspec()">添加</a>
    <%--<a href="javascript:void(0)" class="amd_avarage" onclick="updatespec()">修改</a>--%>
    <%--<a href="javascript:void(0)" class="amd_avarage"onclick="delspec()">删除</a>--%>
</div>
<%
    Map<String,Object> speclistpage  = (Map<String,Object>)request.getAttribute("speclistpage");
    List<Object[]> speclist = (List<Object[]>)speclistpage.get("speclist");
    if(speclist!=null && speclist.size()>0)
    {
%>
<div class="check_all">
    <table class="table" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td>
                <table border="0" cellpadding="0" cellspacing="0" class="table_head">
                    <tr>
                        <td width="5%" align="center"></td>
                        <%--<td width="5%" align="center">
                            <div class="checkall_head">
                                <input id="check_head" type="checkbox"/>
                                <div  class="bg" id="checkallhead" onclick="checkall()"></div>
                            </div>
                        </td>--%>
                        <td width="15%" align="center">设备型号</td>
                        <td width="15%" align="center">设备类型</td>
                        <td width="20%" align="center">连接参数</td>
                        <td width="30%" align="center">设备命令</td>
                        <td width="15%" align="center">操作</td>
                    </tr>
                </table>
            </td>
        </tr>
        <%--<div class="all" style="display: block;">--%>
        <%--</div>--%>
        <%
            for(int i=0;i<speclist.size();i++)
            {
                String dtype = Integer.parseInt(speclist.get(i)[2].toString())==1 ? "NVR" : "IPC";
        %>
                <tr class="tr">
                    <td>
                        <%
                            if(i%2 == 0)
                            {
                        %>
                                <table border="0" cellpadding="0" cellspacing="0" class="table_recycle table_recycle_ebebeb">
                        <%
                            }
                            else
                            {
                        %>
                                <table border="0" cellpadding="0" cellspacing="0" class="table_recycle table_recycle_fff">
                        <%
                            }
                        %>


                            <tr>
                                <td width="5%" align="center"><%=i+1%></td>
                                <%--<td width="5%" align="center">
                                    <div class="check_head">
                                        <div class="bg"></div>
                                    </div>
                                </td>--%>
                                <td width="15%" align="center"><%=speclist.get(i)[1]%></td>
                                <td width="15%" align="center"><%=dtype%></td>
                                <td width="20%" align="center"><%=speclist.get(i)[3]==null?"":speclist.get(i)[3]%></td>
                                <td width="30%" align="center"><%=speclist.get(i)[5]==null?"":speclist.get(i)[5]%></td>
                                <td width="15%" align="center">
                                    <a href="javascript:void(0)" onclick="delspec(<%=speclist.get(i)[0]%>)">删除</a>
                                    /<a href="javascript:void(0)" onclick="updatespec(<%=speclist.get(i)[0]%>)">修改</a>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
        <%
            }
        %>
        <%
            int currentPage = Integer.parseInt(request.getAttribute("currentPage").toString());
            Page pages = (Page)speclistpage.get("page");

            int pageCount = pages.getTotalPageSize();
            Pager pager = new Pager(pageCount,currentPage,"<span class='pages_prevpage'></span>","<span class='pages_nextpage'></span>","","",false);

            String pagers = pager.run();
        %>
        <tr>
            <td>
            <div id="linkpage">
                <%=pagers%>
            </div>
            </td>
        </tr>
    </table>
</div>
<%
    }
%>
<script>
var root_path = $("#root_path").val();
function closelayer()
{
    layer.close(classmanagerlayer);
}
var classmanagerlayer = "";
function addspec()
{
    $.layer({
        type : 2,
        title: '添加设备型号',
        shadeClose: true,
        maxmin: false,
        fix : false,
        area: ['400px', '600px'],
        iframe: {
            src : root_path+'/project/Spec_addList.do'
        }
        /*end:function(){
            //window.location.reload();
            $.get(root_path+'/project/Spec_specList.do',function(data){
                $("#public_right_center").html(data);
            },'html')
        }*/
    });
}
function updatespec(specid)
{
    classmanagerlayer=$.layer({
        type : 2,
        title: '修改设备型号',
        shadeClose: true,
        maxmin: false,
        fix : false,
        area: ['400px', '600px'],
        iframe: {
            src : root_path+'/project/Spec_update.do?specId='+specid
        },
        data:{specId:specid},
        end:function(){
            //window.location.reload();
            $.get(root_path+'/project/Spec_specList.do',function(data){
                $("#public_right_center").html(data);
            },'html')
        }
    });
}
function delspec(specid)
{
    layer.confirm("确定删除该设备型号吗？",function(){

        $.post(root_path+'/project/Spec_delete.do',{specId:specid},function(data){
            //alert(data.msg);
            if(data.msg=="删除失败")
            {
                layer.alert("该设备型号已经被使用不能删除","确定");
            }
            else
            {
                layer.msg(data.msg,1);
                $.get(root_path+'/project/Spec_specList.do',function(data){
                    $("#public_right_center").html(data);
                },'html')
            }

        })
    });
}
</script>
