<script src="${pageContext.request.contextPath}/js/common/jquery-1.7.2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/common/autoheight.js"></script>
<link href="${pageContext.request.contextPath}/css/project/main.css" rel="stylesheet" type="text/css"/>
<div class="errorhight" style="width: 100%;height: 100%;background: white;margin-top:-20px">
<div class="error" style="background:white">
    <%--<div class="error_text"></div>--%>
    <div class="error_pic">
        <span class="error_pictext">您访问的页面丢失了~<a href="${pageContext.request.contextPath}/pages/project/login.jsp" style="color:red;text-decoration: underline;">返回首页</a></span>
    </div>
</div>
</div>
<script>
$(function(){
	var mh=($("body").height()-335)/2+"px";
	$(".error").css("padding-top",mh);
    $(".errorhight").css({"min-height":"636px"});
    $(window).resize(function() {
        if(document.documentElement.clientHeight>740){
            $(".errorhight").css({"height":docheight-1+"px"});
        }
        if(document.documentElement.clientHeight<=740){
            $(".errorhight").css({"min-height":"636px"});
        }
    })
})
	
	
</script>