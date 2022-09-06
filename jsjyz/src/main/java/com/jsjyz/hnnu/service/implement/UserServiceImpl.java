package com.jsjyz.hnnu.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jsjyz.hnnu.mapper.UserMapper;
import com.jsjyz.hnnu.pojo.User;
import com.jsjyz.hnnu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User getUserByAccount(String userName, String password) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.select(User::getUserId,User::getPermissions);
        userLambdaQueryWrapper.eq(User::getUserName,userName);
        userLambdaQueryWrapper.eq(User::getPassword,password);
        User user = userMapper.selectOne(userLambdaQueryWrapper);
        return user;
    }
}
