package com.jsjyz.hnnu.service;

import com.jsjyz.hnnu.vo.KanbanVo;

import java.util.List;

public interface KanbanService {
    List<KanbanVo> getKanban();
    KanbanVo getKanbanByName(String name);
}
