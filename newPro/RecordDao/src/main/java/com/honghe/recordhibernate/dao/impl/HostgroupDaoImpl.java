package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.entity.Hostgroup;
import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.HostgroupDao;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;


import java.math.BigInteger;
import java.util.List;


/**
 * Created by chby on 2014/9/18.
 */
@Repository
public class HostgroupDaoImpl extends BaseDao implements HostgroupDao {

    private final static Logger logger = Logger.getLogger(HostgroupDaoImpl.class);
    private static final int AREA_TYPE_ID = 51;
    /**
     *
     * @param groupId 分组Id
     * @return
     * @throws Exception
     */
    @Override
    public boolean hasHostgroup(final int groupId) throws Exception {
        BigInteger n = (BigInteger) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select count(*) from group2user where group_id=" + groupId).uniqueResult();
            }
        });
        if (n.intValue() == 0) return false;
        return true;
    }

    /**
     * 获取分组是否绑定时间策略
     * @param groupId 分组id
     * @return
     * @throws Exception
     */
    @Override
    public boolean hasClasstimePloy(final int groupId) throws Exception {
        BigInteger n = (BigInteger) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select count(*) from classtimeploy2group where group_id=" + groupId).uniqueResult();
            }
        });
        if (n.intValue() == 0) return false;
        return true;
    }

    /**
     * 根据分组id获取分组信息
     *
     * @param hgId int 分组id
     * @return Hostgroup 类型数据
     * @throws Exception
     */
    @Override
    public Hostgroup getHostgroupInfo(int hgId) throws Exception {
        Hostgroup hostgroup = this.getHibernateTemplate().get(Hostgroup.class, hgId);
        if (hostgroup != null)
            return hostgroup;
        return null;
    }

    /**
     * 按分页的方式获取分组列表
     *
     * @param page Page<Hostgroup> 自定义page<Hostgroup>类，
     * @throws Exception
     */
    @Override
    public void getHostgroupList(final Page<Hostgroup> page) throws Exception {
        HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
        List countList = hibernateTemplate.find("select count(h) from Hostgroup h");
        int totalCount = Integer.parseInt(countList.get(0).toString());
        page.setTotalPageSize(totalCount);
        if (totalCount != 0) {
            DetachedCriteria crit = DetachedCriteria.forClass(Hostgroup.class);
            List result = hibernateTemplate.findByCriteria(crit, page.getFirstResult(), page.getPageSize());
            page.setResult(result);
        }
    }

    /**
     * 添加分组
     *
     * @param hostgroup
     * @throws Exception
     */
    @Override
    public void saveHostgroup(Hostgroup hostgroup) throws Exception {
        this.getHibernateTemplate().save(hostgroup);
    }

    /**
     * 更新分组信息
     *
     * @param hostgroup
     * @return boolean
     * @throws Exception
     */
    @Override
    public boolean updateHostgroup(Hostgroup hostgroup) throws Exception {
        this.getHibernateTemplate().update(hostgroup);
        return true;
    }

    /**
     * 删除分组信息
     *
     * @param id int 分组id
     * @throws Exception
     */
    @Override
    public void delHostgroup(int id) throws Exception {
        Hostgroup hostgroup = new Hostgroup();
        hostgroup.setGroupId(id);
        this.getHibernateTemplate().delete(hostgroup);
    }

    /**
     * 判断某一分组下是否已经分配了主机（教室）
     *
     * @param id int 分组id
     * @return boolean true 表示已分配，false 表示未分配
     * @throws Exception
     */
    @Override
    public boolean isHostgroupRelationExsist(final int id) throws Exception {
        String result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select count(*) from group2host where group_id=" + id).uniqueResult();
            }
        }).toString();
