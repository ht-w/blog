package io.hongting.common;

import com.baomidou.mybatisplus.extension.api.R;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hongting
 * @create 2021 11 03 3:47 PM
 */

@Data
public class Result {

    private Integer code;
    private String message;
    private Map<String,Object> data = new HashMap<>();

    private Result(){

    }

    public static Result ok (){
        Result result = new Result();
        result.setCode(ResultType.SUCCESS.getCode());
        result.setMessage(ResultType.SUCCESS.getMessage());
        return result;
    }
    public static Result error (){
        Result result = new Result();
        result.setCode(ResultType.ERROR.getCode());
        result.setMessage(ResultType.ERROR.getMessage());
        return result;
    }


    public static Result createResult (ResultType resultType){
        Result result = new Result();
        result.setCode(resultType.getCode());
        result.setMessage(resultType.getMessage());
        return result;
    }
    public static Result error (ResultType resultType){
        Result result = new Result();
        result.setCode(resultType.getCode());
        result.setMessage(resultType.getMessage());
        return result;
    }

    public Result data (String key, Object value ){
        this.data.put(key, value);
        return this;
    }

    public  Result data (Map<String,Object> map ){
        this.setData(map);
        return this;
    }


    public Result message (String message){
        this.setMessage(message);
        return this;
    }

    public Result code (Integer code){
        this.setCode(code);
        return this;
    }
}
