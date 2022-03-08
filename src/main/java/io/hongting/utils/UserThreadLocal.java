package io.hongting.utils;

import io.hongting.entity.User;

/**
 * @author hongting
 * @create 2021 11 11 1:18 AM
 */
public class UserThreadLocal implements AutoCloseable{

    private UserThreadLocal(){

    }

    private static final ThreadLocal<User> THREAD_LOCAL = new ThreadLocal<>();

    public static void setUser(User user){
        THREAD_LOCAL.set(user);
    }

    public static User getUser(){
        return THREAD_LOCAL.get();
    }


    @Override
    public void close() throws Exception {
        THREAD_LOCAL.remove();
    }
}
