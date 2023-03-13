package com.tony.framework.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tony.framework.domain.Comment;
import com.tony.framework.domain.User;
import com.tony.framework.domain.vo.CommentVo;
import com.tony.framework.domain.vo.PageVo;
import com.tony.framework.mapper.CommentMapper;
import com.tony.framework.service.CommentService;
import com.tony.framework.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author TonyHu
 * @description 针对表【t_comment(评论表)】的数据库操作Service实现
 * @createDate 2023-03-10 11:16:54
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
        implements CommentService {

    @Resource
    private UserService userService;

    @Override
    public PageVo<CommentVo> commentList(Page page, Integer articleId) {
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Comment::getType, "0");
        wrapper.eq(Comment::getArticleId, articleId);
        wrapper.orderByDesc(Comment::getCreateTime);

        //查询该文章所有评论（根评论、子评论）
        List<Comment> list = list(wrapper);

        //子评论map
        Map<Long, List<Comment>> map = list.stream()
                .filter(p -> p.getRootId() != -1)
                .sorted(Comparator.comparing(Comment::getCreateTime))
                .collect(Collectors.groupingBy(Comment::getRootId));

        //分页查询只查根评论
        wrapper.eq(Comment::getRootId, -1);

        Page<Comment> commentPage = page(page, wrapper);

        List<CommentVo> vos = BeanUtil.copyToList(commentPage.getRecords(), CommentVo.class);

        List<User> userList = userService.getUserList();

        Map<Long, String> userMap = userList.stream().collect(Collectors.toMap(k -> k.getId(), v -> v.getNickName()));

        //补全子评论
        vos.forEach(p -> {
            //补全根评论人名称
            p.setUserName(userMap.get(p.getCreateBy()));
            p.setToCommentUserName(userMap.get(p.getToCommentUserId()));

            if (map.get(p.getId()) != null) {
                List<CommentVo> commentVos = BeanUtil.copyToList(map.get(p.getId()), CommentVo.class);
                commentVos.forEach(child -> {
                    child.setUserName(userMap.get(child.getCreateBy()));
                    child.setToCommentUserName(userMap.get(child.getToCommentUserId()));
                });
                p.setChildren(commentVos);
            }
        });

        return new PageVo<>(commentPage.getTotal(), vos);
    }

    @Override
    public void addComment(Comment comment) {
        save(comment);
    }

    @Override
    public PageVo<CommentVo> linkCommentList(Page page) {
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Comment::getType, "1");
        wrapper.orderByDesc(Comment::getCreateTime);

        //查询该文章所有评论（根评论、子评论）
        List<Comment> list = list(wrapper);

        //子评论map
        Map<Long, List<Comment>> map = list.stream()
                .filter(p -> p.getRootId() != -1)
                .sorted(Comparator.comparing(Comment::getCreateTime))
                .collect(Collectors.groupingBy(Comment::getRootId));

        //分页查询只查根评论
        wrapper.eq(Comment::getRootId, -1);

        Page<Comment> commentPage = page(page, wrapper);

        List<CommentVo> vos = BeanUtil.copyToList(commentPage.getRecords(), CommentVo.class);

        List<User> userList = userService.getUserList();

        Map<Long, String> userMap = userList.stream().collect(Collectors.toMap(k -> k.getId(), v -> v.getNickName()));

        //补全子评论
        vos.forEach(p -> {
            //补全根评论人名称
            p.setUserName(userMap.get(p.getCreateBy()));
            p.setToCommentUserName(userMap.get(p.getToCommentUserId()));

            if (map.get(p.getId()) != null) {
                List<CommentVo> commentVos = BeanUtil.copyToList(map.get(p.getId()), CommentVo.class);
                commentVos.forEach(child -> {
                    child.setUserName(userMap.get(child.getCreateBy()));
                    child.setToCommentUserName(userMap.get(child.getToCommentUserId()));
                });
                p.setChildren(commentVos);
            }
        });

        return new PageVo<>(commentPage.getTotal(), vos);
    }
}




