package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.dao.RoleDao;
import com.honghe.recordhibernate.entity.Role;
import org.apache.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Moon on 2014/9/15.
 */
@Repository
public class RoleDaoImpl extends BaseDao implements RoleDao
{
    private final static Logger logger = Logger.getLogger(RoleDaoImpl.class);

    /**
     * 获取角色列表
     * @return List<Role> 类型数据
     * @throws Exception
     */
    @Override
    public List<Role> getRoleList() throws Exception
    {
        List<Role> list = null;

        try
        {
            list = (List<Role>) this.getHibernateTemplate().find("from Role");
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            logger.error("", e);
            return null;
        }

        return list;
    }

    /**
     * 按分页的方式获取后台角色列表
     *
     * @param page Page 自定义page类，
     * @throws Exception
     */
    @Override
    public void getRoleListBackendByPage(Page page) throws Exception
    {
        HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
        List<Object[]> result = null;
        List countList = null;
        String s_sql = "select count(*) from role ";
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
            String str_sql = "select role_id,role_name from role order by role_id desc limit "+page.getFirstResult()+","+page.getPageSize();
            final String sql  = str_sql;
            result =(List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery(sql).list();
                }
            });
        }
        List newList=new ArrayList();
        for(int i=0;i<result.size();i++){
            Object[] objects = result.get(i);
            if(objects.length>0){
                //for(int j=0;i<objects.length;j++){
                Role role = new Role();
                role.setRoleId((Integer)objects[0]);
                role.setRoleName(objects[1].toString());
                newList.add(role);
            }
        }
        page.setResult(newList);
    }
    /**
     * 按分页的方式获取前台角色列表
     *
     * @param page Page 自定义page类，
     * @throws Exception
     */
    @Override
    public void getRoleListFrontendByPage(final String conditions,Page page) throws Exception
    {
        HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
        List<Object[]> result = null;
        List countList = null;
        String s_sql = "select count(*) from role " + conditions;
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
            String str_sql = "select role_id,role_name from role "+ conditions + " order by role_id desc limit "+page.getFirstResult()+","+page.getPageSize();
            final String sql  = str_sql;
            result =(List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery(sql).list();
                }
            });
        }
        List newList=new ArrayList();
        for(int i=0;i<result.size();i++){
            Object[] objects = result.get(i);
            if(objects.length>0){
                //for(int j=0;i<objects.length;j++){
                Role role = new Role();
                role.setRoleId((Integer)objects[0]);
                role.setRoleName(objects[1].toString());
                newList.add(role);
            }
        }
        page.setResult(newList);
    }
    /**
     * 根据条件获取角色列表
     * @param conditions String 查询条件
     * @return List<Role> 类型数据
     * @throws Exception
     */
    @Override
    public List<Role> getRoleList(final String conditions) throws Exception
    {
        List<Role> list = null;

        try
        {
            list = (List<Role>) this.getHibernateTemplate().find("from Role "+conditions);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

        return list;
    }

    /**
     * 根据名称查找角色列表
     * @param name String
     * @return List<Role> 类型数据
     * @throws Exception
     */
    @Override
    public List<Role> getRoleByName(String name) throws Exception
    {
        List list = null;

        Role role = new Role();
        role.setRoleName(name);
        try
        {
            list = this.getHibernateTemplate().findByExample(role);
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            logger.error("", e);
            return null;
        }

        return list.size()==0?null:list;
    }

    /**
     * 将角色信息存入数据库
     * @param role Role
     * @return boolean 类型数据
     * @throws Exception
     */
    @Override
    public boolean saveRole(Role role) throws Exception
    {
        try
        {
            this.getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
            this.getHibernateTemplate().save(role);
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            logger.error("", e);
            return false;
        }

        return true;
    }

    /**
     * 根据id获取角色信息
     * @param id int
     * @return Role 类型数据
     * @throws Exception
     */
    @Override
    public Role getRoleById(int id) throws Exception
    {
        try
        {
            List<Role> list = (List<Role>)this.getHibernateTemplate().find("from Role where role_id = "+id);
            if (list.size() >= 1)
            {
                return list.get(0);
            }
            return null;
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            logger.error("", e);
            return null;
        }
    }

    /**
     * 将更新的角色信息存入数据库
     * @param role Role
     * @return boolean 类型数据
     * @throws Exception
     */
    @Override
    public boolean updataRole(Role role) throws Exception
    {
        try
        {
            this.getHibernateTemplate().merge(role);
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
     * 将某角色从数据库中删除
     * @param id int
     * @return boolean 类型数据
     * @throws Exception
     */
    @Override
    public boolean delRole(int id) throws Exception
    {
        Role role = new Role();
        role.setRoleId(id);
        try
        {
            this.getHibernateTemplate().delete(role);
            return true;
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            logger.error("", e);
        }
        return false;
    }
    /**
     * 将某角色从数据库中删除
     * @param role Role
     * @return boolean 类型数据
     * @throws Exception
     */
    @Override
    public boolean delRole(Role role) throws Exception
    {
        try
        {
            this.getHibernateTemplate().delete(role);
            return true;
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            logger.error("", e);
        }
        return false;
    }
    /**
     * 将该角色所分配的权限关系删除
     * @param id int 角色id;
     * @throws Exception
     */
    @Override
    public void delRoleAuthBySql(final int id) throws Exception
    {
        try {
            Object result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    int result = session.createSQLQuery("delete from role2authority where role_id =" + id).executeUpdate();
                    if (result == 0) throw new HibernateException("no data need delete for role2authority relation!");
                    return result;
                }
            });
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            logger.error("", e);
        }
    }

    /**
     * 测试该ID的角色是否已经被分配权限
     * @param id int 角色id;
     * @return boolean 为true则表示已分配，false未分配
     * @throws Exception
     */
    @Override
    public boolean isRoleAuthExsist(final int id) throws Exception
    {
        try {
            String result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery("select count(*) from role2authority where role_id="+id).uniqueResult();
                }
            }).toString();
            if(Integer.parseInt(result)>0)
                return true;
            else
                return false;
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            logger.error("", e);
            return false;
        }
    }
    /**
     * 测试该ID的角色是否已经分配给用户
     * @param id int 角色id;
     * @return boolean 为true则表示已分配，false未分配
     * @throws Exception
     */
    @Override
    public boolean isRoleUserExsist(final int id) throws Exception
    {
        try {
            String result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery("select count(*) from user2role where role_id="+id).uniqueResult();
                }
            }).toString();
            if(Integer.parseInt(result)>0)
                return true;
            else
                return false;
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            logger.error("", e);
            return false;
        }
    }
    /**
     *  将权限分配给某个角色
     * @param roleId int 角色id
     * @param authId int 用户id；
     * @throws Exception
     */
    @Override
    public void addAuthToRole(final int roleId, final int authId) throws Exception
    {
        try
        {
            this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    session.createSQLQuery("insert into role2authority(role_id,auth_id) values("+roleId+","+authId+")").executeUpdate();
                    return null;
                }
            });
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            logger.error("", e);
        }
    }
}
