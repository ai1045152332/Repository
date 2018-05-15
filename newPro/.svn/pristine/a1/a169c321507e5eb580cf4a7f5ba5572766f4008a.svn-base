package com.honghe.recordweb.action.frontend.htprojector;

import com.honghe.recordhibernate.entity.User;
import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.action.SessionManager;
import com.honghe.recordweb.service.frontend.devicemanager.HTProjectorCommand;
import com.honghe.recordweb.service.frontend.hostgroup.HostgroupService;
import com.honghe.recordweb.service.frontend.syslog.SyslogService;
import com.honghe.recordweb.service.frontend.user.UserService;
import com.honghe.recordweb.util.CommonName;
import com.opensymphony.xwork2.ActionContext;
import net.sf.json.JSONSerializer;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by lxy on 2015/12/22.
 */
@Controller
@Scope(value = "prototype")
public class HTProjectorAction extends BaseAction {
    private final static Logger logger = Logger.getLogger(HTProjectorAction.class);
    @Resource
    private UserService userService;
    @Resource
    private HostgroupService hostgroupService;
    @Resource
    private SyslogService syslogService;

    @Resource
    private HTProjectorCommand htProjectorCommand;

    private String hostIp;  //主机设备IP
    private String cmdCode; //开关机命令
    private String hostid; //主机设备Id

    private String pageSize = "4";
    private String currentPage = "1";

    private String groupid;//组id；

    private Map<String, List<Map<String, String>>> cmdMap;

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

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getHostid() {
        return hostid;
    }

    public void setHostid(String hostid) {
        this.hostid = hostid;
    }

    public String getCmdCode() {
        return cmdCode;
    }

