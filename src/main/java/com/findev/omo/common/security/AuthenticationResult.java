package com.findev.omo.common.security;

import com.findev.omo.controller.dto.MemberDto;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class AuthenticationResult {

  private final String apiToken;

  private final MemberDto memberDto;

  public String getApiToken() {
    return apiToken;
  }

  @Override
  public String toString() {
    return "AuthenticationResult{" +
      "apiToken='" + apiToken + '\'' +
      ", member=" + memberDto +
      '}';
  }
}