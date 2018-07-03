package com.zjy.blog.blog_start.service;

import com.zjy.blog.blog_start.domain.Authority;

/**
 * Authority 服务接口.
 * 
 */
public interface AuthorityService {
	/**
	 * 根据ID查询 Authority
	 * @param id
	 * @return
	 */
    Authority getAuthorityById(Long id);
}
