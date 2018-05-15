package com.honghe.recordweb.service.frontend.user;

import com.honghe.dao.JdbcTemplateUtil;
import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.dao.UserDao;
import com.honghe.recordhibernate.entity.User;
import com.honghe.recordweb.action.SessionManager;
import com.honghe.recordweb.util.CommonName;
import com.honghe.recordweb.util.ConfigureUtil;
import com.honghe.recordweb.util.HttpServiceClientFactory;
import com.honghe.recordweb.util.UserServiceFactory;
import com.honghe.service.client.Result;
import com.honghe.user.sdk.UserServiceClient;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by wj on 2014-10-08.
 */
@Service
public class UserService {

    private final static Logger logger = Logger.getLogger(UserService.class);

    @Resource
    private UserDao userDao;//Sping来管理对象

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
            logger.error("返回用户列表（Json格式）异常", e);
            return null;
        }
    }

    /**
     * 根据page类方法，返回分页的分组数据
     *
     * @param page Page<User>
     * @return List<User>
     */
    @Transactional
    public void userListService(Page<User> page) {
        try {
            userDao.getUserListFrontendByPage(page);
        } catch (Exception error) {
//            error.printStackTrace();
            logger.error("返回分页的分组数据异常", error);
        }
    }

    /**
     * 根据page类方法，返回分页的分组数据
     *
     * @param currentPage int,pageSize int
     * @return Map<String,Object>
     */
    @Transactional
    public Map<String, Object> userListService(int currentPage, int pageSize, String type) {
        Map<String, Object> userList = new LinkedHashMap<String, Object>();
        UserServiceClient userServiceClient = UserServiceFactory.getUserServiceClient();
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(currentPage));
        params.put("pageSize", String.valueOf(pageSize));
//        if (ConfigureUtil.isOnlyHhrec()) {
//            params.put("token", CommonName.DEVICE_TYPE_RECOURD);
//        } else if (ConfigureUtil.isOnlyHttc()) {
//            params.put("token", CommonName.DEVICE_TYPE_SCREEN);
//        } else if (ConfigureUtil.isOnlyHtpr()) {
//            params.put("token", CommonName.DEVICE_TYPE_PROJECTOR);
//        } else if (ConfigureUtil.isOnlyHhtwb()) {
//            params.put("token",CommonName.DEVICE_TYPE_WHITEBOARD);
//        } else {
//            params.put("token", type);
//        }
        Result result = userServiceClient.execute("user", UserServiceClient.Method.User_Search_ByPage, params, 30000);
        Map values = ((Map) result.getValue());
        userList = new HashMap<>();
        if (result.getCode() == 0 && !values.isEmpty()) {
            userList.put("pageCount", values.get("totalItems").toString());
            List items = (List) values.get("items");
            List<Object[]> list = new ArrayList<>();
            for (int i = 0; i < items.size(); i++) {
                Map _value = (Map) items.get(i);
                list.add(new Object[]{_value.get("userId"), _value.get("userName"), "", _value.get("roleId"), _value.get("roleName")});
            }
            userList.put("userlist", list);
        }
