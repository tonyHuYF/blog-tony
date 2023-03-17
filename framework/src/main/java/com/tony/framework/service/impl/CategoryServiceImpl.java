package com.tony.framework.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tony.framework.constants.SystemConstants;
import com.tony.framework.domain.Category;
import com.tony.framework.domain.dto.CategoryListDto;
import com.tony.framework.domain.vo.CategoryListVo;
import com.tony.framework.domain.vo.CategoryVo;
import com.tony.framework.domain.vo.PageVo;
import com.tony.framework.mapper.CategoryMapper;
import com.tony.framework.service.CategoryService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public PageVo<CategoryListVo> getCategoryListPage(Page page, CategoryListDto param) {
        IPage<CategoryListVo> pageVo = categoryMapper.getCategoryListPage(page, param);
        return new PageVo<>(pageVo.getTotal(), pageVo.getRecords());
    }

    @Override
    public void export(HttpServletResponse res, CategoryListDto param) throws IOException {
        PageVo<CategoryListVo> pageVo = getCategoryListPage(new Page(1, Integer.MAX_VALUE), param);
        List<CategoryListVo> records = pageVo.getRows();

        Map<String, Object> map = new HashMap<>();
        List<Map<String, String>> mapList = new ArrayList();

        records.stream().forEach(p -> {
            Map<String, String> temp = new HashMap<>();
            temp.put("name", p.getName());
            temp.put("description", p.getDescription());
            temp.put("status", p.getStatus().equals("0") ? "正常" : "禁用");

            mapList.add(temp);
        });

        map.put("mapList", mapList);

        TemplateExportParams params = new TemplateExportParams("template/categoryList.xls");

        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        ServletOutputStream outputStream = res.getOutputStream();
        workbook.write(outputStream);
        outputStream.close();
    }
}




