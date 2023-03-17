package com.tony.framework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tony.framework.domain.Category;
import com.tony.framework.domain.dto.CategoryListDto;
import com.tony.framework.domain.vo.CategoryListVo;
import com.tony.framework.domain.vo.CategoryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    IPage<CategoryListVo> getCategoryListPage(Page page, @Param("param") CategoryListDto param);
}




