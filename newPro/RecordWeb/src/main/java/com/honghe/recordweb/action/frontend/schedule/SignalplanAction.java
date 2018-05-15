package com.honghe.recordweb.action.frontend.schedule;

import com.honghe.recordhibernate.entity.*;
import com.honghe.recordweb.action.SessionManager;
import com.honghe.recordweb.service.frontend.devicemanager.ComputerCommand;
import com.honghe.recordweb.service.frontend.hostgroup.HostgroupService;
import com.honghe.recordweb.service.frontend.signalplan.SignalplanService;
import com.honghe.recordweb.service.frontend.user.UserService;
import com.honghe.recordweb.util.CommonName;
import com.opensymphony.xwork2.ActionContext;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by yanxue on 2015-06-25.
 */
@Controller
@Scope(value = "prototype")
public class SignalplanAction extends ScheduleAction<Signalplan> {
    private final static Logger logger = Logger.getLogger(SignalplanAction.class);
    private final String SIGNSLPLAN_LIST = "signalplanList";
    private final String GROUPTREE = "groupTree";
    private final String UNKNOWGROUP = "unknowGroup";
    private final String HOST_LIST = "host_list";
    private final String SUCCESS = "success";
    private final String MSG = "msg";
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    private String hostStr;
    private String hostIpStr;
    private String hostNameStr;
    private int pageCount;
    private int currentPage;
    private Map<String, List<Map<String, String>>> cmdMap;
    private int userId;
    private int isAdmin;
    private String pagesize; //每页条目
    private String userName; //用户名

