package com.jsjyz.hnnu.service;


import com.jsjyz.hnnu.vo.ContactVo;
import com.jsjyz.hnnu.vo.ResultResponse;

public interface ContactService {
    ResultResponse saveContact(ContactVo contact);

}
