package com.honghe.recordweb.service.frontend.timeplan;

import com.honghe.recordhibernate.dao.ClasstimeDao;
import com.honghe.recordhibernate.dao.HostDao;
import com.honghe.recordhibernate.dao.NasDao;
import com.honghe.recordhibernate.dao.TimeplanDao;
import com.honghe.recordhibernate.entity.*;
import com.honghe.recordweb.service.frontend.device.HongheDeviceService;
import com.honghe.recordweb.service.frontend.devicemanager.HostDevice;
import com.honghe.recordweb.service.frontend.devicemanager.NVR;
import com.honghe.recordweb.service.frontend.devicemanager.NVRCommand;
import com.honghe.recordweb.service.frontend.hostgroup.HostgroupService;
import com.honghe.recordweb.service.frontend.settings.SettingsService;
import com.honghe.recordweb.util.CommonName;
import com.honghe.service.client.Result;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lch on 2014/9/28.
 */
@Service("timeplanService")
public class TimeplanService {
    private final static Logger logger = Logger.getLogger(TimeplanService.class);

    public enum TimePlanType {
        PLAN("plan"), TEMP("temp"), ALL("all");
        String desc;
        TimePlanType(String desc) {
            this.desc = desc;
        }
        @Override
        public String toString() {
            return this.desc;
        }
    }
    @Resource
    HostgroupService hostgroupService;
    @Resource
    HostDevice hostDevice;
    @Resource
    HongheDeviceService hongheDeviceService;
    @Resource
    ClasstimeService classtimeService;
    @Resource
    private TimeplanDao timeplanDao;
    @Resource
    private HostDao hostDao;
    @Resource
    private ClasstimeDao classtimeDao;
    @Resource
    private NVR nvr;
    @Resource
    private NVRCommand nvrCommand;
    @Resource
    private NasDao nasDao;
    @Resource
    private SettingsService settingsService;
    public boolean isHavePlan(int id) {
        try {
            return timeplanDao.isHavePlan(id);
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
    }
    /**
     * 添加新的录像计划数据
     *
     * @param hostid           Integer[] 主机id（班级id）
     * @param classtime_id     Integer[] 上课时间id
     * @param timeplan_week    Byte[] 录像计划的星期
     * @param timeplan_turnoff Byte[] 录像开始前多长时间关机，单位为分钟 默认15分钟
     * @param timeplan_autooff Byte[] 录像结束后是否自动关机 0为否，1为是
     * @param timeplan_turnon  Byte[] 录像开始前多长时间开机，单位为分钟 默认15分钟
     * @return boolean default false,添加成功返回true
     */
    @Transactional
    public boolean addTimeplan(Integer[] hostid, Integer[][] classtime_id, Byte[] timeplan_week, Byte[] timeplan_turnoff, Byte[] timeplan_autooff, Byte[] timeplan_turnon[]) {
        boolean resultFlag = false;
        try {
            Integer j = 0;
            for (int i = 0; i < hostid.length; i++) {
                if (hostid[i] != null) {
                    for (int k = 0; k < classtime_id[i].length; k++) {
                        if (classtime_id[k] != null) {
                            Timeplan timeplan = new Timeplan();
                            // timeplan.setHostId(hostid[i]);
                            // timeplan.setClasstimeId(classtime_id[i][k]);
                            timeplan.setTimeplanWeek(timeplan_week[0]);
                            timeplan.setTimeplanTurnoff(timeplan_turnoff[k]);
                            timeplan.setTimeplanAutooff(timeplan_autooff[k]);
                            timeplan.setTimeplanTurnon(timeplan_autooff[k]);
                            timeplanDao.save(timeplan);
                        } else {
                            j++;
                        }
                    }
                }
            }
            if (j >= hostid.length) {
                resultFlag = false;
            } else {
                resultFlag = true; //添加成功
            }
        } catch (Exception e) {
            logger.error("添加新的录像计划数据异常", e);
//            e.printStackTrace();
        }
        return resultFlag;
    }

    //获取主机列表
    /*public List<Object[]> hostlist(int uid) {
        List hostlist = null;
        try {
            hostlist = hostDao.getHostList2(uid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hostlist;
    }*/
    public List<Object[]> hostlist(int uid) {
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
                List<Object[]> listO = hostDao.getHostListByUserId(uid);
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
                        if (map.get("dspec").toString().equals("2")//精品录播主机
                            || map.get("dspec").toString().equals("5")//简易录播
                            || map.get("dspec").toString().equals("16")//简易录播四机位
                            || map.get("dspec").toString().equals("31")//WBOX录播主机
                            )
                        {
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
            }
        } catch (Exception e) {
            logger.error("获取主机列表异常", e);
//            e.printStackTrace();
        }
        return hostlist;
    }

//    /**
//     * 获取主机录像计划列表
//     *
//     * @param week_id Byte 周几
//     * @return list
//     */
//    public List getHostTimeplan(Byte week_id) {
//        List list = null;
//        try {
//            list = timeplanDao.getHostTimeplan(week_id);
//        } catch (Exception e) {
//            logger.error("获取主机录像计划列表", e);
////            e.printStackTrace();
//        }
//        return list;
//    }
    //添加计划

