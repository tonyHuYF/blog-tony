package com.tony.framework.service;

import com.tony.framework.domain.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author TonyHu
* @description 针对表【sys_menu(菜单权限表)】的数据库操作Service
* @createDate 2023-03-15 10:57:07
*/
public interface MenuService extends IService<Menu> {

    List<String> selectPermsByUserId(Long id);

}
