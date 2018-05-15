package com.honghe.recordweb.action.frontend.place;

import com.honghe.recordhibernate.entity.User;
import com.honghe.recordweb.action.BaseAction;
import com.honghe.recordweb.action.SessionManager;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;

/**
 * Created by zgh on 2018/4/19.
 */
@Controller
@Scope(value = "Prototype")
public class PlaceAction extends BaseAction {
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getLogOutUrl() {
        return logOutUrl;
    }

    public void setLogOutUrl(String logOutUrl) {
        this.logOutUrl = logOutUrl;
    }

    public String getLocalAddr() {
        return localAddr;
    }
    public void setLocalAddr(String localAddr) {

        this.localAddr = localAddr;
    }

    private String localAddr;
    private String userId;
    private String userName;
    private String userRealName;
    private String logOutUrl;

    public String placeManagement(){
        User user = SessionManager.get(ServletActionContext.getRequest().getSession(), SessionManager.Key.USER);
        HttpServletRequest request = ServletActionContext.getRequest();
        String referer = request.getHeader("Referer");
        String substring = referer.substring(referer.lastIndexOf("/"), referer.length());
        if(referer.contains("login.jsp") || substring.equals("/")){
            //http://localhost:8080/viewclass/Viewclass_getHostGroup.do
            referer = request.getScheme()+"://" + request.getServerName() + ":"+ request.getServerPort()+"/viewclass/Viewclass_getHostGroup.do";
        }
        localAddr = request.getServerName();
        logOutUrl = referer.replaceAll("/","%2F").replaceAll(":","%3A");
        userId = user.getUserId()+"";
        userName = user.getUserName();
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
        return "placemanagement";
    }

}
