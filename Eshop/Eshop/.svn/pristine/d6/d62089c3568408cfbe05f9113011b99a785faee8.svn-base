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
import com.shop.bean.ProductPropertyBean;
import com.shop.service.ProductPropertyOptionService;
import com.shop.service.ProductPropertyService;
import com.shop.util.Constants;
import com.shop.util.DateUtil;
import com.shop.util.StringUtil;


public class ProductPropertyAction {
	public HttpServletResponse response = ServletActionContext.getResponse();
	public HttpServletRequest request = ServletActionContext.getRequest();
	public HttpSession session = request.getSession();

	
	private ProductPropertyBean productPropertyBean;
	
	@Autowired
	private ProductPropertyService productPropertyService;
	@Autowired
	private ProductPropertyOptionService productPropertyOptionService;
	
	public String update() {
		if(productPropertyBean.getId()>0) {
			productPropertyService.update(productPropertyBean);
		} else{
			AdminBean adminBean = (AdminBean) session.getAttribute(Constants.SESSION_ADMIN);
			productPropertyBean.setAdminBean(adminBean);
			productPropertyBean.setCreateDate(DateUtil.getNow());
			productPropertyService.add(productPropertyBean);
		}
		ActionContext.getContext().put("typeId", productPropertyBean.getProductTypeBean().getId());
		return "update";
	}
	
	
	public String toAdd() {
		int typeId = StringUtil.toInt2(request.getParameter("typeId"));
		ActionContext.getContext().put("typeId", typeId);
		
		return "toAdd";
	}
	
	
	//查询所有的数据
	public String list() {
		int typeId = StringUtil.toInt2(request.getParameter("typeId"));
		
		if(typeId>0) {
			List<ProductPropertyBean> list = productPropertyService.getAllByTypeId(typeId);
			ActionContext.getContext().put("list", list);
			ActionContext.getContext().put("typeId", typeId);
		}
		
		return "list";
	}
	
	public String toUpdate() {
		int id = StringUtil.toInt(request.getParameter("id"));
		if(id>0) {
			ProductPropertyBean bean = productPropertyService.getById(id);
			ActionContext.getContext().put("bean", bean);
		}
		return "toUpdate";
	}
	
	public void del2() {
		int id = StringUtil.toInt(request.getParameter("id"));
		if(id>0) {
			int flag = 1;
			//删除前判断是否有选项
			if(productPropertyOptionService.hasOption(id)) {
				flag = 2;
			} else {
				productPropertyService.del(id);
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
	}
	
	public String del() {
		
		int id = StringUtil.toInt(request.getParameter("id"));
		if(id>0) {
			ProductPropertyBean productPropertyBean = productPropertyService.getById(id);
			productPropertyService.del(id);
			ActionContext.getContext().put("typeId", productPropertyBean.getProductTypeBean().getId());
		}
		return "del";
	}

	public ProductPropertyBean getProductPropertyBean() {
		return productPropertyBean;
	}

	public void setProductPropertyBean(ProductPropertyBean productPropertyBean) {
		this.productPropertyBean = productPropertyBean;
	}
}
