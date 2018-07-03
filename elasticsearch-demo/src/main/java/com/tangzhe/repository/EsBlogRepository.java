package com.tangzhe.repository;

import com.tangzhe.entity.EsBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by 唐哲
 * 2018-04-18 18:36
 */
public interface EsBlogRepository extends ElasticsearchRepository<EsBlog, String> {

    Page<EsBlog> findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContaining(
            String title, String summary, String content, Pageable pageable);

}
