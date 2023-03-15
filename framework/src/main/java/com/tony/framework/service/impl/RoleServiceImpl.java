package com.tony.framework.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tony.framework.domain.Role;
import com.tony.framework.service.RoleService;
import com.tony.framework.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TonyHu
 * @description 针对表【sys_role(角色信息表)】的数据库操作Service实现
 * @createDate 2023-03-15 10:56:46
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
        implements RoleService {

    @Override
    public List<String> selectRoleKeyByUserId(Long id) {

        //如果是管理员，返回admin
        if (id == 1L) {
            List<String> roleKeys = new ArrayList<>();
            roleKeys.add("admin");
            return roleKeys;
        }
        //否则按用户id返回具有的角色
        return getBaseMapper().selectRoleKeyByUserId(id);
    }
}




