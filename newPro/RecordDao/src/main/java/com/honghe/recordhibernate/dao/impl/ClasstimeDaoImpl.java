package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.ClasstimeDao;
import com.honghe.recordhibernate.entity.Classtime;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lch on 2014/9/26.
 */
@Repository
public class ClasstimeDaoImpl extends BaseDao implements ClasstimeDao {


    /*@Override
    public List<Classtime> getClasstimeListByHostId(String hostId) throws Exception {
        List list = null;
        list = (List<Classtime>)this.getHibernateTemplate().find("from Classtime where ");

        return list;
    }*/

    /**
     * todo 加注释
     * @return
     * @throws Exception
     */
    @Override
    public List<Classtime> getClasstimeList() throws Exception
    {
        List list  = (List<Classtime>)this.getHibernateTemplate().find("from Classtime");
        return list;
    }

    /**
     * todo 加注释
     * @param classtime
     * @return
     */
    @Override
    public boolean save(Classtime classtime)
    {
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
        this.getHibernateTemplate().save(classtime);
        return true;
    }

    /**
     * todo 加注释
     * @param classtime
     * @return
     * @throws Exception
     */
    @Override
    public boolean update(Classtime classtime) throws Exception
    {
        this.getHibernateTemplate().update(classtime);
        return true;
    }

    /**
     * todo 加注释
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public boolean delete(int id) throws Exception
    {
        final Classtime classtime = new Classtime();
        classtime.setClasstimeId(id);
        this.getHibernateTemplate().delete(classtime);
        return true;
    }

    /**
     * todo 加注释
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Classtime getInfo(int id) throws Exception
    {
        return this.getHibernateTemplate().get(Classtime.class,id);
    }

    /**
     * todo 加注释
     * @param hostId
     * @param weekId
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getClasstimeList(int hostId,int weekId) throws Exception
    {
        String sql = "SELECT c.classtime_id,c.week_id,c.classtime_section," +
                "c.classtime_start,c.classtime_end,c.classtime_ploy " +
                "FROM classtime c " +
                "INNER JOIN classtime_ploy p on c.classtime_ploy = p.ploy_id " +
                "INNER JOIN classtimeploy2group p2g ON p.ploy_id = p2g.ploy_id " +
                "INNER JOIN group2host g2h ON p2g.group_id = g2h.group_id  " +
                "WHERE g2h.host_id = " + hostId +
                " and c.week_id = " + weekId;
        final String sql_f = sql;
        List<Object[]> list = (List<Object[]>)this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql_f).list();
            }
        });
        return list;
    }
    /**
     * todo 加注释
     * @param weekId
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getClasstimeListNoGroup(int weekId) throws Exception
    {
        String sql = "SELECT c.classtime_id,c.week_id,c.classtime_section," +
                "c.classtime_start,c.classtime_end,c.classtime_ploy " +
                "FROM classtime c " +
                "INNER JOIN classtime_ploy p on c.classtime_ploy = p.ploy_id " +
                "INNER JOIN classtimeploy2group p2g ON p.ploy_id = p2g.ploy_id and p2g.group_id = -1 " +
                "WHERE c.week_id = " + weekId;
        final String sql_f = sql;
        List<Object[]> list = (List<Object[]>)this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql_f).list();
            }
        });
        return list;
    }

    /**
     * todo 加注释
     * @return
     * @throws Exception
     */
    @Override
    public List<Integer> getSectionList() throws Exception
    {
        String sql = "SELECT DISTINCT  classtime_section " +
                "FROM classtime " +
                "ORDER  BY classtime_section ASC ";
        final String sql_f = sql;
        List<Integer> list = (List<Integer>)this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql_f).list();
            }
        });
        return list;
    }

