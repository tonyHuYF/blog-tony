package com.tony.framework.mapper;

import com.tony.framework.domain.ArticleTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author TonyHu
* @description 针对表【t_article_tag(文章标签关联表)】的数据库操作Mapper
* @createDate 2023-03-16 16:30:30
* @Entity com.tony.framework.domain.ArticleTag
*/
@Mapper
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {

}




