package com.jsjyz.hnnu.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jsjyz.hnnu.mapper.UserMapper;
import com.jsjyz.hnnu.pojo.User;
import com.jsjyz.hnnu.service.UserService;
import com.jsjyz.hnnu.vo.ErrorCode;
import com.jsjyz.hnnu.vo.PaginationVo;
import com.jsjyz.hnnu.vo.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User getPermissionsByAccount(String userName, String password) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.select(User::getUserId,User::getPermissions);
        userLambdaQueryWrapper.eq(User::getDeleted,0);
        userLambdaQueryWrapper.eq(User::getUserName,userName);
        userLambdaQueryWrapper.eq(User::getPassword,password);
        User user = userMapper.selectOne(userLambdaQueryWrapper);
        return user;
    }
    @Override
    public List<User> getAllUsers(PaginationVo paginationVo) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.select(User::getUserId,User::getUserName,User::getPermissions);
        userLambdaQueryWrapper.eq(User::getDeleted,0);
        Page<User> userPage = new Page<>(paginationVo.getPageNum(), paginationVo.getPageSize());
        IPage<User> users = userMapper.selectPage(userPage,userLambdaQueryWrapper);
        List<User> records = users.getRecords();
        return records;
    }
    @Override
    public ResultResponse insert(User user){
        int insert = userMapper.insert(user);
        if (insert == 0){
            return new ResultResponse(11000,"failed");
        }
        return new ResultResponse(10000,"success");
    }

    @Override
    public ResultResponse update(User user) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getDeleted,0);
        int i = userMapper.update(user,userLambdaQueryWrapper);
        if (i == 0){
            return  new ResultResponse(ErrorCode.FAILED);
        }
        return new ResultResponse(ErrorCode.SUCCESS);
    }

    @Override
    public ResultResponse deleted(List<User> users) {
        users.forEach(user -> user.setDeleted(1));
        boolean b = updateBatchById(users);
        if (!b){
            return  new ResultResponse(ErrorCode.FAILED);
        }
        return new ResultResponse(ErrorCode.SUCCESS);
    }

    @Override
    public User userInfo(User user) {
        Long id = user.getUserId();
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.select(User::getUserId,User::getUserName,User::getPermissions);
        userLambdaQueryWrapper.eq(User::getDeleted,0);
        return userMapper.selectOne(userLambdaQueryWrapper);
    }


}
