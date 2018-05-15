package com.honghe.recordweb.service.frontend.devicemanager;

import com.alibaba.fastjson.JSON;
import com.honghe.device.sdk.DeviceServiceClient;
import com.honghe.recordhibernate.dao.DeviceDao;
import com.honghe.recordweb.action.frontend.viewclass.DirectorViewDTO;
import com.honghe.recordweb.exception.FrontException;
import com.honghe.recordweb.exception.ProgramException;
import com.honghe.recordweb.service.frontend.device.HongheDeviceService;
import com.honghe.recordweb.service.frontend.devicemanager.message.DeviceRequestMessage;
import com.honghe.recordweb.service.frontend.devicemanager.message.DeviceResponseMessage;
import com.honghe.recordweb.service.frontend.devicemanager.message.OperatorMessage;
import com.honghe.recordweb.service.frontend.syslog.SyslogService;
import com.honghe.recordweb.service.frontend.websocket.MessageSender;
import com.honghe.recordweb.service.frontend.websocket.SessionPool;
import com.honghe.recordweb.util.CommonName;
import com.honghe.recordweb.util.HongheDeviceServiceFactory;
import com.honghe.service.client.Result;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by zhanghongbin on 2014/12/6.
 */
@Component
public final class NVRCommand {
    private final static Logger logger = Logger.getLogger(NVRCommand.class);
    @Resource
    private NVR nvr;

    public NVR getNvr() {
        return this.nvr;
    }

    @Resource
    private DeviceDao deviceDao;
    @Resource
    private SyslogService deviceLogService;
    @Resource
    private HongheDeviceService hongheDeviceService;


