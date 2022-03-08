package io.hongting.utils.annotation;

import java.lang.annotation.*;

/**
 * @author hongting
 * @create 2021 12 13 8:15 PM
 */



@Target ({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ArticleView {

    String description () default "";
}
