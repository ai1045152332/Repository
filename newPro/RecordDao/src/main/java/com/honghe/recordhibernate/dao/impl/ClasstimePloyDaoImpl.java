package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.ClasstimePloyDao;
import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.entity.ClasstimePloy;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzz on 2016/7/18.
 */
@Repository
public class ClasstimePloyDaoImpl extends BaseDao implements ClasstimePloyDao {

    private static final int AREA_TYPE_ID = 51;

    /**
     * 获取时间策略列表
     *
     * @param ，
     * @throws Exception
     */
    @Override
    public List<ClasstimePloy> getClasstimePloyList() throws Exception
    {
        List list = null;
        list = (List<ClasstimePloy>)this.getHibernateTemplate().find("from ClasstimePloy");
        return list;
    }
    /**
     * 新增某时间时间策略
     *
     * @param classtimePloy ClasstimePloy 时间策略实体，
     * @throws Exception
     */
    @Override
    public boolean save(ClasstimePloy classtimePloy) throws Exception
    {
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
        this.getHibernateTemplate().save(classtimePloy);
        return true;
    }
    /**
     * 更新某时间时间策略
     *
     * @param classtimePloy ClasstimePloy 时间策略实体，
     * @throws Exception
     */
    @Override
    public boolean update(ClasstimePloy classtimePloy) throws Exception
    {
        this.getHibernateTemplate().update(classtimePloy);
        return true;
    }
    /**
     * 删除某时间策略
     *
     * @param id int 时间策略id，
     * @throws Exception
     */
    @Override
    public boolean delete(int id) throws Exception
    {
        final ClasstimePloy classtimePloy = new ClasstimePloy();
        classtimePloy.setPloyId(id);
        this.getHibernateTemplate().delete(classtimePloy);
        return true;
    }
    /**
     * 根据id获取时间策略
     *
     * @param id int 时间策略id，
     * @throws Exception
     */
    @Override
    public ClasstimePloy getInfo(int id) throws Exception
    {
        return this.getHibernateTemplate().get(ClasstimePloy.class,id);
    }
    /**
     * 按分页的方式获取时间策略列表
     *
     * @param page Page 自定义page类，
     * @throws Exception
     */
    @Override
    public void getClasstimePloyListByPage(Page page) throws Exception
    {
        List<Object[]> result = new ArrayList<>();
        String s_sql = "select count(ploy_id) from classtime_ploy ";
        final String s_sql_count = s_sql;
        List countList = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(s_sql_count).list();
            }
        });
        int totalCount = Integer.parseInt(countList.get(0).toString());
        page.setTotalPageSize(totalCount);
        if (totalCount != 0)
        {
            String str_sql = "select p.ploy_id,p.ploy_name,GROUP_CONCAT( DISTINCT conv( oct(g.group_id ) , 8, 10 )) as groupids," +
                    "GROUP_CONCAT(DISTINCT g.group_name) as groups " +
                    "from classtime_ploy p " +
                    "LEFT JOIN classtimeploy2group p2g on p.ploy_id = p2g.ploy_id " +
                    "LEFT JOIN hostgroup g on p2g.group_id = g.group_id " +
                    "GROUP BY p.ploy_id " +
                    "order by p.ploy_id ASC ,g.group_id ASC  " +
                    "limit "+page.getFirstResult()+","+page.getPageSize();
            final String sql  = str_sql;
            result =(List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery(sql).list();
                }
            });
        }
