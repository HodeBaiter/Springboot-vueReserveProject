package com.jsjyz.hnnu.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jsjyz.hnnu.mapper.AnnouncementMapper;
import com.jsjyz.hnnu.pojo.Announcement;
import com.jsjyz.hnnu.service.AnnouncementService;
import com.jsjyz.hnnu.vo.AnnouncementVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    @Autowired
    private AnnouncementMapper announcementMapper;
    @Override
    public AnnouncementVo getMarkdown(Long id) {
        Announcement announcement = announcementMapper.selectById(id);
        AnnouncementVo announcementVo = new AnnouncementVo();
        BeanUtils.copyProperties(announcement,announcementVo);
        announcementVo.setId(announcementVo.getId());
        return announcementVo;
    }

    @Override
    public List<AnnouncementVo> getAllAnnouncement() {
        ArrayList<AnnouncementVo> announcementVos = new ArrayList<>();
        List<Announcement> announcements = announcementMapper.selectList(new LambdaQueryWrapper<>());
        for (Announcement announcement:announcements){
            AnnouncementVo announcementVo = copyAnnouncement(announcement);
            announcementVos.add(announcementVo);
        }
        return announcementVos;
    }
    public AnnouncementVo copyAnnouncement(Announcement announcement){
        AnnouncementVo announcementVo = new AnnouncementVo();
        BeanUtils.copyProperties(announcement,announcementVo);
        announcementVo.setId(announcement.getId().toString());
        return announcementVo;
    }
}
