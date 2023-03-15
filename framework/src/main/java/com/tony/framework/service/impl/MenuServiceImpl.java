package com.tony.framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tony.framework.constants.SystemConstants;
import com.tony.framework.domain.Menu;
import com.tony.framework.service.MenuService;
import com.tony.framework.mapper.MenuMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author TonyHu
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Service实现
* @createDate 2023-03-15 10:57:07
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService{



    @Override
    public List<String> selectPermsByUserId(Long id) {

        //如果是管理员，返回所有权限
        if(id ==1L){
            LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
            wrapper.in(Menu::getMenuType, SystemConstants.MENU,SystemConstants.BUTTON);
            wrapper.eq(Menu::getStatus,SystemConstants.SYSTEM_STATUS_NORMAL);
            List<Menu> list = list(wrapper);
            List<String> perms = list.stream().map(Menu::getPerms).collect(Collectors.toList());
            return perms;
        }

        //否则返回具有的权限

        return getBaseMapper().selectPermsByUserId(id);
    }
}




