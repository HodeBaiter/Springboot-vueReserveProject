package com.jsjyz.hnnu.service;

import com.jsjyz.hnnu.vo.ArchivesListVo;
import com.jsjyz.hnnu.vo.PaginationVo;
import javafx.scene.control.Pagination;

import java.util.List;
import java.util.Map;

public interface ArchivesService {
    Map<String, List<ArchivesListVo>> getArchives(PaginationVo pagination);



    List<ArchivesListVo> searchArticles(String searchContent,PaginationVo paginationVo);
}
