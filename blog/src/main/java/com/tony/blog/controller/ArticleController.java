package com.tony.blog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tony.framework.domain.ResultBean;
import com.tony.framework.domain.vo.ArticleDetailVo;
import com.tony.framework.domain.vo.ArticleVo;
import com.tony.framework.domain.vo.HotArticleVo;
import com.tony.framework.domain.vo.PageVo;
import com.tony.framework.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;


    @GetMapping("/hotArticleList")
    public ResultBean<List<HotArticleVo>> hotArticleList() {
        List<HotArticleVo> list = articleService.getHotArticleList();
        return new ResultBean<>(list);
    }

    @GetMapping("/articleList")
    public ResultBean<PageVo<ArticleVo>> articleList(Integer pageNum, Integer pageSize, Integer categoryId) {
        PageVo<ArticleVo> list = articleService.getArticleList(new Page(pageNum, pageSize), categoryId);
        return new ResultBean<>(list);
    }

    @GetMapping("/{id}")
    public ResultBean<ArticleDetailVo> articleDetail(@PathVariable Integer id) {
        ArticleDetailVo articleDetailVo = articleService.articleDetail(id);
        return new ResultBean<>(articleDetailVo);
    }

    @PutMapping("/updateViewCount/{id}")
    public ResultBean<Void>updateViewCount(@PathVariable Integer id){
        articleService.updateViewCount(id);
        return new ResultBean<>();
    }

}
