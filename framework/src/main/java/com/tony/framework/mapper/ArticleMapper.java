package com.tony.framework.mapper;

import com.tony.framework.domain.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author TonyHu
* @description 针对表【t_article(文章表)】的数据库操作Mapper
* @createDate 2023-03-06 14:47:54
* @Entity com.tony.framework.domain.Article
*/
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

}




