package com.honghe.recordweb.service.frontend.timeplan;

import com.honghe.recordhibernate.dao.ClasstimeDao;
import com.honghe.recordhibernate.dao.ClasstimePloyDao;
import com.honghe.recordhibernate.dao.HostgroupDao;
import com.honghe.recordhibernate.dao.TimeplanDao;
import com.honghe.recordhibernate.entity.Classtime;
import com.honghe.recordhibernate.entity.ClasstimePloy;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import java.util.*;
@Service
public class ClasstimeService {

    private final static Logger logger = Logger.getLogger(ClasstimeService.class);
    @Resource
    private ClasstimeDao classtimeDao;//Sping来管理对象
    @Resource
    private TimeplanDao timeplanDao;
    @Resource
    private HostgroupDao hostgroupDao;
    @Resource
    private ClasstimePloyDao classtimePloyDao;


    /**
     * 获取上课时间列表
     * @return
     */
    public List<Classtime> classtimeList() {
        try {
            List list = null;
            //ClasstimeDao classtime = new ClasstimeDaoImpl();
            list = classtimeDao.getClasstimeList();
            return list;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("获取上课时间列表异常", e);
            return null;
        }
    }

    /**
     * 添加新的上课时间数据
     *
     * @param classtimeStart String[] 上课开始时间
     * @param classtimeEnd   String[] 上课结束时间
     * @return boolean default false,添加成功返回true
     */
    @Transactional
    public boolean addClasstime(String[] weekIds,String[] classtimeStart, String[] classtimeStart_1, String[] classtimeEnd, String[] classtimeEnd_1) {
        boolean resultFlag = false;
        try {
            Integer j = 0;

            for (int i = 0; i < classtimeStart.length; i++) {
                if (!classtimeStart[i].equals("") && !classtimeEnd[i].equals("")) {
                    Classtime classtime = new Classtime();
                    int weekId = Integer.parseInt(weekIds[i]);
                    classtime.setWeekId(weekId);
                    classtime.setClasstimeStart(classtimeStart[i] + ":" + classtimeStart_1[i]);
                    classtime.setClasstimeEnd(classtimeEnd[i] + ":" + classtimeEnd_1[i]);
                    classtimeDao.save(classtime);
                } else {
                    j++;
                }
            }
            if (j >= classtimeStart.length) {
                resultFlag = false;
            } else {
                resultFlag = true; //添加成功
            }
        } catch (Exception e) {
            logger.error("添加新的上课时间数据异常", e);
        }
        return resultFlag;
    }

    /**
     * 修改上课时间
     * @param classtimeId
     * @param weekId
     * @param classtimeStart
     * @param classtimeStart_1
     * @param classtimeEnd
     * @param classtimeEnd_1
     * @return
     */
    @Transactional
    public int updateClasstime(String[] classtimeId,String[] weekId, String[] classtimeStart, String[] classtimeStart_1, String[] classtimeEnd, String[] classtimeEnd_1) {
        int resultFlag = 0;
        int flag = 0;
        try {
            if (classtimeId == null && weekId == null && classtimeStart == null && classtimeStart_1 == null && classtimeEnd == null && classtimeEnd_1 == null) {
                resultFlag = 2;
                return resultFlag;
            }
            for (int i = 0; i < classtimeId.length; i++) {
                if (!classtimeId[i].equals("") && !classtimeStart[i].equals("") && !classtimeEnd[i].equals("")) {
                    Integer classtimeid = Integer.parseInt(classtimeId[i]);
                    Integer weekid = Integer.parseInt(weekId[i]);
                    List info = timeplanDao.getTimeplanByClasstimeid(classtimeid);
                    if (info.isEmpty()) {
                        Classtime classtime = new Classtime();
                        classtime.setClasstimeId(classtimeid);
                        classtime.setWeekId(weekid);
                        classtime.setClasstimeStart(classtimeStart[i] + ":" + classtimeStart_1[i]);
                        classtime.setClasstimeEnd(classtimeEnd[i] + ":" + classtimeEnd_1[i]);
                        if (classtimeDao.update(classtime)) {
                            resultFlag = 0;
                        } else {
                            resultFlag = -1;
                        }
                    } else {
                        Classtime classtime1 = classtimeDao.getInfo(classtimeid);
                        String oldstarttime = classtime1.getClasstimeStart();
                        String oldendtime = classtime1.getClasstimeEnd();
                        String newstarttime = classtimeStart[i] + ":" + classtimeStart_1[i];
                        String newendtime = classtimeEnd[i] + ":" + classtimeEnd_1[i];
                        if (!oldstarttime.equals(newstarttime) || !oldendtime.equals(newendtime)) {
                            flag++;
                        }
                    }
                } else if (classtimeId[i].equals("") && !classtimeStart[i].equals("") && !classtimeEnd[i].equals("")) {
                    Classtime classtime = new Classtime();
                    classtime.setClasstimeStart(classtimeStart[i] + ":" + classtimeStart_1[i]);
                    classtime.setClasstimeEnd(classtimeEnd[i] + ":" + classtimeEnd_1[i]);
                    classtimeDao.save(classtime);
                    resultFlag = 0;//修改成功
                }
            }
            if (flag > 0) {
                resultFlag = 1;//修改失败，
            }
            //resultFlag =0;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("添加新的上课时间数据异常", e);
            resultFlag = -1;
        }
        return resultFlag;
    }

