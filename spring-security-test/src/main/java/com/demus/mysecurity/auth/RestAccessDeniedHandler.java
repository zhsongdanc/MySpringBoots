package com.demus.mysecurity.auth;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {

  public static final String denyJson = "{\"code\": 403, \"message\": \"Forbidden\"}";

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException) throws IOException, ServletException {

    response.setStatus(HttpStatus.FORBIDDEN.value());
    response.setContentType("application/json");

    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(denyJson);

  }

}