//        Map<String, Object> map = new LinkedHashMap<String, Object>();
//        try {
//
//            Page<List<Object[]>> page = new Page<List<Object[]>>(currentPage, pageSize);
//            List<Object[]> result = userDao.getUserListFrontByPage(page);
//            map.put("userlist", result);
//            map.put("pageCount", String.valueOf(page.getTotalPageSize()));
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
        return userList;

    }

    /**
     * 按名称模糊查询用户信息
     *
     * @param name String 用户名称
     * @return List<User>
     */
    @Transactional
    public List<User> getUserByNameService(String name) {
        try {
            List list = null;
            list = userDao.getFrontUserByName(name);
            return list;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("按名称模糊查询用户信息异常", e);
            return null;
        }
    }

    /**
     * 按名称模糊查询用户信息
     *
     * @param name String 用户名称
     * @return List<User>
     */
    @Transactional
    public Map<String, Object> getUserByNamePage(String name, int currentPage, int pageSize) {
        //List<Map> values = new ArrayList<Map>();
        UserServiceClient userServiceClient = UserServiceFactory.getUserServiceClient();
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(currentPage));
        params.put("pageSize", String.valueOf(pageSize));
        //params.put("token", "hhrec");
        params.put("userName", name);
        Result result = userServiceClient.execute("user", UserServiceClient.Method.User_Search_ByPage, params, 30000);
        Map values = ((Map) result.getValue());
        Map<String, Object> userList = new LinkedHashMap<String, Object>();
        userList = new HashMap<>();
        if (result.getCode() == 0 && !values.isEmpty()) {
            userList.put("pageCount", values.get("totalItems").toString());
            List items = (List) values.get("items");
            List<Object[]> list = new ArrayList<>();
            for (int i = 0; i < items.size(); i++) {
                Map _value = (Map) items.get(i);
                list.add(new Object[]{_value.get("userId"), _value.get("userName"), "", _value.get("roleId"), _value.get("roleName")});
            }
            userList.put("userlist", list);
        }
       /* Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {

            Page<List<Object[]>> page = new Page<List<Object[]>>(currentPage, pageSize);
            //hostgroupDao.getHostgroupList(page);
            List<Object[]> result = userDao.getUserByNamePage(name, page);

            map.put("userlist", result);

            map.put("pageCount", String.valueOf(page.getTotalPageSize()));

            //values.add(map);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        // return JSONArray.fromObject(values).toString();
        return userList;

    }

    /**
     * 按姓名精确查找用户信息
     *
     * @param name String 用户名称
     * @return List<User>
     */
    @Transactional
    public Boolean getUserService(String name) {
        UserServiceClient userServiceClient = UserServiceFactory.getUserServiceClient();
        Map<String, String> params = new HashMap<>();
        //params.put("token", "hhrec");
        params.put("userName", name);
        Result result = userServiceClient.execute("user", UserServiceClient.Method.User_IsExist, params, 30000);

        if (result.getCode() == 0) {
            if (result.getValue().equals("true")) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
//        try
//        {
//            User user = null;
//            user = userDao.getUser(name);
//            return user;
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//            return null;
//        }
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
            logger.error("判断某一id的用户是否被分配角色异常 id=" + id, e);
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
            logger.error("删除某用户的所有角色关系异常 id=" + id, e);
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
            logger.error("给用户分配一个角色异常", e);
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
            logger.error("判断是否给某id的用户分配组异常 id=" + id, e);
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
            logger.error("删除某用户的所有组关系异常 id=" + id, e);
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
            logger.error("给用户分配一个组异常", e);
            return false;
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
            UserServiceClient userServiceClient = UserServiceFactory.getUserServiceClient();
            Map<String, String> params = new HashMap<>();
            params.put("userId", id + "");
            Result result = userServiceClient.execute("user", UserServiceClient.Method.User_Search, params, 30000);
            Map userValue = (Map) result.getValue();
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            if (result.getCode() == 0 && !userValue.isEmpty()) {
                map.put("user_id", userValue.get("userId"));
                map.put("user_name", userValue.get("userName"));
                //            map.put("user_pwd", "");
                //            map.put("school_name","");
                //            map.put("user_desc", "");
                //            map.put("user_salt","");
                List<Map> groupList = new ArrayList<Map>();
                List<Object[]> groups = userDao.getUserGroupById(id);
                if (groups != null && groups.size() > 0) {
                    for (Object[] group : groups) {
                        Map<String, String> groupMap = new HashMap<String, String>();
                        groupMap.put("id", group[0].toString());
                        groupMap.put("name", group[1].toString());
                        groupList.add(groupMap);
                    }
                }
                List<Map> roleList = new ArrayList<Map>();
                Result roleResult = userServiceClient.execute("user", UserServiceClient.Method.Role_Search, params, 30000);
                Map roles = (Map) roleResult.getValue();
                if (roleResult.getCode() == 0 && !roles.isEmpty()) {
                    Iterator it = roles.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry entry = (Map.Entry) it.next();
                        String roleid = entry.getKey().toString();
                        String rolename = entry.getValue().toString();

                        Map<String, String> roleMap = new HashMap<String, String>();
                        roleMap.put("id", roleid);
                        roleMap.put("name", rolename);
                        roleList.add(roleMap);
                    }
                }
                map.put("group_list", groupList);
                map.put("role_list", roleList);
                values.add(map);
            }
        } catch (Exception e) {
            logger.error("获取用户信息异常", e);
//            e.printStackTrace();
        }
        return values;
    }

    /**
     * 保存用户的信息
     *
     * @param user User自定义用户类型
     * @param flag 1表示app传参
     * @return 0新增用户成功 1用户名已存在 -1新增用户失败
     * Author xinye
     */
    @Transactional
    public int addUserService(User user, String roleStr, String groupStr, int flag) {
        try {
//            if(userDao.saveUser(user))
            UserServiceClient userServiceClient = UserServiceFactory.getUserServiceClient();
            Map<String, String> params = new HashMap<>();
            params.put("userName", user.getUserName());
            params.put("userRealName", user.getUserName());
            params.put("userPwd", user.getUserPwd());
            params.put("userType", "1");
            Result result = userServiceClient.execute("user", UserServiceClient.Method.User_Add, params, 30000);
            int userId = Integer.parseInt(result.getValue().toString());
            if (result.getCode() == 0 && userId!=-1) {
                userDao.addUserToRes(userId);
                params.clear();
                params.put("userId",userId+"");
                params.put("agencyId","1");
                HttpServiceClientFactory.getHttpServiceClient().execute("agency","agencyUserRelationAdd",params);
                if (!roleStr.equals("")) {
                    String roleId;
                    if (flag == 1) {
                        roleId = roleStr;
                    } else {
                        roleId = roleStr.substring(0, roleStr.length() - 1);
                    }
                    Map<String, String> params_role = new HashMap<>();
                    params_role.put("userId", userId + "");
                    params_role.put("roleId", roleId);
                    Result result_role = userServiceClient.execute("user", UserServiceClient.Method.Role_Allot, params_role, 30000);
                }
                if (!groupStr.equals("")) {
                    String[] groups = groupStr.split(",");
                    for (String group : groups) {
                        int groupid = Integer.parseInt(group);
                        addUserGroupService(userId, groupid);
                    }
                }
                return 0;
            }else if(result.getCode() == 0 && userId==-1){
                return 1;
            }
            return -1;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("保存用户的信息异常", e);
            return -1;
        }
    }
    /* cancel by xinye 用户服务端，判断用户是否存在，不能正常使用（sql用的like），重写此方法通过add的返回值判断是否重名
    @Transactional
    public boolean addUserService(User user, String roleStr, String groupStr, int flag) {
        try {
//            if(userDao.saveUser(user))
            UserServiceClient userServiceClient = UserServiceFactory.getUserServiceClient();
            Map<String, String> params = new HashMap<>();
            params.put("userName", user.getUserName());
            params.put("userPwd", user.getUserPwd());
            params.put("userType", "1");
            Result result = userServiceClient.execute("user", UserServiceClient.Method.User_Add, params, 30000);
            if (result.getCode() == 0) {
                int userId = Integer.parseInt(result.getValue().toString());
                if (!roleStr.equals("")) {
//                    String[] roles = roleStr.split(",");
//                    for(String role : roles) {
//                        int roleid = Integer.parseInt(role);
//                        addUserRoleService(user.getUserId(), roleid);
//                    }
                    String roleId;
                    if (flag == 1) {
                        roleId = roleStr;
                    } else {
                        roleId = roleStr.substring(0, roleStr.length() - 1);
                    }
                    Map<String, String> params_role = new HashMap<>();
                    params_role.put("userId", userId + "");
                    params_role.put("roleId", roleId);
                    Result result_role = userServiceClient.execute("user", UserServiceClient.Method.Role_Allot, params_role, 30000);
                }
                if (!groupStr.equals("")) {
                    String[] groups = groupStr.split(",");
                    for (String group : groups) {
                        int groupid = Integer.parseInt(group);
//                        addUserGroupService(user.getUserId(),groupid);
                        addUserGroupService(userId, groupid);
                    }
                }
                return true;
            }
            return false;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("保存用户的信息异常", e);
            return false;
        }
    }
    *cancel by xinye end
    */
    /**
     * 更新用户信息
     *
     * @param userId
     * @param roleStr
     * @param groupStr
     * @param flag     1为app传参
     * @return
     */
    @Transactional
    public boolean updateUserService(int userId, String roleStr, String groupStr, int flag) {
        try {
//            if(userDao.updateUser(user))
            UserServiceClient userServiceClient = UserServiceFactory.getUserServiceClient();
//            Map<String, String> params = new HashMap<>();
//            params.put("userId", user.getUserId()+"");
//            Result result = userServiceClient.execute(UserServiceClient.Method.User_Update, params);
//            if(result.getCode() == 0)
//            {
            if (!roleStr.equals("")) {
                //                    String[] roles = roleStr.split(",");
                //                    for(String role : roles) {
                //                        int roleid = Integer.parseInt(role);
                //                        addUserRoleService(user.getUserId(), roleid);
                //                    }
                String roleId;
                if (flag == 1) {
                    roleId = roleStr;
                } else {
                    roleId = roleStr.substring(0, roleStr.length() - 1);
                }
                Map<String, String> params_role = new HashMap<>();
                params_role.put("userId", userId + "");
                params_role.put("roleId", roleId);
                Result result_role = userServiceClient.execute("user", UserServiceClient.Method.Role_Allot, params_role, 30000);
            }
//                else
//                {
//                    if(userRoleService(user.getUserId())) {
//                        delUserRoleService(user.getUserId());
//                    }
//                }
            if (groupStr != null && !groupStr.equals("")) {
                if (userGroupService(userId)) {
                    if (delUserGroupService(userId)) {
                        String[] groups = groupStr.split(",");
                        for (String group : groups) {
                            int groupid = Integer.parseInt(group);
                            addUserGroupService(userId, groupid);
                        }
                    }
                } else {
                    String[] groups = groupStr.split(",");
                    for (String group : groups) {
                        int groupid = Integer.parseInt(group);
                        addUserGroupService(userId, groupid);
                    }
                }
            } else {
                if (userGroupService(userId)) {
                    delUserGroupService(userId);
                }
            }
            return true;
//            }

//            return true;
        } catch (Exception e) {
            logger.error("更新用户信息异常", e);
//            e.printStackTrace();
            return false;
        }
    }

    /**
     * 重置密码
     *
     * @param user User自定义用户类型
     * @return
     */
    @Transactional
    public boolean updateUserService(User user) {

        try {
            return userDao.updateUser(user);
        } catch (Exception e) {
            logger.error(" 重置密码", e);
            return false;
        }
    }

    /**
     * 删除用户信息
     *
     * @param userId
     * @return
     */
    @Transactional
    public boolean delUserService(int userId) {
        try {
            boolean flagGroup = true;
            if (userGroupService(userId)) {
                flagGroup = delUserGroupService(userId);
            }
            if (flagGroup) {
                UserServiceClient userServiceClient = UserServiceFactory.getUserServiceClient();
                Map<String, String> params = new HashMap<>();
                params.put("userId", userId + "");
                Result result_del = userServiceClient.execute("user", UserServiceClient.Method.User_Delete, params, 30000);
                boolean response = (boolean) result_del.getValue();
                if (result_del.getCode() == 0 && response) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        } catch (Exception e) {
            logger.error("删除用户信息异常　userId=" + userId, e);
            return false;
        }
    }

    /**
     * 删除多个用户信息
     *
     * @param ids String 用户id，逗号分隔
     * @return
     */
    @Transactional
    public boolean delUserListService(String ids) {
        try {
            int i = 0;
            String[] uids = ids.split(",");
            for (String uid : uids) {
                int id = Integer.parseInt(uid);
                if (delUserService(id)) {
                    i++;
                }
            }
            if (i < uids.length) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            logger.error("删除多个用户信息 ids=" + ids, e);
            return false;
        }
    }

    /**
     * 获取某用户的角色信息
     *
     * @param user User自定义用户类型
     * @return
     */
    @Transactional
    public HashMap<String, Integer> getUserRoles(User user) {
        try {
            HashMap<String, Integer> rolemap = new HashMap<String, Integer>();
            rolemap = userDao.getUserRoles(user);
            return rolemap;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("获取某用户的角色信息异常", e);
            return null;
        }
    }

    /**
     * 获取当前用户的角色Session
     *
     * @return
     */
    @Transactional
    public int getUserRole() {
        try {
            Map userroleMap = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.ROLE);
            if (userroleMap != null || userroleMap.size() > 0) {
                int minValue = Integer.MAX_VALUE;
                Set<String> keys = userroleMap.keySet();
                for (String key : keys) {
                    int keyValue = Integer.parseInt(key);
                    if (minValue > keyValue) {
                        minValue = keyValue;
                    }
                }
                return minValue;
            } else {
                return -1;
            }
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("获取当前用户的角色Session 异常", e);
            return -1;
        }
    }

    /**
     * 内部通过userServiceClient来获取user
     * by lyx
     *
     * @param userId
     * @return
     */
    @Transactional
    public User getUserinfoByIdService(int userId) {

        User user = new User();
        UserServiceClient userServiceClient = UserServiceFactory.getUserServiceClient();
        Map<String, String> params = new HashMap<>();
        params.put("userId", userId + "");
        Result result = userServiceClient.execute("user", UserServiceClient.Method.User_Search, params, 30000);
        Map values = ((Map) result.getValue());
        if (result.getCode() == 0 && values != null && !values.isEmpty()) {
            user = new User(values);
        }
        return user;

    }

    /**
     * 判断校园管理员/系统管理员是否存在
     * 只有大屏或录播系统时，校园管理员
     * 存在多个系统时，系统管理
     *
     * @return boolean true 表示分配，false表示未分配
     */
    @Transactional
    public boolean adminExistsService() {
        try {
            UserServiceClient userServiceClient = UserServiceFactory.getUserServiceClient();
            Map<String, String> params = new HashMap<>();

//            if (ConfigureUtil.isOnlyHhrec()) {
//                params.put("roleName", Role.Role_SystemManager.toString());
//                params.put("token", CommonName.DEVICE_TYPE_RECOURD);
//            } else if (ConfigureUtil.isOnlyHttc()) {
//                params.put("roleName", Role.Role_SystemManager.toString());
//                params.put("token", CommonName.DEVICE_TYPE_SCREEN);
//            }else if (ConfigureUtil.isOnlyHtpr()) {
//                params.put("roleName", Role.Role_SystemManager.toString());
//                params.put("token", CommonName.DEVICE_TYPE_PROJECTOR);
//            }else if (ConfigureUtil.isOnlyHhtwb()) {
//                params.put("roleName",Role.Role_SystemManager.toString());
//                params.put("token",CommonName.DEVICE_TYPE_WHITEBOARD);
//            } else {
//                params.put("roleName", Role.Role_SchoolManager.toString());
//                params.put("token", "dmanager");
//            }
            Result result = userServiceClient.execute("user", UserServiceClient.Method.Role_Search, params, 30000);
            if (result.getCode() == 0) {
                if (Integer.parseInt(result.getValue().toString()) == 0) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return true;
            }
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("判断校园管理员是否存在 异常", e);
            return false;
        }
    }

    /**
     * todo 加注释
     * @param userid
     * @return
     */
    public int getRoleByUserid(int userid) {
        try {
            UserServiceClient userServiceClient = UserServiceFactory.getUserServiceClient();
            Map<String, String> params = new HashMap<>();
            if (ConfigureUtil.isOnlyHhrec()) {
                params.put("token", CommonName.DEVICE_TYPE_RECOURD);
            } else if (ConfigureUtil.isOnlyHttc()) {
                params.put("token", CommonName.DEVICE_TYPE_SCREEN);
            } else if (ConfigureUtil.isOnlyHtpr()) {
                params.put("token", CommonName.DEVICE_TYPE_PROJECTOR);
            } else if (ConfigureUtil.isOnlyHhtwb()) {
                params.put("token",CommonName.DEVICE_TYPE_WHITEBOARD);
            } else {
                params.put("token", "dmanager");
            }
            params.put("userId", userid + "");
            Result result = userServiceClient.execute("user", UserServiceClient.Method.Role_Search, params, 30000);
            if (result.getCode() == 0) {
                Map<String, String> rolemap = (Map<String, String>) result.getValue();
            }
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return 0;
        }
        return 0;
    }

    /**
     * todo 加注释
     * update by zlj on 2018/04/12
     * @param userid
     * @return
     */
    public Map<String, String> getRoleMapByUserid(int userid) {
        try {
            String token;
            if (ConfigureUtil.isOnlyHhrec()) {
                token = CommonName.DEVICE_TYPE_RECOURD;
            } else if (ConfigureUtil.isOnlyHttc()) {
                token = CommonName.DEVICE_TYPE_SCREEN;
            } else if (ConfigureUtil.isOnlyHtpr()) {
                token =  CommonName.DEVICE_TYPE_PROJECTOR;
            } else if (ConfigureUtil.isOnlyHhtwb()) {
                token = CommonName.DEVICE_TYPE_WHITEBOARD;
            }else {
                token =  "dmanager";
            }

            Map result = (Map) roleSearch(String.valueOf(userid), token);
            if (result != null&&!result.isEmpty()) {
                return result;
            } else {
                return new HashMap<>();
            }
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return new HashMap<>();
        }
    }


