package com.jsjyz.hnnu.vo;

import lombok.Data;

@Data
public class ResultResponse {
    private Integer code;
    private String message;
    public ResultResponse(Integer code,String message){
    this.code = code;
    this.message = message;
    }

}
