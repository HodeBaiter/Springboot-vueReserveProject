package com.jsjyz.hnnu.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResultResponse {


    private Integer code;
    private String message;
    private Object data;
    public ResultResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public ResultResponse(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
