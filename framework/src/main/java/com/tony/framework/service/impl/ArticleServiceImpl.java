package com.tony.framework.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tony.framework.constants.RedisConstants;
import com.tony.framework.constants.SystemConstants;
import com.tony.framework.domain.Article;
import com.tony.framework.domain.ArticleTag;
import com.tony.framework.domain.dto.ArticleDto;
import com.tony.framework.domain.vo.*;
import com.tony.framework.mapper.ArticleMapper;
import com.tony.framework.mapper.ArticleTagMapper;
import com.tony.framework.service.ArticleService;
import com.tony.framework.utils.RedisCache;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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

    @Resource
    private ArticleTagMapper articleTagMapper;

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

    @Override
    @Transactional
    public void insert(ArticleDto articleDto) {

        Article article = new Article();
        BeanUtil.copyProperties(articleDto, article);

        save(article);

        if (ObjectUtil.isNotEmpty(articleDto.getTags())) {
            for (Long tagId : articleDto.getTags()) {
                articleTagMapper.insert(new ArticleTag(article.getId(), tagId));
            }
        }
    }

    @Override
    public PageVo<ArticleListVo> getArticlePageList(Page page, ArticleDto param) {
        IPage<ArticleListVo> result = articleMapper.getArticlePageList(page, param);

        List<ArticleListVo> records = result.getRecords();

        PageVo pageVo = new PageVo(result.getTotal(), records);

        return pageVo;
    }

    @Override
    public ArticleDto getDetailById(Integer id) {
        Article article = getById(id);
        ArticleDto vo = new ArticleDto();
        BeanUtil.copyProperties(article, vo);

        LambdaQueryWrapper<ArticleTag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ArticleTag::getArticleId, id);
        List<ArticleTag> articleTags = articleTagMapper.selectList(wrapper);

        List<Long> tagIds = articleTags.stream().map(ArticleTag::getTagId).collect(Collectors.toList());

        Long[] tags = tagIds.toArray(new Long[tagIds.size()]);

        vo.setTags(tags);

        return vo;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        removeById(id);
        LambdaQueryWrapper<ArticleTag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ArticleTag::getArticleId, id);
        articleTagMapper.delete(wrapper);
    }
}




