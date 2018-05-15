package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.LiveplanDao;
import com.honghe.recordhibernate.dao.TimeplanDao;
import com.honghe.recordhibernate.entity.Classtime;
import com.honghe.recordhibernate.entity.Liveplan;
import com.honghe.recordhibernate.entity.Timeplan;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by wzz on 2016/7/22.
 */
@Repository
public class LiveplanDaoImpl extends BaseDao implements LiveplanDao {
    /**
     * todo 加注释
     * @param liveplan_id
     * @return
     * @throws Exception
     */
    @Override
    public Liveplan getLiveplan(int liveplan_id)  throws Exception
    {
        Liveplan liveplan = this.getHibernateTemplate().get(Liveplan.class, liveplan_id);
        if (liveplan != null)
            return liveplan;
        return null;
    }

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
                    String sql = "SELECT distinct(liveplan_host) FROM liveplan";
                    return session.createSQLQuery(sql).list();
                }
            });
    }

    /**
     * todo 加注释
     * @param week_id
     * @return
     * @throws Exception
     */
    @Override
    public List<Liveplan> getLiveplanList(final Byte week_id) throws Exception {
        List list = (List<Liveplan>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createQuery("from Liveplan where liveplanWeek = :weekid").setParameter("weekid",week_id).list();
            }
        });
        return list;
    }

    /**
     * todo 加注释
     * @param liveplan
     * @return
     * @throws Exception
     */
    @Override
    public boolean save(Liveplan liveplan) throws Exception{
        liveplan.setLiveplanFlag(0);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
        this.getHibernateTemplate().save(liveplan);
        return true;
    }

    /**
     * todo 加注释
     * @param liveplan
     * @return
     * @throws Exception
     */
    @Override
    public boolean update(Liveplan liveplan) throws Exception {
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
        this.getHibernateTemplate().update(liveplan);
        return true;
    }

    /**
     * todo 加注释
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public boolean delete(final int id) throws Exception {
        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                String sql = "delete from Liveplan where liveplanId="+id;
                Integer datacount = session.createQuery(sql).executeUpdate();
                return datacount;
            }
        });
        return true;
    }

    /**
     * todo 加注释
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public boolean clearPlan(final int id) throws Exception {
        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                String sql = "delete from Liveplan where liveplanHost="+id;
                Integer datacount = session.createQuery(sql).executeUpdate();
                return datacount;
            }
        });
        return true;
    }

    /**
     * todo 加注释
     * @param hostid
     * @return
     * @throws Exception
     */
    @Override
    public boolean isHavePlan(final int hostid) throws Exception {
        BigInteger result = (BigInteger)this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select count(*) from liveplan where liveplan_host=" + hostid).uniqueResult();
            }
        });
        if(result.intValue() == 0)return false;
        return true;
    }

    /**
     * todo 加注释
     * @param hostid
     * @throws Exception
     */
    @Override
    public void delHostPlan(final int hostid) throws Exception {
        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
               Integer datacount = session.createSQLQuery("delete from liveplan where liveplan_host=" + hostid).executeUpdate();
                return datacount;
            }
        });
    }

    /**
     * todo 加注释
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getAllPlanHost() throws Exception{
        List<Object[]> res = (List<Object[]>)this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select l.liveplan_host from liveplan l  group by l.liveplan_host").list();
            }
        });
        return res;
    }

    /**
     * todo 加注释
     * @param hostid
     * @param curSection
     * @param date
     * @return
     * @throws Exception
     */
    @Override
    public Integer getLiveplan(int hostid, int curSection, String date) throws Exception {
        Date d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        String sql = "select liveplan_id from liveplan where liveplan_date=? and liveplan_host=? and liveplan_section=?";
        return (Integer) this.getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createSQLQuery(sql)
                .setParameter(0, d)
                .setParameter(1, hostid)
                .setParameter(2, curSection)
                .uniqueResult();
    }

    /**
     * todo 加注释
     * @param hostid
     * @param curSection
     * @param week_id
     * @return
     * @throws Exception
     */
    @Override
    public Integer getLiveplan(int hostid, int curSection, int week_id) throws Exception {
        String sql = "select liveplan_id from liveplan where liveplan_week=? and liveplan_host=? and liveplan_section=?";
        return (Integer) this.getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createSQLQuery(sql)
                .setParameter(0, week_id)
                .setParameter(1, hostid)
                .setParameter(2, curSection)
                .uniqueResult();
    }

    /**
     * todo 加注释
     * @param hostid
     * @param curSection
     * @param date
     * @return
     * @throws Exception
     */
    @Override
    public String getChannel(int hostid, int curSection, String date) throws Exception {
        Date d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        String sql = "select liveplan_channel from liveplan where liveplan_date=? and liveplan_host=? and liveplan_section=?";
        return (String) this.getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createSQLQuery(sql)
                .setParameter(0, d)
                .setParameter(1, hostid)
                .setParameter(2, curSection)
                .uniqueResult();
    }

    /**
     * todo 加注释
     * @param hostid
     * @param curSection
     * @param week_id
     * @return
     * @throws Exception
     */
    @Override
    public String getChannel(int hostid, int curSection, int week_id) throws Exception {
        String sql = "select liveplan_channel from liveplan where liveplan_week=? and liveplan_host=? and liveplan_section=?";
        return (String) this.getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createSQLQuery(sql)
                .setParameter(0, week_id)
                .setParameter(1, hostid)
                .setParameter(2, curSection)
                .uniqueResult();
    }

    /**
     * todo 加注释
     * @param channels
     * @param liveplanId
     * @return
     * @throws Exception
     */
    @Override
    public int update(String channels, Integer liveplanId) throws Exception {
        String sql = "update liveplan set liveplan_channel= ? where liveplan_id=?";
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql)
                .setParameter(0, channels)
                .setParameter(1, liveplanId)
                .executeUpdate();

    }

    /**
     * todo 加注释
     * @param hostid
     * @param curSection
     * @param date
     * @return
     * @throws Exception
     */
    @Override
    public Integer getLiveplanId(int hostid, int curSection, String date) throws Exception {
        Date d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        String sql = "select liveplan_id from liveplan where liveplan_date=? and liveplan_host=? and liveplan_section=?";
        return (Integer) this.getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createSQLQuery(sql)
                .setParameter(0, d)
                .setParameter(1, hostid)
                .setParameter(2, curSection)
                .uniqueResult();
    }

    /**
     * todo 加注释
     * @throws Exception
     */
    @Override
    public void clearPlan() throws Exception {
        Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
        session.createSQLQuery("truncate table liveplan").executeUpdate();
    }

    /**
     * todo 加注释
     * @param hostid
     * @param curSection
     * @param week_id
     * @return
     * @throws Exception
     */
    @Override
    public Integer getLiveplanId(int hostid, int curSection, int week_id) throws Exception {
        String sql = "select liveplan_id from liveplan where liveplan_week=? and liveplan_host=? and liveplan_section=?";
        return (Integer) this.getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createSQLQuery(sql)
                .setParameter(0, week_id)
                .setParameter(1, hostid)
                .setParameter(2, curSection)
                .uniqueResult();
    }

    @Override
    public List<Object[]> getLivePlanListByHostId(final int hostId) throws Exception {
        final String sql = "SELECT liveplan_id,liveplan_flag FROM liveplan WHERE liveplan_host = ?";
        return (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).setParameter(0,hostId).list();
            }
        });
    }

    // ************************************************** cancel by lichong ****************************************
