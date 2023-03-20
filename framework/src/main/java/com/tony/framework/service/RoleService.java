package com.tony.framework.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tony.framework.domain.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tony.framework.domain.dto.RoleInsertDto;
import com.tony.framework.domain.dto.RoleListDto;
import com.tony.framework.domain.vo.PageVo;
import com.tony.framework.domain.vo.RoleVo;

import java.util.List;

/**
* @author TonyHu
* @description 针对表【sys_role(角色信息表)】的数据库操作Service
* @createDate 2023-03-15 10:56:46
*/
public interface RoleService extends IService<Role> {

    List<String> selectRoleKeyByUserId(Long id);

    PageVo<RoleVo> listPage(Page page, RoleListDto param);

    void insert(RoleInsertDto roleInsertDto);

    void delete(Integer id);

    List<Role> listAllRole();

}
