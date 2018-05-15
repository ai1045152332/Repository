package com.honghe.recordhibernate.dao;

import com.honghe.recordhibernate.entity.DeviceLog;

import java.util.List;

/**
 * Created by lch on 2015/1/14.
 */
public interface DeviceLogDao {
    //获取所有日志按日期倒序排列
    public void devicelogList(Page page,int flag) throws Exception;
    //根据ip按时间查询日志
    public void devicelogListByTimeAndIP(int startTime,int endTime,String logType,Page page,String ip,int flag) throws Exception;
    //可根据ip按信息类型查询日志
    public void devicelogListByTypeAndIp(String logType,Page page,String ip ,int flag) throws Exception;
    //添加日志
    public boolean save(DeviceLog deviceLog) throws Exception;
}
