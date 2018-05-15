package com.honghe.recordweb.action.frontend.viewclass;

import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.dao.PagerSimple;
import com.honghe.recordhibernate.entity.*;
import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.action.SessionManager;
import com.honghe.recordweb.service.frontend.device.DeviceService;

import com.honghe.recordweb.service.frontend.device.HongheDeviceService;
import com.honghe.recordweb.service.frontend.devicemanager.HostDevice;
import com.honghe.recordweb.service.frontend.devicemanager.NVR;
import com.honghe.recordweb.service.frontend.devicemanager.NVRCommand;
import com.honghe.recordweb.service.frontend.devicemanager.message.DeviceResponseMessage;
import com.honghe.recordweb.service.frontend.hostgroup.HostgroupService;
import com.honghe.recordweb.service.frontend.liveplan.LiveplanService;
import com.honghe.recordweb.service.frontend.news.ConfigUtil;
import com.honghe.recordweb.service.frontend.settings.SettingsService;
import com.honghe.recordweb.service.frontend.timeplan.TimeplanService;
import com.honghe.recordweb.service.frontend.user.UserService;
import com.honghe.recordweb.service.frontend.websocket.DirectPool;
import com.honghe.recordweb.service.frontend.websocket.MessageSender;
import com.honghe.recordweb.util.*;
import com.honghe.service.client.Result;
import com.opensymphony.xwork2.ActionContext;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.jsoup.Connection;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: zhanghongbin
 * Date: 14-9-30
 * Time: 上午10:24
 * To change this template use File | Settings | File Templates.
 * Alter by wzz on 2015/07/15 设备管理服务整合
 */
@Controller
@Scope(value = "prototype")
public class ViewClassAction extends BaseAction {
    public final static Logger logger = Logger.getLogger(ViewClassAction.class);
    private String recordType = "";
    @Resource
    private HostgroupService hostgroupService;
    @Resource
    private HongheDeviceService hongheDeviceService;
    @Resource
    private HostDevice hostDevice;
    @Resource
    private DeviceService deviceService;
    @Resource
    private TimeplanService timeplanService;
    @Resource
    private NVR nvr;
    @Resource
    private NVRCommand nvrCommand;
    @Resource
    private SettingsService settingsService;
    @Resource
    private UserService userService;
    @Resource
    private LiveplanService liveplanService;
    private String hostid;
    private String token;

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    private Page page;

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    private String pageSize = "4";

    private String currentPage = "1";

    public String getHostid() {
        return hostid;
    }

    public void setHostid(String hostid) {
        this.hostid = hostid;
    }

    public String getToken() {
        return token;
    }

    public String getViewClassCameraName() {
        return viewClassCameraName;
    }

    public void setViewClassCameraName(String viewClassCameraName) {
        this.viewClassCameraName = viewClassCameraName;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String viewClassCameraName = "";

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    private String hostname = "";

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    private String hostIp;

    public String getSetBackground() {
        return setBackground;
    }

    public void setSetBackground(String setBackground) {
        this.setBackground = setBackground;
    }

    private String setBackground;

    private String groupid;  //分组ID

    private String pagesize;//区分四九分屏

    public String getPagesize() {
        return pagesize;
    }

    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    private String deviceId;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    private String hostIdStr;
    public String getHostIdStr() {
        return hostIdStr;
    }
    public void setHostIdStr(String hostIdStr) {
        this.hostIdStr = hostIdStr;
    }
    public String getHostGroup() {
           User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        Integer userId = user.getUserId();
        ServletActionContext.getRequest().setAttribute("groupTree", getTreeMap(userId, "NVR"));
        return "viewclass";
    }

    public void gotoViewClass() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String remoteAddr = hostgroupService.getRemoteHost(request);
        if (setBackground.equals("false")) {
            nvrCommand.setBackgroundDirectMode(this.hostIp, false, remoteAddr);
        } else {
            nvrCommand.setBackgroundDirectMode(this.hostIp, true, remoteAddr);
        }
    }

    /**
     * 获取客户端真实ip地址
     *
     * @param
     * @return
     */
//    public String getRemoteHost(HttpServletRequest request){
//        String ip = request.getHeader("x-forwarded-for");
//        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
//            ip = request.getHeader("Proxy-Client-IP");
//        }
//        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
//            ip = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
//            ip = request.getRemoteAddr();
//        }
//        return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
//    }

    /**
     * 爱录客设备从设备管理模块进行媒体流地址重定向，不再从这里重定向。2015-10-28 wzz
     */
    public void gotoDirectView() {
        String ip = this.token;
//        HttpServletRequest request = ServletActionContext.getRequest();
//        String remoteAddr = hostgroupService.getRemoteHost(request);
//        String rtpAddr = "vga=rtp://"+remoteAddr+":60586;movie=rtp://"+remoteAddr+":60588";
        String rtpAddr = "";
        nvrCommand.setBackgroundDirectMode(ip, true, rtpAddr);
    }

