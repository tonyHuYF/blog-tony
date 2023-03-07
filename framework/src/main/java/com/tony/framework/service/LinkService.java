package com.tony.framework.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tony.framework.domain.Link;
import com.tony.framework.domain.vo.LinkVo;

import java.util.List;

/**
* @author TonyHu
* @description 针对表【t_link(友链)】的数据库操作Service
* @createDate 2023-03-07 16:37:30
*/
public interface LinkService extends IService<Link> {

    List<LinkVo> getAllLink();
}
