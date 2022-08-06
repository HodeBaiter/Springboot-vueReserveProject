package com.jsjyz.hnnu.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jsjyz.hnnu.mapper.ArchivesMapper;
import com.jsjyz.hnnu.pojo.Form;
import com.jsjyz.hnnu.service.ArchivesService;
import com.jsjyz.hnnu.vo.ArchivesVo;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ArchivesServiceImpl implements ArchivesService {
    @Autowired
    private ArchivesMapper archivesMapper;


    @Override
    public List<ArchivesVo> getArchives(Integer year) {
        LambdaQueryWrapper<Form> formLambdaQueryWrapper = new LambdaQueryWrapper<>();

        Timestamp startDate =Timestamp.valueOf(LocalDateTime.of(year,01,01,00,00,00));
        Timestamp endDate =Timestamp.valueOf(LocalDateTime.of(year,12,31,23,59,59));
        formLambdaQueryWrapper.ge(Form::getCreateTime,startDate.getTime());
        formLambdaQueryWrapper.le(Form::getCreateTime,endDate.getTime());
        List<Form> forms = archivesMapper.selectList(formLambdaQueryWrapper);
        ArrayList<ArchivesVo> archivesVos = new ArrayList<ArchivesVo>();
        for (Form form :
                forms) {
            ArchivesVo archivesVo = new ArchivesVo();
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date(form.getCreateTime()));
            archivesVo.setCreateTime(date.toString());
            BeanUtils.copyProperties(form, archivesVo);
            archivesVos.add(archivesVo);
        }
        return  archivesVos;
    }

    @Override
    public List<Integer> getYear() {
        return archivesMapper.selectYears();
    }


}
