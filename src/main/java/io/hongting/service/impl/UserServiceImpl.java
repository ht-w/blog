package io.hongting.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.usthe.sureness.provider.DefaultAccount;
import com.usthe.sureness.provider.SurenessAccount;
import com.usthe.sureness.util.Md5Util;
import com.usthe.sureness.util.SurenessCommonUtil;
import io.hongting.common.ResultType;
import io.hongting.common.exception.Assert;
import io.hongting.common.exception.LoginAcctExistedException;

import io.hongting.entity.User;
import io.hongting.entity.UserRoleBind;
import io.hongting.mapper.UserMapper;
import io.hongting.service.RoleService;
import io.hongting.service.UserRoleBindService;
import io.hongting.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hongting
 * @since 2021-11-08
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Autowired
    private UserRoleBindService userRoleBindService;


    @Autowired
    private RoleService roleService;


    @Autowired
    private  RedisTemplate<String, Object> redisTemplate;



    @Override
    public boolean saveUser (User user) {
        checkIfUserNameUnique(user.getUsername());
        Long roleId = user.getRole();
        String salt = SurenessCommonUtil.getRandomString(6);
        String encryptedPassword = Md5Util.md5(user.getPassword() + salt);
        user.setPassword(encryptedPassword);
        user.setSalt(salt);
        boolean save = this.save(user);
        if (save){
            return this.grantAuthority(user.getUsername(), roleId);
        }else{
            return false;
        }
    }

//
//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public User login(LoginVO loginVo, HttpServletResponse httpResponse) {
//        String username = loginVo.getUsername();
//        User user = this.getOne(new QueryWrapper<User>().eq("username", username));
//        Assert.notNull(user, ResultType.LOGIN_ACCT_ERROR);
//
//        byte[] bytes = loginVo.getPassword().getBytes(StandardCharsets.UTF_8);
//        byte[] salt = "abcdefg".getBytes(StandardCharsets.UTF_8);
//        try {
//            byte[] scrypt = Scrypt.scrypt(bytes, salt, Scrypt.COST, Scrypt.BLOCKSIZE, Scrypt.PARALLEL, 32);
//            String encryptedPassword = HashUtils.encodeBase64(scrypt);
//            Assert.equals(encryptedPassword, user.getPassword(), ResultType.LOGIN_PASSWORD_ERROR );
//
//            //generate JWT Token
//            String token = JwtUtils.generateToken(user.getId(), user.getUsername());
//            httpResponse.setHeader(SystemConstant.REQUEST_HEADER, token);
//            httpResponse.setHeader("Access-Control-Expose-Headers", SystemConstant.REQUEST_HEADER);
//
//            return user;
//
//        } catch (GeneralSecurityException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }




    @Override
    public boolean grantAuthority (String username, Long roleId){
        User user = this.getOne(new QueryWrapper<User>().eq("username", username));
        Assert.notNull(user, ResultType.LOGIN_ACCT_ERROR);
        Long userId = user.getId();
        UserRoleBind userRoleBind = new UserRoleBind();
        userRoleBind.setUserId(userId);
        userRoleBind.setRoleId(roleId);
        userRoleBindService.save(userRoleBind);
        return true;
    }


    @Override
    public SurenessAccount loadAccount(String username) {
        User user = this.getOne(new QueryWrapper<User>().eq("username", username));
        DefaultAccount.Builder accountBuilder = DefaultAccount.builder(username)
                .setPassword(user.getPassword())
                .setSalt(user.getSalt())
                .setDisabledAccount(false);
        List<String> roles = roleService.loadAccountRoles(username);
        if (roles != null){
            accountBuilder.setOwnRoles(roles);
        }
        return accountBuilder.build();
    }

    @Override
    public boolean updateUser(User user) {
        LambdaQueryWrapper<UserRoleBind> queryWrapper = new LambdaQueryWrapper<UserRoleBind>().eq(UserRoleBind::getUserId, user.getId());
        UserRoleBind userRoleBind = userRoleBindService.getBaseMapper().selectOne(queryWrapper);
        Long role = user.getRole();
        userRoleBind.setRoleId(role);
        return this.updateById(user) && userRoleBindService.updateById(userRoleBind);


    }


    private void checkIfUserNameUnique(String username){
        Integer count = baseMapper.selectCount(new QueryWrapper<User>().eq("username", username));
        if (count > 0){
            throw new LoginAcctExistedException(ResultType.LOGIN_ACCT_EXISTED.getMessage());
        }
    }


}
