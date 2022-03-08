package io.hongting.service.impl;

import io.hongting.constant.RedisConstant;
import io.hongting.service.LimitAccessRateService;
import io.hongting.utils.annotation.LimitAccessRate;
import io.lettuce.core.Limit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author hongting
 * @create 2021 12 13 10:40 PM
 */

@Service
public class LimitAccessRateImpl implements LimitAccessRateService {


    @Resource
    RedisTemplate <String, Integer> redisTemplate;


    @Override
    public Boolean limit(String ipAddr, String uri, LimitAccessRate limitAccessRate)  {
        String key = RedisConstant.ACCESS_RATE + ipAddr  +  ":" + uri;
        if (!redisTemplate.hasKey(key)) {
            redisTemplate.opsForValue().set(key,1,limitAccessRate.cycle(), TimeUnit.SECONDS);

        } else {
            redisTemplate.opsForValue().increment(key,1);
            if (redisTemplate.opsForValue().get(key) > limitAccessRate.number()){
                return false;
            }
        }
        return true;

    }
}
