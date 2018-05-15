package com.honghe.recordweb.action.frontend.news.entity;

import java.util.ArrayList;
import java.util.List;

public class ItemExcellent extends Item{
	
	private static final long serialVersionUID = 1L;
	
	private String h;
	
	private String w;
	
	private String workType;
	
	private String backPic;
	
	private String mapped;

	private List<ExcellentArticle> articleList = new ArrayList<ExcellentArticle>();

	public List<ExcellentArticle> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<ExcellentArticle> articleList) {
		this.articleList = articleList;
	}

	public String getH() {
		return h;
	}

	public void setH(String h) {
		this.h = h;
	}

	public String getW() {
		return w;
	}

	public void setW(String w) {
		this.w = w;
	}
	
	public String getWorkType() {
		return workType;
	}
	
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	
	public String getBackPic() {
		return backPic;
	}
	
	public void setBackPic(String backPic) {
		this.backPic = backPic;
	}
	
	public String getMapped() {
		return mapped;
	}

	public void setMapped(String mapped) {
		this.mapped = mapped;
	}

	public void updateRadio(Float scX, Float scY)
	{
		super.updateRadio(scX, scY);
		
		this.w = String.valueOf(Float.parseFloat(this.w)*scX);
		this.h = String.valueOf(Float.parseFloat(this.h)*scY);
	}
}
