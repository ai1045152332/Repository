package com.honghe.recordweb.service.frontend.ringbell.impl;

import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.dao.RingbellDao;
import com.honghe.recordhibernate.entity.PageBean;
import com.honghe.recordhibernate.entity.Ringbell;
import com.honghe.recordweb.service.frontend.ringbell.RingBellTimerService;
import com.honghe.recordweb.service.frontend.ringbell.RingbellService;
import com.honghe.recordweb.service.frontend.syslog.SyslogService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by yanxue on 2015-06-23.
 */
@Service
@Transactional
public class RingbellServiceImpl implements RingbellService {
    private final static Logger logger = Logger.getLogger(RingbellServiceImpl.class);

    @Resource
    private RingbellDao ringbellDao;
    @Resource
    private SyslogService syslogService;
    @Resource
    private RingBellTimerService ringBellTimerService;


    /**
     * 根据page类方法，返回分页的分组数据
     *
     * @param _currentPage 当前页
     * @param _pageSize    每页的总条数
     * @param _uid         用户ID 0，超级用户；1：普通用户
     * @return
     */
    @Transactional
    @Override
    public PageBean ringbellListService(int _currentPage, int _pageSize, int _uid ,String type) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        int pageCount = 0;
        List<Object[]> result = new ArrayList<Object[]>();
        try {

            Page<List<Object[]>> page = new Page<List<Object[]>>(_currentPage, _pageSize);
            result = ringbellDao.getRingbellListByUser(page, _uid ,type);
            pageCount = Integer.valueOf(String.valueOf(page.getTotalPageSize()));
        } catch (Exception e) {
            logger.error("返回分页的分组数据异常", e);
//            e.printStackTrace();
        }
        return new PageBean(_currentPage, _pageSize, pageCount, result);
    }

    /**
     * 判断某一id的定时计划是否发布给班级
     *
     * @param _id 定时打铃ID
     * @return boolean  true已发布，false未发布
     */
    @Transactional
    @Override
    public boolean ringbellHostExistsService(int _id) {
        try {
            return ringbellDao.isRingbellHostExists(_id);
        } catch (Exception e) {
            logger.error("判断某一id的定时计划是否发布给班级异常，id=" + _id, e);
//            e.printStackTrace();
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
    @Override
    public boolean delRingbellHostHostService(int _id) {

        try {
            ringbellDao.delRingbellHostByRingbell(_id);
            return true;
        } catch (Exception e) {
            logger.error(" 删除某定时计划的所有班级关系异常，id=" + _id, e);
//            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将定时计划发布给一个班级
     *
     * @param _scheduleId int 定时响铃计划id
     * @param _hostId     int 班级id
     * @param _hostName   班级名
     * @return boolean  true发布成功，false发布失败
     */
    @Transactional
    @Override
    public boolean addRingbellToHostService(int _scheduleId, int _hostId, String _hostName) {

        try {
            ringbellDao.addRingbellToHost(_scheduleId, _hostId, _hostName);
            return true;
        } catch (Exception e) {
            logger.error("将定时计划发布给一个班级", e);
//            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取定时响铃计划信息
     *
     * @param _id 定时打铃ID
     * @return
     */
    @Override
    public List<Map> getRingbellInfoService(int _id) {
        List<Map> values = new ArrayList<Map>();
        try {
            List<Object[]> result = ringbellDao.getRingbellById(_id);
            for (Object[] obj : result) {
                Map<String, Object> map = new LinkedHashMap<String, Object>();
                int i = 0;
                //
                map.put("r_id", obj[i++]);
                map.put("r_loop", obj[i++]);
                map.put("r_looptype", obj[i++]);
                map.put("r_date", obj[i++]);
                map.put("r_week", obj[i++]);
                //
                map.put("r_time", obj[i++]);
                map.put("r_singletime", obj[i++]);
                map.put("r_uid", obj[i++]);
                map.put("r_username", obj[i++]);
                map.put("r_createtime", obj[i++]);
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
            logger.error("获取定时响铃计划信息列表异常", e);
//            e.printStackTrace();
        }
        return values;
    }

    /**
     * 获取定时响铃计划信息
     *
     * @param _id
     * @return
     */
    @Override
    public Ringbell getRingbellService(int _id) {
        try {
            Ringbell ringbell = ringbellDao.getRingbell(_id);
            return ringbell;
        } catch (Exception e) {
            logger.error("获取定时响铃计划信息异常", e);
//            e.printStackTrace();
            return null;
        }
    }

    /**
     * 保存定时响铃计划的信息
     *
     * @param _ringbell
     * @param _hostStr
     * @return
     */
    @Transactional
    @Override
    public boolean addRingbellService(Ringbell _ringbell, String _hostStr, String _hostNameStr,String _hostIpStr) {

        try {
            ringbellDao.saveRingbell(_ringbell);
            ringBellTimerService.addTimerbyRingBell(_ringbell, _hostStr);
            if (!_hostStr.equals("")) {
                String[] hosts = _hostStr.split(",");
                String[] hostnames = _hostNameStr.split(",");
                for (int i = 0; i < hosts.length; i++) {
                    addRingbellToHostService(_ringbell.getRingbellId(), Integer.parseInt(hosts[i]), hostnames[i]);
                }
            }
            String log = "_id:" + _ringbell.getRingbellId() + "Loop:" + _ringbell.getRingbellLoop() + "LoopType:" + _ringbell.getRingbellLooptype()
                    + "Date:" + _ringbell.getRingbellDate() + "Week:" + _ringbell.getRingbellWeek() + "Time:" + _ringbell.getRingbellTime();
            syslogService.addDeviceLog( _hostIpStr, _hostIpStr + "添加了定时响铃计划", "SYSTEM");

            return true;

        } catch (Exception e) {
            logger.error("保存定时响铃计划的信息异常", e);
//            e.printStackTrace();
        }

        return false;
    }

    /**
     * 更新定时响铃计划信息
     *
     * @param _ringbell    自定义定时计划类型
     * @param _hostStr     班级ID
     * @param _hostNameStr 班级名称
     * @return
     */
    @Override
    public boolean updateRingbellService(Ringbell _ringbell, String _hostStr, String _hostNameStr,String _hostIpStr) {
        try {
            if (ringbellDao.updateRingbell(_ringbell)) {
                boolean flag = true;
                if (_hostStr != null && !_hostStr.equals("")) {
                    if (ringbellHostExistsService(_ringbell.getRingbellId())) {

                        ringBellTimerService.delTimerbyRingBell(_ringbell.getRingbellId());
                        ringBellTimerService.addTimerbyRingBell(_ringbell, _hostStr);
                        ringbellDao.delRingbellHostByRingbell(_ringbell.getRingbellId());

                        String[] hosts = _hostStr.split(",");
                        String[] hostNames = _hostNameStr.split(",");

                        for (int i = 0; i < hosts.length; i++) {
                            int hostid = Integer.parseInt(hosts[i]);
                            String hostname = hostNames[i];

                            addRingbellToHostService(_ringbell.getRingbellId(), hostid, hostname);
                        }
                    } else {
                        ringBellTimerService.addTimerbyRingBell(_ringbell, _hostStr);

                        String[] hosts = _hostStr.split(",");
                        String[] hostNames = _hostNameStr.split(",");

                        for (int i = 0; i < hosts.length; i++) {
                            int hostid = Integer.parseInt(hosts[i]);
                            String hostname = hostNames[i];
                            addRingbellToHostService(_ringbell.getRingbellId(), hostid, hostname);
                        }
                    }
                }
                String log = "_id:" + _ringbell.getRingbellId() + "Loop:" + _ringbell.getRingbellLoop() + "LoopType:" + _ringbell.getRingbellLooptype()
                        + "Date:" + _ringbell.getRingbellDate() + "Week:" + _ringbell.getRingbellWeek() + "Time:" + _ringbell.getRingbellTime();
                syslogService.addDeviceLog(_hostIpStr, _hostIpStr + "修改了定时响铃计划", "SYSTEM");
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error("更新定时响铃计划信息异常", e);
//            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除定时响铃计划信息
     *
     * @param _ringbell
     * @param _hostStr
     * @return
     */
    @Override
    public boolean delRingbellService(Ringbell _ringbell, String _hostStr) {
        try {
            int id = _ringbell.getRingbellId();
            if (ringbellHostExistsService(id)) {
                ringBellTimerService.delTimerbyRingBell(id);
                ringbellDao.delRingbellHostByRingbell(id);
                ringbellDao.delRingbell(_ringbell);
            } else {
                ringbellDao.delRingbell(_ringbell);
            }
            syslogService.addDeviceLog(_hostStr, _hostStr + "删除了定时响铃计划:" + _ringbell.getRingbellId() + "", "SYSTEM");
            return true;
        } catch (Exception e) {
            logger.error("删除定时响铃计划信息异常", e);
//            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除某班级的定时响铃计划信息
     *
     * @param _hid
     * @return
     */
    @Override
    public boolean delRingbellByHostService(int _hid) {

        try {
            if (ringbellDao.isRingbellHostExists(_hid)) {
                ringBellTimerService.delTimerbyHost(_hid);
                ringbellDao.delRingbellHostByHost(_hid);
            }
            return true;
        } catch (Exception e) {
            logger.error("删除某班级的定时响铃计划信息异常", e);
            return false;
        }
    }


}
