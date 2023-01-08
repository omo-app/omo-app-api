package com.findev.omo.controller.dto;

import com.findev.omo.common.security.AuthenticationResult;
import lombok.Getter;
import lombok.Setter;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@Setter
public class AuthenticationResultDto {

  private String apiToken;

  private MemberDto memberDto;

  public AuthenticationResultDto(AuthenticationResult source) {
    copyProperties(source, this);

    this.memberDto = source.getMemberDto();
  }

//  @Override
//  public String toString() {
//    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
//      .append("apiToken", apiToken)
//      .append("member", memberDto)
//      .toString();
//  }

}