package io.hongting.config;

import io.hongting.constant.RedisConstant;
import io.hongting.utils.IpAddressUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;


/**
 * @author hongting
 * @create 2021 12 13 8:17 PM
 */



@Aspect
@Configuration
@Slf4j
public class ArtivleViewAspect {


    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Pointcut ("@annotation(io.hongting.utils.annotation.ArticleView)")
    public void ArticleViewAspect(){

    }


    @Around ("ArticleViewAspect()")
    public Object around (ProceedingJoinPoint joinPoint){
        Object [] object = joinPoint.getArgs();
        Object articleId = object[0];
        log.info ("articleId:{}", articleId);
        Object obj = null;
        try{
            String ipAddr = IpAddressUtils.getIpAddr();
            log.info ("IP Address: {}", ipAddr);
            String key = RedisConstant.ARTICLE_VIEW + articleId;
            if (ipAddr!=null){
                Long count =  redisTemplate.opsForHyperLogLog().add(key, ipAddr);
                if (count == 0){
                    log.info ("this IP Address:{} has been added" , ipAddr);
                }
                obj  = joinPoint.proceed();

            }
        }catch (Throwable e){
            e.printStackTrace();
        }
        return obj;
    }
}
