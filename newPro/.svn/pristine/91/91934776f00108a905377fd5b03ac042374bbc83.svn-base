package com.honghe.recordweb.action.frontend.user;

import com.honghe.recordhibernate.entity.Hostgroup;
import com.honghe.recordhibernate.entity.Role;
import com.honghe.recordhibernate.entity.User;
import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.action.CookieManager;
import com.honghe.recordweb.action.SessionManager;
import com.honghe.recordweb.service.frontend.hostgroup.HostgroupService;
import com.honghe.recordweb.service.frontend.syslog.SyslogService;
import com.honghe.recordweb.service.frontend.user.LoginPathManager;
import com.honghe.recordweb.service.frontend.user.UserService;
import com.honghe.recordweb.util.CommonName;
import com.honghe.recordweb.util.ConfigureUtil;
import com.honghe.recordweb.util.UserServiceFactory;
import com.honghe.service.client.Result;
import com.honghe.user.sdk.UserServiceClient;
import jodd.util.Base64;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;


/**
 * Created by Moon on 2014/8/5.
 */
@Controller
@Scope(value = "prototype")
public class UserAction extends BaseAction {
    private final static Logger logger = Logger.getLogger(UserAction.class);
    @Resource
    private UserService userService;
    @Resource
    private HostgroupService hostgroupService;
    @Resource
    private SyslogService syslogService;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    private String flag;
    private int type = 0;

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    private String userName;

    public String getLogin_pass() {
        return this.login_pass;
    }

