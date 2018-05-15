package com.honghe.recordweb.service.frontend.hostgroup;

import com.honghe.device.sdk.DeviceServiceClient;
import com.honghe.recordhibernate.dao.*;
import com.honghe.recordhibernate.entity.Dspecification;
import com.honghe.recordhibernate.entity.Host;
import com.honghe.recordhibernate.entity.Hostgroup;
import com.honghe.recordhibernate.entity.Setting;
import com.honghe.recordweb.service.frontend.device.DeviceService;
import com.honghe.recordweb.service.frontend.device.HongheDeviceService;
import com.honghe.recordweb.service.frontend.devicemanager.HostDevice;
import com.honghe.recordweb.service.frontend.devicemanager.NVR;
import com.honghe.recordweb.service.frontend.devicemanager.NVRCommand;
import com.honghe.recordweb.service.frontend.liveplan.LiveplanService;
import com.honghe.recordweb.service.frontend.news.NewsService;
import com.honghe.recordweb.service.frontend.policy.PolicyService;
import com.honghe.recordweb.service.frontend.programme.ProgrammeService;
import com.honghe.recordweb.service.frontend.ringbell.RingbellService;
import com.honghe.recordweb.service.frontend.settings.SettingsService;
import com.honghe.recordweb.service.frontend.signalplan.SignalplanService;
import com.honghe.recordweb.service.frontend.tempplan.TemporaryplanService;
import com.honghe.recordweb.service.frontend.timeplan.ClasstimeService;
import com.honghe.recordweb.service.frontend.timeplan.TimeplanService;
import com.honghe.recordweb.service.frontend.touchscreen.TouchscreenService;
import com.honghe.recordweb.util.CommonName;
import com.honghe.recordweb.util.HongheDeviceServiceFactory;
import com.honghe.service.client.Result;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by chby on 2014/9/26.
 */
@Service
public class HostgroupService {
    @Resource
    private HostgroupDao hostgroupDao;
    @Resource
    private HostDao hostDao;
    @Resource
    private DeviceDao deviceDao;
    @Resource
    private SpecDao specDao;
    @Resource
    private DeviceService deviceService;
    @Resource
    private SettingsService settingsService;
    @Resource
    private HongheDeviceService hongheDeviceService;
    @Resource
    private PolicyService policyService;
    @Resource
    private ClasstimeService classtimeService;
    @Resource
    private TouchscreenService touchscreenService;
    @Resource
    private ProgrammeService programmeService;
    @Resource
    private SignalplanService signalplanService;
    @Resource
    private RingbellService ringbellService;
    @Resource
    private NewsService newsService;
    @Resource
    private NVR nvr;
    @Resource
    private HostDevice hostDevice;
    @Resource
    private NVRCommand nvrCommand;
    @Resource
    private TimeplanService timeplanService;
    @Resource
    private LiveplanService liveplanService;
    @Resource
    private TemporaryplanService temporaryplanService;
    @Resource
    private DeviceConfigDao deviceConfigDao;
    private final static Logger logger = Logger.getLogger(HostgroupService.class);

    /**
     * 获取所有主机列表
     *
     * @return
     */
    public List<Host> getHostService() {
        try {
            return hostDao.getHostList();
        } catch (Exception e) {
            return Collections.EMPTY_LIST;
        }
    }

    /**
     * 添加一条新的分组数据
     *
     * @param strGroupName String 新增分组名称
     * @return boolean default false,添加成功返回true
     */
    @Transactional
    public boolean addHostgroupService(String strGroupName) {
        boolean resultFlag = false;
        try {
            Hostgroup hostgroup = new Hostgroup();
            hostgroup.setGroupName(strGroupName);
            hostgroupDao.saveHostgroup(hostgroup);
            resultFlag = true; //添加成功
        } catch (Exception e) {
            logger.error("添加分组异常", e);
//            e.printStackTrace();
        }
        return resultFlag;
    }

    /**
     * 修改分组名称
     *
     * @param hostgroup Hostgroup
     * @return boolean default false 更新成功返回true
     */
    @Transactional
    public boolean updateHostgroupService(Hostgroup hostgroup) {
        boolean resultFlag = false;
        try {
            resultFlag = hostgroupDao.updateHostgroup(hostgroup);
        } catch (Exception e) {
            logger.error("修改分组异常", e);
//            e.printStackTrace();
        }
        return resultFlag;
    }

