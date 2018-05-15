package com.honghe.recordweb.service.frontend.programme;

import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.dao.ProgrammeDao;
import com.honghe.recordhibernate.entity.Programme;
import com.honghe.recordweb.service.frontend.syslog.SyslogService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by lyx on 2015-08-22.
 */
@Service
public class ProgrammeService {

    private final static Logger logger = Logger.getLogger(ProgrammeService.class);
    @Resource
    private SyslogService syslogService;
    @Resource
    private ProgrammeDao programmeDao;
    @Resource
    private ProgrammeTimerService programmeTimerService;


    /**
     * 根据page类方法，返回分页的分组数据
     *
     * @param _currentPage int,_pageSize int
     * @return Map<String,Object>
     */
    @Transactional
    public Map<String, Object> programmeListService(int _currentPage, int _pageSize, int _uid) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {

            Page<List<Object[]>> page = new Page<List<Object[]>>(_currentPage, _pageSize);
            List<Object[]> result = programmeDao.getProgrammeListByUser(page, _uid);

            map.put("programmelist", result);
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
     * @param _id int 定时计划id
     * @return boolean true 表示分配，false表示未分配
     */
    @Transactional
    public boolean programmeHostExistsService(int _id) {
        try {
            return programmeDao.isProgrammeHostExists(_id);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("判断某一id的定时计划是否发布给班级异常 id=" + _id, e);
            return false;
        }
    }


    /**
     * 删除某定时计划的所有班级关系
     *
     * @param _id int 定时计划id
     * @return boolean  true删除成功，false失败
     */
    @Transactional
    private boolean delProgrammeHostService(int _id) {
        try {
            programmeDao.delProgrammeHostByProgramme(_id);
            return true;
        } catch (Exception e) {
            logger.error("删除某定时计划的所有班级关系异常 id=" + _id, e);
            return false;
        }
    }


    /**
     * 将定时计划发布给一个班级
     *
     * @param _policyId int 定时计划id
     * @param _hostId   int 班级id
     * @return
     */
    @Transactional
    private boolean addProgrammeToHostService(int _policyId, int _hostId, String _hostName) {
        try {
            programmeDao.addProgrammeToHost(_policyId, _hostId, _hostName);
            return true;
        } catch (Exception e) {
            logger.error("将定时计划发布给一个班级异常", e);
            return false;
        }
    }


    /**
     * 获取定时计划信息
     *
     * @param _id
     * @return
     */
    public List<Map> getProgrammeInfoService(int _id) {
        List<Map> values = new ArrayList<Map>();
        try {
            List<Object[]> result = programmeDao.getProgrammeById(_id);
            for (Object[] obj : result) {
                Map<String, Object> map = new LinkedHashMap<String, Object>();
                int i = 0;
                //
                map.put("p_id", obj[i++]);
                map.put("p_type", obj[i++]);
                map.put("p_loop", obj[i++]);
                map.put("p_looptype", obj[i++]);
                map.put("p_date", obj[i++]);

                map.put("p_week", obj[i++]);                //
                map.put("p_time", obj[i++]);
                map.put("p_singletime", obj[i++]);
                map.put("p_uid", obj[i++]);
                map.put("p_username", obj[i++]);

                map.put("p_createtime", obj[i++]);
                //
                List<Map> hostList = new ArrayList<Map>();
                if (obj[i] != null && !obj[i].equals("")) {

                    String[] hostIds = obj[i++].toString().split(",");
                    String[] hostNames = obj[i++].toString().split(",");

                    for (int n = 0; n < hostIds.length; n++) {
                        Map<String, String> host = new HashMap<String, String>();
                        host.put("_id", hostIds[n]);
                        host.put("name", hostNames[n]);
                        hostList.add(host);
                    }
                }
                map.put("host_list", hostList);
                values.add(map);
            }
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("获取定时计划信息列表异常 id=" + _id, e);
        }
        return values;
    }


    /**
     * 获取定时切换电视节目计划信息
     *
     * @param _id
     * @return
     */
    public Programme getProgrammeService(int _id) {
        try {
            Programme programme = programmeDao.getProgramme(_id);
            return programme;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("获取定时切换电视节目计划信息异常 id=" + _id, e);
            return null;
        }
    }


    /**
     * 保存定时计划的信息
     *
     * @param _policy Policy
     * @return
     */
    @Transactional
    public boolean addProgrammeService(Programme _policy, String _hostStr, String _hostNames,String _hostIpStr) {
        try {
            programmeDao.saveProgramme(_policy);
            programmeTimerService.addTimerbyProgramme(_policy, _hostStr);
            if (!_hostStr.equals("")) {
                String[] hosts = _hostStr.split(",");
                String[] hostnames = _hostNames.split(",");
                for (int i = 0; i < hosts.length; i++) {
                    addProgrammeToHostService(_policy.getProId(), Integer.parseInt(hosts[i]), hostnames[i]);
                }
            }
            String log = "_id:" + _policy.getProId() + "Type:" + _policy.getProType() + "Loop:" + _policy.getProLoop() + "LoopType:" + _policy.getProLooptype()
                    + "Date:" + _policy.getProDate() + "Week:" + _policy.getProWeek() + "Time:" + _policy.getProTime();
            syslogService.addDeviceLog(_hostIpStr, _hostIpStr + "添加了定时切换节目计划:" + log + "", "SYSTEM");
            return true;
        } catch (Exception e) {
            logger.error("保存定时计划的信息异常", e);
//            e.printStackTrace();
            return false;
        }
    }

    /**
     * 更新定时节目切换计划信息
     *
     * @param _programme 自定义定时计划类型
     * @param _hostStr
     * @return
     */
    @Transactional
    public boolean updateProgrammeService(Programme _programme, String _hostStr, String _hostNames,String _hostIpStr) {
        try {
            if (programmeDao.updateProgramme(_programme)) {
                boolean flag = true;
                if (_hostStr != null && !_hostStr.equals("")) {
                    if (programmeHostExistsService(_programme.getProId())) {
                        programmeTimerService.delTimerbyProgramme(_programme.getProId());
                        programmeTimerService.addTimerbyProgramme(_programme, _hostStr);
                        programmeDao.delProgrammeHostByProgramme(_programme.getProId());
                        String[] hosts = _hostStr.split(",");
                        String[] hostNames = _hostNames.split(",");
                        for (int i = 0; i < hosts.length; i++) {
                            int hostid = Integer.parseInt(hosts[i]);
                            String hostName = hostNames[i];
                            addProgrammeToHostService(_programme.getProId(), hostid, hostName);
                        }
                    } else {
                        programmeTimerService.addTimerbyProgramme(_programme, _hostStr);
                        String[] hosts = _hostStr.split(",");
                        String[] hostNames = _hostNames.split(",");
                        for (int i = 0; i < hosts.length; i++) {
                            int hostid = Integer.parseInt(hosts[i]);
                            String hostName = hostNames[i];
                            addProgrammeToHostService(_programme.getProId(), hostid, hostName);
                        }
                    }
                }
                String log = "id:" + _programme.getProId() + "Loop:" + _programme.getProLoop() + "LoopType:" + _programme.getProLooptype()
                        + "Date:" + _programme.getProDate() + "Week:" + _programme.getProWeek() + "Time:" + _programme.getProTime();
                syslogService.addDeviceLog( _hostIpStr, _hostIpStr + "修改了定时切换节目计划", "SYSTEM");
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error("更新定时节目切换计划信息", e);
//            e.printStackTrace();
            return false;
        }
    }


    /**
     * 删除定时计划信息
     *
     * @param _programme Policy
     * @return
     */
    @Transactional
    public boolean delProgrammeService(Programme _programme, String _hostStr) {
        try {
            int id = _programme.getProId();
            if (programmeHostExistsService(id)) {
                programmeTimerService.delTimerbyProgramme(id);
                programmeDao.delProgrammeHostByProgramme(id);
                programmeDao.delProgramme(_programme);
            } else {
                programmeDao.delProgramme(_programme);
            }
            syslogService.addDeviceLog(_hostStr, _hostStr + "删除了定时切换节目计划:" + _programme.getProId() + "", "SYSTEM");
            return true;
        } catch (Exception e) {
            logger.error("删除定时计划信息信息", e);
            return false;
        }
    }

    /**
     * 删除某班级的定时计划信息
     *
     * @param _hid int
     * @return
     */
    @Transactional
    public boolean delProgrammeByHostService(int _hid) {
        try {
            if (programmeDao.isProgrammeHostExistsByHost(_hid)) {
                programmeTimerService.delTimerbyHost(_hid);
                programmeDao.delProgrammeHostByHost(_hid);
            }
            return true;
        } catch (Exception e) {
            logger.error("删除某班级的定时计划信息", e);
            return false;
        }
    }


}