    public void setLogin_pass(String word) {
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

    public String getOldpwd() {
        return oldpwd;
    }

    public void setOldpwd(String oldpwd) {
        this.oldpwd = oldpwd;
    }

    private String oldpwd;

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

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int pageSize;

    public Map<String, Object> getUserList() {
        return userList;
    }

    public void setUserList(Map<String, Object> userList) {
        this.userList = userList;
    }

    private Map<String, Object> userList;

    public String getRoleNameStr() {
        return roleNameStr;
    }

    public void setRoleNameStr(String roleNameStr) {
        this.roleNameStr = roleNameStr;
    }

    private String roleNameStr;

    /**
     * 登录 *
     */

    public void login() {
        JSONObject json = new JSONObject();
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        try {
            Map<String, String> userInfo = new HashMap<>();
            UserServiceClient userServiceClient = UserServiceFactory.getUserServiceClient();
            Map<String, String> params = new HashMap<>();
            //智慧校园登录
//            if((request.getParameter("logOut")!=null && !"".equals(request.getParameter("logOut")))
//                || (request.getParameter("amp;logOut")!=null && !"".equals(request.getParameter("amp;logOut")))
//                ) {
//                CommonName.LOGOUT_PATH = request.getParameter("logOut")==null
//                        ?request.getParameter("amp;logOut"):request.getParameter("logOut");
//                if (this.userName == null) {
//                    response.sendRedirect(CommonName.LOGOUT_PATH);
//                    return;
//                }
//                params.clear();
//                params.put("userName", userName);
//                userInfo = (Map<String, String>) userService.userSearch(userName,null);
////                Result result = userServiceClient.execute("user", UserServiceClient.Method.User_Search, params, 30000);
//                if (userInfo==null||userInfo.isEmpty())
//                {
//                    response.sendRedirect(CommonName.LOGOUT_PATH);
//                    return;
//                }
//            } else {
//                if (userName == null || login_pass == null) {
//                    json.put("success", false);
//                    json.put("msg", "用户名或密码错误");
//                }
//                params.clear();
                params.put("userName", userName);
                params.put("userPwd", login_pass);
                Map<String, String> login = userService.login(userName,"", login_pass); //add -zgh 2018-4-12
                //Result result = userServiceClient.execute("user", UserServiceClient.Method.User_Check, params, 30000);
                if (login == null || login.size() == 0 )
                {
                    json.put("success", false);
                    json.put("msg", "用户名或密码错误");
                }
                userInfo = login;
//            }
            if(!userInfo.isEmpty()) {
                user = new User();
                user.setUserId(Integer.parseInt(userInfo.get("userId")));
                user.setUserName(this.userName);
                user.setUserRealName(userInfo.get("userRealName"));
                params.clear();
                params.put("userId", user.getUserId().toString());
                params.put("res", "device:create");
                Result result = userServiceClient.execute("user", UserServiceClient.Method.AuthCheck, params, 30000);
                user.setUser_salt(result.getValue().toString());
                SessionManager.add(request.getSession(), SessionManager.Key.USER, user);
                if (app_source()) {
                    json.put("success", true);
                    List list = new ArrayList();
                    list.add(user.getUserId());
                    Map<String, String> roleName = (Map<String, String>) userService.roleSearch(user.getUserId().toString(),null);
                    String value = "";
                    for (String key : roleName.keySet()) {
                        value = roleName.get(key);
                    }
                    if (user.getUserName().equals("admin") || roleName.containsValue(com.honghe.recordweb.service.frontend.user.Role.Role_SchoolManager.toString())) {
                        value = com.honghe.recordweb.service.frontend.user.Role.Role_SchoolManager.toString();
                    }
                    list.add(user.getUserName());
                    list.add(value);
                    json.put("msg", list);
                } else {
                    if (login_rememberme == true) {
                        String value = userName + "," + Base64.encodeToString(login_pass);
                        String path = "/";
                        int life = 60 * 60 * 24 * 7;//设置cookie存活时间为一周
                    }
                    String pagePath = "/pages/frontend/404.jsp";
                    String path = LoginPathManager.getLoginPath(request);
                    if (path.equals(LoginPathManager.Path.DMANAGER.toString())) {
                        if (ConfigureUtil.isHhtc()) {//只要包含大屏
                            pagePath = "/dmanager/DManager_getHostGroup.do";
                        } else if (ConfigureUtil.isOnlyHhrec()) {
                            pagePath = "/viewclass/Viewclass_getHostGroup.do";
                        } else if (ConfigureUtil.isOnlyHtpr()) {//投影仪登录界面
                            pagePath = "/htprojector/HTProjector_getHostGroup.do";
                        } else if (ConfigureUtil.isOnlyHhtwb()) {
                            pagePath = "/hhtwhiteboard/HHTWhiteboard_getHostGroup.do";
                        } else {
                            pagePath = "/viewclass/Viewclass_getHostGroup.do";
                        }
                    } else if (path.equals(LoginPathManager.Path.TIMEPLAN.toString())) {
                        pagePath = "/timeplan/Timeplan_plan.do";
                    } else if (path.equals(LoginPathManager.Path.DEVICE.toString())) {
                        if (ConfigureUtil.isHhtc()) {
                            SessionManager.add(ServletActionContext.getRequest().getSession(), SessionManager.Key.DeviceType, CommonName.DEVICE_TYPE_SCREEN);
                            pagePath = "/hostgroup/Hostgroup_management.do";
                        }
                        if (ConfigureUtil.isOnlyHhrec()) {
                            SessionManager.add(ServletActionContext.getRequest().getSession(), SessionManager.Key.DeviceType, CommonName.DEVICE_TYPE_RECOURD);
                            pagePath = "/hostgroup/Hostgroup_management.do";
                        }
                        if (ConfigureUtil.isOnlyHtpr()) {
                            SessionManager.add(ServletActionContext.getRequest().getSession(), SessionManager.Key.DeviceType, CommonName.DEVICE_TYPE_PROJECTOR);
                            pagePath = "/hostgroup/Hostgroup_management.do";
                        }
                        if (ConfigureUtil.isOnlyHhtwb()) {
                            SessionManager.add(ServletActionContext.getRequest().getSession(), SessionManager.Key.DeviceType,CommonName.DEVICE_TYPE_WHITEBOARD);
                            pagePath = "/hostgroup/Hostgroup_management.do";
                        }
                    } else if (path.equals(LoginPathManager.Path.RESOURCE.toString())) {
                        pagePath = "/resource/Resource_resourceList.do";
                    } else if (path.equals(LoginPathManager.Path.USER.toString())) {
                        pagePath = "/user/User_userList.do";
                    } else if (path.equals(LoginPathManager.Path.ROLE.toString())) {
                        pagePath = "/role/Role_roleList.do";
                    } else if (path.equals(LoginPathManager.Path.VIEWCLASS.toString())) {
                        pagePath = "/viewclass/Viewclass_getHostGroup.do";
                    } else if (path.equals(LoginPathManager.Path.VIEWCLASSDEVICE.toString())) {
                        SessionManager.add(ServletActionContext.getRequest().getSession(), SessionManager.Key.DeviceType, CommonName.DEVICE_TYPE_RECOURD);
                        pagePath = "/hostgroup/Hostgroup_management.do";
                    }
                    //添加投影机对象
                    else if (path.equals(LoginPathManager.Path.HTPROBJECTOR.toString())) {
                        pagePath = "/htprojector/HTProjector_getHostGroup.do";
                    } else if (path.equals(LoginPathManager.Path.HTPROBJECTORDEVICE.toString())) {
                        SessionManager.add(ServletActionContext.getRequest().getSession(), SessionManager.Key.DeviceType, CommonName.DEVICE_TYPE_PROJECTOR);
                        pagePath = "/hostgroup/Hostgroup_management.do";
                    }//白板一体机
                    else if (path.equals(LoginPathManager.Path.HHTWBOBJECTOR.toString())) {
                        pagePath = "/hhtwhiteboard/HHTWhiteboard_getHostGroup.do";
                    } else if (path.equals(LoginPathManager.Path.HHTWBOBJECTORDEVICE.toString())) {
                        SessionManager.add(ServletActionContext.getRequest().getSession(), SessionManager.Key.DeviceType, CommonName.DEVICE_TYPE_WHITEBOARD);
                        pagePath = "/hostgroup/Hostgroup_management.do";
                    }
                    syslogService.addDeviceLog(request.getRemoteAddr(), "用户" + userName + "登录系统", "LOGIN");
                    if(CommonName.LOGOUT_PATH.equals(ServletActionContext.getRequest().getContextPath()+"/login.jsp")) {
                        json.put("success", true);
                        json.put("msg", pagePath);
                    }
                    else {
                        response.sendRedirect(request.getContextPath() + pagePath);
                        return;
                    }
                }
            }
            else
            {
                if(CommonName.LOGOUT_PATH.equals(ServletActionContext.getRequest().getContextPath()+"/login.jsp"))
                {
                    json.put("success", false);
                    json.put("msg", "用户名或密码错误");
                }
                else {
                    response.sendRedirect(request.getContextPath() + CommonName.LOGOUT_PATH);
                    return;
                }
            }
        } catch (Exception e) {
            logger.error("登陆失败", e);
            if(CommonName.LOGOUT_PATH.equals(ServletActionContext.getRequest().getContextPath()+"/login.jsp"))
            {
                json.put("success", false);
                json.put("msg", "用户名或密码错误");
            }
        }
        if(CommonName.LOGOUT_PATH.equals(ServletActionContext.getRequest().getContextPath()+"/login.jsp"))
        {
            writeJSON(json.toString());
        }
    }

    /**
     * 退出登录 *
     */
    public void logout() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        User user = SessionManager.get(request.getSession(), SessionManager.Key.USER);
        username = user.getUserName();
        //清除该用户session
        SessionManager.del(request.getSession(), SessionManager.Key.USER);
        SessionManager.del(request.getSession(), SessionManager.Key.ROLE);
        SessionManager.del(request.getSession(), SessionManager.Key.Authority);
        String path = "/";
        CookieManager.clearCookie(request, response, path, CookieManager.Key.REMEMBERME);
        CookieManager.clearCookie(request, response, path, CookieManager.Key.GROUPSELECTED);
        CookieManager.clearCookie(request, response, path, CookieManager.Key.SELECTAUTO);
        CookieManager.clearCookie(request, response, path, CookieManager.Key.SCREEN49);
        CookieManager.clearCookie(request, response, path, CookieManager.Key.VIEWCAMERA);
        syslogService.addDeviceLog("用户退出", "用户" + username + "退出系统", "SYSTEM");
        JSONObject json = new JSONObject();
        json.put("path",CommonName.LOGOUT_PATH);
        writeJSON(json.toString());
//        return "logout";
    }

