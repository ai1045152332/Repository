package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.TimeplanDao;
import com.honghe.recordhibernate.entity.Classtime;
import com.honghe.recordhibernate.entity.Curriculum;
import com.honghe.recordhibernate.entity.Timeplan;
import org.apache.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lch on 2014/9/26.
 * Altered by wzz on 2016/07/19 ClasstimeID改为sectionID
 */
@Repository
public class TimeplanDaoImpl extends BaseDao implements TimeplanDao {
//    private final static Logger logger = Logger.getLogger(TimeplanDaoImpl.class);

    /**
     * todo 加注释
     * @return
     * @throws Exception
     */
    @Override
    public List<Integer> getAllPan() throws Exception {
           return  (List<Integer>)this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
               @Override
               public Object doInHibernate(Session session) throws HibernateException {
                   String sql = "SELECT distinct(host_id) FROM timeplan";
                   return session.createSQLQuery(sql).list();
               }
           });
    }

    /**
     *  todo 加注释
     * @param week_id
     * @return
     * @throws Exception
     */
    @Override
    public List<Timeplan> getTimeplanList(final Byte week_id) throws Exception {
        List list = (List<Timeplan>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createQuery("from Timeplan where timeplanWeek = :weekid").setParameter("weekid",week_id).list();

            }
        });
        return list;
    }

