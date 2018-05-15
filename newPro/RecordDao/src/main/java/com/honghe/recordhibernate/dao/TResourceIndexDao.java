package com.honghe.recordhibernate.dao;

import com.honghe.recordhibernate.entity.TResourceIndex;

import java.util.List;

/**
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: 北京鸿合盛视数字媒体技术有限公司</p>
 *
 * @author Tpaduser
 * @date 2015/8/24
 */
public interface TResourceIndexDao {

    public List<Object[]> findById(int id) throws Exception;

    public void save(TResourceIndex tResourceIndex) throws Exception;

    public TResourceIndex findIndexById(int id) throws Exception;
}

