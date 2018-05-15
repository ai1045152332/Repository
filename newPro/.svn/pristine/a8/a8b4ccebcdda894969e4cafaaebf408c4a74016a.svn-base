package com.honghe.recordweb.service.frontend.signalplan.impl;

import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.dao.SignalplanDao;
import com.honghe.recordhibernate.entity.PageBean;
import com.honghe.recordhibernate.entity.Signalplan;
import com.honghe.recordweb.service.frontend.signalplan.SignalplanService;
import com.honghe.recordweb.service.frontend.signalplan.SignalplanTimerService;
import com.honghe.recordweb.service.frontend.syslog.SyslogService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by yanxue on 2015-06-25.
 */
@Service
@Transactional
public class SignalplanImpl implements SignalplanService {

    private final static Logger logger = Logger.getLogger(SignalplanImpl.class);
    @Resource
    private SignalplanDao signalplanDao;
    @Resource
    private SyslogService syslogService;
    @Resource
    private SignalplanTimerService signalplanTimerService;


    /**
     * 根据page类方法，返回分页的分组数据
     *
     * @param currentPage
     * @param pageSize
     * @param uid
     * @return
     */
    @Override
    public PageBean signalplanListService(int currentPage, int pageSize, int uid, String type) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        int pageCount = 0;
        List<Object[]> result = new ArrayList<Object[]>();
        try {

            Page<List<Object[]>> page = new Page<List<Object[]>>(currentPage, pageSize);
            result = signalplanDao.getSignalplanListByUser(page, uid, type);
            pageCount = Integer.valueOf(String.valueOf(page.getTotalPageSize()));
        } catch (Exception e) {
            logger.error("返回分页的分组数据异常", e);
//            e.printStackTrace();
        }
        return new PageBean(currentPage, pageSize, pageCount, result);
    }

    /**
     * 判断某一id的定时计划是否发布给班级
     *
     * @param id
     * @return
     */
    @Override
    public boolean signalplanHostExistsService(int id) {
        try {
            return signalplanDao.isSignalplanHostExists(id);
        } catch (Exception e) {
            logger.error("判断某一id的定时计划是否发布给班级异常 id=" + id, e);
//            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除某定时计划的所有班级关系
     *
     * @param id
     * @return
     */
    @Override
    public boolean delSignalplanHostHostService(int id) {
        try {
            signalplanDao.delSignalplanHostBySignalplan(id);
            return true;
        } catch (Exception e) {
            logger.error("删除某定时计划的所有班级关系异常 id=" + id, e);
//            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将定时计划发布给一个班级
     *
     * @param scheduleId int 定时信号切换计划id
     * @param hostId     int 班级id
     * @return
     */
    @Override
    public boolean addSignalplanToHostService(int scheduleId, int hostId, String hostName) {
        try {
            signalplanDao.addSignalplanToHost(scheduleId, hostId, hostName);
            return true;
        } catch (Exception e) {
            logger.error("将定时计划发布给一个班级异常", e);
//            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取定时信号切换计划信息
     *
     * @param id
     * @return
     */
    @Override
    public List<Map> getSignalplanInfoService(int id) {
        List<Map> values = new ArrayList<Map>();
        try {
            List<Object[]> result = signalplanDao.getSignalplanById(id);
            for (Object[] obj : result) {
                Map<String, Object> map = new LinkedHashMap<String, Object>();
                int i = 0;
                //
                map.put("s_id", obj[i++]);
                map.put("s_signal", obj[i++]);
                map.put("s_signal_code", obj[i++]);
                map.put("s_loop", obj[i++]);
                map.put("s_looptype", obj[i++]);
                map.put("s_date", obj[i++]);
                map.put("s_week", obj[i++]);
                //
                map.put("s_time", obj[i++]);
                map.put("s_singletime", obj[i++]);
                map.put("s_uid", obj[i++]);
                map.put("s_username", obj[i++]);
                map.put("s_createtime", obj[i++]);
                //
                List<Map> hostList = new ArrayList<Map>();
                if (obj[i] != null && !obj[i].equals("")) {
                    String[] hostIds = obj[i++].toString().split(",");
                    String[] hostNames = obj[i++].toString().split(",");
                    for (int n = 0; n < hostIds.length; n++) {
                        Map<String, String> host = new HashMap<String, String>();
                        host.put("id", hostIds[n]);
                        host.put("name", hostNames[n]);
                        hostList.add(host);
                    }
                }
                map.put("host_list", hostList);
                values.add(map);
            }
        } catch (Exception e) {
            logger.error("获取定时信号切换计划信息异常", e);
//            e.printStackTrace();
        }
        return values;
    }

    /**
     * 获取定时信号切换计划信息
     *
     * @param id
     * @return
     */
    @Override
    public Signalplan getSignalplanService(int id) {
        try {

            Signalplan signalplan = signalplanDao.getSignalplan(id);
            return signalplan;
        } catch (Exception e) {
            logger.error("获取定时信号切换计划信息异常", e);
//            e.printStackTrace();
            return null;
        }
    }

    /**
     * 保存定时信号切换计划的信息
     *
     * @param signalplan
     * @param hostStr
     * @return
     */
    @Override
    public boolean addSignalplanService(Signalplan signalplan, String hostStr, String hostNameStr,String hostIpStr) {

        try {
            signalplanDao.saveSignalplan(signalplan);
            signalplanTimerService.addTimerbySignalplan(signalplan, hostStr);
            if (!hostStr.equals("")) {
                String[] hosts = hostStr.split(",");
                String[] hostName = hostNameStr.split(",");
                for (int i = 0; i < hosts.length; i++) {
                    addSignalplanToHostService(signalplan.getSignalId(), Integer.parseInt(hosts[i]), hostName[i]);
                }
            }
//            String log = "id:" + signalplan.getSignalId() + "signal:" + signalplan.getSignal() + "signalCode" + signalplan.getSignalCode() + "Loop:" + signalplan.getSignalLoop() + "LoopType:" + signalplan.getSignalLooptype()
//                    + "Date:" + signalplan.getSignalDate() + "Week:" + signalplan.getSignalWeek() + "Time:" + signalplan.getSignalTime();
            syslogService.addDeviceLog(hostIpStr, hostIpStr + "添加了定时信号切换计划", "SYSTEM");
            return true;

        } catch (Exception e) {
            logger.error("保存定时信号切换计划的信息异常", e);
//            e.printStackTrace();
        }

        return false;

    }

    /**
     * 更新定时信号切换计划信息
     *
     * @param signalplan 自定义定时计划类型
     * @param hostStr
     * @return
     */
    @Override
    public boolean updateSignalplanService(Signalplan signalplan, String hostStr, String hostNameStr,String hostIpStr) {
        try {
            if (signalplanDao.updateSignalplan(signalplan)) {
                boolean flag = true;
                if (hostStr != null && !hostStr.equals("")) {
                    if (signalplanHostExistsService(signalplan.getSignalId())) {
                        signalplanTimerService.delTimerbySignalplan(signalplan.getSignalId());
                        signalplanTimerService.addTimerbySignalplan(signalplan, hostStr);
                        signalplanDao.delSignalplanHostBySignalplan(signalplan.getSignalId());

                        String[] hosts = hostStr.split(",");
                        String[] hostNames = hostNameStr.split(",");
                        for (int i = 0; i < hosts.length; i++) {
                            int hostid = Integer.parseInt(hosts[i]);
                            addSignalplanToHostService(signalplan.getSignalId(), hostid, hostNames[i]);
                        }
                    } else {
                        signalplanTimerService.addTimerbySignalplan(signalplan, hostStr);
                        String[] hosts = hostStr.split(",");
                        String[] hostNames = hostNameStr.split(",");
                        for (int i = 0; i < hosts.length; i++) {
                            int hostid = Integer.parseInt(hosts[i]);
                            addSignalplanToHostService(signalplan.getSignalId(), hostid, hostNames[i]);
                        }
                    }
                }
                String log = "id:" + signalplan.getSignalId() + "signal:" + signalplan.getSignal() + "signalCode" + signalplan.getSignalCode() + "Loop:" + signalplan.getSignalLoop() + "LoopType:" + signalplan.getSignalLooptype()
                        + "Date:" + signalplan.getSignalDate() + "Week:" + signalplan.getSignalWeek() + "Time:" + signalplan.getSignalTime();
                syslogService.addDeviceLog(hostIpStr, hostIpStr + "修改了定时信号切换计划", "SYSTEM");
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error("更新定时信号切换计划信息异常", e);
//            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除定时信号切换计划信息
     *
     * @param signalplan
     * @param hostStr
     * @return
     */
    @Override
    public boolean delSignalplanService(Signalplan signalplan, String hostStr) {
        try {
            int id = signalplan.getSignalId();
            if (signalplanHostExistsService(id)) {
                signalplanTimerService.delTimerbySignalplan(id);
                signalplanDao.delSignalplanHostBySignalplan(id);
                signalplanDao.delSignalplan(signalplan);
            } else {
                signalplanDao.delSignalplan(signalplan);
            }
            syslogService.addDeviceLog(hostStr, hostStr + "删除了定时信号切换计划:" + signalplan.getSignalId() + "", "SYSTEM");
            return true;
        } catch (Exception e) {
            logger.error("删除定时信号切换计划信息异常", e);
//            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除某班级的定时响铃计划信息
     *
     * @param hid
     * @return
     */
    @Override
    public boolean delSignalplanByHostService(int hid) {
        try {
            if (signalplanDao.isSignalplanHostExists(hid)) {
                signalplanTimerService.delTimerbySignalplan(hid);
                signalplanDao.delSignalplanHostByHost(hid);
            }
            return true;
        } catch (Exception e) {
            logger.error("删除某班级的定时响铃计划信息异常", e);
//            e.printStackTrace();
            return false;
        }
    }
}
