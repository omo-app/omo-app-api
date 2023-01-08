package com.findev.omo.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


// TODO 인증&인가 Exception Handling
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
  static String E403 = "Authentication error (cause: forbidden)";

  private final ObjectMapper om;

  public JwtAccessDeniedHandler(ObjectMapper om) {
    this.om = om;
  }

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    response.setHeader("content-type", "application/json");
    response.getWriter().write(om.writeValueAsString(E403));
    response.getWriter().flush();
    response.getWriter().close();
  }
}
