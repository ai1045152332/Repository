package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.CurriculumDao;
import com.honghe.recordhibernate.entity.Curriculum;
import org.apache.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by wuzhenzhen on 2016/06/09.
 */
@Repository
public class CurriculumDaoImpl extends BaseDao implements CurriculumDao {
//    private final static  Logger logger = Logger.getLogger(CurriculumDaoImpl.class);
    @Override
    public List<Curriculum> getCurriculumList() throws Exception
    {
        List list = null;
        list = (List<Curriculum>)this.getHibernateTemplate().find("from Curriculum");
        return list;
    }

    /**
     * 根据hostid 获取课程信息
     * @param hostid
     * @return
     * @throws Exception
     */
    @Override
    public List<Curriculum> getCurriculumList(int hostid) throws Exception {
        return (List<Curriculum>) this.getHibernateTemplate().find("from Curriculum where curHost = ?", hostid);
    }

    /**
     * 根据hostid weekid 获取课程信息
     * @param hostid
     * @param week_id
     * @return
     * @throws Exception
     */
    @Override
    public List<Curriculum> getCurriculumList(int hostid, int week_id) throws Exception {
        return (List<Curriculum>) this.getHibernateTemplate().find("from Curriculum where curHost = ? and curWeek = ?", hostid, week_id);
    }

    /**
     * 根据hostid data获取课程信息
     * @param hostid
     * @param date
     * @return
     * @throws Exception
     */
    @Override
    public List<Curriculum> getCurriculumList(int hostid, String date) throws Exception {
        String sql = "select cur_id, date_format(cur_date,'%Y-%m-%d') as cur_date, cur_week, cur_section, cur_host, cur_class, cur_teacher, cur_subject, cur_unit from curriculum where cur_host= :cur_host and cur_date= :cur_date";

        Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
        List<Curriculum> curriculums = session.createSQLQuery(sql).addEntity(Curriculum.class).setParameter("cur_host", hostid).setParameter("cur_date", date).list();
        return curriculums;
    }

    /**
     * 根据hostid section data获取课程信息
     * @param hostid
     * @param cur_section
     * @param date
     * @return
     * @throws Exception
     */
    @Override
    public Curriculum getCurriculum(int hostid, int cur_section, String date) throws Exception {
        String sql = "select cur_id, date_format(cur_date,'%Y-%m-%d') as cur_date, cur_week, cur_section, cur_host, cur_class, cur_teacher, cur_subject, cur_unit from curriculum where cur_host= :cur_host and cur_date= :cur_date and cur_section= :cur_section";

        Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
        return (Curriculum) session.createSQLQuery(sql).addEntity(Curriculum.class).setParameter("cur_host", hostid).setParameter("cur_date", date).setParameter("cur_section", cur_section).uniqueResult();
    }

    /**
     * 根据hostid section weekid 获取课程信息
     * @param hostid
     * @param cur_section
     * @param week_id
     * @return
     * @throws Exception
     */
    @Override
    public Curriculum getCurriculum(int hostid, int cur_section, int week_id) throws Exception {

        Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
        Curriculum curriculum = (Curriculum) session.createQuery("from Curriculum where curHost= :curHost and curSection= :curSection and curWeek= :curWeek")
                .setParameter("curHost", hostid)
                .setParameter("curSection", cur_section)
                .setParameter("curWeek", week_id)
                .uniqueResult();
        return curriculum;
    }

