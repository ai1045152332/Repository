package com.honghe.recordweb.service.frontend.tempplan;

import com.honghe.recordhibernate.dao.HostDao;
import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.dao.TemporaryplanDao;
import com.honghe.recordhibernate.entity.Temporaryplan;
import com.honghe.recordweb.service.frontend.devicemanager.HostDevice;
import com.honghe.recordweb.service.frontend.devicemanager.NVRCommand;
import com.honghe.recordweb.service.frontend.timeplan.TimeplanService;
import com.honghe.recordweb.util.CommonName;
import com.honghe.service.client.Result;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by honghe on 2016/9/5.
 */
@Service
public class TemporaryplanService {
    private final static Logger logger = Logger.getLogger(TemporaryplanService.class);
    private static final ExecutorService executorService = Executors.newCachedThreadPool();
    @Resource
    private TemporaryplanDao temporaryplanDao;
    @Resource
    private HostDao hostDao;
    @Resource
    private TimeplanService timeplanService;
    @Resource
    private NVRCommand nvrCommand;
    @Resource
    private HostDevice hostDevice;
    /**
     *新增临时计划
     * Author xinye
     * @param tempPlan
     * @return
     */
    @Transactional
    public boolean addPlan(Temporaryplan tempPlan){
        try{
            temporaryplanDao.addPlan(tempPlan);
            return true;
        }catch(Exception e){
            logger.error("新增临时计划异常",e);
            return false;
        }
    }

    /**
     * 添加临时计划和host之间的关联关系
     * Author xinye
     * @param temp_id 临时计划Id
     * @param host_id
     * @return
     */
    @Transactional
    public boolean addRelation(Integer temp_id,Integer host_id){
        try{
            hostDao.addHostToTemporaryplan(host_id,temp_id);
            return true;
        }catch(Exception e){
            logger.error("",e);
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 修改临时计划
     * Author xinye
     * @param tempPlan
     * @return
     */
    @Transactional
    public boolean updatePlan(Temporaryplan tempPlan){
        try{
            temporaryplanDao.updatePlan(tempPlan);
            hostDao.delRelationToTemp(tempPlan.getTemporaryplanId());
            return true;
        }catch(Exception e){
            logger.error("修改临时计划异常",e);
            return false;
        }
    }

    /**
     * 删除临时计划
     * Author xinye
     * @param tempId
     * @return
     */
    @Transactional
    public boolean deletePlan(final Integer tempId){
        try{
            final List hostIdList = hostDao.fetchHostInTemp2Host(tempId);
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    for(int i=0,j=hostIdList.size();i<j;i++){
                        int hostId = Integer.parseInt(hostIdList.get(i).toString());
                        Map<String,Object> host_map = timeplanService.getHost(hostId);
                        int flag = nvrCommand.delTempPlanCommand(hostId,tempId,host_map.get("host_ipaddr").toString());
                    }
                }
            });

            hostDao.delRelationToTemp(tempId);
            temporaryplanDao.deletePlan(tempId);
            return true;
        }catch(Exception e){
            logger.error("删除临时计划异常",e);
            return false;
        }
    }

    /**
     * 查询临时计划列表
     * Author xinye
     * @param page
     */
    public void tempplanList(Page page){
        try{
            page.setResult(temporaryplanDao.tempPlanList());
        }catch(Exception e){
            logger.error("查询临时计划列表异常",e);
        }
    }

    /**
     * todo 加注释
     * @param id
     * @return
     */
    public Temporaryplan getTempById(Integer id){
        Temporaryplan temp = null;
        try{
            temp = temporaryplanDao.getTempById(id);
        }catch(Exception e){
            logger.error("",e);
            return null;
        }
        return temp;
    }

    /**
     * 根据临时计划的id查找关联的host
     * Author xinye
     * @param tempId 临时计划id
     * @return
     */
    public List getHostListInTemp(Integer tempId){
        try{
            return hostDao.fetchHostInTemp2Host(tempId);
        }catch(Exception e){
            logger.error("",e);
            return null;
        }
    }

    /**
     * 下发临时录像计划
     * Author xinye
     * @param host_ids 关联主机的id
     * @param temporaryplan 临时计划的实体类
     *
     */
    public void setTempPlan(String[] host_ids){
       for(String hostId:host_ids){
            Map<String,Object> host_map = timeplanService.getHost(Integer.parseInt(hostId));
            setTempPlan(host_map.get("host_ipaddr").toString(), Integer.valueOf(hostId), getTempPlanByHost(Integer.valueOf(hostId)));
        }
    }

    /**
     * 根据hostId查找结束时间晚于当前时间的结果集
     * @param hostId
     * @return List<Object[]>
     */
    public List getTempPlanByHost(int hostId){
        try{
            List list = temporaryplanDao.getTempBeforeTime(hostId, Calendar.getInstance());
            return list;
        }catch(Exception e){
            logger.error("",e);
            return null;
        }
    }

    /**
     * 下发临时录像计划
     * Author xinye
     * @param hostIp 设备ip地址
     * @param hostId    host_id
     * @param tempList 包含temporaryplan信息（id，time_start,time_end）的数组
     */
    public void setTempPlan(String hostIp,int hostId,List<Object[]> tempList){
        List<Map<String,Object>> recordTasksList = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        for(int i = 0,j=tempList.size();i<j;i++){
            map.put("timeplan_id",tempList.get(i)[0]);
            map.put("week_id",0);
            map.put("startTime",tempList.get(i)[3]);
            map.put("stopTime",tempList.get(i)[2]);
            map.put("hostId",hostId);
            recordTasksList.add(map);
        }
        nvrCommand.setPlanCommand(hostIp,recordTasksList);
    }

    /**
     * todo 加注释
     * @param tempId
     * @return
     */
    public List<Object[]> getGroupNameAndHostNameByTempId(int tempId){
        try{
            List<Object[]> list =temporaryplanDao.getGroupNameAndHostNameByTempId(tempId);
            return list;
        }catch(Exception e){
            logger.error("",e);
            return null;
        }
    }

    /**
     * 为临时计划提供主机列表  复制TimeplanService中的hostlist()方法改写的
     * Author xinye
     * @param uid  userid
     * @return
     */
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
                    Object[] hostInfoArr = new Object[4];
                    if (map != null) {
                        if (map.get("dspec").toString().equals("2")
                                || map.get("dspec").toString().equals("5")
                                || map.get("dspec").toString().equals("16")
                                )
                        {
                            hostInfoArr[0] = map.get("id");
                            hostInfoArr[1] = map.get("name");
                            hostInfoArr[2] = map.get("host_ip");
                            hostInfoArr[3] = map.get("dspecid");
//                            hostInfoArr[4] = map.get("host_username");
//                            hostInfoArr[5] = map.get("host_password");
//                            hostInfoArr[6] = map.get("host_serialno");
                            hostlist.add(hostInfoArr);
                            System.out.println("map:"+map);
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
}
