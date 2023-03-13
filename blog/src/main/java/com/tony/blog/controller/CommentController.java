package com.tony.blog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tony.framework.domain.Comment;
import com.tony.framework.domain.ResultBean;
import com.tony.framework.domain.vo.CommentVo;
import com.tony.framework.domain.vo.PageVo;
import com.tony.framework.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @GetMapping("/commentList")
    public ResultBean<PageVo<CommentVo>> commentList(Integer pageNum, Integer pageSize, Integer articleId) {
        PageVo<CommentVo> vo = commentService.commentList(new Page(pageNum, pageSize), articleId);
        return new ResultBean<>(vo);
    }

    @GetMapping("/linkCommentList")
    public ResultBean<PageVo<CommentVo>> linkCommentList(Integer pageNum, Integer pageSize) {
        PageVo<CommentVo> vo = commentService.linkCommentList(new Page(pageNum, pageSize));
        return new ResultBean<>(vo);
    }

    @PostMapping
    public ResultBean<Void> addComment(@RequestBody Comment comment) {
        commentService.addComment(comment);
        return new ResultBean<>();
    }

}
