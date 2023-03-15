package com.tony.framework.service;

import com.tony.framework.domain.User;
import com.tony.framework.domain.vo.AdminUserInfoVo;

import java.util.Map;

public interface LoginService {
    Map<String, String> login(User user);

    void logout();

    AdminUserInfoVo getInfo();

}