    /**
     * app退出登录
     */
    public void applogout() {
        if (app_source()) {
            HttpServletRequest request = ServletActionContext.getRequest();
            HttpServletResponse response = ServletActionContext.getResponse();
            try {
                User user = SessionManager.get(request.getSession(), SessionManager.Key.USER);
                username = user.getUserName();
                //清除该用户session
                SessionManager.del(request.getSession(), SessionManager.Key.USER);
                SessionManager.del(request.getSession(), SessionManager.Key.ROLE);
                SessionManager.del(request.getSession(), SessionManager.Key.Authority);
                String path = "/";
                CookieManager.clearCookie(request, response, path, CookieManager.Key.REMEMBERME);
                CookieManager.clearCookie(request, response, path, CookieManager.Key.GROUPSELECTED);
                CookieManager.clearCookie(request, response, path, CookieManager.Key.SELECTAUTO);
                CookieManager.clearCookie(request, response, path, CookieManager.Key.SCREEN49);
                CookieManager.clearCookie(request, response, path, CookieManager.Key.VIEWCAMERA);
                syslogService.addDeviceLog("用户退出", "用户" + username + "退出系统", "SYSTEM");
            } catch (Exception e) {
                SessionManager.del(request.getSession(), SessionManager.Key.USER);
                SessionManager.del(request.getSession(), SessionManager.Key.ROLE);
                SessionManager.del(request.getSession(), SessionManager.Key.Authority);
                String path = "/";
                CookieManager.clearCookie(request, response, path, CookieManager.Key.REMEMBERME);
                CookieManager.clearCookie(request, response, path, CookieManager.Key.GROUPSELECTED);
                CookieManager.clearCookie(request, response, path, CookieManager.Key.SELECTAUTO);
                CookieManager.clearCookie(request, response, path, CookieManager.Key.SCREEN49);
                CookieManager.clearCookie(request, response, path, CookieManager.Key.VIEWCAMERA);
                JSONObject json = new JSONObject();
                json.put("success", true);
                writeJSON(json.toString());
                return;
            }
            JSONObject json = new JSONObject();
            json.put("success", true);
            writeJSON(json.toString());
        }
    }