//        System.out.println("the number of group_host relation " + result);
        logger.debug("the number of group_host relation " + result);
        if (Integer.parseInt(result) > 0)
            return true;
        else
            return false;
    }

    /**
     * 删除某一id分组分配的教室,如果hostIdStr不为空，则只删除其中的教室
     *
     * @param id        int 分组id
     * @param hostIdStr String 教室id集合（1,2,3)
     * @throws Exception
     */
    @Override
    public void delGroupRelationBySql(final int id, final String hostIdStr) throws Exception {
        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                String strSql = "delete from group2host where group_id =" + id;
                if (hostIdStr != null && !hostIdStr.equals(""))
                    strSql += " and host_id in (" + hostIdStr + ") ";
                int result = session.createSQLQuery(strSql).executeUpdate();
                if (result == 0) throw new HibernateException("no data need delete for group relation!");
                return result;
            }
        });
    }

    /**
     * 根据用户id获取分组和主机信息
     * @param userId int 用户id
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getHostgroupList(final int userId) throws Exception {
        if (userId == 0) {
            return (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery("select g.group_id,g.group_name,group_concat(cast(g2h.host_id as char)) from " +
                            "(select group_id,group_name from hostgroup) " +
                            " g,group2host g2h where g.group_id=g2h.group_id " +
                            " group by g.group_id").list();

                }
            });
        } else {
            return (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery("select g.group_id,g.group_name,group_concat(cast(g2h.host_id as char)) from " +
                            "(select group_id,group_name from hostgroup where group_id in (" +
                            "SELECT group_id FROM group2user where user_id=" + userId + ")) " +
                            " g,group2host g2h where g.group_id=g2h.group_id " +
                            " group by g.group_id").list();

                }
            });
        }


    }

    /**
     * 根据用户ID权限获取分组和主机信息，如果user为超级管理员，则取消权限限制
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getHostgroupListNew(final int userId) throws Exception {
        String sql = "select g.group_id,g.group_name,group_concat(cast(g2h.host_id as char))," +
                "group_concat(h.host_name),group_concat(h.host_ipaddr),group_concat(ds.dspec_name),group_concat(cast(ds.dtype_id as char)) from ";
        if (userId > 0) {
            sql += "(select group_id,group_name from hostgroup where group_id in (" +
                    "SELECT group_id FROM group2user where user_id=" + userId + ")) " +
                    " g,group2host g2h,host h,dspecification ds where g.group_id=g2h.group_id and g2h.host_id=h.host_id and ds.dspec_id =h.dspec_id group by g.group_id";
        } else {
            // sql+=" hostgroup g,group2host g2h,host h,dspecification ds where g.group_id=g2h.group_id and g2h.host_id=h.host_id and ds.dspec_id =h.dspec_id group by g.group_id";
            sql = "select n.group_id,n.group_name,m.host_id,m.host_name,m.host_ipaddr,m.dspec_name,m.dtype_id from hostgroup n " +
                    " LEFT JOIN (select g.group_id,g.group_name,group_concat(cast(g2h.host_id as char)) as host_id,group_concat(h.host_name) as host_name, " +
                    " group_concat(h.host_ipaddr) as host_ipaddr,group_concat(ds.dspec_name) as dspec_name,group_concat(cast(ds.dtype_id as char)) as dtype_id from " +
                    " hostgroup g,group2host g2h,host h,dspecification ds where g.group_id=g2h.group_id and g2h.host_id=h.host_id and ds.dspec_id =h.dspec_id group by g.group_id) m on m.group_id = n.group_id order by host_id desc";
        }
        final String sqlStr = sql;
        return (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sqlStr).list();

            }
        });

    }

    /**
     * 统计分组个数
     * @param conditions
     * @return
     * @throws Exception
     */
    @Override
    public int count(final String conditions) throws Exception {
        String result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select count(*) from hostgroup " + conditions).uniqueResult();
            }
        }).toString();
        return Integer.parseInt(result);
    }

    /**
     * 统计分组个数
     * @param conditions
     * @return
     * @throws Exception
     */
    @Override
    public int countHostGroup(final String conditions) throws Exception {
        try {
            String result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery("select count(DISTINCT h.group_id) from hostgroup h ,group2user g2u where h.group_id = g2u.group_id " + conditions).uniqueResult();
                }
            }).toString();
            return Integer.parseInt(result);
        } catch (Exception e) {
            logger.error("",e);
            return 0;
        }
    }

    /**
     * 获取分组列表
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getHostgroupList2(final int userId) throws Exception {
        // return(List<Hostgroup>)this.getHibernateTemplate().find("from Hostgroup h");
       // String sql = "select g.group_id,g.group_name,(select count(*) from group2host a where a.group_id=g.group_id) as hostcount ";
        String sql = "select g.group_id,g.group_name";
        if (userId > 0)//userId有效则返回用户权限下的分组；为0的话则返回所有分组
        {
            sql += " from (select group_id,group_name from hostgroup where group_id in (SELECT group_id FROM group2user where user_id=" + userId + ")) g";
        } else {
            sql += " from hostgroup g ";
        }
        final String tmpSql = sql;
        return (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(tmpSql).list();

            }
        });
    }

    /**
     * 获取所有分组列表，不区分权限
     * @return
     * @throws Exception
     */
    @Override
    public List<Hostgroup> getHostgroupListAll() throws Exception {
        return (List<Hostgroup>) this.getHibernateTemplate().find("from Hostgroup h");
    }

    /**
     * //删除分组与时间策略关系
     * @param groupId
     * @throws Exception
     */
    @Override
    public void delGroupWithHostBySql(final String groupId) throws Exception {
        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                int result = session.createSQLQuery("delete from group2host where group_id=" + groupId).executeUpdate();
                if (result == 0) throw new HibernateException("no data need delete for group2host!");
                return result;
            }
        });
    }

    /**
     * 删除分组与用户的关系
     * @param id
     * @param userId
     * @throws Exception
     */
    @Override
    public void delGroupWithUserBySql(final String id, final String userId) throws Exception {
        String strSql = "delete from group2user where group_id =" + id;
        if (userId != null && !userId.equals("")) {
            strSql += " and user_id=" + userId;
        }
        final String str = strSql;
        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {

                int result = session.createSQLQuery(str).executeUpdate();
                if (result == 0) throw new HibernateException("no data need delete for group2user!");
                return result;
            }
        });
    }

    /**
     * 删除分组与时间策略的关系
     * @param id
     * @param ployId
     * @throws Exception
     */
    @Override
    public void delGroupWithPloyBySql(final String id, final String ployId) throws Exception {
        String strSql = "delete from classtimeploy2group where group_id =" + id;
        if (ployId != null && !ployId.equals("")) {
            strSql += " and ploy_id=" + ployId;
        }
        final String str = strSql;
        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                int result = session.createSQLQuery(str).executeUpdate();
                if (result == 0) throw new HibernateException("no data need delete for group2user!");
                return result;
            }
        });
    }

    /**
     * 增加分组与用户的关系
     * @param id
     * @param userId
     * @throws Exception
     */
    @Override
    public void addGroupWithUserBySql(final String id, final String userId) throws Exception {
        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                session.createSQLQuery("insert into group2user(group_id,user_id) values(" + id + "," + userId + ")").executeUpdate();
                return null;
            }
        });
    }

    /**
     * Author: Wen
     * Date:2014.10.30
     * 根据page类 获得分页的分组数据
     *
     * @param userId
     * @param page
     */


    @Override
    public void getHostGroupListByPage(int userId, Page page) {
        HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
        List<Object[]> result = null;
        List countList = null;
        String s_sql = "select count(DISTINCT hg.group_id) from hostgroup hg";
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
            // String sql ="select g.group_id,g.group_name,(select count(*) from group2host a where a.group_id=g.group_id) as hostcount ";
            String str_sql = "select DISTINCT hg.group_id,hg.group_name,(select count(*) from group2host a where a.group_id=hg.group_id) as hostcount";
            if (userId > 0) {
                str_sql += " from (select group_id,group_name from hostgroup where group_id in (SELECT group_id FROM group2user where user_id=" + userId + ")) hg " + " limit " + page.getFirstResult() + " , " + page.getPageSize();
            } else {
                str_sql += " from hostgroup hg  limit " + page.getFirstResult() + " , " + page.getPageSize();
            }
            final String sql = str_sql;
            result = this.getHibernateTemplate().execute(new HibernateCallback<List<Object[]>>() {
                @Override
                public List<Object[]> doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery(sql).list();
                }
            });
        }
      /*  List newList=new ArrayList();
        for(int i=0;i<result.size();i++){
            Object[] object=result.get(i);
            if(object.length>0){
                Hostgroup hg=new Hostgroup();
                hg.setGroupId((Integer)object[0]);
                hg.setGroupName(object[1].toString());
                newList.add(hg);
            }
        }*/
        page.setResult(result);
    }

    /**
     * Author:Wen
     * Date:2014.11.5
     *
     * @param hostGroupId
     * @return
     * @throws Exception 根据分组的ID 查询可以管理该分组的user 信息
     */
    @Override
    public List<Object[]> getUserForHostGroup(String hostGroupId) throws Exception {
        List<Object[]> info;
        final String sql = "select u.user_id,u.user_name,u.user_pwd,hg.group_id,hg.group_name from user u LEFT JOIN group2user g2u on g2u.user_id=u.user_id LEFT JOIN hostgroup hg on hg.group_id=g2u.group_id where g2u.group_id=" + hostGroupId;
        info = this.getHibernateTemplate().execute(new HibernateCallback<List<Object[]>>() {
            @Override
            public List<Object[]> doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).list();
            }
        });
        return info;
    }

    /**
     * Author:Wen
     * Date:2014.11.6
     *
     * @param hostId
     * @return
     * @throws Exception 根据未分组班级的hostId  查询host表及dspecification表信息
     */
    @Override
    public List<Object[]> getHostAndDspecfication(String hostId) throws Exception {
        List<Object[]> info;
        final String sql = "select h.host_id,h.host_name,h.host_ipaddr,ds.dspec_name from `host` h,dspecification ds where h.dspec_id=ds.dspec_id AND h.host_id=" + hostId;
        info = this.getHibernateTemplate().execute(new HibernateCallback<List<Object[]>>() {
            @Override
            public List<Object[]> doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).list();
            }
        });
        return info;
    }

    /**
     * 获取某分组的信息
     *
     * @param name String，
     * @return Hostgroup 类型数据
     * @throws Exception
     */
    @Override
    public Hostgroup getHostGroup(String name) throws Exception {
        Hostgroup hostgroup = new Hostgroup();
        hostgroup.setGroupName(name);
        try {
            List<Hostgroup> hostgroups = (List<Hostgroup>) this.getHibernateTemplate().findByExample(hostgroup);
            if (!hostgroups.isEmpty()) {
                return hostgroups.get(0);
            }
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("", e);
            return null;
        }

        return null;
    }

    /**
     * 根据设备名称获取设备型号id
     * @param dtypeName
     * @return
     * @throws Exception
     */
    public List<Object[]> getDspecIds(String dtypeName) throws Exception{
        List<Object[]> result = null;
        String str_sql = "select ds.dspec_id,ds.dspec_name from dspecification ds,dtype d where ds.dtype_id = d.dtype_id and d.dtype_name = '"+dtypeName+"'";
        final String sql = str_sql;

        result = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).list();
            }
        });
        return result;
    }

    /**
     * 获取HostId
     * @param groupId
     * @return
     * @throws Exception
     */
    public List<Object[]> getHostIds(int groupId) throws Exception{
        List<Object[]> result = null;
        String str_sql = "select group_concat(host_id), group_id from group2host where group_id="+groupId;
        final String sql = str_sql;

        result = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).list();
            }
        });
        return result;
    }

