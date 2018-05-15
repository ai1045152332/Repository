package com.honghe.recordweb.action.frontend.news.entity;

public class ItemVideo extends Item{

	private static final long serialVersionUID = 1L;
	
	private String path;
	
	private String snap;
	
	private String mapped;

	private String w;
	
	private String h;

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
	
	public String getSnap() {
		return snap;
	}

	public void setSnap(String snap) {
		this.snap = snap;
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
