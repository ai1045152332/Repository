package com.zjy.blog.blog_start.vo;

/**
 * 后台管理的菜单.
 * 值对象
 */
public class Menu {
	// 菜单名称
    private String name; 
    // 菜单 URL
    private String url; 
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Menu(String name, String url) {
		this.name = name;
		this.url = url;
	}
}
