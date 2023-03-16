package com.tony.framework.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tony.framework.domain.Article;
import com.tony.framework.domain.dto.ArticleDto;
import com.tony.framework.domain.vo.*;

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

    void updateViewCount(Integer id);

    void insert(ArticleDto articleDto);

    PageVo<ArticleListVo> getArticlePageList(Page page, ArticleDto param);

    ArticleDto getDetailById(Integer id);

    void delete(Integer id);

}
