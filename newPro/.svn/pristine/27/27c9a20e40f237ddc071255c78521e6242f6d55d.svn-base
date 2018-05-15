package com.honghe.recordweb.action.frontend.news.entity;

import java.util.ArrayList;
import java.util.List;

public class ItemSwipe extends Item{
	
	private static final long serialVersionUID = 1L;

	private String h;
	
	private String w;
	
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

//	private List<CarouseText> textList = new ArrayList<CarouseText>();
	
	private String isFtp;

	public List<CarouseImage> getImageList() {
		return imageList;
	}

	public void setImageList(List<CarouseImage> imageList) {
		this.imageList = imageList;
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

	public String getIsFtp() {
		return isFtp;
	}

	public void setIsFtp(String isFtp) {
		this.isFtp = isFtp;
	}
	
	public void updateRadio(Float scX, Float scY)
	{
		super.updateRadio(scX, scY);
		
		this.w = String.valueOf(Float.parseFloat(this.w)*scX);
		this.h = String.valueOf(Float.parseFloat(this.h)*scY);
	}
}