    public void setCmdCode(String cmdCode) {
        this.cmdCode = cmdCode;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    /**
     * 获取设备分组列表
     *
     * @return
     */
    public String getHostGroup() {
        SessionManager.add(ServletActionContext.getRequest().getSession(), SessionManager.Key.DeviceType, CommonName.DEVICE_TYPE_PROJECTOR);
        cmdMap = htProjectorCommand.getDspecCommand(CommonName.DEVICE_TYPE_PROJECTOR);
        int uid = 0;
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        //用户如果是admin,project或者校园管理员，uid传0值。
        Integer userId = user.getUserId();
        uid = userService.getAuthorityValueByUserid(userId);
        if (0 == uid) {
            Map<String, Object> result = super.getTreeMap(0, CommonName.DEVICE_TYPE_PROJECTOR, hostgroupService);
            if (result.isEmpty()) {
                ActionContext.getContext().put("", Collections.EMPTY_LIST);
            } else {
                List<Map> groupTree = (List<Map>) result.get("groupTree");
                Map unknowGroup = (Map) result.get("unknowGroup");
                if (unknowGroup != null) {
                    List<Map> hostList = (List<Map>) unknowGroup.get("host_list");
                    if (!hostList.isEmpty()) {
                        groupTree.add(0, unknowGroup);
                    }
                }
                ActionContext.getContext().put("groupTree", groupTree);
            }
        } else {
            //  User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
            ActionContext.getContext().put("groupTree", hostgroupService.getGroupService(user.getUserId(), CommonName.DEVICE_TYPE_PROJECTOR));
        }
        return "dmanager";
    }

    /**
     * 获取设备分组(4屏或是9屏)
     *
     * @return
     */
    public String tdeviceList() {
        if (!app_source()) {
            Integer pagesize = Integer.valueOf(pageSize.trim());
            String a = this.groupid.trim();
            Integer groupId = Integer.valueOf(a);
            if (groupId == -1) {//获取未分组设备
                ActionContext.getContext().put("hostingroup", hostgroupService.hostInGroup(groupId, "hostNoRelation", Integer.parseInt(this.currentPage), pagesize, CommonName.DEVICE_TYPE_PROJECTOR));
            } else {//获取该组下的设备
                ActionContext.getContext().put("hostingroup", hostgroupService.hostInGroup(groupId, "hostRelation", Integer.parseInt(this.currentPage), pagesize, CommonName.DEVICE_TYPE_PROJECTOR));
            }
            if (pagesize == 4) {
                return "htplist";
            } else {
                return "htplist9";
            }
        } else {
            return "authorityDeny";
        }
    }

    /**
     *
     */

    public void getInfo() {
        String[] hostIdArray = this.hostid.split(",");
        String[] hostIpArray = this.hostIp.split(",");
        List datas = new ArrayList<HashMap<String, Object>>();

        for (int i = 0; i < hostIdArray.length; i++) {
            String _hostId = hostIdArray[i];
            String _hostIp = hostIpArray[i];
            Map<String, String> values = htProjectorCommand.getInfo(_hostIp);
            if (values != null && !values.isEmpty()) {
                String topware = "";
                try {
                    topware = java.net.URLDecoder.decode(values.get("TopSoftWare"), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                values.put("id", _hostId);
                values.put("TopSoftWare", topware);
                datas.add(values);
            } else {
                Map<String, String> map = new HashMap<>();
                map.put("id", _hostId);
                map.put("TurnState", "");
                map.put("ChannelState", "");
                map.put("CpuUsage", "");
                map.put("Memmary", "");
                map.put("Disk_C", "");
                map.put("TopSoftWare", "");
                datas.add(map);
            }
        }
        this.writeJSON(JSONSerializer.toJSON(datas).toString());
    }

    public boolean isRightTurnState(String ip) {
        boolean flag = true;

        Map<String, String> values = htProjectorCommand.getInfo(ip);
        if (values != null && !values.isEmpty()) {
            if ("error".equals(values.get("TurnState")) || "".equals(values.get("TurnState"))) {
                flag = false;
            }

        }

        return flag;
    }

    /**
     * 主机设备开机
     */

    public void start() {
        boolean flag = htProjectorCommand.start(this.hostIp);
        Map<String, String> hashmap = new HashMap<String, String>();
        if (flag) {
            hashmap.put("success", "true");
            hashmap.put("msg", "开机成功");//成功
            syslogService.addDeviceLog(this.hostIp, "开机成功", "DCONTROL");
        } else {
            hashmap.put("success", "false");
            hashmap.put("msg", "开机失败");//成功
            syslogService.addDeviceLog(this.hostIp, "开机失败", "DCONTROL");
        }
        writeJSON(JSONSerializer.toJSON(hashmap).toString());
    }

    /**
     * 主机设备关机
     */
    public void shutdown() {
        boolean flag = htProjectorCommand.shutdown(this.hostIp, cmdCode);
        Map<String, String> hashmap = new HashMap<String, String>();
        if (flag) {
            hashmap.put("success", "true");
            hashmap.put("msg", "关机成功");//成功
            syslogService.addDeviceLog(this.hostIp, "关机成功", "DCONTROL");
        } else {
            hashmap.put("success", "false");
            hashmap.put("msg", "关机失败");//成功
            syslogService.addDeviceLog(this.hostIp, "开机失败", "DCONTROL");
        }
        writeJSON(JSONSerializer.toJSON(hashmap).toString());
    }


    /**
     * 获取设备截屏
     */
    public void getOneImage() {
        List datas = new ArrayList<HashMap<String, Object>>();
        Map<String, String> images = (Map<String, String>) htProjectorCommand.getPictrue(hostIp);
        if (images != null && !images.isEmpty()) {
            images.put("id", this.hostid);
            datas.add(images);
        } else {
            Map<String, String> map = new HashMap<>();
            map.put("id", this.hostIp);
            map.put("Value", "");
            datas.add(map);
        }
        this.writeJSON(JSONSerializer.toJSON(datas).toString());
    }

    /**
     * 切换信号源
     */
    public void changeSignal() {
        Map<String, String> hashmap = new HashMap<String, String>();
        boolean flag = htProjectorCommand.changSignal(this.hostIp, this.cmdCode);
        if (flag) {
            hashmap.put("success", "true");
            syslogService.addDeviceLog(this.hostIp, "切换信号源到" + this.cmdCode + "成功", "DCONTROL");
        } else {
            hashmap.put("success", "false");
            syslogService.addDeviceLog(this.hostIp, "切换信号源到" + this.cmdCode + "失败", "DCONTROL");
        }
        writeJSON(JSONSerializer.toJSON(hashmap).toString());
    }


    /**
     * 投影机开关机
     */
    public void setProjectorTurnState() {
        // 添加投影机设备开关机的操作
        try {
            boolean flag = htProjectorCommand.setProjectorTurnState(this.hostIp, cmdCode);
            Map<String, String> hashmap = new HashMap<String, String>();
            if (cmdCode.equals("TurnOn")) {
                if (flag) {
                    hashmap.put("success", "true");
                    hashmap.put("msg", "开机成功");//成功
                    syslogService.addDeviceLog(this.hostIp, "开机成功", "DCONTROL");
                } else {
                    hashmap.put("success", "false");
                    hashmap.put("msg", "开机失败");//失败
                    syslogService.addDeviceLog(this.hostIp, "开机失败", "DCONTROL");
                }
            } else {
                if (flag) {
                    hashmap.put("success", "true");
                    hashmap.put("msg", "关机成功");//成功
                    syslogService.addDeviceLog(this.hostIp, "关机成功", "DCONTROL");
                } else {
                    hashmap.put("success", "false");
                    hashmap.put("msg", "关机失败");//失败
                    syslogService.addDeviceLog(this.hostIp, "关机失败", "DCONTROL");
                }
            }
            writeJSON(JSONSerializer.toJSON(hashmap).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


