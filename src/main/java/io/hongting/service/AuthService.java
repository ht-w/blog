package io.hongting.service;

import cn.hutool.http.HttpResponse;
import io.hongting.entity.User;

import javax.servlet.http.HttpServletResponse;

/**
 * @author hongting
 * @create 2022 01 27 4:42 PM
 */
public interface AuthService {



    String login (String username, String password);


}
