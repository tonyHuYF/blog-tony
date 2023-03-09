package com.tony.framework.handler.security;

import com.alibaba.fastjson.JSON;
import com.tony.framework.domain.Error;
import com.tony.framework.domain.ResultBean;
import com.tony.framework.utils.WebUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        authException.printStackTrace();

        ResultBean result = null;

        if (authException instanceof BadCredentialsException) {
            result = new ResultBean(Error.username_password_not_exist);
        } else if (authException instanceof InsufficientAuthenticationException) {
            result = new ResultBean(Error.unauthorized_access);
        } else {
            result = new ResultBean(Error.authentication_error);
        }

        //响应给前端
        WebUtils.renderString(response, JSON.toJSONString(result));

    }
}
