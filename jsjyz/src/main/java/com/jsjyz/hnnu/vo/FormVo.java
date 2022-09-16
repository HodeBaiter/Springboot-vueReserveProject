package com.jsjyz.hnnu.vo;

import lombok.Data;

import java.lang.reflect.Array;

@Data
public class FormVo {
    private Long id;
    private String name;
    private String college;
    private String time;
    private String question;
    private String image;
    private String createTime;
}
