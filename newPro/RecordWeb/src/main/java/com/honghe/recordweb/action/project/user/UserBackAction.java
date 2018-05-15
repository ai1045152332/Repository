package com.honghe.recordweb.action.project.user;

import com.honghe.recordhibernate.dao.Page;
import com.honghe.recordhibernate.dao.UserDao;
import com.honghe.recordhibernate.entity.Hostgroup;
import com.honghe.recordhibernate.entity.Role;
import com.honghe.recordhibernate.entity.User;
import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.action.SessionManager;
import com.honghe.recordweb.service.frontend.hostgroup.HostgroupService;
import com.honghe.recordweb.service.frontend.user.SaltRandom;
import com.honghe.recordweb.service.frontend.user.UserService;
import com.honghe.recordweb.service.project.role.RoleService;
import com.honghe.recordweb.service.project.user.UserBackService;
import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Moon on 2014/8/5.
 */
@Controller
@Scope(value="prototype")
public class UserBackAction extends BaseAction
{
    private final static Logger logger = Logger.getLogger(UserBackAction.class);
    @Resource
    private UserDao userDao;
    @Resource
    private UserBackService userBackService;

    @Resource
    private RoleService roleService;
    @Resource
    private HostgroupService hostgroupService;

    public String getLogin_name()
    {
        return this.login_name;
    }
    public void setLogin_name(String name)
    {
        this.login_name = name;
    }
    private String login_name;

    public String getLogin_pass()
    {
        return this.login_pass;
    }
    public void setLogin_pass(String word)
    {
        this.login_pass = word;
    }
    private String login_pass;

    public boolean isLogin_rememberme() {
        return login_rememberme;
    }
    public void setLogin_rememberme(boolean login_rememberme) {
        this.login_rememberme = login_rememberme;
    }
    private boolean login_rememberme;

    public List getUserlist() {
        return userlist;
    }
    public void setUserlist(List userlist) {
        this.userlist = userlist;
    }
    private List userlist;//主机列表

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    private String username;//用户名称

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    private int userId;//用户ID

    public String getUserpwd() {
        return userpwd;
    }
    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }
    private String userpwd;

//    public String getUserschool() {
//        return userschool;
//    }
//    public void setUserschool(String userschool) {
//        this.userschool = userschool;
//    }
//    private String userschool;

    public String getUserdesc() {
        return userdesc;
    }
    public void setUserdesc(String userdesc) {
        this.userdesc = userdesc;
    }
    private String userdesc;

    public String getUserIdStr() {
        return userIdStr;
    }
    public void setUserIdStr(String userIdStr) {
        this.userIdStr = userIdStr;
    }
    private String userIdStr;//多个用户ID，逗号分隔

    public String getRoleIdStr() {
        return roleIdStr;
    }
    public void setRoleIdStr(String roleIdStr) {
        this.roleIdStr = roleIdStr;
    }
    private String roleIdStr;

    public String getHostgroupIdStr() {
        return hostgroupIdStr;
    }
    public void setHostgroupIdStr(String hostgroupIdStr) {
        this.hostgroupIdStr = hostgroupIdStr;
    }
    private String hostgroupIdStr;

    public List<Role> getRolelist() {
        return rolelist;
    }
    public void setRolelist(List<Role> rolelist) {
        this.rolelist = rolelist;
    }
    private List<Role> rolelist;

    public List<Hostgroup> getHostgrouplist() {
        return hostgrouplist;
    }
    public void setHostgrouplist(List<Hostgroup> hostgrouplist) {
        this.hostgrouplist = hostgrouplist;
    }
    private List<Hostgroup> hostgrouplist;

    public List<Map> getUserinfo() {
        return userinfo;
    }
    public void setUserinfo(List<Map> userinfo) {
        this.userinfo = userinfo;
    }
    private List<Map> userinfo;

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    private User user;

    public int getPageCount() {
        return pageCount;
    }
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    public int pageCount;

    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int currentPage;
    /**
     * 登录 *
     */
    public void login()
    {
        JSONObject json = new JSONObject();
        if(login_name==null || login_pass == null)
        {
            json.put("success", false);
            json.put("msg", "用户名或密码错误");
        }
        try
        {
            User user = userDao.getUser(login_name);
            if (user==null)
            {
                json.put("success", false);
                json.put("msg", "用户名或密码错误");
            }
            else
            {
                boolean flag =true;
                Iterator iterator =  user.getRoles().iterator();
                while (iterator.hasNext())
                {
                    Role role = (Role)iterator.next();
                    int roleId = role.getRoleId();
                    if(roleId<=2)
                    {
                        flag =false;
                        break;
                    }
                }
                if(flag)
                {
                    json.put("success", false);
                    json.put("msg", "用户名或密码错误");
                }
                else
                {
                    String salt = user.getUser_salt();
                    String pwd = user.getUserPwd();
                    if (pwd.equals(DigestUtils.md5Hex(login_pass + salt))) {
                        HttpServletRequest request = ServletActionContext.getRequest();
                        HttpServletResponse response = ServletActionContext.getResponse();
                        //在Session中保存UserManager对象
                        HashMap<String, Integer> RoleMap = new HashMap<String, Integer>(0);
                        RoleMap = userDao.getUserRoles(user);
                        HashMap<String, Integer> AuthMap = new HashMap<String, Integer>(0);
                        AuthMap = userDao.getUserAuthoritys(user);
                        SessionManager.add(request.getSession(), SessionManager.Key.USERBACK, user);
                        //SessionManager.add(request.getSession(), SessionManager.Key.ROLE,RoleMap);
                        SessionManager.add(request.getSession(), SessionManager.Key.AuthorityBACK, AuthMap);
                        json.put("success", true);
                        json.put("msg", "登录成功");
                    } else {
                        json.put("success", false);
                        json.put("msg", "用户名或密码错误");
                    }
                }
            }
        }
        catch(Exception e)
        {
            logger.error("登陆异常",e);
            json.put("success", false);
            json.put("msg", "用户名或密码错误");
        }
        writeJSON(json.toString());
    }
    /**
     * 退出登录 *
     */
    public String logout()
    {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        SessionManager.del(request.getSession(),SessionManager.Key.USERBACK);
        SessionManager.del(request.getSession(), SessionManager.Key.AuthorityBACK);
        return "logout";
    }
