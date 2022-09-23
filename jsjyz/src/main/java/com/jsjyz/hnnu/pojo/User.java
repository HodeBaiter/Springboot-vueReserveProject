package com.jsjyz.hnnu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

@Data
public class User {
    @TableId(value = "user_id",type = IdType.AUTO)
    private Long userId;
    private String userName;
    private String email;
    private String permissions;
    private String password;
    @TableLogic
    private Integer deleted;
}
