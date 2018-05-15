<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html
PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<!-- scripting.qdoc -->
<head>
    <title>导播</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/jquery-1.9.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/common/jquery.cookie.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/common/screendetail.js"></script>
</head>
<body>

<%    String token = request.getAttribute("token").toString();
    String hostIp = token;
    String recordType = request.getAttribute("recordType").toString();
    String liveAddr = request.getAttribute("liveAddr").toString();
    String isSetNas = request.getAttribute("isSetNas").toString();
    User user = (User) session.getAttribute("user");
    String userId = user.getUserId().toString();
    String hostId = request.getAttribute("hostid").toString();
    String hostname = request.getAttribute("hostName").toString();
    String currentPage = request.getParameter("currentPage");
    //--获取设备支持命令列表
    List<String> supportsList = (List<String>) request.getAttribute("supportsList");
    Map<String, String[]> mediaUrlList = (Map<String, String[]>) request.getAttribute("mediaUrlList");
    for (Map.Entry<String, String[]> entry : mediaUrlList.entrySet()) {
        String key = entry.getKey();
        String[] value = entry.getValue();
%>
<input type="hidden" name="media" id="<%=value[0]%>" value="<%=key%>" isPTZ="<%=value[1]%>"/>
<%
    }
%>
<div class="public">
    <jsp:include page="/pages/frontend/lb_header.jsp"></jsp:include>
    <style>
        .mm_head_option {
            text-indent: 10px;
        }
    </style>
    <div class="mm">
        <div class="mm_head">
            <%--<a href="javascript:location.href='${pageContext.request.contextPath}/viewclass/Viewclass_getHostGroup.do'"><span--%>
            <%--class="mm_nogroup_option"><span class="mm_nogroup_icon"></span>返回</span></a>--%>
            <a href="javascript:gotoViewClass('<%=hostIp%>','false')">
                <span class="mm_head_option"><span class="mm_nogroup_icon"></span>返回</span>
            </a>
            <span class="groupname"><%=hostname%></span>
        </div>

        <div class="obj" style="width: 100%;height:93%;background: #fff;display: block;">
            <object class="directview" name="ScriptableObject" id="ScriptableObject" CLASSID="CLSID:5a22176d-118f-4185-9653-9f98958a6df8"
                    width="100%" height="100%"></object>
        </div>
    </div>
    <div class="foot">
        <jsp:include page="/pages/frontend/footer.jsp"></jsp:include>
    </div>
    <%@include file="../viewclass/websocket.jsp" %>
