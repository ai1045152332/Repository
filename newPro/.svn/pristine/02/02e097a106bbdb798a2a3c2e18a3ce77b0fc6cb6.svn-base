package com.honghe.recordweb.action.frontend.policy;

import com.honghe.recordhibernate.entity.Policy;
import com.honghe.recordhibernate.entity.User;
import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.action.SessionManager;
import com.honghe.recordweb.service.frontend.hostgroup.HostgroupService;
import com.honghe.recordweb.service.frontend.policy.PolicyService;
import com.honghe.recordweb.service.frontend.user.UserService;
import com.honghe.recordweb.util.CommonName;
import com.honghe.recordweb.util.RoleUtil;
import com.opensymphony.xwork2.ActionContext;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wzz on 2015/3/1.
 */
@Controller
@Scope(value = "prototype")
public class PolicyAction extends BaseAction {
    private final static Logger logger = Logger.getLogger(PolicyAction.class);
    @Resource
    private PolicyService policyService;
    @Resource
    private HostgroupService hostgroupService;
    @Resource
    private UserService userService;

    private int userid;
    private String userName;//用户名
    private String userId;//用户id
    private String pagesize;//每页的条目
    private int policyId;
    private int policyType;
    private int policyLoop;
    private String policyLooptype;
    private int policyDate;
    private int policyWeek;
    private String policyTime;
    private String policySingletime;
    private String policyDevicetype;
    private String hostStr;
    private String hostIpStr;
    private String hostNameStr;
    private int pageCount;
    private int currentPage;
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public int getPolicyType() {
        return policyType;
    }

    public void setPolicyType(int policyType) {
        this.policyType = policyType;
    }

    public int getPolicyLoop() {
        return policyLoop;
    }

    public void setPolicyLoop(int policyLoop) {
        this.policyLoop = policyLoop;
    }

    public String getPolicyLooptype() {
        return policyLooptype;
    }

    public void setPolicyLooptype(String policyLooptype) {
        this.policyLooptype = policyLooptype;
    }

    public int getPolicyDate() {
        return policyDate;
    }

    public void setPolicyDate(int policyDate) {
        this.policyDate = policyDate;
    }

    public int getPolicyWeek() {
        return policyWeek;
    }

    public void setPolicyWeek(int policyWeek) {
        this.policyWeek = policyWeek;
    }

    public String getPolicyTime() {
        return policyTime;
    }

    public void setPolicyTime(String policyTime) {
        this.policyTime = policyTime;
    }

    public String getPolicySingletime() {
        return policySingletime;
    }

    public void setPolicySingletime(String policySingletime) {
        this.policySingletime = policySingletime;
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

    public String getPolicyDevicetype() {
        return policyDevicetype;
    }

    public void setPolicyDevicetype(String policyDevicetype) {
        this.policyDevicetype = policyDevicetype;
    }

    public String getHostIpStr() {
        return hostIpStr;
    }

    public void setHostIpStr(String hostIpStr) {
        this.hostIpStr = hostIpStr;
    }

    /**
     * app返回定是计划
     */
    public void appPolicyList() {
        //  int uid = 0;
        int pagesize = 5;
        if (app_source()) {
            Map<String, Object> hashmap = new HashMap<String, Object>();
            try {
                Integer userId = Integer.parseInt(this.userId);
                Map<String, String> roleName = userService.getRoleByUid(userId.toString());
                String type = CommonName.DEVICE_TYPE_SCREEN;
                userid = RoleUtil.RoleToPermission(userId, roleName);

                pagesize = Integer.parseInt(this.pagesize);
                hashmap.put("policyList", policyService.policyListService(currentPage, pagesize, userid ,type));
                hashmap.put("groupTree", hostgroupService.getGroupService(userid, CommonName.DEVICE_TYPE_SCREEN));
            } catch (Exception e) {
                logger.error("", e);
                hashmap.put("policyList", new LinkedHashMap<String, Object>());
                hashmap.put("groupTree", new ArrayList<Map>());
            }

            writeJSON(JSONSerializer.toJSON(hashmap).toString());
        }
    }

    /**
     * 返回定时信息列表
     */
    public String policyList() {
        int pagesize = 5;
        HashMap<String, Integer> roleMap = new HashMap<String, Integer>(0);
        String deviceType = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.DeviceType);
        //用户如果是admin,project或者校园管理员，uid传0值。
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        Integer userId = user.getUserId();
        Map<String, String> roleName = userService.getRoleByUid(userId.toString());
          ActionContext.getContext().put("userId", userId);
        String type = RoleUtil.RoleToDevice(userId.toString(), roleName);
        userId = RoleUtil.RoleToPermission(userId, roleName);

        if (this.currentPage == 0) {
            this.currentPage = 1;
        }
        ActionContext.getContext().put("policyList", policyService.policyListService(currentPage, pagesize, userId,deviceType));
        //获取分组信息
        if (userId == 0) {
            final int uid = userId;
            Map<String, Object> result = super.getTreeMap(uid, deviceType, hostgroupService);
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
            ActionContext.getContext().put("groupTree", hostgroupService.getGroupService(userId, deviceType));
        }
        return "policyList";
    }

