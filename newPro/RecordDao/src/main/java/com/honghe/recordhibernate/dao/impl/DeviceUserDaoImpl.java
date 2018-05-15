package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.DeviceUseDao;
import com.honghe.recordhibernate.entity.DeviceUse;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lyx on 2015-08-15.
 */
@Repository
public class DeviceUserDaoImpl extends BaseDao implements DeviceUseDao {
    private final static  Logger logger = Logger.getLogger(DeviceUserDaoImpl.class);

    /**
     * 保存设备使用信息
     *
     * @param deviceUse
     * @return
     * @throws Exception
     */
    @Override
    public boolean saveDevice(DeviceUse deviceUse) throws Exception {
        try {
            this.getHibernateTemplate().save(deviceUse);
            return true;
        } catch (DataAccessException e) {
            //e.printStackTrace();
            logger.error("", e);
            return false;
        }
    }

    /**
     * 获取设备活跃度排名基础信息
     *
     * @return
     */
    @Override
    public List<Object[]> getRanking(String startTime, String endTime) {
        startTime = null == startTime ? "" : startTime;
        endTime = null == endTime ? "" : endTime;

        final String strSql = "SELECT d.d_hostname , SUM(d.d_usertime) FROM deviceuse d  " +
                " WHERE d.d_createtime BETWEEN '" + startTime + "' AND '" + endTime + "' " +
                " GROUP BY d.d_hostname";

        final String sql = strSql;
        return (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(strSql).list();
            }
        });

    }
}
