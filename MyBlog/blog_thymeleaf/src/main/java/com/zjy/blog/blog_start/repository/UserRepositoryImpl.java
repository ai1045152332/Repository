/**
 * 
 */
package com.zjy.blog.blog_start.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import com.zjy.blog.blog_start.domain.User;

/**
 * @author zjy
 * 20180628
 */
public class UserRepositoryImpl implements UserRepository {
	//zjy 暂时存储在内存中,没用关系型数据库
	private final ConcurrentMap<Long, User> userMap = new ConcurrentHashMap<>();
	//计数,每次增加用户 都会递增 保证id唯一
	private static AtomicLong counter = new AtomicLong();
	/**
	 * 保存或修改用户
	 */
	@Override
	public User saveOrupdateUser(User user) {
		Long id = user.getId();
		if(id==null) {
			//新建用户  生成递增id
			id = counter.incrementAndGet();
			user.setId(id);
		}
		this.userMap.put(id, user);
		return null;
	}
	/**
	 * 删除用户
	 */
	@Override
	public void deleteUser(Long id) {
		this.userMap.remove(id);
	}
	/**
	 * 通过id查询用户
	 */
	@Override
	public User getUserById(Long id) {
		return this.userMap.get(id);
	}
	/**
	 * 获取所有用户
	 */
	@Override
	public List<User> listUsers() {
		return new ArrayList<User>(this.userMap.values());
	}



}
