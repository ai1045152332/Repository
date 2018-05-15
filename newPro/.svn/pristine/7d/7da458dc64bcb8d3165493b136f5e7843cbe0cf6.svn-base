package com.honghe.recordhibernate.dao;

import com.honghe.recordhibernate.entity.Temporaryplan;

import java.util.Calendar;
import java.util.List;

/**
 * Created by honghe on 2016/9/5.
 */
public interface TemporaryplanDao {
    /**
     * 新建临时计划
     * Author xinye
     * @param tempPlan
     */
    public abstract void addPlan(Temporaryplan tempPlan) throws Exception;

    /**
     * 修改临时计划
     * Author xinye
     * @param tempPlan
     * @throws Exception
     */
    public abstract void updatePlan(Temporaryplan tempPlan) throws  Exception;

    /**
     * 删除指定临时计划
     * Author xinye
     * @param tempId
     */
    public abstract  void deletePlan(final Integer tempId) throws Exception;

    /**
     * 查找临时计划列表
     * Author xinye
     *
     */
    public abstract List<Temporaryplan> tempPlanList() throws Exception;

    /**
     * 根据id查找临时计划
     * @param id
     * @return
     * @throws Exception
     */
    public abstract Temporaryplan getTempById(Integer id) throws Exception;

    /**
     * 根据与hostId 和 时间条件，查询指定时间之前的临时计划数据
     * Author xinye
     * @param hostId
     * @param calender 查询条件，Calendar的实例对象
     * @return
     * @throws Exception
     */
    public List getTempBeforeTime(int hostId,Calendar calender) throws  Exception;

    /**
     * 根据临时计划id，查找该计划下的主机名称和分组名称的列表
     * @param tempId 临时计划id
     * @return group_name ，host_name
     * @throws Exception
     */
    public List<Object[]> getGroupNameAndHostNameByTempId(int tempId) throws Exception;
}
