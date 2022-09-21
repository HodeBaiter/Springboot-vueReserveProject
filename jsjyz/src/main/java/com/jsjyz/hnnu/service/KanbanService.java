package com.jsjyz.hnnu.service;


import com.jsjyz.hnnu.vo.KanbanVo.KanbanGroupVo;
import com.jsjyz.hnnu.vo.KanbanVo.KanbanVo;
import com.jsjyz.hnnu.vo.PaginationVo;
import com.jsjyz.hnnu.vo.ResultResponse;

import java.util.ArrayList;
import java.util.List;

public interface KanbanService {
    ArrayList<KanbanGroupVo> getKanban();
    List<KanbanVo> getKanbanByName(String name, PaginationVo paginationVo);

        ResultResponse updateKanban(List<KanbanGroupVo> kanbanGroupVos);
}
