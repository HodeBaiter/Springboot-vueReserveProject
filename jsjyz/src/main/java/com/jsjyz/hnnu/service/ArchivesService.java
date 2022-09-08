package com.jsjyz.hnnu.service;

import com.jsjyz.hnnu.vo.ArchivesVo.ArchivesListVo;
import com.jsjyz.hnnu.vo.ArchivesVo.ArchivesVo;
import com.jsjyz.hnnu.vo.PaginationVo;

import java.util.List;

public interface ArchivesService {
    List<ArchivesVo> getArchives(PaginationVo pagination);



    List<ArchivesListVo> searchArticles(String searchContent,PaginationVo paginationVo);

    Long getTotalPages();
}
