package com.jsjyz.hnnu.controller;


import com.jsjyz.hnnu.service.KanbanService;
import com.jsjyz.hnnu.vo.KanbanVo.KanbanGroupVo;
import com.jsjyz.hnnu.vo.KanbanVo.KanbanVo;
import com.jsjyz.hnnu.vo.PaginationVo;
import com.jsjyz.hnnu.vo.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class KanbanController {
    @Autowired
    private KanbanService kanbanService;
    @GetMapping ("/kanban")
    public ArrayList<KanbanGroupVo> getKanban(){
        return kanbanService.getKanban();
    }
    @PostMapping("/inquery")
    public List<KanbanVo> selectKanbanByName(@RequestBody Map<String, String> param) {
        String name = param.get("name");
        PaginationVo paginationVo = new PaginationVo();
        paginationVo.setPageNum( Long.valueOf(param.get("pageNum")));
        paginationVo.setPageSize(Long.valueOf(param.get("pageSize")));
        return kanbanService.getKanbanByName(name,paginationVo);
    }
    @PostMapping("admin/kanban/update")
    public ResultResponse update(@RequestBody List<KanbanGroupVo> kanbanGroupVo){
        return kanbanService.updateKanban(kanbanGroupVo);
    }

}
