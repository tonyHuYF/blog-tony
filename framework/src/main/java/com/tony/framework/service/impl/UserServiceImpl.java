package com.tony.framework.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tony.framework.constants.RedisConstants;
import com.tony.framework.domain.User;
import com.tony.framework.domain.vo.UserInfoVo;
import com.tony.framework.mapper.UserMapper;
import com.tony.framework.service.UserService;
import com.tony.framework.utils.RedisCache;
import com.tony.framework.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author TonyHu
 * @description 针对表【sys_user(用户表)】的数据库操作Service实现
 * @createDate 2023-03-07 16:59:00
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Resource
    private RedisCache redisCache;

    @Override
    public List<User> getUserList() {
        List<User> list = null;

        if (redisCache.getCacheObject(RedisConstants.USER_LIST) == null) {
            list = list();
            redisCache.setCacheObject(RedisConstants.USER_LIST, list, 120, TimeUnit.MINUTES);
        } else {
            list = redisCache.getCacheObject(RedisConstants.USER_LIST);
        }

        return list;
    }

    @Override
    public UserInfoVo userInfo() {
        User user = getById(SecurityUtils.getUserId());
        UserInfoVo vo = new UserInfoVo();
        BeanUtil.copyProperties(user, vo);
        return vo;
    }
}