    /**
     * 添加主机录像计划
     *
     * @param hostId      int 主机id
     * @param sectionId int 上课节次
     * @param week_id     byte 周几
     * @return list
     */
    @Transactional
    public Integer addplan(int hostId, int sectionId, Byte week_id,String dateStr) {
        try {
            Timeplan timeplan = new Timeplan();
            timeplan.setSectionId(sectionId);
            timeplan.setHostId(hostId);
            Setting setting = settingsService.getSetting();
            int curriculumType = Curriculum.CUR_WEEK_TYPE;
            if(setting != null && setting.getCurriculumType() != null)
            {
                curriculumType = setting.getCurriculumType();
            }
            Integer timeplanId;
            if (curriculumType == Curriculum.CUT_DATE_TYPE) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date _date = sdf.parse(dateStr);
                timeplan.setTimeplanDate(_date);
                timeplanId = timeplanDao.getTimeplan(hostId,sectionId,dateStr);
            } else {
                timeplan.setTimeplanWeek(week_id);
                timeplanId = timeplanDao.getTimeplan(hostId,sectionId,week_id);
            }
            if(timeplanId != null && timeplanId != 0)
            {
                return timeplanId;
            }
            else {
                if (timeplanDao.save(timeplan)) {
                    return timeplan.getTimeplanId();
                } else {
                    return 0;
                }
            }
        } catch (Exception e) {
            logger.error("添加主机录像计划异常", e);
            return 0;
        }
    }

    /**
     * 删除计划
     * @param timeplan_id
     * @return
     */
    @Transactional
    public Boolean delplan(int timeplan_id) {
        try {
            if (timeplanDao.delete(timeplan_id)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error("删除计划异常", e);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除计划
     * @param hostId
     * @return
     */
    @Transactional
    public boolean delete(String hostId) {
        try {
            timeplanDao.delPlanByHost(Integer.parseInt(hostId));
            return true;
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }

    }

    /**
     * 清除所有计划
     *
     * @return boolean
     */
    @Transactional
    public Boolean clearplan(int id) {
        try {
            timeplanDao.clearPlan(id);
            return true;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return false;
        }
    }

//    public List<Timeplan> getTimeplanByWeek(Byte weekday) {
//        try {
//            List<Timeplan> timeplanlist = timeplanDao.getTimeplanList(weekday);
//            return timeplanlist;
//        } catch (Exception e) {
//            logger.error("", e);
//            return null;
//        }
//    }


//    //删除计划通知设备
//    public int delPlanCommand(TimePlanType timePlanType, String id, String IP) {
//
//        try {
//            id = "profix:" + timePlanType.toString() + " value:" + id;
//            // String vedioToken = getVedioToken(IP);
//            // nvs.stopRecording(IP, id);
//            return 0;
//        } catch (Exception e) {
//            logger.error("", e);
////            e.printStackTrace();
//            return -1;
//        }
//    }

    //根据id获取host
    /*public Host getHost(int id) {
        try {
            return hostDao.getHostInfo(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }*/


    /**
     * 根据id获取host
     * update by zlj on 2018/04/12
     * @param id
     * @return
     */
    public Map<String, Object> getHost(int id) {
        Map<String, Object> re_value = null;
        try {
            re_value = hongheDeviceService.getHostInfoById(String.valueOf(id));
        } catch (Exception e) {
            logger.error("", e);
        }
        return re_value;
    }


    /**
     * 根据id获取上课时间
     * @param id
     * @return
     */
    public Classtime getClasstime(int id) {
        try {
            return classtimeDao.getInfo(id);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return null;
        }
    }

    /**
     * todo 加注释
     * @return
     */
    public List<Object[]> getTimeplanHostList() {
        List<Object[]> hostlist = new ArrayList<>();
        try {
            //return timeplanDao.getAllPlanHost();
            // List<Object[]> list = timeplanDao.getAllPlanHost();
            List<Integer> list = timeplanDao.getAllPan();
            if (list != null && list.size() > 0) {
                String hostIds = "";
                for (Integer obs : list) {
                    hostIds += obs + ",";
                }
                if (hostIds.endsWith(",")) {
                    hostIds = hostIds.substring(0, hostIds.length() - 1);
                }

                Result res = hostDevice.getHostInfo(CommonName.DEVICE_TYPE_RECOURD, hostIds);

                if (res.getCode() == 0 && res.getValue() != null) {
                    List<Map<String, Object>> hostRes = ((List<Map<String, Object>>) res.getValue());
                    if (hostRes != null && hostRes.size() > 0) {
                        for (Map<String, Object> map : hostRes) {
                            Object[] hostInfoArr = new Object[2];
                            //cancel by xinye
//                            if (map != null) {
                            //cancel by xinye end
                            //add by xinye
                            //添加了对设备类型的判断
                            if(map!=null){
                                String dspec = (String)map.get("dspec");
                                if(dspec.equals("2")||dspec.equals("5")||dspec.equals("16")||dspec.equals("31")) {
                            //add by xinye end
                                    hostInfoArr[1] = map.get("id");
                                    hostInfoArr[0] = map.get("host_ip");
                                    hostlist.add(hostInfoArr);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
        }
        return hostlist;
    }

    /**
     * todo 加注释
     * @param ip
     * @param hostId
     * @return
     */
    @Transactional
    public boolean delPlanCommand(String ip, String hostId) {
        try {
            timeplanDao.delHostPlan(Integer.parseInt(hostId));
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
        return true;
    }

    /*public String delPlanCommand(String ip, int timeplanid) {
        String timeplan_id = String.valueOf(timeplanid);
        RecordTask[] recordTasks = nvrCommand.getAllPlan(ip);
        String res = "no";
        //RecordTask[] recordTask_new = new RecordTask[recordTasks.length-1];
        //recordTask = Arrays.copyOf(recordTask, recordlength + recordlength_old);//扩容
        //System.arraycopy(recordTasks_old, 0, recordTask, recordlength, recordlength_old);
        List<RecordTask> recordTaskslist = new ArrayList<>();
        if (recordTasks != null && recordTasks.length > 0) {

            for (int i = 0; i < recordTasks.length; i++) {
                if (!recordTasks[i].getID().getName().equals(timeplan_id))//去掉要删除的那个
                {

                    recordTaskslist.add(recordTasks[i]);
                    //System.arraycopy(recordTasks_old, 0, recordTask, recordlength, recordlength_old);
//                    for(int j=i;j<recordTasks.length-1;j++)
//                    {
//                        recordTasks[j] = (RecordTask)recordTasks[j+1].clone();
//                    }
                    //recordTaskslist.remove(i);
                }
            }
            recordTasks = recordTaskslist.toArray(new RecordTask[recordTaskslist.size()]);
            int flag = nvrCommand.addPlanCommand(ip, recordTasks);
            if (flag == 1) {
                if (this.delplan(timeplanid)) {
                    res = "OK";
                }
            }
        } else {
            if (this.delplan(timeplanid)) {
                res = "OK";
            }
        }
        return res;
    }
*/

    /**
     * todo 加注释
     * @param hostId
     * @param timeplanid
     * @param ip
     * @return
     */
    @Transactional
    public int delPlanCommand(int hostId, int timeplanid, String ip) {
        int flag = nvrCommand.delPlanCommand(hostId, timeplanid, ip);
        if (flag == 1) {
            if (this.delplan(timeplanid)) {
                flag = 1;
            } else {
                flag = -1;
            }
        }
        return flag;
    }

    /**
     * todo 加注释
     * @param hostid
     * @return
     */
    public Boolean getNasByHostid(String hostid) {
        try {
            Nas nas = nasDao.findNas(hostid);
            if (nas != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error("", e);
//            e.printStackTrace();
            return false;
        }
    }

    /**
     * todo 加注释
     * @return
     */
    public List<Integer> getAllPlan() {
        try {
            return timeplanDao.getAllPan();
        } catch (Exception e) {
            logger.error("", e);
            return Collections.EMPTY_LIST;
        }
    }

    /**
     * todo 加注释
     * @param hostid
     * @param classtime
     * @param week_id
     * @return
     */
    public Integer getTimeplan(int hostid, int classtime, int week_id) {
        try {
            return timeplanDao.getTimeplan(hostid, classtime, week_id);
        } catch (Exception e) {
            logger.error(e);
            return 0;
        }
    }

    /**
     * todo 加注释
     * @param hostid
     * @param classtime
     * @param date
     * @return
     */
    public Integer getTimeplan(int hostid, int classtime, String date) {
        try {
            return timeplanDao.getTimeplan(hostid, classtime, date);
        } catch (Exception e) {
            logger.error(e);
            return 0;
        }
    }
    /**
     * 向录播主机下发7日内录像计划
     * update by zlj on 2018/04/12
     * @param
     * @param
     * @return
     */
    public int setTimePlan(String hostIp,List<Object[]>timePlanList,int hostId,NVRCommand nvrCommand)
    {
        List<Map<String, Object>> recordTasks = new ArrayList<>();
        for(int i=0;i<timePlanList.size();i++)
        {
            Object[] timePlan = timePlanList.get(i);
            Map<String, Object> params = new HashMap<>();
            params.put("hostId", String.valueOf(hostId));
            Map hostmap = hongheDeviceService.getHostInfoById(String.valueOf(hostId));
            String dspecid = hostmap.get("dspec_id").toString();
            if(dspecid.equals("31")) {
                params.put("timeplan_id", Integer.valueOf(timePlan[2].toString())-1);
            }
            else
            {
                params.put("timeplan_id", timePlan[0]);
            }
            params.put("ext", "");
            params.put("week_id", timePlan[1]);
            params.put("startTime", timePlan[4]);
            params.put("stopTime", timePlan[5]);
            params.put("subjectName",timePlan[6]);
            params.put("teacher",timePlan[7]);
            recordTasks.add(params);
        }
        return nvrCommand.setPlanCommand(hostIp, recordTasks);
    }

    @Transactional
    public void delete(String hostId, int week) throws Exception{
        timeplanDao.delPlanByHostAndWeek(hostId, week);
    }

    @Transactional
    public void delete(String hostId, String date) throws Exception{
        timeplanDao.delPlanByHostAndDate(hostId, date);
    }

// **************************************** cancel by lichong ******************************************

//    /**
//     * 根据班级ID获取当前7日内录像计划
//     * @param hostId
//     * @param curDate
//     * @return
//     */
//    public List<Object[]> getTimeplanByHost(int hostId,Date curDate)
//    {
//        List<Object[]> timePlanList = new ArrayList<>();
//        try {
//            Setting setting = settingsService.getSetting();
//            int curriculumType = Curriculum.CUR_WEEK_TYPE;
//            if(setting != null && setting.getCurriculumType() != null)
//            {
//                curriculumType = setting.getCurriculumType();
//            }
//            String type = "week";
//            if(curriculumType == Curriculum.CUT_DATE_TYPE)
//            {
//                type = "term";
//            }
//            timePlanList = timeplanDao.getTimeplanByHost(hostId,type,curDate);
//        }
//        catch (Exception e)
//        {
//            timePlanList = Collections.EMPTY_LIST;
//        }
//        return timePlanList;
//    }


//    /**
//     * 查询指定班级、星期录像计划
//     * @param
//     * @param
//     * @return
//     */
//    public List<Object[]> getTimeplanList(int hostid, int week_id) {
//        try {
//            return timeplanDao.getTimeplanList(hostid, week_id);
//        } catch (Exception e) {
//            logger.error(e);
//            return Collections.emptyList();
//        }
//    }

//    /**
//     * 查询指定班级、日期录像计划
//     * @param
//     * @param
//     * @return
//     */
//    public List<Object[]> getTimeplanList(int hostid, Date date) {
//        try {
//            List<Object[]> curriculums = timeplanDao.getTimeplanList(hostid, date);
//            return curriculums;
//        } catch (Exception e) {
//            logger.error(e);
//            return Collections.emptyList();
//        }
//    }


// *************************************************** edit by lichong ***********************************************

    /**
     * 根据班级ID获取当前7日内录像计划
     * @param hostId
     * @param curDate
     * @return
     */
    public List<Object[]> getTimeplanByHost(int hostId,Date curDate)
    {
        List<Object[]> timePlanList = new ArrayList<>();
        int groupId = -1;
        try {
            Setting setting = settingsService.getSetting();
            List<Object[]> groupList = classtimeService.getGroupInfoByHostId(hostId);
            int curriculumType = Curriculum.CUR_WEEK_TYPE;
            if(setting != null && setting.getCurriculumType() != null)
            {
                curriculumType = setting.getCurriculumType();
            }
            String type = "week";
            if(curriculumType == Curriculum.CUT_DATE_TYPE)
            {
                type = "term";
            }
            if(groupList != null && !groupList.isEmpty()){
                groupId = Integer.valueOf(groupList.get(0)[0].toString());
            }
            timePlanList = timeplanDao.getTimeplanByHost(groupId,hostId,type,curDate);
        }
        catch (Exception e)
        {
            timePlanList = Collections.EMPTY_LIST;
        }
        return timePlanList;
    }

    /**
     * 查询指定班级、日期录像计划
     * @param
     * @param
     * @return
     */
    public List<Object[]> getTimeplanList(int hostid, Date date) {
        int groupId = -1;
        try {
            List<Object[]> groupList = classtimeService.getGroupInfoByHostId(hostid);
            if(groupList!=null && !groupList.isEmpty()){
                groupId = Integer.valueOf(groupList.get(0)[0].toString());
            }
            List<Object[]> curriculums = timeplanDao.getTimeplanList(groupId, hostid, date);
            return curriculums;
        } catch (Exception e) {
            logger.error(e);
            return Collections.emptyList();
        }
    }

    /**
     * 查询指定班级、星期录像计划
     * @param
     * @param
     * @return
     */
    public List<Object[]> getTimeplanList(int hostid, int week_id) {
        List<Object[]> re_value = new ArrayList<>();
        try {
            List<Object[]> groupList = classtimeService.getGroupInfoByHostId(hostid);
            if(groupList!=null && !groupList.isEmpty()){
                int groupId = Integer.valueOf(groupList.get(0)[0].toString());
                re_value = timeplanDao.getTimeplanList(groupId, hostid, week_id);
            }
            return re_value;
        } catch (Exception e) {
            logger.error(e);
            return Collections.emptyList();
        }
    }
}
