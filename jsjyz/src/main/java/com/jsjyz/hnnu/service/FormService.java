package com.jsjyz.hnnu.service;

import com.jsjyz.hnnu.pojo.Form;
import com.jsjyz.hnnu.vo.FormVo;
import com.jsjyz.hnnu.vo.PaginationVo;
import com.jsjyz.hnnu.vo.ResultResponse;
import org.apache.velocity.runtime.directive.contrib.For;

import java.util.List;

public interface FormService  {
    ResultResponse saveForm(FormVo FormVo);
    List<Form> selectAllForm(PaginationVo paginationVo);
    Form selectById(Form form);
    ResultResponse update(Form form);
    
    ResultResponse delete(List<Form> forms);
    List<Form> getAllForms();

    ResultResponse updateList(List<Form> forms);
}
