package com.jsjyz.hnnu.controller;

import com.jsjyz.hnnu.service.ArchivesService;
import com.jsjyz.hnnu.vo.ArchivesVo.ArchivesListVo;
import com.jsjyz.hnnu.vo.ArchivesVo.ArchivesVo;
import com.jsjyz.hnnu.vo.PaginationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ArchivesController {
    @Autowired
    private ArchivesService archivesService;
    @PostMapping("/archives")
    public List<ArchivesVo> getArchives(@RequestBody PaginationVo paginationVo)  {
        return archivesService.getArchives(paginationVo);
    }
    @PostMapping("/archives/search")
    public List<ArchivesListVo> search(@RequestBody Map<String,String> searchParam) {
        String searchContent = searchParam.get("searchContent");
        PaginationVo paginationVo = new PaginationVo();
        paginationVo.setPageNum( Long.parseLong(searchParam.get("pageNum")));
        paginationVo.setPageSize(Long.parseLong(searchParam.get("pageSize")));
        return archivesService.searchArticles(searchContent,paginationVo);
    }
    @GetMapping("/archives/pageCount")
    public Long pageCount(){
        return archivesService.getTotalPages();
    }
}
