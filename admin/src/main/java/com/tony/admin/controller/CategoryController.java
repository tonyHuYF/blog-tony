package com.tony.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tony.framework.domain.Category;
import com.tony.framework.domain.ResultBean;
import com.tony.framework.domain.dto.CategoryListDto;
import com.tony.framework.domain.vo.CategoryListVo;
import com.tony.framework.domain.vo.CategoryVo;
import com.tony.framework.domain.vo.PageVo;
import com.tony.framework.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/content/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @GetMapping("/listAllCategory")
    public ResultBean<List<CategoryVo>> listAllCategory() {
        List<CategoryVo> vo = categoryService.listAllCategory();
        return new ResultBean<>(vo);
    }


    @GetMapping("/list")
    public ResultBean<PageVo<CategoryListVo>> getCategoryListPage(Integer pageNum, Integer pageSize, CategoryListDto param) {
        PageVo<CategoryListVo> vo = categoryService.getCategoryListPage(new Page<>(pageNum, pageSize), param);
        return new ResultBean<>(vo);
    }


    @GetMapping("/{id}")
    public ResultBean<CategoryListVo> getById(@PathVariable Integer id){
        Category category = categoryService.getById(id);
        CategoryListVo vo = new CategoryListVo();
        BeanUtil.copyProperties(category,vo);
        return new ResultBean<>(vo);
    }

    @DeleteMapping("/{id}")
    public ResultBean<Void> delete(@PathVariable Integer id){
        categoryService.removeById(id);
        return new ResultBean<>();
    }

    @PostMapping
    public ResultBean<Void> insert(@RequestBody Category category){
        categoryService.save(category);
        return new ResultBean<>();
    }

    @PutMapping
    public ResultBean<Void> update(@RequestBody Category category){
        categoryService.updateById(category);
        return new ResultBean<>();
    }

    @GetMapping("/export")
    public ResultBean<Void> export(HttpServletResponse res, CategoryListDto param) throws IOException {
        categoryService.export(res,param);
        return new ResultBean<>();
    }

}
