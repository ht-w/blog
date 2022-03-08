package io.hongting.utils.annotation;

import java.lang.annotation.*;

/**
 * @author hongting
 * @create 2021 11 11 1:06 AM
 */


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthorizationNotRequired {
}
