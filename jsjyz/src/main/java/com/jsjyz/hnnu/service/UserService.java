package com.jsjyz.hnnu.service;

import com.jsjyz.hnnu.pojo.User;

public interface UserService {
    User getUserByAccount(String userName,String password);
}
