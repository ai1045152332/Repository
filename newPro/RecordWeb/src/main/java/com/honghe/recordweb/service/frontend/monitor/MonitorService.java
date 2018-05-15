package com.honghe.recordweb.service.frontend.monitor;

import com.honghe.recordhibernate.entity.Monitor;
import com.honghe.recordhibernate.entity.PageBean;

import java.util.List;

/**
 * Created by yanxue on 2015-07-07.
 */
public interface MonitorService {

    /**
     * 根据page类方法，返回满足查询条件的分页的分组数据
     *
     * @param currentPage 当前页
     * @param pageSize    每页的条目
     * @param mac         //设备物理地址
     * @param startTime   起始日期
     * @param endTime     结束日期
     * @return
     * @throws Exception
     */
    public PageBean listsService(int currentPage, int pageSize, String mac, String startTime, String endTime) throws Exception;


    /**
     * 保存监控软件的信息
     *
     * @param monitor
     * @return
     */
    public boolean addMonitorService(Monitor monitor);


    /**
     * 获取软件活跃度排名
     *
     * @return
     */
    public List<Object[]> getRanking();


    /**
     * 依据host物理地址获取该设备的软件使用情况
     *
     * @param mac
     * @return
     */
    public List<Object[]> getSoftConditionByHostMac(String mac, String startTime, String endTime);

    /**
     * 依据host物理地址获取该设备的软件使用情况
     *
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
