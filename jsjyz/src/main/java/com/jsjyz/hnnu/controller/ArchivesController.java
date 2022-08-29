package com.jsjyz.hnnu.controller;

import com.jsjyz.hnnu.service.ArchivesService;
import com.jsjyz.hnnu.vo.ArchivesListVo;
import com.jsjyz.hnnu.vo.paginationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ArchivesController {
    @Autowired
    private ArchivesService archivesService;
    @PostMapping("/archives")
    public Map<String, List<ArchivesListVo>> getArchives(@RequestBody paginationVo pagination)  {
        return archivesService.getArchives(pagination);
    }

}
