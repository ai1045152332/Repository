package com.honghe.recordweb.util.base.entity;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;


/**
 * 用于描述功能模块
 * @author hucl
 * @param
 *
 */
public class FunctionModule implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//功能模块的id，是32位的整数，用于组合用户的权限
	private int id;
	private String name;
	//功能模块中的子模块
	private String urlPath;
	private Set<FunctionModule> subModules=new LinkedHashSet<FunctionModule>();
	private Integer type;
	public int getId() {
		return id;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUrlPath() {
		return urlPath;
	}
	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}
	public String getName() {
		return name;
	}
	public Set<FunctionModule> getSubModules() {
		return subModules;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSubModules(Set subModules) {
		this.subModules = subModules;
	}

	
}
