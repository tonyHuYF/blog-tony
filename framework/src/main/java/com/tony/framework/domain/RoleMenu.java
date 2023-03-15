package com.tony.framework.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
* 角色和菜单关联表
* @TableName sys_role_menu
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_role_menu")
public class RoleMenu implements Serializable {

    /**
    * 角色ID
    */
    @TableId
    private Long roleId;
    /**
    * 菜单ID
    */
    private Long menuId;

}
