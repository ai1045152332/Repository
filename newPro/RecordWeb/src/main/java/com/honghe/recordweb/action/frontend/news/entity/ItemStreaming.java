package com.honghe.recordweb.action.frontend.news.entity;

public class ItemStreaming extends Item {

	private static final long serialVersionUID = 1L;
	
	private String w;
	
	private String h;
	
	private String url;
	
	private String thirdstream;
	
	private String honghestream;
	
	public String getThirdstream() {
		return thirdstream;
	}

	public void setThirdstream(String thirdstream) {
		this.thirdstream = thirdstream;
	}

	public String getHonghestream() {
		return honghestream;
	}

	public void setHonghestream(String honghestream) {
		this.honghestream = honghestream;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
