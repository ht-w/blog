package io.hongting.search.controller;

import io.hongting.common.Result;
import io.hongting.common.ResultType;
import io.hongting.entity.Post;
import io.hongting.search.entity.vo.SearchVO;
import io.hongting.search.service.SearchService;
import io.hongting.service.PostService;
import io.hongting.utils.annotation.AuthorizationNotRequired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hongting
 * @create 2021 12 21 3:21 PM
 */

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    SearchService searchService;


    @AuthorizationNotRequired
    @GetMapping
    public Result searchArticles(@RequestParam String keyword) {
        List<SearchVO> searchVO = searchService.searchWithHighlight(keyword);
        return Result.ok().data("searchData", searchVO);
    }

}
