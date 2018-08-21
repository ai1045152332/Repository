package com.shop.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.shop.bean.AdminBean;
import com.shop.bean.ProductBean;
import com.shop.bean.ProductPropertyBean;
import com.shop.bean.ProductTypeBean;
import com.shop.filter.json.ComplexPropertyPreFilter;
import com.shop.service.ProductPropertyService;
import com.shop.service.ProductService;
import com.shop.service.ProductTypeService;
import com.shop.util.Constants;
import com.shop.util.DateUtil;
import com.shop.util.FileUtil;
import com.shop.util.StringUtil;
import com.shop.util.bean.PagingBean;


public class ProductAction {
	public HttpServletResponse response = ServletActionContext.getResponse();
	public HttpServletRequest request = ServletActionContext.getRequest();
	public HttpSession session = request.getSession();

	
	private ProductBean productBean;
	
	private File pic;
	private String picFileName;
	private String[] properties;
	
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductTypeService productTypeService;
	@Autowired
	private ProductPropertyService productPropertyService;
	
	public String update() {
		if(pic!=null) {
			//upload
			String ext = FileUtil.getFileExt(picFileName);
			String fileName = FileUtil.getPicName(ext);
			String filePath = FileUtil.getYMDPath();
			
			FileUtil.uploadMyFile(pic, Constants.UPLOAD_PRODUCT_LIST+filePath, fileName);
			productBean.setPic(filePath+fileName);
		}
		
		properties = request.getParameterValues("propertys");
		String propertiesStr = "";
		if(properties!=null&&properties.length>0) {
			for(int i=0; i<properties.length; i++) {
				if(propertiesStr.length()>0) {
					propertiesStr += ";";
				}
				propertiesStr += properties[i];
			}
		}
		if(productBean.getId()>0) {
			productBean.setPropertys(propertiesStr);
			productService.update(productBean);
		} else {
			
			AdminBean adminBean = (AdminBean) session.getAttribute(Constants.SESSION_ADMIN);
			productBean.setCreateDate(DateUtil.getNow());
			productBean.setAdminBean(adminBean);
			productBean.setPropertys(propertiesStr);
			productService.add(productBean);
		}
		return "update";
	}
	
	
	
	public String toAdd() {
		List<ProductTypeBean> productTypeBeans = productTypeService.getAll();
		ActionContext.getContext().put("productTypeBeans", productTypeBeans);
		return "toAdd";
	}
	
	public void getProperties() {
		int typeId = StringUtil.toInt(request.getParameter("typeId"));
		if(typeId>0) {
			List<ProductPropertyBean> propertyBeans = productPropertyService.getByType(typeId);
			ComplexPropertyPreFilter filter = new ComplexPropertyPreFilter();
			Set<String> excludes = new HashSet<String>();
			excludes.add("adminBean");
			excludes.add("productTypeBean");
			filter.setExcludes(excludes);
			String json = JSON.toJSONString(propertyBeans, filter);
			response.setCharacterEncoding("utf-8");
			try {
				PrintWriter out = response.getWriter();
				out.print(json);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//查询所有的数据
	public String list() {
		
		int currentPage = StringUtil.toInt2(request.getParameter("currentPage"));
		int totalCount = productService.getCount();
		int pageSize = 10;
		PagingBean pagingBean = new PagingBean(currentPage, totalCount, pageSize);
		pagingBean.setPrefixUrl(request.getContextPath()+"/admin/listProductAction");
		pagingBean.setAnd(false);
		List<ProductBean> list = productService.getByPage(currentPage*pageSize, pageSize);
		
		ActionContext.getContext().put("list", list);
		ActionContext.getContext().put("pagingBean", pagingBean);
		
		return "list";
	}
	
	public String toUpdate() {
		int id = StringUtil.toInt(request.getParameter("id"));
		if(id>0) {
			//获取商品信息
			ProductBean bean = productService.getById(id);
			ActionContext.getContext().put("bean", bean);
			//获取所有的商品分类
			List<ProductTypeBean> productTypeBeans = productTypeService.getAll();
			ActionContext.getContext().put("productTypeBeans", productTypeBeans);
			//获取该商品所属的商品分类的所有属性信息list
			int typeId = bean.getProductTypeBean().getId();
			List<ProductPropertyBean> propertyBeans = productPropertyService.getByType(typeId);
			ActionContext.getContext().put("propertyBeans", propertyBeans);
			
			//处理选项值  1-CPU-1-1G HZ;5-品牌-7-1.5G HZ
			Map<Integer, Integer> propertyToOption = new HashMap<Integer, Integer>();
			if(bean.getPropertys()!=null&&bean.getPropertys().length()>0) {
				String[] items = bean.getPropertys().split(";");
				for(int i=0; i<items.length; i++) {
					String[] values = items[i].split("-");
					if(values.length==4) {
						propertyToOption.put(StringUtil.toInt(values[0]), StringUtil.toInt(values[2]));
					}
				}
			}
			ActionContext.getContext().put("propertyToOption", propertyToOption);
			
		}
		return "toUpdate";
	}
	
	public String del() {
		int id = StringUtil.toInt(request.getParameter("id"));
		if(id>0) {
			productService.del(id);
		}
		return "del";
	}

	
	
	public ProductBean getProductBean() {
		return productBean;
	}

	public void setProductBean(ProductBean productBean) {
		this.productBean = productBean;
	}

	public File getPic() {
		return pic;
	}

	public void setPic(File pic) {
		this.pic = pic;
	}

	public String getPicFileName() {
		return picFileName;
	}
	
	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}

	public void setProperties(String[] properties) {
		this.properties = properties;
	}
	
}