//    /**
//     * 根据hostid获取所在分组信息
//     * @param hostId
//     * @return
//     * @throws Exception
//     */
//    public List<Object[]> getGroupInfoByhostId(int hostId) throws Exception{
//        List<Object[]> result = null;
//        final String sql = "select g.group_id,g.group_name from hostgroup g,group2host g2h where g.group_id=g2h.group_id and g2h.host_id="+hostId;
//        result = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
//            @Override
//            public Object doInHibernate(Session session) throws HibernateException {
//                return session.createSQLQuery(sql).list();
//            }
//        });
//        return result;
//    }

    /**
     * add by lichong
     * 原本读取自己本地的数据库，改为读取service的数据库获取信息
     * 根据hostid获取所在分组信息
     * @param hostId
     * @return
     * @throws Exception
     */
    public List<Object[]> getAreaByDeviceHostId(int hostId) throws Exception{
        List<Object[]> result = null;
        final String sql = "select " +
                            "sa.id as group_id," + "\n" +
                            "sa.name as group_name " + "\n" +
                            "from service.ad_area sa " + "\n" +
                           "left join service.ad_area2device sa2d on sa.id = sa2d.area_id " +
                           "where sa2d.device_id="+hostId;
        result = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).list();
            }
        });
        return result;
    }
    /**
     * add by lichong
     * 获取平台数据库中存有的所有分组和策略的对应关系
     * @return
     * @throws Exception
     */
    public List<Object[]> getGroupPloyRel() throws Exception{
        List<Object[]> result = null;
        final String sql = "select " +
                            "gp.group_id," + "\n" +
                            "gp.ploy_id " + "\n" +
                           "from classtimeploy2group gp ";
        result = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).list();
            }
        });
        return result;
    }

    /**
     * add by lichong
     * 获取地点管理里面的所有地点列表
     * @return
     * @throws Exception
     */
    public List<Object[]> getAllArea() throws Exception{
        List<Object[]> result = null;
        final String sql = "select " +
                "sa.id, " + "\n" +
                "sa.p_id, " + "\n" +
                "sa.name " + "\n" +
                "from service.ad_area sa ";
        result = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).list();
            }
        });
        return result;
    }

    /**
     * add by lichong
     * 根据hostid获取所在地点信息
     * @param hostId
     * @return
     * @throws Exception
     */
    public List<Object[]> getAreaByHostId(int hostId) throws Exception{
        List<Object[]> result = null;
        final String sql = "select " +
                "sa.id as area_id," + "\n" +
                "sa.name as area_name " + "\n" +
                "from service.ad_area sa " + "\n" +
                "left join service.ad_area2device sa2d on sa.id = sa2d.area_id " +
                "where sa2d.device_id="+hostId;
        result = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).list();
            }
        });
        return result;
    }

    /**
     * add by lichong
     * 根据hostid获取所在分组信息
     * @param hostId
     * @return
     * @throws Exception
     */
    public List<Object[]> getGroupInfoByhostId(int hostId) throws Exception{
        List<Object[]> result = null;
        final String sql = "select " +
                "sa.id as group_id," + "\n" +
                "sa.name as group_name " + "\n" +
                "from service.ad_area sa " + "\n" +
                "left join service.ad_area2device sa2d on sa.id = sa2d.area_id " +
                "where sa2d.device_id="+hostId;
        result = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).list();
            }
        });
        return result;
    }

    /**
     * 录播根据hostid获取作息策略
     * @param hostId
     * @return
     * @throws Exception
     */
    public List<Object[]> getPloyNameById(int hostId) throws Exception {
        List<Object[]> result = null;
        final String sql = "select cp.ploy_name, c2g.group_id from classtime_ploy cp,classtimeploy2group c2g where cp.ploy_id = c2g.ploy_id and c2g.group_id="+hostId;
        result = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).list();
            }
        });
        return result;
    }

   /* @Override
    public String getHostGroupName(Integer timePloyId) throws Exception {
        String sql = "SELECT group_name" +
                " FROM" +
                " (SELECT group_id" +
                " from classtime_ploy cp" +
                " JOIN classtimeploy2group c2g" +
                " on cp.ploy_id=c2g.ploy_id" +
                " where cp.ploy_id=:ploy_id)t" +
                " JOIN hostgroup hg" +
                " WHERE t.group_id=hg.group_id";
        return (String) this.getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createSQLQuery(sql).setParameter("ploy_id", timePloyId).uniqueResult();
    }*/
}
