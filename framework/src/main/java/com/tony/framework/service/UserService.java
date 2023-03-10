package com.tony.framework.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tony.framework.domain.User;

import java.util.List;

/**
* @author TonyHu
* @description 针对表【sys_user(用户表)】的数据库操作Service
* @createDate 2023-03-07 16:59:00
*/
public interface UserService extends IService<User> {

    List<User> getUserList();
}
