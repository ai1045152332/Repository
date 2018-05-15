package com.honghe.recordhibernate.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

/**
 * Created by Moon on 2014/8/19.
 */
public abstract class BaseDao extends HibernateDaoSupport
{

    @Autowired
    public void setSessionFactory0(SessionFactory sessionFactory)
    {
        super.setSessionFactory(sessionFactory);
    }

}
