package com.honghe.recordweb.action.frontend.schedule;

import com.honghe.recordhibernate.entity.*;
import com.honghe.recordweb.action.SessionManager;
import com.honghe.recordweb.service.frontend.hostgroup.HostgroupService;
import com.honghe.recordweb.service.frontend.ringbell.RingbellService;
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
 * Created by yanxue on 2015-06-23.
 */
@Controller
@Scope(value = "prototype")
public class RingbellAction extends ScheduleAction<Ringbell> {
    private final static Logger logger = Logger.getLogger(RingbellAction.class);
    private final String RINGBELL_LIST = "ringbellList";
    private final String GROUPTREE = "groupTree";
    private final String UNKNOWGROUP = "unknowGroup";
    private final String HOST_LIST = "host_list";
    private final String SUCCESS = "success";
    private final String MSG = "msg";

    @Resource
    private RingbellService ringbellService;
    @Resource
    private HostgroupService hostgroupService;
    @Resource
    private UserService userService;
    private int userId;
    private int isAdmin;
    private String hostStr;
    private String hostIpStr;
    private String hostNameStr;
    private int pageCount;
    private int currentPage;
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    private String userName; //用户名
    private String pagesize; //每页条目

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

    public String getHostIpStr() {
        return hostIpStr;
    }

    public void setHostIpStr(String hostIpStr) {
        this.hostIpStr = hostIpStr;
    }

    public String getPagesize() {
        return pagesize;
    }

    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHostStr() { return hostStr; }

    public void setHostStr(String hostStr) { this.hostStr = hostStr; }

    public String getHostNameStr() { return hostNameStr; }

    public void setHostNameStr(String hostNameStr) { this.hostNameStr = hostNameStr; }

    public int getPageCount() { return pageCount; }

    public void setPageCount(int pageCount) { this.pageCount = pageCount; }

    public int getCurrentPage() { return currentPage; }

    public void setCurrentPage(int currentPage) { this.currentPage = currentPage; }

    /**
     * app返回定时信息列表
     */
    public void appRingbellList() {
        int uid = 0;
        int pagesize = 5;
        if (app_source()) {
            Map<String, Object> hashmap = new HashMap<String, Object>();
            try {
                int userId = this.userId;
                uid = userService.getAuthorityValueByUserid(userId);
                pagesize = Integer.parseInt(this.pagesize);

                this.currentPage = this.currentPage == 0 ? 1 : this.currentPage;

                PageBean pageBean = ringbellService.ringbellListService(currentPage, pagesize, uid,CommonName.DEVICE_TYPE_SCREEN);

                Map<String, Object> map = new LinkedHashMap<String, Object>();

                map.put(RINGBELL_LIST, pageBean.getRecordList());

                map.put("pageCount", pageBean.getPageCount());

                hashmap.put(RINGBELL_LIST, map);

                hashmap.put(GROUPTREE, hostgroupService.getGroupService(uid, CommonName.DEVICE_TYPE_SCREEN));

            } catch (Exception e) {
                logger.error("", e);
                hashmap.put(RINGBELL_LIST, null);
                hashmap.put(GROUPTREE, null);
            }

            writeJSON(JSONSerializer.toJSON(hashmap).toString());
        }
    }


    /**
     * 返回定时信息列表 *
     */
    public String ringbellList() {
        int uid = 0;
        int pagesize = 5;
        String deviceType = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.DeviceType);
        //用户如果是admin,project或者校园管理员，uid传0值。
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        userId = user.getUserId();
        UserService userService = new UserService();
        isAdmin = userService.getAuthorityValueByUserid(userId);//返回是否是管理员，0为是，1为否
        uid = userService.getAuthorityValueByUserid(userId);
        this.currentPage = this.currentPage == 0 ? 1 : this.currentPage;
        PageBean<Object[]> pageBean = ringbellService.ringbellListService(currentPage, pagesize, uid,deviceType);
        List<RingbellToHost> ringbellToHostList = new ArrayList<RingbellToHost>();
        for (Object[] obj : pageBean.getRecordList()) {
            RingbellToHost ringbellToHost = new RingbellToHost();
            int i = 0;
            ringbellToHost.setRingId(String.valueOf(obj[i++]));
            ringbellToHost.setRingLoop(String.valueOf(obj[i++]));
            ringbellToHost.setRingLooptype(String.valueOf(obj[i++]));
            ringbellToHost.setRingDate(String.valueOf(obj[i++]));
            ringbellToHost.setRingWeek(String.valueOf(obj[i++]));
            ringbellToHost.setRingTime(String.valueOf(obj[i++]));
            ringbellToHost.setRingSingletime(String.valueOf(obj[i++]));
            ringbellToHost.setRingUid(String.valueOf(obj[i++]));
            ringbellToHost.setRingUname(String.valueOf(obj[i++]));
            ringbellToHost.setRingCreatetime(String.valueOf(obj[i++]));
            ringbellToHost.setHostids(String.valueOf(obj[i++]));
            ringbellToHost.setHosts(String.valueOf(obj[i++]));
            ringbellToHostList.add(ringbellToHost);
        }
        pageCount = pageBean.getPageCount();
        ActionContext.getContext().put(RINGBELL_LIST, ringbellToHostList);

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
        return RINGBELL_LIST;
    }


    /**
     * 删除定时信息及班级关系 *
     */
    public void delRingbell() {
        JSONObject json = new JSONObject();
        try {

            if (model == null) {
                json.put(SUCCESS, true);
                json.put(MSG, "删除成功");
            } else {

                hostStr = hostStr == null ? "" : hostStr;

                boolean result = ringbellService.delRingbellService(model, hostStr);

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
    public void addRingbell() {
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

            model.setRingbellUid(uid);
            model.setRingbellUname(uname);
            model.setRingbellCreatetime(new Timestamp(df.parse(df.format(new Date())).getTime()));

            boolean result = ringbellService.addRingbellService(model, hostStr, hostNameStr,hostIpStr);

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
     * 修改打铃定是计划
     */
    public void alterRingbell() {
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

            model.setRingbellUid(uid);
            model.setRingbellUname(uname);
            model.setRingbellCreatetime(new Timestamp(df.parse(df.format(new Date())).getTime()));

            boolean result = ringbellService.updateRingbellService(model, hostStr, hostNameStr,hostIpStr);

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
