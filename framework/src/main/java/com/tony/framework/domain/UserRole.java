package com.tony.framework.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
* 用户和角色关联表
* @TableName sys_user_role
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user_role")
public class UserRole implements Serializable {

    /**
    * 用户ID
    */
    @TableId
    private Long userId;
    /**
    * 角色ID
    */
    private Long roleId;

}
