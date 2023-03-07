package com.tony.framework.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetailVo extends ArticleVo{
    /**
     * 文章内容
     */
    private String content;
}
