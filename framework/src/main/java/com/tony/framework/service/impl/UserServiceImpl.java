package com.tony.framework.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tony.framework.constants.RedisConstants;
import com.tony.framework.domain.Error;
import com.tony.framework.domain.LoginUser;
import com.tony.framework.domain.User;
import com.tony.framework.domain.dto.UserInsertDto;
import com.tony.framework.domain.dto.UserListDto;
import com.tony.framework.domain.vo.PageVo;
import com.tony.framework.domain.vo.UserInfoVo;
import com.tony.framework.domain.vo.UserListVo;
import com.tony.framework.exception.CommonException;
import com.tony.framework.mapper.RoleMapper;
import com.tony.framework.mapper.UserMapper;
import com.tony.framework.service.UserService;
import com.tony.framework.utils.RedisCache;
import com.tony.framework.utils.SecurityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private RoleMapper roleMapper;

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


    @Override
    public void updateUserInfo(User user) {
        LoginUser loginUser = (LoginUser) redisCache.getCacheObject(RedisConstants.USER_BLOG + user.getId());
        User redisUser = loginUser.getUser();
        BeanUtil.copyProperties(user, redisUser);
        updateById(redisUser);
        //更新redis
        loginUser.setUser(redisUser);
        redisCache.setCacheObject(RedisConstants.USER_BLOG + redisUser.getId(), loginUser, 120, TimeUnit.MINUTES);
    }

    @Override
    public void register(User user) {
        if (existUserName(user.getUserName())) {
            throw new CommonException(Error.username_exist);
        }

        if (existNickName(user.getNickName())) {
            throw new CommonException(Error.nickrname_exist);
        }

        //密码加密
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);

        save(user);
    }


    private boolean existUserName(String userName) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName, userName);
        long count = count(wrapper);
        return count > 0;
    }

    private boolean existNickName(String nickName) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getNickName, nickName);
        long count = count(wrapper);
        return count > 0;
    }

    @Override
    public PageVo<UserListVo> listPage(Page page, UserListDto param) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(ObjectUtil.isNotEmpty(param.getUserName()), User::getUserName, param.getUserName());
        wrapper.like(ObjectUtil.isNotEmpty(param.getStatus()), User::getStatus, param.getStatus());
        wrapper.like(ObjectUtil.isNotEmpty(param.getPhonenumber()), User::getPhonenumber, param.getPhonenumber());
        Page data = page(page, wrapper);

        return new PageVo<>(data.getTotal(), BeanUtil.copyToList(data.getRecords(), UserListVo.class));
    }

    @Override
    @Transactional
    public void insert(UserInsertDto param) {
        User user = new User();
        BeanUtil.copyProperties(param, user);
        save(user);

        if (ObjectUtil.isNotEmpty(param.getRoleIds())) {
            param.setId(user.getId());
            roleMapper.insertUserRelate(param);
        }
    }

    @Override
    @Transactional
    public void delete(String id) {
        removeById(id);
        roleMapper.deleteUserRelate(id);
    }
}




