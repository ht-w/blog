package io.hongting.common.exception;

import io.hongting.common.ResultType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hongting
 * @create 2021 11 09 8:34 PM
 */
@Data
@NoArgsConstructor
public class  BlogException extends RuntimeException {


    private Integer code;
    private String message;

    public BlogException(Integer code){
        this.code = code;
    }


    public BlogException(String message){
        this.message = message;
    }


    public BlogException(Integer code, String message){
        this.code = code;
        this.message = message;
    }


    public BlogException(Integer code, String message, Throwable cause){
        super(cause);
        this.code = code;
        this.message = message;

    }

    public BlogException(ResultType resultType){
        this.code = resultType.getCode();
        this.message = resultType.getMessage();
    }

    public BlogException(ResultType resultType, Throwable cause){
        super(cause);
        this.code = resultType.getCode();
        this.message = resultType.getMessage();
    }
}