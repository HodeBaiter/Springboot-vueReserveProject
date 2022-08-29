package com.jsjyz.hnnu.service;

import com.jsjyz.hnnu.vo.ArchivesListVo;
import com.jsjyz.hnnu.vo.paginationVo;

import java.util.List;
import java.util.Map;

public interface ArchivesService {
    Map<String, List<ArchivesListVo>> getArchives(paginationVo pagination);
}
