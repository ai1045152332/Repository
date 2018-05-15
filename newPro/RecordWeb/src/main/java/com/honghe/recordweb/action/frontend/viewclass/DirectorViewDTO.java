package com.honghe.recordweb.action.frontend.viewclass;

import com.honghe.recordweb.service.frontend.devicemanager.Constant;
import com.honghe.recordweb.service.frontend.devicemanager.HostDevice;
import com.honghe.recordweb.service.frontend.hostgroup.HostgroupService;
import com.honghe.recordweb.service.frontend.settings.SettingsService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.web.context.ContextLoaderListener;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by zhanghongbin on 2014/12/8.
 */
public class DirectorViewDTO {

    private String requestParam = "";
    private String token = "";
    private Object target;
    private String event = "";

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    private String hostId = "";
    private static HostgroupService hostgroupService = (HostgroupService) ContextLoaderListener.getCurrentWebApplicationContext().getBean(HostgroupService.class);
    private static SettingsService settingsService = (SettingsService) ContextLoaderListener.getCurrentWebApplicationContext().getBean(SettingsService.class);
    private static HostDevice hostDevice = (HostDevice) ContextLoaderListener.getCurrentWebApplicationContext().getBean(HostDevice.class);

    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
    /**
     * todo 加注释
     * @param values
     * @return
     */
    public DirectorViewDTO getRecordingStatus(Map<String, Object> values) {
        if (values != null && !values.isEmpty()) {
            String status = String.valueOf(values.get("Status"));
            this.requestParam = "workingMode";
            this.token = "";
            Map<String, String> _target = new HashMap<String, String>();
            if (status.equals("REC") || status.equals("PAUSE")) {
                JSONArray localMedias = (JSONArray) values.get("LocalMedias");
                Object[] tokens = null;
                if (localMedias != null && localMedias.size() > 0) {
                    tokens = localMedias.toArray();
                }
                if (tokens == null || (tokens.length == 1 && tokens[0].toString().equals("movie")) || (tokens.length == 1 && tokens[0].toString().equals("MovieToken")) || (tokens.length == 1 && tokens[0].toString().equals(""))) {
                    _target.put("workingMode", values.get("workingMode").toString());
                    _target.put("recordingMode", "recordingModeMovie");
                } else {
                    _target.put("workingMode", values.get("workingMode").toString());
                    _target.put("recordingMode", "recordingModeMovieAndResource");
                }
                this.event = status.replaceAll("REC", "start").replaceAll("PAUSE", "pause");
                _target.put("startTime", values.get("Duration").toString());
            } else {
                // _target.put("workingMode", "workingModeRecording");
                _target.put("workingMode", values.get("workingMode").toString());
                _target.put("startTime", "");
                this.event = "stop";
            }
            this.target = _target;
            if (values.get("hostId") != null) {
                this.hostId = values.get("hostId").toString();
            }
        }
        return this;
    }

    /**
     * todo 加注释
     * @param values
     * @return
     */
    public DirectorViewDTO getVolume(Object values) {
        if (values != null) {
            this.requestParam = "workingMode";
            this.token = "";
            this.event = "volume";
            this.target = values.toString();
        }
        return this;
    }

    /**
     * todo 加注释
     * @param mode
     * @return
     */
    public DirectorViewDTO getDirectMode(Object mode) {
        if (mode != null) {
            this.requestParam = "directMode";
            this.token = "";
            this.event = "";
            if (mode.toString().equals("Manual")) {
                this.target = "directModeManual";
            } else {
                this.target = "directModeAuto";
            }
        }
        return this;
    }

    /**
     * todo 加注释
     * @param result
     * @param currentLayout
     * @return
     */
    public DirectorViewDTO getAllLayout(List<Map<String, Object>> result, String currentLayout) {
        if (!result.isEmpty()) {
            this.requestParam = "settingMutilScreen";
            this.token = "";
            this.event = currentLayout;
            this.target = result;
        }
        return this;
    }

    /**
     * todo 加注释
     * @param name
     * @return
     */
    public DirectorViewDTO getLayout(Object name) {
        if (name != null) {
            this.requestParam = "mutilScreen";
            this.token = "";
            this.event = "";
            this.target = name.toString();
        }
        return this;
    }

