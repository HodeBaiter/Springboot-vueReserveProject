package com.jsjyz.hnnu.vo;

import lombok.Data;

@Data
public class LoginParam {
    private String email;
    private String userName;
    private String password;
    private String verCode;
}
