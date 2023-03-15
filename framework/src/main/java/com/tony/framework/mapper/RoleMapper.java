package com.tony.framework.mapper;

import com.tony.framework.domain.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author TonyHu
* @description 针对表【sys_role(角色信息表)】的数据库操作Mapper
* @createDate 2023-03-15 10:56:46
* @Entity com.tony.framework.domain.Role
*/
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    List<String> selectRoleKeyByUserId(Long id);

}




