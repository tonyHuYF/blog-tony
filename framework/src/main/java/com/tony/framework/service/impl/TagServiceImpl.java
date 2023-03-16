package com.tony.framework.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tony.framework.domain.Tag;
import com.tony.framework.domain.dto.TagListDto;
import com.tony.framework.domain.vo.PageVo;
import com.tony.framework.domain.vo.TagVo;
import com.tony.framework.mapper.TagMapper;
import com.tony.framework.service.TagService;
import org.springframework.stereotype.Service;

/**
 * @author TonyHu
 * @description 针对表【t_tag(标签)】的数据库操作Service实现
 * @createDate 2023-03-14 15:26:48
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
        implements TagService {

    @Override
    public PageVo<TagVo> getTagList(Page page, TagListDto param) {
        IPage<TagVo> tagVoIPage = getBaseMapper().getTagList(page, param);

        return new PageVo<>(tagVoIPage.getTotal(), tagVoIPage.getRecords());
    }
}




