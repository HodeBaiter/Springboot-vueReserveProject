package com.jsjyz.hnnu.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jsjyz.hnnu.mapper.UserMapper;
import com.jsjyz.hnnu.pojo.User;
import com.jsjyz.hnnu.service.UserService;
import com.jsjyz.hnnu.vo.ErrorCode;
import com.jsjyz.hnnu.vo.ResultResponse;
import io.netty.util.internal.ObjectUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User getPermissionsByAccount(String userName,String email, String password) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (userName != null) {
            userLambdaQueryWrapper.eq(User::getUserName,userName);
        }
        if (email != null) {
            userLambdaQueryWrapper.eq(User::getEmail,email);
        }
        userLambdaQueryWrapper.select(User::getUserId,User::getPermissions);
        userLambdaQueryWrapper.eq(User::getDeleted,0);
        userLambdaQueryWrapper.eq(User::getPassword,password);
        User user = userMapper.selectOne(userLambdaQueryWrapper);
        return user;
    }
    @Override
    public List<User> getAllUsers() {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getDeleted,0);
        userLambdaQueryWrapper.select(User::getUserId,User::getUserName,User::getPermissions,User::getDeleted,User::getEmail);
        List<User> users = userMapper.selectList(userLambdaQueryWrapper);
        return users;
    }
    @Override
    public ResultResponse insert(User user){
        user.setPermissions("1");
        user.setDeleted(1);
        int insert = userMapper.insert(user);
        if (insert == 0){
            return new ResultResponse(11000,"failed");
        }
        return new ResultResponse(10000,"success");
    }

    @Override
    public ResultResponse update(List<User> users) {

        for (User user : users) {
            int i = updateItem(user);
            if (i == 0){
                return new ResultResponse(11000,"failed",user);
            }
        }
        return new ResultResponse(ErrorCode.SUCCESS);
    }
    public int updateItem(User user){
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getDeleted,0);
        userLambdaQueryWrapper.eq(User::getUserId,user.getUserId());
        int i = userMapper.update(user,userLambdaQueryWrapper);
        return i;
    }
    @Override
    public ResultResponse deleted(List<User> users) {
        User errorUser = new User();
        users.forEach(user -> {
            user.setDeleted(1);
            int i = userMapper.deleteById(user);
            if (i == 0){
                BeanUtils.copyProperties(user,errorUser);
                return;
            }

        });
        return new ResultResponse(ErrorCode.SUCCESS);
    }

    @Override
    public User userInfo(User user) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.select(User::getUserId,User::getUserName,User::getPermissions);
        userLambdaQueryWrapper.eq(User::getUserId, user.getUserId());
        userLambdaQueryWrapper.eq(User::getDeleted,0);
        return userMapper.selectOne(userLambdaQueryWrapper);
    }

    @Override
    public User getUserByName(User user) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.select(User::getUserId);
        userLambdaQueryWrapper.eq(User::getUserId, user.getUserName());
        userLambdaQueryWrapper.eq(User::getDeleted,0);
        return userMapper.selectOne(userLambdaQueryWrapper);
    }
}
