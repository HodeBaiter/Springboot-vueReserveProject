package com.jsjyz.hnnu.vo.KanbanVo;

import lombok.Data;

import java.util.List;

@Data
public class KanbanGroupVo {
    private String status;
    private List<KanbanVo> kanbanVoList;
}
