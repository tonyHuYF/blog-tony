package com.tony.framework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tony.framework.domain.Article;
import com.tony.framework.domain.dto.ArticleDto;
import com.tony.framework.domain.vo.ArticleDetailVo;
import com.tony.framework.domain.vo.ArticleListVo;
import com.tony.framework.domain.vo.ArticleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author TonyHu
* @description 针对表【t_article(文章表)】的数据库操作Mapper
* @createDate 2023-03-06 14:47:54
* @Entity com.tony.framework.domain.Article
*/
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    IPage<ArticleVo> getArticleList(IPage iPage, @Param("categoryId")Integer categoryId);

    ArticleDetailVo getArticleById(Integer id);


    IPage<ArticleListVo> getArticlePageList(Page iPage, @Param("param") ArticleDto param);

}




