package io.hongting.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.hongting.entity.Role;

import java.util.List;


/**
 * @author hongting
 * @create 2022 01 19 11:45 PM
 */
public interface RoleService extends IService<Role> {


    List<String> loadAccountRoles(String username);

    Long getAccountRoleId(Long userId);
}
