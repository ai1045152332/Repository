package com.shop.action;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ActionContext;
import com.shop.bean.AdminBean;
import com.shop.service.AdminService;
import com.shop.util.Constants;
import com.shop.util.DateUtil;
import com.shop.util.StringUtil;
import com.shop.util.bean.PagingBean;
public class AdminAction {
	public HttpServletResponse response = ServletActionContext.getResponse();
	public HttpServletRequest request = ServletActionContext.getRequest();
	public HttpSession session = request.getSession();

	
	private AdminBean adminBean;
	
	@Autowired
	private AdminService adminService;
	
	public String update() {
		adminBean.setCreateDate(DateUtil.getNow());
		adminService.add(adminBean);
		return "update";
	}
	
	
	public String toAdd() {
		return "toAdd";
	}
	
	public void checkUsername() {
		String username = request.getParameter("username");
		AdminBean bean = adminService.getByUsername(username);
		System.out.println("username:"+username);
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
	
	public String toLogin() {
		return "toLogin";
	}
	
	public String login() {
		
		AdminBean bean = adminService.login(adminBean);
		if(bean!=null) {
			//成功,把用户信息放入 session
			session.setAttribute(Constants.SESSION_ADMIN, bean);
			return "login";
		} else {
			//失败
			return "loginFailure";
		}
	}
	
	//查询所有的数据
public String list() {
		
		int currentPage = StringUtil.toInt2(request.getParameter("currentPage"));
		int totalCount = adminService.getCount();
		int pageSize = 10;
		int totalPage = 0;
		if(totalCount % pageSize==0) {
			totalPage = totalCount/pageSize;
		} else {
			totalPage = totalCount/pageSize+1;
		}
		PagingBean pagingBean = new PagingBean(currentPage, totalCount, pageSize);
		pagingBean.setPrefixUrl(request.getContextPath()+"/admin/listAdminAction");
		pagingBean.setAnd(false);
		List<AdminBean> list = adminService.getByPage(currentPage*pageSize, pageSize);
		
		ActionContext.getContext().put("list", list);
		ActionContext.getContext().put("currentPage", currentPage);
		ActionContext.getContext().put("pageSize", pageSize);
		ActionContext.getContext().put("totalPage", totalPage);
		ActionContext.getContext().put("pagingBean", pagingBean);
		
		
		
		return "list";
	}
	
	public String toUpdate() {
		int id = StringUtil.toInt(request.getParameter("id"));
		if(id>0) {
			AdminBean bean = adminService.getById(id);
			//等价于request.setAttribute("bean",)
			ActionContext.getContext().put("bean", bean);
		}
		return "toUpdate";
	}
	
	public String del() {
		int id = StringUtil.toInt(request.getParameter("id"));
		if(id>0) {
			adminService.del(id);
		}
		return "del";
	}

	public AdminBean getAdminBean() {
		return adminBean;
	}

	public void setAdminBean(AdminBean adminBean) {
		this.adminBean = adminBean;
	}
}
