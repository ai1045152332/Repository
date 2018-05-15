package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.DeviceLogDao;
import com.honghe.recordhibernate.dao.OnlineTimeDao;
import com.honghe.recordhibernate.entity.OnlineTime;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;
import com.honghe.recordhibernate.dao.BaseDao;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by qinzhihui on 2015/12/7.
 */
@Repository
public class OnlineTimeDaoImpl extends BaseDao implements OnlineTimeDao{
    /**
     * 储存在线信息
     * @param onlineTime
     * @throws Exception
     */
    @Override
    public void save(OnlineTime onlineTime) throws Exception {
        this.getHibernateTemplate().save(onlineTime);
    }

    /**
     * 修改信息 主要功能为记录关机时间
     * @param onlineTime
     * @throws Exception
     */
    @Override
    public void update(OnlineTime onlineTime) throws Exception {
        this.getHibernateTemplate().update(onlineTime);
    }

    /**
     * 查到本次开机时间 此方法用于记录关机时间
     * @param mac
     * @return
     * @throws Exception
     */
    @Override
    public OnlineTime findLastOpen(String mac) throws Exception {
        List list = this.getHibernateTemplate().find("from OnlineTime where mac='"+mac+"' order by id desc");
        return (OnlineTime)list.get(0);
    }

    /**
     * 统计查询时间时用的方法
     * @param mac
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    @Override
    public List findByMac(String mac, long startTime ,long endTime) throws Exception {
        String hql = "from OnlineTime where mac='"+mac+"' and opentime>"+ startTime +" and opentime<"+endTime;
        List list = this.getHibernateTemplate().find(hql);
        return list;
    }

    /**
     * 通过设备类型查询设备信息
     * @param mac
     * @param type
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    @Override
    public List findByType(String mac,String type,long startTime, long endTime) throws Exception{
        String hql = "from OnlineTime where  mac='"+mac+"' and type='"+type+"' and opentime>"+ startTime +" and opentime<"+endTime;
        List list = this.getHibernateTemplate().find(hql);
        return list;
    }

    /**
     * 删除过早的数据
     * @param time
     * @param type
     * @throws Exception
     */
    @Override
    public void deleteByTime(long time,String type) throws Exception {
        /*Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();//获取session

        Transaction tx = session.beginTransaction(); //打开事务（针对读数据库）

        String hql="delete from OnlineTime o where opentime<? and o.type ='"+type+"'";//准备hql

        session.createQuery(hql).setParameter(0,time);  //更新

        tx.commit();//提交事务

        session.close(); //关闭session*/
        final String delSql = "delete from OnlineTime where opentime <" + time + " and type='" + type + "'";
        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                int result = session.createSQLQuery(delSql).executeUpdate();
                if (result == 0) throw new HibernateException("no data need delete for group relation!");
                return result;
            }
        });
    }

    /**
     * 用于查看某天是否开机过
     * @param mac
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    @Override
    public OnlineTime findWhetherOpen(String mac,long startTime, long endTime) throws Exception {
        String hql = "from OnlineTime where mac='"+mac+"' and opentime>"+ startTime +" and opentime<"+endTime;
        List list = this.getHibernateTemplate().find(hql);
        return list.size()>0?(OnlineTime)list.get(0):null;
    }

    /**
     * 查看某天相应设备类型的设备是否开机过
     * @param type
     * @param beginTime
     * @param endTime
     * @return
     * @throws Exception
     */
    @Override
    public OnlineTime findWhetherOpenByType(String type,long beginTime,long endTime) throws Exception{
        String hql = "from OnlineTime where type='"+type+"' and opentime>"+ beginTime +" and opentime<"+endTime;
        List list = this.getHibernateTemplate().find(hql);
        return list.size()>0?(OnlineTime)list.get(0):null;
    }

    /**
     * 获得一段时间内所有设备的总时长
     * @param startTime
     * @param endTime
     * @param type
     * @return
     * @throws Exception
     */
    @Override
    public List findTotalTime(long startTime, long endTime,String type) throws Exception {
        String hql = "from OnlineTime where opentime>'"+ startTime +"' and opentime<'"+endTime+"' and type='"+type+"'";
        List list = this.getHibernateTemplate().find(hql);
        return list;
    }
    /**
     * 2018/3/31 -zgh
     * 录制次数(前十)
     */
    public List<Object[]> findCountRecordByGroup(int startTime,int endTime,String type)throws Exception {
        List<Object[]> result = null;
        List noHostList = null;
        String str_sql = "SELECT hg.group_name,COUNT(*) AS counts FROM hostgroup hg, onlinetime ot, group2host g2h WHERE g2h.host_id = ot.ip AND g2h.group_id = hg.group_id AND ot.type ='"+type+"' AND ot.opentime BETWEEN "+startTime+" AND "+endTime+" GROUP BY group_name ORDER BY counts DESC LIMIT 0,10";
        final String noHost = "SELECT hg.`group_name` FROM hostgroup hg LEFT JOIN group2host g2h ON hg.`group_id` = g2h.`group_id`  WHERE g2h.`host_id` IS NULL";
        noHostList = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(noHost).list();
            }
        });

        final String sql = str_sql;
        result = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(sql).list();
            }
        });
        if ( !(result.size() < 10)) {
            return result;
        }
        if(noHostList == null && noHostList.size() == 0) {
            return result;
        }
        for (int j = 0; j < noHostList.size(); j++) {
            Object[] obj = new Object[2];
            obj[0] = noHostList.get(j);
            obj[1] = 0;
            result.add(obj);
            if(result.size() == 10){
                break;
            }
        }
        return result;
    }
}
