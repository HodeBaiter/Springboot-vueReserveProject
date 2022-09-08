package com.jsjyz.hnnu.controller;

import com.jsjyz.hnnu.pojo.Form;
import com.jsjyz.hnnu.service.DetailService;
import com.jsjyz.hnnu.vo.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DetailController {
    @Autowired
    private DetailService detailService;
    @GetMapping("/detail/{id}")
    public ResultResponse getDetail(@PathVariable Long id){
        Form form = new Form();
        form.setId(id);
        return detailService.getDetail(form);
    }
}
