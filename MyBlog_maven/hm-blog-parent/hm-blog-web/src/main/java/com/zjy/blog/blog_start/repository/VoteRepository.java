package com.zjy.blog.blog_start.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zjy.blog.blog_start.domain.Vote;


/**
 * Vote Repository接口.
 * 点赞
 * @author 赵健宇
 */
public interface VoteRepository extends JpaRepository<Vote, Long> {
}
