package com.tangzhe.entity;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class User implements Serializable{
 
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue
	private Long id;

	@Column(name = "username")
 	private String username;
	
	@Column(name = "password")
	private String password;

	protected User() {}
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

}
