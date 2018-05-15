package com.honghe.recordhibernate.dao;

import com.honghe.recordhibernate.entity.Syslog;

import java.util.List;

/**
 * Created by lch on 2015/1/14.
 */
public interface SyslogDao {
    //获取所有日志按日期倒序排列
    public void syslogList(Page page, int flag) throws Exception;
    //根据ip按时间查询日志
    public void syslogListByTimeAndIP(int startTime, int endTime, String logType, Page page, String ip, int flag) throws Exception;
    //可根据ip按信息类型查询日志
    public void syslogListByTypeAndIp(String logType, Page page, String ip, int flag) throws Exception;
    //添加日志
    public boolean save(Syslog syslog) throws Exception;
    //获取id之前的日志
    public List<Object[]> syslogListById(int count,int id,int flag);
    //根据ip按信息类型查询id以后的日志
    public List<Object[]> syslogListByTypeAndIpafterId(String logType, String ip ,int count,int id,int flag);
    //根据ip按时间查询id以后的日志
    public List<Object[]> syslogListByTimeAndIPafterId(int startTime,int endTime,String logType,String ip,int count,int id,int flag);

//    List<Object[]> getGroupInfo(Integer pageSize, int currentPage);
    void getGroupInfo(Page page);
}
