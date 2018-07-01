/**
 * 
 */
package com.zjy.blog.blog_start.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zjy.blog.blog_start.domain.es.EsBlog;
import com.zjy.blog.blog_start.repository.es.EsBlogRepository;

/**
 * @author zjy 博客控制器
 */
@RestController
@RequestMapping("/blogs")
public class BlogController {

	@Autowired
	EsBlogRepository esBlogRepository;

	@GetMapping
	public String listBlogs(
			@RequestParam(value="order",required=false,defaultValue="new") String order,
			@RequestParam(value="keyword",required=false,defaultValue="" ) String keyword){
		System.out.println("order:" +order + ";keyword:" +keyword );
		return "redirect:/index?order="+order+"&keyword="+keyword;
	}
	
//	@GetMapping
//	public List<EsBlog> listBlogs(@RequestParam(value = "title") String title,
//			@RequestParam(value = "summary") String summary, @RequestParam(value = "content") String content,
//			@RequestParam(value = "pageIndex", defaultValue = "0") int pageIndex,
//			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
//		Pageable pageable = new PageRequest(pageIndex, pageSize);
//		Page<EsBlog> page = esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContaining(
//				title, summary, content, pageable);
//		return page.getContent();
//	}
//	@GetMapping("/blogs")
//	public List<EsBlog> list() {
//		Pageable pageable = new PageRequest(0, 10);
//		Page<EsBlog> page = esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContaining(
//				"1 ", "1", "1", pageable);
//		return page.getContent();
//	}
}
