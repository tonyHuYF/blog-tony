package com.tony.framework.service.impl;

import com.tony.framework.domain.LoginUser;
import com.tony.framework.service.PermissionService;
import com.tony.framework.utils.SecurityUtils;
import org.springframework.stereotype.Service;

@Service("ps")
public class PermissionServiceImpl implements PermissionService {
    @Override
    public boolean hasPermission(String permission) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        return loginUser.getPermission().contains(permission);
    }
}
