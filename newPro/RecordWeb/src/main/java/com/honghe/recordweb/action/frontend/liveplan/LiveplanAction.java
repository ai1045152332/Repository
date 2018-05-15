package com.honghe.recordweb.action.frontend.liveplan;

import com.honghe.recordhibernate.entity.Curriculum;
import com.honghe.recordhibernate.entity.Liveplan;
import com.honghe.recordhibernate.entity.Setting;
import com.honghe.recordhibernate.entity.User;
import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.action.SessionManager;
import com.honghe.recordweb.service.frontend.device.DeviceService;
import com.honghe.recordweb.service.frontend.device.HongheDeviceService;
import com.honghe.recordweb.service.frontend.hostgroup.HostgroupService;
import com.honghe.recordweb.service.frontend.liveplan.LiveplanService;
import com.honghe.recordweb.service.frontend.settings.SettingsService;
import com.honghe.recordweb.service.frontend.timeplan.ClasstimeService;
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
public class LiveplanAction extends BaseAction {
    private final static Logger logger = Logger.getLogger(LiveplanAction.class);
    @Resource
    private LiveplanService liveplanService;
    @Resource
    private ClasstimeService classtimeService;
    @Resource
    private SettingsService settingsService;
    @Resource
    private HostgroupService hostgroupService;
    @Resource
    private HongheDeviceService hongheDeviceService;
    @Resource
    private DeviceService deviceService;

