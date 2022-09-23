package com.jsjyz.hnnu.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jsjyz.hnnu.mapper.FormMapper;
import com.jsjyz.hnnu.pojo.Form;
import com.jsjyz.hnnu.service.FormService;
import com.jsjyz.hnnu.service.KanbanService;
import com.jsjyz.hnnu.vo.ErrorCode;
import com.jsjyz.hnnu.vo.KanbanVo.KanbanGroupVo;
import com.jsjyz.hnnu.vo.KanbanVo.KanbanVo;
import com.jsjyz.hnnu.vo.PaginationVo;
import com.jsjyz.hnnu.vo.ResultResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class KanbanServiceImpl implements KanbanService {
    private static final HashMap<String,String> map = new HashMap<String,String>(){{
        put("未完成", "undone");
        put("过期", "expired");
        put("已完成", "done");
        put("取消", "cancel");
    }};
    @Autowired
    private FormMapper formMapper;
    @Autowired
    private FormService formService;
    @Override
    public ArrayList<KanbanGroupVo> getKanban() {
        LambdaQueryWrapper<Form> formLambdaQueryWrapper = new LambdaQueryWrapper<>();
        formLambdaQueryWrapper.eq(Form::getDeleted,0);
        List<Form> formList = formMapper.selectList(formLambdaQueryWrapper);
        ArrayList<KanbanVo> kanbanVos = new ArrayList<>();
        //group
        ArrayList<KanbanGroupVo> kanbanGroupVos = new ArrayList<>();

        kanbanGroupVos.add(new KanbanGroupVo("undone",new ArrayList<KanbanVo>()));
        kanbanGroupVos.add(new KanbanGroupVo("cancel",new ArrayList<KanbanVo>()));
        kanbanGroupVos.add(new KanbanGroupVo("expired" ,new ArrayList<KanbanVo>()));
        kanbanGroupVos.add(new KanbanGroupVo("done",new ArrayList<KanbanVo>()));
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
        (key, value) -> kanbanGroupVos.forEach(
            kanbanGroupVo -> {
              if (key.equals(kanbanGroupVo.getStatus()) ) {

                kanbanGroupVo.setKanbanVoList(value);
              }
            }));
        return kanbanGroupVos;
    }
    @Override
    public List<KanbanVo> getKanbanByName(String name, PaginationVo paginationVo) {
        LambdaQueryWrapper<Form> formLambdaQueryWrapper = new LambdaQueryWrapper<>();
        formLambdaQueryWrapper.like(Form::getName,name);
        formLambdaQueryWrapper.eq(Form::getDeleted,0);
        Page<Form> formPage = new Page<>(paginationVo.getPageNum(), paginationVo.getPageSize());
        IPage<Form> records = formMapper.selectPage(formPage, formLambdaQueryWrapper);
        List<Form> forms = records.getRecords();
        List<KanbanVo> kanbanVos = new ArrayList<>();
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

    @Override
    public ResultResponse updateKanban(List<KanbanGroupVo> kanbanGroupVos) {
        ArrayList<Form> forms = new ArrayList<>();
        if (kanbanGroupVos == null || kanbanGroupVos.size() == 0) {
            return new ResultResponse(ErrorCode.PARAMS_ERROR);
        }
        kanbanGroupVos.forEach(kanbanGroupVo ->{
                kanbanGroupVo.setStatus(map.get(kanbanGroupVo.getStatus()));
                if (kanbanGroupVo.getKanbanVoList() == null || kanbanGroupVo.getKanbanVoList().size() == 0) {
                    return;
                }
                kanbanGroupVo.getKanbanVoList().forEach(kanbanVo -> {
                    if (!kanbanVo.getStatus().equals(kanbanGroupVo.getStatus()) ){
                        Form form = new Form();
                        kanbanVo.setStatus(kanbanGroupVo.getStatus());
                        form.setId(kanbanVo.getId());
                        Form formTemplate = formService.selectById(form);
                        BeanUtils.copyProperties(kanbanVo,formTemplate);

                        forms.add(formTemplate);
                    }
                });
            });
        ResultResponse resultResponse = formService.updateList(forms);
        return resultResponse;
    }
}