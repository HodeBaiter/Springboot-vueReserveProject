package com.jsjyz.hnnu.vo.KanbanVo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class KanbanGroupVo {
    private String status;

    private List<KanbanVo> kanbanVoList;
    public KanbanGroupVo(String status) {
        this.status = status;
    }


}
