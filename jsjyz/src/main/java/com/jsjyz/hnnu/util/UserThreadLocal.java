package com.jsjyz.hnnu.util;

import com.jsjyz.hnnu.pojo.User;

public class UserThreadLocal {
    private UserThreadLocal(){
    }
    private static final ThreadLocal<User> threadLocal = new ThreadLocal<>();
    public static void put(User user){
        threadLocal.set(user);
    }
    public static User get(){
        return threadLocal.get();
    }
    public static void  remove(){
        threadLocal.remove();
    }
}
