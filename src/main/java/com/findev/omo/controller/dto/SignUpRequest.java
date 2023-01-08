package com.findev.omo.controller.dto;

import com.findev.omo.model.user.Member;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class SignUpRequest {

  private final String memberId;
  private final String memberNickName;
  private final String memberPassword;

  public Member toEntity(){
    return Member.builder()
      .memberPk(UUID.randomUUID().toString())
      .memberId(memberId)
      .memberNickname(memberNickName)
      .memberPassword(memberPassword)
      .build();
  }

  @Override
  public String toString() {
    return "SignUpRequest{" +
      "memberId='" + memberId + '\'' +
      ", memberNickName='" + memberNickName + '\'' +
      ", memberPassword='" + memberPassword + '\'' +
      '}';
  }
}
