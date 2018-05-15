package com.honghe.recordhibernate.dao.impl;

import com.honghe.recordhibernate.dao.BaseDao;
import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.dao.RoleDao;
import com.honghe.recordhibernate.dao.UserDao;
import com.honghe.recordhibernate.entity.Authority;
import com.honghe.recordhibernate.entity.Hostgroup;
import com.honghe.recordhibernate.entity.Role;
import com.honghe.recordhibernate.entity.User;
import com.honghe.recordhibernate.util.MD5Util;
import com.honghe.recordhibernate.util.SaltRandom;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by Moon on 2014/8/11.
 */

@Repository
public class UserDaoImpl extends BaseDao implements UserDao
{
    public final static int TYPE_SHCOOL = 3;
    public final static int TYPE_AREA = 2;
    private final static Logger logger = Logger.getLogger(UserDaoImpl.class);
    public final static String SQL = "select  u.user_id as userId, u.user_name as userName," +
            "u.user_type as userType,u.user_realname as userRealName,u.user_realname as name,u.user_path as userPath,u.user_avatar as userAvatar," +
            "u.user_gender as userGender,u.user_birthday as userBirthday,u.user_mobile as userMobile,u.user_email as userEmail," +
            "u.user_address as userAddress,u.user_num as userNum,u.user_regdate as userRegdate,u.user_status as userStatus," +
            "u.user_card as userCard,u.user_course as userCourse,u.user_info as userInfo,u.user_isadmin as userIsAdmin,u.user_itoken as userIToken," +
            "a.id as agencyId, a.name as agencyName,a.p_id as pId,a.type_id as agencyLevel " +
            "from service.user u left join service.ad_campus2user c2u on u.user_id=c2u.user_id left join service.ad_campus a on c2u.campus_id=a.id ";

