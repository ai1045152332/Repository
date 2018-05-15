package com.honghe.recordweb.action.frontend.hhtwhiteboard;

import com.alibaba.fastjson.JSONObject;
import com.honghe.recordhibernate.entity.User;
import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.action.SessionManager;
import com.honghe.recordweb.service.frontend.devicemanager.WhiteboardCommand;
import com.honghe.recordweb.service.frontend.hostgroup.HostgroupService;
import com.honghe.recordweb.service.frontend.syslog.SyslogService;
import com.honghe.recordweb.service.frontend.user.UserService;
import com.honghe.recordweb.util.CommonName;
import com.honghe.recordweb.util.RoleUtil;
import com.opensymphony.xwork2.ActionContext;
import net.sf.json.JSONSerializer;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by Techer on 2016/5/23.
 */

@Controller
@Scope(value = "prototype")
public class HHTWhiteboardAction extends BaseAction {
    @Resource
    private WhiteboardCommand whiteboardCommand;
    @Resource
    private SyslogService syslogService;
    @Resource
    private HostgroupService hostgroupService;
    @Resource
    private UserService userService;

    private Map<String, List<Map<String, String>>> cmdMap;

    private String hostIp;  //主机设备IP
    private String cmdCode; //开关机命令
    private String hostid; //主机设备Id
    private String pageSize = "4";
    private String groupid;//组id
    private String currentPage = "1";

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getCmdCode() {
        return cmdCode;
    }

    public void setCmdCode(String cmdCode) {
        this.cmdCode = cmdCode;
    }

    public Map<String, List<Map<String, String>>> getCmdMap() {
        return cmdMap;
    }