    /**
     * 删除上课时间
     *
     * @param classtime_id int 上课时间id
     * @return boolean
     */
    @Transactional
    public Boolean delete(int classtime_id) {
        try {
            if (classtimeDao.delete(classtime_id)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("删除上课时间异常", e);
            return false;
        }
    }

    public Classtime getInfo(int id) {
        try {
            Classtime info = classtimeDao.getInfo(id);
            return info;
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
    public Map<Integer, String> intToUpper() {
        Map<Integer, String> intToUpper = new HashedMap();
        intToUpper.put(1, "一");
        intToUpper.put(2, "二");
        intToUpper.put(3, "三");
        intToUpper.put(4, "四");
        intToUpper.put(5, "五");
        intToUpper.put(6, "六");
        intToUpper.put(7, "七");
        intToUpper.put(8, "八");
        intToUpper.put(9, "九");
        intToUpper.put(10, "十");
        intToUpper.put(11, "十一");
        intToUpper.put(12, "十二");
        intToUpper.put(13, "十三");
        intToUpper.put(14, "十四");
        intToUpper.put(15, "十五");
        intToUpper.put(16, "十六");
        intToUpper.put(17, "十七");
        intToUpper.put(18, "十八");
        intToUpper.put(19, "十九");
        intToUpper.put(20, "二十");
        intToUpper.put(21, "二十一");
        return intToUpper;
    }
//    /**
//     * 获取某班级某周几的上课时间
//     *
//     * @param hostId int 班级id，weekId int 星期几 1-星期一...7-星期日
//     * @return Map
//     */
//    public List<Object[]> getClasstimList(int hostId,int weekId) {
//        try {
//            List<Object[]> classtimeList = new ArrayList<>();
//            List<Object[]> groupList = hostgroupDao.getGroupInfoByhostId(hostId);
//            if(groupList == null || groupList.isEmpty())
//            {
//                classtimeList = classtimeDao.getClasstimeListNoGroup(weekId);
//            }
//            else {
//                classtimeList = classtimeDao.getClasstimeList(hostId, weekId);
//            }
//            return classtimeList;
//        } catch (Exception e) {
//            logger.error("", e);
//            return Collections.emptyList();
//        }
//    }


    /**
     * add by lichong
     * 获取某班级某周几的上课时间
     * @param hostId int 设备id，weekId int 星期几 1-星期一...7-星期日
     * @return Map
     */
    public List<Object[]> getClasstimList(int hostId,int weekId) {
        try {
            List<Object[]> classtimeList = new ArrayList<>();
            List<Object[]> groupList = this.getGroupInfoByHostId(hostId);
            if(groupList == null || groupList.isEmpty())
            {
                classtimeList = classtimeDao.getClasstimeListNoGroup(weekId);
            } else {
                int groupId = Integer.valueOf(groupList.get(0)[0].toString());
                classtimeList = classtimeDao.getClasstimeListByGroup(weekId, groupId);
            }
            return classtimeList;
        } catch (Exception e) {
            logger.error("", e);
            return Collections.emptyList();
        }
    }

    /**
     * add by lichong
     * 根据设备id获取有策略的组的信息（包括它的父级）
     * @param hostId
     * @return
     */
    public List<Object[]> getGroupInfoByHostId(int hostId) {
        List<Object[]> re_value = new ArrayList<>();
        List<Map<String,String>> areaList = this.getAllArea();
        Map<String,String> relMap = this.getGroupPloyRel();
        if(areaList.size()>0 || relMap.size()>0){
            try {
                List<Object[]> area = hostgroupDao.getAreaByHostId(hostId);
                String areaId = area.get(0)[0].toString();
                re_value = this.getHavePolyRelGroupInfoByAreaId(areaId,areaList,relMap);
            } catch (Exception e) {
                logger.error("", e);
            }
        }
        return re_value;
    }

    /** add by lichong
     * 递归找到绑定策略的组（地点）信息
     * 先判断当前地点是否绑定策略，如果没有在去找父级，依次递归，直到找到绑定策略的地点（如果没有找到，直接返回空数据）
     * @param areaId
     * @param areaList
     * @param relMap
     * @return
     */
    private List<Object[]> getHavePolyRelGroupInfoByAreaId(String areaId, List<Map<String,String>> areaList, Map<String,String> relMap){
        List<Object[]> re_value = new ArrayList<>();
        Object[] obj = new Object[2];
        for (Map<String,String> areaMap : areaList) {
            if(areaMap.get("areaId") == null || !areaMap.get("areaId").equals(areaId)){
                continue;
            }else {
                if(relMap.get(areaId) != null ) {
                    obj[0] = areaMap.get("areaId");
                    obj[1] = areaMap.get("areaName");
                    re_value.add(obj);
                }else {
                    areaId = areaMap.get("areaPId").toString();
                    return this.getHavePolyRelGroupInfoByAreaId(areaId, areaList, relMap);
                }
            }
        }
        return re_value;
    }


    private List<Map<String,String>> getAllArea(){
        List<Map<String,String>> areaList = new ArrayList<>();
        try {
            List<Object[]> areaDataList = hostgroupDao.getAllArea();
            for(Object[] areaObj : areaDataList){
                Map<String,String> areaMap = new HashMap<>();
                String areaId = areaObj[0].toString();
                String areaPId = areaObj[1].toString();
                String areaName = areaObj[2].toString();
                areaMap.put("areaId",areaId);
                areaMap.put("areaPId",areaPId);
                areaMap.put("areaName",areaName);
                areaList.add(areaMap);
            }
        }catch (Exception e){
            logger.error("获取所有的地点数据解析异常:"+e);
            return null;
        }
        return areaList;
    }

    private Map<String,String> getGroupPloyRel(){
        Map<String,String> relMap = new HashMap<>();
        try {
            List<Object[]> groupPloyRelList = hostgroupDao.getGroupPloyRel();
            for(Object[] rel : groupPloyRelList){
                String groupId = rel[0].toString();
                String polyId = rel[1].toString();
                relMap.put(groupId,polyId);
            }
        }catch (Exception e){
            logger.error("获取所有的地点数据解析异常:"+e);
            return null;
        }
        return relMap;
    }



    /**
     * edit by lichong
     * 获取某班级在某天的上课时间及课表信息
     *
     * @param hostId int 班级id，weekId int 星期几 1-星期一...7-星期日
     * @return Map
     */
    public List<Object[]> getClasstimListWithCurriculum(int hostId,int weekId) {
        try {
            List<Object[]> classtimeList = new ArrayList<>();
//            List<Object[]> groupList = hostgroupDao.getGroupInfoByhostId(hostId);
            List<Object[]> groupList = this.getGroupInfoByHostId(hostId);
            if(groupList == null || groupList.isEmpty()) {
                classtimeList = classtimeDao.getClasstimeListWithCirriculumNoGroup(hostId,weekId);
            } else {
//                classtimeList = classtimeDao.getClasstimeListWithCirriculum(hostId,weekId);
                int groupId = Integer.valueOf(groupList.get(0)[0].toString());   //获取组信息（有策略的那个特殊类型的地点）
                classtimeList = classtimeDao.getClasstimeListWithCirriculum(groupId, hostId, weekId);
            }
            return classtimeList;
        } catch (Exception e) {
            logger.error("", e);
            return Collections.emptyList();
        }
    }

    /**
     * edit by lichong
     * 获取某班级的上课时间及课表信息
     *
     * @param hostId int 班级id，weekId int 星期几 1-星期一...7-星期日
     * @return Map
     */
    public List<Object[]> getClasstimListWithCurriculum(int hostId,Date date) {
        try {
            List<Object[]> classtimeList = new ArrayList<>();
//            List<Object[]> groupList = hostgroupDao.getGroupInfoByhostId(hostId);
            List<Object[]> groupList = this.getGroupInfoByHostId(hostId);
            if(groupList == null || groupList.isEmpty())
            {
                classtimeList = classtimeDao.getClasstimeListWithCirriculumNoGroup(hostId,date);
            }
            else {
//                classtimeList = classtimeDao.getClasstimeListWithCirriculum(hostId,date);
                int groupId = Integer.valueOf(groupList.get(0)[0].toString());
                classtimeList = classtimeDao.getClasstimeListWithCirriculum(groupId, hostId, date);
            }
            return classtimeList;
        } catch (Exception e) {
            logger.error("", e);
            return Collections.emptyList();
        }
    }
    /**
     * 获取上课时间表所有上课节次
     *
     * @return List
     */
    public List<Integer> getSectionList() {
        try {
            return classtimeDao.getSectionList();
        } catch (Exception e) {
            logger.error("", e);
            return Collections.emptyList();
        }
    }

    /**
     * edit by lichong
     * 获取某班級上课时间表所有上课节次
     *
     * @return List
     */
    public List<Integer> getSectionListByHost(int hostId) {
        try {
            List<Integer> sectionList = new ArrayList<>();
//            List<Object[]> groupList = hostgroupDao.getGroupInfoByhostId(hostId);
            List<Object[]> groupList = this.getGroupInfoByHostId(hostId);
            if(groupList == null || groupList.isEmpty()) {
                sectionList = classtimeDao.getSectionListNoGroup(hostId);
            } else {
//                sectionList = classtimeDao.getSectionList(groupId);
                int groupId = Integer.valueOf(groupList.get(0)[0].toString());
                sectionList = classtimeDao.getSectionList(groupId);
            }
            return sectionList;
        } catch (Exception e) {
            logger.error("", e);
            return Collections.emptyList();
        }
    }

    /**
     * 获取所有组所对应的时间策略
     * @return
     */
    public List<Object[]> getAllClasstimeployGroup() {
        try {
            return classtimePloyDao.getAllClasstimeployGroup();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 添加时间策略
     * @throws Exception
     */
    @Transactional
    public void saveTimePloy(String timePloy) throws Exception{
        // 添加时间策略
        ClasstimePloy classtimePloy = new ClasstimePloy();
        classtimePloy.setPloyName(timePloy);
        classtimePloyDao.saveTimePloy(classtimePloy);
    }

    /**
     * 获取所有的时间策略
     * @return
     */
    public List<ClasstimePloy> getAllTimePloy() {
        try {
            List list= classtimePloyDao.getAllTimePloy();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取策略id
     * @param timePloy
     * @return
     */
    public Integer getTimePloyId(String timePloy) {
        try {
            return classtimePloyDao.getTimePloyId(timePloy);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 删除时间策略
     * @param timePloy
     * @throws Exception
     */
    @Transactional
    public void deleteTimePloy(String timePloy) throws Exception {
            Integer timePloyId = classtimePloyDao.getTimePloyId(timePloy);
            if (timePloyId >= -1) {
                // 删除相应的节次
                classtimePloyDao.delClassTime(timePloyId);
                // 删除对应的关系
                classtimeDao.deleteClasstime2Group(timePloyId);
                // 删除时间策略
                classtimePloyDao.delTimePloy(timePloy);
            }

        }

    /**
     * 更新分组id策略id
     * @param groupId
     * @param ployId
     * @throws Exception
     */
    @Transactional
    public void updateGroupIdAndPloyId(Integer groupId, Integer ployId) throws Exception{

        Integer p2gId = classtimePloyDao.getP2gId(groupId);
        // 插入操作
        if (p2gId == null) {
            classtimePloyDao.saveGroupIdAndPloyId(groupId, ployId);
            // 更新操作
        } else {
            classtimePloyDao.updateGroupIdAndPloyId(groupId, ployId);
        }
    }

    /**
     * todo 加注释
     * @param week
     * @param section
     * @throws Exception
     */
    public void delSection(int week, int section) throws Exception{
        classtimePloyDao.delSection(week, section);
    }

    /**
     * todo 加注释
     * @param week
     * @param sectionsList
     * @param classtimeStart
     * @param classtimeStart_1
     * @param classtimeEnd
     * @param classtimeEnd_1
     * @param timePloyId
     */
    @Transactional
    public void addClasstime(int week, List<String[]> sectionsList, String[] classtimeStart, String[] classtimeStart_1, String[] classtimeEnd, String[] classtimeEnd_1, Integer timePloyId) {
        try {
            if (sectionsList != null && !sectionsList.isEmpty()) {

                int index_start = 0;
                int index_end = 0;
                // 每周节次
                for (int i = 0;i < week; i++)
                {
                    String[] sections1 = sectionsList.get(i);
                    if (sections1 != null) {
                        index_end +=sections1.length;
                    }
                }

                String[] sections = sectionsList.get(week-1);
                if (sections != null) {
                    index_start = index_end - sections.length;
                }

                for (int i = index_start, k = 0; i < index_end; i++, k++) {

                    if (!classtimeStart[i].equals("") && !classtimeEnd[i].equals("") && (sections!=null)) {
                        Classtime classtime = new Classtime();
                        classtime.setWeekId(week);
                        ClasstimePloy classtimePloy = new ClasstimePloy();
                        classtimePloy.setPloyId(timePloyId);
                        classtime.setClasstimePloy(classtimePloy);
                        classtime.setSectionId(Integer.parseInt(sections[k]));
                        classtime.setClasstimeStart(classtimeStart[i] + ":" + classtimeStart_1[i]);
                        classtime.setClasstimeEnd(classtimeEnd[i] + ":" + classtimeEnd_1[i]);
                        classtimeDao.save(classtime);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("添加新的时间策略异常", e);
        }
    }

    /**
     * todo 加注释
     * @param week
     * @param sectionsList
     * @param classtimeStart
     * @param classtimeStart_1
     * @param classtimeEnd
     * @param classtimeEnd_1
     * @param timePloyId
     * @throws Exception
     */
    @Transactional
    public void updateClasstime(Integer week, List<String[]> sectionsList, String[] classtimeStart, String[] classtimeStart_1, String[] classtimeEnd, String[] classtimeEnd_1, Integer timePloyId) throws Exception {
        addClasstime(week, sectionsList, classtimeStart, classtimeStart_1, classtimeEnd, classtimeEnd_1, timePloyId);
    }

    /**
     * todo 加注释
     * @param ployId
     * @return
     * @throws Exception
     */
    public List<Object[]> getAllClassTime(Integer ployId) throws Exception{
        return classtimePloyDao.getAllClassTime(ployId);
    }

    /**
     * todo 加注释
     * @param timePloyId
     * @return
     */
    @Transactional
    public boolean deleteClassTime(Integer timePloyId)  {
        try {
            classtimePloyDao.delClassTime(timePloyId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * todo 加注释
     * @param timePloy
     * @return
     * @throws Exception
     */
    public boolean getTimePloy(String timePloy) throws Exception{
        ClasstimePloy classtimePloy = classtimePloyDao.getTimePloyByName(timePloy);
        if (classtimePloy != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * todo 加注释
     * @param timePloy
     * @return
     */
    public Integer getClassTimePloyId(String timePloy) {
        try {
            return classtimePloyDao.getClassTimePloyId(timePloy);
        } catch (Exception e) {
            return 0;
        }
    }
}