    //导播页面 update by zlj on 2018/04/12
    public String directorView() {
        String referer = ServletActionContext.getRequest().getHeader("referer");
        if (referer == null) {
            try {
                ServletActionContext.getResponse().sendRedirect(ServletActionContext.getRequest().getContextPath() + "/viewclass/Viewclass_getHostGroup.do");

            } catch (Exception e) {
                logger.error("", e);
            }
        }
//        return "directorviewtip";
        if (DirectPool.containKey(this.token)) {
            return "directorviewtip";
        }
        boolean isSetNas = settingsService.hasSettingNas(Integer.parseInt(this.hostid));
        List<Object[]> tokenList = deviceService.getToken(Integer.parseInt(this.hostid));
        Map<String, String[]> result = new LinkedHashMap<String, String[]>();
        List<Object[]> deviceConfigList = settingsService.getDeviceConfigList();
        Map hostInfo = hongheDeviceService.getHostInfoById(this.hostid);
        //add by xinye
        //只要不是HL-ZF0400的设备都放行
        isSetNas = hostInfo.get("dspec_id").equals("5")?isSetNas:true;
        //add by xinye end
        String hostFactory = hostInfo.get("host_factory").toString();
        String connect_param = settingsService.getConnect_param(this.hostid);

        for (Object[] deviceToken : tokenList) {
            //String url = nvr.getCameraUrl(ip, deviceToken[2].toString());
            String token = deviceToken[2].toString();
//            if (url.equals("")) {
//                url = nvr.getCameraUrl(ip, deviceToken[1].toString());
//                token = deviceToken[1].toString();
//            }
            String isPtz = "0";
            if (!hostFactory.equals("Arec")) {
                for (Object[] deviceConfig : deviceConfigList) {
                    if (deviceConfig[1].toString().equals(deviceToken[0].toString())) {
                        if ((deviceConfig[2].toString().equals(token) || deviceConfig[3].toString().equals(token)) && connect_param.equals(deviceConfig[0].toString())) {
                            isPtz = deviceConfig[4].toString();
                            break;
                        }
                    }
                }
            }
            result.put(deviceToken[0].toString(), new String[]{deviceToken[2].toString(), isPtz});


        }
        String hostName = hostInfo.get("host_name").toString();
        String liveAddr = "";
        if (settingsService.getSetting() != null && !settingsService.getSetting().getLiveAddr().trim().equals("")) {
            liveAddr = settingsService.getSetting().getLiveAddr() + "/" + DigestUtils.md5Hex(hostName);
            if (!liveAddr.toLowerCase().startsWith("rtmp")) {
                liveAddr = "rtmp://" + liveAddr;
            }
        }
        List<String> supportsList = nvrCommand.getNvrSupport(token);
        ActionContext.getContext().put("supportsList", supportsList);
        ActionContext.getContext().put("mediaUrlList", result);
        ActionContext.getContext().put("liveAddr", liveAddr);
        ActionContext.getContext().put("isSetNas", isSetNas);
        ActionContext.getContext().put("hostName", hostName);
//        JsonConfig jsonConfig = new JsonConfig();
//        jsonConfig.setExcludes(new String[]{"recordingStatus", "volume", "directMode", "layout", "courseInfo"});
//        ActionContext.getContext().put("directorViewDTOList", JSONArray.fromObject(directorViewDTOList, jsonConfig).toString());
        return "directorview";
    }

    //4屏页面
    public String viewClass4() {
        Integer pagesize = Integer.parseInt(this.pagesize);
        Integer groupId = Integer.parseInt(this.groupid);
        pagesize *= pagesize;
        try {
            viewClassCameraName = URLDecoder.decode(viewClassCameraName, "UTF-8");
        } catch (Exception e) {
            // viewClassCameraName_new = viewClassCameraName;
            logger.error("", e);
            //e.printStackTrace();
        }
        if (groupId == -1) {

            ActionContext.getContext().put("hostingroup", hostgroupService.hostInGroup(viewClassCameraName, groupId, "hostNoRelation", Integer.parseInt(this.currentPage), pagesize));
        } else {
            ActionContext.getContext().put("hostingroup", hostgroupService.hostInGroup(viewClassCameraName, groupId, "hostRelation", Integer.parseInt(this.currentPage), pagesize));
        }

        return "viewclass4";
    }

    //九屏页面
    public String viewClass9() {
        Integer groupId = Integer.parseInt(this.groupid);
        Integer pagesize = Integer.parseInt(this.pagesize);
        pagesize *= pagesize;
        try {
            viewClassCameraName = URLDecoder.decode(viewClassCameraName, "UTF-8");
        } catch (Exception e) {
            // viewClassCameraName_new = viewClassCameraName;
            logger.error("", e);
            //e.printStackTrace();
        }
        if (groupId == -1) {
            ActionContext.getContext().put("hostingroup", hostgroupService.hostInGroup(this.viewClassCameraName.trim(), groupId, "hostNoRelation", Integer.parseInt(this.currentPage), pagesize));
        } else {
            ActionContext.getContext().put("hostingroup", hostgroupService.hostInGroup(this.viewClassCameraName.trim(), groupId, "hostRelation", Integer.parseInt(this.currentPage), pagesize));

        }
        return "viewclass9";
    }

