package com.jsjyz.hnnu.service.implement;

import com.jsjyz.hnnu.mapper.ContactMapper;

import com.jsjyz.hnnu.pojo.Contact;
import com.jsjyz.hnnu.service.ContactService;
import com.jsjyz.hnnu.vo.ContactVo;
import com.jsjyz.hnnu.vo.ErrorCode;
import com.jsjyz.hnnu.vo.ResultResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactMapper contactMapper;
    @Override
    public ResultResponse saveContact(ContactVo contactVo) {
            Contact contact = new Contact();
            BeanUtils.copyProperties(contactVo,contact);
            contactMapper.insert(contact);
            return new ResultResponse(ErrorCode.SUCCESS);
    }
}
