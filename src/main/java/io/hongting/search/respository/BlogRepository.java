package io.hongting.search.respository;

import io.hongting.search.entity.BlogSearchIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author hongting
 * @create 2021 12 20 8:05 PM
 */
public interface BlogRepository  extends ElasticsearchRepository<BlogSearchIndex, Integer > {


}