    public String viewClass(){
        String split = ServletActionContext.getRequest().getParameter("split");
        String[] ids = hostid.split(",");
        if("2".equals(split)){
            String[] strs = new String[4];
            StringBuffer sb = new StringBuffer();
            int star = (Integer.parseInt(currentPage) - 1) * 4;
            int end = (Integer.parseInt(currentPage)) * 4;

            if(ids.length == 0){
                hostid = "";
            }else{
                if(ids.length>5){
                    strs = Arrays.copyOfRange(ids, star, end);
                    for (int i = 0; i < strs.length; i++) {
                        sb.append(strs[i]+",");
                    }
                }else{
                    for (int i = 0; i < ids.length; i++) {
                        sb.append(ids[i]+",");
                    }
                }
                String s = sb.toString();
                if(s.endsWith(",")){
                    hostid = s.substring(0, s.length() - 1);
                }
            }

        }else{
            String[] strs = new String[9];
            StringBuffer sb = new StringBuffer();
            int star = (Integer.parseInt(currentPage) - 1) * 9;
            int end = (Integer.parseInt(currentPage)) * 9;

            if(ids.length == 0){
                hostid = "";
            }else{
                if(ids.length>9){
                    strs = Arrays.copyOfRange(ids, star, end);
                    for (int i = 0; i < strs.length; i++) {
                        sb.append(strs[i]+",");
                    }
                }else{
                    for (int i = 0; i < ids.length; i++) {
                        sb.append(ids[i]+",");
                    }
                }
                String s = sb.toString();
                if(s.endsWith(",")){
                    hostid = s.substring(0, s.length() - 1);
                }
            }
        }
        Result result = null;
        try {
            viewClassCameraName = URLDecoder.decode(viewClassCameraName, "UTF-8");
            if(hostid!=null&&!hostid.isEmpty()){
                Map<String,String> params = new HashMap<>();
                params.put("hostIdStr",hostid);
                params.put("deviceType","");
                result = HttpServiceUtil.service(HttpServiceUtil.SERVICE_DEVICE,HttpServiceUtil.Method.Device_HostInfo.toString(),params);
            }
        } catch (Exception e) {
            logger.error("巡课页面异常", e);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("pageCount",ServletActionContext.getRequest().getParameter("pageCount"));
        if(result!=null&&result.getCode()==0){
            map.put("hostInfo",result.getValue());
        }else{
            map.put("hostInfo",Collections.EMPTY_LIST);
        }
        ServletActionContext.getRequest().setAttribute("hostingroup",map);
//        String split = ServletActionContext.getRequest().getParameter("split");
        if("2".equals(split)){
            return "viewclass4";
        }else{
            return "viewclass9";
        }
    }
    public void viewClassAjax(){
        List<Map<String, Object>> dataList = new ArrayList<>();
        try {
            viewClassCameraName = URLDecoder.decode(URLDecoder.decode(viewClassCameraName, "UTF-8"), "UTF-8");
            String pageCount = ServletActionContext.getRequest().getParameter("pageCount");
            Map<String,String> params = new HashMap<>();
            params.put("hostIdStr",hostid);
            params.put("deviceType","");
            Result result = HttpServiceUtil.service(HttpServiceUtil.SERVICE_DEVICE,HttpServiceUtil.Method.Device_HostInfo.toString(),params);
            if (result!=null&&result.getCode()==0) {
                List<Map> hostList = (List<Map>) result.getValue();
                if (hostList != null && !hostList.isEmpty()) {
                    for (int i = 0; i < hostList.size(); i++) {
                        Map host = hostList.get(i);
                        Map<String, Object> data = new HashMap<>();
                        Map<String, String> status = new HashMap<>();
                        status.put(host.get("host_ip").toString(),host.get("status").toString());
                        data.putAll(host);
                        data.put("cameralurl", getCameraUrl(Integer.parseInt(host.get("id").toString())));
                        data.put("workingMode", getRecordStatus(host.get("host_ip").toString(),status));
                        if (i == 0) {
                            PagerSimple pager = new PagerSimple(Integer.parseInt(pageCount), Integer.parseInt(currentPage), "<span class='pages_prevpage'></span>", "<span class='pages_nextpage'></span>", "", "", true);
                            String pagers = pager.run();
                            data.put("pagers", pagers);
                        } else {
                            data.put("pagers", "");
                        }
                        dataList.add(data);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("ajax获取数据异常",e);
        }
        this.writeJSON(JSONSerializer.toJSON(dataList).toString());
    }
    //开始录制 update by zlj on 2018/04/12
    public void startRecord() {
        Integer hostId = Integer.valueOf(this.hostid);
        Map<String, String> hashmap = new HashMap<String, String>();
        int flag;
        /*Host host = timeplanService.getHost(hostId);
        boolean isSetNas = settingsService.hasSettingNas(hostId);
        if (isSetNas || host.getHostDesc().equals("1")) {
            String ip = host.getHostIpaddr();
            String ext = "";
            if (recordType.equals("1")) {
                ext = "mode_record";
                //精品录播
            }
            flag = nvrCommand.startRecord(ip, hostId, ext);
            if (flag == 1) {
                hashmap.put("success", "true");
                hashmap.put("msg", "开始录制");//成功
                if (host.getHostDesc().equals("1") && !isSetNas) {
                    hashmap.put("desc", "无NAS服务,录制到本地中");
                }
                JSONObject jsonObject = new JSONObject();
                Map<String, String> _target = new HashMap<String, String>();
                _target.put("recordingMode", "recordingModeMovie");
                _target.put("startTime", "1");
                _target.put("workingMode", "workingModeRecording");
                jsonObject.put("requestParam", "workingMode");
                jsonObject.put("target", _target);
                jsonObject.put("event", "start");
                jsonObject.put("hostId", hostId);

                nvrCommand.sendWebSocketMessage(ip + "-ONVIF", "0x005004", JSONObject.fromObject(jsonObject).toString());
            } else {
                hashmap.put("success", "false");
                hashmap.put("msg", "录制失败,请重试");//失败
            }
        } else {
            hashmap.put("success", "false");
            hashmap.put("msg", "nas未设置");
        }*/
        Map<String, Object> host = timeplanService.getHost(hostId);
        boolean isSetNas = settingsService.hasSettingNas(hostId);
        //cancel by xinye
//        if (host != null && !host.isEmpty() && (isSetNas || host.get("host_desc").toString().equals("1"))) {
        //cancel by xinye end
        //add by xinye
        //只要不是HL-ZF0400的设备都放行
        if (host != null && !host.isEmpty() && (isSetNas || !host.get("dspec_id").toString().equals("5"))) {
            //add by xinye end
            String ip = host.get("host_ipaddr").toString();
            String ext = "";
            if (recordType.equals("1")) {
                ext = "mode_record";
                //精品录播
            }
            flag = nvrCommand.startRecord(ip, hostId, ext);
            if (flag == 0) {
                hashmap.put("success", "true");
                hashmap.put("msg", "开始录制");//成功
                if (host.get("host_desc").equals("1") && !isSetNas) {
                    hashmap.put("desc", "无NAS服务,录制到本地中");
                }
                JSONObject jsonObject = new JSONObject();
                Map<String, String> _target = new HashMap<String, String>();
                _target.put("recordingMode", "recordingModeMovie");
                _target.put("startTime", "1");
                _target.put("workingMode", "workingModeRecording");
                jsonObject.put("requestParam", "workingMode");
                jsonObject.put("target", _target);
                jsonObject.put("event", "start");
                jsonObject.put("hostId", hostId);

                nvrCommand.sendWebSocketMessage(ip + "-ONVIF", "0x005004", JSONObject.fromObject(jsonObject).toString());
            } else {
                hashmap.put("success", "false");
                hashmap.put("msg", "录制失败,请重试");//失败
            }
        } else {
            hashmap.put("success", "false");
            hashmap.put("msg", "nas未设置");
        }
        writeJSON(JSONSerializer.toJSON(hashmap).toString());
    }

    //停止录制 update by zlj on 2018/04/12
    public void stopRecord() {
        Map<String, String> hashmap = new HashMap<String, String>();

        Map<String, Object> recordStatus = nvrCommand.getRecordingStatus(this.hostIp);
        if (recordStatus != null && !recordStatus.isEmpty()) {
            if (recordStatus.get("workingMode").equals("workingModeRecording")) {
                Integer hostId = Integer.parseInt(this.hostid);
//                Host host = timeplanService.getHost(hostId);
//                String ip = host.getHostIpaddr();
                Map<String, Object> hostMap = timeplanService.getHost(hostId);
                if (hostMap != null && !hostMap.isEmpty()) {
                    String ip = hostMap.get("host_ipaddr").toString();
                    int flag = nvrCommand.stopRecord(ip);
                    if (flag == 0) {
                        hashmap.put("success", "true");
                        hashmap.put("msg", "停止录制成功");//成功
                        JSONObject jsonObject = new JSONObject();
                        Map<String, String> _target = new HashMap<String, String>();
                        _target.put("recordingMode", "recordingModeMovie");
                        _target.put("startTime", "");
                        _target.put("workingMode", "workingModeRecording");
                        jsonObject.put("requestParam", "workingMode");
                        jsonObject.put("target", _target);
                        jsonObject.put("event", "stop");
                        jsonObject.put("hostId", hostId);
                        nvrCommand.sendWebSocketMessage(ip + "-ONVIF", "0x005007", JSONObject.fromObject(jsonObject).toString());
                    } else {
                        hashmap.put("success", "false");
                        hashmap.put("msg", "停止录制失败");
                    }
                }
            } else {
                hashmap.put("success", "false");
                hashmap.put("msg", "停止录制失败,请从导播台操作");
            }
        } else {
            hashmap.put("success", "false");
            hashmap.put("msg", "停止录制失败");
        }

        writeJSON(JSONSerializer.toJSON(hashmap).toString());
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    //开机 update by zlj on 2018/04/12
    public void start() {
//        Host host = timeplanService.getHost(Integer.parseInt(hostid));
//        nvrCommand.start(host.getHostIpaddr(), host.getHostMac());
        try {
            Map<String, Object> host = timeplanService.getHost(Integer.parseInt(hostid));
            if (host != null && !host.isEmpty()) {
//                nvrCommand.start(host.get("host_ipaddr").toString(), host.get("host_mac").toString());
                if ("honghe-wbox".equals(host.get("host_factory"))) {
                    nvrCommand.setRecordInfo(host.get("host_ipaddr").toString(), "power", "1", "PowerOn");
                } else {
                    nvrCommand.start(host.get("host_ipaddr").toString(), host.get("host_mac").toString());
                }
            }
        } catch (Exception e) {
            logger.error("", e);
            //e.printStackTrace();
        }
    }

    //关机 update by zlj on 2018/04/12
    public void shutdown() {
        Map<String, String> hashmap = new HashMap<String, String>();
        try {
            Map<String, Object> host = timeplanService.getHost(Integer.parseInt(hostid));
            if (host != null && !host.isEmpty()) {
                String userSessionId = ServletActionContext.getRequest().getSession().getId();
                boolean flag = nvrCommand.shutdown(host.get("host_ipaddr").toString(), userSessionId);
                if (flag) {
                    hashmap.put("success", "true");
                    hashmap.put("msg", "关机成功");//成功
                } else {
                    hashmap.put("success", "false");
                    hashmap.put("msg", "关机失败");//成功
                }

            }
        } catch (Exception e) {
            logger.error("", e);
            //e.printStackTrace();
            hashmap.put("success", "false");
            hashmap.put("msg", "未找到设备");//成功
        }
        writeJSON(JSONSerializer.toJSON(hashmap).toString());
    }

    public void upload() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String uploadFileFoldPath = request.getServletContext().getRealPath("/") + File.separator + "upload" + File.separator;
        MultiPartRequestWrapper multiPartRequestWrapper = ((MultiPartRequestWrapper) request);
        File[] pic = multiPartRequestWrapper.getFiles("pic");
        File[] audio = multiPartRequestWrapper.getFiles("audio");
        if (pic != null) {
            String name = multiPartRequestWrapper.getFileNames("pic")[0];
            IOUtil.write(pic[0], uploadFileFoldPath + name);
        }
        if (audio != null) {
            String name = multiPartRequestWrapper.getFileNames("audio")[0];
            IOUtil.write(audio[0], uploadFileFoldPath + name);
        }

    }


    public void getCameraUrl() {
        List datas = new ArrayList<HashMap<String, Object>>();
        try {
            viewClassCameraName = URLDecoder.decode(URLDecoder.decode(viewClassCameraName, "UTF-8"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("viewClassCameraName解码异常",e);
        }
        if (!this.viewClassCameraName.equals("")) {
            String[] hostIdArray = this.hostid.split(",");
            // String[] hostIpArray = this.hostIp.split(",");
            for (int i = 0; i < hostIdArray.length; i++) {
                String _hostId = hostIdArray[i];
                Map<String, Object> host = timeplanService.getHost(Integer.parseInt(_hostId));
                // String _hostIp = hostIpArray[i];
                Map<String, Object> map = new HashMap<String, Object>();
                String ip = host.get("host_ipaddr").toString();
                String factory = host.get("host_factory").toString();
                if (viewClassCameraName.equals("教师学生")) {
                    String[] video_url = getVideo(_hostId);
                    if ("honghe-wbox".equals(factory)) {
                        if (!nvr.isOnline(ip)) {
                            video_url[0] = "";
                            video_url[1] = "";
                        }
                    }
                    map.put("video_url_arr", video_url);
                    map.put("id", _hostId);
                } else {
                    String[] video_url = new String[1];
                    Object[] objects = deviceService.getStream(Integer.parseInt(_hostId), viewClassCameraName);
                    if (objects == null) {
                        video_url[0] = "";
                    } else {
                        if ("honghe-wbox".equals(factory)) {
                            if (!nvr.isOnline(ip)) {
                                video_url[0] = "";
                                video_url[1] = "";
                            } else {
                                if (!objects[1].toString().equals("")) {
                                    video_url[0] = objects[1].toString();
                                } else {
                                    video_url[0] = objects[0].toString();
                                }
                            }
                        } else {
                            if (!objects[1].toString().equals("")) {
                                video_url[0] = objects[1].toString();
                            } else {
                                video_url[0] = objects[0].toString();
                            }
                        }
                    }
                    map.put("video_url_arr", video_url);
                    map.put("id", _hostId);
                }
                datas.add(map);
            }
        } else {
            List<Object[]> tokenList = deviceService.getToken(Integer.parseInt(this.hostid));
//            Map hostInfo = hostgroupService.getHostInfoById(this.hostid);
//            String hostFactory = hostInfo.get("host_factory").toString();
//            HttpServletRequest request = ServletActionContext.getRequest();
//            String remoteAddr = hostgroupService.getRemoteHost(request);
            for (Object[] deviceToken : tokenList) {
                Map<String, String> result = new LinkedHashMap<String, String>();
                String url = deviceToken[4].toString();
                String token = deviceToken[2].toString();
//                if (url.equals("") || deviceToken[0].equals("电影模式")) {
//                    url = deviceToken[3].toString();
//                    token = deviceToken[1].toString();
//                }
                result.put("name", deviceToken[0].toString());
                result.put("token", token);
//                if(hostFactory.equals("Arec") && deviceToken[0].toString().equals("VGA")) {
//                    String vgaAddr = "rtp://"+remoteAddr+":60586";
//                    result.put("url", vgaAddr);
//                }
//                else if(hostFactory.equals("Arec") && deviceToken[0].toString().equals("电影模式")) {
//                    String movieAddr = "rtp://"+remoteAddr+":60588";
//                    result.put("url", movieAddr);
//                }
//                else{
//                    result.put("url", url);
//                }
                result.put("url", url);
                datas.add(result);
            }
        }
        this.writeJSON(JSONSerializer.toJSON(datas).toString());
    }

    private String[] getVideo(String hostId) {
        String[] streamUrl = new String[2];
        List<Object[]> streamList = deviceService.getTeachAndStudentStream(Integer.parseInt(hostId));
        if (!streamList.isEmpty()) {
            if (streamList.size() == 2) {
                for (Object[] obj : streamList) {
                    if (obj[0].toString().equals("教师全景")) {
                        if (!obj[2].toString().equals("")) {
                            streamUrl[0] = obj[2].toString();
                        } else {
                            streamUrl[0] = obj[1].toString();
                        }
                    } else {
                        if (!obj[2].toString().equals("")) {
                            streamUrl[1] = obj[2].toString();
                        } else {
                            streamUrl[1] = obj[1].toString();
                        }
                    }
                }

            } else {
                Object[] objects = streamList.get(0);
                int index;
                if (objects[0].toString().equals("教师全景")) {
                    streamUrl[1] = "";
                    index = 0;
                } else {
                    index = 1;
                    streamUrl[0] = "";
                }
                if (!objects[2].toString().equals("")) {
                    streamUrl[index] = objects[2].toString();
                } else {
                    streamUrl[index] = objects[1].toString();
                }
            }
        } else {
            streamUrl[0] = "@_@";
            streamUrl[1] = "@_@";
        }
        return streamUrl;
    }

    // update by zlj on 2018/04/12
    public void getRecordingStatus() {
        if (!this.viewClassCameraName.equals("")) {
            Map<String,String> statusMap = new HashMap<>();
            String[] hostIdArray = this.hostid.split(",");
            String[] hostIpArray = this.hostIp.split(",");
            List datas = new ArrayList<HashMap<String, Object>>();
            List<Map<String, String>> deviceStatus = hostDevice.getDevicesStatus(hostIp);
            //将设备的状态信息存入map中，key为ip，value为状态
            for (Map<String,String> status :deviceStatus){
                statusMap.put(status.get("ip"),status.get("deviceStatus"));
            }
            for (int i = 0; i < hostIdArray.length; i++) {
                String _hostId = hostIdArray[i];
                String _hostIp = hostIpArray[i];
                Map<String, Object> map = new HashMap<String, Object>();
                String hostStatus = statusMap.get(_hostIp);
                if(hostStatus.equals("Offline"))//设备如果离线，不再像设备服务获取录制状态  add by wzz 20180323
                {
                    map.put("id", _hostId);
                    map.put("status", "false");
                }
                else {
                    Map<String, Object> values = nvrCommand.getRecordingStatus(_hostIp);
                    if (values != null && !values.isEmpty()) {
                        //todo 虚拟主机没有返回值
                        String status = values.get("Status") == null ? "" : values.get("Status").toString();
                        if (status.equals("REC") || status.equals("PAUSE")) {
                            map.put("id", _hostId);
                            map.put("status", "true");
                            map.put("workingMode", values.get("workingMode"));
                        } else {
                            map.put("id", _hostId);
                            map.put("status", "false");
                        }
                    } else {
                        map.put("id", _hostId);
                        map.put("status", "false");
                    }
                }
                datas.add(map);
            }
            this.writeJSON(JSONSerializer.toJSON(datas).toString());
        } else {
            //设备如果离线，不再像设备服务获取录制状态  add by wzz 20180323
            String hostStatus = hostDevice.getStatus(this.hostIp);
            if(hostStatus.equals("Offline"))
            {
                this.toJson(new DirectorViewDTO().getRecordingStatus(Collections.EMPTY_MAP));
            }
            else
            {
                Map<String, Object> recordStatus = nvrCommand.getRecordingStatus(this.hostIp);
                DirectorViewDTO directorViewDTO = new DirectorViewDTO().getRecordingStatus(recordStatus);
                this.toJson(directorViewDTO);
            }
        }
    }

    public void getDirectMode() {
        String directMode = nvrCommand.getDirectMode(hostIp);
        DirectorViewDTO directorViewDTO = new DirectorViewDTO().getDirectMode(directMode);
        this.toJson(directorViewDTO);
    }

    public void getCourseInfo() {
        Map<String, String> courseInfoMap = new HashMap<>();
        Map<String, JSONObject> courseInfoCache = settingsService.getCourseInfoCache();
        if (courseInfoCache.isEmpty()) {
            courseInfoMap = nvrCommand.getCourseInfo(hostIp);
        } else {
            JSONObject params = courseInfoCache.get(hostIp);
            courseInfoMap.put("MainTitle", params.getString("title"));
            courseInfoMap.put("CourseTitle", params.getString("course"));
            courseInfoMap.put("ClassName", params.getString("grade"));
            courseInfoMap.put("SubjectName", params.getString("subject"));
            courseInfoMap.put("School", params.getString("school"));
            courseInfoMap.put("Teacher", params.getString("teacher"));
        }
        DirectorViewDTO directorViewDTO = new DirectorViewDTO().getCourseInfo(courseInfoMap);
        this.toJson(directorViewDTO);
    }

//    public void getCurrentLayout() {
//        String layout = nvrCommand.getCurrentLayout(hostIp);
//        Map<String, String> layoutMap = settingsService.getLayoutWindow();
//        String name = "multiScreenTeacherStudent0";
//        for (String key : layoutMap.keySet()) {
//            if (layoutMap.get(key).equals(layout)) {
//                name = key;
//                break;
//            }
//        }
//        DirectorViewDTO directorViewDTO = new DirectorViewDTO().getLayout(name);
//        this.toJson(directorViewDTO);
//    }


    //  update by zlj on 2018/04/12
    public void getAllLayout() {
        // Layout[] layouts = this.nvrCommand.getLayout(hostIp);

        List<Map<String, Object>> layoutsList = this.nvrCommand.getLayout(hostIp);
        String currentLayout = nvrCommand.getCurrentLayout(hostIp);
        List<Object[]> deviceConfigList = settingsService.getDeviceConfigList();
        String connect_param = settingsService.getConnect_paramByIp(hostIp);
        List<Map<String, Object>> result = new ArrayList<>();
        if (layoutsList != null && layoutsList.size() > 0) {
            for (Map<String, Object> _layoutMap : layoutsList) {
                String layoutName = _layoutMap.get("name").toString();
                Map<String, Object> layoutWindowMap = new LinkedHashMap<>();
                List<Map> tokenList = new ArrayList<>();
                layoutWindowMap.put("layoutId", layoutName);


                List<Map<String, String>> _winList = (List<Map<String, String>>) _layoutMap.get("windows");
                if (_winList != null && _winList.size() > 0) {
                    for (Map<String, String> _singleWinMap : _winList) {
                        Map<String, String> tokens = new HashMap<>();
                        if (_singleWinMap != null && _singleWinMap.size() > 0) {
                            String tokenId = _singleWinMap.get("videoSource").toString();
                            boolean flag = false;
                            for (Object[] deviceConfig : deviceConfigList) {
                                if ((deviceConfig[2].toString().equals(tokenId) || deviceConfig[3].toString().equals(tokenId))&&connect_param.equals(deviceConfig[0].toString())) {
                                    tokens.put("tokenId", tokenId);
                                    tokens.put("tokenNames", deviceConfig[1].toString());
                                    flag = true;
                                    break;
                                }
                            }
                            if (!flag) {
                                tokens.put("tokenId", "");
                                tokens.put("tokenNames", "");
                            }
                            tokens.put("top", String.valueOf(_singleWinMap.get("top")));
                            tokens.put("left", String.valueOf(_singleWinMap.get("left")));
                            tokens.put("width", String.valueOf(_singleWinMap.get("width")));
                            tokens.put("height", String.valueOf(_singleWinMap.get("height")));
                            tokenList.add(tokens);
                        }
                    }
                    layoutWindowMap.put("tokens", tokenList);
                    result.add(layoutWindowMap);
                }

            }
        }
        DirectorViewDTO directorViewDTO = new DirectorViewDTO().getAllLayout(result, currentLayout);
        this.toJson(directorViewDTO);
        /*if (layouts != null) {
            for (Layout layout : layouts) {
                String layoutName = layout.getName().getName();
                LayoutWindow[] layoutWindows = layout.getWindows();
                Map<String, Object> layoutWindowMap = new LinkedHashMap<>();
                List<Map> tokenList = new ArrayList<>();
                layoutWindowMap.put("layoutId", layoutName);
                layoutWindowMap.put("tokens", tokenList);
                for (LayoutWindow layoutWindow : layoutWindows) {
                    Map<String, String> tokens = new HashMap<>();
                    String tokenId = layoutWindow.getVideoSource().getToken();
                    boolean flag = false;
                    for (Object[] deviceConfig : deviceConfigList) {
                        if (deviceConfig[2].toString().equals(tokenId) || deviceConfig[3].toString().equals(tokenId)) {
                            tokens.put("tokenId", tokenId);
                            tokens.put("tokenNames", deviceConfig[1].toString());
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        tokens.put("tokenId", "");
                        tokens.put("tokenNames", "");
                    }
                    tokens.put("top", String.valueOf(layoutWindow.getTop()));
                    tokens.put("left", String.valueOf(layoutWindow.getLeft()));
                    tokens.put("width", String.valueOf(layoutWindow.getWidth()));
                    tokens.put("height", String.valueOf(layoutWindow.getHeight()));
                    tokenList.add(tokens);
                }
                result.add(layoutWindowMap);
            }

        }
        DirectorViewDTO directorViewDTO = new DirectorViewDTO().getAllLayout(result, currentLayout);
        this.toJson(directorViewDTO);*/
    }

    public void getTitleVideo() {
        Map<String, Object> titleVideo = nvrCommand.getTitleVideo(hostIp);
        DirectorViewDTO directorViewDTO = new DirectorViewDTO().getTitleVideo(titleVideo);
        this.toJson(directorViewDTO);
    }

    public void getEndingVideo() {
        Map<String, Object> endingVideo = nvrCommand.getEndingVideo(hostIp);
        DirectorViewDTO directorViewDTO = new DirectorViewDTO().getEndingVideo(endingVideo);
        this.toJson(directorViewDTO);
    }

    private void toJson(DirectorViewDTO directorViewDTO) {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"recordingStatus", "volume", "directMode", "layout", "courseInfo", "titleVideo", "endingVideo", "tokenName", "restartStatus", "allLayout"});
        this.writeJSON(JSONObject.fromObject(directorViewDTO, jsonConfig).toString());
    }

    public void getLiveRoom() {
        String content = "";
        //統一配置文件，將原system.properties的配置信息迁移到config.properties wzz 2017-01-5
        String url = "http://" + ConfigUtil.get("ResourceIp") + "/apps/live_api.php?cmd=get_lives&key=getlive&flag=0";
        try {
            content = org.jsoup.Jsoup.connect(url).method(Connection.Method.GET).ignoreContentType(true).execute().body().trim();
            content = StringEscapeUtils.unescapeJava(content).trim();
            logger.debug("获取直播间：" + content);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("state", "fail");
            content = JSONSerializer.toJSON(error).toString();
            logger.debug("无法获取直播间");
        }
        if (CacheUtil.contain(this.token)) {
            String _vid = CacheUtil.get(this.token)[0];
            if (content.indexOf("videoid\":\"" + _vid) != -1) {
                CacheUtil.remove(this.token);
            } else {
                if (content.indexOf("\"data\":null") != -1) {
                    CacheUtil.remove(this.token);
                } else {
                    content = "{flag:''}";
                }
            }
        }
        this.writeJSON(content);
    }

    private String videoId;
    private String title;
    private String roomName;
    private String videoPassword;
    private String broadCast;
    private String school;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    private String startTime;
    private String endTime;

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    private String course;
    private String subject;
    private String grade;

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    private String teacher;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String code;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getVideoPassword() {
        return videoPassword;
    }

    public void setVideoPassword(String videoPassword) {
        this.videoPassword = videoPassword;
    }

    public String getBroadCast() {
        return broadCast;
    }

    public void setBroadCast(String broadCast) {
        this.broadCast = broadCast;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    public void closeLiveRoom() {
        //統一配置文件，將原system.properties的配置信息迁移到config.properties wzz 2017-01-5
        String url = "http://" + ConfigUtil.get("ResourceIp") + "/apps/server_api.php?cmd=end_live&key=hellokey&videoid=" + videoId;
        String content = "";
        try {
            Map<String, String> data = new HashMap<>();
            data.put("start_time", DateUtil.now());
            data.put("end_time", DateUtil.getOneHoursAgoTime());
            content = org.jsoup.Jsoup.connect(url).method(Connection.Method.POST).data(data).ignoreContentType(true).execute().body().trim();
            content = StringEscapeUtils.unescapeJava(content).trim();
        } catch (Exception e) {
            logger.error("", e);
            //e.printStackTrace();
        }
    }


    public void setLiveRoom() {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("roomid",videoId);
            data.put("title", title);//标题
//            data.put("description", description);//科目
            data.put("subject", subject);//教师
            data.put("teacher_name", teacher);//教师
            data.put("room_name", roomName);//直播间名称
            data.put("start", startTime);//开始时间
            data.put("end", endTime);//结束时间
            String rtmpUrl = "[{\"stream\":\""+code+"\",\"name\":\"电影模式\"}]";
            data.put("code", rtmpUrl);
            if(liveplanService.editLiveRomm(data))
            {
                CacheUtil.put(token, new String[]{videoId, videoPassword, broadCast});
                this.writeJSON("{'result':'true'}");
            }
            else
            {
                this.writeJSON("{'result':'false'}");
            }
        } catch (Exception e) {
            logger.error("", e);
            this.writeJSON("{'result':'false'}");
        }
    }

    public void getLiveRoomId() {
        Map<String, String> result = new HashMap<>();
        if (CacheUtil.contain(token)) {
            String[] data = CacheUtil.get(token);
            result.put("id", data[0]);
        } else {
            result.put("id", "");
        }
        this.writeJSON(JSONObject.fromObject(result).toString());
    }

//    public static void main(String[] args) {
//        String ww = "{\"ret\":\"0\",\"msg\":\"\\u6307\\u4ee4\\u6267\\u884c\\u5b8c\\u6bd5\",\"state\":\"ok\",\"data\":{\"msg\":\"\\u6307\\u4ee4\\u6267\\u884c\\u5b8c\\u6bd5\",\"state\":\"ok\",\"data\":0}}";
//        System.out.println(StringEscapeUtils.unescapeJava(ww));
//    }


    /**
     * 强制使用导播需要删除token
     */
    public void removeDirectPoolToken() {
        DeviceResponseMessage deviceResponseMessage = new DeviceResponseMessage(this.token, "hhrec", DeviceResponseMessage.Type.EVENT, "100");
        deviceResponseMessage.setEventType("100");
        DirectPool.remove(this.token);
        MessageSender.send(deviceResponseMessage.toJson());
        // nvrCommand.setBackgroundDirectMode(this.token, true);
    }

    //四屏页面-ajax todo
    public void viewClass4Ajax() {
        Map<String,String> statusMap = new HashMap<>();
        Integer groupId = Integer.parseInt(this.groupid);
        Integer pagesize = Integer.parseInt(this.pagesize);
        pagesize *= pagesize;
        try {
            viewClassCameraName = URLDecoder.decode(URLDecoder.decode(viewClassCameraName, "UTF-8"), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> hostMap = new HashMap<>();
        if (groupId == -1) {
            hostMap = hostgroupService.hostInGroup(this.viewClassCameraName.trim(), groupId, "hostNoRelation", Integer.parseInt(this.currentPage), pagesize);
        } else {
            hostMap = hostgroupService.hostInGroup(this.viewClassCameraName.trim(), groupId, "hostRelation", Integer.parseInt(this.currentPage), pagesize);
        }

        //批量查询设备在离线状态
        List<Map<String, String>> deviceStatus = hostDevice.getDevicesStatus(hostIp);
        //将设备的状态信息存入map中，key为ip，value为状态
        for (Map<String,String> status :deviceStatus){
            statusMap.put(status.get("ip"),status.get("deviceStatus"));
        }
        List<Map<String, Object>> dataList = new ArrayList<>();
        if (!hostMap.isEmpty()) {
            List<Map> hostList = (List<Map>) hostMap.get("hostInfo");
            if (hostList != null && !hostList.isEmpty()) {
                for (int i = 0; i < hostList.size(); i++) {
                    Map host = hostList.get(i);
                    Map<String, Object> data = new HashMap<>();
                    data.putAll(host);
                    data.put("cameralurl", getCameraUrl(Integer.parseInt(host.get("id").toString())));
                    data.put("workingMode", getRecordStatus(host.get("host_ip").toString(),statusMap));
                    if (i == 0) {
                        PagerSimple pager = new PagerSimple(Integer.parseInt(hostMap.get("pageCount").toString()), Integer.parseInt(this.currentPage), "<span class='pages_prevpage'></span>", "<span class='pages_nextpage'></span>", "", "", true);
                        String pagers = pager.run();
                        data.put("pagers", pagers);
                    } else {
                        data.put("pagers", "");
                    }
                    dataList.add(data);
                }
            }
        }
        this.writeJSON(JSONSerializer.toJSON(dataList).toString());
    }

    //九屏页面-ajax
    public void viewClass9Ajax() {
        Map<String,String> statusMap = new HashMap<>();
        Integer groupId = Integer.parseInt(this.groupid);
        Integer pagesize = Integer.parseInt(this.pagesize);
        pagesize *= pagesize;
        try {
            viewClassCameraName = URLDecoder.decode(URLDecoder.decode(viewClassCameraName, "UTF-8"), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> hostMap = new HashMap<>();
        if (groupId == -1) {
            hostMap = hostgroupService.hostInGroup(this.viewClassCameraName.trim(), groupId, "hostNoRelation", Integer.parseInt(this.currentPage), pagesize);
        } else {
            hostMap = hostgroupService.hostInGroup(this.viewClassCameraName.trim(), groupId, "hostRelation", Integer.parseInt(this.currentPage), pagesize);
        }

        //批量查询设备在离线状态
        List<Map<String, String>> deviceStatus = hostDevice.getDevicesStatus(hostIp);
        //将设备的状态信息存入map中，key为ip，value为状态
        for (Map<String,String> status :deviceStatus){
            statusMap.put(status.get("ip"),status.get("deviceStatus"));
        }
        List<Map<String, Object>> dataList = new ArrayList<>();
        if (!hostMap.isEmpty()) {
            List<Map> hostList = (List<Map>) hostMap.get("hostInfo");
            if (hostList != null && !hostList.isEmpty()) {
                PagerSimple pager = new PagerSimple(Integer.parseInt(hostMap.get("pageCount").toString()), Integer.parseInt(this.currentPage), "<span class='pages_prevpage'></span>", "<span class='pages_nextpage'></span>", "", "", true);
                for (Map host : hostList) {
                    Map<String, Object> data = new HashMap<>();
                    data.putAll(host);
                    data.put("cameralurl", getCameraUrl(Integer.parseInt(host.get("id").toString())));
                    data.put("workingMode", getRecordStatus(host.get("host_ip").toString(),statusMap));
                    String pagers = pager.run();
                    data.put("pagers", pagers);
                    dataList.add(data);
                }
            }
        }
        this.writeJSON(JSONSerializer.toJSON(dataList).toString());
    }

    /**
     * 获取设备录制状态
     * update by zlj on 2018/04/12
     * @param hostIp
     * @param statusMap
     * @return
     */
    public String getRecordStatus(String hostIp,Map<String,String> statusMap) {
        String re_value = "false";
        String hostStatus = statusMap.get(hostIp);//如果设备离线，不再向设备服务获取录制状态 add by wzz 20180323
        if(hostStatus.equals("Offline")) {
            return "false";
        }
        Map<String, Object> values = nvrCommand.getRecordingStatus(hostIp);
        if (values != null && !values.isEmpty()) {
            String status = String.valueOf(values.get("Status"));
            if (status.equals("REC") || status.equals("PAUSE")) {
                re_value = String.valueOf(values.get("workingMode"));
            }
        }
        return re_value;
    }

    public String[] getCameraUrl(int hostid) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (viewClassCameraName.equals("教师学生")) {
            String[] video_url = getVideo(hostid + "");
            return video_url;
        } else {
            String[] video_url = new String[1];
            Object[] objects = deviceService.getStream(hostid, viewClassCameraName);
            if (objects == null) {
                video_url[0] = "";
            } else {
                if (!objects[1].toString().equals("")) {
                    video_url[0] = objects[1].toString();
                } else {
                    video_url[0] = objects[0].toString();
                }
            }
            return video_url;
        }
    }

    /**
     * 分屏
     * update by zlj on 2018/04/12
     * @return
     */
    public String splitscreen() {
        List<Object[]> tokenList = deviceService.getToken(Integer.parseInt(hostid));
        List<Object[]> deviceConfigList = settingsService.getDeviceConfigList();
        Map hostInfo = hongheDeviceService.getHostInfoById(this.hostid);
        String dspecid = hostInfo.get("dspec_id").toString();
        String hostFactory = hostInfo.get("host_factory").toString();
        Map result = new LinkedHashMap();

        //获取主机类型
        String connect_param = "";
        List<Dspecification> dspecifications = hostgroupService.getSpecList();
        for (Dspecification dspecification : dspecifications) {
            if (dspecification.getDspecId() == Integer.valueOf(dspecid)) {
                connect_param = dspecification.getConnectParam();
            }
        }

        //获取镜头信息
        List<Object[]> deviceList = deviceService.getDeviceListService(Integer.parseInt(hostid));

        for (int i = 0; i < deviceList.size(); i++) {
            Object[] device = deviceList.get(i);
            if (device[1].equals("电影模式")) {
                // 判断如果第一个位置不是电影模式
                if (i != 0) {
                    Object[] device0 = deviceList.get(0);
                    deviceList.set(0, deviceList.get(i));
                    deviceList.set(i, device0);
                }
            }
        }
        for (Object[] deviceToken : tokenList) {
            String token = deviceToken[2].toString();
            String isPtz = "0";
            if (!hostFactory.equals("Arec")) {
                if(dspecid.equals("17"))
                {
                    isPtz = "1";
                }
                else {
                    for (Object[] deviceConfig : deviceConfigList) {
                        if (deviceConfig[1].toString().equals(deviceToken[0].toString())) {
                            if ((deviceConfig[2].toString().equals(token) || deviceConfig[3].toString().equals(token)) && connect_param.equals(deviceConfig[0].toString())) {
                                isPtz = deviceConfig[4].toString();
                                break;
                            }
                        }
                    }
                }
            }
            result.put(deviceToken[0].toString(), isPtz);
        }

        ActionContext.getContext().put("deviceList", deviceList);
        ActionContext.getContext().put("hostName",hostInfo.get("host_name").toString());
        ActionContext.getContext().put("hostIp",hostInfo.get("host_ipaddr").toString());
        ActionContext.getContext().put("hostId",Integer.valueOf(hostid));
        ActionContext.getContext().put("dspecid",dspecid);
        ActionContext.getContext().put("mediaUrlList", result);
        if (tokenList.size() == 3) {
            return "split3";
        } else if (tokenList.size() == 4) {
            return "split4";
        } else if (tokenList.size() == 5) {
            return "split5";
        } else if (tokenList.size() == 6) {
            return "split6";
        } else if (tokenList.size() == 7) {
            return "split7";
        } else if (tokenList.size() == 8) {
            return "split8";
        } else if (tokenList.size() == 9) {
            return "split9";
        }else if (tokenList.size() == 1) {
            return "split1";
        }else if (tokenList.size() == 2) {
            return "split2";
        }

        return "split0";
    }
    /**
     * 镜头界面
     *
     * 从外网设备获取视频流仍为内网地址
     */
    public String cameraInfo() {
        Device device = deviceService.getDeviceInfoService(Integer.parseInt(this.deviceId));
        String mediaUrl = "";
        mediaUrl = device.getDeviceMainStream();
        if (mediaUrl.equals("")) {
            mediaUrl = device.getDeviceSubStream();
        }
        //ActionContext.getContext().put("mediaUrl", mediaUrl);
        ActionContext.getContext().put("device", device);
        return "cameraInfo";
    }
    public void findCulum() {

        JSONObject jsonObject = new JSONObject();
        try {
            Curriculum curriculum = settingsService.findCulum(hostid);
            if(curriculum != null) {
                jsonObject.put("teacher", curriculum.getCurTeacher()==null?"":curriculum.getCurTeacher());
                jsonObject.put("subject", curriculum.getCurSubject()==null?"":curriculum.getCurSubject());
                jsonObject.put("class", curriculum.getCurClass()==null?"":curriculum.getCurClass());
                jsonObject.put("unit", curriculum.getCurUnit()==null?"":curriculum.getCurUnit());
                jsonObject.put("hostid", hostid);
            }
            else
            {
                jsonObject.put("teacher", "");
                jsonObject.put("subject", "");
                jsonObject.put("class", "");
                jsonObject.put("unit", "");
                jsonObject.put("hostid", hostid);
            }

        } catch (Exception e) {
            jsonObject.put("teacher", "");
            jsonObject.put("subject", "");
            jsonObject.put("class", "");
            jsonObject.put("unit", "");
            jsonObject.put("hostid", hostid);
        }

        writeJson(jsonObject);
//        jsonObject.put("curriculum", JSONObject.fromObject(curriculum));
    }

}
