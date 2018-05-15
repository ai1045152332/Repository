package com.honghe.recordweb.action.frontend.news.entity;

public class ItemImage extends Item{

	private static final long serialVersionUID = 1L;
	
	private String rds;
	
	private String path;
	
	private String mapped;

	private String w;
	
	private String h;
//	private String oP;  // 部件的锁定 解锁

//	public String getoP() {
//		return oP;
//	}
//
//	public void setoP(String oP) {
//		this.oP = oP;
//	}
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public String getRds() {
		return rds;
	}

	public void setRds(String rds) {
		this.rds = rds;
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
