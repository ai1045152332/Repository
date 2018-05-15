package com.honghe.recordweb.action.frontend.hostgroup;

import com.honghe.recordhibernate.entity.Host;
import com.honghe.recordhibernate.entity.Hostgroup;
import com.honghe.recordhibernate.entity.User;
import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.action.SessionManager;
import com.honghe.recordweb.service.frontend.device.HongheDeviceService;
import com.honghe.recordweb.service.frontend.devicemanager.HostDevice;
import com.honghe.recordweb.service.frontend.hostgroup.HostgroupService;
import com.honghe.recordweb.service.frontend.user.UserService;
import com.honghe.recordweb.util.CommonName;
import com.honghe.recordweb.util.ConfigureUtil;
import com.honghe.recordweb.util.RoleUtil;
import com.opensymphony.xwork2.ActionContext;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chby on 2014/9/18.
 */
@Controller //action注解
@Scope(value = "prototype")
public class HostgroupAction extends BaseAction {
    private final static Logger logger = Logger.getLogger(HostgroupAction.class);
    private String groupId;
    private String groupName;
    private String hostNumber;
    private int sumNumberHost;//统计当前教室（主机）数量
    private int sumNumberDevice;//统计当前设备数量
    private int sumNumberDeviceNoRelation;//统计当前未分配的设备
    private List<Host> hostList;//所有教室（主机）列表
    private int sumNumber;//统计当前分组数量
    private int sumNumberHHTCHost;
    private int sumNumberUnknownHost;
    private int sumNumberHTPrHost;//投影仪总数
    private int sumNumberHrecHost; //录播设备总数
    private int sumNumberHhtwbHost; //白板一体机设备总数
    private List<Hostgroup> hostgroupList;
    private String strGroupIds;
    //弹窗参数
    private String windowParamName;
    private String windowTitle;
    private String windowLableName;
    private String windowParamId;
    private String windowType;
    private String windowUrl;
    private String hostId;
    private String userId; //用户ID
    private String userName; //用户名
    //分页参数
    private int pageCount;
    private int currentPage;
    @Resource
    private HostgroupService hostgroupService;
    @Resource
    private HostDevice hostDevice;
    @Resource
    HongheDeviceService hongheDeviceService;
    @Resource
    UserService userService;

    public String getHostNumber() {
        return hostNumber;
    }

    public void setHostNumber(String hostNumber) {
        this.hostNumber = hostNumber;
    }

    public int getSumNumberHhtwbHost() {
        return sumNumberHhtwbHost;
    }

    public void setSumNumberHhtwbHost(int sumNumberHhtwbHost) {
        this.sumNumberHhtwbHost = sumNumberHhtwbHost;
    }

    public int getSumNumberUnknownHost() {
        return sumNumberUnknownHost;
    }

    public void setSumNumberUnknownHost(int sumNumberUnknownHost) {
        this.sumNumberUnknownHost = sumNumberUnknownHost;
    }

    public int getSumNumberHrecHost() {
        return sumNumberHrecHost;
    }

    public int getSumNumberHTPrHost() {
        return sumNumberHTPrHost;
    }

    public void setSumNumberHTPrHost(int sumNumberHTPrHost) {
        this.sumNumberHTPrHost = sumNumberHTPrHost;
    }

    public void setSumNumberHrecHost(int sumNumberHrecHost) {
        this.sumNumberHrecHost = sumNumberHrecHost;
    }

    public int getSumNumberHHTCHost() {
        return sumNumberHHTCHost;
    }

    public void setSumNumberHHTCHost(int sumNumberHHTCHost) {
        this.sumNumberHHTCHost = sumNumberHHTCHost;
    }

    public List<Hostgroup> getHostgroupList() {
        return hostgroupList;
    }

    public void setHostgroupList(List<Hostgroup> hostgroupList) {
        this.hostgroupList = hostgroupList;
    }

    private List<Object[]> hglist;

    public List<Object[]> getHglist() {
        return hglist;
    }

