package com.jsjyz.hnnu.controller;

import com.jsjyz.hnnu.pojo.Announcement;
import com.jsjyz.hnnu.service.AnnouncementService;
import com.jsjyz.hnnu.vo.AnnouncementVo;
import com.jsjyz.hnnu.vo.ErrorCode;
import com.jsjyz.hnnu.vo.ResultResponse;
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
    public ResultResponse markdown(@PathVariable Long id) {
        AnnouncementVo markdown = announcementService.getMarkdown(id);
        return new ResultResponse(ErrorCode.SUCCESS,markdown);
    }

    @PostMapping("announcement")
    public ResultResponse allAnnouncements(){
        List<AnnouncementVo> allAnnouncement = announcementService.getAllAnnouncement();
        return new ResultResponse(ErrorCode.SUCCESS,allAnnouncement);
    }
    public ResultResponse updateAnnouncement(AnnouncementVo announcement){
        return new ResultResponse(ErrorCode.SUCCESS,announcementService.updateAnnouncement(announcement));
    }
}
