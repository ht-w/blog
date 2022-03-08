package io.hongting.interceptor;

import io.hongting.common.ResultType;
import io.hongting.common.exception.BlogException;
import io.hongting.service.LimitAccessRateService;
import io.hongting.utils.annotation.AuthorizationNotRequired;
import io.hongting.utils.annotation.LimitAccessRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hongting
 * @create 2021 12 13 10:42 PM
 */

@Component
public class LimitAccessRateInterceptor implements HandlerInterceptor {

    @Autowired
    private LimitAccessRateService limitAccessRateService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //determine whether the request belong to method request
        if (handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            LimitAccessRate limitAccessRate = handlerMethod.getMethodAnnotation(LimitAccessRate.class);
            if (limitAccessRate == null){
                return true;
            }
            String ipAddr = request.getRemoteAddr();
            String uri = request.getRequestURI();
            if (!limitAccessRateService.limit (ipAddr, uri, limitAccessRate)){
                throw new BlogException(limitAccessRate.message());
            }
        }
        return true;
    }
}
