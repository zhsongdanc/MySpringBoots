package com.demus.mysecurity.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demus.mysecurity.repository.entity.UserAuthDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserAuthMapper extends BaseMapper<UserAuthDO> {

    @Select("select * from user_authorizations where user_id=#{userId} ")
    UserAuthDO queryUserId(String userId);
}
