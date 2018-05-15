package com.honghe.recordweb.action.frontend.timeplan;

import com.alibaba.fastjson.JSON;
import com.honghe.recordhibernate.entity.Curriculum;
import com.honghe.recordhibernate.entity.Setting;
import com.honghe.recordhibernate.entity.User;
import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.action.SessionManager;
import com.honghe.recordweb.service.frontend.devicemanager.HostDevice;
import com.honghe.recordweb.service.frontend.devicemanager.NVR;
import com.honghe.recordweb.service.frontend.devicemanager.NVRCommand;
import com.honghe.recordweb.service.frontend.settings.SettingsService;
import com.honghe.recordweb.service.frontend.timeplan.ClasstimeService;
import com.honghe.recordweb.service.frontend.timeplan.TimeplanService;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Scope(value = "Prototype")
public class TimeplanAction extends BaseAction {
    private final static Logger logger = Logger.getLogger(TimeplanAction.class);
    @Resource
    private TimeplanService timeplanService;
    @Resource
    private ClasstimeService classtimeService;
    @Resource
    private NVR nvr;
    @Resource
    private HostDevice hostDevice;
    @Resource
    private NVRCommand nvrCommand;
    @Resource
    private SettingsService settingsService;
    private List<Object[]> hostlist;//主机列表
//    private List classtimelist;//上课时间列表
//    private List hostTimplanList;//主机录像计划列表
    private Byte week_id = 1;
    private String host_id;
    private List<Integer> sectionList;
    private Map<Integer, String> intToUpper;
    private Setting setting;
    private String date;
//    private int classtime_id;
    private int section_id;
    private String hostIds;
    private String hostIps;
    private Map<Integer, List<Object[]>> timeplanMaps = new HashMap<Integer, List<Object[]>>();
    private Map<Integer, List<Object[]>> classtimeMaps = new HashMap<Integer, List<Object[]>>();

