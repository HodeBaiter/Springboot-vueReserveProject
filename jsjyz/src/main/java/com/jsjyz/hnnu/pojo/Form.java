package com.jsjyz.hnnu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Form {
    private String name;
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private String title;
    private String question;
    private String status;
    private Long createTime;
    private String answer;
    private String college;
    private String reserveTime;
    private String image;
    private Integer isArchived;
}