//    public Map<String, String> getRoleMapByUserid(int userid) {
//        try {
//            //TODO--zgh
//            UserServiceClient userServiceClient = UserServiceFactory.getUserServiceClient();
//            Map<String, String> params = new HashMap<>();
//            if (ConfigureUtil.isOnlyHhrec()) {
//                params.put("token", CommonName.DEVICE_TYPE_RECOURD);
//            } else if (ConfigureUtil.isOnlyHttc()) {
//                params.put("token", CommonName.DEVICE_TYPE_SCREEN);
//            } else if (ConfigureUtil.isOnlyHtpr()) {
//                params.put("token", CommonName.DEVICE_TYPE_PROJECTOR);
//            } else if (ConfigureUtil.isOnlyHhtwb()) {
//                params.put("token",CommonName.DEVICE_TYPE_WHITEBOARD);
//            }else {
//                params.put("token", "dmanager");
//            }
//            params.put("userId", String.valueOf(userid));
//            Result result = userServiceClient.execute("user", UserServiceClient.Method.Role_Search, params, 30000);
//            if (result.getCode() == 0) {
//                Map<String, String> rolemap = (Map<String, String>) result.getValue();
//                return rolemap;
//            } else {
//                return new HashMap<>();
//            }
//        } catch (Exception e) {
////            e.printStackTrace();
//            logger.error("", e);
//            return new HashMap<>();
//        }
//    }

    /**
     * todo 加注释
     * @param userId
     * @return
     */
    public Map<String, String> getRoleByUid(String userId) {
        UserServiceClient userServiceClient = UserServiceFactory.getUserServiceClient();
        Map<String, String> params = new HashMap<>();
        Map<String, String> rolemap = new HashMap<>();
        params.put("userId", userId);
        Result result = userServiceClient.execute("user", UserServiceClient.Method.Role_Search, params, 30000);
        if (result.getCode() == 0) {
            rolemap = (Map<String, String>) result.getValue();
            if (rolemap != null && rolemap.size() > 0) {
                return rolemap;
            }
        }
        return rolemap;

    }

    /**
     * 判断用户是否是超级管理员，并返回对应用户的权值
     *
     * @param userId
     * @return int  0：管理员；userId：普通用户
     */
    public int getAuthorityValueByUserid(int userId) {
        int uid = userId;

        if (uid == 1) {
            return 0;
        }
        UserServiceClient userServiceClient = UserServiceFactory.getUserServiceClient();
        Map<String, String> params = new HashMap<>();
        params.put("userId", userId + "");
        Result result = userServiceClient.execute("user", UserServiceClient.Method.Role_Search, params, 30000);
        if (result.getCode() == 0) {
            Map<String, String> rolemap = (Map<String, String>) result.getValue();
            if (rolemap != null && rolemap.size() > 0) {
                if (userId == 1 || rolemap.containsValue(Role.Role_SchoolManager.toString())|| rolemap.containsValue(Role.Role_SystemManager.toString())) {
                    uid = 0;
                } else if (rolemap.containsValue(Role.Role_HhrecManager.toString())) {
                    uid = 0;
                } else if (rolemap.containsValue(Role.Role_HhtcManger.toString())) {
                    uid = 0;
                }else if (rolemap.containsValue(Role.Role_ProjectorManger.toString())) {
                    uid = 0;
                }else if (rolemap.containsValue(Role.Role_HhtwbManager.toString())){
                    uid = 0;
                }
            }
        }
        return uid;
    }

    /**
     * todo 加注释
     * @param userId
     * @param token
     * @param system
     * @return
     */
    public static Boolean authCheck(String userId, String token, String system) {
        try {
            UserServiceClient userServiceClient = UserServiceFactory.getUserServiceClient();
            Map<String, String> params = new HashMap<>();
            params.put("userId", userId);
//            params.put("token", system);
            if (ConfigureUtil.isOnlyHhrec()) {
                params.put("token", CommonName.DEVICE_TYPE_RECOURD);
            } else if (ConfigureUtil.isOnlyHttc()) {
                params.put("token", CommonName.DEVICE_TYPE_SCREEN);
            } else if (ConfigureUtil.isOnlyHtpr()) {
                params.put("token", CommonName.DEVICE_TYPE_PROJECTOR);
            } else if (ConfigureUtil.isOnlyHhtwb()){
                params.put("token",CommonName.DEVICE_TYPE_WHITEBOARD);
            }else {
                params.put("token", system);
            }
            params.put("res", token);
            Result result = userServiceClient.execute("user", UserServiceClient.Method.AuthCheck, params, 30000);
            if (result.getCode() == 0) {
                return (Boolean) result.getValue();
            }
            return false;
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("", e);
            return false;
        }
    }
    /**
     * 登录
     *
     */
    public Map<String,String> login(String loginName, String userName, String userPwd){
        Map<String, String> result = null;
        String name = "";
        if (loginName != null) {
            name = loginName;
        }
        if ("".equals(name) && userName != null) {
            name = userName;
        }
        if ("".equals(name) || userPwd == null || "".equals(userPwd)) {
           return null;
        }
        try {
            result = userDao.login(name, userPwd);
        }catch(Exception e){
            logger.error("登录异常", e);
        }
        return result;
    }
    /**
     * 用户修改密码
     */
    public int resetPwd(String userId, String oldPassword, String password){
        int result = -1;
        try {
            result = userDao.resetPwd(userId, oldPassword, password);
        }catch(Exception e){
            logger.error("用户修改密码异常", e);
        }
            return result;
    }

    /*-----------------------------------------------------------用户管理接口调用转为数据库查询-------------------------------------------------*/
    public Object userSearch(String userName,String userId){
        Object object = null;
        Map<String,String> resultMap = null;
        List<Map<String,String>> resultList = null;
        if (userId != null && !"".equals(userId)) {
            String[] userIdArray = userId.split(",");
            if (userIdArray.length == 1) {
                resultMap = userDao.find(Integer.parseInt(userIdArray[0]));
            } else {
                resultList = userDao.findByIds(userIdArray);
            }
        } else if (userName != null && !"".equals(userName)) {
            resultMap = userDao.findByName(userName);
        }
        if(resultMap!=null){
            object = this.putAdminCampusList(resultMap,userId);
        }else if(resultList!=null){
            List<Map<String,Object>> resList = new ArrayList<>();
            for(Map<String,String> map:resultList){
                resList.add(this.putAdminCampusList(map,userId));
            }
            object = resList;
        }
        return object;
    }

    /**
     * 用户角色查询
     * @param userId 用户id
     * @param token
     * @return
     */
    public Object roleSearch(String userId,String token) {
        Object re_value;
        if (userId != null) {
            re_value = userDao.getRoleIdByUserId(userId);
        }else if(token != null){
            re_value = userDao.getRoleByToken(token);
        }
        else {
            re_value = userDao.findAllRole();
        }
        return re_value;
    }


    private Map<String,Object> putAdminCampusList(Map<String,String> map,String userId){
        List<Map<String,String>> adminCampusList = Collections.EMPTY_LIST;
        Map<String,Object> res = new HashMap<>();
        Iterator<String> iterator = map.keySet().iterator();
        while(iterator.hasNext()){
            String key = iterator.next();
            res.put(key,map.get(key));
        }
        if("1".equals(map.get("userIsAdmin"))) {
            adminCampusList = userDao.findAdminCampusList(map.get("userId"));
            List campusId = new ArrayList();
            for (Map campusMap : adminCampusList){
                String id = campusMap.get("agencyId").toString();
                campusId.add(id);
            }
            if ("1".equals(userId)){
                adminCampusList = userDao.getAllCampusInfo();
            }
        }
        res.put("userAdminList", adminCampusList);
        return res;
    }
}
