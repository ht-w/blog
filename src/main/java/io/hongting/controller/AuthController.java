package io.hongting.controller;



import io.hongting.common.Result;
import io.hongting.entity.vo.LoginVO;
import io.hongting.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;


/**
 * @author hongting
 * @create 2022 01 21 3:12 AM
 */

@RestController
@RequestMapping("/auth")
public class AuthController {



    @Autowired
    AuthService authService;



    @PostMapping("/login")
    public Result login (@RequestBody LoginVO loginVO) {
        String jwt = authService.login(loginVO.getUsername(), loginVO.getPassword());
        return Result.ok().data("token", jwt).message("Successfully login");
    }



}
