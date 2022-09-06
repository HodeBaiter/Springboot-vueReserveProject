package com.jsjyz.hnnu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {
    @TableId(value = "user_id",type = IdType.AUTO)
    private Long userId;
    private String userName;
    private String permissions;
    private String password;
}
