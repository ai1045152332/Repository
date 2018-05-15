package com.honghe.recordhibernate.dao;

import com.honghe.recordhibernate.entity.TResource;
import com.honghe.recordhibernate.entity.form.JsonForm;
import com.honghe.recordhibernate.entity.form.ResourceForm;

import java.util.List;

/**
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: 北京鸿合盛视数字媒体技术有限公司</p>
 *
 * @author Tpaduser
 * @date 2015/8/24
 */
public interface TResourceDao {

    public List<JsonForm> selectResources(String virtual, String type, String srhText) throws Exception;

    public List<ResourceForm> selectResource(int rid) throws Exception;

    public Integer executeHql(String hql) throws Exception;

    public Integer save(TResource tResource) throws Exception;

    public TResource findById(int id) throws Exception;
}

