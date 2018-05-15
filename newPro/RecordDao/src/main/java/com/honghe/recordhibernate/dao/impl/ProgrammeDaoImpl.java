package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.dao.ProgrammeDao;
import com.honghe.recordhibernate.entity.Policy;
import com.honghe.recordhibernate.entity.Programme;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by lyx on 2015-08-22.
 */
@Repository
public class ProgrammeDaoImpl extends BaseDao implements ProgrammeDao {
    private final static Logger logger = Logger.getLogger(ProgrammeDaoImpl.class);
    /**
     * 获取某节目切换计划信息
     *
     * @param id
     * @return Programme 类型数据
     * @throws Exception
     */
    @Override
    public Programme getProgramme(int id) throws Exception {
        try {
            List<Programme> programmes = (List<Programme>) this.getHibernateTemplate().find("from Programme p where p.proId =" + id);
            if (!programmes.isEmpty()) {
                return programmes.get(0);
            }
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("", e);
            return null;
        }

        return null;

    }

    /**
     * 获取某节目切换计划详细信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getProgrammeById(final int id) throws Exception {
        try {
            List<Object[]> authlist = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                public Object doInHibernate(Session session) throws HibernateException {
                    List list = session.createSQLQuery(
                            "SELECT DISTINCT p.p_id,p.p_type,p.p_loop,p.p_looptype,p.p_date,p_week,p_time," +
                                    "p_singletime,p_uid,u.user_name,p_createtime," +
                                    "GROUP_CONCAT( DISTINCT conv( oct(p2h.host_id ) , 8, 10 )) as hostids," +
                                    "GROUP_CONCAT(DISTINCT p2h.host_name) as hosts " +
                                    "FROM programme p " +
                                    "LEFT JOIN programme2host p2h on p.p_id = p2h.p_id " +
                                    "WHERE p.p_id = " + id + " " +
                                    "GROUP BY p.p_id "

                    ).list();
                    return list;
                }
            });
            return authlist;
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("", e);
            return null;
        }
    }


    /**
     * 获取某节目切换的host列表
     *
     * @param programme
     * @return HashMap<String,Integer> 类型数据
     * @throws Exception
     */
    @Override
    public HashMap<String, Integer> getProgrammeHosts(Programme programme) throws Exception {
        try {
            final int id = programme.getProId();
            List<Object[]> hostlist = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                public Object doInHibernate(Session session) throws HibernateException {
                    List list = session.createSQLQuery(
                            "SELECT DISTINCT p2h.host_id,p2h.host_name " +
                                    "FROM programme p " +
                                    "INNER JOIN programme2host p2h on p.p_id = p2h.p_id " +
                                    "WHERE p.p_id = " + id

                    ).list();
                    return list;
                }
            });
            HashMap<String, Integer> programmeHosts = new HashMap<String, Integer>(0);
            if (hostlist != null && !hostlist.isEmpty()) {
                for (int i = 0; i < hostlist.size(); i++) {
                    int hostId = Integer.parseInt(hostlist.get(i)[0].toString());
                    programmeHosts.put("" + hostId, new Integer(hostId));
                }
            }
            return programmeHosts;
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("", e);
        }
        return null;
    }

    /**
     * 按分页的方式获取定时节目切换计划列表
     *
     * @param page page Page<Programme> 自定义page<Programme>类，
     * @throws Exception
     */
    @Override
    public void getProgrammeList(Page<Programme> page) throws Exception {
        HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
        List countList = hibernateTemplate.find("select count(*) from Programme");
        int totalCount = Integer.parseInt(countList.get(0).toString());
        page.setTotalPageSize(totalCount);
        if (totalCount != 0) {
            DetachedCriteria crit = DetachedCriteria.forClass(Policy.class);
            crit.addOrder(Order.desc("proId"));
            List result = hibernateTemplate.findByCriteria(crit, page.getFirstResult(), page.getPageSize());
            page.setResult(result);
        }
    }

    /**
     * 获取所有定时节目切换计划列表
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Programme> getProgrammeList() throws Exception {
        try {
            return (List<Programme>) this.getHibernateTemplate().find("from Programme");
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("", e);
            return null;
        }
    }

    /**
     * 按分页的方式获取用户有关节目切换计划列表及host关系
     *
     * @param page
     * @param uid
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getProgrammeListByUser(Page page, int uid) throws Exception {
        HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
        List<Object[]> result = null;
        List countList = null;
        String s_sql = "";
        if (uid == 0) {
            s_sql = "SELECT COUNT(p.p_id) FROM programme p ";
        } else {
            s_sql = "SELECT COUNT(distinct p.p_id) " +
                    "FROM programme p " +
                    "INNER JOIN programme2host p2h on p.p_id = p2h.p_id " +
                    "WHERE p2h.host_id in " +
                    "(" +
                    "   SELECT g2h.host_id " +
                    "   FROM group2host g2h,group2user g2u " +
                    "   WHERE g2h.group_id = g2u.group_id AND g2u.user_id = " + uid +
                    ") ";
        }
        final String s_sql_count = s_sql;
        countList = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(s_sql_count).list();
            }
        });
        int totalCount = Integer.parseInt(countList.get(0).toString());
        page.setTotalPageSize(totalCount);
        if (totalCount != 0) {
            String str_sql =
                    "SELECT DISTINCT " +
                            "p.p_id,p.p_type,p.p_loop,p.p_looptype,p.p_date," +
                            "p_week,p_time,p_singletime,p_uid,p.p_username," +
                            "p_createtime,GROUP_CONCAT( DISTINCT conv( oct(p2h.host_id ) , 8, 10 )) as hostids,GROUP_CONCAT(DISTINCT p2h.host_name) as hosts,p_singnal " +
                            "FROM programme p ";
            if (uid == 0) {
                str_sql = str_sql +
                        "LEFT JOIN programme2host p2h on p.p_id = p2h.p_id " +
                        "GROUP BY p.p_id " +
                        "ORDER by p.p_id desc ,p2h.host_id ASC " +
                        "LIMIT " + page.getFirstResult() + "," + page.getPageSize();
            } else {
                str_sql = str_sql +
                        "INNER JOIN programme2host p2h on p.p_id = p2h.p_id " +
                        "where p.p_uid = "+uid+
                        " and p2h.host_id in " +
                        "(" +
                        "   SELECT g2h.host_id " +
                        "   FROM group2host g2h,group2user g2u " +
                        "   WHERE g2h.group_id = g2u.group_id and g2u.user_id = " + uid +
                        ") " +
                        "GROUP BY p.p_id " +
                        "ORDER by p.p_id desc ,p2h.host_id ASC " +
                        "LIMIT " + page.getFirstResult() + "," + page.getPageSize();
            }
            final String sql = str_sql;
            result = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery(sql).list();
                }
            });
        }
        return result;
    }

    /**
     * 保存定时计划
     *
     * @param programme Programme需要保存的定时计划信息
     * @return
     * @throws Exception
     */
    @Override
    public boolean saveProgramme(Programme programme) throws Exception {
        try {
            this.getHibernateTemplate().save(programme);
            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("", e);
            return false;
        }
    }

    /**
     * 修改定时计划信息
     *
     * @param programme Programme 需要修改的定时计划信息
     * @return boolean 修改是否成功
     * @throws Exception
     */
    @Override
    public boolean updateProgramme(Programme programme) throws Exception {
        try {
            this.getHibernateTemplate().update(programme);
            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("", e);
            return false;
        }
    }

    /**
     * 删除定时计划信息
     *
     * @param id int 定时计划id;
     * @throws Exception
     */
    @Override
    public void delProgramme(int id) throws Exception {

        try {
            Programme programme = new Programme();
            programme.setProId(id);
            this.getHibernateTemplate().delete(programme);
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("", e);
        }

    }

    /**
     * 删除用户信息
     *
     * @param programme Programme;
     * @throws Exception
     */
    @Override
    public boolean delProgramme(Programme programme) throws Exception {
        try {
            this.getHibernateTemplate().delete(programme);
            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("", e);
            return false;
        }
    }

    /**
     * 将该定时计划所属的班级关系删除
     *
     * @param id int 定时计划id;
     * @throws Exception
     */
    @Override
    public void delProgrammeHostBySql(final int id) throws Exception {
        Object result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                int result = session.createSQLQuery("delete from programme2host where p_id =" + id).executeUpdate();
                return result;
            }
        });
    }

    /**
     * 测试该ID的定时计划是否已经发布给班级
     *
     * @param id int 定时计划id;
     * @return boolean 为true则表示已分配，false未分配
     * @throws Exception
     */
    @Override
    public boolean isProgrammeHostExists(final int id) throws Exception {
        String result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select count(*) from programme2host where p_id=" + id).uniqueResult();
            }
        }).toString();
        if (Integer.parseInt(result) > 0)
            return true;
        else
            return false;
    }

    /**
     * 测试该班级是否有定时计划
     *
     * @param hid int 班级id;
     * @return boolean 为true则表示已分配，false未分配
     * @throws Exception
     */
    @Override
    public boolean isProgrammeHostExistsByHost(final int hid) throws Exception {
        String result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select count(*) from programme2host where host_id=" + hid).uniqueResult();
            }
        }).toString();
        if (Integer.parseInt(result) > 0)
            return true;
        else
            return false;
    }

    /**
     * 给某个班级发布定时计划
     *
     * @param programmeId int 定时计划id；
     * @param hostId      int 班级id
     * @throws Exception
     */
    @Override
    public void addProgrammeToHost(final int programmeId, final int hostId,final String hostName) throws Exception {
        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                session.createSQLQuery("insert into programme2host(p_id,host_id,host_name) values(" + programmeId + "," + hostId+",'"+hostName +"')").executeUpdate();
                return null;
            }
        });
    }

    /**
     * 将该定时计划所分配的班级关系删除
     *
     * @param id int 定时计划id;
     * @throws Exception
     */
    @Override
    public void delProgrammeHostByProgramme(final int id) throws Exception {

        Object result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                int result = session.createSQLQuery("delete from programme2host where p_id =" + id).executeUpdate();
                return result;
            }
        });

    }

    /**
     * 将该定时计划所属的某个班级关系删除
     *
     * @param pid int 定时计划id;
     *            * @param hid int 班级id;
     * @throws Exception
     */
    @Override
    public void deProgrammeHostBySql(final int pid, final int hid) throws Exception {
        Object result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                int result = session.createSQLQuery("delete from programme2host where p_id =" + pid + " and host_id =" + hid).executeUpdate();
                return result;
            }
        });
    }

    @Override
    public void delProgrammeHostByHost(final int id) throws Exception {
        Object result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                int result = session.createSQLQuery("delete from programme2host where host_id =" + id).executeUpdate();
                return result;
            }
        });
    }
}
