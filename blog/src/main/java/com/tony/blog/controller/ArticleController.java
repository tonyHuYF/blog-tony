package com.tony.blog.controller;

import com.tony.framework.domain.Article;
import com.tony.framework.domain.ResultBean;
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

    @GetMapping("/list")
    public ResultBean< List<Article> > list(){
        List<Article> list = articleService.list();
        return new ResultBean<>(list);
    }
}
