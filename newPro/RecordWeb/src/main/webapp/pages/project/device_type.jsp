<%@ page import="com.honghe.recordhibernate.entity.Dtype" %>
<%@ page import="com.honghe.recordhibernate.dao.Page" %>
<%@ page import="java.util.List" %>
<%@ page import="com.honghe.recordhibernate.dao.Pager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="${pageContext.request.contextPath}/js/common/layer/skin/layer.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/js/common/layer/skin/layer.ext.css" rel="stylesheet" type="text/css"/>
<script src="${pageContext.request.contextPath}/js/common/jquery-1.11.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
<script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>

<%
    Page<Dtype> dtypePage = (Page<Dtype>) request.getAttribute("dTypeList");
    List<Dtype> dtypeList = dtypePage.getResult();
//    Pager pager = new Pager(2, Integer.parseInt(request.getAttribute("currentPageSize").toString()), "<span class='pages_prevpage'></span>", "<span class='pages_nextpage'></span>", "", "", false);
//    String pagers = pager.run();
%>

<div class="amd">
    <a href="javascript:addDtype()" class="amd_avarage">添加</a>
</div>
<div class="check_all">
    <table class="table" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td>
                <table border="0" cellpadding="0" cellspacing="0" class="table_head">
                    <tr>
                        <td width="5%" align="center"></td>

                        <td width="30%" align="center">设备类型</td>
                        <td width="30%" align="center">类型描述</td>

                        <td width="30%" align="center">操作</td>
                    </tr>
                </table>
            </td>
        </tr>
        <%
            for (int i = 0; i < dtypeList.size(); i++) {
                Dtype dtype = dtypeList.get(i);
        %>
        <tr class="tr">
            <td>
                <table border="0" cellpadding="0" cellspacing="0"
                       class="table_recycle table_recycle_ebebeb">
                    <tr>
                        <td width="5%" align="center"><%=i + 1%>
                        </td>

                        <td width="30%" align="center"><%=dtype.getDtypeName()%>
                        </td>
                        <td width="30%" align="center">
                            <%
                                String desc = "";
                                if (dtype.getDtypeDesc() != null) {
                                    desc = dtype.getDtypeDesc();
                                    out.println(desc);
                                }
                            %>

                        </td>
                        <td width="30%" align="center"><a href="javascript:deleteDeviceType('<%=dtype.getDtypeId()%>')">删除</a>
                            /<a
                                    href="javascript:updateDeviceType('<%=dtype.getDtypeId()%>','<%=dtype.getDtypeName()%>','<%=desc%>')">修改</a>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <%
            }

        %>

        <!--<tr>
            <table border="0" cellpadding="0" cellspacing="0" class="table_recycle">
                <tr>
                    <td colspan="5" align="center">
                        <span class="page_prev">上一页</span>
                        <span class="page_center">1/10</span>
                        <span class="page_next">下一页</span>
                    </td>
                </tr>
            </table>
        </tr>-->
    </table>

</div>
<script>

    function updateDeviceType(id, name, desc) {
        var path = "${pageContext.request.contextPath}/pages/project/device_type_add.jsp?id=" + id + "&name=" + name + "&desc=" + desc;

        $.layer({
            isClose: false,
            type: 2,
            title: '修改设备类型',
            shadeClose: true,
            maxmin: false,
            fix: false,
            area: ['430px', '357px'],
            iframe: {
                src: path
            },
            close: function (index) {
                this.isClose = true;
            },
            end: function () {
                if (!this.isClose) {
                    $.get("${pageContext.request.contextPath}/dtype/Dtype_findDTypeList.do", {currentPageSize: "1"}, function (data) {
                        $("#public_right_center").empty();
                        $("#public_right_center").append(data);
                    }, "html");
                }

            }
        });
    }

    function addDtype() {
        var path = "${pageContext.request.contextPath}/pages/project/device_type_add.jsp";

        $.layer({
            isClose: false,
            type: 2,
            title: '增加设备类型',
            shadeClose: true,
            maxmin: false,
            fix: false,
            area: ['430px', '357px'],
            iframe: {
                src: path
            },
            close: function (index) {
                this.isClose = true;
            },
            end: function () {
                if (!this.isClose) {
                    $.get("${pageContext.request.contextPath}/dtype/Dtype_findDTypeList.do", {currentPageSize: "1"}, function (data) {
                        $("#public_right_center").empty();
                        $("#public_right_center").append(data);
                    }, "html");
                }

            }
        });

    }

    function deleteDeviceType(id) {

        $.layer({
            shade: [0.5, '#000'],
            maxmin: false,
            area: ['auto', 'auto'],
            dialog: {
                msg: '确定删除该设备类别？',
                btns: 2,
                type: 4,
                btn: ['确定', '取消'],
                yes: function () {


                    $.get("${pageContext.request.contextPath}/dtype/Dtype_hasDtype.do", {'typeId': id}, function (data) {
                        if (data.success == true){
                            parent.layer.closeAll();
                            layer.alert(data.msg);
                        }else{
                            $.get("${pageContext.request.contextPath}/dtype/Dtype_deleteDType.do", {'typeId': id}, function (data) {
                                if (data.success == true) {
                                    layer.msg(data.msg, 1, 1);
                                    setTimeout(function () {
                                        $.get("${pageContext.request.contextPath}/dtype/Dtype_findDTypeList.do", {currentPageSize: "1"}, function (data) {
                                            $("#public_right_center").empty();
                                            $("#public_right_center").append(data);
                                        }, "html");
                                    }, 1000);

                                }
                                else {
                                    layer.msg(data.msg);
                                }
                            }, 'json')
                        }
                    }, "json");

                }, no: function () {

                }
            }
        });

    }
</script>


