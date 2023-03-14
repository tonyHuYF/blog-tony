package com.tony.framework.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
* 标签
* @TableName t_tag
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_tag")
public class Tag implements Serializable {

    /**
    * 
    */
    @TableId
    private Long id;
    /**
    * 标签名
    */
    private String name;
    /**
    * 
    */
    private Long createBy;
    /**
    * 
    */
    private Date createTime;
    /**
    * 
    */
    private Long updateBy;
    /**
    * 
    */
    private Date updateTime;
    /**
    * 删除标志（0代表未删除，1代表已删除）
    */
    private Integer delFlag;
    /**
    * 备注
    */
    private String remark;

}
