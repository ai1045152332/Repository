package com.honghe.recordweb.action.frontend.news.entity;


import com.honghe.recordweb.util.base.util.StringUtil;

public class ItemText extends Item{
	
	private static final long serialVersionUID = 1L;
	
	private String w;
	
	private String h;
	
	private String con;
	
	private String speed;
	
	private String dim;
	
	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getDim() {
		return dim;
	}

	public void setDim(String dim) {
		this.dim = dim;
	}

	public String getCon() {
		return con;
	}

	public void setCon(String con) {
		this.con = StringUtil.getUtf8Str(con);//gbk
	}
	
	public String getW() {
		return w;
	}

	public void setW(String w) {
		this.w = w;
	}

	public String getH() {
		return h;
	}

	public void setH(String h) {
		this.h = h;
	}

	public void updateRadio(Float scX, Float scY)
	{
		super.updateRadio(scX, scY);
		
		this.w = String.valueOf(Float.parseFloat(this.w)*scX);
		this.h = String.valueOf(Float.parseFloat(this.h)*scY);
	}
}
