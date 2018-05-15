package com.honghe.recordweb.service.frontend.monitor.impl;

import com.honghe.recordhibernate.dao.MonitorDao;
import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.entity.Monitor;
import com.honghe.recordhibernate.entity.PageBean;
import com.honghe.recordweb.service.frontend.monitor.MonitorService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yanxue on 2015-07-07.
 */
@Service
@Transactional
public class MonitorServiceImpl implements MonitorService {

    private final static Logger logger = Logger.getLogger(MonitorServiceImpl.class);
    @Resource
    private MonitorDao monitorDao;

    /**
     * 加注释
     * @param currentPage 当前页
     * @param pageSize    每页的条目
     * @param mac         //设备物理地址
     * @param startTime   起始日期
     * @param endTime     结束日期
     * @return
     */
    @Override
    public PageBean listsService(int currentPage, int pageSize, String mac, String startTime, String endTime) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        int pageCount = 0;
        List<Monitor> result = new ArrayList<Monitor>();
        try {
            Page<Monitor> page = new Page<Monitor>(currentPage, pageSize);
            monitorDao.getMonitorList(page, mac, startTime, endTime);
            pageCount = Integer.valueOf(String.valueOf(page.getTotalPageSize()));
            result = page.getResult();
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
        }

        return new PageBean(currentPage, pageSize, pageCount, result);
    }

    /**
     * todo 加注释
     * @param monitor
     * @return
     */
    @Override
    public boolean addMonitorService(Monitor monitor) {

        try {

            return monitorDao.saveMonitor(monitor);

        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return false;
        }

    }


    /**
     * 获取软件活跃度排名
     *
     * @return
     */
    @Override
    public List<Object[]> getRanking() {
        return monitorDao.getRanking();
    }

    /**
     * 依据host物理地址获取该设备的软件使用情况
     *
     * @param mac
     * @param startTime
     * @param endTime   @return
     */
    @Override
    public List<Object[]> getSoftConditionByHostMac(String mac, String startTime, String endTime) {
        return monitorDao.getSoftConditionByHostMac(mac, startTime, endTime);
    }

    /**
     * 依据host物理地址获取该设备的软件使用情况
     *
     * @param mac
     * @return
     */
    @Override
    public List<Object[]> getSoftConditionByHostMac(String mac) {
        return monitorDao.getSoftConditionByHostMac(mac);
    }

    /**
     * 获取置顶软件列表
     *
     * @return
     */
    @Override
    public List<Object[]> getMonitorList() {
        return monitorDao.getMonitorList();
    }

}
