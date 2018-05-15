<%--
  Created by IntelliJ IDEA.
  User: Frank
  Date: 2014/9/26
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" type="text/css"
      href="${pageContext.request.contextPath}/js/zTree_v3/css/zTreeStyle/zTreeStyle.css">
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/zTree_v3/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/zTree_v3/js/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/zTree_v3/js/jquery.ztree.exedit-3.5.min.js"></script>

<script type="text/javascript">
    $(document).ready( function () {
                $('li.button a').click(function (e) {

                    var dropDown = $(this).parent().next();
//                    $('.dropdown').not(dropDown).slideUp('slow');
                    dropDown.slideToggle('slow');
                    e.preventDefault();
                })
            });
</script>

<div id="leftmenu">
    <ul  ><a href="#"  >所有设备（99）</a></li>
    <ul class="menu">
        <li class="button"><a href="#"  >未分组</a></li>
            <ul class="dropdown">
                <li><a href="#" >设备名称1</a></li>
                <li><a href="#" >设备名称2</a></li>
                <li><a href="#" >设备名称3</a></li>
                <li><a href="#" >设备名称4</a></li>
            </ul>
        <li class="button"><a href="#"  >高一年级</a></li>
            <ul class="dropdown">
                <li class="button"><a href="#">高一（1）班</a></li>
                    <ul class="dropdown">
                        <li><a href="#" >设备名称1</a></li>
                                <li><a href="#" >设备名称2</a></li>
                                <li><a href="#" >设备名称3</a></li>
                                <li><a href="#" >设备名称4</a></li>
                    </ul>
                <li><a href="#" >高一（2）班</a></li>
                <li><a href="#" >高一（3）班</a></li>
                <li><a href="#" >高一（4）班</a></li>
            </ul>
        <li class="button"><a href="#" >高二年级</a></li>
            <ul class="dropdown">
                <li><a href="#" >设备名称1</a></li>
                <li><a href="#" >设备名称2</a></li>
                <li><a href="#" >设备名称3</a></li>
                <li><a href="#" >设备名称4</a></li>
            </ul>
    </ul>
    <%--<div class="clear"></div>--%>
</div>