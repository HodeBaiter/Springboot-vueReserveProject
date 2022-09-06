package com.jsjyz.hnnu.service;

import com.jsjyz.hnnu.vo.LoginParam;
import com.jsjyz.hnnu.vo.ResultResponse;


public interface LoginService {
    ResultResponse login(LoginParam loginParam);
}
