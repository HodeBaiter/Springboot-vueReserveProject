package com.jsjyz.hnnu.pojo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

@Data
public class KeyWords {
    private Long id;
    private String keyWords;
    private Long articleId;
    @TableLogic
    private Integer deleted;

}
