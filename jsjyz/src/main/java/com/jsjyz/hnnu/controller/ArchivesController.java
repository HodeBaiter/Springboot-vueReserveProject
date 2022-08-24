package com.jsjyz.hnnu.controller;

import com.jsjyz.hnnu.service.ArchivesService;
import com.jsjyz.hnnu.vo.ArchivesVo;
import com.jsjyz.hnnu.vo.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
public class ArchivesController {
    @Autowired
    private ArchivesService archivesService;
    @PostMapping("/archives")
    public List<ArchivesVo> getArchives()  {
        return archivesService.getArchives();
    }
}
