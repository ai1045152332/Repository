package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.HostDao;
import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.entity.Host;
import jdk.nashorn.internal.objects.NativeUint32Array;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Moon on 2014/9/11.
 * Alter by wzz on 2015/07/15 设备管理服务整合
 */
@Repository
public class HostDaoImpl extends BaseDao implements HostDao {

    private final static Logger logger = Logger.getLogger(HostDaoImpl.class);

    @Override
    public Host findHost(final String ip) throws Exception {
        List hosts = this.getHibernateTemplate().find("from Host h where h.hostIpaddr='" + ip + "'");
        if (hosts.isEmpty()) return null;
        return (Host) hosts.get(0);
    }

    /**
     * 根据id获取主机信息
     *
     * @param id int 主机（教室）id;
     * @return Host类型
     * @throws Exception
     */
    @Override
    public Host getHostInfo(int id) throws Exception {
        Host host = this.getHibernateTemplate().get(Host.class, id);
        if (host != null) {
            return host;
        }
        return null;
    }

    /**
     * 获取所有教室列表
     *
     * @return List<Host>
     * @throws Exception
     */
    @Override
    public List<Host> getHostList() throws Exception {

        return (List<Host>) this.getHibernateTemplate().find("from Host");
    }

    /**
     * todo 加注释
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getHostList2(int userId) throws Exception {
        /*String strSql = "select distinct h.host_id,h.host_name,h.host_ipaddr,h.host_desc,h.host_username,h.host_password,h.host_serialno from host h";
        if (userId > 0) //如果userId>0为有效用户，则获取该用户分组下的所有主机；如果userId=0，则返回所有主机数据
        {
            strSql += ", group2host g2h,group2user g2u where h.host_id =g2h.host_id and g2u.group_id=g2h.group_id and user_id=" + userId;
        }*/
        String strSql = "select distinct g2h.host_id from group2host g2h,group2user g2u where  g2u.group_id=g2h.group_id and user_id=" + userId;
        final String sql = strSql;
        return (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).list();
            }
        });
    }

    /**
     * 获取未分组设备
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getUnknowGroup() throws Exception {
        final String sql = "select GROUP_CONCAT(host_id) as host_id_str,GROUP_CONCAT(group_id) from group2host";
        return (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).list();
            }
        });
    }

    /**
     * todo 加注释
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getHostsInGroup() throws Exception {
        final String sql = "select distinct host_id from group2host";
        return (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).list();
            }
        });
    }
//    /**
//     * 根据条件获取教室列表
//     *
//     * @return List<Host>
//     * @throws Exception
//     */
//    @Override
//    public List<Object[]> getHostListFromSql(final int hostgroupId, String condition, final int userId) throws Exception {
//
//        String strSql = "select h.host_id,h.host_name,h.host_ipaddr,h.host_desc,h.host_username,h.host_password,h.host_serialno from host h";
//        //String strSql="select DISTINCT hh.host_id,hh.host_name,hh.host_ipaddr,hh.host_desc,hh.host_username,hh.host_password,hh.host_serialno,ds.dspec_name,hh.dspec_id from (select distinct h.host_id,h.host_name,h.host_ipaddr,h.host_desc,h.host_username,h.host_password,h.host_serialno,h.dspec_id from host h, group2host g2h,group2user g2u where h.host_id =g2h.host_id ";
//        String userSql = "";
//
//        if (condition.equals("hostRelation"))//获取某一用户管理某一分组下的所有主机
//        {
//            if (userId > 0) {
//                userSql += " and g2u.group_id=g2h.group_id and user_id=" + userId;
//                //userSql += " and g2u.group_id=g2h.group_id and user_id="+userId;
//            }
//            strSql += ", group2host g2h where h.host_id =g2h.host_id  " + userSql + " and g2h.group_id=" + hostgroupId;
//            // strSql+=userSql+"and g2h.group_id=" +hostgroupId+" ) hh,dspecification ds WHERE hh.dspec_id=ds.dspec_id";
//        } else if (condition.equals("hostNoRelation"))//获取所有未分组的主机
//        {
//            strSql += "  where host_id not in (select host_id from group2host) order by h.host_id";
//            /*strSql=" select hd.host_id,hd.host_name,hd.host_ipaddr,hd.host_desc,hd.host_username,hd.host_password,hd.host_serialno,ds.dspec_name from(select distinct * from host h " +
//                    "where host_id not in (select host_id from group2host g2h))hd,dspecification ds where hd.dspec_id=ds.dspec_id";*/
//        } else if (condition.equals(""))//获取用户管理的所有主机及未分组的主机
//        {
//           /* SELECT DISTINCT h.host_id,h.host_name from group2host g RIGHT OUTER JOIN `host` h ON g.host_id = h.host_id
//             where (group_id in (select group_id from group2user where user_id = 1) -- 获取分组下的所有主机
//             and  g.host_id not in (select host_id from group2host where group_id = 35))  -- 去掉已经分配过的主机
//             or h.host_id not in(select host_id from group2host)*/
////            if (userId > 0) {
////                strSql = "SELECT DISTINCT g.host_id,h.host_name,h.host_ipaddr,h.host_desc,h.host_username,h.host_password,h.host_serialno from group2host g LEFT JOIN host h ON g.host_id = h.host_id" +
////                        " where group_id in (select group_id from group2user where  g.host_id not in (select host_id from group2host where group_id = " + hostgroupId + ") " + userSql + ")  or h.host_id not in(select host_id from group2host)";
////            } else {
//            strSql += " where host_id not in (select host_id from group2host g2h)";
//                /*strSql="select distinct h.host_id,h.host_name,h.host_ipaddr,h.host_desc,h.host_username,h.host_password,h.host_serialno from host h where h.host_id not in (select host_id from group2host g2h where group_id=\"+hostgroupId+\")";*/
//            //  }
//
//        }
//
//        final String sql = strSql;
//        return (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
//            @Override
//            public Object doInHibernate(Session session) throws HibernateException {
//                return session.createSQLQuery(sql).list();
//            }
//        });
//    }

    /**
     * 根据分组id获取分组下的设备id
     * @return List<Host>
     * @throws Exception
     */
    @Override
    public List<Object[]> getHostListFromSql(final int hostgroupId) throws Exception {

        final String strSql = "select GROUP_CONCAT(g2h.host_id) as host_id_str,GROUP_CONCAT(g2h.group_id) from group2host g2h where g2h.group_id="+hostgroupId;
//        String userSql = "";
//        if (condition.equals("hostRelation"))//获取某一用户管理某一分组下的所有主机
//        {
//            if (userId > 0) {
//                strSql += ",group2user g2u where and g2u.group_id=g2h.group_id and user_id=" + userId+" and";
//            }else{
//                strSql += " where g2h.group_id = "+hostgroupId;
//            }
//        } else if (condition.equals("hostNoRelation"))//获取所有未分组的主机
//        {
//            strSql += "  where host_id not in (select host_id from group2host) order by h.host_id";
//        } else if (condition.equals(""))//获取用户管理的所有主机及未分组的主机
//        {
//            strSql += " where host_id not in (select host_id from group2host g2h)";
//
//        }

        return (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(strSql).list();
            }
        });
    }

    /**
     * 获取分组下的教室列表并分页
     * @param hostgroupId
     * @param condition
     * @param page
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getHostListFromSqlBypage(int hostgroupId, String condition, Page page) throws Exception {
        List<Object[]> result = null;
        if (condition.equals("hostRelation")) {
            List countList = null;
           // String s_sql = "select count(*) from host h,group2host g2h where h.host_id =g2h.host_id and g2h.group_id= " + hostgroupId;
            String s_sql = "select count(*) from group2host g2h where  g2h.group_id= " + hostgroupId;
            final String s_sql_count = s_sql;
            countList = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    // System.out.println(s_sql_count);
                    return session.createSQLQuery(s_sql_count).list();
                }
            });
            //List countList = hibernateTemplate.find("select count(h) from Host h,group2host g2h where h.host_id =g2h.host_id and g2h.group_id="+hostgroupId);
            int totalCount = Integer.parseInt(countList.get(0).toString());
            page.setTotalPageSize(totalCount);
            //DetachedCriteria crit = DetachedCriteria.forClass(Host.class);
            if (totalCount != 0) {
               // String str_sql = "select h.host_id,h.host_name,h.host_ipaddr,h.host_serialno,h.dspec_id,h.host_desc,h.host_username,h.host_password,d.dtype_id from host h,group2host g2h,dspecification d where h.host_id =g2h.host_id and h.dspec_id = d.dspec_id and g2h.group_id=" + hostgroupId + " order by h.host_id" + " limit " + page.getFirstResult() + "," + page.getPageSize();
               String str_sql = "select g2h.host_id,g2h.group_id from group2host g2h where  g2h.group_id= " + hostgroupId;
                final String sql = str_sql;

                result = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                    @Override
                    public Object doInHibernate(Session session) throws HibernateException {
                        return session.createSQLQuery(sql).list();
                    }
                });
            }

        } else if (condition.equals("hostNoRelation")) {
           /* String sql = "select h.host_id,h.host_name,h.host_ipaddr,h.host_serialno,h.dspec_id,h.host_desc,h.host_username,h.host_password from host h where h.host_id not in (select host_id from group2host)";

            final String sqlCount = "select count(*) from (" + sql + ") c";
            BigInteger bigInteger = (BigInteger) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery(sqlCount).uniqueResult();
                }
            });
            page.setTotalPageSize(bigInteger.intValue());
            if (bigInteger.intValue() != 0) {
                final String sqlResult = "select a.*,b.dtype_id from (" + sql + " order by h.host_desc desc,h.host_id desc limit " + page.getFirstResult() + "," + page.getPageSize() + ") a,dspecification b where a.dspec_id = b.dspec_id order by a.host_desc desc,a.host_id desc";
                result = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                    @Override
                    public Object doInHibernate(Session session) throws HibernateException {
                        return session.createSQLQuery(sqlResult).list();
                    }
                });
            }*/
        }
        return result;
    }

    /**
     * 保存主机信息
     *
     * @param host Host类型 需要保存的教室信息
     * @throws Exception
     */
    @Override
    public void saveHost(Host host) throws Exception {
        this.getHibernateTemplate().save(host);
    }

    /**
     * 添加主机
     * @param host
     * @return
     * @throws Exception
     */
    @Override
    public Integer addHost(Host host) throws Exception {
        return (Integer) this.getHibernateTemplate().save(host);
    }

    /**
     * 修改主机信息
     *
     * @param host Host类型 需要修改的教室信息
     * @return boolean 修改是否成功
     * @throws Exception
     */
    @Override
    public boolean updateHost(Host host) throws Exception {
        this.getHibernateTemplate().update(host);
        return true;
    }

    /**
     * 删除未分配分组的主机信息
     *
     * @param id int 主机（教室）id;
     * @throws Exception
     */
    @Override
    public void delHost(int id) throws Exception {
        this.getHibernateTemplate().clear();
        Host host = new Host();
        host.setHostId(id);
        this.getHibernateTemplate().delete(host);
    }

    /**
     * 将该教室所分配的组关系删除
     *
     * @param id int 主机（教室）id;
     * @throws Exception
     */
    @Override
    public void delHostRelationBySql(final int id) throws Exception {
        Object result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                int result = session.createSQLQuery("delete from group2host where host_id =" + id).executeUpdate();
                if (result == 0) throw new HibernateException("no data need delete for host relation!");
                return result;
            }
        });
    }

    /**
     * 测试该ID的教室是否已经被分配
     *
     * @param id int 主机（教室）id;
     * @return boolean 为true则表示已分配，false未分配
     * @throws Exception
     */
    @Override
    public boolean isHostRelationExsist(final int id) throws Exception {

        String result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select count(*) from group2host where host_id=" + id).uniqueResult();
            }
        }).toString();
        //  System.out.println("the number of host_group relation "+result);
        if (Integer.parseInt(result) > 0)
            return true;
        else
            return false;
    }

    /**
     * 将教室分配给某个分组
     *
     * @param hostId  int 教室（主机）id；
     * @param groupId int 分组id
     * @throws Exception
     */
    @Override
    public void addHostToGroup(final int hostId, final int groupId) throws Exception {
        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                session.createSQLQuery("insert into group2host(group_id,host_id) values(" + groupId + "," + hostId + ")").executeUpdate();
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    /**
     * 统计教室数量
     *
     * @param conditions 附属条件
     * @return int
     * @throws Exception
     */
    @Override
    public int count(final String conditions) throws Exception {
        String result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select count(*) from host " + conditions).uniqueResult();
            }
        }).toString();