//    /**
//     * 根据班级ID获取指定星期直播计划。
//     * @param hostId
//     * @param weekId
//     * @return
//     * @throws Exception
//     */
//    @Override
//    public List<Object[]> getLiveplanList(int hostId, int weekId) throws Exception {
//        List<Object[]> planList= new ArrayList<>();
//        String sql = "SELECT l.liveplan_id,l.liveplan_week,l.liveplan_date,l.liveplan_section,l.liveplan_host, " +
//                "c.classtime_start,c.classtime_end , cur.cur_subject,cur.cur_teacher,l.liveplan_channel," +
//                "l.liveplan_sessionid,l.liveplan_roomid,l.liveplan_autoclose,l.liveplan_ext " +
//                "FROM liveplan l " +
//                "LEFT JOIN curriculum cur on l.liveplan_section = cur.cur_section and l.liveplan_host = cur.cur_host " +
//                "and l.liveplan_week = cur.cur_week " +
//                "INNER JOIN group2host g2h ON l.liveplan_host= g2h.host_id " +
//                "INNER JOIN classtimeploy2group p2g ON p2g.group_id = g2h.group_id " +
//                "INNER JOIN classtime_ploy p on p.ploy_id = p2g.ploy_id " +
//                "INNER JOIN classtime c ON c.classtime_ploy = p.ploy_id and c.classtime_section = l.liveplan_section " +
//                "and c.week_id = l.liveplan_week " +
//                "WHERE l.liveplan_host = "+hostId+" "+
//                "and l.liveplan_week = "+weekId+" "+
//                "UNION " +
//                "select l.liveplan_id,l.liveplan_week,l.liveplan_date,l.liveplan_section,l.liveplan_host, " +
//                "c.classtime_start,c.classtime_end , cur.cur_subject,cur.cur_teacher,l.liveplan_channel," +
//                "l.liveplan_sessionid,l.liveplan_roomid,l.liveplan_autoclose,l.liveplan_ext " +
//                "from liveplan l " +
//                "LEFT JOIN curriculum cur on l.liveplan_section = cur.cur_section and l.liveplan_host = cur.cur_host " +
//                "and l.liveplan_week = cur.cur_week " +
//                "INNER JOIN classtimeploy2group p2g ON p2g.group_id = -1 " +
//                "INNER JOIN classtime_ploy p on p.ploy_id = p2g.ploy_id " +
//                "INNER JOIN classtime c ON c.classtime_ploy = p.ploy_id and c.classtime_section = l.liveplan_section " +
//                "and c.week_id = l.liveplan_week " +
//                "where l.liveplan_host = "+hostId+" "+
//                "and l.liveplan_host not in(select distinct host_id from group2host)" +
//                "and l.liveplan_week = "+weekId;
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
//     * 根据班级ID获取当前时间5分钟内直播计划。
//     * @param hostId
//     * @return
//     * @throws Exception
//     */
//    @Override
//    public List<Object[]> getLiveplanByHost(final int hostId,final String type,Date curDate) throws Exception {
//        List<Object[]> planList= new ArrayList<>();
//        String sqlJoin = "";
//        String sqlSelect = "";
//        String sqlclasstime = "";
//        if(type.equals("week"))
//        {
//            sqlSelect = "l.liveplan_week, ";
//            sqlJoin = "and l.liveplan_week = cur.cur_week ";
//            sqlclasstime = "and c.week_id = l.liveplan_week ";
//        }
//        else {
//            sqlSelect = "l.liveplan_date as liveplan_week, ";
//            sqlJoin = "and l.liveplan_date = cur.cur_date ";
//            sqlclasstime = "and c.week_id =  case when DAYOFWEEK(l.liveplan_date)-1=0 then 7 else DAYOFWEEK(l.liveplan_date)-1 end ";
//        }
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(curDate);
//        calendar.add(Calendar.MINUTE,5);
//        SimpleDateFormat dtTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
//        String dateStartStr = dtTime.format(calendar.getTime());//当前时间10:00,查找10:05之前开始的直播计划
//        String dateEndStr = dtTime.format(curDate);
//        String dateStr = dt.format(curDate);
//        String sqlWhere = "and l.liveplan_flag = 0 ";
//        if(type.equals("term"))
//        {
//            sqlWhere += "and date(l.liveplan_date) = date('"+dateStartStr+"') " +
//                    "and str_to_date(concat(date(l.liveplan_date),' ',c.classtime_start,':00'),'%Y-%m-%d %H:%i:%s') <= '"+dateStartStr+"' "+
//                    "and str_to_date(concat(date(l.liveplan_date),' ',c.classtime_end,':00'),'%Y-%m-%d %H:%i:%s') > '"+dateEndStr+"' ";
//        }
//        else
//        {
//            sqlWhere += "and l.liveplan_week  = " +
//                    "case when dayofweek('"+dateStartStr+"')-1 = 0 then 7 else dayofweek('"+dateStartStr+"')-1 end " +
//                    "and str_to_date(concat('"+dateStr+"',' ',c.classtime_start,':00'),'%Y-%m-%d %H:%i:%s') " +
//                    "<= '"+dateStartStr+"' "+
//                    "and str_to_date(concat('"+dateStr+"',' ',c.classtime_end,':00'),'%Y-%m-%d %H:%i:%s') " +
//                    "> '"+dateEndStr+"' ";
//        }
//        String sql = "SELECT l.liveplan_id,"+sqlSelect+"l.liveplan_section,l.liveplan_host, " +
//                "l.liveplan_channel,l.liveplan_sessionid,l.liveplan_roomid,l.liveplan_autoclose, " +
//                "c.classtime_start,c.classtime_end , cur.cur_subject,cur.cur_teacher " +
//                "FROM liveplan l " +
//                "LEFT JOIN curriculum cur on l.liveplan_section = cur.cur_section and l.liveplan_host = cur.cur_host " +
//                sqlJoin +
//                "INNER JOIN group2host g2h ON l.liveplan_host = g2h.host_id " +
//                "INNER JOIN classtimeploy2group p2g ON p2g.group_id = g2h.group_id " +
//                "INNER JOIN classtime_ploy p on p.ploy_id = p2g.ploy_id " +
//                "INNER JOIN classtime c ON c.classtime_ploy = p.ploy_id and c.classtime_section = l.liveplan_section " +
//                sqlclasstime + " " +
//                "WHERE l.liveplan_host = "+hostId+" "+
//                sqlWhere +
//                "UNION " +
//                "select l.liveplan_id,"+sqlSelect+"l.liveplan_section,l.liveplan_host, " +
//                "l.liveplan_channel,l.liveplan_sessionid,l.liveplan_roomid,l.liveplan_autoclose, " +
//                "c.classtime_start,c.classtime_end , cur.cur_subject,cur.cur_teacher " +
//                "from liveplan l " +
//                "LEFT JOIN curriculum cur on l.liveplan_section = cur.cur_section and l.liveplan_host = cur.cur_host " +
//                sqlJoin +
//                "INNER JOIN classtimeploy2group p2g ON p2g.group_id = -1 " +
//                "INNER JOIN classtime_ploy p on p.ploy_id = p2g.ploy_id " +
//                "INNER JOIN classtime c ON c.classtime_ploy = p.ploy_id and c.classtime_section = l.liveplan_section " +
//                sqlclasstime + " " +
//                "where l.liveplan_host = "+hostId+" "+
//                sqlWhere + " " +
//                " and l.liveplan_host not in(select distinct host_id from group2host)" +
//                "ORDER BY liveplan_week,liveplan_section,classtime_start";
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
//     * 根据班级ID获取指定结束的直播计划。
//     * @param hostId
//     * @param flag;0:查询所有正在直播的直播间,1:查询当前时间5分钟后即将结束的直播计划
//     * @return
//     * @throws Exception
//     */
//    @Override
//    public List<Object[]> getLiveplanEndByHost(final int hostId,final String type,Date curDate,int flag) throws Exception {
//        List<Object[]> planList= new ArrayList<>();
//        String sqlJoin = "";
//        String sqlSelect = "";
//        String sqlclasstime = "";
//        if(type.equals("week"))
//        {
//            sqlSelect = "l.liveplan_week, ";
//            sqlJoin = "and l.liveplan_week = cur.cur_week ";
//            sqlclasstime = "and c.week_id = l.liveplan_week ";
//        }
//        else {
//            sqlSelect = "l.liveplan_date as liveplan_week, ";
//            sqlJoin = "and l.liveplan_date = cur.cur_date ";
//            sqlclasstime = "and c.week_id =  case when DAYOFWEEK(l.liveplan_date)-1=0 then 7 else DAYOFWEEK(l.liveplan_date)-1 end ";
//        }
//        String sqlWhere = "and l.liveplan_flag = 1 ";
//        if(flag == 1) {
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(curDate);
//            calendar.add(Calendar.MINUTE, 5);
//            SimpleDateFormat dtTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
//            String dateStartStr = dtTime.format(curDate);
//            String dateStr = dt.format(curDate);
//            if (type.equals("term")) {
//                sqlWhere += "and date(l.liveplan_date) = date('" + dateStartStr + "') " +
//                        "and str_to_date(concat(date(l.liveplan_date),' ',c.classtime_end,':00'),'%Y-%m-%d %H:%i:%s') <= '" + dateStartStr + "' ";
//            } else {
//                sqlWhere += "and l.liveplan_week  = " +
//                        "case when dayofweek('" + dateStartStr + "')-1 = 0 then 7 else dayofweek('" + dateStartStr + "')-1 end " +
//                        "and str_to_date(concat('" + dateStr + "',' ',c.classtime_end,':00'),'%Y-%m-%d %H:%i:%s') " +
//                        "<= '" + dateStartStr + "' ";
//            }
//        }
//        String sql = "SELECT l.liveplan_id,"+sqlSelect+"l.liveplan_section,l.liveplan_host, " +
//                "l.liveplan_channel,l.liveplan_sessionid,l.liveplan_roomid,l.liveplan_autoclose, " +
//                "c.classtime_start,c.classtime_end , cur.cur_subject,cur.cur_teacher " +
//                "FROM liveplan l " +
//                "LEFT JOIN curriculum cur on l.liveplan_section = cur.cur_section and l.liveplan_host = cur.cur_host " +
//                sqlJoin +
//                "INNER JOIN group2host g2h ON l.liveplan_host = g2h.host_id " +
//                "INNER JOIN classtimeploy2group p2g ON p2g.group_id = g2h.group_id " +
//                "INNER JOIN classtime_ploy p on p.ploy_id = p2g.ploy_id " +
//                "INNER JOIN classtime c ON c.classtime_ploy = p.ploy_id and c.classtime_section = l.liveplan_section " +
//                sqlclasstime + " " +
//                "WHERE l.liveplan_host = "+hostId+" "+
//                sqlWhere +
//                "UNION " +
//                "select l.liveplan_id,"+sqlSelect+"l.liveplan_section,l.liveplan_host, " +
//                "l.liveplan_channel,l.liveplan_sessionid,l.liveplan_roomid,l.liveplan_autoclose, " +
//                "c.classtime_start,c.classtime_end , cur.cur_subject,cur.cur_teacher " +
//                "from liveplan l " +
//                "LEFT JOIN curriculum cur on l.liveplan_section = cur.cur_section and l.liveplan_host = cur.cur_host " +
//                sqlJoin +
//                "INNER JOIN classtimeploy2group p2g ON p2g.group_id = -1 " +
//                "INNER JOIN classtime_ploy p on p.ploy_id = p2g.ploy_id " +
//                "INNER JOIN classtime c ON c.classtime_ploy = p.ploy_id and c.classtime_section = l.liveplan_section " +
//                sqlclasstime + " " +
//                "where l.liveplan_host = "+hostId+" "+
//                sqlWhere + " " +
//                " and l.liveplan_host not in(select distinct host_id from group2host)" +
//                "ORDER BY liveplan_week,liveplan_section,classtime_start";
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
//     * 根据班级ID获取指定结束的直播计划。
//     * @param liveplan_id
//     * @return
//     * @throws Exception
//     */
//    @Override
//    public List<Object[]> getLiveplanList(int liveplan_id,String type) throws Exception {
//        List<Object[]> planList= new ArrayList<>();
//        String sqlJoin = "";
//        String sqlSelect = "";
//        String sqlclasstime = "";
//        if(type.equals("week"))
//        {
//            sqlSelect = "l.liveplan_week, ";
//            sqlJoin = "and l.liveplan_week = cur.cur_week ";
//            sqlclasstime = "and c.week_id = l.liveplan_week ";
//        }
//        else {
//            sqlSelect = "l.liveplan_date as liveplan_week, ";
//            sqlJoin = "and l.liveplan_date = cur.cur_date ";
//            sqlclasstime = "and c.week_id =  case when DAYOFWEEK(l.liveplan_date)-1=0 then 7 else DAYOFWEEK(l.liveplan_date)-1 end ";
//        }
//        String sql = "SELECT l.liveplan_id,"+sqlSelect+"l.liveplan_section,l.liveplan_host, " +
//                "l.liveplan_channel,l.liveplan_sessionid,l.liveplan_roomid,l.liveplan_autoclose, " +
//                "c.classtime_start,c.classtime_end , cur.cur_subject,cur.cur_teacher " +
//                "FROM liveplan l " +
//                "LEFT JOIN curriculum cur on l.liveplan_section = cur.cur_section and l.liveplan_host = cur.cur_host " +
//                sqlJoin +
//                "left JOIN group2host g2h ON l.liveplan_host = g2h.host_id " +
//                "inner JOIN classtimeploy2group p2g ON p2g.group_id = COALESCE(g2h.`group_id`,-1) " +
//                "inner JOIN classtime_ploy p on p.ploy_id = p2g.ploy_id " +
//                "inner JOIN classtime c ON c.classtime_ploy = p.ploy_id and c.classtime_section = l.liveplan_section " +
//                sqlclasstime + " " +
//                "WHERE l.liveplan_id = "+liveplan_id;
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
//     * 根据班级ID获取指定日期直播计划。
//     * @param hostId
//     * @param date
//     * @return
//     * @throws Exception
//     */
//    @Override
//    public List<Object[]> getLiveplanList(int hostId, Date date) throws Exception {
//        List<Object[]> planList= new ArrayList<>();
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        String dateStr = df.format(date);
//        String sql = "SELECT l.liveplan_id,l.liveplan_week,l.liveplan_date,l.liveplan_section,l.liveplan_host, " +
//                "c.classtime_start,c.classtime_end , cur.cur_subject,cur.cur_teacher,l.liveplan_channel," +
//                "l.liveplan_sessionid,l.liveplan_roomid,l.liveplan_autoclose,l.liveplan_ext " +
//                "FROM liveplan l " +
//                "LEFT JOIN curriculum cur on l.liveplan_section = cur.cur_section and l.liveplan_host = cur.cur_host " +
//                "and l.liveplan_date = cur.cur_date " +
//                "INNER JOIN group2host g2h ON l.liveplan_host = g2h.host_id " +
//                "INNER JOIN classtimeploy2group p2g ON p2g.group_id = g2h.group_id " +
//                "INNER JOIN classtime_ploy p on p.ploy_id = p2g.ploy_id " +
//                "INNER JOIN classtime c ON c.classtime_ploy = p.ploy_id and c.classtime_section = l.liveplan_section " +
//                "and c.week_id =  case when DAYOFWEEK(l.liveplan_date)-1=0 then 7 else DAYOFWEEK(l.liveplan_date)-1 end " +
//                "WHERE l.liveplan_host = "+hostId+" "+
//                "and l.liveplan_date = '"+dateStr+"' " +
//                "UNION " +
//                "select l.liveplan_id,l.liveplan_week,l.liveplan_date,l.liveplan_section,l.liveplan_host, " +
//                "c.classtime_start,c.classtime_end , cur.cur_subject,cur.cur_teacher,liveplan_channel," +
//                "l.liveplan_sessionid,l.liveplan_roomid,l.liveplan_autoclose,l.liveplan_ext " +
//                "from liveplan l " +
//                "LEFT JOIN curriculum cur on l.liveplan_section = cur.cur_section and l.liveplan_host = cur.cur_host " +
//                "and l.liveplan_date = cur.cur_date " +
//                "INNER JOIN classtimeploy2group p2g ON p2g.group_id = -1 " +
//                "INNER JOIN classtime_ploy p on p.ploy_id = p2g.ploy_id " +
//                "INNER JOIN classtime c ON c.classtime_ploy = p.ploy_id and c.classtime_section = l.liveplan_section " +
//                "and c.week_id =  case when DAYOFWEEK(l.liveplan_date)-1=0 then 7 else DAYOFWEEK(l.liveplan_date)-1 end " +
//                "where l.liveplan_host = "+hostId+" "+
//                "and l.liveplan_host not in(select distinct host_id from group2host)" +
//                "and l.liveplan_date = '"+dateStr+"' " ;
//        final String sql_str = sql;
//        planList=(List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
//            public Object doInHibernate(Session session) throws HibernateException {
//                List list = session.createSQLQuery(sql_str).list();
//                return list;
//            }
//        });
//        return planList;
//    }
    // ************************************************** add by lichong ****************************************

    /**
     * add by lichong
     * 根据班级ID获取指定星期直播计划。
     * @param hostId
     * @param weekId
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getLiveplanList(int groupId, int hostId, int weekId) throws Exception {
        List<Object[]> planList= new ArrayList<>();
        String sql = "SELECT l.liveplan_id,l.liveplan_week,l.liveplan_date,l.liveplan_section,l.liveplan_host, " +
                "c.classtime_start,c.classtime_end , cur.cur_subject,cur.cur_teacher,l.liveplan_channel," +
                "l.liveplan_sessionid,l.liveplan_roomid,l.liveplan_autoclose,l.liveplan_ext " +
                "FROM liveplan l " +
                "LEFT JOIN curriculum cur on l.liveplan_section = cur.cur_section and l.liveplan_host = cur.cur_host " +
                "and l.liveplan_week = cur.cur_week " +
                "INNER JOIN classtimeploy2group p2g ON p2g.group_id = " + groupId +" "+
                "INNER JOIN classtime_ploy p on p.ploy_id = p2g.ploy_id " +
                "INNER JOIN classtime c ON c.classtime_ploy = p.ploy_id and c.classtime_section = l.liveplan_section " +
                "and c.week_id = l.liveplan_week " +
                "WHERE l.liveplan_host = "+hostId+" "+
                "and l.liveplan_week = "+weekId;
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
     * 根据班级ID获取当前时间5分钟内直播计划。
     * @param hostId
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getLiveplanByHost(int groupId, int hostId, String type,Date curDate) throws Exception {
        List<Object[]> planList= new ArrayList<>();
        String sqlJoin = "";
        String sqlSelect = "";
        String sqlclasstime = "";
        if(type.equals("week"))
        {
            sqlSelect = "l.liveplan_week, ";
            sqlJoin = "and l.liveplan_week = cur.cur_week ";
            sqlclasstime = "and c.week_id = l.liveplan_week ";
        }
        else {
            sqlSelect = "l.liveplan_date as liveplan_week, ";
            sqlJoin = "and l.liveplan_date = cur.cur_date ";
            sqlclasstime = "and c.week_id =  case when DAYOFWEEK(l.liveplan_date)-1=0 then 7 else DAYOFWEEK(l.liveplan_date)-1 end ";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(curDate);
        calendar.add(Calendar.MINUTE,5);
        SimpleDateFormat dtTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        String dateStartStr = dtTime.format(calendar.getTime());//当前时间10:00,查找10:05之前开始的直播计划
        String dateEndStr = dtTime.format(curDate);
        String dateStr = dt.format(curDate);
        String sqlWhere = "and l.liveplan_flag = 0 ";
        if(type.equals("term"))
        {
            sqlWhere += "and date(l.liveplan_date) = date('"+dateStartStr+"') " +
                    "and str_to_date(concat(date(l.liveplan_date),' ',c.classtime_start,':00'),'%Y-%m-%d %H:%i:%s') <= '"+dateStartStr+"' "+
                    "and str_to_date(concat(date(l.liveplan_date),' ',c.classtime_end,':00'),'%Y-%m-%d %H:%i:%s') > '"+dateEndStr+"' ";
        }
        else
        {
            sqlWhere += "and l.liveplan_week  = " +
                    "case when dayofweek('"+dateStartStr+"')-1 = 0 then 7 else dayofweek('"+dateStartStr+"')-1 end " +
                    "and str_to_date(concat('"+dateStr+"',' ',c.classtime_start,':00'),'%Y-%m-%d %H:%i:%s') " +
                    "<= '"+dateStartStr+"' "+
                    "and str_to_date(concat('"+dateStr+"',' ',c.classtime_end,':00'),'%Y-%m-%d %H:%i:%s') " +
                    "> '"+dateEndStr+"' ";
        }
        String sql = "SELECT l.liveplan_id,"+sqlSelect+"l.liveplan_section,l.liveplan_host, " +
                "l.liveplan_channel,l.liveplan_sessionid,l.liveplan_roomid,l.liveplan_autoclose, " +
                "c.classtime_start,c.classtime_end , cur.cur_subject,cur.cur_teacher " +
                "FROM liveplan l " +
                "LEFT JOIN curriculum cur on l.liveplan_section = cur.cur_section and l.liveplan_host = cur.cur_host " +
                sqlJoin +
                "INNER JOIN classtimeploy2group p2g ON p2g.group_id = " + groupId +" "+
                "INNER JOIN classtime_ploy p on p.ploy_id = p2g.ploy_id " +
                "INNER JOIN classtime c ON c.classtime_ploy = p.ploy_id and c.classtime_section = l.liveplan_section " +
                sqlclasstime + " " +
                "WHERE l.liveplan_host = "+hostId+" "+
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
     * 根据班级ID获取指定结束的直播计划。
     * @param hostId
     * @param flag;0:查询所有正在直播的直播间,1:查询当前时间5分钟后即将结束的直播计划
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getLiveplanEndByHost(int groupId, int hostId, String type,Date curDate,int flag) throws Exception {
        List<Object[]> planList= new ArrayList<>();
        String sqlJoin = "";
        String sqlSelect = "";
        String sqlclasstime = "";
        if(type.equals("week"))
        {
            sqlSelect = "l.liveplan_week, ";
            sqlJoin = "and l.liveplan_week = cur.cur_week ";
            sqlclasstime = "and c.week_id = l.liveplan_week ";
        }
        else {
            sqlSelect = "l.liveplan_date as liveplan_week, ";
            sqlJoin = "and l.liveplan_date = cur.cur_date ";
            sqlclasstime = "and c.week_id =  case when DAYOFWEEK(l.liveplan_date)-1=0 then 7 else DAYOFWEEK(l.liveplan_date)-1 end ";
        }
        String sqlWhere = "and l.liveplan_flag = 1 ";
        if(flag == 1) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(curDate);
            calendar.add(Calendar.MINUTE, 5);
            SimpleDateFormat dtTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
            String dateStartStr = dtTime.format(curDate);
            String dateStr = dt.format(curDate);
            if (type.equals("term")) {
                sqlWhere += "and date(l.liveplan_date) = date('" + dateStartStr + "') " +
                        "and str_to_date(concat(date(l.liveplan_date),' ',c.classtime_end,':00'),'%Y-%m-%d %H:%i:%s') <= '" + dateStartStr + "' ";
            } else {
                sqlWhere += "and l.liveplan_week  = " +
                        "case when dayofweek('" + dateStartStr + "')-1 = 0 then 7 else dayofweek('" + dateStartStr + "')-1 end " +
                        "and str_to_date(concat('" + dateStr + "',' ',c.classtime_end,':00'),'%Y-%m-%d %H:%i:%s') " +
                        "<= '" + dateStartStr + "' ";
            }
        }
        String sql = "SELECT l.liveplan_id,"+sqlSelect+"l.liveplan_section,l.liveplan_host, " +
                "l.liveplan_channel,l.liveplan_sessionid,l.liveplan_roomid,l.liveplan_autoclose, " +
                "c.classtime_start,c.classtime_end , cur.cur_subject,cur.cur_teacher " +
                "FROM liveplan l " +
                "LEFT JOIN curriculum cur on l.liveplan_section = cur.cur_section and l.liveplan_host = cur.cur_host " +
                sqlJoin +
                "INNER JOIN classtimeploy2group p2g ON p2g.group_id = " + groupId +" "+
                "INNER JOIN classtime_ploy p on p.ploy_id = p2g.ploy_id " +
                "INNER JOIN classtime c ON c.classtime_ploy = p.ploy_id and c.classtime_section = l.liveplan_section " +
                sqlclasstime + " " +
                "WHERE l.liveplan_host = "+hostId+" "+
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
     * 根据班级ID获取指定结束的直播计划。
     * @param liveplan_id
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getLiveplanList(int groupId, int liveplan_id,String type) throws Exception {
        List<Object[]> planList= new ArrayList<>();
        String sqlJoin = "";
        String sqlSelect = "";
        String sqlclasstime = "";
        if(type.equals("week"))
        {
            sqlSelect = "l.liveplan_week, ";
            sqlJoin = "and l.liveplan_week = cur.cur_week ";
            sqlclasstime = "and c.week_id = l.liveplan_week ";
        }
        else {
            sqlSelect = "l.liveplan_date as liveplan_week, ";
            sqlJoin = "and l.liveplan_date = cur.cur_date ";
            sqlclasstime = "and c.week_id =  case when DAYOFWEEK(l.liveplan_date)-1=0 then 7 else DAYOFWEEK(l.liveplan_date)-1 end ";
        }
        String sql = "SELECT l.liveplan_id,"+sqlSelect+"l.liveplan_section,l.liveplan_host, " +
                "l.liveplan_channel,l.liveplan_sessionid,l.liveplan_roomid,l.liveplan_autoclose, " +
                "c.classtime_start,c.classtime_end , cur.cur_subject,cur.cur_teacher " +
                "FROM liveplan l " +
                "LEFT JOIN curriculum cur on l.liveplan_section = cur.cur_section and l.liveplan_host = cur.cur_host " +
                sqlJoin +
                "inner JOIN classtimeploy2group p2g ON p2g.group_id = " + groupId +" "+
                "inner JOIN classtime_ploy p on p.ploy_id = p2g.ploy_id " +
                "inner JOIN classtime c ON c.classtime_ploy = p.ploy_id and c.classtime_section = l.liveplan_section " +
                sqlclasstime + " " +
                "WHERE l.liveplan_id = "+liveplan_id;
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
     * 根据班级ID获取指定日期直播计划。
     * @param hostId
     * @param date
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getLiveplanList(int groupId, int hostId, Date date) throws Exception {
        List<Object[]> planList= new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = df.format(date);
        String sql = "SELECT l.liveplan_id,l.liveplan_week,l.liveplan_date,l.liveplan_section,l.liveplan_host, " +
                "c.classtime_start,c.classtime_end , cur.cur_subject,cur.cur_teacher,l.liveplan_channel," +
                "l.liveplan_sessionid,l.liveplan_roomid,l.liveplan_autoclose,l.liveplan_ext " +
                "FROM liveplan l " +
                "LEFT JOIN curriculum cur on l.liveplan_section = cur.cur_section and l.liveplan_host = cur.cur_host " +
                "and l.liveplan_date = cur.cur_date " +
                "INNER JOIN classtimeploy2group p2g ON p2g.group_id = " +groupId+" "+
                "INNER JOIN classtime_ploy p on p.ploy_id = p2g.ploy_id " +
                "INNER JOIN classtime c ON c.classtime_ploy = p.ploy_id and c.classtime_section = l.liveplan_section " +
                "and c.week_id =  case when DAYOFWEEK(l.liveplan_date)-1=0 then 7 else DAYOFWEEK(l.liveplan_date)-1 end " +
                "WHERE l.liveplan_host = "+hostId+" "+
                "and l.liveplan_date = '"+dateStr+"' ";
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
