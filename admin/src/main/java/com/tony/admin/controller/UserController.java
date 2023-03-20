package com.tony.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tony.framework.domain.ResultBean;
import com.tony.framework.domain.User;
import com.tony.framework.domain.dto.UserChangeStatusDto;
import com.tony.framework.domain.dto.UserInsertDto;
import com.tony.framework.domain.dto.UserListDto;
import com.tony.framework.domain.vo.PageVo;
import com.tony.framework.domain.vo.UserListVo;
import com.tony.framework.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("system/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/list")
    public ResultBean<PageVo<UserListVo>> list(Integer pageNum, Integer pageSize, UserListDto param) {
        PageVo<UserListVo> vo = userService.listPage(new Page<>(pageNum, pageSize), param);
        return new ResultBean<>(vo);
    }


    @GetMapping("/{id}")
    public ResultBean<User> getById(@PathVariable Integer id) {
        User user = userService.getById(id);
        return new ResultBean<>(user);
    }

    @PutMapping("/{id}")
    public ResultBean<Void> update(@RequestBody User user) {
        userService.updateById(user);
        return new ResultBean<>();
    }

    @DeleteMapping("/{id}")
    public ResultBean<Void> delete(@PathVariable String id) {
        userService.delete(id);
        return new ResultBean<>();
    }

    @PostMapping
    public ResultBean<Void> insert(@RequestBody UserInsertDto param) {
        userService.insert(param);
        return new ResultBean<>();
    }


    @PutMapping("/changeStatus")
    public ResultBean<Void> changeStatus(@RequestBody UserChangeStatusDto param) {
        User user = new User();
        user.setId(Long.valueOf(param.getUserId()));
        user.setStatus(param.getStatus());
        userService.updateById(user);
        return new ResultBean<>();
    }

}
