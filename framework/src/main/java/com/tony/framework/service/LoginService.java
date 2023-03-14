package com.tony.framework.service;

import com.tony.framework.domain.User;

import java.util.Map;

public interface LoginService {
    Map<String, String> login(User user);

    void logout();
}
