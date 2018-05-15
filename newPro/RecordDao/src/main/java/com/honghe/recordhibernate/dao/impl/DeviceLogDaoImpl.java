package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.DeviceLogDao;
import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.entity.DeviceLog;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lch on 2015/1/14.
 */
@Repository
public class DeviceLogDaoImpl extends BaseDao implements DeviceLogDao {

    private final static  Logger logger = Logger.getLogger(DeviceLogDaoImpl.class);

    /**
     * 获取所有日志按日期倒序排列
     * @param page
     * @param flag
     * @throws Exception
     */
    @Override
    public void devicelogList(Page page, int flag) throws Exception {
        List<Object[]> result = null;
        List countList = null;
        String s_sql;
        if (flag == 0) {
            s_sql = "select count(log_id) from device_log dl";
        } else {
            s_sql = "select count(log_id) from device_log dl where log_type <> 'SYSTEM'";
        }

        final String s_sql_count = s_sql;
        countList = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<List>() {
            @Override
            public List doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(s_sql_count).list();
            }
        });
        int totalCount = Integer.parseInt(countList.get(0).toString());
        page.setTotalPageSize(totalCount);
        if (totalCount != 0) {
            String str_sql;
            if (flag == 0) {
                str_sql = "select dl.log_id, dl.log_user,dl.device_name,dl.device_ip,dl.log_time,dl.log_desc,t.logtype_name from device_log dl ,device_log_type t where t.logtype_key = dl.log_type order by dl.log_time DESC  limit " + page.getFirstResult() + " , " + page.getPageSize();
            } else {
                str_sql = "select dl.log_id, dl.log_user,dl.device_name,dl.device_ip,dl.log_time,dl.log_desc,t.logtype_name from device_log dl , device_log_type t where t.logtype_key = dl.log_type and dl.log_type <> 'SYSTEM' order by dl.log_time DESC  limit " + page.getFirstResult() + " , " + page.getPageSize();
            }
            final String sql = str_sql;
            result = this.getHibernateTemplate().execute(new HibernateCallback<List<Object[]>>() {
                @Override
                public List<Object[]> doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery(sql).list();
                }
            });
        }
        page.setResult(result);
    }

    /**
     * 根据ip按时间查询日志
     * @param startTime
     * @param endTime
     * @param logType
     * @param page
     * @param ip
     * @param flag
     * @throws Exception
     */
    @Override
    public void devicelogListByTimeAndIP(int startTime, int endTime, String logType, Page page, String ip, int flag) throws Exception {
        List<Object[]> result = null;
        List countList = null;
        String s_sql;
        String str_sql;
        if (logType.equals("ALL")) {
            if (flag == 0) {
                s_sql = "select count(log_id) from device_log dl where dl.log_time BETWEEN " + startTime + " and " + endTime;
            } else {
                s_sql = "select count(log_id) from device_log dl where dl.log_type <> 'SYSTEM' and dl.log_time BETWEEN " + startTime + " and " + endTime;
            }
        } else {
            s_sql = "select count(log_id) from device_log dl where dl.log_time BETWEEN " + startTime + " and " + endTime + " and dl.log_type = '" + logType + "'";
        }
        if (!ip.equals("")) {
            s_sql += " and dl.device_ip = '" + ip + "'";
        }

        final String s_sql_count = s_sql;
        countList = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<List>() {
            @Override
            public List doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(s_sql_count).list();
            }
        });
        int totalCount = Integer.parseInt(countList.get(0).toString());
        page.setTotalPageSize(totalCount);
        str_sql = "select dl.log_id, dl.log_user,dl.device_name,dl.device_ip,dl.log_time,dl.log_desc,t.logtype_name from device_log dl,device_log_type t where dl.log_type=t.logtype_key and dl.log_time BETWEEN " + startTime + " and " + endTime;
        if (totalCount != 0) {
            if (logType.equals("ALL")) {
                if (flag != 0) {
                    str_sql += " and dl.log_type <> 'SYSTEM'";
                }
                if (!ip.equals("")) {
                    str_sql += " and dl.device_ip = '" + ip + "'";
                }
                str_sql += " order by dl.log_time DESC limit " + page.getFirstResult() + " , " + page.getPageSize();
            } else {
                if (!ip.equals("")) {
                    str_sql += " and dl.device_ip = '" + ip + "'";
                }
                str_sql += " and dl.log_type = '" + logType + "' order by dl.log_time DESC limit " + page.getFirstResult() + " , " + page.getPageSize();
            }

            final String sql = str_sql;
            result = this.getHibernateTemplate().execute(new HibernateCallback<List<Object[]>>() {
                @Override
                public List<Object[]> doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery(sql).list();
                }
            });
        }
        page.setResult(result);
    }

    /**
     * 可根据ip按信息类型查询日志
     * @param logType
     * @param page
     * @param ip
     * @param flag
     * @throws Exception
     */
    @Override
    public void devicelogListByTypeAndIp(String logType, Page page, String ip, int flag) throws Exception {
        List<Object[]> result = null;
        List countList = null;
        String s_sql;
        String str_sql;
        if (logType.equals("ALL")) {
            s_sql = "select count(log_id) from device_log dl";
            if (flag != 0) {
                s_sql += " where dl.log_type <> 'SYSTEM'";
                if (!ip.equals("")) {
                    s_sql += " and dl.device_ip = '" + ip + "'";
                }
            } else {
                if (!ip.equals("")) {
                    s_sql += " where dl.device_ip = '" + ip + "'";
                }
            }

        } else {
            s_sql = "select count(log_id) from device_log dl where dl.log_type = '" + logType + "'";
            if (!ip.equals("")) {
                s_sql += " and dl.device_ip = '" + ip + "'";
            }
        }

        final String s_sql_count = s_sql;
        countList = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<List>() {
            @Override
            public List doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(s_sql_count).list();
            }
        });
        int totalCount = Integer.parseInt(countList.get(0).toString());
        page.setTotalPageSize(totalCount);
        if (totalCount != 0) {
            str_sql = "select dl.log_id, dl.log_user,dl.device_name,dl.device_ip,dl.log_time,dl.log_desc,t.logtype_name from device_log dl,device_log_type t where t.logtype_key=dl.log_type ";
            if (logType.equals("ALL")) {
                if (flag != 0) {
                    str_sql += " and dl.log_type <> 'SYSTEM'";
                    if (!ip.equals("")) {
                        str_sql += " and dl.device_ip = '" + ip + "'";
                    }
                } else {
                    if (!ip.equals("")) {
                        str_sql += " and dl.device_ip = '" + ip + "'";
                    }
                }
                str_sql += " order by dl.log_time DESC limit " + page.getFirstResult() + " , " + page.getPageSize();
            } else {
                str_sql += " and dl.log_type = '" + logType + "'";
                if (!ip.equals("")) {
                    str_sql += " and dl.device_ip = '" + ip + "'";
                }
                str_sql += " order by dl.log_time DESC limit " + page.getFirstResult() + " , " + page.getPageSize();
            }
            final String sql = str_sql;
            result = this.getHibernateTemplate().execute(new HibernateCallback<List<Object[]>>() {
                @Override
                public List<Object[]> doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery(sql).list();
                }
            });
        }
        page.setResult(result);
    }

    /**
     * 添加日志
     * @param deviceLog
     * @return
     */
    @Override
    public boolean save(DeviceLog deviceLog) {
        try {
            //this.getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
            this.getHibernateTemplate().save(deviceLog);
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("", e);
            return false;
        }
        return true;
    }
}
