package com.tony.framework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tony.framework.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author TonyHu
* @description 针对表【sys_user(用户表)】的数据库操作Mapper
* @createDate 2023-03-07 16:59:00
* @Entity com.tony.framework.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




