package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.SyslogDao;
import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.entity.Syslog;
import org.apache.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SyslogDaoImpl extends BaseDao implements SyslogDao{

    private final static Logger logger = Logger.getLogger(SyslogDaoImpl.class);

    /**
     * 获取所有日志按日期倒序排列
     * @param page
     * @param flag
     * @throws Exception
     */
    @Override
    public void syslogList(Page page,int flag) throws Exception{
        List<Object[]> result = null;
        List countList = null;
        String s_sql;
        if(flag == 0){
            s_sql = "select count(log_id) from syslog ";
        }else{
            s_sql = "select count(log_id) from syslog where log_type <> 'SYSTEM'";
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
            if(flag == 0){
                str_sql = "select sl.log_id, sl.log_user,sl.log_content,sl.log_host,sl.log_ip,sl.log_time,t.log_name from syslog sl ,logtype t where t.log_type = sl.log_type order by sl.log_time DESC  limit " + page.getFirstResult() + " , " + page.getPageSize();
            }else{
                str_sql = "select sl.log_id, sl.log_user,sl.log_content,sl.log_host,sl.log_ip,sl.log_time,t.log_name from syslog sl , logtype t where t.log_type = sl.log_type and sl.log_type <> 'SYSTEM' order by sl.log_time DESC  limit " + page.getFirstResult() + " , " + page.getPageSize();
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
    public void syslogListByTimeAndIP(int startTime,int endTime,String logType,Page page,String ip,int flag) throws Exception
    {
        List<Object[]> result = null;
        List countList = null;
        String s_sql;
        String str_sql;
        if(ip==null){
            ip = "";
        }
        if(logType.equals("ALL")){
            if(flag == 0){
                s_sql = "select count(log_id) from syslog sl where sl.log_time BETWEEN "+startTime+" and "+endTime;
            }else{
                s_sql = "select count(log_id) from syslog sl where sl.log_type <> 'SYSTEM' and sl.log_time BETWEEN "+startTime+" and "+endTime;
            }
        }else {
            s_sql = "select count(log_id) from syslog sl where sl.log_time BETWEEN "+startTime+" and "+endTime +" and sl.log_type = '"+logType+"'";
        }
        if(!ip.equals("")){
            s_sql += " and sl.log_ip = '"+ip+"'";
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
        str_sql = "select sl.log_id, sl.log_user,sl.log_content,sl.log_host,sl.log_ip,sl.log_time,t.log_name from syslog sl,logtype t where sl.log_type=t.log_type and sl.log_time BETWEEN "+startTime+" and "+endTime;
        if (totalCount != 0) {
            if(logType.equals("ALL")){
                if(flag != 0){
                    str_sql += " and sl.log_type <> 'SYSTEM'";
                }
                if(!ip.equals("")){
                    str_sql += " and sl.log_ip = '"+ip+"'";
                }
                str_sql += " order by sl.log_time DESC limit " + page.getFirstResult() + " , " + page.getPageSize();
            }else{
                if(!ip.equals("")){
                    str_sql += " and sl.log_ip = '"+ip+"'";
                }
                str_sql += " and sl.log_type = '"+logType+"' order by sl.log_time DESC limit " + page.getFirstResult() + " , " + page.getPageSize();
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
    public void syslogListByTypeAndIp(String logType,Page page,String ip,int flag) throws Exception
    {
        List<Object[]> result = null;
        List countList = null;
        String s_sql;
        String str_sql;
        if(ip==null){
            ip = "";
        }
        if(logType.equals("ALL")){
            s_sql = "select count(log_id) from syslog sl ";
            if(flag != 0){
                s_sql += " where sl.log_type <> 'SYSTEM'";
                if(!ip.equals("")){
                    s_sql += " and sl.log_ip = '"+ip+"'";
                }
            }
            else{
                if(!ip.equals("")){
                    s_sql += " where sl.log_ip = '"+ip+"'";
                }
            }

        }else {
            s_sql = "select count(log_id) from syslog sl where sl.log_type = '"+logType+"'";
            if(!ip.equals("")){
                s_sql += " and sl.log_ip = '"+ip+"'";
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
            str_sql = "select sl.log_id, sl.log_user,sl.log_content,sl.log_host,sl.log_ip,sl.log_time,t.log_name from syslog sl,logtype t where t.log_type=sl.log_type ";
            if(logType.equals("ALL")){
                if(flag != 0){
                    str_sql += " and sl.log_type <> 'SYSTEM'";
                    if(!ip.equals("")){
                        str_sql += " and sl.log_ip = '"+ip+"'";
                    }
                }else{
                    if(!ip.equals("")){
                        str_sql += " and sl.log_ip = '"+ip+"'";
                    }
                }
                str_sql += " order by sl.log_time DESC limit " + page.getFirstResult() + " , " + page.getPageSize();
            }else{
                str_sql += " and sl.log_type = '"+logType+"'";
                if(!ip.equals("")){
                    str_sql += " and sl.log_ip = '"+ip+"'";
                }
                str_sql += " order by sl.log_time DESC limit "+ page.getFirstResult() + " , " + page.getPageSize();
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
     * @param syslog
     * @return
     */
    @Override
    public boolean save(Syslog syslog) {
        try {
            this.getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
            this.getHibernateTemplate().save(syslog);
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("syslog添加失败:", e);
            return false;
        }
        return true;
    }

    /**
     * 获取id之前的日志
     * @param count
     * @param id
     * @param flag
     * @return
     */
    @Override
    public List<Object[]> syslogListById(int count,int id,int flag){
        List<Object[]> result = null;
        String str_sql;
        if(flag == 0){
            str_sql = "select sl.log_id, sl.log_user,sl.log_content,sl.log_host,sl.log_ip,sl.log_time,t.log_name from syslog sl ,logtype t where t.log_type = sl.log_type AND sl.log_id < "+id+" order by sl.log_time DESC LIMIT 0,"+ count;
        }else{
            str_sql = "select sl.log_id, sl.log_user,sl.log_content,sl.log_host,sl.log_ip,sl.log_time,t.log_name from syslog sl , logtype t where t.log_type = sl.log_type and sl.log_type <> 'SYSTEM' AND sl.log_id < " + id + " order by sl.log_time DESC LIMIT 0,"+ count;
        }
        final String sql = str_sql;
        result = this.getHibernateTemplate().execute(new HibernateCallback<List<Object[]>>() {
            @Override
            public List<Object[]> doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).list();
            }
        });
         return result;
    }

    /**
     * 根据ip按信息类型查询id以后的日志
     * @param logType
     * @param ip
     * @param count
     * @param id
     * @param flag
     * @return
     */
    @Override
    public List<Object[]> syslogListByTypeAndIpafterId(String logType, String ip ,int count,int id,int flag){
        List<Object[]> result = null;
        String str_sql = "select sl.log_id, sl.log_user,sl.log_content,sl.log_host,sl.log_ip,sl.log_time,t.log_name from syslog sl ,logtype t where t.log_type = sl.log_type";
        if(logType.equals("ALL")){
            if(flag != 0){
                str_sql += " and sl.log_type <> 'SYSTEM'";
                if(!ip.equals("")){
                    str_sql += " and sl.log_ip = '"+ip+"'";
                }
            }else{
                if(!ip.equals("")){
                    str_sql += " and sl.log_ip = '"+ip+"'";
                }
            }
            str_sql += " AND sl.log_id < "+id+" order by sl.log_time DESC limit  0,"+ count;
        }else{
            str_sql += " and sl.log_type = '"+logType+"'";
            if(!ip.equals("")){
                str_sql += " and sl.log_ip = '"+ip+"'";
            }
            str_sql += " AND sl.log_id < "+id+" order by sl.log_time DESC limit  0,"+ count;
        }
        final String sql = str_sql;
        result = this.getHibernateTemplate().execute(new HibernateCallback<List<Object[]>>() {
            @Override
            public List<Object[]> doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).list();
            }
        });
        return result;
    }

    /**
     * 根据ip按时间查询id以后的日志
     * @param startTime
     * @param endTime
     * @param logType
     * @param ip
     * @param count
     * @param id
     * @param flag
     * @return
     */
    public List<Object[]> syslogListByTimeAndIPafterId(int startTime,int endTime,String logType,String ip,int count,int id,int flag){

        List<Object[]> result = null;
        String str_sql = "select sl.log_id, sl.log_user,sl.log_content,sl.log_host,sl.log_ip,sl.log_time,t.log_name from syslog sl,logtype t where sl.log_type=t.log_type and sl.log_time BETWEEN "+startTime+" and "+endTime;
            if (logType.equals("ALL")) {
                if (flag != 0) {
                    str_sql += " and sl.log_type <> 'SYSTEM'";
                }
                if (!ip.equals("")) {
                    str_sql += " and sl.log_ip = '" + ip + "'";
                }
                str_sql += " AND sl.log_id < "+id+" order by sl.log_time DESC limit  0,"+ count;
            } else {
                str_sql +=" and sl.log_type = '" + logType + "'";
                if (!ip.equals("")) {
                    str_sql += " and sl.log_ip = '" + ip + "'";
                }
                str_sql += " AND sl.log_id < "+id+" order by sl.log_time DESC limit  0,"+ count;
            }

            final String sql = str_sql;
            result = this.getHibernateTemplate().execute(new HibernateCallback<List<Object[]>>() {
                @Override
                public List<Object[]> doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery(sql).list();
                }
            });
        return result;
    }

//    @Override
//    public List<Object[]> getGroupInfo(final Integer pageSize, final int currentPage) {
//        final String sql = "select g.group_name as groupName, count(group_name) as count, group_concat(distinct g2h.host_id) as hostIds\n" +
//                "from hostgroup g\n" +
//                "join group2host g2h\n" +
//                "on g.group_id=g2h.group_id\n" +
//                "group by group_name";
//
//
//        return this.getHibernateTemplate().execute(new HibernateCallback<List<Object[]>>() {
//            @Override
//            public List<Object[]> doInHibernate(Session session) throws HibernateException {
//                return session.createSQLQuery(sql).setFirstResult((currentPage-1)*pageSize).setMaxResults(pageSize).list();
//            }
//        });
//    }

	@Override
	public void getGroupInfo(Page page) {
		List<Object[]> result = new ArrayList<>();
		final String sql = "select g.group_name as groupName, count(distinct g2h.host_id) as count, IFNULL(group_concat(distinct g2h.host_id),0) as hostIds\n" +
				"from hostgroup g\n" +
				"LEFT join group2host g2h\n" +
				"on g.group_id=g2h.group_id\n" +
				"group by group_name limit "+ page.getFirstResult() + " , " + page.getPageSize();
		result = this.getHibernateTemplate().execute(new HibernateCallback<List<Object[]>>() {
			@Override
			public List<Object[]> doInHibernate(Session session) throws HibernateException {
				return session.createSQLQuery(sql).list();
			}
		});

		final String sql_count = "select count(group_id) from hostgroup";
		BigInteger totalCount = this.getHibernateTemplate().execute(new HibernateCallback<BigInteger>() {
			@Override
			public BigInteger doInHibernate(Session session) throws HibernateException {
				return (BigInteger) session.createSQLQuery(sql_count).uniqueResult();
			}
		});

//		int totalPage = 0;
//		if (totalCount.intValue() % page.getPageSize() > 0) {
//			totalPage = totalCount.intValue() / page.getPageSize() + 1;
//		} else {
//			totalPage = totalCount.intValue() / page.getPageSize();
//		}
		page.setTotalPageSize(totalCount.intValue());
		page.setResult(result);
	}

}