    public List<Integer> getSectionList() {
        return sectionList;
    }
    public void setSectionList(List<Integer> sectionList) {
        this.sectionList = sectionList;
    }
    public String getHost_id() {
        return host_id;
    }
    public void setHost_id(String host_id) {
        this.host_id = host_id;
    }
//    private List timeplanList;//录像计划列表
    public Map<Integer, String> getIntToUpper() {
        return intToUpper;
    }
    public void setIntToUpper(Map<Integer, String> intToUpper) {
        this.intToUpper = intToUpper;
    }
    public List<Object[]> getHostlist() {
        return hostlist;
    }
    public void setHostlist(List<Object[]> hostlist) {
        this.hostlist = hostlist;
    }
//    public List getClasstimelist() {
//        return classtimelist;
//    }
//    public void setClasstimelist(List classtimelist) {
//        this.classtimelist = classtimelist;
//    }
//    public List getHostTimplanList() {
//        return hostTimplanList;
//    }
//    public void setHostTimplanList(List hostClasstimeList) {
//        this.hostTimplanList = hostClasstimeList;
//    }
    public Byte getWeek_id() {
        return week_id;
    }
    public void setWeek_id(Byte week_id) {
        this.week_id = week_id;
    }
//    public List<TimeplanDao> getTimeplanList() {
//        return timeplanList;
//    }
//    public void setTimeplanList(List<TimeplanDao> timeplanList) {
//        this.timeplanList = timeplanList;
//    }
    public Setting getSetting() {
        return setting;
    }
    public void setSetting(Setting setting) {
        this.setting = setting;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
//    public int getClasstime_id() {
//        return classtime_id;
//    }
//    public void setClasstime_id(int classtime_id) {
//        this.classtime_id = classtime_id;
//    }
    public int getSection_id() {
        return section_id;
    }
    public void setSection_id(int section_id) {
        this.section_id = section_id;
    }
    public String getHostIds() {
        return hostIds;
    }
    public void setHostIds(String hostIds) {
        this.hostIds = hostIds;
    }
    public String getHostIps() {
        return hostIps;
    }
    public void setHostIps(String hostIps) {
        this.hostIps = hostIps;
    }
    public Map<Integer, List<Object[]>> getTimeplanMaps() {
        return timeplanMaps;
    }
    public void setTimeplanMaps(Map<Integer, List<Object[]>> timeplanMaps) {
        this.timeplanMaps = timeplanMaps;
    }
    public Map<Integer, List<Object[]>> getClasstimeMaps() {
        return classtimeMaps;
    }
    public void setClasstimeMaps(Map<Integer, List<Object[]>> classtimeMaps) {
        this.classtimeMaps = classtimeMaps;
    }
    public String hostids;

    public String getHostids() {
        return hostids;
    }

    public void setHostids(String hostids) {
        this.hostids = hostids;
    }

    // 取消录像计划模式
    public void cancelModel() {
        List<String> hostList = JSON.parseArray(hostids, String.class);
        for (String host : hostList) {
            Map<String, Object> hostObj = timeplanService.getHost(Integer.parseInt(host));
            String ip = hostObj.get("host_ipaddr").toString();
            String factory = hostObj.get("host_factory").toString();
            if ("honghe-wbox".equals(factory)) {
                nvrCommand.setRecordInfo(ip, "RecordType", "0", "SetRecordType");
            }
        }
    }

    // 设置录像计划模式
    public void setModel() {
        List<String> hostList = JSON.parseArray(hostids, String.class);
        for (String host : hostList) {
            Map<String, Object> hostObj = timeplanService.getHost(Integer.parseInt(host));
            String ip = hostObj.get("host_ipaddr").toString();
            String factory = hostObj.get("host_factory").toString();
            if ("honghe-wbox".equals(factory)) {
                nvrCommand.setRecordInfo(ip, "RecordType", "1", "SetRecordType");
            }
        }
    }


    /**
     * 计划列表
     */
    public String plan() {
        try {
            intToUpper = classtimeService.intToUpper();
            setting = settingsService.getSetting();
            int curriculumType = Curriculum.CUR_WEEK_TYPE;
            if(setting != null && setting.getCurriculumType() != null)
            {
                curriculumType = setting.getCurriculumType();
            }
            sectionList = classtimeService.getSectionList();
            if(sectionList.isEmpty())
            {
                return "classtimeploy";
            }
            User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
            Integer uid = user.getUserId();
            if (user.getUser_salt().equals("true")) {
                uid = 0;
            }
            hostlist = timeplanService.hostlist(uid);//主机列表
            List<Object[]> timeplanList = new ArrayList<>();
            List<Object[]> classtimeList = new ArrayList<>();
            Calendar calendar = Calendar.getInstance();
            week_id = Byte.parseByte(String.valueOf(calendar.get(Calendar.DAY_OF_WEEK) - 1));
            if (week_id == 0) {
                week_id = 7;
            }
            date  = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            for (int i=0; i<hostlist.size(); i++) {
                Object[] objs = hostlist.get(i);
                int hostid = Integer.parseInt(objs[0].toString());
                if (curriculumType == Curriculum.CUR_WEEK_TYPE) {
                    timeplanList = timeplanService.getTimeplanList(hostid, week_id);
                    classtimeList = classtimeService.getClasstimListWithCurriculum(hostid, week_id);
                }
                else
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date _date = sdf.parse(date);
                    timeplanList = timeplanService.getTimeplanList(hostid, _date);
                    classtimeList = classtimeService.getClasstimListWithCurriculum(hostid, _date);
                }
                timeplanMaps.put(hostid, timeplanList);
                classtimeMaps.put(hostid, classtimeList);
            }
        }
        catch (Exception e) {
            logger.error(e);
        }
        return "timeplan";
    }
    /**
     * 计划列表-ajax
     */
    public String ajaxplan() {
        try
        {
            intToUpper = classtimeService.intToUpper();
            setting = settingsService.getSetting();
            int curriculumType = Curriculum.CUR_WEEK_TYPE;
            if(setting != null && setting.getCurriculumType() != null)
            {
                curriculumType = setting.getCurriculumType();
            }
            User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
            Integer uid = user.getUserId();
            if (user.getUser_salt().equals("true")) {
                uid = 0;
            }
            hostlist = timeplanService.hostlist(uid);//主机列表
            List<Object[]> timeplanList = new ArrayList<>();
            List<Object[]> classtimeList = new ArrayList<>();
            for (int i=0; i<hostlist.size(); i++) {
                Object[] objs = hostlist.get(i);
                int hostid = Integer.parseInt(objs[0].toString());
                if (curriculumType == Curriculum.CUR_WEEK_TYPE) {
                    timeplanList = timeplanService.getTimeplanList(hostid, week_id);
                    classtimeList = classtimeService.getClasstimListWithCurriculum(hostid, week_id);
                }
                else {
                    SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
                    Date _date = sdf.parse(date);
                    timeplanList = timeplanService.getTimeplanList(hostid, _date);
                    classtimeList = classtimeService.getClasstimListWithCurriculum(hostid, _date);
                }
                timeplanMaps.put(hostid, timeplanList);
                classtimeMaps.put(hostid,classtimeList);
            }
        }
        catch (Exception e)
        {
            logger.error(e);
        }
        return "timeplanlist";
    }
    /**
     * 添加某录像计划
     */
    public void addplan() {
        Map<String, String> hashmap = new HashMap<String, String>();
        try {
            Integer hostId = Integer.parseInt(this.host_id);
            Byte week_id = this.week_id;
            boolean isSetNas = settingsService.hasSettingNas(hostId);
            Map<String, Object> host = timeplanService.getHost(hostId);
            //add by xinye
            //只要不是HL-ZF0400的设备都放行
            isSetNas = host.get("dspec_id").equals("5")?isSetNas:true;
            //add by xinye end
            if (host != null && !host.isEmpty()) {
                String ip = host.get("host_ipaddr").toString();
                if (isSetNas || host.get("host_desc").equals("1")) {
                    Integer timeplan_id = timeplanService.addplan(hostId, section_id, week_id,this.date);
                    if(timeplan_id != 0) {
                        hashmap.put("id", timeplan_id.toString());
                        hashmap.put("ip", ip);
                        hashmap.put("msg", "添加成功");
                    }
                    else
                    {
                        hashmap.put("msg", "添加失败");
                    }
                } else {
                    hashmap.put("msg", "nas未设置");
                }
            } else {
                hashmap.put("msg", "未找到设备");
            }
        } catch (Exception e) {
            logger.error("", e);
            hashmap.put("msg", "添加失败");
        }
        writeJSON(JSONSerializer.toJSON(hashmap).toString());
    }

