package com.jsjyz.hnnu.service;

import com.jsjyz.hnnu.vo.AnnouncementVo;

import java.util.List;

public interface  AnnouncementService {
    AnnouncementVo getMarkdown(Long id);

    List<AnnouncementVo> getAllAnnouncement();

}
