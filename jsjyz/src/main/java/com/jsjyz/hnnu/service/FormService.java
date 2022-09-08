package com.jsjyz.hnnu.service;

import com.jsjyz.hnnu.pojo.Form;
import com.jsjyz.hnnu.vo.PaginationVo;
import com.jsjyz.hnnu.vo.ResultResponse;

import java.util.List;

public interface FormService  {
    ResultResponse saveForm(Form Form);
    List<Form> selectAllForm(PaginationVo paginationVo);
    Form selectById(Form form);
    ResultResponse update(Form form);
    ResultResponse delete(List<Form> forms);
}
