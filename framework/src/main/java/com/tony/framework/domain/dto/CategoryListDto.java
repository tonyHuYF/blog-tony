package com.tony.framework.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryListDto {
    /**
     * 分类名
     */
    private String name;
    /**
     * 状态
     */
    private String status;
}