    @Override
    public List<Object[]> getCurriculum(Integer hostid, String startRecordTime,Integer curriculumType) throws Exception {
        String sqlSelect = "cur.cur_week";
        if(curriculumType.equals(Curriculum.CUT_DATE_TYPE)){
            sqlSelect = "WEEKDAY(cur.cur_date) + 1";
        }
        Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
       /* String sql = "SELECT cur.cur_id, cur.cur_date, cur.cur_week, cur.cur_section, cur.cur_host, group_concat(DISTINCT cur.cur_class), group_concat(DISTINCT cur.cur_teacher), group_concat(DISTINCT cur.cur_subject),cur.cur_unit " +
                "FROM curriculum cur WHERE cur.cur_host=? and " +
                "cur_section IN (" +
                "SELECT DISTINCT " +
                "classtime_section " +
                "FROM " +
                "classtime c " +
                "cur.cur_host=? and " +
                "cur_section IN (" +
                "SELECT DISTINCT " +
                "classtime_section " +
                "FROM " +
                "classtime c " +
                "WHERE" +
                "(" +
                "? <= c.classtime_start " +
                "AND c.classtime_end <= DATE_FORMAT(NOW(), '%H:%i')" +
                ")" +
                "and c.classtime_ploy = (select ploy_id from classtimeploy2group where group_id=(" +
                "select group_id from group2host where host_id = ?))" +
                "union " +
                "SELECT DISTINCT " +
                "classtime_section " +
                "FROM " +
                "classtime c " +
                "WHERE" +
                "(" +
                "DATE_FORMAT(NOW(), '%H:%i') >= c.classtime_start " +
                "AND c.classtime_end >= DATE_FORMAT(NOW(), '%H:%i')" +
                ")" +
                "and c.classtime_ploy = (select ploy_id from classtimeploy2group where group_id=(" +
                "select group_id from group2host where host_id = ?))" +
                ")" +
                "AND cur.cur_week= (WEEKDAY(curdate()) + 1)";
        List<Object[]> curriculums = (List<Object[]>) session.createSQLQuery(sql)
                .setParameter(0, hostid)
                .setParameter(1, hostid)
                .setParameter(2, startRecordTime)
                .setParameter(3, hostid)
                .setParameter(4, hostid)
                .list();*/
        String sql = "SELECT cur.cur_id, cur.cur_date, cur.cur_week, cur.cur_section, cur.cur_host, GROUP_CONCAT(DISTINCT cur.cur_class), GROUP_CONCAT(DISTINCT cur.cur_teacher), GROUP_CONCAT(DISTINCT cur.cur_subject),cur.cur_unit " +
                "FROM classtime ct " +
                "RIGHT JOIN classtimeploy2group ct2 ON ct.classtime_ploy=ct2.ploy_id " +
                "RIGHT JOIN group2host g2 ON ct2.group_id = g2.group_id " +
                "RIGHT JOIN curriculum cur ON g2.host_id=cur.cur_host AND "+sqlSelect+" = ct.week_id AND cur.cur_section=ct.classtime_section " +
                "WHERE g2.host_id=? " +
                "AND ct.week_id= WEEKDAY(CURDATE()) + 1 " +
                "AND (" +
                "?>= ct.classtime_start " +
                "OR ct.classtime_start <= DATE_FORMAT(NOW(), '%H:%i')" +
                ")" +
                " AND (" +
                "? <= ct.classtime_end " +
                "OR ct.classtime_end >= DATE_FORMAT(NOW(), '%H:%i')" +
                ")";
        List<Object[]> curriculums = (List<Object[]>) session.createSQLQuery(sql)
                .setParameter(0, hostid)
                .setParameter(1, startRecordTime)
                .setParameter(2, startRecordTime)
                .list();

        return curriculums;
    }

    /**
     * 保存课表
     * @param curriculum
     * @return
     * @throws Exception
     */
    @Override
    public boolean save(Curriculum curriculum) throws Exception
    {
//        this.getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
        this.getHibernateTemplate().save(curriculum);
        return true;
    }

    /**
     * 更新课表
     * @param curriculum
     * @return
     * @throws Exception
     */
    @Override
    public boolean update(Curriculum curriculum) throws Exception
    {
//        this.getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
        this.getHibernateTemplate().update(curriculum);
        return true;
    }

    /**
     * 更新周课表
     * @param curriculum
     * @return
     * @throws Exception
     */
    @Override
    public boolean updateWeekCurriculum(Curriculum curriculum) throws Exception
    {
        Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();

        session.createQuery("UPDATE Curriculum SET curTeacher=?, curSubject=?, curUnit=? WHERE curHost=? AND curSection=? AND curWeek=?")
                .setParameter(0, curriculum.getCurTeacher())
                .setParameter(1, curriculum.getCurSubject())
                .setParameter(2, curriculum.getCurUnit())
                .setParameter(3, curriculum.getCurHost())
                .setParameter(4, curriculum.getCurSection())
                .setParameter(5, curriculum.getCurWeek()).executeUpdate();
        return true;

    }

