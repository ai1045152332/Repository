package com.honghe.recordhibernate.dao;

import com.honghe.recordhibernate.entity.TResourceStatus;

import java.util.List;

/**
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: 北京鸿合盛视数字媒体技术有限公司</p>
 *
 * @author Tpaduser
 * @date 2015/8/27
 */
public interface TResouceStatusDao {

    public void update(TResourceStatus tResourceStatus) throws Exception;

    public void update1(TResourceStatus tResourceStatus) throws Exception;

    public Integer save(TResourceStatus resourceStatus) throws Exception;

    public TResourceStatus getResourceStatus(int resourceStatutsId) throws Exception;

    public List<Object[]> findById(int id) throws Exception;

    public Integer executeHql(String hql) throws Exception;
}