    public void setCmdMap(Map<String, List<Map<String, String>>> cmdMap) {
        this.cmdMap = cmdMap;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getPageSize() {
        return pageSize;

    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getHostid() {
        return hostid;
    }

    public void setHostid(String hostid) {
        this.hostid = hostid;
    }


    public String getHostGroup() {
        SessionManager.add(ServletActionContext.getRequest().getSession(), SessionManager.Key.DeviceType, CommonName.DEVICE_TYPE_WHITEBOARD);
        if (!app_source()) {
            cmdMap = whiteboardCommand.getDspecCommand(CommonName.DEVICE_TYPE_WHITEBOARD);
            User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
            final String type = CommonName.DEVICE_TYPE_WHITEBOARD;
            Integer userId = user.getUserId();
            Map<String, String> roleName = userService.getRoleByUid(userId.toString());
            userId = RoleUtil.RoleToPermission(userId, roleName);

            if (userId == 0) {
                final int uid = userId;
                Map<String, Object> result = super.getTreeMap(uid, type, hostgroupService);
                if (result.isEmpty()) {
                    ActionContext.getContext().put("", Collections.EMPTY_LIST);
                } else {
                    List<Map> groupTree = (List<Map>) result.get("groupTree");
                    Map unknowGroup = (Map) result.get("unknowGroup");
                    List<Map> hostList = (List<Map>) unknowGroup.get("host_list");
                    if (!hostList.isEmpty()) {
                        groupTree.add(0, unknowGroup);
                    }
                    ActionContext.getContext().put("groupTree", groupTree);
                }
            } else {
                ActionContext.getContext().put("groupTree", hostgroupService.getGroupService(userId, type));
            }
            return "whiteboard";
        }
        return "authorityDeny";
    }

    /**
     * 白板一体机开机
     */
    public void start() {
        boolean flag = whiteboardCommand.start(hostIp);
        Map<String, String> hashmap = new HashMap<String, String>();
        if (flag) {
            hashmap.put("success", "true");
            hashmap.put("msg", "开机成功");//成功
            syslogService.addDeviceLog(hostIp, "白板一体机开机成功", "DCONTROL");
        } else {
            hashmap.put("success", "false");
            hashmap.put("msg", "开机失败");//成功
            syslogService.addDeviceLog(hostIp, "白板一体机开机失败", "DCONTROL");
        }
        this.writeJSON(JSONSerializer.toJSON(hashmap).toString());
    }

    /**
     * 白板一体机关机
     */
    public void shutdown() {
        boolean flag = whiteboardCommand.shutdown(hostIp, cmdCode);
        Map<String, String> hashmap = new HashMap<String, String>();
        if (flag) {
            hashmap.put("success", "true");
            hashmap.put("msg", "关机成功");//成功
            syslogService.addDeviceLog(hostIp, "白板一体机关机成功", "DCONTROL");
        } else {
            hashmap.put("success", "false");
            hashmap.put("msg", "关机失败");//成功
            syslogService.addDeviceLog(hostIp, "白板一体机开机失败", "DCONTROL");
        }
        writeJSON(JSONSerializer.toJSON(hashmap).toString());
    }

    /**
     * 切换信号源
     */
    public void changeSignal() {
        Map<String, String> hashmap = new HashMap<String, String>();
        boolean flag = whiteboardCommand.changSignal(hostIp, cmdCode);
        if (flag) {
            hashmap.put("success", "true");
            syslogService.addDeviceLog(hostIp, "切换信号源到" + cmdCode + "成功", "DCONTROL");
        } else {
            hashmap.put("success", "false");
            syslogService.addDeviceLog(hostIp, "切换信号源到" + cmdCode + "失败", "DCONTROL");
        }
        writeJSON(JSONSerializer.toJSON(hashmap).toString());
    }

    /**
     * 获取设备截屏
     */
    public void getImage() {
        String[] hostIdArray = this.hostid.split(",");
        String[] hostIpArray = this.hostIp.split(",");
        List datas = new ArrayList<HashMap<String, Object>>();

        for (int i = 0; i < hostIdArray.length; i++) {
            String _hostId = hostIdArray[i];
            String _hostIp = hostIpArray[i];
            Map<String, String> images = (Map<String, String>) whiteboardCommand.getPictrue(_hostIp);
            if (images != null && !images.isEmpty()) {
                images.put("id", _hostId);
                datas.add(images);
            } else {
                Map<String, String> map = new HashMap<>();
                map.put("id", _hostId);
                map.put("Type", "");
                map.put("Value", "");
                datas.add(map);
            }
        }
        this.writeJSON(JSONSerializer.toJSON(datas).toString());
    }

    /**
     * 获取设备信息
     */
    public void getInfo() {
        String[] hostIdArray = this.hostid.split(",");
        String[] hostIpArray = this.hostIp.split(",");
        List datas = new ArrayList<HashMap<String, Object>>();

        for (int i = 0; i < hostIdArray.length; i++) {
            String _hostId = hostIdArray[i];
            String _hostIp = hostIpArray[i];
            Map<String, String> values = whiteboardCommand.getInfo(_hostIp);
            if (values != null && !values.isEmpty()) {
                String a = values.get("TopSoftWare");
                String strChinese = "";
                try {
                    if (a != null) {
                        strChinese = java.net.URLDecoder.decode(a, "utf-8");
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                values.put("id", _hostId);
                values.put("TopSoftWare", strChinese);
                datas.add(values);
            } else {
                Map<String, String> map = new HashMap<>();
                map.put("id", _hostId);
                map.put("ChannelName", "");
                map.put("EnergyMode", "");
                map.put("Volume", "");
                map.put("AudioMode", "");
                map.put("TouchMode", "");
                map.put("TopSoftWare", "");
                datas.add(map);
            }
        }
        this.writeJSON(JSONSerializer.toJSON(datas).toString());
    }

    /**
     * 4/9屏页面
     * @return
     */
    public String deviceList() {
        if (!app_source()) {
            Integer pagesize = Integer.parseInt(pageSize);
            Integer groupId = 0;
            try {
                groupId = Integer.parseInt(this.groupid);
            } catch (Exception e) {
                // e.printStackTrace();
            }
            String type = CommonName.DEVICE_TYPE_WHITEBOARD;
            if (groupId == -1) {//获取未分组设备
                ActionContext.getContext().put("hostingroup", hostgroupService.hostInGroup(groupId, "hostNoRelation", Integer.parseInt(this.currentPage), pagesize, type));
            } else {//获取该组下的设备
                ActionContext.getContext().put("hostingroup", hostgroupService.hostInGroup(groupId, "hostRelation", Integer.parseInt(this.currentPage), pagesize, type));
            }
            if (pagesize == 4) {
                return "hhtwblist";
            } else {
                return "hhtwblist9";
            }
        } else {
            return "authorityDeny";
        }
    }

    /**
     * 一键锁定/解锁
     */
    public void setBoardOneKeyLock(){
        JSONObject json=new JSONObject();
        boolean result=whiteboardCommand.setBoardOneKeyLock(hostIp, cmdCode);
        if (result){
            json.put("success",true);
            json.put("msg",result);
        }else {
            json.put("success",false);
            if (cmdCode.equals("lock")){
                json.put("msg","一键锁定失败");
            }else{
                json.put("msg","一键解锁失败");
            }

        }
        writeJSON(json.toString());
    }

    /**
     * 音量设置
     */
    public void audioControl(){
        JSONObject json = new JSONObject();
        boolean result = whiteboardCommand.setBoardVolume(hostIp, cmdCode);
        if (result){
            json.put("success",true);
            json.put("msg",result);
        }else {
            json.put("success",false);
            json.put("msg","音量设置失败");
        }
        writeJSON(json.toString());
    }

    /**
     * 静音
     */
    public void boardMuteState(){
        JSONObject json = new JSONObject();
        boolean result = whiteboardCommand.setBoardMuteState(hostIp, cmdCode);
        if (result){
            json.put("success",true);
            json.put("msg",result);
        }else {
            json.put("success",false);
            json.put("msg","静音设置失败");
        }
        writeJSON(json.toString());
    }

    /**
     * 投影机待机
     */
    public void boardProjectorStandby(){
        JSONObject json = new JSONObject();
        boolean result = whiteboardCommand.setBoardProjectorStandby(hostIp, cmdCode);
        if (result){
            json.put("success",true);
            json.put("msg",result);
        }else {
            json.put("success",false);
            if (cmdCode.equals("Standby")){
                json.put("msg","待机模式切换失败");
            }else{
                json.put("msg","正常模式切换失败");
            }
        }
        writeJSON(json.toString());
    }

    /**
     * 投影机省电
     */
    public void  boardProjectorEnergy(){
        HashMap hashmap = new HashMap();
        boolean result = whiteboardCommand.setBoardProjectorEnergy(hostIp,cmdCode);
        if (result) {
            hashmap.put("success", "true");
            syslogService.addDeviceLog(hostIp, "切换投影机" + cmdCode + "成功", "DCONTROL");
        } else {
            hashmap.put("success", "false");
            syslogService.addDeviceLog(hostIp, "切换投影机" + cmdCode + "失败", "DCONTROL");
        }
        writeJSON(JSONSerializer.toJSON(hashmap).toString());
    }

    /**
     * 清理垃圾
     */
    public void cleanRubbish() {
        Map<String, String> hashmap = new HashMap<String, String>();
        boolean flag = whiteboardCommand.cleanRubbish(hostIp);
        if (flag) {
            hashmap.put("success", "true");
            syslogService.addDeviceLog(hostIp, "清理垃圾成功", "DCONTROL");
        } else {
            hashmap.put("success", "false");
            syslogService.addDeviceLog(hostIp, "清理垃圾失败", "DCONTROL");
        }
        writeJSON(JSONSerializer.toJSON(hashmap).toString());
    }

    /**
     * 打铃
     */
    public void ring(){
        JSONObject json = new JSONObject();
        boolean result = whiteboardCommand.ring(hostIp);
        if (result){
            json.put("success",true);
            json.put("msg","打铃成功");
        }else {
            json.put("success",false);
            json.put("msg","打铃失败");
        }
        writeJSON(json.toString());
    }

    /**
     * 系统还原
     */
    public void recovery() {
        Map<String, String> hashmap = new HashMap<String, String>();
        boolean flag = whiteboardCommand.recovery(hostIp);
        if (flag) {
            hashmap.put("success", "true");
            syslogService.addDeviceLog(hostIp, "系统还原成功", "DCONTROL");
        } else {
            hashmap.put("success", "false");
            syslogService.addDeviceLog(hostIp, "系统还原失败", "DCONTROL");
        }
        writeJSON(JSONSerializer.toJSON(hashmap).toString());
    }

    /**
     * 系统备份
     */
    public void backup() {
        Map<String, String> hashmap = new HashMap<String, String>();
        boolean flag = whiteboardCommand.backup(hostIp);
        if (flag) {
            hashmap.put("success", "true");
            syslogService.addDeviceLog(hostIp, "系统备份成功", "DCONTROL");
        } else {
            hashmap.put("success", "false");
            syslogService.addDeviceLog(hostIp, "系统备份失败", "DCONTROL");
        }
        writeJSON(JSONSerializer.toJSON(hashmap).toString());
    }


}
