package com.jsjyz.hnnu.service;

import com.jsjyz.hnnu.vo.ArchivesVo;

import java.text.ParseException;
import java.util.List;

public interface ArchivesService {
    List<ArchivesVo> getArchives(Integer year);

    List<Integer> getYear();
}
