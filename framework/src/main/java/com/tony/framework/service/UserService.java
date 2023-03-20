package com.tony.framework.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tony.framework.domain.User;
import com.tony.framework.domain.dto.UserInsertDto;
import com.tony.framework.domain.dto.UserListDto;
import com.tony.framework.domain.vo.PageVo;
import com.tony.framework.domain.vo.UserInfoVo;
import com.tony.framework.domain.vo.UserListVo;

import java.util.List;

/**
* @author TonyHu
* @description 针对表【sys_user(用户表)】的数据库操作Service
* @createDate 2023-03-07 16:59:00
*/
public interface UserService extends IService<User> {

    List<User> getUserList();

    UserInfoVo userInfo();


    void updateUserInfo(User user);

    void register(User user);

    PageVo<UserListVo> listPage(Page page, UserListDto param);

    void insert(UserInsertDto param);

    void delete(String id);

}
