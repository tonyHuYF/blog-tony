package com.tony.framework.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tony.framework.domain.Menu;
import com.tony.framework.domain.vo.MenuListVo;
import com.tony.framework.domain.vo.MenuVo;
import com.tony.framework.domain.vo.RoleMenuTreeUpdateVo;
import com.tony.framework.domain.vo.RoleMenuTreeVo;

import java.util.List;

/**
 * @author TonyHu
 * @description 针对表【sys_menu(菜单权限表)】的数据库操作Service
 * @createDate 2023-03-15 10:57:07
 */
public interface MenuService extends IService<Menu> {

    List<String> selectPermsByUserId(Long id);

    List<MenuVo> selectRouterMenuTreeByUserId(Long id);

    List<MenuListVo> allList(String menuName, String status);


    RoleMenuTreeUpdateVo roleMenuTreeSelect(Integer id);

    List<RoleMenuTreeVo> treeSelect();

}
