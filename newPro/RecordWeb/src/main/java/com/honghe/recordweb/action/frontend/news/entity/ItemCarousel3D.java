package com.honghe.recordweb.action.frontend.news.entity;
import java.util.ArrayList;
import java.util.List;

public class ItemCarousel3D extends Item{

	private static final long serialVersionUID = 1L;

	private String scX;
	
	private String scY;
	
	private String isFtp;
	
	private String during;
	
	private String ftpPath;
	

	public String getFtpPath() {
		return ftpPath;
	}

	public void setFtpPath(String ftpPath) {
		this.ftpPath = ftpPath;
	}
	
	public String getDuring() {
		return during;
	}

	public void setDuring(String during) {
		this.during = during;
	}
	
	private List<CarouseImage> imageList = new ArrayList<CarouseImage>();

	private List<CarouseText> textList = new ArrayList<CarouseText>();
	
	public List<CarouseImage> getImageList() {
		return imageList;
	}

	public void setImageList(List<CarouseImage> imageList) {
		this.imageList = imageList;
	}

	public List<CarouseText> getTextList() {
		return textList;
	}

	public void setTextList(List<CarouseText> textList) {
		this.textList = textList;
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
	
	public String getIsFtp() {
		return isFtp;
	}

	public void setIsFtp(String isFtp) {
		this.isFtp = isFtp;
	}

	public void updateRadio(Float scX, Float scY)
	{
		super.updateRadio(scX, scY);
		
		this.scX = String.valueOf(Float.parseFloat(this.scX)*scX);
		this.scY = String.valueOf(Float.parseFloat(this.scY)*scY);
	}
}