    /**
     * 返回用户列表 *
     */
    private String userRealName;
    public String getUserRealName() {
        return userRealName;
    }
    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    private String localAddr;
    public String getLocalAddr() {
        return localAddr;
    }
    public void setLocalAddr(String localAddr) {
        this.localAddr = localAddr;
    }
    private String logOutUrl;
    public String getLogOutUrl() {
        return logOutUrl;
    }
    public void setLogOutUrl(String logOutUrl) {
        this.logOutUrl = logOutUrl;
    }

    public String userList() {
        if (!app_source()) {
            this.user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
            HttpServletRequest request = ServletActionContext.getRequest();
            String referer = request.getHeader("Referer");
            String substring = referer.substring(referer.lastIndexOf("/"), referer.length());
            if(referer.contains("login.jsp") || substring.equals("/")){
                //http://localhost:8080/viewclass/Viewclass_getHostGroup.do
                referer = request.getScheme()+"://" + request.getServerName() + ":"+ request.getServerPort()+"/viewclass/Viewclass_getHostGroup.do";
            }
            localAddr = request.getServerName();
            logOutUrl = referer.replaceAll("/","%2F").replaceAll(":","%3A");
            try
            {
                userRealName = user.getUserRealName();
                if(userRealName != null)
                {
                    userRealName = URLEncoder.encode(userRealName,"utf-8");
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            userId = user.getUserId();
            userName = user.getUserName();
//            User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
//            Integer userId = user.getUserId();
//            Map<String, String> roleName = userService.getRoleByUid(userId.toString());
//            if (user.getUserName().equals("admin") || roleName.containsValue(com.honghe.recordweb.service.frontend.user.Role.Role_SchoolManager.toString()) || roleName.containsValue(com.honghe.recordweb.service.frontend.user.Role.Role_SystemManager.toString())) {
//                if (this.currentPage == 0) {
//                    this.currentPage = 1;
//                }
//                pageSize = 10000;
//                String type = "dmanager";
//                this.userList = userService.userListService(currentPage, pageSize, type);
//                return "userlist";
//            } else {
//                return "authorityDeny";
//            }
            if (this.currentPage == 0) {
                this.currentPage = 1;
            }
            pageSize = 10000;
            String type = "dmanager";
            this.userList = userService.userListService(currentPage, pageSize, type);
            return "userlist";
        } else {
            return "authorityDeny";
        }
    }

    /**
     * app用户列表
     */
    public void appUserList() {
        if (app_source()) {
            //this.user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
            Integer userId = this.userId;
            Map<String, String> roleName = userService.getRoleByUid(userId.toString());
            if (userId == 1 || roleName.containsValue(com.honghe.recordweb.service.frontend.user.Role.Role_SchoolManager.toString()) || roleName.containsValue(com.honghe.recordweb.service.frontend.user.Role.Role_SystemManager.toString())) {
                if (this.currentPage == 0) {
                    this.currentPage = 1;
                }
                String type = "dmanager";
                pageSize = 10000;
                userList = userService.userListService(currentPage, pageSize, type);
            } else {
                userList = null;
            }
            Map<String, Object> hashmap = new HashMap<String, Object>();
            hashmap.put("userList", userList);
            writeJSON(JSONSerializer.toJSON(hashmap).toString());
        }
    }

    /**
     * 根据用户名查找用户信息 *
     */
    public String userSearch() {
        if (!app_source()) {
            pageSize = 15;
            if (this.currentPage == 0) {
                this.currentPage = 1;
            }
            this.userList = userService.getUserByNamePage(username, currentPage, pageSize);
            return "usersearch";
        } else {
            return "authorityDeny";
        }
    }

    /**
     * app查找用户
     */
    public void appUserSearch() {
        if (app_source()) {
            pageSize = 10000;
            if (this.currentPage == 0) {
                this.currentPage = 1;
            }
            this.userList = userService.getUserByNamePage(username, currentPage, pageSize);
            Map<String, Object> hashmap = new HashMap<String, Object>();
            hashmap.put("userList", userList);
            writeJSON(JSONSerializer.toJSON(hashmap).toString());
        }
    }

    /**
     * 删除用户及角色关系 *
     */
    public void delUser() {
        JSONObject json = new JSONObject();
        try {
            UserServiceClient userServiceClient = UserServiceFactory.getUserServiceClient();
            Map<String, String> params = new HashMap<>();
            params.put("userId", userId + "");
            Result result = userServiceClient.execute("user", UserServiceClient.Method.User_Search, params, 30000);
            Map values = ((Map) result.getValue());
            if (result.getCode() == 0 && values.isEmpty()) {
                json.put("success", true);
                json.put("msg", "删除成功");
            } else {
                boolean result_del = userService.delUserService(userId);
                if (result_del) {
                    json.put("success", true);
                    json.put("msg", "删除成功");
                } else {
                    json.put("success", false);
                    json.put("msg", "删除失败");
                }
            }
        } catch (Exception e) {
            logger.error("登陆失败", e);
            json.put("success", false);
            json.put("msg", "删除失败");
        }
        writeJSON(json.toString());
    }

    /**
     * 删除多个用户及角色关系 *
     */
    public void delUserList() {
        JSONObject json = new JSONObject();
        try {
            boolean result = userService.delUserListService(userIdStr);
            if (result) {
                json.put("success", true);
                json.put("msg", "删除成功");
            } else {
                json.put("success", false);
                json.put("msg", "删除失败");
            }
        } catch (Exception e) {
            logger.error("登陆失败", e);
            json.put("success", false);
            json.put("msg", "删除失败");
        }
        writeJSON(json.toString());
    }

    /**
     * 添加、编辑用户 *
     * userId为 -1，表示添加，否则为编辑
     */
    public String userDetaile() {
        UserServiceClient userServiceClient = UserServiceFactory.getUserServiceClient();
        Map<String, String> params = new HashMap<>();
        params.put("token",getDeviceToken());
        Result result = userServiceClient.execute("user", UserServiceClient.Method.Role_Search, params, 30000);
        Map value = (Map) result.getValue();
        if (result.getCode() == 0 && !value.isEmpty()) {
            List<Role> roleList = new ArrayList<>();
            Iterator it = value.entrySet().iterator();
            //todo 代码需要优化
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                String roleid = entry.getKey().toString();
                String rolename = entry.getValue().toString();
                Role role = new Role();
                role.setRoleId(Integer.parseInt(roleid));
                role.setRoleName(rolename);
                if (role.getRoleName().equals("大屏管理员") && !ConfigureUtil.isHhtc()) {
                    continue;
                }
                if ((role.getRoleName().equals("录播设备管理员") && !ConfigureUtil.isHhrec()) || (role.getRoleName().equals("巡课人员")) && !ConfigureUtil.isHhrec()) {
                    continue;
                }
                if (role.getRoleName().equals("投影机管理员") && !ConfigureUtil.isHtpr()) {
                    continue;
                }
                if (role.getRoleName().equals("白板管理员") && !ConfigureUtil.isHhtwb()|| (role.getRoleName().equals("白板设备控制人员")) && !ConfigureUtil.isHhtwb()){
                    continue;
                }
                
                roleList.add(role);
            }
            this.rolelist = roleList;
        }
        this.hostgrouplist = hostgroupService.getHostgroupListAllService();
        if (userId != -1) {
            this.userinfo = (List<Map>) userService.UserInfoService(userId);
        }
        return "userdetail";
    }

    /**
     * app获取角色列表 *
     */
    public void appGetUserGroup() {
        Integer userId = this.userId;
        if (userId != -1) {
            this.userinfo = (List<Map>) userService.UserInfoService(userId);
        }
        Map map = userinfo.get(0);

        JSONObject json = new JSONObject();
        json.put("group_list", map.get("group_list"));
        writeJSON(json.toString());
    }

    /**
     * app获取角色列表 *
     */
    public void appGetAllRole() {
        if (app_source()) {
            rolelist = new ArrayList<>();
            Integer userId = this.userId;
            Map<String, String> roleName = userService.getRoleByUid(userId.toString());
            if (userId == 1 || roleName.containsValue(com.honghe.recordweb.service.frontend.user.Role.Role_SchoolManager.toString()) || roleName.containsValue(com.honghe.recordweb.service.frontend.user.Role.Role_SystemManager.toString())) {
                UserServiceClient userServiceClient = UserServiceFactory.getUserServiceClient();
                Map<String, String> params = new HashMap<>();
                params.put("token",getDeviceToken());
                Result result = userServiceClient.execute("user", UserServiceClient.Method.Role_Search, params, 30000);
                Map value = (Map) result.getValue();
                if (result.getCode() == 0 && !value.isEmpty()) {
                    List<Role> roleList = new ArrayList<>();
                    Iterator it = value.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry entry = (Map.Entry) it.next();
                        String roleid = entry.getKey().toString();
                        String rolename = entry.getValue().toString();
                        Role role = new Role();
                        role.setRoleId(Integer.parseInt(roleid));
                        role.setRoleName(rolename);
                        if (role.getRoleName().equals("大屏管理员") && !ConfigureUtil.isHhtc()) {
                            continue;
                        }
                        if ((role.getRoleName().equals("录播设备管理员") && !ConfigureUtil.isHhrec()) || (role.getRoleName().equals("巡课人员")) && !ConfigureUtil.isHhrec()) {
                            continue;
                        }
                        if (role.getRoleName().equals("投影机管理员") && !ConfigureUtil.isHtpr()) {
                            continue;
                        }
                        roleList.add(role);
                    }
                    this.rolelist = roleList;
                }
            }
            JSONObject json = new JSONObject();
            json.put("rolelist", rolelist);
            writeJSON(json.toString());
        }
    }

