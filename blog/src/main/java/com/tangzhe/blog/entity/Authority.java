package com.tangzhe.blog.entity;

import javax.persistence.*;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * 权限
 */
@Entity
@Data
public class Authority implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Override
	public String getAuthority() {
		return name;
	}

}
