package com.honghe.recordweb.action.frontend.news.entity;

public class ItemWeather extends Item{
	
	private static final long serialVersionUID = 1L;

	private String scX;
	
	private String scY;
	
	private String type;
	
	private String bgC;
	
	private String ftC;
	
	private String bdW;
	
	private String bdC;
	
	private String city;
	
	private String serverIp;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getBGC() {
		return bgC;
	}

	public void setBGC(String bgC) {
		this.bgC = bgC;
	}
	
	public String getFTC() {
		return ftC;
	}

	public void setFTC(String ftC) {
		this.ftC = ftC;
	}
	
	public String getBDW() {
		return bdW;
	}

	public void setBDW(String bdW) {
		this.bdW = bdW;
	}
	
	public String getBDC() {
		return bdC;
	}

	public void setBDC(String bdC) {
		this.bdC = bdC;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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
