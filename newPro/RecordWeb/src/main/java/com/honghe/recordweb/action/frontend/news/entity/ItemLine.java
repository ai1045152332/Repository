package com.honghe.recordweb.action.frontend.news.entity;

public class ItemLine extends Item{

	private static final long serialVersionUID = 1L;

	private String h;
	
	private String w;
	
	private String bdS;
	
	public String getBdS() {
		return bdS;
	}

	public void setBdS(String bdS) {
		this.bdS = bdS;
	}

	private String bdC;


	public String getBdC() {
		return bdC;
	}

	public void setBdC(String bdC) {
		this.bdC = bdC;
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

	public void updateRadio(Float scX, Float scY)
	{
		super.updateRadio(scX, scY);
		
		this.w = String.valueOf(Float.parseFloat(this.w)*scX);
		this.h = String.valueOf(Float.parseFloat(this.h)*scY);
	}
}
