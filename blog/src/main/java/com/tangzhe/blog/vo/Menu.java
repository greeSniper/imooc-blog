package com.tangzhe.blog.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by 唐哲
 * 2018-04-22 23:36
 */
@Data
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String url;

    public Menu() {}
    public Menu(String name, String url) {
        this.name = name;
        this.url = url;
    }

}