    /**
     * 添加复制录像计划
     */
    public void addcopyplan() {

        JSONObject jsonObject = new JSONObject();

        setting = settingsService.getSetting();
        int curriculumType = Curriculum.CUR_WEEK_TYPE;
        if(setting != null && setting.getCurriculumType() != null)
        {
            curriculumType = setting.getCurriculumType();
        }


        String hostid = ServletActionContext.getRequest().getParameter("hostids");
        String[] hostids = hostid.split("/");
        List<Integer> timeplans = new ArrayList<>();
        try {
            for (String id : hostids) {
                String[] newHostids = id.split(",");
                if (curriculumType == Curriculum.CUR_WEEK_TYPE) {
                    timeplanService.delete(newHostids[0], week_id);
                } else {
                    timeplanService.delete(newHostids[0], date);
                }

                for (int i = 1; i < newHostids.length; i++) {
                    Integer timeplan_id = timeplanService.addplan(Integer.parseInt(newHostids[0]), Integer.parseInt(newHostids[i]), week_id, this.date);
                    timeplans.add(timeplan_id);
                }
            }
            jsonObject.put("msg", "添加成功");
            jsonObject.put("timeplans", timeplans);
        } catch (Exception e) {
            logger.error(e);
            jsonObject.put("msg", "添加失败");
        }

        writeJSON(jsonObject.toString());


        /*List<Object[]> timeplanList = new ArrayList<Object[]>();
        setting = settingsService.getSetting();
        int curriculumType = Curriculum.CUR_WEEK_TYPE;
        if(setting != null && setting.getCurriculumType() != null)
        {
            curriculumType = setting.getCurriculumType();
        }

        try {
            if (curriculumType == Curriculum.CUR_WEEK_TYPE) {
                timeplanList = timeplanService.getTimeplanList(Integer.parseInt(hostids[0]), week_id);
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date _date = sdf.parse(date);
                timeplanList = timeplanService.getTimeplanList(Integer.parseInt(hostids[0]), _date);
            }
            for (int i=1; i<hostids.length; i++) {
                Byte week_id = this.week_id;
                Map<String, Object> host = timeplanService.getHost(Integer.parseInt(hostids[i]));
                if (host != null && !host.isEmpty()) {
                    Integer timeplan_id = 0;
                    timeplanService.delete(hostids[i].toString(),curriculumType);
                    for (int j = 0; j < timeplanList.size(); j++) {
                        timeplan_id = timeplanService.addplan(Integer.parseInt(hostids[i]), Integer.parseInt(timeplanList.get(j)[3].toString()), week_id, this.date);

                        if(timeplan_id != 0) {
                            timeplans.add(timeplan_id);

                            //hashmap.put("msg", "添加成功");
                        }
                        *//*else
                        {
                            hashmap.put("msg", "添加失败");
                        }*//*
                        jsonObject.put("msg", "添加成功");
                        jsonObject.put("timeplans", timeplans);
                    }
                } else {
                    jsonObject.put("msg", "未找到设备");
                }
            }
        } catch (Exception e) {
            logger.error("", e);
            jsonObject.put("msg", "添加失败");
        }

        writeJSON(jsonObject.toString());*/
    }
    /**
     * todo 加注释
     */
    public void findNas() {
        Map<String, String> hashmap = new HashMap<String, String>();
        String[] hostid = ServletActionContext.getRequest().getParameterValues("hostids");
        String[] hostids = hostid[0].split(",");

        try {
            for (String hostId : hostids) {
                boolean isSetNas = settingsService.hasSettingNas(Integer.parseInt(hostId));
                Map<String, Object> host = timeplanService.getHost(Integer.parseInt(hostId));
                //add by xinye
                //只要不是HL-ZF0400的设备都放行
                isSetNas = host.get("dspec_id").equals("5")?isSetNas:true;
                //add by xinye end
                if (host != null && !host.isEmpty()) {
                    // String ip = host.get("host_ipaddr").toString();
                    String hostName = host.get("host_name").toString();
                    if (isSetNas == false) {
                        hashmap.put("msg", "nas未设置");
                        break;
                    } else {
                        hashmap.put("msg", "nas设置成功");
                    }
                } else {
                    hashmap.put("msg", "未找到设备");
                }
            }

        } catch (Exception e) {
            logger.error("", e);
            hashmap.put("msg", "添加失败");
        }

        writeJSON(JSONSerializer.toJSON(hashmap).toString());
    }

