package com.tangzhe.blog.repository;

import com.tangzhe.blog.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Authority 仓库
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long>{
}
