package com.jsjyz.hnnu.handler;

import com.alibaba.fastjson2.JSON;
import com.jsjyz.hnnu.pojo.User;
import com.jsjyz.hnnu.service.LoginService;
import com.jsjyz.hnnu.util.JwtUtil;
import com.jsjyz.hnnu.util.UserThreadLocal;
import com.jsjyz.hnnu.vo.ErrorCode;
import com.jsjyz.hnnu.vo.ResultResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private LoginService loginService;
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        String token = request.getHeader("Authorization");
        if ( StringUtils.isBlank(token)){
            ResultResponse result = new ResultResponse(ErrorCode.NO_LOGIN);
            response.setContentType("application/json;charset = utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }
        Map<String, Object> parseToken = jwtUtil.parse(token);
        if (parseToken == null){
            ResultResponse result = new ResultResponse(ErrorCode.NO_LOGIN);
            response.setContentType("application/json;charset = utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }
        if (String.valueOf(parseToken.get("permissions")).equals("0")){
            ResultResponse result = new ResultResponse(ErrorCode.NO_PERMISSIONS);
            response.setContentType("application/json;charset = utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }
        User user = new User();
        user.setUserId( Long.parseLong(String.valueOf(parseToken.get("userId"))));
        UserThreadLocal.put(user);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserThreadLocal.remove();
    }
}
