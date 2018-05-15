package com.honghe.recordhibernate.dao;

import com.honghe.recordhibernate.entity.ClasstimePloy;
import com.honghe.recordhibernate.entity.Hostgroup;

import java.util.List;
import java.util.Map;

/**
 * Created by chby on 2014/9/18.
 */

public interface HostgroupDao {
    public boolean hasHostgroup(int groupId)throws Exception;
    //获取分组是否绑定时间策略
    public boolean hasClasstimePloy(final int groupId) throws Exception;
    //根据id获取分组信息
    public Hostgroup getHostgroupInfo(int hgId) throws Exception;
    //获取所有分组信息
    public void getHostgroupList(Page<Hostgroup> page) throws Exception;
    //获取所有分组列表，不区分权限
    public List<Hostgroup> getHostgroupListAll() throws Exception;
    //保存分组信息
    public void saveHostgroup(Hostgroup hostgroup) throws Exception;
    //修改分组信息
    public boolean updateHostgroup(Hostgroup hostgroup) throws Exception;
    //删除分组信息
    public void delHostgroup(int id) throws Exception;
    //判断某一分组下是否已经分配了主机（教室）
    public boolean isHostgroupRelationExsist(int id) throws Exception;
    //删除分组下的关系
    public void delGroupRelationBySql(final int id, final String hostIdStr) throws Exception;
     // 根据用户id获取分组和主机信息
    public List<Object[]> getHostgroupList(int userId) throws Exception;
    //根据用户ID权限获取分组和主机信息，如果user为超级管理员，则取消权限限制
    public List<Object[]> getHostgroupListNew(final int userId) throws Exception;
    //统计分组个数
    public int count(final String conditions) throws Exception;
    public int countHostGroup(final String conditions) throws Exception;
    //获取分组列表,
    public List<Object[]> getHostgroupList2(final int userId) throws Exception;
    //删除分组与用户的关系
    public void delGroupWithUserBySql(final String id,final String userId) throws Exception;
    //删除分组与时间策略关系
    public void delGroupWithPloyBySql(final String id, final String ployId) throws Exception;

    public void delGroupWithHostBySql(final String groupId)throws Exception;
    //增加分组与用户的关系
    public void addGroupWithUserBySql(final String id,final String userId) throws Exception;
    //根据page获取分组列表（分页）
    public void getHostGroupListByPage(int userId, Page page) throws Exception;
    //根据hostgroup的ID 获取管理该组的用户
    public  List<Object[]> getUserForHostGroup(String hostGroupId) throws Exception;
    //根据为分组班级的host_id 查询host表及dspecification表信息
    public  List<Object[]> getHostAndDspecfication(String hostId) throws Exception;

    public Hostgroup getHostGroup(String name)  throws Exception;

    public List<Object[]> getDspecIds(String dtypeName) throws Exception;

    public List<Object[]> getHostIds(int groupId) throws Exception;
    //根据hostid获取所在分组信息
    public List<Object[]> getGroupInfoByhostId(int hostId) throws Exception;
    //获取平台中的所有组跟策略的关系列表
    public List<Object[]> getGroupPloyRel() throws Exception;
    //获取地点管理里面所有的地点列表
    public List<Object[]> getAllArea() throws Exception;
    //根据设备id获取绑定的地点信息
    public List<Object[]> getAreaByHostId(int hostId) throws Exception;
    //录播根据hostid获取作息策略
    public List<Object[]> getPloyNameById(int hostId) throws Exception;
}