    /**
     * 添加用户及角色、分组关系 *
     * Author xinye
     */
    public void addUser() {
        JSONObject json = new JSONObject();
        try {
            if (app_source()) {
                type = 1;
            }
            roleNameStr = URLDecoder.decode(roleNameStr, "UTF-8");
            if ((hasAdminRole() && !userService.adminExistsService()) || !hasAdminRole()) {
                User user = new User();
                user.setUserName(username);
                user.setUserPwd(userpwd);
                int result_add = userService.addUserService(user, roleIdStr, hostgroupIdStr, type);
                if(result_add==0){
                    json.put("success", true);
                    json.put("msg", "用户添加成功");
                }else if(result_add==1){
                    json.put("success", false);
                    json.put("msg", "该用户名称已存在");
                }else{
                    json.put("success", false);
                    json.put("msg", "用户添加失败");
                }
            } else {
                json.put("success", false);
                json.put("msg", roleNameStr + "已存在");
            }
        } catch (Exception e) {
            logger.error("登陆失败", e);
            //e.printStackTrace();
            json.put("success", false);
            json.put("msg", "用户添加失败");
        }
        writeJSON(json.toString());
    }
    /**
     * 修改用户及角色、分组关系 *
     */
    public void alterUser() {
        JSONObject json = new JSONObject();
        try {
            if (app_source()) {
                type = 1;
            }
            roleNameStr = URLDecoder.decode(roleNameStr, "UTF-8");
            if (!hasAdminRole()) {
                boolean result = userService.updateUserService(userId, roleIdStr, hostgroupIdStr, type);
                if (result) {
                    json.put("success", true);
                    json.put("msg", "用户修改成功");
                } else {
                    json.put("success", false);
                    json.put("msg", "用户修改失败");
                }
            } else if (hasAdminRole() && !userService.adminExistsService()) {
                boolean result = userService.updateUserService(userId, roleIdStr, hostgroupIdStr, type);
                if (result) {
                    json.put("success", true);
                    json.put("msg", "用户修改成功");
                } else {
                    json.put("success", false);
                    json.put("msg", "用户修改失败");
                }
            } else {
                json.put("success", false);
                json.put("msg", roleNameStr + "已存在");
            }
        } catch (Exception e) {
            logger.error("登陆失败", e);
            //e.printStackTrace();
            json.put("success", false);
            json.put("msg", "用户修改失败");
        }
        writeJSON(json.toString());
    }

