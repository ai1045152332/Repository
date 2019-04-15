package com.zjy.blog.blog_start.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zjy.blog.blog_start.domain.Authority;

/**
 * Authority 仓库.
 * 
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
