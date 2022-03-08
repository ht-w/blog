package io.hongting.search.service;

import io.hongting.search.entity.vo.SearchVO;

import java.util.List;

/**
 * @author hongting
 * @create 2021 12 22 2:52 PM
 */
public interface SearchService {

    List<SearchVO> searchWithHighlight(String keyword);
}
