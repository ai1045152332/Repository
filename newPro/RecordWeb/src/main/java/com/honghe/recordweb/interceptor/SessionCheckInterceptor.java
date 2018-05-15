package com.honghe.recordweb.interceptor;

import com.honghe.recordhibernate.entity.User;
import com.honghe.recordweb.action.CookieManager;
import com.honghe.recordweb.action.SessionManager;
import com.honghe.recordweb.service.frontend.user.CookieKeeplogin;
import com.honghe.recordweb.service.frontend.user.LoginPathManager;
import com.honghe.recordweb.util.CommonName;
import com.honghe.recordweb.util.ConfigureUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * 登陆拦截器，如果已经登陆，进入目标action.
 * Created by wzz on 2014-09-23.
 */
public class SessionCheckInterceptor extends
        AbstractInterceptor {

    private final static Logger logger = Logger.getLogger(SessionCheckInterceptor.class);

    /**
     * 验证cookie
     *
     * @return
     */
    //private LoginPathManager loginPathManager;
    private void checkCookie(HttpServletRequest request) {
        String cookieValue = null;
        cookieValue = CookieManager.getCookieValue(request, CookieManager.Key.REMEMBERME);
        if (StringUtils.isNotBlank(cookieValue)) {
            CookieKeeplogin cookiekeep = ContextLoaderListener.getCurrentWebApplicationContext()
                    .getBean(CookieKeeplogin.class);
            User user = cookiekeep.getCookieKeeplogin(cookieValue);
            if (user != null) {
                HashMap<String, Integer> RoleMap = new HashMap<String, Integer>(0);
                RoleMap = cookiekeep.getRole(user);
                HashMap<String, Integer> AuthMap = new HashMap<String, Integer>(0);
                AuthMap = cookiekeep.getAuth(user);
                SessionManager.add(request.getSession(), SessionManager.Key.USER, user);
                SessionManager.add(request.getSession(), SessionManager.Key.ROLE, RoleMap);
                SessionManager.add(request.getSession(), SessionManager.Key.Authority, AuthMap);
            }
        }
    }

    /**
     * todo 加注释
     * @param invocation
     * @return
     * @throws Exception
     */
    public String intercept(ActionInvocation invocation) throws Exception {

        logger.info("进入拦截器");
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setDateHeader("Expires", 0);
        checkCookie(request);
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        //Map userauthMap = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.Authority);

        if (request.getServletPath().indexOf("/user/User_login.do") != -1) {
            //用户已经登录，进入默认页
            if (user != null) {
                String path = LoginPathManager.getLoginPath(request);
                if (path.equals(LoginPathManager.Path.DMANAGER.toString())) {
                    return "loginSuccessdmanager";
                }
                if (path.equals(LoginPathManager.Path.TIMEPLAN.toString())) {
                    return "loginSuccesstimeplan";
                }
                if (path.equals(LoginPathManager.Path.DEVICE.toString())) {
                    SessionManager.add(ServletActionContext.getRequest().getSession(), SessionManager.Key.DeviceType, CommonName.DEVICE_TYPE_SCREEN);
                    return "loginSuccessdevice";
                }
                if (path.equals(LoginPathManager.Path.RESOURCE.toString())) {
                    return "loginSuccessresource";
                }
                if (path.equals(LoginPathManager.Path.USER.toString())) {
                    return "loginSuccessuser";
                }
                if (path.equals(LoginPathManager.Path.ROLE.toString())) {
                    return "loginSuccessrole";
                }
                if (path.equals(LoginPathManager.Path.VIEWCLASS.toString())) {
                    return "loginSuccessviewclass";
                }
                if (path.equals(LoginPathManager.Path.VIEWCLASSDEVICE.toString())) {
                    SessionManager.add(ServletActionContext.getRequest().getSession(), SessionManager.Key.DeviceType, CommonName.DEVICE_TYPE_RECOURD);
                    return "loginSuccessviewclassvice";
                }
                if (path.equals(LoginPathManager.Path.HTPROBJECTOR.toString())) {
                    return "loginSuccesshtprobjector";
                }
                if (path.equals(LoginPathManager.Path.HTPROBJECTORDEVICE.toString())) {
                    SessionManager.add(ServletActionContext.getRequest().getSession(), SessionManager.Key.DeviceType, CommonName.DEVICE_TYPE_PROJECTOR);
                    return "loginSuccesshtprobjectorvice";
                }
                if (path.equals(LoginPathManager.Path.HHTWBOBJECTOR.toString())) {
                    return "loginSuccesshhtwbobjector";
                }
                if (path.equals(LoginPathManager.Path.HHTWBOBJECTORDEVICE.toString())) {
                    SessionManager.add(ServletActionContext.getRequest().getSession(), SessionManager.Key.DeviceType, CommonName.DEVICE_TYPE_WHITEBOARD);
                    return "loginSuccesshhtwbobjectorvice";
                }
                return "loginSuccessdefault";
            } else {
                return invocation.invoke();
            }
        } else {
            String hhtc = ServletActionContext.getRequest().getParameter("hhtc");
            if (hhtc != null && hhtc.equals("ctrldevice")) {
                invocation.invoke();
            }
            // 如果没有登陆，返回重新登陆
            if (user != null) {
                if (request.getServletPath().indexOf("/user/User_logout.do") != -1) {
                    return invocation.invoke();
                }
                String str = request.getQueryString();
                if (request.getServletPath().indexOf("/hostgroup/Hostgroup_management.do") != -1
                        || request.getServletPath().indexOf("/syslog/SysLog_deviceLogList.do") != -1
                        || request.getServletPath().indexOf("/user/User_userList.do") != -1) {
                    if (request.getQueryString() != null && !ConfigureUtil.isHhrec() && SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.DeviceType).equals(CommonName.DEVICE_TYPE_RECOURD)) {
                        response.sendRedirect(request.getContextPath() + "/pages/frontend/404.jsp");
                    }

                    if (request.getQueryString() != null && !ConfigureUtil.isHhtc() && SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.DeviceType).equals(CommonName.DEVICE_TYPE_SCREEN)) {
                        response.sendRedirect(request.getContextPath() + "/pages/frontend/404.jsp");
                    }

                    if (request.getQueryString() != null  && !ConfigureUtil.isHtpr() && SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.DeviceType).equals(CommonName.DEVICE_TYPE_PROJECTOR)) {
                        response.sendRedirect(request.getContextPath() + "/pages/frontend/404.jsp");
                    }

                    if (request.getQueryString() != null  && !ConfigureUtil.isHhtwb() && SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.DeviceType).equals(CommonName.DEVICE_TYPE_WHITEBOARD)) {
                        response.sendRedirect(request.getContextPath() + "/pages/frontend/404.jsp");
                    }
                }
                return invocation.invoke();
            } else {
                String type = request.getHeader("X-Requested-With");
                if ("XMLHttpRequest".equalsIgnoreCase(type)) {// AJAX REQUEST PROCESS
                    response.setHeader("frontend-sessionstatus", "frontend-timeout");
                    return null;
                } else {// NORMAL REQUEST PROCESS
//                    return Action.LOGIN;
                    response.sendRedirect(CommonName.LOGOUT_PATH);
                    return null;
                }
            }
        }
    }
}
