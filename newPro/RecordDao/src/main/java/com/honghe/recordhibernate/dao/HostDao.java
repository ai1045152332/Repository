package com.honghe.recordhibernate.dao;

import com.honghe.recordhibernate.entity.Host;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;


/**
 * Created by Moon on 2014/9/11.
 * Alter by wzz on 2015/07/15 设备管理服务整合
 */
public interface HostDao {
    //获取主机列表
    public List<Host> getHostList() throws Exception;

    public List<Object[]> getHostList2(int userId) throws Exception;
    //根据条件获取教室（主机）列表
    public List<Object[]> getHostListFromSql(final int hostgroupId) throws Exception;

    public List<Object[]> getUnknowGroup()throws Exception;

    public List<Object[]> getHostsInGroup() throws Exception;
    //获取分组下的教室列表并分页
    public List<Object[]> getHostListFromSqlBypage(final int hostgroupId, String condition, Page page) throws Exception;
    //获取主机信息
    public Host getHostInfo(int id) throws Exception;
    //保存主机信息
    public void saveHost(Host host) throws Exception;

    public Integer addHost(Host host)throws Exception;
    //修改主机信息
    public boolean updateHost(Host host) throws Exception;
    //删除主机信息
    public void delHost(int id) throws Exception;
    //将教室分配给某个分组
    public void addHostToGroup(int hostId, int groupId) throws Exception;
    //将该教室所分配的组关系删除
    public void delHostRelationBySql(final int id) throws Exception;
    //查询该ID的教室有分组关系
    public boolean isHostRelationExsist(final int id) throws Exception;
    //统计host的数量,可加条件限制
    public int count(final String conditions) throws Exception;
    //修改主机型号
    public void updateHostDspec(int hostId, int dspecId) throws Exception;
    //移动主机到新分组
    public void moveHostToNewGroup(final int hostId, final int groupId, final int groupIdOld) throws Exception;

    public Host findHost(String ip)throws Exception;
    //根据型号id获取host
    public List getHostBySpec(int specid) throws Exception;
    //更新主机信息
    public void updateHostInfo(Host host) throws Exception;

    public Host getHost(String name) throws Exception;

    public void delHostDeviceBySql(final int id) throws Exception;

    public boolean isHostDeviceExsist(final int id) throws Exception;

    public void updateHostUserPasswordWrongList(final String ip)throws Exception;
    //add by xinye
    //临时计划添加主机
    public void addHostToTemporaryplan(final Integer hostId,final Integer tempId) throws Exception;

    public List fetchHostInTemp2Host(final Integer tempId) throws Exception;

    public void delRelationToTemp(final int tempId) throws Exception;
    //add by xinye end
    public Map<String,String> getHostInfoByIp(String deviceip)throws Exception;
    //add  zgh

    public List getHostListByUserId(Integer userId);//add by zlj
}
