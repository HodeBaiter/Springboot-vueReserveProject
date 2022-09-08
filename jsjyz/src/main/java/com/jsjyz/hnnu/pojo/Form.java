package com.jsjyz.hnnu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
    private Timestamp createTime;
    private String answer;
    private String college;
    private String reserveTime;
    private String image;
    private Integer isArchived;
    private String tag;
    private String phoneNumber;
    @TableLogic
    private Integer deleted;
}
