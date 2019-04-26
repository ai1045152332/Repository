package com.zjy.respository;

import com.zjy.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Authority 仓库.
 * 
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
