package com.jsjyz.hnnu.controller;

import com.jsjyz.hnnu.pojo.Announcement;
import com.jsjyz.hnnu.service.AnnouncementService;
import com.jsjyz.hnnu.vo.AnnouncementVo;
import com.jsjyz.hnnu.vo.ErrorCode;
import com.jsjyz.hnnu.vo.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/admin/announcement/update")
    public ResultResponse updateAnnouncement(@RequestBody AnnouncementVo announcement){
        return  announcementService.updateAnnouncement(announcement);
    }
    @PostMapping("/admin/announcement/insert")
    public ResultResponse insertAnnouncement(@RequestBody AnnouncementVo announcement){
        return  announcementService.insertAnnouncement(announcement);
    }
}
