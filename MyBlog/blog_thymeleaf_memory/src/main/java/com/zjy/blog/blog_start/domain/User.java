/**
 * 
 */
package com.zjy.blog.blog_start.domain;

/**
 * @author zjy
 * 20180628
 *	用户实体
 */
public class User {
	private Long id;
	private String name;
	private String email;
	
	public User() {
		super();
	}
	
	public User(Long id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
