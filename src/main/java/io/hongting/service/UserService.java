package io.hongting.service;

import cn.hutool.system.UserInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.usthe.sureness.provider.SurenessAccount;
import io.hongting.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;


import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hongting
 * @since 2021-11-08
 */
public interface UserService extends IService<User> {


    boolean saveUser(User user);


    boolean grantAuthority(String username, Long roleId);


    SurenessAccount loadAccount(String username);

    boolean updateUser(User user);
}