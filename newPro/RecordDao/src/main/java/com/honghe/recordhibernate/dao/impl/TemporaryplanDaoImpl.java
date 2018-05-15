package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.TemporaryplanDao;
import com.honghe.recordhibernate.entity.Temporaryplan;
import org.apache.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by honghe on 2016/9/5.
 */
@Repository
public class TemporaryplanDaoImpl extends BaseDao implements TemporaryplanDao {
    private final static Logger logger = Logger.getLogger(TemporaryplanDaoImpl.class);

    /**
     * 新建临时计划
     * @param tempPlan
     * @throws Exception
     */
    @Override
    public void addPlan(Temporaryplan tempPlan) throws Exception {
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
        this.getHibernateTemplate().save(tempPlan);
    }

    /**
     * 修改指定临时计划
     * @param tempPlan
     * @throws Exception
     */
    @Override
    public void updatePlan(Temporaryplan tempPlan) throws Exception {
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
        this.getHibernateTemplate().update(tempPlan);
    }

    /**
     * 删除临时计划列
     * @param tempId
     * @throws Exception
     */
    @Override
    public void deletePlan(final Integer tempId) throws Exception {
        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                String sql = "delete from temporaryplan where temporaryplan_id = "+tempId;
                Integer datacount = session.createSQLQuery(sql).executeUpdate();
                return datacount;
            }
        });
    }

    /**
     * 查找临时计划列表
     * @return
     * @throws Exception
     */
    @Override
    public List<Temporaryplan> tempPlanList() throws Exception {

        List<Temporaryplan> list = (List<Temporaryplan>)this.getHibernateTemplate().find("from Temporaryplan");
        return list;
    }

    /**
     * 根据id查找临时计划
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Temporaryplan getTempById(Integer id) throws Exception {
        String sql = "from Temporaryplan where temporaryplanId = "+id;
        List list = this.getHibernateTemplate().find(sql);
        if(list.isEmpty()){
            return null;
        }
        return (Temporaryplan)list.get(0);
    }

    /**
     * 根据与hostId 和 时间条件，查询指定时间之前的临时计划数据
     * @param hostId
     * @param calender 查询条件，Calendar的实例对象
     * @return
     * @throws Exception
     */
    @Override
    public List getTempBeforeTime(final int hostId,Calendar calender) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        final String data = df.format(calender.getTime());
        final String sql = "SELECT plan.* FROM `temporaryplan` AS plan LEFT JOIN `temp2host` AS relation ON plan.`temporaryplan_id` = relation.temp_id" +
                " WHERE relation.`host_id` = "+hostId+" AND plan.`time_end` > '"+data+"'";
        return this.getHibernateTemplate().execute(new HibernateCallback<List>() {
            @Override
            public List doInHibernate(Session session) throws HibernateException {
                session.flush();
                return session.createSQLQuery(sql).list();
            }
        });
    }

    /**
     * 根据临时计划id，查找该计划下的主机名称和分组名称的列表
     * @param tempId 临时计划id
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getGroupNameAndHostNameByTempId(int tempId) throws Exception {
        final String sql = "SELECT th.`host_id`,IF(hg.`group_name` IS NULL,'未分组',hg.`group_name`) FROM temp2host AS th " +
                "LEFT JOIN group2host AS gh ON th.`host_id` = gh.`host_id` LEFT JOIN hostgroup AS hg ON gh.`group_id`=hg.`group_id` " +
                "WHERE th.`temp_id` = "+tempId+" ORDER BY hg.`group_name` ASC";
        return this.getHibernateTemplate().execute(new HibernateCallback<List>() {
            @Override
            public List doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).list();
            }
        });
    }
}
