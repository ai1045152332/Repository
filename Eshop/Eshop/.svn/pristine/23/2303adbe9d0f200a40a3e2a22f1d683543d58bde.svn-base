package com.shop.action;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.shop.bean.UserBean;
import com.shop.service.UserService;
import com.shop.util.Constants;
import com.shop.util.DateUtil;
public class UserAction {
	public HttpServletResponse response = ServletActionContext.getResponse();
	public HttpServletRequest request = ServletActionContext.getRequest();
	public HttpSession session = request.getSession();
	private UserBean userBean;
	@Autowired
	private UserService userService;
	
	
	//登录页面
	public String toLogin() {
		return "toLogin";
	}
	//注册页面
	public String toRegister(){
		return "toRegister";
	}
	//登录
	public String login() {
		UserBean bean = userService.login(userBean);
		if(bean!=null) {		
			session.setAttribute(Constants.SESSION_ADMIN1, bean);
			return "login";
		} else {
			return "loginFailure";
		}
	}
	//注册
	public String register() {
		userBean.setCreateDate(DateUtil.getNow());
		userService.register(userBean);
		return "register";
	}
	//检测用户名合法性
	public void checkUsername() {
		String username = request.getParameter("username");
		UserBean bean = userService.getByUsername(username);
		boolean flag = true;
		if(bean!=null) {
			flag = false;
		}
		try {
			PrintWriter out = response.getWriter();
			out.print(flag);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public UserBean getUserBean() {
		return userBean;
	}
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
}