package com.zjy.blog.blog_start.service;

import com.zjy.blog.blog_start.domain.Authority;

/**
 * Authority 服务接口.
 * 
 * @since 1.0.0 2017年5月30日
 * @author <a href="https://zjy.com">zhaojianyu</a>
 */
public interface AuthorityService {
	/**
	 * 根据ID查询 Authority
	 * @param id
	 * @return
	 */
    Authority getAuthorityById(Long id);
}
