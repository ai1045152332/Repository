package com.honghe.recordweb.interceptor;

import com.honghe.recordhibernate.entity.User;
import com.honghe.recordweb.action.CommonManager;
import com.honghe.recordweb.action.SessionManager;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 登陆拦截器，如果已经登陆，进入目标action.
 * Created by wzz on 2014-09-23.
 */
public class BackendSessionCheckInterceptor extends
        AbstractInterceptor

{

    private final static Logger logger = Logger.getLogger(BackendSessionCheckInterceptor.class);

    /**
     * todo 加注释
     * @param invocation
     * @return
     * @throws Exception
     */
    public String intercept(ActionInvocation invocation) throws Exception {

        logger.info("进入后台拦截器");
        User userback = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USERBACK);
        Map userauthMapBack = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.AuthorityBACK);
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();

        if (request.getServletPath().indexOf("/userback/Userback_login.do") != -1) {
            //用户已经登录，进入默认页
            if (userback != null) {
                return "loginBackSuccess";
            } else {
                return invocation.invoke();
            }
        } else {
            if (userback != null) {
                if (request.getServletPath().indexOf("/userback/Userback_logout.do") != -1) {
                    return invocation.invoke();
                }
                Map globalRoleMap = CommonManager.getActionMap();
                String actionName = invocation.getProxy().getActionName();
                String authorStr = (String) globalRoleMap.get(actionName);
                //切分，当初对于不同权限访问同一action配置空格隔开的字符串
                if (authorStr != null && userauthMapBack != null) {
                    String[] authorList = authorStr.split(" ");
                    for (int i = 0; i < authorList.length; i++) {
                        if (userauthMapBack.containsKey(authorList[i])) {
                            return invocation.invoke();
                        }
                    }
                }
                //return "authorityDeny";
                return Action.ERROR;
            } else {
                String type = request.getHeader("X-Requested-With");
                if ("XMLHttpRequest".equalsIgnoreCase(type)) {// AJAX REQUEST PROCESS

                    response.setHeader("sessionstatus", "timeout");

                    return null;

                } else {// NORMAL REQUEST PROCESS

                    return Action.LOGIN;

                }
            }
        }
    }
}
