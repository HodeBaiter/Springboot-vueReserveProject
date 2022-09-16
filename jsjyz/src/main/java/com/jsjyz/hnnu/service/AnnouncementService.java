package com.jsjyz.hnnu.service;

import com.jsjyz.hnnu.vo.AnnouncementVo;
import com.jsjyz.hnnu.vo.ResultResponse;

import java.util.List;

public interface  AnnouncementService {
    AnnouncementVo getMarkdown(Long id);

    List<AnnouncementVo> getAllAnnouncement();

    ResultResponse updateAnnouncement(AnnouncementVo announcement);
}
