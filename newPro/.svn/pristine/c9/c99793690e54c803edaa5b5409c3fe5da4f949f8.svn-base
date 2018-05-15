package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.MonitorDao;
import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.entity.Monitor;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * Created by yanxue on 2015-07-07.
 */
@Repository
public class MonitorDaoImpl extends BaseDao implements MonitorDao {

    private final static Logger logger = Logger.getLogger(MonitorDaoImpl.class);

    /**
     * 保存软件监控信息
     * @param monitor
     * @return
     * @throws Exception
     */
    @Override
    public boolean saveMonitor(Monitor monitor) throws Exception {

        try {
            this.getHibernateTemplate().save(monitor);
            return true;
        } catch (DataAccessException e) {
            //e.printStackTrace();
            logger.error("", e);
            return false;
        }

    }

    /**
     * 请求软件监控信息列表
     * @param page
     * @param mac
     * @param startTime
     * @param endTime
     * @throws Exception
     */
    @Override
    public void getMonitorList(Page<Monitor> page, String mac, String startTime, String endTime) throws Exception {

        HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
        List countList = hibernateTemplate.find("select count(*) from Monitor");
        int totalCount = Integer.parseInt(countList.get(0).toString());
        page.setTotalPageSize(totalCount);
        if (totalCount != 0) {
            DetachedCriteria crit = DetachedCriteria.forClass(Monitor.class);

            if (mac != null && !mac.isEmpty() && startTime != null && !startTime.isEmpty() && endTime != null && !endTime.isEmpty()) {
                crit.add(Restrictions.and(Restrictions.eq("mac", mac), Restrictions.and(Restrictions.ge("createTime", Date.valueOf(startTime)), Restrictions.le("createTime", Date.valueOf(endTime)))));
            } else if (startTime != null && !startTime.isEmpty() && endTime != null && !endTime.isEmpty()) {
                crit.add(Restrictions.and(Restrictions.ge("createTime", Date.valueOf(startTime)), Restrictions.le("createTime", Date.valueOf(endTime))));
            } else if (mac != null && !mac.isEmpty()) {
                crit.add(Restrictions.eq("mac", mac));
            }

            crit.addOrder(Order.desc("id"));
            List result = hibernateTemplate.findByCriteria(crit, page.getFirstResult(), page.getPageSize());
            page.setResult(result);
        }

    }


    /**
     * 获取软件活跃度排名
     *
     * @return
     */
    @Override
    public List<Object[]> getRanking() {
        final String sql = "SELECT  m.m_softname ,SUM(m.m_usetime) FROM monitor m  GROUP BY m.m_softname  ORDER BY m.m_usetime ASC";
        return (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).list();
            }
        });
    }

    /**
     * 依据host物理地址获取该设备的软件使用情况
     *
     * @param mac
     * @param startTime
     * @param endTime   @return
     */
    @Override
    public List<Object[]> getSoftConditionByHostMac(String mac, String startTime, String endTime) {

        if (null == mac) {
            return null;
        }

        startTime = null == startTime ? "" : startTime;
        endTime = null == endTime ? "" : endTime;

        final String strSql = "SELECT  m.m_softname , SUM(m.m_usetime) FROM monitor m  " +
                "WHERE m.m_mac = '" + mac + "' AND m.m_createtime BETWEEN '" + startTime + "' AND '" + endTime + "' " +
                "GROUP BY m.m_softname";

        final String sql = strSql;
        return (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(strSql).list();
            }
        });
    }

    /**
     * 依据host物理地址获取该设备的软件使用情况
     *
     * @param mac
     * @return
     */
    @Override
    public List<Object[]> getSoftConditionByHostMac(String mac) {

        if (null == mac) {
            return null;
        }


        final String strSql = "SELECT  m.m_softname , SUM(m.m_usetime) FROM monitor m  " +
                "WHERE m.m_mac = '" + mac + "' GROUP BY m.m_softname";

        final String sql = strSql;

        return (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(strSql).list();
            }
        });
    }
    /**
     * 获取置顶软件列表
     *
     *
     * @return
     */
    public List<Object[]> getMonitorList(){

        final String strSql = "SELECT * FROM(SELECT m_mac,m_devicename FROM monitor ORDER BY m_id DESC)d " +
                " GROUP BY d.m_mac";

        return (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(strSql).list();
            }
        });
    }


}
