package com.tony.framework.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tony.framework.constants.RedisConstants;
import com.tony.framework.constants.SystemConstants;
import com.tony.framework.domain.Article;
import com.tony.framework.domain.vo.ArticleDetailVo;
import com.tony.framework.domain.vo.ArticleVo;
import com.tony.framework.domain.vo.HotArticleVo;
import com.tony.framework.domain.vo.PageVo;
import com.tony.framework.mapper.ArticleMapper;
import com.tony.framework.service.ArticleService;
import com.tony.framework.utils.RedisCache;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author TonyHu
 * @description 针对表【t_article(文章表)】的数据库操作Service实现
 * @createDate 2023-03-06 14:47:54
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
        implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private RedisCache redisCache;

    @Override
    public List<HotArticleVo> getHotArticleList() {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        wrapper.orderByDesc(Article::getViewCount);
        Page<Article> page = page(new Page<>(), wrapper);
        List<HotArticleVo> hotArticleVos = BeanUtil.copyToList(page.getRecords(), HotArticleVo.class);
        hotArticleVos.forEach(p -> {
            //从redis拿viewCount
            Integer viewCount = redisCache.getCacheMapValue(RedisConstants.ARTICLE_VIEW_COUNT, p.getId().toString());
            p.setViewCount(viewCount.longValue());
        });

        return hotArticleVos;
    }

    @Override
    public PageVo<ArticleVo> getArticleList(Page page, Integer categoryId) {

        IPage<ArticleVo> result = articleMapper.getArticleList(page, categoryId);

        List<ArticleVo> records = result.getRecords();

        records.forEach(p -> {
            Integer viewCount = redisCache.getCacheMapValue(RedisConstants.ARTICLE_VIEW_COUNT, p.getId().toString());
            p.setViewCount(viewCount.longValue());
        });

        PageVo pageVo = new PageVo(result.getTotal(), records);

        return pageVo;
    }

    @Override
    public ArticleDetailVo articleDetail(Integer id) {
        ArticleDetailVo articleById = articleMapper.getArticleById(id);
        //从redis拿viewCount
        Integer viewCount = redisCache.getCacheMapValue(RedisConstants.ARTICLE_VIEW_COUNT, id.toString());
        articleById.setViewCount(viewCount.longValue());
        return articleById;
    }

    @Override
    public void updateViewCount(Integer id) {
        redisCache.incrementCacheMapValue(RedisConstants.ARTICLE_VIEW_COUNT, id.toString(), 1);
    }
}




