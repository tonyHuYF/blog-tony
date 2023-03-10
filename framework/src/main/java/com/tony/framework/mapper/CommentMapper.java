package com.tony.framework.mapper;

import com.tony.framework.domain.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author TonyHu
* @description 针对表【t_comment(评论表)】的数据库操作Mapper
* @createDate 2023-03-10 11:16:54
* @Entity com.tony.framework.domain.Comment
*/
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}




