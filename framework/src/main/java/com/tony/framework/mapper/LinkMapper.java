package com.tony.framework.mapper;

import com.tony.framework.domain.Link;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author TonyHu
* @description 针对表【t_link(友链)】的数据库操作Mapper
* @createDate 2023-03-07 16:37:30
* @Entity com.tony.framework.domain.Link
*/
@Mapper
public interface LinkMapper extends BaseMapper<Link> {

}




