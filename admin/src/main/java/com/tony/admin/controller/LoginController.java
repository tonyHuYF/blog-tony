package com.tony.admin.controller;

import com.tony.framework.domain.ResultBean;
import com.tony.framework.domain.User;
import com.tony.framework.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("/user/login")
    public ResultBean<Map<String, String>> login(@RequestBody User user) {
        Map<String, String> map = loginService.login(user);
        return new ResultBean<>(map);
    }

}
