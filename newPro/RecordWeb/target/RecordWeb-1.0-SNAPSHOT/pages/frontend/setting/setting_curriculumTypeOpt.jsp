<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=10; IE=EDGE"/>
    <title>录系统设置 | 集控平台</title>
    <link href="${pageContext.request.contextPath}/css/frontend/main.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/time_celve.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/frontend/record/hpager.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/index.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/main.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/frontend/record/fd-slider.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/schedule.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/common/jquery-1.9.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/layer.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/layer/extend/layer.ext.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/lb_frontend/setting/setting.js"></script>

    <script>
        $(function() {
            // 控制弹窗
            $(".schedule_unselected").click(function() {
                $.layer({
                    shade: [0],
                    maxmin: false,
                    area: ['auto','auto'],
                    dialog: {
                        msg: '您选择的课表类型为:${setting != null && setting.curriculumType==1?"周课表":"学期课表"}'+'<br /><br />'+'之前所导入的课表将会被清空，是否确认？',
                        btns: 2,
                        type: 4,
                        btn: ['确定','取消'],
                        shade: false,
                        yes: function(){
                            var delUrl =rootpath+"/settings/Settings_curriculumDel.do";

                            $.post(delUrl,function(data){

                                if(data.flag) {
                                    window.location.href=rootpath+"/settings/Settings_curriculumTypeOpt.do";
                                    layer.msg(data.msg);
                                } else {
                                    layer.msg(data.msg);
                                }

                            });

                            //$.session.set('key', 'value')
                            layer.close(index);
                        }
                    }
                });
            });
        });

    </script>

</head>
<body>

<div class="public" style="background-color: #f2f2f2;min-width: 1680px;">
    <jsp:include page="/pages/frontend/lb_header.jsp"></jsp:include>
    <div class="set">
        <div class="set_head">
            <div class="settinghead">
                <ul>
                    <li onclick="javascript:jump(0)"><div class="tb"></div>台标</li>
                    <li onclick="javascript:jump(1)"><div class="videoinfo"></div>录像信息</li>
                    <li onclick="javascript:jump(2)"><div class="living"></div>直播</li>
                    <li onclick="javascript:jump(3)"><div class="nas"></div>NAS存储设置</li>
                    <li onclick="javascript:jump(4)"><div class="passuser"></div>录播主机用户名密码设置</li>
                    <li onclick="javascript:jump(5)" ><div class="ftp"></div>FTP设置</li>
                    <li onclick="javascript:jump(6)" ><div class="license"></div>License设置</li>
                    <li onclick="javascript:jump(7)" style="color:#28b779;"><div class="second_nav_selected"></div>课表类型选择</li>
                    <li onclick="javascript:jump(8)" ><div class="second_nav_schedule"></div>课表管理</li>
                    <li onclick="javascript:jump(9)"><div class="second_nav_celve"></div>作息时间策略</li>
                    <li onclick="javascript:jump(10)"><div class="record_name_setting"></div>录制文件名设置</li>
                </ul>
            </div>
        </div>
        <div class="main_content">
            <div class="select_type">
                <p style="font-weight:500;">
                    请先选择您要导入课表的类型：
                </p>
                <p class="current_schedule_type">
                    当前的课表类型为：<span>${setting == null || setting.curriculumType==2?'周课表':'学期课表'}</span>
                </p>
                <div>
                    <button type="button" class="btn_schedule_week ${setting == null || setting.curriculumType==2?'schedule_selected':'schedule_unselected'}" id="week_schedule"><span></span>周课表</button>
                    <button type="button" class="btn_schedule_course ${setting != null && setting.curriculumType!=2?'schedule_selected':'schedule_unselected'}" id="course_schedule"><span></span>学期课表</button>
                </div>
                <div class="schedule_tag">
                    <div class="schedule_week_tag">
                        <p>周课表模式适用于每周课表相同的模式，大部分中、小学适用。</p>
                    </div>
                    <div class="schedule_course_tag">
                        <p>学期课表模式适用于以学期为单位排课的大部分大中职院校和高等院校。</p>
                    </div>
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
