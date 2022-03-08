package io.hongting.utils.annotation;

import java.lang.annotation.*;

/**
 * @author hongting
 * @create 2021 12 13 10:35 PM
 */

@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface LimitAccessRate {


    int cycle() default 30;

    int number() default 1;

    String message() default "Do not spam the request";
}
