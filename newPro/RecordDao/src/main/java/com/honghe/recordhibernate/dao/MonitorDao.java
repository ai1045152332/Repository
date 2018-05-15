package com.honghe.recordhibernate.dao;

import com.honghe.recordhibernate.entity.Monitor;

import java.util.List;

/**
 * Created by yanxue on 2015-07-07.
 */
public interface MonitorDao {

    /**
     * 保存软件监控信息
     *
     * @param monitor
     * @return
     * @throws Exception
     */
    public boolean saveMonitor(Monitor monitor) throws Exception;

    /**
     * 请求软件监控信息列表
     *
     * @param page
     * @param mac
     * @param startTime
     * @param endTime
     * @throws Exception
     */
    public void getMonitorList(Page<Monitor> page, String mac, String startTime, String endTime) throws Exception;


    /***
     * 获取软件活跃度排名
     * @return
     */
    public List<Object []> getRanking();


    /**
     * 依据host物理地址获取该设备的软件使用情况
     * @param mac
     * @param startTime
     * @param endTime
     * @return
     */

    public List<Object[]> getSoftConditionByHostMac(String mac, String startTime, String endTime);

    /**
     * 依据host物理地址获取该设备的软件使用情况
     * @param mac
     * @return
     */
    public List<Object[]> getSoftConditionByHostMac(String mac);

    /**
     * 获取置顶软件列表
     *
     *
     * @return
     */
    public List<Object[]> getMonitorList();

}
