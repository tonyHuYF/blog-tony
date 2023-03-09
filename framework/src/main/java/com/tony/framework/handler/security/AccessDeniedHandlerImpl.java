package com.tony.framework.handler.security;

import com.alibaba.fastjson.JSON;
import com.tony.framework.domain.Error;
import com.tony.framework.domain.ResultBean;
import com.tony.framework.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        accessDeniedException.printStackTrace();

        ResultBean result = new ResultBean(Error.unauthorized_data_error);

        //响应给前端
        WebUtils.renderString(response, JSON.toJSONString(result));

    }
}
