package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.dao.PolicyDao;
import com.honghe.recordhibernate.entity.Policy;
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
 * Created by lch on 2014/9/23.
 */
@Repository
public class PolicyDaoImpl extends BaseDao implements PolicyDao {

    private final static Logger logger = Logger.getLogger(PolicyDaoImpl.class);

    /**
     * 获取某定时计划的信息
     *
     * @param id int，
     * @return Policy 类型数据
     * @throws Exception
     */
    @Override
    public Policy getPolicy(int id)  throws Exception
    {
        try
        {
            List<Policy> policies =(List<Policy>) this.getHibernateTemplate().find("from Policy p where p.pId ="+id);
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
    public List<Object[]> getPolicyById(final int id ) throws Exception
    {
        try {
            List<Object[]> authlist=(List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                public Object doInHibernate(Session session) throws HibernateException {
                    List list = session.createSQLQuery(
                            "SELECT DISTINCT p.p_id,p.p_type,p.p_loop,p.p_looptype,p.p_date,p_week,p_time," +
                            "p_singletime,p_uid,u.user_name,p_createtime," +
                            "GROUP_CONCAT( DISTINCT conv( oct(p2h.host_id ) , 8, 10 )) as hostids," +
                            "GROUP_CONCAT(DISTINCT p2h.host_name) as hosts " +
                            "FROM policy p "+
                            "LEFT JOIN policy2host p2h on p.p_id = p2h.p_id " +
                            "LEFT JOIN user u on p.p_uid = u.user_id " +
                            "WHERE p.p_id = " + id+" "+
                            "GROUP BY p.p_id "

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
     * @param policy Policy，
     * @return HashMap<String,Integer> 类型数据
     * @throws Exception
     */
    @Override
    public HashMap<String,Integer> getPolicyHosts(Policy policy) throws Exception
    {
        try
        {
            final int id = policy.getpId();
            List<Object[]> hostlist=(List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                public Object doInHibernate(Session session) throws HibernateException {
                    List list = session.createSQLQuery(
                            "SELECT DISTINCT p2h.host_id,p2h.host_name " +
                                    "FROM policy p "+
                                    "INNER JOIN policy2host p2h on p.p_id = p2h.p_id " +
                                    "WHERE p.p_id = " + id

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
//     * @param page Page<Policy> 自定义page<Policy>类，
//     * @throws Exception
//     */
//    @Override
//    public void getPolicyList(final Page<Policy> page) throws Exception {
//        HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
//        List countList = hibernateTemplate.find("select count(*) from Policy");
//        int totalCount = Integer.parseInt(countList.get(0).toString());
//        page.setTotalPageSize(totalCount);
//        if (totalCount != 0) {
//            DetachedCriteria crit = DetachedCriteria.forClass(Policy.class);
//            crit.addOrder(Order.desc("pId"));
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
    public List<Policy> getPolicyList() throws Exception {
        try
        {
            return  (List<Policy>) this.getHibernateTemplate().find("from Policy");
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
    public List<Object[]> getPolicyListByUser(Page page,int uid,String type) throws Exception
    {
        HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
        List<Object[]> result = null;
        List countList = null;
        String s_sql = "";
        if(uid==0)
        {
            s_sql = "SELECT COUNT(p.p_id) FROM policy p WHERE p.p_devicetype = '"+type+"'";
        }
        else
        {
            s_sql = "SELECT COUNT(distinct p.p_id) " +
                    "FROM policy p " +
                    "INNER JOIN policy2host p2h on p.p_id = p2h.p_id " +
                    "WHERE p2h.host_id in " +
                    "(" +
                    "   SELECT g2h.host_id " +
                    "   FROM group2host g2h,group2user g2u " +
                    "   WHERE g2h.group_id = g2u.group_id AND g2u.user_id = " +uid+
                    ") AND p.p_devicetype = '"+type+"'";
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
                    "SELECT DISTINCT p.p_id,p.p_type,p.p_loop,p.p_looptype,p.p_date,p_week,p_time," +
                    "p.p_singletime,p.p_uid,p.p_createtime," +
                    "GROUP_CONCAT( DISTINCT conv( oct(p2h.host_id ) , 8, 10 )) as hostids," +
                    "GROUP_CONCAT(DISTINCT p2h.host_name) as hosts ,p.p_username " +
                    "FROM policy p ";
            if(uid == 0)
            {
                str_sql = str_sql +
                        "LEFT JOIN policy2host p2h on p.p_id = p2h.p_id " +
                        " WHERE p.p_devicetype = '"+type+"'"+
                        " GROUP BY p.p_id " +
                        "ORDER by p.p_id desc ,p2h.host_id ASC " +
                        "LIMIT "+page.getFirstResult()+","+page.getPageSize();
            }
            else
            {
                str_sql = str_sql +
                        "INNER JOIN policy2host p2h on p.p_id = p2h.p_id " +
                        "where p.p_uid = "+uid+
                        " and p2h.host_id in " +
                        "(" +
                        "   SELECT g2h.host_id " +
                        "   FROM group2host g2h,group2user g2u " +
                        "   WHERE g2h.group_id = g2u.group_id and g2u.user_id = " + uid +
                        ") AND p.p_devicetype = '"+type+"'" +
                        "GROUP BY p.p_id " +
                        "ORDER by p.p_id desc ,p2h.host_id ASC " +
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
     * @param policy Policy 需要保存的定时计划信息
     * @throws Exception
     */
    @Override
    public boolean savePolicy(Policy policy) throws Exception
    {
        try
        {
            this.getHibernateTemplate().save(policy);
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
     * @param policy Policy 需要修改的定时计划信息
     * @return boolean 修改是否成功
     * @throws Exception
     */
    @Override
    public boolean updatePolicy(Policy policy)  throws Exception
    {
        try {
            this.getHibernateTemplate().update(policy);
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
//    public void delPolicy(int id)  throws Exception
//    {
//        try {
//            Policy policy = new Policy();
//            policy.setpId(id);
//            this.getHibernateTemplate().delete(policy);
//        }
//        catch (Exception e)
//        {
//            //e.printStackTrace();
//            logger.error("", e);
//        }
//    }
    /**
     * 删除用户信息
     * @param policy Policy;
     * @throws Exception
     */
    @Override
    public boolean delPolicy(Policy policy)  throws Exception
    {
        try {
            this.getHibernateTemplate().delete(policy);
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
//    public void delPolicyHostBySql(final int id) throws Exception
//    {
//        Object result =   this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
//            @Override
//            public Object doInHibernate(Session session) throws HibernateException {
//                int result =  session.createSQLQuery("delete from policy2host where p_id ="+id).executeUpdate();
//                return result;
//            }
//        });
//    }
//
//    /**
//     * 将该定时计划所属的某个班级关系删除
//     * @param pid int 定时计划id;
//     ** @param hid int 班级id;
//     * @throws Exception
//     */
//    @Override
//    public void delPolicyHostBySql(final int pid,final int hid) throws Exception
//    {
//        Object result =   this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
//            @Override
//            public Object doInHibernate(Session session) throws HibernateException {
//                int result =  session.createSQLQuery("delete from policy2host where p_id ="+pid +" and host_id ="+hid).executeUpdate();
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
    public boolean isPolicyHostExists(final int id) throws Exception
    {

        String result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select count(*) from policy2host where p_id="+id).uniqueResult();
            }
        }).toString();
        if(Integer.parseInt(result)>0)
            return true;
        else
            return false;
    }
    /**
     * 测试该班级是否有定时计划
     * @param hid int 班级id;
     * @return boolean 为true则表示已分配，false未分配
     * @throws Exception
     */
    @Override
    public boolean isPolicyHostExistsByHost(final int hid) throws Exception
    {

        String result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select count(*) from policy2host where host_id="+hid).uniqueResult();
            }
        }).toString();
        if(Integer.parseInt(result)>0)
            return true;
        else
            return false;
    }
    /**
     *  给某个班级发布定时计划
     * @param policyId int 定时计划id；
     * @param hostId int 班级id
     * @throws Exception
     */
    @Override
    public void addPolicyToHost(final int policyId, final int hostId,final String hostName) throws Exception
    {
        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                session.createSQLQuery("insert into policy2host(p_id,host_id,host_name) values("+policyId+","+hostId+",'"+hostName+"')").executeUpdate();
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
    public void delPolicyHostByPolicy(final int id) throws Exception
    {
        Object result =   this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                int result =  session.createSQLQuery("delete from policy2host where p_id ="+id).executeUpdate();
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
    public void delPolicyHostByHost(final int id) throws Exception
    {
        Object result =   this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                int result =  session.createSQLQuery("delete from policy2host where host_id ="+id).executeUpdate();
                return result;
            }
        });
    }
}
