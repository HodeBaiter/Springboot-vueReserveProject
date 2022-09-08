package com.jsjyz.hnnu.service;

import com.jsjyz.hnnu.pojo.User;
import com.jsjyz.hnnu.vo.PaginationVo;
import com.jsjyz.hnnu.vo.ResultResponse;

import java.util.List;


public interface UserService {
    User getPermissionsByAccount(String userName, String password);
    List<User> getAllUsers(PaginationVo paginationVo);
    ResultResponse insert(User user);
    ResultResponse update(User user);
    ResultResponse deleted(List<User> users);
    User userInfo(User user);
}
