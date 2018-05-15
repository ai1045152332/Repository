package com.honghe.recordweb.service.frontend.ringbell;

import com.honghe.recordhibernate.entity.PageBean;
import com.honghe.recordhibernate.entity.Ringbell;

import java.util.List;
import java.util.Map;

/**
 * Created by yanxue on 2015-06-23.
 */
public interface RingbellService {

    /***
     * 根据page类方法，返回分页的分组数据
     * @param currentPage
     * @param pageSize
     * @param uid
     * @return
     */
    public PageBean ringbellListService(int currentPage, int pageSize, int uid, String type);

    /**
     * 判断某一id的定时计划是否发布给班级
     * @param id
     * @return
     */
    public boolean ringbellHostExistsService(int id);

    /**
     * 删除某定时计划的所有班级关系
     * @param id
     * @return
     */
     boolean delRingbellHostHostService(int id);

    /**
     * 将定时计划发布给一个班级
     * @param scheduleId
     * @param hostId
     * @return
     */
     boolean addRingbellToHostService(int scheduleId, int hostId,String hostName);


    /**
     *获取定时计划信息
     * @param id
     * @return
     */
    public List<Map> getRingbellInfoService(int id);


    /**
     * 获取定时计划信息
     * @param id
     * @return
     */
    public Ringbell getRingbellService(int id);


    /**
     * 保存定时计划的信息
     * @param ringbell
     * @param hostStr
     * @return
     */
    public boolean addRingbellService(Ringbell ringbell, String hostStr,String hostNameStr,String hostIpStr);


    /**
     * 更新定时计划信息
     * @param ringbell
     * @param hostStr
     * @return
     */
    public boolean updateRingbellService(Ringbell ringbell, String hostStr,String hostNameStr,String hostIpStr);


    /**
     * 删除定时计划信息
     * @param ringbell
     * @param hostStr
     * @return
     */
    public boolean delRingbellService(Ringbell ringbell, String hostStr);

    /**
     * 删除某班级的定时计划信息
     * @param hid
     * @return
     */
    public boolean delRingbellByHostService(int hid);


}
