package com.jsjyz.hnnu.vo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class DetailVo {
    private String title;
    private Long id;
    private String question;
    private String tag;
    private Timestamp createTime;
    private String answer;
    private String[] image;
    private Integer detected;
}
