package com.jsjyz.hnnu.service.implement;

import com.jsjyz.hnnu.mapper.FormMapper;
import com.jsjyz.hnnu.pojo.Form;
import com.jsjyz.hnnu.service.DetailService;
import com.jsjyz.hnnu.service.FormService;
import com.jsjyz.hnnu.vo.DetailVo;
import com.jsjyz.hnnu.vo.ErrorCode;
import com.jsjyz.hnnu.vo.ResultResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailServiceImpl implements DetailService {
    @Autowired
    private FormService formService;
    @Autowired
    private FormMapper formMapper;
    @Override
    public ResultResponse getDetail(Form form) {
        Form formDetail = formService.selectById(form);
    if (formDetail == null) {
        return new ResultResponse(11000,"failed");
    }
        DetailVo detailVo = new DetailVo();
        if (formDetail.getImage() != null) {
            String[] s = formDetail.getImage().split(" ");
            detailVo.setImage(s);
        }


        BeanUtils.copyProperties(formDetail,detailVo);
        return new ResultResponse(10000,"success",detailVo);
    }

    @Override
    public ResultResponse updateDetail(DetailVo detailVo) {
        Form form = new Form();
        form.setId(detailVo.getId());
        Form formSelect = formService.selectById(form);
        BeanUtils.copyProperties(detailVo, form);
        if (detailVo.getImage() != null) {
            formSelect.setImage(detailVo.getImage().toString());
        }

        formSelect.setIsArchived(1);
        formSelect.setDeleted(0);
        formSelect.setAnswer(detailVo.getAnswer());
        int i = formMapper.updateById(formSelect);
        if (i == 0) {
            return new ResultResponse(ErrorCode.FAILED);
        }
        return new ResultResponse(ErrorCode.SUCCESS);

    }
}
