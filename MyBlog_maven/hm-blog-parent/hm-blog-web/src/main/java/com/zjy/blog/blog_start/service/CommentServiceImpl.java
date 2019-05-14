package com.zjy.blog.blog_start.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zjy.blog.blog_start.domain.Comment;
import com.zjy.blog.blog_start.repository.CommentRepository;


/**
 * Comment Service接口实现.
 * 
 * @since 1.0.0 2017年6月6日
 * @author <a href="https://zjy.com">zhaojianyu</a>
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    
	@Override
	public Comment getCommentById(Long id) {
		return commentRepository.findOne(id);
	}

	@Override
	public void removeComment(Long id) {
		commentRepository.delete(id);
	}

}
