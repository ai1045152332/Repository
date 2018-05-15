package com.honghe.recordweb.action.frontend.news.entity;

import java.util.List;



public class ResourceItem {
	private List<ResourceFile> resourceFile;
	private String md5;
	public List<ResourceFile> getResourceFile() {
		return resourceFile;
	}
	public void setResourceFile(List<ResourceFile> resourceFile) {
		this.resourceFile = resourceFile;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	
}
