package com.tony.framework.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tony.framework.domain.Role;
import com.tony.framework.domain.dto.RoleInsertDto;
import com.tony.framework.domain.dto.RoleListDto;
import com.tony.framework.domain.vo.PageVo;
import com.tony.framework.domain.vo.RoleVo;
import com.tony.framework.mapper.RoleMapper;
import com.tony.framework.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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

    @Resource
    private RoleMapper roleMapper;

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

    @Override
    public PageVo<RoleVo> listPage(Page page, RoleListDto param) {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(ObjectUtil.isNotEmpty(param.getRoleName()), Role::getRoleName, param.getRoleName());
        wrapper.eq(ObjectUtil.isNotEmpty(param.getStatus()), Role::getStatus, param.getStatus());
        wrapper.orderByAsc(Role::getRoleSort);
        Page data = page(page, wrapper);
        return new PageVo<>(data.getTotal(), BeanUtil.copyToList(data.getRecords(), RoleVo.class));
    }

    @Override
    @Transactional
    public void insert(RoleInsertDto roleInsertDto) {
        Role roleData = new Role();
        BeanUtil.copyProperties(roleInsertDto, roleData);
        save(roleData);

        if (ObjectUtil.isNotEmpty(roleInsertDto.getMenuIds())) {
            roleInsertDto.setId(roleData.getId());
            roleMapper.insertRelate(roleInsertDto);
        }
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        removeById(id);
        roleMapper.deleteRelate(id);
    }

    @Override
    public List<Role> listAllRole() {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Role::getStatus, "0");
        List<Role> list = list(wrapper);
        return list;
    }
}




