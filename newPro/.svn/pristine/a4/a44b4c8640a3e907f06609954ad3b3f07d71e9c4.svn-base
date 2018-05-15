package com.honghe.recordweb.action.frontend.dmanager;

import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.entity.User;
import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.action.SessionManager;
import com.honghe.recordweb.service.frontend.devicemanager.ComputerCommand;
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
 * Created with IntelliJ IDEA.
 * User: zhanghongbin
 * Date: 14-9-30
 * Time: 上午10:24
 * To change this template use File | Settings | File Templates.
 */
@Controller
@Scope(value = "prototype")
public class DManagerAction extends BaseAction {
    @Resource
    private HostgroupService hostgroupService;
    @Resource
    private SyslogService syslogService;
    @Resource
    private ComputerCommand computerCommand;
    @Resource
    private UserService userService;

    private String hostid;

    private String token;

    private Page page;

    private String cmdCode;

    private String pageSize = "4";

    private String currentPage = "1";

    private String hostname = "";

    private String hostIp;

    private String hostName;

    private String TVName;

    private String userId;//用户id

    private String groupid;//组id

    private String pagesize;//每页总数

    private String parames;

    private String dapingip;

    private String hostIpStr;

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public String getCmdCode() {
        return cmdCode;
    }

    public void setCmdCode(String cmdCode) {
        this.cmdCode = cmdCode;
    }

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

    public String getHostid() {
        return hostid;
    }

