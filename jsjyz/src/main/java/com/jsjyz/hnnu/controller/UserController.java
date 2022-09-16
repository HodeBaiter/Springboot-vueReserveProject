package com.jsjyz.hnnu.controller;

import com.jsjyz.hnnu.pojo.User;
import com.jsjyz.hnnu.service.LoginService;
import com.jsjyz.hnnu.service.UserService;
import com.jsjyz.hnnu.vo.ErrorCode;
import com.jsjyz.hnnu.vo.LoginParam;
import com.jsjyz.hnnu.vo.PaginationVo;
import com.jsjyz.hnnu.vo.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public ResultResponse login(@RequestBody LoginParam loginParam){
        return loginService.login(loginParam);
    }
    @PostMapping("/register")
    public  ResultResponse register(@RequestBody LoginParam loginParam){
        return loginService.register(loginParam);
    }
    //<=======user========>
    @PostMapping("/user/info")
    public ResultResponse getInfo(@RequestHeader("Authorization") User user){
        User userInfo = userService.userInfo(user);
        return new ResultResponse(ErrorCode.SUCCESS,userInfo);
    }
    //<=======admin=======>
    @PostMapping("/admin/user/insert")
    public ResultResponse insert(@RequestBody User user){
        return userService.insert(user);

    }
    @PostMapping("/admin/user/update")
    public ResultResponse update(@RequestBody List<User> users){
        return userService.update(users);
    }
    @PostMapping("/admin/user/delete")
    public ResultResponse delete(@RequestBody List<User> users){
        return userService.deleted(users);
    }
    @GetMapping ("/admin/user/getUser")
    public ResultResponse getUser() {
        return new ResultResponse(ErrorCode.SUCCESS,userService.getAllUsers()) ;
    }
}
