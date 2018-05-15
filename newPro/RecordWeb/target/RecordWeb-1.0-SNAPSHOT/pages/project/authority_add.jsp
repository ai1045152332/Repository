<%@ page import="com.honghe.recordhibernate.entity.Authority" %>
<%--
  Created by IntelliJ IDEA.
  User: wj
  Date: 2014-10-11
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <script src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
    <link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css" />
    <%--<script src="${pageContext.request.contextPath}/js/common/radio.js"></script>--%>

</head>
<style>
    .headr{margin-top: 6px}
</style>
<script>
    $(function(){
        var win520height=$(".win520_content").height();
        var win520width=$(".win520_content").width();
        parent.$(".xubox_main").find(".xubox_iframe").css({"width":win520width+"px","height":win520height+"px"});
        parent.$(".xubox_main").css({"width":win520width+"px","margin-left":"-120px","margin-top":-win520height/2+"px"});
    })
</script>
<body style="overflow: hidden;width:520px">
<div class="win520" >
<input type="hidden" value="${pageContext.request.contextPath}" id="root_path"/>
<%
Authority authorityinfo = (Authority)request.getAttribute("authorityinfo");
if(authorityinfo==null )
{
%>
<div class="win520_content">
    <div class="tab_content_list" style="margin-top: 25px;">
        <span class="tab_content_listtext">权限名称：</span>
        <input type="text" maxlength="20" name="aname" class="tab_content_listinput" />
    </div>
    <div class="tab_content_list" style="margin-top: 25px;">
        <span class="tab_content_listtext">权限字符：</span>
        <input type="text" maxlength="50" name="word" class="tab_content_listinput" />
    </div>
    <div class="tab_content_list" style="margin-top: 25px;">
        <span class="tab_content_listtext">权限描述：</span>
        <input type="text" maxlength="50" name="desc" class="tab_content_listinput" />
    </div>
    <div class="tab_content_list" style="margin-top: 25px;">
        <span class="tab_content_listtext">权限类别：</span>
        <div class="radio" id="radio1">
            <input type="radio" name="range" value="1" />
            <input type="radio" name="range" value="2" />
        </div>
        <div class="allr" id="all0">
            <div class="headr">
                <span class="bgr"></span>后台权限
            </div>
            <div class="headr">
                <span class="bgr"></span>前台权限
            </div>

        </div>
    </div>
    <div class="win_btn" style="margin: 20px 20px 20px 0;" onclick="javascript:addAuthSave()">
        <div class="win_btn_sure" style="margin-right: 180px;">确定</div>
    </div>
</div>
<%
}
else
{
%>
<div class="win520_content">
    <div class="tab_content_list" style="margin-top: 25px;">
        <span class="tab_content_listtext">权限名称：</span>
        <input type="text" maxlength="20" name="aname" class="tab_content_listinput" value="<%=authorityinfo.getAuthName()%>"/>
        <input type="hidden"  name="aid" value="<%=authorityinfo.getAuthId()%>"/>
    </div>
    <div class="tab_content_list" style="margin-top: 25px;">
        <span class="tab_content_listtext">权限字符：</span>
        <input type="text" maxlength="50" name="word" class="tab_content_listinput" value="<%=authorityinfo.getAuthWord()%>"/>
    </div>
    <div class="tab_content_list" style="margin-top: 25px;">
        <span class="tab_content_listtext">权限描述：</span>
        <input type="text" maxlength="50" name="desc" class="tab_content_listinput" value="<%=authorityinfo.getAuthDesc()==null?"":authorityinfo.getAuthDesc()%>"/>
    </div>
    <div class="tab_content_list" style="margin-top: 25px;">
        <span class="tab_content_listtext">权限类别：</span>
        <div class="radio" id="radio0">
            <%
                int rangeid = authorityinfo.getAuthRange();
            %>
            <input type="radio" name="range" value="1" <%=rangeid==1?"checked":"unchecked" %>/>
            <input type="radio" name="range" value="2" <%=rangeid==2?"checked":"unchecked" %>/>
        </div>
        <div class="allr" id="all1">
            <div class="headr">
                <span  class="<%=rangeid==1?"bgred":"bgr"%>"></span>后台权限
            </div>
            <div class="headr">
                <span class="<%=rangeid==2?"bgred":"bgr"%>"></span>前台权限
            </div>
        </div>
    <div class="win_btn" style="margin: 20px 20px 20px 0;" onclick="javascript:alterAuthSave()">
        <div class="win_btn_sure" style="margin-right: 180px;">确定</div>
    </div>
</div>
<%
}
%>
</div>
</body>
<script type="text/javascript">
    var rootpath = $("#root_path").val();
    //添加权限，保存进数据库(点击‘添加’按钮)
    function addAuthSave()
    {
        var aname = $.trim($("input[name='aname']").val());
        if(aname=="")
        {
            layer.alert("权限名称不能为空");
            return false;
        }
        var word = $.trim($("input[name='word']").val());
        if(word=="")
        {
            layer.alert("权限字符不能为空");
            return false;
        }
        var range =$('input[name="range"]:checked').val();
        if(range=="" || range == null)
        {
            layer.alert("权限类别不能为空");
            return false;
        }
        var desc = $("input[name='desc']").val();
        $.get(rootpath+"/auth/Authority_addAuthority.do", {authname: aname,authword:word,authdesc:desc,authrange:range}, function (data) {
            if(data.success==true)
            {
//                $("input[name='rname']").val("");
//                $("input[name='word']").val("");
//                $("input[name='desc']").val("");
//                $('input:radio:checked').removeAttr("checked");
//                $(".bgred").removeClass("bgred").addClass("bgr");
//                layer.msg(data.msg,1,1);
                layer.msg(data.msg,1,function(){
                    parent.layer.closeAll();
                    //parent.layer.close(layer.index);
                });
            }
            else
            {
                layer.msg(data.msg);
            }
        },'json');
    }
    //修改权限，保存进数据库(点击‘修改’按钮)
    function alterAuthSave()
    {
        var aid = $("input[name='aid']").val();
        var aname = $.trim($("input[name='aname']").val());
        if(aname=="")
        {
            layer.alert("权限名称不能为空");
            return false;
        }
        var word = $.trim($("input[name='word']").val());
        if(word=="")
        {
            layer.alert("权限字符不能为空");
            return false;
        }
        var range =$('input[name="range"]:checked').val();
        if(range=="" || range==null)
        {
            layer.alert("权限类别不能为空");
            return false;
        }
        var desc = $("input[name='desc']").val();
        $.get(rootpath+"/auth/Authority_editAuthority.do", {authId: aid,authname: aname,authword:word,authdesc:desc,authrange:range}, function (data) {
            if(data.success==true)
            {
                //window.parent.location.reload();
                layer.msg(data.msg,1,function(){
                    parent.layer.closeAll();
                    //parent.layer.close(layer.index);
                });
            }
            else
            {
                layer.msg(data.msg);
            }
        },'json');
    }
    $(function(){
        $(document.body).find("input[type=radio]").css("display","none");//初始化隐藏radio
        var checklen=$(".allr").find(".headr").length;
        $(".allr").find(".headr").click(function(){
            var index=$(this).index();
            var flag=$(".radio").find("input[type=radio]").eq(index).prop("checked");
            if(flag==false){
                $(".radio").find("input[type=radio]").eq(index).prop("checked",true);
                for(j=0;j<checklen;j++){
                    if(j==index){
                        $(".allr").find(".headr").eq(j).find(".bgr").addClass("bgred").removeClass("bgr");
                    }else{
                        $(".allr").find(".headr").eq(j).find(".bgred").addClass("bgr").removeClass("bgred");
                    }
                }
            }
        })
    });

</script>
