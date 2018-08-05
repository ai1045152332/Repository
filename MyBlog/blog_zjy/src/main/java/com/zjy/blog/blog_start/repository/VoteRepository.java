package com.zjy.blog.blog_start.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zjy.blog.blog_start.domain.Vote;


/**
 * Vote Repository接口.
 * @since 1.0.0 2017年6月6日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
public interface VoteRepository extends JpaRepository<Vote, Long> {
}