    /**
     * 获取客户端真实ip地址
     *
     * @param request
     * @return
     */
    public String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }

    /**
     * 根据page类方法，返回分页的分组数据（Json格式）
     *
     * @param currentPage int 当前页码
     * @param pageSize    int 每页显示条数
     * @return String json格式的字符串，异常为“”
     */
    @Transactional
    public String groupJSONService(int currentPage, int pageSize) {
        try {
            Page<Hostgroup> page = new Page<Hostgroup>(currentPage, pageSize);
            hostgroupDao.getHostgroupList(page);
            JsonConfig jsonConfig = new JsonConfig();
            String[] strArr = {"hosts", "users"};
            jsonConfig.setExcludes(strArr);
            jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
            JSONArray jsonArray = JSONArray.fromObject(page.getResult(), jsonConfig);
            return jsonArray.toString();
        } catch (Exception e) {
            logger.error("获取分页的分组数据异常", e);
//            e.printStackTrace();
            return "";
        }
    }

    /**
     * 删除分组下关系
     *
     * @param id int 分组id
     * @return boolean true 删除成功，false 删除失败
     */
    @Transactional
    public boolean delGroupRelationService(String id, String hostIdStr) {
        try {
            hostgroupDao.delGroupRelationBySql(Integer.parseInt(id), hostIdStr);
            return true;
        } catch (Exception e) {
            logger.error("删除分组异常", e);
            return false;
        }
    }

    /**
     * 判断某一id的教室是否被分配
     *
     * @param id int 教室（主机）id
     * @return boolean true 表示分配，false表示未分配
     */
    @Transactional
    public boolean hostRelationService(String id) {
        try {
            return hostDao.isHostRelationExsist(Integer.parseInt(id));
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return false;
        }
    }

    /**
     * 获取设备id
     * @param name
     * @return
     */
    public Object[] getSpecId(String name) {
        try {
            return specDao.getSpec(name);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 给分组划分一个教室
     *
     * @param hostId  String 教室（主机）id
     * @param groupId String 分组id
     * @return
     */
    @Transactional
    public boolean addHostRelationService(String hostId, String groupId) {
        try {
            hostDao.addHostToGroup(Integer.parseInt(hostId), Integer.parseInt(groupId));
            return true;
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
    }

    /**
     * 给分组划分一个教室
     *
     * @param hostId     String 教室（主机）id
     * @param groupId    String 分组id
     * @param groupIdOld String 原分组id
     * @return
     */
    @Transactional
    public boolean hostMoveService(String hostId, String groupId, String groupIdOld) {
        try {
            if (groupIdOld == null || groupIdOld.trim().equals("")) {
                //如果该host未分组，则直接添加
                hostDao.addHostToGroup(Integer.parseInt(hostId), Integer.parseInt(groupId));
            } else {
                hostDao.moveHostToNewGroup(Integer.parseInt(hostId), Integer.parseInt(groupId), Integer.parseInt(groupIdOld));
            }
            return true;
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
    }

    /**
     * 获取主机信息
     *
     * @param id int 主机（教室）id;
     * @return Host类型
     * @throws Exception
     */
    public Host getHostInfo(int id) throws Exception {

        Map<String, Object> hostmap = new HashMap<>();
        hostmap = hongheDeviceService.getHostInfoById(String.valueOf(id));
        if (hostmap != null && !hostmap.isEmpty()) {
            Host host = new Host();
//            host.setHostId(Integer.valueOf(hostmap.get("host_id")));
//            Integer hostid = Integer.parseInt(hostmap.get("host_id").toString());
//            System.out.println(hostid);
            host.setHostId(Integer.parseInt(hostmap.get("host_id").toString()));
            host.setHostName(hostmap.get("host_name").toString());
            host.setHostIpaddr(hostmap.get("host_ipaddr").toString());
            host.setHostSerialno(hostmap.get("host_serialno").toString());
            host.setHostDesc(hostmap.get("host_desc").toString());
            host.setHostUserName(hostmap.get("host_username").toString());
            host.setHostPassWord(hostmap.get("host_password").toString());

            return host;
        }
        return null;
    }


    /**
     * 根据id获取主机信息
     *
     * @param ip String 主机（教室）ip;
     * @return Host类型
     * @throws Exception
     */
    public Host getHostInfoByIp(String ip) throws Exception {

        Map<String, Object> hostmap = Collections.emptyMap();


        hostmap = hostDevice.getHostInfoByIp(ip);

        if (hostmap != null && !hostmap.isEmpty()) {
            Host host = new Host();
            Dspecification dspecification = new Dspecification();
            host.setHostId(Integer.valueOf(hostmap.get("host_id").toString()));
            host.setHostName(hostmap.get("host_name").toString());
            host.setHostIpaddr(hostmap.get("host_ipaddr").toString());
            host.setHostSerialno(hostmap.get("host_serialno").toString());
            host.setHostDesc(hostmap.get("host_desc").toString());
            host.setHostUserName(hostmap.get("host_username").toString());
            host.setHostPassWord(hostmap.get("host_password").toString());
            host.setHostMac(hostmap.get("host_mac").toString());
            dspecification.setDspecId(Integer.valueOf(hostmap.get("dspec_id").toString()));
            host.setDspecification(dspecification);
            return host;
        }
        return null;
    }

    /**
     * 通过id获取设备主机信息(由之前向设备服务请求接口获取改为直接请求设备服务数据库表请求)
     * @param hostId
     * @return
     */
    public Map getHostInfoById(String hostId) {
        Map value = new HashMap();
        try {
            value = deviceDao.getHostInfoById(hostId);
            logger.debug("获取设备信息为："+value);
        } catch (Exception e) {
            logger.error("通过设备id获取设备信息异常",e);
        }
        return !value.isEmpty() ? value : null;
    }
//    /**
//     * 通过id获取设备主机信息
//     * @param hostId
//     * @return
//     */
//    public Map getHostInfoById(String hostId) {
//        Map<String, String> params = new HashMap<>();
//        params.put("hostId", hostId);
//        Result res = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_DEVICE, DeviceServiceClient.Method.Device_GetHostInfoById, params);
//        if (res != null && res.getCode() == 0) {
//            Map values = (Map) res.getValue();
//            return !values.isEmpty() ? values : null;
//        } else {
//            return null;
//        }
//    }

    /**
     * 获取未分组设备列表
     *
     * @return
     */
    public Map<String, Object> getUnknowGroup(String type, String pageSize, String currentPage) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("group_id", "-1");
        List<Map> hostList = new ArrayList<Map>();
        String pageCount = "0";
        String itemsCount = "0";
        String str = "";
        try {
            List<Object[]> result = hostDao.getUnknowGroup();
            str = String.valueOf(result.get(0)[0]);
            if (str == "null") {
                str = "";
            }
            Map<String, Object> values = Collections.emptyMap();
            values = hostDevice.getNotHostInfoByPage(type, str, Integer.valueOf(pageSize), Integer.valueOf(currentPage));

            if (!values.isEmpty()) {
                hostList = (List<Map>) values.get("hostInfo");
                pageCount = values.get("pageCount").toString();
                itemsCount = values.get("itemsCount").toString();
            }


        } catch (Exception e) {
            logger.error("", e);
        }
        map.put("host_list", hostList);
        map.put("pageCount", pageCount);
        map.put("itemsCount", itemsCount);
        map.put("group_name", "未分组(" + hostList.size() + ")");
        return map;
    }

    /**
     * 获取未分组设备
     * @param type
     * @return
     */
    public Map<String, Object> getUnknowGroup(String type) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("group_id", "-1");
        List<Map> hostList = new ArrayList<Map>();
        String str = "";
        try {
            List<Object[]> result = hostDao.getUnknowGroup();
            str = String.valueOf(result.get(0)[0]);
            if (str == "null") {
                str = "";
            }
            hostList = hostDevice.getNotHostInfo(type, str);
        } catch (Exception e) {
            logger.error("", e);
        }
        map.put("host_list", hostList);
        map.put("group_name", "未分组(" + hostList.size() + ")");
        return map;
    }

    /**
     * 根据分组id及condition获取符合条件的host列表
     *
     * @param hostgroupId int 分组id
     * @param type        hostRelation 获取该分组下的host列表；hostNoRelation获取未分组的教室列表;""获取所有教室列表
     * @return
     */
    public List<Map> hostListJSON(String hostgroupId, String type) {
        List<Map> re_value = new ArrayList<Map>();
        try {
            String str = "";
            List<Object[]> result = hostDao.getHostListFromSql(Integer.parseInt(hostgroupId));
            if (!result.isEmpty()) {
                for (int i = 0; i < result.size(); i++) {
                    str += String.valueOf(result.get(i)[0]) + ",";
                }
                str = str.substring(0, str.length() - 1);
            }
//            str = String.valueOf(result.get(0)[0]);
            if (!str.equals("null")) {
                Result res = hostDevice.getHostInfo(type, str);
                if (res != null && res.getCode() == 0 && res.getValue() != null) {
                    List<Map> value = ((List<Map>) res.getValue());
                    if (!value.isEmpty()) {
                        re_value = value;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return re_value;
    }

    /**
     * todo 加注释
     * @param type
     * @return
     */
    public List<Map<String, Object>> hostInfoByType(String type) {
        List<Map<String, Object>> re_value = new ArrayList<>();
        try {
            List<Map<String, Object>> list = new ArrayList();
            if (!type.equals("")) {
                list = hongheDeviceService.getHostInfoByType(type);

                for (int i = 0; i < list.size(); i++) {
                    Map<String, Object> map = new HashMap<>();
                    map = list.get(i);
                    String id = list.get(i).get("id").toString();
                    List<Object[]> listobject = this.getGroupInfoByhostId(Integer.parseInt(id));
                    if (listobject != null && listobject.size() > 0) {
                        map.put("hostgroupId", listobject.get(0)[0]);
                        map.put("hostgroupName", listobject.get(0)[1]);
                    } else {
                        map.put("hostgroupId", "");
                        map.put("hostgroupName", "");
                    }
                    re_value.add(map);
                }

            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return re_value;
    }


    /**
     * 根据分组id及condition获取符合条件的host及nas列表
     *
     * @param hostgroupId int 分组id
     * @param condition   hostRelation 获取该分组下的host列表；hostNoRelation获取未分组的教室列表;""获取所有教室列表
     * @return
     */
    public List<Map> hostAndNasListJSON(String hostgroupId, String condition, String userId) {
//        List<Map> values = new ArrayList<Map>();
//        try {
//            List<Object[]> result = nasDao.getHostAndNasListFromSql(Integer.parseInt(_hostgroupId), condition, Integer.parseInt(userId));
//            for (Object[] obj : result) {
//                Map<String, Object> map = new LinkedHashMap<String, Object>();
//                map.put("host_id", obj[0]);
//                map.put("host_name", obj[1]);
//                map.put("host_ipaddr", obj[2]);
//                map.put("nas_id", obj[3]);
//                map.put("nas_rootpath", obj[4]);
//                map.put("nas_filepath", obj[5]);
//                map.put("nas_usrname", obj[6]);
//                map.put("nas_password", obj[7]);
//                String status = "";
//                if (obj[2] != null && !obj[2].toString().equals("")) {
//                    status = isOnLineByIp(obj[2].toString().trim());
//                }
//                map.put("host_status", status);
//                values.add(map);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        // return JSONArray.fromObject(values).toString();
//        return values;
        return null;
    }

    /**
     * 更新教室信息
     *
     * @param host
     * @return
     */
    @Transactional
    public boolean updateHostService(Host host) {
//        computer.get(host.getHostIpaddr()).GetDeviceExtensionInfo().put("host_name", host.getHostName());
//        try {
//            return hostDao.updateHost(host);
//        } catch (Exception e) {
//            return false;
//        }
        return false;
    }

    /**
     * 删除教室信息
     *
     * @param id
     * @return
     */
    @Transactional
    public boolean delHostService(String id, String hostIp) {
        boolean re_value = false;
        try {
            //删除设备中的计划，消息
            //删除定时开关机计划
            policyService.delPolicyByHostService(Integer.parseInt(id));
            //删除定时童锁
            touchscreenService.delTouchByHostService(Integer.parseInt(id));
            //删除定时切换频道
            programmeService.delProgrammeByHostService(Integer.parseInt(id));
            //删除定时切换信号源
            signalplanService.delSignalplanByHostService(Integer.parseInt(id));
            //删除定时打铃
            ringbellService.delRingbellByHostService(Integer.parseInt(id));
            //删除消息
            newsService.delNewsByHostService(Integer.parseInt(id), hostIp);
            //删除录像计划
            int tmpId = Integer.parseInt(id);
            //如果该主机已分配录像计划，则先删除录像计划
            if (timeplanService.isHavePlan(tmpId)) {
                if (!timeplanService.delPlanCommand(hostIp, id)) {
                    return false;
                }
            }
            if (settingsService.getNas2hostMap().containsKey(tmpId)) {
                if (!settingsService.deleteNas2Host(id)) {
                    return false;
                }
                settingsService.initNas();
            }
            //add by xinye
            //删除临时计划和直播计划
            List<Object[]> tempList = temporaryplanService.getTempPlanByHost(Integer.parseInt(id));
            for(int i=0;i<tempList.size();i++){
                temporaryplanService.deletePlan(Integer.parseInt(tempList.get(i)[0].toString()));
            }
            List<Object[]> liveplanList = liveplanService.getLivePlanListByHostId(Integer.parseInt(id));
            for(Object[] obj:liveplanList){
                int flag = Integer.parseInt(obj[1].toString());
                int plan_id =Integer.parseInt(obj[0].toString());
                if(flag==1){
                    Setting setting = settingsService.getCurriculumType();
                    int type = setting.getCurriculumType();
                    List<Object[]> list =liveplanService.getLiveplanList(plan_id,type==1?"term":"week");
                    liveplanService.stopLiveplan(hostIp,list,Integer.parseInt(id));
                    for(int i=0;i<list.size();i++){
                        liveplanService.handleStream(hostIp,Integer.parseInt(id),"stop",list.get(i)[4].toString().split(","));
                    }
                }
                liveplanService.delLiveplan(plan_id);
            }
            //add by xinye end
            if (hostDeviceService(tmpId)) {
                delHostDeviceService(tmpId);
            }
            timeplanService.delete(id);
            re_value = hostDevice.delHostd(id, hostIp);
        } catch (Exception e) {
            logger.error("", e);
        }
        return re_value;
    }


    /**
     * 删除某Host的所有镜头
     *
     * @param id int hostid
     * @return boolean  true删除成功，false失败
     */
    @Transactional
    public boolean delHostDeviceService(int id) {
        boolean re_value = false;
        try {
            hostDao.delHostDeviceBySql(id);
            re_value = true;
        } catch (Exception e) {
            logger.error("删除某Host的所有镜头异常", e);
        }
        return re_value;
    }

    /**
     * 判断某一id的教室是否有镜头
     *
     * @param id int 教室id
     * @return boolean true 表示分配，false表示未分配
     */
    @Transactional
    public boolean hostDeviceService(int id) {
        boolean re_value = false;
        try {
            re_value = hostDao.isHostDeviceExsist(id);
        } catch (Exception e) {
            logger.error("", e);
        }
        return re_value;
    }

    /**
     * 获取用户所在的分组
     *
     * @param userId json 类型数组
     *               [
     *               {"group_id":"","group_name":"","host_list":[{"id":"","name":"",token:"","dspec":","_type":"","status":""}]}
     *               ]
     * @return
     */
    public List<Map> getGroupService(int userId, String type) {

        List<Map> re_value = new ArrayList<Map>();
        try {
            List<Object[]> result = hostgroupDao.getHostgroupList(userId);
            for (Object[] obj : result) {
                Map<String, Object> map = new LinkedHashMap<String, Object>();
                List<Map> hostList = new ArrayList<Map>();
                map.put("group_id", obj[0]);
                map.put("group_name", obj[1]);
                String hostIdStr = obj[2].toString();
                Result res = hostDevice.getHostInfo(type, hostIdStr);

                if (res != null && res.getCode() == 0 && res.getValue() != null) {
                    List<Map> hostlists = ((List<Map>) res.getValue());
                    hostList = hostlists.isEmpty() ? new ArrayList<Map>() : hostlists;
                }
//                Collections.sort(hostList, new Comparator<Map>() {
//                    @Override
//                    public int compare(Map o1, Map o2) {
//                        int id1 = Integer.parseInt(o1.get("id").toString());
//                        int id2 = Integer.parseInt(o2.get("id").toString());
//                        if (id1 > id2) {
//                            return 1;
//                        } else if (id1 < id2) {
//                            return -1;
//                        }
//                        return 0;
//                    }
//                });
                map.put("host_list", hostList);

                re_value.add(map);
            }
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
        }
        return re_value;
    }


    /**
     * 根据用户获取分组tree数据
     *
     * @param userId   String 用户ID
     * @param hostTree String 标志位，=1则要显示分组及班级;否则只显示分组数据
     * @return [
     * {"group_id":"","group_name":"","host_list":[{"id":"","name":"",token:"","dspec":","_type":"","status":""}]}
     * ]
     */
    public List<Map> getHostgroupListNewService(String userId, String hostTree) throws Exception {

        List<Map> re_value = new ArrayList<Map>();
        try {
            int id = Integer.parseInt(userId);
            if (hostTree.equals("1"))//分组与班级结合tree
            {
                List<Object[]> result = hostgroupDao.getHostgroupListNew(id);
                for (Object[] obj : result) {
                    Map map = new LinkedHashMap<>();
                    map.put("group_id", obj[0]);
                    map.put("group_name", obj[1]);
                    String hostIdStr = obj[2] != null ? obj[2].toString() : null;
                    List<Map> hostList = new ArrayList<Map>();
                    Result res = hostDevice.getHostInfo(CommonName.DEVICE_TYPE_ALL, hostIdStr);

                    if (res != null && res.getCode() == 0 && res.getValue() != null) {
                        List<Map> value = ((List<Map>) res.getValue());
                        hostList = re_value.isEmpty() ? new ArrayList<Map>() : value;
                    }
                    map.put("host_list", hostList);
                    map.put("userInfo", this.getUserForHostGroup(obj[0] + ""));
                    re_value.add(map);
                }
            } else //分组tree
            {
                List<Object[]> result = hostgroupDao.getHostgroupList2(id);
                for (Object[] obj : result) {
                    Map<String, Object> map = new LinkedHashMap<String, Object>();
                    map.put("group_id", obj[0]);
                    map.put("group_name", obj[1]);
                    List<Map> hostList = new ArrayList<Map>();
                    map.put("host_list", hostList);
                    re_value.add(map);
                }
            }

        } catch (Exception e) {
            logger.error("", e);
        }
        return re_value;
    }

    /**
     * 根据条件统计HOST，hostgroup符合条件的数量
     *
     * @param type String 条件字符串 type String “host”或者“hostgroup”，统计教室或者分组的数据
     * @return
     */
    public int countService(String type) {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("deviceType", type);
            Result res = HongheDeviceServiceFactory.remoteInvocation(CommonName.METHOD_TYPE_DEVICE, DeviceServiceClient.Method.Device_HostCount, params);
            if (res.getCode() == 0) {
                int value = Integer.parseInt(res.getValue().toString());
                return value;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     *统计分组
     *
     * @param conditions
     * @return
     */
    public int countGroupService(String conditions) {
        int re_value = 0;
        try {
            re_value = hostgroupDao.countHostGroup(conditions);
        } catch (Exception e) {
            logger.error("", e);
        }
        return re_value;
    }

    /**
     * 获取分组列表（List<Hostgroup>）
     * @param userId
     * @param type
     * @return
     */
    public List<Object[]> getHostgroupList2Service(String userId, String type) {
        List<Object[]> re_value = new ArrayList<>();
        try {
            int id = Integer.parseInt(userId);
            List<Object[]> list = hostgroupDao.getHostgroupList2(id);
            for (int i = 0; i < list.size(); i++) {
                int groupId = Integer.parseInt(list.get(i)[0].toString());
                List<Object[]> hostploy = hostgroupDao.getPloyNameById(groupId);
                String ployName = "";
                List<Object[]> hostIds = hostgroupDao.getHostIds(groupId);
                Integer hostCount = 0;
                if (hostIds != null && hostIds.size() > 0 && hostIds.get(0)[0] != null) {
                    String hostIdStr = hostIds.get(0)[0].toString();
                    List<Map> hostInfo = Collections.emptyList();
                    Map map=  hostDevice.getHostInfoByPage(type, hostIdStr, 10000, 1);
                    hostInfo = (List<Map>) map.get("hostInfo");
                    hostCount = hostInfo.size();

                }
                if(hostploy.size() > 0){
                    ployName = hostploy.get(0)[0].toString();
                }
                Object[] objects = list.get(i);
                Object[] newObjects = new Object[5];
                for (int j = 0; j < objects.length; j++) {
                    newObjects[j] = objects[j];
                }
                newObjects[3] = hostCount;
                newObjects[4] = ployName;//录播获取班级作息策略名称
                re_value.add(newObjects);
            }

        } catch (Exception e) {
            logger.error("", e);
        }
        return re_value;
    }


    /**
     * 根据分组ID获取分组信息
     * @param hostgroupId String 分组ID
     * @return
     */
    public Hostgroup getHostgroup(String hostgroupId) {
        Hostgroup re_value = null;
        try {
            re_value = hostgroupDao.getHostgroupInfo(Integer.parseInt(hostgroupId));
        } catch (Exception e) {
            logger.error("", e);
        }
        return re_value;
    }


    /**
     * 根据page类方法，返回分页的分组数据（Json格式）
     *
     * @param _currentPage int 当前页码
     * @param _pageSize    int 每页显示条数
     * @return Map
     */

    public Map hostInGroup(Integer _hostgroupId, String _condition, int _currentPage, int _pageSize, String _type) {
        Map re_values = new HashMap();
        try {
            String hostIds = "";
            if (_hostgroupId == -1) {   //获取未分组设备并分页
                List<Object[]> result = hostDao.getUnknowGroup();
                hostIds = String.valueOf(result.get(0)[0]);
                if (hostIds == "null") {
                    hostIds = "";
                }
                re_values = hostDevice.getNotHostInfoByPage(_type, hostIds, _pageSize, _currentPage);
            } else {
                List<Object[]> result = hostgroupDao.getHostIds(_hostgroupId);
                hostIds = String.valueOf(result.get(0)[0]);
                if (hostIds == "null") {
                    hostIds = "-1";
                }
                re_values = hostDevice.getHostInfoByPage(_type, hostIds, _pageSize, _currentPage);
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return re_values;
    }


    /**
     * 根据page类方法，返回分页的分组数据（Json格式）
     *
     * @param currentPage int 当前页码
     * @param pageSize    int 每页显示条数
     * @return Map
     */

    public Map<String, Object> hostInGroup(String viewClassCameraName, Integer hostgroupId, String condition, int currentPage, int pageSize) {
        Map<String, Object> re_value = Collections.emptyMap();
        try {
            Page<List<Object[]>> page = new Page<List<Object[]>>(currentPage, pageSize);

            Map<String, String> params = new HashMap<>();

            if (condition.equals("hostRelation")) {
                List<Object[]> result1 = hostDao.getHostListFromSqlBypage(hostgroupId, condition, page);
                String hostIdStr = "";
                if (result1 != null && result1.size() > 0) {
                    for (Object[] objects : result1) {
                        if (objects != null && objects.length > 0) {
                            hostIdStr += objects[0].toString() + ",";
                        }
                    }
                    if (hostIdStr.endsWith(",")) {
                        hostIdStr = hostIdStr.substring(0, hostIdStr.length() - 1);
                    }
                }
                if (hostIdStr.length() > 0) {
                    re_value = hostDevice.getHostInfoByPage(CommonName.DEVICE_TYPE_RECOURD, hostIdStr, pageSize, currentPage);
                    if(!re_value.isEmpty()){
                        int pagesss = Integer.parseInt(String.valueOf(re_value.get("itemsCount")));
                        System.out.print(pagesss);
                        page.setTotalPageSize(Integer.parseInt(String.valueOf(re_value.get("itemsCount"))));
                    }else{
                        page.setTotalPageSize(0);
                    }
                    re_value.put("pageCount",page.getTotalPageSize());
                }
            } else {
                List<Object[]> result = hostDao.getHostsInGroup();
                String hostIdStr = "";
                if (!result.isEmpty()) {
                    for (int i = 0; i < result.size(); i++) {
                        hostIdStr += String.valueOf(result.get(i)) + ",";
                    }
                    hostIdStr = hostIdStr.substring(0, hostIdStr.length() - 1);
                }
                re_value = hostDevice.getNotHostInfoByPage(CommonName.DEVICE_TYPE_RECOURD, hostIdStr, pageSize, currentPage);
            }

        } catch (Exception e) {
            logger.error("", e);
        }
        return re_value;
    }

    /**
     * 获取host类型list
     * @return
     */
    public List<Dspecification> getSpecList() {
        List<Dspecification> re_value = null;
        try {
            re_value = (List<Dspecification>) specDao.getSpecList();
        } catch (Exception e) {
            logger.error("获取主机类型列表异常!!!", e);
        }
        return re_value;
    }

    /**
     * 删除用户组
     * @param _hostNumber
     * @param _hostgroupId
     * @return
     */
    @Transactional
    public boolean deleteUserGroup(String _hostNumber, final String _hostgroupId) {
        boolean re_value = true;
        try {
            if (!_hostNumber.equals("0")) {
                hostgroupDao.delGroupWithHostBySql(_hostgroupId);
            }
            if (hostgroupDao.hasHostgroup(Integer.parseInt(_hostgroupId))) {
                hostgroupDao.delGroupWithUserBySql(_hostgroupId, "");
            }
            if(hostgroupDao.hasClasstimePloy(Integer.parseInt(_hostgroupId)))
            {
                hostgroupDao.delGroupWithPloyBySql(_hostgroupId,"");
            }
            hostgroupDao.delHostgroup(Integer.parseInt(_hostgroupId));
        } catch (Exception e) {
            logger.error("", e);
            re_value = false;
        }
        return re_value;
    }

    /**
     * 返回所有分组
     * @return
     */
    public List<Hostgroup> getHostgroupListAllService() {
        List<Hostgroup> re_value = null;
        try {
            re_value = (List<Hostgroup>) hostgroupDao.getHostgroupListAll();
        } catch (Exception e) {
            logger.error(" getHostgroupListAllService failed", e);
        }
        return re_value;
    }

    /**
     * app获取分组
     * @return
     */
    public List<Object[]> appgetallHostgroupList() {
        List<Object[]> re_value = null;
        try {
            re_value = (List<Object[]>) hostgroupDao.getHostgroupList2(0);
        } catch (Exception e) {
            logger.error(" getHostgroupListAllService failed", e);
        }
        return re_value;
    }

    /**
     * Author:Wen
     * Date:2014.11.5
     *
     * @param hostGroupId 根据分组ID 查询对该组有控制权限的user 信息
     */
    public List<Map> getUserForHostGroup(String hostGroupId) throws Exception {
        List<Object[]> info = this.hostgroupDao.getUserForHostGroup(hostGroupId);
        List<Map> result = new ArrayList<Map>();
        for (Object[] obj : info) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId", obj[0]);
            map.put("userName", obj[1]);
            map.put("userPwd", obj[2]);
            map.put("groupId", obj[3]);
            map.put("groupName", obj[4]);
            result.add(map);
        }
        return result;
    }

    /**
     * 按名称精确查找分组信息
     *
     * @param name String 用户名称
     * @return Hostgroup
     */
    @Transactional
    public Hostgroup getHostGroupService(String name) {
        Hostgroup hostgroup = null;
        try {
            hostgroup = hostgroupDao.getHostGroup(name);
        } catch (Exception e) {
            logger.error("", e);
        }
        return hostgroup;
    }

    //todo  要验证接口
    private String getCameraUrl(String ip, String cameraToken) {
//        DeviceServiceClient deviceServiceClient = HongheDeviceServiceFactory.getDeviceServiceClient();
//        Map<String, String> params = new HashMap<>();
//        params.put("ip", ip);
//        params.put("cameraToken", cameraToken);
//        Result result = deviceServiceClient.execute(CommonName.METHOD_TYPE_RECORDCOMMAND, DeviceServiceClient.Method.Device_HhrecCommand_GetCameraUrl, params);
//        String re_value = "";
//        if (result.getCode() == 0 && result.getValue() != null && !"".equals(result.getValue())) {
//            re_value = (String) result.getValue();
//        }
//        return re_value;
        return "";
    }

    /**
     * 通过ip获取主机名称
     * @param hostIp
     * @return
     */
    public String getHostNameByIp(String hostIp) {
        return "";
    }

    //==========================================以下方法是合并录播中HostgroupServiece2.java中独有的方法=================================================


    /**
     * 根据连接参数获取设备配置信息
     *
     * @param connect_param String 连接参数
     * @return List<Object[]>
     */
    public List<Object[]> getDeviceConfigList(String connect_param) {
        List<Object[]> re_value = Collections.emptyList();
        try {
            re_value = deviceConfigDao.getDeviceConfigList(connect_param);
        } catch (Exception e) {
            logger.error("", e);
        }
        return re_value;
    }

    /**
     * 添加主机
     * @param ip
     * @param name
     * @param typeInt
     * @return
     */
    @Transactional
    public int addHost(String ip, String name, String typeInt) {
        int re_value = -1;
        Map<String, Object> host_obj = hostDevice.getHostInfoByIp(ip);
        if (host_obj != null && !host_obj.isEmpty()) return 1; //设备已经存在
        Map<String, Object> host_manual = nvr.discovered(ip, name, typeInt);
        if (host_manual != null && !host_manual.isEmpty()) {
            String hostid = host_manual.get("hostId").toString();
            String hostip = host_manual.get("hostIp").toString();
            if (!hostid.equals("0")) {
                this.addDeviceToHost(hostid, hostip);
            }
            re_value = 0;
        }
        return re_value;
    }

    /**
     * 添加外网设备
     *
     * @param ip
     * @param name
     * @param typeInt
     * @param netUrl
     * @param deviceNames
     * @param mainTokens
     * @param subTokens
     * @param mainTokenStreams
     * @param subTokenStreams
     * @return
     */
    @Transactional
    public int addNetHost(String ip, String name, String typeInt, String netUrl, String deviceNames, String mainTokens, String subTokens, String mainTokenStreams, String subTokenStreams) {
        int re_value = -1;//设备添加失败
        Map<String, Object> host_obj = hostDevice.getHostInfoByIp(ip);
        if (host_obj != null && !host_obj.isEmpty()) return 1; //设备已经存在
        Map<String, Object> host_manual = nvr.addNetHost(ip, name, typeInt, netUrl);
        if (host_manual != null && !host_manual.isEmpty()) {
            String hostid = host_manual.get("hostId").toString();
            logger.debug("手动添加外网设备hostid：" + hostid);
            if (!hostid.equals("0")) {
                this.addNetDeviceToHost(hostid, deviceNames, mainTokens, subTokens, mainTokenStreams, subTokenStreams);
                re_value = 0;//设备添加成功
            }
            logger.debug("手动添加外网设备失败");
        } else {
            logger.debug("手动添加外网设备失败");
        }
        return re_value;
    }


    /**
     * 添加虚拟设备
     *
     * @param hostName   虚拟主机名
     * @param typeInt    类型
     * @param netUrl     镜头码流地址
     * @param cameraName 镜头名称
     * @return
     */
    @Transactional
    public int addVirtualHost(String hostName, String typeInt, String netUrl, String cameraName) {
        int re_value = -1;//设备添加失败

        Boolean hostExists = (Boolean) hostDevice.getHostService(hostName);
        if (hostExists) return 1; //设备已经存在
        Map<String, Object> host_manual = nvr.addVirtualHost(hostName, typeInt, netUrl, cameraName);
        if (host_manual != null && !host_manual.isEmpty()) {
            String hostid = host_manual.get("hostId").toString();
            String hostip = host_manual.get("hostIp").toString();
            logger.debug("手动添加虚拟设备hostid：" + hostid);
            if (!hostid.equals("0")) {
                this.addVirtualDeviceToHost(hostid, hostip);
                re_value = 0;//设备添加成功
            }
            logger.debug("手动添加虚拟设备失败");
        } else {
            logger.debug("手动添加虚拟设备失败");
        }
        return re_value;
    }


    /**
     * 获取主机
     *
     * @param ip
     * @return
     */
    private Map<String, Object> getHost(String ip) {
        Map<String, Object> re_value = Collections.EMPTY_MAP;
        try {
            re_value = hostDevice.getHostInfoByIp(ip);
        } catch (Exception e) {
            logger.error("", e);
        }
        return re_value;
    }

    /**
     * todo 加注释
     * @param hostId
     * @param hostIp
     */
    @Transactional
    public void addDeviceToHost(String hostId, String hostIp) {
        String localModle = nvrCommand.getLocalModel(hostIp);
        List<Object[]> deviceConfigList = settingsService.getDeviceConfigList();
        boolean isConfig = false;
        List<String> mediaToken = null;
        mediaToken = nvrCommand.getAllMediaToken(hostIp);
        if (mediaToken == null) mediaToken = Collections.EMPTY_LIST;

        for (Object[] obj : deviceConfigList) {
            if (obj[0].toString().trim().equals(localModle)) {
                if (!mediaToken.contains(obj[3].toString())) continue;
                isConfig = true;
                String mainStream = nvr.getCameraUrl(hostIp, obj[2].toString());
                String subStream = nvr.getCameraUrl(hostIp, obj[3].toString());
                if (subStream.equals("")) {
                    subStream = mainStream;
                }
                deviceService.addDeviceService(hostId, obj[1].toString(), obj[2].toString(), obj[3].toString(), mainStream, subStream);
            }
        }
    }

    /**
     * todo 加注释
     * @param hostId
     * @param hostIp
     */
    @Transactional
    public void addVirtualDeviceToHost(String hostId, String hostIp) {
        String localModle = nvrCommand.getLocalModel(hostIp);

        boolean isConfig = false;
        List<String> mediaToken = null;
        mediaToken = nvrCommand.getAllMediaToken(hostIp);
        if (mediaToken == null) mediaToken = Collections.EMPTY_LIST;
        for (String str : mediaToken) {
            String mainStream = nvr.getCameraUrl(hostIp, str);
            String subStream = mainStream;
            deviceService.addDeviceService(hostId, str, str, str, mainStream, subStream);
        }
    }


    /**
     * 添加外网设备到host数据库
     *
     * @param hostId
     * @param deviceNames
     * @param mainTokens
     * @param subTokens
     * @param mainTokenStreams
     * @param subTokenStreams
     */
    @Transactional
    public void addNetDeviceToHost(String hostId, String deviceNames, String mainTokens, String subTokens, String mainTokenStreams, String subTokenStreams) {
        String[] deviceName = deviceNames.split(",");
        String[] mainToken = mainTokens.split(",");
        String[] sunToken = subTokens.split(",");
        String[] mainTokenStream = mainTokenStreams.split(",");
        String[] sunTokenStream = subTokenStreams.split(",");
        for (int i = 0; i < deviceName.length; i++) {
            deviceService.addDeviceService(hostId, deviceName[i], mainToken[i], sunToken[i], mainTokenStream[i], sunTokenStream[i]);
        }
    }

    /**
     * todo 加注释
     * @param hostId
     * @return
     */
    public List<Object[]> getGroupInfoByhostId(int hostId) {
        List<Object[]> re_value = new ArrayList<>();
        try {
//            re_value = hostgroupDao.getGroupInfoByhostId(hostId);
            //add by lichong
            re_value = classtimeService.getGroupInfoByHostId(hostId);
        } catch (Exception e) {
            logger.error("", e);
        }
        return re_value;
    }

    private Map<String,String> getAllAreaMap(){
        Map<String,String> areaMap = new HashMap<>();
        try {
            List<Object[]> areaList = hostgroupDao.getAllArea();
            for(Object[] areaObj : areaList){
                String areaId = areaObj[0].toString();
                String areaPId = areaObj[1].toString();
                areaMap.put("areaId",areaId);
                areaMap.put("areaPId",areaPId);
            }
        }catch (Exception e){
            logger.error("获取所有的地点数据解析异常:"+e);
            return null;
        }
        return areaMap;
    }

    private Map<String,String> getAllGroupPloyRel(){
        Map<String,String> relMap = new HashMap<>();
        try {
            List<Object[]> groupPloyRelList = hostgroupDao.getGroupPloyRel();
            for(Object[] rel : groupPloyRelList){
                String groupId = rel[0].toString();
                String polyId = rel[1].toString();
                relMap.put("groupId",groupId);
                relMap.put("polyId",polyId);
            }
        }catch (Exception e){
            logger.error("获取所有的地点数据解析异常:"+e);
            return null;
        }
        return relMap;
    }


    /**
     * 获取平台IP
     *
     * @return
     */
    public String getIp() {
        String ip = "127.0.0.1";
        DeviceServiceClient deviceServiceClient = HongheDeviceServiceFactory.getDeviceServiceClient();
        Map<String, String> params = new HashMap<>();
        Result result = deviceServiceClient.execute("device", DeviceServiceClient.Method.Device_GetIp, params);
        if (result != null && result.getCode() == 0) {
            ip = (String) result.getValue();
        }
        logger.info("获取平台ip地址:" + ip);
        return ip;
    }

    /**
     * 添加设备
     *
     * @param type     设备类型
     * @param userName 用户名
     * @param password 密码
     * @param system   请求对象
     * @return
     */
    public JSONObject addHost(String type, String userName, String password, String system) {
        JSONObject jsonObject = new JSONObject();
        Map values = Collections.emptyMap();
        values = hostDevice.addHost(type, userName, password, system);

        if (!values.isEmpty()) {
            String hostId = values.get("hostId").toString();
            String hostIp = values.get("hostIp").toString();
            if (!hostId.equals("0")) {
                //添加镜头
                nvr.addDevice(hostId, hostIp);
            }
            jsonObject.put("result", values.get("isDevicediscover"));
            jsonObject.put("isMuti", values.get("isMuti"));
        } else {
            jsonObject.put("result", "");
            jsonObject.put("isMuti", "");
        }

        return jsonObject;
    }

    /**
     * 未分组设备信息
     *
     * @param hostIds 设备id
     * @param type    设备类型
     * @return
     */
    public List<Map> getNotHostInfo(String type, String hostIds) {
        return hostDevice.getNotHostInfo(type, hostIds);
    }

    /**
     * 获取主机列表
     * 用于替代timeplanService.hostlist（）（只允许dspec为2，5，16通过），去除了方法中过滤步骤，不对dspec过滤。
     * Author：xinye
     * Date:2016/09/02
     * @param uid
     * @return
     */
    public List<Object[]> hostList(int uid) {
        List<Object[]> hostlist = new ArrayList<>();
        try {
            List<Map<String, Object>> list = new ArrayList<>();
            if (uid == 0) {
                Result hostRes = hostDevice.getHostInfo(CommonName.DEVICE_TYPE_RECOURD, "");
                if (hostRes != null && hostRes.getCode() == 0 && hostRes.getValue() != null) {
                    list = (List<Map<String, Object>>) hostRes.getValue();
                }
            } else {
                String hostIds = "";
                List<Object[]> listO = hostDao.getHostList2(uid);
                if (listO != null && list.size() > 0) {
                    for (Object[] obs : listO) {
                        hostIds += obs[0] + ",";
                    }
                    if (hostIds.endsWith(",")) {
                        hostIds = hostIds.substring(0, hostIds.length() - 1);
                    }
                    Result res = hostDevice.getHostInfo(CommonName.DEVICE_TYPE_RECOURD, hostIds);
                    if (res != null && res.getCode() == 0 && res.getValue() != null) {
                        list = ((List<Map<String, Object>>) res.getValue());
                    }
                } else {
                    hostIds = "#";
                }
            }
            if (list != null && list.size() > 0) {
                for (Map<String, Object> map : list) {
                    Object[] hostInfoArr = new Object[7];
                    if (map != null) {
                        hostInfoArr[0] = map.get("id");
                        hostInfoArr[1] = map.get("name");
                        hostInfoArr[2] = map.get("host_ip");
                        hostInfoArr[3] = map.get("host_desc");
                        hostInfoArr[4] = map.get("host_username");
                        hostInfoArr[5] = map.get("host_password");
                        hostInfoArr[6] = map.get("host_serialno");
                        hostlist.add(hostInfoArr);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("获取主机列表异常", e);
//            e.printStackTrace();
        }
        return hostlist;
    }


    public Map<String, Object> hostInGroup(String viewClassCameraName, String hostIdStr, String condition, int currentPage, int pageSize) {
        Map<String, Object> re_value = Collections.emptyMap();
        try {
            Page<List<Object[]>> page = new Page<List<Object[]>>(currentPage, pageSize);

            Map<String, String> params = new HashMap<>();

           /* if (condition.equals("hostRelation")) {
                List<Object[]> result1 = hostDao.getHostListFromSqlBypage(hostgroupId, condition, page);
                String hostIdStr = "";
                if (result1 != null && result1.size() > 0) {
                    for (Object[] objects : result1) {
                        if (objects != null && objects.length > 0) {
                            hostIdStr += objects[0].toString() + ",";
                        }
                    }
                    if (hostIdStr.endsWith(",")) {
                        hostIdStr = hostIdStr.substring(0, hostIdStr.length() - 1);
                    }
                }*/
            if (hostIdStr.endsWith(",")) {
                hostIdStr = hostIdStr.substring(0, hostIdStr.length() - 1);
            }
            if (hostIdStr.length() > 0) {
                re_value = hostDevice.getHostInfoByPage(CommonName.DEVICE_TYPE_RECOURD, hostIdStr, pageSize, currentPage);
                if(!re_value.isEmpty()){
                    int pagesss = Integer.parseInt(String.valueOf(re_value.get("itemsCount")));
                    System.out.print(pagesss);
                    page.setTotalPageSize(Integer.parseInt(String.valueOf(re_value.get("itemsCount"))));
                }else{
                    page.setTotalPageSize(0);
                }
                re_value.put("pageCount",page.getTotalPageSize());
            }
           /* } else {
                List<Object[]> result = hostDao.getHostsInGroup();
                String hostIdStr = "";
                if (!result.isEmpty()) {
                    for (int i = 0; i < result.size(); i++) {
                        hostIdStr += String.valueOf(result.get(i)) + ",";
                    }
                    hostIdStr = hostIdStr.substring(0, hostIdStr.length() - 1);
                }
                re_value = hostDevice.getNotHostInfoByPage(CommonName.DEVICE_TYPE_RECOURD, hostIdStr, pageSize, currentPage);
            }*/

        } catch (Exception e) {
            logger.error("", e);
        }

        return re_value;
    }
}
