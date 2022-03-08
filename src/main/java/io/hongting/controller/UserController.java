package io.hongting.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.hongting.common.Result;
import io.hongting.common.ResultType;
import io.hongting.common.exception.LoginAcctExistedException;
import io.hongting.constant.SystemConstant;
import io.hongting.entity.User;

import io.hongting.entity.UserRoleBind;
import io.hongting.service.RoleService;
import io.hongting.service.UserRoleBindService;
import io.hongting.service.UserService;
import io.hongting.utils.annotation.AuthorizationNotRequired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hongting
 * @since 2021-11-08
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserRoleBindService userRoleBindService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;


    @GetMapping
    public Result listAll(){
        List<User> list = userService.list();
        List<User> userList  = list.stream().map(user -> {
            Long role = roleService.getAccountRoleId(user.getId());
            user.setRole(role);
            return user;
        }).collect(Collectors.toList());
        return Result.ok().data("list", userList);
    }



    @DeleteMapping("/{id}")
    public Result remove(@PathVariable Long id){
        boolean deleted = userService.removeById(id);
        if (deleted){
            return Result.ok().message("Successfully deleted");
        }else{
            return Result.error().message("Failed to delete");
        }
    }

    @PostMapping
    public Result save (@Valid @RequestBody User user){
        if (userService.saveUser(user)){
            return Result.ok().message("Successfully create new user");
        }else{
            return Result.error().message("Fail to create new user");
        }
    }

    @PutMapping
    public Result update (@RequestBody User user){
        boolean userUpdated = false;

        try {
            userUpdated = userService.updateUser(user);

        } catch (Exception e) {
            if (e instanceof DuplicateKeyException){
                throw new LoginAcctExistedException(ResultType.LOGIN_ACCT_EXISTED.getMessage());
            }
        }
        if (userUpdated){
            return Result.ok().message("Successfully updated");
        }else{
            return Result.error().message("Failed to update");
        }
    }


    @GetMapping("/{id}")
    public Result getUserById (@PathVariable Long id ){
        User user = userService.getById(id);
        if (user!=null){
            Long role = roleService.getAccountRoleId(user.getId());
            user.setRole(role);
            return Result.ok().message("Successfully retrieve the data").data("record", user);
        }else{
            return Result.error().message("Failed to retrieve the data");
        }
    }


    @GetMapping("/{page}/{limit}")
    public Result listByPage(@PathVariable Long page, @PathVariable Long limit){
        Page<User> pages = new Page<>(page, limit);
        userService.page(pages, null);
        List<User> list = pages.getRecords();
        List<User>  userList = list.stream().map(user -> {
            Long role = roleService.getAccountRoleId(user.getId());
            user.setRole(role);
            return user;
        }).collect(Collectors.toList());
        long total = pages.getTotal();
        Map <String, Object> map = new HashMap<>();
        map.put("userList", userList);
        map.put("total", total);
        return Result.ok().data(map);
    }



}