    public String getPagesize() {
        return pagesize;
    }

    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }

    public String getHostIpStr() {
        return hostIpStr;
    }

    public void setHostIpStr(String hostIpStr) {
        this.hostIpStr = hostIpStr;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getHostStr() {
        return hostStr;
    }

    public void setHostStr(String hostStr) {
        this.hostStr = hostStr;
    }

    public String getHostNameStr() {
        return hostNameStr;
    }

    public void setHostNameStr(String hostNameStr) {
        this.hostNameStr = hostNameStr;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public Map<String, List<Map<String, String>>> getCmdMap() {
        return cmdMap;
    }

    public void setCmdMap(Map<String, List<Map<String, String>>> cmdMap) {
        this.cmdMap = cmdMap;
    }

    @Resource
    private SignalplanService signalplanService;
    @Resource
    private HostgroupService hostgroupService;
    @Resource
    private ComputerCommand computerCommand;
    @Resource
    private UserService userService;

    /**
     * app返回定时信息列表 *
     */
    public void appSignalplanList() {
        int uid = 0;
        int pagesize = 5;
        if (app_source()) {
            Map<String, Object> hashmap = new HashMap<String, Object>();
            try {
                int userId = this.userId;

                uid = userService.getAuthorityValueByUserid(userId);
                pagesize = Integer.parseInt(this.pagesize);
                this.currentPage = this.currentPage == 0 ? 1 : this.currentPage;
                PageBean pageBean = signalplanService.signalplanListService(currentPage, pagesize, uid, CommonName.DEVICE_TYPE_SCREEN);
                Map<String, Object> map = new LinkedHashMap<String, Object>();
                map.put(SIGNSLPLAN_LIST, pageBean.getRecordList());
                map.put("pageCount", pageBean.getPageCount());

                hashmap.put(SIGNSLPLAN_LIST, map);
                hashmap.put(GROUPTREE, hostgroupService.getGroupService(uid, CommonName.DEVICE_TYPE_SCREEN));

            } catch (Exception e) {
                logger.error("", e);
                hashmap.put(SIGNSLPLAN_LIST, null);
                hashmap.put(GROUPTREE, null);
            }

            writeJSON(JSONSerializer.toJSON(hashmap).toString());
        }
    }


    /**
     * 返回定时信息列表 *
     */
    public String signalplanList() {
        int uid = 0;
        int pagesize = 5;
        //获取当前设备的类型
        String deviceType = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.DeviceType);

        cmdMap = computerCommand.getDspecCommand(deviceType);

        //用户如果是admin,project或者校园管理员，uid传0值。
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        userId = user.getUserId();
        UserService userService = new UserService();
        isAdmin = userService.getAuthorityValueByUserid(userId);//返回是否是管理员，0为是，1为否
        uid = userService.getAuthorityValueByUserid(userId);
        this.currentPage = this.currentPage == 0 ? 1 : this.currentPage;

        PageBean<Object[]> pageBean = signalplanService.signalplanListService(currentPage, pagesize, uid, deviceType);

        List<SignalplanToHost> signalplanToHostList = new ArrayList<SignalplanToHost>();
        for (Object[] obj : pageBean.getRecordList()) {
            SignalplanToHost signalplanToHost = new SignalplanToHost();
            int i = 0;
            signalplanToHost.setSignalId(String.valueOf(obj[i++]));
            signalplanToHost.setSignal(String.valueOf(obj[i++]));
            signalplanToHost.setSignalCode(String.valueOf(obj[i++]));
            signalplanToHost.setSignalLoop(String.valueOf(obj[i++]));
            signalplanToHost.setSignalLooptype(String.valueOf(obj[i++]));
            signalplanToHost.setSignalDate(String.valueOf(obj[i++]));
            signalplanToHost.setSignalWeek(String.valueOf(obj[i++]));
            signalplanToHost.setSignalTime(String.valueOf(obj[i++]));
            signalplanToHost.setSignalSingletime(String.valueOf(obj[i++]));
            signalplanToHost.setSignalUid(String.valueOf(obj[i++]));
            signalplanToHost.setSignalUname(String.valueOf(obj[i++]));
            signalplanToHost.setSignalCreatetime(String.valueOf(obj[i++]));
            signalplanToHost.setHostids(String.valueOf(obj[i++]));
            signalplanToHost.setHosts(String.valueOf(obj[i++]));
            signalplanToHostList.add(signalplanToHost);
        }

        pageCount = pageBean.getPageCount();
        ActionContext.getContext().put(SIGNSLPLAN_LIST, signalplanToHostList);

        //获取分组信息
        if (0 == uid) {
            Map<String, Object> result = super.getTreeMap(0, deviceType, hostgroupService);
            if (result.isEmpty()) {
                ActionContext.getContext().put("", Collections.EMPTY_LIST);
            } else {
                List<Map> groupTree = (List<Map>) result.get(GROUPTREE);
                Map unknowGroup = (Map) result.get(UNKNOWGROUP);
                List<Map> hostList = (List<Map>) unknowGroup.get(HOST_LIST);
                if (!hostList.isEmpty()) {
                    groupTree.add(0, unknowGroup);
                }

                List<GroupToHosts> groupToHostsList = new ArrayList<>();

                for (Map groupTreeMap : groupTree) {
                    GroupToHosts groupToHosts = new GroupToHosts();
                    String group_id = groupTreeMap.get("group_id").toString();
                    groupToHosts.setGroup_id(group_id);
                    String group_name = groupTreeMap.get("group_name").toString();
                    groupToHosts.setGroup_name(group_name);
                    List<Map> hostLists = (List<Map>) groupTreeMap.get("host_list");
                    List<HostInfo> hostInfoList = new ArrayList<>();
                    for (Map host : hostLists) {
                        HostInfo hostInfo = new HostInfo();

                        hostInfo.setId(host.get("id").toString());
                        hostInfo.setName(host.get("name").toString());
                        hostInfo.setToken(host.get("token").toString());
                        hostInfo.setDspec(host.get("dspec").toString());
                        hostInfo.setType(host.get("type").toString());

                        hostInfo.setStatus(host.get("status").toString());
                        hostInfo.setHost_desc(host.get("host_desc").toString());
                        hostInfo.setDspecid(host.get("dspecid").toString());
                        hostInfoList.add(hostInfo);
                    }
                    groupToHosts.setHostInfoList(hostInfoList);
                    groupToHostsList.add(groupToHosts);
                }

                ActionContext.getContext().put(GROUPTREE, groupToHostsList);
            }
        } else {

            List<GroupToHosts> groupToHostsList = new ArrayList<>();
            List<Map> groupTreeList = hostgroupService.getGroupService(uid, deviceType);
            for (Map groupTreeMap : groupTreeList) {
                GroupToHosts groupToHosts = new GroupToHosts();
                String group_id = groupTreeMap.get("group_id").toString();
                groupToHosts.setGroup_id(group_id);
                String group_name = groupTreeMap.get("group_name").toString();
                groupToHosts.setGroup_name(group_name);
                List<Map> hostLists = (List<Map>) groupTreeMap.get("host_list");
                List<HostInfo> hostInfoList = new ArrayList<>();
                for (Map host : hostLists) {
                    HostInfo hostInfo = new HostInfo();
                    hostInfo.setId(host.get("id").toString());
                    hostInfo.setName(host.get("name").toString());
                    hostInfo.setToken(host.get("token").toString());
                    hostInfo.setDspec(host.get("dspec").toString());
                    hostInfo.setType(host.get("type").toString());
                    hostInfo.setStatus(host.get("status").toString());
                    hostInfo.setHost_desc(host.get("host_desc").toString());
                    hostInfo.setDspecid(host.get("dspecid").toString());
                    hostInfoList.add(hostInfo);
                }
                groupToHosts.setHostInfoList(hostInfoList);
                groupToHostsList.add(groupToHosts);
            }

            ActionContext.getContext().put(GROUPTREE, groupToHostsList);

        }
        ActionContext.getContext().put("cmdMap", computerCommand.getDspecCommand(deviceType));
        return SIGNSLPLAN_LIST;
    }


    /**
     * 删除定时信号切换信息及班级关系 *
     */
    public void delSignalplan() {
        JSONObject json = new JSONObject();
        try {

            if (model == null) {
                json.put(SUCCESS, true);
                json.put(MSG, "删除成功");
            } else {

                this.hostStr = this.hostStr == null ? "" : this.hostNameStr;

                boolean result = signalplanService.delSignalplanService(model, hostStr);

                if (result) {
                    json.put(SUCCESS, true);
                    json.put(MSG, "删除成功");
                } else {
                    json.put(SUCCESS, false);
                    json.put(MSG, result);
                }
            }
        } catch (Exception e) {
            logger.error("", e);
            json.put(SUCCESS, false);
            json.put(MSG, "删除失败");
        }
        writeJSON(json.toString());
    }

    /**
     * 添加定时信息及班级关系 *
     */
    public void addSignalplan() {
        JSONObject json = new JSONObject();
        try {

            int uid = 0;
            String uname = "";

            if (app_source()) {
                uid = this.userId;
                uname = this.userName;
            } else {
                User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
                uid = user.getUserId();
                uname = user.getUserName();
            }
            model.setSignalUid(uid);
            model.setSignalUname(uname);
            model.setSignalCreatetime(new Timestamp(df.parse(df.format(new Date())).getTime()));

            boolean result = signalplanService.addSignalplanService(model, hostStr, hostNameStr,hostIpStr);

            if (result) {
                json.put(SUCCESS, true);
                json.put(MSG, "添加成功");
            } else {
                json.put(SUCCESS, false);
                json.put(MSG, result);
            }
        } catch (Exception e) {
            logger.error("", e);
            //   e.printStackTrace();
            json.put(SUCCESS, false);
            json.put(MSG, "添加失败");
        }
        writeJSON(json.toString());
    }

    /**
     * 修改信号源定时计划
     */
    public void alterSignalplan() {
        JSONObject json = new JSONObject();
        try {
            int uid = 0;
            String uname = "";

            if (app_source()) {
                uid = this.userId;
                uname = this.userName;
            } else {
                User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
                uid = user.getUserId();
                uname = user.getUserName();
            }
            model.setSignalUid(uid);
            model.setSignalUname(uname);
            model.setSignalCreatetime(new Timestamp(df.parse(df.format(new Date())).getTime()));

            boolean result = signalplanService.updateSignalplanService(model, hostStr, hostNameStr,hostIpStr);

            if (result) {
                json.put("success", true);
                json.put("msg", "修改成功");
            } else {
                json.put("success", false);
                json.put("msg", "修改失败");
            }
        } catch (Exception e) {
            logger.error("", e);
            //   e.printStackTrace();
            json.put("success", false);
            json.put("msg", "修改失败");
        }
        writeJSON(json.toString());
    }


}