    /**
     * 删除计划
     */
    public void delplan() {
    Map<String, String> hashmap = new HashMap<String, String>();
    try {
        Integer timeplan_id = Integer.parseInt(ServletActionContext.getRequest().getParameter("timeplan_id"));
        boolean res = timeplanService.delplan(timeplan_id);
        if (res) {
            hashmap.put("msg", "删除成功");
        } else {
            hashmap.put("msg", "删除失败");
        }
    } catch (Exception e) {
        logger.error("", e);
        hashmap.put("msg", "删除失败");
    }
    writeJSON(JSONSerializer.toJSON(hashmap).toString());
}
    /**
    * 清空录像计划
     * update by zlj on 2018/04/12
    */
    //add by xinye
    //清空录像计划时，离线设备也会清除数据库中数据
    public void clearplan() {
        Map<String, String> hashmap = new HashMap<String, String>();
        try {
            //获取所有有计划的主机
            List<String> failList = new ArrayList<String>();
            List<String> offLineList = new ArrayList<String>();
            List<String> failOffList = new ArrayList<String>();
            List<Object[]> timeplanHostList = timeplanService.getTimeplanHostList();
            if (timeplanHostList != null && timeplanHostList.size() > 0) {
                List<String> list = new ArrayList<String>();
                StringBuilder ipStr = new StringBuilder();
                Map<String,String> statusMap = getDeviceStatus(timeplanHostList,ipStr);
                int res = 0;
                for (int i = 0; i < timeplanHostList.size(); i++) {
                    int hostid = Integer.parseInt(timeplanHostList.get(i)[1].toString());
                    String ip = timeplanHostList.get(i)[0].toString();
                    boolean isOnline = statusMap.get(ip).equals("Online")?true:false;
                    if (!isOnline) {
                        list.add(ip);
                        if (!timeplanService.clearplan(hostid)) {
                            res = 1;
                            failOffList.add(ip);//清除失败离线ip集合
                        } else{
                            offLineList.add(ip);//清除成功离线ip集合
                        }
                    } else {
                        boolean flag = nvrCommand.delAllPlanCommand(hostid, ip);
                        if (flag == true) {
                            if (!timeplanService.clearplan(hostid)) {
                                res = 1;
                                failList.add(ip);//清除失败在线ip集合
                            }
                        } else {
                            res = -1;
                        }
                    }
                }
                if (list.size() <= 0 && res == 0 && failList.size()<=0) {
                    hashmap.put("msg", "清空成功");
                } else if (list.size() > 0 && res == 0) {
                    hashmap.put("msg", "清除成功，有设备离线");
                } else if (res != 0) {
                    hashmap.put("msg", "清空失败");
                } else {
                    hashmap.put("msg", "清空失败");
                }
            } else {
                hashmap.put("msg", "清空成功");
            }
            StringBuffer msgList = new StringBuffer();
            if(failOffList.size()>0){
                for(String ip:failOffList){
                    msgList.append("离线设备清除失败：");
                    msgList.append(ip);
                    msgList.append("</br>");
                }
            }
            if(offLineList.size()>0){
                for(String ip:offLineList){
                    msgList.append("离线设备清除成功：");
                    msgList.append(ip);
                    msgList.append("</br>");
                }
            }
            if(failList.size()>0){
                for(String ip:failList){
                    msgList.append("在线设备清除失败:");
                    msgList.append(ip);
                    msgList.append("</br>");
                }
            }
            hashmap.put("msgList",msgList.toString());
        } catch (Exception e) {
            logger.error("", e);
            hashmap.put("msg", "清空失败");
        }
        writeJSON(JSONSerializer.toJSON(hashmap).toString());
    }
    //add by xinye end

