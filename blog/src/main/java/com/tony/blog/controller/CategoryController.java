package com.tony.blog.controller;

import com.tony.framework.domain.ResultBean;
import com.tony.framework.domain.vo.CategoryVo;
import com.tony.framework.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @GetMapping("getCategoryList")
    public ResultBean<List<CategoryVo>> getCategoryList() {
        List<CategoryVo> list = categoryService.getCategoryList();
        return new ResultBean<>(list);
    }
}
