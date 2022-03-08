package io.hongting.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.hongting.entity.Role;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hongting
 * @create 2022 01 19 11:44 PM
 */
@Mapper
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    List<String> loadAccountRoles(String username);

    Long getAccountRoleId(Long userId);
}
