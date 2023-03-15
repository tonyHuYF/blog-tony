package com.tony.framework.service;

import com.tony.framework.domain.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author TonyHu
* @description 针对表【sys_role(角色信息表)】的数据库操作Service
* @createDate 2023-03-15 10:56:46
*/
public interface RoleService extends IService<Role> {

    List<String> selectRoleKeyByUserId(Long id);

}
