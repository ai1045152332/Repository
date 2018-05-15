package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.dao.RingbellDao;
import com.honghe.recordhibernate.entity.Ringbell;
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
 * Created by lch on 2015/6/18.
 */
@Repository
public class RingbellDaoImpl extends BaseDao implements RingbellDao {

    private final static Logger logger = Logger.getLogger(RingbellDaoImpl.class);

    /**
     * 获取某定时计划的信息
     *
     * @param id int，
     * @return Policy 类型数据
     * @throws Exception
     */
    @Override
    public Ringbell getRingbell(int id)  throws Exception
    {
        try
        {
            List<Ringbell> policies =(List<Ringbell>) this.getHibernateTemplate().find("from Ringbell p where p.ringbellId="+id);
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
    public List<Object[]> getRingbellById(final int id ) throws Exception
    {
        try {
            List<Object[]> authlist=(List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                public Object doInHibernate(Session session) throws HibernateException {
                    List list = session.createSQLQuery(
                            "SELECT DISTINCT p.r_id,p.r_loop,p.r_looptype,p.r_date,r_week,r_time," +
                                    "r_singletime,r_uid,r_username,r_createtime," +
                                    "GROUP_CONCAT( DISTINCT conv( oct(p2h.h_id ) , 8, 10 )) as hostids," +
                                    "GROUP_CONCAT(DISTINCT p2h.h_name) as hosts " +
                                    "FROM ringbell p "+
                                    "LEFT JOIN ringbell2host p2h on p.r_id = p2h.r_id " +
                                    "WHERE p.r_id = " + id+" "+
                                    "GROUP BY p.r_id "

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
     * @param ringbell Ringbell，
     * @return HashMap<String,Integer> 类型数据
     * @throws Exception
     */
    @Override
    public HashMap<String,Integer> getRingbellHosts(Ringbell ringbell) throws Exception
    {
        try
        {
            final int id = ringbell.getRingbellId();
            List<Object[]> hostlist=(List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                public Object doInHibernate(Session session) throws HibernateException {
                    List list = session.createSQLQuery(
                            "SELECT DISTINCT p2h.h_id,p2h.h_name " +
                                    "FROM ringbell p "+
                                    "INNER JOIN ringbell2host p2h on p.r_id = p2h.r_id " +
                                    "WHERE p.r_id = " + id

                    ).list();
                    return list;
                }
            });
            HashMap<String, Integer> policyHosts = new HashMap<String, Integer>(0);
            if(hostlist != null && !hostlist.isEmpty()) {
                for (int i = 0; i < hostlist.size(); i++) {
                    int hostId = Integer.parseInt(hostlist.get(i)[0].toString());
                    policyHosts.put("" + hostId, new Integer(hostId));
                }
            }
            return policyHosts;
        }
        catch (Exception e)
        {
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
//    public void getRingbellList(final Page<Ringbell> page) throws Exception {
//        HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
//        List countList = hibernateTemplate.find("select count(*) from Ringbell");
//        int totalCount = Integer.parseInt(countList.get(0).toString());
//        page.setTotalPageSize(totalCount);
//        if (totalCount != 0) {
//            DetachedCriteria crit = DetachedCriteria.forClass(Ringbell.class);
//            crit.addOrder(Order.desc("ringbellId"));
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
    public List<Ringbell> getRingbellList() throws Exception {
        try
        {
            return  (List<Ringbell>) this.getHibernateTemplate().find("from Ringbell");
        }
        catch (Exception e)
        {
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
    public List<Object[]> getRingbellListByUser(Page page,int uid,String type) throws Exception
    {
        HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
        List<Object[]> result = null;
        List countList = null;
        String s_sql = "";
        if(uid==0)
        {
            s_sql = "SELECT COUNT(p.r_id) FROM ringbell p WHERE p.r_type='"+type+"'";
        }
        else
        {
            s_sql = "SELECT COUNT(distinct p.r_id) " +
                    "FROM ringbell p " +
                    "INNER JOIN ringbell2host p2h on p.r_id = p2h.r_id " +
                    "WHERE p2h.h_id in " +
                    "(" +
                    "   SELECT g2h.host_id " +
                    "   FROM group2host g2h,group2user g2u " +
                    "   WHERE g2h.group_id = g2u.group_id AND g2u.user_id = " +uid+
                    ") AND p.r_type='"+type+"'";
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
        if (totalCount != 0)
        {
            String str_sql =
                    "SELECT DISTINCT p.r_id,p.r_loop,p.r_looptype,p.r_date,p.r_week,p.r_time," +
                            "p.r_singletime,p.r_uid,p.r_username,p.r_createtime," +
                            "GROUP_CONCAT( DISTINCT conv( oct(p2h.h_id ) , 8, 10 )) as hostids," +
                            "GROUP_CONCAT(DISTINCT p2h.h_name) as hosts " +
                            "FROM ringbell p ";
            if(uid == 0)
            {
                str_sql = str_sql +
                        "LEFT JOIN ringbell2host p2h on p.r_id = p2h.r_id " +
                        "WHERE p.r_type='"+type+"'"+
                        "GROUP BY p.r_id " +
                        "ORDER by p.r_id desc ,p2h.h_id ASC " +
                        "LIMIT "+page.getFirstResult()+","+page.getPageSize();
            }
            else
            {
                str_sql = str_sql +
                        "INNER JOIN signalplan2host p2h on p.s_id = p2h.s_id " +
                        "WHERE p2h.h_id in " +
                        "(" +
                        "   SELECT g2h.h_id " +
                        "   FROM group2host g2h,group2user g2u " +
                        "   WHERE g2h.group_id = g2u.group_id and g2u.user_id = " + uid +
                        ") AND p.r_type='"+type+"'" +
                        "GROUP BY p.s_id " +
                        "ORDER by p.s_id desc ,p2h.h_id ASC " +
                        "LIMIT "+page.getFirstResult()+","+page.getPageSize();
            }
            final String sql  = str_sql;
            result =(List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
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
     * @param ringbell Ringbell 需要保存的定时计划信息
     * @throws Exception
     */
    @Override
    public boolean saveRingbell(Ringbell ringbell) throws Exception
    {
        try
        {
            this.getHibernateTemplate().save(ringbell);
            return true;
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            logger.error("", e);
            return false;
        }
    }

    /**
     * 修改定时计划信息
     * @param ringbell Ringbell 需要修改的定时计划信息
     * @return boolean 修改是否成功
     * @throws Exception
     */
    @Override
    public boolean updateRingbell(Ringbell ringbell)  throws Exception
    {
        try {
            this.getHibernateTemplate().update(ringbell);
            return true;
        }
        catch (Exception e) {
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
//    public void delRingbell(int id)  throws Exception
//    {
//        try {
//            Ringbell ringbell = new Ringbell();
//            ringbell.setRingbellId(id);
//            this.getHibernateTemplate().delete(ringbell);
//        }
//        catch (Exception e)
//        {
//            //e.printStackTrace();
//            logger.error("", e);
//        }
//    }
    /**
     * 删除用户信息
     * @param ringbell Ringbell;
     * @throws Exception
     */
    @Override
    public boolean delRingbell(Ringbell ringbell)  throws Exception
    {
        try {
            this.getHibernateTemplate().delete(ringbell);
            return true;
        }
        catch (Exception e)
        {
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
//    public void delRingbellHostBySql(final int id) throws Exception
//    {
//        Object result =   this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
//            @Override
//            public Object doInHibernate(Session session) throws HibernateException {
//                int result =  session.createSQLQuery("delete from ringbell2host where r_id ="+id).executeUpdate();
//                return result;
//            }
//        });
//    }
//
//    /**
//     * 将该定时计划所属的某个班级关系删除
//     * @param rid int 定时计划id;
//     ** @param hid int 班级id;
//     * @throws Exception
//     */
//    @Override
//    public void delRingbellHostBySql(final int rid,final int hid) throws Exception
//    {
//        Object result =   this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
//            @Override
//            public Object doInHibernate(Session session) throws HibernateException {
//                int result = session.createSQLQuery("delete from ringbell2host where r_id =" + rid + " and h_id =" + hid).executeUpdate();
//                return result;
//            }
//        });
//    }

    /**
     * 测试该ID的定时计划是否已经发布给班级
     * @param id int 定时计划id;
     * @return boolean 为true则表示已分配，false未分配
     * @throws Exception
     */
    @Override
    public boolean isRingbellHostExists(final int id) throws Exception
    {

        String result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select count(*) from ringbell2host where h_id="+id).uniqueResult();
            }
        }).toString();
        if(Integer.parseInt(result)>0)
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
//    public boolean isRingbellHostExistsByHost(final int hid) throws Exception
//    {
//
//        String result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
//            @Override
//            public Object doInHibernate(Session session) throws HibernateException {
//                return session.createSQLQuery("select count(*) from ringbell2host where h_id="+hid).uniqueResult();
//            }
//        }).toString();
//        if(Integer.parseInt(result)>0)
//            return true;
//        else
//            return false;
//    }
    /**
     *  给某个班级发布定时计划
     * @param ringbellId int 定时计划id；
     * @param hostId int 班级id
     * @throws Exception
     */
    @Override
    public void addRingbellToHost(final int ringbellId, final int hostId, final String hostName) throws Exception
    {
        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                session.createSQLQuery("insert into ringbell2host(r_id,h_id,h_name) values("+ringbellId+","+hostId+",'"+hostName+"')").executeUpdate();
                return null;
            }
        });
    }
    /**
     * 将该定时计划所分配的班级关系删除
     * @param id int 定时计划id;
     * @throws Exception
     */
    @Override
    public void delRingbellHostByRingbell(final int id) throws Exception
    {
        Object result =   this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                int result =  session.createSQLQuery("delete from ringbell2host where r_id ="+id).executeUpdate();
                return result;
            }
        });
    }

    /**
     * 将该班级所分配的定时计划关系删除
     * @param id int 班级id;
     * @throws Exception
     */
    @Override
    public void delRingbellHostByHost(final int id) throws Exception
    {
        Object result =   this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                int result =  session.createSQLQuery("delete from ringbell2host where h_id ="+id).executeUpdate();
                return result;
            }
        });
    }
}
