package com.jsjyz.hnnu.controller;


import com.jsjyz.hnnu.service.FormService;
import com.jsjyz.hnnu.vo.FormVo;
import com.jsjyz.hnnu.vo.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class FormController {
    @Autowired
    private FormService formService;
    @PostMapping("/timeoption")
    public ArrayList<String> timeOption() {
        ArrayList<String> options = new ArrayList<>();
        options.add("3:00~4:00");
        options.add("4:00~5:00");
        options.add("5:00~6:00");
        return options;
    }
    @PostMapping("/form")
    public ResultResponse saveForm(@RequestBody FormVo formVo) {
    return formService.saveForm(formVo);
    }

}