//        List newList=new ArrayList();
//        for(int i=0;i<result.size();i++){
//            Object[] objects = result.get(i);
//            if(objects.length>0){
//                ClasstimePloy classtimePloy = new ClasstimePloy();
//                classtimePloy.setPloyId((Integer)objects[0]);
//                classtimePloy.setPloyName(objects[1].toString());
//                Set<Hostgroup> hostgroups = new HashSet<>();
//                if(objects[2]!=null && !objects[2].equals("")) {
//                    String[] groupIds = objects[2].toString().split(",");
//                    String[] groupNames = objects[3].toString().split(",");
//                    for (int j = 0; j < groupIds.length; j++) {
//                        Hostgroup hostgroup = new Hostgroup();
//                        hostgroup.setGroupId(Integer.parseInt(groupIds[j]));
//                        hostgroup.setGroupName(groupNames[j]);
//                        hostgroups.add(hostgroup);
//                    }
//                }
//                classtimePloy.setHostgroups(hostgroups);
//                newList.add(classtimePloy);
//            }
//        }
        page.setResult(result);
    }
    /**
     * 获取某时间策略详细信息
     *
     * @param id int 时间策略id;
     * @return List 类型数据
     * @throws Exception
     */
    @Override
    public List<Object[]> getClasstimePloyById(final int id ) throws Exception
    {
        List<Object[]> ploylist=(List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            public Object doInHibernate(Session session) throws HibernateException {
                List list = session.createSQLQuery(
                        "select p.ploy_id,p.ploy_name,GROUP_CONCAT(DISTINCT conv( oct(g.group_id ) , 8, 10 )) as groupid," +
                                "GROUP_CONCAT(DISTINCT g.group_name) as groupname " +
                        "FROM classtime_ploy p " +
                        "LEFT JOIN classtimeploy2group p2g on p.ploy_id = p2g.ploy_id " +
                        "LEFT JOIN hostgroup g on p2g.group_id = g.group_id " +
                        "WHERE p.ploy_id = " +id+" "+
                        "GROUP BY p.ploy_id"
                ).list();
                return list;
            }
        });
        return ploylist;
    }
    /**
     * 将该时间策略所分配的组关系删除
     * @param id int 时间策略id;
     * @throws Exception
     */
    @Override
    public void delPloyGroupBySql(final int id) throws Exception
    {
        Object result =   this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                int result =  session.createSQLQuery("delete from classtimeploy2group where ploy_id ="+id).executeUpdate();
                if(result == 0)throw new HibernateException("no data need delete for user_group relation!");
                return result;
            }
        });
    }

    /**
     * 测试该ID的分组是否已经分配时间策略
     * @param id int 分组id;
     * @return boolean 为true则表示已分配，false未分配
     * @throws Exception
     */
    @Override
    public boolean isPloyGroupExsist(final int id) throws Exception
    {

        String result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select count(*) from classtimeploy2group where group_id="+id).uniqueResult();
            }
        }).toString();
        if(Integer.parseInt(result)>0)
            return true;
        else
            return false;
    }
    /**
     *  给分组分配某个时间策略
     * @param ployId int 时间策略id；
     * @param groupId int 分组id
     * @throws Exception
     */
    @Override
    public void addGroupToPloy(final int ployId, final int groupId) throws Exception
    {
        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                session.createSQLQuery(
                        "insert into classtimeploy2group(ploy_id,group_id) values("+ployId+","+groupId+")"
                ).executeUpdate();
                return null;
            }
        });
    }
    /**
     *  删除某时间策略的某个分组
     * @param ployId int 时间策略id；
     * @param groupId int 分组id
     * @throws Exception
     */
    @Override
    public void delGroup2Ploy(final int ployId, final int groupId) throws Exception
    {
        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                session.createSQLQuery(
                        "delete from classtimeploy2group where ploy_id="+ployId+ " and group_id="+groupId
                ).executeUpdate();
                return null;
            }
        });
    }

