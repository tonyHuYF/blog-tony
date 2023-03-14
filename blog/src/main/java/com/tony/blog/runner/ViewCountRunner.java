package com.tony.blog.runner;

import com.tony.framework.constants.RedisConstants;
import com.tony.framework.domain.Article;
import com.tony.framework.service.ArticleService;
import com.tony.framework.utils.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ViewCountRunner implements CommandLineRunner {

    @Resource
    private RedisCache redisCache;

    @Resource
    private ArticleService articleService;


    @Override
    public void run(String... args) throws Exception {
        //系统启动，将所有文章对应浏览次数放到redis（预处理）
        log.info("==========将所有文章对应浏览次数放到redis（预处理）===================");
        List<Article> list = articleService.list();

        Map<String, Integer> map = list.stream().collect(Collectors.toMap(
                k -> k.getId().toString(), v -> v.getViewCount().intValue()));

        redisCache.setCacheMap(RedisConstants.ARTICLE_VIEW_COUNT, map);
    }
}
