package com.honghe.recordweb.action.frontend.news.entity;

public class ItemRectangular extends Item{

	private static final long serialVersionUID = 1L;
	
	private String bdC;
	
	private String bgC;
	
	private String bdW;
	
	private String rds;
	
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

	public String getBdC() {
		return bdC;
	}

	public void setBdC(String bdC) {
		this.bdC = bdC;
	}

	public String getBgC() {
		return bgC;
	}

	public void setBgC(String bgC) {
		this.bgC = bgC;
	}

	public String getBdW() {
		return bdW;
	}

	public void setBdW(String bdW) {
		this.bdW = bdW;
	}

	public String getRds() {
		return rds;
	}

	public void setRds(String rds) {
		this.rds = rds;
	}

	public void updateRadio(Float scX, Float scY)
	{
		super.updateRadio(scX, scY);
		
		this.w = String.valueOf(Float.parseFloat(this.w)*scX);
		this.h = String.valueOf(Float.parseFloat(this.h)*scY);
	}
}
