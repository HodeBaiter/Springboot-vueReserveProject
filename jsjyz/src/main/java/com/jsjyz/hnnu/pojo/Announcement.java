package com.jsjyz.hnnu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


@Data
public class Announcement {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private String markdown;
    private String title;
}
