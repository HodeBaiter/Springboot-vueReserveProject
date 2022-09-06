package com.jsjyz.hnnu.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jsjyz.hnnu.mapper.KanbanMapper;
import com.jsjyz.hnnu.pojo.Form;
import com.jsjyz.hnnu.service.KanbanService;
import com.jsjyz.hnnu.vo.KanbanVo.KanbanGroupVo;
import com.jsjyz.hnnu.vo.KanbanVo.KanbanVo;
import com.jsjyz.hnnu.vo.PaginationVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class KanbanServiceImpl implements KanbanService {
    @Autowired
    private KanbanMapper kanbanMapper;
    @Override
    public ArrayList<KanbanGroupVo> getKanban() {
        LambdaQueryWrapper<Form> formLambdaQueryWrapper = new LambdaQueryWrapper<>();
        List<Form> formList = kanbanMapper.selectList(formLambdaQueryWrapper);
        ArrayList<KanbanVo> kanbanVos = new ArrayList<KanbanVo>();
        //group
        ArrayList<KanbanGroupVo> kanbanGroupVos = new ArrayList<>();

        kanbanGroupVos.add(new KanbanGroupVo("undone" ));
        kanbanGroupVos.add(new KanbanGroupVo("cancel" ));
        kanbanGroupVos.add(new KanbanGroupVo("expired" ));
        kanbanGroupVos.add(new KanbanGroupVo("done"));
        if (formList.isEmpty()){
            return  kanbanGroupVos;
        }
        for (Form form:
                formList) {
            KanbanVo kanbanVo = new KanbanVo();
            BeanUtils.copyProperties(form,kanbanVo);
            kanbanVos.add(kanbanVo);
        }
        Map<String,List<KanbanVo>> groupMaps = kanbanVos.stream().collect(Collectors.groupingBy(item ->{
            String status = item.getStatus();
            return status;
        }));
    groupMaps.forEach(
        (key, value) -> {
          kanbanGroupVos.forEach(
              kanbanGroupVo -> {
                if (key.equals(kanbanGroupVo.getStatus()) ) {

                  kanbanGroupVo.setKanbanVoList(value);
                }
              });
        });
        return kanbanGroupVos;
    }
    @Override
    public List<KanbanVo> getKanbanByName(String name, PaginationVo paginationVo) {
        LambdaQueryWrapper<Form> formLambdaQueryWrapper = new LambdaQueryWrapper<>();
        formLambdaQueryWrapper.like(Form::getName,name);
        Page<Form> formPage = new Page<>(paginationVo.getPageNum(), paginationVo.getPageSize());
        IPage<Form> records = kanbanMapper.selectPage(formPage, formLambdaQueryWrapper);
        List<Form> forms = records.getRecords();
        List<KanbanVo> kanbanVos = new ArrayList<KanbanVo>();
        if(forms.isEmpty()) {
            return kanbanVos;
        }
        forms.forEach(form ->{
            KanbanVo kanbanVo = new KanbanVo();
            BeanUtils.copyProperties(form,kanbanVo);
            kanbanVos.add(kanbanVo);
        });
        return kanbanVos;
    }
}