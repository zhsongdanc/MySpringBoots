package com.demus.mysecurity.auth.sso;

import com.demus.mysecurity.dto.HmUserDTO;
import com.demus.mysecurity.repository.entity.UserAuthDO;
import com.demus.mysecurity.repository.mapper.UserAuthMapper;
import com.tencent.hr.message.common.dto.UserType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class TasUserDetailsProvider implements UserDetailsProvider {


    @Override
    public UserAuthDO provide(HttpServletRequest request) {

        String staffId = request.getHeader("staffId");
        String staffName = request.getHeader("staffName");

        log.info("staffId: {} staffName: {}", staffId, staffName);
        UserAuthDO userAuthDO = new UserAuthDO();
        userAuthDO.setUserId(staffId);
        userAuthDO.setUserName(staffName);

        return userAuthDO;
    }
}
