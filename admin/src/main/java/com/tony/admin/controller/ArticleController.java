package com.tony.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tony.framework.domain.ResultBean;
import com.tony.framework.domain.dto.ArticleDto;
import com.tony.framework.domain.vo.ArticleListVo;
import com.tony.framework.domain.vo.PageVo;
import com.tony.framework.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/content/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @PostMapping
    public ResultBean<Void> insert(@RequestBody ArticleDto articleDto) {
        articleService.insert(articleDto);
        return new ResultBean<>();
    }

    @GetMapping("/list")
    public ResultBean<PageVo<ArticleListVo>> getArticleList(Integer pageNum, Integer pageSize, ArticleDto param) {
        PageVo<ArticleListVo> vo = articleService.getArticlePageList(new Page(pageNum, pageSize), param);
        return new ResultBean<>(vo);
    }

    @GetMapping("/{id}")
    public ResultBean<ArticleDto> getById(@PathVariable Integer id) {
        ArticleDto detail = articleService.getDetailById(id);
        return new ResultBean<>(detail);
    }

    @DeleteMapping("/{id}")
    public ResultBean<Void> delete(@PathVariable Integer id) {
        articleService.delete(id);
        return new ResultBean<>();
    }

}
