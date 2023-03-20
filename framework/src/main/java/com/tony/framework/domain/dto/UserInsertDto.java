package com.tony.framework.domain.dto;

import com.tony.framework.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
* 用户表
* @TableName sys_user
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInsertDto extends User {

   private List<Long> roleIds;

}
