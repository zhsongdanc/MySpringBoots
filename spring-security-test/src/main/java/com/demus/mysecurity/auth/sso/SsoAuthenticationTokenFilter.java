package com.demus.mysecurity.auth.sso;

import com.demus.mysecurity.dto.HmUserDTO;
import com.demus.mysecurity.repository.entity.UserAuthDO;
import com.demus.mysecurity.repository.mapper.UserAuthMapper;
import com.tencent.hr.message.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class SsoAuthenticationTokenFilter extends OncePerRequestFilter {

//    private static final Logger log = LoggerFactory.getLogger(SsoAuthenticationTokenFilter.class);

    @Autowired
    private UserAuthMapper userAuthMapper;
    @Autowired
    private UserDetailsProvider userDetailsProvider;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 如果当前session没有用户信息
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            // 从request获取用户信息
            UserAuthDO user = userDetailsProvider.provide(request);
            if (user != null) {
                // 根据用户id获取用户角色信息
                List<GrantedAuthority> authorities = getAuthorities(user.getUserId(), user.getUserName());

                log.info("id: {} name: {}", user.getUserId(), user.getUserName());
//                log.info("user-{}", JsonUtil.toJson(user));
                // 将用户身份信息存放至session
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,
                        authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }


    private List<GrantedAuthority> getAuthorities(String userId, String userName) {
        List<GrantedAuthority> grants = new ArrayList<>();
        UserAuthDO userAuthDO = userAuthMapper.queryUserId(userId);
        //授权用户
        if (userAuthDO != null && !StringUtils.isEmpty(userAuthDO.getRoleName())) {
            Set<String> auths = new HashSet<>();
            auths.add(userAuthDO.getRoleName());
            List<String> filAuths = auths.stream().filter(e -> !StringUtils.isEmpty(e)).collect(Collectors.toList());
            grants.addAll(filAuths.stream().map(e -> new SimpleGrantedAuthority(e)).collect(Collectors.toList()));
        }

        if(grants.size() == 0) {
            SimpleGrantedAuthority defaultAuthority = new SimpleGrantedAuthority("ROLE_APP_READER");
            return Arrays.asList(defaultAuthority);
        }
        return grants;
    }

}
