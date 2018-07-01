package com.zjy.blog.blog_start.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.zjy.blog.blog_start.domain.User;

/**
 * @author zjy
 *
 */
public interface UserRepository extends JpaRepository<User, Long>{
//public interface UserRepository extends CrudRepository<User, Long>{
	 /**
     * 根据用户姓名分页查询用户列表
     * @param name
     * @param pageable
     * @return
     */
    Page<User> findByNameLike(String name, Pageable pageable);

    /**
     * 根据用户账号查询用户
     * @param username
     * @return
     */
    User findByUsername(String username);
}	
