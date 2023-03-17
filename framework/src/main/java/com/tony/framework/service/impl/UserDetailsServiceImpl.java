package com.tony.framework.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tony.framework.constants.SystemConstants;
import com.tony.framework.domain.Error;
import com.tony.framework.domain.LoginUser;
import com.tony.framework.domain.User;
import com.tony.framework.mapper.UserMapper;
import com.tony.framework.service.MenuService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private MenuService menuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName, username);
        User user = userMapper.selectOne(wrapper);

        if (ObjectUtil.isEmpty(user)) {
            throw new RuntimeException(Error.username_not_exist.getMessage());
        }

        //返回用户信息
        //只有后台用户才需要验证权限
        if (user.getType().equals(SystemConstants.ADMIN)) {
            List<String> perms = menuService.selectPermsByUserId(user.getId());
            return new LoginUser(user, perms);
        }

        return new LoginUser(user, null);
    }
}
