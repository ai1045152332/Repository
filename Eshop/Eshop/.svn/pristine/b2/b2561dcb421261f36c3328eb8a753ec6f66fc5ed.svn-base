package com.shop.action;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ActionContext;
import com.shop.bean.AdminBean;
import com.shop.bean.ProductPropertyOptionBean;
import com.shop.service.ProductPropertyOptionService;
import com.shop.util.Constants;
import com.shop.util.DateUtil;
import com.shop.util.StringUtil;
public class ProductPropertyOptionAction {
	public HttpServletResponse response = ServletActionContext.getResponse();
	public HttpServletRequest request = ServletActionContext.getRequest();
	public HttpSession session = request.getSession();

	
	private ProductPropertyOptionBean productPropertyOptionBean;
	
	@Autowired
	private ProductPropertyOptionService productPropertyOptionService;
	
	public String update() {
		int typeId = StringUtil.toInt2(request.getParameter("typeId"));
		if(productPropertyOptionBean.getId()>0) {
			productPropertyOptionService.update(productPropertyOptionBean);
		} else{
			AdminBean adminBean = (AdminBean) session.getAttribute(Constants.SESSION_ADMIN);
			productPropertyOptionBean.setAdminBean(adminBean);
			productPropertyOptionBean.setCreateDate(DateUtil.getNow());
			productPropertyOptionService.add(productPropertyOptionBean);
		}
		ActionContext.getContext().put("propertyId", productPropertyOptionBean.getProductPropertyBean().getId());
		ActionContext.getContext().put("typeId", typeId);
		return "update";
	}
	
	
	public String toAdd() {
		int typeId = StringUtil.toInt2(request.getParameter("typeId"));
		int propertyId = StringUtil.toInt2(request.getParameter("propertyId"));
		ActionContext.getContext().put("propertyId", propertyId);
		ActionContext.getContext().put("typeId", typeId);
		return "toAdd";
	}
	
	
	//查询所有的数据
	public String list() {
		
		int propertyId = StringUtil.toInt2(request.getParameter("propertyId"));
		int typeId = StringUtil.toInt2(request.getParameter("typeId"));
		List<ProductPropertyOptionBean> list = productPropertyOptionService.getByPropertyId(propertyId);
		
		ActionContext.getContext().put("list", list);
		ActionContext.getContext().put("propertyId", propertyId);
		ActionContext.getContext().put("typeId", typeId);
		return "list";
	}
	
	public String toUpdate() {
		int typeId = StringUtil.toInt2(request.getParameter("typeId"));
		int id = StringUtil.toInt(request.getParameter("id"));
		if(id>0) {
			ProductPropertyOptionBean bean = productPropertyOptionService.getById(id);
			ActionContext.getContext().put("bean", bean);
		}
		ActionContext.getContext().put("typeId", typeId);
		return "toUpdate";
	}
	
	public String del() {
		int typeId = StringUtil.toInt2(request.getParameter("typeId"));
		int id = StringUtil.toInt(request.getParameter("id"));
		if(id>0) {
			ProductPropertyOptionBean optionBean = productPropertyOptionService.getById(id);
			productPropertyOptionService.del(id);
			ActionContext.getContext().put("propertyId", optionBean.getProductPropertyBean().getId());
		}
		ActionContext.getContext().put("typeId", typeId);
		return "del";
	}

	public ProductPropertyOptionBean getProductPropertyOptionBean() {
		return productPropertyOptionBean;
	}

	public void setProductPropertyOptionBean(ProductPropertyOptionBean productPropertyOptionBean) {
		this.productPropertyOptionBean = productPropertyOptionBean;
	}
}
