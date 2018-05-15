package com.honghe.recordweb.filter;
import com.honghe.recordhibernate.entity.User;
import com.honghe.recordweb.action.CookieManager;
import com.honghe.recordweb.action.SessionManager;
import com.honghe.recordweb.service.frontend.liveplan.LiveplanTimerService;
import com.honghe.recordweb.service.frontend.policy.PolicyTimerService;
import com.honghe.recordweb.service.frontend.programme.ProgrammeTimerService;
import com.honghe.recordweb.service.frontend.ringbell.RingBellTimerService;
import com.honghe.recordweb.service.frontend.signalplan.SignalplanTimerService;
import com.honghe.recordweb.service.frontend.timeplan.TimeplanTimerService;
import com.honghe.recordweb.service.frontend.touchscreen.TouchscreenTimerService;
import com.honghe.recordweb.service.frontend.user.CookieKeeplogin;
import com.honghe.recordweb.service.frontend.user.LoginPathManager;
import com.honghe.recordweb.util.CommonName;
import com.honghe.recordweb.util.ConfigureUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
/**
 * *用于检测用户是否登陆的过滤器，如果未登录，则重定向到指的登录页面.
 * Created by wzz on 2014-09-23.
 */
@WebFilter(urlPatterns = {"*.jsp","*.mp4","*.MP4","*.jpg"})
public class SessionCheckFilter implements Filter {

