package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.DeviceConfigDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhanghongbin on 2015/1/8.
 */
@Repository
public class DeviceConfigDaoImpl extends BaseDao implements DeviceConfigDao {
    /**
     * todo 加注释
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getDeviceConfigList() throws Exception {
        return (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select connect_param, name,main,sub,is_ptz from deviceconfig").list();
            }
        });

    }

    /**
     * todo 加注释
     * @param connect_param
     * @return
     * @throws Exception
     */
    public List<Object[]> getDeviceConfigList(final String connect_param) throws Exception {
        return (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select connect_param, name,main,sub,is_ptz from deviceconfig where connect_param = '" + connect_param + "'").list();
            }
        });
    }

    /**
     * 初始化数据库中的TBOX的自定义名称
     * Author xinye
     * @param name
     * @param main
     * @throws Exception
     */
//    @Override
//    public void initDeviceConfig(String name,String main) throws Exception {
//        final String sql = "UPDATE `deviceconfig` SET NAME = '"+name+"' WHERE main = '"+main+"'";
//        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
//            @Override
//            public Object doInHibernate(Session session) throws HibernateException {
//                session.flush();
//                session.createSQLQuery(sql).executeUpdate();
//                return null;
//            }
//        });
//    }
}
