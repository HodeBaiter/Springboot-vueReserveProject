package com.jsjyz.hnnu.service.implement;
import com.jsjyz.hnnu.mapper.FormMapper;
import com.jsjyz.hnnu.pojo.Form;
import com.jsjyz.hnnu.service.FormService;
import com.jsjyz.hnnu.vo.FormVo;
import com.jsjyz.hnnu.vo.ResultResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FormServiceImpl implements FormService {
    @Autowired
    private FormMapper formMapper;
    @Override
    public ResultResponse saveForm(FormVo formVo){
        Form form = copy(formVo);
        form.setStatus("undone");
        form.setCreateTime(System.currentTimeMillis());
        formMapper.insert(form);
        return new ResultResponse(200,"success");
    }
    public Form copy(FormVo formVo) {
        Form form = new Form();
        BeanUtils.copyProperties(formVo,form);
        return form;
    }
}