//    @Override
//    public List<Classtime> getClasstimeList() throws Exception {
//        List list = (List<Classtime>) this.getHibernateTemplate().find("from Classtime");
//        return list;
//    }

    /**
     *  todo 加注释
     * @param timeplan
     * @return
     * @throws Exception
     */
    @Override
    public boolean save(Timeplan timeplan) throws Exception{
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
        this.getHibernateTemplate().save(timeplan);
        return true;
    }

    /**
     *  todo 加注释
     * @param timeplan
     * @return
     * @throws Exception
     */
    @Override
    public boolean update(Timeplan timeplan) throws Exception {
        this.getHibernateTemplate().update(timeplan);
        return true;
    }

    /**
     *  todo 加注释
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public boolean delete(final int id) throws Exception {
        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                String sql = "delete from Timeplan where timeplanId="+id;
                Integer datacount = session.createQuery(sql).executeUpdate();
                return datacount;
            }
        });
        return true;
    }

//    @Override
//    public List getHostTimeplan(Byte week_id) throws Exception {
//        List list = null;
//        list = this.getHibernateTemplate().find("from Host h LEFT JOIN Timeplan p on p.hostId = h.hostId where p.timeplanWeek = " + week_id);
//        return list;
//    }

    /**
     *  todo 加注释
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public boolean clearPlan(final int id) throws Exception {
        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                String sql = "delete from Timeplan where hostId="+id;
                Integer datacount = session.createQuery(sql).executeUpdate();
                return datacount;
            }
        });
        return true;
    }

    /**
     *  todo 加注释
     * @param hostid
     * @return
     * @throws Exception
     */
    @Override
    public boolean isHavePlan(final int hostid) throws Exception {
        BigInteger result = (BigInteger)this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select count(*) from timeplan where host_id=" + hostid).uniqueResult();
            }
        });
        if(result.intValue() == 0)return false;
        return true;
    }

    /**
     *  todo 加注释
     * @param hostid
     * @throws Exception
     */
    @Override
    public void delPlanByHost(final int hostid) throws Exception {
        Integer result = (Integer) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
               Integer datacount = session.createSQLQuery("delete from timeplan where host_id=" + hostid).executeUpdate();
                return datacount;
            }
        });
        if (result == 0) throw new Exception("delete failure");
    }

    /**
     *  todo 加注释
     * @param hostid
     * @throws Exception
     */
    @Override
    public void delHostPlan(final int hostid) throws Exception {
        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
               Integer datacount = session.createSQLQuery("delete from timeplan where host_id=" + hostid).executeUpdate();
                return datacount;
            }
        });
    }

    /**
     * 暂时没有用到，不改
     *  todo 加注释
     * @param classtimeid
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getTimeplanByClasstimeid(int classtimeid) throws Exception {
        String sql = "select t.timeplan_id,t.timeplan_date,t.timeplan_week,t.host_id,t.section_id " +
                     "from timeplan t " +
                     "      INNER JOIN classtime c ON t.section_id = c.classtime_section " +
                     "      INNER JOIN classtime_ploy p ON c.classtime_ploy = p.ploy_id " +
                     "      INNER JOIN classtimeploy2group p2g ON p2g.ploy_id = p.ploy_id " +
                     "      INNER JOIN group2host g2h on p2g.group_id = g2h.host_id and t.host_id = g2h.host_id " +
                     "where c.classtime_id = " +classtimeid+" "+
                     "UNION " +
                     "select t.timeplan_id,t.timeplan_date,t.timeplan_week,t.host_id,t.section_id " +
                     "from timeplan t " +
                     "     INNER JOIN classtime c ON t.section_id = c.classtime_section " +
                     "     INNER JOIN classtime_ploy p ON c.classtime_ploy = p.ploy_id " +
                     "     INNER JOIN classtimeploy2group p2g ON p2g.ploy_id = p.ploy_id and p2g.group_id = -1 " +
                     "where c.classtime_id = " +classtimeid;
        final String sql_f = sql;
        List<Object[]> list = (List<Object[]>)this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql_f).list();
            }
        });
        return list;
    }

//    @Override
//    public List<Object[]> getAllPlanHost() throws Exception{
//        List<Object[]> res = (List<Object[]>)this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
//            @Override
//            public Object doInHibernate(Session session) throws HibernateException {
//                return session.createSQLQuery("SELECT DISTINCT t.host_id FROM timeplan t ").list();
//            }
//        });
//        return res;
//    }

//    /**
//     * 根据班级ID获取当前录像计划，如果为学期课表，则获取当前日期所在星期几直到下周7天的录像计划。
//     * @param hostId
//     * @return
//     * @throws Exception
//     */
//    @Override
//    public List<Object[]> getTimeplanByHost(final int hostId,final String type,Date curDate) throws Exception {
//        List<Object[]> planList= new ArrayList<>();
//        String sqlJoin = "";
//        String sqlSelect = "";
//        String sqlclasstime = "";
//        if(type.equals("week"))
//        {
//            sqlSelect = "t.timeplan_week, ";
//            sqlJoin = "and t.timeplan_week = cur.cur_week ";
//            sqlclasstime = "and c.week_id = t.timeplan_week ";
//        }
//        else {
//            sqlSelect = "case when DAYOFWEEK(t.timeplan_date)-1=0 then 7 else DAYOFWEEK(t.timeplan_date)-1 end as timeplan_week, ";
//            sqlJoin = "and t.timeplan_date = cur.cur_date ";
//            sqlclasstime = "and c.week_id =  case when DAYOFWEEK(t.timeplan_date)-1=0 then 7 else DAYOFWEEK(t.timeplan_date)-1 end ";
//        }
//        String sqlWhere = "";
//        if(type.equals("term"))
//        {
//            SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd");
//            String dateStr = df.format(curDate);
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(curDate);
//            calendar.add(Calendar.DAY_OF_YEAR,7);
//            Date dateEnd = calendar.getTime();
//            String dateEndStr = df.format(dateEnd);
//            sqlWhere = "and t.timeplan_date >= '"+dateStr+"' " +
//                       "and t.timeplan_date < '"+dateEndStr+"' ";
//        }
//        String sql = "SELECT t.timeplan_id,"+sqlSelect+"t.section_id,t.host_id, " +
//                "c.classtime_start,c.classtime_end , cur.cur_subject,cur.cur_teacher " +
//                "FROM timeplan t " +
//                "LEFT JOIN curriculum cur on t.section_id = cur.cur_section and t.host_id = cur.cur_host " +
//                 sqlJoin +
//                "INNER JOIN group2host g2h ON t.host_id = g2h.host_id " +
//                "INNER JOIN classtimeploy2group p2g ON p2g.group_id = g2h.group_id " +
//                "INNER JOIN classtime_ploy p on p.ploy_id = p2g.ploy_id " +
//                "INNER JOIN classtime c ON c.classtime_ploy = p.ploy_id and c.classtime_section = t.section_id " +
//                sqlclasstime + " " +
//                "WHERE t.host_id = "+hostId+" "+
//                 sqlWhere +
//                "UNION " +
//                "select t.timeplan_id,"+sqlSelect+"t.section_id,t.host_id, " +
//                "c.classtime_start,c.classtime_end , cur.cur_subject,cur.cur_teacher " +
//                "from timeplan t " +
//                "LEFT JOIN curriculum cur on t.section_id = cur.cur_section and t.host_id = cur.cur_host " +
//                 sqlJoin +
//                "INNER JOIN classtimeploy2group p2g ON p2g.group_id = -1 " +
//                "INNER JOIN classtime_ploy p on p.ploy_id = p2g.ploy_id " +
//                "INNER JOIN classtime c ON c.classtime_ploy = p.ploy_id and c.classtime_section = t.section_id " +
//                sqlclasstime + " " +
//                "where t.host_id = "+hostId+" "+
//                sqlWhere + " " +
//                " and t.host_id not in(select distinct host_id from group2host)" +
//                "ORDER BY timeplan_week,section_id,classtime_start";
//        final String sql_str = sql;
//        planList=(List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
//            public Object doInHibernate(Session session) throws HibernateException {
//                List list = session.createSQLQuery(sql_str).list();
//                return list;
//            }
//        });
//        return planList;
//    }

    /**
     *  todo 加注释
     * @param hostid
     * @param curSection
     * @param date
     * @return
     * @throws Exception
     */
    @Override
    public Integer getTimeplan(int hostid, int curSection, String date) throws Exception {

        Date d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        String sql = "select timeplan_id from timeplan where timeplan_date=? and host_id= ? and section_id= ?";
        return (Integer) this.getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createSQLQuery(sql)
                .setParameter(0, d)
                .setParameter(1, hostid)
                .setParameter(2, curSection)
                .uniqueResult();
    }

    /**
     *  todo 加注释
     * @param hostid
     * @param curSection
     * @param week_id
     * @return
     * @throws Exception
     */
    @Override
    public Integer getTimeplan(int hostid, int curSection, int week_id) throws Exception {
        String sql = "select timeplan_id from timeplan where timeplan_week= ? and host_id= ? and section_id= ?";
        return (Integer) this.getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createSQLQuery(sql)
                .setParameter(0, week_id)
                .setParameter(1, hostid)
                .setParameter(2, curSection)
                .uniqueResult();
    }
