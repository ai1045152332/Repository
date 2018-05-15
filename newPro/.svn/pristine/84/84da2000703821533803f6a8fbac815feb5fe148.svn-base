package com.honghe.recordhibernate.dao;

import com.honghe.recordhibernate.entity.News;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by lch on 2014/9/23.
 */
public interface NewsDao
{
    //获取消息列表
    public List<News> getNewsList() throws Exception;

    public List<Object[]> getNewsObjectList() throws Exception;

    public void getNewsListByPage(Page page,String groupIds,String newsType,String deviceType) throws Exception;
    /**
     * 根据id获取消息的详细信息
     */
    public News getNewsInfoById(int newId) throws Exception;
    /**
     * 保存消息
     */
    public int addNews(News news) throws Exception;
    /**
     * 修改某条消息
     */
    public void updateNews(News news) throws Exception;
    /**
     * 删除某条信息
     */
    public void delNews(int newId) throws Exception;
    /**
     * 发送信息（添加该条信息与各设备的对应关系）
     */
    public void sendNews(int newId,int hostId,String hostName, int status) throws Exception;
    /**
     * sql语句执行某些操作，包括修改、删除、发送信息等
     */
    public void executeBySql(String sql,String exceptionStr) throws Exception;
    /**
     * 根据newId获取所有设备的名称
     */
    public Map<String,Object> getHostsByNewId(int newId) throws Exception;
    /**
     * 根据sql语句获取number
     */
    public int countNewsBySql(String sql) throws Exception;

    /**
     *根据sql语句获取查询结果集
     */
    public List<Object[]> querryNewsBySql(final String sql) throws Exception;

    public Page getRichNewsByUserId(Page page, String userId, String newsType, String deviceType) throws Exception;

    public Map<String,Object> getNewsInfo(String newsId) throws Exception;

    public Boolean delNewsHostByHost(int hostId) throws Exception;

    public Boolean isNewsHostExists(int hostId) throws Exception;
}
