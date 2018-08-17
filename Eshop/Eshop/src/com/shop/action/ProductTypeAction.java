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
import com.shop.bean.ProductTypeBean;
import com.shop.service.ProductPropertyService;
import com.shop.service.ProductTypeService;
import com.shop.util.Constants;
import com.shop.util.DateUtil;
import com.shop.util.StringUtil;
import com.shop.util.bean.PagingBean;


public class ProductTypeAction {
	public HttpServletResponse response = ServletActionContext.getResponse();
	public HttpServletRequest request = ServletActionContext.getRequest();
	public HttpSession session = request.getSession();

	
	private ProductTypeBean productTypeBean;
	
	@Autowired
	private ProductTypeService productTypeService;
	@Autowired
	private ProductPropertyService productPropertyService;
	
	public String update() {
		
		if(productTypeBean.getId()>0) {
			productTypeService.update(productTypeBean);
			
		} else{
			AdminBean adminBean = (AdminBean) session.getAttribute(Constants.SESSION_ADMIN);
			productTypeBean.setAdminBean(adminBean);
			productTypeBean.setCreateDate(DateUtil.getNow());
			productTypeService.add(productTypeBean);
		}
		return "update";
	}
	
	
	public String toAdd() {
		return "toAdd";
	}
	
	
	//查询所有的数据
	public String list() {
		
		int currentPage = StringUtil.toInt2(request.getParameter("currentPage"));
		int totalCount = productTypeService.getCount();
		int pageSize = 10;
		int totalPage = 0;
		if(totalCount % pageSize==0) {
			totalPage = totalCount/pageSize;
		} else {
			totalPage = totalCount/pageSize+1;
		}
		PagingBean pagingBean = new PagingBean(currentPage, totalCount, pageSize);
		pagingBean.setPrefixUrl(request.getContextPath()+"/admin/listProductTypeAction");
		pagingBean.setAnd(false);
		List<ProductTypeBean> list = productTypeService.getByPage(currentPage*pageSize, pageSize);
		
		ActionContext.getContext().put("list", list);
		ActionContext.getContext().put("currentPage", currentPage);
		ActionContext.getContext().put("pageSize", pageSize);
		ActionContext.getContext().put("totalPage", totalPage);
		ActionContext.getContext().put("pagingBean", pagingBean);
		
		
		
		return "list";
	}
	public String shopType() {
		
		int currentPage = StringUtil.toInt2(request.getParameter("currentPage"));
		int totalCount = productTypeService.getCount();
		int pageSize = 10;
		int totalPage = 0;
		if(totalCount % pageSize==0) {
			totalPage = totalCount/pageSize;
		} else {
			totalPage = totalCount/pageSize+1;
		}
		PagingBean pagingBean = new PagingBean(currentPage, totalCount, pageSize);
		pagingBean.setPrefixUrl(request.getContextPath()+"/admin/shopTypeProductTypeAction");
		pagingBean.setAnd(false);
		List<ProductTypeBean> list = productTypeService.getByPage(currentPage*pageSize, pageSize);
		
		ActionContext.getContext().put("list", list);
		ActionContext.getContext().put("currentPage", currentPage);
		ActionContext.getContext().put("pageSize", pageSize);
		ActionContext.getContext().put("totalPage", totalPage);
		ActionContext.getContext().put("pagingBean", pagingBean);
		return "shopType";
	}
	public String toUpdate() {
		int id = StringUtil.toInt(request.getParameter("id"));
		if(id>0) {
			ProductTypeBean bean = productTypeService.getById(id);
			ActionContext.getContext().put("bean", bean);
		}
		return "toUpdate";
	}
	
	public void del() {
		int id = StringUtil.toInt(request.getParameter("id"));
		if(id>0) {
			int flag = 1;
			//删除前判断是否有属性
			if(productPropertyService.hasProperty(id)) {
				flag = 2;
			} else {
				productTypeService.del(id);
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

	public ProductTypeBean getProductTypeBean() {
		return productTypeBean;
	}

	public void setProductTypeBean(ProductTypeBean productTypeBean) {
		this.productTypeBean = productTypeBean;
	}
}
