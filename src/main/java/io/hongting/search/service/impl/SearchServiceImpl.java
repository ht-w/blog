package io.hongting.search.service.impl;

import io.hongting.mapper.PostMapper;
import io.hongting.search.entity.BlogSearchIndex;
import io.hongting.search.entity.vo.SearchVO;
import io.hongting.search.service.SearchService;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author hongting
 * @create 2021 12 22 2:58 PM
 */

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;


    @Override
    public List<SearchVO> searchWithHighlight(String keyword) {

        String preTag  = "<span style=\"color:#e91e63\">";
        String postTag = "</span>";

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.boolQuery()
                        .must(QueryBuilders.multiMatchQuery(keyword, "title", "description")))
                .withHighlightBuilder(new HighlightBuilder().field("title").field("description").requireFieldMatch(true).preTags(preTag).postTags(postTag))
                .build();

        SearchHits<BlogSearchIndex> contents = elasticsearchRestTemplate.search(searchQuery, BlogSearchIndex.class);
        List<SearchHit<BlogSearchIndex>> articles = contents.getSearchHits();

        if (articles.size() == 0){
            return new ArrayList<>();
        }

        Set<Long> articleId = articles.stream().map(item -> item.getContent().getId()).collect(Collectors.toSet());


        return articles.stream().map(article -> {
            Map<String, List<String>> highlightFields = article.getHighlightFields();
            if (!CollectionUtils.isEmpty(highlightFields.get("title"))) {
                article.getContent().setTitle(highlightFields.get("title").get(0));
            }
            if (!CollectionUtils.isEmpty(highlightFields.get("description"))) {
                article.getContent().setDescription(highlightFields.get("description").get(0));
            }
            SearchVO searchVO = new SearchVO();
            BeanUtils.copyProperties(article.getContent(), searchVO);
            return searchVO;
        }).collect(Collectors.toList());
    }
}
