package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.dao.SignalplanDao;
import com.honghe.recordhibernate.entity.Signalplan;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by lch on 2014/9/23.
 */
@Repository
public class SignalplanDaoImpl extends BaseDao implements SignalplanDao {

    private final static Logger logger = Logger.getLogger(SignalplanDaoImpl.class);
    /**
     * 获取某定时计划的信息
     *
     * @param id int，
     * @return Policy 类型数据
     * @throws Exception
     */
    @Override
    public Signalplan getSignalplan(int id)  throws Exception
    {
        try
        {
            List<Signalplan> policies =(List<Signalplan>) this.getHibernateTemplate().find("from Signalplan p where p.signalId ="+id);
            if(!policies.isEmpty())
            {
                return policies.get(0);
            }
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            logger.error("", e);
            return null;
        }

        return null;
    }
    /**
     * 获取某定时计划详细信息
     *
     * @param id int 定时计划id;
     * @return List 类型数据
     * @throws Exception
     */
    @Override
    public List<Object[]> getSignalplanById(final int id ) throws Exception
    {
        try {
            List<Object[]> authlist=(List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                public Object doInHibernate(Session session) throws HibernateException {
                    List list = session.createSQLQuery(
                            "SELECT DISTINCT p.s_id,p.s_signal,p.s_signal_code,p.s_loop,p.s_looptype,p.s_date,s_week,s_time," +
                                    "s_singletime,s_uid,s_username,s_createtime," +
                                    "GROUP_CONCAT( DISTINCT conv( oct(p2h.h_id ) , 8, 10 )) as hostids," +
                                    "GROUP_CONCAT(DISTINCT p2h.h_name) as hosts " +
                                    "FROM signalplan p "+
                                    "LEFT JOIN signalplan2host p2h on p.s_id = p2h.s_id " +
                                    "WHERE p.s_id = " + id+" "+
                                    "GROUP BY p.s_id "

                    ).list();
                    return list;
                }
            });
            return authlist;
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            logger.error("", e);
            return null;
        }
    }
    /**
     * 获取某定时计划的host列表
     *
     * @param signalplan Signalplan，
     * @return HashMap<String,Integer> 类型数据
     * @throws Exception
     */
    @Override
    public HashMap<String,Integer> getSingnalHosts(Signalplan signalplan) throws Exception
    {
        try
        {
            final int id = signalplan.getSignalId();
            List<Object[]> hostlist=(List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                public Object doInHibernate(Session session) throws HibernateException {
                    List list = session.createSQLQuery(
                            "SELECT DISTINCT p2h.h_id,p2h.h_name " +
                                    "FROM signalplan p " +
                                    "INNER JOIN signalplan2host p2h on p.s_id = p2h.s_id " +
                                    "WHERE p.s_id = " + id

                    ).list();
                    return list;
                }
            });
            HashMap<String, Integer> policyHosts = new HashMap<String, Integer>(0);
            if (hostlist != null && !hostlist.isEmpty()) {
                for (int i = 0; i < hostlist.size(); i++) {
                    int hostId = Integer.parseInt(hostlist.get(i)[0].toString());
                    policyHosts.put("" + hostId, new Integer(hostId));
                }
            }
            return policyHosts;
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("", e);
        }
        return null;
    }
