package com.honghe.recordhibernate.entity.form;

public class ResourceForm {
	
	private String id;
	
	private String path;
	
	private String mapped;

	private String name;
	
	private String pid;
	
	private String snap;
	
	private String sortOrder;
	
	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getSnap() {
		return snap;
	}

	public void setSnap(String snap) {
		this.snap = snap;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMapped() {
		return mapped;
	}

	public void setMapped(String mapped) {
		this.mapped = mapped;
	}
}
