package com.tony.blog.controller;

import com.tony.framework.domain.ResultBean;
import com.tony.framework.domain.vo.HotArticleVo;
import com.tony.framework.service.ArticleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
