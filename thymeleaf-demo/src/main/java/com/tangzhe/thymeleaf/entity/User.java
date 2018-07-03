package com.tangzhe.thymeleaf.entity;

import lombok.Data;

/**
 * Created by 唐哲
 * 2018-04-13 15:57
 */
@Data
public class User {

    private Long id;
    private String username;
    private String email;

    public User() {}
    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

}
