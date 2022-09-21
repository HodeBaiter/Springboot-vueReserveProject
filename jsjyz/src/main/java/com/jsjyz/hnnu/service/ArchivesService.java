package com.jsjyz.hnnu.service;

import com.jsjyz.hnnu.pojo.Form;
import com.jsjyz.hnnu.vo.ArchivesVo.ArchivesListVo;
import com.jsjyz.hnnu.vo.PaginationVo;

import java.util.List;
import java.util.Map;

public interface ArchivesService {
    Map<String, Object> getArchives(PaginationVo pagination, String question, String tag);

    List<ArchivesListVo> searchArticles(String searchContent,PaginationVo paginationVo);
    Long getTotalPages();
    List<String> getTags();

    List<Form> getNewArticles();
}