//    /**
//     * 按分页的方式获取定时计划列表
//     *
//     * @param page Page<Signalplan> 自定义page<Signalplan>类，
//     * @throws Exception
//     */
//    @Override
//    public void getSignalplanList(final Page<Signalplan> page) throws Exception {
//        HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
//        List countList = hibernateTemplate.find("select count(*) from Signalplan");
//        int totalCount = Integer.parseInt(countList.get(0).toString());
//        page.setTotalPageSize(totalCount);
//        if (totalCount != 0) {
//            DetachedCriteria crit = DetachedCriteria.forClass(Signalplan.class);
//            crit.addOrder(Order.desc("signalId"));
//            List result = hibernateTemplate.findByCriteria(crit, page.getFirstResult(), page.getPageSize());
//            page.setResult(result);
//        }
//    }

    /**
     * 获取所有定时计划列表
     *
     * @throws Exception
     */
    @Override
    public List<Signalplan> getSignalplanList() throws Exception {
        try {
            return (List<Signalplan>) this.getHibernateTemplate().find("from Signalplan ");
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("", e);
            return null;
        }
    }

    /**
     * 按分页的方式获取用户有关定时计划列表及host关系
     *
     * @param page Page 自定义page类，
     * @throws Exception
     */
    @Override
    public List<Object[]> getSignalplanListByUser(Page page, int uid, String type) throws Exception {
        HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
        List<Object[]> result = null;
        List countList = null;
        String s_sql = "";
        if (uid == 0) {
            s_sql = "SELECT COUNT(p.s_id) FROM signalplan p " +
                    "WHERE   p.s_type= '" + type+"'";
        } else {
            s_sql = "SELECT COUNT(distinct p.s_id) " +
                    "FROM signalplan p " +
                    "INNER JOIN signalplan2host p2h on p.s_id = p2h.s_id " +
                    "WHERE  p2h.h_id in " +
                    "(" +
                    "   SELECT g2h.host_id " +
                    "   FROM group2host g2h,group2user g2u " +
                    "   WHERE g2h.group_id = g2u.group_id AND g2u.user_id = " + uid +
                    ") AND p.s_type =  '" + type+"'";
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
                    "SELECT DISTINCT p.s_id,p.s_signal,p.s_signal_code,p.s_loop,p.s_looptype,p.s_date,p.s_week,p.s_time," +
                            "p.s_singletime,p.s_uid,p.s_username, p.s_createtime," +
                            "GROUP_CONCAT( DISTINCT conv( oct(p2h.h_id ) , 8, 10 )) as hostids," +
                            "GROUP_CONCAT(DISTINCT p2h.h_name) as hosts " +
                            "FROM signalplan p ";
            if (uid == 0) {
                str_sql = str_sql +
                        "LEFT JOIN signalplan2host p2h on p.s_id = p2h.s_id " +
                        "WHERE p.s_type = '" + type+"'"+
                        "GROUP BY p.s_id " +
                        "ORDER by p.s_id desc ,p2h.h_id ASC " +
                        "LIMIT " + page.getFirstResult() + "," + page.getPageSize();
            } else {
                str_sql = str_sql +
                        "INNER JOIN signalplan2host p2h on p.s_id = p2h.s_id " +
                        "WHERE p2h.h_id in " +
                        "(" +
                        "   SELECT g2h.host_id " +
                        "   FROM group2host g2h,group2user g2u " +
                        "   WHERE g2h.group_id = g2u.group_id and g2u.user_id = " + uid +
                        ")  AND p.s_type = '" + type+"'"+
                        "GROUP BY p.s_id " +
                        "ORDER by p.s_id desc ,p2h.h_id ASC " +
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
     * @param signalplan Signalplan 需要保存的定时计划信息
     * @throws Exception
     */
    @Override
    public boolean saveSignalplan(Signalplan signalplan) throws Exception {
        try {
            this.getHibernateTemplate().save(signalplan);
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
     * @param signalplan Signalplan 需要修改的定时计划信息
     * @return boolean 修改是否成功
     * @throws Exception
     */
    @Override
    public boolean updateSignalplan(Signalplan signalplan) throws Exception {
        try {
            this.getHibernateTemplate().update(signalplan);
            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("", e);
            return false;
        }
    }

//    /**
//     * 删除定时计划信息
//     * @param id int 定时计划id;
//     * @throws Exception
//     */
//    @Override
//    public void delSignalplan(int id)  throws Exception
//    {
//        try {
//            Signalplan signalplan = new Signalplan();
//            signalplan.setSignalId(id);
//            this.getHibernateTemplate().delete(signalplan);
//        }
//        catch (Exception e)
//        {
//            //e.printStackTrace();
//            logger.error("", e);
//        }
//    }

    /**
     * 删除用户信息
     *
     * @param signalplan Signalplan;
     * @throws Exception
     */
    @Override
    public boolean delSignalplan(Signalplan signalplan) throws Exception {
        try {
            this.getHibernateTemplate().delete(signalplan);
            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("", e);
            return false;
        }
    }

//    /**
//     * 将该定时计划所属的班级关系删除
//     * @param id int 定时计划id;
//     * @throws Exception
//     */
//    @Override
//    public void delSignalplanHostBySql(final int id) throws Exception
//    {
//        Object result =   this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
//            @Override
//            public Object doInHibernate(Session session) throws HibernateException {
//                int result =  session.createSQLQuery("delete from signalplan2host where s_id ="+id).executeUpdate();
//                return result;
//            }
//        });
//    }

//    /**
//     * 将该定时计划所属的某个班级关系删除
//     * @param sid int 定时计划id;
//     ** @param hid int 班级id;
//     * @throws Exception
//     */
//    @Override
//    public void delSignalplanHostBySql(final int sid,final int hid) throws Exception
//    {
//        Object result =   this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
//            @Override
//            public Object doInHibernate(Session session) throws HibernateException {
//                int result = session.createSQLQuery("delete from signalplan2host where s_id =" + sid + " and h_id =" + hid).executeUpdate();
//                return result;
//            }
//        });
//    }

    /**
     * 测试该ID的定时计划是否已经发布给班级
     *
     * @param id int 定时计划id;
     * @return boolean 为true则表示已分配，false未分配
     * @throws Exception
     */
    @Override
    public boolean isSignalplanHostExists(final int id) throws Exception {

        String result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select count(*) from signalplan2host where h_id=" + id).uniqueResult();
            }
        }).toString();
        if (Integer.parseInt(result) > 0)
            return true;
        else
            return false;
    }
//    /**
//     * 测试该班级是否有定时计划
//     * @param hid int 班级id;
//     * @return boolean 为true则表示已分配，false未分配
//     * @throws Exception
//     */
//    @Override
//    public boolean isSignalplanHostExistsByHost(final int hid) throws Exception
//    {
//
//        String result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
//            @Override
//            public Object doInHibernate(Session session) throws HibernateException {
//                return session.createSQLQuery("select count(*) from signalplan2host where h_id="+hid).uniqueResult();
//            }
//        }).toString();
//        if(Integer.parseInt(result)>0)
//            return true;
//        else
//            return false;
//    }

    /**
     * 给某个班级发布定时计划
     *
     * @param signalplanid int 定时计划id；
     * @param hostId       int 班级id
     * @throws Exception
     */
    @Override
    public void addSignalplanToHost(final int signalplanid, final int hostId, final String hostName) throws Exception {
        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                session.createSQLQuery("insert into signalplan2host(s_id,h_id,h_name) values(" + signalplanid + "," + hostId + ",'" + hostName + "')").executeUpdate();
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
    public void delSignalplanHostBySignalplan(final int id) throws Exception {
        Object result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                int result = session.createSQLQuery("delete from signalplan2host where s_id =" + id).executeUpdate();
                return result;
            }
        });
    }

    /**
     * 将该班级所分配的定时计划关系删除
     *
     * @param id int 班级id;
     * @throws Exception
     */
    @Override
    public void delSignalplanHostByHost(final int id) throws Exception {
        Object result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                int result = session.createSQLQuery("delete from signalplan2host where h_id =" + id).executeUpdate();
                return result;
            }
        });
    }
}
