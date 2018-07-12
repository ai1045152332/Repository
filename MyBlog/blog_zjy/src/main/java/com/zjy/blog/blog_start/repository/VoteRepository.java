package com.zjy.blog.blog_start.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zjy.blog.blog_start.domain.Vote;


/**
 * Vote Repository接口.
 */
public interface VoteRepository extends JpaRepository<Vote, Long> {
}
