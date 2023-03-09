package com.tony.framework.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.tony.framework.domain.Error;
import com.tony.framework.domain.LoginUser;
import com.tony.framework.domain.User;
import com.tony.framework.domain.vo.BlogUserLoginVo;
import com.tony.framework.domain.vo.UserInfoVo;
import com.tony.framework.service.BlogLoginService;
import com.tony.framework.utils.JwtUtil;
import com.tony.framework.utils.RedisCache;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BlogLoginServiceImpl implements BlogLoginService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisCache redisCache;

    @Override
    public BlogUserLoginVo login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        if (authenticate == null) {
            throw new RuntimeException(Error.username_password_not_exist.getMessage());
        }

        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();

        String userId = loginUser.getUser().getId().toString();

        //生成jwt
        String jwt = JwtUtil.createJWT(userId);

        //存入redis
        redisCache.setCacheObject("blogUser:" + userId, loginUser);

        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtil.copyProperties(loginUser.getUser(), userInfoVo);

        return new BlogUserLoginVo(jwt, userInfoVo);
    }
}
