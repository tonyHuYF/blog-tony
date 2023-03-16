package com.tony.admin.controller;

import com.tony.framework.domain.ResultBean;
import com.tony.framework.domain.User;
import com.tony.framework.domain.vo.AdminUserInfoVo;
import com.tony.framework.domain.vo.RoutersVo;
import com.tony.framework.service.LoginService;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("/user/logout")
    public ResultBean<Void> logout() {
        loginService.logout();
        return new ResultBean<>();
    }

    @GetMapping("/getInfo")
    public ResultBean<AdminUserInfoVo> getInfo() {
        AdminUserInfoVo vo = loginService.getInfo();
        return new ResultBean<>(vo);
    }

    @GetMapping("/getRouters")
    public ResultBean<RoutersVo> getRouters() {
        RoutersVo vo = loginService.getRouters();
        return new ResultBean<>(vo);
    }

}
