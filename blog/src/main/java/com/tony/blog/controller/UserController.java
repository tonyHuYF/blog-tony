package com.tony.blog.controller;

import com.tony.framework.domain.ResultBean;
import com.tony.framework.domain.User;
import com.tony.framework.domain.vo.UserInfoVo;
import com.tony.framework.service.UserService;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/userInfo")
    public ResultBean<Void> updateUserInfo(@RequestBody User user) {
        userService.updateUserInfo(user);
        return new ResultBean<>();
    }

    @PostMapping("/register")
    public ResultBean<Void> register(@RequestBody User user) {
        userService.register(user);
        return new ResultBean<>();
    }
}
