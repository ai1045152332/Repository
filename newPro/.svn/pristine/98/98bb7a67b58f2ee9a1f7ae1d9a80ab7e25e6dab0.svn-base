package com.honghe.recordhibernate.dao;

import com.honghe.recordhibernate.entity.TResourceCatalog;
import com.honghe.recordhibernate.entity.TResourceIndex;
import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: 北京鸿合盛视数字媒体技术有限公司</p>
 *
 * @author Tpaduser
 * @date 2015/8/24
 */
public interface TResourceCatalogDao {

    public List<TResourceCatalog> getResourceCatalogsBySql(String sql) throws Exception;

    public List<TResourceIndex> getResourceIndexBySql(String sql) throws Exception;

    public List<TResourceCatalog> findByProperty(String propertyname,Object value) throws Exception;

    public List<TResourceCatalog> findByPropertyWidthOrder(String propertyname,Object value) throws Exception;

    public List<TResourceCatalog> findByQueryWithOrder(String type, String name, String virtual) throws Exception;

    public TResourceCatalog findById(int id) throws Exception;

    public void update(TResourceCatalog tResourceCatalog) throws Exception;

    public void saveOrUpdate(TResourceCatalog tResourceCatalog) throws Exception;

    public List<TResourceCatalog>getResourceCatalogByName(String name,int type,String fileName) throws Exception;

    public Boolean getItemByFid(int id) throws Exception;

    public void updateFileVirtual(int id,String newName,String oldName) throws Exception;

    public List<TResourceCatalog> findByPath(ArrayList list, String pathId) throws Exception;

    public List<Object[]> findFolderByPath(final String name, String pathId) throws Exception;

    public List<T> find(String hql) throws Exception;
}

