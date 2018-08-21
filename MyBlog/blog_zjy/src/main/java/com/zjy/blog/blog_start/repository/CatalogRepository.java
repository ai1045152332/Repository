package com.zjy.blog.blog_start.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zjy.blog.blog_start.domain.Catalog;
import com.zjy.blog.blog_start.domain.User;

/**
 * Catalog Repository.
 * 
 * @since 1.0.0 2017年6月7日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
public interface CatalogRepository extends JpaRepository<Catalog, Long> {
    /**
     * 根据用户查询
     * @param user
     * @return
     */
    List<Catalog> findByUser(User user);

    /**
     * 根据用户、分类名称查询
     * @param user
     * @param name
     * @return
     */
    List<Catalog> findByUserAndName(User user,String name);
}
