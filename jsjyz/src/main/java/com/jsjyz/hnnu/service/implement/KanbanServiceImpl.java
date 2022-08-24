package com.jsjyz.hnnu.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jsjyz.hnnu.mapper.KanbanMapper;
import com.jsjyz.hnnu.pojo.Form;
import com.jsjyz.hnnu.service.KanbanService;
import com.jsjyz.hnnu.vo.KanbanVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KanbanServiceImpl implements KanbanService {
    @Autowired
    private KanbanMapper kanbanMapper;
    @Override
    public List<KanbanVo> getKanban() {
        LambdaQueryWrapper<Form> formLambdaQueryWrapper = new LambdaQueryWrapper<>();
        List<Form> formList = kanbanMapper.selectList(formLambdaQueryWrapper);
        ArrayList<KanbanVo> kanbanVos = new ArrayList<KanbanVo>();
        for (Form form:
             formList) {
            KanbanVo kanbanVo = new KanbanVo();
            BeanUtils.copyProperties(form,kanbanVo);
            kanbanVos.add(kanbanVo);
        }
        return kanbanVos;
    }
    @Override
    public KanbanVo getKanbanByName(String name) {
        LambdaQueryWrapper<Form> formLambdaQueryWrapper = new LambdaQueryWrapper<>();
        formLambdaQueryWrapper.like(Form::getName,name);
        Form form = kanbanMapper.selectOne(formLambdaQueryWrapper);
        KanbanVo kanbanVo = new KanbanVo();
        if(form == null){
            return kanbanVo;
        }
            BeanUtils.copyProperties(form, kanbanVo);
        return kanbanVo;
    }
}