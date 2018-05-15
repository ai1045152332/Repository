package com.honghe.recordweb.service.frontend.devicemanager.message;

import com.honghe.device.sdk.DeviceServiceClient;
import com.honghe.recordhibernate.entity.Setting;
import com.honghe.recordweb.exception.FrontException;
import com.honghe.recordweb.exception.ProgramException;
import com.honghe.recordweb.service.frontend.device.DeviceService;
import com.honghe.recordweb.service.frontend.devicemanager.Constant;
import com.honghe.recordweb.service.frontend.devicemanager.HostDevice;
import com.honghe.recordweb.service.frontend.devicemanager.NVRCommand;
import com.honghe.recordweb.service.frontend.ftp.Ftp4jService;
import com.honghe.recordweb.service.frontend.hostgroup.HostgroupService;
import com.honghe.recordweb.service.frontend.liveplan.LiveplanService;
import com.honghe.recordweb.service.frontend.news.ConfigUtil;
import com.honghe.recordweb.service.frontend.settings.SettingsService;
import com.honghe.recordweb.util.CacheUtil;
import com.honghe.recordweb.util.CommonName;
import com.honghe.recordweb.util.IOUtil;
import it.sauronsoftware.ftp4j.FTPClient;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Connection;
import org.springframework.web.context.ContextLoaderListener;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OperatorMessage {
    private static DeviceService deviceService = (DeviceService) ContextLoaderListener.getCurrentWebApplicationContext().getBean(DeviceService.class);
    private static Ftp4jService ftp4jService = (Ftp4jService) ContextLoaderListener.getCurrentWebApplicationContext().getBean(Ftp4jService.class);
    private static HostgroupService hostgroupService = (HostgroupService) ContextLoaderListener.getCurrentWebApplicationContext().getBean(HostgroupService.class);
    private static SettingsService settingsService = (SettingsService) ContextLoaderListener.getCurrentWebApplicationContext().getBean(SettingsService.class);
    private static HostDevice hostDevice = (HostDevice) ContextLoaderListener.getCurrentWebApplicationContext().getBean(HostDevice.class);
    private static String uploadPath;//= java.net.URLDecoder.decode(OperatorMessage.class.getResource("/").getPath(),"utf-8").replaceAll("WEB-INF/classes/", "") + "upload/";
    private NVRCommand nvrCommand;
    private static final String DEVICE_CMD_OPERATION = CommonName.METHOD_TYPE_RECORDCOMMAND;
    private static LiveplanService liveplanService = (LiveplanService) ContextLoaderListener.getCurrentWebApplicationContext().getBean(LiveplanService.class);
    static {
        try {
            uploadPath = java.net.URLDecoder.decode(OperatorMessage.class.getResource("/").getPath(), "utf-8").replaceAll("WEB-INF/classes/", "") + "upload/";
        } catch (Exception e) {
            uploadPath = "";
        }
        File file = new File(uploadPath);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public OperatorMessage(NVRCommand nvrCommand) {
        this.nvrCommand = nvrCommand;
    }

    public List<Map<String, Object>> cloudControl(String hostIp, String hostId, String cmdId, String referenceToken, JSONArray params) throws Exception {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> result = new HashMap<>();
        if (cmdId.equals("0x002004"))//垂直转动
        {
            String direction = params.getJSONObject(0).getString("direction");
            float y = 0.5F;
            if (direction.equals("down")) {
                y = -0.5F;
            }
            Map<String, String> _params = new HashMap<>();
            _params.put("ip", hostIp);
            _params.put("timeout", "1000");
            _params.put("x", String.valueOf(0.0F));
            _params.put("y", String.valueOf(y));
            _params.put("profileToken", String.valueOf(referenceToken));
            _params.put("z", String.valueOf(0.0F));
            result.put("method", DeviceServiceClient.Method.Device_HhrecCommand_PTZStartMove);
            result.put("cmdOp", DEVICE_CMD_OPERATION);
            result.put("params", _params);
        } else if (cmdId.equals("0x002003"))//水平转动
        {
            String direction = params.getJSONObject(0).getString("direction");
            float x = 0.5F;
            if (direction.equals("left")) {
                x = -0.5F;
            }
            Map<String, String> _params = new HashMap<>();
            _params.put("ip", hostIp);
            _params.put("timeout", "1000");
            _params.put("x", String.valueOf(x));
            _params.put("y", String.valueOf(0.0F));
            _params.put("profileToken", String.valueOf(referenceToken));
            _params.put("z", String.valueOf(0.0F));
            result.put("method", DeviceServiceClient.Method.Device_HhrecCommand_PTZStartMove);
            result.put("cmdOp", DEVICE_CMD_OPERATION);
            result.put("params", _params);
        } else if (cmdId.equals("0x003001"))//镜头变焦
        {
            float z = 0.5F;
            String zoom = params.getJSONObject(0).getString("zoom");
            if (zoom.equals("reduction")) {//减一次变多少
                z = -0.5F;
            }
//            } else if (!zoom.equals("add")) {
//                //先获取当镜头变焦倍数再加一个倍数
//            }
            Map<String, String> _params = new HashMap<>();
            _params.put("ip", hostIp);
            _params.put("timeout", "1000");
            _params.put("x", String.valueOf(0.0F));
            _params.put("y", String.valueOf(0.0F));
            _params.put("profileToken", String.valueOf(referenceToken));
            _params.put("z", String.valueOf(z));
            result.put("method", DeviceServiceClient.Method.Device_HhrecCommand_PTZStartMove);
            result.put("cmdOp", DEVICE_CMD_OPERATION);
            result.put("params", _params);
        } else if (cmdId.equals("0x002002")) //预置位定位
        {
            String token = params.getJSONObject(0).getString("preset");
            String devicetoken = referenceToken.toString();
            // operationRequest = new OperationRequestGotoPreset(devicetoken, token);
            Map<String, String> _params = new HashMap<>();
            _params.put("ip", hostIp);
            _params.put("profileToken", devicetoken);
            _params.put("presetToken", token);
            result.put("method", DeviceServiceClient.Method.Device_HhrecCommand_SetPresets);
            result.put("cmdOp", DEVICE_CMD_OPERATION);
            result.put("params", _params);

        } else {
            Map<String, String> _params = new HashMap<>();
            _params.put("ip", hostIp);
            _params.put("mediaToken", String.valueOf(referenceToken));
            result.put("method", DeviceServiceClient.Method.Device_HhrecCommand_PTZStopMove);
            result.put("cmdOp", DEVICE_CMD_OPERATION);
            result.put("params", _params);
        }
        list.add(result);
        return list;
    }

    public List<Map<String, Object>> singleScreen(String hostIp, String hostId, String cmdId, String referenceToken, JSONArray params) throws Exception {
        List<Map<String, Object>> list = new ArrayList<>();
        String deviceToken = referenceToken;
        String connect_param = settingsService.getConnect_param(hostId);

        List<Object[]> deviceConfigList = settingsService.getDeviceConfigList();
        if ((deviceConfigList != null) && (!deviceConfigList.isEmpty())) {

            for (Object[] deviceConfig : deviceConfigList) {
                String mainToken = deviceConfig[2].toString();
                String subToken = deviceConfig[3].toString();
                String connectparam = deviceConfig[0].toString();
                if ((deviceToken.equals(mainToken)) || (deviceToken.equals(subToken)) && connect_param.equals(connectparam)) {
                    deviceToken = mainToken;
                    break;
                }
            }
        }

        Map<String, Object> result = new HashMap<>();
        Map<String, String> _params = new HashMap<>();
        _params.put("ip", hostIp);
        _params.put("ext", "");
        _params.put("deviceToken", deviceToken);
        result.put("params", _params);
        result.put("method", DeviceServiceClient.Method.Device_HhrecCommand_SetLayoutConfigurations);
        result.put("cmdOp", DEVICE_CMD_OPERATION);
        if (result != null && !result.isEmpty()) {
            list.add(result);
        }
        return list;

    }

    private Map<String, Object> getRecordOperationRequest(String hostId, String recordingMode, String ext, String hostIp, String recordType) throws Exception {
        List<Object[]> deviceList = deviceService.getDeviceListService(Integer.parseInt(hostId));
        List<String> token = new ArrayList();
        if (recordingMode.equals("recordingModeMovie")) {//电影模式
            for (Object[] objArray : deviceList) {
                if (objArray[1].equals("电影模式")) {
                    token.add(objArray[3].toString());
                }
            }
        } else {
            for (Object[] objArray : deviceList) {
                token.add(objArray[3].toString());
            }
        }
        if (token.isEmpty()) {
            throw new ProgramException("getRecordOperationRequest method token is empty");
        }
        Map<String, Object> result = new HashMap<>();
        result.put("method", DeviceServiceClient.Method.Device_HhrecCommand_StartRecord);
        Map<String, String> params = new HashMap<>();
        params.put("ip", hostIp);
        params.put("recordType", recordType);
        params.put("hostId", hostId);
        params.put("referenceToken", token.toString().replaceAll("\\[|\\]", ""));
        params.put("ext", ext);
        result.put("params", params);
        result.put("cmdOp", DEVICE_CMD_OPERATION);
        return result;
    }

    public List<Map<String, Object>> workingMode(String hostIp, String hostId, String cmdId, String referenceToken, JSONArray params) throws Exception {
        List<Map<String, Object>> result = new ArrayList<>();
        String workingMode = params.getJSONObject(0).getString("workingMode");

        String recordingMode = params.getJSONObject(0).getString("recordingMode");
        String recordType = params.getJSONObject(0).getString("recordType");
        String ext = "";
        if (recordType.equals("1")) {
            ext = "mode_record";
            //精品录播
        }
        String ext1 = "";
        Map<String, Object> info = nvrCommand.getNvr().getExtensionInfo(hostIp);
        if (info.isEmpty()) throw new FrontException("班级已经下线");
        String hostName = info.get("host_name").toString();
        String hostDspec = info.get("dspec_id").toString();
        String liveAddr = "";
        Setting setting = settingsService.getSetting();
        if (setting != null) {
            liveAddr = setting.getLiveAddr();
        }
        if (recordType.equals("1")) {
            ext1 = "mode_record_live:" + liveAddr + "/" + DigestUtils.md5Hex(hostName);
        }
        if (cmdId.equals("0x005004")) {
            Map<String, Object> courseOperationRequest = new HashMap<>();
            Map<String, JSONObject> courseInfoCache = settingsService.getCourseInfoCache();
            if (courseInfoCache != null && courseInfoCache.containsKey(hostIp)) {
                courseOperationRequest = this.getSetCourseInfo(courseInfoCache.get(hostIp), hostIp);
                params.getJSONObject(0).putAll(courseInfoCache.get(hostIp));
                courseInfoCache.remove(hostIp);
            }
            if (workingMode.equals("workingModeRecording")) {
                if (courseOperationRequest != null && !courseOperationRequest.isEmpty()) {
                    // return new OperationRequest[]{this.getRecordOperationRequest(hostId, recordingMode, ext,hostIp,recordType), courseOperationRequest};
                    result.add(this.getRecordOperationRequest(hostId, recordingMode, ext, hostIp, recordType));
                    result.add(courseOperationRequest);
                } else {
                    // return new OperationRequest[]{this.getRecordOperationRequest(hostId, recordingMode, ext, hostIp,recordType)};
                    result.add(this.getRecordOperationRequest(hostId, recordingMode, ext, hostIp, recordType));
                }
            } else if (workingMode.equals("workingModeLiveCasting")) {
//                if (liveAddr == null || liveAddr.equals("")) {
//                    throw new FrontException("请设置直播服务器地址");
//                }
//                if(startLiveRoom(params,nvrCommand.getNvr().getToken(hostIp))){
                //return new OperationRequest[]{new OperationRequestSetLive("false")};
                if (!startLiveRoom(params, hostIp)) {
                    Map<String, Object> falseMap = new HashMap<>();
                    falseMap.put("method", null);
                    falseMap.put("params", new HashMap<>());
                    falseMap.put("cmdOp", "falseResult");
                    result.add(falseMap);
                }
                // return new OperationRequest[]{new OperationRequestStartStreaming("RTMP", liveAddr + "/" + DigestUtils.md5Hex(hostName))};
                Map<String, Object> _result = new HashMap<>();
                _result.put("method", DeviceServiceClient.Method.Device_HhrecCommand_StartStreaming);
                Map<String, String> _params = new HashMap<>();
                _params.put("ip", hostIp);
                //wbox补全地址前缀 by wuzhenzhen 20170907
                if(hostDspec.equals("31"))
                {
                    _params.put("address", "rtmp://" + liveAddr + "/" + DigestUtils.md5Hex(hostName));
                }
                else {
                    _params.put("address", liveAddr + "/" + DigestUtils.md5Hex(hostName));
                }
                _params.put("protocol", "RTMP");
                _result.put("params", _params);
                _result.put("cmdOp", DEVICE_CMD_OPERATION);
                result.add(_result);
            } else {
                if (!startLiveRoom(params, hostIp)) {
                    Map<String, Object> falseMap = new HashMap<>();
                    falseMap.put("method", null);
                    falseMap.put("params", new HashMap<>());
                    falseMap.put("cmdOp", "falseResult");
                    result.add(falseMap);
                }
                if (courseOperationRequest != null && !courseOperationRequest.isEmpty()) {
                    // return new OperationRequest[]{this.getRecordOperationRequest(hostId, recordingMode, ext, local, nas), new OperationRequestStartStreaming("RTMP", liveAddr + "/" + DigestUtils.md5Hex(hostName)), courseOperationRequest};
                    result.add(this.getRecordOperationRequest(hostId, recordingMode, ext1, hostIp, recordType));
                    Map<String, Object> _result = new HashMap<>();
                    _result.put("method", DeviceServiceClient.Method.Device_HhrecCommand_StartStreaming);
                    Map<String, String> _params = new HashMap<>();
                    _params.put("ip", hostIp);
                    //wbox补全地址前缀 by wuzhenzhen 20170907
                    if(hostDspec.equals("31"))
                    {
                        _params.put("address", "rtmp://" + liveAddr + "/" + DigestUtils.md5Hex(hostName));
                    }
                    else {
                        _params.put("address", liveAddr + "/" + DigestUtils.md5Hex(hostName));
                    }
                    _params.put("protocol", "RTMP");
                    _params.put("ext", ext1);
                    _result.put("params", _params);
                    _result.put("cmdOp", DEVICE_CMD_OPERATION);
                    result.add(_result);
                    result.add(courseOperationRequest);
                } else {
                    // return new OperationRequest[]{this.getRecordOperationRequest(hostId, recordingMode, ext, local, nas), new OperationRequestStartStreaming("RTMP", liveAddr + "/" + DigestUtils.md5Hex(hostName))};
                    result.add(this.getRecordOperationRequest(hostId, recordingMode, ext1, hostIp, recordType));
                    Map<String, Object> _result = new HashMap<>();
                    _result.put("method", DeviceServiceClient.Method.Device_HhrecCommand_StartStreaming);
                    Map<String, String> _params = new HashMap<>();
                    _params.put("ip", hostIp);
                    //wbox补全地址前缀 by wuzhenzhen 20170907
                    if(hostDspec.equals("31"))
                    {
                        _params.put("address", "rtmp://" + liveAddr + "/" + DigestUtils.md5Hex(hostName));
                    }
                    else {
                        _params.put("address", liveAddr + "/" + DigestUtils.md5Hex(hostName));
                    }
                    _params.put("protocol", "RTMP");
                    _params.put("ext", ext1);
                    _result.put("params", _params);
                    _result.put("cmdOp", DEVICE_CMD_OPERATION);
                    result.add(_result);
                }

            }
        } else if (cmdId.equals("0x005006")) {
            if (workingMode.equals("workingModeRecording")) {
                Map<String, Object> _result = new HashMap<>();
                _result.put("method", DeviceServiceClient.Method.Device_HhrecCommand_PauseRecording);
                Map<String, String> _params = new HashMap<>();
                _params.put("ip", hostIp);
                _params.put("ext", ext);
                _result.put("params", _params);
                _result.put("cmdOp", DEVICE_CMD_OPERATION);
                result.add(_result);
            } else if (workingMode.equals("workingModeLiveCasting")) {//直播
                Map<String, Object> _result = new HashMap<>();
                _result.put("method", DeviceServiceClient.Method.Device_HhrecCommand_PauseStreaming);
                Map<String, String> _params = new HashMap<>();
                _params.put("ip", hostIp);
                _params.put("ext", ext);
                _result.put("params", _params);
                _result.put("cmdOp", DEVICE_CMD_OPERATION);
                result.add(_result);
            } else {//录制+直播
                Map<String, Object> _result = new HashMap<>();
                _result.put("method", DeviceServiceClient.Method.Device_HhrecCommand_PauseRecording);
                Map<String, String> _params = new HashMap<>();
                _params.put("ip", hostIp);
                _params.put("ext", ext1);
                _result.put("params", _params);
                _result.put("cmdOp", DEVICE_CMD_OPERATION);
                result.add(_result);
                Map hostInfo = hostgroupService.getHostInfoById(hostId);
                String hostFactory = hostInfo.get("host_factory").toString();
                if (!hostFactory.equals("Arec")) {
                    Map<String, Object> _result1 = new HashMap<>();
                    _result1.put("method", DeviceServiceClient.Method.Device_HhrecCommand_PauseStreaming);
                    Map<String, String> _params1 = new HashMap<>();
                    _params1.put("ip", hostIp);
                    _params.put("ext", ext1);
                    _result1.put("params", _params);
                    _result1.put("cmdOp", DEVICE_CMD_OPERATION);
                    result.add(_result1);
                }
            }

        } else if (cmdId.equals("0x005007")) {
            if (workingMode.equals("workingModeRecording")) {
                Map<String, Object> _result = new HashMap<>();
                _result.put("method", DeviceServiceClient.Method.Device_HhrecCommand_StopRecord);
                Map<String, String> _params = new HashMap<>();
                _params.put("ip", hostIp);
                _params.put("ext", ext);
                _result.put("params", _params);
                _result.put("cmdOp", DEVICE_CMD_OPERATION);
                result.add(_result);
            } else if (workingMode.equals("workingModeLiveCasting")) {//直播
                String _token = hostIp;
                if (CacheUtil.contain(_token)) {
                    this.closeLiveRoom(CacheUtil.get(_token)[0], params.getJSONObject(0).getString("closeLiveRoom"), _token);
                }
                Map<String, Object> _result = new HashMap<>();
                _result.put("method", DeviceServiceClient.Method.Device_HhrecCommand_StopStreaming);
                Map<String, String> _params = new HashMap<>();
                _params.put("ip", hostIp);
//                _params.put("ext","");
                _result.put("params", _params);
                _result.put("cmdOp", DEVICE_CMD_OPERATION);
                result.add(_result);
            } else {//录制+直播
                String _token = hostIp;
                if (CacheUtil.contain(_token)) {
                    this.closeLiveRoom(CacheUtil.get(_token)[0], params.getJSONObject(0).getString("closeLiveRoom"), _token);
                }
                Map<String, Object> _result = new HashMap<>();
                _result.put("method", DeviceServiceClient.Method.Device_HhrecCommand_StopRecord);
                Map<String, String> _params = new HashMap<>();
                _params.put("ip", hostIp);
                _params.put("ext", ext1);
                _result.put("params", _params);
                _result.put("cmdOp", DEVICE_CMD_OPERATION);
                result.add(_result);
                Map<String, Object> _result1 = new HashMap<>();
                _result1.put("method", DeviceServiceClient.Method.Device_HhrecCommand_StopStreaming);
                Map<String, String> _params1 = new HashMap<>();
                _params1.put("ip", hostIp);
                _params.put("ext", ext1);
                _result1.put("params", _params);
                _result1.put("cmdOp", DEVICE_CMD_OPERATION);
                result.add(_result1);
            }
        } else if (cmdId.equals("0x005008")) { //已无效
           /* String value = params.getJSONObject(0).getString("value");
            return new OperationRequest[]{new OperationRequestSetVolume(Integer.parseInt(value))};*/
        }
        //暂停后重新开始录制或直播
        else if (cmdId.equals("0x005013")) {
            if (workingMode.equals("workingModeRecording")) {
                Map<String, Object> _result = new HashMap<>();
                _result.put("method", DeviceServiceClient.Method.Device_HhrecCommand_RestartRecording);
                Map<String, String> _params = new HashMap<>();
                _params.put("ip", hostIp);
                _params.put("ext", "");
                _result.put("params", _params);
                _result.put("cmdOp", DEVICE_CMD_OPERATION);
                result.add(_result);

            } else if (workingMode.equals("workingModeLiveCasting")) {//直播
                Map<String, Object> _result = new HashMap<>();
                _result.put("method", DeviceServiceClient.Method.Device_HhrecCommand_RestartStreaming);
                Map<String, String> _params = new HashMap<>();
                _params.put("ip", hostIp);
                //wbox补全地址前缀 by wuzhenzhen 20170907
                if(hostDspec.equals("31"))
                {
                    _params.put("address", "rtmp://" + liveAddr + "/" + DigestUtils.md5Hex(hostName));
                }
                else {
                    _params.put("address", liveAddr + "/" + DigestUtils.md5Hex(hostName));
                }
                _params.put("protocol", "RTMP");
                _result.put("params", _params);
                _result.put("cmdOp", DEVICE_CMD_OPERATION);
                result.add(_result);
            } else {//录制+直播
                // return new OperationRequest[]{new OperationRequestPlayRecording(), new OperationRequestPlayStreaming()};
                //注释掉小侯写的，因为调用的是开始录制
                // result.add(this.getRecordOperationRequest(hostId, recordingMode, ext, hostIp,recordType));
                Map<String, Object> _result = new HashMap<>();
                _result.put("method", DeviceServiceClient.Method.Device_HhrecCommand_RestartRecording);
                Map<String, String> _params = new HashMap<>();
                _params.put("ip", hostIp);
                _params.put("ext", ext1);
                _result.put("params", _params);
                _result.put("cmdOp", DEVICE_CMD_OPERATION);
                result.add(_result);
                Map<String, Object> _result1 = new HashMap<>();
                _result1.put("method", DeviceServiceClient.Method.Device_HhrecCommand_RestartStreaming);
                Map<String, String> _params1 = new HashMap<>();
                _params1.put("ip", hostIp);
                //wbox补全地址前缀 by wuzhenzhen 20170907
                if(hostDspec.equals("31"))
                {
                    _params.put("address", "rtmp://" + liveAddr + "/" + DigestUtils.md5Hex(hostName));
                }
                else {
                    _params1.put("address", liveAddr + "/" + DigestUtils.md5Hex(hostName));
                }
                _params1.put("protocol", "RTMP");
                _params.put("ext", ext1);
                _result1.put("params", _params1);
                _result1.put("cmdOp", DEVICE_CMD_OPERATION);
                result.add(_result1);
            }
        }
        return result;
    }

    /**
     * 导播策略
     *
     * @param hostIp
     * @param hostId
     * @param cmdId
     * @param referenceToken
     * @param params
     * @return
     */
    public List<Map<String, Object>> directMode(String hostIp, String hostId, String cmdId, String referenceToken, JSONArray params) throws Exception {
        List<Map<String, Object>> result = new ArrayList<>();
        if (cmdId.equals("0x006002")) {
            String value = params.getJSONObject(0).getString("value");
            String isStart = "";
            if (value.equals("directModeAuto")) {
                isStart = "0";
            } else {
                isStart = "1";
            }
            Map<String, Object> directMap = new HashMap<>();
            directMap.put("method", DeviceServiceClient.Method.Device_HhrecCommand_SetBackgroundDirectMode);
            Map<String, String> _params = new HashMap<>();
            _params.put("ip", hostIp);
            _params.put("isStart", isStart);
            _params.put("ext", "");
            directMap.put("params", _params);
            directMap.put("cmdOp", DEVICE_CMD_OPERATION);
            result.add(directMap);
        }
        return result;
    }

    /**
     * 字幕
     *
     * @param cmdId
     * @param referenceToken
     * @param params
     * @return
     */
    //public OperationRequest[] subTile(String hostIp, String hostId, String cmdId, ReferenceToken referenceToken, JSONArray params) {
    public List<Map<String, Object>> subTile(String hostIp, String hostId, String cmdId, String referenceToken, JSONArray params) {
//        try {
//            if (cmdId.equals("0x005014")) {
//                if (hostId == null) {
//                    return null;
//                }
//                String ftp = hostIp;
//                String ftpaddr = "/NVRSettings/Captions/";
//
//                String imagesaddr = "http://" + InetAddress.getLocalHost().getHostAddress() + ":8080/image/frontend/n_logo.png";
//                Host host = hostgroupService.HostInfoService(hostId);
//                String name = host.getHostUserName();
//                String pass = host.getHostPassWord();
//                FTPClient ftpClient = ftp4jService.run(ftp, 21, name, pass);
//                if (ftpClient != null) {
//                    ftp4jService.uploadFile(ftpClient, imagesaddr, ftpaddr);
//                    String CaptionPath = "/NVRSettings/Captions/n_logo.png";//字幕存储的相对路径。
//                    String Location = params.getJSONObject(0).getString("duration");//位置代码，该字符串在GlobalDefine可以调用
//                    String subTitleName = params.getJSONObject(0).getString("subTitleName");
//                    String backColor = params.getJSONObject(0).getString("backColor");
//                    String transparency = params.getJSONObject(0).getString("transparency");
//                    String fontSize = params.getJSONObject(0).getString("fontSize");
//                    String startTime = params.getJSONObject(0).getString("startTime");
//                    String fontColor = params.getJSONObject(0).getString("fontColor");
//                    return new OperationRequest[]{new OperationRequestSetCaption(CaptionPath, Location)};
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
        return null;
    }

    /**
     * 视频打点准备
     *
     * @param cmdId
     * @param referenceToken
     * @param params
     * @return
     */
  /*  public OperationRequest[] videoDotPrepare(String hostIp, String hostId, String cmdId, ReferenceToken referenceToken, JSONArray params) throws Exception {
        return new OperationRequest[]{new OperationRequestGetRecordingStatus()};
    }*/
    public List<Map<String, Object>> videoDotPrepare(String hostIp, String hostId, String cmdId, String referenceToken, JSONArray params) throws Exception {
        List<Map<String, Object>> resultList = new ArrayList<>();
        Map<String, Object> _resultMap = new HashMap<>();
        _resultMap.put("method", DeviceServiceClient.Method.Device_HhrecCommand_GetRecordingStatus);
        _resultMap.put("cmdOp", DEVICE_CMD_OPERATION);
        Map<String, String> _params = new HashMap<>();
        _params.put("ip", hostIp);
        _params.put("ext", "mode_record");
        _resultMap.put("params", _params);
        resultList.add(_resultMap);
        return resultList;
    }

    /**
     * 视频打点
     *
     * @param cmdId
     * @param referenceToken
     * @param params
     * @return
     */
    public List<Map<String, Object>> videoDot(String hostIp, String hostId, String cmdId, String referenceToken, JSONArray params) {
        if (cmdId.equals("0x005008")) {
            String timer = params.getJSONObject(0).getString("time");
            String KeyNote = params.getJSONObject(0).getString("keyNote");
            List<Map<String, Object>> resultList = new ArrayList<>();
            Map<String, Object> _resultMap = new HashMap<>();
            _resultMap.put("method", DeviceServiceClient.Method.Device_HhrecCommand_InsertKeyNote);
            _resultMap.put("cmdOp", DEVICE_CMD_OPERATION);
            Map<String, String> _params = new HashMap<>();
            _params.put("ip", hostIp);
            _params.put("keynote", KeyNote);
            _params.put("time", timer);
            _params.put("ext", "");
            _resultMap.put("params", _params);
            resultList.add(_resultMap);
            return resultList;
        }
        return null;
    }
  /*  public OperationRequest[] videoDot(String hostIp, String hostId, String cmdId, ReferenceToken referenceToken, JSONArray params) {
        if (cmdId.equals("0x005008")) {
            String timer = params.getJSONObject(0).getString("time");
            String KeyNote = params.getJSONObject(0).getString("keyNote");
            OperationRequest operationRequest = new OperationRequestInsertKeyNote(Integer.parseInt(timer), KeyNote);
            return new OperationRequest[]{operationRequest};
        }
        return null;
    }*/

    /**
     * 片头
     *
     * @param cmdId
     * @param referenceToken
     * @param params
     * @return
     */
    public List<Map<String, Object>> prelude(String hostIp, String hostId, String cmdId, String referenceToken, JSONArray params) throws Exception {
        List<Map<String, Object>> resultList = new ArrayList<>();
        String avaliable = params.getJSONObject(0).getString("avaliable");
        if (avaliable.equals("1")) {
            Map<String, Object> resultMap = new HashMap<>();
            Map<String, String> params1 = new HashMap<>();
            params1.put("ip", hostIp);
            params1.put("ext", "");
            resultMap.put("params", params1);
            resultMap.put("cmdOp", DEVICE_CMD_OPERATION);
            resultMap.put("method", DeviceServiceClient.Method.Device_HhrecCommand_RemoveTitleVideo);
            resultList.add(resultMap);
            return resultList;
        }
        String picture = params.getJSONObject(0).getString("picture");
        String backMusic = params.getJSONObject(0).getString("backMusic");
        String pictureFlag = params.getJSONObject(0).getString("pictureFlag");
        String musicFlag = params.getJSONObject(0).getString("backMusicFlag");
        String recordType = params.getJSONObject(0).getString("recordType");
        //如果是修改片头，则不上传至录播主机
        if (pictureFlag.equals("0")) {
            Thread.sleep(1000);
            //統一配置文件，將原system.properties的配置信息迁移到config.properties wzz 2017-01-5
            FTPClient ftpClient = ftp4jService.run(hostIp, 21, ConfigUtil.get("FtpUserName"), ConfigUtil.get("FtpPassword"));
            if (ftpClient != null) {
                boolean flag = ftp4jService.uploadFile(ftpClient, uploadPath + picture, Constant.MATERIALPATH);
                if (!flag) throw new FrontException("片头图片上传失败");
                IOUtil.delete(uploadPath + picture);
            } else {
                throw new FrontException("Ftp连接失败");
            }
        }
        if (musicFlag.equals("0")) {
            //統一配置文件，將原system.properties的配置信息迁移到config.properties wzz 2017-01-5
            FTPClient ftpClient = ftp4jService.run(hostIp, 21, ConfigUtil.get("FtpUserName"), ConfigUtil.get("FtpPassword"));
            if (ftpClient != null) {
                boolean flag = ftp4jService.uploadFile(ftpClient, uploadPath + backMusic, Constant.MATERIALPATH);
                if (!flag) throw new FrontException("片头音乐上传失败");
                IOUtil.delete(uploadPath + backMusic);
            } else {
                throw new FrontException("Ftp连接失败");
            }
        }
        if (cmdId.equals("0x005009")) {
            String duration = params.getJSONObject(0).getString("duration");
            if (recordType.equals("0")) {//简易录播
                resultList.add(this.setVideo(0, hostIp, Constant.MATERIALPATH + "/" + backMusic, Constant.MATERIALPATH + "/" + picture, duration, "", ""));
            } else {
                Map<String, String> extParams = new HashMap<>();
                String title = "";
                String course = "";
                String grade = "";
                String subject = "";
                String school = "";
                String teacher = "";
                String fontSize = "";
                String fontColor = "";
                if (params.getJSONObject(0).containsKey("title")) {
                    title = params.getJSONObject(0).getString("title");
                }
                if (params.getJSONObject(0).containsKey("course")) {
                    course = params.getJSONObject(0).getString("course");
                }
                if (params.getJSONObject(0).containsKey("grade")) {
                    grade = params.getJSONObject(0).getString("grade");
                }
                if (params.getJSONObject(0).containsKey("subject")) {
                    subject = params.getJSONObject(0).getString("subject");
                }
                if (params.getJSONObject(0).containsKey("school")) {
                    school = params.getJSONObject(0).getString("school");
                }
                if (params.getJSONObject(0).containsKey("teacher")) {
                    teacher = params.getJSONObject(0).getString("teacher");
                }
                extParams.put("title", title);
                extParams.put("course", course);
                extParams.put("grade", grade);
                extParams.put("subject", subject);
                extParams.put("school", school);
                extParams.put("teacher", teacher);
                extParams.put("preludeName", params.getJSONObject(0).getString("preludeName"));
                extParams.put("fontSize", params.getJSONObject(0).getString("fontSize"));
                extParams.put("fontColor", params.getJSONObject(0).getString("fontColor"));
                String extString = JSONObject.fromObject(extParams).toString();
                //nvrCommand 不用进行字段的判断
                params.getJSONObject(0).putAll(extParams);
                resultList.add(this.setVideo(0, hostIp, Constant.MATERIALPATH + "/" + backMusic, Constant.MATERIALPATH + "/" + picture, duration, extString, ""));
            }
            //return new OperationRequest[]{operationRequest};
            return resultList;
        }
        throw new FrontException("片头设置失败");
    }

    /**
     * 设置片头片尾
     *
     * @param setType  片头还是片尾
     * @param ip
     * @param musPath
     * @param picPath
     * @param duration
     * @param ext
     * @param fileName
     * @return
     */
    public Map<String, Object> setVideo(int setType, String ip, String musPath, String picPath, String duration, String ext, String fileName) {
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        params.put("musPath", musPath);
        params.put("picPath", picPath);
        params.put("duration", duration);
        params.put("ext", ext);
        params.put("fileName", fileName);//?无用字段？
        resultMap.put("params", params);
        resultMap.put("cmdOp", DEVICE_CMD_OPERATION);
        if (setType == 0) { //设置片头
            resultMap.put("method", DeviceServiceClient.Method.Device_HhrecCommand_SetTitleVideo);
        } else {
            resultMap.put("method", DeviceServiceClient.Method.Device_HhrecCommand_SetEndingVideo);
        }
        return resultMap;
    }

    /**
     * 片尾
     *
     * @param cmdId
     * @param referenceToken
     * @param params
     * @return
     */
    public List<Map<String, Object>> curtain(String hostIp, String hostId, String cmdId, String referenceToken, JSONArray params) throws Exception {
        String avaliable = params.getJSONObject(0).getString("avaliable");
        List<Map<String, Object>> resultList = new ArrayList<>();
        if (avaliable.equals("1")) {
            Map<String, Object> resultMap = new HashMap<>();
            Map<String, String> params1 = new HashMap<>();
            params1.put("ip", hostIp);
            params1.put("ext", "");
            resultMap.put("params", params1);
            resultMap.put("cmdOp", DEVICE_CMD_OPERATION);
            resultMap.put("method", DeviceServiceClient.Method.Device_HhrecCommand_RemoveEndVideo);
            resultList.add(resultMap);
            return resultList;
        }
        String picture = params.getJSONObject(0).getString("picture");
        String backMusic = params.getJSONObject(0).getString("backMusic");
        String pictureFlag = params.getJSONObject(0).getString("pictureFlag");
        String musicFlag = params.getJSONObject(0).getString("backMusicFlag");
        String recordType = params.getJSONObject(0).getString("recordType");
        //如果是修改片头，则不上传至录播主机
        if (pictureFlag.equals("0")) {
            Thread.sleep(1000);
            FTPClient ftpClient = ftp4jService.run(hostIp, 21, ConfigUtil.get("FtpUserName"), ConfigUtil.get("FtpPassword"));
            if (ftpClient != null) {
                boolean flag = ftp4jService.uploadFile(ftpClient, uploadPath + picture, Constant.MATERIALPATH);
                if (!flag) throw new FrontException("片尾图片上传失败");
                IOUtil.delete(uploadPath + picture);
            } else {
                throw new FrontException("Ftp连接失败");
            }
        }
        if (musicFlag.equals("0")) {
            FTPClient ftpClient = ftp4jService.run(hostIp, 21, ConfigUtil.get("FtpUserName"), ConfigUtil.get("FtpPassword"));
            if (ftpClient != null) {
                boolean flag = ftp4jService.uploadFile(ftpClient, uploadPath + backMusic, Constant.MATERIALPATH);
                if (!flag) throw new FrontException("片尾音乐上传失败");
                IOUtil.delete(uploadPath + backMusic);
            } else {
                throw new FrontException("Ftp连接失败");
            }
        }

        if (cmdId.equals("0x005010")) {
            String duration = params.getJSONObject(0).getString("duration");
            if (recordType.equals("0")) {//简易录播
                // operationRequest = new OperationRequestSetEndingVideo(Constant.MATERIALPATH + "/" + picture, Constant.MATERIALPATH + "/" + backMusic, "", Integer.parseInt(duration));
                resultList.add(this.setVideo(1, hostIp, Constant.MATERIALPATH + "/" + backMusic, Constant.MATERIALPATH + "/" + picture, duration, "", ""));
            } else {
                Map<String, String> extParams = new HashMap<>();
                String date = "";
                String copyright = "";
                String production = "";
                if (params.getJSONObject(0).containsKey("date")) {
                    date = params.getJSONObject(0).getString("date");
                }
                if (params.getJSONObject(0).containsKey("copyright")) {
                    copyright = params.getJSONObject(0).getString("copyright");
                }
                if (params.getJSONObject(0).containsKey("production")) {
                    production = params.getJSONObject(0).getString("production");
                }
                extParams.put("date", date);
                extParams.put("copyright", copyright);
                extParams.put("curtainName", params.getJSONObject(0).getString("curtainName"));
                extParams.put("production", production);
                extParams.put("fontSize", params.getJSONObject(0).getString("fontSize"));
                extParams.put("fontColor", params.getJSONObject(0).getString("fontColor"));
                String extString = JSONObject.fromObject(extParams).toString();
                //nvrCommand 不用进行字段的判断
                params.getJSONObject(0).putAll(extParams);
                resultList.add(this.setVideo(1, hostIp, Constant.MATERIALPATH + "/" + backMusic, Constant.MATERIALPATH + "/" + picture, duration, extString, ""));
            }
            return resultList;
        }
        throw new FrontException("片尾设置失败");
    }

    /* public OperationRequest[] curtain(String hostIp, String hostId, String cmdId, ReferenceToken referenceToken, JSONArray params) throws Exception {
         String avaliable = params.getJSONObject(0).getString("avaliable");
         if (avaliable.equals("1")) {
             return new OperationRequest[]{new OperationRequestRemoveEndingVideo()};
         }
         String picture = params.getJSONObject(0).getString("picture");
         String backMusic = params.getJSONObject(0).getString("backMusic");
         String pictureFlag = params.getJSONObject(0).getString("pictureFlag");
         String musicFlag = params.getJSONObject(0).getString("backMusicFlag");
         String recordType = params.getJSONObject(0).getString("recordType");
         //如果是修改片头，则不上传至录播主机
         if(pictureFlag.equals("0")) {
             Thread.sleep(1000);
             FTPClient ftpClient = ftp4jService.run(hostIp, 21, System.getProperty("FtpUserName"), System.getProperty("FtpPassword"));
             if (ftpClient != null) {
                 boolean flag = ftp4jService.uploadFile(ftpClient, uploadPath + picture, Constant.MATERIALPATH);
                 if (!flag) throw new FrontException("片尾图片上传失败");
 //                try {
 //                    if (!ftpClient.currentDirectory().equals("/")) {
 //                        ftpClient.changeDirectory("/");
 //                    }
 //                } catch (Exception e) {
 //                    throw new FrontException("片尾上传失败" + e.getMessage());
 //                }
                 IOUtil.delete(uploadPath + picture);
             }
             else {
                 throw new FrontException("Ftp连接失败");
             }
         }
         if(musicFlag.equals("0")) {
             FTPClient ftpClient = ftp4jService.run(hostIp, 21, System.getProperty("FtpUserName"), System.getProperty("FtpPassword"));
             if (ftpClient != null) {
                 boolean flag = ftp4jService.uploadFile(ftpClient, uploadPath + backMusic, Constant.MATERIALPATH);
                 if (!flag) throw new FrontException("片尾音乐上传失败");
                 IOUtil.delete(uploadPath + backMusic);
             }
             else {
                 throw new FrontException("Ftp连接失败");
             }
         }

         if (cmdId.equals("0x005010")) {
             String duration = params.getJSONObject(0).getString("duration");
             OperationRequest operationRequest;
             if (recordType.equals("0")) {//简易录播
                 operationRequest = new OperationRequestSetEndingVideo(Constant.MATERIALPATH + "/" + picture, Constant.MATERIALPATH + "/" + backMusic, "", Integer.parseInt(duration));
             } else {
                 Map<String, String> extParams = new HashMap<>();
                 String date = "";
                 String copyright = "";
                 String production = "";
                 if (params.getJSONObject(0).containsKey("date")) {
                     date = params.getJSONObject(0).getString("date");
                 }
                 if (params.getJSONObject(0).containsKey("copyright")) {
                     copyright = params.getJSONObject(0).getString("copyright");
                 }
                 if (params.getJSONObject(0).containsKey("production")) {
                     production = params.getJSONObject(0).getString("production");
                 }
                 extParams.put("date", date);
                 extParams.put("copyright", copyright);
                 extParams.put("curtainName", params.getJSONObject(0).getString("curtainName"));
                 extParams.put("production", production);
                 extParams.put("fontSize", params.getJSONObject(0).getString("fontSize"));
                 extParams.put("fontColor", params.getJSONObject(0).getString("fontColor"));
                 String extString = JSONObject.fromObject(extParams).toString();
                 //nvrCommand 不用进行字段的判断
                 params.getJSONObject(0).putAll(extParams);
                 operationRequest = new OperationRequestSetEndingVideo(Constant.MATERIALPATH + "/" + picture, Constant.MATERIALPATH + "/" + backMusic, extString, Integer.parseInt(duration));
             }
             return new OperationRequest[]{operationRequest};
         }
         throw new FrontException("片尾设置失败");
     }*/
    private Map<String, Object> getSetCourseInfo(JSONObject params, String hostIp) {
        String courseTitle = "";
        String MainTititle = "";
        String ClassName = "";
        String SubjectName = "";
        String Teacher = "";
        String School = "";
        if (params.containsKey("course")) {
            courseTitle = params.getString("course");
        }
        if (params.containsKey("title")) {
            MainTititle = params.getString("title");
        }
        if (params.containsKey("grade")) {
            ClassName = params.getString("grade");
        }
        if (params.containsKey("subject")) {
            SubjectName = params.getString("subject");
        }
        if (params.containsKey("teacher")) {
            Teacher = params.getString("teacher");
        }
        if (params.containsKey("school")) {
            School = params.getString("school");
        }
        Map<String, Object> courseMap = new HashMap<>();
        courseMap.put("method", DeviceServiceClient.Method.Device_HhrecCommand_SetCourseInfo);

        //OperationRequest operationRequest = new OperationRequestSetCourseInfo(courseTitle, MainTititle, ClassName, SubjectName, Teacher, School);
        // return operationRequest;
        Map<String, String> _params = new HashMap<>();
        _params.put("ip", hostIp);
        _params.put("ext", "");
        _params.put("courseTitle", courseTitle);
        _params.put("mainTitle", MainTititle);
        _params.put("className", ClassName);
        _params.put("subjectName", SubjectName);
        _params.put("teacher", Teacher);
        _params.put("school", School);
        courseMap.put("params", _params);
        courseMap.put("cmdOp", DEVICE_CMD_OPERATION);
        return courseMap;
    }

    /**
     * 课程信息
     *
     * @param cmdId
     * @param referenceToken
     * @param params
     * @return
     */
    public List<Map<String, Object>> courseInfo(String hostIp, String hostId, String cmdId, String referenceToken, JSONArray params) {
        if (cmdId.equals("0x005011")) {
            List<Map<String, Object>> resultList = new ArrayList<>();
            String recordStatus = params.getJSONObject(0).getString("recordStatus");
            if (recordStatus.equals("stop")) {
                Map<String, JSONObject> courseInfoCache = settingsService.getCourseInfoCache();
                courseInfoCache.put(hostIp, params.getJSONObject(0));
                return null;
            }
            resultList.add(this.getSetCourseInfo(params.getJSONObject(0), hostIp));
            return resultList;
        }
        return null;
    }


    public List<Map<String, Object>> mutilScreen(String hostIp, String hostId, String cmdId, String referenceToken, JSONArray params) {
        List<Map<String, Object>> list = new ArrayList<>();
        if (cmdId.equals("0x006001")) {
            String recordStatus = params.getJSONObject(0).getString("value");
            String ID = params.getJSONObject(0).getString("value");
            Map<String, Object> mutilScreenMap = new HashMap<>();
            mutilScreenMap.put("method", DeviceServiceClient.Method.Device_HhrecCommand_SetLayout);
            Map<String, String> _params = new HashMap<>();
            _params.put("ip", hostIp);
            _params.put("layoutName", ID);
            mutilScreenMap.put("params", _params);
            mutilScreenMap.put("cmdOp", DEVICE_CMD_OPERATION);
            list.add(mutilScreenMap);
            return list;
        }
        return null;
    }


    private boolean closeLiveRoom(String vid, String isClose, String token) {
////        String unlock = "";
//        if (isClose.equals("1")) {
////            unlock = "&unlock=yes";
//            CacheUtil.remove(token);
//        }
//        //直播间关闭失败，将直播间从cache中移除
//        if(!liveplanService.closeLiveRomm(Integer.parseInt(vid)))
//        {
//            CacheUtil.remove(token);
//            return true;
//        }
//        return true;

        if (isClose.equals("1")) { // 1关闭直播间 add 2017-02-21
//            unlock = "&unlock=yes";
            CacheUtil.remove(token);
            if (!liveplanService.closeLiveRomm(Integer.parseInt(vid))) {
                return false;
            }
            return true;
        }
        return true; // 0 不关闭直播间

        //直播间关闭失败，将直播间从cache中移除
//        if(!liveplanService.closeLiveRomm(Integer.parseInt(vid)))
//        {
//            CacheUtil.remove(token);
//            return true;
//        }
//        return true;

//        String url = "http://" + System.getProperty("ResourceIp") + "/apps/server_api.php?cmd=end_live&key=hellokey&videoid=" + vid + unlock;
//        String content = "";
//        try {
//            Map<String, String> data = new HashMap<>();
//            content = org.jsoup.Jsoup.connect(url).method(Connection.Method.POST).data(data).ignoreContentType(true).execute().body().trim();
//            //content = StringEscapeUtils.unescapeJava(content).trim();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//        if (content.indexOf("fail") != -1) {
//            CacheUtil.remove(token);
//            return true;
//        }
//        return true;
    }

    private boolean startLiveRoom(JSONArray params, String token) {
        String[] videoid = CacheUtil.get(token);
        if (videoid == null) {
            return false;
        }
        return liveplanService.openLiveRomm(Integer.parseInt(videoid[0]));
//        //  String video_password = params.getJSONObject(0).getString("video_password");
//        // String broadcast = params.getJSONObject(0).getString("broadcast");
//        String url = "http://" + System.getProperty("ResourceIp") + "/apps/server_api.php?cmd=start_live&key=hellokey&videoid=" + videoid[0];
//        String content = "";
//        try {
//            Map<String, String> data = new HashMap<>();
//            //data.put("video_password",video_password);
//            //data.put("broadcast",broadcast);
//            content = org.jsoup.Jsoup.connect(url).method(Connection.Method.POST).data(data).ignoreContentType(true).execute().body().trim();
//            content = StringEscapeUtils.unescapeJava(content).trim();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (content.indexOf("fail") != -1) {
//            return true;
//        }
//        return false;
    }

    /* public OperationRequest[] settingMutilScreen(String hostIp, String hostId, String cmdId, ReferenceToken referenceToken, JSONArray params) throws Exception {
         JSONObject jsonParams = params.getJSONObject(0).getJSONObject("value");
         String to = jsonParams.getString("to");
         String from = jsonParams.getString("from");
         String layoutId = jsonParams.getString("layoutId");
         String operate = jsonParams.getString("operate");
         List<Object[]> deviceConfigList = settingsService.getDeviceConfigList();
         if ((deviceConfigList != null) && (!deviceConfigList.isEmpty())) {
             for (int i = 0; i < deviceConfigList.size(); i++) {
                 String mainToken = ((Object[]) deviceConfigList.get(i))[2].toString();
                 String subToken = ((Object[]) deviceConfigList.get(i))[3].toString();
                 if ((to.equals(mainToken)) || (to.equals(subToken))) {
                     to = mainToken;
                     continue;
                 }
                 if ((from.equals(mainToken)) || (from.equals(subToken))) {
                     from = mainToken;
                     continue;
                 }

             }
         }
         Layout[] layouts = this.nvrCommand.getLayout(hostIp);
         if (layouts == null) {
             throw new ProgramException("OperationRequestGetLayoutConfigurations method is null");
         }
         for (Layout layout : layouts) {
             if (layout.getName().getName().equals(layoutId)) {
                 LayoutWindow[] layoutWindows = layout.getWindows();
                 if (operate.equals("select")) {
                     for (LayoutWindow layoutWindow : layoutWindows) {
                         if (layoutWindow.getVideoSource().getToken().equals(from)) {
                             Token token = new Token();
                             token.setToken(to);
                             layoutWindow.setVideoSource(token);
                             break;
                         }
                     }
                 } else {
                     for (LayoutWindow layoutWindow : layoutWindows) {
                         if (layoutWindow.getVideoSource().getToken().equals(to)) {
                             Token token = new Token();
                             token.setToken(from);
                             layoutWindow.setVideoSource(token);
                             continue;
                         } else if (layoutWindow.getVideoSource().getToken().equals(from)) {
                             Token token = new Token();
                             token.setToken(to);
                             layoutWindow.setVideoSource(token);
                             continue;
                         }
                     }

                 }
             }
         }
         OperationRequest operationRequest = new OperationRequestSetLayoutConfigurations(layouts);
         return new OperationRequest[]{operationRequest};
     }*/
    public List<Map<String, Object>> settingMutilScreen(String hostIp, String hostId, String cmdId, String referenceToken, JSONArray params) throws Exception {
        List<Map<String, Object>> list = new ArrayList<>();
        JSONObject jsonParams = params.getJSONObject(0).getJSONObject("value");
        String to = jsonParams.getString("to");
        String from = jsonParams.getString("from");
        String layoutId = jsonParams.getString("layoutId");
        String operate = jsonParams.getString("operate");

        String connect_param = settingsService.getConnect_param(hostId);

        List<Object[]> deviceConfigList = settingsService.getDeviceConfigList();
        if ((deviceConfigList != null) && (!deviceConfigList.isEmpty())) {
            for (Object[] deviceConfig : deviceConfigList) {
                String mainToken = deviceConfig[2].toString();
                String subToken = deviceConfig[3].toString();
                String connectparam = deviceConfig[0].toString();
                if ((to.equals(mainToken) || to.equals(subToken)) && connect_param.equals(connectparam)) {
                    to = mainToken;
                    continue;
                }
                if ((from.equals(mainToken) || from.equals(subToken)) && connect_param.equals(connectparam)) {
                    from = mainToken;
                    continue;
                }

            }
        }
        Map<String, Object> mutilScreenMap = new HashMap<>();
        mutilScreenMap.put("method", DeviceServiceClient.Method.Device_HhrecCommand_SettingMutilScreen);
        Map<String, String> _params = new HashMap<>();
        _params.put("ip", hostIp);
        _params.put("to", to);
        _params.put("from", from);
        _params.put("layoutId", layoutId);
        _params.put("operate", operate);
        _params.put("ext", "");
        mutilScreenMap.put("params", _params);
        mutilScreenMap.put("cmdOp", DEVICE_CMD_OPERATION);
        list.add(mutilScreenMap);
        return list;
    }


}
