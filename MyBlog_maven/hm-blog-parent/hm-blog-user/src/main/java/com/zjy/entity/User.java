package com.zjy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * User 实体.
 *
 */
@Entity
public class User{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Size(min=2, max=20)
	@Column(nullable = false, length = 20)
	private String name;

	@Size(max=50)
	@Email(message= "邮箱格式不对" )
	@Column(nullable = false, length = 50, unique = true)
	private String email;

	@Size(min=3, max=20)
	@Column(nullable = false, length = 20, unique = true)
	private String username;

	@NotEmpty(message = "密码不能为空")
	@Size(max=100)
	@Column(length = 100)
	private String password;

	@Column(length = 200)
	private String avatar;


	protected User() { // 无参构造函数;设为 protected 防止直接使用
	}

	public User(Long id, String name, String username, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.username = username;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 加密密码
	 * @param password
	 */
	public void setEncodePassword(String password) {
		PasswordEncoder  encoder = new BCryptPasswordEncoder();
		String encodePasswd = encoder.encode(password);
		this.password = encodePasswd;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public String toString() {
		return String.format("User[id=%d,name='%s',username='%s',email='%s']", id, name, username, email);
	}
}
