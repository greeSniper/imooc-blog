package com.tangzhe.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * Created by 唐哲
 * 2018-04-18 18:25
 */
@Data
@Document(indexName = "blog", type = "blog") // 文档
public class EsBlog implements Serializable {

    @Id // 主键
    private String id;
    private String title;
    private String summary;
    private String content;

    protected EsBlog() {} // JPA规范要求；防止直接使用
    public EsBlog(String title, String summary, String content) {
        this.title = title;
        this.summary = summary;
        this.content = content;
    }

}
