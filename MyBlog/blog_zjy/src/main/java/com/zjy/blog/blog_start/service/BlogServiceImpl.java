package com.zjy.blog.blog_start.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.zjy.blog.blog_start.domain.Blog;
import com.zjy.blog.blog_start.domain.Comment;
import com.zjy.blog.blog_start.domain.User;
import com.zjy.blog.blog_start.repository.BlogRepository;


/**
 * Blog 服务.
 * 
 */
@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogRepository blogRepository;
 
	@Transactional
	@Override
	public Blog saveBlog(Blog blog) {
		Blog returnBlog = blogRepository.save(blog);
		return returnBlog;
	}

	@Transactional
	@Override
	public void removeBlog(Long id) {
		blogRepository.delete(id);
	}

	@Override
	public Blog getBlogById(Long id) {
		return blogRepository.findOne(id);
	}

	@Override
	public Page<Blog> listBlogsByTitleVote(User user, String title, Pageable pageable) {
		// 模糊查询
		title = "%" + title + "%";
		String tags = title;
		Page<Blog> blogs = blogRepository.findByTitleLikeAndUserOrTagsLikeAndUserOrderByCreateTimeDesc(title,user, tags,user, pageable);
		return blogs;
	}

	@Override
	public Page<Blog> listBlogsByTitleVoteAndSort(User user, String title, Pageable pageable) {
		// 模糊查询
		title = "%" + title + "%";
		Page<Blog> blogs = blogRepository.findByUserAndTitleLike(user, title, pageable);
		return blogs;
	}

	@Override
	public void readingIncrease(Long id) {
		Blog blog = blogRepository.findOne(id);
		blog.setReadSize(blog.getReadSize()+1); // 在原有的阅读量基础上递增1
		this.saveBlog(blog);
	}

	@Override
	public Blog createComment(Long blogId, String commentContent) {
	    Blog originalBlog = blogRepository.findOne(blogId);
	    User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
	    Comment comment = new Comment(user, commentContent);
	    originalBlog.addComment(comment);
	    return this.saveBlog(originalBlog);
	}

	@Override
	public void removeComment(Long blogId, Long commentId) {
	    Blog originalBlog = blogRepository.findOne(blogId);
	    originalBlog.removeComment(commentId);
	    this.saveBlog(originalBlog);
	}
}
