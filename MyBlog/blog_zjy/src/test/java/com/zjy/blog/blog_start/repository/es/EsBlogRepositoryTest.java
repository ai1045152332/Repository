package com.zjy.blog.blog_start.repository.es;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.zjy.blog.blog_start.domain.es.EsBlog;

/**
 * Es资源库接口测试
 * 
 * @author Administrator
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsBlogRepositoryTest {

	@Autowired
	EsBlogRepository es;

	@Before
	public void initRepository() {
		// 清除所有数据
		es.deleteAll();
		es.save(new EsBlog("1", "标题1", "简介1", "内容1"));
		es.save(new EsBlog("2", "标题2", "简介2", "内容2"));
		es.save(new EsBlog("3", "标题3", "简介3", "内容3"));

	}

	@Test
	public void testFindDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContaining() {
//		Pageable pageable = new PageRequest(0, 20);
//		String title = "3";
//		String summary = "3";
//		String content = "内容3";
//		Page<EsBlog> page = es.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContaining(title, summary,
//				content, pageable);
//		assertThat(page.getNumberOfElements()).isEqualTo(1);
//		for (EsBlog blog : page.getContent()) {
//			System.out.println(blog.toString());
//		}
	}
}
