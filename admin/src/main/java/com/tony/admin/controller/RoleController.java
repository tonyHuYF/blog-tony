package com.tony.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tony.framework.domain.ResultBean;
import com.tony.framework.domain.Role;
import com.tony.framework.domain.dto.RoleChangeStatusDto;
import com.tony.framework.domain.dto.RoleInsertDto;
import com.tony.framework.domain.dto.RoleListDto;
import com.tony.framework.domain.vo.PageVo;
import com.tony.framework.domain.vo.RoleVo;
import com.tony.framework.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/system/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @GetMapping("/list")
    public ResultBean<PageVo<RoleVo>> listPage(Integer pageNum, Integer pageSize, RoleListDto param) {
        PageVo<RoleVo> vo = roleService.listPage(new Page<>(pageNum, pageSize), param);
        return new ResultBean<>(vo);
    }

    @GetMapping("/{id}")
    public ResultBean<Role> getById(@PathVariable Integer id) {
        Role vo = roleService.getById(id);
        return new ResultBean<>(vo);
    }

    @DeleteMapping("/{id}")
    public ResultBean<Void> delete(@PathVariable Integer id) {
        roleService.delete(id);
        return new ResultBean<>();
    }

    @PutMapping
    public ResultBean<Void> update(@RequestBody Role role) {
        roleService.updateById(role);
        return new ResultBean<>();
    }

    @PostMapping
    public ResultBean<Void> insert(@RequestBody RoleInsertDto roleInsertDto) {
        roleService.insert(roleInsertDto);
        return new ResultBean<>();
    }

    @PutMapping("/changeStatus")
    public ResultBean<Void> changeStatus(@RequestBody RoleChangeStatusDto param) {
        Role role = new Role();
        role.setId(Long.valueOf(param.getRoleId()));
        role.setStatus(param.getStatus());
        roleService.updateById(role);
        return new ResultBean<>();
    }

}
