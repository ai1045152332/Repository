package com.shop.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.shop.util.bean.PagingBean;

public class PageTag  extends SimpleTagSupport{

	private PagingBean pagingBean;
	
	/*
	 * 
	 */
	@Override
	public void doTag() throws JspException, IOException {
		StringBuilder builder = new StringBuilder();
		builder.append("<ul class='pagination'>");
		if(pagingBean.getCurrentPage()>0) {
			//上一页和首页是一个连接
			builder.append("<li><a href='").append(pagingBean.getPrefixUrl()).append(pagingBean.isAnd()?"&":"?").append("currentPage=0").append("' aria-label='Previous'>");
			builder.append("<span aria-hidden='true'>首页</span></a></li>");
			builder.append("<li><a href='").append(pagingBean.getPrefixUrl()).append(pagingBean.isAnd()?"&":"?").append("currentPage=").append(pagingBean.getCurrentPage()-1).append("' aria-label='Previous'>");
			builder.append("<span aria-hidden='true'>上一页</span></a></li>");
		} else {
			builder.append("<li class='disabled'><a href='#' aria-label='Previous'>");
			builder.append("<span aria-hidden='true'>首页<span></a></li>");
			builder.append("<li class='disabled'><a href='#' aria-label='Previous'>");
			builder.append("<span aria-hidden='true'>上一页</span></a></li>");
		}
		
		if(pagingBean.getCurrentPage()<(pagingBean.getPageCount()-1)) {
			//下一页和尾页是一个连接 
			builder.append("<li><a href='").append(pagingBean.getPrefixUrl()).append(pagingBean.isAnd()?"&":"?").append("currentPage=").append(pagingBean.getCurrentPage()+1).append("' aria-label='Next'>");
			builder.append("<span aria-hidden='true'>下一页</span></a></li>");
			builder.append("<li><a href='").append(pagingBean.getPrefixUrl()).append(pagingBean.isAnd()?"&":"?").append("currentPage=").append(pagingBean.getPageCount()-1).append("' aria-label='Next'>");
			builder.append("<span aria-hidden='true'>尾页</span></a></li>");
			
			
		} else{
			builder.append("<li class='disabled'><a href='#' aria-label='Next'> <span ");
			builder.append("aria-hidden='true'>下一页</span></a></li>");
			builder.append("<li class='disabled'><a href='#' aria-label='Next'> <span ");
			builder.append("aria-hidden='true'>尾页</span></a></li>");
		}
		builder.append("<li class='disabled'><a href='#' aria-label='Next'> <span aria-hidden='true'>");
		builder.append(pagingBean.getCurrentPage()+1).append("/").append(pagingBean.getPageCount());
		builder.append("</span></a></li>");
		builder.append("</ul>");
		
		getJspContext().getOut().print(builder.toString());
	}

	public PagingBean getPagingBean() {
		return pagingBean;
	}

	public void setPagingBean(PagingBean pagingBean) {
		this.pagingBean = pagingBean;
	}
}