    /**
     * todo 加注释
     */
    public void clearplan1() {
        Map<String, String> hashmap = new HashMap<String, String>();
        try {
            //获取所有有计划的主机
            List<Object[]> timeplanHostList = (List<Object[]>) timeplanService.getTimeplanHostList();
            if (timeplanHostList != null && timeplanHostList.size() > 0) {
                List<String> list = new ArrayList<String>();
                int res = 0;
                for (int i = 0; i < timeplanHostList.size(); i++) {
                    int hostid = Integer.parseInt(timeplanHostList.get(i)[1].toString());
                    String ip = timeplanHostList.get(i)[0].toString();
                    boolean isOnlie = nvr.isOnline(ip);
                    if (!isOnlie) {
                        list.add(ip);
                    } else {
                        boolean flag = nvrCommand.delAllPlanCommand(hostid, ip);
                        if (flag == true) {
                            if (timeplanService.clearplan(hostid)) {
                                res = 1;
                            } else {
                                res = 0;
                            }
                        } else {
                            res = -1;
                        }
                    }
                }
                if (list.size() <= 0 && res == 1) {
                    hashmap.put("msg", "清空成功");
                } else if (list.size() > 0 && res == 1) {
                    hashmap.put("msg", "部分删除，有设备离线");
                } else if (res != 1) {
                    hashmap.put("msg", "清空失败");
                } else {
                    hashmap.put("msg", "清空失败");
                }
            } else {
                hashmap.put("msg", "清空成功");
            }
        } catch (Exception e) {
            logger.error("", e);
            hashmap.put("msg", "清空失败");
        }
        writeJSON(JSONSerializer.toJSON(hashmap).toString());
    }
    /**
     * update by zlj on 2018/04/12
     * 像录播主机下发录像计划
     */
    public void setTimeplan(){
        if(hostIds != null && !hostIds.equals(""))
        {
            Calendar calendar = Calendar.getInstance();
            String[] hostIdArray = hostIds.split(",");
            String[] hostIpArray = hostIps.split(",");
            String msg = "";
            Map<String,String> statusMap =new HashMap<>();
            //批量查询设备在离线状态
            List<Map<String, String>> deviceStatus = hostDevice.getDevicesStatus(hostIps);
            //将设备的状态信息存入map中，key为ip，value为状态
            for (Map<String,String> status :deviceStatus){
                statusMap.put(status.get("ip"),status.get("deviceStatus"));
            }
            for(int i = 0;i<hostIdArray.length;i++) {
                int hostId = Integer.parseInt(hostIdArray[i]);
                String hostIp = hostIpArray[i];
                boolean isOnline = statusMap.get(hostIp).equals("Online")?true:false;
                if (isOnline) {
                    List<Object[]> timePlanList = timeplanService.getTimeplanByHost(hostId, calendar.getTime());
                    if (!timePlanList.isEmpty()) {
                        int flag = timeplanService.setTimePlan(hostIp, timePlanList, hostId, nvrCommand);
                        if(flag != 1) {
                            msg += hostIp+"：下发录像计划失败；<br />";
                        }
                    }
                    //add by xinye
                    //清空设备上的计划
                    else{
                        nvrCommand.delAllPlanCommand(hostId, hostIp);
                    }
                    //add by xinye end
                }
                else
                {
                    msg += hostIp+"离线未下发计划，当主机上线时会重新下发；<br />";
                }
            }
            Map<String, String> hashmap = new HashMap<String, String>();
            hashmap.put("msg",msg);
            writeJSON(JSONSerializer.toJSON(hashmap).toString());
        }
    }

    /**
     * add by zlj on 2018/04/12
     * 批量获取设备的在离线状态
     * @param timeplanHostList
     * @param ipStr
     * @return
     */
    private Map<String,String> getDeviceStatus(List<Object[]> timeplanHostList,StringBuilder ipStr){
        Map<String,String> statusMap = new HashMap<>();

        //获取timeplanHostList中的ip，并将ip拼成串，用逗号隔开
        for (int i = 0; i < timeplanHostList.size(); i++) {
            String ip = timeplanHostList.get(i)[0].toString();
            ipStr.append(ip);
            ipStr.append(",");
        }
        //去掉最后多余的逗号
        if (ipStr.length()>0&&ipStr.charAt(ipStr.length()-1)==','){
            ipStr = ipStr.deleteCharAt(ipStr.length()-1);
        }
        //批量查询设备在离线状态
        List<Map<String, String>> deviceStatus = hostDevice.getDevicesStatus(ipStr.toString());
        //将设备的状态信息存入map中，key为ip，value为状态
        for (Map<String,String> status :deviceStatus){
            statusMap.put(status.get("ip"),status.get("deviceStatus"));
        }
        return statusMap;
    }

}
