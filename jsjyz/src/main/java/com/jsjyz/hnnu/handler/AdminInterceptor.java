package com.jsjyz.hnnu.handler;

import com.alibaba.fastjson2.JSON;
import com.jsjyz.hnnu.pojo.User;
import com.jsjyz.hnnu.util.JwtUtil;
import com.jsjyz.hnnu.util.UserThreadLocal;
import com.jsjyz.hnnu.vo.ErrorCode;
import com.jsjyz.hnnu.vo.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class AdminInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        Map<String, Object> parseToken = jwtUtil.parse(token);
        if (Long.parseLong(String.valueOf(parseToken.get("permissions"))) < 2L){
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
