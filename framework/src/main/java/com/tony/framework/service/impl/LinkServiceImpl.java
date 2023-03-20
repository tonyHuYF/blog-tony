package com.tony.framework.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tony.framework.constants.SystemConstants;
import com.tony.framework.domain.Link;
import com.tony.framework.domain.dto.LinkListDto;
import com.tony.framework.domain.vo.LinkVo;
import com.tony.framework.domain.vo.PageVo;
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

    @Override
    public PageVo<LinkVo> listPage(Page page, LinkListDto param) {
        LambdaQueryWrapper<Link> wrapper = new LambdaQueryWrapper();
        wrapper.like(ObjectUtil.isNotEmpty(param.getName()), Link::getName, param.getName());
        wrapper.eq(ObjectUtil.isNotEmpty(param.getStatus()), Link::getStatus, param.getStatus());
        Page data = page(page, wrapper);

        return new PageVo<>(data.getTotal(), BeanUtil.copyToList(data.getRecords(), LinkVo.class));
    }
}




