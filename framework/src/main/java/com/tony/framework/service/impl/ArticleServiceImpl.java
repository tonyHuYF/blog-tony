package com.tony.framework.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tony.framework.domain.Article;
import com.tony.framework.service.ArticleService;
import com.tony.framework.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

/**
* @author TonyHu
* @description 针对表【t_article(文章表)】的数据库操作Service实现
* @createDate 2023-03-06 14:47:54
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

}




