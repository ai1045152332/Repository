package com.zjy.blog.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zjy.blog.web.domain.Authority;

/**
 * Authority 仓库.
 * 
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
