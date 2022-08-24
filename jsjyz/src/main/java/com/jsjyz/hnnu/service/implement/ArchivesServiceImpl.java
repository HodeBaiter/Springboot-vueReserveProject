package com.jsjyz.hnnu.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jsjyz.hnnu.mapper.ArchivesMapper;
import com.jsjyz.hnnu.pojo.Form;
import com.jsjyz.hnnu.service.ArchivesService;
import com.jsjyz.hnnu.vo.ArchivesVo;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;

@Service
public class ArchivesServiceImpl implements ArchivesService {
    @Autowired
    private ArchivesMapper archivesMapper;
    @Override
    public List<ArchivesVo> getArchives() {
        LambdaQueryWrapper<Form> formLambdaQueryWrapper = new LambdaQueryWrapper<>();
        formLambdaQueryWrapper.eq(Form::getIsArchived,true);
        List<Form> forms = archivesMapper.selectList(formLambdaQueryWrapper);
        ArrayList<ArchivesVo> archivesVos = new ArrayList<ArchivesVo>();
        for (Form form :
                forms) {
            ArchivesVo archivesVo = new ArchivesVo();
            archivesVo.setIsArchived(form.getIsArchived().toString());
            archivesVo.setCreateTime(form.getCreateTime().toString());
            BeanUtils.copyProperties(form, archivesVo);
            archivesVos.add(archivesVo);
        }
        return  archivesVos;
    }




}
