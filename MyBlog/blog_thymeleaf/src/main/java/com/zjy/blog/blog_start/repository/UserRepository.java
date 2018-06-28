package com.zjy.blog.blog_start.repository;

import java.util.List;

import com.zjy.blog.blog_start.domain.User;

/**
 * @author zjy
 *
 */
public interface UserRepository {
	/**
	 * 创建或修改用户
	 * @param user
	 * @return
	 */
	User saveOrupdateUser(User user);
	/**
	 * 删除用户
	 * @param id
	 */
	void deleteUser(Long id);
	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	User getUserById(Long id);
	/**
	 * 获取用户列表
	 * @return
	 */
	List<User> listUsers();
}
