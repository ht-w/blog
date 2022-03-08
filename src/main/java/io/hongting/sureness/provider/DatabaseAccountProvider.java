package io.hongting.sureness.provider;

import com.usthe.sureness.provider.SurenessAccount;
import com.usthe.sureness.provider.SurenessAccountProvider;
import io.hongting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author hongting
 * @create 2022 01 24 2:56 PM
 */
@Component
public class DatabaseAccountProvider implements SurenessAccountProvider {

    @Autowired
    UserService userService;

    @Override
    public SurenessAccount loadAccount(String username) {
        return userService.loadAccount(username);
    }
}