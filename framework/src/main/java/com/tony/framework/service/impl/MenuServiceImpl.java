package com.tony.framework.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tony.framework.constants.SystemConstants;
import com.tony.framework.domain.Menu;
import com.tony.framework.domain.vo.MenuListVo;
import com.tony.framework.domain.vo.MenuVo;
import com.tony.framework.domain.vo.RoleMenuTreeUpdateVo;
import com.tony.framework.domain.vo.RoleMenuTreeVo;
import com.tony.framework.mapper.MenuMapper;
import com.tony.framework.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author TonyHu
 * @description 针对表【sys_menu(菜单权限表)】的数据库操作Service实现
 * @createDate 2023-03-15 10:57:07
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
        implements MenuService {


    @Override
    public List<String> selectPermsByUserId(Long id) {

        //如果是管理员，返回所有权限
        if (id == 1L) {
            LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
            wrapper.in(Menu::getMenuType, SystemConstants.MENU, SystemConstants.BUTTON);
            wrapper.eq(Menu::getStatus, SystemConstants.SYSTEM_STATUS_NORMAL);
            List<Menu> list = list(wrapper);
            List<String> perms = list.stream().map(Menu::getPerms).collect(Collectors.toList());
            return perms;
        }

        //否则返回具有的权限

        return getBaseMapper().selectPermsByUserId(id);
    }

    @Override
    public List<MenuVo> selectRouterMenuTreeByUserId(Long id) {
        //如果是管理员，返回所有权限
        List<MenuVo> menuVos;
        if (id == 1L) {
            menuVos = getBaseMapper().selectAllRouterMenu();
        } else {
            menuVos = getBaseMapper().selectRouterMenuTreeByUserId(id);
        }

        Map<Long, List<MenuVo>> map = menuVos.stream().collect(Collectors.groupingBy(MenuVo::getParentId));

        //补录子菜单
        menuVos.forEach(p -> p.setChildren(map.get(p.getId())));

        //构建菜单权限树
        List<MenuVo> treeMenus = menuVos.stream().filter(p -> p.getParentId() == 0).collect(Collectors.toList());
        return treeMenus;
    }


    @Override
    public List<MenuListVo> allList(String menuName, String status) {
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(ObjectUtil.isNotEmpty(menuName), Menu::getMenuName, menuName);
        wrapper.eq(ObjectUtil.isNotEmpty(status), Menu::getStatus, status);
        wrapper.orderByAsc(Menu::getParentId, Menu::getOrderNum);

        List<Menu> list = list(wrapper);

        List<MenuListVo> vos = BeanUtil.copyToList(list, MenuListVo.class);
        return vos;
    }

    @Override
    public RoleMenuTreeUpdateVo roleMenuTreeSelect(Integer id) {

        RoleMenuTreeUpdateVo result = new RoleMenuTreeUpdateVo();

        List<RoleMenuTreeVo> treeVos = treeSelect();

        result.setMenus(treeVos);

        List<Long> checkIds = getBaseMapper().selectMenuTreeByRoleId(id.longValue());

        result.setCheckedKeys(checkIds);

        return result;
    }

    @Override
    public List<RoleMenuTreeVo> treeSelect() {
        List<MenuVo> menuVos = getBaseMapper().selectAllRouterMenu();
        List<RoleMenuTreeVo> list = menuVos.stream().map(p ->
                new RoleMenuTreeVo(p.getId(), p.getMenuName(), p.getParentId())).collect(Collectors.toList());

        Map<Long, List<RoleMenuTreeVo>> map = list.stream().collect(Collectors.groupingBy(RoleMenuTreeVo::getParentId));

        //补录子菜单
        list.forEach(p -> p.setChildren(map.get(p.getId())));

        //构建菜单权限树
        List<RoleMenuTreeVo> treeMenus = list.stream().filter(p -> p.getParentId() == 0).collect(Collectors.toList());
        return treeMenus;
    }
}




