package com.tony.framework.handler.exception;


import cn.hutool.core.util.RandomUtil;
import com.tony.framework.domain.Error;
import com.tony.framework.domain.ResultBean;
import com.tony.framework.exception.CommonException;
import com.tony.framework.utils.ExceptionUtil;
import com.tony.framework.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CommonException.class)
    public ResultBean commonExceptionHandler(CommonException ex) {
        String prefix = RandomUtil.randomString(10);
        String errorMessage = "\r\n业务错误代号:" + prefix + ", code:" + ex.getError().getCode() + ", message:" + ex.getError().getMessage();
        StringBuilder sb = new StringBuilder();
        RequestUtil.getHttpParamLogInfo(sb);
        log.warn(errorMessage + sb.toString());
        if (ex.getExtra() != null) {
            prefix = ex.getExtra().toString();
        }
        return new ResultBean<>(ex, prefix);
    }

    @ExceptionHandler(Exception.class)
    public ResultBean exceptionHandler(Exception ex) {
        String prefix = RandomUtil.randomString(10);
        log.error(ExceptionUtil.getErrorInfoFromException(ex, prefix));
        return new ResultBean<>(Error.internal_server_error, prefix);
    }

    /**
     * 解决SpringMVC 接收List/数组大小默认限制为256个
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setAutoGrowNestedPaths(true);
        binder.setAutoGrowCollectionLimit(Integer.MAX_VALUE);
    }
}
