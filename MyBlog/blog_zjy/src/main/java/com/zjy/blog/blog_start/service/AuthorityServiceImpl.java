package com.zjy.blog.blog_start.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjy.blog.blog_start.domain.Authority;
import com.zjy.blog.blog_start.repository.AuthorityRepository;


/**
 * Authority 服务接口的实现.
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	private AuthorityRepository authorityRepository;


	@Override
	public Authority getAuthorityById(Long id) {
		return authorityRepository.findOne(id);
	}

}
