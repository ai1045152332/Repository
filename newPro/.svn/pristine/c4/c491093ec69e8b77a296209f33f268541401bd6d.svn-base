package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.Spec2commandDao;
import com.honghe.recordhibernate.entity.Dspecification;
import com.honghe.recordhibernate.entity.Spec2command;
import org.apache.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lch on 2014/9/23.
 */
@Repository
public class Spec2commandDaoImpl extends BaseDao implements Spec2commandDao
{
    private final static Logger logger = Logger.getLogger(Spec2commandDaoImpl.class);

    /**
     * 通过设备id获取设备信息
     * @param dspecification
     * @return
     * @throws Exception
     */
    @Override
    public List getSpec2commandBySpecID(Dspecification dspecification) throws Exception
    {
        List list = null;

        Spec2command spec2command = new Spec2command();
        spec2command.setDspecification(dspecification);

        list = this.getHibernateTemplate().findByExample(spec2command);

        return list;
    }

    /**
     * 删除设备
     * @param dspecification1
     * @throws Exception
     */
    @Override
    public void delete(Dspecification dspecification1) throws Exception
    {
        int id = dspecification1.getDspecId();

        try {
            String hql = "delete Spec2command a where a.dspecification.dspecId in"+"("+id+")";
            Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
            int count = query.executeUpdate();
//            System.out.println(id+" delete count : " + count); //删除条数
            logger.debug(id+" delete count : " + count); //删除条数
        } catch (Exception e) {
            logger.error("删除失败"+e);
        }
    }

    /**
     * 保存设备
     * @param spec2command
     * @return
     * @throws Exception
     */
    @Override
    public boolean save(Spec2command spec2command) throws Exception
    {
        try
        {
            this.getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
            this.getHibernateTemplate().save(spec2command);
        }
        catch (Exception e) {
            //e.printStackTrace();
            logger.error("", e);
            return false;
        }
        return true;
    }

    /**
     * 根据型号删除关系
     * @param specid
     * @return
     * @throws Exception
     */
    @Override
    public int deleteRelation(final int specid) throws Exception
    {
        return (Integer) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("delete from Spec2command where dspec_id="+specid).executeUpdate();

            }
        });
    }
}
