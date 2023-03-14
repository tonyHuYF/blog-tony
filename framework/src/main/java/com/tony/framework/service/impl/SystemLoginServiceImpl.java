package com.tony.framework.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.tony.framework.constants.RedisConstants;
import com.tony.framework.domain.Error;
import com.tony.framework.domain.LoginUser;
import com.tony.framework.domain.User;
import com.tony.framework.exception.CommonException;
import com.tony.framework.service.LoginService;
import com.tony.framework.utils.JwtUtil;
import com.tony.framework.utils.RedisCache;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class SystemLoginServiceImpl implements LoginService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisCache redisCache;

    @Override
    public Map<String, String> login(User user) {

        if (ObjectUtil.isEmpty(user.getUserName())) {
            throw new CommonException(Error.username_not_exist);
        }

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
        redisCache.setCacheObject(RedisConstants.USER_ADMIN + userId, loginUser, 120, TimeUnit.MINUTES);

        Map<String,String> map =new HashMap<>();
        map.put("token",jwt);

        return map;
    }

    @Override
    public void logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        redisCache.deleteObject(RedisConstants.USER_ADMIN + loginUser.getUser().getId());
    }
}