    /**
     * 删除定时信息及班级关系 *
     */
    public void delPolicy() {
        JSONObject json = new JSONObject();
        try {
            Policy policy = policyService.getPolicyService(policyId);
            if (policy == null) {
                json.put("success", true);
                json.put("msg", "删除成功");
            } else {
                if (hostStr == null) {
                    hostStr = "";
                }
                boolean result = policyService.delPolicyService(policy, hostStr);
                if (result) {
                    json.put("success", true);
                    json.put("msg", "删除成功");
                } else {
                    json.put("success", false);
                    json.put("msg", result);
                }
            }
        } catch (Exception e) {
            logger.error("定时计划删除失败", e);
            json.put("success", false);
            json.put("msg", "删除失败");
        }
        writeJSON(json.toString());
    }

    /**
     * 添加定时信息及班级关系 *
     */
    public void addPolicy() {
        JSONObject json = new JSONObject();
        try {
            Policy policy = new Policy();
            policy.setpType(policyType);
            policy.setpLoop(policyLoop);
            policy.setpLooptype(policyLooptype);
            policy.setpDate(policyDate);
            policy.setpWeek(policyWeek);
            policy.setpTime(policyTime);
            policy.setpDevicetype(policyDevicetype);
            if (!policySingletime.equals("")) {
                policy.setpSingletime(df.parse(policySingletime));
            }
            if (app_source()) {
                userid = Integer.parseInt(this.userId);
                userName = this.userName;
            } else {
                User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
                userid = user.getUserId();
                userName = user.getUserName();
            }
            policy.setpUid(userid);
            Date now = new Date();
            policy.setpCreateTime(df.parse(df.format(now)));
            policy.setpUsername(userName);
            boolean result = policyService.addPolicyService(policy, hostStr, hostNameStr,hostIpStr);
            if (result) {
                json.put("success", true);
                json.put("msg", "添加成功");
            } else {
                json.put("success", false);
                json.put("msg", result);
            }
        } catch (Exception e) {
            logger.error("app定时计划添加失败", e);
            // e.printStackTrace();
            json.put("success", false);
            json.put("msg", "添加失败");
        }
        writeJSON(json.toString());
    }

    /**
     * 修改定时信息及班级关系 *
     */
    public void alterPolicy() {
        JSONObject json = new JSONObject();
        try {
            Policy policy = policyService.getPolicyService(policyId);
            policy.setpType(policyType);
            policy.setpLoop(policyLoop);
            policy.setpLooptype(policyLooptype);
            policy.setpDate(policyDate);
            policy.setpWeek(policyWeek);
            policy.setpTime(policyTime);
            policy.setpDevicetype(policyDevicetype);
            if (!policySingletime.equals("")) {
                policy.setpSingletime(df.parse(policySingletime));
            }
            int uid = 0;
            String userName = "";
            if (app_source()) {
                uid = Integer.parseInt(this.userId);
                userName = this.userName;
            } else {
                User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
                uid = user.getUserId();
                userName = user.getUserName();
            }
            policy.setpUid(uid);
            policy.setpUsername(userName);
            Date now = new Date();
            policy.setpCreateTime(df.parse(df.format(now)));
            boolean result = policyService.updatePolicyService(policy, hostStr, hostNameStr,hostIpStr);
            if (result) {
                json.put("success", true);
                json.put("msg", "修改成功");
            } else {
                json.put("success", false);
                json.put("msg", "修改失败");
            }
        } catch (Exception e) {
            logger.error("定时计划修改失败", e);
            //   e.printStackTrace();
            json.put("success", false);
            json.put("msg", "修改失败");
        }
        writeJSON(json.toString());
    }
}
