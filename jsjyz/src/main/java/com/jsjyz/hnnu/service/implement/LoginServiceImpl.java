package com.jsjyz.hnnu.service.implement;

import com.jsjyz.hnnu.pojo.User;
import com.jsjyz.hnnu.service.LoginService;
import com.jsjyz.hnnu.service.UserService;
import com.jsjyz.hnnu.util.JwtUtil;
import com.jsjyz.hnnu.vo.ErrorCode;
import com.jsjyz.hnnu.vo.LoginParam;
import com.jsjyz.hnnu.vo.ResultResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class LoginServiceImpl implements LoginService {
    private String salt = "jsjyz";
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public ResultResponse login(LoginParam loginParam) {
        String userName = loginParam.getUserName();
        String password = loginParam.getPassword();
        if (StringUtils.isBlank(userName)||StringUtils.isBlank(password)){
            return new ResultResponse(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMessage());
        }
        ResultResponse resultResponse = new ResultResponse();

        String md5Password = DigestUtils.md5DigestAsHex((password+salt).getBytes());
        User user = userService.getPermissionsByAccount(userName, md5Password);
        if (user == null){
            return new ResultResponse(ErrorCode.ACCOUNT_PWD_NOT_FOUND.getCode(),ErrorCode.ACCOUNT_PWD_NOT_FOUND.getMessage());
        }
        String token = jwtUtil.create(user.getUserId(),user.getPermissions());
        return new ResultResponse(10000,"访问成功",token);
    }


}
