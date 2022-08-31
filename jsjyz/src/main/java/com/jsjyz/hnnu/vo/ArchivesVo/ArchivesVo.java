package com.jsjyz.hnnu.vo.ArchivesVo;

import lombok.Data;

import java.util.List;

@Data
public class ArchivesVo {
    private String dateString;
    private List<ArchivesListVo> archivesListVo;
}
