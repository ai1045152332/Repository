package com.honghe.recordweb.action.frontend.news.entity;

public class ItemCountdown extends Item{
	
	private static final long serialVersionUID = 1L;

	private String scX;
	
	private String scY;
	
	private String type;
	
	private String tymd;
	
	private String tymdhms;
	
	private String tymdval;
	
	private String tymdhmsval;
	
	private String fontcolor;
	
	private String numbercolor;
	
//	private String fontsize;
	
	public String getType() {
		return type;
	}

//	public String getFontsize() {
//		return fontsize;
//	}
//
//	public void setFontsize(String fontsize) {
//		this.fontsize = fontsize;
//	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTymd() {
		return tymd;
	}

	public void setTymd(String tymd) {
		this.tymd = tymd;
	}

	public String getTymdhms() {
		return tymdhms;
	}

	public void setTymdhms(String tymdhms) {
		this.tymdhms = tymdhms;
	}

	public String getTymdval() {
		return tymdval;
	}

	public void setTymdval(String tymdval) {
		this.tymdval = tymdval;
	}

	public String getTymdhmsval() {
		return tymdhmsval;
	}

	public void setTymdhmsval(String tymdhmsval) {
		this.tymdhmsval = tymdhmsval;
	}

	public String getFontcolor() {
		return fontcolor;
	}

	public void setFontcolor(String fontcolor) {
		this.fontcolor = fontcolor;
	}

	public String getNumbercolor() {
		return numbercolor;
	}

	public void setNumbercolor(String numbercolor) {
		this.numbercolor = numbercolor;
	}

	public String getScX() {
		return scX;
	}

	public void setScX(String scX) {
		this.scX = scX;
	}
	
	public String getScY() {
		return scY;
	}

	public void setScY(String scY) {
		this.scY = scY;
	}
	
	public void updateRadio(Float scX, Float scY)
	{
		super.updateRadio(scX, scY);
		
		this.scX = String.valueOf(Float.parseFloat(this.scX)*scX);
		this.scY = String.valueOf(Float.parseFloat(this.scY)*scY);
	}
}
