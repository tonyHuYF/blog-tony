package com.tony.framework.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
* 角色信息表
* @TableName sys_role
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleVo implements Serializable {

    /**
    * 角色ID
    */
    private Long id;
    /**
    * 角色名称
    */
    private String roleName;
    /**
    * 角色权限字符串
    */
    private String roleKey;
    /**
    * 显示顺序
    */
    private Integer roleSort;
    /**
    * 角色状态（0正常 1停用）
    */
    private String status;
    /**
    * 删除标志（0代表存在 1代表删除）
    */
    private String delFlag;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 更新时间
    */
    private Date updateTime;

}
