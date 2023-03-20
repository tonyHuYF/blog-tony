package com.tony.framework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tony.framework.domain.Menu;
import com.tony.framework.domain.vo.MenuVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author TonyHu
 * @description 针对表【sys_menu(菜单权限表)】的数据库操作Mapper
 * @createDate 2023-03-15 10:57:07
 * @Entity com.tony.framework.domain.Menu
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long id);

    List<MenuVo> selectAllRouterMenu();

    List<MenuVo> selectRouterMenuTreeByUserId(Long id);

    List<Long> selectMenuTreeByRoleId(Long id);

}