    /**
     * todo 加注释
     * @param token
     * @param hostIp
     * @return
     */
    public DirectorViewDTO getTokenName(String token, String hostIp) {
        List<Object[]> deviceConfigList = settingsService.getDeviceConfigList();
        String connect_param = settingsService.getConnect_paramByIp(hostIp);
        String name = "";
        for (Object[] deviceConfig : deviceConfigList) {
            String mainToken = deviceConfig[2].toString();
            String subToken = deviceConfig[3].toString();
            String connectparam = deviceConfig[0].toString();

            if ((mainToken.equals(token) || subToken.equals(token)) && connect_param.equals(connectparam)) {
                name = deviceConfig[1].toString();
                break;
            }
        }


        if (name != null && !name.equals("")) {
            this.requestParam = "singleScreen";
            this.token = "";
            this.event = "";
            this.target = name.toString();
        }
        return this;
    }

    /**
     * todo 加注释
     * @param result
     * @return
     */
    public DirectorViewDTO getCourseInfo(Map<String, String> result) {
        if (result != null && !result.isEmpty()) {
            this.requestParam = "courseInfo";
            this.token = "";
            this.event = "";
            Map<String, String> tmp = new Hashtable<>();
            tmp.put("title", result.get("MainTitle"));
            tmp.put("course", result.get("CourseTitle"));
            tmp.put("grade", result.get("ClassName"));
            tmp.put("subject", result.get("SubjectName"));
            tmp.put("school", result.get("School"));
            tmp.put("teacher", result.get("Teacher"));
            this.target = tmp;
        }
        return this;
    }

    /**
     * todo 加注释
     * @param result
     * @return
     */
    public DirectorViewDTO getTitleVideo(Map<String, Object> result) {
        if (result != null && !result.isEmpty()) {
            this.requestParam = "prelude";
            this.token = "";
            this.event = "";
            Map<String, Object> tmp = new HashMap<>();
            if (result.get("Duration") != null) {
                tmp.put("duration", result.get("Duration"));
            }
            //如果是ftp路径，则取文件名，否则返回“”空字符
            String ftpPath1 = Constant.MATERIALPATH.toString().split("/")[1];
            tmp.put("backMusic", "");
            if (result.get("MusPath") != null && !result.get("MusPath").equals("")) {
                String[] musPaths = result.get("MusPath").toString().split("/");
                if (musPaths.length >= 2 && musPaths[musPaths.length - 2].equals(ftpPath1)) {
                    tmp.put("backMusic", musPaths[musPaths.length - 1]);
                }
            }
            tmp.put("picture", "");
            if (result.get("PicPath") != null && !result.get("PicPath").equals("")) {
                String[] picPaths = result.get("PicPath").toString().split("/");
                if (picPaths.length > 2 && picPaths[picPaths.length - 2].equals(ftpPath1)) {
                    tmp.put("picture", picPaths[picPaths.length - 1]);
                }
            }
//            tmp.put("backMusic", result.get("MusPath"));
//            tmp.put("picture", result.get("PicPath"));
            tmp.put("avaliable", "0");
            if (result.containsKey("Ext") && !result.get("Ext").equals("GetVideoMaterialConfiguration-ok") && !result.get("Ext").equals("")) {
                JSONObject jsonObject = JSONObject.fromObject(result.get("Ext"));
                tmp.put("title", jsonObject.get("title"));
                tmp.put("course", jsonObject.get("course"));
                tmp.put("grade", jsonObject.get("grade"));
                tmp.put("subject", jsonObject.get("subject"));
                tmp.put("school", jsonObject.get("school"));
                tmp.put("teacher", jsonObject.get("teacher"));
                tmp.put("fontSize", jsonObject.get("fontSize"));
                //tmp.put("preludeName", jsonObject.get("preludeName"));
                tmp.put("fontColor", jsonObject.get("fontColor"));
                if (jsonObject.containsKey("enableFilmHead")) {
                    String avaliable = jsonObject.get("enableFilmHead").toString();
                    if (!avaliable.equals("true")) {
                        tmp.put("avaliable", "1");
                    }
                }
            } else {
                tmp.put("title", "");
                tmp.put("course", "");
                tmp.put("grade", "");
                tmp.put("subject", "");
                tmp.put("school", "");
                tmp.put("teacher", "");
                tmp.put("fontSize", "");
                tmp.put("preludeName", "");
                tmp.put("fontColor", "");
            }

            this.target = tmp;
        }
        return this;
    }

