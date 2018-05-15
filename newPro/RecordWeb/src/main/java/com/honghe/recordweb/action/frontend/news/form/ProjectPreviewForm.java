package com.honghe.recordweb.action.frontend.news.form;

import java.io.UnsupportedEncodingException;

public class ProjectPreviewForm {
	
	private String prid;
	
	private String pid;
	
	private String source;
	
	private String navFlag;
	

	public String getNavFlag() {
		return navFlag;
	}

	public void setNavFlag(String navFlag) {
		this.navFlag = navFlag;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getPrid() {
//		try {
//			prid = new String(prid.getBytes("ISO-8859-1"),"utf-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		return prid;
	}

	public void setPrid(String prid) {
		this.prid = prid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
}
