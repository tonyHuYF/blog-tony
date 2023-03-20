package com.tony.admin.controller;

import com.tony.framework.domain.Menu;
import com.tony.framework.domain.ResultBean;
import com.tony.framework.domain.vo.MenuListVo;
import com.tony.framework.service.MenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/system/menu")
public class MenuController {

    @Resource
    private MenuService menuService;


    @GetMapping("/list")
    public ResultBean<List<MenuListVo>> allList(String menuName,String status) {
        List<MenuListVo> vo = menuService.allList(menuName,status);
        return new ResultBean<>(vo);
    }


    @GetMapping("/{id}")
    public ResultBean<Menu> getById(@PathVariable Integer id) {
        Menu menu = menuService.getById(id);
        return new ResultBean<>(menu);
    }

    @PostMapping
    public ResultBean<Void> insert(@RequestBody Menu menu) {
        menuService.save(menu);
        return new ResultBean<>();
    }

    @DeleteMapping("/{id}")
    public ResultBean<Void> delete(@PathVariable Integer id) {
        menuService.removeById(id);
        return new ResultBean<>();
    }

    @PutMapping
    public ResultBean<Void> update(@RequestBody Menu menu) {
        menuService.updateById(menu);
        return new ResultBean<>();
    }

}
