package com.tony.framework.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleMenuTreeVo {
    /**
     * 菜单ID
     */
    private Long id;
    /**
     * 菜单名称
     */
    private String label;
    /**
     * 父菜单ID
     */
    private Long parentId;

    private List<RoleMenuTreeVo> children;

    public RoleMenuTreeVo(Long id, String label, Long parentId) {
        this.id = id;
        this.label = label;
        this.parentId = parentId;
    }
}
