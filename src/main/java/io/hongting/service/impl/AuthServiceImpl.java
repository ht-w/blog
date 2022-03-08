package io.hongting.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.usthe.sureness.util.JsonWebTokenUtil;
import com.usthe.sureness.util.Md5Util;
import io.hongting.common.ResultType;
import io.hongting.common.exception.Assert;
import io.hongting.common.exception.BlogException;

import io.hongting.entity.User;
import io.hongting.mapper.UserMapper;


import io.hongting.service.AuthService;
import io.hongting.service.RoleService;
import io.hongting.service.UserService;

import io.hongting.utils.IpAddressUtils;
import io.hongting.utils.UserThreadLocal;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * @author hongting
 * @create 2022 01 27 4:43 PM
 */

@Transactional
@Service
public class AuthServiceImpl implements AuthService {


    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleService roleService;


    @Override
    public String login(String username, String password) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User user = userMapper.selectOne(queryWrapper);
        Assert.notNull(user, ResultType.LOGIN_PASSWORD_ERROR);
        if (!user.getPassword().equals(Md5Util.md5(password + user.getSalt()))) {
            throw new BlogException(ResultType.LOGIN_PASSWORD_ERROR.getMessage());
        }
        List<String> ownRole = roleService.loadAccountRoles(user.getUsername());
        String jwt = JsonWebTokenUtil.issueJwt(UUID.randomUUID().toString(), user.getUsername(),
                "blog-server", 3600L, ownRole);
        String ipAddr = IpAddressUtils.getIpAddr();
        if (ipAddr!=null) {
            redisTemplate.opsForValue().set(ipAddr, jwt, 3600L, TimeUnit.SECONDS);
        }
        return jwt;
    }
}
