package com.demus.mysecurity.repository.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Desc:
 * @Date: 9:20 下午 2022/10/09
 * @Author v_boqiyu
 */
@Data
@TableName("user_authorizations")
@EqualsAndHashCode(callSuper = true)
public class UserAuthDO extends BaseDO {

    /**
     * 用户id
     */

    private String userId;

    /**
     * 角色名称
     */

    private String roleName;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 操作人
     */
    private String operator;

}
