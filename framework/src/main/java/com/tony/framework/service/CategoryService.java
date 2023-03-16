package com.tony.framework.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tony.framework.domain.Category;
import com.tony.framework.domain.vo.CategoryVo;

import java.util.List;

/**
* @author TonyHu
* @description 针对表【t_category(分类表)】的数据库操作Service
* @createDate 2023-03-07 11:02:57
*/
public interface CategoryService extends IService<Category> {

    List<CategoryVo> getCategoryList();

    List<CategoryVo> listAllCategory();

}