    /**
     * 更新总课表
     * @param curriculum
     * @return
     * @throws Exception
     */
    @Override
    public boolean updateDateCurriculum(Curriculum curriculum) throws Exception
    {
        Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();

        session.createQuery("UPDATE Curriculum SET curTeacher=?, curSubject=?, curUnit=? WHERE curHost=? AND curSection=? AND curDate=?")
                .setParameter(0, curriculum.getCurTeacher())
                .setParameter(1, curriculum.getCurSubject())
                .setParameter(2, curriculum.getCurUnit())
                .setParameter(3, curriculum.getCurHost())
                .setParameter(4, curriculum.getCurSection())
                .setParameter(5, curriculum.getCurDate()).executeUpdate();
        return true;
    }

    /**
     * 删除课表
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public boolean delete(int id) throws Exception
    {
        final Curriculum curriculum = new Curriculum();
        curriculum.setCurId(id);
        this.getHibernateTemplate().delete(curriculum);
        return true;
    }

    /**
     * 获取课表信息
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Curriculum getInfo(int id) throws Exception
    {
        return this.getHibernateTemplate().get(Curriculum.class,id);
    }
    /**
     * 获取某条课表信息
     *
     * @param curDate Date，curClasstime int,curHost int
     * @return Curriculum 类型数据
     * @throws Exception
     */
    @Override
    public Curriculum getCurriculum_term(Date curDate, int curSection, int curHost)
    {
        try
        {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr = df.format(curDate);
            List<Curriculum> curriculumList = (List<Curriculum>)this.getHibernateTemplate().
                    find("from Curriculum c where c.curDate = '"+dateStr+"' and c.curSection =" +curSection
                            +" and c.curHost ="+curHost);
            if(!curriculumList.isEmpty())
            {
                // add by lichong   如果查出来一个终端有两套课表，就删除一套
                if(curriculumList.size()>1){
                    this.getHibernateTemplate().delete(curriculumList.get(1));
                }
                // add by lichong end
                return curriculumList.get(0);
            }
        }
        catch (Exception e)
        {
            logger.error("", e);
        }
        return null;
    }
    /**
     * 获取某条课表信息
     *
     * @param week int，curClasstime int,curHost int
     * @return Curriculum 类型数据
     * @throws Exception
     */
    @Override
    public Curriculum getCurriculum_week(int week, int curSection, int curHost)  throws Exception
    {
        Curriculum curriculum = new Curriculum();
        curriculum.setCurWeek(week);

        curriculum.setCurSection(curSection);

        curriculum.setCurHost(curHost);
        List<Curriculum> curriculumList = (List<Curriculum>) this.getHibernateTemplate().findByExample(curriculum);
        if(!curriculumList.isEmpty())
        {
            // add by lichong   如果查出来一个终端有两套课表，就删除一套
            if(curriculumList.size()>1){
                this.getHibernateTemplate().delete(curriculumList.get(1));
            }
            // add by lichong end
            return curriculumList.get(0);
        }
        return null;
    }

    /**
     * 清空课表信息
     * @throws Exception
     */
    @Override
    public void trucateCurrriculum() throws Exception {
        Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
        session.createSQLQuery("truncate table curriculum").executeUpdate();
    }

    /**
     * 删除周课表
     * @param hostid
     * @param curSection
     * @param week_id
     * @throws Exception
     */
    @Override
    public void delWeekCurriculum(int hostid, int curSection, int week_id) throws Exception {
        Curriculum curriculum = getCurriculum(hostid, curSection, week_id);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().delete(curriculum);
    }

    /**
     * 删除日期课表
     * @param hostid
     * @param curSection
     * @param date
     * @throws Exception
     */
    @Override
    public void delDateCurriculum(int hostid, int curSection, String date) throws Exception {
        Curriculum curriculum = getCurriculum(hostid, curSection, date);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().delete(curriculum);
    }

    @Override
    public List getCurriculum() throws Exception {
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("from Curriculum").list();
    }

}