//    /**
//     * todo 加注释
//     * @return
//     * @throws Exception
//     */
//    @Override
//    public List<Object[]> getAllClasstimeployGroup() throws Exception {
//        String sql = "SELECT hg.group_id, hg.group_name, cp.ploy_name, c2g.p2g_id, cp.ploy_id " +
//                "from (" +
//                "       select group_id,group_name " +
//                "       from hostgroup " +
//                "       union " +
//                "       select -1,'未分组'" +
//                "      )hg " +
//                "LEFT JOIN classtimeploy2group c2g on hg.group_id=c2g.group_id " +
//                "LEFT JOIN classtime_ploy cp on c2g.ploy_id=cp.ploy_id";
//        Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
//        List<Object[]> lists = session.createSQLQuery(sql).list();
//
//        return lists;
//
//    }

    /**
     * add by lichong
     * 之前的分组状态是读取自己本地的数据库，现在改为读取地点服务的数据库
     *
     * 获取分组状态
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getAllClasstimeployGroup() throws Exception {
        String sql = "select " + "\n" +
                        "s_a.id as group_id, " + "\n" +
                        "s_a.name as group_name, " + "\n" +
                        "cp.ploy_name, " + "\n" +
                        "c2g.p2g_id, " + "\n" +
                        "cp.ploy_id  " + "\n" +
                    "from service.ad_area s_a " + "\n" +
                    "LEFT JOIN dmanager.classtimeploy2group c2g on s_a.id = c2g.group_id "+ "\n" +
                    "LEFT JOIN dmanager.classtime_ploy cp on c2g.ploy_id=cp.ploy_id " + "\n" +
                    "where s_a.type_id = "+ AREA_TYPE_ID;
        Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
        List<Object[]> lists = session.createSQLQuery(sql).list();

        return lists;

    }

    /**
     * todo 加注释
     * @param classtimePloy
     * @throws Exception
     */
    @Override
    public void saveTimePloy(ClasstimePloy classtimePloy) throws Exception {
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().save(classtimePloy);
    }

    /**
     * todo 加注释
     * @return
     * @throws Exception
     */
    @Override
    public List getAllTimePloy() throws Exception {
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("from ClasstimePloy").list();
    }

    /**
     * todo 加注释
     * @param timePloy
     * @return
     * @throws Exception
     */
    @Override
    public Integer getTimePloyId(String timePloy) throws Exception {
        return (Integer) this.getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createQuery("select ployId from ClasstimePloy where ployName=:ployName")
                .setParameter("ployName", timePloy)
                .uniqueResult();

    }

    /**
     * todo 加注释
     * @param timePloy
     * @throws Exception
     */
    @Override
    public void delTimePloy(String timePloy) throws Exception {
        this.getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createSQLQuery("DELETE FROM classtime_ploy WHERE ploy_name= :ploy_name")
                .setParameter("ploy_name", timePloy)
                .executeUpdate();

    }

    /**
     * todo 加注释
     * @param timePloyId
     * @throws Exception
     */
    @Override
    public void delClassTime(Integer timePloyId) throws Exception {
        this.getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createSQLQuery("DELETE FROM classtime WHERE classtime_ploy= :classtime_ploy")
                .setParameter("classtime_ploy", timePloyId)
                .executeUpdate();
    }

    /**
     * todo 加注释
     * @param groupId
     * @param ployId
     * @throws Exception
     */
   /* @Override
    public void delGroupName(String groupName) throws Exception {
        this.getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createSQLQuery("DELETE FROM hostgroup WHERE group_name= :group_name")
                .setParameter("group_name", groupName)
                .executeUpdate();
    }*/

    /**
     * todo 加注释
     * @param groupId
     * @param ployId
     * @throws Exception
     */
    @Override
    public void updateGroupIdAndPloyId(Integer groupId, Integer ployId) throws Exception {
        String sql = "UPDATE classtimeploy2group" +
                " SET ploy_id= :ploy_id" +
                " WHERE group_id= :group_id";
        this.getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createSQLQuery(sql)
                .setParameter("ploy_id", ployId)
                .setParameter("group_id", groupId)
                .executeUpdate();
    }

    /**
     * todo 加注释
     * @param groupId
     * @param ployId
     * @throws Exception
     */
    @Override
    public void saveGroupIdAndPloyId(Integer groupId, Integer ployId) throws Exception {
        String sql = "INSERT INTO classtimeploy2group (ploy_id, group_id) VALUES ("+ployId+", "+groupId+")";
        this.getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createSQLQuery(sql)
                .executeUpdate();
    }

    /**
     * todo 加注释
     * @param week
     * @param section
     * @throws Exception
     */
    @Override
    public void delSection(int week, int section) throws Exception {
        String sql = "DELETE FROM classtime WHERE week_id= :week_id and classtime_section= :classtime_section";
        this.getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createSQLQuery(sql)
                .setParameter("week_id", week)
                .setParameter("classtime_section", section)
                .executeUpdate();
    }

    /**
     * todo 加注释
     * @param ployId
     * @return
     * @throws Exception
     */
    @Override
    public List<Object[]> getAllClassTime(Integer ployId) throws Exception {
        String sql = "SELECT week_id, classtime_section, classtime_start, classtime_end" +
                " from classtime" +
                " where classtime_ploy= :classtime_ploy";
        List<Object[]> list = this.getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createSQLQuery(sql).setParameter("classtime_ploy", ployId).list();
        return list;
    }

    /**
     * todo 加注释
     * @param timePloy
     * @return
     * @throws Exception
     */
    @Override
    public ClasstimePloy getTimePloyByName(String timePloy) throws Exception {
        return (ClasstimePloy) this.getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createQuery("from ClasstimePloy where ployName= :ployName").setParameter("ployName", timePloy).uniqueResult();
    }

    /**
     * todo 加注释
     * @return
     * @throws Exception
     */
    @Override
    public List getGroupIds() throws Exception {
        String sql = "SELECT group_id from hostgroup";
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createSQLQuery(sql).list();
    }

    /**
     * todo 加注释
     * @param groupId
     * @return
     * @throws Exception
     */
    @Override
    public Integer getP2gId(Integer groupId) throws Exception {
        return (Integer) this.getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT p2g_id" +
                        " FROM classtimeploy2group" +
                        " WHERE group_id= :group_id")
                .setParameter("group_id", groupId)
                .uniqueResult();
    }

    /**
     * todo 加注释
     * @param timePloy
     * @return
     * @throws Exception
     */
    @Override
    public Integer getClassTimePloyId(String timePloy) throws Exception {
        return (Integer) this.getHibernateTemplate().getSessionFactory().getCurrentSession()
                .createSQLQuery("SELECT ploy_id" +
                        " FROM classtime_ploy" +
                        " WHERE ploy_name= :ploy_name")
                .setParameter("ploy_name", timePloy)
                .uniqueResult();
    }

}
