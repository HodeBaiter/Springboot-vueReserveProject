package com.jsjyz.hnnu.controller;

import com.jsjyz.hnnu.service.AnnouncementService;
import com.jsjyz.hnnu.vo.AnnouncementVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;
    @GetMapping("announcement/{id}")
    public AnnouncementVo markdown(@PathVariable Long id) {
        return announcementService.getMarkdown(id);
    }

    @PostMapping("announcement")
    public List<AnnouncementVo> allAnnouncements(){
        return announcementService.getAllAnnouncement();
    }
}
