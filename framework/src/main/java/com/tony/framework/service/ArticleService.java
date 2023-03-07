package com.tony.framework.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tony.framework.domain.Article;
import com.tony.framework.domain.vo.ArticleDetailVo;
import com.tony.framework.domain.vo.ArticleVo;
import com.tony.framework.domain.vo.HotArticleVo;
import com.tony.framework.domain.vo.PageVo;

import java.util.List;

/**
 * @author TonyHu
 * @description 针对表【t_article(文章表)】的数据库操作Service
 * @createDate 2023-03-06 14:47:54
 */
public interface ArticleService extends IService<Article> {

    List<HotArticleVo> getHotArticleList();

    PageVo<ArticleVo> getArticleList(Page page, Integer categoryId);

    ArticleDetailVo articleDetail(Integer id);
}
