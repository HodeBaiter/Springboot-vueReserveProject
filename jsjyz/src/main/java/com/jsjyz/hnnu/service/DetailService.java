package com.jsjyz.hnnu.service;

import com.jsjyz.hnnu.pojo.Form;
import com.jsjyz.hnnu.vo.DetailVo;
import com.jsjyz.hnnu.vo.ResultResponse;

public interface DetailService {
    ResultResponse getDetail(Form form);

    ResultResponse updateDetail(DetailVo detailVo);
}
