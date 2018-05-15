<%@ page import="java.util.List" %>
<%@ page import="com.honghe.recordhibernate.dao.Page" %>
<%@ page import="com.honghe.recordhibernate.dao.Pager" %>
<%@ page import="com.honghe.recordhibernate.entity.Command" %>
<%@ page import="java.util.Map" %>
<%--
  Created by IntelliJ IDEA.
  User: lch
  Date: 2014/11/3
  Time: 14:16
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
    <a href="javascript:void(0)" class="amd_avarage" onclick="addcmd()">添加</a>
    <%--<a href="javascript:void(0)" class="amd_avarage" onclick="updatespec()">修改</a>--%>
    <%--<a href="javascript:void(0)" class="amd_avarage"onclick="delspec()">删除</a>--%>
</div>
<div class="check_all">
    <table class="table" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td>
                <table border="0" cellpadding="0" cellspacing="0" class="table_head">
                    <tr>
                        <td width="5%" align="center"></td>
                        <td width="15%" align="center">命令名称</td>
                        <td width="15%" align="center">命令组</td>
                        <td width="15%" align="center">命令图标</td>
                        <td width="15%" align="center">命令缺省</td>
                        <td width="10%" align="center">命令标识</td>
                        <td width="10%" align="center">命令代号</td>
                        <td width="15%" align="center">操作</td>
                    </tr>
                </table>
            </td>
        </tr>
            <%
            Map<String,Object> commandlists  = (Map<String,Object>)request.getAttribute("commandList");
            //List<Object[]> speclist = (List<Object[]>)speclistpage.get("speclist");
            List<Command> commandlist = (List<Command>)commandlists.get("commandlist");

            //out.print(speclist.size());
            for(int i=0;i<commandlist.size();i++)
            {
                 //String dtype = Integer.parseInt(speclist.get(i)[2].toString())==1 ? "NVR" : "IPC";
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
                            <td width="15%" align="center"><%=commandlist.get(i).getCmdName()%></td>
                            <td width="15%" align="center"><%=commandlist.get(i).getCmdGroup()%></td>
                            <td width="15%" align="center"><%=commandlist.get(i).getCmdImage()%></td>
                            <td width="15%" align="center"><%=commandlist.get(i).getCmdDefault()%></td>
                            <td width="10%" align="center"><%=commandlist.get(i).getCmdFlag()%></td>
                            <td width="10%" align="center"><%=commandlist.get(i).getCmdHex()%></td>
                            <td width="15%" align="center">
                                <a href="javascript:void(0)" onclick="delcmd(<%=commandlist.get(i).getCmdId()%>)">删除</a>
                                /<a href="javascript:void(0)" onclick="updatecmd(<%=commandlist.get(i).getCmdId()%>)">修改</a>
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
                        Page pages = (Page)commandlists.get("page");

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
                <script>
                    var root_path = $("#root_path").val();
                    function closelayer()
                    {
                        layer.close(classmanagerlayer);
                    }
                    var classmanagerlayer = "";
                    function addcmd()
                    {
                        var cmdId = -1;//
                        $.layer({
                            type: 2,
                            title: '添加命令',
                            shadeClose: true,
                            maxmin: false,
                            fix: false,
                            area: ['450px', '366px'],
                            iframe: {
                                src: root_path + "/project/Devicecomm_commdetail.do?cmdId=" + cmdId
                            },
                            end: function () {
                                $.get('/project/Devicecomm_commList.do',function(data){
                                    $("#public_right_center").html(data);
                                },'html')
                            }
                        });
                    }
                    //编辑命令信息
                    function updatecmd(cmdId)
                    {
                        //alert(cmdId)
                        $.layer({
                            type : 2,
                            title: '编辑用户',
                            shadeClose: true,
                            maxmin: false,
                            fix : false,
                            area: ['450px', '366px'],
                            iframe: {
                                src : root_path+"/project/Devicecomm_commdetail.do?cmdId="+cmdId
                            }
                        });

                    }

                    function delcmd(commandid)
                    {
                        layer.confirm("确定删除该命令吗？",function(){
                            layer.closeAll();
                            $.post(root_path+'/project/Devicecomm_delete.do',{commandId:commandid},function(data){
                                //alert(data.msg);
                                if(data.msg=="删除成功")
                                {
                                    $.get(root_path+'/project/Devicecomm_commList.do',function(data){
                                        $("#public_right_center").html(data);
                                    },'html');
                                }
                                else
                                {
                                    layer.alert("删除失败","确定");
                                }
                            })
                        });
                    }
                </script>
</div>
