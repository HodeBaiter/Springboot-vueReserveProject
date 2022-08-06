package com.jsjyz.hnnu.controller;


import com.jsjyz.hnnu.pojo.Form;
import com.jsjyz.hnnu.service.KanbanService;
import com.jsjyz.hnnu.vo.KanbanVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KanbanController {
    @Autowired
    private KanbanService kanbanService;
    @GetMapping ("/kanban")
    public List<KanbanVo> getKanban(){
        return kanbanService.getKanban();
    }
    @PostMapping("/inquery")
    public KanbanVo selectKanbanByName(@RequestBody Form formName){
        return kanbanService.getKanbanByName(formName.getName());
    }
}
