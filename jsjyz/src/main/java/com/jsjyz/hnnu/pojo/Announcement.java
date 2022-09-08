package com.jsjyz.hnnu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;


@Data
public class Announcement {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private String markdown;
    private String title;
    @TableLogic
    private Integer deleted;
    private Long updateTime;
}
