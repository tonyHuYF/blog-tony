package com.tony.framework.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tony.framework.domain.Error;
import com.tony.framework.domain.LoginUser;
import com.tony.framework.domain.User;
import com.tony.framework.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName, username);
        User user = userMapper.selectOne(wrapper);

        if (ObjectUtil.isEmpty(user)) {
            throw new RuntimeException(Error.username_not_exist.getMessage());
        }

        //返回用户信息
        //todo 查询用户权限并封装

        return new LoginUser(user);
    }
}