    public void setHglist(List<Object[]> hglist) {
        this.hglist = hglist;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getHostId() {
        return hostId;
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

    public List<Map> getHostNoRelationList() {
        return hostNoRelationList;
    }

    public void setHostNoRelationList(List<Map> hostNoRelationList) {
        this.hostNoRelationList = hostNoRelationList;
    }

    private List<Map> hostNoRelationList;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    private String flag;

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public String getWindowUrl() {
        return windowUrl;
    }

    public void setWindowUrl(String windowUrl) {
        this.windowUrl = windowUrl;
    }

    public String getWindowType() {
        return windowType;
    }

    public void setWindowType(String windowType) {
        this.windowType = windowType;
    }

    public String getWindowParamName() {
        return windowParamName;
    }

    public void setWindowParamName(String windowParamName) {
        this.windowParamName = windowParamName;
    }

    public String getWindowTitle() {
        return windowTitle;
    }

    public void setWindowTitle(String windowTitle) {
        this.windowTitle = windowTitle;
    }

    public String getWindowLableName() {
        return windowLableName;
    }

    public void setWindowLableName(String windowLableName) {
        this.windowLableName = windowLableName;
    }

    public String getWindowParamId() {
        return windowParamId;
    }

    public void setWindowParamId(String windowParamId) {
        this.windowParamId = windowParamId;
    }

    //设备弹窗
    public String jumpWindow() {
        return "authorityDeny";
    }

    public int getSumNumber() {
        return sumNumber;
    }

    public void setSumNumber(int sumNumber) {
        this.sumNumber = sumNumber;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public void setGroupId(String gId) {
        this.groupId = gId;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String gName) {
        this.groupName = gName;
    }

    public String getStrGroupIds() {
        return strGroupIds;
    }

    public void setStrGroupIds(String str) {
        this.strGroupIds = str;
    }

    public int getSumNumberHost() {
        return sumNumberHost;
    }

    public void setSumNumberHost(int sumNumberHost) {
        this.sumNumberHost = sumNumberHost;
    }

    public int getSumNumberDevice() {
        return sumNumberDevice;
    }

    public void setSumNumberDevice(int sumNumberDevice) {
        this.sumNumberDevice = sumNumberDevice;
    }

    public int getSumNumberDeviceNoRelation() {
        return sumNumberDeviceNoRelation;
    }

    public void setSumNumberDeviceNoRelation(int sumNumberDeviceNoRelation) { this.sumNumberDeviceNoRelation = sumNumberDeviceNoRelation; }

    public List<Host> getHostList() {
        return hostList;
    }

    public void setHostList(List<Host> hostList) {
        this.hostList = hostList;
    }

    public void setHostgroup(Hostgroup hostgroup) {
        this.hostgroup = hostgroup;
    }

    public Hostgroup getHostgroup() {
        return hostgroup;
    }

    private Hostgroup hostgroup;
    /**
     * 获取分组列表
     * @return
     */
    public List<Hostgroup> getGroupList() {
//        try {
//            List groupList = hostgroupDao.getHostgroupList();
//            return groupList;
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//            return null;
//        }  /////////
        return null;
    }

    /**
     * 增加分组
     */
    public void addHostgroup() {
        JSONObject json = new JSONObject();
        if (groupName.equals("")) {
            json.put("success", false);
            json.put("msg", "不能为空！");
        } else {
            if (app_source()) {
                try {
                    groupName = URLDecoder.decode(groupName, "UTF-8");
                } catch (Exception e) {
                    e.printStackTrace();
                    json.put("success", false);
                    json.put("msg", "添加失败");
                }
            }
            Hostgroup hostgroup = hostgroupService.getHostGroupService(groupName);
            if (hostgroup == null) {
                if (hostgroupService.addHostgroupService(groupName)) {
                    json.put("success", true);
                    json.put("msg", "添加成功！");
                } else {
                    json.put("success", false);
                    json.put("msg", "添加失败！");
                }

            } else {

                json.put("success", false);
                json.put("msg", "该名称已存在，添加失败！");
            }
        }
        String jsonstr = json.toString();

        writeJSON(jsonstr);
    }

    /**
     * 更新分组信息
     */
    public void updateHostgroup() {
        JSONObject json = new JSONObject();
        Hostgroup hostgroup = hostgroupService.getHostgroup(groupId);
        if (app_source()) {
            try {
                groupName = URLDecoder.decode(groupName, "UTF-8");
            } catch (Exception e) {
                logger.error("", e);
                //  e.printStackTrace();
            }
        }
        if (groupName != null && !groupName.equals("") && !hostgroup.getGroupName().equals(groupName)) {
            Hostgroup hostgroupExists = hostgroupService.getHostGroupService(groupName);
            if (hostgroupExists == null) {
                hostgroup.setGroupName(groupName);
            } else {
                json.put("success", false);
                json.put("msg", "名称重复，修改失败");
            }
        }
        if (json.size() <= 0) {
            if (hostgroupService.updateHostgroupService(hostgroup)) {
                json.put("success", true);
                json.put("msg", "保存成功！");
            } else {
                json.put("success", true);
                json.put("msg", "保存失败！");
            }
        }
        String jsonstr = json.toString();
        writeJSON(jsonstr);
    }

    /**
     * 删除分组信息
     */
    public void delHostgroup() {

        JSONObject json = new JSONObject();
        boolean result = hostgroupService.deleteUserGroup(this.hostNumber, strGroupIds);
        if (result) {
            json.put("success", true);
            json.put("msg", "删除成功！");
        } else {
            json.put("success", false);
            json.put("msg", "删除失败！");
        }
        writeJSON(json.toString());
    }

    /**
     * //todo 加注释
     * @return
     */
    public List groupList() {
        List<Hostgroup> groupSet = null;
        return groupSet;
    }

    /**
     *  //todo 加注释
     */
    //@Transactional
    public void groupJSON() {
        String result = hostgroupService.groupJSONService(1, 10);
        if (result.equals("")) {
            logger.debug("error!!!!!");
        } else {
            writeJSON(result.toString());
        }
    }



    /**
     * 加载device_management页面数据
     * @return
     * @throws Exception
     */
    private String userRealName;
    private String logOutUrl;
    private String localAddr;
    public String getLocalAddr() {
        return localAddr;
    }
    public void setLocalAddr(String localAddr) {
        this.localAddr = localAddr;
    }
    public String getUserRealName() {
        return userRealName;
    }
    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }
    public String getLogOutUrl() {
        return logOutUrl;
    }
    public void setLogOutUrl(String logOutUrl) {
        this.logOutUrl = logOutUrl;
    }

    public String management() throws Exception {
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        Integer userId = user.getUserId();
        Map<String, String> roleName = userService.getRoleByUid(userId.toString());
        String type = RoleUtil.RoleToDevice(userId.toString(), roleName);


        HttpServletRequest request = ServletActionContext.getRequest();
        String referer = request.getHeader("Referer");
        String substring = referer.substring(referer.lastIndexOf("/"), referer.length());
        if(referer.contains("login.jsp") || substring.equals("/")){
            //http://localhost:8080/viewclass/Viewclass_getHostGroup.do
            referer = request.getScheme()+"://" + request.getServerName() + ":"+ request.getServerPort()+"/viewclass/Viewclass_getHostGroup.do";
        }
        localAddr = request.getServerName();
        logOutUrl = referer.replaceAll("/","%2F").replaceAll(":","%3A");
        try
        {
            userRealName = user.getUserRealName();
            if(userRealName != null)
            {
                userRealName = URLEncoder.encode(userRealName,"utf-8");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        setUserId(userId+"");
        userName = user.getUserName();
        return "hostgroupmanagement";
        /*sumNumberHHTCHost = 0;
        sumNumberHrecHost = 0;
        sumNumberHTPrHost = 0;
        sumNumberUnknownHost = 0;
        sumNumberHhtwbHost = 0;
        userId = 0; //todo 加注释
        if (CommonName.DEVICE_TYPE_ALL.equals(type)) {
            sumNumberHHTCHost = hostDevice.getHostCount(CommonName.DEVICE_TYPE_SCREEN);
            sumNumberHrecHost = hostDevice.getHostCount(CommonName.DEVICE_TYPE_RECOURD);
            sumNumberHTPrHost = hostDevice.getHostCount(CommonName.DEVICE_TYPE_PROJECTOR);

            sumNumberHhtwbHost = hostDevice.getHostCount(CommonName.DEVICE_TYPE_WHITEBOARD);
            sumNumberUnknownHost = hostDevice.getHostCount(CommonName.DEVICE_TYPE_UNKNOWN);
        } else if (CommonName.DEVICE_TYPE_RECOURD.equals(type)) {
            sumNumberHrecHost = hostDevice.getHostCount(CommonName.DEVICE_TYPE_RECOURD);
        } else if (CommonName.DEVICE_TYPE_SCREEN.equals(type)) {
            sumNumberHHTCHost = hostDevice.getHostCount(CommonName.DEVICE_TYPE_SCREEN);
            sumNumberHrecHost = 0;
            sumNumberUnknownHost = 0;
            sumNumberHhtwbHost = 0;
            userId = 0;

        } else if (CommonName.DEVICE_TYPE_PROJECTOR.equals(type)) {
            sumNumberHTPrHost = hostDevice.getHostCount(CommonName.DEVICE_TYPE_PROJECTOR);
            sumNumberUnknownHost = 0;
            sumNumberHhtwbHost = 0;
            userId = 0;
        } else if (CommonName.DEVICE_TYPE_WHITEBOARD.equals(type)) {
            sumNumberHhtwbHost = hostDevice.getHostCount(CommonName.DEVICE_TYPE_WHITEBOARD);

        }

        hglist = hostgroupService.getHostgroupList2Service(userId.toString(), type);
        //获取所有未分组的教室数量
        Map<String, Object> map = hostgroupService.getUnknowGroup(type);
        List<Map> hostList = (List<Map>) map.get("host_list");
        if (hostList != null) {
            sumNumberDeviceNoRelation = hostList.size();
        } else {
            sumNumberDeviceNoRelation = 0;
        }

//        ActionContext.getContext().put("discoverDevice", hostDevice.discoverCount(type));


        if (ConfigureUtil.isAll()) {  //软件支持所有设备时
            ActionContext.getContext().put("discoverDevice", hostDevice.discoverCount(type));//获取设备数量
        } else {//软件依据配置信息，支持“hhtc,hhrec,htpr,hhtwb”中的某几类设备时
            StringBuffer strType = new StringBuffer();
            if (type.equals(CommonName.DEVICE_TYPE_ALL)) {
                if (ConfigureUtil.isHhtc()) {
                    strType.append(CommonName.DEVICE_TYPE_SCREEN + ",");
                }
                if (ConfigureUtil.isHhrec()) {
                    strType.append(CommonName.DEVICE_TYPE_RECOURD + ",");
                }
                if (ConfigureUtil.isHtpr()) {
                    strType.append(CommonName.DEVICE_TYPE_PROJECTOR + ",");
                }if (ConfigureUtil.isHhtwb()){
                    strType.append(CommonName.DEVICE_TYPE_WHITEBOARD+",");
                }
            } else {

                if (ConfigureUtil.isHhtc() && type.equals(CommonName.DEVICE_TYPE_SCREEN)) {
                    strType.append(CommonName.DEVICE_TYPE_SCREEN + ",");
                }
                if (ConfigureUtil.isHhrec() && type.equals(CommonName.DEVICE_TYPE_RECOURD)) {
                    strType.append(CommonName.DEVICE_TYPE_RECOURD + ",");
                }
                if (ConfigureUtil.isHtpr() && type.equals(CommonName.DEVICE_TYPE_PROJECTOR)) {
                    strType.append(CommonName.DEVICE_TYPE_PROJECTOR + ",");
                }
                if (ConfigureUtil.isHhtwb()&&type.equals(CommonName.DEVICE_TYPE_WHITEBOARD)){
                    strType.append(CommonName.DEVICE_TYPE_WHITEBOARD+",");
                }
            }
            ActionContext.getContext().put("discoverDevice", hostDevice.discoverCount(strType.toString()));
        }

        //todo 考虑是否添加拓奇设备

        ActionContext.getContext().put("deviceConfig_jp", hostgroupService.getDeviceConfigList("12345678"));
        ActionContext.getContext().put("deviceConfig_fbs", hostgroupService.getDeviceConfigList("HL-ZF0400"));
        ActionContext.getContext().put("deviceConfig_1u", hostgroupService.getDeviceConfigList("HL-ZJ0500"));
        return "hostgroupmanagement";*/
    }

    /**
     * 提供 app接口
     */
    public void appManagement() {
        if (app_source()) {
            String userId = this.userId;
            String userName = this.userName;

            Map<String, String> roleName = userService.getRoleByUid(userId.toString());
            String type = RoleUtil.RoleToDevice(userId, roleName);
            userId = "0";
            if (CommonName.DEVICE_TYPE_ALL.equals(type)) {
                sumNumberHHTCHost = hostDevice.getHostCount(CommonName.DEVICE_TYPE_SCREEN);
                sumNumberHrecHost = hostDevice.getHostCount(CommonName.DEVICE_TYPE_RECOURD);
                sumNumberHTPrHost = hostDevice.getHostCount(CommonName.DEVICE_TYPE_PROJECTOR);
                sumNumberHhtwbHost = hostDevice.getHostCount(CommonName.DEVICE_TYPE_WHITEBOARD);
                userId = "0";

            } else if (CommonName.DEVICE_TYPE_RECOURD.equals(type)) {
                sumNumberHrecHost = hostDevice.getHostCount(CommonName.DEVICE_TYPE_RECOURD);
            } else if (CommonName.DEVICE_TYPE_SCREEN.equals(type)) {
                sumNumberHHTCHost = hostDevice.getHostCount(CommonName.DEVICE_TYPE_SCREEN);
            } else if (CommonName.DEVICE_TYPE_PROJECTOR.equals(type)) {
                sumNumberHTPrHost = hostDevice.getHostCount(CommonName.DEVICE_TYPE_PROJECTOR);
            } else if (CommonName.DEVICE_TYPE_WHITEBOARD.equals(type)){
                sumNumberHhtwbHost = hostDevice.getHostCount(CommonName.DEVICE_TYPE_WHITEBOARD);
                userId = "0";
            }
            hglist = hostgroupService.getHostgroupList2Service(userId, type);
            //获取所有未分组的教室数量
            sumNumberDeviceNoRelation = hostgroupService.getUnknowGroup(type).size();
            Map<String, Object> hashmap = new HashMap<String, Object>();
            hashmap.put("discoverCount", hostDevice.discoverCount(type));
            hashmap.put("hglist", hglist);//分组列表
            hashmap.put("sumNumberHHTCHost", sumNumberHHTCHost);//获取主机数量
            hashmap.put("sumNumberHrecHost", sumNumberHHTCHost);//获取主机数量
            hashmap.put("sumNumberHTPrHost", sumNumberHTPrHost);//获取主机数量
            hashmap.put("sumNumberHhtwbHost",sumNumberHhtwbHost);//获取主机数量
            hashmap.put("noGroupDevice", sumNumberDeviceNoRelation);//未分组设备数量
            writeJSON(JSONSerializer.toJSON(hashmap).toString());
        }
    }

    /**
     * 返回所有分组
     */
    public void appGetAllGroup() {
        if (app_source()) {
            Integer userId = Integer.parseInt(this.userId);
            Map<String, String> roleName = userService.getRoleByUid(userId.toString());
            if (userId == 1 || roleName.containsValue(com.honghe.recordweb.service.frontend.user.Role.Role_SchoolManager.toString()) || roleName.containsValue(com.honghe.recordweb.service.frontend.user.Role.Role_SystemManager.toString())) {
                List<Object[]> hostgrouplist = hostgroupService.appgetallHostgroupList();
                JSONObject json = new JSONObject();
                json.put("hostgrouplist", hostgrouplist);
                writeJSON(json.toString());
            }
        }
    }

}
