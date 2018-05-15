package com.honghe.recordweb.service.frontend.signalplan;

import com.honghe.recordhibernate.entity.PageBean;
import com.honghe.recordhibernate.entity.Signalplan;

import java.util.List;
import java.util.Map;

/**
 * Created by yanxue on 2015-06-25.
 */
public interface SignalplanService {
    /***
     * 根据page类方法，返回分页的分组数据
     * @param currentPage
     * @param pageSize
     * @param uid
     * @return
     */
    public PageBean  signalplanListService(int currentPage, int pageSize, int uid,String type);

    /**
     * 判断某一id的定时计划是否发布给班级
     * @param id
     * @return
     */
    public boolean  signalplanHostExistsService(int id);

    /**
     * 删除某定时计划的所有班级关系
     * @param id
     * @return
     */
    boolean delSignalplanHostHostService(int id);

    /**
     * 将定时计划发布给一个班级
     * @param scheduleId
     * @param hostId
     * @return
     */
    boolean addSignalplanToHostService(int scheduleId, int hostId,String hostName);


    /**
     *获取定时计划信息
     * @param id
     * @return
     */
    public List<Map> getSignalplanInfoService(int id);


    /**
     * 获取定时计划信息
     * @param id
     * @return
     */
    public Signalplan getSignalplanService(int id);


    /**
     * 保存定时计划的信息
     * @param signalplan
     * @param hostStr
     * @return
     */
    public boolean addSignalplanService(Signalplan signalplan, String hostStr,String hostNameStr,String hostIpStr);


    /**
     * 更新定时计划信息
     * @param signalplan
     * @param hostStr
     * @return
     */
    public boolean updateSignalplanService(Signalplan signalplan, String hostStr,String hostNameStr,String hostIpStr);


    /**
     * 删除定时计划信息
     * @param signalplan
     * @param hostStr
     * @return
     */
    public boolean delSignalplanService(Signalplan signalplan, String hostStr);

    /**
     * 删除某班级的定时计划信息
     * @param hid
     * @return
     */
    public boolean delSignalplanByHostService(int hid);

}
