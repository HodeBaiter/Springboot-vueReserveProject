package com.jsjyz.hnnu.controller;

import com.jsjyz.hnnu.service.LoginService;
import com.jsjyz.hnnu.vo.LoginParam;
import com.jsjyz.hnnu.vo.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private LoginService loginService;
    @PostMapping("/login")
    public ResultResponse login(@RequestBody LoginParam loginParam){
        return loginService.login(loginParam);
    }
}
