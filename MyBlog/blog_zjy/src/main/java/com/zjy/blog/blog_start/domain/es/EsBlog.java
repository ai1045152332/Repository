package com.zjy.blog.blog_start.domain.es;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 文档
 * 
 * @author Administrator
 *
 */
@Document(indexName = "blog", type = "blog")
public class EsBlog implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String title;
	// 摘要
	private String summary;
	// 正文
	private String content;

	protected EsBlog() {
		// JPA规范 防止直接使用
	}

	public EsBlog(String id, String title, String summary, String content) {
		super();
		this.id = id;
		this.title = title;
		this.summary = summary;
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "EsBlog [id=" + id + ", title=" + title + ", summary=" + summary + ", content=" + content + "]";
	}

}
