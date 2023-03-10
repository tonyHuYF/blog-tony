package com.tony.blog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tony.framework.domain.ResultBean;
import com.tony.framework.domain.vo.CommentVo;
import com.tony.framework.domain.vo.PageVo;
import com.tony.framework.service.CommentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
