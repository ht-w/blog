package io.hongting.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.hongting.entity.Role;
import io.hongting.mapper.RoleMapper;
import io.hongting.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hongting
 * @create 2022 01 19 11:42 PM


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hongting
 * @since 2022-01-19
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {


    @Override
    public List<String> loadAccountRoles(String username) {
        return  this.getBaseMapper().loadAccountRoles(username);
    }

    @Override
    public Long getAccountRoleId(Long userId) {
        return this.getBaseMapper().getAccountRoleId(userId);
    }


}
