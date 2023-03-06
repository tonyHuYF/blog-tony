package com.tony.framework.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tony.framework.constants.SystemConstants;
import com.tony.framework.domain.Article;
import com.tony.framework.domain.vo.HotArticleVo;
import com.tony.framework.service.ArticleService;
import com.tony.framework.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author TonyHu
 * @description 针对表【t_article(文章表)】的数据库操作Service实现
 * @createDate 2023-03-06 14:47:54
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
        implements ArticleService {

    @Override
    public List<HotArticleVo> getHotArticleList() {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        wrapper.orderByDesc(Article::getViewCount);
        Page<Article> page = page(new Page<>(), wrapper);
        List<HotArticleVo> hotArticleVos = BeanUtil.copyToList(page.getRecords(), HotArticleVo.class);
        return hotArticleVos;
    }
}