    private final static Logger logger = Logger.getLogger(SessionCheckFilter.class);
    @Override
    public void destroy() {

    }
    /**
     * 验证cookie
     * @return
     */
    private void checkCookie(HttpServletRequest request)
    {
        String cookieValue = null;
        cookieValue= CookieManager.getCookieValue(request, CookieManager.Key.REMEMBERME);
        if (StringUtils.isNotBlank(cookieValue)) {
            CookieKeeplogin cookiekeep =  ContextLoaderListener.getCurrentWebApplicationContext()
                    .getBean(CookieKeeplogin.class);
            User user =   cookiekeep.getCookieKeeplogin(cookieValue);
            if(user != null){
                SessionManager.add(request.getSession(), SessionManager.Key.USER,user);
            }
        }
    }
    /**
     * 验证权限
     * @return
     */
    private boolean checkAuthority(HttpServletRequest request,Map userauthMap)
    {
        boolean flag = request.getHeader("Referer") != null;
        return flag;
    }
    /**
     * todo 加注释
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        logger.info("进入过滤器");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setDateHeader("Expires", 0);
        if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){
            //request.info("session为空，所发请求为ajax请求，页面跳转至："+request.getRequestURI());
            if(request.getRequestURI().contains("User_kick")){
                filterChain.doFilter(request, response);
            }
            response.setHeader("sessionstatus", "timeout");//在响应头设置session状态
            response.setStatus(911);//表示session timeout
        }
        if(request.getServletPath().startsWith("/pages/frontend/404.jsp")||request.getServletPath().startsWith("/pages/project/404.jsp"))
        {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        checkCookie(request);
        User user = SessionManager.get(request.getSession(), SessionManager.Key.USER);
        User userback = SessionManager.get(request.getSession(), SessionManager.Key.USERBACK);
        Map userauthMap = SessionManager.get(request.getSession(), SessionManager.Key.Authority);
        Map userauthMapBack = SessionManager.get(request.getSession(), SessionManager.Key.AuthorityBACK);
        //前台登陆页
        if(request.getServletPath().startsWith("/login.jsp")||request.getServletPath().equals("/"))
        {
            if(user==null)
            {
                filterChain.doFilter(servletRequest, servletResponse);
            }
            else
            {
                String path = LoginPathManager.getLoginPath(request);
                if (path.equals(LoginPathManager.Path.DMANAGER.toString()))
                {
                    if(ConfigureUtil.isOnlyHhrec()){
                        response.sendRedirect(request.getContextPath() + "/viewclass/Viewclass_getHostGroup.do");
                        return;
                    }else if(ConfigureUtil.isOnlyHtpr()){
                        response.sendRedirect(request.getContextPath() + "/htprojector/HTProjector_getHostGroup.do");
                        return;
                    }else if (ConfigureUtil.isOnlyHhtwb()){
                        response.sendRedirect(request.getContextPath()+"/hhtwhiteboard/HHTWhiteboard_getHostGroup.do");
                        return;
                    }
                    response.sendRedirect(request.getContextPath() + "/dmanager/DManager_getHostGroup.do");
                    return;
                }
                if (path.equals(LoginPathManager.Path.TIMEPLAN.toString())) {
                    response.sendRedirect(request.getContextPath() + "/timeplan/Timeplan_plan.do");
                    return;
                }
                if(path.equals(LoginPathManager.Path.DEVICE.toString())){
                    response.sendRedirect(request.getContextPath() + "/hostgroup/Hostgroup_management.do");
                    return;
                }
                if(path.equals(LoginPathManager.Path.RESOURCE.toString())){
                    response.sendRedirect(request.getContextPath() + "/resource/Resource_resourceList.do");
                    return;
                }
                if(path.equals(LoginPathManager.Path.USER.toString())){
                    response.sendRedirect(request.getContextPath() + "/user/User_userList.do");
                    return;
                }
                if(path.equals(LoginPathManager.Path.ROLE.toString())){
                    response.sendRedirect(request.getContextPath() + "/role/Role_roleList.do");
                    return;
                }
                if(path.equals(LoginPathManager.Path.VIEWCLASS.toString())){
                    response.sendRedirect(request.getContextPath() + "/viewclass/Viewclass_getHostGroup.do");
                    return;
                } if(path.equals(LoginPathManager.Path.VIEWCLASSDEVICE.toString())){
                    SessionManager.add(ServletActionContext.getRequest().getSession(), SessionManager.Key.DeviceType, CommonName.DEVICE_TYPE_PROJECTOR);
                    response.sendRedirect(request.getContextPath() + "/hostgroup/Hostgroup_management.do");
                return;
            }
                response.sendRedirect(request.getContextPath() + "/deny.jsp");
                return;
            }
        }
        //后台登陆页
        else if(request.getServletPath().startsWith("/pages/project/login.jsp")||request.getServletPath().equals("/pages/project"))
        {
            if(userback==null)
            {
                filterChain.doFilter(servletRequest, servletResponse);
            }
            else
            {
                response.sendRedirect(request.getContextPath() + "/pages/project/index.jsp");
            }
        }
        //前台非登陆页
        else if(request.getServletPath().startsWith("/pages/frontend/"))
        {
            if(user==null)
            {
//                response.sendRedirect(request.getContextPath() + "/login.jsp");
                response.sendRedirect(CommonName.LOGOUT_PATH);
            }
            else
            {
                if(checkAuthority(request,userauthMap))
                {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
                else if(request.getServletPath().equals("/pages/frontend/news/editor/index.jsp")){
                    filterChain.doFilter(servletRequest, servletResponse);
                }
                else
                {
                    response.sendRedirect(request.getContextPath() + "/pages/frontend/404.jsp");
                    return;
                }
            }
        }
        //后台非登陆页
        else if(request.getServletPath().startsWith("/pages/project/"))
        {
            if(userback==null)
            {
                response.sendRedirect(request.getContextPath() + "/pages/project/login.jsp");
            }
            else
            {
                if(checkAuthority(request,userauthMapBack))
                {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
                else
                {
                    response.sendRedirect(request.getContextPath() + "/pages/project/404.jsp");
                    return;
                }
            }
        }
        else if(request.getServletPath().endsWith(".MP4") || request.getServletPath().endsWith(".mp4") || request.getServletPath().endsWith(".jpg")){
            filterChain.doFilter(servletRequest, servletResponse);
           // return;
        }
        else {
            response.sendRedirect(request.getContextPath() + "/pages/frontend/404.jsp");
            return;
        }
    }

    /**
     * todo 加注释
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       final SignalplanTimerService signalplanTimerService =    ContextLoaderListener.getCurrentWebApplicationContext().getBean(SignalplanTimerService.class);
        final  RingBellTimerService ringBellTimerService =    ContextLoaderListener.getCurrentWebApplicationContext().getBean(RingBellTimerService.class);
        final PolicyTimerService policyTimerService =    ContextLoaderListener.getCurrentWebApplicationContext().getBean(PolicyTimerService.class);
        final ProgrammeTimerService  programmeTimerService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(ProgrammeTimerService.class);
        final TouchscreenTimerService touchscreenTimerService=ContextLoaderListener.getCurrentWebApplicationContext().getBean(TouchscreenTimerService.class);
        final TimeplanTimerService timeplanTimerService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(TimeplanTimerService.class);
        final LiveplanTimerService liveplanTimerService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(LiveplanTimerService.class);
      new Thread(new Runnable() {
          @Override
          public void run() {
//              System.out.println("11111111111111111111111");
              signalplanTimerService.init();
              ringBellTimerService.init();
              policyTimerService.init();
              programmeTimerService.init();
              touchscreenTimerService.init();
              timeplanTimerService.init();
              liveplanTimerService.init();
//              System.out.println("22222222222222222222222");
          }
      }).start();
    }
}