<%@ page import="java.util.List" %>
<%@ page import="com.honghe.recordhibernate.entity.RecordNameSetting" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
  <title>系统设置 | 集控平台</title>
  <link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css"/>
  <link href="${pageContext.request.contextPath}/css/frontend/time_celve_two.css" rel="stylesheet" type="text/css">
  <link href="${pageContext.request.contextPath}/css/frontend/record/hpager.css" rel="stylesheet" type="text/css"/>
  <link href="${pageContext.request.contextPath}/css/frontend/record/index.css" rel="stylesheet" type="text/css"/>
  <link href="${pageContext.request.contextPath}/css/frontend/record/fd-slider.css" rel="stylesheet" type="text/css"/>
  <script src="${pageContext.request.contextPath}/js/common/jquery-1.9.1.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/lb_common/screendetailforrecord.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/lb_frontend/setting/setting.js"></script>
</head>
<body>

<div class="public" style="min-width:1680px;">
    <jsp:include page="/pages/frontend/lb_header.jsp"></jsp:include>
    <style>
      .mm_list_text {
        text-indent: 0;

      }
    </style>
        <div class="set">
          <div class="set_head">
            <div class="settinghead">
              <ul>
                <li onclick="javascript:jump(0)" ><div class="tb"></div>台标</li>
                <li onclick="javascript:jump(1)"><div class="videoinfo"></div>录像信息</li>
                <li onclick="javascript:jump(2)"><div class="living"></div>直播</li>
                <li onclick="javascript:jump(3)"><div class="nas"></div>NAS存储设置</li>
                <li onclick="javascript:jump(4)"><div class="passuser"></div>录播主机用户名密码设置</li>
                <li onclick="javascript:jump(5)"><div class="ftp"></div>FTP设置</li>
                <li onclick="javascript:jump(6)" ><div class="license"></div>License设置</li>
                <li onclick="javascript:jump(7)" ><div class="second_nav_selected"></div>课表类型选择</li>
                <li onclick="javascript:jump(8)" ><div class="second_nav_schedule"></div>课表管理</li>
                <li onclick="javascript:jump(9)" ><div class="second_nav_celve"></div>作息时间策略</li>
                <li onclick="javascript:jump(10)" style="color:#28b779;"><div class="record_name_setting"></div>录制文件名设置</li>
              </ul>
            </div>
          </div>
          <div class="main_content">
            <div class="main_content_consiz">
              <div class="main_content_consiz_li">
                <p class="titl_ut">请选择录制文件的命名方式：</p>
              </div>
              <% RecordNameSetting recordNameSetting = (RecordNameSetting)request.getAttribute("recordNameSetting");
                boolean subject = false;
                boolean teahcer = false;
                boolean classroom = false;
                if(recordNameSetting!=null){
                  subject =recordNameSetting.isSubjectName();
                  teahcer = recordNameSetting.isTeacherName();
                  classroom = recordNameSetting.isClassRoomName();
                }
              %>
              <div class="main_content_consiz_li chlicd_icon">
                <span <%if(subject){%>class="global_checkbox"<%}%> exmple="语文"></span><p>科目名称</p>
                <span <%if(teahcer){%>class="global_checkbox"<%}%> exmple="李老师"></span><p>教师名称</p>
                <span <%if(classroom){%>class="global_checkbox"<%}%> exmple="初一三班"></span><p>教室名称</p>
              </div>
              <div class="main_content_consiz_li chlibiy_icon">
                <p>当前选择命名方式示例：<span>科目-教师-教室.mp4</span></p>
              </div>
              <div class="main_content_consiz_li chlibyd_icon">
                <span>确定</span>
              </div>
            </div>

          </div>
        </div>
      <div class="foot" >
	<jsp:include page="/pages/frontend/footer.jsp"></jsp:include>
</div>
</div>

</body>
</html>
<script>

  function showExmple(){
    var array = $(".chlicd_icon span[class=global_checkbox]");
    if(array.length>0){
      var str = "";
      for(var i= 0,j=array.length;i<j;i++){
        str+= i==j-1?$(array[i]).attr("exmple")+".mp4":$(array[i]).attr("exmple")+"-";
      }
      $(".chlibiy_icon span").html(str);
    }else{
      $(".chlibiy_icon span").html("请选择命名方式");
    }
  }
  $(document).ready(function(){
    showExmple();
  })
  $(function(){
    $(".chlicd_icon span").on("click",function(){
      var flag = $(this).attr("class")
      if(flag == "global_checkbox"){
        $(this).removeClass();
      }else{
        $(this).attr('class','global_checkbox')
      }
      showExmple();
    })
    $(".chlibyd_icon span").click(function(){
      var str = "";
      str = '<div class="win_tishi_i"><span>设置成功！</span></div>';
      var sub = $(".chlicd_icon span:first").attr("class");
      var tea = $(".chlicd_icon span:nth-child(3)").attr("class");
      var cla = $(".chlicd_icon span:last").attr("class");
      var subject = sub=="global_checkbox"?true:false;
      var teacher = tea=="global_checkbox"?true:false;
      var classroom = cla=="global_checkbox"?true:false;
      var flagt = 1;
      var object = this;
      $.post("${pageContext.request.contextPath}/settings/Settings_saveRecordNameSetting.do",{subject:subject,teacher:teacher,classroom:classroom},function(data){
        flagt = data.status;
        if(flagt == 0 ){
          $(object).parents(".main_content").append(str);
          $(".win_tishi_i").css({"left":"45%","top":"18%"})
          setTimeout(function () {
            $(".win_tishi_i").remove();
          }, 1000);
        }else{
          $(object).parents(".main_content").append(str);
          $(".win_tishi_i").find("span").html("设置失败！")
          setTimeout(function () {
            $(".win_tishi_i").remove();
          }, 1000);
        }
      });
    })
  })
</script>

