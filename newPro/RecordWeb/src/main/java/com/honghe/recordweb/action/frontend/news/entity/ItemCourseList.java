package com.honghe.recordweb.action.frontend.news.entity;

public class ItemCourseList extends Item{
	
	private static final long serialVersionUID = 1L;

	private String scX;
	
	private String scY;
	
	private String cListID;
	
	private String bgImg;
	
	private String bgMapped;
	
	private String cData;
	
	private String serverIp;

	public String getCListID() {
		return cListID;
	}

	public void setCListID(String cListID) {
		this.cListID = cListID;
	}
	
	public String getBGImg() {
		return bgImg;
	}

	public void setBGImg(String bgImg) {
		this.bgImg = bgImg;
	}
	
	public String getBGMapped() {
		return bgMapped;
	}

	public void setBGMapped(String bgMapped) {
		this.bgMapped = bgMapped;
	}
	
	public String getCData() {
		return cData;
	}

	public void setCData(String cData) {
		this.cData = cData;
	}
	
	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
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
