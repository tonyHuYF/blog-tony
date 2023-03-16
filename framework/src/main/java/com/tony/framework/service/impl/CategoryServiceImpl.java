package com.tony.framework.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tony.framework.constants.SystemConstants;
import com.tony.framework.domain.Category;
import com.tony.framework.domain.vo.CategoryVo;
import com.tony.framework.service.CategoryService;
import com.tony.framework.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author TonyHu
 * @description 针对表【t_category(分类表)】的数据库操作Service实现
 * @createDate 2023-03-07 11:02:57
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
        implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryVo> getCategoryList() {
        return categoryMapper.getCategoryList();
    }

    @Override
    public List<CategoryVo> listAllCategory() {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getStatus, SystemConstants.SYSTEM_STATUS_NORMAL);
        List<Category> list = list(wrapper);
        List<CategoryVo> categoryVos = BeanUtil.copyToList(list, CategoryVo.class);
        return categoryVos;
    }
}




