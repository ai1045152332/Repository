package com.zjy.blog.blog_start.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zjy.blog.blog_start.domain.Comment;


/**
 * Comment Repository 接口. 
 * 
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
