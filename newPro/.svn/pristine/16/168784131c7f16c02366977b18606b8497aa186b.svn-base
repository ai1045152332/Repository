package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.TResourceIndexDao;
import com.honghe.recordhibernate.entity.TResourceCatalog;
import com.honghe.recordhibernate.entity.TResourceIndex;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: 北京鸿合盛视数字媒体技术有限公司</p>
 *
 * @author Tpaduser
 * @date 2015/8/24
 */
@Repository
public class TResourceIndexDaoImpl extends BaseDao implements TResourceIndexDao {
    /**
     * todo 加注释
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> findById(int id) throws Exception{
        List<Object[]> result = null;
        String querystring = "select resouceStatus_id,id FROM t_resource_index where id="+id;
        final String sqlstr = querystring;
        result = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<List>() {
            @Override
            public List<Object[]> doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sqlstr).list();
            }
        });
        return result;
        //return this.getHibernateTemplate().get(TResourceIndex.class,id);
    }

    /**
     * todo 加注释
     * @param tResourceIndex
     * @throws Exception
     */
    @Override
    public void save(TResourceIndex tResourceIndex) throws Exception{
        this.getHibernateTemplate().save(tResourceIndex);
    }

    /**
     * todo 加注释
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public TResourceIndex findIndexById(int id) throws Exception{
        return this.getHibernateTemplate().get(TResourceIndex.class,id);
    }
}
