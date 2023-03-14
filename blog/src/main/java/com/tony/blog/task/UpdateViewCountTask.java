package com.tony.blog.task;

import com.tony.framework.constants.RedisConstants;
import com.tony.framework.domain.Article;
import com.tony.framework.service.ArticleService;
import com.tony.framework.utils.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class UpdateViewCountTask {

    @Resource
    private RedisCache redisCache;

    @Resource
    private ArticleService articleService;

    //每10分钟运行
    @Scheduled(cron = "0 */10 * * * ?")
    public void taskTime() {
        log.info("==========每10分钟更新文章访问量（从redis->mysql）==========");
        Map<String, Integer> map = redisCache.getCacheMap(RedisConstants.ARTICLE_VIEW_COUNT);

        List<Article> articles = map.entrySet().stream().map(m -> new Article(Long.valueOf(m.getKey()), Long.valueOf(m.getValue())))
                .collect(Collectors.toList());

        articleService.updateBatchById(articles);

    }
}