    /**
     * 判断传回的roleNameStr中是否包含系统管理员/校园管理员的字符串
     *
     * @return
     */
    private boolean hasAdminRole() {
        boolean flag = false;
        if (ConfigureUtil.isOnlyHhrec() || ConfigureUtil.isOnlyHttc()) {
            if (roleNameStr.indexOf(com.honghe.recordweb.service.frontend.user.Role.Role_SystemManager.toString()) != -1) {
                flag = true;
            }
        } else {
            if (roleNameStr.indexOf(com.honghe.recordweb.service.frontend.user.Role.Role_SchoolManager.toString()) != -1) {
                flag = true;
            }
        }
        return flag;
    }


    /**
     * 当前用户修改密码 *
     */
    public void resetPwd() {
        JSONObject json = new JSONObject();
        UserServiceClient userServiceClient = UserServiceFactory.getUserServiceClient();
        Map<String, String> params = new HashMap<>();
        params.put("userId", String.valueOf(userId));
        params.put("userPwd", userpwd);
        params.put("userOldPwd", oldpwd);
        int result = userService.resetPwd(String.valueOf(userId),oldpwd,userpwd);
        //Result result = userServiceClient.execute("user", UserServiceClient.Method.User_Update, params, 30000);
        if (result == 0 ) {
            json.put("success", true);
            json.put("msg", "修改成功");
        } else {
            json.put("success", false);
            json.put("msg", "修改失败");
        }
        writeJSON(json.toString());
    }

