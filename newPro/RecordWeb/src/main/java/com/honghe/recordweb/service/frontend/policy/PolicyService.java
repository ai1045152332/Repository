package com.honghe.recordweb.service.frontend.policy;

import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.dao.PolicyDao;
import com.honghe.recordhibernate.entity.Policy;
import com.honghe.recordweb.service.frontend.syslog.SyslogService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by wzz on 2015-03-01.
 */
@Service
public class PolicyService {
    private final static Logger logger = Logger.getLogger(PolicyService.class);
    @Resource
    private PolicyDao policyDao;//Sping来管理对象
    @Resource
    private PolicyTimerService policyTimerService;
    @Resource
    private SyslogService syslogService;

    /**
     * 根据page类方法，返回分页的分组数据
     *
     * @param currentPage int,pageSize int
     * @return Map<String,Object>
     */
    @Transactional
    public Map<String, Object> policyListService(int currentPage, int pageSize, int uid, String type ) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {

            Page<List<Object[]>> page = new Page<List<Object[]>>(currentPage, pageSize);
            List<Object[]> result = policyDao.getPolicyListByUser(page, uid, type );
            map.put("policylist", result);
            map.put("pageCount", String.valueOf(page.getTotalPageSize()));
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("返回分页的分组数据异常", e);
        }
        return map;

    }

    /**
     * 判断某一id的定时计划是否发布给班级
     *
     * @param id int 定时计划id
     * @return boolean true 表示分配，false表示未分配
     */
    @Transactional
    public boolean policyHostExistsService(int id) {
        try {
            return policyDao.isPolicyHostExists(id);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("判断某一id的定时计划是否发布给班级异常 id=" + id, e);
            return false;
        }
    }

    /**
     * 删除某定时计划的所有班级关系
     *
     * @param id int 定时计划id
     * @return boolean  true删除成功，false失败
     */
    @Transactional
    private boolean delPolicyHostService(int id) {
        try {
            policyDao.delPolicyHostByPolicy(id);
            return true;
        } catch (Exception e) {
            logger.error("删除某定时计划的所有班级关系异常 id=" + id, e);
            return false;
        }
    }

    /**
     * 将定时计划发布给一个班级
     *
     * @param policyId int 定时计划id
     * @param hostId   int 班级id
     * @return
     */
    @Transactional
    private boolean addPolicyToHostService(int policyId, int hostId, String hostName) {
        try {
            policyDao.addPolicyToHost(policyId, hostId, hostName);
            return true;
        } catch (Exception e) {
            logger.error("将定时计划发布给一个班级异常", e);
            return false;
        }
    }

    /**
     * 获取定时计划信息
     *
     * @param id
     * @return
     */
    public List<Map> getPolicyInfoService(int id) {
        List<Map> values = new ArrayList<Map>();
        try {
            List<Object[]> result = policyDao.getPolicyById(id);
            for (Object[] obj : result) {
                Map<String, Object> map = new LinkedHashMap<String, Object>();
                map.put("p_id", obj[0]);
                map.put("p_type", obj[1]);
                map.put("p_loop", obj[2]);
                map.put("p_looptype", obj[3]);
                map.put("p_date", obj[4]);
                map.put("p_week", obj[5]);
                map.put("p_time", obj[6]);
                map.put("p_singletime", obj[7]);
                map.put("p_uid", obj[8]);
                map.put("p_username", obj[9]);
                map.put("p_createtime", obj[10]);
                List<Map> hostList = new ArrayList<Map>();
                if (obj[11] != null && !obj[11].equals("")) {
                    String[] hostIds = obj[11].toString().split(",");
                    String[] hostNames = obj[12].toString().split(",");
                    for (int i = 0; i < hostIds.length; i++) {
                        Map<String, String> host = new HashMap<String, String>();
                        host.put("id", hostIds[i]);
                        host.put("name", hostNames[i]);
                        hostList.add(host);
                    }
                }
                map.put("host_list", hostList);
                values.add(map);
            }
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("获取定时计划信息列表异常 id=" + id, e);
        }
        return values;
    }

    /**
     * 获取定时计划信息
     *
     * @param id
     * @return
     */
    public Policy getPolicyService(int id) {
        try {
            Policy policy = policyDao.getPolicy(id);
            return policy;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("获取定时计划信息异常 id=" + id, e);
            return null;
        }
    }

    /**
     * 保存定时计划的信息
     *
     * @param policy Policy
     * @return
     */
    @Transactional
    public boolean addPolicyService(Policy policy, String hostStr, String hostNameStr,String hostIpStr) {
        try {
            policyDao.savePolicy(policy);
            policyTimerService.addTimerbyPolicy(policy, hostStr);
            if (!hostStr.equals("")) {
                String[] hosts = hostStr.split(",");
                String[] hostNames = hostNameStr.split(",");
                for (int i = 0; i < hosts.length; i++) {
                    addPolicyToHostService(policy.getpId(), Integer.parseInt(hosts[i]), hostNames[i]);
                }
            }
            String log = "id:" + policy.getpId() + "Type:" + policy.getpType() + "Loop:" + policy.getpLoop() + "LoopType:" + policy.getpLooptype()
                    + "Date:" + policy.getpDate() + "Week:" + policy.getpWeek() + "Time:" + policy.getpTime();
            syslogService.addDeviceLog(hostIpStr, hostIpStr + "添加了开关机定时计划", "SYSTEM");
            return true;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("保存定时计划信息异常", e);
            return false;
        }
    }

    /**
     * 更新定时计划信息
     *
     * @param policy Policy自定义定时计划类型
     * @return
     */
    @Transactional
    public boolean updatePolicyService(Policy policy, String hostStr, String hostStrName,String hostIpStr) {
        try {
            if (policyDao.updatePolicy(policy)) {
                boolean flag = true;
                if (hostStr != null && !hostStr.equals("")) {
                    if (policyHostExistsService(policy.getpId())) {
                        policyTimerService.delTimerbyPolicy(policy.getpId());
                        policyTimerService.addTimerbyPolicy(policy, hostStr);
                        delPolicyHostService(policy.getpId());
                        String[] hosts = hostStr.split(",");
                        String[] hostNames = hostStrName.split(",");
                        for (int i = 0; i < hosts.length; i++) {
                            int hostid = Integer.parseInt(hosts[i]);
                            String hostName = hostNames[i];
                            addPolicyToHostService(policy.getpId(), hostid, hostName);
                        }
                    } else {
                        policyTimerService.addTimerbyPolicy(policy, hostStr);
                        String[] hosts = hostStr.split(",");
                        String[] hostNames = hostStrName.split(",");
                        for (int i = 0; i < hosts.length; i++) {
                            int hostid = Integer.parseInt(hosts[i]);
                            String hostName = hostNames[i];
                            addPolicyToHostService(policy.getpId(), hostid, hostName);
                        }
                    }
                }
                String log = "id:" + policy.getpId() + "Type:" + policy.getpType() + "Loop:" + policy.getpLoop() + "LoopType:" + policy.getpLooptype()
                        + "Date:" + policy.getpDate() + "Week:" + policy.getpWeek() + "Time:" + policy.getpTime();
                syslogService.addDeviceLog(hostIpStr, hostIpStr + "修改了开关机定时计划" , "SYSTEM");
                return true;
            }
            return false;
        } catch (Exception e) {
            //            e.printStackTrace();
            logger.error("更新定时计划信息异常", e);
            return false;
        }
    }

    /**
     * 删除定时计划信息
     *
     * @param policy Policy
     * @return
     */
    @Transactional
    public boolean delPolicyService(Policy policy, String hostStr) {
        try {
            int id = policy.getpId();
            if (policyHostExistsService(id)) {
                policyTimerService.delTimerbyPolicy(id);
                policyDao.delPolicyHostByPolicy(id);
                policyDao.delPolicy(policy);
            } else {
                policyDao.delPolicy(policy);
            }
            syslogService.addDeviceLog(hostStr, hostStr + "删除了定时计划:" + policy.getpId() + "", "SYSTEM");
            return true;
        } catch (Exception e) {
            //            e.printStackTrace();
            logger.error("删除定时计划信息异常", e);
            return false;
        }
    }

    /**
     * 删除某班级的定时计划信息
     *
     * @param hid int
     * @return
     */
    @Transactional
    public boolean delPolicyByHostService(int hid) {
        try {
            if (policyDao.isPolicyHostExistsByHost(hid)) {
                policyTimerService.delTimerbyHost(hid);
                policyDao.delPolicyHostByHost(hid);
            }
            return true;
        } catch (Exception e) {
            //            e.printStackTrace();
            logger.error("删除某班级的定时计划信息异常 hid=" + hid, e);
            return false;
        }
    }
}
