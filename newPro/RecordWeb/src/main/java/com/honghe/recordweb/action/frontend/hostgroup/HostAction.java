package com.honghe.recordweb.action.frontend.hostgroup;

import com.honghe.recordhibernate.entity.Device;
import com.honghe.recordhibernate.entity.Dspecification;
import com.honghe.recordhibernate.entity.Host;
import com.honghe.recordhibernate.entity.User;
import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.action.SessionManager;
import com.honghe.recordweb.service.frontend.devicemanager.HostDevice;
import com.honghe.recordweb.service.frontend.hostgroup.HostgroupService;
import com.honghe.recordweb.service.frontend.user.UserService;
import com.honghe.recordweb.util.*;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by chby on 2014/9/29.
 */
@Controller
@Scope(value = "prototype")
public class HostAction extends BaseAction {
    private String hostId;
    private String hostName;
    private String hostIpaddr;
    private String hostSerialno = "";
    private String hostDesc = "";
    private String cameraName = ""; //镜头名
    private int pageSize;
    private String hostUserName = "";
    private String hostPassWord = "";
    private String groupId;
    private String dspecId;
    private String flag;
    private String hostgroupId;//当前选中的分组
    private String hostgroupIdOld;//原分组
    private String hostRelation;//当前分组已分配的教室
    private String hostNoRelation;//所有未分配的教室
    private String strHostIds;//重新分配的教室ID
    private List<Map> hostNoRelationList;
    private List<Map> hostRelationList;
    private List<Host> hostList;
    private List<Object[]> hostgroupListObject;//移动分组tree
    private int sumNumberHHTCHost;
    private int sumNumberHTPrHost;//投影仪总数
    private int sumNumberHHTwbHost;
    private String hostTreeFlag;//分组tree标志位；hostTreeFlag=1,则分组下显示host（为设备页面的移动分组tree）；否则只有分组数据（为班级移动分组tree）
    private Map<String, Device> hostMap;//当前发现的主机
    private Host hostInfo;//当前选中的host信息
    private String userId;//用户id
    private String type;//设备类型
    private String deviceType = "";
    private String protocolType = "";
    private int sumNumberHrecHost;
    private String nasUserName;
    private String nasPassword;
    private String nasRootPath;
    private String nasFilePath;
    private String hostIps = "";//,分割
    private String netUrl;
    private String deviceNames;
    private String mainTokens;
    private String subTokens;
    private int groupNumber;
    private String mianTokenStreams;
    private String subTokenStreams;
    private int sumNumber;
    private Integer currentPage;
    private List<Object[]> deviceConfigList;
    private static final ReentrantLock reentrantLock = new ReentrantLock();
    private static int oneSettingResponse = 0;
    private String hostIds = "";//分配教室时的id集合"1,2,3,"
    private String hostgroupName = "";//当前分组的名称
    private String nasId = "";
    private static boolean isDevicediscover = false;
    private Map<String, Object> hostNoRelationLists;
    private Map<String, Object> hostNoRelationHhtcLists;
    private Map<String, Object> hostNoRelationHhrecLists;
    private Map<String, Object> hostNoRelationHtprLists;
    private Map<String, Object> hostNoRelationHhtwbLists;

    @Resource
    private HostgroupService hostgroupService;
    @Resource
    private HostDevice hostDevice;
    @Resource
    private UserService userService;
    public List<Map<String, Object>> getHostInfoList() {
        return hostInfoList;
    }

    public void setHostInfoList(List<Map<String, Object>> hostInfoList) {
        this.hostInfoList = hostInfoList;
    }

    private List<Map<String, Object>> hostInfoList; //根据主机类型获取主机列表

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    //对应 dspecification表里数据

    private Dspecification dspecification;

