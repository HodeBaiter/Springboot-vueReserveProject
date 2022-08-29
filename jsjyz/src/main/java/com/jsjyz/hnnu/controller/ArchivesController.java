package com.jsjyz.hnnu.controller;

import com.jsjyz.hnnu.service.ArchivesService;
import com.jsjyz.hnnu.vo.ArchivesListVo;
import com.jsjyz.hnnu.vo.PaginationVo;
import javafx.scene.control.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ArchivesController {
    @Autowired
    private ArchivesService archivesService;
    @PostMapping("/archives")
    public Map<String, List<ArchivesListVo>> getArchives(@RequestBody PaginationVo pagination)  {
        return archivesService.getArchives(pagination);
    }
    @PostMapping("/search")
    public List<ArchivesListVo> search(@RequestBody Map<String,String> searchParam) {
        String searchContent = searchParam.get("searchContent");
        PaginationVo paginationVo = new PaginationVo();
        paginationVo.setPageNum( Long.parseLong(searchParam.get("pageNum")));
        paginationVo.setPageSize(Long.parseLong(searchParam.get("pageSize")));
        return archivesService.searchArticles(searchContent,paginationVo);
    }

}
