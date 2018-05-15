package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.EventFieldDao;
import com.honghe.recordhibernate.entity.Eventfield;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hthwx on 2015/2/3.
 */
@Repository
public class EventFieldDaoImpl extends BaseDao implements EventFieldDao {
    /**
     * todo 加注释
     * @param efId
     * @return
     * @throws Exception
     */
    @Override
    public Eventfield getEventFieldInfo(int efId) throws Exception {
        return null;
    }

    /**
     * todo 加注释
     * @return
     * @throws Exception
     */
    @Override
    public List<Eventfield> getEventFieldList() throws Exception {
        return (List<Eventfield>) this.getHibernateTemplate().find("from Eventfield e");
    }

    /**
     * todo 加注释
     * @param belong
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getEventFieldListByBelong(String belong) throws Exception
    {
        final String sql = "select field_belong,field_name,field_content,field_remark from eventfield where field_belong ='" + belong + "'";
        return (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).list();

            }
        });
    }
}
