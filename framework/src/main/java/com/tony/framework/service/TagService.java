package com.tony.framework.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tony.framework.domain.Tag;
import com.tony.framework.domain.dto.TagListDto;
import com.tony.framework.domain.vo.PageVo;
import com.tony.framework.domain.vo.TagVo;

import java.util.List;

/**
* @author TonyHu
* @description 针对表【t_tag(标签)】的数据库操作Service
* @createDate 2023-03-14 15:26:48
*/
public interface TagService extends IService<Tag> {


    PageVo<TagVo> getTagList(Page page, TagListDto param);

    List<TagVo> listAllTag();

}
