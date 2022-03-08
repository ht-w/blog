package io.hongting.interceptor;

import io.hongting.common.ResultType;
import io.hongting.common.exception.UserNotFoundException;
import io.hongting.constant.SystemConstant;
import io.hongting.entity.User;
import io.hongting.service.UserService;
import io.hongting.utils.UserThreadLocal;
import io.hongting.utils.annotation.AuthorizationNotRequired;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hongting
 * @create 2021 11 11 1:11 AM
 */

@Component
public class UserTokenInterceptor implements HandlerInterceptor {

//    @Autowired
//    private UserService userService;
//
//
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        //check whether the handler match the controller method
//        if (!(handler instanceof HandlerMethod)){
//            return true;
//        }
//        //check whether the controller method has @NoAuthorization annotation
//        if(((HandlerMethod) handler).hasMethodAnnotation(AuthorizationNotRequired.class)){
//            return true;
//        }
//        String token = request.getHeader(SystemConstant.REQUEST_HEADER);
//        if (StringUtils.isEmpty(token)){
//            throw new UserNotFoundException(ResultType.NO_TOKEN_ERROR.getMessage());
//        }
//
//        if(JwtUtils.checkToken(token)){
//            throw new UserNotFoundException(ResultType.INVALID_TOKEN_ERROR.getMessage());
//        }
//
//        Long userId = JwtUtils.getUserId(token);
//        User user = userService.getById(userId);
//        if(user==null){
//            throw new UserNotFoundException(ResultType.LOGIN_AUTH_ERROR.getMessage());
//        }
//
//        UserThreadLocal.setUser(user);
//        return true;
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        UserThreadLocal.remove();
//    }
}
