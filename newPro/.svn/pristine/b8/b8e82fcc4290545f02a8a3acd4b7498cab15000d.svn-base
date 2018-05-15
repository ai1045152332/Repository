package com.honghe.recordweb.service.project.user;

import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.dao.UserDao;
import com.honghe.recordhibernate.entity.User;
import com.honghe.recordweb.action.SessionManager;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by chby on 2014/10/29.
 */
@Service
public class UserBackService {

    private final static Logger logger = Logger.getLogger(UserBackService.class);

    @Resource
    private UserDao userDao;

    /**
     * 返回用户列表（Json格式）     *
     *
     * @return String json格式的字符串，异常为“”
     */
    @Transactional
    public List<User> userListService() {
        try {
            List list = null;
            list = userDao.getUserList();
            return list;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return null;
        }
    }

    /**
     * 根据page类方法，返回分页的分组数据（Json格式）
     *
     * @param page Page<User>
     * @return List<User>
     */
    @Transactional
    public void userListService(Page<User> page) {
        try {
            userDao.getUserList(page);
        } catch (Exception error) {
            logger.error("", error);
//            error.printStackTrace();
        }
    }

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    public List<Map> UserInfoService(int id) {
        List<Map> values = new ArrayList<Map>();
        try {
            List<Object[]> result = userDao.getUserById(id);
            for (Object[] obj : result) {
                Map<String, Object> map = new LinkedHashMap<String, Object>();
                map.put("user_id", obj[0]);
                map.put("user_name", obj[1]);
                map.put("user_pwd", obj[2]);
                map.put("school_name", obj[3]);
                map.put("user_desc", obj[4]);
                map.put("user_salt", obj[5]);
                List<Map> groupList = new ArrayList<Map>();
                List<Map> roleList = new ArrayList<Map>();
                List<Map> authList = new ArrayList<Map>();
                if (obj[6] != null && !obj[6].equals("")) {
                    String[] groupIds = obj[6].toString().split(",");
                    String[] groupNames = obj[7].toString().split(",");
                    for (int i = 0; i < groupIds.length; i++) {
                        Map<String, String> group = new HashMap<String, String>();
                        group.put("id", groupIds[i]);
                        group.put("name", groupNames[i]);
                        groupList.add(group);
                    }
                }
                if (obj[8] != null && !obj[8].equals("")) {
                    String[] roleIds = obj[8].toString().split(",");
                    String[] roleNames = obj[9].toString().split(",");
                    String[] authIds = obj[10].toString().split(",");
                    String[] authNames = obj[11].toString().split(",");
                    for (int i = 0; i < roleIds.length; i++) {
                        Map<String, String> role = new HashMap<String, String>();
                        role.put("id", roleIds[i]);
                        role.put("name", roleNames[i]);
                        roleList.add(role);
                    }
                    for (int i = 0; i < authIds.length; i++) {
                        Map<String, String> auth = new HashMap<String, String>();
                        auth.put("id", authIds[i]);
                        auth.put("name", authNames[i]);
                        authList.add(auth);
                    }
                }
                map.put("group_list", groupList);
                map.put("role_list", roleList);
                map.put("auth_list", authList);
                values.add(map);
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return values;
    }

    /**
     * todo 加注释
     * @param userId
     * @return
     */
    @Transactional
    public User getUserinfoByIdService(int userId) {
        try {
            User user = null;
            user = userDao.getUserinfoById(userId);
            return user;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return null;
        }
    }

    /**
     * 更新用户信息
     *
     * @param user User自定义用户类型
     * @return
     */
    @Transactional
    public boolean updateUserService(User user, String roleStr, String groupStr) {
        try {
            if (userDao.updateUser(user)) {
                User currentUser = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USERBACK);
                if (!user.getUserId().toString().equals("1") && !user.getUserId().toString().equals("2") && !user.getUserId().toString().equals(currentUser.getUserId().toString())) {
                    if (roleStr != null && !roleStr.equals("")) {
                        if (userRoleService(user.getUserId())) {
                            if (delUserRoleService(user.getUserId())) {
                                String[] roles = roleStr.split(",");
                                for (String role : roles) {
                                    int roleid = Integer.parseInt(role);
                                    addUserRoleService(user.getUserId(), roleid);
                                }
                            }
                        } else {
                            String[] roles = roleStr.split(",");
                            for (String role : roles) {
                                int roleid = Integer.parseInt(role);
                                addUserRoleService(user.getUserId(), roleid);
                            }
                        }
                    }
                }

                if (groupStr != null && !groupStr.equals("")) {
                    if (userGroupService(user.getUserId())) {
                        if (delUserGroupService(user.getUserId())) {
                            String[] groups = groupStr.split(",");
                            for (String group : groups) {
                                int groupid = Integer.parseInt(group);
                                addUserGroupService(user.getUserId(), groupid);
                            }
                        }
                    } else {
                        String[] groups = groupStr.split(",");
                        for (String group : groups) {
                            int groupid = Integer.parseInt(group);
                            addUserGroupService(user.getUserId(), groupid);
                        }
                    }
                } else {
                    if (userGroupService(user.getUserId())) {
                        delUserGroupService(user.getUserId());
                    }
                }
                return true;
            }

            return true;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return false;
        }
    }

    /**
     * 删除某用户的所有角色关系
     *
     * @param id int 用户id
     * @return boolean  true删除成功，false失败
     */
    @Transactional
    public boolean delUserRoleService(int id) {
        try {
            userDao.delUserRoleBySql(id);
            return true;
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
    }

    /**
     * 给用户分配一个角色
     *
     * @param userId int 用户id
     * @param roleId int 角色id
     * @return
     */
    @Transactional
    public boolean addUserRoleService(int userId, int roleId) {
        try {
            userDao.addUserToRole(userId, roleId);
            return true;
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
    }

    /**
     * 删除某用户的所有组关系
     *
     * @param id int 用户id
     * @return boolean  true删除成功，false失败
     */
    @Transactional
    public boolean delUserGroupService(int id) {
        try {
            userDao.delUserGroupBySql(id);
            return true;
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
    }

    /**
     * 给用户分配一个组
     *
     * @param userId  int 用户id
     * @param groupId int 角色id
     * @return
     */
    @Transactional
    public boolean addUserGroupService(int userId, int groupId) {
        try {
            userDao.addUserToGroup(userId, groupId);
            return true;
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
    }

    /**
     * 按姓名精确查找用户信息
     *
     * @param name String 用户名称
     * @return List<User>
     */
    @Transactional
    public User getUserService(String name) {
        try {
            User user = null;
            user = userDao.getUser(name);
            return user;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return null;
        }
    }

    /**
     * 保存用户的信息
     *
     * @param user User自定义用户类型
     * @return
     */
    @Transactional
    public boolean addUserService(User user, String roleStr, String groupStr) {
        try {
            if (userDao.saveUser(user)) {
                if (!roleStr.equals("")) {
                    String[] roles = roleStr.split(",");
                    for (String role : roles) {
                        int roleid = Integer.parseInt(role);
                        addUserRoleService(user.getUserId(), roleid);
                    }
                }
                if (!groupStr.equals("")) {
                    String[] groups = groupStr.split(",");
                    for (String group : groups) {
                        int groupid = Integer.parseInt(group);
                        addUserGroupService(user.getUserId(), groupid);
                    }
                }
                return true;
            }
            return false;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return false;
        }
    }

    /**
     * 判断是否给某id的用户分配组
     *
     * @param id int 用户id
     * @return boolean true 表示分配，false表示未分配
     */
    @Transactional
    public boolean userGroupService(int id) {
        try {
            return userDao.isUserGroupExsist(id);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return false;
        }
    }

    /**
     * 判断某一id的用户是否被分配角色
     *
     * @param id int 用户id
     * @return boolean true 表示分配，false表示未分配
     */
    @Transactional
    public boolean userRoleService(int id) {
        try {
            return userDao.isUserRoleExsist(id);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return false;
        }
    }

    /**
     * 删除用户信息
     *
     * @param user User
     * @return
     */
    @Transactional
    public boolean delUserService(User user) {
        try {
            int id = user.getUserId();
            if (userRoleService(id)) {
                if (delUserRoleService(id) && delUserGroupService(id)) {
                    if (userDao.delUser(user)) {
                        return true;
                    } else {
                        return false;
                    }
                }
            } else {
                if (userDao.delUser(user)) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        } catch (Exception e) {
            logger.error("", e);
            return false;
        }
    }
}
