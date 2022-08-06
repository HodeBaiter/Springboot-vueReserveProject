package com.jsjyz.hnnu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Contact {
    @TableId(value = "contactId",type = IdType.AUTO)
    private Long contactId;
    private String contactName;
    private String contactMessage;
    private String contactEmail;
}
