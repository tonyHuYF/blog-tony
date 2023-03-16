package com.tony.framework.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
* 文章标签关联表
* @TableName t_article_tag
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_article_tag")
public class ArticleTag implements Serializable {

    /**
    * 文章id
    */
    @TableId
    private Long articleId;
    /**
    * 标签id
    */
    private Long tagId;

}
