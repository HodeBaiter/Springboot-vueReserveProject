package com.jsjyz.hnnu.service.implement;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jsjyz.hnnu.mapper.FormMapper;
import com.jsjyz.hnnu.pojo.Form;
import com.jsjyz.hnnu.service.FormService;
import com.jsjyz.hnnu.vo.ErrorCode;
import com.jsjyz.hnnu.vo.FormVo;
import com.jsjyz.hnnu.vo.PaginationVo;
import com.jsjyz.hnnu.vo.ResultResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;


@Service
public class FormServiceImpl extends ServiceImpl<FormMapper,Form> implements FormService {
    @Autowired
    private FormMapper formMapper;
    @Override
    public ResultResponse saveForm(FormVo formVo){
        Form form = copy(formVo);
        form.setStatus("undone");
        form.setCreateTime(new Timestamp(System.currentTimeMillis()));
        formMapper.insert(form);
        return new ResultResponse(ErrorCode.SUCCESS);
    }
    public Form copy(FormVo formVo) {
        Form form = new Form();
        String join = String.join(" ", formVo.getImage());
        form.setImage(join);
        BeanUtils.copyProperties(formVo,form);
        return form;
    }

    @Override
    public List<Form> selectAllForm(PaginationVo paginationVo) {
        LambdaQueryWrapper<Form> formLambdaQueryWrapper = new LambdaQueryWrapper<>();
        formLambdaQueryWrapper.eq(Form::getDeleted,0);
        Page<Form> formPage = new Page<>(paginationVo.getPageNum(), paginationVo.getPageSize());
        IPage<Form> page = formMapper.selectPage(formPage, formLambdaQueryWrapper);
        List<Form> records = page.getRecords();
        return records;
    }

    @Override
    public Form selectById(Form form) {
        LambdaQueryWrapper<Form> formLambdaQueryWrapper = new LambdaQueryWrapper<>();
        formLambdaQueryWrapper.eq(Form::getDeleted,0);
        formLambdaQueryWrapper.eq(Form::getId,form.getId());
        Form one = formMapper.selectOne(formLambdaQueryWrapper);
        return one;
    }

    @Override
    public ResultResponse update(Form form) {
        LambdaQueryWrapper<Form> formLambdaQueryWrapper = new LambdaQueryWrapper<>();
        formLambdaQueryWrapper.eq(Form::getDeleted,0);
        formLambdaQueryWrapper.eq(Form::getId,form.getId());
        int update = formMapper.update(form, formLambdaQueryWrapper);
        if (update == 0){
            return  new ResultResponse(ErrorCode.FAILED);
        }
        return new ResultResponse(ErrorCode.SUCCESS);
    }

    @Override
    public ResultResponse delete(List<Form> forms) {
        if (forms == null){
            return  new ResultResponse(ErrorCode.PARAMS_ERROR);
        }
        forms.forEach(form -> form.setDeleted(1));
        boolean b = updateBatchById(forms);
        if (!b){
            return  new ResultResponse(ErrorCode.FAILED);
        }
        return new ResultResponse(ErrorCode.SUCCESS);
    }

    @Override
    public List<Form> getAllForms() {
        LambdaQueryWrapper<Form> formLambdaQueryWrapper = new LambdaQueryWrapper<>();

        List<Form> forms = formMapper.selectList(formLambdaQueryWrapper);
        return forms;
    }

    @Override
    public ResultResponse updateList(List<Form> forms) {
        for (Form form : forms) {
            ResultResponse update = update(form);
            if (update.getCode() == ErrorCode.FAILED.getCode()){
                return new ResultResponse(ErrorCode.FAILED,form);
            }
        }
        return new ResultResponse(ErrorCode.SUCCESS);
    }
}

