package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.AuthorityDao;
import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.entity.Authority;
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
 * Created by Moon on 2014/8/13.
 */

@Repository
public class AuthorityDaoImpl extends BaseDao implements AuthorityDao
{
//    @Resource
//    SessionFactory sessionFactory;
//    Session session;
//    Transaction transaction;

    private final static Logger logger = Logger.getLogger(AuthorityDaoImpl.class);

    /**
     * todo 加注释
     * @return
     */
    @Override
    public List getAuthorityList()
    {
        List<Authority> list = null;
//        session=sessionFactory.openSession();
//        String hql = "from Authority authority";
//        Query query = session.createQuery(hql);
//        list = query.list();

        list = (List<Authority>) this.getHibernateTemplate().find("from Authority");

        return list;
    }

    /**
     * 根据userId取前台显示权限列表 userId=-1：导播权限集合、巡课权限集合、角色权限；userId！=-1：属于该用户的导播权限集合、巡课权限集合、角色权限。
     * @param userId int 用户id;
     * @return List
     * @throws Exception
     */
    @Override
    public List getAuthorityFrontList(final int userId) throws Exception
    {
        List<Authority> authlist = new ArrayList();
        try
        {
            if(userId == -1 )
            {
                authlist = (List<Authority>) this.getHibernateTemplate().find("from Authority where auth_range =2 or auth_id = 1 ");
            }
            else
            {
                List<Object[]> result=(List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                    public Object doInHibernate(Session session) throws HibernateException {
                        List list = session.createSQLQuery(
                                "select DISTINCT a.auth_id,a.auth_word,a.auth_name,a.auth_desc " +
                                        "FROM user u " +
                                        "INNER JOIN user2role u2r on u.user_id = u2r.user_id " +
                                        "INNER JOIN role2authority r2a on u2r.role_id = r2a.role_id " +
                                        "INNER JOIN authority a on r2a.auth_id = a.auth_id " +
                                        "WHERE u.user_id = " + userId+" and (a.auth_range = 2 or a.auth_id = 1)"
                        ).list();
                        return list;
                    }
                });
                for(int i=0;i<result.size();i++){
                    Object[] objects = result.get(i);
                    if(objects.length>0){
                        //for(int j=0;i<objects.length;j++){
                        Authority authority = new Authority();
                        authority.setAuthId((Integer)objects[0]);
                        authority.setAuthWord(objects[1].toString());
                        authority.setAuthName(objects[2].toString());
                        if(objects[3]!=null) {
                            authority.setAuthDesc(objects[3].toString());
                        }
                        authlist.add(authority);
                    }
                }
            }
            return authlist;
        }
        catch (Exception e)
        {
//            e.printStackTrace();
            logger.error("",e);
            return null;
        }
    }
    /**
     * 根据userId取后台显示权限列表 userId=-1：导播权限集合、巡课权限集合、角色权限；
     * userId=1、userId=2:产品定义权限集合、导播权限集合、巡课权限集合、角色权限；
     * 其他：属于该用户的导播权限集合、巡课权限集合、角色权限。
     * @param userId int 用户id;
     * @return List
     * @throws Exception
     */
    @Override
    public List getAuthorityBackList(final int userId) throws Exception
    {
        List<Authority> authlist = new ArrayList();
        try
        {
            if(userId == -1 )
            {
                authlist = (List<Authority>) this.getHibernateTemplate().find("from Authority where auth_id =2 or auth_id = 1 ");
            }
            else if(userId == 1 )
            {
                authlist = (List<Authority>) this.getHibernateTemplate().find("from Authority  ");
            }
            else if(userId ==2)
            {
                List<Object[]> result=(List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                    public Object doInHibernate(Session session) throws HibernateException {
                        List list = session.createSQLQuery(
                                "select DISTINCT a.auth_id,a.auth_word,a.auth_name,a.auth_desc " +
                                        "FROM user u " +
                                        "INNER JOIN user2role u2r on u.user_id = u2r.user_id " +
                                        "INNER JOIN role2authority r2a on u2r.role_id = r2a.role_id " +
                                        "INNER JOIN authority a on r2a.auth_id = a.auth_id " +
                                        "WHERE u.user_id = " + userId
                        ).list();
                        return list;
                    }
                });
                for(int i=0;i<result.size();i++){
                    Object[] objects = result.get(i);
                    if(objects.length>0){
                        //for(int j=0;i<objects.length;j++){
                        Authority authority = new Authority();
                        authority.setAuthId((Integer)objects[0]);
                        authority.setAuthWord(objects[1].toString());
                        authority.setAuthName(objects[2].toString());
                        if(objects[3]!=null) {
                            authority.setAuthDesc(objects[3].toString());
                        }
                        authlist.add(authority);
                    }
                }
            }
            else
            {
                List<Object[]> result=(List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                    public Object doInHibernate(Session session) throws HibernateException {
                        List list = session.createSQLQuery(
                                "select DISTINCT a.auth_id,a.auth_word,a.auth_name,a.auth_desc " +
                                        "FROM user u " +
                                        "INNER JOIN user2role u2r on u.user_id = u2r.user_id " +
                                        "INNER JOIN role2authority r2a on u2r.role_id = r2a.role_id " +
                                        "INNER JOIN authority a on r2a.auth_id = a.auth_id " +
                                        "WHERE u.user_id = " + userId+" and (a.auth_range = 2 or a.auth_id = 1)"
                        ).list();
                        return list;
                    }
                });
                for(int i=0;i<result.size();i++){
                    Object[] objects = result.get(i);
                    if(objects.length>0){
                        //for(int j=0;i<objects.length;j++){
                        Authority authority = new Authority();
                        authority.setAuthId((Integer)objects[0]);
                        authority.setAuthWord(objects[1].toString());
                        authority.setAuthName(objects[2].toString());
                        if(objects[3]!=null) {
                            authority.setAuthDesc(objects[3].toString());
                        }
                        authlist.add(authority);
                    }
                }
            }
            return authlist;
        }
        catch (Exception e)
        {
            logger.error("",e);
            return null;
        }
    }

    /**
     * todo 加注释
     * @param name
     * @return
     */
    @Override
    public List getAuthorityNameList(String name)
    {
        List<Authority> list = null;

        Authority authority = new Authority();
        authority.setAuthName(name);
        list = (List<Authority>) this.getHibernateTemplate().findByExample(authority);

        return list.size()==0?null:list;
    }

    /**
     * todo 加注释
     * @param word
     * @return
     */
    @Override
    public List getAuthorityWordList(String word)
    {
        List<Authority> list = null;

        Authority authority = new Authority();
        authority.setAuthWord(word);
        list = (List<Authority>) this.getHibernateTemplate().findByExample(authority);

        return list.size()==0?null:list;
    }

    /**
     * 按分页的方式获取后台权限列表
     *
     * @param page Page 自定义page类，
     * @throws Exception
     */
    @Override
    public void getAuthorityListBackendByPage(Page page) throws Exception
    {
        HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
        List<Object[]> result = null;
        List countList = null;
        String s_sql = "select count(*) from authority ";
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
            String str_sql = "select auth_id,auth_word,auth_name,auth_desc,auth_range from authority limit "+page.getFirstResult()+","+page.getPageSize();
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
                Authority authority = new Authority();
                authority.setAuthId((Integer)objects[0]);
                authority.setAuthWord(objects[1].toString());
                authority.setAuthName(objects[2].toString());
                if(objects[3]!=null)
                {
                    authority.setAuthDesc(objects[3].toString());
                }
                authority.setAuthRange((Integer)objects[4]);
                newList.add(authority);
            }
        }
        page.setResult(newList);
    }

    /**
     * todo 加注释
     * @param authority
     */
    @Override
    public void saveAuthority(Authority authority)
    {
        this.getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
        this.getHibernateTemplate().save(authority);
    }

    /**
     * todo 加注释
     * @param id
     * @throws Exception
     */
    @Override
    public void delAuthority(int id) throws Exception
    {
        try {
            Authority authority = new Authority();
            authority.setAuthId(id);
            this.getHibernateTemplate().delete(authority);
        }
        catch (Exception e)
        {
//         e.printStackTrace();
           logger.error("",e);
        }
    }

    /**
     * todo 加注释
     * @param authority
     * @return
     * @throws Exception
     */
    @Override
    public boolean delAuthority(Authority authority) throws Exception
    {
        try {
            this.getHibernateTemplate().delete(authority);
            return true;
        }
        catch (Exception e)
        {
//            e.printStackTrace();
            logger.error("",e);
            return false;
        }
    }

    /**
     * todo 加注释
     * @param id
     * @return
     */
    @Override
    public Authority getAluthorityById(int id)
    {
        List<Authority> list = (List<Authority>)this.getHibernateTemplate().find(" from Authority where auth_id = "+id);
        if (list.size() >= 1)
        {
            return list.get(0);
        }

        return null;
    }

    /**
     * todo 加注释
     * @param authority
     * @return
     */
    @Override
    public boolean updatAuthority(Authority authority)
    {
        try
        {
            this.getHibernateTemplate().merge(authority);
            return true;
        }
        catch (Exception e)
        {
        //            e.printStackTrace();
            logger.error("",e);
            return false;
        }
    }
    /**
     * 测试该ID的权限是否已经分配给角色
     * @param id int 权限id;
     * @return boolean 为true则表示已分配，false未分配
     * @throws Exception
     */
    @Override
    public boolean isAuthRoleExsist(final int id) throws Exception
    {
        try {
            String result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery("select count(*) from role2authority where auth_id="+id).uniqueResult();
                }
            }).toString();
            if(Integer.parseInt(result)>0)
                return true;
            else
                return false;
        }
        catch (Exception e)
        {
//            e.printStackTrace();
            logger.error("",e);
            return false;
        }
    }
}