    /**
     * 获取某用户的信息
     *
     * @param name String，
     * @return User 类型数据
     * @throws Exception
     */
    @Override
    public User getUser(String name)  throws Exception
    {
        User user = new User();
        user.setUserName(name);
        //user.setUserPwd(password);
        try
        {
          List<User> users = (List<User>) this.getHibernateTemplate().findByExample(user);
          if(!users.isEmpty())
          {
            return users.get(0);
          }
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            logger.error("", e);
            return null;
        }

        return null;
    }
    /**
     * 根据用户名查找用户信息
     *
     * @param name String，
     * @return List<User> 类型数据
     * @throws Exception
     */
    @Override
    public List<User> getUserByName(String name)  throws Exception
    {
        //user.setUserPwd(password);
        try
        {
            //List<User> users = (List<User>) this.getHibernateTemplate().findByExample(user);
            List<User> users =(List<User>) this.getHibernateTemplate().find("from User u where u.userName like '%"+name+"%'");
            return users;
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            logger.error("", e);
            return null;
        }

    }
    /**
     * 根据用户名查找用户信息
     *
     * @param name String，
     * @return List<User> 类型数据
     * @throws Exception
     */
    @Override
    public List<User> getFrontUserByName(String name)  throws Exception
    {
        try
        {
            //HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
            List<Object[]> result = null;
            String str_sql = "select DISTINCT u.user_id,u.user_name,u.user_pwd, u.school_name,u.user_desc,u.user_salt,GROUP_CONCAT( DISTINCT conv( oct(r.role_id ) , 8, 10 )) as roleids,GROUP_CONCAT(DISTINCT r.role_name) as roles " +
                    "from user u " +
                    "LEFT JOIN user2role u2r on u.user_id = u2r.user_id " +
                    "LEFT JOIN role r on u2r.role_id = r.role_id " +
                    "where u.user_id not in (select user_id from user2role where role_id = 1 or role_id = 2  ) " +
                    "and u.user_name like '%"+name+"%' " +
                    "GROUP BY u.user_id " ;
            final String sql  = str_sql;
            result =(List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery(sql).list();
                }
            });
            List newList=new ArrayList();
            for(int i=0;i<result.size();i++){
                Object[] objects = result.get(i);
                if(objects.length>0){
                    //for(int j=0;i<objects.length;j++){
                    User user = new User();
                    user.setUserId((Integer)objects[0]);
                    user.setUserName(objects[1].toString());
                    user.setUserPwd(objects[2].toString());
                    if(objects[3]!=null)
                    {
                        user.setSchoolName(objects[3].toString());
                    }
                    if(objects[4]!=null)
                    {
                        user.setUserDesc(objects[4].toString());
                    }
                    if(objects[5]!=null)
                    {
                        user.setUser_salt(objects[5].toString());
                    }
                    Set<Role> roleList = new HashSet<Role>();
                    if(objects[6]!=null && !objects[6].equals("")) {
                        String[] roleIds = objects[6].toString().split(",");
                        String[] roleNames = objects[7].toString().split(",");
                        for (int j = 0; j < roleIds.length; j++) {
                            Role role = new Role();
                            role.setRoleId(Integer.parseInt(roleIds[j]));
                            role.setRoleName(roleNames[j]);
                            roleList.add(role);
                        }
                    }
                    user.setRoles(roleList);
                    newList.add(user);
                }
            }
            return newList;
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            logger.error("", e);
            return null;
        }

    }
    /**
     * 获取某用户的角色列表
     *
     * @param user User 自定义User类，
     * @return HashMap<String,Integer> 类型数据
     * @throws Exception
     */
    public HashMap<String,Integer> getUserRoles(User user) throws Exception
    {
        try
        {
            Set<Role> usersarray = user.getRoles();
            HashMap<String, Integer> roleauth = new HashMap<String, Integer>(0);

            Iterator iterator = usersarray.iterator();
            while (iterator.hasNext()) {
                int roleid = ((Role) iterator.next()).getRoleId();
                roleauth.put("" + roleid, new Integer(roleid));
            }

            return roleauth;
        }
        catch (Exception e)
        {
//            System.out.println("UserDaoImpl error catch");
            //e.printStackTrace();
            logger.debug("UserDaoImpl error catch");
            logger.error("", e);
        }
        return null;
    }
    /**
     * 获取某用户的权限列表
     *
     * @param user User 自定义User类，
     * @return HashMap<String,Integer> 类型数据
     * @throws Exception
     */
    @Override
    public HashMap<String,Integer> getUserAuthoritys(User user) throws Exception {
        try
        {
            HashMap<String, Integer> authmap = new HashMap<String, Integer>(0);
            Set<Role> roles = user.getRoles();
            Iterator iterator = roles.iterator();
            while (iterator.hasNext()) {
                Set<Authority> auths2role= ((Role) iterator.next()).getAuthoritys();
                Iterator authIterator = auths2role.iterator();
                while (authIterator.hasNext()) {
                    Authority authority = (Authority)authIterator.next();
                    String authWord = authority.getAuthWord();
                    int authId = authority.getAuthId();
                    if(!authmap.containsKey(authWord)) {
                        authmap.put(authWord, authId);
                    }
                }
            }

            return authmap;
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            logger.error("", e);
            return null;
        }

    }
    /**
     * 获取某用户所属组别
     *
     * @param user User 自定义User类，
     * @return HashMap<String,Integer> 类型数据
     * @throws Exception
     */
    public HashMap<String,String> getHostgroups(User user) throws Exception
    {
        try
        {
            Set<Hostgroup> usersarray = user.getHostgroups();
            HashMap<String, String> hostgroupMap = new HashMap<String, String>(0);

            Iterator iterator = usersarray.iterator();
            while (iterator.hasNext()) {
                Hostgroup hostgroup = (Hostgroup) iterator.next();
                int hostgroupid = hostgroup.getGroupId();
                String hostgroupname = hostgroup.getGroupName();
                hostgroupMap.put("" + hostgroupid, hostgroupname);
            }

            return hostgroupMap;
        }
        catch (Exception e)
        {
//            System.out.println("UserDaoImpl error catch");
            //e.printStackTrace();
            logger.error("", e);
        }
        return null;
    }
    /**
     * 获取用户列表
     *
     * @return List 类型数据
     * @throws Exception
     */
    @Override
    public List<User> getUserList() throws Exception
    {
        List<User> list = null;

        try
        {
            list = (List<User>) this.getHibernateTemplate().find("from User");
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
     * 按分页的方式获取用户列表
     *
     * @param page Page<User> 自定义page<User>类，
     * @throws Exception
     */
    @Override
    public void getUserList(final Page<User> page) throws Exception {
        HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
        List countList = hibernateTemplate.find("select count(*) from User");
        int totalCount = Integer.parseInt(countList.get(0).toString());
        page.setTotalPageSize(totalCount);
        if (totalCount != 0) {
            DetachedCriteria crit = DetachedCriteria.forClass(User.class);
            crit.addOrder(Order.desc("userId"));
            List result = hibernateTemplate.findByCriteria(crit, page.getFirstResult(), page.getPageSize());
            page.setResult(result);
        }
    }
    /**
     * 按分页的方式获取前台用户列表
     *
     * @param page Page 自定义page类，
     * @throws Exception
     */
    @Override
    public void getUserListFrontendByPage(Page page) throws Exception
    {
        HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
        List<Object[]> result = null;
        List countList = null;
        String s_sql = "select count(DISTINCT u.user_id) from user u where u.user_id not in (select user_id from user2role where role_id = 1 or role_id = 2) ";
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
            String str_sql = "select DISTINCT u.user_id,u.user_name,u.user_pwd, u.school_name,u.user_desc,u.user_salt,GROUP_CONCAT( DISTINCT conv( oct(r.role_id ) , 8, 10 )) as roleids,GROUP_CONCAT(DISTINCT r.role_name) as roles " +
                    "from user u " +
                    "LEFT JOIN user2role u2r on u.user_id = u2r.user_id " +
                    "LEFT JOIN role r on u2r.role_id = r.role_id " +
                    "where u.user_id not in (select user_id from user2role where role_id = 1 or role_id = 2  ) " +
                    "GROUP BY u.user_id order by u.user_id ASC ,r.role_id ASC " +
                    "limit "+page.getFirstResult()+","+page.getPageSize();
            final String sql  = str_sql;
            result =(List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery(sql).list();
                }
            });
        }
      //  List<User> newList = null;  //实体类
        List newList=new ArrayList();
        for(int i=0;i<result.size();i++){
            Object[] objects = result.get(i);
            if(objects.length>0){
                //for(int j=0;i<objects.length;j++){
                User user = new User();
                user.setUserId((Integer)objects[0]);
                user.setUserName(objects[1].toString());
                user.setUserPwd(objects[2].toString());
                if(objects[3]!=null)
                {
                    user.setSchoolName(objects[3].toString());
                }
                if(objects[4]!=null)
                {
                    user.setUserDesc(objects[4].toString());
                }
                if(objects[5]!=null)
                {
                    user.setUser_salt(objects[5].toString());
                }
                Set<Role> roleList = new HashSet<Role>();
                if(objects[6]!=null && !objects[6].equals("")) {
                    String[] roleIds = objects[6].toString().split(",");
                    String[] roleNames = objects[7].toString().split(",");
                    for (int j = 0; j < roleIds.length; j++) {
                        Role role = new Role();
                        role.setRoleId(Integer.parseInt(roleIds[j]));
                        role.setRoleName(roleNames[j]);
                        roleList.add(role);
                    }
                }
                user.setRoles(roleList);
                newList.add(user);
            }
        }
        page.setResult(newList);
    }
    /**
     * 获取用户列表
     *
     * @param id int 用户id;
     * @return List 类型数据
     * @throws Exception
     */
    @Override
    public List<Object[]> getUserById(final int id ) throws Exception
    {
        try {
            List<Object[]> authlist=(List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                public Object doInHibernate(Session session) throws HibernateException {
                    List list = session.createSQLQuery(
                            "select u.user_id,u.user_name,u.user_pwd, u.school_name,u.user_desc,u.user_salt,GROUP_CONCAT(DISTINCT conv( oct(hg.group_id ) , 8, 10 )) as groupid,GROUP_CONCAT(DISTINCT hg.group_name) as groupname,GROUP_CONCAT(DISTINCT conv( oct(u2r.role_id ) , 8, 10 )) as roleid,GROUP_CONCAT(DISTINCT r.role_name) as rolename,GROUP_CONCAT(DISTINCT conv( oct(a.auth_id ) , 8, 10 )) as authid,GROUP_CONCAT(DISTINCT a.auth_name ) as authname " +
                                    "FROM user u " +
                                    "LEFT JOIN group2user g2u on u.user_id = g2u.user_id " +
                                    "LEFT JOIN hostgroup hg on g2u.group_id = hg.group_id " +
                                    "LEFT JOIN user2role u2r on u.user_id = u2r.user_id " +
                                    "LEFT JOIN role r on u2r.role_id = r.role_id " +
                                    "LEFT JOIN role2authority r2a on u2r.role_id = r2a.role_id " +
                                    "LEFT JOIN authority a on r2a.auth_id = a.auth_id " +
                                    "WHERE u.user_id = " + id+" "+
                                    "GROUP BY u.user_id"
                    ).list();
                    return list;
                }
            });
            return authlist;
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            logger.error("", e);
            return null;
        }
    }
    @Override
    public List<Object[]> getUserGroupById(final int id ) throws Exception
    {
        try {
            List<Object[]> authlist=(List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                public Object doInHibernate(Session session) throws HibernateException {
                List list = session.createSQLQuery(
                    "select hg.group_id,hg.group_name " +
                        "FROM group2user g2u , hostgroup hg " +
                        "WHERE g2u.user_id = " + id+" and g2u.group_id = hg.group_id "
                ).list();
                return list;
                }
            });
            return authlist;
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            logger.error("", e);
            return null;
        }
    }
    /**
     * 保存用户信息
     * @param user User类型 需要保存的用户信息
     * @throws Exception
     */
    @Override
    public boolean saveUser(User user) throws Exception
    {
        try
        {
            this.getHibernateTemplate().save(user);
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
     * 修改用户信息
     * @param user User类型 需要修改的用户信息
     * @return boolean 修改是否成功
     * @throws Exception
     */
    @Override
    public boolean updateUser(User user)  throws Exception
    {
        try {
            this.getHibernateTemplate().update(user);
            return true;
        }
        catch (Exception e) {
            //e.printStackTrace();
            logger.error("", e);
            return false;
        }
    }

    /**
     * 删除用户信息
     * @param id int 用户id;
     * @throws Exception
     */
    @Override
    public void delUser(int id)  throws Exception
    {
        try {
            User user = new User();
            user.setUserId(id);
            this.getHibernateTemplate().delete(user);
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            logger.error("", e);
        }
    }
    /**
     * 删除用户信息
     * @param user User;
     * @throws Exception
     */
    @Override
    public boolean delUser(User user)  throws Exception
    {
        try {
            this.getHibernateTemplate().delete(user);
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
     * 将该用户所分配的角色关系删除
     * @param id int 用户id;
     * @throws Exception
     */
    @Override
    public void delUserRoleBySql(final int id) throws Exception
    {
        Object result =   this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                int result =  session.createSQLQuery("delete from user2role where user_id ="+id).executeUpdate();
                if(result == 0)throw new HibernateException("no data need delete for userrole relation!");
                return result;
            }
        });
    }

    /**
     * 测试该ID的用户是否已经被分配角色
     * @param id int 用户id;
     * @return boolean 为true则表示已分配，false未分配
     * @throws Exception
     */
    @Override
    public boolean isUserRoleExsist(final int id) throws Exception
    {

        String result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select count(*) from user2role where user_id="+id).uniqueResult();
            }
        }).toString();
        if(Integer.parseInt(result)>0)
            return true;
        else
            return false;
    }
    /**
     *  将角色分配给某个用户
     * @param userId int 用户id；
     * @param roleId int 角色id
     * @throws Exception
     */
    @Override
    public void addUserToRole(final int userId, final int roleId) throws Exception
    {
        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                session.createSQLQuery("insert into user2role(user_id,role_id) values("+userId+","+roleId+")").executeUpdate();
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }
    /**
     * 将该用户所分配的组关系删除
     * @param id int 用户id;
     * @throws Exception
     */
    @Override
    public void delUserGroupBySql(final int id) throws Exception
    {
        Object result =   this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                int result =  session.createSQLQuery("delete from group2user where user_id ="+id).executeUpdate();
                if(result == 0)throw new HibernateException("no data need delete for user_group relation!");
                return result;
            }
        });
    }

    /**
     * 测试该ID的用户是否已经被分配组
     * @param id int 用户id;
     * @return boolean 为true则表示已分配，false未分配
     * @throws Exception
     */
    @Override
    public boolean isUserGroupExsist(final int id) throws Exception
    {

        String result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select count(*) from group2user where user_id="+id).uniqueResult();
            }
        }).toString();
        if(Integer.parseInt(result)>0)
            return true;
        else
            return false;
    }
    /**
     *  给用户分配某个组
     * @param userId int 用户id；
     * @param groupId int 角色id
     * @throws Exception
     */
    @Override
    public void addUserToGroup(final int userId, final int groupId) throws Exception
    {
        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                session.createSQLQuery("insert into group2user(user_id,group_id) values("+userId+","+groupId+")").executeUpdate();
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }
    /**
     * 根据用户ID查找用户信息
     *
     * @param userId int，
     * @return User 类型数据
     * @throws Exception
     */
    @Override
    public User getUserinfoById(int userId)  throws Exception
    {
        try
        {
            //List<User> users = (List<User>) this.getHibernateTemplate().findByExample(user);
            List<User> users =(List<User>) this.getHibernateTemplate().find("from User u where u.userId ="+userId);
            return users.get(0);
        }
        catch (Exception e)
        {
            //e.printStackTrace();
            logger.error("", e);
            return null;
        }

    }
    @Override
    public List<Object[]> getUserByNamePage(String name,Page page) throws Exception
    {
        final String s_sql;
        if(name.equals(""))
        {
            s_sql = "select count(*) from user u where u.user_id not in (select user_id from user2role where role_id = 1 or role_id = 2  )";
        }
        else
        {
            s_sql = "select count(*) from user u where u.user_id not in (select user_id from user2role where role_id = 1 or role_id = 2  ) and user_name like '%"+name+"%'";
        }
        HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
        List users = null;

        List countList = null;
        countList = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                // System.out.println(s_sql_count);
                return session.createSQLQuery(s_sql).list();
            }
        });
        //List countList = hibernateTemplate.find("select count(h) from Host h,group2host g2h where h.host_id =g2h.host_id and g2h.group_id="+hostgroupId);
        int totalCount = Integer.parseInt(countList.get(0).toString());
        page.setTotalPageSize(totalCount);
        if (totalCount != 0)
        {
            final String sql;
            if(name.equals(""))
            {
                sql = "select u.user_id,u.user_name, u.school_name,u.user_desc,group_concat(r.role_name) from user u left join user2role ur on ur.user_id = u.user_id left join role r on r.role_id = ur.role_id where u.user_id not in (select user_id from user2role where role_id = 1 or role_id = 2  ) group by u.user_id order by u.user_id,r.role_id  limit " + page.getFirstResult() + "," + page.getPageSize();
            }
            else
            {
                sql = "select u.user_id,u.user_name, u.school_name,u.user_desc,group_concat(r.role_name) from user u left join user2role ur on ur.user_id = u.user_id left join role r on r.role_id = ur.role_id where u.user_id not in (select user_id from user2role where role_id = 1 or role_id = 2  ) and user_name like '%" + name + "%'  group by u.user_id order by u.user_id,r.role_id limit " + page.getFirstResult() + "," + page.getPageSize();
            }
            users =(List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery(sql).list();
                }
            });
            //users =(List<User>) this.getHibernateTemplate().find("from User u where u.userName like '%"+name+"%' limit  " + page.getFirstResult() + "," + page.getPageSize());
        }

       /* HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
        List<Object[]> result = null;

        List countList = null;
        String s_sql = "select count(*) from User where userName = '"+name+"'";
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
        if (totalCount != 0)
        {
            String str_sql = "select userId,userName, schoolName,userDesc where userName= '" + name + "' limit " + page.getFirstResult() + "," + page.getPageSize();
            final String sql  = str_sql;

            result =(List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    System.out.println(sql);
                    return session.createSQLQuery(sql).list();
                }
            });
        }*/

        return users;
    }
    /**
     * 按分页的方式获取前台用户列表
     *
     * @param page Page 自定义page类，
     * @throws Exception
     */
    @Override
    public List<Object[]> getUserListFrontByPage(Page page) throws Exception
    {
        HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
        List<Object[]> result = null;
        List countList = null;
        String s_sql = "select count(DISTINCT u.user_id) from user u where u.user_id not in (select user_id from user2role where role_id = 1 or role_id = 2) ";
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
            String str_sql = "select DISTINCT u.user_id,u.user_name, u.school_name,u.user_desc,GROUP_CONCAT( DISTINCT conv( oct(r.role_id ) , 8, 10 )) as roleids,GROUP_CONCAT(DISTINCT r.role_name) as roles " +
                    "from user u " +
                    "LEFT JOIN user2role u2r on u.user_id = u2r.user_id " +
                    "LEFT JOIN role r on u2r.role_id = r.role_id " +
                    "where u.user_id not in (select user_id from user2role where role_id = 1 or role_id = 2  ) " +
                    "GROUP BY u.user_id order by u.user_id DESC ,r.role_id ASC " +
                    "limit "+page.getFirstResult()+","+page.getPageSize();
            final String sql  = str_sql;
            result =(List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery(sql).list();
                }
            });
        }
       return result;
    }
    /**
     * 测试校园管理员是否存在
     * @return boolean 为true则表示已分配，false未分配
     * @throws Exception
     */
    @Override
    public boolean isSchoolAdminExists() throws Exception
    {

        String result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select count(*) from user2role where role_id = 3").uniqueResult();
            }
        }).toString();
        if(Integer.parseInt(result)>0)
            return true;
        else
            return false;
    }

    @Override
    public int getRoleByUserId(final int uid) throws Exception{
        String result = this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery("select role_id from user2role where user_id = "+uid).uniqueResult();
            }
        }).toString();
        return Integer.parseInt(result);
    }

    /**
     *  用户管理添加用户后向资源平台写入用户扩展表
     * @param userId int 用户id；
     * @param
     * @throws Exception
     */
    @Override
    public void addUserToRes(final int userId) throws Exception
    {
        this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                session.createSQLQuery("insert into hvideo_db_3_1_0.hv_user(user_id,user_type,user_agency_id,user_status)" +
                        " values("+userId+",1,1,0)").executeUpdate();
                return null;
            }
        });
    }
    /**
     * 功能描述:
     * @param:
     * @return: 
     * @auther: zou
     * @date:  
     */
    public final Map<String,String> login(final String name, String password)throws Exception {
        final String _SQL = "select  user_id as userId, user_name as userName,user_pwd as userPwd,user_salt as userSalt," +
                "user_type as userType,user_realname as userRealName,user_path as userPath,user_avatar as userAvatar," +
                "user_gender as userGender,user_birthday as userBirthday,user_mobile as userMobile,user_email as userEmail," +
                "user_address as userAddress,user_num as userNum,user_regdate as userRegdate,user_status as userStatus, " +
                "user_card as userCard,user_course as userCourse,user_info as userInfo from service.user ";

        List list =(List)this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                return session.createSQLQuery(_SQL + "where (user_name='" + name + "' or user_email='" + name + "' or user_mobile='" + name + "' or user_num='" + name + "') and (user_status=1 or user_status=0)").setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
            }
        });
        Map result = (Map)list.get(0);
        if (result.isEmpty()) {
            return Collections.emptyMap();
        }
        String id = result.get("userId")+"";
        result.put("userId",id);
        String userSalt = (String)result.get("userSalt");
        password = MD5Util.getMD5((password + userSalt).getBytes());
        if (result.get("userPwd").equals(password)) {
            result.remove("userPwd");
            result.remove("userSalt");
            return result;
        }
        return Collections.emptyMap();
    }
    /**
     * 功能描述:
     * @param:
     * @return:
     * @auther: zou
     * @date:
     */
    public final int resetPwd(final String userId, String oldPassword, String newPassword)throws Exception {
        //select user_pwd as userPwd,user_salt as userSalt  from user where user_id=" + userId
        //"update user set user_pwd='" + password + "',user_salt='" + salt + "' where user_id=" + userId
        if (!oldPassword.equals("")) {
            List list =(List)this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    return session.createSQLQuery("select user_pwd as userPwd,user_salt as userSalt  from service.user where user_id=" + userId).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
                }
            });
            Map<String, String> userInfo = (Map)list.get(0);
            if (userInfo.isEmpty()) {
                return 1;// 用户不存在
            }
            String userPwd = userInfo.get("userPwd");
            String userSalt = userInfo.get("userSalt");
            if (!MD5Util.getMD5((oldPassword + userSalt).getBytes()).equals(userPwd)) {
                return 2;// 旧密码错误
            }
        }else{
            return 2;// 旧密码错误
        }
        final String salt = SaltRandom.runVerifyCode(6);
        final String password = MD5Util.getMD5((newPassword + salt).getBytes());
        Object result =   this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                int result =  session.createSQLQuery("update service.user set user_pwd='" + password + "',user_salt='" + salt + "' where user_id=" + userId).executeUpdate();
                if(result == 1){
                    return 0;// 修改成功
                }else{
                    return 3;// 未知错误
                }
            }
        });
      return (int)result;
    }

    /*---------------------------------------------------------------用户服务数据库查询--------------------------------------------------------------*/

    public Map<String, String> find(int userId) {
        Map result = (Map) this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(SQL + " where u.user_id=" + userId).uniqueResult();
        List<Map<String,String>> resultCampus = new ArrayList<>();
        if(userId==1){
            Map map = new HashMap();
            map.put("campus_id","1");
            map.put("type_id","1");
            resultCampus.add(map);
        }else{
            String sql ="select a.campus_id ,c.type_id from service.ad_campus2user a ,service.ad_campus c where a.campus_id = c.id and a.user_id = "+userId+" ";
            resultCampus = (List<Map<String, String>>) this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).uniqueResult();
        }
        if(result.size()>0) {
            if (resultCampus.size() > 0) {
                StringBuffer campusname = new StringBuffer();
                StringBuffer campustype = new StringBuffer();
                for (int i = 0; i < resultCampus.size(); i++) {
                    Map map = resultCampus.get(i);
                    campusname.append(map.get("campus_id").toString()).append(",");
                    campustype.append(map.get("type_id").toString()).append(",");
                }
                String campusId = campusname.toString();
                String campusType = campustype.toString();
                if (null != campusId && !"".equals(campusId)) {
                    campusId = campusId.substring(0, campusId.length() - 1);
                }
                if (null != campusType && !"".equals(campusType)) {
                    campusType = campusType.substring(0, campusType.length() - 1);
                }
                result.put("campusId", campusId);
                result.put("campusType", campusType);
            }
            else{
                result.put("campusId", "");
                result.put("campusType", "");
            }
        }
        return result;
    }

    //----------------------------------------------------查询用户信息-----------------------------------------------
    public List<Map<String, String>> findByIds(String[] userId) {
        StringBuilder sb = new StringBuilder();
        for (String _userId : userId) {
            sb.append(_userId + ",");
        }
        String u = sb.toString().substring(0, sb.length() - 1);
        return (List<Map<String, String>>) this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(SQL + "where  u.user_id in(" + u + ")").list();
    }

    public Map<String, String> findByName(String name) {
        return (Map<String, String>) this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery("select user_id as userId from service.user where user_name='" + name + "' and (user_status=1 or user_status=0)").uniqueResult();
    }

    public List<Map<String,String>> findAdminCampusList(String userId){
        String  sql = "select a2a.campus_id AS agencyId,name AS agencyName from service.ad_campus2admin AS a2a left JOIN service.ad_campus AS aa on a2a.campus_id = aa.id " +
                "WHERE a2a.user_id="+userId;
        return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).list();
    }

    public List<Map<String,String>> getAllCampusInfo(){

        String sql = "select id as agencyId,name as agencyName from service.ad_campus where type_id <="+TYPE_SHCOOL+" and type_id>="+TYPE_AREA;
        List<Map<String,String>> campusList = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).list();
        return campusList;
    }

    /*--------------------------------------------------查询用户信息方法结束------------------------------------------------*/

    public Map<String, String> getRoleIdByUserId(String userId) {
        String sql = "select r.role_id as roleId,r.role_name as roleName from user_role r left join user_user2role u on r.role_id=u.role_id where u.user_id=" + userId;
        List<Map<String, String>> list = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).list();
        Map<String, String> returnMap = new HashMap<>();
        for (Map<String, String> map : list) {
            returnMap.put(map.get("roleId"), map.get("roleName"));
        }
        return returnMap;
    }

    public Map<String,String> getRoleByToken(String sysName){
        List<String> roleIdList = new ArrayList<>();
        String sql = "select permission_id as permissionId from service.user_sys2permission  where sys_name='" + sysName.trim() + "'";
        String communication = "select permission_id as permissionId from service.user_sys2permission  where sys_name='communication'";
        Map<String, Map<String, String>> sys2permission = (Map<String, Map<String, String>>) this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).uniqueResult();
        sys2permission.putAll((Map<String, Map<String, String>>) this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(communication).uniqueResult());
        if (sys2permission.isEmpty()) return new HashMap<>();
        String roleSql = "select r2p.role_id as roleId, group_concat(up.permission_id) as permissionId," +
                "group_concat(up.permission_name) as permissionName ,group_concat(up.permission_path) as permissionPath ," +
                "group_concat(up.p_id) as pId,group_concat(up.permission_desc) as permissionDesc  from service.user_role2permission as r2p," +
                "service.user_permission as up where r2p.permission_id = up.permission_id group by role_id";
        Map<String, Map<String, String>> role2permission = (Map<String, Map<String, String>>) this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(roleSql).uniqueResult();
        if (role2permission.isEmpty()) return new HashMap<>();
        Set<String> permission = sys2permission.keySet();
        for (Map.Entry<String, Map<String, String>> entry : role2permission.entrySet()) {
            String roleId = entry.getKey();
            Map<String, String> value = entry.getValue();
            String[] permissionIdArray = value.get("permissionId").split(",");
            for (String permissionId : permissionIdArray) {
                if (permission.contains(permissionId)) {
                    roleIdList.add(roleId);
                    break;
                }
            }
        }
        if (roleIdList.isEmpty()) return new HashMap<>();
        return this.findRole(roleIdList.toArray(new String[roleIdList.size()]));
    }

    /**
     * 查询所有角色
     *
     * @return 结果集
     */
    public List<Map<String, String>> findAllRole() {
        List<Map<String, String>> list = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery("SELECT role_id as roleId, role_name as roleName FROM service.user_role order by role_id").list();
        return list;
    }

    private Map<String, String> findRole(String... roleId) {
        StringBuilder sb = new StringBuilder();
        for (String _roleId : roleId) {
            sb.append("'" + _roleId + "',");
        }
        if (sb.length() == 0) return new HashMap<>();
        String where = sb.substring(0, sb.length() - 1);
        return (Map<String, String>) this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery("select role_id as roleId, role_name as roleName from serivce.user_role where role_id in(" + where + ")");
    }
}
