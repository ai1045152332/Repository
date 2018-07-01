package com.zjy.blog.blog_start.repository.es;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.zjy.blog.blog_start.domain.es.EsBlog;
/**
 * Es资源库接口
 * @author Administrator
 *
 */
public interface EsBlogRepository extends ElasticsearchRepository<EsBlog, String> {
	/**
	 * 根据用户名分页查询用户列表
	 * @param name
	 * @param pageable
	 * @return
	 */
	Page<EsBlog> findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContaining(String title, String summary, String content, Pageable pageable);
}