//    /**
//     * 根据班级ID获取指定日期录像计划。
//     * @param hostId
//     * @param date
//     * @return
//     * @throws Exception
//     */
//    @Override
//    public List<Object[]> getTimeplanList(final int hostId,Date date) throws Exception {
//        List<Object[]> planList= new ArrayList<>();
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        String dateStr = df.format(date);
//        String sql = "SELECT t.timeplan_id,t.timeplan_week,t.timeplan_date,t.section_id,t.host_id, " +
//                "c.classtime_start,c.classtime_end , cur.cur_subject,cur.cur_teacher " +
//                "FROM timeplan t " +
//                "LEFT JOIN curriculum cur on t.section_id = cur.cur_section and t.host_id = cur.cur_host " +
//                "and t.timeplan_date = cur.cur_date " +
//                "INNER JOIN group2host g2h ON t.host_id = g2h.host_id " +
//                "INNER JOIN classtimeploy2group p2g ON p2g.group_id = g2h.group_id " +
//                "INNER JOIN classtime_ploy p on p.ploy_id = p2g.ploy_id " +
//                "INNER JOIN classtime c ON c.classtime_ploy = p.ploy_id and c.classtime_section = t.section_id " +
//                "and c.week_id =  case when DAYOFWEEK(t.timeplan_date)-1=0 then 7 else DAYOFWEEK(t.timeplan_date)-1 end " +
//                "WHERE t.host_id = "+hostId+" "+
//                "and t.timeplan_date = '"+dateStr+"' " +
//                "UNION " +
//                "select t.timeplan_id,t.timeplan_week,t.timeplan_date,t.section_id,t.host_id, " +
//                "c.classtime_start,c.classtime_end , cur.cur_subject,cur.cur_teacher " +
//                "from timeplan t " +
//                "LEFT JOIN curriculum cur on t.section_id = cur.cur_section and t.host_id = cur.cur_host " +
//                "and t.timeplan_date = cur.cur_date " +
//                "INNER JOIN classtimeploy2group p2g ON p2g.group_id = -1 " +
//                "INNER JOIN classtime_ploy p on p.ploy_id = p2g.ploy_id " +
//                "INNER JOIN classtime c ON c.classtime_ploy = p.ploy_id and c.classtime_section = t.section_id " +
//                "and c.week_id =  case when DAYOFWEEK(t.timeplan_date)-1=0 then 7 else DAYOFWEEK(t.timeplan_date)-1 end " +
//                "where t.host_id = "+hostId+" "+
//                "and t.host_id not in(select distinct host_id from group2host)" +
//                "and t.timeplan_date = '"+dateStr+"' " ;
//        final String sql_str = sql;
//        planList=(List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
//            public Object doInHibernate(Session session) throws HibernateException {
//                List list = session.createSQLQuery(sql_str).list();
//                return list;
//            }
//        });
//        return planList;
//    }

