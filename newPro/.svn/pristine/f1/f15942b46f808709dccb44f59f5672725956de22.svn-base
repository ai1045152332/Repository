package com.honghe.recordweb.action.frontend.deviceview;


import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.action.SessionManager;
import com.honghe.recordweb.action.frontend.onlinetime.entity.OnlineTimeData;
import com.honghe.recordweb.service.frontend.devicemanager.ComputerCommand;
import com.honghe.recordweb.service.frontend.devicemanager.HostDevice;
import com.honghe.recordweb.service.frontend.devicemanager.WhiteboardCommand;
import com.honghe.recordweb.service.frontend.hostgroup.HostgroupService;
import com.honghe.recordweb.service.frontend.onlinetime.OnlineTimeService;
import com.honghe.recordweb.util.CommonName;
import com.opensymphony.xwork2.ActionContext;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;

/**
 * Created by lyx on 2015-12-03.
 * <p/>
 * 软件监控
 */
@Controller
@Scope(value = "prototype")
public class DeviceViewAction extends BaseAction{

    @Resource
    private HostDevice hostDevice;
    private Map<String,Object> equipMap;
    private String mac;
    @Resource
    private ComputerCommand computerCommand;
    @Resource
    private HostgroupService hostgroupService;
    @Resource
    private WhiteboardCommand whiteboardCommand;
    @Resource
    private OnlineTimeService onlineTimeService;
    private String fileName;
    private InputStream inputStream;
    private long fileLength;
    private static final Object lock_excel = new Object();
    private String startTime;//页面上选择开始时间
    private String endTime;//页面上选择的结束时间

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public long getFileLength() {
        return fileLength;
    }

    public void setFileLength(long fileLength) {
        this.fileLength = fileLength;
    }

    public static Object getLock_excel() {
        return lock_excel;
    }

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

    public OnlineTimeService getOnlineTimeService() {
        return onlineTimeService;
    }

    public void setOnlineTimeService(OnlineTimeService onlineTimeService) { this.onlineTimeService = onlineTimeService; }

    public HostgroupService getHostgroupService() {
        return hostgroupService;
    }

    public void setHostgroupService(HostgroupService hostgroupService) {
        this.hostgroupService = hostgroupService;
    }

    public Map<String, Object> getEquipMap() {
        return equipMap;
    }

    public void setEquipMap(Map<String, Object> equipMap) {
        this.equipMap = equipMap;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    /**
     * 获取当前设备的列表
     *
     * @return String
     */
    public String deviceList() {
        //获取设备类型
        String deviceType = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.DeviceType);
        ActionContext.getContext().put("hostingroup", hostDevice.getAllHostInfo(deviceType));
        return "devicelist";
    }
    /**
     * 获取相应mac地址的设备信息
     * @return  String
     */
    public void searchList(){
        String deviceType = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.DeviceType);
        Map equipinfo=hostDevice.getAllHostInfo(deviceType);
        List listinfo=(List)equipinfo.get("hostInfo");
        List list=new ArrayList();
        JSONObject json = new JSONObject();
        if("ALL".equals(mac)){
            for (int i=0;i<listinfo.size();i++){
                Map map=(Map)listinfo.get(i);
                if ("Online".equals(map.get("status"))) {
                    if (deviceType.equals("hhtc")) {
                        Map values = computerCommand.getInfo(map.get("host_ip").toString());//英文
                        hhtcCircle(values, map);
                    }else if (deviceType.equals("hhtwb")){
                       Map values = whiteboardCommand.getInfo(map.get("host_ip").toString());//英文
                        hhtwbCircle(values, map);
                    }

                }

                list.add(map);
            }
            json.put("hostingroup",listinfo);
        }else {
            for (int i = 0;i<listinfo.size(); i++) {
                Map map = (Map) listinfo.get(i);
                if (map.get("host_mac").equals(mac)) {
                    if ("Online".equals(map.get("status"))) {
                        if (deviceType.equals("hhtc")) {
                            Map  values = computerCommand.getInfo(map.get("host_ip").toString());
                            hhtcCircle(values, map);
                        }else if (deviceType.equals("hhtwb")){
                            Map  values = whiteboardCommand.getInfo(map.get("host_ip").toString());
                            hhtwbCircle(values, map);
                        }



                    }
                        list.add(map);

                }
            }
            json.put("hostingroup", list);
        }
        writeJson(json);
    }

    /**
     * 大屏
     * circle方法是供searchList()调用的方法
     * @param values
     * @param map
     */
    public void hhtcCircle(Map values, Map map){
        List flist= (List) map.get("host_cmd");
        String currentSignal="";
        String currentAudioMode="";
        String currentEnergyMode="";
        if (flist.size()>0) {
            String channelName ="";
            if(values.containsKey("ChannelName")){
                channelName=values.get("ChannelName").toString();
            }
            String audioMode="";
            if(values.containsKey("AudioMode")){
                audioMode=values.get("AudioMode").toString();
            }
            String energyMode="";
            if(values.containsKey("EnergyMode")){
                energyMode=values.get("EnergyMode").toString();
            }
            for (int i=0;i<flist.size();i++){
                Map mapCode = (Map) flist.get(i);
                String code_type = mapCode.get("codetype").toString();
                String code_cmd = mapCode.get("code_cmd").toString();
                String[] arr=code_type.split(",");
                String[] arr_cmd=code_cmd.split(",");
                if (code_cmd.contains(channelName)){
                    for (int k=0;k<arr.length;k++){
                        if(arr_cmd[k].equals(channelName)){
                            currentSignal=arr[k];//获取中文信号源
                            break;
                        }
                    }
                }
                else if (code_cmd.contains(audioMode)){
                    for (int k=0;k<arr.length;k++){
                        if (arr_cmd[k].equals(audioMode)){
                            currentAudioMode=arr[k];//获取中文音响模式
                            break;
                        }
                    }
                }
                else if (code_cmd.contains(energyMode)){
                    for (int k=0;k<arr.length;k++){
                        if (arr_cmd[k].equals(energyMode)){
                            currentEnergyMode=arr[k];//获取中文节能模式
                            break;
                        }
                    }
                }
            }
        }
        String topSoftWare = "";
        try {
            topSoftWare = java.net.URLDecoder.decode(values.get("TopSoftWare").toString(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        map.put("ChannelName",currentSignal);
        map.put("Volume",values.get("Volume").toString());
        map.put("AudioMode",currentAudioMode);
        map.put("EnergyMode",currentEnergyMode);
        map.put("CpuUsage",values.get("CpuUsage").toString());
        map.put("Memmary",values.get("Memmary").toString());
        map.put("Disk_C",values.get("Disk_C").toString());
        map.put("TopSoftWare",topSoftWare);

    }

    /**
     * 白板一体机
     * circle方法是供searchList()调用的方法
     * @param values
     * @param map
     */
    public void hhtwbCircle(Map values, Map map){
        String topSoftWare = "";
        String volume = "";
        try {
            topSoftWare = java.net.URLDecoder.decode(values.get("TopSoftWare").toString(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (values.containsKey("Volume")) {
             volume = values.get("Volume").toString();
        }

//        map.put("ChannelName",currentTurnState);
        map.put("Volume",volume);
//        map.put("AudioMode",currentAudio);
//        map.put("EnergyMode",currentSavingEnergy);
//        map.put("StandbyMode",currentStandby);
        map.put("CpuUsage",values.get("CpuUsage").toString());
        map.put("Memmary",values.get("Memmary").toString());
        map.put("Disk_C",values.get("Disk_C").toString());
        map.put("TopSoftWare",topSoftWare);

    }
}




