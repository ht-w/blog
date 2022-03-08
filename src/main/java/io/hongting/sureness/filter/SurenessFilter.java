package io.hongting.sureness.filter;

import cn.hutool.json.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.usthe.sureness.mgt.SurenessSecurityManager;
import com.usthe.sureness.processor.exception.*;
import com.usthe.sureness.subject.SubjectSum;
import com.usthe.sureness.util.SurenessContextHolder;
import io.hongting.common.Result;
import io.hongting.common.ResultType;
import io.hongting.common.exception.BlogException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.BindException;
import java.util.Collections;
import java.util.Map;

/**
 * @author hongting
 * @create 2022 01 25 1:33 AM
 */

@Slf4j
@Order(1)
@WebFilter(filterName = "SurenessFilter", urlPatterns = "/*", asyncSupported = true)
public class SurenessFilter implements Filter {



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        try {
            SubjectSum subject = SurenessSecurityManager.getInstance().checkIn(servletRequest);
            // You can consider using SurenessContextHolder to bind subject in threadLocal
            // if bind, please remove it when end
            if (subject != null) {
                SurenessContextHolder.bindSubject(subject);
            }
        } catch (IncorrectCredentialsException | UnknownAccountException | ExpiredCredentialsException e1) {
            log.error("this request account info is illegal, {}", e1.getMessage());
            responseWrite(Result.error().message("this request account info is illegal") ,servletResponse);
            return;
        } catch (DisabledAccountException | ExcessiveAttemptsException e2 ) {
            log.debug("the account is disabled, {}", e2.getMessage());
            responseWrite(Result.error().message("the account is disabled") ,servletResponse);
            return;
        } catch (UnauthorizedException e5) {
            log.debug("this account can not access this resource, {}", e5.getMessage());
            responseWrite(Result.error().message("this account can not access this resource") ,servletResponse);
            return;
        } catch (RuntimeException e) {
            log.error("other exception happen: ", e);
            responseWrite(Result.error().message(e.getMessage()) ,servletResponse);
            return;
        }
        try {
            // if ok, doFilter and add subject in request
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            SurenessContextHolder.clear();
        }
    }


    protected void responseWrite( Result result, ServletResponse response) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.append("message", result.getMessage());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer!= null) {
                writer.close();
            }
        }
    }
}