// 返回用户列表 *

    public String userList()
    {
        this.user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        if(this.currentPage==0 )
        {
            this.currentPage =1;
        }
        Page<User> page = new Page<User>(currentPage, 13);
        userBackService.userListService(page);
        this.pageCount = page.getTotalPageSize();
        this.userlist = page.getResult();
        return "userlistback";
    }
    //   * 添加、编辑用户 *
    //  * userId为 -1，表示添加，否则为编辑

    public String userDetaile()
    {
        this.hostgrouplist = hostgroupService.getHostgroupListAllService();
        this.rolelist = roleService.roleListConditionService();
        if(userId != -1)
        {
           // this.rolelist = roleService.roleListService();
            this.userinfo = (List<Map>)userBackService.UserInfoService(userId);
        }
//        else
//        {
//            this.rolelist = roleService.roleListConditionService();
//        }
        return "userdetail";
    }
    //     * 修改用户及角色、分组关系 *
//
    public void alterUser()
    {
        JSONObject json = new JSONObject();
        try {
            User user = (User) userBackService.getUserinfoByIdService(userId);
            String salt = user.getUser_salt();
            if (userpwd != null && !userpwd.equals("")) {
                String pwd = DigestUtils.md5Hex(userpwd + salt);
                user.setUserPwd(pwd);
            }
//            if (userschool != null) {
//                user.setSchoolName(userschool);
//            }
            if (userdesc != null) {
                user.setUserDesc(userdesc);
            }
            boolean result = userBackService.updateUserService(user, roleIdStr, hostgroupIdStr);
            if (result) {
                json.put("success", true);
                json.put("msg", "用户修改成功");
            } else {
                json.put("success", false);
                json.put("msg", "用户修改失败");
            }
            HttpServletRequest request = ServletActionContext.getRequest();
            User currentUser = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USERBACK);
            //修改自己的session
            if (userId == currentUser.getUserId()) {
                User user1 = (User) userBackService.getUserinfoByIdService(userId);
//                SessionManager.del(request.getSession(), SessionManager.Key.ROLE);
                SessionManager.del(request.getSession(), SessionManager.Key.AuthorityBACK);
//                HashMap<String, Integer> RoleMap = new HashMap<String, Integer>(0);
//                RoleMap = userDao.getUserRoles(user1);
                HashMap<String, Integer> AuthMap = new HashMap<String, Integer>(0);
                AuthMap = userDao.getUserAuthoritys(user1);
//                SessionManager.add(request.getSession(), SessionManager.Key.ROLE, RoleMap);
                SessionManager.add(request.getSession(), SessionManager.Key.AuthorityBACK, AuthMap);
            }
        }
        catch (Exception e)
        {
            logger.error("用户修改失败：",e);
            //e.printStackTrace();
            json.put("success", false);
            json.put("msg", "用户修改失败");
        }
        writeJSON(json.toString());
    }
    //     * 添加用户及角色、分组关系 *
//
    public void addUser()
    {
        JSONObject json = new JSONObject();
        try {
            User user = new User();
            user.setUserName(username);
            SaltRandom saltRandom = new SaltRandom();
            String salt = saltRandom.runVerifyCode(6);
            user.setUser_salt(salt);
            String pwd = DigestUtils.md5Hex(userpwd + salt);
            user.setUserPwd(pwd);
//            user.setSchoolName(userschool);
            user.setUserDesc(userdesc);
            User userExists = (User) userBackService.getUserService(username);
            if (userExists == null) {
                boolean result = userBackService.addUserService(user, roleIdStr, hostgroupIdStr);
                if (result) {
                    json.put("success", true);
                    json.put("msg", "用户添加成功");
                } else {
                    json.put("success", false);
                    json.put("msg", "用户添加失败");
                }
            } else {
                json.put("success", false);
                json.put("msg", "该用户名称已存在");
            }
        }
        catch (Exception e)
        {
            logger.error("用户添加失败：",e);
            json.put("success", false);
            json.put("msg", "用户添加失败");
        }
        writeJSON(json.toString());
    }

    // 删除用户及角色关系 *

    public void delUser()
    {
        JSONObject json = new JSONObject();
        try {
            User user = userBackService.getUserinfoByIdService(userId);
            if(user == null)
            {
                json.put("success", true);
                json.put("msg", "删除成功");
            }
            else {
                boolean result = userBackService.delUserService(user);
                if (result) {
                    json.put("success", true);
                    json.put("msg", "删除成功");
                } else {
                    json.put("success", false);
                    json.put("msg", "删除失败");
                }
            }
        }
        catch (Exception e)
        {
            logger.error("用户删除失败：",e);
            json.put("success", false);
            json.put("msg", "删除失败");
        }
        writeJSON(json.toString());
    }
}