//        System.out.println("the number of hosts count =" + result);
        logger.debug("the number of hosts count =" + result);
        return Integer.parseInt(result);
    }

    /**
     * 修改主机型号
     * @param hostId
     * @param dspecId
     * @throws Exception
     */
    @Override
    public void updateHostDspec(final int hostId, final int dspecId) throws Exception {
        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                // session.createSQLQuery("insert into group2host(group_id,host_id) values("+groupId+","+hostId+")").executeUpdate();
                int result = session.createSQLQuery("update host set dspec_id =" + dspecId + " where host_id=" + hostId).executeUpdate();
                if (result == 0)
                    throw new HibernateException("error for update the dspec_id=" + dspecId + " of host id=" + hostId);
                return result;
            }
        });
    }

    /**
     * 移动主机到新分组
     * @param hostId
     * @param groupId
     * @param groupIdOld
     * @throws Exception
     */
    @Override
    public void moveHostToNewGroup(final int hostId, final int groupId, final int groupIdOld) throws Exception {
        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                session.createSQLQuery("update group2host set group_id=" + groupId + " where host_id=" + hostId + " and group_id=" + groupIdOld).executeUpdate();
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    /**
     * 根据型号id获取host
     * @param specid
     * @return
     * @throws Exception
     */
    @Override
    public List getHostBySpec(final int specid) throws Exception {
        return (List) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select host_id,host_name,host_ipaddr,host_serialno,dspec_id,host_desc,host_username,host_password from Host where dspec_id=" + specid).list();

            }
        });
    }

    /**
     * Author：Wen
     * Date:2014.11.10
     *
     * @param host
     * @throws Exception 更新Host 信息
     */
    @Override
    public void updateHostInfo(Host host) throws Exception {
//        System.out.print(host.getHostId() + "aaaaa");
        logger.debug(host.getHostId() + "aaaaa");
        this.getHibernateTemplate().update(host);
    }

    /**
     * 按名称获取某host的信息
     *
     * @param name String，
     * @return Host 类型数据
     * @throws Exception
     */
    @Override
    public Host getHost(String name) throws Exception {
        Host host = new Host();
        host.setHostName(name);
        try {
            List<Host> hosts = (List<Host>) this.getHibernateTemplate().findByExample(host);
            if (!hosts.isEmpty()) {
                return hosts.get(0);
            }
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("", e);
            return null;
        }

        return null;
    }

    /**
     * 将该教室host的镜头删除
     *
     * @param id int 教室id;
     * @throws Exception
     */
    @Override
    public void delHostDeviceBySql(final int id) throws Exception {
        Object result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                int result = session.createSQLQuery("delete from device where host_id =" + id).executeUpdate();
                if (result == 0) throw new HibernateException("no data need delete for HostDevice relation!");
                return result;
            }
        });
    }

    /**
     * todo 加注释
     * @param ip
     * @throws Exception
     */
    @Override
    public void updateHostUserPasswordWrongList(final String ip) throws Exception {
        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("update host set host_username='@error@' where host_ipaddr='" + ip + "'").uniqueResult();
            }
        });
    }

    /**
     * 测试该ID的教室是否有镜头
     *
     * @param id int 教室id;
     * @return boolean 为true则表示已分配，false未分配
     * @throws Exception
     */
    @Override
    public boolean isHostDeviceExsist(final int id) throws Exception {

        String result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select count(*) from device where host_id=" + id).uniqueResult();
            }
        }).toString();
        if (Integer.parseInt(result) > 0)
            return true;
        else
            return false;
    }

    /**
     * 为临时录像计划添加设备关联关系
     * Author xinye
     * @param hostId
     * @param tempId
     * @throws Exception
     */
    @Override
    public void addHostToTemporaryplan(final Integer hostId, final Integer tempId) throws Exception {
        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                session.createSQLQuery("insert into temp2host(temp_id,host_id) values(" + tempId + "," + hostId + ")").executeUpdate();
                session.flush();
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    /**
     * 根据临时计划的id查找相关联的Host_id
     * Author xinye
     * @param tempId
     * @return
     * @throws Exception
     */
    @Override
    public List fetchHostInTemp2Host(final Integer tempId) throws Exception {
        return (List) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("SELECT host_id FROM temp2host WHERE temp_id = " + tempId).list();

            }
        });
    }

    /**
     * 根据临时计划的id删除对应的关联关系
     * Author xinye
     * @param tempId
     * @throws Exception
     */
    @Override
    public void delRelationToTemp(final int tempId) throws Exception {
        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                int result = session.createSQLQuery("delete from temp2host where temp_id =" + tempId).executeUpdate();
//                if (result == 0) throw new HibernateException("no data need delete for temp2host relation!");
                return result;
            }
        });
    }

    /**
     * update by zlj on 2018/04/12
     * 通过用户id获取设备id
     * @param userId
     * @return
     */
    public List getHostListByUserId(Integer userId){
        final String sql = "select au.device_id from service.ad_user2device au " +
                "left join service.ad_area2device ad on ad.device_id = au.device_id " +
                "left join service.ad_area aa on aa.area_id =ad.area_id where au.user_id="+userId+"and aa.type_id = 4";
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).list();
    }

    /**
     *  add --zgh 2018/4/16
     */
    public Map<String,String> getHostInfoByIp(final String deviceip){
        List list =(List)this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select * from service.device_host where host_ipaddr ='"+deviceip+"'").setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
            }
        });

        if (list.isEmpty()) {
            return Collections.emptyMap();
        }
        Map result = (Map) list.get(0);
        String hostId = result.get("host_id")+"";
        result.put("host_id",hostId);
        String dspecId = result.get("dspec_id")+"";
        result.put("dspec_id",dspecId);
        String isTour = result.get("isTour")+"";
        result.put("isTour",isTour);

        return result;
    }
}
