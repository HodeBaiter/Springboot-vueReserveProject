package com.jsjyz.hnnu.controller;

import com.jsjyz.hnnu.service.ArchivesService;
import com.jsjyz.hnnu.vo.ArchivesVo.ArchivesListVo;
import com.jsjyz.hnnu.vo.ArchivesVo.ArchivesVo;
import com.jsjyz.hnnu.vo.ErrorCode;
import com.jsjyz.hnnu.vo.PaginationVo;
import com.jsjyz.hnnu.vo.ResultResponse;
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
    public ResultResponse getArchives(@RequestBody PaginationVo paginationVo)  {

         return new ResultResponse(ErrorCode.SUCCESS,archivesService.getArchives(paginationVo));
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
}
