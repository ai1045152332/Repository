package com.honghe.recordweb.action.frontend.news.entity;


import com.honghe.recordweb.util.base.util.StringUtil;

public class Item implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	private String prid;
	
	private String pid;
	
	private String bid;
	
	private String x;
	
	private String y;

	private String t;
	
	private String idx;
	
	private String iH;

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}
	
	public String getPrid() {
		return prid;
	}

	public void setPrid(String prid) {
		this.prid = prid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}
	
	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

	public String getiH() {
		return iH;
	}

	public void setiH(String iH) {
		this.iH = StringUtil.getUtf8Str(iH);//gbk
	}

	public void updateRadio(Float scX, Float scY)
	{
		this.x = String.valueOf(Float.parseFloat(this.x)*scX);
		this.y = String.valueOf(Float.parseFloat(this.y)*scY);
		//this.iH;
	}
}
