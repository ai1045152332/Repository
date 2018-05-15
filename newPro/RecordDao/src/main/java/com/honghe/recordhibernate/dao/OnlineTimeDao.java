package com.honghe.recordhibernate.dao;

import com.honghe.recordhibernate.entity.OnlineTime;

import java.util.List;

/**
 * Created by qinzhihui on 2015/12/7.
 */
public interface OnlineTimeDao {
    /**
     * 储存在线信息
     */
    public void save(OnlineTime onlineTime) throws Exception;
    /**
     *修改信息 主要功能为记录关机时间
     */
    public void update(OnlineTime onlineTime) throws Exception;
    /**
     *查到本次开机时间 此方法用于记录关机时间
     */
    public OnlineTime findLastOpen(String mac) throws Exception;
    /**
     * 统计查询时间时用的方法
     */
    public List findByMac(String mac,long startTime, long endTime) throws Exception;
    //通过设备类型查询设备信息
    public List findByType(String mac,String type,long startTime, long endTime) throws Exception;
    /**
     * 删除过早的数据
     */
    public void deleteByTime(long time,String type) throws Exception;
    /**
     *用于查看某天是否开机过
     */
    public OnlineTime findWhetherOpen(String mac,long beginTime,long endTime) throws Exception;
    //查看某天相应设备类型的设备是否开机过
    public OnlineTime findWhetherOpenByType(String type,long beginTime,long endTime) throws Exception;
    /**
     * 获得一段时间内所有设备的总时长
     */
    public List findTotalTime(long startTime, long endTime,String type) throws Exception;
    //录制次数(前十)
    public List<Object[]> findCountRecordByGroup(int startTime,int endTime,String type)throws Exception;
}