    public void setHostid(String hostid) {
        this.hostid = hostid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public Map<String, List<Map<String, String>>> getCmdMap() {
        return cmdMap;
    }

    public void setCmdMap(Map<String, List<Map<String, String>>> cmdMap) {
        this.cmdMap = cmdMap;
    }

    private Map<String, List<Map<String, String>>> cmdMap;

    public String getTVName() {
        return TVName;
    }

    public void setTVName(String TVName) {
        this.TVName = TVName;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPagesize() {
        return pagesize;
    }

    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }

    public String getParames() {
        return parames;
    }

    public void setParames(String parames) { this.parames = parames; }

    public String getDapingip() {
        return dapingip;
    }

    public void setDapingip(String dapingip) {
        this.dapingip = dapingip;
    }

    public String getHostIpStr() {
        return hostIpStr;
    }

    public void setHostIpStr(String hostIpStr) {
        this.hostIpStr = hostIpStr;
    }

    /**
     *app获取设备控制主界面
     */
    public void appGetHostGroup() {
        cmdMap = computerCommand.getDspecCommand(CommonName.DEVICE_TYPE_SCREEN);
        Map<String, Object> hashmap = new HashMap<String, Object>();
        final String type = CommonName.DEVICE_TYPE_SCREEN;
        if (app_source()) {
            Integer userId = Integer.parseInt(this.userId);
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
                    hashmap.put("groupTree", groupTree);
                }
            } else {
                hashmap.put("groupTree", hostgroupService.getGroupService(userId, type));
            }
            hashmap.put("cmdMap", cmdMap);
            writeJSON(JSONSerializer.toJSON(hashmap).toString());
        }
    }

    /**
     *获取设备控制主界面
     * @return String 设备控制主界面
     */
    public String getHostGroup() {
        SessionManager.add(ServletActionContext.getRequest().getSession(), SessionManager.Key.DeviceType, CommonName.DEVICE_TYPE_SCREEN);
        if (!app_source()) {
            cmdMap = computerCommand.getDspecCommand(CommonName.DEVICE_TYPE_SCREEN);
            User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
            final String type = "hhtc";
            Integer userId = user.getUserId();
            Map<String, String> roleName = userService.getRoleByUid(userId.toString());
            userId = RoleUtil.RoleToPermission(userId, roleName);

            if (userId == 0) {
                //TODO 加注释 userId == 0
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
            return "dmanager";
        }
        return "authorityDeny";
    }

    /**
     * 四九屏页面
     * @return String 返回四屏或九屏页面
     */
    public String deviceList() {
        if (!app_source()) {
            int pagesize;
            int groupId;
            int currpage;
            System.out.println("1pagesize:" + this.pageSize + "groupid:" + this.groupid + "currentPage:" + this.currentPage);
            groupId = Integer.parseInt(this.groupid);
            pagesize = Integer.parseInt(pageSize);
            currpage = Integer.parseInt(this.currentPage);
            System.out.println("2pagesize:" + pagesize + "groupid:" + groupId + "currentPage:" + currpage);
            String type = "hhtc";
            if (groupId == -1) {//获取未分组设备
                ActionContext.getContext().put("hostingroup", hostgroupService.hostInGroup(groupId, "hostNoRelation", currpage, pagesize, type));
            } else {//获取该组下的设备
                ActionContext.getContext().put("hostingroup", hostgroupService.hostInGroup(groupId, "hostRelation", currpage, pagesize, type));
            }
            if (pagesize == 4) {
                return "devicelist";
            } else {
                return "devicelist9";
            }
        } else {
            return "authorityDeny";
        }
    }

    /**
     *app显示屏页面
     */
    public void appDevicelist() {
        if (app_source()) {
            int pagesize = Integer.parseInt(this.pagesize);
            Integer groupId = Integer.parseInt(this.groupid);
            Map<String, Object> hashmap = new HashMap<String, Object>();
            if (groupId == -1) {
                hashmap.put("hostingroup", hostgroupService.hostInGroup(groupId, "hostNoRelation", Integer.parseInt(this.currentPage), pagesize, "hhtc"));
            } else {
                hashmap.put("hostingroup", hostgroupService.hostInGroup(groupId, "hostRelation", Integer.parseInt(this.currentPage), pagesize, "hhtc"));
            }
            writeJSON(JSONSerializer.toJSON(hashmap).toString());
        }
    }

    /**
     * 开机
     */
    public void start() {
        Map<String, String> hashmap = new HashMap<String, String>();
        try {
            hashmap.put("success", "true");
            hashmap.put("msg", "开机成功");
            String hostIps = this.hostIpStr;
            if(hostIps.endsWith(","))
            {
                hostIps =  hostIps.substring(0,hostIps.length()-1);
            }
            String[] hostIpArr = hostIps.split(",");
            for(int i=0;i<hostIpArr.length;i++) {
                String ip = hostIpArr[i];
                boolean flag = computerCommand.start(ip);
                if (flag) {
    //                hashmap.put("success", "true");
    //                hashmap.put("msg", "开机成功");
                    syslogService.addDeviceLog(hostIp, "开机成功", "DCONTROL");
                } else {
                    hashmap.put("success", "false");
                    hashmap.put("msg", "开机失败");
                    syslogService.addDeviceLog(hostIp, "开机失败", "DCONTROL");
                }
            }
            Thread.sleep(3000);
            for(int i=0;i<hostIpArr.length;i++) {
                String ip = hostIpArr[i];
                boolean flag = computerCommand.start(ip);
                if (flag) {
    //                hashmap.put("success", "true");
    //                hashmap.put("msg", "开机成功");
                    syslogService.addDeviceLog(hostIp, "开机成功", "DCONTROL");
                } else {
                    hashmap.put("success", "false");
                    hashmap.put("msg", "开机失败");
                    syslogService.addDeviceLog(hostIp, "开机失败", "DCONTROL");
                }
            }
        }
        catch (Exception e)
        {
            hashmap.put("success", "false");
            hashmap.put("msg", "开机失败");
        }
        writeJSON(JSONSerializer.toJSON(hashmap).toString());
    }

    /**
     *关机
     */
    public void shutdown() {
        Map<String, String> hashmap = new HashMap<String, String>();
        try {
            hashmap.put("success", "true");
            hashmap.put("msg", "关机成功");
            String hostIps = this.hostIpStr;
            if(hostIps.endsWith(","))
            {
                hostIps =  hostIps.substring(0,hostIps.length()-1);
            }
            String[] hostIpArr = hostIps.split(",");
            for(int i=0;i<hostIpArr.length;i++) {
                String ip = hostIpArr[i];
                boolean flag = computerCommand.shutdown(ip, cmdCode);
                if (flag) {
//                hashmap.put("success", "true");
//                hashmap.put("msg", "关机成功");
                    syslogService.addDeviceLog(hostIp, "关机成功", "DCONTROL");
                } else {
                    hashmap.put("success", "false");
                    hashmap.put("msg", "关机失败");
                    syslogService.addDeviceLog(hostIp, "关机失败", "DCONTROL");
                }
            }
            Thread.sleep(3000);
            for(int i=0;i<hostIpArr.length;i++) {
                String ip = hostIpArr[i];
                boolean flag = computerCommand.shutdown(ip, cmdCode);
                if (flag) {
//                hashmap.put("success", "true");
//                hashmap.put("msg", "关机成功");
                    syslogService.addDeviceLog(hostIp, "关机成功", "DCONTROL");
                } else {
                    hashmap.put("success", "false");
                    hashmap.put("msg", "关机失败");
                    syslogService.addDeviceLog(hostIp, "关机失败", "DCONTROL");
                }
            }
        }
        catch (Exception e)
        {
            hashmap.put("success", "false");
            hashmap.put("msg", "关机失败");
        }
        writeJSON(JSONSerializer.toJSON(hashmap).toString());
    }

    /**
     * 切换触屏模式
     */
    public void changeTouchScreen() {
        Map<String, String> hashmap = new HashMap<String, String>();
        String touch_mode = computerCommand.changeTouchScreen(hostIp, cmdCode);
        if (!"".equals(touch_mode)) {
            hashmap.put("success", "true");
            syslogService.addDeviceLog(hostIp, "切换触屏模式到" + cmdCode + "成功", "DCONTROL");
        } else {
            hashmap.put("success", "false");
            syslogService.addDeviceLog(hostIp, "切换触屏模式到" + cmdCode + "失败", "DCONTROL");
        }
        writeJSON(JSONSerializer.toJSON(hashmap).toString());
    }

    /**
     * 切换节能模式
     */
    public void changeRemoteEnergy() {
        Map<String, String> hashmap = new HashMap<String, String>();
        boolean flag = computerCommand.changeRemoteEnergy(hostIp, cmdCode);
        if (flag) {
            hashmap.put("success", "true");
            syslogService.addDeviceLog(hostIp, "切换节能模式到" + cmdCode + "成功", "DCONTROL");
        } else {
            hashmap.put("success", "false");
            syslogService.addDeviceLog(hostIp, "切换节能模式到" + cmdCode + "失败", "DCONTROL");
        }
        writeJSON(JSONSerializer.toJSON(hashmap).toString());
    }

    /**
     * 切换音响模式
     */
    public void changeAudioMode() {
        Map<String, String> hashmap = new HashMap<String, String>();
        boolean flag = computerCommand.changeAudioMode(hostIp, cmdCode);
        if (flag) {
            hashmap.put("success", "true");
            syslogService.addDeviceLog(hostIp, "切换音响模式到" + cmdCode + "成功", "DCONTROL");
        } else {
            hashmap.put("success", "false");
            syslogService.addDeviceLog(hostIp, "切换音响模式到" + cmdCode + "失败", "DCONTROL");
        }
        writeJSON(JSONSerializer.toJSON(hashmap).toString());
    }

    /**
     * 切换信号源
     */
    public void changeSignal() {
        Map<String, String> hashmap = new HashMap<String, String>();
        boolean flag = computerCommand.changSignal(hostIp, cmdCode);
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
     * 音量调节
     */
    public void audioControl() {
        Map<String, String> hashmap = new HashMap<String, String>();
        boolean flag = computerCommand.changeAudioControl(hostIp, cmdCode);
        if (flag) {
            hashmap.put("success", "true");
            syslogService.addDeviceLog(hostIp, "音量调节到" + cmdCode + "成功", "DCONTROL");
        } else {
            hashmap.put("success", "false");
            syslogService.addDeviceLog(hostIp, "音量调节到" + cmdCode + "失败", "DCONTROL");
        }
        writeJSON(JSONSerializer.toJSON(hashmap).toString());
    }

    /**
     * 设置ATV||DTV频道号
     * @return boolean 是否切换成功
     */
    public boolean changeAtvOrDtv() {
        Map<String, String> hashmap = new HashMap<String, String>();
        boolean flag = computerCommand.atvOrDtv(hostIp, cmdCode, TVName);
        if (flag) {
            hashmap.put("success", "true");
            syslogService.addDeviceLog(hostIp, "切换频道" + cmdCode + "成功", "DCONTROL");
        } else {
            hashmap.put("success", "false");
            syslogService.addDeviceLog(hostIp, "切换频道" + cmdCode + "失败", "DCONTROL");
        }
        writeJSON(JSONSerializer.toJSON(hashmap).toString());
        return flag;
    }

    /**
     * app切换频道号
     */
    public void appChangeAtvOrDtv() {
        Map<String, String> hashmap = new HashMap<String, String>();
        boolean flag = computerCommand.atvOrDtv(hostIp, cmdCode, TVName);
        if (flag) {
            hashmap.put("success", "true");
            syslogService.addDeviceLog(hostIp, "切换频道" + cmdCode + "成功", "DCONTROL");
        } else {
            hashmap.put("success", "false");
            syslogService.addDeviceLog(hostIp, "切换频道" + cmdCode + "失败", "DCONTROL");
        }
        writeJSON(JSONSerializer.toJSON(hashmap).toString());
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
            Map<String, String> values = computerCommand.getInfo(_hostIp);
            int j=0;
            while((values == null || values.isEmpty())&&j<3){
                values = computerCommand.getInfo(_hostIp);
                j++;
            }
            if (values!=null&&!values.isEmpty()){
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
            }else {
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
     * 获取设备截屏
     */
    public void getImage() {
        String[] hostIdArray = this.hostid.split(",");
        String[] hostIpArray = this.hostIp.split(",");
        List datas = new ArrayList<HashMap<String, Object>>();

        for (int i = 0; i < hostIdArray.length; i++) {
            String _hostId = hostIdArray[i];
            String _hostIp = hostIpArray[i];
            Map<String, String> images = (Map<String, String>) computerCommand.getPictrue(_hostIp);
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
     * app获取设备截屏
     */
    public void appgetImage() {
        String[] hostIdArray = this.hostid.split(",");
        String[] hostIpArray = this.hostIp.split(",");
        String[] hostNameArray = this.hostName.split(",");
        List datas = new ArrayList<HashMap<String, Object>>();

        for (int i = 0; i < hostIdArray.length; i++) {
            String _hostId = hostIdArray[i];
            String _hostIp = hostIpArray[i];
            String _hostName = hostNameArray[i];
            Map<String, String> images = (Map<String, String>) computerCommand.getPictrue(_hostIp);
            if (images != null && !images.isEmpty()) {
                images.put("id", _hostId);
                images.put("name", _hostName);
                datas.add(images);
            } else {
                Map<String, String> map = new HashMap<>();
                map.put("id", _hostId);
                map.put("name", _hostName);
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
    public void getOneInfo() {
        List datas = new ArrayList<HashMap<String, Object>>();
        Map<String, String> values = computerCommand.getInfo(hostIp);
        if (values != null && !values.isEmpty()) {
            values.put("id", hostid);
            datas.add(values);
        } else {
            Map<String, String> map = new HashMap<>();
            map.put("id", hostid);
            map.put("ChannelName", "");
            map.put("EnergyMode", "");
            map.put("Volume", "");
            map.put("AudioMode", "");
            map.put("TouchMode", "");
            map.put("CpuUsage", "");
            map.put("Disk_C", "");
            map.put("Memmary", "");
            datas.add(map);
        }
        this.writeJSON(JSONSerializer.toJSON(datas).toString());
    }

    /**
     * 获取设备截屏
     */
    public void getOneImage() {
        List datas = new ArrayList<HashMap<String, Object>>();
        Map<String, String> images = (Map<String, String>) computerCommand.getPictrue(hostIp);
        if (images != null && !images.isEmpty()) {
            images.put("id", hostid);
            datas.add(images);
        } else {
            Map<String, String> map = new HashMap<>();
            map.put("id", hostIp);
            map.put("Value", "");
            datas.add(map);
        }
        this.writeJSON(JSONSerializer.toJSON(datas).toString());
    }

    /**
     * 清理垃圾
     */
    public void cleanRubbish() {
        Map<String, String> hashmap = new HashMap<String, String>();
        boolean flag = computerCommand.cleanRubbish(hostIp);
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
    public void ring() {
        Map<String, String> hashmap = new HashMap<String, String>();
        boolean flag = computerCommand.ring(hostIp);
        if (flag) {
            hashmap.put("success", "true");
            syslogService.addDeviceLog(hostIp, "打铃成功", "DCONTROL");
        } else {
            hashmap.put("success", "false");
            syslogService.addDeviceLog(hostIp, "打铃失败", "DCONTROL");
        }
        writeJSON(JSONSerializer.toJSON(hashmap).toString());
    }

    /**
     * 系统还原
     */
    public void recovery() {
        Map<String, String> hashmap = new HashMap<String, String>();
        boolean flag = computerCommand.recovery(hostIp);
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
        boolean flag = computerCommand.backup(hostIp);
        if (flag) {
            hashmap.put("success", "true");
            syslogService.addDeviceLog(hostIp, "系统备份成功", "DCONTROL");
        } else {
            hashmap.put("success", "false");
            syslogService.addDeviceLog(hostIp, "系统备份失败", "DCONTROL");
        }
        writeJSON(JSONSerializer.toJSON(hashmap).toString());
    }

    /**
     * 大屏垃圾清理事件成功后由事件传到提示界面
     * @return String 返回清理成功提示界面
     */
    public String turnInfo(){
        String para=parames;
        String ip=dapingip;
        Map map = new HashMap();
        map.put("parames",para);
        map.put("dapingip",ip);
        ActionContext.getContext().put("cleaninfo",map);
        return "cleansuccess";
    }
}
