package com.jsjyz.hnnu.controller;

import com.jsjyz.hnnu.pojo.Form;
import com.jsjyz.hnnu.service.DetailService;
import com.jsjyz.hnnu.vo.DetailVo;
import com.jsjyz.hnnu.vo.ResultResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

@RestController
public class DetailController {
    @Autowired
    private DetailService detailService;

    @GetMapping("/detail/{id}")
    public ResultResponse getDetail(@PathVariable Long id) {
        Form form = new Form();
        form.setId(id);
        return detailService.getDetail(form);
    }

    @PostMapping("/admin/detail/update")
    public ResultResponse updateDetail(@RequestBody DetailVo detailVo) {
        return detailService.updateDetail(detailVo);
    }
}
