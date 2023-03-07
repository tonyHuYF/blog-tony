package com.tony.framework.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tony.framework.constants.SystemConstants;
import com.tony.framework.domain.Link;
import com.tony.framework.domain.vo.LinkVo;
import com.tony.framework.mapper.LinkMapper;
import com.tony.framework.service.LinkService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author TonyHu
 * @description 针对表【t_link(友链)】的数据库操作Service实现
 * @createDate 2023-03-07 16:37:30
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link>
        implements LinkService {

    @Override
    public List<LinkVo> getAllLink() {
        LambdaQueryWrapper<Link> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> list = list(wrapper);
        List<LinkVo> linkVos = BeanUtil.copyToList(list, LinkVo.class);
        return linkVos;
    }
}




