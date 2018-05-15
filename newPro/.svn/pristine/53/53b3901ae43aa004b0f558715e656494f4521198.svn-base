package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.LogtypeDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lch on 2015/2/4.
 */
@Repository
public class LogtypeDaoImpl extends BaseDao implements LogtypeDao {
    /**
     * 获取日志类型
     * @param flag
     * @return
     * @throws Exception
     */
    @Override
    public List getLogType(int flag) throws Exception{
        List countList = null;
        final String s_sql;
        if(flag == 0){
            s_sql = "select log_type,log_name from logtype";
        }else {
            s_sql = "select log_type,log_name from logtype where log_type <> 'SYSTEM'";
        }
        countList = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<List>() {
            @Override
            public List doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(s_sql).list();
            }
        });
        return countList;
    }
}
