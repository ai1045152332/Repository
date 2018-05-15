package com.honghe.recordhibernate.dao;
import com.honghe.recordhibernate.entity.Resource;

import java.util.List;

/**
 * Created by hthwx on 2015/3/24.
 */

public interface ResourceDao
{
    public void getResourceListByPageSql(Page page, String str,String order) throws Exception;

    public void getResourceListByPageWithIpSql(Page page, String str,String order,String ip,String beginDate,String endDate) throws Exception;
    /**
     * 根据id获取资源的详细信息
     */
    public Resource getResourceInfoById(int rId) throws Exception;
    /**
     * 保存资源
     */
    public int addResource(Resource res) throws Exception;
    /**
     * 修改某条资源
     */
    public void updateResourceInfo(Resource res) throws Exception;

    public void updateResourceInfoBySql(String sql) throws Exception;
    /**
     * 删除某条资源
     */
    public void delResource(int rId) throws Exception;
    /**
     * 统计当前有多少资源
     */
    public int countResource() throws Exception;
    //统计当前符合条件的资源有多少
    public int countResourceBySql(String sql) throws Exception;
    /**
     * 查询最新的一条非空资源数据信息的res_path路径字段
     */
    public Object queryResourcePath() throws Exception;
    /**
     * 根据文件路径获取id
     */
    public Object getIdByPath(Resource res) throws Exception;
    /**
     * 判断mp4文件是否存在
     */
    public Object getDataexist(String name,String res_id) throws Exception;

    /**
     * 获取某个nas下资源时间超过约定自动删除时间的所有资源
     * @param nasIp
     * @param dateTime
     * @return
     * @throws Exception
     */
    public List<Object[]> getResourceNasDelTime(String nasIp, String dateTime) throws Exception;

}