    public String getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(String protocolType) {
        this.protocolType = protocolType;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getSumNumberHHTwbHost() {
        return sumNumberHHTwbHost;
    }

    public void setSumNumberHHTwbHost(int sumNumberHHTwbHost) {
        this.sumNumberHHTwbHost = sumNumberHHTwbHost;
    }

    public int getSumNumberHTPrHost() {
        return sumNumberHTPrHost;
    }

    public void setSumNumberHTPrHost(int sumNumberHTPrHost) {
        this.sumNumberHTPrHost = sumNumberHTPrHost;
    }

    public int getSumNumberHrecHost() {
        return sumNumberHrecHost;
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

    public Map<String, Object> getHostNoRelationLists() {
        return hostNoRelationLists;
    }

    public void setHostNoRelationLists(Map<String, Object> hostNoRelationLists) { this.hostNoRelationLists = hostNoRelationLists; }

    public Map<String, Object> getHostNoRelationHhtwbLists() {
        return hostNoRelationHhtwbLists;
    }

    public void setHostNoRelationHhtwbLists(Map<String, Object> hostNoRelationHhtwbLists) { this.hostNoRelationHhtwbLists = hostNoRelationHhtwbLists; }

    public Map<String, Object> getHostNoRelationHhrecLists() {
        return hostNoRelationHhrecLists;
    }

    public void setHostNoRelationHhrecLists(Map<String, Object> hostNoRelationHhrecLists) { this.hostNoRelationHhrecLists = hostNoRelationHhrecLists; }

    public Map<String, Object> getHostNoRelationHhtcLists() {
        return hostNoRelationHhtcLists;
    }

    public void setHostNoRelationHhtcLists(Map<String, Object> hostNoRelationHhtcLists) { this.hostNoRelationHhtcLists = hostNoRelationHhtcLists; }

    public Map<String, Object> getHostNoRelationHtprLists() {
        return hostNoRelationHtprLists;
    }

    public void setHostNoRelationHtprLists(Map<String, Object> hostNoRelationHtprLists) { this.hostNoRelationHtprLists = hostNoRelationHtprLists; }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNasUserName() {
        return nasUserName;
    }

    public void setNasUserName(String nasUserName) {
        this.nasUserName = nasUserName;
    }

    public String getNasId() {
        return nasId;
    }

    public void setNasId(String nasId) {
        this.nasId = nasId;
    }

    public String getNasPassword() {
        return nasPassword;
    }

    public void setNasPassword(String nasPassword) {
        this.nasPassword = nasPassword;
    }

    public String getNasRootPath() {
        return nasRootPath;
    }

    public void setNasRootPath(String nasRootPath) {
        this.nasRootPath = nasRootPath;
    }

    public String getNasFilePath() {
        return nasFilePath;
    }

    public void setNasFilePath(String nasFilePath) {
        this.nasFilePath = nasFilePath;
    }

    public String getHostgroupName() {
        return hostgroupName;
    }

    public void setHostgroupName(String hostgroupName) {
        this.hostgroupName = hostgroupName;
    }

    private List<Dspecification> dspecificationList;

    public String getCameraName() {
        return cameraName;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public String getHostIps() {
        return hostIps;
    }

    public void setHostIps(String hostIps) {
        this.hostIps = hostIps;
    }

    public String getDspecId() {
        return dspecId;
    }

    public void setDspecId(String dspecId) {
        this.dspecId = dspecId;
    }

    public List<Dspecification> getDspecificationList() {
        return dspecificationList;
    }

    public void setDspecificationList(List<Dspecification> dspecificationList) { this.dspecificationList = dspecificationList; }

    public String getHostIds() {
        return hostIds;
    }

    public void setHostIds(String hostIds) {
        this.hostIds = hostIds;
    }

    public Host getHostInfo() {
        return hostInfo;
    }

    public void setHostInfo(Host hostInfo) {
        this.hostInfo = hostInfo;
    }

    public String getHostTreeFlag() {
        return hostTreeFlag;
    }

    public void setHostTreeFlag(String hostTreeFlag) {
        this.hostTreeFlag = hostTreeFlag;
    }

    public String getHostgroupIdOld() {
        return hostgroupIdOld;
    }

    public void setHostgroupIdOld(String hostgroupIdOld) {
        this.hostgroupIdOld = hostgroupIdOld;
    }

    public String getHostNoRelation() {
        return hostNoRelation;
    }

    public void setHostNoRelation(String hostNoRelation) {
        this.hostNoRelation = hostNoRelation;
    }

    public String getHostRelation() {
        return hostRelation;
    }

    public void setHostRelation(String hostRelation) {
        this.hostRelation = hostRelation;
    }

    public List<Host> getHostList() {
        return hostList;
    }

    public void setHostList(List<Host> hostList) {
        this.hostList = hostList;
    }

    public Map<String, Device> getHostMap() {
        return hostMap;
    }

    public void setHostMap(Map<String, Device> hostMap) {
        this.hostMap = hostMap;
    }

    public List<Map> getHostNoRelationList() {
        return hostNoRelationList;
    }

    public void setHostNoRelationList(List<Map> hostNoRelationList) {
        this.hostNoRelationList = hostNoRelationList;
    }

    public List<Map> getHostRelationList() {
        return hostRelationList;
    }

    public void setHostRelationList(List<Map> hostRelationList) {
        this.hostRelationList = hostRelationList;
    }

    public String getHostgroupId() {
        return hostgroupId;
    }

    public void setHostgroupId(String hostgroupId) {
        this.hostgroupId = hostgroupId;
    }

    public String getStrHostIds() {
        return strHostIds;
    }

    public void setStrHostIds(String strHostIds) {
        this.strHostIds = strHostIds;
    }

    public int getSumNumber() {
        return sumNumber;
    }

    public void setSumNumber(int sumNumber) {
        this.sumNumber = sumNumber;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getNetUrl() {
        return netUrl;
    }

    public void setNetUrl(String netUrl) {
        this.netUrl = netUrl;
    }

    public String getDeviceNames() {
        return deviceNames;
    }

    public void setDeviceNames(String deviceNames) {
        this.deviceNames = deviceNames;
    }

    public String getMainTokens() {
        return mainTokens;
    }

    public void setMainTokens(String mainTokens) {
        this.mainTokens = mainTokens;
    }

    public String getSubTokens() {
        return subTokens;
    }

    public void setSubTokens(String subTokens) {
        this.subTokens = subTokens;
    }

    public String getMianTokenStreams() {
        return mianTokenStreams;
    }

    public void setMianTokenStreams(String mianTokenStreams) {
        this.mianTokenStreams = mianTokenStreams;
    }

    public String getSubTokenStreams() {
        return subTokenStreams;
    }

    public void setSubTokenStreams(String subTokenStreams) {
        this.subTokenStreams = subTokenStreams;
    }

    public List<Object[]> getDeviceConfigList() {
        return deviceConfigList;
    }

    public void setDeviceConfigList(List<Object[]> deviceConfigList) {
        this.deviceConfigList = deviceConfigList;
    }

    public String getHostPassWord() { return hostPassWord; }

    public void setHostPassWord(String hostPassWord) { this.hostPassWord = hostPassWord; }

    public String getHostId() { return hostId; }

    public void setHostId(String hostId) { this.hostId = hostId; }

    public String getHostName() { return hostName; }

    public void setHostName(String hostName) { this.hostName = hostName; }

    public String getHostIpaddr() { return hostIpaddr; }

    public void setHostIpaddr(String hostIpaddr) { this.hostIpaddr = hostIpaddr; }

    public String getHostSerialno() { return hostSerialno; }

    public void setHostSerialno(String hostSerialno) { this.hostSerialno = hostSerialno; }

    public String getHostDesc() { return hostDesc; }

    public void setHostDesc(String hostDesc) { this.hostDesc = hostDesc; }

    public String getHostUserName() { return hostUserName; }

    public void setHostUserName(String hostUserName) { this.hostUserName = hostUserName; }

    /**
     * 判断某一id的教室是否已划分到分组中（需要给用户提示）
     */
    public void hostRelation() {
        JSONObject json = new JSONObject();
        boolean result = hostgroupService.hostRelationService(hostId);
        if (result) {
            json.put("success", true);
            json.put("msg", "该教室已被划分到分组中");
        } else {
            json.put("success", false);
            json.put("msg", "未划分，可直接删除！");
        }
        writeJSON(json.toString());
    }

    /**
     * 删除某一id教室的分组关系
     */
    public void delHostRelation() {
        JSONObject json = new JSONObject();
        // boolean result = hostgroupService.delHostRelationService(hostId);
        boolean result = hostgroupService.delGroupRelationService(hostgroupId, hostIds);
        if (result) {
            json.put("success", true);
            json.put("msg", "删除成功");
        } else {
            json.put("success", false);
            json.put("msg", "删除失败");
        }
        writeJSON(json.toString());
    }

    /**
     * 将对应id的教室划分给对应id的分组,移动分组
     */
    public void moveHostRelation() {
        JSONObject json = new JSONObject();
        if (hostIds != null && !hostIds.equals("")) {
            String rArray[] = hostIds.split(",");
            for (int i = 0; i < rArray.length; i++) {
                if (!rArray[i].isEmpty()) {
                    hostgroupService.hostMoveService(rArray[i], groupId, hostgroupIdOld);
                }
            }
            json.put("success", true);
            json.put("msg", "移动成功");
        } else {
            json.put("success", false);
            json.put("msg", "移动失败");
        }
        writeJSON(json.toString());
    }

    /**
     * 获取教室信息
     * @return
     */
    public Host getHostinfo() {
        try {
            return hostgroupService.getHostInfo(Integer.valueOf(hostId));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 修改教室信息
     */
    public void updateHost() {
        JSONObject json = new JSONObject();
        Map hostInfo = hostDevice.getHostInfoById(hostId);
        //根据设备id获取设备信息
        if (hostName != null && !hostName.equals("") && hostInfo != null && !hostInfo.isEmpty()) {
            Boolean hostExists = (Boolean) hostDevice.getHostService(hostName);
            String devicetype = (String) hostInfo.get("dtype_name");
            if (!hostExists) {
                if (hostExists == null) {
                    json.put("success", false);
                    json.put("msg", "出现异常，修改失败");
                } else {
                    Boolean res = hostDevice.updateHostName(hostIpaddr, hostName,devicetype);
                    if (res) {
                        json.put("success", true);
                        json.put("msg", "修改成功");
                    } else {
                        json.put("success", false);
                        json.put("msg", "修改失败");
                    }
                }
            } else {
                json.put("success", false);
                json.put("msg", "名称重复，修改失败");
            }
        }
        writeJSON(json.toString());
    }

    /**
     * 删除教室
     */
    public void delHost() {
        JSONObject json = new JSONObject();
        try {
            //删除计划等
            //删除设备
            boolean result = hostgroupService.delHostService(hostId, this.hostIpaddr);
            if (result) {
                json.put("success", true);
                json.put("msg", "删除成功");
            } else {
                json.put("success", false);
                json.put("msg", "删除失败");
            }
        } catch (Exception e) {
            json.put("success", false);
            json.put("msg", "删除失败");
        }
        writeJSON(json.toString());
    }

    /**
     * 获取当前的教室数量,按不同的conditions
     */
    public void countNumber() {
//        String conditions = "";
//        sumNumberHHTCHost = hostgroupService.countService("hhtc");
//        sumNumberHrecHost = hostgroupService.countService("hhrec");
//        groupNumber = hostgroupService.countGroupService(conditions);
    }

    /**
     * 加载教室分配列表页面
     * @return
     */
    public String assignHost() {
        if (!hostgroupId.equals(null) && !hostgroupId.equals("")) {
            User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
            Integer userId = user.getUserId();
            Map<String, String> roleName = userService.getRoleByUid(userId.toString());
            String type = RoleUtil.RoleToDevice(userId.toString(), roleName);

            hostRelationList = hostgroupService.hostListJSON(hostgroupId, type);
            Map<String, Object> map = hostgroupService.getUnknowGroup(type); //hostNoRelation,目前修改为某用户负责的所有教室，及未分组的教室，对于同一教室可以分为多个组
            hostNoRelationLists = map;
            return "assignhost";
        }
        return "authorityDeny";
    }

    /**
     * app教室分配
     */
    public void appassignHost() {
        JSONObject json = new JSONObject();
        if (app_source()) {
            if (hostgroupId != null && !hostgroupId.equals("") && hostgroupIdOld != null && !hostgroupIdOld.equals("")) {
                String rArray[] = hostIds.split(",");
                //hostgroupId 小于 0 表示从之前分组移到未分组
                if (Integer.parseInt(hostgroupId) < 0 && Integer.parseInt(hostgroupIdOld) > 0) {
                    for (int i = 0; i < rArray.length; i++) {
                        if (!rArray[i].isEmpty()) {
                            hostgroupService.delGroupRelationService(hostgroupIdOld, rArray[i]);
                        }
                    }
                    json.put("success", true);
                    json.put("msg","分配成功");
                } else if (Integer.parseInt(hostgroupId) > 0 && Integer.parseInt(hostgroupIdOld) < 0) {
                    for (int i = 0; i < rArray.length; i++) {
                        if (!rArray[i].isEmpty()) {
                            hostgroupService.addHostRelationService(rArray[i], hostgroupId);
                        }
                    }
                    json.put("success", true);
                    json.put("msg", "分配成功");
                } else if (Integer.parseInt(hostgroupId) > 0 && Integer.parseInt(hostgroupIdOld) > 0) {
                    for (int i = 0; i < rArray.length; i++) {
                        if (!rArray[i].isEmpty()) {
                            hostgroupService.hostMoveService(rArray[i], hostgroupId, hostgroupIdOld);
                        }
                    }
                    json.put("success", true);
                    json.put("msg", "分配成功");
                } else {
                    json.put("success", false);
                    json.put("msg", "数据错误");
                }
            }
            writeJSON(json.toString());
        }
    }

    /**
     * 教室分配
     */
    public void assignConfirm() {
        JSONObject json = new JSONObject();
        if (hostgroupId != null && !hostgroupId.equals("") && Integer.parseInt(hostgroupId) > 0) {
            //  int tmpHgId=Integer.parseInt(hostgroupId);
            hostgroupService.delGroupRelationService(hostgroupId, "");
            String rArray[] = hostIds.split(",");
            for (int i = 0; i < rArray.length; i++) {
                if (!rArray[i].isEmpty()) {
                    hostgroupService.addHostRelationService(rArray[i], hostgroupId);
                }
            }
            json.put("success", true);
            json.put("msg", "分配成功");
        } else {
            json.put("success", false);
            json.put("msg", "数据错误");
        }
        writeJSON(json.toString());
    }

    /**
     * 加载当前所有未分组教室（主机）列表页面
     * @return
     */
    public String hostManagement() {
        //获取所有未分组的教室
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        Integer userId = user.getUserId();
        Map<String, String> roleName = userService.getRoleByUid(userId.toString());
        String type = RoleUtil.RoleToDevice(userId.toString(), roleName);

        if (currentPage == null || currentPage == 0) {
            currentPage = 1;
        }
        pageSize = 100;
        if (type.equals(CommonName.DEVICE_TYPE_ALL)) {
            hostNoRelationHhtcLists = hostgroupService.getUnknowGroup(CommonName.DEVICE_TYPE_SCREEN, String.valueOf(pageSize), String.valueOf(currentPage));
            hostNoRelationHhrecLists = hostgroupService.getUnknowGroup(CommonName.DEVICE_TYPE_RECOURD, String.valueOf(pageSize), String.valueOf(currentPage));
            hostNoRelationHtprLists = hostgroupService.getUnknowGroup(CommonName.DEVICE_TYPE_PROJECTOR, String.valueOf(pageSize), String.valueOf(currentPage));
            hostNoRelationHhtwbLists = hostgroupService.getUnknowGroup(CommonName.DEVICE_TYPE_WHITEBOARD,String.valueOf(pageSize),String.valueOf(currentPage));
        }
        hostNoRelationLists = hostgroupService.getUnknowGroup(type, String.valueOf(pageSize), String.valueOf(currentPage));
        return "hostmanagement";
    }

    /**
     * app加载当前所有未分组教室（主机）列表页面
     */
    public void appHostManagement() {
        if (app_source()) {
            String userId = this.userId;
            Map<String, String> roleName = userService.getRoleByUid(userId);
            String type = RoleUtil.RoleToDevice(userId, roleName);
            hostNoRelationLists = hostgroupService.getUnknowGroup(type);
            Map<String, Object> hashmap = new HashMap<String, Object>();
            hashmap.put("hostList", hostNoRelationLists);
            writeJSON(JSONSerializer.toJSON(hashmap).toString());
        }
    }

    /**
     * 加载（发现）主机
     */
    public void loadNewHost() {
        JSONObject jsonObject = new JSONObject();
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        if (user == null) {
            return;
        }
        Map<String, String> roleName = userService.getRoleByUid(user.getUserId().toString());
        String type = RoleUtil.RoleToDevice(user.getUserId().toString(), roleName);
        String userName = "";
        String password = "";
        String system = "/";
        system = ServletActionContext.getRequest().getContextPath();

        if (ConfigureUtil.isAll()) { //软件支持所有设备时
            jsonObject = hostgroupService.addHost(type, userName, password, system);
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
                }
                if (ConfigureUtil.isHhtwb()){
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
                if (ConfigureUtil.isHhtwb() && type.equals(CommonName.DEVICE_TYPE_WHITEBOARD)){
                    strType.append(CommonName.DEVICE_TYPE_WHITEBOARD+",");
                }
            }

            jsonObject = hostgroupService.addHost(strType.toString(), userName, password, system);
        }
        this.writeJSON(jsonObject.toString());
    }


    /**
     * 手动添加
     */
    public void addNewHost() {
        JSONObject jsonObject = new JSONObject();
        int maxCnt = LicenseUtils.DEVICE_NUM_DEFAULT;
        String ip = this.hostIpaddr;
        String name = this.hostName;
        String type = this.deviceType;
        sumNumberHHTCHost = hostDevice.getHostCount(CommonName.DEVICE_TYPE_SCREEN);
        sumNumberHrecHost = hostDevice.getHostCount(CommonName.DEVICE_TYPE_RECOURD);
        sumNumberHTPrHost = hostDevice.getHostCount(CommonName.DEVICE_TYPE_PROJECTOR);//投影仪接口
        sumNumberHHTwbHost = hostDevice.getHostCount(CommonName.DEVICE_TYPE_WHITEBOARD);//白板一体机
        Map<String, String> licenseInfo = LicenseUtils.findLicense();
        if (String.valueOf(DeviceNameUtil.DEVIC_SCREEN).equals(type) || String.valueOf(DeviceNameUtil.DEVICE_PROJECTOR).equals(type) ||String.valueOf(DeviceNameUtil.DEVICE_WHITEBOARD).equals(type)) {
            maxCnt = LicenseUtils.getDeviceNum("hhtc_device_num");
            if (sumNumberHHTCHost >= maxCnt) {
                jsonObject.put("result", -2);
            } else {
                int result = hostgroupService.addHost(ip, name, type);
                jsonObject.put("result", result);
            }
        } else {
            maxCnt = LicenseUtils.getDeviceNum("hhtrec_device_num");
            if (sumNumberHrecHost >= maxCnt) {
                jsonObject.put("result", -2);
            } else {
                int result = hostgroupService.addHost(ip, name, type);
                jsonObject.put("result", result);
            }
        }
        this.writeJSON(jsonObject.toString());
    }

    /**
     * 手动添加外网设备
     */
    public void addNetHost() {
        synchronized (HostAction.class) {

            int hostCnt = hostDevice.getHostCount(CommonName.DEVICE_TYPE_RECOURD);
            int maxCnt = LicenseUtils.getDeviceNum("hhtrec_device_num");
            JSONObject jsonObject = new JSONObject();
            if (hostCnt >= maxCnt) {
                jsonObject.put("result", -2);
            } else {
                String ip = this.hostIpaddr;
                String name = this.hostName;
                String type = this.deviceType;
//        String netUrl = "http://www.onvif.org/ver10/hrec/wsdl http://192.168.17.135:666/HRECBinding,"
//                + "http://www.onvif.org/ver10/device/wsdl http://192.168.17.135:666/DeviceBinding,"
//                + "http://www.onvif.org/ver10/events/wsdl http://192.168.17.135:666/PullPointSubscriptionBinding,"
//                + "http://www.onvif.org/ver10/media/wsdl http://192.168.17.135:666/MediaBinding,"
//                + "http://www.onvif.org/ver10/ptz/wsdl http://192.168.17.135:666/PTZBinding";
                int result = hostgroupService.addNetHost(ip, name, type, netUrl, deviceNames, mainTokens, subTokens, mianTokenStreams, subTokenStreams);
                jsonObject.put("result", result);
            }
            this.writeJSON(jsonObject.toString());
        }

    }

    /**
     * 添加虚拟主机
     */
    public void addVirtualHost() {
        synchronized (HostAction.class) {
            int hostCnt = hostDevice.getHostCount(CommonName.DEVICE_TYPE_RECOURD);
            int maxCnt = LicenseUtils.getDeviceNum("hhtrec_device_num");
            JSONObject jsonObject = new JSONObject();
            if (hostCnt >= maxCnt) {
                jsonObject.put("result", -2);
            } else {
                int result = hostgroupService.addVirtualHost(hostName, deviceType, netUrl, cameraName);
                jsonObject.put("result", result);
            }
            this.writeJSON(jsonObject.toString());
        }
    }

    /**
     * 教室信息编辑页面
     * @return
     */
    public String editHostInfo() {
        if (hostId != null && !hostId.equals("")) {
            //hostInfo = hostgroupService.HostInfoService(hostId);
            //dspecificationList = hostgroupService.getSpecList();//获取类型列表
            return "edithostinfo";
        } else {
            return "authorityDeny";
        }
    }

    /**
     * 加载某个分组下的教室
     * @return
     * @throws Exception
     */
    public String loadHostByHostgroup() throws Exception {
        hostgroupName = java.net.URLDecoder.decode(hostgroupName, "UTF-8");
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        if (user == null) {
            return "authorityDeny";
        }
        Map<String, String> roleName = userService.getRoleByUid(user.getUserId().toString());
        String type = RoleUtil.RoleToDevice(user.getUserId().toString(), roleName);

        if (hostgroupId != null && !hostgroupId.equals("") && Integer.parseInt(hostgroupId) > 0) {

            hostRelationList = hostgroupService.hostListJSON(hostgroupId, type);
            //Hostgroup hostgroup = hostgroupService.getHostgroup(hostgroupId);
            return "loadhostbyhostgroup";

        } else {
            return "authorityDeny";
        }
    }

    /**
     * app加载某个分组下的教室
     */
    public void appLoadHostByHostgroup() {
        if (app_source()) {
            String userId = this.userId;
            if (userId == null || userId.equals("")) {
                return;
            }
            String type = RoleUtil.RoleToDevice(userId, userService.getRoleByUid(userId));

            hostRelationList = hostgroupService.hostListJSON(hostgroupId, type);
            Map<String, Object> hashmap = new HashMap<String, Object>();
            hashmap.put("hostList", hostRelationList);
            writeJSON(JSONSerializer.toJSON(hashmap).toString());
        }
    }

    /**
     * 分组tree
     * @return
     * @throws Exception
     */
    public String hostTree() throws Exception {
        String userId = "0";
        pageSize = 100;
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        if (user == null) {
            return "authorityDeny";
        }
        Map<String, String> roleName = userService.getRoleByUid(user.getUserId().toString());
        String type = RoleUtil.RoleToDevice(user.getUserId().toString(), roleName);

        hostTreeFlag = (hostTreeFlag.isEmpty() || hostTreeFlag == null) ? "0" : "1";
        hostRelationList = hostgroupService.getHostgroupListNewService(userId, hostTreeFlag);
        hostNoRelationLists = hostgroupService.getUnknowGroup(type);
        return "hosttree";
    }

    /**
     * 通过类型获取设备
     * @return
     */
    public String getDeviceBytype() {
        String type = "";
        if (this.type != null) {
            type = this.type.toString();
        }
        hostInfoList = hostgroupService.hostInfoByType(type);
        return "devicelist";
    }

    /**
     * 获取未分组设备
     * @return
     */
    public String getNoRelationDeviceBytype() {
        String type = "";
        if (this.type != null) {
            type = this.type.toString();
        }
        pageSize = 100;
        if (currentPage == null || currentPage == 0) {
            currentPage = 1;
        }
        hostNoRelationLists = hostgroupService.getUnknowGroup(type, String.valueOf(pageSize), String.valueOf(currentPage));
        return "deviceNoRelationList";
    }
    }
