package com.tony.blog.controller;

import com.tony.framework.domain.ResultBean;
import com.tony.framework.domain.vo.UserInfoVo;
import com.tony.framework.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/userInfo")
    public ResultBean<UserInfoVo> userInfo() {
        UserInfoVo userInfoVo = userService.userInfo();
        return new ResultBean<>(userInfoVo);
    }
}
