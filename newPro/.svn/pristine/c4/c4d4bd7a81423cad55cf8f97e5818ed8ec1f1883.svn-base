package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.TResouceStatusDao;
import com.honghe.recordhibernate.entity.TResourceStatus;
import org.hibernate.HibernateException;
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
 * @date 2015/8/27
 */
@Repository
public class TResouceStatusDaoImpl extends BaseDao implements TResouceStatusDao {
    /**
     * todo 加注释
     * @param tResourceStatus
     * @throws Exception
     */
    @Override
    public void update(TResourceStatus tResourceStatus) throws Exception{
        Session session = this.getHibernateTemplate().getSessionFactory().openSession();
        session.beginTransaction();
        session.update(tResourceStatus);
        session.getTransaction().commit();
        session.close();
       }

    /**
     * todo 加注释
     * @param tResourceStatus
     * @throws Exception
     */
    @Override
    public void update1(TResourceStatus tResourceStatus) throws Exception{

       this.getHibernateTemplate().update(tResourceStatus);
    }

    /**
     * todo 加注释
     * @param resourceStatus
     * @return
     * @throws Exception
     */
    @Override
    public Integer save(TResourceStatus resourceStatus) throws Exception{
        return (Integer) this.getHibernateTemplate().save(resourceStatus);
    }

    /**
     * todo 加注释
     * @param resourceStatutsId
     * @return
     * @throws Exception
     */
    @Override
    public TResourceStatus getResourceStatus(int resourceStatutsId) throws Exception{
        return this.getHibernateTemplate().get(TResourceStatus.class,resourceStatutsId);
    }

    /**
     * todo 加注释
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> findById(int id) throws Exception{
        List<Object[]> result = null;
        String querystring = "select uploadtime,id FROM t_resource_status where id="+id;
        final String sqlstr = querystring;
        result = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<List>() {
            @Override
            public List<Object[]> doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sqlstr).list();
            }
        });
        return result;
        //return this.getHibernateTemplate().get(TResourceStatus.class,id);
}

    /**
     * todo 加注释
     * @param hql
     * @return
     * @throws Exception
     */
    @Override
    public Integer executeHql(String hql) throws Exception {
        Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        int id = session.createQuery(hql).executeUpdate();
        session.getTransaction().commit();
        session.flush();
        session.close();
        return id;
    }
}
