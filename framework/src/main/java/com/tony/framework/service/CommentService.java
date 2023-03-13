package com.tony.framework.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tony.framework.domain.Comment;
import com.tony.framework.domain.vo.CommentVo;
import com.tony.framework.domain.vo.PageVo;

/**
* @author TonyHu
* @description 针对表【t_comment(评论表)】的数据库操作Service
* @createDate 2023-03-10 11:16:54
*/
public interface CommentService extends IService<Comment> {

    PageVo<CommentVo> commentList(Page page, Integer articleId);

    void addComment(Comment comment);

    PageVo<CommentVo> linkCommentList(Page page);

}
