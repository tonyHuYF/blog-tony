package com.tony.framework.service;

import com.tony.framework.domain.User;
import com.tony.framework.domain.vo.BlogUserLoginVo;

public interface BlogLoginService {
    BlogUserLoginVo login(User user);

}
