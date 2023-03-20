package com.tony.framework.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserChangeStatusDto {
    /**
     * 角色名称
     */
    private String userId;
    /**
     * 角色状态（0正常 1停用）
     */
    private String status;
}
