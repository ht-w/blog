package io.hongting.service.impl;

import io.hongting.constant.RedisConstant;
import io.hongting.entity.Post;
import io.hongting.mapper.PostMapper;
import io.hongting.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author hongting
 * @create 2021 12 14 12:05 PM
 */

@Component
@Slf4j
public class ArticleViewTask {


    @Autowired
    RedisTemplate<String, Object> redisTemplate;


    @Autowired
    PostService postService;


    // sec, min, hour, day, month, week ,year
    @Scheduled( cron = "0 0 2 * * ?") // every two hours
    @Transactional(rollbackFor = Exception.class)
    public void createHyperLog(){
        List<Long> allArticleId = postService.getAllArticleId();
        for (Long articleId : allArticleId) {
            String key = RedisConstant.ARTICLE_VIEW + articleId;
            Long view = redisTemplate.opsForHyperLogLog().size(key);
            if (view > 0){
                Post post = postService.getById(articleId);
                Long views = view +  post.getView();
                post.setView(views);
                boolean updated = postService.updateById(post);
                if (updated){
                    log.info("Updated view of articles is ：{}", views);
                    redisTemplate.delete(key);
                }

            }

        }
        log.info("Successfully updated the database");

    }
}
