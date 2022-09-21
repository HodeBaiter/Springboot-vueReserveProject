package com.jsjyz.hnnu.controller;

import com.jsjyz.hnnu.service.ArchivesService;
import com.jsjyz.hnnu.vo.ArchivesVo.ArchivesListVo;
import com.jsjyz.hnnu.vo.ArchivesVo.ArchivesVo;
import com.jsjyz.hnnu.vo.ErrorCode;
import com.jsjyz.hnnu.vo.PaginationVo;
import com.jsjyz.hnnu.vo.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ArchivesController {
    @Autowired
    private ArchivesService archivesService;
    @PostMapping("/archives")
    public ResultResponse getArchives(@RequestBody Map<String, String> archives)   {
        PaginationVo paginationVo = new PaginationVo();
        paginationVo.setPageNum(Long.parseLong(archives.get("pageNum")) );
        paginationVo.setPageSize(Long.parseLong(archives.get("pageSize")) );
        String question = archives.get("question");
        String tag = archives.get("tag");
        return new ResultResponse(ErrorCode.SUCCESS,archivesService.getArchives(paginationVo,question,tag));
    }
    @PostMapping("/archives/search")
    public ResultResponse search(@RequestBody Map<String,String> searchParam) {
        String searchContent = searchParam.get("searchContent");
        PaginationVo paginationVo = new PaginationVo();
        paginationVo.setPageNum( Long.parseLong(searchParam.get("pageNum")));
        paginationVo.setPageSize(Long.parseLong(searchParam.get("pageSize")));
        return new ResultResponse(ErrorCode.SUCCESS,archivesService.searchArticles(searchContent,paginationVo)) ;

    }
    @GetMapping("/archives/pageCount")
    public ResultResponse pageCount(){
        return new ResultResponse(ErrorCode.SUCCESS,archivesService.getTotalPages()) ;
    }
    @GetMapping("/archives/tag")
    public ResultResponse getTags(){
        return new ResultResponse(ErrorCode.SUCCESS,archivesService.getTags()) ;
    }

    @GetMapping("/archives/newArticles")
    public ResultResponse getNewArticles(){
        return new ResultResponse(ErrorCode.SUCCESS,archivesService.getNewArticles()) ;
    }
}
