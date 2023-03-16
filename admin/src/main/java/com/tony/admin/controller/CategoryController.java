package com.tony.admin.controller;

import com.tony.framework.domain.ResultBean;
import com.tony.framework.domain.vo.CategoryVo;
import com.tony.framework.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/content/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @GetMapping("/listAllCategory")
    public ResultBean<List<CategoryVo>> listAllCategory(){
        List<CategoryVo> vo =  categoryService.listAllCategory();
        return  new ResultBean<>(vo);
    }
}
