package com.jsjyz.hnnu.controller;


import com.jsjyz.hnnu.service.ContactService;
import com.jsjyz.hnnu.vo.ContactVo;
import com.jsjyz.hnnu.vo.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {
    @Autowired
    private ContactService contactService;
    @PostMapping("/contact")
    public ResultResponse saveContact(@RequestBody ContactVo contactVo){
        System.out.println(contactVo);

        System.out.println(contactService.saveContact(contactVo));
        return contactService.saveContact(contactVo);
    }
}
