package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.dao.SpecDao;
import com.honghe.recordhibernate.entity.Dspecification;
import org.apache.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Moon on 2014/9/11.
 */
@Repository
public class SpecDaoImpl extends BaseDao implements SpecDao {

    private final static Logger logger = Logger.getLogger(SpecDaoImpl.class);

    /**
     * 获取设备
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public Object[] getSpec(final String name) throws Exception {
        return (Object[])this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select dspec_id,record_type from dspecification where connect_param='" + name + "'").uniqueResult();
            }
        });

    }

    /**
     * todo 加注释
     * @param dtype_id
     * @return
     * @throws Exception
     */
    @Override
    public boolean hasDtype(final int dtype_id) throws Exception {
        Object obj = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select count(*) from dspecification where dtype_id=" + dtype_id).uniqueResult();
            }
        });
        BigInteger bigInteger = (BigInteger) obj;
        if (bigInteger.intValue() == 0) {
            return false;
        }
        return true;
    }

    /**
     * 获取设备类型
     * @param specid
     * @return
     */
    @Override
    public int getDiveceType(int specid) {
        int typeid = -1;

        try {
            Dspecification dspecification = new Dspecification();
            dspecification.setDspecId(specid);

            List<Dspecification> list = this.getHibernateTemplate().findByExample(dspecification);
            if (list.size() > 0) {
                typeid = list.get(0).getDtype().getDtypeId();
            }
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("", e);
        }

        return typeid;
    }

    /**
     * 获取设备列表
     * @return
     * @throws Exception
     */
    @Override
    public List getSpecList() throws Exception {

        return this.getHibernateTemplate().find("from Dspecification ");

    }

    /**
     * 按页获取设备列表
     * @param page
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getSpecListByPage(Page page) throws Exception {
        List<Object[]> result = null;
        List countList = null;
        String s_sql = "select count(*) from dspecification";
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
            String str_sql = "select d.dspec_id,d.dspec_name,d.dtype_id,d.connect_param," +
                    "GROUP_CONCAT(conv( oct(s2c.cmd_id) , 8, 10 )) as cmd_id," +
                    "GROUP_CONCAT(c.cmd_name) as cmd_name," +
                    "GROUP_CONCAT(s2c.cmd_word) as cmd_word," +
                    "GROUP_CONCAT(s2c.cmd_param) as cmd_param," +
                    "GROUP_CONCAT(s2c.cmd_return) as cmd_return," +
                    "GROUP_CONCAT(s2c.cmd_flag) as cmd_flag, " +
                    "t.dtype_name " +
                    "from dspecification d " +
                    "LEFT JOIN spec2command s2c on d.dspec_id = s2c.dspec_id " +
                    "LEFT JOIN command c on s2c.cmd_id = c.cmd_id " +
                    "LEFT JOIN dtype t on d.dtype_id = t.dtype_id " +
                    "GROUP BY d.dspec_id " +
                    "limit " + page.getFirstResult() + "," + page.getPageSize();
            final String sql = str_sql;
            result = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery(sql).list();
                }
            });
        }

        return result;
        //return (List<Dspecification>) this.getHibernateTemplate().find("from dspecification limit "+currentPage*pageSize+","+pageSize);
    }

    /**
     * 通过设备名称获取设备信息
     * @param name
     * @return
     */
    @Override
    public List getSpecByName(String name) {
        Dspecification dspecification = new Dspecification();
        dspecification.setDspecName(name);

        List list = null;

        try {
            list = this.getHibernateTemplate().findByExample(dspecification);
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("", e);
        }

        return list.size() == 0 ? null : list;
    }

    /**
     * 保存设备
     * @param dspecification
     * @return
     */
    public boolean save(Dspecification dspecification) {
        try {
            this.getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
            this.getHibernateTemplate().save(dspecification);
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("", e);
            return false;
        }

        return true;
    }

    /**
     * 删除设备
     * @param id
     * @return
     */
    @Override
    @Transactional
    public boolean delete(int id) {
        final Dspecification dspecification = new Dspecification();
        dspecification.setDspecId(id);
        try {

            this.getHibernateTemplate().delete(dspecification);
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("", e);
            return false;
        }
        return true;
    }

    /**
     * 通过id获取设备信息
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Dspecification getSpecById(int id) throws Exception {
        return this.getHibernateTemplate().get(Dspecification.class, id);//修改by hwx
        /*List<Dspecification> list = null;
        Dspecification dspecification = new Dspecification();
        dspecification.setDspecId(id);
        try {
            list = this.getHibernateTemplate().findByExample(dspecification);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return list.size() == 0 ? null : list.get(0);*/
    }

    /**
     * 修改设备信息
     * @param dspecification
     * @return
     * @throws Exception
     */
    @Override
    public boolean updata(Dspecification dspecification) throws Exception {
        try {
            this.getHibernateTemplate().merge(dspecification);
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("", e);
            return false;
        }

        return true;
    }

    /**
     * todo 加注释
     * @param specid
     * @return
     * @throws Exception
     */
    @Override
    public List getSpecRelation(final int specid) throws Exception {
        return (List) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select t2c_id, dspec_id,cmd_id,cmd_word,cmd_param,cmd_return,cmd_flag from Spec2command where dspec_id=" + specid).list();

            }
        });
    }
}
