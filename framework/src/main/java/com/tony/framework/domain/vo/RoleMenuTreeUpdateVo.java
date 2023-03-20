package com.tony.framework.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleMenuTreeUpdateVo {
    /**
     * 菜单
     */
    private List<RoleMenuTreeVo> menus;
    /**
     * 关联的菜单id
     */
    private List<Long> checkedKeys;

}
