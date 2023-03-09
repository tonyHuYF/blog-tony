package com.tony.framework.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tony.framework.domain.User;
import com.tony.framework.service.UserService;
import com.tony.framework.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author TonyHu
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2023-03-07 16:59:00
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




