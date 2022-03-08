package io.hongting.common.exception;

import io.hongting.common.ResultType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * @author hongting
 * @create 2021 11 09 8:36 PM
 */
@Slf4j
public class Assert {

    public static void notNull(Object obj, ResultType resultType) {
        if (obj == null) {
            log.info("Object is null");
            throw new BlogException(resultType);
        }
    }

    public static void isTrue(boolean expression, ResultType resultType) {
        if (!expression) {
            log.info("Fail");
            throw new BlogException(resultType);
        }
    }

    public static void isNull(Object obj, ResultType resultType) {
        if (obj != null) {
            log.info("Object is not null");
            throw new BlogException(resultType);
        }
    }

    public static void notEquals(Object m1, Object m2, ResultType resultType) {
        if (m1.equals(m2)) {
            log.info("equals...............");
            throw new BlogException(resultType);
        }
    }


    public static void equals(Object m1, Object m2, ResultType resultType) {
        if (!m1.equals(m2)) {
            log.info("not equals...............");
            throw new BlogException(resultType);
        }
    }


    public static void notEmpty(String s, ResultType resultType) {
        if (StringUtils.isEmpty(s)) {
            log.info("not equals...............");
            throw new BlogException(resultType);
        }
    }
}
