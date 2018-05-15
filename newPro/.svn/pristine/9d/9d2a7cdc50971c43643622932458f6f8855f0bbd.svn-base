package com.honghe.recordhibernate.dao;

import com.honghe.recordhibernate.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Moon on 2014/8/15.
 */

public interface UserDao {


    public User getUser(String name) throws Exception;

    public List<User> getUserByName(String name)  throws Exception;

    public HashMap<String,Integer> getUserRoles(User user) throws Exception;

    public HashMap<String,Integer> getUserAuthoritys(User user) throws Exception;
    //public HashMap<String,String> getAuthoritys(User user) throws Exception;

    public HashMap<String,String> getHostgroups(User user) throws Exception;

    public List<User> getUserList() throws Exception;

    public void getUserList(final Page<User> page) throws Exception;

    public void getUserListFrontendByPage(Page page) throws Exception;

    public List<Object[]> getUserById(final int id ) throws Exception;

    public List<Object[]> getUserGroupById(final int id ) throws Exception;

    public boolean saveUser(User user) throws Exception;

    public boolean updateUser(User user)  throws Exception;

    public void delUser(int id)  throws Exception;

    public boolean delUser(User user)  throws Exception;

    public void delUserRoleBySql(final int id) throws Exception;

    public boolean isUserRoleExsist(final int id) throws Exception;

    public void addUserToRole(final int userId, final int roleId) throws Exception;

    public void delUserGroupBySql(final int id) throws Exception;

    public boolean isUserGroupExsist(final int id) throws Exception;

    public void addUserToGroup(final int userId, final int groupId) throws Exception;

    public User getUserinfoById(int userId)  throws Exception;

    public List<User> getFrontUserByName(String name)  throws Exception;

    public List<Object[]> getUserByNamePage(String name,Page page) throws Exception;

    public List<Object[]> getUserListFrontByPage(Page page) throws Exception;

    public boolean isSchoolAdminExists() throws Exception;

    public int getRoleByUserId(int uid) throws Exception;

    public void addUserToRes(final int userId) throws Exception;

    public Map<String,String> login(String name,String userPwd) throws Exception;

    public int resetPwd(final String userId, String oldPassword, String password) throws Exception;

    public Map<String, String> find(int userId);

    public List<Map<String, String>> findByIds(String[] userId);

    public Map<String, String> findByName(String name);

    public List<Map<String,String>> findAdminCampusList(String userId);

    public List<Map<String,String>> getAllCampusInfo();

    public Map<String, String> getRoleIdByUserId(String userId);

    public Map<String,String> getRoleByToken(String sysName);

    public List<Map<String, String>> findAllRole();
}