    /**
     * todo 加注释
     * @param result
     * @return
     */
    public DirectorViewDTO getEndingVideo(Map<String, Object> result) {
        if (result != null && !result.isEmpty()) {
            this.requestParam = "curtain";
            this.token = "";
            this.event = "";
            Map<String, Object> tmp = new HashMap<>();
            if (result.get("Duration") != null) {
                tmp.put("duration", result.get("Duration"));
            }
            //如果是ftp路径，则取文件名，否则返回“”空字符
            String ftpPath1 = Constant.MATERIALPATH.toString().split("/")[1];
            tmp.put("backMusic", "");
            if (result.get("MusPath") != null && !result.get("MusPath").equals("")) {
                String[] musPaths = result.get("MusPath").toString().split("/");
                if (musPaths.length > 2 && musPaths[musPaths.length - 2].equals(ftpPath1)) {
                    tmp.put("backMusic", musPaths[musPaths.length - 1]);
                }
            }
            tmp.put("picture", "");
            if (result.get("PicPath") != null && !result.get("PicPath").equals("")) {
                String[] picPaths = result.get("PicPath").toString().split("/");
                if (picPaths.length > 2 && picPaths[picPaths.length - 2].equals(ftpPath1)) {
                    tmp.put("picture", picPaths[picPaths.length - 1]);
                }
            }
//            tmp.put("backMusic", result.get("MusPath"));
//            tmp.put("picture", result.get("PicPath"));
            tmp.put("avaliable", "0");
            if (result.containsKey("Ext") && !result.get("Ext").equals("GetVideoMaterialConfiguration-ok") && !result.get("Ext").equals("")) {
                JSONObject jsonObject = JSONObject.fromObject(result.get("Ext"));
                tmp.put("date", jsonObject.get("date") + "");
                tmp.put("copyright", jsonObject.get("copyright") + "");
                tmp.put("curtainName", jsonObject.get("curtainName") + "");
                tmp.put("production", jsonObject.get("production") + "");
                tmp.put("fontSize", jsonObject.get("fontSize") + "");
                tmp.put("fontColor", jsonObject.get("fontColor") + "");
                if (jsonObject.containsKey("enableFilmTail")) {
                    String avaliable = jsonObject.get("enableFilmTail").toString();
                    if (!avaliable.equals("true")) {
                        tmp.put("avaliable", "1");
                    }
                }
            } else {
                tmp.put("date", "");
                tmp.put("copyright", "");
                tmp.put("curtainName", "");
                tmp.put("production", "");
                tmp.put("fontSize", "");
                tmp.put("fontColor", "");
            }

            this.target = tmp;
        }
        return this;
    }

    /**
     * todo 加注释
     * @param values
     * @return
     */
    public DirectorViewDTO getRestartStatus(Map<String, Object> values) {

        if (!values.isEmpty()) {
            Map<String, String> _target = new HashMap<>();
            _target.put("workingMode", values.get("workingMode").toString());
            _target.put("recordingMode", values.get("recordingMode").toString());
            _target.put("startTime", "0");
            this.requestParam = "workingMode";
            this.token = "";
            this.event = "restart";
            this.target = _target;
        }
        return this;
    }



//    public String toJSON() {
//        if (requestParam.length() == 0) {
//            return "";
//        }
//        return JSONObject.fromObject(this).toString();
//    }

//    public static void main(String[] args) {
//        System.out.println(new DirectorViewDTO().getRecordingStatus(null).toJSON());
//    }


    public static void main(String[] args) {
//        DirectorViewDTO directorViewDTO = new DirectorViewDTO();
//        JsonConfig jsonConfig = new JsonConfig();
//        Map<String, String> www = new Hashtable<>();
//
//        www.put("CourseTitle", "2323");
//        www.put("MainTitle", "werw");
//        www.put("ClassName", "eee");
//        www.put("SubjectName", "eee");
//        www.put("School", "eeee");
//        www.put("Teacher", "wewe");
//        directorViewDTO.getCourseInfo(www);
//        jsonConfig.setExcludes(new String[]{"recordingStatus", "volume", "directMode", "layout", "courseInfo"});
//        System.out.println(JSONArray.fromObject(directorViewDTO, jsonConfig).toString());
//        System.out.println(JSONObject.fromObject("{\"curtainName\":\"curtain\",\"fontColor\":\"#000000\",\"copyright\":\"2\",\"date\":\"3\",\"fontSize\":\"10\",\"production\":\"1\"}").get("copyright"));
    }

}
