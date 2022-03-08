package io.hongting.config;

import io.hongting.interceptor.LimitAccessRateInterceptor;
import io.hongting.interceptor.UserTokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author hongting
 * @create 2021 11 11 12:59 AM
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private UserTokenInterceptor userTokenInterceptor;

    @Autowired
    private LimitAccessRateInterceptor limitAccessRateInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(limitAccessRateInterceptor);
    }

}