    private List<Object[]> hostlist;//主机列表
    private Byte week_id = 1;
    private String host_id;
    private List<Integer> sectionList;
    private Map<Integer, String> intToUpper;
    private Setting setting;
    private String date;
    private int section_id;
    private String hostIds;
    private String hostIps;
    private Map<Integer, List<Object[]>> liveplanMaps = new HashMap<Integer, List<Object[]>>();
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
    public Byte getWeek_id() {
        return week_id;
    }
    public void setWeek_id(Byte week_id) {
        this.week_id = week_id;
    }
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
    public Map<Integer, List<Object[]>> getLiveplanMaps() {
        return liveplanMaps;
    }
    public void setLiveplanMaps(Map<Integer, List<Object[]>> liveplanMaps) {
        this.liveplanMaps = liveplanMaps;
    }
    public Map<Integer, List<Object[]>> getClasstimeMaps() {
        return classtimeMaps;
    }
    public void setClasstimeMaps(Map<Integer, List<Object[]>> classtimeMaps) {
        this.classtimeMaps = classtimeMaps;
    }
    /**
     * 直播计划列表
     */
    public String liveplan() {
        try {
            intToUpper = classtimeService.intToUpper();
            setting = settingsService.getSetting();
            sectionList = classtimeService.getSectionList();
            if(sectionList.isEmpty())
            {
                return "classtimeploy";
            }
            User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
            Integer uid = user.getUserId();
            if (user.getUser_salt().equals("true")) {
                uid = 0;//管理员
            }
            hostlist = hostgroupService.hostList(uid);//主机列表
            List<Object[]> liveplanList = new ArrayList<>();
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
                if (setting == null || setting.getCurriculumType() == Curriculum.CUR_WEEK_TYPE) {
                    liveplanList = liveplanService.getLiveplanList(hostid, week_id);
                    classtimeList = classtimeService.getClasstimListWithCurriculum(hostid, week_id);
                } else if (setting != null && setting.getCurriculumType() == Curriculum.CUT_DATE_TYPE) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date _date = sdf.parse(date);
                    liveplanList = liveplanService.getLiveplanList(hostid, _date);
                    classtimeList = classtimeService.getClasstimListWithCurriculum(hostid, _date);
                }
                liveplanMaps.put(hostid, liveplanList);
                classtimeMaps.put(hostid, classtimeList);
            }
        }
        catch (Exception e)
        {
            logger.error(e);
        }
        return "liveplan";
    }

    /**
     * 直播计划列表-ajax
     */
    public String ajaxLiveplan() {
        try
        {
            intToUpper = classtimeService.intToUpper();
            setting = settingsService.getSetting();
            User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
            Integer uid = user.getUserId();
            if (user.getUser_salt().equals("true")) {
                uid = 0;
            }
            hostlist = hostgroupService.hostList(uid);//主机列表
            List<Object[]> liveplanList = new ArrayList<>();
            List<Object[]> classtimeList = new ArrayList<>();
            for (int i=0; i<hostlist.size(); i++) {
                Object[] objs = hostlist.get(i);
                int hostid = Integer.parseInt(objs[0].toString());
                if (setting == null || setting.getCurriculumType() == Curriculum.CUR_WEEK_TYPE ) {
                    liveplanList = liveplanService.getLiveplanList(hostid, week_id);
                    classtimeList = classtimeService.getClasstimListWithCurriculum(hostid, week_id);
                } else if (setting != null && setting.getCurriculumType() == Curriculum.CUT_DATE_TYPE  ) {
                    SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
                    Date _date = sdf.parse(date);
                    liveplanList = liveplanService.getLiveplanList(hostid, _date);
                    classtimeList = classtimeService.getClasstimListWithCurriculum(hostid, _date);
                }
                liveplanMaps.put(hostid, liveplanList);
                classtimeMaps.put(hostid,classtimeList);
            }
        }
        catch (Exception e)
        {
            logger.error(e);
        }
        return "liveplanlist";
    }

    /**
     * update by zlj on 2018/04/12
     * 添加直播计划
     */
    public void addLiveplan() {
        Map<String, String> hashmap = new HashMap<String, String>();
        try {
            Integer hostId = Integer.parseInt(this.host_id);
            Byte week_id = this.week_id;
            Map<String, Object> host = hongheDeviceService.getHostInfoById(hostId+"");
            if (host != null && !host.isEmpty()) {
                String ip = host.get("host_ipaddr").toString();
                // 添加直播计划
                Integer liveplan_id = liveplanService.addLiveplan(hostId, section_id, week_id,this.date);
                if(liveplan_id != 0) {
                    hashmap.put("id", liveplan_id.toString());
                    hashmap.put("ip", ip);
                    hashmap.put("msg", "添加成功");
                }
                else
                {
                    hashmap.put("msg", "添加失败");
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
     * 删除直播计划
     */
    public void delLiveplan() {
        Map<String, String> hashmap = new HashMap<String, String>();
        try {
            Integer liveplan_id = 0;
            setting = settingsService.getSetting();
            int curriculumType = Curriculum.CUR_WEEK_TYPE;
            String type="";
            if(setting != null && setting.getCurriculumType() != null)
            {
                curriculumType = setting.getCurriculumType();
            }
            if (setting == null || curriculumType == Curriculum.CUR_WEEK_TYPE) {
                type="week";
                liveplan_id = liveplanService.getLiveplanId(section_id, Integer.parseInt(host_id), week_id);
            } else if (setting != null && curriculumType == Curriculum.CUT_DATE_TYPE) {
                type="term";
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date _date = sdf.parse(date);
                liveplan_id = liveplanService.getLiveplanId(section_id, Integer.parseInt(host_id), _date);
            }
            // 关闭直播间
            String hostIp = liveplanService.getHostIp(host_id);
//            List<Object[]> livePlanList = liveplanService.getLiveplanList(liveplan_id,type); //  原来的
            List<Object[]> livePlanList = liveplanService.getLiveplanList(Integer.parseInt(host_id), liveplan_id,type);  //add by lichong
            liveplanService.stopLiveplan(hostIp, livePlanList , Integer.parseInt(host_id));
            for(int i=0;i<livePlanList.size();i++){
                liveplanService.handleStream(hostIp,Integer.parseInt(host_id),"stop",livePlanList.get(i)[4].toString().split(","));
            }
//            liveplanService.handleStream(hostIp,Integer.parseInt(host_id),"stop",livePlanList.get(0)[4].toString().split(","));
            // 删除直播计划
            boolean res = liveplanService.delLiveplan(liveplan_id);

            if (res) {
                hashmap.put("flag", "true");
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

    public void getTokenAndChannel() {
        JSONObject jsonObj = new JSONObject();
        String channel = null;
        String[] channels = null;

        try {
            setting = settingsService.getSetting();
            int curriculumType = Curriculum.CUR_WEEK_TYPE;
            if(setting != null && setting.getCurriculumType() != null)
            {
                curriculumType = setting.getCurriculumType();
            }
            if (setting == null || curriculumType == Curriculum.CUR_WEEK_TYPE) {
                channel = liveplanService.getChannel(section_id, Integer.parseInt(host_id), week_id);
            } else if (setting != null && curriculumType == Curriculum.CUT_DATE_TYPE) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date _date = sdf.parse(date);
                channel = liveplanService.getChannel(section_id, Integer.parseInt(host_id), _date);
            }

            List<Object[]> deviceList = deviceService.getDeviceListService(Integer.parseInt(host_id));
            List<Object> tokens = new ArrayList<Object>();

            for (int i = 0; i < deviceList.size(); i++) {
                tokens.add(deviceList.get(i)[1]);
            }

            if (channel != null) {
                channels = channel.split(",");
            }

            if (deviceList != null && deviceList.size() != 0) {
                jsonObj.put("flag", true);
                jsonObj.put("tokens", tokens);
                jsonObj.put("channels", channels);
            } else {
                jsonObj.put("flag", false);
                jsonObj.put("tokens", null);
                jsonObj.put("channels", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            jsonObj.put("flag", false);
            jsonObj.put("tokens", null);
            jsonObj.put("channels", null);
        }

        writeJSON(jsonObj.toString());
    }

    /**
     * 修改直播通道
     */
    public void updateChannel() {
        String  channelstr = ServletActionContext.getRequest().getParameter("channels");
        String[] channels = channelstr.split(",");
        JSONObject jsonObj = new JSONObject();

        try {
            setting = settingsService.getSetting();
            int curriculumType = Curriculum.CUR_WEEK_TYPE;
            if (setting != null && setting.getCurriculumType() != null) {
                curriculumType = setting.getCurriculumType();
            }
            List<Object[]> liveplanList = null;
            Liveplan liveplan = null;
            Integer planId = null;
            if (setting == null || curriculumType == Curriculum.CUR_WEEK_TYPE) {
                liveplan = liveplanService.updateChannel(Integer.parseInt(host_id), week_id, section_id, channelstr);
                planId =liveplanService.getLiveplanId(section_id,Integer.parseInt(host_id), week_id);
//                liveplanList = liveplanService.getLiveplanList(planId,"week");    // 原来的
                liveplanList = liveplanService.getLiveplanList(Integer.parseInt(host_id), planId,"week"); // add by lichong
            } else if (setting != null && curriculumType == Curriculum.CUT_DATE_TYPE) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date _date = sdf.parse(date);
                liveplan = liveplanService.updateChannel(Integer.parseInt(host_id), _date, section_id, channelstr);
                planId = liveplanService.getLiveplanId(section_id,Integer.parseInt(host_id),_date);
//                liveplanList = liveplanService.getLiveplanList(planId,"term");  // 原来的
                liveplanList = liveplanService.getLiveplanList(Integer.parseInt(host_id), planId,"term"); // add by lichong
            }
            String hostIp = liveplanService.getHostIp(host_id);
            //// 判断是否需要立即直播
            SimpleDateFormat sdf =   new SimpleDateFormat( "HH:mm" );
            String startTime = liveplanList.get(0)[8].toString();
            String endTime = liveplanList.get(0)[9].toString();
            Date startDate = sdf.parse(startTime);
            Date endDate = sdf.parse(endTime);
            Calendar cal = Calendar.getInstance();
            Date now = sdf.parse(sdf.format(cal.getTime()));
            boolean excuteNow = now.before(endDate) && now.after(startDate);
            if(excuteNow){
                //// 判断是需要新建还是关闭
                boolean isSend = liveplanService.startLiveplan(hostIp,liveplanList,Integer.parseInt(host_id));
                if(!isSend){
                    throw new Exception("开启直播间失败（没有设置直播地址时会导致异常）");
                }
                if(liveplan==null){
                    liveplanService.handleStream(hostIp,Integer.parseInt(host_id),"start",channels);
                }else{
                    String[] channel_old = liveplan.getLiveplanChannel().split(",");
                    List<String> oldData = new ArrayList<>();
                    for(String str:channel_old){
                        oldData.add(str);
                    }
                    List<String> newData = new ArrayList<>();
                    for(String str:channels){
                        newData.add(str);
                    }
                    List<String> common = new ArrayList<>();
                    for(String str:channels){
                        common.add(str);
                    }
                    common.retainAll(oldData);
                    newData.removeAll(common);//筛选出所有需要新建的通道
                    if(newData.size()!=0){
                        String[] cc = new String[newData.size()];
                        liveplanService.handleStream(hostIp,Integer.parseInt(host_id),"start",newData.toArray(cc));
                    }
                    oldData.removeAll(common);//筛选出所有需要关闭的通道
                   if(oldData.size()!=0){
                       String[] cc = new String[oldData.size()];
                       liveplanService.handleStream(hostIp,Integer.parseInt(host_id),"stop",oldData.toArray(cc));
                   }
                }
            }
            jsonObj.put("flag", true);
            jsonObj.put("msg", "分配成功");
        } catch (Exception e) {
            logger.error(e);
            jsonObj.put("flag", false);
            jsonObj.put("msg", "分配失败");
        }

        writeJSON(jsonObj.toString());
    }

    /**
     * 清空直播计划
     */
    public void clearplan() {

        JSONObject jsonObj = new JSONObject();
        try {
            Calendar calendar = Calendar.getInstance();
            List<Object[]> liveplanHostList = liveplanService.getLiveplanHostList();
            if(!liveplanHostList.isEmpty())
            {
                for (int i = 0; i < liveplanHostList.size(); i++) {
                    int hostId = Integer.parseInt(liveplanHostList.get(i)[1].toString());
                    String hostIp = liveplanHostList.get(i)[0].toString();
                    List<Object[]> livePlanList = liveplanService.getLiveplanEndByHost(hostId, calendar.getTime(),0);
                    if (!livePlanList.isEmpty()) {
                        boolean flag = liveplanService.stopLiveplan(hostIp, livePlanList, hostId);
                        for(int j=0;j<livePlanList.size();j++){
                            liveplanService.handleStream(hostIp,hostId,"stop",livePlanList.get(j)[4].toString().split(","));
                        }
                    }
                }
            }
            liveplanService.clearplan();
            jsonObj.put("flag", true);
            jsonObj.put("msg", "清空成功");
        } catch (Exception e) {
            jsonObj.put("msg", "清空失败");
        }

        writeJSON(jsonObj.toString());
    }
}