//    /**
//     * todo 加注释
//     * @param hostId
//     * @return
//     * @throws Exception
//     */
//    @Override
//    public List<Integer> getSectionList(int hostId) throws Exception
//    {
//        String sql = "SELECT DISTINCT  c.classtime_section " +
//                "FROM classtime c " +
//                "INNER JOIN classtime_ploy p on c.classtime_ploy = p.ploy_id " +
//                "INNER JOIN classtimeploy2group p2g ON p.ploy_id = p2g.ploy_id " +
//                "INNER JOIN group2host g2h ON p2g.group_id = g2h.group_id  " +
//                "WHERE g2h.host_id = " + hostId;
//        final String sql_f = sql;
//        List<Integer> list = (List<Integer>)this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
//            @Override
//            public Object doInHibernate(Session session) throws HibernateException {
//                return session.createSQLQuery(sql_f).list();
//            }
//        });
//        return list;
//    }

    /**
     * todo 加注释
     * @param hostId
     * @return
     * @throws Exception
     */
    @Override
    public List<Integer> getSectionListNoGroup(int hostId) throws Exception
    {
        String sql = "SELECT DISTINCT  c.classtime_section " +
                "FROM classtime c " +
                "INNER JOIN classtime_ploy p on c.classtime_ploy = p.ploy_id " +
                "INNER JOIN classtimeploy2group p2g ON p.ploy_id = p2g.ploy_id and p2g.group_id = -1 " ;
        final String sql_f = sql;
        List<Integer> list = (List<Integer>)this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql_f).list();
            }
        });
        return list;
    }

    /**
     * todo 加注释
     * @param section
     * @param classtimPloy
     * @return
     * @throws Exception
     */
    @Override
    public Classtime getClassTime(int section, int classtimPloy) throws Exception {
        Classtime classtime = (Classtime) this.getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createQuery("from Classtime where sectionId= :sectionId and classtimePloy.ployId= :ployId")
                .setParameter("sectionId", section)
                .setParameter("ployId", classtimPloy)
                .uniqueResult();
        return classtime;
    }

    /**
     * todo 加注释
     * @param timePloyId
     * @throws Exception
     */
    @Override
    public void deleteClasstime2Group(Integer timePloyId) throws Exception {
        this.getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createSQLQuery("DELETE FROM classtimeploy2group WHERE ploy_id= :ploy_id")
                .setParameter("ploy_id", timePloyId)
                .executeUpdate();
    }

    /**
     * todo 加注释
     * @param hostId
     * @param weekId
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getClasstimeListWithCirriculum(int hostId,int weekId) throws Exception
    {
        String sql = "SELECT distinct c.classtime_id,c.week_id,c.classtime_section," +
                "c.classtime_start,c.classtime_end,c.classtime_ploy," +
                "cur.cur_teacher,cur.cur_subject,cur.cur_unit,cur.cur_class " +
                "FROM classtime c " +
                "INNER JOIN classtime_ploy p on c.classtime_ploy = p.ploy_id " +
                "INNER JOIN classtimeploy2group p2g ON p.ploy_id = p2g.ploy_id " +
                "INNER JOIN group2host g2h ON p2g.group_id = g2h.group_id  " +
                "LEFT  JOIN curriculum cur on c.classtime_section =  cur.cur_section and c.week_id = cur.cur_week " +
                "and cur.cur_host = g2h.host_id " +
                "WHERE p2g.group_id = " + hostId +
                " and c.week_id = " + weekId;
        final String sql_f = sql;
        List<Object[]> list = (List<Object[]>)this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql_f).list();
            }
        });
        return list;
    }

    /**
     * todo 加注释
     * @param hostId
     * @param date
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getClasstimeListWithCirriculum(int hostId,Date date) throws Exception
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int weekId = Byte.parseByte(String.valueOf(calendar.get(Calendar.DAY_OF_WEEK) - 1));
        if (weekId == 0) {
            weekId = 7;
        }
        String _date = new SimpleDateFormat("yyyy-MM-dd").format(date);
        String sql = "SELECT distinct c.classtime_id,c.week_id,c.classtime_section," +
                "c.classtime_start,c.classtime_end,c.classtime_ploy," +
                "cur.cur_teacher,cur.cur_subject,cur.cur_unit,cur.cur_class " +
                "FROM classtime c " +
                "INNER JOIN classtime_ploy p on c.classtime_ploy = p.ploy_id " +
                "INNER JOIN classtimeploy2group p2g ON p.ploy_id = p2g.ploy_id " +
                "INNER JOIN group2host g2h ON p2g.group_id = g2h.group_id  " +
                "LEFT  JOIN curriculum cur on c.classtime_section =  cur.cur_section " +
                "and cur.cur_date = '"+_date+"' and cur.cur_host = g2h.host_id " +
                "WHERE p2g.group_id = " + hostId +
                " and c.week_id = " + weekId;
        final String sql_f = sql;
        List<Object[]> list = (List<Object[]>)this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql_f).list();
            }
        });
        return list;
    }

    /**
     * todo 加注释
     * @param hostId
     * @param weekId
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getClasstimeListWithCirriculumNoGroup(int hostId,int weekId) throws Exception
    {
        String sql = "SELECT distinct c.classtime_id,c.week_id,c.classtime_section," +
                "c.classtime_start,c.classtime_end,c.classtime_ploy, " +
                "cur.cur_teacher,cur.cur_subject,cur.cur_unit,cur.cur_class " +
                "FROM classtime c " +
                "INNER JOIN classtime_ploy p on c.classtime_ploy = p.ploy_id " +
                "INNER JOIN classtimeploy2group p2g ON p.ploy_id = p2g.ploy_id and p2g.group_id = -1 " +
                "LEFT  JOIN curriculum cur on c.classtime_section =  cur.cur_section and c.week_id = cur.cur_week " +
                "and cur.cur_host = " + hostId + " "+
                "WHERE c.week_id = " + weekId;
        final String sql_f = sql;
        List<Object[]> list = (List<Object[]>)this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql_f).list();
            }
        });
        return list;
    }

    /**
     * todo 加注释
     * @param hostId
     * @param date
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getClasstimeListWithCirriculumNoGroup(int hostId,Date date) throws Exception
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int weekId = Byte.parseByte(String.valueOf(calendar.get(Calendar.DAY_OF_WEEK) - 1));
        if (weekId == 0) {
            weekId = 7;
        }
        String _date = new SimpleDateFormat("yyyy-MM-dd").format(date);
        String sql = "SELECT distinct c.classtime_id,c.week_id,c.classtime_section," +
                "c.classtime_start,c.classtime_end,c.classtime_ploy, " +
                "cur.cur_teacher,cur.cur_subject,cur.cur_unit,cur.cur_class " +
                "FROM classtime c " +
                "INNER JOIN classtime_ploy p on c.classtime_ploy = p.ploy_id " +
                "INNER JOIN classtimeploy2group p2g ON p.ploy_id = p2g.ploy_id and p2g.group_id = -1 " +
                "LEFT  JOIN curriculum cur on c.classtime_section =  cur.cur_section " +
                "AND cur.cur_date = '"+_date+"' and cur.cur_host = " + hostId + " "+
                "WHERE c.week_id = " + weekId;
        final String sql_f = sql;
        List<Object[]> list = (List<Object[]>)this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql_f).list();
            }
        });
        return list;
    }

    // ******************************************************add by lichong******************************************

    /**
     * add by lichong
     * 获得组策略的节次
     * @param groupId  组id
     * @return
     * @throws Exception
     */
    @Override
    public List<Integer> getSectionList(int groupId) throws Exception
    {
        String sql = "SELECT DISTINCT  c.classtime_section " +
                "FROM classtime c " +
                "INNER JOIN classtime_ploy p on c.classtime_ploy = p.ploy_id " +
                "INNER JOIN classtimeploy2group p2g ON p.ploy_id = p2g.ploy_id " +
                "where p2g.group_id = " + groupId;
        final String sql_f = sql;
        List<Integer> list = (List<Integer>)this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql_f).list();
            }
        });
        return list;
    }

    /**
     * add by lichong
     * @param groupId
     * @param weekId
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getClasstimeListWithCirriculum(int groupId, int hostId, int weekId) throws Exception
    {
        String sql = "SELECT distinct c.classtime_id,c.week_id,c.classtime_section," +
                "c.classtime_start,c.classtime_end,c.classtime_ploy," +
                "cur.cur_teacher,cur.cur_subject,cur.cur_unit,cur.cur_class " +
                "FROM classtime c " +
                "INNER JOIN classtime_ploy p on c.classtime_ploy = p.ploy_id " +
                "INNER JOIN classtimeploy2group p2g ON p.ploy_id = p2g.ploy_id " +
                "LEFT  JOIN curriculum cur on c.classtime_section =  cur.cur_section and c.week_id = cur.cur_week " +
                "where cur.cur_host = " + hostId + " "+
                "and p2g.group_id = " + groupId + " "+
                "and c.week_id = " + weekId;
        final String sql_f = sql;
        List<Object[]> list = (List<Object[]>)this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql_f).list();
            }
        });
        return list;
    }


    /**
     * add by lichong
     * 根据组和日期获取课表详细信息
     * @param groupId
     * @param date
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getClasstimeListWithCirriculum(int groupId, int hostId, Date date) throws Exception
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int weekId = Byte.parseByte(String.valueOf(calendar.get(Calendar.DAY_OF_WEEK) - 1));
        if (weekId == 0) {
            weekId = 7;
        }
        String _date = new SimpleDateFormat("yyyy-MM-dd").format(date);
        String sql = "SELECT distinct c.classtime_id,c.week_id,c.classtime_section," +
                "c.classtime_start,c.classtime_end,c.classtime_ploy," +
                "cur.cur_teacher,cur.cur_subject,cur.cur_unit,cur.cur_class " +
                "FROM classtime c " +
                "INNER JOIN classtime_ploy p on c.classtime_ploy = p.ploy_id " +
                "INNER JOIN classtimeploy2group p2g ON p.ploy_id = p2g.ploy_id " +
                "LEFT  JOIN curriculum cur on c.classtime_section =  cur.cur_section " +
                "and cur.cur_date = '" +_date+"'" + " " +
                "and cur.cur_host = " + hostId + " " +
                "WHERE p2g.group_id = " + groupId + " " +
                "and c.week_id = " + weekId;
        final String sql_f = sql;
        List<Object[]> list = (List<Object[]>)this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql_f).list();
            }
        });
        return list;
    }


    /**
     * add by lichong
     * 根据group获取所绑定的策略信息
     * @param weekId
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getClasstimeListByGroup(int weekId,int groupId) throws Exception
    {
        String sql = "SELECT " + "\n" +
                "c.classtime_id," + "\n" +
                "c.week_id," + "\n" +
                "c.classtime_section," + "\n" +
                "c.classtime_start," + "\n" +
                "c.classtime_end," + "\n" +
                "c.classtime_ploy " + "\n" +
                "FROM dmanager.classtime c " + "\n" +
                "INNER JOIN dmanager.classtime_ploy p on c.classtime_ploy = p.ploy_id " + "\n" +
                "INNER JOIN dmanager.classtimeploy2group p2g ON p.ploy_id = p2g.ploy_id " + "\n" +
                "WHERE c.week_id = " + weekId + "\n" +
                "and p2g.group_id = " +groupId;
        final String sql_f = sql;
        List<Object[]> list = (List<Object[]>)this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql_f).list();
            }
        });
        return list;
    }

}
