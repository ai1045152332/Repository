package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.LayoutWindowDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhanghongbin on 2015/1/14.
 */
@Repository
public class LayoutWindowDaoImpl extends BaseDao implements LayoutWindowDao {
    /**
     * todo 加注释
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, String> getLayoutWindow() throws Exception {
        List<Object[]> objList = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select name,layoutId from layoutwindow").list();
            }
        });
        Map<String,String> result = new HashMap<>();
        for(Object[] obj:objList){
            result.put(obj[0].toString(),obj[1].toString());
        }
        return result;
    }
}