</div>
<script>

    var recordType = "<%=recordType%>";
    var isSetNas = "<%=isSetNas%>";

    var upLoadService = "http://<%=serviceAddr%>${pageContext.request.contextPath}/upload/Viewclass_upload.do";
    $(function () {
        $(window).resize(function () {
            location.reload();
            var docwidth = document.documentElement.clientWidth;
            var docheight = document.documentElement.clientHeight;
            var names = document.getElementsByName("ScriptableObject");
            for (var i = 0; i < names.length; i++) {
                var url = names[i].getAttribute("url");
                var id = names[i].getAttribute("id");
                try {
                    names[i].sltFlush(docwidth, docheight);
                } catch (e) {

                }
            }
        });
    });
    window.onscroll = function () {
        var docwidth = document.documentElement.clientWidth;
        var docheight = document.documentElement.clientHeight;
        var names = document.getElementsByName("ScriptableObject");
        for (var i = 0; i < names.length; i++) {
            var url = names[i].getAttribute("url");
            var id = names[i].getAttribute("id");
            try {
                names[i].sltFlush(docwidth, docheight);
            } catch (e) {

            }
        }

    };
    liveData = null;
    courseInfo = null;
    _liveData = null;
    var token = "<%=token%>";
    var hostId = "<%=hostId%>";
    var hostIp = "<%=hostIp%>";
    var liveAddr = "<%=liveAddr%>";
    var uid = "<%=userId%>";
    wsUrl = "ws://<%=serviceAddr%>${pageContext.request.contextPath}/webSocket?token=" + token + "&uid=" + uid;
    <%--var directorViewDTOList = '<%=directorViewDTOList%>';--%>
    function Command() {
        this.cloudControl = function (_target, _event, request) {
            if (_target == "Cloud_Up") {
                if (_event == "press") {
                    request.cmdId = "0x002004";//垂直转动
                    request.params = [
                        {"direction": "up"}
                    ];
                } else {
                    request.cmdId = "0";
                }
            } else if (_target == "Cloud_Down") {
                if (_event == "press") {
                    request.cmdId = "0x002004";
                    request.params = [
                        {"direction": "down"}
                    ];
                } else {
                    request.cmdId = "0";
                }
            } else if (_target == "Cloud_Left") {
                if (_event == "press") {
                    request.cmdId = "0x002003";
                    request.params = [
                        {"direction": "left"}
                    ];
                } else {
                    request.cmdId = "0";
                }
            } else if (_target == "Cloud_Right") {
                if (_event == "press") {
                    request.cmdId = "0x002003";
                    request.params = [
                        {"direction": "right"}
                    ];
                } else {
                    request.cmdId = "0";
                }
            } else if (_target == "Cloud_ZoomIn") {//变焦
                if (_event == "press") {
                    request.cmdId = "0x003001";
                    request.params = [
                        {"zoom": "reduction"} //减
                    ];
                } else {
                    request.cmdId = "0";
                }
            } else if (_target == "Cloud_ZoomOut") {//变焦
                if (_event == "press") {
                    request.cmdId = "0x003001";
                    request.params = [
                        {"zoom": "add"} //加
                    ];
                } else {
                    request.cmdId = "0";
                }
            } else if (_target == "Cloud_Zero") {//预置位定位
                //if (_event == "press") {
                request.cmdId = "0x002002";
                request.params = [
                    {"preset": "0"} //0
                ];
//                } else {
//                    request.cmdId = "0";
//                }
            } else if (_target == "Cloud_One") {//预置位定位
                //  if (_event == "press") {
                request.cmdId = "0x002002";
                request.params = [
                    {"preset": "1"} //加
                ];
//                } else {
//                    request.cmdId = "0";
//                }
            } else if (_target == "Cloud_Two") {//预置位定位
                //if (_event == "press") {
                request.cmdId = "0x002002";
                request.params = [
                    {"preset": "2"} //加
                ];
//                } else {
//                    request.cmdId = "0";
//                }
            } else if (_target == "Cloud_Three") {//预置位定位
                //if (_event == "press") {
                request.cmdId = "0x002002";
                request.params = [
                    {"preset": "3"} //加
                ];
//                } else {
//                    request.cmdId = "0";
//                }
            } else if (_target == "Cloud_Four") {//预置位定位
                //if (_event == "press") {
                request.cmdId = "0x002002";
                request.params = [
                    {"preset": "4"} //加
                ];
//                } else {
//                    request.cmdId = "0";
//                }
            } else if (_target == "Cloud_Five") {//预置位定位
                //  if (event == "press") {
                request.cmdId = "0x002002";
                request.params = [
                    {"preset": "5"} //加
                ];
//                } else {
//                    request.cmdId = "0";
//                }
            } else if (_target == "Cloud_Six") {//预置位定位
                // if (event == "press") {
                request.cmdId = "0x002002";
                request.params = [
                    {"preset": "6"} //加
                ];
//                } else {
//                    request.cmdId = "0";
//                }
            } else if (_target == "Cloud_Seven") {//预置位定位
                //  if (event == "press") {
                request.cmdId = "0x002002";
                request.params = [
                    {"preset": "7"} //加
                ];
//                } else {
//                    request.cmdId = "0";
//                }
            } else if (_target == "Cloud_Eight") {//预置位定位
                // if (event == "press") {
                request.cmdId = "0x002002";
                request.params = [
                    {"preset": "8"} //加
                ];
//                } else {
//                    request.cmdId = "0";
//                }
            } else if (_target == "Cloud_Nine") {//预置位定位
                // if (event == "press") {
                request.cmdId = "0x002002";
                request.params = [
                    {"preset": "9"} //加
                ];
//                } else {
//                    request.cmdId = "0";
//                }
            }

        }
        //录制功能
        this.workingMode = function (_target, _event, request) {
            //开始
            if (_event == 'start') {
                request.cmdId = "0x005004";
                request.params = [
                    {
                        "workingMode": _target.workingMode,
                        "recordingMode": _target.recordingMode,
                        "recordType": recordType
                    }
                ];
                //结束
            } else if (_event == 'pause') {
                request.cmdId = "0x005006";
                request.params = [
                    {
                        "workingMode": _target.workingMode,
                        "recordingMode": _target.recordingMode,
                        "recordType": recordType
                    }
                ];
                //结束
            } else if (_event == 'stop') {
                request.cmdId = "0x005007";
//            console.log(_target);
                request.params = [
                    {
                        "workingMode": _target.workingMode,
                        "recordingMode": _target.recordingMode,
                        "closeLiveRoom": _target.closeLiveRoom, //1关闭直播间 0 不关闭
                        "recordType": recordType
                    }
                ];
                //重新开始
            } else if (_event == 'restart') {
                request.cmdId = "0x005013";
                request.params = [
                    {
                        "workingMode": _target.workingMode,
                        "recordingMode": _target.recordingMode,
                        "recordType": recordType
                    }
                ];
            }
            else {//声音
                request.cmdId = "0x005008";
                request.params = [
                    {"value": _target}
                ];
            }
        }

        //单屏幕切换
        this.singleScreen = function (_target, _event, request) {
            request.cmdId = "0x002005";
            request.params = [{"token": request.token, "deviceToken": request.deviceToken}];
        }
        //画中画--分屏
        this.mutilScreen = function (_target, _event, request) {
            request.cmdId = "0x006001";
            request.params = [
                {"value": _target}
            ];
        }
        this.settingMutilScreen = function (_target, _event, request) {
            request.cmdId = "0x006003";
            request.params = [
                {"value": _target}
            ];
        }
        //导播策略
        this.directMode = function (_target, _event, request) {
            //暂时不做处理
            request.cmdId = "0x006002";
            request.params = [
                {"value": _target}
            ];
        }
        //字幕
        this.subTile = function (_target, _event, request) {
            request.cmdId = "0x005014";//字幕设置
            var jsonstring = _target;
            request.params = [
                {
                    "duration": jsonstring.duration,
                    "subTitleName": jsonstring.subTitleName,
                    "backColor": jsonstring.backColor,
                    "transparency": jsonstring.transparency,
                    "fontSize": jsonstring.fontSize,
                    "startTime": jsonstring.startTime,
                    "fontColor": jsonstring.fontColor
                }
            ];
        }
        //视频打点准备
        this.videoDotPrepare = function (_target, _event, request) {
            request.cmdId = "0x005012";//视频打点
        }
        //视频打点
        this.videoDot = function (_target, _event, request) {
            request.cmdId = "0x005008";//视频打点
            var jsonstring = _target;
            request.params = [//DotName  DotTime  Info_type  Info_content
                {"time": jsonstring.dotTime, "keyNote": jsonstring.infoContent}
            ];
        }
        //片头
        this.prelude = function (_target, _event, request) {
            request.cmdId = "0x005009";//片头
            var jsonstring = _target;
            request.params = [
                {
                    "duration": jsonstring.duration,
                    "teacher": jsonstring.teacher,
                    "school": jsonstring.school,
                    "subject": jsonstring.subject,
                    "grade": jsonstring.grade,
                    "backMusic": jsonstring.backMusic,
                    "course": jsonstring.course,
                    "fontSize": jsonstring.fontSize,
                    "preludeName": jsonstring.preludeName,
                    "title": jsonstring.title,
                    "fontColor": jsonstring.fontColor,
                    "picture": jsonstring.picture,
                    "avaliable": jsonstring.avaliable,
                    "recordType": recordType,
                    "backMusicFlag": jsonstring.backMusicFlag,
                    "pictureFlag": jsonstring.pictureFlag
                }
            ];
        }
        //片尾
        this.curtain = function (_target, _event, request) {
            request.cmdId = "0x005010";//片尾
            var jsonstring = _target;
            request.params = [
                {
                    "date": jsonstring.date,
                    "duration": jsonstring.duration,
                    "copyright": jsonstring.copyright,
                    "curtainName": jsonstring.curtainName,
                    "production": jsonstring.production,
                    "backMusic": jsonstring.backMusic,
                    "fontSize": jsonstring.fontSize,
                    "fontColor": jsonstring.fontColor,
                    "picture": jsonstring.picture,
                    "avaliable": jsonstring.avaliable,
                    "recordType": recordType,
                    "backMusicFlag": jsonstring.backMusicFlag,
                    "pictureFlag": jsonstring.pictureFlag
                }
            ];
        }
        //课程信息
        this.courseInfo = function (_target, _event, request) {
            request.cmdId = "0x005011";//课程信息
            //var jsonstring = JSON.parse(_target);
            var jsonstring = _target;
            request.params = [
                {
                    "teacher": jsonstring.teacher,
                    "school": jsonstring.school,
                    "subject": jsonstring.subject,
                    "grade": jsonstring.grade,
                    "course": jsonstring.course,
                    "title": jsonstring.title,
                    "recordStatus": jsonstring.recordStatus
                }
            ];
        }
        this.initLiveRoom = function (_target, _event, request) {
            $.post("${pageContext.request.contextPath}/viewclass/Viewclass_getLiveRoom.do", {"token": token}, function (result) {
                if (typeof(result.flag) == "undefined") {
                    var setInitRoom = {};
                    setInitRoom.requestParam = "initLiveRoom";
                    setInitRoom.event = "";
                    setInitRoom.token = "";
                    if (result.code != "0") {
                        setInitRoom.target = "";
                        ScriptableObject.sltReceiveCommand(JSON.stringify(setInitRoom));
                    } else {
//                        liveData = result.data.data;
                        liveData = result.result;
                        var infoArray = [];
                        for (var i = 0; i < liveData.length; i++) {
                            infoArray.push({id: liveData[i].id, name: liveData[i].room_name});
                        }
                        if (infoArray.length != 0) {
                            setInitRoom.target = infoArray;
                            ScriptableObject.sltReceiveCommand(JSON.stringify(setInitRoom));
                        } else {
                            setInitRoom.target = "";
                            ScriptableObject.sltReceiveCommand(JSON.stringify(setInitRoom));
                        }
                    }
                }

            }, 'json');

        }
        this.setLiveRoom = function (_target, _event, request) {
//            console.log(liveData);
            if (liveData != null) {
                for (var i = 0; i < liveData.length; i++) {
                    if (liveData[i].id == _target) {
                        _liveData = liveData[i];
                        break;
                    }
                }
            }
//            console.log(_liveData);
            var school = courseInfo.school;
            var course = courseInfo.course;
            var subject = courseInfo.subject;
            var grade = courseInfo.grade;
            var teacher = courseInfo.teacher;
            if (teacher == "") {
                teacher = _liveData.teacher;
            }
            //课程信息中的学校与用户服务机构未对应，所以先注释掉
            //start
//            if (school == "") {
//                school = _liveData.teacher_org;
//            }
            //end by wzz
            var liveParams =
            {
                "token": token,
                "videoId": _liveData.id,
                "school": school,
                "title": _liveData.title,
                "course": course,
                "subject": subject,
                "grade": grade,
                "teacher": teacher,
                "code": liveAddr,
                "roomName": _liveData.room_name,
                "broadCast": _liveData.broadcast,
                "videoPassword": _liveData.video_password,
                //从资源平台读取数据再设置回去，重复操作，所以删掉
                //start by wzz
//                "description": _liveData.description,
                //end by wzz
                "startTime": _liveData.starttime,
                "endTime": _liveData.endtime
            };
            $.post("${pageContext.request.contextPath}/viewclass/Viewclass_setLiveRoom.do", liveParams, function (data) {
                if (data.result == "false") {
                    ScriptableObject.sltReceiveCommand("{\"requestParam\":\"workingMode\",\"target\":\"直播设置失败\",\"event\":\"error\",\"token\":\"\"}");
                }
            }, 'json');
        }

    }


    var command = new Command();
    var ScriptableObject;
    /**
     *   巡课播放
     */
    function directorviewPlay() {
        try {
            ScriptableObject = document.getElementById('ScriptableObject');
            ScriptableObject.sltSetUploadService(upLoadService);
            //隐藏所有控制按钮
            ScriptableObject.sltSetFunctionShownState("TransitionEffect", "hide");
            ScriptableObject.sltSetFunctionShownState("Captions", "hide");
            ScriptableObject.sltSetFunctionShownState("Credits", "hide");
            ScriptableObject.sltSetFunctionShownState("VideoPoint", "hide");//视频打点
            ScriptableObject.sltSetFunctionShownState("CourseInfo", "hide");
            ScriptableObject.sltSetFunctionShownState("CreditsContent", "hide");
            ScriptableObject.sltSetFunctionShownState("ScreenSetting", "hide");//
            ScriptableObject.sltSetFunctionShownState("ScreenSettingEntirety", "hide");//分屏
//            ScriptableObject.sltSetFunctionShownState("ScreenSpliterSettings", "hide");//分屏-设置按钮
            ScriptableObject.sltSetFunctionShownState("BroadcastStrategy", "hide");  //导播模式
            ScriptableObject.sltSetFunctionShownState("AvaliableMemoryState", "hide");//

            ScriptableObject.sltSetFunctionShownState("TransitionEffect", "disable");
            ScriptableObject.sltSetFunctionShownState("Captions", "disable");
            ScriptableObject.sltSetFunctionShownState("Credits", "disable");
//            ScriptableObject.sltSetFunctionShownState("VideoPoint", "disable");//视频打点
            ScriptableObject.sltSetFunctionShownState("CourseInfo", "disable");
            ScriptableObject.sltSetFunctionShownState("CreditsContent", "disable");
            ScriptableObject.sltSetFunctionShownState("ScreenSetting", "disable");//
            ScriptableObject.sltSetFunctionShownState("ScreenSettingEntirety", "disable");//分屏
//            ScriptableObject.sltSetFunctionShownState("ScreenSpliterSettings", "disable");//分屏-设置按钮
            ScriptableObject.sltSetFunctionShownState("BroadcastStrategy", "disable");  //导播模式
            ScriptableObject.sltSetFunctionShownState("AvaliableMemoryState", "disable");//

            ScriptableObject.sltSetFunctionShownState("VideoPoint", "disable");//视频打点
//            ScriptableObject.sltSetFunctionShownState("VideoPoint", "show");//视频打点

            <% for(String item : supportsList) {%>
            var supportsItem = "<%=item%>";
            ScriptableObject.sltSetFunctionShownState(supportsItem, "show");
            <%}%>
            /*  if (recordType == 0) {
             //参数1 标识插件控件  TransitionEffect:过渡特效,Captions:字幕,Credits:片头片尾,VideoPoint:视频打点,CourseInfo:课程信息,CreditsContent:片头片尾设置信息,ScreenSetting:版式设置之VGA|学生|学生全景|教师
             // 参数2  0不显示 1显示
             ScriptableObject.sltSetFunctionShownState("TransitionEffect", "hide");
             ScriptableObject.sltSetFunctionShownState("Captions", "hide");
             ScriptableObject.sltSetFunctionShownState("CreditsContent", "hide");
             ScriptableObject.sltSetFunctionShownState("ScreenSetting", "hide");
             } else {
             ScriptableObject.sltSetFunctionShownState("Captions", "hide");
             ScriptableObject.sltSetFunctionShownState("TransitionEffect", "hide");
             }*/
            //页面初始化，按钮全部灰显
            //MovieMode:电影模式,Movide+ResourceMode:电影资源模式,AutoControl:自动控制,HandControl:手动控制,
            //ScreenSettingButton:板式设置按钮,LiveAddress:直播地址,PopBar:悬浮框
//            ScriptableObject.sltSetFunctionShownState("MovieMode", "disable");
//            ScriptableObject.sltSetFunctionShownState("Movide+ResourceMode", "disable");//
////            ScriptableObject.sltSetFunctionShownState("AutoControl", "disable");
////            ScriptableObject.sltSetFunctionShownState("HandControl", "disable");
//            ScriptableObject.sltSetFunctionShownState("ScreenSettingButton", "disable");//板式设置按钮
//            ScriptableObject.sltSetFunctionShownState("PopBar", "disable");//悬浮框
//            ScriptableObject.sltSetFunctionShownState("CourseInfo", "disable"); //课程信息
//            ScriptableObject.sltSetFunctionShownState("Credits", "disable"); //片头片尾
//            ScriptableObject.sltSetFunctionShownState("LiveAddress", "enable"); //直播地址
            ScriptableObject.sltSetLiveAddress(liveAddr);
            ScriptableObject.sltSetNasState(isSetNas);
            ScriptableObject.sltSetDeviceType(recordType);
            ScriptableObject.text = "";
            $("input[name='media']").each(function () {
                //三个参数 sltSetOverviewWidget  name token ptz
                //三个参数 url ,name,token
//            ScriptableObject.sltSetOverviewSource($(this).attr("url"), $(this).attr("value"), $(this).attr("id"),$(this).attr("isPTZ"));
                ScriptableObject.sltSetOverviewWidget($(this).attr("value"), $(this).attr("id"), $(this).attr("isPTZ"));
            });

            setInterval(function () {
                if (ScriptableObject.text.length != 0) {
                    var json = JSON.parse(ScriptableObject.text);
                    ScriptableObject.text = "";
                    var request = {"token": token};
                    request.hostId = hostId;
                    request.deviceToken = json.token;
                    command[json.requestParam](json.target, json.event, request);
                    request.func = json.requestParam;
                    if (json.requestParam != "initLiveRoom" && json.requestParam != "setLiveRoom") {
                        message.send(JSON.stringify(request));
                    }
                }


            }, 100);
        } catch (e) {
//            alert(e);
            if (e.toString().indexOf("sltSetOverviewSource") != -1) {
                //notify("导播台注册失败");
            } else {
                // notify(e.toString());
            }

        }
    }
    var isUserRecord = false;

    $(function () {
        document.cookie="xk_flag="+escape("0")+";path=/";
        message.processOnlineOfflineEvent = function (tokenParams, eventType) {
            if (eventType == "102" && token == tokenParams) {   //离线
                ScriptableObject.sltReceiveCommand("{\"requestParam\":\"workingMode\",\"event\":\"stop\",\"target\":\"\",\"token\":\"\"}");
                ScriptableObject.sltReceiveCommand("{\"requestParam\":\"workingMode\",\"target\":\"当前班级已离线\",\"event\":\"error\",\"token\":\"\"}");
                location.href = "${pageContext.request.contextPath}/viewclass/Viewclass_getHostGroup.do";
            }
            else if (eventType == "101" && token == tokenParams) {
                location.reload();
            }
        }
        message.processOtherEvent = function (tokenParams, eventType, desc) {
            if (token == tokenParams) {
                if (desc.indexOf("event_direct_modechange") != -1) {  //精品录播互斥事件
                    ScriptableObject.sltReceiveCommand("{\"requestParam\":\"workingMode\",\"target\":\"当前班级已取消远程控制,将返回到巡课页面\",\"event\":\"error\",\"token\":\"\"}");
                    location.href = "${pageContext.request.contextPath}/viewclass/Viewclass_getHostGroup.do";
                } else if (desc.indexOf("event_direct_start") != -1) {  //简易录播开始
//                    alert(isUserRecord);
                    if (!isUserRecord) {
//                        ScriptableObject.sltReceiveCommand("{\"requestParam\":\"workingMode\",\"event\":\"start\",\"target\":\"{\"workingMode\":\"workingModeRecording\",\"recordingMode\":\"recordingModeMovie\",\"startTime\":\"1\"}\",\"token\":\"\"}");
                    }
                    isUserRecord = false;
                } else if (desc.indexOf("event_direct_stop") != -1) {  //简易录播停止
//                    ScriptableObject.sltReceiveCommand("{\"requestParam\":\"workingMode\",\"event\":\"stop\",\"target\":\"\",\"token\":\"\"}");
                } else if (desc.indexOf("event_disk_avaliable") != -1) {
                    var value = desc.split("@@")[0];
                    ScriptableObject.sltSetFunctionShownState("AvaliableMemory", value);
                } else if (desc.indexOf("event_direct_pause") != -1) {
//                    ScriptableObject.sltReceiveCommand("{\"requestParam\":\"workingMode\",\"event\":\"pause\",\"target\":\"{\"workingMode\":\"workingModeRecording\",\"recordingMode\":\"recordingModeMovie\"}\",\"token\":\"\"}");
                }
                else if (desc.indexOf("event_direct_continue") != -1 || desc.indexOf("event_direct_resume") != -1) {
//                    ScriptableObject.sltReceiveCommand("{\"requestParam\":\"workingMode\",\"event\":\"restart\",\"target\":\"{\"workingMode\":\"workingModeRecording\",\"recordingMode\":\"recordingModeMovie\"}\",\"token\":\"\"}");
                }
                /**
                 *其它人在使用导播，当前用户返回到巡课页面
                 */
                if (eventType == "100") {
                    gotoViewClass(hostIp, "true");
                }
            }
        }
        message.processResponse = function (tokenParams, eventType, desc) {
//            alert(desc)
            if (token == tokenParams) {
//                var directorViewJson = eval("(" + desc + ")");
                //错误处理
                if (eventType == '0x000000') {
                    ScriptableObject.sltReceiveCommand(desc);
                    return;
                }
                //视频打点获取时间
                else if (eventType == '0x005012') {
                    ScriptableObject.sltReceiveCommand(desc);
                }
                //成功开始录制
                else if (eventType == '0x005004') {//录制
                    if (desc == "false") {
                        ScriptableObject.sltReceiveCommand("{\"requestParam\":\"workingMode\",\"target\":\"开启直播失败\",\"event\":\"error\",\"token\":\"\"}");
                        return;
                    }
                    isUserRecord = true;
                    ScriptableObject.sltReceiveCommand(desc);
                } else if (eventType == '0x005007') {//结束
                    <%--if (desc.indexOf("workingModeLiveCasting") != -1 && _liveData != null) {//直播停止通知电视台--%>
                    <%--var liveParamJson = {};--%>
                    <%--liveParamJson.videoId = _liveData.videoid;--%>
                    <%--liveParamJson.title = _liveData.title;--%>
                    <%--liveParamJson.roomName = _liveData.room_name;--%>
                    <%--liveParamJson.broadCast = _liveData.broadcast;--%>
                    <%--liveParamJson.videoPassword = _liveData.video_password;--%>
                    <%--liveParamJson.description = _liveData.description;--%>
                    <%--$.post("${pageContext.request.contextPath}/viewclass/Viewclass_closeLiveRoom.do", liveParamJson, function (result) {--%>

                    <%--}, 'json');--%>
                    <%--}--%>
                    ScriptableObject.sltReceiveCommand(desc);
                }
                else if (eventType == '0x005006') {//暂停
                    ScriptableObject.sltReceiveCommand(desc);
                } else if (eventType == '0x005013') {//重新开始
                    isUserRecord = true;
                    ScriptableObject.sltReceiveCommand(desc);
                }
                else if (eventType == '0x002005') {//通道切换
                    ScriptableObject.sltReceiveCommand(desc);
                }
                else if (eventType == '0x006001') {//分屏
                    ScriptableObject.sltReceiveCommand(desc);
                }
                else if (eventType == '0x006002') {//导播策略
                    ScriptableObject.sltReceiveCommand(desc);
                }
                else if (eventType == '0x005009') {//片头
                    ScriptableObject.sltReceiveCommand(desc);
                }
                else if (eventType == '0x005010') {//片尾
                    ScriptableObject.sltReceiveCommand(desc);
                }
                else if (eventType == '0x005011') {//课程
                    ScriptableObject.sltReceiveCommand(desc);
                }
            }
        }
        directorviewPlay();
        $.post("${pageContext.request.contextPath}/viewclass/Viewclass_getCameraUrl.do", {
            hostid: hostId,
            hostIp: hostIp,
            viewClassCameraName: ""
        }, function (data) {
            if (data) {
                for (var i = 0; i < data.length; i++) {
                    ScriptableObject.sltSetOverviewSource(data[i].url, data[i].name, data[i].token);
                }
                ScriptableObject.sltSetFunctionShownState("PopBar", "enable");
                ScriptableObject.sltSetFunctionShownState("MovieMode", "enable");
                ScriptableObject.sltSetFunctionShownState("Movide+ResourceMode", "enable");
                <%--$.post("${pageContext.request.contextPath}/viewclass/Viewclass_getCurrentToken.do", {recordType:recordType,hostIp: hostIp}, function (data) {--%>
                <%--if (data.requestParam.length != 0) {--%>
                <%--ScriptableObject.sltReceiveCommand(JSON.stringify(data));--%>
                <%--}--%>
                <%--}, 'json');--%>
            }
        }, 'json');
        $.post("${pageContext.request.contextPath}/viewclass/Viewclass_getRecordingStatus.do", {
            hostIp: hostIp,
            viewClassCameraName: ""
        }, function (data) {
            if (data.requestParam.length != 0) {
                ScriptableObject.sltReceiveCommand(JSON.stringify(data));
            }
        }, 'json');
        $.post("${pageContext.request.contextPath}/viewclass/Viewclass_getAllLayout.do", {hostIp: hostIp}, function (data) {
            if (data.requestParam.length != 0) {
                ScriptableObject.sltReceiveCommand(JSON.stringify(data));
            }
        }, 'json');
        $.post("${pageContext.request.contextPath}/viewclass/Viewclass_getDirectMode.do", {hostIp: hostIp}, function (data) {
            if (data.requestParam.length != 0) {
                ScriptableObject.sltReceiveCommand(JSON.stringify(data));
//                ScriptableObject.sltSetFunctionShownState("AutoControl", "enable");
//                ScriptableObject.sltSetFunctionShownState("HandControl", "enable");
            }
        }, 'json');
        $.post("${pageContext.request.contextPath}/viewclass/Viewclass_getCourseInfo.do", {hostIp: hostIp}, function (data) {
            if (data.requestParam.length != 0) {
                courseInfo = data.target;
                ScriptableObject.sltReceiveCommand(JSON.stringify(data));
            }
        }, 'json');
        $.post("${pageContext.request.contextPath}/viewclass/Viewclass_getTitleVideo.do", {hostIp: hostIp}, function (data) {
            if (data.requestParam.length != 0) {
                ScriptableObject.sltReceiveCommand(JSON.stringify(data));
            }
        }, 'json');
        $.post("${pageContext.request.contextPath}/viewclass/Viewclass_getEndingVideo.do", {hostIp: hostIp}, function (data) {
            if (data.requestParam.length != 0) {
                ScriptableObject.sltReceiveCommand(JSON.stringify(data));
            }
        }, 'json');
    });


    function gotoViewClass(hostIp, setBackground) {
        if (recordType == "1") {
            //精品录播
            $.post("${pageContext.request.contextPath}/viewclass/Viewclass_gotoViewClass.do", {
                hostIp: hostIp,
                setBackground: setBackground
            }, function (data) {
//                history.back();
                location.href = "${pageContext.request.contextPath}/viewclass/Viewclass_getHostGroup.do?currentPage="+<%=currentPage%>;
            }, 'json');
        } else {
//            history.back();
            location.href = "${pageContext.request.contextPath}/viewclass/Viewclass_getHostGroup.do?currentPage="+<%=currentPage%>;
        }
    }


</script>

</body>
</html>