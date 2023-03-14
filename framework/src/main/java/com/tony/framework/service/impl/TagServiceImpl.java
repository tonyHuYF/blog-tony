package com.tony.framework.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tony.framework.domain.Tag;
import com.tony.framework.service.TagService;
import com.tony.framework.mapper.TagMapper;
import org.springframework.stereotype.Service;

/**
* @author TonyHu
* @description 针对表【t_tag(标签)】的数据库操作Service实现
* @createDate 2023-03-14 15:26:48
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{

}




