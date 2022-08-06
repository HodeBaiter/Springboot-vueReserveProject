package com.jsjyz.hnnu.service.implement;

import com.jsjyz.hnnu.mapper.DetailMapper;
import com.jsjyz.hnnu.pojo.Form;
import com.jsjyz.hnnu.service.DetailService;
import com.jsjyz.hnnu.vo.DetailVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class DetailServiceImpl implements DetailService {
    @Autowired
    private DetailMapper detailMapper;
    @Override
    public DetailVo getDetail(Long id) {
        Form form = detailMapper.selectById(id);
        DetailVo detailVo = new DetailVo();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(form.getCreateTime());
        detailVo.setCreateTime(date.toString());
        BeanUtils.copyProperties(form,detailVo);
        return detailVo;

    }
}
