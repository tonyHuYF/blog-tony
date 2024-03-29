package com.tony.framework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tony.framework.domain.Tag;
import com.tony.framework.domain.dto.TagListDto;
import com.tony.framework.domain.vo.TagVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author TonyHu
 * @description 针对表【t_tag(标签)】的数据库操作Mapper
 * @createDate 2023-03-14 15:26:48
 * @Entity com.tony.framework.domain.Tag
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {


    IPage<TagVo> getTagList(Page Page, @Param("param") TagListDto param);
}




