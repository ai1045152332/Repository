package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.NasDao;
import com.honghe.recordhibernate.entity.Nas;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhanghongbin on 2014/12/8.
 */
@Repository
public class NasDaoImpl extends BaseDao implements NasDao {

    private final static Logger logger = Logger.getLogger(NasDaoImpl.class);

    /**
     * todo 加注释
     * @param hostId
     * @param nasId
     * @throws Exception
     */
    @Override
    public void saveNas2Host(final String[] hostId, final Integer nasId) throws Exception {
        for (final String _hostId : hostId) {
            this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    session.createSQLQuery("insert into nas2host(host_id,nas_id) values(" + _hostId + "," + nasId + ")").executeUpdate();
                    return null;
                }
            });
        }
    }


    /**
     * todo 加注释
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getNas2HostList() throws Exception {
        return this.getHibernateTemplate().execute(new HibernateCallback<List<Object[]>>() {
            @Override
            public List<Object[]> doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select n.host_id,n.nas_id from nas2host n").list();
            }
        });
    }

    /**
     * todo 加注释
     * @param hostId
     * @throws Exception
     */
    @Override
    public void deleteFromHost(final String hostId) throws Exception {
        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                int n = session.createSQLQuery("delete from nas2host where host_id=" + hostId).executeUpdate();
                if (n == 0) throw new HibernateException("delete from nas2host where host_id=" + hostId + "failure");
                return null;
            }
        });
    }

    /**
     * todo 加注释
     * @param nasId
     * @throws Exception
     */
    @Override
    public void deleteFromNas(final String nasId) throws Exception {
        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                int n = session.createSQLQuery("delete from nas2host where nas_id=" + nasId).executeUpdate();
                if (n == 0) throw new HibernateException("delete from nas2host where Nas_id=" + nasId + "failure");
                return null;
            }
        });
    }

    /**
     * todo 加注释
     * @param nasId
     * @throws Exception
     */
    @Override
    public void deleteNas(final String nasId) throws Exception {
        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                int n = session.createSQLQuery("delete from setting_nas where nas_id=" + nasId).executeUpdate();
                if (n == 0) throw new HibernateException("delete from setting_nas where Nas_id=" + nasId + "failure");
                return null;
            }
        });
    }

    /**
     * todo 加注释
     * @return
     * @throws Exception
     */
    @Override
    //获取分组列表,
    public List<Object[]> getNasList() throws Exception {
        final String sql = "select n.nas_id,n.nas_rootpath,n.nas_username,n.nas_password,(select count(*) from nas2host n2h where n2h.nas_id=n.nas_id) as hostcount from setting_nas n";
        return (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).list();
            }
        });
    }

    /**
     * todo 加注释
     * @param hostgroupId
     * @param condition
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getHostAndNasListFromSql(int hostgroupId, String condition, int userId) throws Exception {
        String strSql = "select distinct h.host_id,h.host_name,h.host_ipaddr,h.host_desc,h.host_username,h.host_password,h.host_serialno from host h";
        //String strSql="select DISTINCT hh.host_id,hh.host_name,hh.host_ipaddr,hh.host_desc,hh.host_username,hh.host_password,hh.host_serialno,ds.dspec_name,hh.dspec_id from (select distinct h.host_id,h.host_name,h.host_ipaddr,h.host_desc,h.host_username,h.host_password,h.host_serialno,h.dspec_id from host h, group2host g2h,group2user g2u where h.host_id =g2h.host_id ";
        String userSql = "";

        if (condition.equals("hostRelation"))//获取某一用户管理某一分组下的所有主机
        {
            if (userId > 0) {
                userSql += " and g2u.group_id=g2h.group_id and user_id=" + userId;
                //userSql += " and g2u.group_id=g2h.group_id and user_id="+userId;
            }
            strSql += ", group2host g2h,group2user g2u where h.host_id =g2h.host_id  " + userSql + " and g2h.group_id=" + hostgroupId;
            // strSql+=userSql+"and g2h.group_id=" +hostgroupId+" ) hh,dspecification ds WHERE hh.dspec_id=ds.dspec_id";
        } else if (condition.equals("hostNoRelation"))//获取所有未分组的主机
        {
            strSql += "  where host_id not in (select host_id from group2host)";
            /*strSql=" select hd.host_id,hd.host_name,hd.host_ipaddr,hd.host_desc,hd.host_username,hd.host_password,hd.host_serialno,ds.dspec_name from(select distinct * from host h " +
                    "where host_id not in (select host_id from group2host g2h))hd,dspecification ds where hd.dspec_id=ds.dspec_id";*/
        } else if (condition.equals(""))//获取用户管理的所有主机及未分组的主机
        {
           /* SELECT DISTINCT h.host_id,h.host_name from group2host g RIGHT OUTER JOIN `host` h ON g.host_id = h.host_id
             where (group_id in (select group_id from group2user where user_id = 1) -- 获取分组下的所有主机
             and  g.host_id not in (select host_id from group2host where group_id = 35))  -- 去掉已经分配过的主机
             or h.host_id not in(select host_id from group2host)*/
            if (userId > 0) {
                strSql = "SELECT DISTINCT g.host_id,h.host_name,h.host_ipaddr,h.host_desc,h.host_username,h.host_password,h.host_serialno from group2host g LEFT JOIN `host` h ON g.host_id = h.host_id" +
                        " where group_id in (select group_id from group2user where  g.host_id not in (select host_id from group2host where group_id = " + hostgroupId + ") " + userSql + ")  or h.host_id not in(select host_id from group2host)";
            } else {
                strSql += " where host_id not in (select host_id from group2host g2h where group_id=" + hostgroupId + ")";
                /*strSql="select distinct h.host_id,h.host_name,h.host_ipaddr,h.host_desc,h.host_username,h.host_password,h.host_serialno from host h where h.host_id not in (select host_id from group2host g2h where group_id=\"+hostgroupId+\")";*/
            }

        }

        final String sql = "select a.host_id,a.host_name,a.host_ipaddr,b.nas_id,b.nas_rootpath,b.nas_filepath,b.nas_username,b.nas_password from (" + strSql + ") a left join nas b on a.host_id = b.host_id";
        return (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).list();
            }
        });
    }

    /**
     *
     * todo 加注释
     * @param hostId
     * @return
     * @throws Exception
     */
    @Override
    public Nas findNas(String hostId) throws Exception {
        List<Nas> nasList = (List<Nas>) this.getHibernateTemplate().find("from Nas nas where nas.hostId = " + hostId);
        if (nasList.isEmpty()) return null;
        return nasList.get(0);
    }

    /**
     * todo 加注释
     * @param nas
     * @throws Exception
     */
    @Override
    public void updateNas(Nas nas) throws Exception {
        this.getHibernateTemplate().update(nas);
    }

    /**
     * todo 加注释
     * @param nas
     * @return
     * @throws Exception
     */
    @Override
    public Integer saveNas(Nas nas) throws Exception {
        try {
            this.getHibernateTemplate().save(nas);
            return nas.getNasId();
        } catch (Exception e) {
            logger.error("保存失败", e);
            return -1;
        }
    }

    /**
     * todo 加注释
     * @param nasPath
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getNasByPath(String nasPath) throws Exception {
        String str_sql = "select nas_id,nas_rootpath,nas_username,nas_password " +
                "from setting_nas " +
                "where nas_rootpath = '"+nasPath+"' " ;
        final String sql  = str_sql;
        List<Object[]> result =(List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).list();
            }
        });
        return result;
    }
}
