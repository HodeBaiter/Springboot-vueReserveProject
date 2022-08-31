package com.jsjyz.hnnu.vo.ArchivesVo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ArchivesListVo {
    private Long id;
    private Timestamp createTime;
    private String title;
    private String status;
    private String question;
    private String isArchived;
}