    /**
     * 管理员重置密码 *
     */
    public void alterPwd() {
        JSONObject json = new JSONObject();
        UserServiceClient userServiceClient = UserServiceFactory.getUserServiceClient();
        Map<String, String> params = new HashMap<>();
        params.put("userId", String.valueOf(userId));
        params.put("userPwd", userpwd);
        Result result = userServiceClient.execute("user", UserServiceClient.Method.User_Update, params, 30000);
        if (result.getCode() == 0 && result.getValue().toString().equals("0")) {
            json.put("success", true);
            json.put("msg", "修改成功");
        } else {
            json.put("success", false);
            json.put("msg", "修改失败");
        }
        writeJSON(json.toString());
    }
    /**
     * 返回平台管理设备内容 *
     */
    public String getDeviceToken() {
        if (ConfigureUtil.isOnlyHhrec()) {
            return CommonName.DEVICE_TYPE_RECOURD;
        } else if (ConfigureUtil.isOnlyHttc()) {
            return CommonName.DEVICE_TYPE_SCREEN;
        } else if (ConfigureUtil.isOnlyHtpr()) {
            return CommonName.DEVICE_TYPE_PROJECTOR;
        } else if (ConfigureUtil.isOnlyHhtwb()) {
            return CommonName.DEVICE_TYPE_WHITEBOARD;
        } else {
            return "dmanager";
        }
    }
//    public static void main(String[] args) {
//        UserServiceClient userServiceClient = UserServiceFactory.getUserServiceClient();
//        Map<String, String> params = new HashMap<>();
////        params.put("userName", "admin");
////        params.put("userPwd", "admin");
//
//        params.put("page", "1");
//        params.put("pageSize", "100");
//        if(ConfigureUtil.isOnlyHhrec()){
//            params.put("token", CommonName.DEVICE_TYPE_RECOURD);
//        }else if(ConfigureUtil.isOnlyHttc()){
//            params.put("token", CommonName.DEVICE_TYPE_SCREEN);
//        }else{
//            params.put("token", "dmanager");
//        }
////        Result result = userServiceClient.execute("user", UserServiceClient.Method.User_Check, params, 30000);
//        Result result = userServiceClient.execute("user",UserServiceClient.Method.User_Search_ByPage, params,30000);
//        System.out.println(result);
//    }


}
