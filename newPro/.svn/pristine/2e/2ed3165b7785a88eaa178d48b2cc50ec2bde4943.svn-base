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
};
function PTZcontrol(v,deviceToken){
    var request = {"token": hostip};
    var json = JSON.parse(v);
    request.hostId = hostid;
    request.deviceToken = deviceToken;
    command[json.requestParam](json.target, json.event, request);
    request.func = json.requestParam;
        if(json.requestParam == "cloudControl") {//云台控制
            console.log("后台发送指令成功")
            message.send(JSON.stringify(request));
        }
};
$(function () {
    document.cookie="xk_flag="+escape("0")+";path=/";
    var names = document.getElementsByName("ScriptableObject");
    for (var i = 0; i < names.length; i++) {
        var url = names[i].getAttribute("url");
        var id = names[i].getAttribute("id");
        var titles = names[i].getAttribute("title").split("---");
        var obj = document.getElementById(id);
        try {
            var channelName = titles[0];
            var mainStream = titles[1];
            var subStream = titles[2];
            var isPTZ = titles[3];
            names[i].setStreamingUrls(mainStream,subStream.toString());
            names[i].play(url);
            names[i].setChannelName(channelName);
            names[i].setRunMode(2);
            //names[i].setSpeed();
            // 监看画面隐藏录制功能
            names[i].setButtonEnable('record',0);
            if(isPTZ == "0"){
                names[i].setPtzEnable(false);
            }
        } catch (e) {
        }
    }
});
function gotoViewClass(currentpage) {
    location.href = "${pageContext.request.contextPath}/viewclass/Viewclass_getHostGroup.do?currentPage="+currentpage;
}