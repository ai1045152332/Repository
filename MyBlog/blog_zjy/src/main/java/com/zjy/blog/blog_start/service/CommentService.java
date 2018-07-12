package com.zjy.blog.blog_start.service;

import com.zjy.blog.blog_start.domain.Comment;

/**
 * Comment Service接口.
 * 
 */
public interface CommentService {

	/**
     * 根据id获取 Comment
     * @param id
     * @return
     */
    Comment getCommentById(Long id);
    /**
     * 删除评论
     * @param id
     * @return
     */
    void removeComment(Long id);
}
