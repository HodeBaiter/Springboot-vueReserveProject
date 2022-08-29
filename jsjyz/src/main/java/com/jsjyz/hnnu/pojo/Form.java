package com.jsjyz.hnnu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class Form {
    private String name;
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private String title;
    private String question;
    private String status;
    private Timestamp createTime;
    private String answer;
    private String college;
    private String reserveTime;
    private String image;
    private Integer isArchived;

}