    /**
     * 设置台标
     *
     * @param ip       主机ip
     * @param path     台标路径
     * @param guid     //todo 不明确其含义
     * @param position 位置
     */
    public final void setLogo(String ip, String path, String guid, String position) {
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        params.put("path", path);
        params.put("guid", guid);
        params.put("position", position);
        Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_HhrecCommand_SetLogo, params);
    }


    /**
     * 获取台标
     */
    @Deprecated
    public final void getLogo(String ip) {
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        params.put("ext", "");
        Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_HhrecCommand_GetLogo, params);
    }

    /**
     * 移除台标
     *
     * @param ip
     */
    public final void removeLogo(String ip) {
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        params.put("ext", "");
        Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_HhrecCommand_RemoveLogo, params);
    }

    /**
     * 设置分辨率
     *
     * @param ip ip
     * @param x  分辨率中的宽
     * @param y  分辨率中的高
     */
    public final void setResolution(String ip, String hostId, int x, int y) {
        String referenceToken = getMainTokenByHostid(Integer.parseInt(hostId), "电影模式");
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        params.put("referenceToken", referenceToken);
        params.put("x", String.valueOf(x));
        params.put("y", String.valueOf(y));
        Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_HhrecCommand_SetResolution, params);
    }

    /**
     * 设置录像信息
     *
     * @param ip
     * @param x  分辨率中的宽
     * @param y  分辨率中的高
     * @param bitRate
     */
    public final void setVideoInfo(String ip, String hostId, int x, int y, int bitRate) {
        String referenceToken = getMainTokenByHostid(Integer.parseInt(hostId), "电影模式");
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        params.put("mediaToken", referenceToken);
        params.put("width", String.valueOf(x));
        params.put("height", String.valueOf(y));
        params.put("bitrate", String.valueOf(bitRate));
        Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_HhrecCommand_SetVideoRecorderInfo, params);
    }

    /**
     * 查询分辨率
     *
     * @param ip
     */
    @Deprecated
    public final void getResolution(String ip, String hostId) {
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        params.put("hostId", hostId);
        Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_HhrecCommand_GetResolution, params);
    }

    /**
     * 进入/退出导播页面
     *
     * @param ip      录播主机ip
     * @param isStart true 进入导播页面 ；false 退出导播页面
     */
    public final void setBackgroundDirectMode(String ip, boolean isStart, String remoteAddr) {
        String isStartStr = "2";
        if (!isStart) {
            isStartStr = "3";
        }
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        params.put("isStart", isStartStr);
        params.put("ext", remoteAddr);
        Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_HhrecCommand_SetBackgroundDirectMode, params);
    }

    /**
     * 设置码率
     *
     * @param ip
     * @param bitrate
     */
    public final void setBitrate(String ip, String hostId, int bitrate) {
        String referenceToken = getMainTokenByHostid(Integer.parseInt(hostId), "电影模式");
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        params.put("referenceToken", referenceToken);
        params.put("bitrate", String.valueOf(bitrate));
        Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_HhrecCommand_SetBitrate, params);
    }

    /**
     * 查询码率
     *
     * @param ip
     */
    @Deprecated
    public final void getBitrate(String ip, String hostId) {
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        params.put("hostId", hostId);
        Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_HhrecCommand_GetBitrate, params);
    }

    /**
     * 设置nas
     *
     * @param ip
     * @param rootPath
     * @param hostName
     * @param userName
     * @param password
     */
    public final void setNas(String ip, String rootPath, String hostName, String userName, String password) {
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        params.put("hostId", "");
        params.put("rootPath", rootPath);
        params.put("hostName", hostName);
        params.put("userName", userName);
        params.put("password", password);
        Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_HhrecCommand_SetNas, params);
    }

    /**
     * 清除nas设置
     *
     * @param ip
     */
    public final void clearNas(String ip) {
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        params.put("ext", "");
        Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_HhrecCommand_ClearNas, params);
    }

    /**
     * 查询录制状态
     * <p/>
     * String ,Object
     * Status STOP,REC,PAUSED
     * Duration
     * update by zlj on 2018/04/12
     * @param ip
     * @return
     */
    public final Map<String, Object> getRecordingStatus(String ip) {
        Map<String, Object> info = hongheDeviceService.getHostInfoByIp(ip);
        String dspecid = info.get("dspec_id").toString();
        if("17".equals(dspecid)){//虚拟设备不获取录制状态
            return Collections.EMPTY_MAP;
        }
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        params.put("ext", "");
        Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_HhrecCommand_GetRecordingStatus, params);
        if (result != null && result.getCode() == 0 && result.getValue() != null) {
            Map<String, Object> res = (Map<String, Object>) result.getValue();
            if (!res.isEmpty()) {
                return res;
            } else {
                return Collections.EMPTY_MAP;
            }
        } else {
            return Collections.EMPTY_MAP;
        }
    }

    @Deprecated
    private final Map<String, Object> getRecording(String ip) {
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        params.put("ext", "");
        Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_HhrecCommand_GetRecording, params);
        if (result != null && result.getCode() == 0 && result.getValue() != null) {
            Map<String, Object> res = (Map<String, Object>) result.getValue();
            if (!res.isEmpty()) {
                return res;
            } else {
                return Collections.EMPTY_MAP;
            }
        } else {
            return Collections.EMPTY_MAP;
        }
    }

    /**
     * 查询音量
     *
     * @param ip
     * @return
     */
    public final Integer getVolume(String ip) {
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        params.put("ext", "");
        Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_HhrecCommand_GetVolume, params);
        if (result.getCode() == 0 && !result.getValue().toString().equals("")) {
            try {
                return Integer.parseInt(result.getValue().toString());
            } catch (Exception e) {
                return 0;
            }
        } else {
            return 0;
        }
    }

    /**
     * 调用设备通信进行字幕查询
     * 调用请求：OperationRequestGetCaption
     * 返回参数为hashtable
     * Key：CaptionPath    Value：string，字幕存储的相对路径。(/NVRSettings/Captions/***.***)
     * Key：Location		Value：string，位置代码，该字符串在GlobalDefine可以调用。
     */
    public final Map<String, Object> getCaption(String ip) {
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_HhrecCommand_GetCaption, params);
        if (result != null && result.getCode() == 0 && result.getValue() != null) {
            Map<String, Object> res = (Map<String, Object>) result.getValue();
            if (!res.isEmpty()) {
                return res;
            } else {
                return Collections.EMPTY_MAP;
            }
        } else {
            return Collections.EMPTY_MAP;
        }
    }

    /**
     * 片头查询
     * 返回的参数为
     * string，片头存储的相对路径。(/NVRSettings/ VideoMaterial /***.***)
     *
     * @param ip
     * @return
     */
    public final Map<String, Object> getTitleVideo(String ip) {
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        params.put("ext", "");
        Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_HhrecCommand_GetTitleVideo, params);
        if (result != null && result.getCode() == 0 && result.getValue() != null) {
            Map<String, Object> res = (Map<String, Object>) result.getValue();
            if (!res.isEmpty()) {
                return res;
            } else {
                return Collections.EMPTY_MAP;
            }
        } else {
            return Collections.EMPTY_MAP;
        }
    }

    /**
     * 片尾查询
     *
     * @param ip
     * @return
     */
    public final Map<String, Object> getEndingVideo(String ip) {
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        params.put("ext", "");
        Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_HhrecCommand_GetEndingVideo, params);
        if (result != null && result.getCode() == 0 && result.getValue() != null) {
            Map<String, Object> res = (Map<String, Object>) result.getValue();
            if (!res.isEmpty()) {
                return res;
            } else {
                return Collections.EMPTY_MAP;
            }
        } else {
            return Collections.EMPTY_MAP;
        }
    }

    /**
     * 查询课程信息，调用设备通信进行查询。
     * 调用请求：OperationRequestGetCourseInfo
     * 返回参数为hashtable
     * Key：CourseTitle    Value：string，标题
     * Key：MainTitle    Value：string，主讲标题
     * Key：ClassName    Value：tring，年级
     * Key：SubjectName    Value：string，科目
     * Key：Teacher    Value：string，主讲人
     * Key：School    Value：string，学校
     */
    public final Map<String, String> getCourseInfo(String ip) {
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        params.put("ext", "");
        Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_HhrecCommand_GetCourseInfo, params);
        if (result != null && result.getCode() == 0 && result.getValue() != null) {
            Map<String, String> res = (Map<String, String>) result.getValue();
            if (!res.isEmpty()) {
                return res;
            } else {
                return Collections.EMPTY_MAP;
            }
        } else {
            return Collections.EMPTY_MAP;
        }
    }

    /**
     * 导播界面开启时，调用设备通信进行导播策略的查询。当状态为自动控制模式时候，分屏操作和云台操作禁用。
     * string，导播模式，该字符串在GlobalDefine可以调用。
     *
     * @param ip
     * @return
     */
    public final String getDirectMode(String ip) {
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        params.put("ext", "");
        Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_HhrecCommand_GetDirectMode, params);
        if (result != null && result.getCode() == 0 && result.getValue() != null) {
            //String res = (String) result.getValue();
            Map<String, String> res = (Map<String, String>) result.getValue();
            if (res != null && res.size() > 0 && res.get("Mode") != null) {
                return res.get("Mode").toString();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 处理导播页面执行后返回结果
     *
     * @param result
     * @param operationRequest
     * @param hostId
     * @param params
     * @return
     * @throws FrontException
     */
    private String processSynState(Result result, DeviceServiceClient.Method operationRequest, String hostId, JSONArray params) throws FrontException {
        if (result.getCode() != 0) throw new FrontException("请检查设备服务");
        DirectorViewDTO directorViewDTO = null;

        if (operationRequest == DeviceServiceClient.Method.Device_HhrecCommand_StartStreaming || operationRequest == DeviceServiceClient.Method.Device_HhrecCommand_StartRecord) {
            int _result = Integer.parseInt(result.getValue().toString());
            if (_result == 0) {
                Map<String, Object> values = new HashMap<>();
                values.putAll(params.getJSONObject(0));
                values.put("Status", "REC");
                values.put("Duration", "0");
                values.put("hostId", hostId);
                if (!params.getJSONObject(0).get("recordingMode").equals("recordingModeMovie")) {
                    JSONArray tokens = new JSONArray();
                    tokens.add(0, "token0");
                    tokens.add(1, "token1");
                    values.put("LocalMedias", tokens);
                }
                directorViewDTO = new DirectorViewDTO().getRecordingStatus(values);
            } else if (_result == 2) throw new FrontException("录制失败，请检查NAS服务器");
            else if (_result == 3) throw new FrontException("录制失败，NAS服务器空间已满");
            else if (_result == 4) throw new FrontException("录制失败，USB存储空间已满");
            else throw new FrontException("命令执行异常");
        } else if (operationRequest == DeviceServiceClient.Method.Device_HhrecCommand_StopStreaming || operationRequest == DeviceServiceClient.Method.Device_HhrecCommand_StopRecord) {
            int _result = Integer.parseInt(result.getValue().toString());
            if (_result == 0) {
                Map<String, Object> values = new HashMap<>();
                values.put("workingMode", params.getJSONObject(0).getString("workingMode"));
                values.put("Status", "stop");
                values.put("Duration", "0");
                values.put("hostId", hostId);
                if (!params.getJSONObject(0).get("recordingMode").equals("recordingModeMovie")) {
                    JSONArray tokens = new JSONArray();
                    tokens.add(0, "token0");
                    tokens.add(1, "token1");
                    values.put("LocalMedias", tokens);
                }
                directorViewDTO = new DirectorViewDTO().getRecordingStatus(values);
            } else {
                throw new FrontException("命令执行异常");
            }


        } else if (operationRequest == DeviceServiceClient.Method.Device_HhrecCommand_PauseStreaming || operationRequest == DeviceServiceClient.Method.Device_HhrecCommand_PauseRecording) {
            if (Integer.parseInt(result.getValue().toString()) == 0) {
                Map<String, Object> values = new HashMap<>();
                values.putAll(params.getJSONObject(0));
                values.put("Status", "PAUSE");
                values.put("Duration", "0");
                values.put("hostId", hostId);
                if (!params.getJSONObject(0).get("recordingMode").equals("recordingModeMovie")) {
                    JSONArray tokens = new JSONArray();
                    tokens.add(0, "token0");
                    tokens.add(1, "token1");
                    values.put("LocalMedias", tokens);
                }
                directorViewDTO = new DirectorViewDTO().getRecordingStatus(values);
            } else {
                throw new FrontException("命令执行异常");
            }
        } else if (operationRequest == DeviceServiceClient.Method.Device_HhrecCommand_RestartRecording || operationRequest == DeviceServiceClient.Method.Device_HhrecCommand_RestartStreaming) {
            if (Integer.parseInt(result.getValue().toString()) == 0) {
                Map<String, Object> values = new HashMap<>();
                values.putAll(params.getJSONObject(0));
                if (!params.getJSONObject(0).get("recordingMode").equals("recordingModeMovie")) {
                    JSONArray tokens = new JSONArray();
                    tokens.add(0, "token0");
                    tokens.add(1, "token1");
                    values.put("LocalMedias", tokens);
                }
                directorViewDTO = new DirectorViewDTO().getRestartStatus(values);
            } else {
                throw new FrontException("命令执行异常");
            }
        } else if (operationRequest == DeviceServiceClient.Method.Device_HhrecCommand_SetLayoutConfigurations) {
            //频道切换
            if (params.getJSONObject(0).containsKey("deviceToken")) {
                directorViewDTO = new DirectorViewDTO().getTokenName(params.getJSONObject(0).get("deviceToken").toString(), params.getJSONObject(0).get("token").toString());
            } else {
                throw new FrontException("命令执行异常");
            }

        } else if (operationRequest == DeviceServiceClient.Method.Device_HhrecCommand_SetLayout) {
            //分屏
            directorViewDTO = new DirectorViewDTO().getLayout(params.getJSONObject(0).get("value").toString());
        } else if (operationRequest == DeviceServiceClient.Method.Device_HhrecCommand_SetBackgroundDirectMode) {
            String value = params.getJSONObject(0).getString("value");
            //导播策略
            if (value.equals("directModeAuto")) {
                value = "Auto";
            } else {
                value = "Manual";
            }
            directorViewDTO = new DirectorViewDTO().getDirectMode(value);
        } else if (operationRequest == DeviceServiceClient.Method.Device_HhrecCommand_SetTitleVideo) {
            Map<String, Object> values = new HashMap<>();
            Map<String, Object> ext = new HashMap<>();
            values.put("MusPath", Constant.MATERIALPATH + "/" + params.getJSONObject(0).get("backMusic"));
            values.put("PicPath", Constant.MATERIALPATH + "/" + params.getJSONObject(0).get("picture"));
            values.put("Duration", params.getJSONObject(0).get("duration"));
            ext.put("title", params.getJSONObject(0).get("title"));
            ext.put("course", params.getJSONObject(0).get("course"));
            ext.put("grade", params.getJSONObject(0).get("grade"));
            ext.put("subject", params.getJSONObject(0).get("subject"));
            ext.put("school", params.getJSONObject(0).get("school"));
            ext.put("teacher", params.getJSONObject(0).get("teacher"));
            ext.put("fontSize", params.getJSONObject(0).get("fontSize"));
            ext.put("fontColor", params.getJSONObject(0).get("fontColor"));
            values.put("Ext", ext);
            //片头
            directorViewDTO = new DirectorViewDTO().getTitleVideo(values);
        } else if (operationRequest == DeviceServiceClient.Method.Device_HhrecCommand_SetEndingVideo) {
            //片尾
            Map<String, Object> values = new HashMap<>();
            Map<String, Object> ext = new HashMap<>();
            values.put("MusPath", Constant.MATERIALPATH + "/" + params.getJSONObject(0).get("backMusic"));
            values.put("PicPath", Constant.MATERIALPATH + "/" + params.getJSONObject(0).get("picture"));
            values.put("Duration", params.getJSONObject(0).get("duration"));
            ext.put("date", params.getJSONObject(0).get("date"));
            ext.put("copyright", params.getJSONObject(0).get("copyright"));
            ext.put("curtainName", params.getJSONObject(0).get("curtainName"));
            ext.put("production", params.getJSONObject(0).get("production"));
            ext.put("fontSize", params.getJSONObject(0).get("fontSize"));
            ext.put("fontColor", params.getJSONObject(0).get("fontColor"));
            values.put("Ext", ext);
            directorViewDTO = new DirectorViewDTO().getEndingVideo(values);
        } else if (operationRequest == DeviceServiceClient.Method.Device_HhrecCommand_SetCourseInfo) {
            //课程信息
            Map<String, String> values = new HashMap<>();
            values.put("MainTitle", params.getJSONObject(0).get("title").toString());
            values.put("CourseTitle", params.getJSONObject(0).get("course").toString());
            values.put("ClassName", params.getJSONObject(0).get("grade").toString());
            values.put("SubjectName", params.getJSONObject(0).get("subject").toString());
            values.put("School", params.getJSONObject(0).get("school").toString());
            values.put("Teacher", params.getJSONObject(0).get("teacher").toString());
            directorViewDTO = new DirectorViewDTO().getCourseInfo(values);
        } else if (operationRequest == DeviceServiceClient.Method.Device_HhrecCommand_GetRecordingStatus) {
            //打点准备
            HashMap<String, String> info = new HashMap<>();
            info.put("target", ((Map<String, Object>) result.getValue()).get("Duration").toString());
            info.put("requestParam", "videoDotPrepare");
            info.put("event", "");
            info.put("token", "");
            JSONObject jsonObject = new JSONObject();
            jsonObject.putAll(info);
            return JSONObject.fromObject(jsonObject).toString();
        }  // else if(operationRequest instanceof OperationRequestSetLive){
//            return result.toString();
//        }
        if (directorViewDTO == null) {
            return "";
        } else {
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.setExcludes(new String[]{"recordingStatus", "volume", "directMode", "layout", "courseInfo", "titleVideo", "endingVideo", "tokenName", "restartStatus", "allLayout"});
            return JSONObject.fromObject(directorViewDTO, jsonConfig).toString();
        }


    }

    /**
     * @param ip String NVR的ip
     * @desc 开始录制(同步)
     */
    public int startRecord(String ip, int hostid, String ext) {
        int re_value = -2;
        String referenceToken = this.getMainTokenByHostid(hostid, "电影模式");
        ;
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        String recordType = "0";
        if (ext != null && ext.equals("mode_record")) {
            recordType = "1";
        }
        params.put("recordType", recordType);
        params.put("hostId", String.valueOf(hostid));
        params.put("referenceToken", referenceToken);
        params.put("ext", ext);
        Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_HhrecCommand_StartRecord, params);
        if (result.getCode() == 0) {
            if (result.getValue() != null || !"".equals(result.getValue())) {
                String res = String.valueOf(result.getValue());
                try {
                    re_value = Integer.parseInt(res);
                } catch (Exception e) {
                    deviceLogService.addDeviceLog(ip, "开始录制失败", "SYSTEM");
                    re_value = -2;
                }
            } else {
                deviceLogService.addDeviceLog(ip, "开始录制失败", "SYSTEM");
                re_value = -2;
            }
        }
        return re_value;
    }

    /**
     * @param ip
     * @desc 停止录制(同步)
     */
    public int stopRecord(String ip) {
        int re_value = -1;
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        params.put("ext", "");
        Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_HhrecCommand_StopRecord, params);
        if (result.getCode() == 0) {

            if (result.getValue() != null && !result.getValue().equals("")) {
                String res = String.valueOf(result.getValue());
                try {
                    re_value = Integer.parseInt(res);
                } catch (Exception e) {
                    deviceLogService.addDeviceLog(ip, "停止录制失败", "SYSTEM");
                }
            } else {
                deviceLogService.addDeviceLog(ip, "停止录制失败", "SYSTEM");
            }
        }
        return re_value;
    }

    /**
     * 获取镜头主码流token
     *
     * @param hostid
     * @param device_name 电影模式
     * @return
     */
    public String getMainTokenByHostid(int hostid, String device_name) {
        try {
            String token = deviceDao.getMainTokenByHostid(hostid, device_name);
            return token;
        } catch (Exception e) {
            logger.error("获取镜头主码流token异常，hostid：" + hostid + "device_name:" + device_name, e);
            return null;
        }
    }

    /**
     * 添加录像计划
     *
     * @param ip
     * @param recordTasksList
     * @return
     */
    public int addPlanCommand(String ip, List<Map<String, Object>> recordTasksList) {
        int flag = -1;
        try {
            if (recordTasksList != null && recordTasksList.size() > 0) {
                for (Map<String, Object> map : recordTasksList) {
                    if (map.containsKey("timeplan_id")) {
                        Map<String, String> params = new HashMap<>();
                        params.put("hostId", map.get("hostId")!=null ? map.get("hostId").toString() : "");
                        params.put("timeplan_id", map.get("timeplan_id")!=null ? map.get("timeplan_id").toString():"");
                        params.put("ext", map.get("ext") != null ? map.get("ext").toString() : "");
                        params.put("week_id", map.get("week_id") != null ? map.get("week_id").toString() : "");
                        params.put("startTime", map.get("startTime") != null ? map.get("startTime").toString() : "");
                        params.put("stopTime", map.get("stopTime") != null ? map.get("stopTime").toString() : "");
                        String referenceToken = "";
                        if (map.get("hostId") != null && !map.get("hostId").toString().equals("")) {
                            referenceToken = deviceDao.getMainTokenByHostid(Integer.parseInt(map.get("hostId").toString()), "电影模式");
                        }
                        params.put("referenceToken", referenceToken);
                        params.put("protocol", "");
                        params.put("streamimgAddres","");
                        params.put("courseTitle","");
                        params.put("mainTitle","");
                        params.put("subjectName", map.get("subjectName") != null ? map.get("subjectName").toString() : "");
                        params.put("school","");
                        params.put("teacher", map.get("teacher") != null ? map.get("teacher").toString() : "");
                        params.put("extension","");
                        Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_HhrecCommand_AddPlanCommand, params);

                        if (result.getCode() == 0 && result.getValue() != null && !result.getValue().equals("")) {
                            String res = result.getValue().toString();
                            try {
                                flag = Integer.parseInt(res);
                            } catch (Exception e) {
                                deviceLogService.addDeviceLog(ip, "添加录像计划服务失败", "SYSTEM");
                            }
                        } else {
                            deviceLogService.addDeviceLog(ip, "添加录像计划服务失败", "SYSTEM");
                        }
                    }
                }
            }
        } catch (Exception e) {
            deviceLogService.addDeviceLog(ip, "添加录像计划服务失败", "SYSTEM");
        }
        return flag;
    }
    /**
     * 批量设置录像计划
     *
     * @param ip
     * @param recordTasksList
     * @return
     */
    public int setPlanCommand(String ip, List<Map<String, Object>> recordTasksList) {
        logger.info("下发录像计划，ip:"+ip);
        int flag = -1;
        List<Map<String,String>> paramList = new ArrayList<>();
        try {
            if (recordTasksList != null && recordTasksList.size() > 0) {
                for (Map<String, Object> map : recordTasksList) {
                    if (map.containsKey("timeplan_id")) {
                        Map<String, String> params = new HashMap<>();
                        params.put("hostId", map.get("hostId")!=null ? map.get("hostId").toString() : "");
                        params.put("timeplan_id", map.get("timeplan_id")!=null ? map.get("timeplan_id").toString():"");
                        params.put("ext", map.get("ext") != null ? map.get("ext").toString() : "");
                        params.put("week_id", map.get("week_id") != null ? map.get("week_id").toString() : "");
                        params.put("startTime", map.get("startTime") != null ? map.get("startTime").toString() : "");
                        params.put("stopTime", map.get("stopTime") != null ? map.get("stopTime").toString() : "");
                        String referenceToken = "";
                        if (map.get("hostId") != null && !map.get("hostId").toString().equals("")) {
                            referenceToken = deviceDao.getMainTokenByHostid(Integer.parseInt(map.get("hostId").toString()), "电影模式");
                        }
                        params.put("referenceToken", referenceToken);
                        params.put("protocol", "");
                        params.put("streamimgAddres","");
                        params.put("courseTitle","");
                        params.put("mainTitle","");
                        params.put("subjectName", map.get("subjectName") != null ? map.get("subjectName").toString() : "");
                        params.put("school","");
                        params.put("teacher", map.get("teacher") != null ? map.get("teacher").toString() : "");
                        params.put("extension","");
                        logger.info("下发录像计划，详细信息:timeplamid:"+params.get("timeplan_id")
                                +"  week_id:"+params.get("week_id")
                                +"  startTime:"+params.get("startTime")
                                +"  stopTime:"+params.get("stopTime"));
                        paramList.add(params);
                    }
                }
                Map recordtasks = new HashMap<>();
                String paramStr = JSON.toJSONString(paramList);
                recordtasks.put("recordtasks",paramStr);
                Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND,
                        DeviceServiceClient.Method.Device_HhrecCommand_SetPlanCommand, recordtasks);
                if (result.getCode() == 0 && result.getValue() != null && !result.getValue().equals("")) {
                    String res = result.getValue().toString();
                    flag = Integer.parseInt(res);
                    logger.info("下发录像计划结果:"+flag);
                } else {
                    deviceLogService.addDeviceLog(ip, "添加录像计划服务失败", "SYSTEM");
                    logger.error("下发录像计划异常。");
                }
            }
        } catch (Exception e) {
            deviceLogService.addDeviceLog(ip, "添加录像计划服务失败", "SYSTEM");
            logger.error("下发录像计划异常。"+e);
        }
        return flag;
    }
    /**
     * 删除录播计划
     *
     * @param hostId
     * @param timeplan_id
     * @param ip
     * @return
     */
    public int delPlanCommand(int hostId, int timeplan_id, String ip) {
        int flag = -1;
        try {
            Map<String, String> params = new HashMap<>();
            params.put("hostId", hostId + "");
            params.put("timeplan_id", timeplan_id + "");
            params.put("ext", "");
            Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_HhrecCommand_DelPlan, params);

            if (result.getCode() == 0 && result.getValue() != null && !result.getValue().equals("")) {
                String res = result.getValue().toString();
                flag = Integer.parseInt(res);
                if (flag != 1) {
                    deviceLogService.addDeviceLog(ip, "删除录像计划服务失败", "SYSTEM");
                }
            }
        } catch (Exception e) {
            logger.error("删除录像计划服务失败", e);
            deviceLogService.addDeviceLog(ip, "删除录像计划服务失败", "SYSTEM");
        }
        return flag;
    }

    /**
     * 删除临时录像
     * Author xinye
     * @param hostId
     * @param tempplan_id
     * @param ip
     * @return
     */
    public int delTempPlanCommand(int hostId, int tempplan_id, String ip){
        int flag = -1;
        if(!nvr.isOnline(ip)){
            return 0;
        }
        try {
            Map<String, String> params = new HashMap<>();
            params.put("hostId", hostId + "");
            params.put("timeplan_id", tempplan_id + "");
            params.put("ext", "");
            Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_Command_DelTempPlan, params);

            if (result.getCode() == 0 && result.getValue() != null && !result.getValue().equals("")) {
                String res = result.getValue().toString();
                flag = Integer.parseInt(res);
                if (flag != 1) {
                    deviceLogService.addDeviceLog(ip, "删除临时录像计划服务失败", "SYSTEM");
                }
            }
        } catch (Exception e) {
            logger.error("删除临时录像计划服务失败", e);
            logger.info("删除临时录像计划服务失败",e);
            deviceLogService.addDeviceLog(ip, "删除临时录像计划服务失败", "SYSTEM");
        }
        return flag;
    }
    /**
     * 删除所有的录像计划
     *
     * @param hostId
     * @param ip
     * @return
     */
    public boolean delAllPlanCommand(int hostId, String ip) {
        boolean flag = false;
        try {
            Map<String, String> params = new HashMap<>();
            params.put("hostId", hostId + "");
            Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_HhrecCommand_DelAllPlan, params);
            if (result.getCode() == 0) {
                if ((Integer) result.getValue() == 1) {
                    flag = true;
                } else {
                    deviceLogService.addDeviceLog(ip, "删除录像计划服务失败", "SYSTEM");
                }
            }
        } catch (Exception e) {
            logger.error("添加录像计划服务失败", e);
            deviceLogService.addDeviceLog(ip, "添加录像计划服务失败", "SYSTEM");
        }
        return flag;
    }

    /**
     * 获取所有的计划
     *
     * @param ip
     * @param ext
     * @return
     */
    public List<Map<String, Object>> getAllPlan(String ip, String ext) {
        List<Map<String, Object>> re_value = Collections.EMPTY_LIST;
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        params.put("ext", ext);
        Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_HhrecCommand_GetAllPlan, params);
        try {
            if (result.getCode() == 0) {
                List<Map<String, Object>> res = (List<Map<String, Object>>) result.getValue();
                if (!res.isEmpty() && res.size() > 0) {
                    re_value = res;
                }
            }
        } catch (Exception e) {
            logger.error("获取所有的计划异常", e);
        }
        return re_value;
    }

    /**
     * 获取设备型号
     *
     * @param ip
     * @return
     */
    public String getLocalModel(String ip) {
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        Result res = HongheDeviceServiceFactory.remoteInvocation("device", DeviceServiceClient.Method.Device_GetLocalModel, params);
        if (res.getCode() == 0 && res.getValue() != null) {
            return res.getValue().toString();
        } else {
            return "";
        }
    }

    /**
     * 获取当前版式
     *
     * @param ip
     * @return
     */
    public String getCurrentLayout(String ip) {
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        params.put("ext", "");
        Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_HhrecCommand_GetCurrentLayout, params);
        if (result.getCode() == 0 && result.getValue() != null && !result.getValue().equals("")) {
            String res = (String) result.getValue();
            return res;
        } else {
            return null;
        }
    }

    /**
     * 获取版式
     *
     * @param ip
     * @return
     */
    public List<Map<String, Object>> getLayout(String ip) {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("ip", ip);
            params.put("ext", "");
            Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_HhrecCommand_GetLayout, params);
            if (result != null && result.getCode() == 0) {
                return (List<Map<String, Object>>) result.getValue();
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error("获取版式异常", e);
            return null;
        }
    }

    /**
     * 开机
     *
     * @param ip
     * @param mac
     * @return
     */
    public boolean start(String ip, String mac) {
        boolean re_value = false;
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_Command_Boot, params);
        try {
            if (result.getCode() != 0 && result.getValue() != null) {
                re_value = (boolean) result.getValue();
            }
        } catch (Exception e) {
            logger.error("录播设备开机异常", e);
        }
        return re_value;
    }

    /**
     * 关机
     *
     * @param ip
     * @param userSessionId
     * @return
     */
    public boolean shutdown(String ip, String userSessionId) {
        boolean re_value = false;
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        params.put("cmdCode", "");
        Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_Command_Shutdown, params);
        try {
            if (result.getCode() != 0 && result.getValue() != null) {
                re_value = (boolean) result.getValue();
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return re_value;
    }

    /**
     * Status
     * Duration
     * Adress
     * Protocol
     */
    //todo 需要验证是否有问题
    private Map<String, Object> getLive(String ip) {
        Map<String, Object> re_value = Collections.EMPTY_MAP;
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        params.put("ext", "");
        Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_HhrecCommand_GetCourseInfo, params);
        try {

            if (result.getCode() == 0 && result.getValue() != null) {
                Map<String, Object> res = (Map<String, Object>) result.getValue();
                if (!res.isEmpty()) {
                    re_value = res;
                }
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return re_value;
    }


    /**
     * 获取所有token
     *
     * @param ip
     * @return
     */
    public List<String> getAllMediaToken(String ip) {
        List<String> re_value = Collections.EMPTY_LIST;
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_HhrecCommand_GetAllMediaToken, params);
        try {

            if (result.getCode() == 0 && result.getValue() != null) {
                List<String> res = (List<String>) result.getValue();
                if (!res.isEmpty()) {
                    re_value = res;
                }
            }
        } catch (Exception e) {
            logger.error("获取所有token异常", e);
        }
        return re_value;
    }

    /**
     * 获取设备功能列表
     *
     * @param ip
     * @return
     */
    public List getNvrSupport(String ip) {
        List<String> list = Collections.emptyList();
        Map<String, String> params = new HashMap<>();
        params.put("ip", ip);
        Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_HhrecCommand_GetSupports, params);
        try {
            if (result != null && result.getCode() == 0) {
                if (result.getValue() != null && !result.getValue().equals("")) {
                    list = (List<String>) result.getValue();
                }
            }
        } catch (Exception e) {
            logger.error("获取设备功能列表", e);
        }
        return list;
    }

    /**
     * todo 加注释
     * @param strDeviceToken
     * @param deviceType
     * @param strEventType
     * @param strEventContext
     */
    public void sendWebSocketMessage(String strDeviceToken, String deviceType, String strEventType, String strEventContext) {
        DeviceResponseMessage deviceResponseMessage = new DeviceResponseMessage(strDeviceToken, deviceType, DeviceResponseMessage.Type.RESPONSE, strEventContext);
        deviceResponseMessage.setEventType(strEventType);
        Set<String> idSet = SessionPool.Keys();
        for (String id : idSet) {
            MessageSender.send(id, deviceResponseMessage.toJson());
        }
    }

    /**
     * 发送导播页面命令处理后结果给浏览器
     *
     * @param strDeviceToken
     * @param strEventType
     * @param strEventContext
     */
    public void sendWebSocketMessage(String strDeviceToken, String strEventType, String strEventContext) {
        DeviceResponseMessage deviceResponseMessage = new DeviceResponseMessage(strDeviceToken, CommonName.DEVICE_TYPE_RECOURD, DeviceResponseMessage.Type.RESPONSE, strEventContext);
        deviceResponseMessage.setEventType(strEventType);
        Set<String> idSet = SessionPool.Keys();
        for (String id : idSet) {
            MessageSender.send(id, deviceResponseMessage.toJson());
        }
    }

    /**
     * 执行导播页面接收到的命令
     *
     * @param deviceRequestMessage
     */
    public void executeOperatorMessage(DeviceRequestMessage deviceRequestMessage) {
        DeviceServiceClient deviceServiceClient = HongheDeviceServiceFactory.getDeviceServiceClient();
        String func = deviceRequestMessage.getFunc();
        List<Map<String, Object>> operationRequest;
        try {
            String result = "";
            operationRequest = (List<Map<String, Object>>) OperatorMessage.class.getMethod(func, String.class, String.class, String.class, String.class, JSONArray.class).
                    invoke(OperatorMessage.class.getConstructor(NVRCommand.class).newInstance(this), deviceRequestMessage.getToken(), deviceRequestMessage.getHostId(),
                            deviceRequestMessage.getCmdId(), deviceRequestMessage.getDeviceToken(), deviceRequestMessage.getParams());
            if (operationRequest != null) {
//
                if (operationRequest.size() == 1) {
                    Map<String, Object> _operationRequest = operationRequest.get(0);
                    String cmdOp = (String) _operationRequest.get("cmdOp");
                    if (cmdOp.equals("falseResult")) {
                        result = "false";
                    } else {
                        DeviceServiceClient.Method method = (DeviceServiceClient.Method) _operationRequest.get("method");
                        Map<String, String> params = (Map<String, String>) _operationRequest.get("params");
                        Result _result = deviceServiceClient.execute(cmdOp, method, params);
                        result = processSynState(_result, method, deviceRequestMessage.getHostId(), deviceRequestMessage.getParams());
                    }
                    if (!result.equals("")) {
                        sendWebSocketMessage(deviceRequestMessage.getToken(), deviceRequestMessage.getCmdId(), result);
                    }
                } else {
                    for (Map<String, Object> _operationRequest : operationRequest) {
                        String cmdOp = (String) _operationRequest.get("cmdOp");
                        if (cmdOp.equals("falseResult")) {
                            result = "false";
                        } else {
                            DeviceServiceClient.Method method = (DeviceServiceClient.Method) _operationRequest.get("method");
                            Map<String, String> params = (Map<String, String>) _operationRequest.get("params");
                            Result _result = deviceServiceClient.execute(cmdOp, method, params);
                            result = processSynState(_result, method, deviceRequestMessage.getHostId(), deviceRequestMessage.getParams());
                        }
                        if (!result.equals("")) {
                            sendWebSocketMessage(deviceRequestMessage.getToken(), deviceRequestMessage.getCmdId(), result);
                        }
                    }
                }

            }

        } catch (Exception e) {
            if (e.getClass() == ProgramException.class) {
                //deviceLogService.addDeviceSystemLog(ip, e.getCause().getMessage());
            } else if (e.getClass() == FrontException.class) {
                HashMap<String, String> error = new HashMap<>();
                error.put("target", e.getMessage());
                error.put("requestParam", "workingMode");
                error.put("event", "error");
                error.put("token", "");
                JSONObject jsonObject = new JSONObject();
                jsonObject.putAll(error);
                this.sendWebSocketMessage(deviceRequestMessage.getToken(), "0x000000", JSONObject.fromObject(jsonObject).toString());
            }
            logger.error("", e);
        }
    }

    /**
     * 下发录制文件名称
     * Author xinye
     * @param commodStr 执行命名协议
     * @param ip 设备ip
     *日期:         %d           date
     *时间：        %t           time
     *班级:         %c           class
     *课程:         %s           subject
     *教师:         %l           lecture
     *资源通道:  %r           channel
     *文件扩展名:   %e      extension
     *例如:
     *20160621_1430_高三一班_数学_王老师_电影.mp4的格式化字符串是
     *%d_%t_%c_%s_%l_%r_%e
     */
    public void setRecordInfo(String ip,String commodStr){

        Map<String,String> params = new HashMap<>();
        params.put("ip",ip);
        params.put("ethName","CourseInfoFormat.format.value");
        params.put("value",commodStr);
        params.put("ext","");
        HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_Command_SetLubocfg, params);
    }

    public boolean setRecordInfo(String ip,String commod, String commodValue, String ext){

        Map<String,String> params = new HashMap<>();
        params.put("ip",ip);
        params.put("ethName",commod);
        params.put("value", commodValue);
        params.put("ext", ext);
        Result result = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_Command_SetLubocfg, params);

        if (result != null && result.getCode() == 0) {
            logger.error("wbox 发送 扩展指令: " + commod + ", value: " + commodValue + " 到设备服务下发成功 -- 时间: -> " + new Date());
            return (boolean)result.getValue();
        } else {
            logger.error("wbox 发送 扩展指令: " + commod + ", value: " + commodValue + " 失败");
            return false;
        }
    }
}
