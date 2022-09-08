package com.jsjyz.hnnu.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jsjyz.hnnu.mapper.AnnouncementMapper;
import com.jsjyz.hnnu.pojo.Announcement;
import com.jsjyz.hnnu.service.AnnouncementService;
import com.jsjyz.hnnu.vo.AnnouncementVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    @Autowired
    private AnnouncementMapper announcementMapper;
    @Override
    public AnnouncementVo getMarkdown(Long id) {
        LambdaQueryWrapper<Announcement> announcementLambdaQueryWrapper = new LambdaQueryWrapper<>();
        announcementLambdaQueryWrapper.eq(Announcement::getDeleted,0).eq(Announcement::getId,id);
        Announcement announcement = announcementMapper.selectOne(announcementLambdaQueryWrapper);
        AnnouncementVo announcementVo = new AnnouncementVo();
        announcementVo.setUpdateTime(new Timestamp(announcement.getUpdateTime()).toString());
        BeanUtils.copyProperties(announcement,announcementVo);
        return announcementVo;
    }

    @Override
    public List<AnnouncementVo> getAllAnnouncement() {
        ArrayList<AnnouncementVo> announcementVos = new ArrayList<>();
        LambdaQueryWrapper<Announcement> announcementLambdaQueryWrapper = new LambdaQueryWrapper<>();
        announcementLambdaQueryWrapper.eq(Announcement::getDeleted,0);
        announcementLambdaQueryWrapper.select(Announcement::getId,Announcement::getUpdateTime,Announcement::getTitle    );
        List<Announcement> announcements = announcementMapper.selectList(announcementLambdaQueryWrapper);
        for (Announcement announcement:announcements){
            AnnouncementVo announcementVo = copyAnnouncement(announcement);
            announcementVos.add(announcementVo);
        }
        return announcementVos;
    }
    public AnnouncementVo copyAnnouncement(Announcement announcement){
        AnnouncementVo announcementVo = new AnnouncementVo();
        announcementVo.setUpdateTime(new Timestamp(announcement.getUpdateTime()).toString() );
        BeanUtils.copyProperties(announcement,announcementVo);
        return announcementVo;
    }
}
