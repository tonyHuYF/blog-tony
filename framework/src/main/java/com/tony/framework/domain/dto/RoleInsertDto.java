package com.tony.framework.domain.dto;

import com.tony.framework.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleInsertDto extends Role {
    /**
     * 菜单ids
     */
    private List<Long> menuIds;

}
