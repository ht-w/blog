package io.hongting.controller;


import io.hongting.common.Result;
import io.hongting.service.RoleService;
import io.hongting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hongting
 * @since 2022-01-19
 */
@RestController
@RequestMapping("/role")
public class RoleController {


    @Autowired
    private RoleService roleService;

    @GetMapping
    public Result listAll (){
        return Result.ok().data("roleList", roleService.list());
    }
}

