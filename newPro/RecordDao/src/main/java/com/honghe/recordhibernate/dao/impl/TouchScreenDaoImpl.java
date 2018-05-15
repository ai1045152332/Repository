package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.dao.TouchscreenDao;
import com.honghe.recordhibernate.entity.Touchscreen;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by sky on 2016/5/19.
 */
@Repository
public class TouchScreenDaoImpl extends BaseDao implements TouchscreenDao{
    /**
     * 按分页的方式获取用户有关定时计划列表及host关系
     *
     * @param page Page 自定义page类，
     * @throws Exception
     */
    @Override
    public List<Object[]> getTouchScreenListByUser(Page page,int uid,String type)throws Exception{
        HibernateTemplate hibernateTemplate=this.getHibernateTemplate();
        List<Object[]> result=null;
        List countlist=null;
        String s_sql="";
        if (0==uid){
            s_sql="select count(t.t_id) from touchscreen t where t.t_devicetype = '"+type+"'";
        }else{
            s_sql="select count(distinct t.t_id) from touchscreen t "
                    +" inner join touchscreen2host ts2 on t.t_id=ts2.t_id "
                    +" where ts2.host_id in (select ts2.host_id from group2host g2h,group2user g2u "
                    +" where g2h.group_id=g2u.group_id and g2u.user_id= "+uid+" ) and t.t_devicetype = '"+type+"'";
        }
        final String s_sql_count=s_sql;
        countlist=(List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(s_sql_count).list();
            }
        });
        int count=Integer.parseInt(countlist.get(0).toString());
        page.setTotalPageSize(count);

        if (count!=0){
                String ssql="select distinct t.t_id,t.t_type,t.t_loop,t.t_looptype,t.t_date,t.t_week,"
                        +"t.t_time,t.t_singletime,t.t_uid,t.t_createtime,group_concat(distinct conv( oct(t2h.host_id ) , 8, 10 ))"
                        +" as hostids,group_concat(distinct t2h.host_name) as hosts,t.t_uname from touchscreen t";
            if(uid==0){
                ssql+=" left join touchscreen2host t2h on t.t_id=t2h.t_id where t.t_devicetype = '"+type+"' group by t.t_id order by t.t_id desc,"
                        +"t2h.host_id asc "+" limit "+page.getFirstResult()+","+page.getPageSize();

            }else{
                ssql+=" inner join touchscreen2host t2h on t.t_id = t2h.t_id where t.t_uid = "
                        +uid+ " and t2h.host_id in "
                        +"( "
                        +" select t2h.host_id from group2host g2h,group2user g2u "
                        + "  where g2h.group_id = g2u.group_id and g2u.user_id = " + uid + ") and t.t_devicetype = '"+type+"'"
                        +" group by t.t_id "
                        + " order by t.t_id desc ,t2h.host_id asc " + "limit " + page.getFirstResult() + "," + page.getPageSize();
            }



            final String sql = ssql;
            result = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery(sql).list();
                }
            });
        }

        return  result;
    }


    /**
     * 获取所有定时计划列表
     *
     * @throws Exception
     */
    @Override
    public List<Touchscreen> getTouchScreenlist()throws Exception{
        try{
            return (List<Touchscreen>)this.getHibernateTemplate().find("from Touchscreen");
        }catch (Exception e){
            return null;
        }
    }
    /**
     * 获取某定时计划的host列表
     *
     * @param touchscreen Touchscreen，
     * @return HashMap<String,Integer> 类型数据
     * @throws Exception
     */
    @Override
    public HashMap<String, Integer> getTouchScreenHosts(Touchscreen touchscreen)throws Exception{
        try {
            final int id=touchscreen.getTid();
            List<Object[]> hostlist=(List<Object[]>)this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                public Object doInHibernate(Session session) throws HibernateException {
                    List list = session.createSQLQuery(
                            "SELECT DISTINCT t2h.host_id,t2h.host_name " +
                                    "FROM  touchscreen t "+
                                    "INNER JOIN touchscreen2host t2h on t.t_id = t2h.t_id " +
                                    "WHERE t.t_id = " + id

                    ).list();
                    return list;
                }
            });
            HashMap<String, Integer> touchscreenHosts=new HashMap<String, Integer>(0);
            if (hostlist!=null&&!hostlist.isEmpty()){
                for (int i=0;i<hostlist.size();i++){
                    int hostId=Integer.parseInt(hostlist.get(i)[0].toString());
                    touchscreenHosts.put(""+hostId,new Integer(hostId));
                }
            }
            return touchscreenHosts;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 保存定时计划
     * @param touchscreen Touchscreen 需要保存的定时计划信息
     * @throws Exception
     */
    @Override
    public boolean save(Touchscreen touchscreen)throws Exception{
        try
        {
            this.getHibernateTemplate().save(touchscreen);
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
     *  给某个班级发布定时计划
     * @param touchId int 定时计划id；
     * @param hostId int 班级id
     * @throws Exception
     */
    @Override
    public void addTouchToHost(final int touchId,final int hostId,final String hostName)throws Exception {
        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                session.createSQLQuery("insert into touchscreen2host(t_id,host_id,host_name) values(" + touchId + "," + hostId + ",'" + hostName + "')").executeUpdate();
                return null;
            }
        });
    }
    /**
     * 获取某定时计划的信息
     *
     * @param id int，
     * @return Touchscreen 类型数据
     * @throws Exception
     */
    @Override
    public Touchscreen getTouchListById(int id)throws Exception{
        try {
            List<Touchscreen> touchscreens =(List<Touchscreen>) this.getHibernateTemplate().find("from Touchscreen t where t.tid ="+id);
            if(!touchscreens.isEmpty())
            {
                return touchscreens.get(0);
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return null;
    }
    /**
     *更新某定时计划的信息
     * @param touchscreen 类型数据
     * @throws Exception
     */
    @Override
    public boolean updateTouch(Touchscreen touchscreen)throws Exception{
        try {
            this.getHibernateTemplate().update(touchscreen);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 测试该ID的定时计划是否已经发布给班级
     * @param id int 定时计划id;
     * @return boolean 为true则表示已分配，false未分配
     * @throws Exception
     */
    @Override
    public boolean isTouchHostExists(final int id)throws Exception{
        try {
            String result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery("select count(*) from touchscreen2host where t_id="+id).uniqueResult();
                }
            }).toString();
            if (Integer.parseInt(result)>0){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 将该定时计划所分配的班级关系删除
     * @param id int 定时计划id;
     * @throws Exception
     */
    @Override
    public void delTouchHostByTouch(final int id)throws Exception{
        Object result =   this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                int result =  session.createSQLQuery("delete from touchscreen2host where t_id ="+id).executeUpdate();
                return result;
            }
        });
    }
    /**
     * 删除定时计划
     * @param touchscreen
     * @throws Exception
     */
    @Override
    public boolean delTouchscreen(Touchscreen touchscreen)throws Exception{
        try {
            this.getHibernateTemplate().delete(touchscreen);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 测试该班级是否有定时计划
     * @param id int 班级id;
     * @return boolean 为true则表示已分配，false未分配
     * @throws Exception
     */
    @Override
    public boolean isTouchHostExistsByHost(final int id) throws Exception{
        String result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select count(*) from touchscreen2host where host_id=" + id).uniqueResult();
            }
        }).toString();
        if(Integer.parseInt(result)>0)
            return true;
        else
            return false;
    }

    /**
     * 将该班级所分配的定时计划关系删除
     * @param id int 班级id;
     * @throws Exception
     */
    @Override
    public void delTouchHostByHost(final int id) throws Exception{
        Object result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                int result =  session.createSQLQuery("delete from touchscreen2host where host_id ="+id).executeUpdate();
                return result;
            }
        });
    }
}
