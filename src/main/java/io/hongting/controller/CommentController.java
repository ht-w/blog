package io.hongting.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.hongting.common.Result;
import io.hongting.entity.Category;
import io.hongting.entity.Comment;
import io.hongting.entity.vo.CommentVO;
import io.hongting.entity.vo.PostVO;
import io.hongting.mapper.CommentMapper;
import io.hongting.service.CommentService;
import io.hongting.utils.annotation.AuthorizationNotRequired;
import io.hongting.utils.annotation.LimitAccessRate;
import org.apache.logging.log4j.message.ReusableMessage;

import org.elasticsearch.ingest.IngestStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hongting
 * @since 2021-11-19
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @LimitAccessRate(cycle = 30,  message = "Only one comment can be submitted within 30 seconds")
    @AuthorizationNotRequired
    @PostMapping
    public Result save (@RequestBody Comment comment) throws MessagingException {
        commentService.saveComment(comment);
        return Result.ok().message("Successfully posted the comment");
    }


    @PutMapping
    public Result update (@RequestBody Comment comment){
        if (commentService.updateById(comment)){
            return Result.ok().message("Successfully edited the comment");
        }else{
            return Result.error().message("Fail to edit the comment");
        }
    }



    @DeleteMapping("/{id}")
    public Result remove (@PathVariable Long id){
        if(commentService.removeById(id)){
            return Result.ok().message("Successfully deleted the comment");
        }else{
            return Result.error().message("Fail to delete the comment");
        }
    }


    @GetMapping("/{id}")
    public Result getCommentById (@PathVariable Long id){
        Comment comment = commentService.getById(id);
        if (comment!=null){
            return Result.ok().message("Successfully retrieve the data").data("comment", comment);
        }else{
            return Result.error().message("Fail to retrieve the data");
        }
    }

    @GetMapping
    public Result listAll() {
        return Result.ok().data("commentList", commentService.list());
    }


    @GetMapping("/foreign/{id}")
    public Result getCommentsByPost(@PathVariable Long id){
        Map <String,Object> map = new HashMap<>();
        map.put("commentList", commentService.getCommentsByPost(id));
        map.put("total", commentService.count(new LambdaQueryWrapper<Comment>().eq(Comment::getForeignId, id )));
        return Result.ok().data("data", map);
    }



    @GetMapping("/{page}/{limit}")
    public Result listByPage(@PathVariable Long page, @PathVariable Long limit){
        Page<CommentVO> pages =  commentService.getCommentVOList (page, limit );
        Map<String, Object> map = new HashMap<>();
        map.put("commentList", pages.getRecords());
        map.put("total", pages.getTotal());
        return Result.ok().data(map);
    }



    @GetMapping("/{page}/{limit}/{postId}")
    public Result listByPage(@PathVariable Long page, @PathVariable Long limit, @PathVariable Long postId){
        Page<CommentVO> pages =  commentService.getCommentVOListByPost (page, limit, postId);
        Map<String, Object> map = new HashMap<>();
        map.put("commentList", pages.getRecords());
        map.put("total", pages.getTotal());
        return Result.ok().data(map);
    }


}

