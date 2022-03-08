package io.hongting.common.exception;


import com.usthe.sureness.processor.exception.UnauthorizedException;
import io.hongting.common.Result;
import io.hongting.common.ResultType;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;

/**
 * @author hongting
 * @create 2021 11 08 6:47 PM
 */
@Slf4j
@RestControllerAdvice
public class UnifiedExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result handleException (Exception e){
        log.error(e.getMessage(), e);
        return Result.error();
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    public Result handleException (BadSqlGrammarException e) {
        log.error(e.getMessage(), e);
        return Result.createResult(ResultType.SQL_GRAMMAR_ERROR);
    }

    @ExceptionHandler(LoginAcctExistedException.class)
    public Result handleException (LoginAcctExistedException e) {
        log.error(e.getMessage(), e);
        return Result.createResult(ResultType.LOGIN_ACCT_EXISTED);
    }

    @ExceptionHandler(BlogException.class)
    public Result handleException (BlogException e){
        log.error(e.getMessage(), e);
        return Result.error().message(e.getMessage()).code(e.getCode());
    }


    @ExceptionHandler(UserNotFoundException.class)
    public Result handleException (UserNotFoundException e){
        log.error(e.getMessage(), e);
        return Result.error().message(e.getMessage());
    }

    @ExceptionHandler( MethodArgumentNotValidException.class)
    public Result handleException(MethodArgumentNotValidException e)  {
        log.error(e.getMessage(), e);
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        return Result.error().message(objectError.getDefaultMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public Result handleException (UnauthorizedException e) {
            log.error(e.getMessage(), e);
            return Result.error(ResultType.NO_PERMISSION);
    }










    /**
     * Controller上一层相关异常
     */
    @ExceptionHandler({
            NoHandlerFoundException.class,
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            MissingPathVariableException.class,
            MissingServletRequestParameterException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class,
            HttpMediaTypeNotAcceptableException.class,
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            MissingServletRequestPartException.class,
            AsyncRequestTimeoutException.class
    })
    public Result handleServletException(Exception e) {
        log.error(e.getMessage(), e);
        //SERVLET_ERROR(-102, "servlet请求异常"),
        return Result.error().message(ResultType.SERVLET_ERROR.getMessage()).code(ResultType.SERVLET_ERROR.getCode());
    }

}