//    /**
//     * 根据班级ID获取指定星期录像计划。
//     * @param hostId
//     * @param weekId
//     * @return
//     * @throws Exception
//     */
//    @Override
//    public List<Object[]> getTimeplanList(final int hostId,int weekId) throws Exception {
//        List<Object[]> planList= new ArrayList<>();
//        String sql = "SELECT t.timeplan_id,t.timeplan_week,t.timeplan_date,t.section_id,t.host_id, " +
//                "c.classtime_start,c.classtime_end , cur.cur_subject,cur.cur_teacher " +
//                "FROM timeplan t " +
//                "LEFT JOIN curriculum cur on t.section_id = cur.cur_section and t.host_id = cur.cur_host " +
//                "and t.timeplan_week = cur.cur_week " +
//                "INNER JOIN group2host g2h ON t.host_id = g2h.host_id " +
//                "INNER JOIN classtimeploy2group p2g ON p2g.group_id = g2h.group_id " +
//                "INNER JOIN classtime_ploy p on p.ploy_id = p2g.ploy_id " +
//                "INNER JOIN classtime c ON c.classtime_ploy = p.ploy_id and c.classtime_section = t.section_id " +
//                "and c.week_id = t.timeplan_week " +
//                "WHERE t.host_id = "+hostId+" "+
//                "and t.timeplan_week = "+weekId+" "+
//                "UNION " +
//                "select t.timeplan_id,t.timeplan_week,t.timeplan_date,t.section_id,t.host_id, " +
//                "c.classtime_start,c.classtime_end , cur.cur_subject,cur.cur_teacher " +
//                "from timeplan t " +
//                "LEFT JOIN curriculum cur on t.section_id = cur.cur_section and t.host_id = cur.cur_host " +
//                "and t.timeplan_week = cur.cur_week " +
//                "INNER JOIN classtimeploy2group p2g ON p2g.group_id = -1 " +
//                "INNER JOIN classtime_ploy p on p.ploy_id = p2g.ploy_id " +
//                "INNER JOIN classtime c ON c.classtime_ploy = p.ploy_id and c.classtime_section = t.section_id " +
//                "and c.week_id = t.timeplan_week " +
//                "where t.host_id = "+hostId+" "+
//                "and t.host_id not in(select distinct host_id from group2host)" +
//                "and t.timeplan_week = "+weekId;
//        final String sql_str = sql;
//        planList=(List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
//            public Object doInHibernate(Session session) throws HibernateException {
//                List list = session.createSQLQuery(sql_str).list();
//                return list;
//            }
//        });
//        return planList;
//    }

    @Override
    public void delPlanByHostAndDate(String hostId, String date) {
        final String sql = "DELETE FROM timeplan WHERE host_id= ? and timeplan_date= ?";
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql)
                .setParameter(0, hostId)
                .setParameter(1, date)
                .executeUpdate();
    }

    @Override
    public void delPlanByHostAndWeek(String hostId, int week) throws Exception {
        final String sql = "DELETE FROM timeplan WHERE host_id= ? and timeplan_week= ?";
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql)
                .setParameter(0, hostId)
                .setParameter(1, week)
                .executeUpdate();
    }

    //***************************************** add by lichong ******************************************

    /**
     * add by lichong
     * 根据班级ID和groupId获取指定星期录像计划。
     * @param hostId
     * @param weekId
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getTimeplanList(int groupId, int hostId,int weekId) throws Exception {
        List<Object[]> planList= new ArrayList<>();
        String sql = "SELECT t.timeplan_id,t.timeplan_week,t.timeplan_date,t.section_id,t.host_id, " +
                "c.classtime_start,c.classtime_end , cur.cur_subject,cur.cur_teacher " +
                "FROM timeplan t " +
                "LEFT JOIN curriculum cur on t.section_id = cur.cur_section and t.host_id = cur.cur_host " +
                "and t.timeplan_week = cur.cur_week " +
                "INNER JOIN classtimeploy2group p2g " +
                "INNER JOIN classtime_ploy p on p.ploy_id = p2g.ploy_id " +
                "INNER JOIN classtime c ON c.classtime_ploy = p.ploy_id and c.classtime_section = t.section_id " +
                "and c.week_id = t.timeplan_week " +
                "WHERE t.host_id = "+hostId +" "+
                "and t.timeplan_week = "+weekId +" "+
                "AND p2g.group_id = "+ groupId ;
        final String sql_str = sql;
        planList=(List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            public Object doInHibernate(Session session) throws HibernateException {
                List list = session.createSQLQuery(sql_str).list();
                return list;
            }
        });
        return planList;
    }

    /**
     * 根据班级ID获取当前录像计划，如果为学期课表，则获取当前日期所在星期几直到下周7天的录像计划。
     * @param hostId
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getTimeplanByHost(int groupId, int hostId, String type,Date curDate) throws Exception {
        List<Object[]> planList= new ArrayList<>();
        String sqlJoin = "";
        String sqlSelect = "";
        String sqlclasstime = "";
        if(type.equals("week"))
        {
            sqlSelect = "t.timeplan_week, ";
            sqlJoin = "and t.timeplan_week = cur.cur_week ";
            sqlclasstime = "and c.week_id = t.timeplan_week ";
        }
        else {
            sqlSelect = "case when DAYOFWEEK(t.timeplan_date)-1=0 then 7 else DAYOFWEEK(t.timeplan_date)-1 end as timeplan_week, ";
            sqlJoin = "and t.timeplan_date = cur.cur_date ";
            sqlclasstime = "and c.week_id =  case when DAYOFWEEK(t.timeplan_date)-1=0 then 7 else DAYOFWEEK(t.timeplan_date)-1 end ";
        }
        String sqlWhere = "";
        if(type.equals("term"))
        {
            SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd");
            String dateStr = df.format(curDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(curDate);
            calendar.add(Calendar.DAY_OF_YEAR,7);
            Date dateEnd = calendar.getTime();
            String dateEndStr = df.format(dateEnd);
            sqlWhere = "and t.timeplan_date >= '"+dateStr+"' " +
                    "and t.timeplan_date < '"+dateEndStr+"' ";
        }
        String sql = "SELECT t.timeplan_id,"+sqlSelect+"t.section_id,t.host_id, " +
                "c.classtime_start,c.classtime_end , cur.cur_subject,cur.cur_teacher " +
                "FROM timeplan t " +
                "LEFT JOIN curriculum cur on t.section_id = cur.cur_section and t.host_id = cur.cur_host " +
                sqlJoin +
                "INNER JOIN classtimeploy2group p2g ON p2g.group_id = " + groupId + " " +
                "INNER JOIN classtime_ploy p on p.ploy_id = p2g.ploy_id " +
                "INNER JOIN classtime c ON c.classtime_ploy = p.ploy_id and c.classtime_section = t.section_id " +
                sqlclasstime + " " +
                "WHERE t.host_id = "+hostId+" "+
                sqlWhere ;
        final String sql_str = sql;
        planList=(List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            public Object doInHibernate(Session session) throws HibernateException {
                List list = session.createSQLQuery(sql_str).list();
                return list;
            }
        });
        return planList;
    }

    /**
     * 根据班级ID获取指定日期录像计划。
     * @param hostId
     * @param date
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getTimeplanList(int groupId, int hostId,Date date) throws Exception {
        List<Object[]> planList= new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = df.format(date);
        String sql = "SELECT t.timeplan_id,t.timeplan_week,t.timeplan_date,t.section_id,t.host_id, " +
                "c.classtime_start,c.classtime_end , cur.cur_subject,cur.cur_teacher " +
                "FROM timeplan t " +
                "LEFT JOIN curriculum cur on t.section_id = cur.cur_section and t.host_id = cur.cur_host " +
                "and t.timeplan_date = cur.cur_date " +
                "INNER JOIN classtimeploy2group p2g ON p2g.group_id = " +groupId+" "+
                "INNER JOIN classtime_ploy p on p.ploy_id = p2g.ploy_id " +
                "INNER JOIN classtime c ON c.classtime_ploy = p.ploy_id and c.classtime_section = t.section_id " +
                "and c.week_id =  case when DAYOFWEEK(t.timeplan_date)-1=0 then 7 else DAYOFWEEK(t.timeplan_date)-1 end " +
                "WHERE t.host_id = "+hostId+" "+
                "and t.timeplan_date = '"+dateStr+"' ";
        final String sql_str = sql;
        planList=(List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            public Object doInHibernate(Session session) throws HibernateException {
                List list = session.createSQLQuery(sql_str).list();
                return list;
            }
        });
        return planList;
    }

}
