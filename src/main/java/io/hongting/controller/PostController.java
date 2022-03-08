package io.hongting.controller;



import cn.hutool.core.util.PageUtil;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.usthe.sureness.provider.annotation.RequiresRoles;
import io.hongting.common.Result;
import io.hongting.common.exception.Assert;
import io.hongting.entity.Post;
import io.hongting.entity.User;
import io.hongting.entity.vo.PostVO;
import io.hongting.service.PostService;
import io.hongting.utils.annotation.ArticleView;
import io.hongting.utils.annotation.AuthorizationNotRequired;
import org.apache.ibatis.annotations.Param;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hongting
 * @since 2021-11-08
 */

@CrossOrigin
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;



    @GetMapping
    public Result listAll(){
        return Result.ok().data("list", postService.list());
    }


    @GetMapping("/{page}/{limit}")
    public Result listByPage(@PathVariable Long page, @PathVariable Long limit){
        Page<PostVO> pages =  postService.getPostVOList (page, limit );
        Map<String, Object> map = new HashMap<>();
        map.put("postList", pages.getRecords());
        map.put("total", pages.getTotal());
        return Result.ok().data(map);

    }

    @ArticleView
    @GetMapping("/{id}")
    public Result getPostById(@PathVariable Long id){
        Post post = postService.getById(id);

        if (post!=null){
            return Result.ok().data("post", post).message("Successfully retrieve the data");
        }else{
            return Result.error().message("Fail to retrieve the data");
        }
    }

    @GetMapping("/next/{id}")
    public Result getNextPostTitle (@PathVariable Long id){
        Post post  = postService.getNextTitleById(id);
        if (post!=null){
            return Result.ok().data("post", post).message("Successfully retrieve the data");
        }else{
            return Result.error().message("Fail to retrieve the data");
        }
    }



    @GetMapping("/prev/{id}")
    public Result getPrevPostTitle (@PathVariable Long id){
        Post post = postService.getPrevTitleById(id);
        if (post!=null){
            return Result.ok().data("post", post).message("Successfully retrieve the data");
        }else{
            return Result.error().message("Fail to retrieve the data");
        }
    }



    @GetMapping("/{page}/{limit}/{categoryId}")
    public Result listByPage(@PathVariable Long page, @PathVariable Long limit, @PathVariable Long categoryId){
        Page<PostVO> pages =  postService.getPostVOListByCategory (page, limit, categoryId );
        Map<String, Object> map = new HashMap<>();
        map.put("postList", pages.getRecords());
        map.put("total", pages.getTotal());
        return Result.ok().data(map);
    
    }


    @DeleteMapping("/{id}")
    public Result remove(@PathVariable Long id){
        boolean deleted = postService.removeById(id);
        if (deleted){
            return Result.ok().message("Successfully removed the post");
        }else{
            return Result.error().message("Fail to remove the post ");
        }
    }


    @PostMapping
    public Result save (@RequestBody Post post){
        if (postService.save(post)){
            return Result.ok().message("Successfully create new post");
        }else{
            return Result.error().message("Fail to create new post");
        }
    }


    @PutMapping
    public Result update (@RequestBody Post post){
        if (postService.updateById(post)){
            return Result.ok().message("Successfully update the post");
        }else{
            return Result.error().message("Fail to update the post");
        }
    }


}

