package com.tony.framework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tony.framework.domain.Category;
import com.tony.framework.domain.vo.CategoryVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author TonyHu
* @description 针对表【t_category(分类表)】的数据库操作Mapper
* @createDate 2023-03-07 11:02:57
* @Entity com.tony.framework.domain.Category
*/
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    List<CategoryVo> getCategoryList();
}




