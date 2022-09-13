package com.jsjyz.hnnu.vo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorCode {
    SUCCESS(10000,"成功"),
    PARAMS_ERROR(10001,"参数错误"),
    ACCOUNT_PWD_NOT_FOUND(10002,"账号或密码错误"),
    NO_PERMISSIONS(10003,"无权限"),
    SESSION_TIMEOUT(10004,"会话超时"),
    NO_LOGIN(10005,"未登录"),
    FAILED(11000,"操作失败");
    private int code;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
