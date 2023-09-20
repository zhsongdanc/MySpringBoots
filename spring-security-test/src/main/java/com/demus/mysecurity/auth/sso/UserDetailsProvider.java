package com.demus.mysecurity.auth.sso;

import com.demus.mysecurity.dto.HmUserDTO;
import com.demus.mysecurity.repository.entity.UserAuthDO;


import javax.servlet.http.HttpServletRequest;

/**
 * 从request中取出sso用户信息
 */
public interface UserDetailsProvider {

    UserAuthDO provide(HttpServletRequest request);
}
