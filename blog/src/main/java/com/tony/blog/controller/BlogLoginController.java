package com.tony.blog.controller;

import com.tony.framework.domain.ResultBean;
import com.tony.framework.domain.User;
import com.tony.framework.domain.vo.BlogUserLoginVo;
import com.tony.framework.service.BlogLoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class BlogLoginController {

    @Resource
    private BlogLoginService blogLoginService;

    @PostMapping("/login")
    public ResultBean<BlogUserLoginVo> login(@RequestBody User user) {
        BlogUserLoginVo vo = blogLoginService.login(user);
        return new ResultBean<>(vo);
    }

    @PostMapping("/logout")
    public ResultBean<Void> logout() {
        blogLoginService.logout();
        return new ResultBean<>();
    }
}
