package com.zjy.blog.blog_start.repository;

import org.springframework.data.repository.CrudRepository;

import com.zjy.blog.blog_start.domain.User;

/**
 * @author zjy
 *
 */
public interface UserRepository extends CrudRepository<User, Long>{
	
}
